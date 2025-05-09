package com.huawei.ui.main.stories.health.temperature.provider;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.KnitHealthDataChangeListener;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.listener.LineChartRangeShowCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.temperature.chart.KnitTemperatureLineChartHolder;
import defpackage.eah;
import defpackage.eaj;
import defpackage.ixx;
import defpackage.jec;
import defpackage.koq;
import defpackage.nom;
import defpackage.qkg;
import defpackage.qny;
import defpackage.qpg;
import defpackage.qpk;
import defpackage.qpr;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class TemperatureLineChartProvider extends MajorProvider<qpg> implements LineChartRangeShowCallback, KnitHealthDataChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private String f10242a;
    private final List<Integer> b;
    private WeakReference<Context> c;
    private String d;
    private DataInfos e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private long k;
    private long l;
    private long m;
    private boolean n;
    private boolean o;
    private KnitTemperatureLineChartHolder p;
    private long q;
    private String r;
    private Resources s;
    private Observer t;
    private List<Integer> u;
    private String v;
    private qpg w;
    private SectionBean x;

    static /* synthetic */ long h(TemperatureLineChartProvider temperatureLineChartProvider) {
        long j = temperatureLineChartProvider.q - 1;
        temperatureLineChartProvider.q = j;
        return j;
    }

    public TemperatureLineChartProvider() {
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        this.u = new ArrayList();
        this.w = new qpg();
        this.k = 0L;
        this.m = -1L;
        this.h = true;
        this.g = false;
        this.j = false;
        this.n = false;
        this.f = true;
        this.i = true;
        this.e = DataInfos.NoDataPlaceHolder;
        this.q = -1L;
        this.v = "TEMPERATURE_MIN_MAX";
        this.t = new Observer() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureLineChartProvider.2
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                TemperatureLineChartProvider.this.e();
            }
        };
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BODY_TEMPERATURE_SET.value()));
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(arrayList, new eaj(this, "KnitTemperatureLineChartProvider"));
        this.o = Utils.o();
        LogUtil.a("KnitTemperatureLineChartProvider", "is oversea ?" + this.o);
        ObserverManagerUtil.d(this.t, "data_changed_week_month_resume");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qpg qpgVar) {
        hashMap.put("FIRST_TIME_BIND", Boolean.valueOf(this.i));
        if (!this.i) {
            hashMap.put("START_TIME", Long.valueOf(qpgVar.c()));
        }
        this.i = false;
        hashMap.put("SHOW_DATA_TYPE", this.o ? "SKIN_TEMPERATURE_MIN_MAX" : qpgVar.b());
        LogUtil.a("KnitTemperatureLineChartProvider", "SHOW_DATA_TYPE is " + qpgVar.b());
        c();
    }

    @Override // com.huawei.health.knit.section.listener.LineChartRangeShowCallback
    public void onRangeChange(Context context, long j) {
        long d2 = jec.d(j);
        long a2 = a(d2);
        if (nom.p(j)) {
            if (this.k != d2 || this.f) {
                this.k = d2;
                this.m = a2;
                this.w.b(d2, a2, this.l);
                qny qnyVar = new qny();
                qnyVar.c(d2);
                qnyVar.b(a2);
                LogUtil.a("KnitTemperatureLineChartProvider", "storage.requestData");
                qnyVar.a(this.e, this.w, new e(this));
            }
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.x = sectionBean;
        HashMap<String, Object> c = c(BaseApplication.getContext());
        sectionBean.a(c);
        b();
        qpg qpgVar = new qpg();
        qpgVar.e(((Long) c.get("START_TIME")).longValue());
        sectionBean.e(qpgVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        LogUtil.a("KnitTemperatureLineChartProvider", "begin to loadData");
        this.x = sectionBean;
        if (this.c == null) {
            this.c = new WeakReference<>(context);
        }
        c(new a(this, this.x));
        onRangeChange(context, this.k);
        this.f = true;
    }

    private void c() {
        LogUtil.c("KnitTemperatureLineChartProvider", "onSelect is ", this.v);
        if (this.v.equals("TEMPERATURE_MIN_MAX")) {
            a(AnalyticsValue.TEMPERATURE_SWITCH_TYPE_2060076.value(), 0);
        } else if (this.v.equals("SKIN_TEMPERATURE_MIN_MAX")) {
            a(AnalyticsValue.TEMPERATURE_SWITCH_TYPE_2060076.value(), 1);
        } else {
            LogUtil.c("KnitTemperatureLineChartProvider", "onSelect unKnow");
        }
        KnitTemperatureLineChartHolder knitTemperatureLineChartHolder = this.p;
        if (knitTemperatureLineChartHolder != null) {
            if (this.o) {
                this.v = "SKIN_TEMPERATURE_MIN_MAX";
            }
            knitTemperatureLineChartHolder.c(this.v);
            this.p.b(this.v);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        if (this.h) {
            this.h = false;
            return;
        }
        WeakReference<Context> weakReference = this.c;
        if (weakReference == null || weakReference.get() == null) {
            LogUtil.b("KnitTemperatureLineChartProvider", "context is null");
        } else if (this.n) {
            this.n = false;
            e();
            ObserverManagerUtil.c("data_changed_day_resume", new Object[0]);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onStop() {
        super.onStop();
        this.n = true;
    }

    public void c(CommonUiBaseResponse commonUiBaseResponse) {
        qpk d2 = qpk.d();
        int[] iArr = {d2.b(), 2104};
        if (Utils.o()) {
            iArr = new int[]{d2.o()};
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setCount(1);
        d2.a(iArr, hiDataReadOption, new d(this, commonUiBaseResponse));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list, CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("KnitTemperatureLineChartProvider", "handleTemperatureData enter");
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(31);
        if (list != null && list.size() > 0) {
            LogUtil.a("KnitTemperatureLineChartProvider", "handleTemperatureData data.size = ", Integer.valueOf(list.size()));
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getEndTime() <= currentTimeMillis) {
                    double value = hiHealthData.getValue();
                    long startTime = hiHealthData.getStartTime();
                    int i = hiHealthData.getInt("trackdata_deviceType");
                    qkg qkgVar = new qkg();
                    qkgVar.c(value);
                    qkgVar.a(startTime);
                    qkgVar.c(i);
                    if (Math.abs(qkgVar.o()) >= 0.0d) {
                        arrayList.add(qkgVar);
                    } else {
                        LogUtil.h("KnitTemperatureLineChartProvider", "invalid temperature value");
                    }
                }
            }
        } else {
            LogUtil.a("KnitTemperatureLineChartProvider", "handleTemperatureData data is null or empty");
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(0, arrayList);
        }
        LogUtil.a("KnitTemperatureLineChartProvider", "handleTemperatureData cost of time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        c();
        this.w.e(this.v);
        long j = this.q;
        if (j <= 0) {
            j = this.k;
        }
        this.w.e(j);
        LogUtil.a("KnitTemperatureLineChartProvider", "sectionBean.setData");
        this.x.e(this.w);
    }

    private void b() {
        LogUtil.a("KnitTemperatureLineChartProvider", "begin to init registerObserver");
        this.d = "KnitHealthData_CardSelected_" + this.e;
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureLineChartProvider.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                LogUtil.a("KnitTemperatureLineChartProvider", "begin to notify registerObserver");
                if (objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof String)) {
                    return;
                }
                TemperatureLineChartProvider.this.v = (String) obj;
                LogUtil.a("KnitTemperatureLineChartProvider", "mShowDataType is " + TemperatureLineChartProvider.this.v);
                TemperatureLineChartProvider.this.e();
            }
        }, this.d);
        this.f10242a = "KnitHealthData_Edit_" + this.e;
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureLineChartProvider.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                LogUtil.a("KnitTemperatureLineChartProvider", "update mLastTimestamp");
                if (objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Long)) {
                    return;
                }
                TemperatureLineChartProvider.this.l = ((Long) obj).longValue();
                TemperatureLineChartProvider.this.w.d(TemperatureLineChartProvider.this.l);
                LogUtil.a("KnitTemperatureLineChartProvider", "mLastTimestamp is " + TemperatureLineChartProvider.this.l);
            }
        }, this.f10242a);
        this.r = "KnitHealthData_MarkView_" + this.e;
        LogUtil.a("KnitTemperatureLineChartProvider", "begin to update mMarkViewTime");
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureLineChartProvider.5
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (objArr == null || objArr.length <= 0) {
                    return;
                }
                Object obj = objArr[0];
                if (obj instanceof Long) {
                    TemperatureLineChartProvider.this.q = ((Long) obj).longValue();
                    if (TemperatureLineChartProvider.this.m <= 0 || TemperatureLineChartProvider.this.q <= TemperatureLineChartProvider.this.m) {
                        return;
                    }
                    TemperatureLineChartProvider.h(TemperatureLineChartProvider.this);
                }
            }
        }, this.r);
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureLineChartProvider.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                LogUtil.a("KnitTemperatureLineChartProvider", "notify updateChart");
                TemperatureLineChartProvider.this.e();
            }
        }, "REFRESH_TEMPERATURE_CHART");
    }

    private HashMap<String, Object> c(Context context) {
        DataInfos dataInfos;
        HashMap<String, Object> hashMap = new HashMap<>();
        Bundle extra = getExtra();
        Serializable serializable = extra != null ? extra.getSerializable("key_bundle_health_line_chart_data_infos") : null;
        if (serializable instanceof DataInfos) {
            dataInfos = (DataInfos) serializable;
        } else {
            dataInfos = DataInfos.NoDataPlaceHolder;
        }
        this.e = dataInfos;
        hashMap.put("DATA_INFO", dataInfos);
        Bundle extra2 = getExtra();
        Object obj = DataInfos.NoDataPlaceHolder;
        long j = 0;
        if (extra2 != null) {
            long j2 = extra2.getLong("key_bundle_health_last_data_time", 0L);
            if (j2 == 0) {
                LogUtil.a("KnitTemperatureLineChartProvider", "first into card latestDataTime is zero");
                j = System.currentTimeMillis();
            } else {
                j = j2;
            }
            Serializable serializable2 = extra2.getSerializable("key_bundle_health_line_chart_data_infos");
            if (serializable2 instanceof DataInfos) {
                obj = (DataInfos) serializable2;
            }
        }
        LogUtil.a("KnitTemperatureLineChartProvider", "first into card latestDataTime " + j);
        this.k = j;
        this.l = j;
        hashMap.put("START_TIME", Long.valueOf(j));
        hashMap.put("DATA_INFO", obj);
        hashMap.put("CURSOR_UP_AVERAGE_TEXT", context.getString(R$string.IDS_temperature_average));
        a(context, hashMap);
        hashMap.put("BOTTOM_LEFT_COLOR", Integer.valueOf(ContextCompat.getColor(context, R.color._2131299223_res_0x7f090b97)));
        hashMap.put("BOTTOM_MID_COLOR", Integer.valueOf(ContextCompat.getColor(context, R.color._2131299224_res_0x7f090b98)));
        hashMap.put("BOTTOM_RIGHT_COLOR", Integer.valueOf(ContextCompat.getColor(context, R.color._2131299222_res_0x7f090b96)));
        KnitTemperatureLineChartHolder knitTemperatureLineChartHolder = new KnitTemperatureLineChartHolder(context);
        this.p = knitTemperatureLineChartHolder;
        hashMap.put("HEALTH_CHART_HOLDER", knitTemperatureLineChartHolder);
        hashMap.put("RANGE_SHOW_CALL_BACK", this);
        return hashMap;
    }

    private void a(Context context, HashMap<String, Object> hashMap) {
        this.s = context.getResources();
        String e2 = UnitUtil.e(35.0d, 1, 1);
        String e3 = UnitUtil.e(37.20000076293945d, 1, 1);
        String string = this.s.getString(R$string.IDS_temperature_normal_range, e2, e3);
        String string2 = this.s.getString(R$string.IDS_temperature_less_than, e2);
        String string3 = this.s.getString(R$string.IDS_temperature_more_than, e3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(string2);
        arrayList.add(string);
        arrayList.add(string3);
        String e4 = UnitUtil.e(qpr.c(35.0f), 1, 1);
        String e5 = UnitUtil.e(qpr.c(37.2f), 1, 1);
        String string4 = this.s.getString(R$string.IDS_temperature_fahrenheit_normal, e4, e5);
        String string5 = this.s.getString(R$string.IDS_temperature_fahrenheit_less, e4);
        String string6 = this.s.getString(R$string.IDS_temperature_fahrenheit_more, e5);
        arrayList.add(string5);
        arrayList.add(string4);
        arrayList.add(string6);
        hashMap.put("BOTTOM_LEGEND_TEXT", arrayList);
    }

    private long a(long j) {
        long a2 = jec.a(j);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(a2);
        if (this.e.isDayData()) {
            LogUtil.a("KnitTemperatureLineChartProvider", "getQueryEndTime isDay");
        } else if (this.e.isWeekData()) {
            calendar.add(5, 6);
        } else if (this.e.isMonthData()) {
            calendar.add(2, 1);
            calendar.add(5, -1);
        } else {
            LogUtil.b("KnitTemperatureLineChartProvider", "getQueryEndTime failed, cause unknown data type of dataInfos = ", this.e);
        }
        return calendar.getTimeInMillis();
    }

    private void a(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext().getApplicationContext(), str, hashMap, 0);
    }

    @Override // com.huawei.health.knit.data.KnitHealthDataChangeListener
    public void onDataDeleted() {
        LogUtil.a("KnitTemperatureLineChartProvider", "temperatureDataDeleted");
        this.j = true;
    }

    @Override // com.huawei.health.knit.data.KnitHealthDataChangeListener
    public void onDataInserted(long j) {
        LogUtil.a("KnitTemperatureLineChartProvider", "temperatureDataInserted");
        this.g = true;
    }

    @Override // com.huawei.health.knit.data.KnitHealthDataChangeListener
    public void setSubscribeList(List<Integer> list) {
        this.u = list;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.u, new eah("KnitTemperatureLineChartProvider_" + this.e));
        ObserverManagerUtil.e(this.f10242a);
        ObserverManagerUtil.e(this.r);
        ObserverManagerUtil.e(this.d);
        ObserverManagerUtil.e("REFRESH_TEMPERATURE_CHART");
        ObserverManagerUtil.e(this.t, "data_changed_week_month_resume");
    }

    static class e implements ResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<TemperatureLineChartProvider> f10244a;

        public e(TemperatureLineChartProvider temperatureLineChartProvider) {
            this.f10244a = new WeakReference<>(temperatureLineChartProvider);
        }

        @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
        public void onResult(int i, Object obj) {
            LogUtil.a("KnitTemperatureLineChartProvider", "DataRspCallBack");
            TemperatureLineChartProvider temperatureLineChartProvider = this.f10244a.get();
            if (temperatureLineChartProvider != null) {
                if (temperatureLineChartProvider.o) {
                    return;
                }
                temperatureLineChartProvider.notifyMinorProviders(temperatureLineChartProvider.w);
                return;
            }
            LogUtil.a("KnitTemperatureLineChartProvider", "provider is destroy");
        }
    }

    static class a implements CommonUiBaseResponse {
        private WeakReference<TemperatureLineChartProvider> c;
        private WeakReference<SectionBean> d;

        public a(TemperatureLineChartProvider temperatureLineChartProvider, SectionBean sectionBean) {
            this.c = new WeakReference<>(temperatureLineChartProvider);
            this.d = new WeakReference<>(sectionBean);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            WeakReference<SectionBean> weakReference;
            LogUtil.a("KnitTemperatureLineChartProvider", "CommonUiRspCallBack");
            TemperatureLineChartProvider temperatureLineChartProvider = this.c.get();
            if (temperatureLineChartProvider == null || (weakReference = this.d) == null) {
                LogUtil.a("KnitTemperatureLineChartProvider", "provider is destroy");
                return;
            }
            SectionBean sectionBean = weakReference.get();
            if (sectionBean == null) {
                LogUtil.a("KnitTemperatureLineChartProvider", "mSectionBean is null");
                return;
            }
            List list = obj instanceof List ? (List) obj : null;
            if (!temperatureLineChartProvider.g) {
                if (!temperatureLineChartProvider.j || (temperatureLineChartProvider.l >= temperatureLineChartProvider.k && temperatureLineChartProvider.l <= temperatureLineChartProvider.m)) {
                    if (temperatureLineChartProvider.j) {
                        LogUtil.a("KnitTemperatureLineChartProvider", "on data deleted");
                        temperatureLineChartProvider.j = false;
                        temperatureLineChartProvider.w.e(temperatureLineChartProvider.q > 0 ? temperatureLineChartProvider.q : temperatureLineChartProvider.k);
                        sectionBean.e(temperatureLineChartProvider.w);
                        return;
                    }
                    LogUtil.a("KnitTemperatureLineChartProvider", "on data normal");
                    return;
                }
                LogUtil.a("KnitTemperatureLineChartProvider", "on data delet and date null");
                temperatureLineChartProvider.j = false;
                if (koq.b(list)) {
                    return;
                }
                if (temperatureLineChartProvider.l > temperatureLineChartProvider.w.c()) {
                    temperatureLineChartProvider.w.e(temperatureLineChartProvider.l);
                }
                sectionBean.e(temperatureLineChartProvider.w);
                return;
            }
            LogUtil.a("KnitTemperatureLineChartProvider", "on data insert");
            temperatureLineChartProvider.w.e(temperatureLineChartProvider.l >= 0 ? temperatureLineChartProvider.l : System.currentTimeMillis());
            temperatureLineChartProvider.l = -1L;
            temperatureLineChartProvider.g = false;
            sectionBean.e(temperatureLineChartProvider.w);
        }
    }

    static class d implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<CommonUiBaseResponse> f10243a;
        private WeakReference<TemperatureLineChartProvider> c;

        public d(TemperatureLineChartProvider temperatureLineChartProvider, CommonUiBaseResponse commonUiBaseResponse) {
            this.c = new WeakReference<>(temperatureLineChartProvider);
            this.f10243a = new WeakReference<>(commonUiBaseResponse);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("KnitTemperatureLineChartProvider", "BaseResponseCallback");
            TemperatureLineChartProvider temperatureLineChartProvider = this.c.get();
            if (temperatureLineChartProvider == null) {
                LogUtil.a("KnitTemperatureLineChartProvider", "provider is destroy");
                return;
            }
            List list = obj instanceof List ? (List) obj : null;
            CommonUiBaseResponse commonUiBaseResponse = this.f10243a.get();
            if (commonUiBaseResponse != null) {
                temperatureLineChartProvider.a((List<HiHealthData>) list, commonUiBaseResponse);
            } else {
                LogUtil.b("KnitTemperatureLineChartProvider", "BaseResponseCallback onResponse abort, response is null!");
            }
        }
    }
}
