package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.SparseArray;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.utils.HarmonyOsTypefaceSpan;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepScoringProvider;
import com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager;
import defpackage.bkz;
import defpackage.eef;
import defpackage.eel;
import defpackage.fcj;
import defpackage.fda;
import defpackage.fdp;
import defpackage.jdl;
import defpackage.mtp;
import defpackage.nom;
import defpackage.nru;
import defpackage.ppj;
import defpackage.pqp;
import defpackage.pqr;
import defpackage.pwd;
import defpackage.scx;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class SleepScoringProvider extends MinorProvider<fdp> {
    private String i;
    private String k;
    private pwd m;
    private String n;
    private SectionBean p;
    private String q;
    private fdp t;
    private Typeface u;
    private String v;
    private String y;
    private static final int[] e = {44102, 44103, 44105, 44106, 44201, 44202, 44107, 44109, 44218, 44217, 44104, 44110};
    private static final int[] b = {DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_FALL_ASLEEP_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value()};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f9832a = {"data_session_manual_sleep_go_to_bed_time_key", "data_session_manual_sleep_sleep_time_key", "data_session_manual_sleep_get_up_time_key"};
    private static final int[] j = {DicDataTypeUtil.DataType.BED_TIME.value()};
    private static final String[] c = {"bedTime"};
    private static final String[] d = {"core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_total_sleep_time_key", "core_sleep_deep_sleep_part_key", "core_sleep_fall_key", "core_sleep_wake_up_key", "core_sleep_wake_count_key", "data_session_manual_sleep_bed_time_key", "core_sleep_latency_key", "core_sleep_bed_time_key", "core_sleep_wake_key", "sleep_device_category_key"};
    private boolean o = true;
    private boolean h = false;
    private boolean r = false;
    private String w = null;
    private String g = null;
    private String f = null;
    private int l = -1;
    private int x = -1;
    private int s = -1;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        e(context, (HashMap<String, Object>) hashMap, (fdp) obj);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.o;
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        LogUtil.a("SleepScoringProvider", "loadData");
        this.p = sectionBean;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        this.g = null;
        this.f = null;
        this.h = false;
        this.r = false;
        LogUtil.a("SleepScoringProvider", "onSetSectionBeanData, data: " + fdpVar.toString());
        if (sectionBean != null) {
            this.p = sectionBean;
        }
        boolean z = fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY && (fcj.e(fdpVar) || fcj.d(fdpVar));
        boolean z2 = fdpVar.e() != SleepViewConstants.ViewTypeEnum.DAY && (fcj.e(fdpVar) || fcj.d(fdpVar));
        boolean i = i(fdpVar);
        LogUtil.a("SleepScoringProvider", "isDayNoonSleep: ", Boolean.valueOf(z), ", isWeekMonthNoonSleep: ", Boolean.valueOf(z2), ", isValidScoringData: ", Boolean.valueOf(i));
        if (z || z2 || !i) {
            LogUtil.a("SleepScoringProvider", "data is invalid");
            this.o = false;
            this.p.e(this, fdpVar);
            return;
        }
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL && (fdpVar.d().s() || fdpVar.d().q())) {
            LogUtil.a("SleepScoringProvider", "isManualOnlyBedTime");
            this.o = false;
            this.p.e(this, fdpVar);
            return;
        }
        this.o = true;
        this.t = fdpVar;
        this.m = (pwd) fdpVar.l().get("FITNESSCORE_SLEEP_DETAIL_INTERACTOR");
        Map<String, Object> l = fdpVar.l();
        Object obj = l.get("IS_PHONE_AND_CORE_NOON");
        LogUtil.a("SleepScoringProvider", "isPhoneAndCoreNoon: ", Boolean.valueOf(obj instanceof Boolean ? ((Boolean) obj).booleanValue() : false));
        if (l.containsKey("MGMT_DAILY_SLEEP_PROCESS") && (l.get("MGMT_DAILY_SLEEP_PROCESS") instanceof fda)) {
            b(fdpVar, l, jdl.t(fdpVar.g()));
        } else {
            e(fdpVar, l);
        }
    }

    private boolean i(fdp fdpVar) {
        return (fdpVar.j().ar() > 0 || fdpVar.j().h() > 0) || (fdpVar.d().k() > 0 || fdpVar.d().p() != -1) || (fdpVar.f().h() > 0 || fdpVar.f().aa() != -1) || (fdpVar.c().h() > 0);
    }

    private void b(fdp fdpVar, Map map, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j2 - 518400000);
        hiAggregateOption.setEndTime(86399999 + j2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(d);
        hiAggregateOption.setType(e);
        hiAggregateOption.setReadType(0);
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new d(this, j2, fdpVar, map));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<fdp> d(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3, long j2) {
        return fcj.d(3, jdl.d(j2, -6), new int[]{7, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL}, fcj.e(list, list2, list3));
    }

    public static class d implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private fdp f9835a;
        private final WeakReference<SleepScoringProvider> b;
        private long c;
        private Map e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        public d(SleepScoringProvider sleepScoringProvider, long j, fdp fdpVar, Map map) {
            this.b = new WeakReference<>(sleepScoringProvider);
            this.c = j;
            this.f9835a = fdpVar;
            this.e = map;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(final List<HiHealthData> list, int i, int i2) {
            LogUtil.a("SleepScoringProvider", "ChartSumSleepResponseCallback response!");
            final SleepScoringProvider sleepScoringProvider = this.b.get();
            if (sleepScoringProvider == null) {
                return;
            }
            if (!bkz.e(list)) {
                LogUtil.a("SleepScoringProvider", "ChartSumSleepResponseCallback sumList is", list.toString());
                int h = ((fda) this.e.get("MGMT_DAILY_SLEEP_PROCESS")).h();
                final eef eefVar = new eef();
                switch (h) {
                    case 0:
                    case 5:
                    case 8:
                        g(eefVar, list, this.c);
                        eefVar.e(6);
                        break;
                    case 1:
                    case 3:
                        b(eefVar, list, this.c);
                        eefVar.e(1);
                        break;
                    case 2:
                        f(eefVar, list, this.c);
                        eefVar.e(5);
                        break;
                    case 4:
                        c(eefVar, list, this.c);
                        eefVar.e(2);
                        break;
                    case 6:
                        d(eefVar, list, this.c);
                        eefVar.e(3);
                        break;
                    case 7:
                        e(eefVar, list, this.c);
                        eefVar.e(7);
                        break;
                    case 9:
                        a(eefVar, list, this.c);
                        eefVar.e(4);
                        break;
                    case 10:
                        a(eefVar, list, this.c, sleepScoringProvider);
                        eefVar.e(8);
                        break;
                    case 11:
                        eefVar.e(9);
                        ThreadPoolManager.getInstance().execute(new Runnable() { // from class: ppa
                            @Override // java.lang.Runnable
                            public final void run() {
                                SleepScoringProvider.d.this.c(eefVar, list, sleepScoringProvider);
                            }
                        });
                        return;
                }
                this.f9835a.l().put("SMART_SLEEP_CHART_DATA", eefVar);
                sleepScoringProvider.e(this.f9835a, this.e);
                return;
            }
            LogUtil.a("SleepScoringProvider", "ChartSumSleepResponseCallback sumList is empty!");
            sleepScoringProvider.e(this.f9835a, this.e);
        }

        public /* synthetic */ void c(eef eefVar, List list, SleepScoringProvider sleepScoringProvider) {
            d(eefVar, list, this.c, sleepScoringProvider);
        }

        private void d(eef eefVar, List<HiHealthData> list, long j, SleepScoringProvider sleepScoringProvider) {
            HiAggregateOption hiAggregateOption = new HiAggregateOption();
            int b = DateFormatUtil.b(jdl.b(j, -6));
            hiAggregateOption.setTimeInterval(String.valueOf(b), String.valueOf(DateFormatUtil.b(jdl.b(j, 2))), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
            hiAggregateOption.setType(SleepScoringProvider.b);
            hiAggregateOption.setConstantsKey(SleepScoringProvider.f9832a);
            hiAggregateOption.setAggregateType(1);
            hiAggregateOption.setGroupUnitType(0);
            HiDataFilter.DataFilterExpression dataFilterExpression = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_go_to_bed_time_key", HiDataFilter.DataFilterExpression.BIGGER_EQUAL, jdl.e(jdl.b(j, -7), 20, 0));
            HiDataFilter.DataFilterExpression dataFilterExpression2 = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_go_to_bed_time_key", HiDataFilter.DataFilterExpression.LESS_EQUAL, jdl.e(j, 20, 0) - 1);
            ArrayList arrayList = new ArrayList();
            arrayList.add(dataFilterExpression);
            arrayList.add(dataFilterExpression2);
            HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).b(HiJsonUtil.e(new HiDataFilter(arrayList, Collections.singletonList(0)))).c((String) null).c();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(c);
            arrayList2.add(b(b, DateFormatUtil.b(j)));
            this.e.put("SUM_LIST", list);
            HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).aggregateHiHealthDataProEx(arrayList2, new b(sleepScoringProvider, eefVar, j, this.f9835a, this.e));
        }

        private HiDataAggregateProOption b(int i, int i2) {
            HiAggregateOption hiAggregateOption = new HiAggregateOption();
            hiAggregateOption.setTimeInterval(String.valueOf(i), String.valueOf(i2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
            hiAggregateOption.setType(SleepScoringProvider.j);
            hiAggregateOption.setConstantsKey(SleepScoringProvider.c);
            hiAggregateOption.setAggregateType(1);
            hiAggregateOption.setGroupUnitType(0);
            return new HiDataAggregateProOption.Builder().c(hiAggregateOption).c((String) null).c();
        }

        private void a(eef eefVar, List<HiHealthData> list, long j, SleepScoringProvider sleepScoringProvider) {
            int j2;
            long f;
            eel[] eelVarArr = new eel[7];
            for (fdp fdpVar : sleepScoringProvider.d(list, new ArrayList(), new ArrayList(), j)) {
                int i = AnonymousClass3.c[fdpVar.i().ordinal()];
                if (i == 1) {
                    j2 = fdpVar.f().j();
                    f = fdpVar.f().f();
                } else if (i == 2) {
                    j2 = fdpVar.d().j();
                    f = fdpVar.d().f();
                } else {
                    LogUtil.h("SleepScoringProvider", "no sleep efficiency");
                    f = 0;
                    j2 = -1;
                }
                if (j2 == -1) {
                    LogUtil.h("SleepScoringProvider", "no sleep efficiency");
                } else {
                    int e = jdl.e(f, j);
                    LogUtil.a("SleepScoringProvider", " sleep efficiency is ", Integer.valueOf(j2));
                    eelVarArr[7 - e] = new eel(f, j2);
                }
            }
            eefVar.d(eelVarArr);
        }

        private void e(eef eefVar, List<HiHealthData> list, long j) {
            eel[] eelVarArr = new eel[7];
            for (int i = 0; i < 7; i++) {
                long d = jdl.d(j, i - 6);
                Iterator<HiHealthData> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        HiHealthData next = it.next();
                        if (jdl.d(next.getStartTime(), d)) {
                            eelVarArr[i] = new eel(d, next.getInt("core_sleep_deep_sleep_part_key"));
                            break;
                        }
                    }
                }
            }
            eefVar.d(eelVarArr);
        }

        private void g(eef eefVar, List<HiHealthData> list, long j) {
            eel[] eelVarArr = new eel[7];
            for (int i = 0; i < 7; i++) {
                long d = jdl.d(j, i - 6);
                Iterator<HiHealthData> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        HiHealthData next = it.next();
                        if (jdl.d(next.getStartTime(), d)) {
                            eelVarArr[i] = new eel(d, next.getInt("core_sleep_total_sleep_time_key"));
                            break;
                        }
                    }
                }
            }
            eefVar.d(eelVarArr);
        }

        private void f(eef eefVar, List<HiHealthData> list, long j) {
            eel[] eelVarArr = new eel[7];
            for (int i = 0; i < 7; i++) {
                long d = jdl.d(j, i - 6);
                Iterator<HiHealthData> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        HiHealthData next = it.next();
                        if (jdl.d(next.getStartTime(), d)) {
                            eelVarArr[i] = new eel(d, next.getInt("core_sleep_wake_count_key"));
                            break;
                        }
                    }
                }
            }
            eefVar.d(eelVarArr);
        }

        private void a(eef eefVar, List<HiHealthData> list, long j) {
            eel[] eelVarArr = new eel[7];
            for (int i = 0; i < 7; i++) {
                long d = jdl.d(j, i - 6);
                Iterator<HiHealthData> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        HiHealthData next = it.next();
                        if (jdl.d(next.getStartTime(), d)) {
                            long j2 = next.getLong("core_sleep_wake_up_key");
                            if (j2 != 0) {
                                eelVarArr[i] = new eel(d, j2);
                            }
                        }
                    }
                }
            }
            eefVar.d(eelVarArr);
        }

        private void d(eef eefVar, List<HiHealthData> list, long j) {
            eel[] eelVarArr = new eel[7];
            for (int i = 0; i < 7; i++) {
                long d = jdl.d(j, i - 6);
                Iterator<HiHealthData> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        HiHealthData next = it.next();
                        if (jdl.d(next.getStartTime(), d)) {
                            long j2 = next.getLong("core_sleep_fall_key");
                            if (j2 != 0) {
                                eelVarArr[i] = new eel(d, j2);
                            }
                        }
                    }
                }
            }
            eefVar.d(eelVarArr);
        }

        private void c(eef eefVar, List<HiHealthData> list, long j) {
            eel[] eelVarArr = new eel[7];
            for (int i = 0; i < 7; i++) {
                long d = jdl.d(j, i - 6);
                Iterator<HiHealthData> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        HiHealthData next = it.next();
                        if (jdl.d(next.getStartTime(), d)) {
                            eelVarArr[i] = new eel(d, c(next));
                            break;
                        }
                    }
                }
            }
            eefVar.d(eelVarArr);
        }

        private void b(eef eefVar, List<HiHealthData> list, long j) {
            eel[] eelVarArr = new eel[7];
            for (int i = 0; i < 7; i++) {
                long d = jdl.d(j, i - 6);
                Iterator<HiHealthData> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        HiHealthData next = it.next();
                        if (jdl.d(next.getStartTime(), d)) {
                            eelVarArr[i] = new eel(d, b(next));
                            break;
                        }
                    }
                }
            }
            eefVar.d(eelVarArr);
        }

        private int c(HiHealthData hiHealthData) {
            int i = hiHealthData.getInt("core_sleep_deep_key");
            int i2 = hiHealthData.getInt("core_sleep_shallow_key");
            int i3 = hiHealthData.getInt("core_sleep_total_sleep_time_key");
            if (i3 == 0) {
                return -1;
            }
            float f = i3;
            int round = Math.round((i / f) * 100.0f);
            return Math.round((((float) i2) / f) * 100.0f) + round > 100 ? round - 1 : round;
        }

        private int b(HiHealthData hiHealthData) {
            int i = hiHealthData.getInt("core_sleep_deep_key");
            int i2 = hiHealthData.getInt("core_sleep_shallow_key");
            int i3 = hiHealthData.getInt("core_sleep_total_sleep_time_key");
            SleepViewConstants.SleepTypeEnum e = fcj.e(hiHealthData, new ArrayList());
            if (i3 == 0 || e != SleepViewConstants.SleepTypeEnum.SCIENCE) {
                return -1;
            }
            float f = i;
            float f2 = i3;
            int round = (100 - Math.round((i2 / f2) * 100.0f)) - Math.round((f / f2) * 100.0f);
            if (round <= 0) {
                return 0;
            }
            return round;
        }
    }

    static class b implements HiAggregateListenerEx {

        /* renamed from: a, reason: collision with root package name */
        private fdp f9834a;
        private long b;
        private Map c;
        private eef d;
        private WeakReference<SleepScoringProvider> e;

        public b(SleepScoringProvider sleepScoringProvider, eef eefVar, long j, fdp fdpVar, Map map) {
            this.e = new WeakReference<>(sleepScoringProvider);
            this.d = eefVar;
            this.b = j;
            this.f9834a = fdpVar;
            this.c = map;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
        public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
            SleepScoringProvider sleepScoringProvider = this.e.get();
            if (sleepScoringProvider == null) {
                LogUtil.b("SleepScoringProvider", "SleepLatencyCallBack provider is null.");
                return;
            }
            Object[] objArr = new Object[2];
            objArr[0] = "requestSleepLatency back datas is null";
            objArr[1] = Boolean.valueOf(sparseArray == null);
            LogUtil.a("SleepScoringProvider", objArr);
            drA_(this.d, nru.d(this.c, "SUM_LIST", HiHealthData.class, new ArrayList()), sparseArray, this.b);
            this.f9834a.l().put("SMART_SLEEP_CHART_DATA", this.d);
            sleepScoringProvider.e(this.f9834a, this.c);
        }

        private void drA_(eef eefVar, List<HiHealthData> list, SparseArray<List<HiHealthData>> sparseArray, long j) {
            int i;
            long f;
            eel[] eelVarArr = new eel[7];
            if (bkz.e(list)) {
                LogUtil.b("SleepScoringProvider", "sumList is null");
                eefVar.d(eelVarArr);
                return;
            }
            SleepScoringProvider sleepScoringProvider = this.e.get();
            if (sleepScoringProvider == null) {
                LogUtil.b("SleepScoringProvider", "SleepLatencyCallBack provider is null.");
                return;
            }
            for (fdp fdpVar : sleepScoringProvider.d(list, (sparseArray == null || sparseArray.get(0) == null) ? new ArrayList<>() : sparseArray.get(0), (sparseArray == null || sparseArray.get(1) == null) ? new ArrayList<>() : sparseArray.get(1), j)) {
                int i2 = AnonymousClass3.c[fdpVar.i().ordinal()];
                if (i2 == 1) {
                    i = fdpVar.f().i();
                    f = fdpVar.f().f();
                } else if (i2 == 2) {
                    i = fdpVar.d().i();
                    f = fdpVar.d().f();
                } else {
                    LogUtil.h("SleepScoringProvider", "no sleep efficiency");
                    f = 0;
                    i = -1;
                }
                if (i == -1) {
                    LogUtil.h("SleepScoringProvider", "no sleep efficiency");
                } else {
                    int e = jdl.e(f, j);
                    LogUtil.a("SleepScoringProvider", " latency is ", Integer.valueOf(i));
                    eelVarArr[7 - e] = new eel(f, i);
                }
            }
            eefVar.d(eelVarArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(fdp fdpVar, Map map) {
        if (e(map)) {
            LogUtil.a("SleepScoringProvider", "isInCompleteData");
            this.y = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R$string.IDS_incomplete_sleep_data);
            this.q = "";
            this.p.e(this, this.t);
            return;
        }
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
            if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
                LogUtil.a("SleepScoringProvider", "day manual sleep");
                e(fdpVar);
                return;
            }
            if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
                LogUtil.a("SleepScoringProvider", "day phone sleep");
                d(fdpVar);
                return;
            } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
                LogUtil.a("SleepScoringProvider", "day core sleep");
                g(fdpVar);
                return;
            } else {
                if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
                    LogUtil.a("SleepScoringProvider", "is Common sleep");
                    this.h = true;
                    this.p.e(this, this.t);
                    return;
                }
                return;
            }
        }
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK) {
            if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
                LogUtil.a("SleepScoringProvider", "week isHasWearCoreData sleep");
                g(fdpVar);
                return;
            }
            if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
                LogUtil.a("SleepScoringProvider", "week isHasPhoneData sleep");
                j(fdpVar);
                return;
            } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
                LogUtil.a("SleepScoringProvider", "week isHasPhoneData sleep");
                f(fdpVar);
                return;
            } else {
                if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
                    LogUtil.a("SleepScoringProvider", "is Common sleep");
                    this.h = true;
                    this.p.e(this, this.t);
                    return;
                }
                return;
            }
        }
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH) {
            if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
                LogUtil.a("SleepScoringProvider", "month isHasWearCoreData sleep");
                g(fdpVar);
                return;
            }
            if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
                LogUtil.a("SleepScoringProvider", "month isHasPhoneData sleep");
                c(fdpVar);
                return;
            } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
                LogUtil.a("SleepScoringProvider", "month isHasPhoneData sleep");
                a(fdpVar);
                return;
            } else {
                if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
                    LogUtil.a("SleepScoringProvider", "is Common sleep");
                    this.h = true;
                    this.p.e(this, this.t);
                    return;
                }
                return;
            }
        }
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.YEAR) {
            LogUtil.a("SleepScoringProvider", "year view, Common sleep");
            this.h = true;
            this.p.e(this, this.t);
        }
    }

    private void e(fdp fdpVar) {
        String c2;
        String e2;
        int h = fdpVar.d().h();
        LogUtil.a("SleepScoringProvider", "mNightTotalSleepTime: ", Integer.valueOf(h));
        boolean z = h > 600;
        int d2 = pqr.d(fdpVar);
        this.y = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_two_parts, pqr.b(fdpVar.d().r()), pqr.a(fdpVar, d2, z, h));
        if (this.l == d2) {
            c2 = this.k;
            e2 = this.i;
        } else {
            c2 = pqr.c(d2);
            e2 = pqr.e(d2);
            this.l = d2;
            this.k = c2;
            this.i = e2;
        }
        this.q = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_two_parts, c2, e2);
        this.h = false;
        this.p.e(this, this.t);
    }

    private boolean e(Map map) {
        Object obj = map.get("IS_INCOMPLETE_DATA");
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    private void d(fdp fdpVar) {
        pqr.d(new Date(fdpVar.g()), new e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(fdp fdpVar) {
        pqr.c(new Date(fdpVar.g()), new IBaseResponseCallback() { // from class: poq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SleepScoringProvider.this.d(i, obj);
            }
        });
    }

    public /* synthetic */ void d(int i, Object obj) {
        LogUtil.a("SleepScoringProvider", "getSleepEndReason errorCode: ", Integer.valueOf(i), ", data: ", obj);
        if (i == 0) {
            Map map = (Map) obj;
            if (map.containsKey("detect_abnormal")) {
                this.g = (String) map.get("detect_abnormal");
            }
            if (map.containsKey("detect_abnormal_jump")) {
                this.f = (String) map.get("detect_abnormal_jump");
            }
        }
        this.h = false;
        this.p.e(this, this.t);
    }

    private void j(final fdp fdpVar) {
        Date date = new Date(fdpVar.g());
        pqr.c(date, new Date((nom.a() + date.getTime()) - 1), new IBaseResponseCallback() { // from class: poy
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SleepScoringProvider.this.c(fdpVar, i, obj);
            }
        });
        pqr.a(d(new Date(fdpVar.g())), c(new Date(fdpVar.g())), new IBaseResponseCallback() { // from class: pox
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SleepScoringProvider.this.a(fdpVar, i, obj);
            }
        });
    }

    public /* synthetic */ void c(fdp fdpVar, int i, Object obj) {
        LogUtil.a("SleepScoringProvider", "getWeekSleepSuggestion errorCode: ", Integer.valueOf(i), ", data: ", obj);
        if (i == 0) {
            Integer num = (Integer) obj;
            this.q = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_three_parts, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_week_average_sleep_duration, pqr.d(fdpVar.f().h()), pqr.a(fdpVar.f().h())), pqr.c(fdpVar.f().y(), num.intValue(), true), pqr.e(num.intValue()));
            this.p.e(this, fdpVar);
            return;
        }
        this.o = false;
        this.p.e(this, fdpVar);
    }

    public /* synthetic */ void a(fdp fdpVar, int i, Object obj) {
        LogUtil.a("SleepScoringProvider", "getDailySleepRegularity for week errorCode: ", Integer.valueOf(i), ", data: ", obj);
        this.y = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_two_parts, i == 0 ? (String) obj : "", fdpVar.l().get("DATA_DIFFERENCE"));
        this.p.e(this, fdpVar);
    }

    private void f(fdp fdpVar) {
        String str;
        int d2 = pqr.d(fdpVar);
        String string = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_week_average_sleep_duration, pqr.d(fdpVar.d().h()), pqr.a(fdpVar.d().h()));
        String c2 = pqr.c(fdpVar.d().r(), d2, true);
        if (this.x == d2) {
            str = this.v;
        } else {
            String e2 = pqr.e(d2);
            this.x = d2;
            this.v = e2;
            str = e2;
        }
        this.q = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_three_parts, string, c2, str);
        this.y = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_two_parts, pqr.b(fdpVar), fdpVar.l().get("DATA_DIFFERENCE"));
        this.p.e(this, fdpVar);
    }

    private void c(final fdp fdpVar) {
        pqr.c(new Date(jdl.s(fdpVar.g())), new Date(jdl.a(fdpVar.g())), new IBaseResponseCallback() { // from class: pou
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SleepScoringProvider.this.e(fdpVar, i, obj);
            }
        });
        pqr.a(new Date(jdl.s(fdpVar.g())), new Date(jdl.a(fdpVar.g())), new IBaseResponseCallback() { // from class: pow
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SleepScoringProvider.this.d(fdpVar, i, obj);
            }
        });
    }

    public /* synthetic */ void e(fdp fdpVar, int i, Object obj) {
        LogUtil.a("SleepScoringProvider", "getMonthSleepSuggestion errorCode: ", Integer.valueOf(i), ", data: ", obj);
        if (i == 0) {
            Integer num = (Integer) obj;
            this.q = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_three_parts, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_month_average_sleep_duration, pqr.d(fdpVar.f().h()), pqr.a(fdpVar.f().h())), pqr.c(fdpVar.f().y(), num.intValue(), false), pqr.e(num.intValue()));
            this.p.e(this, fdpVar);
            return;
        }
        this.o = false;
        this.p.e(this, fdpVar);
    }

    public /* synthetic */ void d(fdp fdpVar, int i, Object obj) {
        LogUtil.a("SleepScoringProvider", "getDailySleepRegularity for month errorCode: ", Integer.valueOf(i), ", data: ", obj);
        this.y = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_two_parts, i == 0 ? (String) obj : "", fdpVar.l().get("DATA_DIFFERENCE"));
        this.p.e(this, fdpVar);
    }

    private void a(fdp fdpVar) {
        String str;
        int d2 = pqr.d(fdpVar);
        String string = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_month_average_sleep_duration, pqr.d(fdpVar.d().h()), pqr.a(fdpVar.d().h()));
        String c2 = pqr.c(fdpVar.d().r(), d2, false);
        if (this.s == d2) {
            str = this.n;
        } else {
            String e2 = pqr.e(d2);
            this.s = d2;
            this.n = e2;
            str = e2;
        }
        this.q = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_three_parts, string, c2, str);
        this.y = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_two_parts, pqr.b(fdpVar), fdpVar.l().get("DATA_DIFFERENCE"));
        this.p.e(this, fdpVar);
    }

    public void e(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepScoringProvider", "parseParams");
        hashMap.clear();
        hashMap.put("SHOW_VALUE", fdpVar.l().get("SMART_SLEEP_CHART_DATA"));
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.YEAR) {
            hashMap.put("COMMON_TITLE_TEXT", context.getString(R$string.IDS_manual_sleep_annual_average_data));
            int c2 = fcj.c(fdpVar);
            hashMap.put("IS_CORE_SLEEP", false);
            d(context, hashMap, c2, fdpVar.e());
            return;
        }
        if (this.h) {
            b(context, hashMap, fdpVar);
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            hashMap.put("IS_PHONE_SLEEP", Boolean.valueOf(fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL));
            a(context, hashMap, fdpVar);
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            hashMap.put("IS_PHONE_SLEEP", Boolean.valueOf(fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE));
            d(context, hashMap, fdpVar);
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            hashMap.put("IS_CORE_SLEEP", Boolean.valueOf(fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE));
            c(context, hashMap, fdpVar);
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
            b(context, hashMap, fdpVar);
        }
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
            hashMap.put("IS_DATA_TYPE_DAY", true);
        }
    }

    private void a(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepScoringProvider", "setManualSleepData");
        int r = fdpVar.d().r();
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
            hashMap.put("LEVEL_TITLE", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_sleep_quality));
        } else {
            hashMap.put("LEVEL_TITLE", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_fitness_core_sleep_avg_night_sleep));
        }
        if (e(fdpVar.l())) {
            hashMap.put("LEVEL_VALUE", "--");
        } else {
            hashMap.put("LEVEL_VALUE", pqr.f(r));
            hashMap.put("LEVEL_VALUE_COLOR", Integer.valueOf(pqr.g(r)));
        }
        hashMap.put("SUGGEST_TITLE", this.y);
        hashMap.put("SUGGEST_CONTENT", this.q);
        LogUtil.a("SleepScoringProvider", hashMap);
    }

    private void d(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepScoringProvider", "setPhoneSleepData");
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
            hashMap.put("LEVEL_TITLE", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_sleep_quality));
        } else {
            hashMap.put("LEVEL_TITLE", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_fitness_core_sleep_avg_night_sleep));
        }
        int y = fdpVar.f().y();
        hashMap.put("LEVEL_VALUE", pqr.f(y));
        hashMap.put("LEVEL_VALUE_COLOR", Integer.valueOf(pqr.g(y)));
        hashMap.put("SUGGEST_TITLE", this.y);
        hashMap.put("SUGGEST_CONTENT", this.q);
        String str = this.g;
        if (str != null) {
            hashMap.put("DETECT_ABNORMAL", str);
        }
        String str2 = this.f;
        if (str2 != null) {
            hashMap.put("DETECT_ABNORMAL_JUMP", str2);
            a(hashMap);
        }
        LogUtil.a("SleepScoringProvider", hashMap);
    }

    private void a(Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepScoringProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("COMMON_CLICK_EVENT".equals(str)) {
                    mtp.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getActivity(), "#/SleepSetting");
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void c(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepScoringProvider", "setCoreSleepData");
        int ar = fdpVar.j().ar();
        if (ar == 0) {
            hashMap.put("SCORING_VALUE", "--");
            hashMap.put("SCORING_UNIT", null);
            hashMap.put("SCORING_RATING", 0);
            hashMap.put("SCORING_DESC", null);
        } else {
            hashMap.put("SCORING_VALUE", UnitUtil.e(ar, 1, 0));
            hashMap.put("SCORING_UNIT", context.getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, ar));
            hashMap.put("SCORING_RATING", Integer.valueOf((ar + 9) / 10));
            hashMap.put("SCORING_DESC", this.w);
        }
        hashMap.put("HARVARD_LOGO_TEXT", null);
        hashMap.put("HARVARD_IMAGE", null);
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
            if (VersionControlUtil.isSupportSleepManagement()) {
                hashMap.put("SCORING_TITLE", null);
            } else {
                hashMap.put("SCORING_TITLE", context.getString(R$string.IDS_hw_show_main_home_page_sleep_score));
            }
        } else {
            hashMap.put("SCORING_TITLE", context.getString(R$string.IDS_fitness_core_sleep_avg_night_sleep));
        }
        hashMap.remove("DETECT_ABNORMAL");
        hashMap.remove("DETECT_ABNORMAL_JUMP");
        hashMap.put("SUGGEST_TITLE", this.y);
        hashMap.put("SUGGEST_CONTENT", this.q);
        LogUtil.b("SleepScoringProvider", hashMap);
    }

    private void b(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepScoringProvider", "setCommonSleepData");
        hashMap.put("IS_PHONE_SLEEP", false);
        hashMap.put("IS_CORE_SLEEP", false);
        SleepViewConstants.ViewTypeEnum e2 = fdpVar.e();
        if (e2 == SleepViewConstants.ViewTypeEnum.MONTH || e2 == SleepViewConstants.ViewTypeEnum.WEEK) {
            hashMap.put("COMMON_TITLE_TEXT", context.getString(R$string.IDS_fitness_average_sleep_data_title));
            d(context, hashMap, fdpVar.c().h(), e2);
        } else {
            d(context, hashMap, this.r ? fdpVar.f().h() : fdpVar.c().h(), e2);
        }
    }

    private void d(Context context, HashMap<String, Object> hashMap, int i, SleepViewConstants.ViewTypeEnum viewTypeEnum) {
        String string;
        SpannableString spannableString;
        LogUtil.a("SleepScoringProvider", "setSleepDataToCommonSleep");
        this.u = Typeface.createFromAsset(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        int i2 = i / 60;
        if (viewTypeEnum == SleepViewConstants.ViewTypeEnum.DAY && i2 >= 15) {
            scx.e(context, new SimpleDateFormat("yyyy-MM-dd").format(new Date(this.t.g())), "EXCE_NORMALSLEEP__TIME_ERROR");
        }
        int i3 = i % 60;
        String e2 = UnitUtil.e(i2, 1, 0);
        String e3 = UnitUtil.e(i3, 1, 0);
        String d2 = d(R.plurals._2130903200_res_0x7f0300a0, i3);
        if (i2 == 0) {
            spannableString = new SpannableString(d2);
            int indexOf = i3 != 0 ? d2.indexOf(e3) : d2.indexOf("--");
            int length = i3 != 0 ? e3.length() : 2;
            if (indexOf >= 0) {
                drz_(spannableString, indexOf, length);
                spannableString.setSpan(new AbsoluteSizeSpan(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363007_res_0x7f0a04bf)), indexOf, length + indexOf, 17);
            }
        } else {
            String d3 = d(R.plurals._2130903199_res_0x7f03009f, i2);
            if (LanguageUtil.y(context)) {
                string = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_three_parts, d3, "و ", d2);
            } else {
                string = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(com.huawei.ui.commonui.R$string.IDS_two_parts, d3, d2);
            }
            int lastIndexOf = string.lastIndexOf(e3);
            int indexOf2 = string.indexOf(e2);
            SpannableString spannableString2 = new SpannableString(string);
            dry_(lastIndexOf, indexOf2, spannableString2, e3, e2);
            spannableString = spannableString2;
        }
        hashMap.put("COMMON_SLEEP_TEXT", spannableString);
        LogUtil.a("SleepScoringProvider", hashMap);
    }

    private String d(int i, int i2) {
        try {
            return com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getQuantityString(i, i2, UnitUtil.e(i2, 1, 0));
        } catch (IllegalFormatConversionException e2) {
            LogUtil.h("SleepScoringProvider", "getQuantityValue() resId = ", Integer.valueOf(i), " IllegalFormatConversionException = ", e2);
            return "";
        }
    }

    private void drz_(SpannableString spannableString, int i, int i2) {
        if (this.u == null) {
            LogUtil.c("SleepScoringProvider", "custom typeface is null.,return");
        } else if (Build.VERSION.SDK_INT >= 28) {
            spannableString.setSpan(new HarmonyOsTypefaceSpan(this.u), i, i2 + i, 17);
        } else {
            spannableString.setSpan(new TypefaceSpan(Constants.FONT), i, i2 + i, 17);
        }
    }

    private void dry_(int i, int i2, SpannableString spannableString, String str, String str2) {
        if (i >= 0) {
            drz_(spannableString, i, str.length());
            spannableString.setSpan(new AbsoluteSizeSpan(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363007_res_0x7f0a04bf)), i, str.length() + i, 17);
        }
        if (i2 >= 0) {
            drz_(spannableString, i2, str2.length());
            spannableString.setSpan(new AbsoluteSizeSpan(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363007_res_0x7f0a04bf)), i2, str2.length() + i2, 17);
        }
    }

    private void g(fdp fdpVar) {
        LogUtil.a("SleepScoringProvider", "requestSleepData");
        b();
        h(fdpVar);
    }

    private void h(fdp fdpVar) {
        if (this.m == null) {
            LogUtil.b("SleepScoringProvider", "requestSuggestData, but mInteractor is null");
            return;
        }
        int i = AnonymousClass3.e[fdpVar.e().ordinal()];
        if (i == 1) {
            this.p.e(this, fdpVar);
            return;
        }
        if (i == 2) {
            this.m.b(a(new Date(fdpVar.g())), 13, new a());
        } else {
            if (i != 3) {
                return;
            }
            this.m.c(new Date(jdl.s(fdpVar.g())), new Date(jdl.a(fdpVar.g())), new a());
        }
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepScoringProvider$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] c;
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[SleepViewConstants.ViewTypeEnum.values().length];
            e = iArr;
            try {
                iArr[SleepViewConstants.ViewTypeEnum.DAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[SleepViewConstants.ViewTypeEnum.WEEK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[SleepViewConstants.ViewTypeEnum.MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SleepViewConstants.SleepTypeEnum.values().length];
            c = iArr2;
            try {
                iArr2[SleepViewConstants.SleepTypeEnum.PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[SleepViewConstants.SleepTypeEnum.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private Date a(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(6, 6);
        return calendar.getTime();
    }

    public Date d(Date date) {
        return e(date).getTime();
    }

    public Date c(Date date) {
        Calendar e2 = e(date);
        e2.add(7, 6);
        return e2.getTime();
    }

    private Calendar e(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(7, calendar.get(7) == 1 ? -6 : 2 - calendar.get(7));
        return calendar;
    }

    private void b() {
        if (Utils.l()) {
            return;
        }
        pqp.b(new CommonUiBaseResponse() { // from class: pov
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public final void onResponse(int i, Object obj) {
                SleepScoringProvider.this.e(i, obj);
            }
        });
    }

    public /* synthetic */ void e(int i, Object obj) {
        if (obj instanceof ppj) {
            d(i, this.t.j().ar(), (ppj) obj);
        }
    }

    private void d(int i, int i2, ppj ppjVar) {
        if (i == -1) {
            this.w = null;
            return;
        }
        if (ppjVar.c() == null) {
            this.w = null;
        } else {
            this.w = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R$string.IDS_core_sleep_user_rate, UnitUtil.e(r4.get(String.valueOf(i2)).intValue(), 2, 0));
        }
        this.p.e(this, this.t);
    }

    static class e implements IBaseResponseCallback {
        private final WeakReference<SleepScoringProvider> b;

        private e(SleepScoringProvider sleepScoringProvider) {
            this.b = new WeakReference<>(sleepScoringProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("SleepScoringProvider", "PhoneSuggestDataResponse errorCode: ", Integer.valueOf(i), ", data: ", obj);
            SleepScoringProvider sleepScoringProvider = this.b.get();
            if (sleepScoringProvider == null) {
                return;
            }
            fdp fdpVar = sleepScoringProvider.t;
            if (i != 0) {
                sleepScoringProvider.h = true;
                sleepScoringProvider.r = true;
                sleepScoringProvider.p.e(sleepScoringProvider, fdpVar);
                return;
            }
            HashMap hashMap = (HashMap) obj;
            int intValue = ((Integer) hashMap.get("sleep_tag")).intValue();
            int intValue2 = ((Integer) hashMap.get("sdk_type")).intValue();
            LogUtil.a("SleepScoringProvider", "sleepTagMap: ", hashMap.toString());
            if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
                String b = pqr.b(fdpVar.f().y());
                int h = fdpVar.f().h();
                LogUtil.a("SleepScoringProvider", "mNightTotalSleepTime: ", Integer.valueOf(h));
                String b2 = pqr.b(intValue, intValue2, h > 600, h);
                String c = pqr.c(intValue);
                String e = pqr.e(intValue);
                sleepScoringProvider.y = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_two_parts, b, b2);
                sleepScoringProvider.q = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R$string.IDS_two_parts, c, e);
                sleepScoringProvider.b(fdpVar);
            }
        }
    }

    static class a implements CommonUiBaseResponse {
        private final WeakReference<SleepScoringProvider> c;

        private a(SleepScoringProvider sleepScoringProvider) {
            this.c = new WeakReference<>(sleepScoringProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            SleepScoringProvider sleepScoringProvider = this.c.get();
            if (sleepScoringProvider == null || i == -1 || !(obj instanceof Map)) {
                return;
            }
            fdp fdpVar = sleepScoringProvider.t;
            Map map = (Map) obj;
            if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK || fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH) {
                if (TextUtils.isEmpty((CharSequence) map.get("SUGGEST_TITLE")) || TextUtils.isEmpty((CharSequence) map.get("SUGGEST_CONTENT"))) {
                    sleepScoringProvider.y = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R$string.IDS_core_sleep_suggesttion_nullstatus_tital);
                    if (fdpVar.j().h() > 180) {
                        sleepScoringProvider.q = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R$string.IDS_core_sleep_suggesttion_nullstatus_content3);
                    } else {
                        sleepScoringProvider.q = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R$string.IDS_core_sleep_suggesttion_nullstatus_content, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903265_res_0x7f0300e1, 3, 3));
                    }
                } else {
                    sleepScoringProvider.y = (String) map.get("SUGGEST_TITLE");
                    sleepScoringProvider.q = (String) map.get("SUGGEST_CONTENT");
                    sleepScoringProvider.p.e(sleepScoringProvider, fdpVar);
                    return;
                }
            } else if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
                if (fdpVar.j().ar() == 0) {
                    sleepScoringProvider.y = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R$string.IDS_core_sleep_suggesttion_nullstatus_tital);
                    sleepScoringProvider.q = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R$string.IDS_core_sleep_suggesttion_novalidData_content);
                } else {
                    sleepScoringProvider.y = (String) map.get("SUGGEST_TITLE");
                    sleepScoringProvider.q = (String) map.get("SUGGEST_CONTENT");
                }
            }
            sleepScoringProvider.p.e(sleepScoringProvider, fdpVar);
        }
    }
}
