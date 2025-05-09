package com.huawei.indoorequip.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.basesport.controls.ControlButtonLayout;
import com.huawei.health.basesport.sportui.OnEndCountdownListener;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.ui.viewholder.CountdownDialog;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipDisplayActivity;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.CircleProgressButtonForIndoorEquip;
import com.huawei.indoorequip.ui.DataFirstPageFragment;
import com.huawei.indoorequip.ui.DataSecondPageFragment;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.cas;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dij;
import defpackage.gwg;
import defpackage.gww;
import defpackage.jbw;
import defpackage.koq;
import defpackage.kyu;
import defpackage.kza;
import defpackage.kzc;
import defpackage.lbb;
import defpackage.lbc;
import defpackage.lbk;
import defpackage.lbv;
import defpackage.nrs;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.StorageParams;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class IndoorEquipDisplayActivity extends BaseActivityOfIndoorEquip implements View.OnClickListener {
    private lbk aw;
    private lbb ay;
    private RelativeLayout az;
    private ControlButtonLayout ba;
    private LinearLayout bb;
    private LinearLayout be;
    private int bf;
    private DataFirstPageFragment bg;
    private List<Fragment> bn;
    private ImageView bo;
    private b br;
    private HealthViewPager bs;
    private SportLaunchParams bu;
    private ImageView bv;
    private DataSecondPageFragment bw;
    private long bt = 0;
    private String bm = b(Locale.getDefault());
    private boolean bl = false;
    private boolean bi = false;
    private final int bh = 2;
    private final int bp = 0;
    private final int bq = 1;
    private boolean bj = true;
    private boolean bk = false;
    private int bd = 0;
    private CountdownDialog bc = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setDefaultOrientation() {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setTahitiModelOrientation() {
    }

    private void al() {
        this.af = new Handler(new Handler.Callback() { // from class: com.huawei.indoorequip.activity.IndoorEquipDisplayActivity.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                IndoorEquipDisplayActivity.this.b(message.what);
                IndoorEquipDisplayActivity.this.a(message.what);
                IndoorEquipDisplayActivity.this.c(message.what);
                int i = message.what;
                if (i != 908) {
                    switch (i) {
                        case 1014:
                            if (IndoorEquipDisplayActivity.this.bg != null && (message.obj instanceof HashMap)) {
                                LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "MSG_UPDATE_UI", message.obj);
                                HashMap hashMap = (HashMap) message.obj;
                                IndoorEquipDisplayActivity.this.c(hashMap);
                                IndoorEquipDisplayActivity.this.bg.d(hashMap);
                                if (IndoorEquipDisplayActivity.this.bw != null) {
                                    IndoorEquipDisplayActivity.this.bw.c(hashMap);
                                    break;
                                } else {
                                    LogUtil.b("Track_IDEQ_IndoorEquipDisplayActivity", "mSecondPageFragment = null");
                                    break;
                                }
                            }
                            break;
                        case 1015:
                            IndoorEquipDisplayActivity.this.r();
                            break;
                        case 1016:
                            int intValue = ((Integer) message.obj).intValue();
                            IndoorEquipDisplayActivity.this.bg.b(intValue);
                            if (IndoorEquipDisplayActivity.this.ax != null) {
                                IndoorEquipDisplayActivity.this.ax.e(intValue);
                                break;
                            }
                            break;
                        case 1017:
                            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "MSG_UPDATE_HEART_VIEW heart rate band connect");
                            IndoorEquipDisplayActivity.this.l = true;
                            if (IndoorEquipDisplayActivity.this.bg != null) {
                                IndoorEquipDisplayActivity.this.bg.f();
                            }
                            IndoorEquipDisplayActivity.this.w();
                            break;
                    }
                    return false;
                }
                LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "in handleMessage, case is FINISH_ACT");
                IndoorEquipDisplayActivity.this.x = true;
                IndoorEquipDisplayActivity.this.finish();
                return false;
            }
        });
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip, com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        super.onResumeSport();
        ControlButtonLayout controlButtonLayout = this.ba;
        if (controlButtonLayout != null) {
            controlButtonLayout.foldBtnStopAndPlay();
        }
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip, com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        super.onPauseSport();
        if (this.ba != null) {
            runOnUiThread(new Runnable() { // from class: kzt
                @Override // java.lang.Runnable
                public final void run() {
                    IndoorEquipDisplayActivity.this.ae();
                }
            });
        }
    }

    public /* synthetic */ void ae() {
        this.ba.showBtnStopAndPlay();
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void r() {
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "initView");
        ap();
        t();
        lbv.d(getApplicationContext(), "onCreate");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.bt;
        long j2 = elapsedRealtime - j;
        if (j > 0 && j2 > 0 && j2 < 1000) {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "initView and will do interceptNfcMessageAndDisplayProjection");
            q();
        } else {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "initView and will NOT do interceptNfcMessageAndDisplayProjection");
        }
        ao();
        if (lbc.e(this.ar)) {
            LogUtil.b("Track_IDEQ_IndoorEquipDisplayActivity", "rope jumping reject display");
        } else {
            z();
        }
    }

    private void an() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.count_down_view);
        this.az = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.indoorequip.activity.IndoorEquipDisplayActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.h("Track_IDEQ_IndoorEquipDisplayActivity", " the countdown view can't click");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CountdownDialog countdownDialog = new CountdownDialog(this.i, getWindow().getDecorView().getRootView());
        this.bc = countdownDialog;
        countdownDialog.addEndCountdown(new OnEndCountdownListener() { // from class: com.huawei.indoorequip.activity.IndoorEquipDisplayActivity.1
            @Override // com.huawei.health.basesport.sportui.OnEndCountdownListener
            public void endCountdown() {
                IndoorEquipDisplayActivity.this.az.setVisibility(8);
                IndoorEquipDisplayActivity.this.bg();
                if (IndoorEquipDisplayActivity.this.bg != null) {
                    IndoorEquipDisplayActivity.this.bg.b();
                }
            }
        });
        this.bc.setTimeStart(5);
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SystemClock.sleep(2000L);
            SportLaunchParams e = cas.e();
            Object[] objArr = new Object[2];
            objArr[0] = "sportLaunchParams == null";
            objArr[1] = Boolean.valueOf(e == null);
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", objArr);
            if (e != null) {
                if (IndoorEquipDisplayActivity.this.ar == 283) {
                    e.addExtra("productId", IndoorEquipDisplayActivity.this.aj);
                }
                e.addExtra("is_course", Boolean.valueOf(IndoorEquipDisplayActivity.this.y));
                e.addExtra("musicPlayConfirm", Boolean.valueOf(IndoorEquipDisplayActivity.this.bi));
                if (IndoorEquipDisplayActivity.this.bw != null) {
                    e.addExtra("is_has_aw70", true);
                }
                e.addExtra("has_heart_rate_device_connect", Boolean.valueOf(IndoorEquipDisplayActivity.this.l));
                if (IndoorEquipDisplayActivity.this.au != null && IndoorEquipDisplayActivity.this.au.e() != null) {
                    e.addExtra("deviceMac", IndoorEquipDisplayActivity.this.au.e().getBtMac());
                    e.addExtra("deviceName", IndoorEquipDisplayActivity.this.au.e().getBtName());
                }
                Intent intent = new Intent();
                e.setRestart(true);
                if (IndoorEquipDisplayActivity.this.aq != null) {
                    e.addExtra("supportDataRange", IndoorEquipDisplayActivity.this.aq);
                }
                intent.putExtra("bundle_key_sport_launch_paras", e);
                intent.addFlags(268435456);
                intent.setClass(IndoorEquipDisplayActivity.this.getApplication(), IndoorEquipDisplayActivity.class);
                IndoorEquipDisplayActivity.this.getApplication().startActivity(intent);
            }
        }
    }

    private void e(boolean z) {
        SharedPreferences.Editor edit;
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "editKillBySelf ", Boolean.valueOf(z));
        Context context = this.i;
        Context context2 = this.i;
        SharedPreferences sharedPreferences = context.getSharedPreferences("IndoorEquipServiceRunning", 0);
        if (sharedPreferences == null || (edit = sharedPreferences.edit()) == null) {
            return;
        }
        edit.putBoolean("isKillBySelf", z);
        edit.apply();
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void c() {
        DataSecondPageFragment dataSecondPageFragment;
        super.c();
        this.bg.c();
        if (!aw() || (dataSecondPageFragment = this.bw) == null) {
            return;
        }
        dataSecondPageFragment.b();
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void h() {
        DataSecondPageFragment dataSecondPageFragment;
        super.h();
        DataFirstPageFragment dataFirstPageFragment = this.bg;
        if (dataFirstPageFragment != null) {
            dataFirstPageFragment.e();
        }
        if (!aw() || (dataSecondPageFragment = this.bw) == null) {
            return;
        }
        dataSecondPageFragment.a();
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void i() {
        DataSecondPageFragment dataSecondPageFragment;
        super.i();
        DataFirstPageFragment dataFirstPageFragment = this.bg;
        if (dataFirstPageFragment != null) {
            dataFirstPageFragment.e();
        }
        if (!aw() || (dataSecondPageFragment = this.bw) == null) {
            return;
        }
        dataSecondPageFragment.a();
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void j() {
        DataSecondPageFragment dataSecondPageFragment;
        super.j();
        DataFirstPageFragment dataFirstPageFragment = this.bg;
        if (dataFirstPageFragment != null) {
            dataFirstPageFragment.d();
        }
        if (!aw() || (dataSecondPageFragment = this.bw) == null) {
            return;
        }
        dataSecondPageFragment.d();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.voice_image) {
            lbv.c(getApplicationContext(), "VoiceBtn");
            this.bj = !gww.a();
            be();
            gww.b(this.bj);
        } else if (id == R.id.horizontal_screen) {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "mCurrentOrientation = ", Integer.valueOf(this.bf), " hasRunPostureDevice = ", Boolean.valueOf(this.t));
            this.u = false;
            bh();
        } else {
            LogUtil.b("Track_IDEQ_IndoorEquipDisplayActivity", "onClick viewId is not used.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bh() {
        lbv.c(getApplicationContext(), "horizontalScreenBtn");
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        sportLaunchParams.setSportType(this.ar);
        sportLaunchParams.addExtra("musicPlayConfirm", Boolean.valueOf(this.bi));
        sportLaunchParams.addExtra("KEY_IS_SWITCH_TO_HORIZONTAL_SCREEN", true);
        sportLaunchParams.addExtra("has_heart_rate_device_connect", Boolean.valueOf(this.l));
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "startLandActivity mHasHeartRateDevice =  ", Boolean.valueOf(this.l));
        if (!TextUtils.isEmpty(this.ak)) {
            sportLaunchParams.addExtra("sportMode", this.ak);
        }
        sportLaunchParams.setSportTarget(this.ap);
        sportLaunchParams.setTargetValue(this.ao);
        sportLaunchParams.addExtra("productId", this.aj);
        if (this.au != null) {
            sportLaunchParams.addExtra("key_progress", Integer.valueOf(this.au.l()));
        }
        if (this.ar == 283 && this.ap == 8) {
            sportLaunchParams.addExtra("type_intermittent_jump_mode_data", this.q);
        }
        c(false);
        Intent intent = new Intent(this, (Class<?>) IndoorEquipLandDisplayActivity.class);
        if (this.aq != null) {
            sportLaunchParams.addExtra("supportDataRange", this.aq);
        }
        intent.putExtra("bundle_key_sport_launch_paras", sportLaunchParams);
        intent.putExtra("ExitApp", this.v);
        intent.putExtra("key_is_keep_connect", this.aa);
        this.bl = true;
        startActivity(intent);
        this.x = true;
        finish();
    }

    public static boolean a(Context context, int i) {
        return CommonUtil.bd() && gwg.a(context) && new gww(context, new StorageParams(), Integer.toString(20002)).f(i) == 1 && lbv.b(i);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "onSaveInstanceState");
        if (bundle == null) {
            return;
        }
        super.onSaveInstanceState(bundle);
        bundle.remove("android:support:fragments");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "onDestroy");
        if (!this.x) {
            LogUtil.h("Track_IDEQ_IndoorEquipDisplayActivity", "unnormal onDestroy");
            List<Fragment> list = this.bn;
            if (list != null) {
                list.clear();
            }
            this.br = new b();
            ThreadPoolManager.d().execute(this.br);
            if (this.k != null) {
                this.k.setIsInit(false);
            }
        } else {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "normal onDestroy");
            this.ac = false;
            if (this.ax != null && !this.bl && !this.bk) {
                this.ax.c();
            }
            if (this.k != null && this.u) {
                this.k.destroyWirelessProjection();
            }
            e(false);
        }
        lbv.d(getApplicationContext(), "onDestroy");
        this.r = kzc.n();
        this.r.e(false);
        e();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        e(true);
        r();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        boolean at = at();
        this.bk = at;
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "isLanguageChanged = ", Boolean.valueOf(at));
        if (this.bk) {
            ay();
        } else if (nsn.ae(this.i.getApplicationContext())) {
            e(true);
            r();
        } else {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "onConfigurationChanged is not pad");
        }
    }

    private void ay() {
        Intent intent = new Intent();
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        sportLaunchParams.setSportType(this.ar);
        intent.putExtra("bundle_key_sport_launch_paras", sportLaunchParams);
        intent.setClass(this, IndoorEquipDisplayActivity.class);
        this.x = true;
        finish();
        startActivity(intent);
    }

    private boolean at() {
        String b2 = b(Locale.getDefault());
        if (b2.equals(this.bm)) {
            return false;
        }
        this.bm = b2;
        return true;
    }

    private String b(Locale locale) {
        if (locale == null) {
            return "";
        }
        return locale.getLanguage() + Constants.LINK + locale.getCountry();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "will do onResume and do interceptNfcMessageAndDisplayProjection later");
        getWindow().addFlags(128);
        lbv.d(getApplicationContext(), "onResume");
        q();
        this.bt = SystemClock.elapsedRealtime();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        Parcelable[] parcelableArr;
        super.onNewIntent(intent);
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "onNewIntent");
        if (intent != null) {
            if (this.d != null && intent.getBooleanExtra("show tips key", false)) {
                this.d.a(this);
                this.d.b();
            }
            if ("android.nfc.action.NDEF_DISCOVERED".equals(intent.getAction())) {
                LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "onNewIntent, action is ACTION_NDEF_DISCOVERED");
                String str = null;
                try {
                    parcelableArr = intent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
                } catch (ArrayIndexOutOfBoundsException unused) {
                    LogUtil.b("Track_IDEQ_IndoorEquipDisplayActivity", "onNewIntent ArrayIndexOutOfBoundsException");
                    parcelableArr = null;
                }
                if (parcelableArr == null || parcelableArr.length == 0) {
                    return;
                }
                Parcelable parcelable = parcelableArr[0];
                NdefMessage ndefMessage = parcelable instanceof NdefMessage ? (NdefMessage) parcelable : null;
                if (ndefMessage != null) {
                    try {
                        if (koq.e(ndefMessage.getRecords(), 0) && ndefMessage.getRecords()[0] != null) {
                            str = new String(ndefMessage.getRecords()[0].getPayload(), "UTF-8");
                        }
                    } catch (UnsupportedEncodingException unused2) {
                        LogUtil.b("Track_IDEQ_IndoorEquipDisplayActivity", "onNewIntent, UnsupportedEncodingException");
                    }
                }
                c(str);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "IndoorEquipDisplayActivity, onPause");
        super.onPause();
        this.bt = 0L;
        if (this.z && this.ae != null) {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "NFC DisableForegroundDispatch");
            this.ae.disableForegroundDispatch(this);
        }
        lbv.d(getApplicationContext(), "onPause");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "onCreate");
        this.i = this;
        al();
        cancelMarginAdaptation();
        this.s = new kyu(this, this.af, false);
        this.k = (DistributedApi) Services.a("DistributedService", DistributedApi.class);
        if (this.k != null) {
            this.k.init(this.i);
            this.k.detectLastWirelessDevice();
        } else {
            LogUtil.b("Track_IDEQ_IndoorEquipDisplayActivity", "can not get DistributedApi.");
        }
        this.d = new kza(null, this, null);
        this.d.a(this.i);
        this.d.bTd_(this.af);
        ak();
        clearBackgroundDrawable();
        setContentView(R.layout.display_activity_layout);
        y();
        aq();
        r();
        getWindow().setStatusBarColor(0);
        getWindow().getDecorView().setSystemUiVisibility(1024);
        getWindow().addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        getWindow().addFlags(128);
    }

    private void ak() {
        Intent intent = getIntent();
        if (intent != null) {
            this.v = intent.getBooleanExtra("ExitApp", false);
            LogUtil.c("Track_IDEQ_IndoorEquipDisplayActivity", "acquireDataFromIntent ExitApp ", Boolean.valueOf(this.v));
            this.aa = intent.getBooleanExtra("key_is_keep_connect", false);
            SportLaunchParams sportLaunchParams = (SportLaunchParams) intent.getParcelableExtra("bundle_key_sport_launch_paras");
            this.bu = sportLaunchParams;
            if (sportLaunchParams != null) {
                this.ar = sportLaunchParams.getSportType();
                this.bu.setLaunchActivity(IndoorEquipDisplayActivity.class.getName());
                this.bi = ((Boolean) this.bu.getExtra("musicPlayConfirm", Boolean.class, false)).booleanValue();
                this.aq = (SupportDataRange) this.bu.getExtra("supportDataRange", SupportDataRange.class);
                this.ap = this.bu.getSportTarget();
                this.ao = this.bu.getTargetValue();
                if (this.ar == 283) {
                    this.aj = (String) this.bu.getExtra("productId", String.class);
                    as();
                    bc();
                }
                this.r = kzc.n();
                this.r.e(true);
                if (this.ar == 283) {
                    this.r.c(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED);
                }
                if (this.ar != 0) {
                    this.r.c(this.ar);
                } else {
                    this.ar = this.r.r();
                }
                bTb_(this.bu, this.af, this.ar);
                this.l = this.l ? true : ((Boolean) this.bu.getExtra("has_heart_rate_device_connect", Boolean.class, false)).booleanValue();
                LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "acquireDataFromIntent: mHasHeartRateDevice = ", Boolean.valueOf(this.l));
                this.au.d((String) this.bu.getExtra("deviceMac", String.class), (String) this.bu.getExtra("deviceName", String.class));
                this.as = this.au.getSportStatus();
            }
        }
    }

    private void as() {
        if (this.ap == 8) {
            this.q = (IntermitentJumpData) this.bu.getExtra("type_intermittent_jump_mode_data", IntermitentJumpData.class, new IntermitentJumpData());
        }
    }

    private void aq() {
        this.bf = getResources().getConfiguration().orientation;
        this.r = kzc.n();
        this.s.e(true);
        this.ah = false;
        this.ag = false;
        this.z = getPackageManager().hasSystemFeature("android.hardware.nfc");
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "this phone supports Nfc? ", Boolean.valueOf(this.z));
    }

    private void ao() {
        Context context = this.i;
        Context context2 = this.i;
        SharedPreferences sharedPreferences = context.getSharedPreferences("IndoorEquipServiceRunning", 0);
        if (sharedPreferences != null) {
            this.ac = sharedPreferences.getBoolean("isKillBySelf", false);
        }
        if (!this.ac && ax()) {
            this.az.setVisibility(0);
            this.az.bringToFront();
            this.bc.startCountdown();
        } else {
            this.az.setVisibility(8);
        }
        if (this.bu == null || !lbc.e(this.ar) || this.ac || !this.bu.isRestart()) {
            return;
        }
        bb();
    }

    private boolean ax() {
        SportLaunchParams sportLaunchParams = this.bu;
        if (sportLaunchParams == null) {
            return false;
        }
        return (!lbc.e(this.ar) || this.bu.isRestart() || ((Boolean) sportLaunchParams.getExtra("KEY_IS_SWITCH_TO_HORIZONTAL_SCREEN", Boolean.class, false)).booleanValue()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bg() {
        if (this.au != null) {
            this.au.onStartSport();
        }
    }

    private void bb() {
        bg();
    }

    private void ap() {
        ImageView imageView;
        au();
        an();
        lbk lbkVar = new lbk(getSupportFragmentManager(), this.bn);
        this.aw = lbkVar;
        this.bs.setAdapter(lbkVar);
        this.bs.setCurrentItem(0);
        int i = this.bd;
        if (i != 0) {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "initData mCurrentSelectedPosition is", Integer.valueOf(i));
            this.bs.setCurrentItem(this.bd);
        }
        this.bs.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.indoorequip.activity.IndoorEquipDisplayActivity.4
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                IndoorEquipDisplayActivity.this.bd = i2;
                if (i2 == 1) {
                    LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "setOnPageChangeListener position = 1");
                } else if (i2 == 0 && IndoorEquipDisplayActivity.this.t) {
                    LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "setOnPageChangeListener position = 0 && hasRunPostureDevice = true");
                } else {
                    LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "setOnPageChangeListener position:", Integer.valueOf(i2));
                }
            }
        });
        aj();
        ar();
        az();
        if (this.au != null) {
            this.au.v();
        }
        am();
        if (!isInMultiWindowMode() || (imageView = this.bo) == null) {
            return;
        }
        imageView.setVisibility(8);
    }

    private void am() {
        this.e = (CircleProgressButtonForIndoorEquip) findViewById(R.id.track_main_page_btn_stop);
        p();
        if (this.ar == 283) {
            this.e.setVisibility(8);
        } else {
            this.bb.setVisibility(8);
        }
    }

    private void ar() {
        this.bb = (LinearLayout) findViewById(R.id.control_button_linearlayout);
        ControlButtonLayout controlButtonLayout = (ControlButtonLayout) findViewById(R.id.control_button);
        this.ba = controlButtonLayout;
        controlButtonLayout.setClickCallback(new IBaseResponseCallback() { // from class: kzs
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                IndoorEquipDisplayActivity.this.b(i, obj);
            }
        });
        this.ba.setScreenSwitchLength(this.bo, this.bv);
        if (this.ar == 283) {
            this.ba.setPauseButtonPading();
        }
    }

    public /* synthetic */ void b(int i, Object obj) {
        if (this.au == null) {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "mViewModel == null ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "mControlButtonLayout click code= ", Integer.valueOf(i));
        if (i == 101) {
            this.au.onPauseSport();
            return;
        }
        if (i == 100) {
            this.au.onResumeSport();
        } else if (i == 102) {
            this.au.e(true);
        } else {
            LogUtil.h("Track_IDEQ_IndoorEquipDisplayActivity", "error code ", Integer.valueOf(i));
        }
    }

    private void bc() {
        dcz d = ResourceManager.e().d(this.aj);
        if (d == null) {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "saveDeviceName productInfo is null");
            return;
        }
        String d2 = dcx.d(this.aj, d.n().b());
        this.bu.addExtra("deviceName", d2);
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences(PluginPayAdapter.KEY_DEVICE_INFO_NAME, 0).edit();
        if (edit != null) {
            edit.putString("device_name_key", d2);
            edit.apply();
        }
    }

    private void az() {
        if (!dij.c(this.ar, this.ap)) {
            this.ba.setHidePauseAndStopButton();
        }
        if (this.as == 2) {
            this.ba.a();
        }
    }

    private void aj() {
        if (jbw.c(this.i)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.indoorequip.activity.IndoorEquipDisplayActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "updateProductMapConfig is ", Boolean.valueOf(jbw.d(IndoorEquipDisplayActivity.this.i, 2)));
                }
            });
        } else {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "loadProductMapConfig is ", Boolean.valueOf(ProductMapParseUtil.b(this.i)));
        }
    }

    private void au() {
        this.bm = b(Locale.getDefault());
        this.al = (ImageView) findViewById(R.id.runway);
        this.am = (ImageView) findViewById(R.id.runwayBackground);
        this.be = (LinearLayout) findViewById(R.id.dots_layout);
        this.bo = (ImageView) findViewById(R.id.horizontal_screen);
        this.bv = (ImageView) findViewById(R.id.voice_image);
        this.bs = (HealthViewPager) findViewById(R.id.mViewPager);
        ba();
        if (lbv.c(this.ar)) {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "mVoiceIcon hide");
            this.bv.setVisibility(4);
        } else {
            this.bv.setOnClickListener(this);
            this.bj = gww.a();
            be();
        }
        this.bo.setOnClickListener(this);
        if (this.ay == null) {
            this.ay = new lbb(this.i, this.be, 2);
        }
        this.bs.addOnPageChangeListener(this.ay);
        this.bn = new ArrayList(16);
        Bundle bundle = new Bundle();
        bundle.putInt("deviceType", this.ar);
        bundle.putInt("currentSkipperTargetType", this.ap);
        bundle.putInt("currentSkipperTarget", (int) this.ao);
        if (this.ar == 283 && this.ap == 8) {
            bundle.putParcelable("type_intermittent_jump_mode_data", this.q);
        }
        this.bg = DataFirstPageFragment.bVw_(bundle);
        if (av()) {
            this.bw = new DataSecondPageFragment();
            this.bn.add(this.bg);
            this.bn.add(this.bw);
        } else {
            this.be.setVisibility(8);
            this.bn.add(this.bg);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        ImageView imageView = this.bo;
        if (imageView == null) {
            LogUtil.h("Track_IDEQ_IndoorEquipDisplayActivity", "onMultiWindowModeChanged mScreenSwitch is null.");
        } else {
            imageView.setVisibility(z ? 8 : 0);
        }
    }

    private void be() {
        if (this.bj) {
            this.bv.setImageDrawable(getResources().getDrawable(R.drawable._2131430359_res_0x7f0b0bd7));
        } else {
            this.bv.setImageDrawable(getResources().getDrawable(R.drawable._2131430360_res_0x7f0b0bd8));
        }
    }

    private void ba() {
        BitmapDrawable cKn_;
        int dimensionPixelOffset;
        if (this.ar == 283) {
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "set skipping");
            try {
                findViewById(R.id.linearlayout_display).setBackgroundResource(R.drawable._2131430361_res_0x7f0b0bd9);
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("Track_IDEQ_IndoorEquipDisplayActivity", "Resources NotFoundException");
            }
            this.al.setVisibility(8);
            this.am.setVisibility(8);
            if (!"1".equals(dij.c("pageVersion", this.aj))) {
                this.bo.setVisibility(4);
            }
            this.bo.setImageDrawable(getResources().getDrawable(R.drawable._2131430347_res_0x7f0b0bcb));
            this.bs.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 1.0f));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            if (nrs.e(this.i)) {
                dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131362954_res_0x7f0a048a);
            } else {
                dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131362860_res_0x7f0a042c);
            }
            layoutParams.bottomMargin = dimensionPixelOffset;
            ((RelativeLayout) findViewById(R.id.bottom_layout)).setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.topMargin = 0;
            layoutParams2.addRule(14);
            ((HealthColumnRelativeLayout) findViewById(R.id.btn_layout)).setLayoutParams(layoutParams2);
            bd();
            return;
        }
        if (!LanguageUtil.bc(this.i) || (cKn_ = nrz.cKn_(this.i, R.drawable._2131430347_res_0x7f0b0bcb)) == null) {
            return;
        }
        this.bo.setImageDrawable(cKn_);
    }

    private void bd() {
        int c = nsn.c(this.i, 50.0f);
        if (this.bv != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(c, c);
            layoutParams.addRule(15);
            layoutParams.addRule(21);
            this.bv.setLayoutParams(layoutParams);
        }
        ImageView imageView = this.bo;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
            if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
                layoutParams3.width = c;
                layoutParams3.height = c;
                this.bo.setLayoutParams(layoutParams3);
            }
        }
    }

    private boolean av() {
        return this.ar != 283 && (aw() || !(this.ar == 264 || this.ar == 281));
    }

    private boolean aw() {
        return this.au != null && (this.au.s() || this.au.t());
    }

    public boolean ag() {
        return this.l;
    }

    public boolean ai() {
        return this.au != null && this.au.t();
    }

    public int ah() {
        if (this.au == null) {
            return 0;
        }
        return this.au.n();
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void c(Map<Integer, Object> map) {
        if (this.af == null) {
            return;
        }
        if (this.as == 1 && !this.bi && a(this.i, this.ar) && SportMusicController.a().j() != null) {
            SportMusicController.a().b(this.ar);
            this.bi = true;
            LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "play song list:", Integer.valueOf(SportMusicController.a().j().getState()));
        }
        if (map == null) {
            return;
        }
        LogUtil.a("Track_IDEQ_IndoorEquipDisplayActivity", "indoorEquipData: ", map.toString());
        if (this.ax != null) {
            this.ax.b(map);
        }
        if (this.ar == 274 && (map.get(26) instanceof Float)) {
            c = ((Float) map.get(26)).floatValue();
        } else if (this.ar != 283 && (map.get(3) instanceof Integer)) {
            c = ((Integer) map.get(3)).intValue() / 100.0f;
        } else {
            LogUtil.b("Track_IDEQ_IndoorEquipDisplayActivity", "mSportType is ", Integer.valueOf(this.ar));
        }
    }

    public void af() {
        if (this.k != null) {
            this.k.startWirelessProjection();
        }
    }
}
