package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider;
import defpackage.edo;
import defpackage.edp;
import defpackage.edt;
import defpackage.edx;
import defpackage.fda;
import defpackage.fdp;
import defpackage.fdq;
import defpackage.gge;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.pqg;
import defpackage.qmx;
import defpackage.scn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;

/* loaded from: classes9.dex */
public abstract class SleepBaseExpandListProvider extends MinorProvider<fdp> {
    protected static final int DEFAULT_SHOW_TYPE = 1;
    private int mAbnormalCode;
    protected int mArrowRes;
    private Observer mBiObserver;
    protected boolean mIsActive;
    protected String mRequestDay;
    protected SectionBean mSectionBean;
    protected WeakReference<KnitSleepDetailActivity> mWeakRef;
    protected edt mExpandReportListBean = new edt();
    protected fdp mSleepViewData = new fdp(SleepViewConstants.ViewTypeEnum.DAY);
    private String mAbnormalData = null;
    protected boolean mIsOpen = false;
    private Map<String, Integer> mOrder = new HashMap();
    private String mReferenceLine = Constants.LINK;

    protected abstract String getTag();

    protected int getType() {
        return 1;
    }

    protected abstract void handleTruSleepData(CharSequence[] charSequenceArr, c cVar);

    protected abstract boolean isEffectiveDate();

    protected abstract void setSectionBeanData(SectionBean sectionBean, fdp fdpVar);

