package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.view.dashboard.BloodSugarDashboardView;
import com.huawei.ui.commonui.linechart.view.dashboard.DashboardRingView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarFeedbackActivity;
import com.huawei.ui.main.stories.health.adapter.SugarFoodReferenceAdapter;
import com.huawei.ui.main.stories.health.util.BloodSugarCallback;
import com.huawei.ui.main.stories.health.util.UpDataFileListener;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.bkz;
import defpackage.bzs;
import defpackage.gge;
import defpackage.jdl;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qjv;
import defpackage.qlb;
import defpackage.qra;
import defpackage.qro;
import defpackage.quh;
import defpackage.qul;
import defpackage.qvz;
import defpackage.rud;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class BloodSugarFeedbackActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10028a;
    private long aa;
    private SugarFoodReferenceAdapter ab;
    private CustomTitleBar ac;
    private String ad;
    private int b;
    private HealthTextView c;
    private HealthColumnLinearLayout d;
    private HealthTextView e;
    private HealthButton f;
    private String g;
    private BloodSugarDashboardView h;
    private double i;
    private int j;
    private HealthRecycleView k;
    private qra l;
    private HealthButton m;
    private long n;
    private int o;
    private Handler p;
    private boolean q;
    private HealthTextView r;
    private boolean s;
    private HealthTextView t;
    private boolean u;
    private String v;
    private boolean w;
    private long x;
    private String y;
    private List<qlb> z = new ArrayList(16);
    private a ag = new a(Looper.getMainLooper(), this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f();
        h();
    }

    private void f() {
        if (nsn.r()) {
            setContentView(R.layout.health_data_bloodsugar_feedback_large);
        } else {
            setContentView(R.layout.health_data_bloodsugar_feedback);
        }
        clearBackgroundDrawable();
        e();
        this.ac = (CustomTitleBar) findViewById(R.id.health_healthdata_bloodsugar_title_layout);
        this.t = (HealthTextView) findViewById(R.id.health_blood_desc);
        this.c = (HealthTextView) findViewById(R.id.blood_feed_back_top_year);
        this.e = (HealthTextView) findViewById(R.id.blood_feed_back_top_time);
        this.f10028a = (HealthTextView) findViewById(R.id.blood_feed_back_top_type);
        this.k = (HealthRecycleView) findViewById(R.id.sugary_foods_recycler_view);
        this.r = (HealthTextView) findViewById(R.id.health_blood_desc_by_blood_number);
        this.m = (HealthButton) findViewById(R.id.btn_enter_diet);
        this.f = (HealthButton) findViewById(R.id.hw_show_health_data_inputbloodsugar_confirm);
        this.d = (HealthColumnLinearLayout) findViewById(R.id.blood_sugar_foot_container);
        this.f.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.h = (BloodSugarDashboardView) findViewById(R.id.blood_sugar_dashboard_view);
        this.k.setLayoutManager(new GridLayoutManager(this, 4) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarFeedbackActivity.4
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    private void e() {
        Intent intent = getIntent();
        if (intent != null) {
            this.ad = intent.getStringExtra("titleName");
            this.aa = intent.getLongExtra("time", 0L);
            this.i = intent.getDoubleExtra("bloodNum", 0.0d);
            this.b = intent.getIntExtra("bloodTimePeriod", 0);
            this.u = intent.getBooleanExtra("isEdit", false);
            this.s = intent.getBooleanExtra("bloodSugarDataIsFromMeter", false);
            this.n = intent.getLongExtra("showDefaultTime", 0L);
            this.x = intent.getLongExtra(ParsedFieldTag.TASK_MODIFY_TIME, 0L);
            this.j = intent.getIntExtra("clientId", 0);
            this.o = intent.getIntExtra("pageFrom", 0);
            if (this.n > 0) {
                this.q = true;
            } else {
                this.q = false;
            }
        }
    }

    private void h() {
        this.p = new Handler();
        this.g = CommonUtil.c();
        String x = CommonUtil.x();
        this.v = x;
        LogUtil.c("BloodSugarFeedbackActivity", "current language", this.g, x);
        SugarFoodReferenceAdapter sugarFoodReferenceAdapter = new SugarFoodReferenceAdapter(this);
        this.ab = sugarFoodReferenceAdapter;
        this.k.setAdapter(sugarFoodReferenceAdapter);
        this.l = new qra();
        if (String.valueOf(1001).equals(qjv.a(this, this.b, (float) this.i).get("HEALTH_BLOOD_SUGAR_LEVEL_KEY"))) {
            this.w = true;
            this.d.setVisibility(0);
        } else {
            this.w = false;
        }
        this.l.b(new b());
        n();
        b(this.b, (float) this.i);
        i();
    }

    private void i() {
        this.r.setText(qro.a(this.b, this));
    }

    private void b(int i, float f) {
        ArrayList arrayList = new ArrayList();
        List<Float> a2 = qjv.a(i);
        int i2 = 0;
        int i3 = 0;
        while (i3 < a2.size() && i3 != a2.size() - 1 && f >= a2.get(i3).floatValue()) {
            i3++;
        }
        int[] a3 = qjv.a(this);
        int length = (a3.length / 2) + i3;
        float f2 = 0.0f;
        while (i2 < a2.size()) {
            float floatValue = a2.get(i2).floatValue();
            int i4 = i2 == i3 ? length : i2;
            if (a3 != null && i4 < a3.length) {
                arrayList.add(new DashboardRingView.b(f2, floatValue, a3[i4]));
            }
            i2++;
            f2 = floatValue;
        }
        this.h.setRingAreas(1.0f, 33.0f, arrayList);
        String valueOf = String.valueOf(qjv.a(this, i, f).get("HEALTH_BLOOD_SUGAR_LEVEL_DESC"));
        if (a3 != null && length < a3.length) {
            this.h.setStatusText(valueOf, a3[length]);
        }
        this.h.setCurrentValue(f);
        this.h.c();
    }

    private void n() {
        if (!TextUtils.isEmpty(this.ad)) {
            this.ac.setTitleText(this.ad);
        }
        if (this.q) {
            this.c.setText(DateFormatUtil.d(this.n, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD));
            this.e.setText(DateFormatUtil.d(this.n, DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE));
        } else {
            this.c.setText(DateFormatUtil.d(this.aa, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD));
            this.e.setText(DateFormatUtil.d(this.aa, DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE));
        }
        String b2 = qro.b(this.b, this);
        LogUtil.c("BloodSugarFeedbackActivity", "current time period text ", b2, Integer.valueOf(this.b));
        if (!TextUtils.isEmpty(b2)) {
            this.f10028a.setText(b2);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.ac.getRightIconImage().setScaleX(-1.0f);
        }
        this.ac.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
        this.ac.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: qcp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarFeedbackActivity.this.dzl_(view);
            }
        });
        j();
        if (this.u) {
            this.f.setVisibility(8);
            this.ac.setRightSoftkeyVisibility(0);
            this.ac.setRightButtonVisibility(0);
        } else {
            this.f.setVisibility(0);
            this.ac.setRightSoftkeyVisibility(8);
            this.ac.setRightButtonVisibility(8);
        }
    }

    public /* synthetic */ void dzl_(View view) {
        if (this.u) {
            Intent intent = new Intent();
            if (this.s) {
                dzj_(intent);
            } else {
                dzk_(intent);
            }
            startActivityForResult(intent, 201);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.u) {
            return;
        }
        g();
    }

    private void g() {
        int i;
        LogUtil.a("BloodSugarFeedbackActivity", "mBloodTimePeriod ", Integer.valueOf(this.b), " mModifyTime ", Long.valueOf(this.x), " mTime ", Long.valueOf(this.aa), " mDefaultTime ", Long.valueOf(this.n));
        int i2 = this.b;
        if ((i2 == 2008 || i2 == 2010 || i2 == 2012) && this.i < 3.9d) {
            this.ag.sendEmptyMessage(30011);
            LogUtil.a("BloodSugarFeedbackActivity", "mBloodValue is lower than 3.9");
            return;
        }
        if (i2 == 2008 || i2 == 2009) {
            i = 10;
        } else if (i2 == 2010 || i2 == 2011) {
            i = 20;
        } else if (i2 == 2012 || i2 == 2013) {
            i = 30;
        } else {
            this.ag.sendEmptyMessage(30011);
            LogUtil.a("BloodSugarFeedbackActivity", "mBloodValue is MSG_DIET_REFRESH");
            i = 40;
        }
        if (i != 40) {
            d(i, this.aa);
        }
    }

    private void d(int i, long j) {
        int b2 = DateFormatUtil.b(j);
        ReleaseLogUtil.b("BloodSugarFeedbackActivity", "queryTimeDietRecord dayFormat ", Integer.valueOf(b2));
        qvz.d(b2, b2, new d(i, this.ag));
    }

    private int a() {
        int i = this.b;
        return (i == 2009 || i == 2011 || i == 2013 || i == 2014) ? 2 : 1;
    }

    private void c(long j) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", Integer.valueOf(a()));
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_FEED_BACK_2040134.value();
        hashMap.put("click", "1");
        gge.e(value, hashMap);
        LogUtil.a("BloodSugarFeedbackActivity", "addPath is /diet_recording_tool?date=", Long.valueOf(j));
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/diet_recording_tool?date=" + j + "&from=2");
        bzs.e().loadH5ProApp(this, "com.huawei.health.h5.diet-diary", builder);
    }

    private void j() {
        PrivacyDataModel privacyDataModel = new PrivacyDataModel();
        if (this.q) {
            privacyDataModel.setStartTime(this.n);
            privacyDataModel.setEndTime(this.n);
            privacyDataModel.setModifyTime(this.x);
        } else {
            privacyDataModel.setStartTime(this.aa);
            privacyDataModel.setEndTime(this.aa);
            privacyDataModel.setModifyTime(this.x);
        }
        privacyDataModel.setClientId(this.j);
        String string = getResources().getString(R$string.IDS_device_measure_sugar_value_unit);
        privacyDataModel.setDoubleValue(this.i);
        privacyDataModel.setDataTitle(UnitUtil.e(this.i, 1, 1) + string);
        rud.c(this, this.ac, 108, privacyDataModel);
    }

    private void dzk_(Intent intent) {
        intent.setClass(this, InputBloodSugarActivity.class);
        intent.putExtra("titleName", this.ad);
        if (this.q) {
            intent.putExtra("showDefaultTime", this.n);
        }
        intent.putExtra("time", this.aa);
        intent.putExtra("bloodNum", this.i);
        intent.putExtra("bloodTimePeriod", this.b);
        intent.putExtra("pageFrom", this.o);
    }

    private void dzj_(Intent intent) {
        intent.setClass(this, BloodSugarDeviceMeasureActivity.class);
        intent.putExtra("entrance", "jump_from_blood_sugar_feedback");
        if (this.q) {
            intent.putExtra("showDefaultTime", this.n);
        }
        intent.putExtra("start_time", this.aa);
        intent.putExtra("time_period", this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        final String str = qjv.a(this, this.b, (float) this.i).get("HEALTH_BLOOD_SUGAR_LEVEL_KEY");
        LogUtil.a("BloodSugarFeedbackActivity", "read file current level", str);
        if (String.valueOf(1003).equals(str)) {
            str = str + "_" + this.b;
        }
        LogUtil.a("BloodSugarFeedbackActivity", "read file current level", str);
        this.l.c(new BloodSugarCallback() { // from class: qck
            @Override // com.huawei.ui.main.stories.health.util.BloodSugarCallback
            public final void result(int i, JSONObject jSONObject) {
                BloodSugarFeedbackActivity.this.e(str, i, jSONObject);
            }
        });
    }

    public /* synthetic */ void e(String str, int i, JSONObject jSONObject) {
        LogUtil.a("BloodSugarFeedbackActivity", "load blood file", Integer.valueOf(i));
        if (i != 0 || jSONObject == null) {
            LogUtil.h("BloodSugarFeedbackActivity", "load blood file");
            return;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("bloodSugarLevel");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                c(jSONObject2, jSONObject2.getString(OpAnalyticsConstants.OPERATION_ID), str);
            }
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("BloodSugarFeedbackActivity", "not find res id");
        } catch (NumberFormatException unused2) {
            LogUtil.b("BloodSugarFeedbackActivity", "num error");
        } catch (JSONException unused3) {
            LogUtil.b("BloodSugarFeedbackActivity", "JSONException ");
        }
    }

    private void c(JSONObject jSONObject, String str, String str2) throws JSONException {
        final String replace;
        LogUtil.a("BloodSugarFeedbackActivity", "current id is ", str, ", compare id is ", str2);
        if (str2.equals(str)) {
            Iterator<String> keys = jSONObject.keys();
            String string = jSONObject.getString("suggesionDesc");
            LogUtil.a("BloodSugarFeedbackActivity", "serviceId is ", string);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            String str3 = this.l.b() + "strings";
            String d2 = qro.d(string, str3, this.g, this.v);
            LogUtil.a("BloodSugarFeedbackActivity", "path is ", str3);
            if (TextUtils.isEmpty(d2)) {
                replace = qro.c(str3 + "/strings.xml", string);
            } else {
                replace = d2.replace("\\", "");
                LogUtil.a("BloodSugarFeedbackActivity", "desc is ", replace);
            }
            e(jSONObject, keys, replace);
            LogUtil.a("BloodSugarFeedbackActivity", "current desc ", this.y);
            this.p.post(new Runnable() { // from class: qcq
                @Override // java.lang.Runnable
                public final void run() {
                    BloodSugarFeedbackActivity.this.a(replace);
                }
            });
        }
    }

    public /* synthetic */ void a(String str) {
        if (!TextUtils.isEmpty(this.y)) {
            this.t.setText(this.y);
        } else {
            this.t.setText(str);
        }
    }

    private void e(JSONObject jSONObject, Iterator<String> it, String str) throws JSONException {
        while (it.hasNext()) {
            String next = it.next();
            LogUtil.a("BloodSugarFeedbackActivity", "replace key", next, str);
            if ("placeholder".equals(next)) {
                this.y = b(str, jSONObject.getJSONArray("placeholder"));
            }
        }
    }

    private String b(String str, JSONArray jSONArray) throws JSONException, NumberFormatException {
        if (jSONArray == null) {
            return str;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        LogUtil.c("BloodSugarFeedbackActivity", "current desc ", str);
        int length = jSONArray.length();
        if (length == 1) {
            return a(str, jSONArray);
        }
        if (length == 2) {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            String string = jSONObject.getString("type");
            String string2 = jSONObject.getString("value");
            JSONObject jSONObject2 = jSONArray.getJSONObject(1);
            String string3 = jSONObject2.getString("type");
            String string4 = jSONObject2.getString("value");
            try {
            } catch (NumberFormatException unused) {
                LogUtil.b("BloodSugarFeedbackActivity", "Value NumberFormatException");
            }
            if ("100".equals(string)) {
                if ("100".equals(string3)) {
                    return String.format(Locale.getDefault(), str, Integer.valueOf(Integer.parseInt(string2)), Integer.valueOf(Integer.parseInt(string4)));
                }
                return String.format(Locale.getDefault(), str, Integer.valueOf(Integer.parseInt(string2)), Float.valueOf(Float.parseFloat(string4)));
            }
            if ("101".equals(string)) {
                if ("101".equals(string3)) {
                    return String.format(Locale.getDefault(), str, Float.valueOf(Float.parseFloat(string2)), Float.valueOf(Float.parseFloat(string4)));
                }
                return String.format(Locale.getDefault(), str, Float.valueOf(Float.parseFloat(string2)), Integer.valueOf(Integer.parseInt(string4)));
            }
            LogUtil.c("BloodSugarFeedbackActivity", "no replace");
            return "";
        }
        return a(str, jSONArray);
    }

    private String a(String str, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject = jSONArray.getJSONObject(0);
        String string = jSONObject.getString("type");
        String string2 = jSONObject.getString("value");
        try {
        } catch (NumberFormatException unused) {
            LogUtil.b("BloodSugarFeedbackActivity", "Value NumberFormatException");
        }
        if ("100".equals(string)) {
            return String.format(Locale.getDefault(), str, Integer.valueOf(Integer.parseInt(string2)));
        }
        if (string.equals("101")) {
            return String.format(Locale.getDefault(), str, Float.valueOf(Float.parseFloat(string2)));
        }
        LogUtil.c("BloodSugarFeedbackActivity", "no replace");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.l.a(new BloodSugarCallback() { // from class: qch
            @Override // com.huawei.ui.main.stories.health.util.BloodSugarCallback
            public final void result(int i, JSONObject jSONObject) {
                BloodSugarFeedbackActivity.this.e(i, jSONObject);
            }
        });
    }

    public /* synthetic */ void e(int i, JSONObject jSONObject) {
        if (i != 0 || jSONObject == null) {
            return;
        }
        try {
            a(jSONObject.getJSONArray("sugarFood"));
            this.p.post(new Runnable() { // from class: qci
                @Override // java.lang.Runnable
                public final void run() {
                    BloodSugarFeedbackActivity.this.d();
                }
            });
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("BloodSugarFeedbackActivity", "not find res id");
        } catch (JSONException unused2) {
            LogUtil.b("BloodSugarFeedbackActivity", "JSONException ");
        }
    }

    public /* synthetic */ void d() {
        this.ab.e(this.z);
    }

    private void a(JSONArray jSONArray) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            qlb qlbVar = new qlb();
            d(jSONObject, qlbVar, jSONObject.getString("name"));
            qlbVar.d(this.l.b() + "images" + File.separator + jSONObject.getString("pictureUrl"));
            if (this.z.size() < 4) {
                this.z.add(qlbVar);
            }
        }
    }

    private void d(JSONObject jSONObject, qlb qlbVar, String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String d2 = qro.d(str, this.l.b() + "strings", this.g, this.v);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            LogUtil.c("BloodSugarFeedbackActivity", "replace key", next);
            if ("placeholder".equals(next)) {
                d2 = b(d2, jSONObject.getJSONArray("placeholder"));
            }
        }
        qlbVar.c(d2);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 201 && i2 == 200) {
            finish();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_enter_diet) {
            long t = jdl.t(this.aa) / 1000;
            LogUtil.a("BloodSugarFeedbackActivity", "onClick currentTime ", Long.valueOf(t));
            c(t);
        }
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.ac != null) {
            this.ac = null;
        }
        if (this.f != null) {
            this.f = null;
        }
        super.onDestroy();
    }

    static class a extends BaseHandler<BloodSugarFeedbackActivity> {
        WeakReference<BloodSugarFeedbackActivity> b;

        public a(Looper looper, BloodSugarFeedbackActivity bloodSugarFeedbackActivity) {
            super(looper, bloodSugarFeedbackActivity);
            this.b = new WeakReference<>(bloodSugarFeedbackActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dzm_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BloodSugarFeedbackActivity bloodSugarFeedbackActivity, Message message) {
            BloodSugarFeedbackActivity bloodSugarFeedbackActivity2 = this.b.get();
            if (bloodSugarFeedbackActivity2 == null || message == null) {
                LogUtil.h("BloodSugarFeedbackActivity", "BloodSugarFeedbackActivity weakReference or message is null");
                return;
            }
            int i = message.what;
            if (i == 30010) {
                if (bloodSugarFeedbackActivity2.m != null && bloodSugarFeedbackActivity2.f != null) {
                    bloodSugarFeedbackActivity2.m.setVisibility(0);
                    bloodSugarFeedbackActivity2.f.setBackgroundResource(R.color._2131299296_res_0x7f090be0);
                    bloodSugarFeedbackActivity2.f.setTextColor(bloodSugarFeedbackActivity.getResources().getColor(R.color.emui_accent));
                    return;
                }
                LogUtil.h("BloodSugarFeedbackActivity", "mEnterDiet or mConfirmButton is null");
                return;
            }
            if (i == 30011) {
                if (bloodSugarFeedbackActivity2.m != null) {
                    bloodSugarFeedbackActivity2.m.setVisibility(8);
                    bloodSugarFeedbackActivity2.f.setTextAppearance(bloodSugarFeedbackActivity, R.style.health_button_style_emphasize);
                    return;
                } else {
                    LogUtil.h("BloodSugarFeedbackActivity", "mEnterDiet is null");
                    return;
                }
            }
            LogUtil.h("BloodSugarFeedbackActivity", "other message");
        }
    }

    static class b implements UpDataFileListener {
        private final WeakReference<BloodSugarFeedbackActivity> c;

        private b(BloodSugarFeedbackActivity bloodSugarFeedbackActivity) {
            this.c = new WeakReference<>(bloodSugarFeedbackActivity);
        }

        @Override // com.huawei.ui.main.stories.health.util.UpDataFileListener
        public void onFinish() {
            BloodSugarFeedbackActivity bloodSugarFeedbackActivity = this.c.get();
            if (bloodSugarFeedbackActivity == null) {
                LogUtil.h("BloodSugarFeedbackActivity", "activity is null");
                return;
            }
            LogUtil.a("BloodSugarFeedbackActivity", "download onFinish");
            bloodSugarFeedbackActivity.c();
            if (bloodSugarFeedbackActivity.w) {
                bloodSugarFeedbackActivity.b();
            }
        }
    }

    static class d implements ResponseCallback<List<quh>> {

        /* renamed from: a, reason: collision with root package name */
        int f10029a;
        a e;

        d(int i, a aVar) {
            this.f10029a = i;
            this.e = aVar;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<quh> list) {
            LogUtil.a("BloodSugarFeedbackActivity", "DietDiaryCbkInner errorCode ", Integer.valueOf(i), " list ", list);
            if (bkz.e(list)) {
                this.e.sendEmptyMessage(30010);
                return;
            }
            quh quhVar = list.get(0);
            if (quhVar == null) {
                this.e.sendEmptyMessage(30010);
                return;
            }
            boolean b = b(quhVar);
            LogUtil.a("BloodSugarFeedbackActivity", "queryTimeDietRecord isContainsMeal ", Boolean.valueOf(b), " MSG_DIET_REFRESH is true, MSG_NO_DIET is false");
            if (b) {
                this.e.sendEmptyMessage(30011);
            } else {
                this.e.sendEmptyMessage(30010);
            }
        }

        private boolean b(quh quhVar) {
            List<qul> a2 = quhVar.a();
            boolean z = false;
            if (bkz.e(a2)) {
                return false;
            }
            for (qul qulVar : a2) {
                if (!bkz.e(qulVar.c())) {
                    int h = qulVar.h();
                    LogUtil.a("BloodSugarFeedbackActivity", "queryTimeDietRecord meals is ", Integer.valueOf(a2.size()), " timeType is ", Integer.valueOf(this.f10029a), " whichMeal is ", Integer.valueOf(h));
                    if (this.f10029a == h) {
                        LogUtil.a("BloodSugarFeedbackActivity", "queryTimeDietRecord has a dietRecord");
                        z = true;
                    }
                }
            }
            return z;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
