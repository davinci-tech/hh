package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateResultActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.PressureCalibrateQuestionActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import defpackage.jpt;
import defpackage.mcf;
import defpackage.nsy;
import defpackage.psm;
import defpackage.ptd;
import defpackage.ptt;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes6.dex */
public class PressureCalibrateQuestionActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9873a;
    private HealthViewPager c;
    private CustomTitleBar d;
    private Context e;
    private Intent f;
    private boolean h;
    private int i;
    private boolean j;
    private LinearLayout k;
    private MessageReceiver n;
    private ptd o;
    private String b = "unknown";
    private String l = "";
    private String m = "";
    private Handler g = new Handler(Looper.getMainLooper());

    public static /* synthetic */ boolean dtm_(View view, MotionEvent motionEvent) {
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_pressure_calibrate_question);
        this.e = this;
        ptt.d().s();
        Intent intent = getIntent();
        this.f = intent;
        if (intent != null) {
            this.j = intent.getBooleanExtra("pressure_is_have_datas", false);
            this.b = this.f.getStringExtra("health_device_type");
            this.l = this.f.getStringExtra("health_wifi_device_userId");
            this.m = this.f.getStringExtra("health_wifi_device_productId");
            this.f9873a = this.f.getBooleanExtra("from_health_record", false);
            this.i = this.f.getIntExtra(ArkUIXConstants.FROM_TYPE, 0);
            ptt.d().b(this.b);
            ptt.d().c(this.l);
            ptt.d().d(this.m);
            ptt.d().a(this.f9873a);
            ptt.d().a(this.i);
            if (!"wifi_device".equals(this.b)) {
                ptt.d().c(this.j);
                this.h = this.f.getBooleanExtra("press_auto_monitor", false);
                ptt.d().d(this.h);
            }
        }
        if (e()) {
            return;
        }
        i();
        a();
        b();
    }

    private boolean e() {
        DeviceInfo a2 = jpt.a("PressureCalibrateQuestionActivity");
        if ("wifi_device".equals(this.b)) {
            return false;
        }
        if (a2 != null && a2.getDeviceConnectState() == 2) {
            return false;
        }
        ReleaseLogUtil.e("PressureCalibrateQuestionActivity", "Device is null or disconnected");
        Intent intent = new Intent(this, (Class<?>) PressureCalibrateResultActivity.class);
        intent.putExtra("stopTimer", true);
        intent.putExtra(ArkUIXConstants.FROM_TYPE, this.i);
        mcf.cfJ_(this, intent);
        finish();
        return true;
    }

    private void b() {
        this.n = new MessageReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.ui.pressure.question.next");
        intentFilter.addAction("com.huawei.ui.pressure.question.previous");
        intentFilter.addAction("com.huawei.ui.pressure.calibrate.err");
        BroadcastManagerUtil.bFA_(this, this.n, intentFilter, LocalBroadcast.c, null);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        a();
    }

    private void a() {
        this.k = (LinearLayout) findViewById(R.id.hw_pressure_calibrate_viewpager_layout);
        this.c = (HealthViewPager) findViewById(R.id.hw_pressure_calibrate_question_viewpager);
        cancelLayoutById(this.k);
        ReleaseLogUtil.e("R_PressureCalibrateQuestionActivity", "mDeviceType is ", this.b);
        if (!"wifi_device".equals(this.b)) {
            psm.e().b(1, new b());
            psm.e().c(4, 60, 2);
        } else {
            c();
        }
        ptt.d().e(System.currentTimeMillis());
        this.c.setCurrentItem(0);
        ArrayList arrayList = new ArrayList(12);
        for (int i = 0; i < 12; i++) {
            arrayList.add(QuestionItemFragment.e(i));
        }
        ptd ptdVar = new ptd(getSupportFragmentManager(), arrayList);
        this.o = ptdVar;
        this.c.setAdapter(ptdVar);
        this.c.setIsScroll(false);
        this.c.setOnTouchListener(new View.OnTouchListener() { // from class: pth
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return PressureCalibrateQuestionActivity.dtm_(view, motionEvent);
            }
        });
        psm.e().b((PressureCalibrateQuestionActivity) new WeakReference(this).get());
    }

    static class b implements IBaseResponseCallback {
        private final WeakReference<PressureCalibrateQuestionActivity> d;

        private b(PressureCalibrateQuestionActivity pressureCalibrateQuestionActivity) {
            this.d = new WeakReference<>(pressureCalibrateQuestionActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            PressureCalibrateQuestionActivity pressureCalibrateQuestionActivity = this.d.get();
            if (pressureCalibrateQuestionActivity != null) {
                pressureCalibrateQuestionActivity.c(i);
            } else {
                LogUtil.b("PressureCalibrateQuestionActivity", "mReference is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        ReleaseLogUtil.e("R_PressureCalibrateQuestionActivity", "errCode: ", Integer.valueOf(i));
        if (i == 126007) {
            j();
        } else {
            this.g.postDelayed(new Runnable() { // from class: ptg
                @Override // java.lang.Runnable
                public final void run() {
                    PressureCalibrateQuestionActivity.this.c();
                }
            }, 300L);
        }
    }

    private void i() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.hw_pressure_calibrate_title_layout);
        this.d = customTitleBar;
        customTitleBar.setLeftButtonClickable(true);
        this.d.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: ptn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PressureCalibrateQuestionActivity.this.dtp_(view);
            }
        });
    }

    public /* synthetic */ void dtp_(View view) {
        if (!ptt.d().j()) {
            h();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        int currentItem = this.c.getCurrentItem();
        LogUtil.a("PressureCalibrateQuestionActivity", "customViewPager position = " + this.c);
        this.c.setCurrentItem(currentItem + 1, false);
        ptt.d().h(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        int currentItem = this.c.getCurrentItem();
        LogUtil.a("PressureCalibrateQuestionActivity", "customViewPager position = " + this.c);
        this.c.setCurrentItem(currentItem + (-1), false);
        ptt.d().h(false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        MessageReceiver messageReceiver = this.n;
        if (messageReceiver != null) {
            unregisterReceiver(messageReceiver);
        }
        if (!ptt.d().j() && !ptt.d().l()) {
            psm.e().v();
            psm.e().w();
        }
        finish();
        super.onDestroy();
        LogUtil.a("PressureCalibrateQuestionActivity", "pressure calibrate question onDestroy");
    }

    private void h() {
        String string = this.e.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_dialog_notify);
        String string2 = this.e.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_dialog_text);
        String string3 = this.e.getResources().getString(R$string.IDS_hw_common_ui_dialog_cancel);
        String string4 = this.e.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_dialog_button_stop);
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.e);
        builder.b(string).e(string2).cyV_(string4, new View.OnClickListener() { // from class: ptj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PressureCalibrateQuestionActivity.this.dto_(view);
            }
        }).cyS_(string3, new View.OnClickListener() { // from class: ptk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PressureCalibrateQuestionActivity.dtn_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void dto_(View view) {
        d(KnitPressureActivity.class);
        LogUtil.a("PressureCalibrateQuestionActivity", "Question have data = " + this.j);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dtn_(View view) {
        LogUtil.a("PressureCalibrateQuestionActivity", "calibrate button click cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(Class cls) {
        if ("wifi_device".equals(this.b)) {
            psm.e().w();
            ptt.d().u();
            finish();
            return;
        }
        psm.e().v();
        if (this.h || this.f9873a || this.i == 1) {
            psm.e().w();
            ptt.d().u();
            finish();
            return;
        }
        Intent intent = new Intent(this.e, (Class<?>) cls);
        this.f = intent;
        intent.putExtra("pressure_is_have_datas", this.j);
        startActivity(this.f);
        psm.e().w();
        ptt.d().u();
        finish();
    }

    private void j() {
        Intent intent = new Intent(this, (Class<?>) PressureCalibrateResultActivity.class);
        intent.putExtra("stopTimer", true);
        intent.putExtra("MovementMode", true);
        intent.putExtra(ArkUIXConstants.FROM_TYPE, this.i);
        mcf.cfJ_(this, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        nsy.cMA_((RelativeLayout) findViewById(R.id.pressure_loading_layout), 8);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (ptt.d().j()) {
                return false;
            }
            h();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public static class MessageReceiver extends BroadcastReceiver {
        private WeakReference<PressureCalibrateQuestionActivity> c;

        MessageReceiver(PressureCalibrateQuestionActivity pressureCalibrateQuestionActivity) {
            this.c = new WeakReference<>(pressureCalibrateQuestionActivity);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            final PressureCalibrateQuestionActivity pressureCalibrateQuestionActivity = this.c.get();
            if (pressureCalibrateQuestionActivity == null || intent == null || context == null) {
                LogUtil.h("PressureCalibrateQuestionActivity", "onReceive activity or intent or content is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("PressureCalibrateQuestionActivity", "onReceive action:", action);
            if ("com.huawei.ui.pressure.question.next".equals(action)) {
                Handler handler = pressureCalibrateQuestionActivity.g;
                Objects.requireNonNull(pressureCalibrateQuestionActivity);
                handler.postDelayed(new Runnable() { // from class: pto
                    @Override // java.lang.Runnable
                    public final void run() {
                        PressureCalibrateQuestionActivity.this.d();
                    }
                }, 300L);
                return;
            }
            if ("com.huawei.ui.pressure.question.previous".equals(action)) {
                Handler handler2 = pressureCalibrateQuestionActivity.g;
                Objects.requireNonNull(pressureCalibrateQuestionActivity);
                handler2.post(new Runnable() { // from class: ptl
                    @Override // java.lang.Runnable
                    public final void run() {
                        PressureCalibrateQuestionActivity.this.g();
                    }
                });
            } else {
                if ("com.huawei.ui.pressure.calibrate.err".equals(action)) {
                    Intent intent2 = new Intent(pressureCalibrateQuestionActivity, (Class<?>) PressureCalibrateResultActivity.class);
                    intent2.putExtra("stopTimer", true);
                    intent2.putExtra("mIsFromNoData", pressureCalibrateQuestionActivity.j);
                    intent2.putExtra("press_auto_monitor", pressureCalibrateQuestionActivity.h);
                    intent2.putExtra("from_health_record", pressureCalibrateQuestionActivity.f9873a);
                    intent2.putExtra("no_privacy", intent.getBooleanExtra("no_privacy", false));
                    intent2.putExtra(ArkUIXConstants.FROM_TYPE, pressureCalibrateQuestionActivity.i);
                    pressureCalibrateQuestionActivity.startActivity(intent2);
                    pressureCalibrateQuestionActivity.finish();
                    return;
                }
                LogUtil.h("PressureCalibrateQuestionActivity", "onReceive other action");
            }
        }
    }
}