    protected abstract void updateDataUi();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (fdp) obj);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.mIsActive;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return getTag();
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        registerMoreAnalysisObserver();
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        ReleaseLogUtil.e(getTag(), "loadData");
        this.mWeakRef = new WeakReference<>((KnitSleepDetailActivity) context);
        this.mSectionBean = sectionBean;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        setSectionBeanData(sectionBean, fdpVar);
    }

    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a(getTag(), "parseParams");
        processData(fdpVar, hashMap);
    }

    private void processData(fdp fdpVar, HashMap<String, Object> hashMap) {
        LogUtil.a(getTag(), "processData");
        this.mSleepViewData = fdpVar;
        reset(hashMap);
        if (!init()) {
            LogUtil.h(getTag(), "init fail");
            this.mIsActive = false;
        } else {
            updateDataUi();
            LogUtil.a(getTag(), "bean is ", this.mExpandReportListBean);
            hashMap.put("EXPAND_LIST_BEAN", this.mExpandReportListBean);
        }
    }

    public void sortBeanList() {
        if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.b(getTag(), "cannot showSleepManagement");
            return;
        }
        ArrayList arrayList = new ArrayList();
        initAbnormalStatus(arrayList);
        for (edp edpVar : this.mExpandReportListBean.d()) {
            if (edpVar instanceof edo) {
                if (arrayList.contains(((edo) edpVar).i())) {
                    edpVar.a(true);
                }
            } else if ((edpVar instanceof edx) && arrayList.contains(((edx) edpVar).j())) {
                edpVar.a(true);
            }
        }
        edp[] edpVarArr = (edp[]) this.mExpandReportListBean.d().toArray(new edp[0]);
        Arrays.sort(edpVarArr, new Comparator() { // from class: pmf
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Integer.compare(((edp) obj).c(), ((edp) obj2).c());
                return compare;
            }
        });
        Arrays.sort(edpVarArr, new Comparator() { // from class: pmh
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Boolean.compare(((edp) obj2).b(), ((edp) obj).b());
                return compare;
            }
        });
        Arrays.sort(edpVarArr, new Comparator<edp>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider.3
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(edp edpVar2, edp edpVar3) {
                return (edpVar2.c() == SleepBaseExpandListProvider.this.mExpandReportListBean.g() && edpVar2.b()) ? -1 : 0;
            }
        });
        this.mExpandReportListBean.d().clear();
        for (edp edpVar2 : edpVarArr) {
            this.mExpandReportListBean.d().add(edpVar2);
        }
        LogUtil.a(getTag(), "mTopAbnormal is ", Integer.valueOf(this.mExpandReportListBean.g()), "after sort list is ", this.mExpandReportListBean.d().toString());
    }

    private void initAbnormalStatus(List<String> list) {
        list.add(nsf.h(R$string.IDS_details_sleep_grade_late));
        list.add(nsf.h(R$string.IDS_details_sleep_grade_high));
        list.add(nsf.h(R$string.IDS_details_sleep_grade_early));
        list.add(nsf.h(R$string.IDS_details_sleep_grade_low));
        list.add(nsf.h(R$string.IDS_hwh_runningstyle_longer));
        list.add(nsf.h(R$string.IDS_hwh_runningstyle_shorter));
        list.add(nsf.h(R$string.IDS_physiological_index_high));
        list.add(nsf.h(R$string.IDS_physiological_index_low));
    }

    public void setProviderActive(fdp fdpVar) {
        if (fdpVar.e() != SleepViewConstants.ViewTypeEnum.YEAR && fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL && (fdpVar.d().s() || fdpVar.d().q())) {
            LogUtil.a(getTag(), "isManualOnlyBedTime");
            this.mIsActive = false;
        } else {
            this.mIsActive = fdpVar.m();
        }
    }

    private void reset(HashMap<String, Object> hashMap) {
        hashMap.clear();
        this.mExpandReportListBean.a();
    }

    private boolean init() {
        this.mReferenceLine = scn.a(BaseApplication.getContext());
        if (getExtra() != null) {
            if (!isEffectiveDate()) {
                LogUtil.h(getTag(), "sleep day is null");
                return false;
            }
            this.mRequestDay = DateFormatUtil.b(this.mSleepViewData.g(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.mArrowRes = R.drawable._2131427841_res_0x7f0b0201;
        } else {
            this.mArrowRes = R.drawable._2131427842_res_0x7f0b0202;
        }
        setSleepManagementData(this.mSleepViewData);
        return true;
    }

    private void setSleepManagementData(fdp fdpVar) {
        if (fdpVar.l().containsKey("MGMT_DAILY_SLEEP_PROCESS") && (fdpVar.l().get("MGMT_DAILY_SLEEP_PROCESS") instanceof fda)) {
            LogUtil.a(getTag(), "update abnormal");
            this.mAbnormalCode = ((fda) fdpVar.l().get("MGMT_DAILY_SLEEP_PROCESS")).h();
            LogUtil.a(getTag(), "abnormalCode:", Integer.valueOf(this.mAbnormalCode));
            if (this.mAbnormalCode == 0) {
                this.mExpandReportListBean.e(-1);
                return;
            } else {
                this.mOrder = qmx.f16490a;
                this.mExpandReportListBean.e(qmx.b.get(Integer.valueOf(this.mAbnormalCode)).intValue());
                LogUtil.a(getTag(), "get abnormal data is ", qmx.b.get(Integer.valueOf(this.mAbnormalCode)));
            }
        }
        if (fdpVar.l().containsKey("OPEN_STATUS") && (fdpVar.l().get("OPEN_STATUS") instanceof Boolean)) {
            this.mIsOpen = ((Boolean) fdpVar.l().get("OPEN_STATUS")).booleanValue();
        }
    }

    private void registerMoreAnalysisObserver() {
        if (this.mBiObserver != null) {
            return;
        }
        LogUtil.a(getTag(), "init observer");
        Observer observer = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if ("SLEEP_MORE_ANALYSIS".equals(str)) {
                    c(objArr);
                } else {
                    LogUtil.h(SleepBaseExpandListProvider.this.getTag(), "other labels");
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Boolean] */
            private void c(Object[] objArr) {
                String str;
                Map hashMap = new HashMap(16);
                ArrayList arrayList = new ArrayList();
                SleepBaseExpandListProvider.this.setBiData(arrayList);
                if (objArr.length == 1 && (objArr[0] instanceof String) && AnalyticsValue.HEALTH_SLEEP_CORE_SLEEP_TIME_2030111.value().equals((String) objArr[0]) && SleepBaseExpandListProvider.this.mExpandReportListBean != null) {
                    hashMap.put("click", 1);
                    if (!TextUtils.isEmpty(SleepBaseExpandListProvider.this.mAbnormalData)) {
                        str = SleepBaseExpandListProvider.this.mAbnormalData;
                    } else {
                        str = false;
                    }
                    hashMap.put("alertData", str);
                    if (koq.b(arrayList)) {
                        arrayList = false;
                    }
                    hashMap.put("abnormalData", arrayList);
                    LogUtil.a(SleepBaseExpandListProvider.this.getTag(), "HEALTH_SLEEP_CORE_SLEEP_TIME_2030111 bi map: ", hashMap.toString());
                } else if (objArr.length == 2 && (objArr[0] instanceof String) && AnalyticsValue.SLEEP_MONITORING_RESULTS_21300045.value().equals((String) objArr[0])) {
                    Object obj = objArr[1];
                    if (obj instanceof Map) {
                        hashMap = (Map) obj;
                        boolean add = TextUtils.isEmpty(SleepBaseExpandListProvider.this.mAbnormalData) ? false : arrayList.add(SleepBaseExpandListProvider.this.mAbnormalData);
                        if (koq.b(arrayList)) {
                            arrayList = false;
                        }
                        hashMap.put("abnormalData", arrayList);
                        LogUtil.a(SleepBaseExpandListProvider.this.getTag(), "SLEEP_MONITORING_RESULTS_21300045 bi map is:", hashMap.toString(), " hasWarningData :", Boolean.valueOf(add));
                    }
                }
                gge.e((String) objArr[0], hashMap);
            }
        };
        this.mBiObserver = observer;
        ObserverManagerUtil.d(observer, "SLEEP_MORE_ANALYSIS");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBiData(List<String> list) {
        if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.b(getTag(), "cannot showSleepManagement");
            return;
        }
        this.mAbnormalData = null;
        for (edp edpVar : this.mExpandReportListBean.d()) {
            if (edpVar.b() && edpVar.c() != this.mExpandReportListBean.g()) {
                list.add(getMappingName(edpVar.c()));
            } else if (edpVar.c() == this.mExpandReportListBean.g()) {
                this.mAbnormalData = getMappingName(edpVar.c());
            }
        }
    }

    private String getMappingName(int i) {
        if (this.mOrder.isEmpty()) {
            this.mOrder = qmx.f16490a;
        }
        for (Map.Entry<String, Integer> entry : this.mOrder.entrySet()) {
            if (entry.getValue().intValue() == i) {
                return entry.getKey();
            }
        }
        return "";
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        this.mExpandReportListBean.a();
        ObserverManagerUtil.e("SLEEP_MORE_ANALYSIS");
        ObserverManagerUtil.e(ObserveLabels.HEART_RATE_SWITCH_STATUS);
        ObserverManagerUtil.e(ObserveLabels.SPO2_MONITOR_SWITCH_STATUS);
    }

    public String handleRtlPercent(String str, int i) {
        StringBuilder sb = new StringBuilder(str);
        int indexOf = sb.indexOf("%");
        int i2 = i < 100 ? 2 : 3;
        if (indexOf == -1 || indexOf < i2) {
            return str;
        }
        sb.deleteCharAt(indexOf);
        sb.insert(indexOf - i2, "%");
        return sb.toString();
    }

    public void setSleepEfficientClickEvent(Context context, int i, int i2) {
        LogUtil.a(getTag(), "setSleepEfficientClickEvent");
        SleepRateActivity.d(context, "TYPE_SLEEP_EFFICIENCY", i, i2);
    }

    public String getItemTitle(int i, String str) {
        return nsf.b(com.huawei.ui.main.R$string.IDS_sleep_title_item_two_parts, nsf.h(i), str);
    }

    public void setBedTimeClickEvent(int i, int i2, int i3) {
        LogUtil.a(getTag(), "setBedTimeClickEvent");
        setNightSleepTimeClickEvent("TYPE_SLEEP_BED_TIME", i, i2, i3);
    }

    public void setNightSleepTimeClickEvent(String str, int i, int i2, int i3) {
        LogUtil.a(getTag(), "setNightSleepClickEvent");
        WeakReference<KnitSleepDetailActivity> weakReference = this.mWeakRef;
        if (weakReference == null || weakReference.get() == null) {
            LogUtil.a(getTag(), "setNightSleepClickEvent, mWeakRef is invalid");
        } else {
            SleepDurationActivity.c(this.mWeakRef.get(), str, i, i2, i3, this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE);
        }
    }

    public String getFormatTime(int i, int i2) {
        try {
            String a2 = nsf.a(R.plurals._2130903200_res_0x7f0300a0, i2, UnitUtil.e(i2, 1, 0));
            StringBuilder sb = new StringBuilder();
            if (i == 0) {
                sb.append(a2);
            } else {
                sb.append(BaseApplication.getContext().getString(com.huawei.ui.main.R$string.IDS_two_parts, nsf.a(R.plurals._2130903199_res_0x7f03009f, i, UnitUtil.e(i, 1, 0)), a2));
            }
            return sb.toString();
        } catch (MissingFormatArgumentException unused) {
            LogUtil.h(getTag(), "getFormatTime, MissingFormatArgumentException");
            return "";
        }
    }

    public void setAvgSleepTimeClickEvent(int i, int i2, int i3) {
        LogUtil.a(getTag(), "setSleepTimeClickEvent");
        setNightSleepTimeClickEvent("TYPE_SLEEP_TIME", i, i2, i3);
    }

    public int getSleepLatency() {
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            return this.mSleepViewData.f().i();
        }
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            return this.mSleepViewData.d().i();
        }
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            return this.mSleepViewData.j().i();
        }
        return -1;
    }

    public edo getSleepLatencyBean(int i, int i2) {
        if (i < 0) {
            LogUtil.h(getTag(), "not SupportSleepManagement or sleepLatency is error value, not show.");
            return new edo();
        }
        ReleaseLogUtil.e(getTag(), "getSleepLatencyBean sleepLatency ", Integer.valueOf(i), " source ", this.mSleepViewData.i());
        String b = nsf.b(com.huawei.ui.main.R$string.IDS_sleep_referece_title_smaller_string, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, 30, UnitUtil.e(30.0d, 1, 0)));
        final int o = scn.o(i);
        final int i3 = i / 60;
        final int i4 = i % 60;
        return new edo(getItemTitle(i2, getFormatTime(i3, i4)), b, pqg.c(o, "TYPE_SLEEP_LATENCY_TIME"), pqg.d(o), this.mArrowRes, new View.OnClickListener() { // from class: pmk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m817x44ba222c(i3, i4, o, view);
            }
        });
    }

    /* renamed from: lambda$getSleepLatencyBean$2$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m817x44ba222c(int i, int i2, int i3, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setNightSleepTimeClickEvent("TYPE_SLEEP_LATENCY_TIME", i, i2, i3);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public edo getSleepEfficientBean(final Context context, final int i, int i2) {
        if (i <= 0) {
            LogUtil.a(getTag(), "no sleep efficiency data");
            return new edo();
        }
        String itemTitle = getItemTitle(i2, pqg.e(i, 0));
        String e = pqg.e(85, 100, 3);
        final int n = scn.n(i);
        return new edo(itemTitle, e, pqg.c(n, "TYPE_SLEEP_EFFICIENCY"), pqg.d(n), this.mArrowRes, new View.OnClickListener() { // from class: pml
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m816x26237190(context, i, n, view);
            }
        });
    }

    /* renamed from: lambda$getSleepEfficientBean$3$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m816x26237190(Context context, int i, int i2, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setSleepEfficientClickEvent(context, i, i2);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public int getSleepEfficientValue() {
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            return this.mSleepViewData.f().j();
        }
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            return this.mSleepViewData.d().j();
        }
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            return this.mSleepViewData.j().j();
        }
        return -1;
    }

    public edo getBedTimeBean(int i, int i2) {
        if (i <= 0) {
            return new edo();
        }
        final int i3 = i / 60;
        final int i4 = i % 60;
        String itemTitle = getItemTitle(i2, getFormatTime(i3, i4));
        String a2 = nsf.a(R.plurals._2130903201_res_0x7f0300a1, 6, pqg.b(UnitUtil.e(6.0d, 1, 0), UnitUtil.e(10.5d, 1, 1)));
        final int e = scn.e(i);
        return new edo(itemTitle, a2, pqg.c(e, "TYPE_SLEEP_BED_TIME"), pqg.d(e), this.mArrowRes, new View.OnClickListener() { // from class: pmc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m808x83b9e0a3(i3, i4, e, view);
            }
        });
    }

    /* renamed from: lambda$getBedTimeBean$4$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m808x83b9e0a3(int i, int i2, int i3, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setBedTimeClickEvent(i, i2, i3);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public edo getSleepTimeBean(String str, int i) {
        ReleaseLogUtil.e(getTag(), "getSleepTimeBean sleepTime ", Integer.valueOf(i));
        String e = pqg.e(6, 10, 2);
        final int l = scn.l(i);
        final int i2 = i / 60;
        final int i3 = i % 60;
        return new edo(str, e, pqg.c(l, "TYPE_SLEEP_TIME"), pqg.d(l), this.mArrowRes, new View.OnClickListener() { // from class: plz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m819x9b55a9fa(i2, i3, l, view);
            }
        });
    }

    /* renamed from: lambda$getSleepTimeBean$5$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m819x9b55a9fa(int i, int i2, int i3, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setAvgSleepTimeClickEvent(i, i2, i3);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public int getBedTime() {
        int k;
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            k = this.mSleepViewData.f().k();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            k = this.mSleepViewData.d().k();
        } else {
            k = this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE ? this.mSleepViewData.j().k() : 0;
        }
        LogUtil.a(getTag(), "getBedTime deviceType ", this.mSleepViewData.i(), " sleepBedTime ", Integer.valueOf(k));
        return k;
    }

    public edo getWakeTimesBean(final Context context, int i) {
        final int bc;
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            bc = this.mSleepViewData.f().ab();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            bc = this.mSleepViewData.j().bc();
        } else {
            return new edo();
        }
        LogUtil.a(getTag(), " wakeUpTimes:", Integer.valueOf(bc));
        String itemTitle = getItemTitle(i, nsf.a(R.plurals._2130903213_res_0x7f0300ad, bc, UnitUtil.e(bc, 1, 0)));
        String e = pqg.e(0, 1, 1);
        final int s = scn.s(bc);
        return new edo(itemTitle, e, pqg.c(s, "TYPE_WAKE_UP_TIMES"), pqg.d(s), this.mArrowRes, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.o()) {
                    SleepBaseExpandListProvider.this.setWakeTimesClickEvent(context, bc, s);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWakeTimesClickEvent(Context context, int i, int i2) {
        LogUtil.a(getTag(), "setWakeTimesClickEvent");
        SleepScoreTimesActivity.b(context, "TYPE_WAKE_UP_TIMES", i, i2);
    }

    public edo getPhoneNightSleepBean(int i, int i2) {
        final int i3 = i / 60;
        final int i4 = i % 60;
        BaseApplication.getContext();
        String itemTitle = getItemTitle(i2, getFormatTime(i3, i4));
        final int f = scn.f(i);
        return new edo(itemTitle, pqg.e(6, 10, 2), pqg.c(f, "TYPE_NIGHT_SLEEP_TIME"), pqg.d(f), this.mArrowRes, new View.OnClickListener() { // from class: pma
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m814x3ccf0ee4(i3, i4, f, view);
            }
        });
    }

    /* renamed from: lambda$getPhoneNightSleepBean$6$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m814x3ccf0ee4(int i, int i2, int i3, View view) {
        setNightSleepTimeClickEvent("TYPE_NIGHT_SLEEP_TIME", i, i2, i3);
        ViewClickInstrumentation.clickOnView(view);
    }

    public edo getCoreNightSleepBean(Context context, int i) {
        nsf.h(com.huawei.ui.main.R$string.IDS_sleep_referece_title_string);
        int h = this.mSleepViewData.j().h();
        final int i2 = h / 60;
        final int i3 = h % 60;
        String itemTitle = getItemTitle(i, getFormatTime(i2, i3));
        String e = pqg.e(6, 10, 2);
        final int f = scn.f(h);
        return new edo(itemTitle, e, pqg.c(f, "TYPE_NIGHT_SLEEP_TIME"), pqg.d(f), this.mArrowRes, new View.OnClickListener() { // from class: pmb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m810xf6b0828(i2, i3, f, view);
            }
        });
    }

    /* renamed from: lambda$getCoreNightSleepBean$7$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m810xf6b0828(int i, int i2, int i3, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setNightSleepTimeClickEvent("TYPE_NIGHT_SLEEP_TIME", i, i2, i3);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public edo getDeepSleepBean(final Context context, int i) {
        final int v = this.mSleepViewData.j().v();
        String itemTitle = getItemTitle(i, pqg.e(v, 0));
        String e = pqg.e(20, 60, 3);
        int i2 = scn.i(v);
        return new edo(itemTitle, e, pqg.c(i2, "TYPE_DEEP_SLEEP"), pqg.d(i2), this.mArrowRes, new View.OnClickListener() { // from class: plx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m811xa21b5d44(context, v, view);
            }
        });
    }

    /* renamed from: lambda$getDeepSleepBean$8$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m811xa21b5d44(Context context, int i, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setDeepSleepClickEvent(context, i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void setDeepSleepClickEvent(Context context, int i) {
        LogUtil.a(getTag(), "setDeepSleepClickEvent");
        SleepRateActivity.d(context, "TYPE_DEEP_SLEEP", i, scn.i(i));
    }

    public edo getLightSleepBean(final Context context, int i) {
        final int ax = this.mSleepViewData.j().ax();
        String itemTitle = getItemTitle(i, pqg.e(ax, 0));
        String b = nsf.b(com.huawei.ui.main.R$string.IDS_sleep_referece_title_smaller_string, pqg.e(55.0d, 0));
        int g = scn.g(ax);
        return new edo(itemTitle, b, pqg.c(g, "TYPE_LIGHT_SLEEP"), pqg.d(g), this.mArrowRes, new View.OnClickListener() { // from class: ply
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m813x65f52bed(context, ax, view);
            }
        });
    }

    /* renamed from: lambda$getLightSleepBean$9$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m813x65f52bed(Context context, int i, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setLightSleepClickEvent(context, i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void setLightSleepClickEvent(Context context, int i) {
        LogUtil.a(getTag(), "setLightSleepClickEvent");
        SleepRateActivity.d(context, "TYPE_LIGHT_SLEEP", i, scn.g(i));
    }

    public edo getRemSleepBean(final Context context, int i) {
        final int ao = this.mSleepViewData.j().ao();
        String itemTitle = getItemTitle(i, pqg.e(ao, 0));
        String e = pqg.e(10, 30, 3);
        int j = scn.j(ao);
        return new edo(itemTitle, e, pqg.c(j, "TYPE_SLUM_SLEEP_RATE"), pqg.d(j), this.mArrowRes, new View.OnClickListener() { // from class: pme
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m815x7678128b(context, ao, view);
            }
        });
    }

    /* renamed from: lambda$getRemSleepBean$10$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m815x7678128b(Context context, int i, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setRemSleepClickEvent(context, i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void setRemSleepClickEvent(Context context, int i) {
        LogUtil.a(getTag(), "setRemSleepClickEvent");
        SleepRateActivity.d(context, "TYPE_SLUM_SLEEP_RATE", i, scn.j(i));
    }

    public edo getDeepSleepContinuityBean(final Context context, int i) {
        String e;
        final int y = this.mSleepViewData.j().y();
        String e2 = pqg.e(70, 100, 0);
        final int h = scn.h(y);
        if (h == -1) {
            e = nsf.h(com.huawei.ui.main.R$string.IDS_physiological_index_invalid);
        } else {
            e = UnitUtil.e(y, 1, 0);
        }
        String itemTitle = getItemTitle(i, nsf.a(R.plurals._2130903221_res_0x7f0300b5, y, e));
        String c2 = pqg.c(h, "TYPE_DEEP_SLEEP_CONTINUITY");
        int d2 = pqg.d(h);
        if (d2 == 0) {
            d2 = nsf.c(R.color._2131297869_res_0x7f09064d);
        }
        return new edo(itemTitle, e2, c2, d2, this.mArrowRes, new View.OnClickListener() { // from class: pls
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m812xd84a91e0(context, y, h, view);
            }
        });
    }

    /* renamed from: lambda$getDeepSleepContinuityBean$11$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m812xd84a91e0(Context context, int i, int i2, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setDeepSleepContinuityClickEvent(context, i, i2);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void setDeepSleepContinuityClickEvent(Context context, int i, int i2) {
        LogUtil.a(getTag(), "setDeepSleepContinuityClickEvent");
        SleepScoreTimesActivity.b(context, "TYPE_DEEP_SLEEP_CONTINUITY", i, i2);
    }

    public edo getBreathQualityBean(final Context context, int i) {
        final int w = this.mSleepViewData.j().w();
        String itemTitle = getItemTitle(i, nsf.a(R.plurals._2130903221_res_0x7f0300b5, w, UnitUtil.e(w, 1, 0)));
        String e = pqg.e(70, 100, 0);
        int b = scn.b(w);
        return new edo(itemTitle, e, pqg.c(b, "TYPE_BREATH_QUALITY"), pqg.d(b), this.mArrowRes, new View.OnClickListener() { // from class: pmg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m809x3483b27d(context, w, view);
            }
        });
    }

    /* renamed from: lambda$getBreathQualityBean$12$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m809x3483b27d(Context context, int i, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            setBreathQualityClickEvent(context, i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void setBreathQualityClickEvent(Context context, int i) {
        LogUtil.a(getTag(), "setBreathQualityClickEvent");
        SleepScoreTimesActivity.b(context, "TYPE_BREATH_QUALITY", i, scn.b(i));
    }

    public static class d implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private int[] f9807a;
        private String b;
        private boolean c = false;
        private String d;
        private WeakReference<SleepBaseExpandListProvider> e;
        private int f;
        private String h;
        private int j;

        public d(SleepBaseExpandListProvider sleepBaseExpandListProvider, String str) {
            this.e = new WeakReference<>(sleepBaseExpandListProvider);
            this.b = str;
        }

        public d a(String str, int[] iArr, int i, String str2, int i2) {
            this.f9807a = iArr;
            this.f = i;
            this.h = str2;
            this.j = i2;
            this.d = str;
            this.c = true;
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!this.c) {
                ReleaseLogUtil.e(this.b, "init status is false");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            SleepBaseExpandListProvider sleepBaseExpandListProvider = this.e.get();
            if (sleepBaseExpandListProvider == null) {
                ReleaseLogUtil.e(this.b, "provider is null");
                ViewClickInstrumentation.clickOnView(view);
            } else {
                sleepBaseExpandListProvider.goToTargetPage(this.d, this.f9807a, this.f, this.h, this.j);
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }

    public void goToTargetPage(String str, int[] iArr, int i, CharSequence charSequence, int i2) {
        if (nsn.o()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(getTag(), "error pageCode: ", str);
            return;
        }
        KnitSleepDetailActivity knitSleepDetailActivity = this.mWeakRef.get();
        if (knitSleepDetailActivity == null) {
            ReleaseLogUtil.e(getTag(), "context is null");
        } else {
            handlePageCode(new c(str, iArr, i, charSequence, i2), knitSleepDetailActivity);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void handlePageCode(c cVar, Context context) {
        char c2;
        String str = cVar.c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1022318430:
                if (str.equals("TRUSLEEP_FIVE_HRV")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 1285815879:
                if (str.equals("TRUSLEEP_FIVE_HEART_RATE")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1627456622:
                if (str.equals("TRUSLEEP_FIVE_SpO2")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1993820721:
                if (str.equals("TRUSLEEP_FIVE_BREATH_RATE")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            handleHRVPage(cVar, context);
            return;
        }
        if (c2 == 1) {
            handleHeartRatePage(cVar, context);
            return;
        }
        if (c2 == 2) {
            handleSpO2Page(cVar, context);
        } else if (c2 == 3) {
            handleBreathRatePage(cVar, context);
        } else {
            ReleaseLogUtil.d(getTag(), "error type:", cVar.c);
        }
    }

    private void handleSpO2Page(c cVar, Context context) {
        CharSequence[] charSequenceArr = new CharSequence[4];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        charSequenceArr[0] = nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_42);
        arrayList.add(nsf.b(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_44, UnitUtil.e(90.0d, 2, 0)));
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_46));
        arrayList3.add("\n");
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_47));
        handleTruSleepData(charSequenceArr, cVar);
        SleepInstructionPageActivity.a(context, charSequenceArr, new CharSequence[]{nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_43), nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_45)}, new CharSequence[][]{(CharSequence[]) arrayList.toArray(new CharSequence[0]), (CharSequence[]) arrayList3.toArray(new CharSequence[0]), (CharSequence[]) arrayList2.toArray(new CharSequence[0])});
    }

    private void handleHRVPage(c cVar, Context context) {
        CharSequence[] charSequenceArr = new CharSequence[4];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        charSequenceArr[0] = nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_35);
        arrayList.add(nsf.b(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_37_new, UnitUtil.e(19.0d, 1, 0), UnitUtil.e(75.0d, 1, 0)));
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_39));
        arrayList3.add("\n");
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_40));
        arrayList3.add("\n");
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_41));
        handleTruSleepData(charSequenceArr, cVar);
        SleepInstructionPageActivity.a(context, charSequenceArr, new CharSequence[]{nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_36), nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_38)}, new CharSequence[][]{(CharSequence[]) arrayList.toArray(new CharSequence[0]), (CharSequence[]) arrayList3.toArray(new CharSequence[0]), (CharSequence[]) arrayList2.toArray(new CharSequence[0])});
    }

    private void handleBreathRatePage(c cVar, Context context) {
        CharSequence[] charSequenceArr = new CharSequence[4];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        charSequenceArr[0] = nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_48);
        arrayList.add(nsf.b(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_50_new, UnitUtil.e(12.0d, 1, 0), UnitUtil.e(20.0d, 1, 0)));
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_52));
        arrayList3.add("\n");
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_53));
        arrayList3.add("\n");
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_54));
        handleTruSleepData(charSequenceArr, cVar);
        SleepInstructionPageActivity.a(context, charSequenceArr, new CharSequence[]{nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_49), nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_51)}, new CharSequence[][]{(CharSequence[]) arrayList.toArray(new CharSequence[0]), (CharSequence[]) arrayList3.toArray(new CharSequence[0]), (CharSequence[]) arrayList2.toArray(new CharSequence[0])});
    }

    private void handleHeartRatePage(c cVar, Context context) {
        CharSequence[] charSequenceArr = new CharSequence[4];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        charSequenceArr[0] = nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_28);
        arrayList.add(nsf.b(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_30, UnitUtil.e(90.0d, 1, 0)));
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_32));
        arrayList3.add("\n");
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_33));
        arrayList3.add("\n");
        arrayList3.add(nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_34));
        handleTruSleepData(charSequenceArr, cVar);
        SleepInstructionPageActivity.a(context, charSequenceArr, new CharSequence[]{nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_29), nsf.h(com.huawei.ui.main.R$string.IDS_hw_health_sleep_dataitem_doc_31)}, new CharSequence[][]{(CharSequence[]) arrayList.toArray(new CharSequence[0]), (CharSequence[]) arrayList3.toArray(new CharSequence[0]), (CharSequence[]) arrayList2.toArray(new CharSequence[0])});
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        public final int f9806a;
        public final int b;
        public final String c;
        public final CharSequence d;
        public final int[] e;

        public c(String str, int[] iArr, int i, CharSequence charSequence, int i2) {
            this.c = str;
            this.e = iArr;
            this.b = i;
            this.d = charSequence;
            this.f9806a = i2;
        }
    }

    public edx getSleepPhysiologicalItem(final Context context, String str) {
        String h;
        String subSleepPhyStr;
        final String str2;
        if ("SLEEP_HEART_RATE".equals(str)) {
            h = nsf.h(com.huawei.ui.main.R$string.IDS_fitness_core_sleep_heartrate);
            subSleepPhyStr = getSubSleepPhyStr(R.plurals._2130903151_res_0x7f03006f, this.mSleepViewData.j().al(), this.mSleepViewData.j().ae(), true);
            str2 = "TYPE_SLEEP_HEART_RATE";
        } else if ("SLEEP_BLOOD_OX".equals(str)) {
            h = nsf.h(com.huawei.ui.main.R$string.IDS_fitness_core_sleep_oxygen_saturation);
            subSleepPhyStr = getSubSleepPhyStr(-1, this.mSleepViewData.j().ak().doubleValue(), this.mSleepViewData.j().ai().doubleValue(), false);
            str2 = "TYPE_SLEEP_BLOOD_OX";
        } else if ("SLEEP_BREATH_RATIO".equals(str)) {
            h = nsf.h(com.huawei.ui.main.R$string.IDS_fitness_core_sleep_breathrate);
            subSleepPhyStr = getSubSleepPhyStr(R.plurals._2130903398_res_0x7f030166, this.mSleepViewData.j().ah().doubleValue(), this.mSleepViewData.j().aa(), true);
            str2 = "TYPE_SLEEP_BREATH_RATE";
        } else {
            return new edx();
        }
        if (TextUtils.isEmpty(subSleepPhyStr)) {
            return new edx();
        }
        return new edx(h, subSleepPhyStr, this.mArrowRes, new View.OnClickListener() { // from class: pmd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepBaseExpandListProvider.this.m818x6241ccce(context, str2, view);
            }
        });
    }

    /* renamed from: lambda$getSleepPhysiologicalItem$13$com-huawei-ui-main-stories-fitness-activity-coresleep-SleepBaseExpandListProvider, reason: not valid java name */
    public /* synthetic */ void m818x6241ccce(Context context, String str, View view) {
        SleepDurationActivity.c(context, str, 0, 0, 0, this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE);
        ViewClickInstrumentation.clickOnView(view);
    }

    private String getSubSleepPhyStr(int i, double d2, double d3, boolean z) {
        if (this.mSleepViewData.j().j() < 0) {
            return null;
        }
        if (d2 <= -1.0d || d3 <= -1.0d || d2 > d3) {
            return "--";
        }
        if (d2 <= 0.0d || d3 <= 0.0d || d2 > d3) {
            return null;
        }
        if (z) {
            return nsf.a(i, UnitUtil.e(d2), Integer.valueOf(UnitUtil.e(d2, 1, 0)), Integer.valueOf(UnitUtil.e(d3, 1, 0)));
        }
        return nsf.b(com.huawei.ui.main.R$string.IDS_hw_health_coresleep_standard_range_1, UnitUtil.e(d2, 1, 0), pqg.e(d3, 0));
    }

    public SpannableString makeSubTitleSpannable(String str, String str2, String str3, int[] iArr) {
        if (!TextUtils.isEmpty(str)) {
            str2 = nsf.b(com.huawei.ui.main.R$string.IDS_bese_line_description, str);
        }
        SpannableString spannableString = new SpannableString(str2);
        Integer[] numArr = qmx.g.get(str3);
        if (this.mSleepViewData.j().d(str3) && (iArr[0] < numArr[0].intValue() || iArr[1] > numArr[1].intValue())) {
            nsi.cKI_(spannableString, str, R.color._2131297089_res_0x7f090341);
        }
        return spannableString;
    }

    public int getDaySleepStatus(int i) {
        int i2 = i > 40 ? 71 : 73;
        LogUtil.a(getTag(), "getDaySleepStatus:", Integer.valueOf(i2));
        return i2;
    }

    public fdq getSleepStatData() {
        if (!(this.mSleepViewData.j() instanceof fdq)) {
            ReleaseLogUtil.d(getTag(), "scienceSleepData is not ScienceSleepStatData");
            return new fdq();
        }
        return (fdq) this.mSleepViewData.j();
    }
}
