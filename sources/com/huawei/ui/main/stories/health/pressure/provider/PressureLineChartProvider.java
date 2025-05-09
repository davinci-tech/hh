package com.huawei.ui.main.stories.health.pressure.provider;

import android.content.Context;
import android.os.Bundle;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.knit.data.KnitHealthDataChangeListener;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.listener.BarChartRangeShowCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.main.R$string;
import defpackage.cvs;
import defpackage.eah;
import defpackage.eaj;
import defpackage.jec;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.koq;
import defpackage.nom;
import defpackage.psm;
import defpackage.pwr;
import defpackage.qmg;
import defpackage.qml;
import defpackage.sdg;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class PressureLineChartProvider extends MajorProvider<qml> implements BarChartRangeShowCallback, KnitHealthDataChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private final List<Integer> f10214a;
    private pwr c;
    private SectionBean g;
    private List<Integer> h;
    private qml j = new qml();
    private DataInfos d = DataInfos.NoDataPlaceHolder;
    private long f = 0;
    private boolean b = true;
    private boolean i = true;
    private boolean e = false;

    @Override // com.huawei.health.knit.data.KnitHealthDataChangeListener
    public void onDataInserted(long j) {
    }

    public PressureLineChartProvider() {
        ArrayList arrayList = new ArrayList();
        this.f10214a = arrayList;
        this.h = new ArrayList();
        arrayList.add(14);
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(arrayList, new eaj(this, "PressureLineChartProvider"));
    }

    private HashMap<String, Object> d(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Bundle extra = getExtra();
        Serializable serializable = extra != null ? extra.getSerializable("key_bundle_health_line_chart_data_infos") : null;
        DataInfos dataInfos = serializable instanceof DataInfos ? (DataInfos) serializable : DataInfos.NoDataPlaceHolder;
        this.d = dataInfos;
        hashMap.put("DATA_INFO", dataInfos);
        ArrayList arrayList = new ArrayList();
        arrayList.add(context.getString(R$string.IDS_hw_pressure_day_average));
        arrayList.add(context.getString(R$string.IDS_hw_pressure_month_average));
        hashMap.put("CURSOR_UP_AVERAGE_TEXT", arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(context.getString(R$string.IDS_hw_pressure_relaxed));
        arrayList2.add(context.getString(R$string.IDS_hw_pressure_normal));
        arrayList2.add(context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_item_3));
        arrayList2.add(context.getString(R$string.IDS_hw_pressure_highly));
        hashMap.put("CURSOR_UP_AVERAGE_STATUS", arrayList2);
        hashMap.put("LEGEND_ONE_TEXT", context.getResources().getString(R$string.IDS_hw_pressure_relaxed));
        hashMap.put("LEGEND_TWO_TEXT", context.getResources().getString(R$string.IDS_hw_pressure_normal));
        hashMap.put("LEGEND_THREE_TEXT", context.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_item_3));
        hashMap.put("LEGEND_FOUR_TEXT", context.getResources().getString(R$string.IDS_hw_pressure_highly));
        Bundle extra2 = getExtra();
        DataInfos dataInfos2 = DataInfos.NoDataPlaceHolder;
        long j = 0;
        if (extra2 != null) {
            j = extra2.getLong("key_bundle_health_last_data_time", 0L);
            LogUtil.a("PressureLineChartProvider", "latestDataTime extra: ", String.valueOf(j));
            dataInfos2 = (DataInfos) extra2.getSerializable("key_bundle_health_line_chart_data_infos");
        }
        this.d = dataInfos2;
        this.f = j;
        hashMap.put("START_TIME", Long.valueOf(j));
        hashMap.put("DATA_INFO", dataInfos2);
        hashMap.put("HEALTH_CHART_HOLDER", new qmg(context, this.d));
        hashMap.put("RANGE_SHOW_CALL_BACK", this);
        return hashMap;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("PressureLineChartProvider", "loadDefaultData : ", "START_TIME");
        this.g = sectionBean;
        HashMap<String, Object> d = d(BaseApplication.getContext());
        sectionBean.a(d);
        qml qmlVar = new qml();
        qmlVar.a(((Long) d.get("START_TIME")).longValue());
        sectionBean.e(qmlVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.g = sectionBean;
        this.c = new pwr();
        LogUtil.a("PressureLineChartProvider", "mDataInfos: ", this.d);
        a();
        DataInfos dataInfos = this.d;
        if (dataInfos == null || !dataInfos.isDayData()) {
            return;
        }
        a(context);
    }

    private void a() {
        long o = psm.e().o();
        LogUtil.a("PressureLineChartProvider", "refreshUi lastStorageTime: ", Long.valueOf(o));
        if (o != 0) {
            this.j.a(o);
            this.g.e(this.j);
            this.i = true;
        }
        if (this.e) {
            this.e = false;
            psm.e().b(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.pressure.provider.PressureLineChartProvider.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("PressureLineChartProvider", "errorCode: ", Integer.valueOf(i));
                    if (obj instanceof List) {
                        List list = (List) obj;
                        if (koq.b(list)) {
                            return;
                        }
                        PressureLineChartProvider.this.j.a(((HiHealthData) list.get(0)).getStartTime());
                        PressureLineChartProvider.this.g.e(PressureLineChartProvider.this.j);
                        PressureLineChartProvider pressureLineChartProvider = PressureLineChartProvider.this;
                        pressureLineChartProvider.notifyMinorProviders(pressureLineChartProvider.j);
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qml qmlVar) {
        hashMap.put("FIRST_TIME_BIND", Boolean.valueOf(this.b));
        if (!this.b) {
            hashMap.put("START_TIME", Long.valueOf(qmlVar.b()));
        }
        hashMap.put("TOAST_TEXT", qmlVar.g());
        this.b = false;
    }

    @Override // com.huawei.health.knit.section.listener.BarChartRangeShowCallback
    public void onRangeChange(int i, int i2) {
        long millis = TimeUnit.MINUTES.toMillis(i);
        long millis2 = TimeUnit.MINUTES.toMillis(i2);
        long d = jec.d(millis);
        LogUtil.a("PressureLineChartProvider", "queryTimeInterval : ", Long.valueOf(millis), " -- ", Long.valueOf(millis2));
        if (nom.p(millis)) {
            if (this.f != d || this.i) {
                this.j.b(millis, millis2);
                this.j.c(this.d);
                this.f = d;
                d();
                this.i = false;
            }
        }
    }

    @Override // com.huawei.health.knit.data.KnitHealthDataChangeListener
    public void onDataDeleted() {
        LogUtil.a("PressureLineChartProvider", "onDataDeleted");
        this.e = true;
    }

    @Override // com.huawei.health.knit.data.KnitHealthDataChangeListener
    public void setSubscribeList(List<Integer> list) {
        this.h = list;
    }

    static class e extends BaseCallback<PressureLineChartProvider> {
        private final int c;

        e(PressureLineChartProvider pressureLineChartProvider, int i) {
            super(pressureLineChartProvider);
            this.c = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.huawei.ui.main.stories.health.pressure.provider.BaseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onCall(PressureLineChartProvider pressureLineChartProvider, int i, Object obj) {
            int i2 = this.c;
            if (i2 == 2) {
                if (pressureLineChartProvider == null) {
                    return;
                }
                qml qmlVar = pressureLineChartProvider.j;
                boolean z = false;
                if (obj != null && (obj instanceof List) && ((List) obj).size() > 0) {
                    z = true;
                }
                qmlVar.d(z);
                pressureLineChartProvider.notifyMinorProviders(qmlVar);
                return;
            }
            if (i2 != 3) {
                LogUtil.b("PressureLineChartProvider", "error type");
                return;
            }
            if (i == -1) {
                pressureLineChartProvider.c();
                return;
            }
            if (i == 0 && (obj instanceof String)) {
                if ("false".equals((String) obj)) {
                    pressureLineChartProvider.c();
                    return;
                }
                return;
            }
            LogUtil.b("PressureLineChartProvider", "checkWearPressSwitch errorCode is other.");
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.h, new eah("PressureLineChartProvider_" + this.d));
    }

    private void d() {
        if (this.d.isDayData()) {
            this.c.a(jec.a(new Date(this.j.f())), 1, new e(this, 2));
            return;
        }
        if (this.d.isWeekData()) {
            this.c.e(this.j.f() / 1000, this.j.c() / 1000, 2, new e(this, 2));
            return;
        }
        if (this.d.isMonthData()) {
            this.c.e(this.j.f() / 1000, this.j.c() / 1000, 3, new e(this, 2));
        } else if (this.d.isYearData()) {
            this.c.e(this.j.f() / 1000, this.j.c() / 1000, 4, new e(this, 2));
        } else {
            LogUtil.b("PressureLineChartProvider", "requestPressureData failed, cause unknown data type of dataInfos = ", this.d);
        }
    }

    private void a(Context context) {
        DeviceInfo a2 = jpt.a("PressureLineChartProvider");
        boolean isLogined = LoginInit.getInstance(context).getIsLogined();
        LogUtil.a("PressureLineChartProvider", "onResume ", "isLogin = ", Boolean.valueOf(isLogined));
        if (isLogined && a2 != null && a2.getDeviceConnectState() == 2) {
            DeviceCapability d = cvs.d();
            if (d == null) {
                LogUtil.a("PressureLineChartProvider", "capability is null");
                return;
            }
            boolean isSupportPressAutoMonitor = d.isSupportPressAutoMonitor();
            LogUtil.c("PressureLineChartProvider", "onResume ", "isSupportPressAutoMonitor = ", Boolean.valueOf(isSupportPressAutoMonitor));
            if (isSupportPressAutoMonitor) {
                jqi.a().getSwitchSetting("press_auto_monitor_switch_status", new e(this, 3));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.j.c(BaseApplication.getContext().getString(R$string.IDS_hw_open_auto_pressure_detector_content, sdg.a()));
        SectionBean sectionBean = this.g;
        if (sectionBean != null) {
            sectionBean.e(this.j);
        }
    }
}
