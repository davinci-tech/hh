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
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.temperature.chart.KnitTemperatureBarChartHolder;
import defpackage.eah;
import defpackage.eaj;
import defpackage.jdl;
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
public class TemperatureBarChartProvider extends MajorProvider<qpg> implements LineChartRangeShowCallback, KnitHealthDataChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Context> f10239a;
    private String b;
    private KnitTemperatureBarChartHolder c;
    private DataInfos d;
    private String e;
    private final List<Integer> f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private long l;
    private boolean m;
    private long n;
    private long o;
    private SectionBean p;
    private String q;
    private long r;
    private Resources s;
    private Observer t;
    private qpg u;
    private List<Integer> w;
    private String x;

    public TemperatureBarChartProvider() {
        ArrayList arrayList = new ArrayList();
        this.f = arrayList;
        this.w = new ArrayList();
        this.u = new qpg();
        this.l = 0L;
        this.o = -1L;
        this.g = false;
        this.i = false;
        this.h = true;
        this.j = true;
        this.m = false;
        this.d = DataInfos.NoDataPlaceHolder;
        this.r = -1L;
        this.x = "TEMPERATURE_MIN_MAX";
        this.t = new Observer() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureBarChartProvider.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                TemperatureBarChartProvider.this.b();
            }
        };
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BODY_TEMPERATURE_SET.value()));
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(arrayList, new eaj(this, "TemperatureBarChartProvider"));
        this.k = Utils.o();
        ObserverManagerUtil.d(this.t, "data_changed_day_resume");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qpg qpgVar) {
        hashMap.put("FIRST_TIME_BIND", Boolean.valueOf(this.j));
        if (!this.j) {
            hashMap.put("START_TIME", Long.valueOf(qpgVar.c()));
        }
        this.j = false;
        hashMap.put("SHOW_DATA_TYPE", this.k ? "SKIN_TEMPERATURE_MIN_MAX" : this.x);
        e();
    }

    @Override // com.huawei.health.knit.section.listener.LineChartRangeShowCallback
    public void onRangeChange(Context context, long j) {
        long d2 = jec.d(j);
        long c = c(d2);
        if (nom.p(j)) {
            if (this.l != d2 || this.h) {
                this.l = d2;
                this.o = c;
                this.u.b(d2, c, this.n);
                qny qnyVar = new qny();
                qnyVar.c(d2);
                qnyVar.b(c);
                LogUtil.a("TemperatureBarChartProvider", "storage.requestData");
                qnyVar.a(this.d, this.u, new b(this));
                if (this.d.isDayData()) {
                    this.h = true;
                } else {
                    this.h = false;
                }
            }
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.p = sectionBean;
        HashMap<String, Object> e2 = e(BaseApplication.getContext());
        sectionBean.a(e2);
        c();
        qpg qpgVar = new qpg();
        qpgVar.e(((Long) e2.get("START_TIME")).longValue());
        this.u = qpgVar;
        sectionBean.e(qpgVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        WeakReference<Context> weakReference = this.f10239a;
        if (weakReference == null || weakReference.get() == null) {
            LogUtil.b("TemperatureBarChartProvider", "context is null");
        } else if (this.m) {
            this.m = false;
            b();
            ObserverManagerUtil.c("data_changed_week_month_resume", new Object[0]);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onStop() {
        super.onStop();
        this.m = true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void c(Context context, SectionBean sectionBean) {
        LogUtil.a("TemperatureBarChartProvider", "begin to loadData");
        if (this.f10239a == null) {
            this.f10239a = new WeakReference<>(context);
        }
        this.p = sectionBean;
        a(new d());
        this.h = true;
    }

    static class b implements ResponseCallback<Object> {
        private final WeakReference<TemperatureBarChartProvider> c;

        b(TemperatureBarChartProvider temperatureBarChartProvider) {
            this.c = new WeakReference<>(temperatureBarChartProvider);
        }

        @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
        public void onResult(int i, Object obj) {
            TemperatureBarChartProvider temperatureBarChartProvider = this.c.get();
            LogUtil.a("TemperatureBarChartProvider", "InnerCallback onResult provider ", temperatureBarChartProvider, " errorCode ", Integer.valueOf(i));
            if (temperatureBarChartProvider == null) {
                return;
            }
            temperatureBarChartProvider.notifyMinorProviders(temperatureBarChartProvider.u);
        }
    }

    static class d implements CommonUiBaseResponse {
        private final WeakReference<TemperatureBarChartProvider> b;

        private d(TemperatureBarChartProvider temperatureBarChartProvider) {
            this.b = new WeakReference<>(temperatureBarChartProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            TemperatureBarChartProvider temperatureBarChartProvider = this.b.get();
            if (temperatureBarChartProvider == null) {
                return;
            }
            List list = obj instanceof List ? (List) obj : null;
            if (!temperatureBarChartProvider.g) {
                if (!temperatureBarChartProvider.i || (temperatureBarChartProvider.n >= temperatureBarChartProvider.l && temperatureBarChartProvider.n <= temperatureBarChartProvider.o)) {
                    if (temperatureBarChartProvider.i) {
                        LogUtil.a("TemperatureBarChartProvider", "on data deleted");
                        temperatureBarChartProvider.i = false;
                        temperatureBarChartProvider.u.e(temperatureBarChartProvider.r > 0 ? temperatureBarChartProvider.r : temperatureBarChartProvider.l);
                        temperatureBarChartProvider.p.e(temperatureBarChartProvider.u);
                        return;
                    }
                    LogUtil.a("TemperatureBarChartProvider", "on data normal");
                    return;
                }
                LogUtil.a("TemperatureBarChartProvider", "on data delet and date null");
                temperatureBarChartProvider.i = false;
                if (koq.b(list)) {
                    return;
                }
                if (temperatureBarChartProvider.n > temperatureBarChartProvider.u.c()) {
                    temperatureBarChartProvider.u.e(temperatureBarChartProvider.n);
                }
                temperatureBarChartProvider.p.e(temperatureBarChartProvider.u);
                return;
            }
            LogUtil.a("TemperatureBarChartProvider", "on data insert");
            temperatureBarChartProvider.u.e(temperatureBarChartProvider.n >= 0 ? temperatureBarChartProvider.n : System.currentTimeMillis());
            temperatureBarChartProvider.n = -1L;
            temperatureBarChartProvider.g = false;
            temperatureBarChartProvider.p.e(temperatureBarChartProvider.u);
        }
    }

    private void e() {
        LogUtil.a("TemperatureBarChartProvider", "onSelect is ", this.x);
        if (this.c != null) {
            LogUtil.a("TemperatureBarChartProvider", "mBarChartHolder.setShowDataType is ", this.x);
            if (this.k) {
                this.x = "SKIN_TEMPERATURE_MIN_MAX";
            }
            this.c.e(this.x);
        }
    }

    public void a(CommonUiBaseResponse commonUiBaseResponse) {
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
        d2.a(iArr, hiDataReadOption, new e(this, commonUiBaseResponse));
    }

    static final class e implements IBaseResponseCallback {
        private final CommonUiBaseResponse b;
        private final WeakReference<TemperatureBarChartProvider> e;

        e(TemperatureBarChartProvider temperatureBarChartProvider, CommonUiBaseResponse commonUiBaseResponse) {
            this.e = new WeakReference<>(temperatureBarChartProvider);
            this.b = commonUiBaseResponse;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            TemperatureBarChartProvider temperatureBarChartProvider = this.e.get();
            if (temperatureBarChartProvider != null) {
                temperatureBarChartProvider.a((List<HiHealthData>) (obj instanceof List ? (List) obj : null), this.b);
            } else {
                LogUtil.h("TemperatureBarChartProvider", "temperatureBarChartProvider is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list, CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("TemperatureBarChartProvider", "handleTemperatureData enter");
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(31);
        if (list != null && list.size() > 0) {
            LogUtil.a("TemperatureBarChartProvider", "handleTemperatureData data.size = ", Integer.valueOf(list.size()));
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
                        LogUtil.h("TemperatureBarChartProvider", "invalid temperature value");
                    }
                }
            }
        } else {
            LogUtil.a("TemperatureBarChartProvider", "handleTemperatureData data is null or empty");
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(0, arrayList);
        }
        LogUtil.a("TemperatureBarChartProvider", "handleTemperatureData cost of time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private void c() {
        LogUtil.a("TemperatureBarChartProvider", "begin to init registerObserver");
        this.b = "KnitHealthData_CardSelected_" + this.d;
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureBarChartProvider.5
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                LogUtil.a("TemperatureBarChartProvider", "begin to notify registerObserver");
                if (objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof String)) {
                    return;
                }
                TemperatureBarChartProvider.this.x = (String) obj;
                LogUtil.a("TemperatureBarChartProvider", "mShowDataType is " + TemperatureBarChartProvider.this.x);
                TemperatureBarChartProvider.this.b();
            }
        }, this.b);
        this.e = "KnitHealthData_Edit_" + this.d;
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureBarChartProvider.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                if (objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Long)) {
                    return;
                }
                TemperatureBarChartProvider.this.n = ((Long) obj).longValue();
                TemperatureBarChartProvider.this.u.e(TemperatureBarChartProvider.this.n);
            }
        }, this.e);
        this.q = "KnitHealthData_MarkView_" + this.d;
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureBarChartProvider.2
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                if (objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Long)) {
                    return;
                }
                TemperatureBarChartProvider.this.r = ((Long) obj).longValue();
            }
        }, this.q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        e();
        this.u.e(this.x);
        long j = this.r;
        if (j <= 0) {
            j = this.l;
        }
        this.u.e(j);
        LogUtil.a("TemperatureBarChartProvider", "sectionBean.setData");
        this.p.e(this.u);
    }

    private HashMap<String, Object> e(Context context) {
        DataInfos dataInfos;
        HashMap<String, Object> hashMap = new HashMap<>();
        Bundle extra = getExtra();
        Serializable serializable = extra != null ? extra.getSerializable("key_bundle_health_line_chart_data_infos") : null;
        if (serializable instanceof DataInfos) {
            dataInfos = (DataInfos) serializable;
        } else {
            dataInfos = DataInfos.NoDataPlaceHolder;
        }
        this.d = dataInfos;
        hashMap.put("DATA_INFO", dataInfos);
        Bundle extra2 = getExtra();
        Object obj = DataInfos.NoDataPlaceHolder;
        long j = 0;
        if (extra2 != null) {
            long j2 = extra2.getLong("key_bundle_health_last_data_time", 0L);
            j = j2 == 0 ? System.currentTimeMillis() : j2;
            Serializable serializable2 = extra2.getSerializable("key_bundle_health_line_chart_data_infos");
            if (serializable2 instanceof DataInfos) {
                obj = (DataInfos) serializable2;
            }
        }
        LogUtil.a("TemperatureBarChartProvider", "first into card latestDataTime " + j);
        this.l = j;
        this.n = j;
        hashMap.put("START_TIME", Long.valueOf(j));
        hashMap.put("DATA_INFO", obj);
        d(context, hashMap);
        hashMap.put("BOTTOM_LEFT_COLOR", Integer.valueOf(ContextCompat.getColor(context, R.color._2131299227_res_0x7f090b9b)));
        hashMap.put("BOTTOM_MID_COLOR", Integer.valueOf(ContextCompat.getColor(context, R.color._2131299225_res_0x7f090b99)));
        hashMap.put("BOTTOM_RIGHT_COLOR", Integer.valueOf(ContextCompat.getColor(context, R.color._2131299226_res_0x7f090b9a)));
        KnitTemperatureBarChartHolder knitTemperatureBarChartHolder = new KnitTemperatureBarChartHolder(context);
        this.c = knitTemperatureBarChartHolder;
        hashMap.put("HEALTH_CHART_HOLDER", knitTemperatureBarChartHolder);
        hashMap.put("RANGE_SHOW_CALL_BACK", this);
        return hashMap;
    }

    private void d(Context context, HashMap<String, Object> hashMap) {
        this.s = context.getResources();
        String e2 = UnitUtil.e(37.20000076293945d, 1, 1);
        String e3 = UnitUtil.e(38.0d, 1, 1);
        String string = this.s.getString(R$string.IDS_temperature_less_than, e2);
        String string2 = this.s.getString(R$string.IDS_temperature_normal_range, e2, e3);
        String string3 = this.s.getString(R$string.IDS_temperature_more_than, e3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(string);
        arrayList.add(string2);
        arrayList.add(string3);
        String e4 = UnitUtil.e(qpr.c(37.2f), 1, 1);
        String e5 = UnitUtil.e(qpr.c(38.0f), 1, 1);
        String string4 = this.s.getString(R$string.IDS_temperature_fahrenheit_less, e4);
        String string5 = this.s.getString(R$string.IDS_temperature_fahrenheit_normal, e4, e5);
        String string6 = this.s.getString(R$string.IDS_temperature_fahrenheit_more, e5);
        arrayList.add(string4);
        arrayList.add(string5);
        arrayList.add(string6);
        hashMap.put("BOTTOM_LEGEND_TEXT", arrayList);
    }

    private long c(long j) {
        long e2 = jdl.e(j);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(e2);
        if (this.d.isDayData()) {
            LogUtil.a("TemperatureBarChartProvider", "getQueryEndTime isDay");
        } else if (this.d.isWeekData()) {
            calendar.add(5, 6);
        } else if (this.d.isMonthData()) {
            calendar.add(2, 1);
            calendar.add(5, -1);
        } else {
            LogUtil.b("TemperatureBarChartProvider", "getQueryEndTime failed, cause unknown data type of dataInfos = ", this.d);
        }
        return calendar.getTimeInMillis();
    }

    @Override // com.huawei.health.knit.data.KnitHealthDataChangeListener
    public void onDataDeleted() {
        this.i = true;
    }

    @Override // com.huawei.health.knit.data.KnitHealthDataChangeListener
    public void onDataInserted(long j) {
        this.g = true;
    }

    @Override // com.huawei.health.knit.data.KnitHealthDataChangeListener
    public void setSubscribeList(List<Integer> list) {
        this.w = list;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.w, new eah("TemperatureBarChartProvider_" + this.d));
        ObserverManagerUtil.e(this.e);
        ObserverManagerUtil.e(this.q);
        ObserverManagerUtil.e(this.b);
        ObserverManagerUtil.e(this.t, "data_changed_day_resume");
    }
}
