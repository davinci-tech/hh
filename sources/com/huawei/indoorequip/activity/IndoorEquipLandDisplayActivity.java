package com.huawei.indoorequip.activity;

import android.content.Intent;
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
import android.view.ViewStub;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.basesport.controls.ControlButtonLayout;
import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipLandDisplayActivity;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.subscriptionmodel.UiUnsubscribeDataCallback;
import com.huawei.indoorequip.ui.CircleProgressButtonForIndoorEquip;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.cas;
import defpackage.dij;
import defpackage.diy;
import defpackage.ffs;
import defpackage.fgd;
import defpackage.gwh;
import defpackage.gww;
import defpackage.hln;
import defpackage.koq;
import defpackage.kyu;
import defpackage.kza;
import defpackage.kzc;
import defpackage.lbc;
import defpackage.lbj;
import defpackage.lbv;
import defpackage.lcd;
import defpackage.lce;
import defpackage.lcf;
import defpackage.lch;
import defpackage.lci;
import defpackage.lcj;
import defpackage.lcp;
import defpackage.lcr;
import defpackage.lcs;
import defpackage.lct;
import defpackage.lcw;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class IndoorEquipLandDisplayActivity extends BaseActivityOfIndoorEquip implements View.OnClickListener, UiUnsubscribeDataCallback {
    private ImageView ay;
    private int az;
    private int ba;
    private int bb;
    private ImageView bc;
    private HealthTextView bd;
    private HealthTextView be;
    private HealthTextView bf;
    private RelativeLayout bg;
    private ControlButtonLayout bh;
    private HealthTextView bi;
    private ImageView bj;
    private HealthTextView bk;
    private lcd bl;
    private RelativeLayout bm;
    private boolean bo;
    private int bu;
    private LinearLayout bw;
    private int bx;
    private int by;
    private ImageView bz;
    private ImageView ca;
    private HealthProgressBar cb;
    private ViewStub cc;
    private ImageView cd;
    private ViewStub ce;
    private ViewStub cf;
    private lce cg;
    private gww ch;
    private HealthTextView ci;
    private HealthTextView cj;
    private HealthTextView ck;
    private HealthTextView cl;
    private HealthTextView cm;
    private HealthTextView cn;
    private HealthTextView co;
    private HealthTextView cp;
    private HealthTextView cq;
    private ImageView cr;
    private int cs;
    private int ct;
    private HealthTextView cv;
    private long bs = 0;
    private Map<SportDetailChartDataType, BaseRealTimeDynamicChartViewModel> aw = new HashMap();
    private boolean bq = false;
    private boolean bt = true;
    private volatile boolean br = false;
    private boolean bp = true;
    private boolean bv = false;
    private boolean bn = false;

    @Override // com.huawei.indoorequip.subscriptionmodel.UiUnsubscribeDataCallback
    public void onResult(Object obj, int i) {
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "unregister ui data listener result", Integer.valueOf(i));
    }

    private void ah() {
        this.af = new Handler(new Handler.Callback() { // from class: lab
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return IndoorEquipLandDisplayActivity.this.bTI_(message);
            }
        });
    }

    public /* synthetic */ boolean bTI_(Message message) {
        b(message.what);
        c(message.what);
        a(message.what);
        int i = message.what;
        if (i == 908) {
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "in handleMessage, case is FINISH_ACT");
            this.x = true;
            finish();
            return false;
        }
        switch (i) {
            case 1014:
                if (message.obj instanceof HashMap) {
                    LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "MSG_UPDATE_UI", message.obj);
                    HashMap hashMap = (HashMap) message.obj;
                    if (this.ar == 283) {
                        b(hashMap);
                        break;
                    } else if (this.bl != null) {
                        c(hashMap);
                        this.bl.e(hashMap);
                        b(hashMap, UnitUtil.h());
                        f(hashMap);
                        break;
                    }
                }
                break;
            case 1015:
                r();
                break;
            case 1016:
                e(((Integer) message.obj).intValue());
                break;
            case 1017:
                LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "MSG_UPDATE_HEART_VIEW heart rate band connect");
                this.l = true;
                r();
                w();
                break;
        }
        return false;
    }

    public void e(int i) {
        HealthProgressBar healthProgressBar = this.cb;
        if (healthProgressBar != null) {
            healthProgressBar.setProgress(i);
        }
        lce lceVar = this.cg;
        if (lceVar != null) {
            lceVar.a(i);
        }
        if (this.ax != null) {
            this.ax.e(i);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        r();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "onCreate");
        at();
        ah();
        this.i = this;
        this.s = new kyu(this, this.af, false);
        this.k = (DistributedApi) Services.a("DistributedService", DistributedApi.class);
        if (this.k != null) {
            this.k.init(this.i);
            this.k.detectLastWirelessDevice();
        } else {
            LogUtil.b("Track_IDEQ_IndoorEquipLandDisplayActivity", "can not get DistributedApi.");
        }
        this.d = new kza(null, null, this);
        this.d.a(this.i);
        this.d.bTd_(this.af);
        clearBackgroundDrawable();
        y();
        Intent intent = getIntent();
        if (intent != null) {
            this.v = getIntent().getBooleanExtra("ExitApp", false);
            this.aa = intent.getBooleanExtra("key_is_keep_connect", false);
            bTH_(intent);
        }
        LogUtil.c("Track_IDEQ_IndoorEquipLandDisplayActivity", "acquireDataFromIntent ExitApp ", Boolean.valueOf(this.v));
        if (this.au != null) {
            this.as = this.au.getSportStatus();
        }
        ao();
        r();
    }

    private void bTH_(Intent intent) {
        SportLaunchParams sportLaunchParams = (SportLaunchParams) intent.getParcelableExtra("bundle_key_sport_launch_paras");
        if (sportLaunchParams != null) {
            sportLaunchParams.setLaunchActivity(IndoorEquipLandDisplayActivity.class.getName());
            this.ar = sportLaunchParams.getSportType();
            this.ap = sportLaunchParams.getSportTarget();
            this.ao = sportLaunchParams.getTargetValue();
            this.bo = ((Boolean) sportLaunchParams.getExtra("musicPlayConfirm", Boolean.class, false)).booleanValue();
            this.aq = (SupportDataRange) sportLaunchParams.getExtra("supportDataRange", SupportDataRange.class);
            this.r = kzc.n();
            this.r.c(true);
            if (this.ar != 0) {
                this.r.c(this.ar);
            } else {
                this.ar = this.r.r();
            }
            d(sportLaunchParams);
            bTb_(sportLaunchParams, this.af, this.ar);
            this.l = this.l ? true : ((Boolean) sportLaunchParams.getExtra("has_heart_rate_device_connect", Boolean.class, false)).booleanValue();
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "setSportLaunchParams: mHasHeartRateDevice=", Boolean.valueOf(this.l));
        }
    }

    private void d(SportLaunchParams sportLaunchParams) {
        if (this.ar == 283) {
            this.aj = (String) sportLaunchParams.getExtra("productId", String.class);
            if (this.ap == 8) {
                this.q = (IntermitentJumpData) sportLaunchParams.getExtra("type_intermittent_jump_mode_data", IntermitentJumpData.class, new IntermitentJumpData());
            }
        }
    }

    private void at() {
        this.ch = new gww(this.i, new StorageParams(), Integer.toString(20002));
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void r() {
        boolean ag = nsn.ag(getApplicationContext());
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "initView: isTahitiModel=", Boolean.valueOf(ag));
        if (ag) {
            setContentView(R.layout.ie_data_show_landscape_aw70_tahiti);
        } else {
            setContentView(R.layout.ie_data_show_landscape_aw70);
        }
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & (-8193));
        window.addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        window.setStatusBarColor(getColor(R.color._2131298134_res_0x7f090756));
        window.setNavigationBarColor(getColor(R.color._2131298134_res_0x7f090756));
        an();
        t();
        e("onCreate");
        z();
        this.bn = true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "onResume");
        getWindow().addFlags(128);
        e("onResume");
        q();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "IndoorEquipLandDisplayActivity, onPause");
        super.onPause();
        if (this.z && this.ae != null) {
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "NFC DisableForegroundDispatch");
            this.ae.disableForegroundDispatch(this);
        }
        e("onPause");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        Parcelable[] parcelableArr;
        super.onNewIntent(intent);
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "enter onNewIntent");
        if (intent != null) {
            if (this.d != null && intent.getBooleanExtra("show tips key", false)) {
                this.d.a(this);
                this.d.b();
            }
            if ("android.nfc.action.NDEF_DISCOVERED".equals(intent.getAction())) {
                LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "onNewIntent, action is equal to ACTION_NDEF_DISCOVERED");
                String str = null;
                try {
                    parcelableArr = intent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
                } catch (ArrayIndexOutOfBoundsException unused) {
                    LogUtil.b("Track_IDEQ_IndoorEquipLandDisplayActivity", "onNewIntent ArrayIndexOutOfBoundsException when intent getParcelableArrayExtra.");
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
                        LogUtil.b("Track_IDEQ_IndoorEquipLandDisplayActivity", "onNewIntent, UnsupportedEncodingException");
                    }
                }
                c(str);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "onDestroy");
        if (!this.x) {
            af();
            if (this.k != null) {
                this.k.setIsInit(false);
            }
        } else {
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "normal onDestroy");
            if (this.ax != null && !this.bv) {
                this.ax.c();
            }
            if (this.k != null && this.u) {
                this.k.destroyWirelessProjection();
                LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "exit the treadmill exercise, you need to disconnect the Projection.");
            }
        }
        e("onDestroy");
        this.r.c(false);
        e();
    }

    private void e(String str) {
        if (this.au != null) {
            lbv.e(getApplicationContext(), str, this.au.o());
        }
    }

    private void af() {
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "unnormal onDestroy");
        ThreadPoolManager.d().execute(new Runnable() { // from class: kzz
            @Override // java.lang.Runnable
            public final void run() {
                IndoorEquipLandDisplayActivity.this.ae();
            }
        });
    }

    public /* synthetic */ void ae() {
        SystemClock.sleep(2000L);
        SportLaunchParams e = cas.e();
        Object[] objArr = new Object[2];
        objArr[0] = "sportLaunchParams == null";
        objArr[1] = Boolean.valueOf(e == null);
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", objArr);
        if (e != null) {
            e.addExtra("musicPlayConfirm", Boolean.valueOf(this.bo));
            if (this.bq) {
                e.addExtra("is_has_aw70", true);
            }
            e.addExtra("has_heart_rate_device_connect", Boolean.valueOf(this.l));
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "onDestroy, mHasHeartRateDevice = ", Boolean.valueOf(this.l));
            Intent intent = new Intent();
            e.setRestart(true);
            if (!TextUtils.isEmpty(this.ak)) {
                e.addExtra("sportMode", this.ak);
            }
            if (this.aq != null) {
                e.addExtra("supportDataRange", this.aq);
            }
            intent.addFlags(268435456);
            intent.putExtra("bundle_key_sport_launch_paras", e);
            intent.putExtra("ExitApp", this.v);
            intent.setClass(getApplication(), IndoorEquipDisplayActivity.class);
            getApplication().startActivity(intent);
        }
    }

    private void an() {
        lcd lcdVar = new lcd(getWindow().getDecorView().getRootView(), this.l, this.au, this);
        this.bl = lcdVar;
        lcdVar.c(this.aq);
        this.bc = (ImageView) findViewById(R.id.background_layout);
        aj();
        this.f6418a = (HealthProgressBar) findViewById(R.id.hw_recycler_loading_hpb);
        this.g = (ImageView) findViewById(R.id.ie_bt_icon);
        this.ay = (ImageView) findViewById(R.id.ie_bolt_icon);
        this.g.setImageDrawable(lbv.bVR_(this.i, lbj.c(this.ar, true)));
        if (this.au != null && this.au.t()) {
            this.ay.setVisibility(0);
            this.ay.setImageResource(lbv.a(this.au.n()));
        }
        this.al = (ImageView) findViewById(R.id.runway);
        this.am = (ImageView) findViewById(R.id.runwayBack);
        if (this.l) {
            if (this.ce != null) {
                RelativeLayout relativeLayout = this.bm;
                if (relativeLayout != null) {
                    relativeLayout.setVisibility(0);
                }
                ViewGroup.LayoutParams layoutParams = this.ce.getLayoutParams();
                layoutParams.width = -2;
                this.ce.setLayoutParams(layoutParams);
                ar();
            }
        } else {
            az();
        }
        this.ca = (ImageView) findViewById(R.id.ie_music_icon);
        ba();
        ax();
        if (this.ar == 283) {
            av();
            aq();
        }
        if (this.au != null) {
            this.au.v();
        }
    }

    private void aq() {
        this.al.setVisibility(8);
        this.am.setVisibility(8);
        this.bc.setVisibility(8);
    }

    private void az() {
        RelativeLayout relativeLayout = this.bm;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
            this.bc.setVisibility(0);
        }
        if (this.bd == null || this.bf == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, nsn.c(this, 18.0f), 0, 0);
        layoutParams.addRule(3, R.id.distance_value);
        layoutParams.addRule(14);
        this.bf.setLayoutParams(layoutParams);
        bg();
        this.bd.setTextSize(1, 64.0f);
        this.bf.setTextSize(1, 14.0f);
    }

    private void bg() {
        if (nsn.ag(getApplicationContext()) && (this.bg.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.bg.getLayoutParams();
            layoutParams.setMargins(0, nsn.c(this, 20.0f), 0, 0);
            layoutParams.addRule(13);
            this.bg.setLayoutParams(layoutParams);
        }
    }

    private void ba() {
        this.cd = (ImageView) findViewById(R.id.horizontal_screen);
        this.ca.setImageDrawable(lbv.bVR_(this.i, R.drawable._2131430349_res_0x7f0b0bcd));
        this.cd.setImageDrawable(lbv.bVR_(this.i, R.drawable._2131430347_res_0x7f0b0bcb));
        if (this.ar == 283) {
            this.cd.setVisibility(8);
            ImageView imageView = (ImageView) findViewById(R.id.ie_rope_screen_icon);
            this.cd = imageView;
            imageView.setVisibility(0);
        }
    }

    private void ax() {
        this.bz = (ImageView) findViewById(R.id.ie_miracast_icon);
        if (Utils.d() == 1 && CommonUtil.bh() && CommonUtil.ba()) {
            this.bz.setVisibility(0);
            this.bz.setOnClickListener(this);
        } else {
            this.bz.setVisibility(4);
        }
        this.e = (CircleProgressButtonForIndoorEquip) findViewById(R.id.ie_track_main_page_btn_stop);
        p();
        this.cr = (ImageView) findViewById(R.id.ie_voice_icon);
        if (this.ar == 283) {
            this.bz.setVisibility(8);
            this.cr.setVisibility(8);
            ImageView imageView = (ImageView) findViewById(R.id.ie_rope_voice_icon);
            this.cr = imageView;
            imageView.setVisibility(0);
            this.e.setVisibility(8);
        }
        if (lbv.c(this.ar)) {
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "mVoiceIcon hide");
            this.cr.setVisibility(4);
        } else {
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "isRealChineseMainLand");
            this.cr.setOnClickListener(this);
            boolean a2 = gww.a();
            this.bt = a2;
            if (a2) {
                LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "mIsVoiceEnable is:", Boolean.valueOf(a2));
                this.cr.setImageDrawable(getResources().getDrawable(R.drawable._2131430359_res_0x7f0b0bd7));
            } else {
                this.cr.setImageDrawable(getResources().getDrawable(R.drawable._2131430360_res_0x7f0b0bd8));
            }
        }
        ak();
        this.cd.setOnClickListener(this);
        bb();
    }

    private void bb() {
        if (lbc.b(this.ar)) {
            this.ca.setVisibility(4);
            return;
        }
        this.ca.setVisibility(0);
        if (LanguageUtil.bc(this.i)) {
            BitmapDrawable cKn_ = nrz.cKn_(this.i, R.drawable._2131430349_res_0x7f0b0bcd);
            if (cKn_ != null) {
                this.ca.setImageDrawable(cKn_);
            }
        } else {
            this.ca.setImageDrawable(getResources().getDrawable(R.drawable._2131430349_res_0x7f0b0bcd));
        }
        this.ca.setOnClickListener(this);
    }

    private void aj() {
        if (this.ar == 283) {
            return;
        }
        if (this.au != null && this.au.d()) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.view_stub_target_mode_with_progress);
            this.cf = viewStub;
            lce lceVar = new lce(viewStub.inflate(), this.au);
            this.cg = lceVar;
            lceVar.b(this.l);
            if (nsn.ag(getApplicationContext())) {
                return;
            }
            this.bc.setVisibility(0);
            return;
        }
        ViewStub viewStub2 = (ViewStub) findViewById(R.id.view_stub_heartrate_distance);
        this.ce = viewStub2;
        viewStub2.setVisibility(0);
        this.bd = (HealthTextView) findViewById(R.id.distance_value);
        this.bf = (HealthTextView) findViewById(R.id.distance_unit);
        this.bg = (RelativeLayout) findViewById(R.id.distance_value_layout);
        this.bm = (RelativeLayout) findViewById(R.id.heartrate_layout);
        this.bj = (ImageView) findViewById(R.id.heartrate_image);
        if (this.l) {
            this.bk = (HealthTextView) findViewById(R.id.heartrate_type);
            this.be = (HealthTextView) findViewById(R.id.heartrate_value);
            this.bi = (HealthTextView) findViewById(R.id.heartrate_unit);
            ay();
        }
    }

    private void av() {
        findViewById(R.id.rope_background_layout).setVisibility(0);
        findViewById(R.id.control_button_linearlayout).setVisibility(0);
        this.bh = (ControlButtonLayout) findViewById(R.id.control_button);
        am();
        ViewStub viewStub = (ViewStub) findViewById(R.id.view_stub_rope_second_skipping_rope);
        this.cc = viewStub;
        viewStub.setVisibility(0);
        this.cl = (HealthTextView) findViewById(R.id.tv_rope_left_name);
        this.cj = (HealthTextView) findViewById(R.id.tv_rope_left_value);
        this.ci = (HealthTextView) findViewById(R.id.tv_rope_mid_top_target_type);
        this.co = (HealthTextView) findViewById(R.id.tv_rope_mid_top_target_value);
        this.cq = (HealthTextView) findViewById(R.id.tv_rope_mid_top_target_value_unit);
        this.cp = (HealthTextView) findViewById(R.id.tv_rope_mid_value);
        this.cn = (HealthTextView) findViewById(R.id.tv_rope_mid_bottom_target_value);
        this.cb = (HealthProgressBar) findViewById(R.id.progressbar_rope_mid_target);
        this.cm = (HealthTextView) findViewById(R.id.tv_rope_right_name);
        this.cv = (HealthTextView) findViewById(R.id.tv_rope_right_value);
        this.ck = (HealthTextView) findViewById(R.id.tv_rope_mid_round);
        this.bw = (LinearLayout) findViewById(R.id.ll_rope_mid_target);
        aw();
    }

    private void aw() {
        int i = this.ap;
        if (i == 0) {
            au();
            return;
        }
        if (i != 5) {
            if (i != 6 && i != 7) {
                if (i == 8) {
                    ap();
                    return;
                } else if (i != 10 && i != 11) {
                    return;
                }
            }
            al();
            return;
        }
        as();
    }

    private void al() {
        this.cb.setVisibility(8);
        this.cn.setVisibility(8);
        this.co.setVisibility(8);
        this.cl.setText(this.i.getResources().getString(R.string._2130839907_res_0x7f020963));
        this.ci.setText(this.i.getResources().getString(R.string._2130843714_res_0x7f021842));
        this.cm.setText(this.i.getResources().getString(R.string._2130847442_res_0x7f0226d2));
    }

    private void au() {
        this.cl.setText(this.i.getResources().getString(R.string._2130843714_res_0x7f021842));
        this.ci.setText(this.i.getResources().getString(R.string._2130839907_res_0x7f020963));
        this.cn.setText(this.i.getResources().getString(R.string._2130846245_res_0x7f022225, UnitUtil.a((int) this.ao)));
        this.cm.setText(this.i.getResources().getString(R.string._2130847442_res_0x7f0226d2));
    }

    private void as() {
        this.cl.setText(this.i.getResources().getString(R.string._2130839907_res_0x7f020963));
        this.ci.setText(this.i.getResources().getString(R.string._2130843714_res_0x7f021842));
        this.cn.setText(this.i.getResources().getString(R.string._2130846245_res_0x7f022225, this.i.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, (int) this.ao, Integer.valueOf((int) this.ao))));
        this.cm.setText(this.i.getResources().getString(R.string._2130847442_res_0x7f0226d2));
    }

    private void ap() {
        this.ci.setVisibility(8);
        this.ck.setVisibility(0);
        this.cl.setText(this.i.getResources().getString(R.string._2130845808_res_0x7f022070));
        this.cm.setText(this.i.getResources().getString(R.string._2130847442_res_0x7f0226d2));
        if (this.q.getIntermittentJumpMode() == 1) {
            this.cn.setText(this.i.getResources().getString(R.string._2130846245_res_0x7f022225, UnitUtil.a((int) this.ao)));
            this.co.setText(this.i.getString(R.string._2130846013_res_0x7f02213d));
        } else {
            this.cn.setText(this.i.getResources().getString(R.string._2130846245_res_0x7f022225, this.i.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, (int) this.ao, Integer.valueOf((int) this.ao))));
            this.co.setText(this.i.getString(R.string._2130845808_res_0x7f022070));
        }
        this.ck.setText(this.i.getString(R.string._2130840019_res_0x7f0209d3, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(this.q.getIntermittentJumpGroups(), 1, 0)));
    }

    private void am() {
        RelativeLayout relativeLayout = (RelativeLayout) this.bh.findViewById(R.id.layout_StopOrResume);
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin = 0;
            relativeLayout.setLayoutParams(layoutParams2);
        }
        this.bh.setClickCallback(new IBaseResponseCallback() { // from class: kzx
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                IndoorEquipLandDisplayActivity.this.d(i, obj);
            }
        });
        if (!dij.c(this.ar, this.ap)) {
            this.bh.setHidePauseAndStopButton();
        }
        if (this.as == 2) {
            this.bh.d();
        }
    }

    public /* synthetic */ void d(int i, Object obj) {
        if (this.au == null) {
            LogUtil.h("Track_IDEQ_IndoorEquipLandDisplayActivity", "mViewModel == null ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "mControlButtonLayout click code= ", Integer.valueOf(i));
        if (i == 101) {
            this.au.onPauseSport();
            return;
        }
        if (i == 100) {
            this.au.onResumeSport();
        } else if (i == 102) {
            this.au.e(true);
        } else {
            LogUtil.h("Track_IDEQ_IndoorEquipLandDisplayActivity", "error code ", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip, com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        super.onResumeSport();
        ControlButtonLayout controlButtonLayout = this.bh;
        if (controlButtonLayout != null) {
            controlButtonLayout.foldBtnStopAndPlay();
        }
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip, com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        super.onPauseSport();
        if (this.bh != null) {
            runOnUiThread(new Runnable() { // from class: kzy
                @Override // java.lang.Runnable
                public final void run() {
                    IndoorEquipLandDisplayActivity.this.ai();
                }
            });
        }
    }

    public /* synthetic */ void ai() {
        this.bh.showBtnStopAndPlay();
    }

    private void ay() {
        boolean z = true;
        boolean z2 = LanguageUtil.aq(this.i) || LanguageUtil.as(this.i) || LanguageUtil.f(this.i) || LanguageUtil.au(this.i);
        if (!LanguageUtil.w(this.i) && !LanguageUtil.bq(this.i) && !LanguageUtil.be(this.i) && !LanguageUtil.ai(this.i)) {
            z = false;
        }
        boolean b = LanguageUtil.b(this.i);
        if (z2) {
            this.bk.setTextSize(9.0f);
            this.bi.setTextSize(6.0f);
        }
        if (z) {
            this.bk.setTextSize(6.0f);
            this.bi.setTextSize(6.0f);
        }
        if (b) {
            this.bk.setTextSize(6.0f);
            this.bi.setTextSize(4.0f);
        }
    }

    private void ak() {
        SportDetailChartDataType[] a2;
        BaseRealTimeDynamicChartViewModel o;
        if (this.au == null || (a2 = lbj.a(this.ar, this.au.t(), this.au.q(), this.au.r())) == null || a2.length == 0) {
            return;
        }
        int length = 200 / a2.length;
        if (lbv.d(this)) {
            length -= 6;
        }
        for (SportDetailChartDataType sportDetailChartDataType : a2) {
            switch (AnonymousClass5.b[sportDetailChartDataType.ordinal()]) {
                case 1:
                    o = o(length);
                    break;
                case 2:
                    o = g(length);
                    break;
                case 3:
                    o = h(length);
                    break;
                case 4:
                    o = j(length);
                    break;
                case 5:
                    o = k(length);
                    break;
                case 6:
                    o = i(length);
                    break;
                case 7:
                    o = d(length);
                    break;
                case 8:
                    o = m(length);
                    break;
                case 9:
                    o = n(length);
                    break;
                default:
                    o = null;
                    break;
            }
            if (o != null) {
                o.updateConfiguration(this.i);
                this.aw.put(sportDetailChartDataType, o);
            }
        }
    }

    /* renamed from: com.huawei.indoorequip.activity.IndoorEquipLandDisplayActivity$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[SportDetailChartDataType.values().length];
            b = iArr;
            try {
                iArr[SportDetailChartDataType.STEP_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[SportDetailChartDataType.GROUND_CONTACT_TIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[SportDetailChartDataType.GROUND_IMPACT_ACCELERATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[SportDetailChartDataType.ACTIVE_PEAK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[SportDetailChartDataType.REALTIME_PACE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[SportDetailChartDataType.PADDLE_FREQUENCY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[SportDetailChartDataType.CADENCE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[SportDetailChartDataType.POWER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[SportDetailChartDataType.SPEED_RATE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private BaseRealTimeDynamicChartViewModel h(int i) {
        findViewById(R.id.divide_ground_impact_magnet).setVisibility(0);
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.ground_impact_magnet);
        realTimeDynamicChartView.setVisibility(0);
        d(realTimeDynamicChartView, i);
        d(realTimeDynamicChartView);
        lcj lcjVar = new lcj(realTimeDynamicChartView);
        if (lcjVar.getTitleTextLength() > 4) {
            lcjVar.setSmallTextSize();
        }
        lcjVar.setDefaultOrdinateY();
        return lcjVar;
    }

    private BaseRealTimeDynamicChartViewModel j(int i) {
        findViewById(R.id.divide_ground_shock_peak).setVisibility(0);
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.ground_shock_peak);
        realTimeDynamicChartView.setVisibility(0);
        d(realTimeDynamicChartView, i);
        d(realTimeDynamicChartView);
        lch lchVar = new lch(realTimeDynamicChartView);
        if (lchVar.getTitleTextLength() > 4) {
            lchVar.setSmallTextSize();
        }
        lchVar.setDefaultOrdinateY();
        return lchVar;
    }

    private BaseRealTimeDynamicChartViewModel g(int i) {
        findViewById(R.id.divide_touchdown_magnet).setVisibility(0);
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.touchdown_magnet);
        realTimeDynamicChartView.setVisibility(0);
        d(realTimeDynamicChartView, i);
        d(realTimeDynamicChartView);
        lci lciVar = new lci(realTimeDynamicChartView);
        if (lciVar.getTitleTextLength() > 4) {
            lciVar.setSmallTextSize();
        }
        lciVar.setDefaultOrdinateY();
        this.bq = true;
        return lciVar;
    }

    private BaseRealTimeDynamicChartViewModel o(int i) {
        findViewById(R.id.divide_step_rate_chart).setVisibility(0);
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.step_rate_chart);
        realTimeDynamicChartView.setVisibility(0);
        d(realTimeDynamicChartView, i);
        d(realTimeDynamicChartView);
        lcw lcwVar = new lcw(realTimeDynamicChartView);
        if (lcwVar.getTitleTextLength() > 4) {
            lcwVar.setSmallTextSize();
        }
        lcwVar.setDefaultOrdinateY();
        return lcwVar;
    }

    private BaseRealTimeDynamicChartViewModel d(int i) {
        findViewById(R.id.divide_cadence_chart).setVisibility(0);
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.cadence_chart);
        realTimeDynamicChartView.setVisibility(0);
        d(realTimeDynamicChartView, i);
        d(realTimeDynamicChartView);
        lcf lcfVar = new lcf(realTimeDynamicChartView);
        if (this.ar == 273) {
            lcfVar.setOrdinateY(0, 120);
        } else {
            lcfVar.setDefaultOrdinateY();
        }
        return lcfVar;
    }

    private BaseRealTimeDynamicChartViewModel i(int i) {
        findViewById(R.id.divide_paddle_freq_magnet).setVisibility(0);
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.paddle_freq_magnet);
        realTimeDynamicChartView.setVisibility(0);
        d(realTimeDynamicChartView, i);
        d(realTimeDynamicChartView);
        lcs lcsVar = new lcs(realTimeDynamicChartView, this.ak);
        lcsVar.setDefaultOrdinateY();
        return lcsVar;
    }

    private BaseRealTimeDynamicChartViewModel k(int i) {
        findViewById(R.id.divide_rt_pace_chart).setVisibility(0);
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.rt_pace_chart);
        realTimeDynamicChartView.setVisibility(0);
        d(realTimeDynamicChartView, i);
        d(realTimeDynamicChartView);
        lcp lcpVar = new lcp(realTimeDynamicChartView);
        lcpVar.setDefaultOrdinateY();
        return lcpVar;
    }

    private BaseRealTimeDynamicChartViewModel n(int i) {
        findViewById(R.id.divide_speed_rate_chart).setVisibility(0);
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.speed_rate_chart);
        realTimeDynamicChartView.setVisibility(0);
        d(realTimeDynamicChartView, i);
        d(realTimeDynamicChartView);
        lct lctVar = new lct(realTimeDynamicChartView);
        lctVar.setDefaultOrdinateY();
        return lctVar;
    }

    private BaseRealTimeDynamicChartViewModel m(int i) {
        findViewById(R.id.divide_power_chart).setVisibility(0);
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.power_chart);
        realTimeDynamicChartView.setVisibility(0);
        d(realTimeDynamicChartView, i);
        d(realTimeDynamicChartView);
        lcr lcrVar = new lcr(realTimeDynamicChartView);
        lcrVar.setDefaultOrdinateY();
        return lcrVar;
    }

    private void d(RealTimeDynamicChartView realTimeDynamicChartView) {
        if (realTimeDynamicChartView == null) {
            return;
        }
        HealthTextView healthTextView = (HealthTextView) realTimeDynamicChartView.findViewById(R.id.title);
        HealthTextView healthTextView2 = (HealthTextView) realTimeDynamicChartView.findViewById(R.id.value);
        if (nsn.ag(getApplicationContext())) {
            healthTextView.setTextSize(0, getResources().getDimension(R.dimen._2131362922_res_0x7f0a046a));
            healthTextView2.setTextSize(0, getResources().getDimension(R.dimen._2131365074_res_0x7f0a0cd2));
        } else {
            healthTextView.setTextSize(0, getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446));
            healthTextView2.setTextSize(0, getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        }
    }

    private void d(RealTimeDynamicChartView realTimeDynamicChartView, int i) {
        if (realTimeDynamicChartView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) realTimeDynamicChartView.getLayoutParams();
            layoutParams.height = nsn.c(this.i, i);
            realTimeDynamicChartView.setLayoutParams(layoutParams);
        }
    }

    private void ao() {
        this.r = kzc.n();
        this.s.e(true);
        this.ah = false;
        this.ag = false;
        this.z = getPackageManager().hasSystemFeature("android.hardware.nfc");
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "this phone supports Nfc? ", Boolean.valueOf(this.z));
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void c(Map<Integer, Object> map) {
        if (!this.bn) {
            LogUtil.b("Track_IDEQ_IndoorEquipLandDisplayActivity", "View is not init.");
            return;
        }
        if (map == null) {
            LogUtil.b("Track_IDEQ_IndoorEquipLandDisplayActivity", "updateUi, indoorEquipDataStructForShow = null");
            return;
        }
        if (this.ax != null) {
            this.ax.b(map);
        }
        if (this.ar == 274) {
            c = ((Float) map.get(26)).floatValue();
        } else {
            Object obj = map.get(3);
            if (obj instanceof Integer) {
                c = ((Integer) obj).intValue() / 100.0f;
            } else {
                LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "updateUi, UiDataType.SPEED is not integer, object = ", obj);
            }
        }
        lce lceVar = this.cg;
        if (lceVar != null) {
            lceVar.d(map);
        }
    }

    private void f(Map<Integer, Object> map) {
        for (Map.Entry<SportDetailChartDataType, BaseRealTimeDynamicChartViewModel> entry : this.aw.entrySet()) {
            SportDetailChartDataType key = entry.getKey();
            BaseRealTimeDynamicChartViewModel value = entry.getValue();
            if (value != null) {
                switch (AnonymousClass5.b[key.ordinal()]) {
                    case 1:
                        value.pushNewData(((Integer) d(map, 4, 0)).intValue(), c);
                        break;
                    case 2:
                    case 3:
                    case 4:
                        b(map, key, value);
                        break;
                    case 5:
                        value.pushNewData(((Integer) d(map, 14, 0)).intValue());
                        break;
                    case 6:
                        value.pushNewData(((Float) d(map, 26, Float.valueOf(0.0f))).floatValue());
                        break;
                    case 7:
                        value.pushNewData(((Integer) d(map, 31, 0)).intValue());
                        break;
                    case 8:
                        value.pushNewData(((Integer) d(map, 7, 0)).intValue());
                        break;
                    case 9:
                        value.pushNewData(((Integer) d(map, 3, 0)).intValue() / 100.0f);
                        break;
                }
            }
        }
    }

    private Object d(Map<Integer, Object> map, int i, Object obj) {
        return (!map.containsKey(Integer.valueOf(i)) || map.get(Integer.valueOf(i)) == null) ? obj : map.get(Integer.valueOf(i));
    }

    private void b(Map<Integer, Object> map, SportDetailChartDataType sportDetailChartDataType, BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel) {
        ffs ffsVar = (ffs) map.get(20002);
        if (ffsVar != null && this.t && this.bq) {
            a(ffsVar, sportDetailChartDataType);
        } else if (ffsVar == null && this.bq) {
            baseRealTimeDynamicChartViewModel.pushNewData(0, 0.0f);
        } else {
            LogUtil.b("Track_IDEQ_IndoorEquipLandDisplayActivity", "updateUi, hasRunPostureDevice = ", Boolean.valueOf(this.t));
        }
    }

    private void b(Map<Integer, Object> map, boolean z) {
        String string;
        if (this.au == null || !this.au.d()) {
            int e = e(map, 1);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            float f = e / 1000.0f;
            if (z) {
                f *= 0.621371f;
            }
            HealthTextView healthTextView = this.bd;
            if (healthTextView != null) {
                healthTextView.setText(decimalFormat.format(f));
                HealthTextView healthTextView2 = this.bf;
                if (z) {
                    string = getResources().getQuantityString(R.plurals._2130903253_res_0x7f0300d5, e, "");
                } else {
                    string = getString(R.string._2130840225_res_0x7f020aa1);
                }
                healthTextView2.setText(string);
            }
            if (this.ak.equals("291")) {
                a(map);
            }
            if (!this.l || this.be == null) {
                return;
            }
            int e2 = e(map, 20004);
            if (e2 <= 0 || e2 > 220) {
                this.be.setText(getString(R.string._2130851303_res_0x7f0235e7));
                this.bi.setText(lbv.e(0, this.i));
            } else {
                this.be.setText(lbv.e(e2, 1, 0));
                this.bi.setText(lbv.e(e2, this.i));
            }
            l(e2);
        }
    }

    private void b(Map<Integer, Object> map) {
        String e = UnitUtil.e(e(map, 40001), 1, 0);
        String e2 = UnitUtil.e(e(map, 6), 1, 0);
        int i = this.ap;
        if (i != 0) {
            if (i != 5 && i != 6 && i != 7) {
                if (i == 8) {
                    d(map);
                    this.cb.setProgress(e(map, 10003));
                    return;
                } else if (i != 10 && i != 11) {
                    return;
                }
            }
            this.cj.setText(UnitUtil.d(e(map, 2)));
            this.cp.setText(e);
            this.cv.setText(e2);
            return;
        }
        this.cj.setText(e);
        this.cp.setText(UnitUtil.a(e(map, 2)));
        this.cv.setText(e2);
    }

    private void d(Map<Integer, Object> map) {
        String string;
        String string2;
        String a2;
        boolean e = e(map);
        this.bw.setVisibility(e ? 0 : 8);
        this.ck.setText(this.i.getString(R.string._2130840019_res_0x7f0209d3, UnitUtil.e(e(map, 40011), 1, 0), UnitUtil.e(this.q.getIntermittentJumpGroups(), 1, 0)));
        this.cv.setText(UnitUtil.e(e(map, e ? 40057 : 40058), 1, 0));
        String e2 = UnitUtil.e(e(map, 40016), 1, 0);
        String a3 = UnitUtil.a(e(map, 40052));
        int intermittentJumpMode = this.q.getIntermittentJumpMode();
        int i = R.string._2130845860_res_0x7f0220a4;
        if (intermittentJumpMode == 1) {
            HealthTextView healthTextView = this.cl;
            if (e) {
                string2 = this.i.getResources().getString(R.string._2130845808_res_0x7f022070);
            } else {
                string2 = this.i.getResources().getString(R.string._2130845858_res_0x7f0220a2);
            }
            healthTextView.setText(string2);
            String e3 = UnitUtil.e(e(map, 40013), 1, 0);
            HealthTextView healthTextView2 = this.cj;
            if (!e) {
                e2 = e3;
            }
            healthTextView2.setText(e2);
            String a4 = UnitUtil.a(e(map, 40051));
            HealthTextView healthTextView3 = this.cp;
            if (e) {
                a3 = a4;
            }
            healthTextView3.setText(a3);
            if (e) {
                a2 = UnitUtil.a(this.q.getIntermittentJumpExerciseTime());
            } else {
                a2 = UnitUtil.a(this.q.getIntermittentJumpBreakTime());
            }
            HealthTextView healthTextView4 = this.cn;
            Resources resources = this.i.getResources();
            if (e) {
                i = R.string._2130846245_res_0x7f022225;
            }
            healthTextView4.setText(resources.getString(i, a2));
            return;
        }
        HealthTextView healthTextView5 = this.cl;
        if (e) {
            string = this.i.getResources().getString(R.string._2130839907_res_0x7f020963);
        } else {
            string = this.i.getResources().getString(R.string._2130845859_res_0x7f0220a3);
        }
        healthTextView5.setText(string);
        String d = UnitUtil.d(e(map, 40051));
        String d2 = UnitUtil.d(e(map, 40014));
        HealthTextView healthTextView6 = this.cj;
        if (!e) {
            d = d2;
        }
        healthTextView6.setText(d);
        HealthTextView healthTextView7 = this.cp;
        if (!e) {
            e2 = a3;
        }
        healthTextView7.setText(e2);
        int intermittentJumpExerciseNum = this.q.getIntermittentJumpExerciseNum();
        String quantityString = this.i.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, intermittentJumpExerciseNum, Integer.valueOf(intermittentJumpExerciseNum));
        if (!e) {
            quantityString = UnitUtil.a(this.q.getIntermittentJumpBreakTime());
        }
        HealthTextView healthTextView8 = this.cn;
        Resources resources2 = this.i.getResources();
        if (e) {
            i = R.string._2130846245_res_0x7f022225;
        }
        healthTextView8.setText(resources2.getString(i, quantityString));
    }

    private boolean e(Map<Integer, Object> map) {
        if (this.ar != 283 || this.ap != 8) {
            return true;
        }
        boolean a2 = a(map, 40015);
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "mIsIntermittentJumpMotion is =", Boolean.valueOf(a2));
        return a2;
    }

    private void a(Map<Integer, Object> map) {
        if (this.bd == null || this.i == null) {
            return;
        }
        this.bd.setText(UnitUtil.e(e(map, 38), 1, 0));
        this.bf.setText(this.i.getResources().getQuantityString(R.plurals._2130903241_res_0x7f0300c9, e(map, 38)));
    }

    private int e(Map<Integer, Object> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            return 0;
        }
        return ((Integer) map.get(Integer.valueOf(i))).intValue();
    }

    private boolean a(Map<Integer, Object> map, int i) {
        if (map.get(Integer.valueOf(i)) != null) {
            Object obj = map.get(Integer.valueOf(i));
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
        }
        return false;
    }

    private void a(ffs ffsVar, SportDetailChartDataType sportDetailChartDataType) {
        int b = ffsVar.b();
        if (sportDetailChartDataType == SportDetailChartDataType.GROUND_CONTACT_TIME) {
            this.aw.get(sportDetailChartDataType).pushNewData(b, c);
        }
        float m = ffsVar.m();
        if (sportDetailChartDataType == SportDetailChartDataType.ACTIVE_PEAK) {
            this.aw.get(sportDetailChartDataType).pushNewData(m, c);
        }
        int e = ffsVar.e();
        if (sportDetailChartDataType == SportDetailChartDataType.GROUND_IMPACT_ACCELERATION) {
            this.aw.get(sportDetailChartDataType).pushNewData(e, c);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.ie_voice_icon || id == R.id.ie_rope_voice_icon) {
            lbv.c(getApplicationContext(), "VoiceBtn");
            boolean z = !gww.a();
            this.bt = z;
            if (z) {
                this.cr.setImageDrawable(getResources().getDrawable(R.drawable._2131430359_res_0x7f0b0bd7));
            } else {
                this.cr.setImageDrawable(getResources().getDrawable(R.drawable._2131430360_res_0x7f0b0bd8));
            }
            gww.b(this.bt);
        } else if (id == R.id.ie_music_icon) {
            lbv.c(this.i, "MusicBtn");
            if (ag()) {
                LogUtil.h("Track_IDEQ_IndoorEquipLandDisplayActivity", "onClick() too fast");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (lbv.b(this.ar)) {
                diy.e(this.i, this.ar);
                SportMusicController.a().d(this.ch.d(this.ar) == null ? 1 : 2, this.ar, true);
            } else if (CommonUtil.bh() && CommonUtil.v(gwh.s)) {
                Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
                intent.setFlags(268435456);
                intent.setPackage(gwh.s);
                startActivity(intent);
            } else {
                LogUtil.h("Track_IDEQ_IndoorEquipLandDisplayActivity", "Don't play music.");
            }
        } else if (id == R.id.ie_miracast_icon) {
            lbv.c(getApplicationContext(), "ProjectionBtn");
            if (this.k != null) {
                this.k.startWirelessProjection();
            }
        } else if (id == R.id.horizontal_screen || id == R.id.ie_rope_screen_icon) {
            bc();
        } else {
            LogUtil.c("Track_IDEQ_IndoorEquipLandDisplayActivity", "onClick");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public boolean ag() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.bs < 1000) {
            LogUtil.h("Track_IDEQ_IndoorEquipLandDisplayActivity", "onClick ", "click too fast");
            this.bs = currentTimeMillis;
            return true;
        }
        this.bs = currentTimeMillis;
        return false;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (z) {
            bc();
        }
    }

    private void bc() {
        this.u = false;
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        sportLaunchParams.addExtra("is_has_aw70", Boolean.valueOf(this.t));
        sportLaunchParams.addExtra("has_heart_rate_device_connect", Boolean.valueOf(this.l));
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "switchToHorizontalScreen: mHasHeartRateDevice = ", Boolean.valueOf(this.l));
        sportLaunchParams.setSportType(this.ar);
        sportLaunchParams.setSportTarget(this.ap);
        sportLaunchParams.setTargetValue(this.ao);
        if (this.ar == 283) {
            sportLaunchParams.addExtra("productId", this.aj);
            if (this.ap == 8) {
                sportLaunchParams.addExtra("type_intermittent_jump_mode_data", this.q);
            }
        }
        if (!TextUtils.isEmpty(this.ak)) {
            sportLaunchParams.addExtra("sportMode", this.ak);
        }
        sportLaunchParams.addExtra("musicPlayConfirm", Boolean.valueOf(this.bo));
        sportLaunchParams.addExtra("KEY_IS_SWITCH_TO_HORIZONTAL_SCREEN", true);
        if (this.au != null) {
            sportLaunchParams.addExtra("key_progress", Integer.valueOf(this.au.l()));
        }
        Intent intent = new Intent(this, (Class<?>) IndoorEquipDisplayActivity.class);
        intent.putExtra("ExitApp", this.v);
        intent.putExtra("key_is_keep_connect", this.aa);
        if (this.aq != null) {
            sportLaunchParams.addExtra("supportDataRange", this.aq);
        }
        intent.putExtra("bundle_key_sport_launch_paras", sportLaunchParams);
        this.bv = true;
        if (this.k != null) {
            this.k.setIsCanPreSearch(false);
        }
        c(false);
        startActivity(intent);
        lbv.c(getApplicationContext(), "verticalScreenBtn");
        this.x = true;
        finish();
    }

    private void ar() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.indoorequip.activity.IndoorEquipLandDisplayActivity.3
            @Override // java.lang.Runnable
            public void run() {
                HwSportTypeInfo d = hln.c(BaseApplication.getContext()).d(IndoorEquipLandDisplayActivity.this.au.getSportType());
                int heartPostureType = d == null ? 1 : d.getHeartPostureType();
                IndoorEquipLandDisplayActivity.this.f(heartPostureType);
                LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "heartPostureType: ", Integer.valueOf(heartPostureType));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Track_IDEQ_IndoorEquipLandDisplayActivity", "userProfileMgrApi is null.");
            return;
        }
        HeartZoneConf e = fgd.e(i, userProfileMgrApi.getLocalUserInfo().getAgeOrDefaultValue());
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "======initData====ClassifyMethod: ", Integer.valueOf(this.ba), "mSportType:", Integer.valueOf(this.ar));
        this.ba = e.getClassifyMethod();
        this.bp = e.isWarningEnble();
        this.ct = e.getWarningLimitHR();
        this.bx = e.getMaxThreshold();
        int i2 = this.ba;
        if (i2 == 0) {
            this.bu = e.getAnaerobicThreshold();
            this.az = e.getAerobicThreshold();
            this.bb = e.getFatBurnThreshold();
            this.by = e.getWarmUpThreshold();
            this.cs = e.getFitnessThreshold();
        } else if (i2 == 1) {
            this.bu = e.getAnaerobicAdvanceThreshold();
            this.az = e.getAnaerobicBaseThreshold();
            this.bb = e.getLacticAcidThreshold();
            this.by = e.getAerobicAdvanceThreshold();
            this.cs = e.getAerobicBaseThreshold();
        } else if (i2 == 3) {
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "mClassifyMethod == 3");
            this.bu = e.getLthrAnaerobicInterval();
            this.az = e.getLthrLactateThreshold();
            this.bb = e.getLthrAerobicHighZone();
            this.by = e.getLthrAerobicLowZone();
            this.cs = e.getLthrRecoveryInterval();
        } else {
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "mClassifyMethod other");
        }
        this.br = true;
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayActivity", "======initData====ClassifyMethod: ", Integer.valueOf(this.ba), "mIsMaxAlarm:", Boolean.valueOf(this.bp), " mUpLimit", Integer.valueOf(this.ct), " mMaxLimitHeartRate", Integer.valueOf(this.bx), " mLimit", Integer.valueOf(this.bu), " mAnaerobic", Integer.valueOf(this.az), " mAerobic", Integer.valueOf(this.bb), " mReduceFat", Integer.valueOf(this.by), " mWarmUp", Integer.valueOf(this.cs));
    }

    private void c(String str, String str2, String str3) {
        int i = this.ba;
        if (i == 0) {
            this.bk.setText(str);
            return;
        }
        if (i == 1) {
            this.bk.setText(str2);
        } else if (i == 3) {
            this.bk.setText(str3);
        } else {
            LogUtil.b("Track_IDEQ_IndoorEquipLandDisplayActivity", "error mClassifyMethod= ", Integer.valueOf(i));
        }
    }

    private void l(int i) {
        if (!this.br) {
            this.bj.setImageDrawable(getResources().getDrawable(R.drawable.heartrate1));
            this.bk.setText("");
            this.be.setTextColor(-1);
            return;
        }
        int i2 = this.bu;
        if (i >= i2 && i < 255) {
            this.bj.setImageDrawable(getResources().getDrawable(R.drawable.heartrate6));
            this.be.setTextColor(-58880);
            c(getString(R.string._2130839494_res_0x7f0207c6), getString(R.string._2130842683_res_0x7f02143b), getString(R.string._2130845602_res_0x7f021fa2));
            return;
        }
        int i3 = this.az;
        if (i >= i3 && i < i2) {
            this.bj.setImageDrawable(getResources().getDrawable(R.drawable.heartrate5));
            this.be.setTextColor(-1282048);
            c(getString(R.string._2130839493_res_0x7f0207c5), getString(R.string._2130842684_res_0x7f02143c), getString(R.string._2130842685_res_0x7f02143d));
            return;
        }
        int i4 = this.bb;
        if (i >= i4 && i < i3) {
            this.bj.setImageDrawable(getResources().getDrawable(R.drawable.heartrate4));
            this.be.setTextColor(-602087);
            c(getString(R.string._2130839492_res_0x7f0207c4), getString(R.string._2130842685_res_0x7f02143d), getString(R.string._2130842686_res_0x7f02143e));
            return;
        }
        int i5 = this.by;
        if (i >= i5 && i < i4) {
            this.bj.setImageDrawable(getResources().getDrawable(R.drawable.heartrate3));
            this.be.setTextColor(-16722343);
            c(getString(R.string._2130839490_res_0x7f0207c2), getString(R.string._2130842686_res_0x7f02143e), getString(R.string._2130842687_res_0x7f02143f));
        } else if (i >= this.cs && i < i5) {
            this.bj.setImageDrawable(getResources().getDrawable(R.drawable.heartrate2));
            this.be.setTextColor(-11100428);
            c(getString(R.string._2130839491_res_0x7f0207c3), getString(R.string._2130842687_res_0x7f02143f), getString(R.string._2130845603_res_0x7f021fa3));
        } else {
            this.bj.setImageDrawable(getResources().getDrawable(R.drawable.heartrate1));
            this.bk.setText("");
            this.be.setTextColor(-1);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setTahitiModelOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setDefaultOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
