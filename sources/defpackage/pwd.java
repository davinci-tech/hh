package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.health.sport.utils.FitnessDataQueryDefine;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.ResultUtils;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData;
import defpackage.pwd;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class pwd {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f16290a = new Object();
    private fdp k;
    private boolean l;
    private Map<String, SleepViewConstants.SleepTypeEnum> b = new ConcurrentHashMap();
    private CopyOnWriteArrayList<pvz> e = new CopyOnWriteArrayList<>();
    private fdp s = new fdp(SleepViewConstants.ViewTypeEnum.UNKNOWN);
    private volatile int h = 0;
    private volatile String i = "";
    private String g = "";
    private String x = "";
    private boolean o = false;
    private volatile boolean m = false;
    private volatile boolean j = false;
    private volatile boolean n = false;
    private List<String> p = new ArrayList(16);
    private List<pvz> c = new ArrayList(16);
    private List<pvz> q = new ArrayList(16);
    private List<pvz> v = new ArrayList(16);
    private List<pvz> u = new ArrayList(16);
    private List<pvz> r = new ArrayList(16);
    private Context d = BaseApplication.getContext();
    private pxb f = new pxb();
    private pwx t = pwx.d(this.d);

    public List<String> a() {
        return (List) ResultUtils.a(this.p);
    }

    public void d(Date[] dateArr, String str, int i, boolean z, CommonUiBaseResponse commonUiBaseResponse) {
        if (dateArr == null || dateArr.length != 2) {
            LogUtil.h("FitnessCoreSleepDetailInteractor", "time range is invalid");
            return;
        }
        Date date = dateArr[0];
        Date date2 = dateArr[1];
        ReleaseLogUtil.e("R_Sleep_FitnessCoreSleepDetailInteractorFitnessCoreSleepDetailInteractor", "requestCoreSleepDetailUiData paraUiDataType = ", Integer.valueOf(i), " startTime is ", date, " endTime is ", date2, " isUseCache ", Boolean.valueOf(z));
        this.j = false;
        this.m = false;
        this.n = false;
        if (i == 1) {
            e(date, z, commonUiBaseResponse);
            return;
        }
        if (i == 2) {
            d(date, date2, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_HISTOGRAM, commonUiBaseResponse);
            e(date, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_WEEK_HISTOGRAM, new c(str));
        } else if (i == 3) {
            d(date, date2, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_HISTOGRAM, commonUiBaseResponse);
            e(date, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_MONTH_HISTOGRAM, new c(str));
        } else {
            if (i != 4) {
                return;
            }
            d(date, date2, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_HISTOGRAM, commonUiBaseResponse);
            e(date, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_YEAR_HISTOGRAM, new c(str));
        }
    }

    class c implements CommonUiBaseResponse {

        /* renamed from: a, reason: collision with root package name */
        private String f16296a;

        public c(String str) {
            this.f16296a = str;
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "SleepDeviceResponse ", obj);
            if (obj instanceof Map) {
                Map map = (Map) obj;
                ObserverManagerUtil.c("SLEEP_DEVICE_LIST" + this.f16296a, qnl.d((List<HiHealthClient>) map.get("core"), (List<HiHealthClient>) map.get("normal")));
            }
        }
    }

    public void a(Date date, Date date2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("FitnessCoreSleepDetailInteractor", "requestCoreSleepDetail startTime = ", date);
        fcd.c().b(date, date2, new int[]{3, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, 3}, iBaseResponseCallback);
    }

    private void e(final Date date, final boolean z, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("FitnessCoreSleepDetailInteractor", "handleDayType enter, start time = ", date);
        final pxa pxaVar = new pxa();
        a(date, pxaVar, commonUiBaseResponse);
        a(date, pxaVar, z, commonUiBaseResponse);
        final List<pwb> d = fch.d(date);
        a(z, d, commonUiBaseResponse, pxaVar);
        e(date, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_DAY_HISTOGRAM, new CommonUiBaseResponse() { // from class: pwd.3
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                pwd.this.n = true;
                CopyOnWriteArrayList<pwb> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
                if (obj instanceof Map) {
                    Map map = (Map) obj;
                    List list = (List) map.get("core");
                    qnl.c((List<HiHealthClient>) list);
                    copyOnWriteArrayList = new CopyOnWriteArrayList<>(qnl.d((List<HiHealthClient>) list, (List<HiHealthClient>) map.get("normal")));
                }
                if (copyOnWriteArrayList.size() == 1) {
                    copyOnWriteArrayList.clear();
                    LogUtil.a("FitnessCoreSleepDetailInteractor", "only one device not show icon");
                }
                LogUtil.a("FitnessCoreSleepDetailInteractor", "day device got ", copyOnWriteArrayList);
                if (z && fch.e(d, copyOnWriteArrayList)) {
                    fch.b(date, copyOnWriteArrayList);
                    pxaVar.d(copyOnWriteArrayList);
                    pwd.this.b(commonUiBaseResponse, 100, pxaVar);
                } else {
                    if (z) {
                        return;
                    }
                    pxaVar.d(copyOnWriteArrayList);
                    pwd.this.b(commonUiBaseResponse, 100, pxaVar);
                }
            }
        });
    }

    private void a(boolean z, List<pwb> list, CommonUiBaseResponse commonUiBaseResponse, pxa pxaVar) {
        if (koq.c(list) && z) {
            this.n = true;
            CopyOnWriteArrayList<pwb> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            copyOnWriteArrayList.addAll(list);
            LogUtil.a("FitnessCoreSleepDetailInteractor", "day device got cache ", copyOnWriteArrayList);
            pxaVar.d(copyOnWriteArrayList);
            b(commonUiBaseResponse, 100, pxaVar);
        }
    }

    private void a(Date date, final pxa pxaVar, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("FitnessCoreSleepDetailInteractor", "go req detail");
        b(date, date, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_HISTOGRAM, new CommonUiBaseResponse() { // from class: pwd.1
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("FitnessCoreSleepDetailInteractor", "requestCoreSleepHistogramData back,errorCode = ", Integer.valueOf(i));
                pwd.this.j = true;
                LogUtil.a("FitnessCoreSleepDetailInteractor", "isDetailBack ", Boolean.valueOf(pwd.this.j), "isStatBack ", Boolean.valueOf(pwd.this.m));
                CopyOnWriteArrayList<pvz> copyOnWriteArrayList = (CopyOnWriteArrayList) obj;
                if (!koq.c(copyOnWriteArrayList)) {
                    pxaVar.b(new CopyOnWriteArrayList<>());
                    LogUtil.b("FitnessCoreSleepDetailInteractor", "sleepViewDataList is null");
                } else {
                    pxaVar.b(copyOnWriteArrayList);
                }
                pwd.this.b(commonUiBaseResponse, i, pxaVar);
            }
        });
    }

    private void a(Date date, final pxa pxaVar, boolean z, final CommonUiBaseResponse commonUiBaseResponse) {
        c(date, z, new CommonUiBaseResponse() { // from class: pwd.5
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("FitnessCoreSleepDetailInteractor", "requestCoreSleepDayTotalData back, errorCode = ", Integer.valueOf(i));
                pwd.this.m = true;
                LogUtil.a("FitnessCoreSleepDetailInteractor", "isDetailBack ", Boolean.valueOf(pwd.this.j), "isStatBack ", Boolean.valueOf(pwd.this.m));
                List list = (List) obj;
                if (!koq.c(list)) {
                    pxaVar.b(new fdp(SleepViewConstants.ViewTypeEnum.DAY));
                    LogUtil.b("FitnessCoreSleepDetailInteractor", "sleepViewDataList is null");
                } else {
                    try {
                        pxaVar.b(((fdp) list.get(0)).clone());
                    } catch (ClassCastException unused) {
                        LogUtil.b("FitnessCoreSleepDetailInteractor", "after reconstruct, type is changed");
                    }
                }
                pwd.this.b(commonUiBaseResponse, i, pxaVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CommonUiBaseResponse commonUiBaseResponse, int i, pxa pxaVar) {
        if (this.n && i == 100) {
            this.n = false;
            c(pxaVar);
            commonUiBaseResponse.onResponse(i, pxaVar);
        } else if (this.j && this.m && commonUiBaseResponse != null) {
            c(pxaVar);
            commonUiBaseResponse.onResponse(i, pxaVar);
        }
    }

    private void c(pxa pxaVar) {
        pxaVar.c(this.i);
        pxaVar.e(this.h);
        pxaVar.e(a());
        pxaVar.d(this.o);
    }

    private void d(Date date, Date date2, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("FitnessCoreSleepDetailInteractor", "handleWeekMonthYearType enter, start time = ", date);
        b(date, date2, fitnessQueryId, new CommonUiBaseResponse() { // from class: pwd.4
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                ReleaseLogUtil.e("R_Sleep_FitnessCoreSleepDetailInteractorFitnessCoreSleepDetailInteractor", "handleWeekMonthYearType histogram back, errorCode = ", Integer.valueOf(i));
                CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                if (commonUiBaseResponse2 != null) {
                    commonUiBaseResponse2.onResponse(i, obj);
                }
            }
        });
    }

    private void e(List<fdp> list) {
        if (list != null) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "Enter processSleepHistogramData paramListData.size: ", Integer.valueOf(list.size()));
            for (int i = 0; i < list.size(); i++) {
                fdp fdpVar = list.get(i);
                e(i, fdpVar);
                LogUtil.a("FitnessCoreSleepDetailInteractor", "sleepTotalData.getType():", fdpVar.i());
            }
            return;
        }
        LogUtil.a("FitnessCoreSleepDetailInteractor", "paramListData is null! ");
    }

    private void e(int i, fdp fdpVar) {
        fdi c2;
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            c2 = fdpVar.f();
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            c2 = fdpVar.d();
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            c2 = fdpVar.j();
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
            c2 = fdpVar.c();
        } else {
            LogUtil.b("FitnessCoreSleepDetailInteractor", "wrong type: ", fdpVar.i());
            return;
        }
        pvz pvzVar = new pvz();
        pvzVar.a(i);
        int i2 = i + 1;
        pvzVar.e(i2);
        pvzVar.d(c2.f());
        pvzVar.e(c2.e());
        pvzVar.b(c2.n());
        pvz pvzVar2 = new pvz();
        pvzVar2.a(i);
        pvzVar2.e(i2);
        pvz pvzVar3 = new pvz();
        pvzVar3.a(i);
        pvzVar3.e(i2);
        pvz pvzVar4 = new pvz();
        pvzVar4.a(i);
        pvzVar4.e(i2);
        pvz pvzVar5 = new pvz();
        pvzVar5.a(i);
        pvzVar5.e(i2);
        int[] iArr = new int[5];
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            iArr[0] = fdpVar.f().p();
            iArr[1] = fdpVar.f().u();
            iArr[2] = fdpVar.f().w();
            iArr[4] = fdpVar.f().z();
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            iArr[0] = fdpVar.j().x();
            iArr[1] = fdpVar.j().aw();
            iArr[2] = fdpVar.j().ap();
            iArr[3] = fdpVar.j().aq();
            iArr[4] = fdpVar.j().ay();
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
            iArr[0] = fdpVar.j().x();
            iArr[1] = fdpVar.j().aw();
            iArr[4] = fdpVar.j().ay();
        }
        pvzVar.b(iArr[0]);
        this.c.add(pvzVar);
        pvzVar3.b(iArr[1]);
        this.q.add(pvzVar3);
        pvzVar2.b(iArr[2]);
        this.r.add(pvzVar2);
        pvzVar4.b(iArr[3]);
        this.u.add(pvzVar4);
        pvzVar5.b(iArr[4]);
        this.v.add(pvzVar5);
    }

    private void c(List<fdp> list, boolean z, CommonUiBaseResponse commonUiBaseResponse) {
        if (z && c(list) && commonUiBaseResponse != null) {
            m();
            a(list);
            commonUiBaseResponse.onResponse(0, list);
            LogUtil.a("FitnessCoreSleepDetailInteractor", "querySummaryData use cache, sleepViewData is ", this.s.toString());
        }
    }

    public void c(Date date, final boolean z, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.c("FitnessCoreSleepDetailInteractor", "requestCoreSleepDayTotalData, start time = ", date);
        final String valueOf = String.valueOf(DateFormatUtil.b(date.getTime()));
        final List<fdp> e = fch.e(valueOf);
        c(e, z, commonUiBaseResponse);
        e(date, date, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC, FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_UNSPECIFIC_SLEEP_TIME, new CommonUiBaseResponse() { // from class: pwd.9
            /* JADX WARN: Code restructure failed: missing block: B:18:0x0053, code lost:
            
                if (defpackage.fch.e(r4, r0) == false) goto L18;
             */
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onResponse(int r5, java.lang.Object r6) {
                /*
                    r4 = this;
                    java.lang.String r0 = "requestGetCoreSleepDetailData errorCode = "
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
                    java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                    java.lang.String r1 = "R_Sleep_FitnessCoreSleepDetailInteractorFitnessCoreSleepDetailInteractor"
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
                    com.huawei.ui.commonui.base.CommonUiBaseResponse r0 = r2
                    java.lang.String r1 = "FitnessCoreSleepDetailInteractor"
                    if (r0 != 0) goto L1f
                    java.lang.String r5 = "updateResponse is null"
                    java.lang.Object[] r5 = new java.lang.Object[]{r5}
                    com.huawei.hwlogsmodel.LogUtil.c(r1, r5)
                    return
                L1f:
                    pwd r0 = defpackage.pwd.this
                    defpackage.pwd.d(r0)
                    if (r5 != 0) goto L8e
                    if (r6 == 0) goto L8e
                    r0 = r6
                    java.util.List r0 = (java.util.List) r0
                    boolean r2 = defpackage.koq.c(r0)
                    if (r2 == 0) goto L36
                    pwd r2 = defpackage.pwd.this
                    defpackage.pwd.e(r2, r0)
                L36:
                    pwd r2 = defpackage.pwd.this
                    fdp r2 = defpackage.pwd.a(r2)
                    java.lang.String r2 = r2.toString()
                    java.lang.String r3 = "request summary data back,"
                    java.lang.Object[] r2 = new java.lang.Object[]{r3, r2}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r2)
                    boolean r2 = r3
                    if (r2 == 0) goto L97
                    java.util.List r2 = r4     // Catch: android.os.BadParcelableException -> L56
                    boolean r2 = defpackage.fch.e(r2, r0)     // Catch: android.os.BadParcelableException -> L56
                    if (r2 != 0) goto L7a
                    goto L5f
                L56:
                    java.lang.String r2 = "BadParcelableException ex"
                    java.lang.Object[] r2 = new java.lang.Object[]{r2}
                    com.huawei.hwlogsmodel.LogUtil.b(r1, r2)
                L5f:
                    pwd r2 = defpackage.pwd.this
                    java.util.List r3 = r4
                    boolean r2 = defpackage.pwd.b(r2, r3)
                    if (r2 == 0) goto L7a
                    pwd r5 = defpackage.pwd.this
                    java.util.List r6 = r4
                    defpackage.pwd.e(r5, r6)
                    java.lang.String r5 = "summary data is the same with sleepDetailData"
                    java.lang.Object[] r5 = new java.lang.Object[]{r5}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r5)
                    return
                L7a:
                    java.lang.String r2 = "summary data is different from sleepDetailData"
                    java.lang.Object[] r2 = new java.lang.Object[]{r2}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r2)
                    java.lang.String r1 = r5
                    defpackage.fch.d(r1, r0)
                    com.huawei.ui.commonui.base.CommonUiBaseResponse r0 = r2
                    r0.onResponse(r5, r6)
                    return
                L8e:
                    java.lang.String r0 = "requestGetCoreSleepDetailData else."
                    java.lang.Object[] r0 = new java.lang.Object[]{r0}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
                L97:
                    java.lang.String r0 = "updateResponse.onResponse !"
                    java.lang.Object[] r0 = new java.lang.Object[]{r0}
                    com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
                    com.huawei.ui.commonui.base.CommonUiBaseResponse r0 = r2
                    r0.onResponse(r5, r6)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.pwd.AnonymousClass9.onResponse(int, java.lang.Object):void");
            }
        });
        LogUtil.a("FitnessCoreSleepDetailInteractor", "Leave requestSleepDetailTotalData.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(List<fdp> list) {
        return koq.c(list) && list.get(0) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<fdp> list) {
        try {
            this.s = list.get(0);
        } catch (ClassCastException unused) {
            LogUtil.b("FitnessCoreSleepDetailInteractor", "after reconstruct, type is changed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.s = new fdp(SleepViewConstants.ViewTypeEnum.UNKNOWN);
        LogUtil.a("FitnessCoreSleepDetailInteractor", "resetSleepTotalData");
    }

    public Map<String, SleepViewConstants.SleepTypeEnum> c() {
        return this.b;
    }

    public List<pvz> b() {
        return (List) ResultUtils.a(this.c);
    }

    public List<pvz> h() {
        return (List) ResultUtils.a(this.q);
    }

    public List<pvz> i() {
        return (List) ResultUtils.a(this.v);
    }

    public List<pvz> j() {
        return (List) ResultUtils.a(this.u);
    }

    public List<pvz> e() {
        return (List) ResultUtils.a(this.r);
    }

    public void b(Date date, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        if (this.t != null) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "enter requestSleepSuggestString()");
            this.t.a(date, i, new CommonUiBaseResponse() { // from class: pwh
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public final void onResponse(int i2, Object obj) {
                    pwd.this.e(commonUiBaseResponse, i2, obj);
                }
            });
        }
        LogUtil.c("FitnessCoreSleepDetailInteractor", "leave getSleepSuggestString()");
    }

    /* synthetic */ void e(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        LogUtil.a("FitnessCoreSleepDetailInteractor", "getSleepAdvice day or week");
        d(i, obj, commonUiBaseResponse);
    }

    public void c(Date date, Date date2, final CommonUiBaseResponse commonUiBaseResponse) {
        if (this.t != null) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "enter requestSleepMonthSuggestString()");
            this.t.c(date, date2, new CommonUiBaseResponse() { // from class: pwf
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public final void onResponse(int i, Object obj) {
                    pwd.this.d(commonUiBaseResponse, i, obj);
                }
            });
        }
        LogUtil.c("FitnessCoreSleepDetailInteractor", "leave getSleepSuggestString()");
    }

    /* synthetic */ void d(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        LogUtil.a("FitnessCoreSleepDetailInteractor", "getSleepAdvice month");
        d(i, obj, commonUiBaseResponse);
    }

    private void d(int i, Object obj, CommonUiBaseResponse commonUiBaseResponse) {
        HashMap hashMap = new HashMap();
        if (i == 0 && (obj instanceof CoreSleepTotalData)) {
            CoreSleepTotalData coreSleepTotalData = (CoreSleepTotalData) obj;
            hashMap.put("SUGGEST_TITLE", c(coreSleepTotalData.getAdNum0(), coreSleepTotalData));
            hashMap.put("SUGGEST_CONTENT", this.f.i(coreSleepTotalData.getAdNum1()));
        } else {
            hashMap.put("SUGGEST_TITLE", "");
            hashMap.put("SUGGEST_CONTENT", "");
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(i, hashMap);
        }
    }

    private String c(int i, CoreSleepTotalData coreSleepTotalData) {
        if (i < 12000) {
            return c(i);
        }
        return a(i, coreSleepTotalData);
    }

    private String a(int i, CoreSleepTotalData coreSleepTotalData) {
        if (coreSleepTotalData == null) {
            return "";
        }
        switch ((i % 1000) / 100) {
            case 0:
                return this.d.getResources().getString(pwt.e(i), this.f.c(2));
            case 1:
                return this.f.b(i, coreSleepTotalData);
            case 2:
                return this.f.g(i, coreSleepTotalData);
            case 3:
                return this.f.h(i, coreSleepTotalData);
            case 4:
                return this.f.e(i, coreSleepTotalData);
            case 5:
                return this.f.c(i, coreSleepTotalData);
            case 6:
                return this.f.d(i, coreSleepTotalData);
            case 7:
                return this.f.a(i, coreSleepTotalData);
            case 8:
                return this.f.a(i, coreSleepTotalData, this.s.j().h(), this.s.j().ar());
            default:
                return this.d.getResources().getString(pwt.e(i));
        }
    }

    private String c(int i) {
        LogUtil.a("FitnessCoreSleepDetailInteractor", " combineDayString index = ", Integer.valueOf(i));
        String string = this.d.getResources().getString(pwt.e(i));
        if (a(i, pwt.g())) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", " ContainIndex totalSleepIndex");
            return String.format(string, scn.a(fcj.b(this.s).h()));
        }
        if (a(i, pwt.d())) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", " ContainIndex dreamRateIndex");
            return String.format(string, UnitUtil.e(this.s.j().ao(), 2, 0));
        }
        if (a(i, pwt.a())) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", " ContainIndex fallAsleepIndex");
            return String.format(string, this.g);
        }
        if (a(i, pwt.i())) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", " ContainIndex wakeIndex");
            if (this.l) {
                LogUtil.a("FitnessCoreSleepDetailInteractor", "late with 8:00");
                return "";
            }
            return String.format(string, this.x);
        }
        if (a(i, pwt.f())) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", " ContainIndex wakeUpTimesIndex");
            String quantityString = this.d.getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, l(), UnitUtil.e(l(), 1, 0));
            LogUtil.a("FitnessCoreSleepDetailInteractor", "wakeUpTimes: ", quantityString);
            return String.format(string, quantityString);
        }
        if (a(i, pwt.c())) {
            if (LanguageUtil.bp(this.d) || LanguageUtil.y(this.d)) {
                StringBuffer stringBuffer = new StringBuffer(UnitUtil.e(g(), 1, 0));
                stringBuffer.insert(0, "%");
                return String.format(string, stringBuffer);
            }
            return String.format(string, UnitUtil.e(g(), 2, 0));
        }
        if (a(i, pwt.b())) {
            return String.format(string, UnitUtil.e(0.0d, 1, 0));
        }
        if (!a(i, pwt.e())) {
            return string;
        }
        return String.format(string, UnitUtil.e(this.s.j().w(), 1, 0) + " " + this.d.getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, this.s.j().w()));
    }

    private int g() {
        if (this.s.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            return this.s.f().t();
        }
        if (this.s.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
            return this.s.c().p();
        }
        if (this.s.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            return this.s.j().v();
        }
        return 0;
    }

    private int l() {
        if (this.s.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            return this.s.f().ab();
        }
        if (this.s.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            return this.s.j().bc();
        }
        if (this.s.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
            return this.s.c().t();
        }
        return 0;
    }

    private boolean a(int i, int[] iArr) {
        LogUtil.a("FitnessCoreSleepDetailInteractor", " isContainIndex()  targetIndex = ", Integer.valueOf(i));
        for (int i2 : iArr) {
            if (i == i2) {
                LogUtil.a("FitnessCoreSleepDetailInteractor", " isContainIndex()  return true ");
                return true;
            }
        }
        LogUtil.a("FitnessCoreSleepDetailInteractor", " isContainIndex()  return false ");
        return false;
    }

    private void b(List<pvz> list) {
        this.g = "";
        this.x = "";
        if (list == null || list.size() == 0) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "sleepHistogramData size = 0");
            return;
        }
        Map<String, Integer> b = pxb.b(list);
        if (b == null) {
            LogUtil.h("FitnessCoreSleepDetailInteractor", "timeMap is null!");
            return;
        }
        int intValue = b.get("core_sleep_start_time_key").intValue();
        int i = intValue / 60;
        int i2 = i + 20;
        if (i2 >= 24) {
            i2 = i - 4;
        }
        String a2 = scn.a(scn.t(i2) + ":" + scn.t(intValue % 60));
        this.g = a2;
        LogUtil.c("FitnessCoreSleepDetailInteractor", "advice mFallAsleepTime = ", a2);
        int intValue2 = b.get("core_sleep_end_time_key").intValue();
        int i3 = intValue2 / 60;
        int i4 = i3 + 20;
        if (i4 >= 24) {
            i4 = i3 - 4;
        }
        String a3 = scn.a(scn.t(i4) + ":" + scn.t(intValue2 % 60));
        this.x = a3;
        this.l = i4 >= 7;
        LogUtil.c("FitnessCoreSleepDetailInteractor", "advice mWakeTime = ", a3);
    }

    private void b(Date date, final Date date2, final FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, final CommonUiBaseResponse commonUiBaseResponse) {
        Date date3;
        if (fitnessQueryId == FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_HISTOGRAM) {
            date3 = new Date(jdl.b(date.getTime(), -7));
        } else {
            if (fitnessQueryId == FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_HISTOGRAM) {
                if (jdl.d(date.getTime(), jdl.s(date.getTime()))) {
                    date3 = new Date(jdl.u(date.getTime()));
                } else {
                    date3 = new Date(jdl.b(date.getTime(), -30));
                }
            }
            Date date4 = date;
            ReleaseLogUtil.e("R_Sleep_FitnessCoreSleepDetailInteractorFitnessCoreSleepDetailInteractor", "getData queryId = ", fitnessQueryId, "getData paraStartTime = ", date4);
            e(date4, date2, fitnessQueryId, FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_UNSPECIFIC_SLEEP_TIME, new CommonUiBaseResponse() { // from class: pwd.8
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i, Object obj) {
                    LogUtil.a("FitnessCoreSleepDetailInteractor", "getData response back.");
                    int i2 = AnonymousClass2.e[fitnessQueryId.ordinal()];
                    if (i2 == 1) {
                        pwd.this.b(i, obj, commonUiBaseResponse);
                        return;
                    }
                    if (i2 == 2 || i2 == 3) {
                        pwd.this.a(i, obj, date2.getTime(), commonUiBaseResponse);
                        return;
                    }
                    if (i2 == 4) {
                        pwd.this.d(i, obj, fitnessQueryId, commonUiBaseResponse);
                        return;
                    }
                    CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                    if (commonUiBaseResponse2 != null) {
                        commonUiBaseResponse2.onResponse(i, obj);
                    }
                }
            });
        }
        date = date3;
        Date date42 = date;
        ReleaseLogUtil.e("R_Sleep_FitnessCoreSleepDetailInteractorFitnessCoreSleepDetailInteractor", "getData queryId = ", fitnessQueryId, "getData paraStartTime = ", date42);
        e(date42, date2, fitnessQueryId, FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_UNSPECIFIC_SLEEP_TIME, new CommonUiBaseResponse() { // from class: pwd.8
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("FitnessCoreSleepDetailInteractor", "getData response back.");
                int i2 = AnonymousClass2.e[fitnessQueryId.ordinal()];
                if (i2 == 1) {
                    pwd.this.b(i, obj, commonUiBaseResponse);
                    return;
                }
                if (i2 == 2 || i2 == 3) {
                    pwd.this.a(i, obj, date2.getTime(), commonUiBaseResponse);
                    return;
                }
                if (i2 == 4) {
                    pwd.this.d(i, obj, fitnessQueryId, commonUiBaseResponse);
                    return;
                }
                CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                if (commonUiBaseResponse2 != null) {
                    commonUiBaseResponse2.onResponse(i, obj);
                }
            }
        });
    }

    public void e(Date date, Date date2, final FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, final FitnessDataQueryDefine.FitnessSleepTotalDataId fitnessSleepTotalDataId, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("FitnessCoreSleepDetailInteractor", "Enter requestGetCoreSleepDetailData");
        if (date == null || date2 == null) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "startTime or endtime is null");
            return;
        }
        synchronized (f16290a) {
            int c2 = FitnessDataQueryDefine.c(fitnessQueryId);
            int a2 = FitnessDataQueryDefine.a(date.getTime(), fitnessQueryId);
            int e = FitnessDataQueryDefine.e(fitnessQueryId);
            LogUtil.a("FitnessCoreSleepDetailInteractor", "requestGetCoreSleepDetailData queryID = " + fitnessQueryId + "unitSize = " + a2 + "  unitCount = " + e + "  unitType = " + c2);
            if (fitnessQueryId == FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_HISTOGRAM || fitnessQueryId == FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_HISTOGRAM) {
                e = jdl.e(date.getTime(), date2.getTime());
                a2 = ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
            }
            fcd.c().b(date, date2, new int[]{c2, a2, e}, new IBaseResponseCallback() { // from class: pwd.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    pwd.this.e(i, obj, fitnessQueryId, fitnessSleepTotalDataId, commonUiBaseResponse);
                }
            });
        }
        LogUtil.a("FitnessCoreSleepDetailInteractor", "Leave requestGetCoreSleepDetailData");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, Object obj, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, FitnessDataQueryDefine.FitnessSleepTotalDataId fitnessSleepTotalDataId, CommonUiBaseResponse commonUiBaseResponse) {
        if (obj != null) {
            ReleaseLogUtil.e("R_Sleep_FitnessCoreSleepDetailInteractorFitnessCoreSleepDetailInteractor", "requestResult onResponse objData != null");
        } else {
            ReleaseLogUtil.e("R_Sleep_FitnessCoreSleepDetailInteractorFitnessCoreSleepDetailInteractor", "requestResult onResponse errCode = ", Integer.valueOf(i));
        }
        switch (AnonymousClass2.e[fitnessQueryId.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 13:
                if (commonUiBaseResponse != null) {
                    commonUiBaseResponse.onResponse(i, obj);
                    break;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                if (i != 0) {
                    if (commonUiBaseResponse != null) {
                        LogUtil.a("FitnessCoreSleepDetailInteractor", "requestResult callback");
                        commonUiBaseResponse.onResponse(i, obj);
                        break;
                    }
                } else {
                    LogUtil.a("FitnessCoreSleepDetailInteractor", "requestResult process getCoreSleepDetail");
                    if (obj != null) {
                        c(fitnessSleepTotalDataId, commonUiBaseResponse, obj);
                        break;
                    }
                }
                break;
            default:
                LogUtil.h("FitnessCoreSleepDetailInteractor", "requestResult process getCoreSleepDetail");
                break;
        }
    }

    private void c(FitnessDataQueryDefine.FitnessSleepTotalDataId fitnessSleepTotalDataId, CommonUiBaseResponse commonUiBaseResponse, Object obj) {
        int i;
        ReleaseLogUtil.e("R_Sleep_FitnessCoreSleepDetailInteractorFitnessCoreSleepDetailInteractor", "Enter processFitnessSleepStatisticData");
        List list = (List) obj;
        int i2 = AnonymousClass2.b[fitnessSleepTotalDataId.ordinal()];
        if (i2 == 1) {
            Iterator it = list.iterator();
            i = 0;
            while (it.hasNext()) {
                i += ((SleepTotalData) it.next()).getTotalSleepTime();
            }
            LogUtil.a("FitnessCoreSleepDetailInteractor", "FITNESS_TOTAL_SLEEP_TIME totalSleepTime = " + i);
        } else if (i2 != 2 && i2 != 3 && i2 != 4) {
            if (commonUiBaseResponse != null) {
                commonUiBaseResponse.onResponse(0, obj);
                return;
            }
            return;
        } else {
            Iterator it2 = list.iterator();
            i = 0;
            while (it2.hasNext()) {
                i += ((SleepTotalData) it2.next()).getTotalSleepTime();
            }
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(0, Integer.valueOf(i));
        }
        ReleaseLogUtil.e("R_Sleep_FitnessCoreSleepDetailInteractorFitnessCoreSleepDetailInteractor", "Leave processFitnessSleepStatisticData");
    }

    /* renamed from: pwd$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[FitnessDataQueryDefine.FitnessSleepTotalDataId.values().length];
            b = iArr;
            try {
                iArr[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_TOTAL_SLEEP_TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_DEEP_SLEEP_TIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_SHALLOW_SLEEP_TIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_WAKEUP_TIME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_UNSPECIFIC_SLEEP_TIME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[FitnessDataQueryDefine.FitnessQueryId.values().length];
            e = iArr2;
            try {
                iArr2[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_HISTOGRAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_HISTOGRAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_HISTOGRAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_HISTOGRAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_STATISTIC.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_STATISTIC.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_STATISTIC.ordinal()] = 8;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC_DETAIL.ordinal()] = 9;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_STATISTIC_DETAIL.ordinal()] = 10;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_STATISTIC_DETAIL.ordinal()] = 11;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_STATISTIC_DETAIL.ordinal()] = 12;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_UP_SLEEP_DETAIL.ordinal()] = 13;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    private void k() {
        this.c.clear();
        this.q.clear();
        this.v.clear();
        this.u.clear();
        this.r.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Object obj, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, CommonUiBaseResponse commonUiBaseResponse) {
        fdp fdpVar;
        DataInfos dataInfos;
        k();
        m();
        this.b.clear();
        if (i == 0 && (obj instanceof List)) {
            List<fdp> list = (List) obj;
            LogUtil.a("FitnessCoreSleepDetailInteractor", " handleWeekAndMonthData retListData.size = ", Integer.valueOf(list.size()));
            e(list);
            if (list.size() < 32) {
                fdpVar = new fdp(SleepViewConstants.ViewTypeEnum.MONTH);
                dataInfos = DataInfos.CoreSleepMonthDetail;
            } else {
                fdpVar = new fdp(SleepViewConstants.ViewTypeEnum.YEAR);
                dataInfos = DataInfos.CoreSleepYearDetail;
            }
            fdpVar.e(list);
            fdpVar.b();
            this.s = fdpVar;
            this.b = fcj.b(list);
            ObserverManagerUtil.c("SLEEP_WEEK_MONTH_YEAR_AVERAGE_DAILY", Integer.valueOf(fcj.c(this.s)), dataInfos);
        }
        if (commonUiBaseResponse != null) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "mDeepSleepHistogramData.size: ", Integer.valueOf(this.c.size()));
            commonUiBaseResponse.onResponse(i, this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object obj, long j, CommonUiBaseResponse commonUiBaseResponse) {
        fdp fdpVar;
        fdp fdpVar2;
        DataInfos dataInfos;
        k();
        m();
        this.b.clear();
        if (i == 0 && (obj instanceof List)) {
            List list = (List) obj;
            int size = list.size() / 2;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i2 = 7;
            if (size == 7) {
                fdpVar = new fdp(SleepViewConstants.ViewTypeEnum.WEEK);
                fdpVar2 = new fdp(SleepViewConstants.ViewTypeEnum.WEEK);
                dataInfos = DataInfos.CoreSleepWeekDetail;
            } else {
                i2 = list.size() - jdl.o(j);
                fdpVar = new fdp(SleepViewConstants.ViewTypeEnum.MONTH);
                fdpVar2 = new fdp(SleepViewConstants.ViewTypeEnum.MONTH);
                dataInfos = DataInfos.CoreSleepMonthDetail;
            }
            for (int i3 = 0; i3 < list.size(); i3++) {
                if (i3 < i2) {
                    arrayList.add((fdp) list.get(i3));
                } else {
                    arrayList2.add((fdp) list.get(i3));
                }
            }
            LogUtil.a("FitnessCoreSleepDetailInteractor", " handleWeekAndMonthLastData retListData.size = ", Integer.valueOf(arrayList2.size()), " alldata.size= ", Integer.valueOf(list.size()));
            fdpVar.e(arrayList2);
            fdpVar2.e(arrayList);
            e(arrayList2);
            this.b = fcj.b(arrayList2);
            fdpVar.b();
            fdpVar2.b();
            this.s = fdpVar;
            this.k = fdpVar2;
            ObserverManagerUtil.c("SLEEP_WEEK_MONTH_YEAR_AVERAGE_DAILY", Integer.valueOf(fcj.c(fdpVar)), dataInfos);
        }
        if (commonUiBaseResponse != null) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "mDeepSleepHistogramData.size: ", Integer.valueOf(this.c.size()));
            commonUiBaseResponse.onResponse(i, this.c);
        }
    }

    public fdp f() {
        return this.s;
    }

    public fdp d() {
        return this.k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(int r22, java.lang.Object r23, com.huawei.ui.commonui.base.CommonUiBaseResponse r24) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pwd.b(int, java.lang.Object, com.huawei.ui.commonui.base.CommonUiBaseResponse):void");
    }

    private void n() {
        this.h = 0;
        this.p.clear();
    }

    private pvz b(List<HiHealthData> list, int i, HiHealthData hiHealthData, List<pvz> list2, pvz pvzVar, int i2) {
        if (pvzVar.b() != -1 && pvzVar.e() != -1) {
            if (i2 != pvzVar.b()) {
                pvzVar.e(i);
                if (i > 0) {
                    pvzVar.c(list.get(i - 1).getEndTime());
                }
                list2.add(pvzVar);
                pvzVar = new pvz();
                if (this.h == 0) {
                    this.h = hiHealthData.getInt("DEVICE_TYPE");
                }
            }
            if (i == list.size() - 1 && pvzVar.b() != -1) {
                pvzVar.e(ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL);
                pvzVar.c(hiHealthData.getEndTime());
                if (this.h == 0) {
                    this.h = hiHealthData.getInt("DEVICE_TYPE");
                }
                list2.add(pvzVar);
            }
        }
        if (pvzVar.e() == -1 && i2 != 0) {
            pvzVar.a(i);
            pvzVar.a(hiHealthData.getStartTime());
            pvzVar.b(i2);
        }
        return pvzVar;
    }

    private void b(HiHealthData hiHealthData) {
        String str;
        String string = hiHealthData.getString("NOON_START_TIME");
        String string2 = hiHealthData.getString("NOON_END_TIME");
        String a2 = scn.a(this.d);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return;
        }
        String[] split = string2.split(",");
        String[] split2 = string.split(",");
        if (split.length == 1) {
            long g = CommonUtil.g(split2[0]);
            long g2 = CommonUtil.g(split[0]);
            long j = (g2 - g) / 60000;
            long j2 = j / 60;
            int i = (int) (j % 60);
            String a3 = nsf.a(R.plurals._2130903200_res_0x7f0300a0, i, UnitUtil.e(i, 1, 0));
            if (j2 == 0) {
                str = scn.b(g) + a2 + scn.b(g2) + "   " + a3;
            } else {
                int i2 = (int) j2;
                str = scn.b(g) + a2 + scn.b(g2) + "   " + this.d.getString(R$string.IDS_two_parts, nsf.a(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(i2, 1, 0)), a3);
            }
            this.p.add(str);
            return;
        }
        e(split, split2);
    }

    private void e(String[] strArr, String[] strArr2) {
        String str;
        String[] strArr3 = strArr;
        String a2 = scn.a(this.d);
        fcj.d(strArr2, strArr3);
        int i = 0;
        while (i < strArr3.length) {
            long g = CommonUtil.g(strArr2[i]);
            long g2 = CommonUtil.g(strArr3[i]);
            long j = (g2 - g) / 60000;
            long j2 = j % 60;
            long j3 = j / 60;
            String e = UnitUtil.e(j3, 1, 0);
            String e2 = UnitUtil.e(j2, 1, 0);
            if (j3 == 0) {
                int i2 = (int) j2;
                str = scn.b(g) + a2 + scn.b(g2) + "   " + this.d.getResources().getQuantityString(R.plurals._2130903041_res_0x7f030001, i2, Integer.valueOf(i2));
            } else {
                str = scn.b(g) + a2 + scn.b(g2) + "   " + this.d.getString(com.huawei.ui.main.R$string.IDS_h_min_unit, e, e2);
            }
            this.p.add(str);
            i++;
            strArr3 = strArr;
        }
    }

    private void e(int i, boolean z, List<pvz> list, CommonUiBaseResponse commonUiBaseResponse) {
        CopyOnWriteArrayList<pvz> copyOnWriteArrayList;
        if (list == null || list.size() <= 0) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        } else {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>(list);
        }
        if (z) {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "dealIfHaveSleepData daySleepHistogramData.size =", Integer.valueOf(copyOnWriteArrayList.size()));
            b(copyOnWriteArrayList);
            this.e = copyOnWriteArrayList;
        } else {
            LogUtil.a("FitnessCoreSleepDetailInteractor", "dealIfHaveSleepData clea daysleephistogramdata");
            copyOnWriteArrayList.clear();
            this.e = copyOnWriteArrayList;
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(i, copyOnWriteArrayList);
        }
    }

    private void e(Date date, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, CommonUiBaseResponse commonUiBaseResponse) {
        long time = date.getTime();
        if (fitnessQueryId == FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_DAY_HISTOGRAM) {
            time = jdl.e(jdl.v(time), 20, 0);
        }
        long j = time / 1000;
        qnl.b(j, fitnessQueryId, new AnonymousClass6(j, fitnessQueryId, commonUiBaseResponse));
    }

    /* renamed from: pwd$6, reason: invalid class name */
    class AnonymousClass6 implements HiDataClientListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f16293a;
        final /* synthetic */ FitnessDataQueryDefine.FitnessQueryId b;
        final /* synthetic */ CommonUiBaseResponse e;

        AnonymousClass6(long j, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, CommonUiBaseResponse commonUiBaseResponse) {
            this.f16293a = j;
            this.b = fitnessQueryId;
            this.e = commonUiBaseResponse;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataClientListener
        public void onResult(final List<HiHealthClient> list) {
            ThreadPoolManager d = ThreadPoolManager.d();
            final long j = this.f16293a;
            final FitnessDataQueryDefine.FitnessQueryId fitnessQueryId = this.b;
            final CommonUiBaseResponse commonUiBaseResponse = this.e;
            d.execute(new Runnable() { // from class: pwg
                @Override // java.lang.Runnable
                public final void run() {
                    pwd.AnonymousClass6.this.e(j, fitnessQueryId, list, commonUiBaseResponse);
                }
            });
        }

        /* synthetic */ void e(long j, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, List list, CommonUiBaseResponse commonUiBaseResponse) {
            pwd.this.b(j, fitnessQueryId, (List<HiHealthClient>) list, commonUiBaseResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, final List<HiHealthClient> list, final CommonUiBaseResponse commonUiBaseResponse) {
        long a2;
        if (fitnessQueryId == FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_DAY_HISTOGRAM) {
            a2 = jdl.b(j * 1000, 1);
        } else {
            a2 = ((FitnessDataQueryDefine.a(j, fitnessQueryId) * FitnessDataQueryDefine.a(fitnessQueryId) * 60) + j) * 1000;
        }
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).fetchDataSourceByType(22000, new HiTimeInterval(j * 1000, a2 - 1), new HiDataClientListener() { // from class: pwd.7
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public void onResult(List<HiHealthClient> list2) {
                HashMap hashMap = new HashMap();
                hashMap.put("core", list);
                hashMap.put("normal", list2);
                LogUtil.a("FitnessCoreSleepDetailInteractor", "devices is ", hashMap);
                commonUiBaseResponse.onResponse(0, hashMap);
            }
        });
    }
}
