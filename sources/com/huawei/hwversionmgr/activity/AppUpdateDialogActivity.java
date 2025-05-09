package com.huawei.hwversionmgr.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.ixx;
import defpackage.kxk;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.UnitUtil;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class AppUpdateDialogActivity extends Activity implements View.OnClickListener {
    private HealthTextView aa;
    private HealthButton ab;
    private HealthTextView ac;
    private HealthTextView ae;
    private HealthTextView af;
    private HealthTextView ag;
    private HealthTextView ah;
    private HealthTextView ai;
    private HealthTextView aj;
    private LinearLayout ak;
    private HealthTextView an;
    private kxk c;
    private ImageView g;
    private HealthButton h;
    private HealthTextView i;
    private HealthButton j;
    private HealthProgressBar k;
    private HealthTextView l;
    private LinearLayout n;
    private HealthTextView o;
    private LinearLayout p;
    private HealthProgressBar q;
    private Intent s;
    private LinearLayout u;
    private HealthButton w;
    private HealthTextView z;
    private Context m = null;
    private boolean y = false;
    private boolean t = false;
    private boolean r = false;
    private long ad = 0;
    private boolean v = false;
    private int e = 0;
    private String b = null;
    private String d = null;
    private String f = null;
    private boolean x = false;

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f6411a = new BroadcastReceiver() { // from class: com.huawei.hwversionmgr.activity.AppUpdateDialogActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a("AppUpdateDialogActivity", "mBroadcastReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("AppUpdateDialogActivity", "onReceive: action = ", action);
            if ("action_app_check_new_version_state".equals(action)) {
                AppUpdateDialogActivity.this.bSr_(intent);
            }
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.m = this;
        LogUtil.a("AppUpdateDialogActivity", "onCreate()");
        this.c = kxk.d();
        setContentView(R.layout.activity_app_update_dialog);
        Window window = getWindow();
        window.setWindowAnimations(R.style.app_update_dialog_anim);
        bSq_(window);
        h();
        Intent intent = getIntent();
        this.s = intent;
        if (intent == null) {
            return;
        }
        boolean z = intent.hasExtra("mChangeLog") || this.s.hasExtra("UpdateMode");
        this.x = z;
        if (z) {
            g();
        } else {
            j();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        BroadcastManagerUtil.bFA_(this.m, this.f6411a, intentFilter, LocalBroadcast.c, null);
    }

    private void bSq_(Window window) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (nsn.ag(this.m)) {
            attributes.width = -1;
            window.setGravity(17);
        } else if (nsn.cLh_(this)) {
            attributes.width = -1;
            window.setGravity(80);
        } else {
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            attributes.width = (nsn.n() - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue();
            window.setGravity(80);
        }
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
    }

    private void j() {
        String str;
        String str2;
        int i = 0;
        String str3 = null;
        try {
            this.y = this.s.getBooleanExtra("isForced", false);
            str = this.s.getStringExtra("name");
            try {
                i = this.s.getIntExtra("size", 0);
                str3 = this.s.getStringExtra("message");
            } catch (Exception unused) {
                LogUtil.b("AppUpdateDialogActivity", "initUpdateMode Exception");
                String str4 = str3;
                str2 = str;
                long j = i;
                this.ad = j;
                LogUtil.a("AppUpdateDialogActivity", "appname:", str2, "appsize", Integer.valueOf(i), "message", str4);
                if (str2 != null) {
                }
                i();
                c();
                return;
            }
        } catch (Exception unused2) {
            str = null;
        }
        String str42 = str3;
        str2 = str;
        long j2 = i;
        this.ad = j2;
        LogUtil.a("AppUpdateDialogActivity", "appname:", str2, "appsize", Integer.valueOf(i), "message", str42);
        if (str2 != null || i == 0 || str42 == null) {
            i();
            c();
            return;
        }
        this.t = true;
        this.c.b(str2);
        this.c.d(nsn.b(this.m, j2));
        LogUtil.a("AppUpdateDialogActivity", "initUpdateMode: Utils.formatFileSize = ", nsn.b(this.m, this.ad), ",result = ", Formatter.formatFileSize(this.m, this.ad));
        kxk kxkVar = this.c;
        kxkVar.e(kxkVar.a(str42));
        p();
    }

    private void g() {
        try {
            this.y = this.s.getBooleanExtra("isForced", false);
            this.e = this.s.getIntExtra("appNewVersionNumSize", this.e);
            this.b = this.s.getStringExtra("mAppNewVersion");
            this.d = HwVersionManager.c(this.m).t(this.s.getStringExtra("mAppNewFeatureContent"));
            this.f = this.s.getStringExtra("mChangeLog");
        } catch (Exception unused) {
            LogUtil.b("AppUpdateDialogActivity", "initValue Exception");
        }
        this.t = true;
        this.c.c(this.e);
        this.c.d(nsn.b(this.m, this.e));
        this.c.b(this.b);
        this.c.e(this.d);
        LogUtil.a("AppUpdateDialogActivity", "initValue() appContent = ", this.c.f(), ", appVersion = ", this.c.i(), ", mAppNewVersionNumSize = ", nsn.b(this.m, this.e), ", mAppNewVersionNumSize = ", Formatter.formatFileSize(this.m, this.e), "isForced = ", Boolean.valueOf(this.y));
        this.c.e(this.m, true);
        if (ParamConstants.CallbackMethod.ON_FAIL.equals(this.f)) {
            LogUtil.a("AppUpdateDialogActivity", "STATE_FETCH_CHANGELOG_FAILED");
            this.t = false;
            a(this.m.getString(R.string._2130841545_res_0x7f020fc9));
            return;
        }
        p();
    }

    @Override // android.app.Activity
    protected void onResume() {
        LogUtil.a("AppUpdateDialogActivity", "onResume()");
        super.onResume();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        LogUtil.a("AppUpdateDialogActivity", "onBackPressed() isForcedUpdate = " + this.y);
        if (this.y) {
            q();
        } else {
            if (this.r) {
                this.ab.setText(this.m.getString(R.string._2130841130_res_0x7f020e2a));
                this.w.setText(this.m.getString(R.string._2130841395_res_0x7f020f33));
                a(this.m.getString(R.string.IDS_service_area_notice_title), this.m.getString(R.string._2130841453_res_0x7f020f6d));
                return;
            }
            finish();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        LogUtil.a("AppUpdateDialogActivity", "onConfigurationChanged()");
        super.onConfigurationChanged(configuration);
        bSq_(getWindow());
    }

    private void i() {
        this.n.setVisibility(0);
        this.ak.setVisibility(8);
        this.p.setVisibility(8);
        this.u.setVisibility(8);
        this.i.setText(this.m.getString(R.string._2130841850_res_0x7f0210fa));
        this.q.setVisibility(0);
    }

    private boolean l() {
        return "com.huawei.bone".equals(BaseApplication.getAppPackage());
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            BroadcastReceiver broadcastReceiver = this.f6411a;
            if (broadcastReceiver != null) {
                unregisterReceiver(broadcastReceiver);
            }
        } catch (Exception unused) {
            LogUtil.b("AppUpdateDialogActivity", "onDestroy Exception");
        }
        this.m = null;
        LogUtil.a("AppUpdateDialogActivity", "onDestroy()");
    }

    private void h() {
        LogUtil.a("AppUpdateDialogActivity", "Enter initView!");
        this.n = (LinearLayout) nsy.cMc_(this, R.id.AppUpdateDialog_check_layout);
        this.i = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_check_textView);
        this.q = (HealthProgressBar) nsy.cMc_(this, R.id.AppUpdateDialog_check_progressbar);
        this.ak = (LinearLayout) nsy.cMc_(this, R.id.AppUpdateDialog_show_changelog);
        this.ae = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_title);
        this.aj = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_version);
        this.an = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_version_value);
        this.af = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_size);
        this.ai = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_size_value);
        this.ah = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_detail);
        this.aa = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_context);
        this.ag = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_update_tip);
        this.h = (HealthButton) nsy.cMc_(this, R.id.AppUpdateDialog_show_left);
        this.j = (HealthButton) nsy.cMc_(this, R.id.AppUpdateDialog_show_right);
        this.p = (LinearLayout) nsy.cMc_(this, R.id.AppUpdateDialog_progress_layout);
        this.o = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_progress_text);
        this.l = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_progress);
        this.k = (HealthProgressBar) nsy.cMc_(this, R.id.AppUpdateDialog_progressbar);
        this.g = (ImageView) nsy.cMc_(this, R.id.AppUpdateDialog_cancel);
        this.u = (LinearLayout) nsy.cMc_(this, R.id.AppUpdateDialog_notification);
        this.z = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_notification_context);
        this.ac = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_notification_title);
        this.w = (HealthButton) nsy.cMc_(this, R.id.AppUpdateDialog_notification_left);
        this.ab = (HealthButton) nsy.cMc_(this, R.id.AppUpdateDialog_notification_right);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        LogUtil.a("AppUpdateDialogActivity", "mIsForced = ", Boolean.valueOf(this.y));
        if (id == R.id.AppUpdateDialog_show_left) {
            o();
        } else if (id == R.id.AppUpdateDialog_show_right) {
            s();
        } else if (id == R.id.AppUpdateDialog_cancel) {
            m();
        } else if (id == R.id.AppUpdateDialog_notification_left) {
            n();
        } else if (id == R.id.AppUpdateDialog_notification_right) {
            k();
        } else {
            LogUtil.a("AppUpdateDialogActivity", "onClick else branch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void k() {
        LogUtil.a("AppUpdateDialogActivity", "notification user choose continue :", Boolean.valueOf(this.t));
        if (this.t) {
            if (this.v) {
                d();
                return;
            } else {
                a();
                return;
            }
        }
        i();
        c();
    }

    private void n() {
        if (this.y) {
            Intent intent = new Intent();
            intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
            LocalBroadcastManager.getInstance(this.m).sendBroadcast(intent);
        }
        if (this.r) {
            this.c.c();
        }
        finish();
    }

    private void m() {
        if (this.y) {
            q();
            return;
        }
        this.ab.setText(this.m.getString(R.string._2130841130_res_0x7f020e2a));
        this.w.setText(this.m.getString(R.string._2130841395_res_0x7f020f33));
        a(this.m.getString(R.string.IDS_service_area_notice_title), this.m.getString(R.string._2130841453_res_0x7f020f6d));
    }

    private void s() {
        f();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.ABOUT_1150001.value(), hashMap, 0);
    }

    private void o() {
        if (this.y) {
            q();
        } else {
            finish();
        }
    }

    private void e(int i) {
        String string;
        LogUtil.a("AppUpdateDialogActivity", "enter checkVersionFailed()");
        if (i == 0) {
            LogUtil.a("AppUpdateDialogActivity", "No New Version");
            nrh.c(getApplicationContext(), this.m.getString(R.string._2130841484_res_0x7f020f8c));
            finish();
        } else {
            if (i == 1) {
                a(this.m.getResources().getString(R.string._2130841548_res_0x7f020fcc));
                return;
            }
            if (i == 2) {
                string = this.m.getResources().getString(R.string._2130841552_res_0x7f020fd0);
            } else {
                string = this.m.getResources().getString(R.string._2130841553_res_0x7f020fd1);
            }
            a(string);
        }
    }

    private void bSp_(Intent intent, int i, String str) {
        LogUtil.a("AppUpdateDialogActivity", "enter checkVersionSuccess()");
        this.t = true;
        this.c.c(i);
        long j = i;
        this.c.d(nsn.b(this.m, j));
        this.c.b(str);
        LogUtil.a("AppUpdateDialogActivity", "STATE_CHECK_NEW_VERSION_SUCCESS: appVersion = ", this.c.i(), " ,size = ", nsn.b(this.m, j), ", result = ", Formatter.formatFileSize(this.m, j));
        this.c.e(this.m, true);
        try {
            this.y = intent.getBooleanExtra("isForced", false);
        } catch (BadParcelableException unused) {
            LogUtil.b("AppUpdateDialogActivity", "checkVersionSuccess() BadParcelableException");
        }
        LogUtil.a("AppUpdateDialogActivity", "check success!  mIsForced :", Boolean.valueOf(this.y));
    }

    private void e() {
        LogUtil.a("AppUpdateDialogActivity", "enter fetchFailed()");
        this.t = false;
        a(this.m.getString(R.string._2130841545_res_0x7f020fc9));
    }

    private void d(int i) {
        LogUtil.a("AppUpdateDialogActivity", "downloadFail()", Integer.valueOf(i));
        this.v = false;
        if (i == 1) {
            a(this.m.getString(R.string._2130841542_res_0x7f020fc6));
        } else if (i == 11) {
            this.w.setText(this.m.getString(R.string._2130841130_res_0x7f020e2a));
            this.ab.setText(this.m.getString(R.string._2130841379_res_0x7f020f23));
            a(this.m.getString(R.string.IDS_service_area_notice_title), this.m.getString(R.string._2130841457_res_0x7f020f71));
            this.v = true;
        } else if (i == 3) {
            a(this.m.getString(R.string._2130841548_res_0x7f020fcc));
        } else if (i == 4) {
            String string = this.m.getString(R.string._2130841495_res_0x7f020f97);
            if (nsn.ae(BaseApplication.getContext())) {
                string = this.m.getString(R.string._2130844350_res_0x7f021abe, UnitUtil.e(10.0d, 2, 0));
            }
            e(string);
        } else {
            a(this.m.getString(R.string._2130841543_res_0x7f020fc7));
        }
        this.r = false;
    }

    private void b() {
        LogUtil.a("AppUpdateDialogActivity", "enter downloadSuccess()");
        this.v = false;
        this.r = false;
        this.c.m();
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        kxk kxkVar = this.c;
        kxkVar.e(kxkVar.a(str));
        LogUtil.a("AppUpdateDialogActivity", "fetchSuccess() appContent = ", this.c.f());
        p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void bSr_(android.content.Intent r9) {
        /*
            r8 = this;
            java.lang.String r0 = "AppUpdateDialogActivity"
            r1 = -1
            java.lang.String r2 = "state"
            int r2 = r9.getIntExtra(r2, r1)     // Catch: android.os.BadParcelableException -> L18
            java.lang.String r3 = "result"
            int r1 = r9.getIntExtra(r3, r1)     // Catch: android.os.BadParcelableException -> L19
            java.lang.String r3 = "content"
            java.lang.String r3 = r9.getStringExtra(r3)     // Catch: android.os.BadParcelableException -> L19
            goto L25
        L18:
            r2 = r1
        L19:
            java.lang.String r3 = "updateAppState() BadParcelableException"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)
            java.lang.String r3 = ""
        L25:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            java.lang.String r5 = ", result = "
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)
            java.lang.String r7 = "updateAppState: state = "
            java.lang.Object[] r4 = new java.lang.Object[]{r7, r4, r5, r6}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)
            r4 = 11
            if (r2 == r4) goto L94
            r4 = 12
            if (r2 == r4) goto L90
            r9 = 27
            if (r2 == r9) goto L8c
            r9 = 40
            if (r2 == r9) goto L7b
            r9 = 31
            if (r2 == r9) goto L77
            r9 = 32
            if (r2 == r9) goto L73
            switch(r2) {
                case 20: goto L6f;
                case 21: goto L6b;
                case 22: goto L67;
                case 23: goto L63;
                default: goto L54;
            }
        L54:
            java.lang.String r9 = "updateAppState default = "
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r1}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r9)
            goto L97
        L63:
            r8.b()
            goto L97
        L67:
            r8.d(r1)
            goto L97
        L6b:
            r8.a(r1)
            goto L97
        L6f:
            r8.r()
            goto L97
        L73:
            r8.b(r3)
            goto L97
        L77:
            r8.e()
            goto L97
        L7b:
            r9 = 47
            if (r1 != r9) goto L97
            android.content.Context r9 = r8.m
            r0 = 2130841546(0x7f020fca, float:1.7288162E38)
            java.lang.String r9 = r9.getString(r0)
            r8.a(r9)
            goto L97
        L8c:
            r8.t()
            goto L97
        L90:
            r8.bSp_(r9, r1, r3)
            goto L97
        L94:
            r8.e(r1)
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwversionmgr.activity.AppUpdateDialogActivity.bSr_(android.content.Intent):void");
    }

    private void r() {
        LogUtil.a("AppUpdateDialogActivity", "STATE_DOWNLOAD_APP_START");
        a(0);
        this.r = true;
        Context context = this.m;
        nrh.c(context, context.getString(R.string._2130843516_res_0x7f02177c));
        finish();
    }

    private void t() {
        this.c.c((Boolean) true);
        finish();
    }

    private void p() {
        LogUtil.a("AppUpdateDialogActivity", "Enter showAppNewVersion");
        this.n.setVisibility(8);
        this.p.setVisibility(8);
        this.ak.setVisibility(0);
        this.u.setVisibility(8);
        this.ag.setVisibility(0);
        this.ae.setText(this.m.getString(R.string._2130841460_res_0x7f020f74));
        this.aj.setText(this.m.getString(R.string._2130841852_res_0x7f0210fc));
        this.an.setText(this.c.i());
        this.af.setText(this.m.getString(R.string._2130841853_res_0x7f0210fd));
        this.ai.setText(this.c.h());
        this.ah.setText(this.m.getString(R.string._2130841854_res_0x7f0210fe));
        this.aa.setText(this.c.f());
        this.h.setText(this.m.getString(R.string._2130841855_res_0x7f0210ff));
        this.j.setText(this.m.getString(R.string._2130841856_res_0x7f021100));
        this.j.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.hwversionmgr.activity.AppUpdateDialogActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                AppUpdateDialogActivity.this.j.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (AppUpdateDialogActivity.this.j.getLineCount() > 1 || AppUpdateDialogActivity.this.h.getLineCount() > 1) {
                    if (AppUpdateDialogActivity.this.j.getParent() instanceof LinearLayout) {
                        ((LinearLayout) AppUpdateDialogActivity.this.j.getParent()).setOrientation(1);
                    }
                    AppUpdateDialogActivity.this.findViewById(R.id.AppUpdateDialog_show_divider).setVisibility(8);
                }
            }
        });
        this.h.setOnClickListener(this);
        this.j.setOnClickListener(this);
    }

    private void a(int i) {
        LogUtil.a("AppUpdateDialogActivity", "Enter showAppDownloadProgress progress = ", Integer.valueOf(i));
        if (!l()) {
            this.k.setProgressDrawable(getResources().getDrawable(R.drawable._2131427928_res_0x7f0b0258));
        }
        this.l.setText(String.valueOf(i) + "%");
        this.k.setProgress(i);
    }

    private void a(String str) {
        this.w.setText(this.m.getString(R.string._2130841130_res_0x7f020e2a));
        this.ab.setText(this.m.getString(R.string._2130841467_res_0x7f020f7b));
        a(this.m.getString(R.string._2130841496_res_0x7f020f98), str);
        this.c.c((Boolean) false);
    }

    private void a(String str, String str2) {
        LogUtil.a("AppUpdateDialogActivity", "showErrorMsg : ", str2);
        this.n.setVisibility(8);
        this.p.setVisibility(8);
        this.ak.setVisibility(8);
        this.u.setVisibility(0);
        this.ac.setText(str);
        this.z.setText(str2);
        this.w.setOnClickListener(this);
        this.ab.setOnClickListener(this);
    }

    private void c() {
        LogUtil.a("AppUpdateDialogActivity", "doCheckAppNewVersion");
        this.c.g();
    }

    private void f() {
        boolean a2 = this.c.a(r0.j());
        LogUtil.a("AppUpdateDialogActivity", "handleAppNewVersion: mAppNewVersionNumSize = ", Integer.valueOf(this.c.j()), "handleAppNewVersion: isMemoryEnough = ", Boolean.valueOf(a2));
        if (!a2) {
            a(this.m.getString(R.string._2130841547_res_0x7f020fcb));
        } else {
            a();
        }
    }

    private void a() {
        boolean n = this.c.n();
        LogUtil.a("AppUpdateDialogActivity", "handleAppNewVersion: isWifiConnected = ", Boolean.valueOf(n));
        if (!n) {
            if (CommonUtil.ah(this.m)) {
                this.w.setText(this.m.getString(R.string._2130841130_res_0x7f020e2a));
                this.ab.setText(this.m.getString(R.string._2130841379_res_0x7f020f23));
                a(this.m.getString(R.string.IDS_service_area_notice_title), this.m.getString(R.string._2130841457_res_0x7f020f71));
                this.v = true;
                return;
            }
            d();
            return;
        }
        d();
    }

    private void d() {
        this.c.e();
        LogUtil.a("AppUpdateDialogActivity", "doDownloadAppFile mIsDownloading: ", Boolean.valueOf(this.r));
        this.n.setVisibility(8);
        this.p.setVisibility(8);
        this.ak.setVisibility(8);
        this.u.setVisibility(8);
        this.o.setText(this.m.getString(R.string._2130841851_res_0x7f0210fb));
        this.g.setOnClickListener(this);
        if (this.r) {
            return;
        }
        this.c.b();
    }

    private void q() {
        this.w.setText(this.m.getString(R.string._2130841492_res_0x7f020f94));
        this.ab.setText(this.m.getString(R.string._2130841549_res_0x7f020fcd));
        a(this.m.getString(R.string.IDS_service_area_notice_title), this.m.getString(R.string._2130841826_res_0x7f0210e2));
    }

    private void e(String str) {
        this.w.setText(this.m.getString(R.string._2130841794_res_0x7f0210c2));
        this.ab.setVisibility(8);
        a(this.m.getString(R.string.IDS_service_area_notice_title), str);
    }
}
