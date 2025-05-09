package com.huawei.ui.main.stories.health.activity.healthdata;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.scrollview.ScrollScaleView;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import com.huawei.uikit.phone.hwbutton.widget.HwButton;
import defpackage.dks;
import defpackage.gmn;
import defpackage.ixx;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.qpk;
import defpackage.qpr;
import defpackage.scn;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class InputTemperatureActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f10054a = {"KnitHealthData_Edit_" + DataInfos.TemperatureDayDetail, "KnitHealthData_Edit_" + DataInfos.TemperatureWeekDetail, "KnitHealthData_Edit_" + DataInfos.TemperatureMonthDetail};
    private HealthTextView aa;
    private HealthTextView ab;
    private b ad;
    private CustomTitleBar ae;
    private int af;
    private HealthTextView ag;
    private Dialog ah;
    private LinearLayout ai;
    private ScrollScaleView am;
    private HwButton b;
    private Context c;
    private qpk d;
    private LinearLayout e;
    private long f;
    private int g;
    private HealthTextView h;
    private a i;
    private int j;
    private ImageView l;
    private int m;
    private e n;
    private ImageView o;
    private boolean p;
    private long q;
    private int r;
    private long u;
    private long w;
    private int x;
    private HwButton y;
    private float v = 0.0f;
    private float ac = 0.0f;
    private final Handler k = new c(this);
    private boolean s = true;
    private boolean t = false;
    private int z = 34;

    private boolean d(double d) {
        return d >= 0.0d && d < 100.0d;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.c("Input_TemperatureActivity", "onCreate");
        super.onCreate(bundle);
        setContentView(R.layout.health_data_input_temperature);
        this.c = this;
        this.p = UnitUtil.d();
        this.n = new e(this);
        this.ad = new b(this);
        this.i = new a();
        this.d = qpk.d();
        e();
        i();
        f();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        scn.c(this.ab);
    }

    private void i() {
        this.ae = (CustomTitleBar) findViewById(R.id.health_data_input_temperature_titlebar);
        this.e = (LinearLayout) findViewById(R.id.health_data_input_temperature_date_layout);
        this.ai = (LinearLayout) findViewById(R.id.health_data_input_temperature_time_layout);
        this.o = (ImageView) findViewById(R.id.health_data_input_temperature_date_arrow);
        this.l = (ImageView) findViewById(R.id.health_data_input_temperature_time_arrow);
        if (LanguageUtil.bc(this.c)) {
            this.o.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.l.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.o.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.l.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.h = (HealthTextView) findViewById(R.id.health_data_input_temperature_date_text);
        this.ag = (HealthTextView) findViewById(R.id.health_data_input_temperature_time_text);
        this.aa = (HealthTextView) findViewById(R.id.health_data_input_temperature_data);
        this.ab = (HealthTextView) findViewById(R.id.health_data_input_temperature_data_unit);
        this.ab.setText(this.p ? getString(R$string.IDS_settings_health_temperature_unit, new Object[]{""}) : getString(R$string.IDS_temp_unit_fahrenheit, new Object[]{""}));
        g();
    }

    private void g() {
        this.y = (HwButton) findViewById(R.id.health_data_input_temperature_confirm);
        this.b = (HwButton) findViewById(R.id.health_data_input_temperature_bind_device);
        if (EnvironmentInfo.k()) {
            this.b.setVisibility(8);
        }
        this.am = (ScrollScaleView) findViewById(R.id.health_data_input_temperature_data_scale);
        float f = this.ac;
        if (f < 34.0f) {
            this.z = (int) Math.floor(f);
        }
        float f2 = this.ac;
        int ceil = f2 > 42.0f ? (int) Math.ceil(f2) : 42;
        ArrayList arrayList = new ArrayList();
        if (!this.p) {
            this.z = (int) Math.ceil(qpr.c(this.z));
            ceil = (int) qpr.c(ceil);
        }
        for (int i = this.z; i <= ceil; i++) {
            arrayList.add(UnitUtil.e(i, 1, 0));
        }
        this.am.setData(arrayList, 10, 40);
        this.am.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputTemperatureActivity.3
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public void onSelected(List<String> list, int i2) {
                float floatValue = new BigDecimal(InputTemperatureActivity.this.z + (i2 * 0.1d)).setScale(1, 4).floatValue();
                InputTemperatureActivity.this.aa.setText(UnitUtil.e(floatValue, 1, 1));
                if (!InputTemperatureActivity.this.p) {
                    floatValue = qpr.a(floatValue);
                }
                InputTemperatureActivity.this.ac = floatValue;
            }
        });
        k();
        h();
    }

    private void k() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.s = intent.getBooleanExtra("isShowInput", false);
                this.g = intent.getIntExtra("deviceId", 1);
            } catch (Exception unused) {
                LogUtil.b("Input_TemperatureActivity", "isShowInputView Exception");
            }
        }
        if (this.s) {
            e(true, 0);
            this.am.setNoScroll(true);
            a();
            this.ae.setRightButtonVisibility(8);
            this.ae.setTitleText(this.c.getString(R$string.IDS_hw_health_show_healthdata_input));
            this.y.setVisibility(0);
            return;
        }
        e(false, 8);
        m();
    }

    private void e(boolean z, int i) {
        this.e.setEnabled(z);
        this.ai.setEnabled(z);
        this.o.setVisibility(i);
        this.l.setVisibility(i);
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (LanguageUtil.bc(this.c)) {
            this.ae.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428443_res_0x7f0b045b), nsf.h(R$string.accessibility_go_back));
        } else {
            this.ae.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
        }
    }

    private void m() {
        this.am.setNoScroll(false);
        a();
        this.ae.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
        this.ae.setRightButtonVisibility(0);
        if (this.g != 1) {
            this.ae.setRightButtonVisibility(8);
        }
        this.ae.setTitleText(this.c.getString(R$string.IDS_hw_base_health_data_history_record));
        this.y.setVisibility(8);
        this.ae.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputTemperatureActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Input_TemperatureActivity", "showNotInput onClick mIsShowInputting = ", Boolean.valueOf(InputTemperatureActivity.this.t));
                if (InputTemperatureActivity.this.t) {
                    if (InputTemperatureActivity.this.j()) {
                        LogUtil.a("Input_TemperatureActivity", "showNotInput onClick isDataCorrect = true");
                        InputTemperatureActivity.this.t = false;
                        InputTemperatureActivity.this.a();
                        InputTemperatureActivity.this.am.setNoScroll(false);
                        if (InputTemperatureActivity.this.s) {
                            InputTemperatureActivity.this.ae.setRightButtonVisibility(8);
                        } else {
                            InputTemperatureActivity.this.ae.setRightButtonDrawable(InputTemperatureActivity.this.getResources().getDrawable(R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
                        }
                        InputTemperatureActivity.this.d();
                    }
                } else {
                    InputTemperatureActivity.this.t = true;
                    InputTemperatureActivity.this.am.setNoScroll(true);
                    InputTemperatureActivity.this.ae.setLeftButtonDrawable(InputTemperatureActivity.this.getResources().getDrawable(R.drawable._2131430274_res_0x7f0b0b82), nsf.h(R$string.accessibility_close));
                    InputTemperatureActivity.this.ae.setRightButtonDrawable(InputTemperatureActivity.this.getResources().getDrawable(R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R$string.IDS_contact_confirm));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.ac = (float) intent.getDoubleExtra(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE, 36.5d);
                this.f = intent.getLongExtra("deletetime", -1L);
            } catch (Exception unused) {
                LogUtil.b("Input_TemperatureActivity", "getDataFromIntent Exception");
            }
            if (!d(this.ac)) {
                LogUtil.h("Input_TemperatureActivity", "getDataFromIntent mTemperature is invalid: ", Float.valueOf(this.ac));
                this.ac = 36.5f;
            }
            this.v = this.ac;
            long j = this.f;
            this.w = j;
            if (j != -1) {
                this.q = j;
            } else {
                this.q = System.currentTimeMillis();
            }
            this.u = this.q;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.d != null) {
            String value = AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_INPUT_2060050.value();
            HashMap hashMap = new HashMap();
            hashMap.put("click", "1");
            ixx.d().d(this.c, value, hashMap, 0);
            HiTimeInterval hiTimeInterval = new HiTimeInterval();
            hiTimeInterval.setStartTime(this.w);
            hiTimeInterval.setEndTime(this.w);
            ArrayList arrayList = new ArrayList();
            arrayList.add(hiTimeInterval);
            this.d.c(this.c, arrayList, this.i);
            LogUtil.a("Input_TemperatureActivity", "delete time of data: ", Long.valueOf(this.w));
            qpk qpkVar = this.d;
            long j = this.q;
            qpkVar.e(j, j, this.ac, this.n);
        }
    }

    private void f() {
        BigDecimal bigDecimal;
        float c2 = this.p ? this.ac : qpr.c(this.ac);
        try {
            bigDecimal = new BigDecimal(UnitUtil.a(c2, 1));
        } catch (NumberFormatException unused) {
            c2 = 36.5f;
            this.ac = 36.5f;
            bigDecimal = new BigDecimal(36.5f);
            LogUtil.h("Input_TemperatureActivity", "initData mTemperature is invalid ", Float.valueOf(this.ac));
        }
        this.am.setSelectedPosition(bigDecimal.subtract(BigDecimal.valueOf(this.z)).multiply(BigDecimal.valueOf(10L)).intValue());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.q);
        this.af = calendar.get(1);
        this.x = calendar.get(2);
        this.j = calendar.get(5);
        this.m = calendar.get(11);
        this.r = calendar.get(12);
        this.h.setText(nsj.g(this.q));
        this.ag.setText(nsj.c(this.c, this.q, 1));
        this.aa.setText(UnitUtil.e(c2, 1, 1));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.e) {
            if (this.s) {
                l();
            } else if (this.t) {
                l();
            }
        } else if (view == this.ai) {
            if (this.s) {
                n();
            } else if (this.t) {
                n();
            }
        } else if (view == this.y) {
            if (nsn.a(500)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            this.y.setClickable(false);
            this.y.setBackgroundResource(R.drawable.button_background_emphasize_disable);
            if (j()) {
                gmn.e(this.c, this.ad);
            }
        } else if (view == this.b) {
            String value = AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_DETAIL_BIND_2060048.value();
            HashMap hashMap = new HashMap();
            hashMap.put("click", "1");
            ixx.d().d(this.c, value, hashMap, 0);
            dks.e(this.c, "HDK_BODY_TEMPERATURE");
        } else {
            LogUtil.h("Input_TemperatureActivity", "onClick this view cannot find");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.q;
        if (currentTimeMillis < j) {
            Toast.makeText(this.c, getString(R$string.IDS_hw_health_show_healthdata_timeerror), 0).show();
            o();
            return false;
        }
        LogUtil.a("Input_TemperatureActivity", "isDataCorrect mInsertTime = ", Long.valueOf(j));
        return true;
    }

    private void h() {
        this.e.setOnClickListener(this);
        this.ai.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.ae.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputTemperatureActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Input_TemperatureActivity", "initClickEvent onClick mIsShowInputting = ", Boolean.valueOf(InputTemperatureActivity.this.t));
                if (InputTemperatureActivity.this.t) {
                    InputTemperatureActivity.this.t = false;
                    InputTemperatureActivity.this.a();
                    if (InputTemperatureActivity.this.s) {
                        InputTemperatureActivity.this.ae.setRightButtonVisibility(8);
                    } else {
                        InputTemperatureActivity.this.ae.setRightButtonDrawable(InputTemperatureActivity.this.getResources().getDrawable(R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
                    }
                    InputTemperatureActivity.this.am.setNoScroll(false);
                    InputTemperatureActivity.this.b();
                } else {
                    InputTemperatureActivity.this.setResult(-1);
                    InputTemperatureActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.ac = this.v;
        this.f = this.w;
        this.q = this.u;
        f();
    }

    private void l() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.q);
        HealthDatePickerDialog healthDatePickerDialog = new HealthDatePickerDialog(this, new HealthDatePickerDialog.DateSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputTemperatureActivity.2
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public void onDateSelected(int i, int i2, int i3) {
                InputTemperatureActivity.this.af = i;
                InputTemperatureActivity.this.x = i2;
                InputTemperatureActivity.this.j = i3;
                calendar.set(InputTemperatureActivity.this.af, InputTemperatureActivity.this.x, InputTemperatureActivity.this.j);
                calendar.set(11, InputTemperatureActivity.this.m);
                calendar.set(12, InputTemperatureActivity.this.r);
                InputTemperatureActivity.this.q = calendar.getTimeInMillis();
                InputTemperatureActivity.this.h.setText(nsj.g(InputTemperatureActivity.this.q));
                calendar.clear();
            }
        }, new GregorianCalendar(this.af, this.x, this.j));
        Calendar calendar2 = Calendar.getInstance();
        healthDatePickerDialog.d(new GregorianCalendar(2014, 0, 1), new GregorianCalendar(calendar2.get(1), calendar2.get(2), calendar2.get(5)));
        healthDatePickerDialog.c(true);
        Window window = healthDatePickerDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.y = (int) getResources().getDimension(R.dimen._2131362573_res_0x7f0a030d);
        window.setAttributes(attributes);
        healthDatePickerDialog.show();
        dAE_(window);
        HwTextView hwTextView = (HwTextView) healthDatePickerDialog.findViewById(R.id.hwdatepicker_dialog_title);
        if (hwTextView != null) {
            hwTextView.setGravity(GravityCompat.START);
        }
    }

    private void n() {
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog(this, new HealthTimePickerDialog.TimeSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputTemperatureActivity.1
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public void onTimeSelected(int i, int i2) {
                LogUtil.c("Input_TemperatureActivity", "hour = ", Integer.valueOf(i), ", minute = ", Integer.valueOf(i2));
                InputTemperatureActivity.this.m = i;
                InputTemperatureActivity.this.r = i2;
                Calendar calendar = Calendar.getInstance();
                calendar.set(InputTemperatureActivity.this.af, InputTemperatureActivity.this.x, InputTemperatureActivity.this.j);
                calendar.set(11, InputTemperatureActivity.this.m);
                calendar.set(12, InputTemperatureActivity.this.r);
                InputTemperatureActivity.this.q = calendar.getTimeInMillis();
                InputTemperatureActivity.this.ag.setText(nsj.c(InputTemperatureActivity.this.c, InputTemperatureActivity.this.q, 1));
                calendar.clear();
                InputTemperatureActivity.this.ah.dismiss();
            }
        });
        healthTimePickerDialog.e(0, 0, 0, this.m, this.r);
        healthTimePickerDialog.b(getString(R$string.IDS_hw_health_show_healthdata_measure_time));
        Window window = healthTimePickerDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.y = (int) getResources().getDimension(R.dimen._2131362573_res_0x7f0a030d);
        window.setAttributes(attributes);
        healthTimePickerDialog.show();
        dAE_(window);
        HwTextView hwTextView = (HwTextView) healthTimePickerDialog.findViewById(R.id.hwtimepicker_title);
        if (hwTextView != null) {
            hwTextView.setGravity(GravityCompat.START);
        }
        this.ah = healthTimePickerDialog;
    }

    private void dAE_(Window window) {
        Context context = this.c;
        if (context == null || window == null) {
            LogUtil.b("Input_TemperatureActivity", "context or window is null");
        } else {
            window.setGravity((nsn.ag(context) || 2 == this.c.getResources().getConfiguration().orientation) ? 17 : 80);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.y.setClickable(true);
        this.y.setBackgroundResource(R.drawable.button_background_emphasize);
    }

    static class b implements IBaseResponseCallback {
        WeakReference<InputTemperatureActivity> b;
        InputTemperatureActivity c;

        b(InputTemperatureActivity inputTemperatureActivity) {
            WeakReference<InputTemperatureActivity> weakReference = new WeakReference<>(inputTemperatureActivity);
            this.b = weakReference;
            this.c = weakReference.get();
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            InputTemperatureActivity inputTemperatureActivity = this.c;
            if (inputTemperatureActivity != null) {
                inputTemperatureActivity.o();
                if (this.c.d != null) {
                    String value = AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_INPUT_2060050.value();
                    HashMap hashMap = new HashMap(16);
                    hashMap.put("click", "1");
                    ixx.d().d(this.c.c.getApplicationContext(), value, hashMap, 0);
                    if (this.c.f != -1) {
                        HiTimeInterval hiTimeInterval = new HiTimeInterval();
                        hiTimeInterval.setStartTime(this.c.f);
                        hiTimeInterval.setEndTime(this.c.f);
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(hiTimeInterval);
                        this.c.d.c(this.c.c.getApplicationContext(), arrayList, this.c.i);
                        LogUtil.a("Input_TemperatureActivity", "delete time of data = ", Long.toString(this.c.f));
                    }
                    this.c.d.e(this.c.q, this.c.q, this.c.ac, this.c.n);
                }
            }
        }
    }

    static class e implements IBaseResponseCallback {
        InputTemperatureActivity b;
        WeakReference<InputTemperatureActivity> d;

        e(InputTemperatureActivity inputTemperatureActivity) {
            WeakReference<InputTemperatureActivity> weakReference = new WeakReference<>(inputTemperatureActivity);
            this.d = weakReference;
            this.b = weakReference.get();
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (this.b != null) {
                LogUtil.c("Input_TemperatureActivity", "onResponse, errorCode = ", Integer.valueOf(i));
                if (i == 0) {
                    LogUtil.a("Input_TemperatureActivity", "onResponse, insert SUCCESS");
                    this.b.k.sendMessage(this.b.k.obtainMessage(3, 0, 0));
                } else {
                    LogUtil.h("Input_TemperatureActivity", "onResponse, insert FAIL");
                    this.b.k.sendMessage(this.b.k.obtainMessage(3, 1, 0));
                }
            }
        }
    }

    static class a implements IBaseResponseCallback {
        private a() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0) {
                LogUtil.a("Input_TemperatureActivity", "DeleteDataResponseCallback onResponse delete successful");
            } else {
                LogUtil.h("Input_TemperatureActivity", "DeleteDataResponseCallback onResponse delete failed");
            }
        }
    }

    public static class c extends BaseHandler<InputTemperatureActivity> {
        c(InputTemperatureActivity inputTemperatureActivity) {
            super(inputTemperatureActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dAF_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(InputTemperatureActivity inputTemperatureActivity, Message message) {
            if (message == null) {
                LogUtil.h("Input_TemperatureActivity", "handleMessageWhenReferenceNotNull msg is null");
                return;
            }
            if (message.what == 3 && message.arg1 == 0) {
                LogUtil.a("Input_TemperatureActivity", "temperature sendBroadcast");
                for (String str : InputTemperatureActivity.f10054a) {
                    ObserverManagerUtil.c(str, Long.valueOf(inputTemperatureActivity.q));
                }
                inputTemperatureActivity.setResult(-1);
                inputTemperatureActivity.finish();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
