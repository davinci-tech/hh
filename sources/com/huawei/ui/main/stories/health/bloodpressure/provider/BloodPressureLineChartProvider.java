package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.content.Context;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.KnitHealthDataChangeListener;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.listener.LineChartRangeShowCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.bloodpressure.chart.BloodPressureChartHolder;
import com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureLineChartProvider;
import defpackage.dpg;
import defpackage.eah;
import defpackage.eaj;
import defpackage.jec;
import defpackage.koq;
import defpackage.nom;
import defpackage.qgx;
import defpackage.qhm;
import defpackage.qkg;
import defpackage.qrc;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class BloodPressureLineChartProvider extends MajorProvider<qhm> implements LineChartRangeShowCallback, KnitHealthDataChangeListener {

    /* renamed from: a, reason: collision with root package name */
    protected SectionBean f10154a;
    private final List<Integer> b;
    private String e;
    private List<Integer> k;
    private long l;
    private long n;
    private String o;
    private qhm d = new qhm();
    private DataInfos c = DataInfos.NoDataPlaceHolder;
    private long m = 0;
    private long f = -1;
    private boolean j = true;
    private boolean i = false;
    private boolean g = false;
    private boolean h = true;

    static final class d {

        /* renamed from: a, reason: collision with root package name */
        long f10156a;
        long c;
        long d;
        long e;
    }

    static /* synthetic */ long d(BloodPressureLineChartProvider bloodPressureLineChartProvider) {
        long j = bloodPressureLineChartProvider.n - 1;
        bloodPressureLineChartProvider.n = j;
        return j;
    }

    public BloodPressureLineChartProvider() {
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        this.k = new ArrayList();
        this.l = -1L;
        this.n = -1L;
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()));
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(arrayList, new eaj(this, "BloodPressureLineChartProvider"));
    }

    private HashMap<String, Object> a(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Bundle extra = getExtra();
        Serializable serializable = extra != null ? extra.getSerializable("key_bundle_health_line_chart_data_infos") : null;
        DataInfos dataInfos = serializable instanceof DataInfos ? (DataInfos) serializable : DataInfos.NoDataPlaceHolder;
        this.c = dataInfos;
        hashMap.put("DATA_INFO", dataInfos);
        long j = extra != null ? extra.getLong("key_marker_view_time") : 0L;
        if (j != 0) {
            hashMap.put("key_marker_view_time", Long.valueOf(j));
        }
        List<String> e = e(context);
        List<String> d2 = d(context);
        hashMap.put("CURSOR_UP_AVERAGE_TEXT", e);
        hashMap.put("CURSOR_UP_AVERAGE_UNIT", context.getString(R$string.IDS_device_measure_pressure_value_unit));
        hashMap.put("CURSOR_DOWN_AVERAGE_TEXT", d2);
        hashMap.put("CURSOR_DOWN_AVERAGE_UNIT", context.getResources().getQuantityString(R.plurals._2130903084_res_0x7f03002c, 3).replace("%d", ""));
        c(context, hashMap);
        return hashMap;
    }

    private void c(Context context, HashMap<String, Object> hashMap) {
        hashMap.put("LEFT_BOTTOM_LEGEND_SIGN", Integer.valueOf(ContextCompat.getColor(context, R.color._2131297275_res_0x7f0903fb)));
        hashMap.put("LEFT_BOTTOM_LEGEND_TEXT", context.getString(R$string.IDS_hw_high_pressure));
        hashMap.put("RIGHT_BOTTOM_LEGEND_SIGN", Integer.valueOf(ContextCompat.getColor(context, R.color._2131297283_res_0x7f090403)));
        hashMap.put("RIGHT_BOTTOM_LEGEND_TEXT", context.getString(R$string.IDS_hw_low_pressure));
        Bundle extra = getExtra();
        DataInfos dataInfos = DataInfos.NoDataPlaceHolder;
        long j = 0;
        if (extra != null) {
            long j2 = extra.getLong("key_bundle_health_last_data_time", 0L);
            j = j2 == 0 ? System.currentTimeMillis() : j2;
            dataInfos = (DataInfos) extra.getSerializable("key_bundle_health_line_chart_data_infos");
        }
        this.c = dataInfos;
        this.d.b(dataInfos);
        this.m = j;
        hashMap.put("START_TIME", Long.valueOf(j));
        hashMap.put("DATA_INFO", dataInfos);
        hashMap.put("HEALTH_CHART_HOLDER", new BloodPressureChartHolder(context));
        hashMap.put("RANGE_SHOW_CALL_BACK", this);
    }

    private List<String> d(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(context.getString(R$string.IDS_hw_health_show_pulse_heart_bmp));
        arrayList.add(context.getString(R$string.IDS_hw_ave_pulse));
        return arrayList;
    }

    private List<String> e(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(context.getString(R$string.IDS_hw_show_main_home_page_bloodpressure));
        arrayList.add(context.getString(R$string.IDS_hw_ave_blood_pressure));
        return arrayList;
    }

    private void a() {
        this.e = "KnitHealthData_Edit_" + this.c;
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureLineChartProvider.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                if (objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Long)) {
                    return;
                }
                BloodPressureLineChartProvider.this.l = ((Long) obj).longValue();
            }
        }, this.e);
        this.o = "KnitHealthData_MarkView_" + this.c;
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureLineChartProvider.2
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (objArr == null || objArr.length <= 0) {
                    return;
                }
                Object obj = objArr[0];
                if (obj instanceof Long) {
                    BloodPressureLineChartProvider.this.n = ((Long) obj).longValue();
                    if (BloodPressureLineChartProvider.this.f <= 0 || BloodPressureLineChartProvider.this.n <= BloodPressureLineChartProvider.this.f) {
                        return;
                    }
                    BloodPressureLineChartProvider.d(BloodPressureLineChartProvider.this);
                }
            }
        }, this.o);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.f10154a = sectionBean;
        HashMap<String, Object> a2 = a(BaseApplication.getContext());
        sectionBean.a(a2);
        a();
        qhm qhmVar = new qhm();
        qhmVar.c(((Long) a2.get("START_TIME")).longValue());
        sectionBean.e(qhmVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        qgx qgxVar = new qgx();
        long d2 = jec.d(this.m);
        qgxVar.a(true, d2, a(d2), (IBaseResponseCallback) new b(this, 0, sectionBean, null));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qhm qhmVar) {
        hashMap.put("FIRST_TIME_BIND", Boolean.valueOf(this.j));
        if (!this.j) {
            hashMap.put("START_TIME", Long.valueOf(qhmVar.i()));
        }
        this.j = false;
    }

    private long a(long j) {
        long a2 = jec.a(j);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(a2);
        DataInfos dataInfos = this.c;
        if (dataInfos == null) {
            LogUtil.h("BloodPressureLineChartProvider", "mDataInfos is null");
            return calendar.getTimeInMillis();
        }
        if (dataInfos.isDayData()) {
            LogUtil.a("BloodPressureLineChartProvider", "getQueryEndTime isDay");
        } else if (this.c.isWeekData()) {
            calendar.add(5, 6);
        } else if (this.c.isMonthData()) {
            calendar.add(2, 1);
            calendar.add(5, -1);
        } else {
            LogUtil.b("BloodPressureLineChartProvider", "getQueryEndTime failed, cause unknown data type of dataInfos = ", this.c);
        }
        return calendar.getTimeInMillis();
    }

    @Override // com.huawei.health.knit.section.listener.LineChartRangeShowCallback
    public void onRangeChange(Context context, long j) {
        long i;
        long g;
        long d2 = jec.d(j);
        long a2 = a(d2);
        if (nom.p(j)) {
            if (this.m != d2 || this.h) {
                d dVar = new d();
                dVar.e = d2;
                dVar.d = a2;
                if (b()) {
                    if (this.c == DataInfos.BloodPressureWeekDetail) {
                        i = dpg.h(d2);
                    } else {
                        i = dpg.i(d2);
                    }
                    dVar.e = i;
                    if (this.c == DataInfos.BloodPressureWeekDetail) {
                        g = dpg.f(d2).longValue();
                    } else {
                        g = dpg.g(d2);
                    }
                    dVar.d = g;
                }
                dVar.f10156a = d2;
                dVar.c = a2;
                new qgx().a(true, dVar.e, dVar.c, (IBaseResponseCallback) new b(this, 1, null, dVar));
                this.m = d2;
                this.f = a2;
                this.h = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Integer, List<qkg>> b(List<qkg> list, d dVar) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        hashMap.put(0, arrayList2);
        hashMap.put(1, arrayList);
        if (koq.b(list)) {
            return hashMap;
        }
        for (qkg qkgVar : list) {
            long h = qkgVar.h();
            if (h >= dVar.e && h <= dVar.d) {
                arrayList.add(qkgVar);
            }
            if (h >= dVar.f10156a && h <= dVar.c) {
                arrayList2.add(qkgVar);
            }
        }
        return hashMap;
    }

    private boolean b() {
        return this.c == DataInfos.BloodPressureMonthDetail || this.c == DataInfos.BloodPressureWeekDetail;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Map<Integer, List<qkg>> map, d dVar) {
        this.d.b(map.get(1), dVar.e, dVar.d);
        this.d.e(map.get(0), dVar.f10156a, dVar.c);
        notifyMinorProviders(this.d);
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
        this.k = list;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.k, new eah("BloodPressureLineChartProvider_" + this.c));
        ObserverManagerUtil.e(this.e);
        ObserverManagerUtil.e(this.o);
    }

    public static final class b implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<BloodPressureLineChartProvider> f10155a;
        private int c;
        private WeakReference<SectionBean> d;
        private d e;

        public b(BloodPressureLineChartProvider bloodPressureLineChartProvider, int i, SectionBean sectionBean, d dVar) {
            this.f10155a = new WeakReference<>(bloodPressureLineChartProvider);
            this.c = i;
            this.d = new WeakReference<>(sectionBean);
            this.e = dVar;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BloodPressureLineChartProvider bloodPressureLineChartProvider = this.f10155a.get();
            SectionBean sectionBean = this.d.get();
            if (bloodPressureLineChartProvider == null) {
                LogUtil.b("BloodPressureLineChartProvider", "handle mReadType ", Integer.valueOf(this.c), ", failed! cause provider has gone!");
                return;
            }
            int i2 = this.c;
            if (i2 == 0) {
                if (sectionBean == null) {
                    LogUtil.a("BloodPressureLineChartProvider", "sectionBean is null");
                    return;
                } else {
                    a(bloodPressureLineChartProvider, sectionBean, obj);
                    return;
                }
            }
            if (i2 == 1) {
                d(bloodPressureLineChartProvider, obj);
            } else {
                LogUtil.b("BloodPressureLineChartProvider", "handle mReadType ", Integer.valueOf(i2), " is unknown!");
            }
        }

        private void d(BloodPressureLineChartProvider bloodPressureLineChartProvider, Object obj) {
            bloodPressureLineChartProvider.b((Map<Integer, List<qkg>>) bloodPressureLineChartProvider.b((List<qkg>) (obj instanceof List ? (List) obj : null), this.e), this.e);
        }

        private void a(BloodPressureLineChartProvider bloodPressureLineChartProvider, final SectionBean sectionBean, Object obj) {
            List list = obj instanceof List ? (List) obj : null;
            final qhm qhmVar = new qhm();
            if (bloodPressureLineChartProvider.g) {
                qhmVar.c(bloodPressureLineChartProvider.l >= 0 ? bloodPressureLineChartProvider.l : System.currentTimeMillis());
                bloodPressureLineChartProvider.l = -1L;
                bloodPressureLineChartProvider.g = false;
                sectionBean.e(qhmVar);
            } else if (!koq.b(list) || !bloodPressureLineChartProvider.i) {
                if (bloodPressureLineChartProvider.i) {
                    long h = qrc.d(list, bloodPressureLineChartProvider.n > 0 ? bloodPressureLineChartProvider.n : bloodPressureLineChartProvider.m).h();
                    bloodPressureLineChartProvider.i = false;
                    qhmVar.c(h);
                    sectionBean.e(qhmVar);
                }
            } else {
                bloodPressureLineChartProvider.i = false;
                qgx.b(new IBaseResponseCallback() { // from class: qhp
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj2) {
                        BloodPressureLineChartProvider.b.b(qhm.this, sectionBean, i, obj2);
                    }
                });
            }
            bloodPressureLineChartProvider.h = true;
        }

        public static /* synthetic */ void b(qhm qhmVar, SectionBean sectionBean, int i, Object obj) {
            if (obj instanceof List) {
                List list = (List) obj;
                if (koq.b(list)) {
                    return;
                }
                qhmVar.c(((HiHealthData) list.get(0)).getStartTime());
                sectionBean.e(qhmVar);
            }
        }
    }
}
