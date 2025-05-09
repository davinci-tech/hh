package com.huawei.indoorequip.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.HeartRateControlSportActivity;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.CircleProgressButtonForIndoorEquip;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.cas;
import defpackage.gwh;
import defpackage.gww;
import defpackage.koq;
import defpackage.kyu;
import defpackage.kza;
import defpackage.kzc;
import defpackage.lbc;
import defpackage.lbj;
import defpackage.lbv;
import defpackage.lcb;
import defpackage.nrh;
import defpackage.nrz;
import health.compact.a.CommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class HeartRateControlSportActivity extends BaseActivityOfIndoorEquip implements View.OnClickListener {
    private ImageView aw;
    private String ba;
    private String bc;
    private boolean bg;
    private HealthImageView bi;
    private HealthImageView bk;
    private HealthImageView bl;
    private lcb bm;
    private HealthImageView bp;
    private boolean be = true;
    private boolean bf = false;
    private boolean bd = false;
    private boolean bh = false;
    private int bj = 0;
    private CustomAlertDialog az = null;
    private CustomAlertDialog bn = null;
    private NoTitleCustomAlertDialog ay = null;
    private NoTitleCustomAlertDialog bb = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("HeartRateControlSportActivity", "onCreate");
        b(true);
        ai();
        this.i = this;
        this.s = new kyu(this, this.af, false);
        this.k = (DistributedApi) Services.a("DistributedService", DistributedApi.class);
        if (this.k != null) {
            this.k.init(this.i);
            this.k.detectLastWirelessDevice();
        } else {
            LogUtil.b("HeartRateControlSportActivity", "can not get DistributedApi");
        }
        this.d = new kza(null, null, null);
        this.d.a(this.i);
        this.d.bTd_(this.af);
        clearBackgroundDrawable();
        y();
        Intent intent = getIntent();
        if (intent != null) {
            this.v = getIntent().getBooleanExtra("ExitApp", false);
            this.aa = intent.getBooleanExtra("key_is_keep_connect", false);
            this.aj = intent.getStringExtra("productId");
            this.bc = intent.getStringExtra("deviceType");
            bTh_(intent);
            LogUtil.a("HeartRateControlSportActivity", "mCourseId is ", this.ba, "mProductId is ", this.aj);
        }
        LogUtil.c("HeartRateControlSportActivity", "acquireDataFromIntent ExitApp ", Boolean.valueOf(this.v));
        if (this.au != null) {
            this.as = this.au.getSportStatus();
        }
        ae();
        r();
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void r() {
        LogUtil.a("HeartRateControlSportActivity", "initView");
        setContentView(R.layout.activity_heart_rate_control_sport);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & (-8193));
        window.addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        window.setStatusBarColor(getColor(R.color._2131298134_res_0x7f090756));
        window.setNavigationBarColor(getColor(R.color._2131298134_res_0x7f090756));
        ag();
        t();
        z();
        this.bf = true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("HeartRateControlSportActivity", "onResume");
        getWindow().addFlags(128);
        q();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.a("HeartRateControlSportActivity", "onPause");
        super.onPause();
        if (!this.z || this.ae == null) {
            return;
        }
        LogUtil.a("HeartRateControlSportActivity", "NFC DisableForegroundDispatch");
        this.ae.disableForegroundDispatch(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.a("HeartRateControlSportActivity", "enter onNewIntent");
        if (intent == null) {
            LogUtil.h("HeartRateControlSportActivity", "on NewIntent intent is null");
            return;
        }
        if (this.d != null && intent.getBooleanExtra("show tips key", false)) {
            this.d.a(this);
            Toast.makeText(HuaweiHealth.a(), this.i.getString(R.string._2130840308_res_0x7f020af4), 0).show();
        }
        if ("android.nfc.action.NDEF_DISCOVERED".equals(intent.getAction())) {
            LogUtil.a("HeartRateControlSportActivity", "onNewIntent, action is equal to ACTION_NDEF_DISCOVERED");
            Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
            if (parcelableArrayExtra == null || parcelableArrayExtra.length == 0) {
                LogUtil.h("HeartRateControlSportActivity", "NDEF_MESSAGES is null");
                return;
            }
            Parcelable parcelable = parcelableArrayExtra[0];
            String str = null;
            NdefMessage ndefMessage = parcelable instanceof NdefMessage ? (NdefMessage) parcelable : null;
            if (ndefMessage != null) {
                try {
                    if (koq.e(ndefMessage.getRecords(), 0) && ndefMessage.getRecords()[0] != null) {
                        str = new String(ndefMessage.getRecords()[0].getPayload(), "UTF-8");
                    }
                } catch (UnsupportedEncodingException unused) {
                    LogUtil.b("HeartRateControlSportActivity", "onNewIntent, UnsupportedEncodingException");
                }
            }
            c(str);
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

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("HeartRateControlSportActivity", "onDestroy");
        if (!this.x) {
            ah();
            if (this.k != null) {
                this.k.setIsInit(false);
            }
        } else {
            LogUtil.a("HeartRateControlSportActivity", "normal onDestroy");
            if (this.ax != null) {
                this.ax.c();
            }
            if (this.k != null && this.u) {
                this.k.destroyWirelessProjection();
                LogUtil.a("HeartRateControlSportActivity", "exit the treadmill exercise, you need to disconnect the Projection.");
            }
        }
        this.r.c(false);
        e();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.ie_voice_icon) {
            boolean z = !gww.a();
            this.be = z;
            if (z) {
                this.bp.setImageDrawable(getResources().getDrawable(R.drawable._2131430359_res_0x7f0b0bd7));
            } else {
                this.bp.setImageDrawable(getResources().getDrawable(R.drawable._2131430360_res_0x7f0b0bd8));
            }
            gww.b(this.be);
        } else if (id == R.id.ie_music_icon) {
            if (CommonUtil.bd() && CommonUtil.v(gwh.s)) {
                Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
                intent.setFlags(268435456);
                intent.setPackage(gwh.s);
                startActivity(intent);
            } else {
                LogUtil.h("HeartRateControlSportActivity", "Don't play music");
            }
        } else if (id == R.id.ie_lock_icon) {
            d(this.bh);
        } else if (id == R.id.ie_miracast_icon) {
            if (this.k != null) {
                this.k.startWirelessProjection();
            }
        } else {
            LogUtil.c("HeartRateControlSportActivity", "onClick");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(final boolean z) {
        LogUtil.a("HeartRateControlSportActivity", "enter showControlSportSwitchDialog isOpen: ", Boolean.valueOf(z));
        CustomAlertDialog customAlertDialog = this.az;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            LogUtil.a("HeartRateControlSportActivity", "showControlSportSwitchDialog is showing");
            return;
        }
        CustomAlertDialog c = new CustomAlertDialog.Builder(this.i).e(lbj.a(this.ar, z)).c(lbj.d(this.ar, z)).cyn_(R.string._2130851070_res_0x7f0234fe, new DialogInterface.OnClickListener() { // from class: kzb
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HeartRateControlSportActivity.bTf_(dialogInterface, i);
            }
        }).cyo_(z ? R.string._2130837520_res_0x7f020010 : R.string._2130851071_res_0x7f0234ff, new DialogInterface.OnClickListener() { // from class: kyz
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HeartRateControlSportActivity.this.bTj_(z, dialogInterface, i);
            }
        }).c();
        this.az = c;
        c.setCancelable(false);
        this.az.show();
    }

    public static /* synthetic */ void bTf_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void bTj_(boolean z, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (z) {
            this.bh = false;
            al();
            a(this.bh);
            lbv.a(this.aj, this.ba, this.bc, 10);
        } else {
            this.bh = true;
            ak();
            a(this.bh);
            lbv.a(this.aj, this.ba, this.bc, 9);
        }
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ar() {
        String string;
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: kzd
                @Override // java.lang.Runnable
                public final void run() {
                    HeartRateControlSportActivity.this.ar();
                }
            });
            return;
        }
        LogUtil.a("HeartRateControlSportActivity", "enter showWearDeviceDisconnectedDialog");
        CustomAlertDialog customAlertDialog = this.bn;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            LogUtil.a("HeartRateControlSportActivity", "showWearDeviceDisconnectedDialog is showing");
            return;
        }
        if (this.ar == 265) {
            string = getString(R.string._2130840354_res_0x7f020b22);
        } else {
            string = getString(R.string._2130840353_res_0x7f020b21);
        }
        CustomAlertDialog c = new CustomAlertDialog.Builder(this.i).e(R.string._2130840350_res_0x7f020b1e).c(string).cyo_(R.string._2130840262_res_0x7f020ac6, new DialogInterface.OnClickListener() { // from class: kzh
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HeartRateControlSportActivity.bTg_(dialogInterface, i);
            }
        }).c();
        this.bn = c;
        c.setCancelable(false);
        this.bn.show();
    }

    public static /* synthetic */ void bTg_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void aj() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.ay;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
            builder.e(R.string._2130840352_res_0x7f020b20);
            builder.czC_(R.string._2130840262_res_0x7f020ac6, new View.OnClickListener() { // from class: kzg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HeartRateControlSportActivity.this.bTk_(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            this.ay = e;
            e.setCancelable(false);
            this.ay.show();
        }
    }

    public /* synthetic */ void bTk_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.ay;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.ay = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void an() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.bb;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.h("HeartRateControlSportActivity", "");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R.string._2130840355_res_0x7f020b23);
        builder.czC_(R.string._2130840262_res_0x7f020ac6, new View.OnClickListener() { // from class: kze
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartRateControlSportActivity.this.bTl_(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        this.bb = e;
        e.setCancelable(false);
        this.bb.show();
    }

    public /* synthetic */ void bTl_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.bb;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.bb = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip
    protected void c(Map<Integer, Object> map) {
        if (!this.bf) {
            LogUtil.b("HeartRateControlSportActivity", "View is not init");
            return;
        }
        if (map == null) {
            LogUtil.b("HeartRateControlSportActivity", "updateUi, indoorEquipDataStructForShow = null");
            return;
        }
        lcb lcbVar = this.bm;
        if (lcbVar != null) {
            lcbVar.c(map);
        }
        if (this.ax != null) {
            this.ax.b(map);
        }
        if (this.ar == 274) {
            c = ((Float) map.get(26)).floatValue();
            return;
        }
        Object obj = map.get(3);
        if (obj instanceof Integer) {
            c = ((Integer) obj).intValue() / 100.0f;
        } else {
            LogUtil.a("HeartRateControlSportActivity", "updateUi, UiDataType.SPEED is not integer, object = ", obj);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    private void ah() {
        LogUtil.a("HeartRateControlSportActivity", "unnormal onDestroy");
        ThreadPoolManager.d().execute(new Runnable() { // from class: kzl
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateControlSportActivity.this.af();
            }
        });
    }

    public /* synthetic */ void af() {
        SystemClock.sleep(2000L);
        SportLaunchParams e = cas.e();
        Object[] objArr = new Object[2];
        objArr[0] = "sportLaunchParams == null";
        objArr[1] = Boolean.valueOf(e == null);
        LogUtil.a("HeartRateControlSportActivity", objArr);
        if (e != null) {
            e.addExtra("musicPlayConfirm", Boolean.valueOf(this.bg));
            e.addExtra("has_heart_rate_device_connect", Boolean.valueOf(this.l));
            LogUtil.a("HeartRateControlSportActivity", "onDestroy, mHasWear = ", Boolean.valueOf(this.l));
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
            intent.putExtra("key_is_keep_connect", this.aa);
            intent.setClass(getApplication(), HeartRateControlSportActivity.class);
            getApplication().startActivity(intent);
        }
    }

    private void ag() {
        lcb lcbVar = new lcb(this, getWindow().getDecorView().getRootView(), this.au, false);
        this.bm = lcbVar;
        lcbVar.d(this.aq);
        this.f6418a = (HealthProgressBar) findViewById(R.id.hw_recycler_loading_hpb);
        this.g = (ImageView) findViewById(R.id.ie_bt_icon);
        this.aw = (ImageView) findViewById(R.id.ie_bolt_icon);
        this.g.setImageDrawable(lbv.bVR_(this.i, lbj.c(this.ar, true)));
        e(true);
        this.al = (ImageView) findViewById(R.id.runway);
        this.am = (ImageView) findViewById(R.id.runwayBack);
        this.bk = (HealthImageView) findViewById(R.id.ie_music_icon);
        this.bi = (HealthImageView) findViewById(R.id.ie_lock_icon);
        am();
        this.bi.setOnClickListener(this);
        a(false);
        if (this.au != null) {
            this.au.v();
        }
    }

    private void am() {
        this.bl = (HealthImageView) findViewById(R.id.ie_miracast_icon);
        if (Utils.d() == 1 && CommonUtil.bh() && CommonUtil.ba()) {
            this.bl.setVisibility(0);
            this.bl.setOnClickListener(this);
        } else {
            this.bl.setVisibility(8);
        }
        this.e = (CircleProgressButtonForIndoorEquip) findViewById(R.id.ie_track_main_page_btn_stop);
        p();
        this.bp = (HealthImageView) findViewById(R.id.ie_voice_icon);
        if (lbv.c(this.ar)) {
            LogUtil.a("HeartRateControlSportActivity", "mVoiceIcon hide");
            this.bp.setVisibility(8);
        } else {
            LogUtil.a("HeartRateControlSportActivity", "isRealChineseMainLand");
            this.bp.setOnClickListener(this);
            boolean a2 = gww.a();
            this.be = a2;
            if (a2) {
                LogUtil.a("HeartRateControlSportActivity", "mIsVoiceEnable is:", Boolean.valueOf(a2));
                this.bp.setImageDrawable(getResources().getDrawable(R.drawable._2131430359_res_0x7f0b0bd7));
            } else {
                this.bp.setImageDrawable(getResources().getDrawable(R.drawable._2131430360_res_0x7f0b0bd8));
            }
        }
        ao();
    }

    private void ao() {
        if (lbc.b(this.ar)) {
            this.bk.setVisibility(8);
            return;
        }
        this.bk.setVisibility(0);
        if (LanguageUtil.bc(this.i)) {
            BitmapDrawable cKn_ = nrz.cKn_(this.i, R.drawable._2131430349_res_0x7f0b0bcd);
            if (cKn_ != null) {
                this.bk.setImageDrawable(cKn_);
            }
        } else {
            this.bk.setImageDrawable(getResources().getDrawable(R.drawable._2131430349_res_0x7f0b0bcd));
        }
        this.bk.setOnClickListener(this);
    }

    private void ae() {
        this.r = kzc.n();
        this.s.e(true);
        this.ah = false;
        this.ag = false;
        this.z = getPackageManager().hasSystemFeature("android.hardware.nfc");
        LogUtil.a("HeartRateControlSportActivity", "this phone supports Nfc? ", Boolean.valueOf(this.z));
    }

    private void bTh_(Intent intent) {
        SportLaunchParams sportLaunchParams = (SportLaunchParams) intent.getParcelableExtra("bundle_key_sport_launch_paras");
        if (sportLaunchParams != null) {
            sportLaunchParams.setLaunchActivity(HeartRateControlSportActivity.class.getName());
            this.ar = sportLaunchParams.getSportType();
            this.ap = sportLaunchParams.getSportTarget();
            this.ao = (int) sportLaunchParams.getTargetValue();
            this.ba = (String) sportLaunchParams.getExtra("HEART_RATE_CONTROL_COURSE_ID", String.class);
            this.bg = ((Boolean) sportLaunchParams.getExtra("musicPlayConfirm", Boolean.class, false)).booleanValue();
            this.aq = (SupportDataRange) sportLaunchParams.getExtra("supportDataRange", SupportDataRange.class);
            this.r = kzc.n();
            this.r.c(true);
            if (this.ar != 0) {
                this.r.c(this.ar);
            } else {
                this.ar = this.r.r();
            }
            sportLaunchParams.setSportTarget(4);
            bTb_(sportLaunchParams, this.af, this.ar);
            this.l = this.l ? true : ((Boolean) sportLaunchParams.getExtra("has_heart_rate_device_connect", Boolean.class, false)).booleanValue();
            LogUtil.a("HeartRateControlSportActivity", "setSportLaunchParams HAS_HEART_RATE_DEVICE_CONNECT ", Boolean.valueOf(this.l));
        }
    }

    private void ai() {
        this.af = new Handler(new Handler.Callback() { // from class: kzf
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return HeartRateControlSportActivity.this.bTi_(message);
            }
        });
    }

    public /* synthetic */ boolean bTi_(Message message) {
        b(message.what);
        c(message.what);
        a(message.what);
        bTe_(message);
        int i = message.what;
        if (i == 908) {
            LogUtil.a("HeartRateControlSportActivity", "in handleMessage, case is FINISH_ACT");
            lbv.a(this.aj, this.ba, this.bc, this.bd ? 2 : 3);
            this.x = true;
            finish();
            return false;
        }
        if (i != 1014) {
            if (i != 1015) {
                return false;
            }
            r();
            return false;
        }
        if (!(message.obj instanceof HashMap)) {
            return false;
        }
        LogUtil.a("HeartRateControlSportActivity", "MSG_UPDATE_UI", message.obj);
        c((HashMap) message.obj);
        return false;
    }

    private void bTe_(Message message) {
        switch (message.what) {
            case 801:
                if (message.obj instanceof HashMap) {
                    LogUtil.a("HeartRateControlSportActivity", "MSG_HR_CONTROL_UPDATE_DATA", message.obj);
                    HashMap hashMap = (HashMap) message.obj;
                    lcb lcbVar = this.bm;
                    if (lcbVar != null) {
                        lcbVar.b(hashMap);
                    }
                    if (this.ax != null) {
                        this.ax.e(hashMap);
                        break;
                    }
                }
                break;
            case ErrorCode.ERROR_PLACEMENT_EMPTY_AD_IDS /* 802 */:
                int intValue = ((Integer) message.obj).intValue();
                d(intValue);
                if (this.ax != null) {
                    this.ax.c(intValue);
                    break;
                }
                break;
            case 803:
                an();
                break;
        }
    }

    private void d(int i) {
        switch (i) {
            case 1001:
            case 1002:
                ar();
                lbv.a(this.aj, this.ba, this.bc, 4);
                break;
            case 1003:
                this.bd = true;
                aj();
                break;
            case 1004:
                nrh.d(HuaweiHealth.a(), getString(R.string._2130840351_res_0x7f020b1f, new Object[]{50}));
                break;
            case 1005:
                lbv.a(this.aj, this.ba, this.bc, 5);
                break;
            case 1006:
                lbv.a(this.aj, this.ba, this.bc, 6);
                break;
            case 1007:
                lbv.a(this.aj, this.ba, this.bc, 7);
                break;
            case 1008:
                lbv.a(this.aj, this.ba, this.bc, 8);
                break;
            case 1009:
                lbv.a(this.aj, this.ba, this.bc, 11);
                break;
        }
    }

    private void e(boolean z) {
        this.aw.setVisibility(0);
        this.aw.setImageDrawable(lbv.bVR_(this.i, z ? R.drawable._2131430578_res_0x7f0b0cb2 : R.drawable._2131430579_res_0x7f0b0cb3));
    }

    private void ak() {
        if (this.au != null) {
            this.bj = 2;
            this.au.c(SportParamsType.HEART_RATE_CONTROL_LOCK, 2);
        }
    }

    private void al() {
        if (this.au != null) {
            this.bj = 1;
            this.au.c(SportParamsType.HEART_RATE_CONTROL_LOCK, 1);
        }
    }

    private void a(boolean z) {
        this.bi.setImageDrawable(lbv.bVR_(this.i, z ? R.drawable._2131430598_res_0x7f0b0cc6 : R.drawable._2131430599_res_0x7f0b0cc7));
    }

    @Override // com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip, com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        super.onResumeSport();
        if (this.ar != 264 || this.bh) {
            return;
        }
        this.af.sendEmptyMessage(803);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
