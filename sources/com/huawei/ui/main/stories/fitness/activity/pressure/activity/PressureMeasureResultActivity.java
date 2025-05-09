package com.huawei.ui.main.stories.fitness.activity.pressure.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressure.activity.PressureMeasureResultActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view.NoTimeClockView;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view.TimeClockView;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import defpackage.gge;
import defpackage.gnm;
import defpackage.nsn;
import defpackage.psm;
import defpackage.ptt;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class PressureMeasureResultActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private static a f9865a;
    private TimeClockView ab;
    private FrameLayout b;
    private MessageReceiver c;
    private FrameLayout d;
    private HealthTextView f;
    private HealthTextView g;
    private HealthButton h;
    private Context i;
    private HealthTextView j;
    private LinearLayout k;
    private int n;
    private Intent o;
    private FrameLayout q;
    private boolean r;
    private HealthTextView s;
    private NoTimeClockView t;
    private HealthTextView u;
    private HealthTextView v;
    private LinearLayout w;
    private RelativeLayout x;
    private FrameLayout y;
    private boolean l = false;
    private boolean p = false;
    private boolean e = false;
    private boolean m = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pressure_measure_result);
        this.i = this;
        ptt.d().e(true);
        this.c = new MessageReceiver();
        f9865a = new a(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.ui.pressure.measure");
        intentFilter.addAction("com.huawei.ui.pressure.measure.suddenness");
        intentFilter.addAction("com.huawei.ui.pressure.measure.err");
        BroadcastManagerUtil.bFA_(this.i, this.c, intentFilter, LocalBroadcast.c, null);
        Intent intent = getIntent();
        this.o = intent;
        if (intent != null) {
            this.r = intent.getBooleanExtra("pressure_is_have_datas", false);
            this.e = this.o.getBooleanExtra("isOpenMeasure", false);
            this.n = this.o.getIntExtra(ArkUIXConstants.FROM_TYPE, 0);
        }
        i();
        this.x = (RelativeLayout) findViewById(R.id.hw_pressure_measure_time_clock_linerlayout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.hw_pressure_measure_time_clock_framelayout);
        this.b = frameLayout;
        frameLayout.setVisibility(0);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.hw_pressure_measure_time_clock_frame);
        this.d = frameLayout2;
        frameLayout2.setVisibility(0);
        FrameLayout frameLayout3 = (FrameLayout) findViewById(R.id.hw_pressure_measure_no_time_clock_frame);
        this.q = frameLayout3;
        frameLayout3.setVisibility(8);
        HealthButton healthButton = (HealthButton) findViewById(R.id.hw_pressure_measure_result_complete_btn);
        this.h = healthButton;
        healthButton.setVisibility(8);
        this.y = (FrameLayout) findViewById(R.id.hw_pressure_measure_result_show);
        this.w = (LinearLayout) findViewById(R.id.hw_pressure_measure_result_linear);
        this.j = (HealthTextView) findViewById(R.id.hw_pressure_measure_result_tv);
        this.g = (HealthTextView) findViewById(R.id.hw_pressure_measure_result_number);
        this.k = (LinearLayout) findViewById(R.id.hw_pressure_measure_result_fail_linear);
        this.f = (HealthTextView) findViewById(R.id.hw_pressure_measure_result_fail_tv);
        this.s = (HealthTextView) findViewById(R.id.hw_pressure_measure_result_notify);
        this.u = (HealthTextView) findViewById(R.id.hw_pressure_measure_result_knowledge);
        this.v = (HealthTextView) findViewById(R.id.hw_pressure_measure_tip_knowledge);
        TimeClockView timeClockView = (TimeClockView) findViewById(R.id.hw_pressure_measure_time_clock_view);
        this.ab = timeClockView;
        timeClockView.d(60, 60, this.s);
        this.s.setVisibility(0);
        this.u.setVisibility(8);
        this.v.setVisibility(8);
        ((HealthTextView) findViewById(R.id.hw_pressure_measure_technic_tv)).setVisibility(0);
        c();
        initViewTahiti();
    }

    private void c() {
        boolean b = psm.e().b();
        LogUtil.a("PressureMeasureResultActivity", "isHaveConnected = ", Boolean.valueOf(b));
        if (b && this.e) {
            psm.e().b(0, new c());
            psm.e().c(4, 60, 0);
            a();
            return;
        }
        g();
    }

    static class c implements IBaseResponseCallback {
        private final WeakReference<PressureMeasureResultActivity> e;

        private c(PressureMeasureResultActivity pressureMeasureResultActivity) {
            this.e = new WeakReference<>(pressureMeasureResultActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (this.e.get() == null) {
                LogUtil.b("PressureMeasureResultActivity", "mReference is null");
            } else if (i == 126007) {
                PressureMeasureResultActivity.f9865a.sendEmptyMessage(126007);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        psm.e().z();
        h();
        HealthTextView healthTextView = this.s;
        if (healthTextView != null) {
            healthTextView.setText(this.i.getResources().getString(R$string.IDS_hw_measure_show_pressure_in_move_mode));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
    }

    private void i() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.hw_pressure_measure_result_title_layout);
        customTitleBar.setLeftButtonClickable(true);
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: psh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PressureMeasureResultActivity.this.dsS_(view);
            }
        });
    }

    public /* synthetic */ void dsS_(View view) {
        if (!this.l) {
            f();
        } else {
            b();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g() {
        this.l = true;
        this.t = new NoTimeClockView(this.i);
        this.d.setVisibility(8);
        this.q.setVisibility(0);
        this.q.addView(this.t);
        this.y.setVisibility(0);
        this.w.setVisibility(8);
        this.k.setVisibility(0);
        this.f.setVisibility(0);
        this.f.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_fail));
        this.s.setTextSize(15.0f);
        this.s.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_notify_stop));
    }

    private void a() {
        this.y.setVisibility(8);
        this.w.setVisibility(8);
        this.k.setVisibility(8);
        this.h.setVisibility(8);
        this.u.setVisibility(0);
        this.v.setVisibility(8);
        this.u.setGravity(1);
        this.u.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_notify_stop));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (!psm.e().i()) {
            psm.e().c(0, false);
        }
        psm.e().t();
        float[] b = psm.e().b(0);
        LogUtil.a("PressureMeasureResultActivity", "backSo is ", Arrays.toString(b));
        this.t = new NoTimeClockView(this.i);
        this.d.setVisibility(8);
        TimeClockView timeClockView = this.ab;
        if (timeClockView != null) {
            timeClockView.b();
            this.ab.setVisibility(8);
        }
        this.q.setVisibility(0);
        this.q.addView(this.t);
        this.y.setVisibility(0);
        this.l = true;
        boolean h = psm.e().h(b);
        LogUtil.a("PressureMeasureResultActivity", "remove clock isMeasureSuccessed = ", Boolean.valueOf(h));
        if (h) {
            b(b);
            c(b);
            return;
        }
        HashMap hashMap = new HashMap(4);
        hashMap.put("MeasureSuccessed", 0);
        gge.e(AnalyticsValue.HEALTH_PRESSUER_MEASUREMENT_SUCCESS_2160010.value(), hashMap);
        this.w.setVisibility(8);
        this.k.setVisibility(0);
        this.f.setVisibility(0);
        this.f.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_fail));
        this.s.setTextSize(15.0f);
        this.s.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_notify_stop));
        this.u.setVisibility(8);
        this.v.setVisibility(8);
    }

    private void b(float[] fArr) {
        TimeClockView timeClockView = this.ab;
        if (timeClockView != null) {
            timeClockView.b();
            this.ab.setVisibility(8);
        }
        HashMap hashMap = new HashMap(4);
        hashMap.put("MeasureSuccessed", 1);
        gge.e(AnalyticsValue.HEALTH_PRESSUER_MEASUREMENT_SUCCESS_2160010.value(), hashMap);
        this.p = true;
        this.w.setVisibility(0);
        this.j.setVisibility(0);
        this.j.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_current_pressure));
        this.g.setVisibility(0);
        this.g.setText(String.valueOf(psm.e().d(fArr)));
        int a2 = psm.e().a(fArr);
        String format = String.format(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_grade), psm.e().d(a2));
        this.u.setGravity(GravityCompat.START);
        this.s.setText(format);
        this.u.setVisibility(0);
        this.v.setVisibility(0);
        if (nsn.ag(this.i)) {
            this.u.setGravity(17);
        }
        if (a2 == 1) {
            this.u.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_1));
        } else if (a2 == 2) {
            this.u.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_2));
        } else if (a2 == 3) {
            this.u.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_3));
        } else if (a2 == 4) {
            this.u.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_4));
        } else {
            LogUtil.a("PressureMeasureResultActivity", " controlMeasureSucess gradeFlag err !!!");
        }
        this.h.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("MeasureSuccessed", 0);
        gge.e(AnalyticsValue.HEALTH_PRESSUER_MEASUREMENT_SUCCESS_2160010.value(), hashMap);
        this.l = true;
        TimeClockView timeClockView = this.ab;
        if (timeClockView != null) {
            timeClockView.b();
            this.ab.setVisibility(8);
        }
        this.d.setVisibility(8);
        this.w.setVisibility(8);
        this.t = new NoTimeClockView(this.i);
        this.q.setVisibility(0);
        this.q.addView(this.t);
        this.y.setVisibility(0);
        this.k.setVisibility(0);
        this.f.setVisibility(0);
        this.f.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_fail));
        this.s.setTextSize(15.0f);
        if (z) {
            this.s.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_no_privacy));
        } else {
            this.s.setText(this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_notify_stop));
        }
        this.u.setVisibility(8);
        this.v.setVisibility(8);
    }

    private void c(float[] fArr) {
        this.l = true;
        LogUtil.a("PressureMeasureResultActivity", " setDataToDatas start !!!");
        psm.e().a(0, this.i, fArr);
        LogUtil.a("PressureMeasureResultActivity", " setDataToDatas end !!!");
    }

    public static class MessageReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (psm.e().k()) {
                return;
            }
            psm.e().f(true);
            if (context == null || intent == null) {
                return;
            }
            String action = intent.getAction();
            if ("com.huawei.ui.pressure.measure".equals(action)) {
                LogUtil.a("PressureMeasureResultActivity", "measure MessageReceiver is end !!!");
                PressureMeasureResultActivity.f9865a.sendEmptyMessage(100);
                return;
            }
            if ("com.huawei.ui.pressure.measure.suddenness".equals(action)) {
                LogUtil.a("PressureMeasureResultActivity", "MEASURE MessageReceiver is suddenness stop !!!");
                PressureMeasureResultActivity.f9865a.sendEmptyMessage(200);
                psm.e().z();
                psm.e().e(4);
                return;
            }
            if ("com.huawei.ui.pressure.measure.err".equals(action)) {
                psm.e().b(intent.getBooleanExtra("isFromNoData", false));
                Message obtain = Message.obtain();
                obtain.what = 1000;
                obtain.obj = Boolean.valueOf(intent.getBooleanExtra("no_privacy", false));
                PressureMeasureResultActivity.f9865a.sendMessage(obtain);
                LogUtil.a("PressureMeasureResultActivity", "MEASURE MessageReceiver is err !!!");
                return;
            }
            LogUtil.a("PressureMeasureResultActivity", "MessageReceiver onReceive action err !!!");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Intent intent = getIntent();
        if (intent != null) {
            intent.putExtra("isOpenMeasure", false);
        }
        TimeClockView timeClockView = this.ab;
        if (timeClockView != null) {
            timeClockView.b();
            this.ab = null;
        }
        if (!this.l && !this.m) {
            psm.e().v();
            LogUtil.a("PressureMeasureResultActivity", "onDestroycalibrateResultStopAllTimer");
        }
        a aVar = f9865a;
        if (aVar != null) {
            aVar.removeMessages(1000);
            f9865a.removeMessages(200);
            f9865a.removeMessages(100);
        }
        unregisterReceiver(this.c);
        this.l = false;
        this.m = false;
        psm.e().w();
        finish();
        super.onDestroy();
    }

    private void f() {
        String string = this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_dialog_notify);
        String string2 = this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_dialog_text);
        String string3 = this.i.getResources().getString(R$string.IDS_hw_common_ui_dialog_cancel);
        String string4 = this.i.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_dialog_button_stop);
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.i);
        builder.b(string).e(string2).cyV_(string4, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.pressure.activity.PressureMeasureResultActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PressureMeasureResultActivity.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(string3, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.pressure.activity.PressureMeasureResultActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("PressureMeasureResultActivity", "button click cancel");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    private void d(Class cls) {
        if (!this.l) {
            psm.e().v();
            this.m = true;
            LogUtil.a("PressureMeasureResultActivity", "jumpTOActivitycalibrateResultStopAllTimer");
        }
        Intent intent = new Intent(this.i, (Class<?>) cls);
        this.o = intent;
        intent.putExtra("pressure_is_have_datas", this.r);
        gnm.aPB_(this.i, this.o);
        psm.e().w();
        finish();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (!this.l) {
                f();
                return false;
            }
            b();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.n == 1) {
            finish();
            return;
        }
        if (this.r) {
            d(KnitPressureActivity.class);
        } else if (psm.e().g()) {
            this.r = true;
            d(KnitPressureActivity.class);
        } else {
            d(KnitPressureActivity.class);
        }
    }

    static class a extends BaseHandler<PressureMeasureResultActivity> {
        a(PressureMeasureResultActivity pressureMeasureResultActivity) {
            super(pressureMeasureResultActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dsT_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PressureMeasureResultActivity pressureMeasureResultActivity, Message message) {
            if (message == null) {
                LogUtil.b("PressureMeasureResultActivity", "handleMessageWhenReferenceNotNull msg is null");
                return;
            }
            LogUtil.a("PressureMeasureResultActivity", "msg.what is ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 100) {
                pressureMeasureResultActivity.h();
                return;
            }
            if (i == 200) {
                pressureMeasureResultActivity.h();
                return;
            }
            if (i != 1000) {
                if (i != 126007) {
                    return;
                }
                pressureMeasureResultActivity.d();
            } else if (message.obj instanceof Boolean) {
                pressureMeasureResultActivity.c(((Boolean) message.obj).booleanValue());
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
