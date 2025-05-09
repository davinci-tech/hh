package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity;

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
import androidx.core.view.GravityCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateResultActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.PressureCalibrateQuestionActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view.NoTimeClockView;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view.TimeClockView;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import defpackage.gge;
import defpackage.nsn;
import defpackage.psm;
import defpackage.ptt;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class PressureCalibrateResultActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private FrameLayout f9867a;
    private TimeClockView aa;
    private int ab;
    private FrameLayout ac;
    private LinearLayout ad;
    private LinearLayout b;
    private MessageReceiver c;
    private FrameLayout d;
    private HealthButton e;
    private HealthTextView f;
    private Context g;
    private HealthTextView h;
    private LinearLayout i;
    private HealthTextView j;
    private boolean k;
    private boolean l;
    private boolean m;
    private int n;
    private a o;
    private boolean p;
    private boolean r;
    private boolean s;
    private HealthTextView v;
    private NoTimeClockView w;
    private HealthTextView x;
    private FrameLayout y;
    private HealthTextView z;
    private boolean q = false;
    private boolean t = false;
    private boolean u = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pressure_calibrate_result);
        this.g = this;
        this.o = new a(this);
        a();
        e();
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getBooleanExtra("pressure_is_have_datas", false);
            this.p = intent.getBooleanExtra("press_auto_monitor", false);
            this.l = intent.getBooleanExtra("from_health_record", false);
            this.r = intent.getBooleanExtra("stopTimer", false);
            this.s = intent.getBooleanExtra("MovementMode", false);
            this.n = intent.getIntExtra(ArkUIXConstants.FROM_TYPE, 0);
            this.m = intent.getBooleanExtra("no_privacy", false);
            LogUtil.a("PressureCalibrateResultActivity", "data mIsAutoMonitorBack: ", Boolean.valueOf(this.p), " mIsStopTimers: ", Boolean.valueOf(this.r), "mIsMovementMode: ", Boolean.valueOf(this.s), " mHasNoPrivacy ", Boolean.valueOf(this.m));
        }
        b();
    }

    private void a() {
        ptt.d().j(true);
        ptt.d().f(true);
        psm.e().g(false);
    }

    private void e() {
        this.c = new MessageReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.ui.pressure.calibrate");
        intentFilter.addAction("com.huawei.ui.pressure.measure.calibrate.stop");
        intentFilter.addAction("com.huawei.ui.pressure.calibrate.err");
        BroadcastManagerUtil.bFA_(this, this.c, intentFilter, LocalBroadcast.c, null);
    }

    private void b() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.hw_pressure_calibrate_result_title_layout);
        customTitleBar.setLeftButtonClickable(true);
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: psp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PressureCalibrateResultActivity.this.dsX_(view);
            }
        });
        this.b = (LinearLayout) findViewById(R.id.hw_pressure_calibrate_result_linearlayout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.hw_pressure_calibrate_time_clock_framelayout);
        this.d = frameLayout;
        frameLayout.setVisibility(0);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.hw_pressure_calibrate_time_clock_frame);
        this.f9867a = frameLayout2;
        frameLayout2.setVisibility(0);
        FrameLayout frameLayout3 = (FrameLayout) findViewById(R.id.hw_pressure_calibrate_no_time_clock_frame);
        this.y = frameLayout3;
        frameLayout3.setVisibility(8);
        HealthButton healthButton = (HealthButton) findViewById(R.id.hw_pressure_calibrate_result_complete_btn);
        this.e = healthButton;
        healthButton.setVisibility(8);
        this.ac = (FrameLayout) findViewById(R.id.hw_pressure_calibrate_result_show);
        this.ad = (LinearLayout) findViewById(R.id.hw_pressure_calibrate_result_linear);
        this.f = (HealthTextView) findViewById(R.id.hw_pressure_calibrate_result_tv);
        this.j = (HealthTextView) findViewById(R.id.hw_pressure_calibrate_result_number);
        this.i = (LinearLayout) findViewById(R.id.hw_pressure_calibrate_result_fail_linear);
        this.h = (HealthTextView) findViewById(R.id.hw_pressure_calibrate_result_fail_tv);
        this.z = (HealthTextView) findViewById(R.id.hw_pressure_calibrate_result_notify);
        this.x = (HealthTextView) findViewById(R.id.hw_pressure_calibrate_result_knowledge);
        this.v = (HealthTextView) findViewById(R.id.hw_pressure_calibrate_tip_knowledge);
        this.aa = (TimeClockView) findViewById(R.id.hw_pressure_calibrate_time_clock_view);
        this.z.setVisibility(0);
        this.x.setVisibility(0);
        this.v.setVisibility(0);
        ((HealthTextView) findViewById(R.id.hw_pressure_calibrate_technic_tv)).setVisibility(0);
        initViewTahiti();
        boolean f = psm.e().f();
        ReleaseLogUtil.e("PressureCalibrateResultActivity", "initView isGameOver: ", Boolean.valueOf(f));
        if (this.r) {
            j();
        } else if (f) {
            i();
        } else {
            c();
        }
    }

    public /* synthetic */ void dsX_(View view) {
        if (!this.t) {
            h();
        } else {
            d();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        boolean ag = nsn.ag(getApplicationContext());
        super.initViewTahiti();
        if (ag) {
            this.x.setGravity(17);
        } else {
            this.x.setGravity(GravityCompat.START);
        }
    }

    private void c() {
        int i;
        boolean b = psm.e().b();
        if (psm.e().l()) {
            i();
            return;
        }
        psm.e().u();
        if (!b) {
            LogUtil.a("PressureCalibrateResultActivity", "No Devices!");
            g();
            return;
        }
        LogUtil.a("PressureCalibrateResultActivity", "Have Devices!");
        int b2 = (int) (ptt.d().b() - ptt.d().t());
        LogUtil.a("PressureCalibrateResultActivity", "time = ", Integer.valueOf(b2));
        if (b2 >= 60000 || ((i = 60000 - b2) > 0 && i < 1000)) {
            i = 1000;
        }
        if (i > 0) {
            int i2 = 60 - (i / 1000);
            this.ab = i2;
            LogUtil.a("PressureCalibrateResultActivity", "startTime= ", Integer.valueOf(i2));
            this.aa.d(this.ab, 60, this.z);
            f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        HashMap hashMap = new HashMap(4);
        hashMap.put("CalibrateSuccessed", 0);
        gge.e(AnalyticsValue.HEALTH_PRESSUER_ADJUST_SUCCESS_2160007.value(), hashMap);
        this.t = true;
        TimeClockView timeClockView = this.aa;
        if (timeClockView != null) {
            timeClockView.b();
            this.aa.setVisibility(8);
            this.f9867a.setVisibility(8);
        }
        if (this.w == null) {
            this.w = new NoTimeClockView(this.g);
        }
        this.ad.setVisibility(8);
        this.w = new NoTimeClockView(this.g);
        this.f9867a.setVisibility(8);
        this.y.setVisibility(0);
        this.y.addView(this.w);
        this.ac.setVisibility(0);
        this.i.setVisibility(0);
        this.h.setVisibility(0);
        this.h.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_answer_fail_text));
        this.z.setTextSize(15.0f);
        if (this.s) {
            this.z.setText(this.g.getResources().getString(R$string.IDS_hw_adjust_show_pressure_in_move_mode));
            k();
        } else if (this.m) {
            this.z.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_no_privacy));
        } else {
            this.z.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_notify_stop));
        }
        this.x.setVisibility(8);
        this.v.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.t = true;
        this.w = new NoTimeClockView(this.g);
        this.f9867a.setVisibility(8);
        TimeClockView timeClockView = this.aa;
        if (timeClockView != null) {
            timeClockView.b();
            this.aa.setVisibility(8);
        }
        this.y.setVisibility(0);
        this.y.addView(this.w);
        this.ac.setVisibility(0);
        this.ad.setVisibility(8);
        this.i.setVisibility(0);
        this.h.setVisibility(0);
        LogUtil.a("PressureCalibrateResultActivity", "pressure calibrate fail");
        this.h.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_answer_fail_text));
        this.z.setTextSize(15.0f);
        this.z.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_notify_stop));
        this.x.setVisibility(8);
        this.v.setVisibility(8);
        this.e.setVisibility(8);
        this.e.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_again));
        this.t = true;
        HashMap hashMap = new HashMap(4);
        hashMap.put("CalibrateSuccessed", 0);
        gge.e(AnalyticsValue.HEALTH_PRESSUER_ADJUST_SUCCESS_2160007.value(), hashMap);
    }

    private void f() {
        int i = this.ab;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        this.x.setText(String.format(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_answer_question_leaving_time_notification), stringBuffer.append(this.g.getResources().getQuantityString(R.plurals._2130903055_res_0x7f03000f, 60, 60)).toString(), stringBuffer2.append(this.g.getResources().getQuantityString(R.plurals._2130903055_res_0x7f03000f, i, Integer.valueOf(i))).toString()).trim());
        this.v.setVisibility(8);
        this.ac.setVisibility(8);
        this.ad.setVisibility(8);
        this.i.setVisibility(8);
        this.e.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.t = true;
        this.w = new NoTimeClockView(this.g);
        this.f9867a.setVisibility(8);
        TimeClockView timeClockView = this.aa;
        if (timeClockView != null) {
            timeClockView.b();
            this.aa.setVisibility(8);
        }
        this.y.setVisibility(0);
        this.y.addView(this.w);
        this.ac.setVisibility(0);
        this.i.setVisibility(8);
        float[] b = psm.e().b(2);
        LogUtil.a("PressureCalibrateResultActivity", "backSo is ", Arrays.toString(b));
        boolean b2 = psm.e().b(b);
        ReleaseLogUtil.e("PressureCalibrateResultActivity", "isCalibrateSuccess: ", Boolean.valueOf(b2));
        if (b2) {
            d(b);
            c(b);
        } else {
            HashMap hashMap = new HashMap(4);
            hashMap.put("CalibrateSuccessed", 0);
            gge.e(AnalyticsValue.HEALTH_PRESSUER_ADJUST_SUCCESS_2160007.value(), hashMap);
            this.ad.setVisibility(8);
            this.i.setVisibility(0);
            this.h.setVisibility(0);
            this.h.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_answer_fail_text));
            this.z.setTextSize(15.0f);
            this.z.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_notify_stop));
            this.x.setVisibility(8);
            this.v.setVisibility(8);
            this.e.setVisibility(8);
            this.e.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_again));
        }
        this.t = true;
    }

    private void d(float[] fArr) {
        TimeClockView timeClockView = this.aa;
        if (timeClockView != null) {
            timeClockView.b();
            this.aa.setVisibility(8);
        }
        HashMap hashMap = new HashMap(4);
        hashMap.put("CalibrateSuccessed", 1);
        gge.e(AnalyticsValue.HEALTH_PRESSUER_ADJUST_SUCCESS_2160007.value(), hashMap);
        this.q = true;
        this.ad.setVisibility(0);
        this.f.setVisibility(0);
        this.f.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_current_pressure));
        this.j.setVisibility(0);
        this.j.setText(UnitUtil.e(psm.e().e(fArr), 1, 0));
        int c = psm.e().c(fArr);
        this.z.setText(String.format(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_grade), psm.e().d(c)));
        this.x.setVisibility(0);
        this.v.setVisibility(0);
        if (c == 1) {
            this.x.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_1));
        } else if (c == 2) {
            this.x.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_2));
        } else if (c == 3) {
            this.x.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_3));
        } else if (c == 4) {
            this.x.setText(this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_4));
        } else {
            LogUtil.a("PressureCalibrateResultActivity", "processPressureCalibrateSuccess is err!!");
        }
        this.e.setVisibility(8);
    }

    private void c(float[] fArr) {
        psm.e().a(2, this.g, fArr);
        this.t = true;
        LogUtil.a("PressureCalibrateResultActivity", " setDataToDatas !!!");
    }

    public static class MessageReceiver extends BroadcastReceiver {
        private WeakReference<PressureCalibrateResultActivity> c;

        MessageReceiver(PressureCalibrateResultActivity pressureCalibrateResultActivity) {
            this.c = new WeakReference<>(pressureCalibrateResultActivity);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            PressureCalibrateResultActivity pressureCalibrateResultActivity = this.c.get();
            if (pressureCalibrateResultActivity == null || intent == null || context == null) {
                LogUtil.h("PressureCalibrateResultActivity", "onReceive activity or intent or content is null");
                return;
            }
            if (psm.e().k()) {
                LogUtil.h("PressureCalibrateResultActivity", "onReceive getIsStopMeasureTimer is true");
                return;
            }
            psm.e().f(true);
            String action = intent.getAction();
            if ("com.huawei.ui.pressure.calibrate".equals(action)) {
                LogUtil.a("PressureCalibrateResultActivity", "calibrate MessageReceiver is end !!!action ==", action);
                pressureCalibrateResultActivity.o.sendEmptyMessage(100);
                return;
            }
            if ("com.huawei.ui.pressure.measure.calibrate.stop".equals(action)) {
                pressureCalibrateResultActivity.o.sendEmptyMessage(200);
                LogUtil.a("PressureCalibrateResultActivity", "calibrate MessageReceiver is suddenness stop !!!");
                psm.e().z();
                psm.e().e(4);
                return;
            }
            if ("com.huawei.ui.pressure.calibrate.err".equals(action)) {
                psm.e().b(intent.getBooleanExtra("isFromNoData", false));
                pressureCalibrateResultActivity.o.sendEmptyMessage(614);
                LogUtil.a("PressureCalibrateResultActivity", "calibrate MessageReceiver is err!!");
                return;
            }
            LogUtil.a("PressureCalibrateResultActivity", "onReceive MessageReceiver is err!!");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("PressureCalibrateResultActivity", "calibrate result onDestroy()");
        TimeClockView timeClockView = this.aa;
        if (timeClockView != null) {
            timeClockView.b();
            this.aa = null;
        }
        if (!this.t && !this.u) {
            psm.e().v();
            LogUtil.a("PressureCalibrateResultActivity", "stopAllTimer");
        }
        a aVar = this.o;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
        }
        k();
        this.t = false;
        this.u = false;
        psm.e().w();
        ptt.d().u();
        super.onDestroy();
    }

    private void k() {
        MessageReceiver messageReceiver = this.c;
        if (messageReceiver != null) {
            unregisterReceiver(messageReceiver);
            this.c = null;
        }
    }

    private void h() {
        String string = this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_dialog_notify);
        String string2 = this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_dialog_text);
        String string3 = this.g.getResources().getString(R$string.IDS_hw_common_ui_dialog_cancel);
        String string4 = this.g.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_dialog_button_stop);
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.g);
        builder.b(string).e(string2).cyV_(string4, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateResultActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                psm.e().i(false);
                PressureCalibrateResultActivity.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(string3, new View.OnClickListener() { // from class: pst
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PressureCalibrateResultActivity.dsW_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public static /* synthetic */ void dsW_(View view) {
        LogUtil.a("PressureCalibrateResultActivity", "calibrate button click cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(Class cls) {
        PressureCalibrateQuestionActivity d = psm.e().d();
        if (d != null) {
            d.finish();
        }
        if (!this.t) {
            psm.e().v();
            this.u = true;
            LogUtil.a("PressureCalibrateResultActivity", "stopAllTimer");
        }
        if (this.p || this.l) {
            psm.e().w();
            ptt.d().u();
            finish();
        } else {
            Intent intent = new Intent(this.g, (Class<?>) cls);
            intent.putExtra("pressure_is_have_datas", this.k);
            startActivity(intent);
            psm.e().w();
            ptt.d().u();
            finish();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (!this.t) {
                LogUtil.a("PressureCalibrateResultActivity", "isIsPressureCalibratedOver == ", Boolean.valueOf(psm.e().l()));
                h();
                return false;
            }
            d();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.n == 1) {
            finish();
        } else if (this.k) {
            a(KnitPressureActivity.class);
        } else if (psm.e().g()) {
            this.k = true;
            a(KnitPressureActivity.class);
        } else {
            a(KnitPressureActivity.class);
        }
        finish();
    }

    static class a extends BaseHandler<PressureCalibrateResultActivity> {
        a(PressureCalibrateResultActivity pressureCalibrateResultActivity) {
            super(pressureCalibrateResultActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dsY_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PressureCalibrateResultActivity pressureCalibrateResultActivity, Message message) {
            if (message == null) {
                LogUtil.b("PressureCalibrateResultActivity", "handleMessageWhenReferenceNotNull msg is null");
                return;
            }
            int i = message.what;
            if (i == 100) {
                pressureCalibrateResultActivity.i();
                return;
            }
            if (i == 200) {
                pressureCalibrateResultActivity.g();
            } else if (i == 614) {
                pressureCalibrateResultActivity.j();
            } else {
                LogUtil.a("PressureCalibrateResultActivity", "no case match!");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
