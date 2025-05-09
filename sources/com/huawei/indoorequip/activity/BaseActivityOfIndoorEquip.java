package com.huawei.indoorequip.activity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.CircleProgressButtonForIndoorEquip;
import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.cun;
import defpackage.kyu;
import defpackage.kza;
import defpackage.kzc;
import defpackage.law;
import defpackage.lbc;
import defpackage.lbj;
import defpackage.lbv;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class BaseActivityOfIndoorEquip extends BaseActivity implements SportLifecycle {
    protected static float c;

    /* renamed from: a, reason: collision with root package name */
    protected HealthProgressBar f6418a;
    protected boolean aa;
    protected boolean ab;
    protected NfcAdapter ae;
    protected Handler af;
    protected IntentFilter ai;
    protected String aj;
    protected String ak;
    protected ImageView al;
    protected ImageView am;
    protected PendingIntent an;
    protected float ao;
    protected IndoorEquipViewModel au;
    protected String[][] av;
    protected law ax;
    protected c b;
    protected CircleProgressButtonForIndoorEquip e;
    protected ImageView g;
    protected Context i;
    protected DistributedApi k;
    protected b m;
    protected IntentFilter[] p;
    protected IntermitentJumpData q;
    protected kzc r;
    protected kyu s;
    protected boolean t;
    protected boolean w;
    protected boolean y;
    protected boolean u = true;
    protected boolean v = false;
    protected NoTitleCustomAlertDialog n = null;
    protected boolean z = false;
    protected boolean x = false;
    protected NoTitleCustomAlertDialog f = null;
    protected NoTitleCustomAlertDialog j = null;
    protected NoTitleCustomAlertDialog o = null;
    protected NoTitleCustomAlertDialog at = null;
    protected int as = 0;
    protected boolean ad = false;
    protected NoTitleCustomAlertDialog h = null;
    protected kza d = null;
    protected boolean ah = false;
    protected boolean ag = false;
    protected boolean l = false;
    protected int ar = 0;
    protected SupportDataRange aq = null;
    protected int ap = 6;
    protected boolean ac = false;

    protected void c(Map<Integer, Object> map) {
    }

    protected void r() {
    }

    protected void bTb_(SportLaunchParams sportLaunchParams, Handler handler, int i) {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "initViewModel: sportType=", Integer.valueOf(i));
        this.au = (IndoorEquipViewModel) new ViewModelProvider(this).get(IndoorEquipViewModel.class);
        LogUtil.c("IDEQ_BaseActivityOfIndoorEquip", "initViewModel: ExitApp=", Boolean.valueOf(this.v));
        this.au.bWa_(sportLaunchParams, this.v, handler);
        this.au.requestData();
        this.au.d(this.aa);
        ag();
        if (cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "IDEQ_BaseActivityOfIndoorEquip") != null) {
            this.l = true;
        } else {
            this.l = false;
        }
        SupportDataRange supportDataRange = this.aq;
        if (supportDataRange != null) {
            this.au.c(supportDataRange);
        }
        boolean p = this.au.p();
        this.t = p;
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "initViewModel: hasRunPostureDevice=", Boolean.valueOf(p));
        c(sportLaunchParams, i);
        this.ak = this.au.r();
    }

    public IndoorEquipViewModel l() {
        return this.au;
    }

    private void ag() {
        ah();
    }

    private void ah() {
        this.au.observeSportLifeCycle(k(), this);
    }

    protected void s() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "onCreate, start to init NFC");
        if (this.z) {
            NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this);
            this.ae = defaultAdapter;
            if (defaultAdapter != null) {
                Intent intent = new Intent(this, getClass());
                intent.setPackage(BaseApplication.d());
                intent.setFlags(536870912);
                this.an = PendingIntent.getActivity(this, 0, intent, AppRouterExtras.COLDSTART);
            } else {
                LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "initNfcModule: mNfcAdapter is null");
                this.an = null;
            }
            if (this.an != null) {
                this.ai = new IntentFilter("android.nfc.action.NDEF_DISCOVERED");
            } else {
                LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "initNfcModule: mNdef is null");
                this.ai = null;
            }
            IntentFilter intentFilter = this.ai;
            if (intentFilter != null) {
                try {
                    intentFilter.addDataType("huawei.sports/address");
                } catch (IntentFilter.MalformedMimeTypeException unused) {
                    LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "mNdef.addDataType exception");
                    this.ai = null;
                }
            }
            IntentFilter intentFilter2 = this.ai;
            if (intentFilter2 != null) {
                this.p = new IntentFilter[]{intentFilter2};
            } else {
                this.p = null;
            }
            if (this.p != null) {
                this.av = new String[][]{new String[]{NfcF.class.getName()}};
                LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "onCreate, NFC init success");
            } else {
                this.av = null;
                LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "onCreate, NFC init failed");
            }
        }
    }

    protected void t() {
        ai();
        n();
        s();
        IndoorEquipViewModel indoorEquipViewModel = this.au;
        if (indoorEquipViewModel == null || indoorEquipViewModel.b() || this.au.j()) {
            return;
        }
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "mViewModel.isSportServiceRunning() = ", Boolean.valueOf(this.au.j()));
        v();
    }

    private void ai() {
        if (AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING.equals(this.r.m()) || AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING.equals(this.r.m()) || AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED.equals(this.r.m())) {
            if (AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED.equals(this.r.m())) {
                Handler handler = this.af;
                if (handler != null) {
                    handler.sendEmptyMessage(305);
                    return;
                }
                return;
            }
            Handler handler2 = this.af;
            if (handler2 != null) {
                handler2.sendEmptyMessage(307);
                return;
            }
            return;
        }
        if (!AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED.equals(this.r.m())) {
            Handler handler3 = this.af;
            if (handler3 != null) {
                handler3.sendEmptyMessage(304);
                return;
            }
            return;
        }
        LogUtil.c("IDEQ_BaseActivityOfIndoorEquip", "BT Connected");
    }

    protected void n() {
        Handler handler;
        Handler handler2;
        this.ab = getRequestedOrientation() == 0;
        af();
        int i = this.ar;
        if (i == 283) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "sport type is SPORT_TYPE_ROPE_SKIPPING");
            return;
        }
        if (i == 274) {
            if (this.ad || (handler2 = this.af) == null) {
                return;
            }
            this.ad = true;
            handler2.sendEmptyMessage(10020);
            return;
        }
        if (this.ad || (handler = this.af) == null) {
            return;
        }
        this.ad = true;
        handler.sendEmptyMessage(10010);
    }

    protected void b() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "====enter dismissTipsForTooShortTwoButton()=====");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.n;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            return;
        }
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "mDialogForTooShortTwoButton is showing, dismiss it now");
        this.ah = false;
        this.n.dismiss();
    }

    protected void a() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.f;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.f.dismiss();
            if (this instanceof IndoorEquipDisplayActivity) {
                this.x = false;
            }
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog2 = this.n;
        if (noTitleCustomAlertDialog2 != null && noTitleCustomAlertDialog2.isShowing()) {
            this.n.dismiss();
            this.ah = false;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog3 = this.j;
        if (noTitleCustomAlertDialog3 == null || !noTitleCustomAlertDialog3.isShowing()) {
            return;
        }
        this.j.dismiss();
    }

    protected void v() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "====enter showTipsForTooShortOneButton()=====");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.h;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "DialogForTooShortOneButton and is showing, not show this dialog");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog2 = this.f;
        if (noTitleCustomAlertDialog2 != null && noTitleCustomAlertDialog2.isShowing()) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "TipsForTooShortOneButton is showing");
            return;
        }
        a();
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(getString(R.string._2130840305_res_0x7f020af1)).czE_(getString(R.string._2130840262_res_0x7f020ac6), new View.OnClickListener() { // from class: com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!BaseActivityOfIndoorEquip.this.isFinishing()) {
                    BaseActivityOfIndoorEquip.this.x = true;
                    BaseActivityOfIndoorEquip.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.f = e;
        e.setCancelable(false);
        this.f.show();
        this.x = true;
    }

    protected void u() {
        String string;
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "====enter showTipsForNotStartFromZero()=====");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.h;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "DialogForTooShortOneButton is showing, not show this dialog");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog2 = this.j;
        if (noTitleCustomAlertDialog2 != null && noTitleCustomAlertDialog2.isShowing()) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "mDialogForNotStartFromZero is showing");
            return;
        }
        a();
        if (this.ar == 264) {
            string = getString(R.string._2130840265_res_0x7f020ac9);
        } else {
            string = getString(R.string._2130840321_res_0x7f020b01);
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(string).czE_(getString(R.string._2130840262_res_0x7f020ac6), new View.OnClickListener() { // from class: com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.j = e;
        e.setCancelable(false);
        this.j.show();
    }

    protected void ab() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "enter showTipsStartOverTarget()");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.o;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(getString(R.string._2130840340_res_0x7f020b14)).czE_(getString(R.string._2130840262_res_0x7f020ac6), new View.OnClickListener() { // from class: kyy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseActivityOfIndoorEquip.this.bTc_(view);
                }
            }).e();
            this.o = e;
            e.setCancelable(false);
            this.o.show();
        }
    }

    public /* synthetic */ void bTc_(View view) {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "click stop");
        this.au.e(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    protected void ac() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "====enter mWearIsSportingDialog()=====");
        if (!lbc.f(this.ar)) {
            LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "not support indoor link not show this dialog");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.h;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "DialogForTooShortOneButton is showing, not show this dialog");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog2 = this.at;
        if (noTitleCustomAlertDialog2 != null && noTitleCustomAlertDialog2.isShowing()) {
            LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "mWearIsSportingDialog is showing");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(getString(R.string._2130840331_res_0x7f020b0b)).czE_(getString(R.string._2130840262_res_0x7f020ac6), new View.OnClickListener() { // from class: com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.at = e;
        e.setCancelable(false);
        this.at.show();
    }

    protected void aa() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "====enter showTipsForTooShortTwoButton()=====");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.h;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "DialogForTooShortOneButton is showing, not show this dialog!");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog2 = this.n;
        if (noTitleCustomAlertDialog2 != null && noTitleCustomAlertDialog2.isShowing()) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "mDialogForTooShortTwoButton is showing");
            return;
        }
        a();
        this.ah = true;
        String ae = ae();
        if (this instanceof IndoorEquipLandDisplayActivity) {
            if (this.as == 0) {
                ae = getString(R.string._2130840258_res_0x7f020ac2);
            } else {
                ae = ae();
            }
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(ae).czE_(getString(R.string._2130840263_res_0x7f020ac7), new View.OnClickListener() { // from class: com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseActivityOfIndoorEquip.this.ah = false;
                if (BaseActivityOfIndoorEquip.this.r.q()) {
                    LogUtil.c("IDEQ_BaseActivityOfIndoorEquip", "SHOW_WAIT_START_FLOAT_WINDOW");
                } else {
                    LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "service is null (6)");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(getString(R.string._2130840264_res_0x7f020ac8), new View.OnClickListener() { // from class: com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "call finishThisSession  (1)");
                if (BaseActivityOfIndoorEquip.this.au != null) {
                    BaseActivityOfIndoorEquip.this.au.e(false);
                } else {
                    BaseActivityOfIndoorEquip.this.f();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.n = e;
        e.setCancelable(false);
        this.n.show();
    }

    private String ae() {
        if (this.ar == 283) {
            return this.i.getResources().getString(R.string._2130843717_res_0x7f021845);
        }
        return this.i.getResources().getString(R.string._2130840260_res_0x7f020ac4);
    }

    protected void x() {
        String string;
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "====enter showTipsForTooShortOneButton()=====");
        a();
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.h;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "TipsForTooShortOneButton is showing");
            return;
        }
        if (this.ar == 283) {
            string = this.i.getResources().getString(R.string._2130843725_res_0x7f02184d);
        } else {
            string = this.i.getResources().getString(R.string._2130840259_res_0x7f020ac3);
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(string).czE_(getString(R.string._2130840262_res_0x7f020ac6), new View.OnClickListener() { // from class: com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseActivityOfIndoorEquip.this.ah = false;
                BaseActivityOfIndoorEquip.this.f();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.h = e;
        e.setCancelable(false);
        this.h.show();
        this.x = true;
    }

    protected void f() {
        LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "finishActivity,mService is null");
        if (isFinishing()) {
            return;
        }
        this.x = true;
        finish();
    }

    protected void q() {
        Object[] objArr = new Object[10];
        objArr[0] = "mIsSupportNfc";
        objArr[1] = Boolean.valueOf(this.z);
        objArr[2] = "mNfcAdapter != null";
        objArr[3] = Boolean.valueOf(this.ae != null);
        objArr[4] = "mPendingIntentForNfc != null ";
        objArr[5] = Boolean.valueOf(this.an != null);
        objArr[6] = "mIntentFiltersArray != null";
        objArr[7] = Boolean.valueOf(this.p != null);
        objArr[8] = "mTechListsArray != null";
        objArr[9] = Boolean.valueOf(this.av != null);
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", objArr);
        if (this.z && this.ae != null && this.an != null && this.p != null && this.av != null) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "NFC EnableForegroundDispatch");
            this.ae.enableForegroundDispatch(this, this.an, this.p, this.av);
        }
        if (this.au == null || lbc.e(this.ar)) {
            LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "skipping not support engine");
        }
    }

    protected void z() {
        if (this.au == null) {
            return;
        }
        if (this.ax == null) {
            law b2 = law.b(getApplicationContext(), this.l, this.au);
            this.ax = b2;
            b2.c(this.w);
        }
        this.au.observeSportLifeCycle(this.ax.e(), this.ax);
        kza kzaVar = this.d;
        if (kzaVar != null) {
            kzaVar.b(this.ax);
        }
        this.ax.d();
        if (this.as == 2) {
            this.au.v();
        }
    }

    private void c(SportLaunchParams sportLaunchParams, int i) {
        boolean booleanValue = ((Boolean) sportLaunchParams.getExtra("KEY_IS_SWITCH_TO_HORIZONTAL_SCREEN", Boolean.class, false)).booleanValue();
        if ((i != 283 || booleanValue) && !sportLaunchParams.isRestart()) {
            return;
        }
        this.au.onPreSport();
    }

    protected void w() {
        law lawVar = this.ax;
        if (lawVar != null) {
            lawVar.c();
            this.ax.b(true);
            this.ax.d();
        }
    }

    protected void b(boolean z) {
        this.w = z;
    }

    protected void c(String str) {
        if (str != null && str.contains("&tvn=")) {
            String c2 = lbv.c(str);
            if (TextUtils.isEmpty(c2)) {
                return;
            }
            Intent intent = new Intent("com.huawei.health.BROADCAST_INTENT_CONNECT_TV");
            intent.putExtra("KEY_OF_TV_DEVICE_NAME", c2);
            LocalBroadcastManager.getInstance(this.i).sendBroadcast(intent);
            return;
        }
        LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "partOfOnNewIntent, payload is null");
    }

    public SupportDataRange m() {
        return this.aq;
    }

    public int o() {
        return this.ar;
    }

    public void c(boolean z) {
        IndoorEquipViewModel indoorEquipViewModel = this.au;
        if (indoorEquipViewModel != null) {
            indoorEquipViewModel.b(z);
            if (z) {
                return;
            }
            this.au.unregisterAll();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        this.as = 2;
        ReleaseLogUtil.e("IDEQ_BaseActivityOfIndoorEquip", "sport state: ", 2);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        this.as = 1;
        n();
        ReleaseLogUtil.e("IDEQ_BaseActivityOfIndoorEquip", "sport state: ", Integer.valueOf(this.as));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.as = 1;
        ReleaseLogUtil.e("IDEQ_BaseActivityOfIndoorEquip", "sport state: ", 1);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.as = 3;
        ReleaseLogUtil.e("IDEQ_BaseActivityOfIndoorEquip", "sport state: ", 3);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        int i = this.ar;
        if (i == 264) {
            Toast.makeText(this.i, getString(R.string._2130840247_res_0x7f020ab7), 0).show();
        } else if (i == 283) {
            Toast.makeText(this.i, getString(R.string._2130839726_res_0x7f0208ae), 0).show();
        } else {
            Toast.makeText(this.i, getString(R.string._2130840324_res_0x7f020b04), 0).show();
        }
    }

    private void af() {
        int i = this.ar;
        if (i == 283) {
            this.am.setVisibility(8);
            this.al.setVisibility(8);
        } else if (i == 274) {
            this.am.setBackgroundResource(this.ab ? R.drawable._2131431264_res_0x7f0b0f60 : R.drawable._2131431258_res_0x7f0b0f5a);
            this.al.setBackgroundResource(this.ab ? R.drawable._2131431265_res_0x7f0b0f61 : R.drawable._2131431259_res_0x7f0b0f5b);
        } else {
            this.am.setBackgroundResource(this.ab ? R.drawable._2131431340_res_0x7f0b0fac : R.drawable._2131431337_res_0x7f0b0fa9);
            this.al.setBackgroundResource(this.ab ? R.drawable._2131431341_res_0x7f0b0fad : R.drawable._2131431338_res_0x7f0b0faa);
        }
    }

    protected void e() {
        this.ah = false;
        this.x = false;
        this.s.e(false);
        ad();
        this.s = null;
        this.d = null;
        this.af = null;
        this.au = null;
    }

    protected void a(int i) {
        if (i == 10010) {
            this.al.setBackgroundResource(this.ab ? R.drawable._2131431341_res_0x7f0b0fad : R.drawable._2131431338_res_0x7f0b0faa);
            d(10011);
        }
        if (i == 10011) {
            this.al.setBackgroundResource(this.ab ? R.drawable._2131431342_res_0x7f0b0fae : R.drawable._2131431339_res_0x7f0b0fab);
            d(10010);
            return;
        }
        switch (i) {
            case 10020:
                this.al.setBackgroundResource(this.ab ? R.drawable._2131431265_res_0x7f0b0f61 : R.drawable._2131431259_res_0x7f0b0f5b);
                e(10021);
                break;
            case 10021:
                this.al.setBackgroundResource(this.ab ? R.drawable._2131431266_res_0x7f0b0f62 : R.drawable._2131431260_res_0x7f0b0f5c);
                e(10022);
                break;
            case 10022:
                this.al.setBackgroundResource(this.ab ? R.drawable._2131431267_res_0x7f0b0f63 : R.drawable._2131431261_res_0x7f0b0f5d);
                e(10023);
                break;
            case 10023:
                this.al.setBackgroundResource(this.ab ? R.drawable._2131431268_res_0x7f0b0f64 : R.drawable._2131431262_res_0x7f0b0f5e);
                e(10024);
                break;
            case 10024:
                this.al.setBackgroundResource(this.ab ? R.drawable._2131431269_res_0x7f0b0f65 : R.drawable._2131431263_res_0x7f0b0f5f);
                e(10020);
                break;
        }
    }

    private void d(int i) {
        Handler handler = this.af;
        if (handler != null && this.as == 1) {
            handler.sendEmptyMessageDelayed(i, lbv.a(c));
        } else if (handler != null) {
            handler.removeMessages(10010);
            this.af.removeMessages(10011);
            this.ad = false;
        }
    }

    private void e(int i) {
        Handler handler = this.af;
        if (handler != null && this.as == 1) {
            handler.sendEmptyMessageDelayed(i, lbv.a(c));
            return;
        }
        if (handler != null) {
            handler.removeMessages(10020);
            this.af.removeMessages(10021);
            this.af.removeMessages(10022);
            this.af.removeMessages(10023);
            this.af.removeMessages(10024);
            this.ad = false;
        }
    }

    protected void p() {
        CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip = this.e;
        if (circleProgressButtonForIndoorEquip != null) {
            circleProgressButtonForIndoorEquip.b(new CircleProgressButtonForIndoorEquip.CircleProcessListener() { // from class: com.huawei.indoorequip.activity.BaseActivityOfIndoorEquip.8
                @Override // com.huawei.indoorequip.ui.CircleProgressButtonForIndoorEquip.CircleProcessListener
                public void onCancel() {
                }

                @Override // com.huawei.indoorequip.ui.CircleProgressButtonForIndoorEquip.CircleProcessListener
                public void onFinished() {
                    if (BaseActivityOfIndoorEquip.this.au != null) {
                        BaseActivityOfIndoorEquip.this.au.e(true);
                    } else {
                        LogUtil.b("IDEQ_BaseActivityOfIndoorEquip", "mService is null");
                    }
                }

                @Override // com.huawei.indoorequip.ui.CircleProgressButtonForIndoorEquip.CircleProcessListener
                public void onStarted() {
                    if (BaseActivityOfIndoorEquip.this.e.getState() == 6) {
                        Toast.makeText(BaseActivityOfIndoorEquip.this.i, BaseActivityOfIndoorEquip.this.getString(R.string._2130840252_res_0x7f020abc), 0).show();
                    }
                }
            });
        }
    }

    protected void b(int i) {
        if (i == 301) {
            d();
            return;
        }
        if (i == 307) {
            g();
            return;
        }
        if (i == 309) {
            i();
            return;
        }
        if (i == 702) {
            j();
        } else if (i == 304) {
            c();
        } else {
            if (i != 305) {
                return;
            }
            h();
        }
    }

    protected void d() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, MSG_BT_CONNECTING_SHOW");
        Handler handler = this.af;
        if (handler != null) {
            handler.sendEmptyMessage(702);
        }
        this.e.setState(6);
    }

    protected void g() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, MSG_BT_RECONNECTING_SHOW");
        Handler handler = this.af;
        if (handler != null) {
            handler.sendEmptyMessage(702);
            this.af.sendEmptyMessage(503);
        }
        CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip = this.e;
        if (circleProgressButtonForIndoorEquip != null) {
            circleProgressButtonForIndoorEquip.setState(6);
        }
        Toast.makeText(this.i, getString(R.string.ie_devicestate_connecting_again), 0).show();
    }

    protected void c() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, case is MSG_BT_DISCONNECTED_SHOW");
        HealthProgressBar healthProgressBar = this.f6418a;
        if (healthProgressBar != null) {
            healthProgressBar.setVisibility(8);
        }
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setImageDrawable(lbv.bVR_(this.i, lbj.c(this.ar, false)));
        }
        kza kzaVar = this.d;
        if (kzaVar != null) {
            kzaVar.a(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED);
        }
    }

    protected void h() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, case is MSG_BT_SERVICE_DISCOVER_SHOW");
        HealthProgressBar healthProgressBar = this.f6418a;
        if (healthProgressBar != null) {
            healthProgressBar.setVisibility(8);
        }
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setImageDrawable(lbv.bVR_(this.i, lbj.c(this.ar, true)));
        }
        CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip = this.e;
        if (circleProgressButtonForIndoorEquip != null) {
            circleProgressButtonForIndoorEquip.setState(0);
        }
        kza kzaVar = this.d;
        if (kzaVar != null) {
            kzaVar.a(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED);
        }
    }

    protected void i() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, case is MSG_BT_SERVICE_REDISCOVER_SHOW");
        HealthProgressBar healthProgressBar = this.f6418a;
        if (healthProgressBar != null) {
            healthProgressBar.setVisibility(8);
        }
        CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip = this.e;
        if (circleProgressButtonForIndoorEquip != null) {
            circleProgressButtonForIndoorEquip.setState(0);
        }
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setImageDrawable(lbv.bVR_(this.i, lbj.c(this.ar, true)));
        }
        Toast.makeText(this.i, getString(R.string.ie_devicestate_connected_again), 1).show();
        kza kzaVar = this.d;
        if (kzaVar != null) {
            kzaVar.a(AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED);
        }
    }

    protected void j() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, case is BT_ICON_CONNECTING");
        HealthProgressBar healthProgressBar = this.f6418a;
        if (healthProgressBar != null) {
            healthProgressBar.setVisibility(0);
        }
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setImageDrawable(lbv.bVR_(this.i, lbj.a(this.ar)));
        }
        kza kzaVar = this.d;
        if (kzaVar != null) {
            kzaVar.a(AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING);
        }
    }

    protected void c(int i) {
        if (i == 507) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage,  MSG_SHOW_TIPS_FOR_NOT_START_FROM_0");
            if (this.ag) {
                return;
            }
            this.ag = true;
            u();
            return;
        }
        if (i == 511) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, SHOW_TIPS_FOR_JUMP_TO_ZERO");
            Toast.makeText(this.i, getString(R.string._2130840266_res_0x7f020aca), 0).show();
            return;
        }
        if (i == 514) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, MSG_DIALOG_WEAR_SPORTING");
            ac();
            return;
        }
        if (i != 515) {
            switch (i) {
                case 501:
                    LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, MSG_SHOW_TIPS_FOR_TOO_SHORT");
                    if (!this.ah) {
                        this.ah = true;
                        x();
                        break;
                    }
                    break;
                case TypedValues.PositionType.TYPE_DRAWPATH /* 502 */:
                    LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, MSG_SHOW_TIPS_FOR_TOO_SHORT_TWO_BUTTON");
                    aa();
                    break;
                case 503:
                    LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage, MSG_DISMISS_TIPS_FOR_TOO_SHORT_TWO_BUTTON");
                    b();
                    break;
            }
            return;
        }
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "in handleMessage,  MSG_SHOW_TIPS_START_OVER_TARGET");
        ab();
    }

    protected void y() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "register BroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.FINISH_DISPLAY_ACTIVITY");
        intentFilter.addAction("com.huawei.health.FINISH_THIS_SESSION");
        this.b = new c();
        LocalBroadcastManager.getInstance(this.i).registerReceiver(this.b, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.huawei.hardware.display.action.WIFI_DISPLAY_CASTING");
        b bVar = new b();
        this.m = bVar;
        BroadcastManagerUtil.bFC_(this, bVar, intentFilter2, "com.huawei.wfd.permission.ACCESS_WIFI_DISPLAY_CASTING", null);
    }

    protected void ad() {
        LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "unregisterBroadcastReceiver");
        if (this.m != null) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "mDoubleScreenBroadcast is not null");
            unregisterReceiver(this.m);
            this.m = null;
        }
        if (this.b != null) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "mBroadcastReceiverInner is not null");
            LocalBroadcastManager.getInstance(this.i).unregisterReceiver(this.b);
            this.b = null;
        }
    }

    final class c extends BroadcastReceiver {
        private c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "Receive localBroadcast and action is ", intent.getAction());
                if ("com.huawei.health.FINISH_THIS_SESSION".equals(intent.getAction())) {
                    if (BaseActivityOfIndoorEquip.this.au != null) {
                        BaseActivityOfIndoorEquip.this.au.bVZ_(intent);
                    }
                } else if ("com.huawei.health.FINISH_DISPLAY_ACTIVITY".equals(intent.getAction())) {
                    BaseActivityOfIndoorEquip.this.x = true;
                    BaseActivityOfIndoorEquip.this.finish();
                } else {
                    LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "BroadcastReceiverInner other");
                }
            }
        }
    }

    class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("IDEQ_BaseActivityOfIndoorEquip", "DoubleScreenBroadcast");
            if (BaseActivityOfIndoorEquip.this.au == null) {
                LogUtil.h("IDEQ_BaseActivityOfIndoorEquip", "mViewModel == null");
            } else {
                BaseActivityOfIndoorEquip.this.z();
            }
        }
    }

    protected String k() {
        return "IDEQ_BaseActivityOfIndoorEquip";
    }
}
