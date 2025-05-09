package com.huawei.sim.multisim;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.cvs;
import defpackage.ixx;
import defpackage.jdi;
import defpackage.ktx;
import defpackage.lnj;
import defpackage.lnl;
import defpackage.lnm;
import defpackage.lnn;
import defpackage.loh;
import defpackage.lok;
import defpackage.nbv;
import defpackage.nbw;
import defpackage.nbx;
import defpackage.ncd;
import defpackage.ncf;
import defpackage.sqo;
import health.compact.a.Base64;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes6.dex */
public class MultiSimConfigActivity extends BaseActivity implements View.OnClickListener {
    private static String[] d = {"android.permission.READ_PHONE_STATE"};

    /* renamed from: a, reason: collision with root package name */
    private boolean f8705a;
    private LinearLayout ab;
    private LinearLayout ac;
    private LinearLayout ad;
    private nbx af;
    private e ah;
    private lok ai;
    private HealthButton al;
    private View ao;
    private HealthProgressBar ap;
    private HealthButton aq;
    private View ar;
    private int as;
    private HealthButton au;
    private HealthButton aw;
    private HealthTextView ax;
    private Animation b;
    private HealthButton g;
    private Context h;
    private HealthTextView k;
    private CustomTextAlertDialog l;
    private HealthButton m;
    private CustomViewDialog n;
    private LinearLayout p;
    private LinearLayout u;
    private LinearLayout v;
    private LinearLayout w;
    private LinearLayout x;
    private LinearLayout y;
    private LinearLayout z;
    private String t = "";
    private String an = "";
    private int aj = 0;
    private int j = 0;
    private int f = 0;
    private CommonDialog21 am = null;
    private MultiSimDeviceInfo o = new MultiSimDeviceInfo();
    private String ae = "";
    private String aa = "";
    private boolean q = false;
    private boolean r = false;
    private final int s = 120000;
    private String i = "";
    private final BroadcastReceiver ak = new BroadcastReceiver() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("MultiSimConfigActivity", "intent is null or action is null");
                sqo.o("intent is null or action is null.");
                return;
            }
            LogUtil.a("MultiSimConfigActivity", "connectedChanged mNonLocalBroadcastReceiver(), intent = ", intent.getAction());
            try {
                if ("android.intent.action.SIM_STATE_CHANGED".equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra("ss");
                    if (stringExtra == null) {
                        LogUtil.h("MultiSimConfigActivity", "simState is null");
                        sqo.o("simState is null");
                        return;
                    }
                    LogUtil.a("MultiSimConfigActivity", "connectedChanged simState = ", stringExtra);
                    if ("UNKNOWN".equals(stringExtra)) {
                        sqo.o("connectedChanged simState = UNKNOWN");
                        MultiSimConfigActivity.this.finish();
                        return;
                    }
                    return;
                }
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || "com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    if (!"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || ncf.csO_(intent)) {
                        MultiSimConfigActivity.this.ah.sendEmptyMessage(1);
                    } else {
                        LogUtil.h("MultiSimConfigActivity", "mNonLocalBroadcastReceiver is device not support current function");
                        sqo.o("mNonLocalBroadcastReceiver is device not support current function");
                    }
                }
            } catch (Exception e2) {
                LogUtil.b("MultiSimConfigActivity", "mNonLocalBroadcastReceiver is Exception");
                sqo.o("mNonLocalBroadcastReceiver is Exception :" + e2.getMessage());
            }
        }
    };
    private View.OnClickListener e = new View.OnClickListener() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new Handler().post(new Runnable() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.7.3
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.c("MultiSimConfigActivity", "bt reconnect");
                    ncf.i(MultiSimConfigActivity.this);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private ClickableSpan c = new ClickableSpan() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.10
        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            MultiSimConfigActivity.this.k();
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            textPaint.setColor(MultiSimConfigActivity.this.getResources().getColor(R.color._2131296315_res_0x7f09003b));
        }
    };
    private View.OnClickListener ag = new View.OnClickListener() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.8
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new Handler().post(new Runnable() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.8.1
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.c("MultiSimConfigActivity", "network setting");
                    ncf.j(MultiSimConfigActivity.this.h);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    private boolean c(int i) {
        if (i == 99) {
            return true;
        }
        return i >= 300 && i <= 599;
    }

    public void b() {
        LogUtil.a("MultiSimConfigActivity", "initSdk");
        lok lokVar = new lok();
        this.ai = lokVar;
        if (this.f8705a) {
            this.ai.d(this.h, this.af, new String(Base64.e("MENBM0RBMzQ5QUExQ0I2OA=="), StandardCharsets.UTF_8), new String(Base64.e("MDZFMUJDMjgwRDFDQ0RCMTFCQjczNEZGREU3QUIyNkYwMzc0QkZFNTY2NUU2MkU4MjZDQzA1N0MwN0IyN0Q2OA=="), StandardCharsets.UTF_8));
            return;
        }
        lokVar.d(this.h);
    }

    public void e() {
        LogUtil.a("MultiSimConfigActivity", "finishSdk");
        lok lokVar = this.ai;
        if (lokVar != null) {
            lokVar.a();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("MultiSimConfigActivity", "onCreate");
        this.h = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.t = intent.getStringExtra("simImsi");
            this.as = intent.getIntExtra("MultiSimSlotId", 0);
        }
        this.ah = new e(this);
        DeviceCapability d2 = cvs.d();
        if (d2 != null) {
            this.f8705a = d2.isSupportNewEsim();
        }
        v();
        m();
        b();
        ac();
        r();
        if (jdi.c(this.h, d)) {
            return;
        }
        LogUtil.h("MultiSimConfigActivity", "no permission need to request");
        jdi.bFL_(this, d, new PermissionsResultAction() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.6
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.c("MultiSimConfigActivity", "onGranted");
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.c("MultiSimConfigActivity", "onDenied");
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "MultiSimConfigActivity", "IS_ESIM_START_FIRST");
        LogUtil.a("MultiSimConfigActivity", "sharedPreference = ", b);
        if (!TextUtils.isEmpty(b)) {
            this.aw.setText(this.h.getString(R.string._2130848002_res_0x7f022902));
        }
        s();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("MultiSimConfigActivity", "onDestroy");
        e();
        t();
        e eVar = this.ah;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
        HealthProgressBar healthProgressBar = this.ap;
        if (healthProgressBar != null) {
            healthProgressBar.setVisibility(8);
        }
        af();
    }

    private void r() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this, this.ak, intentFilter, LocalBroadcast.c, null);
    }

    private void af() {
        try {
            LogUtil.a("MultiSimConfigActivity", "Enter unregisterNonLocalBroadcast()!");
            unregisterReceiver(this.ak);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("MultiSimConfigActivity", "unregisterBroadcast IllegalArgumentException");
        } catch (RuntimeException unused2) {
            LogUtil.b("MultiSimConfigActivity", "unregisterBroadcast RuntimeException");
        }
    }

    private void m() {
        setContentView(R.layout.activity_multi_sim_config);
        this.x = (LinearLayout) findViewById(R.id.multi_sim_config_open_error);
        this.ab = (LinearLayout) findViewById(R.id.multi_sim_config_query_error);
        this.ac = (LinearLayout) findViewById(R.id.multi_sim_config_unbind);
        this.v = (LinearLayout) findViewById(R.id.multi_sim_config_fail);
        this.ad = (LinearLayout) findViewById(R.id.multi_sim_config_waiting);
        this.z = (LinearLayout) findViewById(R.id.multi_sim_config_start);
        HealthButton healthButton = (HealthButton) findViewById(R.id.multi_sim_cinfig_btn_start);
        this.aw = healthButton;
        healthButton.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.multi_sim_config_btn_requery);
        this.aq = healthButton2;
        healthButton2.setOnClickListener(this);
        HealthButton healthButton3 = (HealthButton) findViewById(R.id.multi_sim_config_btn_unbind);
        this.au = healthButton3;
        healthButton3.setOnClickListener(this);
        HealthButton healthButton4 = (HealthButton) findViewById(R.id.multi_sim_config_btn_fail);
        this.g = healthButton4;
        healthButton4.setOnClickListener(this);
        HealthButton healthButton5 = (HealthButton) findViewById(R.id.multi_sim_config_btn_confirm);
        this.al = healthButton5;
        healthButton5.setOnClickListener(this);
        HealthButton healthButton6 = (HealthButton) findViewById(R.id.multi_sim_cinfig_btn_download);
        this.m = healthButton6;
        healthButton6.setOnClickListener(this);
        this.w = (LinearLayout) findViewById(R.id.network_error_bar);
        this.u = (LinearLayout) findViewById(R.id.no_sim_err_bar);
        this.y = (LinearLayout) findViewById(R.id.bt_disconnect);
        this.p = (LinearLayout) findViewById(R.id.bt_connecting);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.network_err_text);
        this.ax = healthTextView;
        healthTextView.setOnClickListener(this);
        View findViewById = findViewById(R.id.set_bt_reconnect);
        this.ar = findViewById;
        findViewById.setOnClickListener(this.e);
        View findViewById2 = findViewById(R.id.set_network_error);
        this.ao = findViewById2;
        findViewById2.setOnClickListener(this.ag);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("MultiSimConfigActivity", "view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        LogUtil.a("MultiSimConfigActivity", "onclick view:", Integer.valueOf(id));
        if (id == R.id.multi_sim_cinfig_btn_download) {
            ktx.e().a("LPA:1$esim.yhdzd.chinamobile.com:8015$", (IBaseResponseCallback) null, (IBaseResponseCallback) null);
        } else if (id == R.id.multi_sim_cinfig_btn_start) {
            SharedPreferenceManager.e(BaseApplication.getContext(), "MultiSimConfigActivity", "IS_ESIM_START_FIRST", "hasclicked", (StorageParams) null);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MULTISIM_1170004.value(), new HashMap(16), 0);
            o();
            this.aj = 0;
        } else if (id == R.id.multi_sim_config_btn_requery) {
            n();
        } else if (id == R.id.multi_sim_config_btn_unbind) {
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MULTISIM_1170005.value(), new HashMap(16), 0);
            a(this.ae, this.aa, "ES");
        } else if (id == R.id.multi_sim_config_btn_fail) {
            h();
        } else if (id == R.id.multi_sim_config_btn_confirm) {
            if (this.aj == 2) {
                h();
            } else {
                finish();
            }
        } else if (id == R.id.network_err_text) {
            this.ah.sendEmptyMessageDelayed(1, 2000L);
            x();
            this.p.setVisibility(0);
            this.b = AnimationUtils.loadAnimation(this, R.anim._2130771992_res_0x7f010018);
            ((ImageView) findViewById(R.id.bt_connecting_imgage)).startAnimation(this.b);
        } else {
            LogUtil.h("MultiSimConfigActivity", "unknown view");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ad() {
        if (this.am == null && !isFinishing()) {
            LogUtil.a("MultiSimConfigActivity", "no dialog");
            new CommonDialog21(this, R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.am = a2;
            a2.e(this.h.getString(R.string._2130847931_res_0x7f0228bb));
            this.am.a();
            return;
        }
        LogUtil.h("MultiSimConfigActivity", "showLoadingDialog have dialog");
    }

    private void d() {
        LogUtil.a("MultiSimConfigActivity", "enter dismissLoadingDialog()");
        CommonDialog21 commonDialog21 = this.am;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        LogUtil.a("MultiSimConfigActivity", "dismiss Dialog!");
        if (isFinishing()) {
            return;
        }
        this.am.dismiss();
        this.am = null;
    }

    private void csq_(SpannableString spannableString) {
        View inflate = View.inflate(this.h, R.layout.layout_esim_dialog_text, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.esim_tips_content);
        this.k = healthTextView;
        healthTextView.setText(spannableString);
        this.k.setMovementMethod(LinkMovementMethod.getInstance());
        this.k.setVisibility(0);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.h);
        builder.a(this.h.getString(R.string._2130847965_res_0x7f0228dd)).czg_(inflate).czf_(this.h.getString(R.string._2130847966_res_0x7f0228de).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("MultiSimConfigActivity", "user choose cancel");
                if (MultiSimConfigActivity.this.n != null) {
                    MultiSimConfigActivity.this.n.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.n = e2;
        e2.show();
    }

    private void d(String str) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.h);
        builder.b(this.h.getString(R.string._2130847965_res_0x7f0228dd));
        builder.e(str);
        builder.cyV_(this.h.getString(R.string._2130847966_res_0x7f0228de), new View.OnClickListener() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("MultiSimConfigActivity", "user choose cancel");
                if (MultiSimConfigActivity.this.l != null) {
                    MultiSimConfigActivity.this.l.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.l = a2;
        a2.setCancelable(false);
        if (this.l.isShowing()) {
            return;
        }
        this.l.show();
    }

    private void c(lnl lnlVar) {
        LogUtil.a("MultiSimConfigActivity", "refreshOpenMultiSimResult");
        t();
        if (lnlVar == null) {
            LogUtil.h("MultiSimConfigActivity", "refreshOpenMultiSimResult result is null");
            c(getString(R.string._2130847951_res_0x7f0228cf), getString(R.string._2130847956_res_0x7f0228d4));
            return;
        }
        ReleaseLogUtil.e("DEVMGR_MultiSimConfigActivity", "refreshOpenMultiSimResult result action = ", Integer.valueOf(lnlVar.c()), " result = ", Integer.valueOf(lnlVar.a()), " reason = ", Integer.valueOf(lnlVar.b()));
        if (lnlVar.a() == 0) {
            loh d2 = lnlVar.d();
            a(d2.c(), d2.b(), "WS");
            return;
        }
        if (lnlVar.a() == 2) {
            LogUtil.a("MultiSimConfigActivity", "operation is processing and do nothing");
            return;
        }
        if (lnlVar.b() == 1004) {
            c(a(this.aj), getString(R.string._2130847967_res_0x7f0228df));
            return;
        }
        if (lnlVar.b() == 0) {
            b(getString(R.string._2130847955_res_0x7f0228d3), getString(R.string._2130847956_res_0x7f0228d4));
            return;
        }
        if (lnlVar.b() == 2007) {
            c(getString(R.string._2130847951_res_0x7f0228cf), getString(R.string._2130847964_res_0x7f0228dc));
        } else if (c(lnlVar.b())) {
            c(a(this.aj), getString(R.string._2130847967_res_0x7f0228df));
        } else {
            c(getString(R.string._2130847951_res_0x7f0228cf), lnlVar.b());
        }
    }

    private int b(ArrayList<lnm> arrayList) {
        if (arrayList == null) {
            LogUtil.h("MultiSimConfigActivity", "getCurrentBindStatus pairedDeviceList is null");
            return 0;
        }
        Iterator<lnm> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            lnm next = it.next();
            if (next.b().equals(this.o.getEID())) {
                String a2 = next.a();
                if (TextUtils.isEmpty(a2)) {
                    this.i = "LPA:1$esim.yhdzd.chinamobile.com:8015$";
                } else {
                    String str = "LPA:1$" + a2 + SampleEvent.SEPARATOR;
                    if (a2.startsWith("LPA:1$")) {
                        str = a2;
                    }
                    this.i = str;
                }
                LogUtil.c("MultiSimConfigActivity", "mCmccAcCode=", this.i, " serverAddress=", a2);
                ncf.b("", false);
                Iterator<SimInfo> it2 = this.o.getSimInfoList().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        i = 4;
                        break;
                    }
                    SimInfo next2 = it2.next();
                    LogUtil.a("MultiSimConfigActivity", "getCurrentBindStatus sim iccid=", next2.getICCID());
                    if (next.d().equals(next2.getICCID())) {
                        i = 1;
                        break;
                    }
                }
            }
        }
        return i;
    }

    private int a(lnl lnlVar) {
        lnj e2 = lnlVar.e();
        if (e2 == null) {
            return 2;
        }
        if (lnlVar.b() == 1502) {
            return 0;
        }
        if (lnlVar.b() == 2008) {
            return 4;
        }
        ArrayList<lnm> a2 = e2.a();
        lnn d2 = e2.d();
        if (d2 != null) {
            this.an = d2.d();
        } else {
            LogUtil.h("MultiSimConfigActivity", "refreshQueryMultiSimRet result PrimaryDevice is null");
        }
        return b(a2);
    }

    private void d(lnl lnlVar) {
        ReleaseLogUtil.e("DEVMGR_MultiSimConfigActivity", "refreshQueryMultiSimResult result=", lnlVar);
        t();
        if (lnlVar == null) {
            LogUtil.h("MultiSimConfigActivity", "refreshQueryMultiSimResult result is null");
            b(null, getString(R.string._2130847955_res_0x7f0228d3));
            return;
        }
        LogUtil.a("MultiSimConfigActivity", "refreshQueryMultiSimResult result action = ", Integer.valueOf(lnlVar.c()), " result = ", Integer.valueOf(lnlVar.a()), " reason = ", Integer.valueOf(lnlVar.b()));
        if (lnlVar.a() == 0 || lnlVar.b() == 1500 || lnlVar.b() == 1501 || lnlVar.b() == 1502) {
            int a2 = a(lnlVar);
            loh d2 = lnlVar.d();
            this.ae = d2.e();
            this.aa = d2.a();
            LogUtil.a("MultiSimConfigActivity", "refreshQueryMultiSimRet status=", Integer.valueOf(a2), " mManagerUrl=", this.ae, " mManagerPostData=", this.aa);
            if (a2 == 1) {
                h();
                return;
            } else if (a2 == 4) {
                a();
                return;
            } else {
                g();
                return;
            }
        }
        if (lnlVar.a() == 2) {
            LogUtil.a("MultiSimConfigActivity", "operation is processing and do nothing");
        } else {
            c(a(this.aj), getString(R.string._2130847967_res_0x7f0228df));
        }
    }

    private void e(int i) {
        ReleaseLogUtil.e("DEVMGR_MultiSimConfigActivity", "refreshMultiSimAuthResult result:", Integer.valueOf(i));
        if (i == 1000) {
            if (this.aj == 0) {
                o();
                return;
            } else {
                n();
                return;
            }
        }
        if (i == 98) {
            c(a(3), getString(R.string._2130848010_res_0x7f02290a));
            return;
        }
        if (i == 403) {
            d(a(3), 403);
            sqo.o("ERROR_REASON_VOLTE_ERROR");
        } else if (i == -1005) {
            c(a(3), getString(R.string._2130848061_res_0x7f02293d));
            sqo.o("UNSUPPORTED_ANDROID_VERSION_EXCEPTION");
        } else if (i == -1006) {
            c(a(3), getString(R.string._2130848059_res_0x7f02293b));
            sqo.o("ONLY_PRIMARY_CARD_EXCEPTION");
        } else {
            d(a(3), 99);
            sqo.o("ERROR_REASON_SERVER_ERROR");
        }
    }

    private String a(int i) {
        if (i == 2) {
            return getString(R.string._2130847960_res_0x7f0228d8);
        }
        if (i == 1) {
            return getString(R.string._2130847958_res_0x7f0228d6);
        }
        if (i == 3) {
            return getString(R.string._2130847969_res_0x7f0228e1);
        }
        return getString(R.string._2130847951_res_0x7f0228cf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        e eVar = this.ah;
        if (eVar == null || !eVar.hasMessages(i)) {
            return;
        }
        this.ah.removeMessages(i);
    }

    static class e extends Handler {
        WeakReference<MultiSimConfigActivity> d;

        e(MultiSimConfigActivity multiSimConfigActivity) {
            this.d = new WeakReference<>(multiSimConfigActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("MultiSimConfigActivity", "handleMessage message is null");
                sqo.o("handleMessage message is null");
                return;
            }
            super.handleMessage(message);
            MultiSimConfigActivity multiSimConfigActivity = this.d.get();
            if (multiSimConfigActivity == null) {
                LogUtil.h("MultiSimConfigActivity", "handleMessage activity is null");
                sqo.o("handleMessage activity is null");
            } else {
                LogUtil.a("MultiSimConfigActivity", "handleMessage message:", Integer.valueOf(message.what));
                MultiSimConfigActivity.csp_(multiSimConfigActivity, message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void csp_(MultiSimConfigActivity multiSimConfigActivity, Message message) {
        int i = message.what;
        if (i == 1) {
            multiSimConfigActivity.s();
        }
        if (i == 2) {
            LogUtil.a("MultiSimConfigActivity", "message MULTI_SIM_MSG_OPEN_SUCCESS message:", message);
            if (message.obj instanceof lnl) {
                multiSimConfigActivity.c((lnl) message.obj);
                return;
            } else {
                LogUtil.h("MultiSimConfigActivity", "message.obj not instanceof MultiSimAsyncResult");
                return;
            }
        }
        if (i == 3) {
            csn_(multiSimConfigActivity, message);
            return;
        }
        if (i == 8) {
            multiSimConfigActivity.b(8);
            multiSimConfigActivity.ac();
            return;
        }
        if (i == 9) {
            multiSimConfigActivity.t();
            multiSimConfigActivity.b(multiSimConfigActivity.a(multiSimConfigActivity.aj), multiSimConfigActivity.getString(R.string._2130847956_res_0x7f0228d4));
            return;
        }
        switch (i) {
            case 13:
                multiSimConfigActivity.e(message.arg1);
                break;
            case 14:
                cso_(multiSimConfigActivity, message);
                break;
            case 15:
                multiSimConfigActivity.b(15);
                multiSimConfigActivity.c(multiSimConfigActivity.getString(R.string._2130847958_res_0x7f0228d6), multiSimConfigActivity.getString(R.string.IDS_plugin_device_info_fail));
                break;
            case 16:
                multiSimConfigActivity.b(16);
                multiSimConfigActivity.f();
                break;
            default:
                LogUtil.h("MultiSimConfigActivity", "Unknown message");
                break;
        }
    }

    private static void csn_(MultiSimConfigActivity multiSimConfigActivity, Message message) {
        LogUtil.a("MultiSimConfigActivity", "message MULTI_SIM_MSG_QUERY_RESULT message:", message);
        if (message.obj instanceof lnl) {
            multiSimConfigActivity.d((lnl) message.obj);
        } else {
            LogUtil.h("MultiSimConfigActivity", "message.obj not instanceof MultiSimAsyncResult");
        }
    }

    private static void cso_(MultiSimConfigActivity multiSimConfigActivity, Message message) {
        multiSimConfigActivity.b(16);
        if (message.arg1 == 0) {
            multiSimConfigActivity.ac();
            if (ncf.b() && !Utils.o()) {
                ncf.a("", "0");
            }
            ktx.e().a(true);
            return;
        }
        ktx.e().a(false);
        multiSimConfigActivity.f();
    }

    private void y() {
        LinearLayout linearLayout = this.x;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        LinearLayout linearLayout2 = this.ab;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
        LinearLayout linearLayout3 = this.ac;
        if (linearLayout3 != null) {
            linearLayout3.setVisibility(8);
        }
        LinearLayout linearLayout4 = this.v;
        if (linearLayout4 != null) {
            linearLayout4.setVisibility(8);
        }
        LinearLayout linearLayout5 = this.ad;
        if (linearLayout5 != null) {
            linearLayout5.setVisibility(8);
        }
        HealthProgressBar healthProgressBar = this.ap;
        if (healthProgressBar != null) {
            healthProgressBar.setVisibility(8);
        }
        LinearLayout linearLayout6 = this.z;
        if (linearLayout6 != null) {
            linearLayout6.setVisibility(8);
        }
        d();
    }

    private void ac() {
        LogUtil.a("MultiSimConfigActivity", "btState :", Boolean.valueOf(ncd.c()));
        q();
        LogUtil.a("MultiSimConfigActivity", "mCurrentConnectStatus = ", Integer.valueOf(this.j));
        int i = this.j;
        if (i == 3 || i == 2 || !ncd.c()) {
            if (this.j == 2 || !ncd.c()) {
                c(getString(R.string._2130847958_res_0x7f0228d6), getString(R.string._2130848008_res_0x7f022908));
            }
            if (this.j == 3) {
                c(getString(R.string._2130847958_res_0x7f0228d6), getString(R.string._2130848009_res_0x7f022909));
            }
            z();
            w();
            return;
        }
        LogUtil.a("MultiSimConfigActivity", "mCurrentConnectStatus, startCurrentView");
        String c = c();
        if (this.j != 0 || !ncf.b(c)) {
            g();
        } else {
            a(getString(R.string._2130847950_res_0x7f0228ce), (String) null);
            u();
        }
        q();
    }

    private void u() {
        ktx.e().b(new IBaseResponseCallback() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.13
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("MultiSimConfigActivity", "getMultiSimDevInfo onResponse errorCode :", Integer.valueOf(i));
                if (i != 0 || !(obj instanceof MultiSimDeviceInfo)) {
                    MultiSimConfigActivity.this.ah.sendEmptyMessage(15);
                    return;
                }
                MultiSimConfigActivity.this.o = (MultiSimDeviceInfo) obj;
                LogUtil.a("MultiSimConfigActivity", "getLocalDeviceInfo mDeviceInfo :", MultiSimConfigActivity.this.o);
                MultiSimConfigActivity multiSimConfigActivity = MultiSimConfigActivity.this;
                if (!multiSimConfigActivity.e(multiSimConfigActivity.o)) {
                    MultiSimConfigActivity.this.ah.sendEmptyMessage(15);
                    return;
                }
                MultiSimConfigActivity.this.aj = 1;
                LogUtil.a("MultiSimConfigActivity", "mIsGbaAuth =", Boolean.valueOf(MultiSimConfigActivity.this.r));
                if (!MultiSimConfigActivity.this.r) {
                    MultiSimConfigActivity.this.aa();
                } else {
                    MultiSimConfigActivity.this.n();
                }
            }
        });
    }

    private void x() {
        LinearLayout linearLayout = this.w;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        LinearLayout linearLayout2 = this.u;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
        LinearLayout linearLayout3 = this.y;
        if (linearLayout3 != null) {
            linearLayout3.setVisibility(8);
        }
        LinearLayout linearLayout4 = this.p;
        if (linearLayout4 != null) {
            linearLayout4.setVisibility(8);
        }
    }

    private void z() {
        LogUtil.a("MultiSimConfigActivity", "setViewStatusBar status:", Integer.valueOf(this.j));
        int i = this.j;
        if (i == 1) {
            this.u.setVisibility(0);
            return;
        }
        if (i == 2) {
            LinearLayout linearLayout = this.y;
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            if (this.f8705a) {
                this.ar.setVisibility(8);
                return;
            } else {
                this.ar.setVisibility(0);
                return;
            }
        }
        if (i == 3) {
            this.w.setVisibility(0);
        } else if (i == 4) {
            ((HealthTextView) findViewById(R.id.no_sim_err_txt)).setText(getString(R.string._2130847968_res_0x7f0228e0));
            this.u.setVisibility(0);
        } else {
            LogUtil.h("MultiSimConfigActivity", "setViewStatusBar ok");
        }
    }

    private void w() {
        if (this.j == 0) {
            int i = this.f;
            if (i == 1) {
                b(this.au);
                return;
            }
            if (i == 4) {
                b(this.aq);
                return;
            }
            if (i == 9) {
                b(this.aw);
                return;
            } else if (i == 10) {
                b(this.g);
                return;
            } else {
                LogUtil.h("MultiSimConfigActivity", "setButtonStatus ok, default view");
                return;
            }
        }
        int i2 = this.f;
        if (i2 == 1) {
            d(this.au);
            return;
        }
        if (i2 == 4) {
            d(this.aq);
            return;
        }
        if (i2 == 9) {
            d(this.aw);
        } else if (i2 == 10) {
            d(this.g);
        } else {
            LogUtil.h("MultiSimConfigActivity", "setButtonStatus default view");
        }
    }

    private void s() {
        LogUtil.a("MultiSimConfigActivity", "refreshViewStatus :", Integer.valueOf(this.f));
        x();
        int i = this.f;
        if (i == 3 || i == 2 || i == 10 || i == 8) {
            return;
        }
        q();
        z();
        w();
    }

    private boolean l() {
        boolean z;
        Object systemService = this.h.getSystemService("phone");
        if (systemService instanceof TelephonyManager) {
            if (((TelephonyManager) systemService).getSimState() == 5) {
                z = true;
                LogUtil.a("MultiSimConfigActivity", "isSimStatusReady isSimReady:", Boolean.valueOf(z));
                return z;
            }
        } else {
            LogUtil.h("MultiSimConfigActivity", "object not instanceof TelephonyManager");
        }
        z = false;
        LogUtil.a("MultiSimConfigActivity", "isSimStatusReady isSimReady:", Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(MultiSimDeviceInfo multiSimDeviceInfo) {
        return (multiSimDeviceInfo == null || multiSimDeviceInfo.getEID() == null || "".equals(multiSimDeviceInfo.getEID())) ? false : true;
    }

    private void q() {
        if (!l()) {
            this.j = 1;
            return;
        }
        if (!ncd.a(this.h)) {
            this.j = 3;
        } else if (!ncd.c() || !ncd.e(this.h)) {
            this.j = 2;
        } else {
            this.j = 0;
        }
    }

    private void d(HealthButton healthButton) {
        if (healthButton != null) {
            healthButton.setEnabled(false);
            healthButton.setClickable(false);
            healthButton.setFocusable(false);
            healthButton.setTextColor(getResources().getColor(R.color._2131296316_res_0x7f09003c));
        }
    }

    private void b(HealthButton healthButton) {
        if (healthButton != null) {
            healthButton.setEnabled(true);
            healthButton.setClickable(true);
            healthButton.setFocusable(true);
            healthButton.setTextColor(getResources().getColor(R.color._2131296315_res_0x7f09003b));
        }
    }

    private String i() {
        if (TextUtils.isEmpty(this.an)) {
            this.an = "";
        }
        return this.an;
    }

    private void h() {
        LogUtil.a("MultiSimConfigActivity", "initMultiSimUnbindView");
        this.f = 1;
        y();
        this.ac.setVisibility(0);
        s();
        String i = i();
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.multi_sim_unbind_notice);
        if (healthTextView != null) {
            healthTextView.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130847959_res_0x7f0228d7), i));
        }
    }

    private void g() {
        LogUtil.a("MultiSimConfigActivity", "initMultiSimStartView");
        this.f = 9;
        y();
        this.z.setVisibility(0);
        s();
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.multi_sim_start_tip_tip1);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.multi_sim_start_tip_tip2);
        if (healthTextView != null) {
            healthTextView.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_plugin_multi_device_note_no1), UnitUtil.e(1.0d, 1, 0)));
        }
        if (healthTextView2 != null) {
            healthTextView2.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130848012_res_0x7f02290c), UnitUtil.e(2.0d, 1, 0)));
        }
        String string = this.h.getString(R.string.IDS_plugin_multi_device_touch_query);
        String string2 = this.h.getString(R.string.IDS_plugin_multi_device_note_no2, UnitUtil.e(3.0d, 1, 0), string);
        int indexOf = string2.indexOf(string);
        int[] iArr = {indexOf};
        if (indexOf == -1) {
            return;
        }
        SpannableString spannableString = new SpannableString(string2);
        ClickableSpan clickableSpan = this.c;
        int i = iArr[0];
        spannableString.setSpan(clickableSpan, i, string.length() + i, 33);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.multi_sim_start_tip_tip3);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.multi_sim_start_tip_tip4);
        if (healthTextView3 != null) {
            healthTextView3.setText(spannableString);
            healthTextView3.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if (healthTextView4 != null) {
            healthTextView4.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130848013_res_0x7f02290d), UnitUtil.e(4.0d, 1, 0)));
        }
    }

    private void d(String str, int i) {
        String string;
        LogUtil.a("MultiSimConfigActivity", "initMultiSimOperationFailView");
        this.f = 3;
        y();
        s();
        this.x.setVisibility(0);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.multi_sim_open_error_notice);
        if (healthTextView != null && str != null && !str.isEmpty()) {
            healthTextView.setText(str);
            healthTextView.setVisibility(0);
        }
        String string2 = this.h.getString(R.string._2130847954_res_0x7f0228d2);
        if (i == 403) {
            string = this.h.getString(R.string._2130848011_res_0x7f02290b, string2);
        } else {
            string = this.h.getString(R.string._2130848006_res_0x7f022906, string2);
        }
        int indexOf = string.indexOf(string2);
        int[] iArr = {indexOf};
        if (indexOf == -1) {
            return;
        }
        SpannableString spannableString = new SpannableString(string);
        ClickableSpan clickableSpan = this.c;
        int i2 = iArr[0];
        spannableString.setSpan(clickableSpan, i2, string2.length() + i2, 33);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.multi_sim_open_error_tip);
        if (healthTextView2 != null) {
            healthTextView2.setText(spannableString);
            healthTextView2.setMovementMethod(LinkMovementMethod.getInstance());
            healthTextView2.setVisibility(0);
        }
    }

    private void c(String str, String str2) {
        LogUtil.a("MultiSimConfigActivity", "initMultiSimOperationFailView");
        this.f = 3;
        y();
        s();
        LinearLayout linearLayout = this.x;
        if (linearLayout == null) {
            return;
        }
        linearLayout.setVisibility(0);
        LogUtil.a("MultiSimConfigActivity", "mLayoutOpenError VISIBLE ID = " + this.x.getId());
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.multi_sim_open_error_notice);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.multi_sim_open_error_tip);
        if (str != null && !str.isEmpty() && healthTextView != null) {
            healthTextView.setText(str);
            healthTextView.setVisibility(0);
        }
        if (healthTextView2 != null) {
            if (str2 != null && !str2.isEmpty()) {
                healthTextView2.setText(str2);
                healthTextView2.setVisibility(0);
            } else {
                healthTextView2.setVisibility(8);
            }
        }
    }

    private void c(String str, int i) {
        LogUtil.a("MultiSimConfigActivity", "initMultiSimOperationFailView errorCode", Integer.valueOf(i));
        this.f = 3;
        y();
        s();
        this.x.setVisibility(0);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.multi_sim_open_error_notice);
        if (healthTextView != null && str != null && !str.isEmpty()) {
            healthTextView.setText(str);
            healthTextView.setVisibility(0);
        }
        String string = this.h.getString(R.string._2130847954_res_0x7f0228d2);
        String string2 = this.h.getString(R.string._2130847953_res_0x7f0228d1, Integer.valueOf(i), string);
        int indexOf = string2.indexOf(string);
        int[] iArr = {indexOf};
        if (indexOf == -1) {
            return;
        }
        SpannableString spannableString = new SpannableString(string2);
        ClickableSpan clickableSpan = this.c;
        int i2 = iArr[0];
        spannableString.setSpan(clickableSpan, i2, string.length() + i2, 33);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.multi_sim_open_error_tip);
        if (healthTextView2 != null) {
            healthTextView2.setText(spannableString);
            healthTextView2.setMovementMethod(LinkMovementMethod.getInstance());
            healthTextView2.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("MultiSimConfigActivity", "openMultiSimErrorDetailActivity");
        ThreadPoolManager.d().d("MultiSimConfigActivity", new Runnable() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.12
            @Override // java.lang.Runnable
            public void run() {
                final String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainTipsResDbankcdn");
                MultiSimConfigActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.12.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MultiSimConfigActivity.this.h != null) {
                            try {
                                Intent intent = new Intent(MultiSimConfigActivity.this.h, (Class<?>) WebViewActivity.class);
                                if (!MultiSimConfigActivity.this.f8705a) {
                                    if (LanguageUtil.m(MultiSimConfigActivity.this.h)) {
                                        LogUtil.c("MultiSimConfigActivity", "openAppHelpActivity url: ", "/SmartWear/Leo/EMUI8.0/C001B001/zh-CN/content/doub.html?pos=3");
                                        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", url + "/SmartWear/Leo/EMUI8.0/C001B001/zh-CN/content/doub.html?pos=3");
                                    } else {
                                        LogUtil.c("MultiSimConfigActivity", "openAppHelpActivity url: ", "/SmartWear/Leo/EMUI8.0/C001B001/en-US/content/en-us_topic_0209307574.html?pos=3");
                                        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", url + "/SmartWear/Leo/EMUI8.0/C001B001/en-US/content/en-us_topic_0209307574.html?pos=3");
                                    }
                                } else {
                                    intent.putExtra("WebViewActivity.REQUEST_URL_KEY", url + "/hwtips/topic/M005/zh-CN/SF-10190314_f2260.html");
                                }
                                intent.putExtra(Constants.JUMP_MODE_KEY, 0);
                                MultiSimConfigActivity.this.h.startActivity(intent);
                            } catch (ActivityNotFoundException unused) {
                                LogUtil.b("MultiSimConfigActivity", "openMultiSimErrorDetailActivity ActivityNotFoundException");
                            }
                        }
                    }
                });
            }
        });
    }

    private void b(String str, String str2) {
        LogUtil.a("MultiSimConfigActivity", "initMultiSimOpenQueryFailView");
        this.f = 4;
        y();
        s();
        this.ab.setVisibility(0);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.multi_sim_query_error_notice);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.multi_sim_query_error_tip);
        if (healthTextView != null && str != null && !str.isEmpty()) {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        }
        if (healthTextView2 != null && str2 != null && !str2.isEmpty()) {
            healthTextView2.setVisibility(0);
            healthTextView2.setText(str2);
        }
        s();
    }

    private void a(String str, String str2) {
        LogUtil.a("MultiSimConfigActivity", "initMultiSimWaitingView");
        this.f = 2;
        y();
        s();
        this.ad.setVisibility(0);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.multi_sim_waiting_info);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.multi_sim_waiting_info_tip);
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
        if (healthTextView2 != null && str2 != null && !str2.isEmpty()) {
            healthTextView2.setVisibility(0);
            healthTextView2.setText(str2);
        }
        HealthProgressBar healthProgressBar = (HealthProgressBar) findViewById(R.id.multi_sim_waiting_image);
        this.ap = healthProgressBar;
        if (healthProgressBar != null) {
            healthProgressBar.setVisibility(0);
        }
    }

    private void f() {
        LogUtil.a("MultiSimConfigActivity", "initMultiSimConfigFailView");
        this.f = 10;
        y();
        this.v.setVisibility(0);
        String string = this.h.getString(R.string._2130847954_res_0x7f0228d2);
        String string2 = this.h.getString(R.string._2130848016_res_0x7f022910, string);
        int indexOf = string2.indexOf(string);
        int[] iArr = {indexOf};
        if (indexOf == -1) {
            return;
        }
        SpannableString spannableString = new SpannableString(string2);
        ClickableSpan clickableSpan = this.c;
        int i = iArr[0];
        spannableString.setSpan(clickableSpan, i, string.length() + i, 33);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.multi_sim_fail_notice);
        if (healthTextView != null) {
            healthTextView.setText(spannableString);
            healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
            healthTextView.setVisibility(0);
        }
        s();
    }

    private void p() {
        e eVar = this.ah;
        if (eVar == null || !eVar.hasMessages(9)) {
            return;
        }
        this.ah.removeMessages(9);
    }

    private void t() {
        LogUtil.a("MultiSimConfigActivity", "removeNotifyMessage");
        p();
    }

    private nbw j() {
        nbw nbwVar = new nbw();
        nbwVar.d(3);
        nbwVar.e(this.o.getEID());
        nbwVar.c(this.o.getProductName());
        nbwVar.f(this.o.getDeviceSerialNumber());
        nbwVar.b(this.o.getDeviceIMEI());
        return nbwVar;
    }

    private void o() {
        LogUtil.a("MultiSimConfigActivity", "openMultiSim");
        String c = c();
        if (!ncf.b(c)) {
            LogUtil.a("MultiSimConfigActivity", "main sim not cmcc");
            String string = this.h.getString(R.string._2130841564_res_0x7f020fdc);
            String string2 = this.h.getString(R.string.IDS_plugin_multi_device_sim_only_support_china_mobile, string);
            int indexOf = string2.indexOf(string);
            int[] iArr = {indexOf};
            if (indexOf == -1) {
                return;
            }
            SpannableString spannableString = new SpannableString(string2);
            ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.5
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    MultiSimConfigActivity.this.ab();
                    ViewClickInstrumentation.clickOnView(view);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                    textPaint.setColor(MultiSimConfigActivity.this.getResources().getColor(R.color._2131296315_res_0x7f09003b));
                }
            };
            int i = iArr[0];
            spannableString.setSpan(clickableSpan, i, string.length() + i, 33);
            csq_(spannableString);
            return;
        }
        if (!e(this.o)) {
            LogUtil.a("MultiSimConfigActivity", "EID of watch is not correct");
            d(getString(R.string._2130848015_res_0x7f02290f));
            return;
        }
        nbv nbvVar = new nbv();
        nbvVar.b(c);
        this.ah.sendEmptyMessageDelayed(9, OpAnalyticsConstants.H5_LOADING_DELAY);
        ad();
        LogUtil.a("MultiSimConfigActivity", "openMultiSim primaryDevice");
        this.ai.bYi_(nbvVar, j(), this.ah.obtainMessage(2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        LogUtil.a("startHelpInstructions enter", new Object[0]);
        ThreadPoolManager.d().d("MultiSimConfigActivity", new Runnable() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.3
            @Override // java.lang.Runnable
            public void run() {
                final String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainTipsResDbankcdn");
                MultiSimConfigActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.3.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MultiSimConfigActivity.this.h != null) {
                            try {
                                Intent intent = new Intent(MultiSimConfigActivity.this.h, (Class<?>) WebViewActivity.class);
                                if (LanguageUtil.m(MultiSimConfigActivity.this.h)) {
                                    intent.putExtra("WebViewActivity.REQUEST_URL_KEY", url + "/SmartWear/Leo/EMUI8.0/C001B001/zh-CN/content/doub.html?pos=2");
                                } else {
                                    intent.putExtra("WebViewActivity.REQUEST_URL_KEY", url + "/SmartWear/Leo/EMUI8.0/C001B001/en-US/content/en-us_topic_0209307574.html?pos=2");
                                }
                                intent.putExtra("WebViewActivity.TITLE", MultiSimConfigActivity.this.getString(R.string._2130847943_res_0x7f0228c7));
                                MultiSimConfigActivity.this.h.startActivity(intent);
                            } catch (ActivityNotFoundException unused) {
                                LogUtil.b("MultiSimConfigActivity", "startHelpInstructions ActivityNotFoundException");
                            }
                        }
                    }
                });
            }
        });
    }

    public String c() {
        if (!TextUtils.isEmpty(this.t)) {
            return this.t;
        }
        SubscriptionManager.from(this.h);
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        Object systemService = this.h.getSystemService("phone");
        if (systemService instanceof TelephonyManager) {
            TelephonyManager createForSubscriptionId = ((TelephonyManager) systemService).createForSubscriptionId(defaultDataSubscriptionId);
            if (createForSubscriptionId != null) {
                return createForSubscriptionId.getSubscriberId();
            }
            LogUtil.h("MultiSimConfigActivity", "newTelephonyManager is null");
            return "";
        }
        LogUtil.h("MultiSimConfigActivity", "object not instanceof TelephonyManager");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        LogUtil.a("MultiSimConfigActivity", "startMultiSimAuth");
        this.r = true;
        v();
        this.ai.bYl_(this.h, this.af, this.ah.obtainMessage(13));
    }

    private void v() {
        if (this.af == null) {
            this.af = new nbx(3);
        }
        this.af.setImsi(c());
        if (this.f8705a) {
            this.af.setSlotId(this.as);
        } else {
            this.af.setSlotId(ncf.b(this.h));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("MultiSimConfigActivity", "queryMultiSimStatus :", this.an);
        nbv nbvVar = new nbv();
        nbvVar.b(c());
        this.ah.sendEmptyMessageDelayed(9, OpAnalyticsConstants.H5_LOADING_DELAY);
        this.ai.bYk_(nbvVar, j(), this.ah.obtainMessage(3));
    }

    private void a(String str, String str2, String str3) {
        LogUtil.a("MultiSimConfigActivity", "startMultiSimWebView type=", str3, " url=", str, " postData=", str2);
        if (str == null || "".equals(str)) {
            LogUtil.h("MultiSimConfigActivity", "startMultiSimWebView url null");
            return;
        }
        d();
        Intent intent = new Intent(this, (Class<?>) MultiSimWebView.class);
        intent.putExtra("url", str);
        intent.putExtra("post_data", str2);
        intent.putExtra("req_type", str3);
        startActivityForResult(intent, 0);
    }

    void a() {
        LogUtil.a("MultiSimConfigActivity", "downLoadEsimProfile mIsDownload =", Boolean.valueOf(this.q));
        if (this.q) {
            h();
            return;
        }
        a(getString(R.string._2130848014_res_0x7f02290e), "");
        this.q = true;
        LogUtil.a("MultiSimConfigActivity", "mCmccAcCode =", this.i);
        this.ah.sendEmptyMessageDelayed(16, 120000L);
        if (ncf.b() && !Utils.o()) {
            ncf.a(this.i, String.valueOf(System.currentTimeMillis()));
        }
        ktx.e().e(this.i, new IBaseResponseCallback() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("MultiSimConfigActivity", "openEsimWithoutConfirm first errorCode =", Integer.valueOf(i));
            }
        }, new IBaseResponseCallback() { // from class: com.huawei.sim.multisim.MultiSimConfigActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("MultiSimConfigActivity", "openEsimWithoutConfirm second errorCode =", Integer.valueOf(i));
                ncf.c(i, true);
                MultiSimConfigActivity.this.b(16);
                Message obtainMessage = MultiSimConfigActivity.this.ah.obtainMessage(14);
                obtainMessage.arg1 = i;
                if (i == 0) {
                    MultiSimConfigActivity.this.ah.sendMessageDelayed(obtainMessage, 5000L);
                } else {
                    MultiSimConfigActivity.this.ah.sendMessage(obtainMessage);
                }
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("MultiSimConfigActivity", "Enter onActivityResult(): requestCode= ", Integer.valueOf(i), " resultCode=", Integer.valueOf(i2));
        if (i2 == 2) {
            this.q = false;
            LogUtil.a("MultiSimConfigActivity", "Enter onActivityResult(): download profile=mIsDownload=", false);
        }
        ac();
        super.onActivityResult(i, i2, intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
