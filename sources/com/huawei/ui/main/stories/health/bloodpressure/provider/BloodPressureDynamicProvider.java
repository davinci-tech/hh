package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.bloodpressure.constant.DynamicBpStatus;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureDynamicProvider;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import defpackage.bkz;
import defpackage.bzs;
import defpackage.cbh;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.jgu;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.qhd;
import defpackage.qif;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class BloodPressureDynamicProvider extends BaseKnitDataProvider<qhd> {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f10151a = BloodPressureUtil.c();
    private boolean b;
    private String c;
    private Context d;
    private boolean e;
    private DynamicBpStatus g;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        this.d = BaseApplication.getContext();
        if (f10151a) {
            sectionBean.a(d());
            b(sectionBean);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        LogUtil.a("BloodPressureDynamicProvider", "onDestroy");
        jgu.d();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, final SectionBean sectionBean) {
        if (f10151a) {
            boolean b2 = qif.b();
            ReleaseLogUtil.e("R_BloodPressure_BloodPressureDynamicProvider", "loadData isSupport: ", Boolean.valueOf(b2));
            if (b2) {
                jgu.a().e(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureDynamicProvider.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("BloodPressureDynamicProvider", "loadData errorCode: ", Integer.valueOf(i), " objData: ", obj);
                        if (i != 100000 || !(obj instanceof cbh)) {
                            LogUtil.h("BloodPressureDynamicProvider", "loadData error");
                            BloodPressureDynamicProvider.this.a(sectionBean);
                        } else {
                            BloodPressureDynamicProvider.this.d(sectionBean, (cbh) obj);
                        }
                    }
                });
            } else {
                a(sectionBean);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(SectionBean sectionBean, cbh cbhVar) {
        long i = cbhVar.i();
        long a2 = cbhVar.a();
        ReleaseLogUtil.e("BloodPressureDynamicProvider", "planStartTime: ", Long.valueOf(i), " planEndTime: ", Long.valueOf(a2));
        if (i == 0 && a2 == 0) {
            a(sectionBean);
        } else {
            c(new a(this, sectionBean, cbhVar));
        }
    }

    static class a implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private cbh f10152a;
        private WeakReference<BloodPressureDynamicProvider> b;
        private SectionBean e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public a(BloodPressureDynamicProvider bloodPressureDynamicProvider, SectionBean sectionBean, cbh cbhVar) {
            this.b = new WeakReference<>(bloodPressureDynamicProvider);
            this.e = sectionBean;
            this.f10152a = cbhVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("BloodPressureDynamicProvider", "InnerReadListener data: ", obj, " errorCode: ", Integer.valueOf(i));
            BloodPressureDynamicProvider bloodPressureDynamicProvider = this.b.get();
            if (bloodPressureDynamicProvider == null) {
                LogUtil.h("BloodPressureDynamicProvider", "InnerReadListener provider is null");
                return;
            }
            if (i != 0 || !(obj instanceof SparseArray)) {
                LogUtil.h("BloodPressureDynamicProvider", "InnerReadListener error");
                bloodPressureDynamicProvider.a(this.e, this.f10152a);
                return;
            }
            Object obj2 = ((SparseArray) obj).get(700019);
            List list = obj2 instanceof List ? (List) obj2 : null;
            if (!bkz.e(list)) {
                bloodPressureDynamicProvider.c(this.e, (HiHealthData) list.get(0), this.f10152a);
            } else {
                LogUtil.h("BloodPressureDynamicProvider", "DynamicBpResultListener dataList is empty");
                bloodPressureDynamicProvider.a(this.e, this.f10152a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SectionBean sectionBean, cbh cbhVar) {
        if (sectionBean == null || cbhVar == null) {
            LogUtil.h("BloodPressureDynamicProvider", "getPlanData error");
            return;
        }
        long i = cbhVar.i();
        long a2 = cbhVar.a();
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("BloodPressureDynamicProvider", "getPlanData planStartTime: ", Long.valueOf(i), " planEndTime: ", Long.valueOf(a2), " now: ", Long.valueOf(currentTimeMillis));
        d(currentTimeMillis, sectionBean, cbhVar, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(SectionBean sectionBean, HiHealthData hiHealthData, cbh cbhVar) {
        String metaData = hiHealthData.getMetaData();
        if (TextUtils.isEmpty(metaData)) {
            LogUtil.h("BloodPressureDynamicProvider", "getLatestPlan metaData is empty");
            a(sectionBean, cbhVar);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(metaData);
            String optString = jSONObject.optString("planID");
            String c = cbhVar.c();
            long i = cbhVar.i();
            long a2 = cbhVar.a();
            long currentTimeMillis = System.currentTimeMillis();
            ReleaseLogUtil.e("BloodPressureDynamicProvider", "getLatestPlan recordPlanID: ", optString, " statusPlanId: ", c, " planStartTime: ", Long.valueOf(i), " planEndTime: ", Long.valueOf(a2), " now: ", Long.valueOf(currentTimeMillis));
            if (TextUtils.equals(optString, c)) {
                d(sectionBean, jSONObject);
            } else {
                d(currentTimeMillis, sectionBean, cbhVar, jSONObject);
            }
        } catch (JSONException unused) {
            LogUtil.b("BloodPressureDynamicProvider", "getLatestPlan JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SectionBean sectionBean) {
        c(new b(this, sectionBean));
    }

    private void c(HiDataReadResultListener hiDataReadResultListener) {
        HiHealthManager.d(this.d).readHiHealthDataPro(HiDataReadProOption.builder().e(e()).b(1).e(), hiDataReadResultListener);
    }

    private HiDataReadOption e() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(currentTimeMillis);
        hiDataReadOption.setType(new int[]{700019});
        return hiDataReadOption;
    }

    private void c(SectionBean sectionBean, cbh cbhVar) {
        qhd qhdVar = new qhd();
        float f = cbhVar.f();
        if (Float.compare(f, 0.0f) <= 0) {
            this.g = DynamicBpStatus.PLAN_IN_PROGRESS_NO_DATA;
            qhdVar.e(c(cbhVar.i(), cbhVar.a()));
        } else {
            this.g = DynamicBpStatus.PLAN_IN_PROGRESS;
            qhdVar.a(cbhVar.e());
            qhdVar.d(cbhVar.d());
            qhdVar.b(cbhVar.b());
            qhdVar.d(f);
            qhdVar.e(c(cbhVar.i(), cbhVar.a()));
        }
        sectionBean.e(qhdVar);
    }

    private void d(long j, SectionBean sectionBean, cbh cbhVar, JSONObject jSONObject) {
        if (System.currentTimeMillis() - cbhVar.a() < 5000) {
            if (j < cbhVar.i()) {
                b(sectionBean, cbhVar);
                return;
            } else {
                c(sectionBean, cbhVar);
                return;
            }
        }
        if (jSONObject != null) {
            d(sectionBean, jSONObject);
        } else {
            b(sectionBean);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(SectionBean sectionBean) {
        this.g = DynamicBpStatus.NO_PLAN_DATA;
        sectionBean.e(new qhd());
    }

    private void b(SectionBean sectionBean, cbh cbhVar) {
        if (cbhVar == null) {
            LogUtil.h("BloodPressureDynamicProvider", "showFutureDynamicData dynamicBp is null");
            return;
        }
        this.g = DynamicBpStatus.FUTURE_PLAN;
        long i = cbhVar.i();
        long a2 = cbhVar.a();
        if (a2 < i) {
            a2 = jdl.b(i, 1);
        }
        String format = String.format(Locale.ROOT, this.d.getString(R$string.IDS_dynamic_bp_plan_future_time), DateFormatUtil.d(i, DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_HH_MM).replace(",", " "));
        String c = c(i, a2);
        qhd qhdVar = new qhd();
        qhdVar.b(format);
        qhdVar.e(c);
        sectionBean.e(qhdVar);
    }

    private String c(long j, long j2) {
        String d;
        String d2;
        if (jdl.g(j, j2)) {
            d = DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_HH_MM);
            d2 = DateFormatUtil.d(j2, DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_HH_MM);
        } else {
            d = DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YMD_HM);
            d2 = DateFormatUtil.d(j2, DateFormatUtil.DateFormatType.DATE_FORMAT_YMD_HM);
        }
        return String.format(Locale.ROOT, this.d.getString(R$string.IDS_blood_pressure_time), d.replace(",", " "), d2.replace(",", " "));
    }

    static class b implements HiDataReadResultListener {
        private SectionBean b;
        private WeakReference<BloodPressureDynamicProvider> c;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public b(BloodPressureDynamicProvider bloodPressureDynamicProvider, SectionBean sectionBean) {
            this.c = new WeakReference<>(bloodPressureDynamicProvider);
            this.b = sectionBean;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("BloodPressureDynamicProvider", "DynamicBpResultListener data: ", obj, " errorCode: ", Integer.valueOf(i));
            BloodPressureDynamicProvider bloodPressureDynamicProvider = this.c.get();
            if (bloodPressureDynamicProvider == null) {
                LogUtil.h("BloodPressureDynamicProvider", "DynamicBpResultListener provider == null");
                return;
            }
            if (i != 0 || !(obj instanceof SparseArray)) {
                bloodPressureDynamicProvider.b(this.b);
                return;
            }
            Object obj2 = ((SparseArray) obj).get(700019);
            List list = obj2 instanceof List ? (List) obj2 : null;
            if (!bkz.e(list)) {
                bloodPressureDynamicProvider.a((HiHealthData) list.get(0), this.b);
            } else {
                LogUtil.h("BloodPressureDynamicProvider", "DynamicBpResultListener dataList is empty");
                bloodPressureDynamicProvider.b(this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HiHealthData hiHealthData, SectionBean sectionBean) {
        String metaData = hiHealthData.getMetaData();
        if (TextUtils.isEmpty(metaData)) {
            b(sectionBean);
            return;
        }
        try {
            d(sectionBean, new JSONObject(metaData));
        } catch (JSONException unused) {
            LogUtil.b("BloodPressureDynamicProvider", "getDynamicBpData JSONException");
        }
    }

    private void d(SectionBean sectionBean, JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("BloodPressureDynamicProvider", "handlePlanIsFinish error");
            return;
        }
        this.g = DynamicBpStatus.COMPLETE_PLAN;
        qhd qhdVar = new qhd();
        long optLong = jSONObject.optLong("planStartTime");
        long optLong2 = jSONObject.optLong("planActualTime");
        if (optLong2 < optLong) {
            optLong2 = jdl.b(optLong, 1);
        }
        int optInt = jSONObject.optInt("validCntAll");
        int optInt2 = jSONObject.optInt("avgSystolicBpAll");
        int optInt3 = jSONObject.optInt("avgDiastolicBpAll");
        int optInt4 = jSONObject.optInt("planStatus");
        boolean contains = jSONObject.optString("extendData").contains("isRead");
        this.b = !contains;
        qhdVar.d(contains);
        LogUtil.a("BloodPressureDynamicProvider", "handlePlanIsFinish validCntAll: ", Integer.valueOf(optInt), " avgSystolicBpAll: ", Integer.valueOf(optInt2), " avgDiastolicBpAll: ", Integer.valueOf(optInt3), " planStatus: ", Integer.valueOf(optInt4), " planStartTime: ", Long.valueOf(optLong), " planActualTime: ", Long.valueOf(optLong2), " isRead: ", Boolean.valueOf(contains));
        qhdVar.e(c(optLong, optLong2));
        qhdVar.d(optInt2);
        qhdVar.a(optInt3);
        if (optInt == 0) {
            this.g = DynamicBpStatus.FINISH_NO_DATA;
            sectionBean.e(qhdVar);
            return;
        }
        ReleaseLogUtil.e("BloodPressureDynamicProvider", "planStatus: ", Integer.valueOf(optInt4));
        if (optInt4 == 3) {
            this.g = DynamicBpStatus.NOT_ENOUGH_DATA;
            qhdVar.a(this.d.getString(R$string.IDS_dynamic_bp_measure_times_desc_day));
        } else if (optInt4 == 4) {
            this.g = DynamicBpStatus.NOT_ENOUGH_DATA;
            qhdVar.a(this.d.getString(R$string.IDS_dynamic_bp_measure_times_desc_night));
        } else if (optInt4 == 5) {
            this.g = DynamicBpStatus.NOT_ENOUGH_DATA;
            qhdVar.a(this.d.getString(R$string.IDS_dynamic_bp_measure_times_desc));
        } else if (optInt4 == 1) {
            this.g = DynamicBpStatus.PLAN_TERMINATE;
            qhdVar.a(this.d.getString(R$string.IDS_dynamic_bp_plan_terminate));
        }
        sectionBean.e(qhdVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qhd qhdVar) {
        if (qhdVar == null) {
            LogUtil.h("BloodPressureDynamicProvider", "parseParams data is null");
            return;
        }
        hashMap.put("PLAN_STATUS", this.g);
        hashMap.put("READ_STATUS", Boolean.valueOf(qhdVar.h()));
        ReleaseLogUtil.e("BloodPressureDynamicProvider", "parseParam mPlanType: ", this.g);
        switch (AnonymousClass3.b[this.g.ordinal()]) {
            case 1:
                this.c = Constants.NULL;
                hashMap.put("PLAN_DESC", this.d.getString(R$string.IDS_dynamic_bp_no_data));
                break;
            case 2:
                this.c = "future_data";
                hashMap.put("PLAN_DESC", this.d.getString(R$string.IDS_dynamic_bp_be_activated));
                hashMap.put("FUTURE_PLAN_DESC", qhdVar.c());
                hashMap.put("PLAN_TIME", qhdVar.b());
                break;
            case 3:
                this.c = "no_current_data";
                hashMap.put("PLAN_TIME", qhdVar.b());
                hashMap.put("PLAN_DESC_ONE", this.d.getString(R$string.IDS_dynamic_bp_no_valid_data));
                hashMap.put("VALUE", "--");
                hashMap.put("UNIT", this.d.getString(R$string.IDS_device_measure_pressure_value_unit));
                break;
            case 4:
            case 5:
                this.c = "no_current_data";
                hashMap.put("PLAN_DESC_TWO", qhdVar.a());
            case 6:
                this.c = "no_current_data";
                e(qhdVar, hashMap);
                break;
            case 7:
                this.c = "current_data";
                c(qhdVar, hashMap);
                break;
            case 8:
                this.c = "current_data";
                hashMap.put("PLAN_TIME", qhdVar.b());
                hashMap.put("PROGRESS", 0);
                hashMap.put("PROGRESS_DESC", dDI_(this.d.getString(R$string.IDS_dynamic_bp_plan_process, UnitUtil.e(0, 2, 0)), String.valueOf(0)));
                hashMap.put("VALUE", "--");
                hashMap.put("UNIT", this.d.getString(R$string.IDS_device_measure_pressure_value_unit));
                break;
        }
        hashMap.put("READ_BIMAP", b());
    }

    /* renamed from: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureDynamicProvider$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[DynamicBpStatus.values().length];
            b = iArr;
            try {
                iArr[DynamicBpStatus.NO_PLAN_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[DynamicBpStatus.FUTURE_PLAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[DynamicBpStatus.FINISH_NO_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[DynamicBpStatus.NOT_ENOUGH_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[DynamicBpStatus.PLAN_TERMINATE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[DynamicBpStatus.COMPLETE_PLAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[DynamicBpStatus.PLAN_IN_PROGRESS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[DynamicBpStatus.PLAN_IN_PROGRESS_NO_DATA.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void c(qhd qhdVar, HashMap<String, Object> hashMap) {
        int f = qhdVar.f();
        int d = qhdVar.d();
        hashMap.put("PLAN_TIME", qhdVar.b());
        int intValue = BigDecimal.valueOf(qhdVar.j() * 100.0f).setScale(0, 4).intValue();
        hashMap.put("PROGRESS", Integer.valueOf(intValue));
        hashMap.put("PROGRESS_DESC", dDI_(this.d.getString(R$string.IDS_dynamic_bp_plan_process, UnitUtil.e(intValue, 2, 0)), String.valueOf(intValue)));
        hashMap.put("UNIT", this.d.getString(R$string.IDS_device_measure_pressure_value_unit));
        hashMap.put("PLAN_DESC_ONE", this.d.getString(R$string.IDS_dynamic_bp_latest_time, DateFormatUtil.d(qhdVar.e(), DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_HH_MM).replace(",", " ")));
        hashMap.put("VALUE", this.d.getResources().getString(R$string.IDS_hwh_home_range, UnitUtil.e(f, 1, 0), UnitUtil.e(d, 1, 0)));
    }

    private void e(qhd qhdVar, HashMap<String, Object> hashMap) {
        hashMap.put("PLAN_TIME", qhdVar.b());
        int f = qhdVar.f();
        int d = qhdVar.d();
        hashMap.put("PROGRESS", Float.valueOf(qif.c(f, d)));
        hashMap.put("UNIT", this.d.getString(R$string.IDS_device_measure_pressure_value_unit));
        hashMap.put("PLAN_DESC_ONE", this.d.getString(R$string.IDS_dynamic_bp_avg_desc, UnitUtil.e(24.0d, 1, 0)));
        boolean a2 = qif.a(f, d);
        this.e = a2;
        if (a2) {
            hashMap.put("LEVEL", this.d.getString(R$string.IDS_dynamic_bp_high));
            hashMap.put("LEVEL_COLOR", Integer.valueOf(R.color._2131297089_res_0x7f090341));
        } else {
            hashMap.put("LEVEL", this.d.getString(R$string.IDS_hw_normal));
            hashMap.put("LEVEL_COLOR", Integer.valueOf(R.color._2131297090_res_0x7f090342));
        }
        hashMap.put("VALUE", this.d.getResources().getString(R$string.IDS_hwh_home_range, UnitUtil.e(f, 1, 0), UnitUtil.e(d, 1, 0)));
    }

    private SpannableString dDI_(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        nsi.cKI_(spannableString, str2, R.color._2131299236_res_0x7f090ba4);
        nsi.cKL_(spannableString, str2, R$string.textFontFamilyMedium);
        return spannableString;
    }

    private HashMap<String, Object> d() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("TITLE", this.d.getString(R$string.IDS_hw_device_more_data));
        hashMap.put("SUBTITLE", this.d.getString(R$string.IDS_dynamic_bp_subtitle));
        hashMap.put("RIGHT_ICON", Integer.valueOf(R.drawable._2131427842_res_0x7f0b0202));
        hashMap.put("HEAD_PIC", ContextCompat.getDrawable(this.d, R.drawable._2131428383_res_0x7f0b041f));
        hashMap.put("CLICK_EVENT_LISTENER", new AnonymousClass1());
        return hashMap;
    }

    /* renamed from: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureDynamicProvider$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (nsn.o()) {
                LogUtil.a("BloodPressureDynamicProvider", "click too fast");
                ViewClickInstrumentation.clickOnView(view);
            } else {
                LoginInit.getInstance(com.huawei.haf.application.BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: qhq
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        BloodPressureDynamicProvider.AnonymousClass1.this.a(i, obj);
                    }
                }, "");
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        public /* synthetic */ void a(int i, Object obj) {
            if (i == 0) {
                BloodPressureDynamicProvider.this.c();
            } else {
                LogUtil.h("BloodPressureDynamicProvider", "onClick errorCode = ", Integer.valueOf(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        d(1);
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/AllData");
        builder.setImmerse();
        builder.setForceDarkMode(1);
        builder.showStatusBar();
        bzs.e().loadH5ProApp(this.d, "com.huawei.health.h5.abpm", builder);
    }

    private Map<String, Object> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("pageId", "BloodPressure_0001");
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, "r1");
        hashMap.put("value", "ABPM");
        hashMap.put("moduleName", "medical_related");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("moduleName", "ABPM");
        hashMap2.put(FunctionSetBeanReader.BI_HAS_DATA, this.c);
        hashMap2.put("abnormalReminder", Boolean.valueOf(this.e));
        hashMap2.put("dataUpdate", Boolean.valueOf(this.b));
        hashMap.put("pageProperties", hashMap2);
        return hashMap;
    }

    private void d(int i) {
        Map<String, Object> b2 = b();
        b2.put("event", Integer.valueOf(i));
        LogUtil.a("BloodPressureDynamicProvider", "uploadBiEvent card , HEALTH_HOME_BLOOD_PRESSURE_DETAIL_2010025: ", b2.toString());
        ixx.d().d(this.d, AnalyticsValue.HEALTH_HOME_BLOOD_PRESSURE_DETAIL_2010025.value(), b2, 0);
    }
}
