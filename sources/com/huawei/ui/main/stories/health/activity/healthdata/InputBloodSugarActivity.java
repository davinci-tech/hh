package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriod;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriodView;
import com.huawei.ui.commonui.scrollview.ScrollScaleView;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.dks;
import defpackage.gmn;
import defpackage.ixx;
import defpackage.kor;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.qjv;
import defpackage.qkh;
import defpackage.qqk;
import defpackage.qrc;
import defpackage.scj;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes6.dex */
public class InputBloodSugarActivity extends BaseActivity implements View.OnClickListener, BloodSugarTimePeriodView.OnTimePeriodItemChangedListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10053a;
    private boolean ab;
    private int ad;
    private LinearLayout ae;
    private HealthButton ag;
    private int ai;
    private CustomTitleBar aj;
    private int an;
    private HealthTextView b;
    private HealthTextView c;
    private HealthButton e;
    private HealthTextView f;
    private HealthTextView g;
    private ScrollScaleView h;
    private HealthTextView i;
    private HealthTextView j;
    private LinearLayout k;
    private Date l;
    private Context m;
    private long n;
    private int o;
    private c p;
    private e q;
    private int s;
    private d t;
    private int u;
    private qkh v;
    private BloodSugarTimePeriodView w;
    private HiHealthData x;
    private long y;
    private int z;
    private double d = 0.0d;
    private int ah = -1;
    private long af = -1;
    private Handler r = new a(this);
    private boolean aa = false;
    private boolean ac = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.health_data_inputbloodsugar);
        clearBackgroundDrawable();
        this.m = this;
        this.v = qkh.c();
        this.t = new d(this);
        this.q = new e(this);
        this.p = new c(this);
        g();
        f();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (LanguageUtil.b(this.m)) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, 8, 0);
            this.g.setLayoutParams(layoutParams);
        }
    }

    private void f() {
        this.y = System.currentTimeMillis();
        a();
        BigDecimal bigDecimal = new BigDecimal(Double.toString(this.d));
        BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(1));
        this.h.setSelectedPosition(bigDecimal.subtract(bigDecimal2).multiply(new BigDecimal(String.valueOf(10))).intValue());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.y);
        this.an = calendar.get(1);
        this.ad = calendar.get(2) + 1;
        this.o = calendar.get(5);
        this.u = calendar.get(11);
        this.z = calendar.get(12);
        this.v.b();
        this.l = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMd"));
        this.f10053a.setText(simpleDateFormat.format(this.l));
        this.c.setText(simpleDateFormat.format(this.l));
        this.j.setText(nsj.c(this.m, calendar.getTimeInMillis(), 1));
        this.f.setText(nsj.c(this.m, calendar.getTimeInMillis(), 1));
        this.i.setText(UnitUtil.e(this.d, 1, 1));
        if (this.aa) {
            this.w.d(this.y, this.ai);
            c(this.d);
        } else {
            this.w.e(this.y);
        }
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", "5");
        ixx.d().d(this.m, value, hashMap, 0);
    }

    private void d() {
        this.s = 1;
        this.aa = false;
        this.d = qjv.d(this.ai);
        LogUtil.c("InputBloodSugarActivity", "handleInputDefault mTimePeriod is ", Integer.valueOf(this.ai), " mBloodSugar ", Double.valueOf(this.d));
        this.aj.setTitleText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_input));
    }

    private void a() {
        try {
            Intent intent = getIntent();
            if (intent == null) {
                d();
                return;
            }
            String stringExtra = intent.getStringExtra("titleName");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.aa = true;
                this.s = intent.getIntExtra("pageFrom", 0);
                this.aj.setTitleText(stringExtra);
                this.aj.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131429705_res_0x7f0b0949), nsf.h(R$string.accessibility_close));
                this.aj.setRightButtonVisibility(0);
                this.aj.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131429706_res_0x7f0b094a), nsf.h(R$string.IDS_contact_confirm));
                this.ai = intent.getIntExtra("bloodTimePeriod", 0);
                this.y = intent.getLongExtra("time", System.currentTimeMillis());
                this.d = intent.getDoubleExtra("bloodNum", 0.0d);
                long longExtra = intent.getLongExtra("showDefaultTime", 0L);
                this.n = longExtra;
                if (longExtra > 0) {
                    this.ab = true;
                } else {
                    this.ab = false;
                }
                this.ag.setVisibility(8);
                this.e.setVisibility(8);
                LogUtil.c("InputBloodSugarActivity", "mTimePeriod: ", Integer.valueOf(this.ai), " mInsertTime: ", Long.valueOf(this.y));
                this.ah = this.ai;
                this.af = this.y;
                if (this.ab) {
                    this.y = this.n;
                    return;
                }
                return;
            }
            d();
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("InputBloodSugarActivity", "initData Exception");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.k) {
            h();
        } else if (view == this.ae) {
            n();
        } else if (view == this.e) {
            l();
        } else {
            HealthButton healthButton = this.ag;
            if (view == healthButton) {
                healthButton.setClickable(false);
                this.ag.setBackgroundResource(R.drawable.button_background_emphasize_disable);
                e();
            } else {
                LogUtil.a("InputBloodSugarActivity", "onClick view is not exist");
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.y);
        HealthDatePickerDialog healthDatePickerDialog = new HealthDatePickerDialog(this, new HealthDatePickerDialog.DateSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputBloodSugarActivity.1
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public void onDateSelected(int i, int i2, int i3) {
                InputBloodSugarActivity.this.an = i;
                InputBloodSugarActivity.this.ad = i2 + 1;
                InputBloodSugarActivity.this.o = i3;
                calendar.set(InputBloodSugarActivity.this.an, InputBloodSugarActivity.this.ad - 1, InputBloodSugarActivity.this.o);
                calendar.set(11, InputBloodSugarActivity.this.u);
                calendar.set(12, InputBloodSugarActivity.this.z);
                InputBloodSugarActivity.this.y = calendar.getTimeInMillis();
                InputBloodSugarActivity.this.l = calendar.getTime();
                InputBloodSugarActivity.this.f10053a.setText(new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMd")).format(InputBloodSugarActivity.this.l));
                InputBloodSugarActivity.this.c(calendar);
                calendar.clear();
            }
        }, new GregorianCalendar(this.an, this.ad - 1, this.o));
        Calendar calendar2 = Calendar.getInstance();
        healthDatePickerDialog.d(new GregorianCalendar(2014, 0, 1), new GregorianCalendar(calendar2.get(1), calendar2.get(2), calendar2.get(5)));
        healthDatePickerDialog.c(true);
        healthDatePickerDialog.show();
    }

    private void n() {
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog(this, new HealthTimePickerDialog.TimeSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputBloodSugarActivity.4
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public void onTimeSelected(int i, int i2) {
                InputBloodSugarActivity.this.u = i;
                InputBloodSugarActivity.this.z = i2;
                Calendar calendar = Calendar.getInstance();
                calendar.set(InputBloodSugarActivity.this.an, InputBloodSugarActivity.this.ad - 1, InputBloodSugarActivity.this.o);
                calendar.set(11, InputBloodSugarActivity.this.u);
                calendar.set(12, InputBloodSugarActivity.this.z);
                InputBloodSugarActivity.this.y = calendar.getTimeInMillis();
                InputBloodSugarActivity.this.l = calendar.getTime();
                InputBloodSugarActivity.this.j.setText(nsj.c(InputBloodSugarActivity.this.m, InputBloodSugarActivity.this.y, 1));
                InputBloodSugarActivity.this.f.setText(nsj.c(InputBloodSugarActivity.this.m, InputBloodSugarActivity.this.y, 1));
                InputBloodSugarActivity.this.c(calendar);
                calendar.clear();
            }
        });
        healthTimePickerDialog.e(0, 0, 0, this.u, this.z);
        healthTimePickerDialog.b(getString(R$string.IDS_hw_health_show_healthdata_measure_time));
        healthTimePickerDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_INPUT_2030034.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        ixx.d().d(this.m, value, hashMap, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.y);
        calendar.set(11, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(this.y);
        calendar2.set(11, 24);
        kor.a().d(this.m.getApplicationContext(), calendar.getTimeInMillis(), calendar2.getTimeInMillis(), 0, this.p);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj) {
        this.ag.setClickable(true);
        this.ag.setBackgroundResource(R.drawable.button_background_emphasize);
        gmn.e(this.m, new b(this, obj));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.m);
        builder.e(R$string.IDS_service_area_notice_title).c(R$string.IDS_hw_show_healthdata_bloodsugar_prompt_dialog_content).cyo_(R$string.IDS_settings_button_ok, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputBloodSugarActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (InputBloodSugarActivity.this.aa) {
                    InputBloodSugarActivity.this.e(false);
                } else {
                    InputBloodSugarActivity.this.c();
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyn_(R$string.IDS_settings_button_cancal, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputBloodSugarActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.c().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Calendar calendar) {
        this.w.e(calendar.getTimeInMillis());
        this.ag.setBackgroundResource(R.drawable.button_background_emphasize_disable);
        this.ag.setClickable(false);
        this.aj.setRightButtonClickable(false);
        this.ai = 0;
        c(0.0d);
    }

    private boolean c(int i, long j, long j2) {
        if (this.ai == i) {
            long j3 = this.y;
            if (j3 == j && j3 == j2) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, long j2) {
        qkh qkhVar = this.v;
        Context applicationContext = this.m.getApplicationContext();
        int i = this.ai;
        qkhVar.d(applicationContext, new long[]{j, j2}, i, this.d, new g(this, this.s, this.aa, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        long j;
        long j2;
        HiHealthData hiHealthData = this.x;
        if (hiHealthData != null) {
            j = hiHealthData.getStartTime();
            j2 = this.x.getEndTime();
        } else {
            j = 0;
            j2 = 0;
        }
        if (c(this.ai, j, j2)) {
            e(j2, j2);
            return;
        }
        qqk.a().c();
        this.v.e(this.m.getApplicationContext(), this.ai, j, j2, this.t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        int i;
        this.ac = z;
        long j = this.af;
        if (j == -1 || (i = this.ah) == -1) {
            return;
        }
        if (!z && c(i, j, j)) {
            e(j, j);
        } else {
            qqk.a().c();
            this.v.e(this.m.getApplicationContext(), this.ah, j, j, this.q);
        }
    }

    private void g() {
        this.aj = (CustomTitleBar) findViewById(R.id.health_healthdata_inputbloodsugar_title_layout);
        this.k = (LinearLayout) findViewById(R.id.hw_show_health_data_inputbloodsugar_top_datelayout);
        if (nsn.c() >= 3.2f && (this.k.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.k.getLayoutParams();
            layoutParams.height = -2;
            this.k.setLayoutParams(layoutParams);
        }
        this.ae = (LinearLayout) findViewById(R.id.hw_show_health_data_inputbloodsugar_top_timelayout);
        if (nsn.c() >= 3.2f && (this.ae.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.ae.getLayoutParams();
            layoutParams2.height = -2;
            this.ae.setLayoutParams(layoutParams2);
        }
        this.f10053a = (HealthTextView) findViewById(R.id.hw_show_health_data_inputbloodsugar_top_date);
        this.c = (HealthTextView) findViewById(R.id.hw_show_health_data_inputbloodsugar_data);
        this.j = (HealthTextView) findViewById(R.id.hw_show_health_data_inputbloodsugar_top_time);
        this.f = (HealthTextView) findViewById(R.id.hw_show_health_data_inputbloodsugar_time);
        if (nsn.c() >= 3.2f) {
            this.f10053a.setVisibility(8);
            this.c.setVisibility(0);
            this.j.setVisibility(8);
            this.f.setVisibility(0);
        } else {
            this.f10053a.setVisibility(0);
            this.c.setVisibility(8);
            this.j.setVisibility(0);
            this.f.setVisibility(8);
        }
        i();
    }

    private void i() {
        this.h = (ScrollScaleView) findViewById(R.id.health_healthdata_inputbloodsugar_bloodsugar_scale);
        this.i = (HealthTextView) findViewById(R.id.hw_show_health_data_inputbloodsugar_mid_bloodsugar);
        this.e = (HealthButton) findViewById(R.id.hw_show_health_data_inputbloodsugar_bind_device);
        if (EnvironmentInfo.k()) {
            this.e.setVisibility(4);
        }
        this.g = (HealthTextView) findViewById(R.id.hw_inputbloodsugare_unit);
        this.b = (HealthTextView) findViewById(R.id.hw_inputbloodsugar_conclusion);
        this.ag = (HealthButton) findViewById(R.id.hw_show_health_data_inputbloodsugar_confirm);
        BloodSugarTimePeriodView bloodSugarTimePeriodView = (BloodSugarTimePeriodView) findViewById(R.id.hw_health_input_bloodsugar_time_period);
        this.w = bloodSugarTimePeriodView;
        BaseActivity.cancelLayoutById(bloodSugarTimePeriodView);
        ImageView imageView = (ImageView) findViewById(R.id.hw_health_input_bloodsugar_date);
        ImageView imageView2 = (ImageView) findViewById(R.id.hw_health_input_bloodsugar_time);
        if (LanguageUtil.bc(this.m)) {
            imageView.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            imageView2.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            imageView.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            imageView2.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.g.setText(getResources().getString(R$string.IDS_hw_health_show_healthdata_bloodsugar_mmol));
        this.aj.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131298057_res_0x7f090709));
        ArrayList arrayList = new ArrayList(10);
        for (int i = 1; i < 34; i++) {
            arrayList.add(UnitUtil.e(i, 1, 0));
        }
        this.h.setData(arrayList, 10, 40);
        this.h.setNoScroll(true);
        b();
    }

    private void b() {
        this.h.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputBloodSugarActivity.2
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public void onSelected(List<String> list, int i) {
                double d2 = (i * 0.1d) + 1.0d;
                DecimalFormat decimalFormat = new DecimalFormat("##0.0");
                DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
                decimalFormatSymbols.setDecimalSeparator(FilenameUtils.EXTENSION_SEPARATOR);
                decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
                InputBloodSugarActivity.this.d = new BigDecimal(decimalFormat.format(d2)).doubleValue();
                InputBloodSugarActivity.this.i.setText(UnitUtil.e(d2, 1, 1));
                if (InputBloodSugarActivity.this.ai != 0) {
                    InputBloodSugarActivity.this.c(d2);
                }
            }
        });
        this.k.setOnClickListener(this);
        this.ae.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.ag.setOnClickListener(this);
        this.ag.setClickable(false);
        this.aj.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputBloodSugarActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputBloodSugarActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.aj.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputBloodSugarActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputBloodSugarActivity.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.w.setOnTimePeriodItemChangedListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(double d2) {
        if (d2 <= 0.0d) {
            this.b.setText("");
            return;
        }
        Map<String, String> a2 = qjv.a(this.m, this.ai, (float) d2);
        String str = a2.get("HEALTH_BLOOD_SUGAR_LEVEL_KEY");
        if (String.valueOf(1001).equals(str) || String.valueOf(1002).equals(str)) {
            this.b.setTextColor(ContextCompat.getColor(this.m, R.color._2131296797_res_0x7f09021d));
        } else if (String.valueOf(1003).equals(str)) {
            this.b.setTextColor(ContextCompat.getColor(this.m, R.color._2131296799_res_0x7f09021f));
        } else if (String.valueOf(1004).equals(str) || String.valueOf(1005).equals(str) || String.valueOf(1006).equals(str)) {
            this.b.setTextColor(ContextCompat.getColor(this.m, R.color._2131296795_res_0x7f09021b));
        } else {
            this.b.setTextColor(ContextCompat.getColor(this.m, R.color._2131296799_res_0x7f09021f));
        }
        String str2 = a2.get("HEALTH_BLOOD_SUGAR_LEVEL_DESC");
        this.b.setText(TextUtils.isEmpty(str2) ? "" : str2);
    }

    private void l() {
        dks.e(this.m, "HDK_BLOOD_SUGAR");
    }

    @Override // com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriodView.OnTimePeriodItemChangedListener
    public void onTimePeriodItemChanged(int i, BloodSugarTimePeriod bloodSugarTimePeriod) {
        this.ai = bloodSugarTimePeriod.getCode();
        this.ag.setBackgroundResource(R.drawable.button_background_emphasize);
        this.ag.setClickable(true);
        this.aj.setRightButtonClickable(true);
        c(this.d);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.p != null) {
            LogUtil.a("InputBloodSugarActivity", "mGetBloodSugarTodayResponseCallback is not null");
            this.p = null;
        }
    }

    static class d implements IBaseResponseCallback {
        private WeakReference<InputBloodSugarActivity> b;

        d(InputBloodSugarActivity inputBloodSugarActivity) {
            this.b = new WeakReference<>(inputBloodSugarActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("InputBloodSugarActivity", "DeleteDataResponseCallback onResponse data = ", obj);
            InputBloodSugarActivity inputBloodSugarActivity = this.b.get();
            if (inputBloodSugarActivity == null) {
                return;
            }
            inputBloodSugarActivity.x = null;
            Message obtainMessage = inputBloodSugarActivity.r.obtainMessage();
            obtainMessage.what = 2;
            inputBloodSugarActivity.r.sendMessage(obtainMessage);
            if (i == 0) {
                LogUtil.a("InputBloodSugarActivity", "DeleteDataResponseCallback delete successful");
            } else {
                LogUtil.a("InputBloodSugarActivity", "DeleteDataResponseCallback delete failed");
            }
        }
    }

    static class e implements IBaseResponseCallback {
        private WeakReference<InputBloodSugarActivity> e;

        e(InputBloodSugarActivity inputBloodSugarActivity) {
            this.e = new WeakReference<>(inputBloodSugarActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("InputBloodSugarActivity", "DeleteDataResponseCallback onResponse data = ", obj);
            InputBloodSugarActivity inputBloodSugarActivity = this.e.get();
            if (inputBloodSugarActivity == null) {
                return;
            }
            if (inputBloodSugarActivity.ac) {
                inputBloodSugarActivity.e(inputBloodSugarActivity.y, inputBloodSugarActivity.y);
            } else {
                inputBloodSugarActivity.c();
            }
            if (i == 0) {
                LogUtil.h("InputBloodSugarActivity", "DeletePreviousDataResponseCallback delete successful");
            } else {
                LogUtil.h("InputBloodSugarActivity", "DeletePreviousDataResponseCallback delete failed");
            }
        }
    }

    static class a extends BaseHandler<InputBloodSugarActivity> {
        a(InputBloodSugarActivity inputBloodSugarActivity) {
            super(inputBloodSugarActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dAB_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(InputBloodSugarActivity inputBloodSugarActivity, Message message) {
            if (inputBloodSugarActivity == null) {
                LogUtil.h("InputBloodSugarActivity", "InputBloodSugarActivityHandler activity is null");
                return;
            }
            if (message == null) {
                LogUtil.h("InputBloodSugarActivity", "handleMessageWhenReferenceNotNull message is null");
                return;
            }
            int i = message.what;
            if (i == 2) {
                inputBloodSugarActivity.e(inputBloodSugarActivity.y, inputBloodSugarActivity.y);
                return;
            }
            if (i != 3) {
                if (i != 5) {
                    return;
                }
                inputBloodSugarActivity.e(message.obj);
            } else if (message.arg1 == 0) {
                if (inputBloodSugarActivity.aa) {
                    inputBloodSugarActivity.setResult(200);
                } else {
                    Intent intent = new Intent(inputBloodSugarActivity, (Class<?>) BloodSugarFeedbackActivity.class);
                    intent.putExtra("titleName", inputBloodSugarActivity.getResources().getString(R$string.IDS_hw_health_show_healthdata_input));
                    intent.putExtra("time", inputBloodSugarActivity.y);
                    intent.putExtra("bloodNum", inputBloodSugarActivity.d);
                    intent.putExtra("bloodTimePeriod", inputBloodSugarActivity.ai);
                    inputBloodSugarActivity.startActivity(intent);
                }
                inputBloodSugarActivity.finish();
            }
        }
    }

    static class g implements IBaseResponseCallback {
        private int b;
        private int c;
        private boolean d;
        private WeakReference<InputBloodSugarActivity> e;

        g(InputBloodSugarActivity inputBloodSugarActivity, int i, boolean z, int i2) {
            this.e = new WeakReference<>(inputBloodSugarActivity);
            this.c = i;
            this.d = z;
            this.b = i2;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0) {
                LogUtil.a("InputBloodSugarActivity", "InsertBloodSugarResponseCallback: start sync cloud");
                c();
                qrc.a();
            }
            InputBloodSugarActivity inputBloodSugarActivity = this.e.get();
            if (inputBloodSugarActivity == null) {
                return;
            }
            LogUtil.c("InputBloodSugarActivity", "InsertBloodSugarResponseCallback, callBackCode = ", Integer.valueOf(i), ", data = ", obj);
            if (i == 0) {
                LogUtil.a("InputBloodSugarActivity", "InsertBloodSugarResponseCallback, insert successful");
                inputBloodSugarActivity.r.sendMessage(inputBloodSugarActivity.r.obtainMessage(3, 0, 0));
            } else {
                LogUtil.a("InputBloodSugarActivity", "InsertBloodSugarResponseCallback, insert fail");
                inputBloodSugarActivity.r.sendMessage(inputBloodSugarActivity.r.obtainMessage(3, 1, 0));
            }
        }

        private void c() {
            int e = scj.e(this.b);
            if (this.c == 0 || e == 0) {
                return;
            }
            String value = AnalyticsValue.HEALTH_HOME_BLOOD_SUGAR_INPUT_VIEW_2030034.value();
            HashMap hashMap = new HashMap();
            hashMap.put("click", "1");
            hashMap.put("recordType", Integer.valueOf(this.d ? 1 : 2));
            hashMap.put("dataType", Integer.valueOf(e));
            hashMap.put("from", Integer.valueOf(this.c));
            ixx.d().d(BaseApplication.e(), value, hashMap, 0);
        }
    }

    static class b implements IBaseResponseCallback {
        private Object d;
        private WeakReference<InputBloodSugarActivity> e;

        b(InputBloodSugarActivity inputBloodSugarActivity, Object obj) {
            this.e = new WeakReference<>(inputBloodSugarActivity);
            this.d = obj;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            InputBloodSugarActivity inputBloodSugarActivity = this.e.get();
            if (inputBloodSugarActivity == null) {
                return;
            }
            if (System.currentTimeMillis() < inputBloodSugarActivity.y) {
                nrh.d(inputBloodSugarActivity.m, inputBloodSugarActivity.m.getResources().getString(R$string.IDS_hw_health_show_healthdata_timeerror));
                return;
            }
            Object obj2 = this.d;
            if (obj2 instanceof List) {
                for (HiHealthData hiHealthData : (List) obj2) {
                    int type = hiHealthData.getType();
                    if (type == inputBloodSugarActivity.ai && type != 2106 && !scj.d(hiHealthData.getInt("trackdata_deviceType"))) {
                        inputBloodSugarActivity.x = hiHealthData;
                        inputBloodSugarActivity.j();
                        return;
                    }
                }
            }
            if (inputBloodSugarActivity.aa) {
                inputBloodSugarActivity.e(true);
            } else {
                inputBloodSugarActivity.e(inputBloodSugarActivity.y, inputBloodSugarActivity.y);
            }
        }
    }

    static class c implements IBaseResponseCallback {
        private WeakReference<InputBloodSugarActivity> d;

        c(InputBloodSugarActivity inputBloodSugarActivity) {
            this.d = new WeakReference<>(inputBloodSugarActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            InputBloodSugarActivity inputBloodSugarActivity = this.d.get();
            if (inputBloodSugarActivity != null) {
                Message obtainMessage = inputBloodSugarActivity.r.obtainMessage();
                obtainMessage.what = 5;
                obtainMessage.obj = obj;
                inputBloodSugarActivity.r.sendMessage(obtainMessage);
                return;
            }
            LogUtil.h("InputBloodSugarActivity", "GetBloodSugarTodayResponseCallback inputBloodSugarActivity is null");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
