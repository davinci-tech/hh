package com.huawei.wear.wallet.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.huawei.uikit.phone.hwbutton.widget.HwButton;
import com.huawei.wallet.proxy.utils.WearActivityManager;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import com.huawei.wear.oversea.overseamanger.QueryReviewResultCallback;
import com.huawei.wear.wallet.ui.SatcomGuideActivity;
import com.huawei.wear.wallet.ui.dialog.HwDialogInterface;
import com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface;
import com.huawei.wear.wallet.ui.dialog.WidgetBuilder;
import defpackage.ssz;
import defpackage.stq;
import defpackage.suj;
import defpackage.suw;
import defpackage.suy;
import defpackage.sva;
import defpackage.svc;
import defpackage.svd;
import defpackage.svf;
import defpackage.svq;
import defpackage.svt;
import defpackage.svw;
import defpackage.swa;
import defpackage.swb;
import defpackage.swe;
import defpackage.tei;
import defpackage.tel;
import java.lang.ref.WeakReference;
import java.util.Date;

/* loaded from: classes9.dex */
public class SatcomGuideActivity extends Activity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private TextView f11214a;
    private String b;
    private String c;
    private String e;
    private RelativeLayout f;
    private Context h;
    private TextView i;
    private String l;
    private HwProgressDialogInterface m;
    private HwButton n;
    private boolean g = false;
    private int d = 1;
    private e j = new e(this);

    public class e extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<SatcomGuideActivity> f11215a;

        public e(SatcomGuideActivity satcomGuideActivity) {
            this.f11215a = new WeakReference<>(satcomGuideActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<SatcomGuideActivity> weakReference = this.f11215a;
            if (weakReference == null) {
                if (message.getTarget() != null) {
                    message.getTarget().removeCallbacksAndMessages(null);
                    return;
                }
                return;
            }
            if (weakReference.get() != null) {
                switch (message.what) {
                    case 0:
                        SatcomGuideActivity.this.i.setText(SatcomGuideActivity.this.getResources().getQuantityString(R.plurals._2130903540_res_0x7f0301f4, SatcomGuideActivity.this.d, Integer.valueOf(SatcomGuideActivity.this.d)));
                        SatcomGuideActivity.this.f.setVisibility(0);
                        SatcomGuideActivity.this.n.setVisibility(8);
                        SatcomGuideActivity.this.f11214a.setBackground(SatcomGuideActivity.this.h.getResources().getDrawable(R.drawable._2131431344_res_0x7f0b0fb0));
                        SatcomGuideActivity.this.f11214a.setPadding(180, 36, 180, 36);
                        SatcomGuideActivity.this.f11214a.setTextColor(SatcomGuideActivity.this.h.getResources().getColor(R.color._2131297064_res_0x7f090328));
                        break;
                    case 1:
                        SatcomGuideActivity.this.q();
                        break;
                    case 2:
                        Toast.makeText(SatcomGuideActivity.this.h, SatcomGuideActivity.this.getString(R.string._2130850602_res_0x7f02332a), 0).show();
                        break;
                    case 3:
                        SatcomGuideActivity.this.n.setText(R.string._2130851504_res_0x7f0236b0);
                        SatcomGuideActivity.this.n.setVisibility(0);
                        SatcomGuideActivity.this.n.setOnClickListener(new View.OnClickListener() { // from class: ths
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                SatcomGuideActivity.e.this.eZI_(view);
                            }
                        });
                        break;
                    case 4:
                        SatcomGuideActivity satcomGuideActivity = SatcomGuideActivity.this;
                        satcomGuideActivity.d(satcomGuideActivity.getString(R.string._2130851597_res_0x7f02370d));
                        break;
                    case 5:
                        SatcomGuideActivity.this.n.setVisibility(0);
                        break;
                    case 6:
                        SatcomGuideActivity.this.a();
                        if (message.obj == null) {
                            stq.b("SatcomGuideActivity", "message{QUERY_REVIEW_RESULT_FINISH} content is null");
                            SatcomGuideActivity.this.a("1");
                            break;
                        } else {
                            SatcomGuideActivity.this.a((String) message.obj);
                            break;
                        }
                    default:
                        stq.b("SatcomGuideActivity", "UIHandler msg.what is invalid");
                        break;
                }
                return;
            }
            if (message.getTarget() != null) {
                message.getTarget().removeCallbacksAndMessages(null);
            }
        }

        public /* synthetic */ void eZI_(View view) {
            SatcomGuideActivity.this.l();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        stq.b("SatcomGuideActivity", "onCreate()");
        eZD_(this);
        setContentView(R.layout.activity_satcom_guide);
        j();
        i();
        WearActivityManager.eLS_(this);
    }

    private void i() {
        this.h = ssz.e();
        this.c = OverSeaMangerUtil.c(ssz.e()).e().b();
        this.e = OverSeaMangerUtil.c(ssz.e()).e().i();
        SafeIntent safeIntent = new SafeIntent(getIntent());
        this.g = safeIntent.getBooleanExtra("isFromWear", false);
        this.l = safeIntent.getStringExtra("wear_home_class_name");
        this.b = safeIntent.getStringExtra("device_id");
        if (1 == o()) {
            m();
        } else {
            this.n.setVisibility(0);
        }
    }

    private void j() {
        this.n = (HwButton) findViewById(R.id.btn_open_start);
        this.f11214a = (TextView) findViewById(R.id.bottom_text);
        this.n.setOnClickListener(this);
        this.f11214a.setOnClickListener(this);
        this.f = (RelativeLayout) findViewById(R.id.rl_notice);
        this.i = (TextView) findViewById(R.id.tv_notice_content);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        stq.b("SatcomGuideActivity", "showApplyForParticipationSuccessDialog");
        HwDialogInterface d = WidgetBuilder.d(this);
        Resources resources = getResources();
        int i = this.d;
        d.setMessage(resources.getQuantityString(R.plurals._2130903538_res_0x7f0301f2, i, Integer.valueOf(i)));
        d.setCanceledOnTouchOutside(false);
        d.setPositiveButton(R.string._2130851410_res_0x7f023652, new DialogInterface.OnClickListener() { // from class: thp
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SatcomGuideActivity.this.eZH_(dialogInterface, i2);
            }
        });
        d.show();
    }

    public /* synthetic */ void eZH_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.j.sendEmptyMessage(0);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private int o() {
        String g = g();
        if (swe.b((CharSequence) g, true) || !g.contains("|")) {
            return 0;
        }
        String[] split = g.split("\\|");
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            int parseInt3 = Integer.parseInt(swb.e(new Date(System.currentTimeMillis()), "yyyyMMdd"));
            return (parseInt3 < parseInt || parseInt3 > parseInt2) ? 0 : 1;
        } catch (NumberFormatException e2) {
            stq.e("SatcomGuideActivity", "parse int failed", false);
            throw e2;
        }
    }

    private String g() {
        return suj.b(getApplicationContext()).e("Mass_Test_Period", "");
    }

    private void m() {
        stq.b("SatcomGuideActivity", "queryParticipationStatus");
        svt.e().a(new Runnable() { // from class: thl
            @Override // java.lang.Runnable
            public final void run() {
                SatcomGuideActivity.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        String d = svw.d(this.h, "/WiseCloudWalletPassService/api/v1/client/getParticipationStatus", "WALLETPASSCOMMON");
        svc svcVar = new svc();
        svcVar.b(this.c);
        svcVar.c(this.e);
        svcVar.d("UDID");
        svd processTask = new svf(this.h, d).processTask(svcVar);
        if (processTask != null && processTask.g == 0) {
            stq.b("SatcomGuideActivity", "queryParticipationStatus response success");
            String e2 = processTask.e();
            stq.b("SatcomGuideActivity", "queryParticipationStatus status : " + e2);
            if (swe.b((CharSequence) e2, true)) {
                return;
            }
            this.d = suj.b(this.h).d("Beidou_Audit_Wait_Day", 1);
            if ("0".equals(e2)) {
                this.j.sendEmptyMessage(3);
                return;
            } else if ("1".equals(e2)) {
                this.j.sendEmptyMessage(0);
                return;
            } else {
                this.j.sendEmptyMessage(5);
                return;
            }
        }
        stq.b("SatcomGuideActivity", "queryParticipationStatus response fail errorCode : " + processTask.g + " , errorMessage : " + processTask.a());
        this.j.sendEmptyMessage(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        stq.b("SatcomGuideActivity", "showApplyForParticipationDialog");
        HwDialogInterface d = WidgetBuilder.d(this);
        d.setCancelable(false);
        d.setMessage(R.string._2130851483_res_0x7f02369b);
        d.setNegativeButton(R.string._2130851408_res_0x7f023650, new DialogInterface.OnClickListener() { // from class: thm
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SatcomGuideActivity.eZF_(dialogInterface, i);
            }
        });
        d.setPositiveButton(R.string._2130851635_res_0x7f023733, new DialogInterface.OnClickListener() { // from class: thj
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SatcomGuideActivity.this.eZG_(dialogInterface, i);
            }
        });
        d.show();
    }

    public static /* synthetic */ void eZF_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void eZG_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        b();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void b() {
        stq.b("SatcomGuideActivity", "applyForParticipation");
        svt.e().a(new Runnable() { // from class: thn
            @Override // java.lang.Runnable
            public final void run() {
                SatcomGuideActivity.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        String d = svw.d(this.h, "/WiseCloudWalletPassService/api/v1/client/applyForParticipation", "WALLETPASSCOMMON");
        suw suwVar = new suw();
        suwVar.d(this.c);
        suwVar.c(this.e);
        suwVar.b("UDID");
        suy processTask = new sva(ssz.e(), d).processTask(suwVar);
        if (processTask != null && processTask.g == 0) {
            stq.b("SatcomGuideActivity", "applyForParticipation response success");
            d();
        } else {
            stq.b("SatcomGuideActivity", "applyForParticipation response fail returnCode : " + processTask.g);
            this.j.sendEmptyMessage(2);
        }
    }

    private void d() {
        if (suj.b(this.h).d("BeiDou_Audit_Cycle", 0) == 0) {
            this.j.sendEmptyMessage(4);
            new svq(new QueryReviewResultCallback() { // from class: com.huawei.wear.wallet.ui.SatcomGuideActivity.5
                @Override // com.huawei.wear.oversea.overseamanger.QueryReviewResultCallback
                public boolean checkReviewResult() {
                    return SatcomGuideActivity.this.n();
                }

                @Override // com.huawei.wear.oversea.overseamanger.QueryReviewResultCallback
                public void onQueryReviewResultCallback(String str) {
                    stq.b("SatcomGuideActivity", "onQueryReviewResultCallback resultCode = " + str);
                    Message message = new Message();
                    message.what = 6;
                    message.obj = str;
                    SatcomGuideActivity.this.j.sendMessage(message);
                }
            });
        } else {
            this.j.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        String d = svw.d(this.h, "/WiseCloudWalletPassService/api/v1/client/getParticipationStatus", "WALLETPASSCOMMON");
        svc svcVar = new svc();
        svcVar.b(this.c);
        svcVar.c(this.e);
        svcVar.d("UDID");
        svd processTask = new svf(this.h, d).processTask(svcVar);
        if (processTask != null && processTask.g == 0) {
            stq.b("SatcomGuideActivity", "queryParticipationStatus response success");
            String e2 = processTask.e();
            stq.b("SatcomGuideActivity", "queryParticipationStatus status : " + e2);
            if (!swe.b((CharSequence) e2, true) && "2".equals(e2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if ("2".equals(str)) {
            d(getString(R.string._2130851468_res_0x7f02368c));
            h();
            return;
        }
        HwDialogInterface d = WidgetBuilder.d(this);
        d.setMessage(getString(R.string._2130851411_res_0x7f023653));
        d.setCanceledOnTouchOutside(false);
        d.setPositiveButton(R.string._2130851410_res_0x7f023652, new DialogInterface.OnClickListener() { // from class: tho
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SatcomGuideActivity.eZE_(dialogInterface, i);
            }
        });
        d.show();
    }

    public static /* synthetic */ void eZE_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private boolean k() {
        HwProgressDialogInterface hwProgressDialogInterface = this.m;
        return hwProgressDialogInterface != null && hwProgressDialogInterface.isShowing();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        HwProgressDialogInterface hwProgressDialogInterface;
        if (isFinishing() || (hwProgressDialogInterface = this.m) == null) {
            return;
        }
        hwProgressDialogInterface.dismiss();
        this.m = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        stq.b("SatcomGuideActivity", FaqConstants.FAQ_SHOW_PROGRESS);
        if (isFinishing()) {
            return;
        }
        if (this.m != null && k()) {
            this.m.setMessage(str);
            this.m.setCanceledOnTouchOutside(false);
            this.m.setCancelable(false);
        } else {
            HwProgressDialogInterface c = WidgetBuilder.c(this);
            this.m = c;
            c.setMessage(str);
            this.m.setCanceledOnTouchOutside(false);
            this.m.setCancelable(false);
            this.m.show();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (tel.e(id)) {
            stq.b("SatcomGuideActivity", "DoubleClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (id == R.id.btn_open_start) {
            h();
        } else if (id == R.id.bottom_text) {
            f();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        if (ssz.d() == null) {
            stq.e("SatcomGuideActivity", "HealthPackageUtil.getAdapter() is null and return.");
            return;
        }
        try {
            SafeIntent safeIntent = new SafeIntent(new Intent());
            safeIntent.setClassName(this, ComponentInfo.PluginPay_A_69);
            safeIntent.putExtra("isFromWear", this.g);
            safeIntent.putExtra("device_id", this.b);
            safeIntent.putExtra("wear_home_class_name", this.l);
            ssz.d().launchActivity(this, safeIntent);
        } catch (ActivityNotFoundException unused) {
            stq.e("SatcomGuideActivity", "gotoSatcomCardActivity() : occur ActivityNotFoundException");
        }
    }

    private void f() {
        stq.e("SatcomGuideActivity", "targetClass = :" + this.l);
        SafeIntent safeIntent = new SafeIntent(new Intent());
        safeIntent.setClassName(this, this.l);
        safeIntent.putExtra("isFromWear", this.g);
        safeIntent.putExtra("device_id", this.b);
        safeIntent.putExtra("wear_home_class_name", this.l);
        startActivity(safeIntent);
        tei.eYX_(this.h, safeIntent);
        finish();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private static void eZD_(Activity activity) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        window.clearFlags(AppRouterExtras.COLDSTART);
        window.addFlags(Integer.MIN_VALUE);
        if (swa.c(activity)) {
            decorView.setSystemUiVisibility(256);
            window.setStatusBarColor(0);
        } else {
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
            window.setStatusBarColor(ViewCompat.MEASURED_SIZE_MASK);
        }
        activity.getWindow().setNavigationBarColor(activity.getResources().getColor(R.color._2131297358_res_0x7f09044e));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.j.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
