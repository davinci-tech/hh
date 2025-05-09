package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.sleep.SleepApi;
import com.huawei.health.sport.utils.FitnessDataQueryDefine;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.SleepDailyProcessResultCallback;
import com.huawei.hwbasemgr.SleepMonthlyProcessResultCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil;
import defpackage.qnr;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@ApiDefine(uri = SleepApi.class)
@Singleton
/* loaded from: classes6.dex */
public class qnr implements SleepApi {

    /* renamed from: a, reason: collision with root package name */
    private int f16508a;
    private HiHealthData b;
    private HiHealthData d;
    private HiHealthData f;
    private final List<String> h = new ArrayList();
    private final List<String> e = new ArrayList();
    private final List<String> c = new ArrayList();

    static /* synthetic */ int a(qnr qnrVar) {
        int i = qnrVar.f16508a;
        qnrVar.f16508a = i + 1;
        return i;
    }

    @Override // com.huawei.health.sleep.SleepApi
    public void getSleepData(final long j, final IBaseResponseCallback iBaseResponseCallback) {
        qnl.b(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_DAY_HISTOGRAM, new HiDataClientListener() { // from class: qnq
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public final void onResult(List list) {
                qnr.this.c(j, iBaseResponseCallback, list);
            }
        });
    }

    /* synthetic */ void c(long j, IBaseResponseCallback iBaseResponseCallback, List list) {
        HiDeviceInfo hiDeviceInfo;
        LogUtil.a("SleepServiceApi", "getSleepData startTime ", Long.valueOf(j), " clientList ", HiJsonUtil.e(list));
        this.h.clear();
        this.e.clear();
        this.c.clear();
        if (koq.c(list)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiHealthClient hiHealthClient = (HiHealthClient) it.next();
                if (hiHealthClient != null && (hiDeviceInfo = hiHealthClient.getHiDeviceInfo()) != null) {
                    int deviceType = hiDeviceInfo.getDeviceType();
                    String deviceUniqueCode = hiDeviceInfo.getDeviceUniqueCode();
                    if (deviceType == 506 && !this.c.contains(deviceUniqueCode)) {
                        this.c.add(deviceUniqueCode);
                    } else if (deviceType == 32 && !this.e.contains(deviceUniqueCode)) {
                        this.e.add(deviceUniqueCode);
                    } else if (!this.h.contains(deviceUniqueCode)) {
                        this.h.add(deviceUniqueCode);
                    }
                }
            }
        }
        a(j, iBaseResponseCallback);
    }

    @Override // com.huawei.health.sleep.SleepApi
    public void requestDailyProcessResult(long j, SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback) {
        qnp.b(j, sleepDailyProcessResultCallback);
    }

    @Override // com.huawei.health.sleep.SleepApi
    public void requestAndUpdateDailyResult(final long j, final SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qnu
            @Override // java.lang.Runnable
            public final void run() {
                pob.b(j, (SleepDailyProcessResultCallback<fda>) sleepDailyProcessResultCallback);
            }
        });
    }

    @Override // com.huawei.health.sleep.SleepApi
    public void getPlanState(IBaseResponseCallback iBaseResponseCallback) {
        pob.d(iBaseResponseCallback);
    }

    @Override // com.huawei.health.sleep.SleepApi
    public Class<?> getSleepManagementJsApi() {
        return SleepManagementJsUtil.class;
    }

    @Override // com.huawei.health.sleep.SleepApi
    public Class<? extends JsBaseModule> getSleepManagementH5Bridge() {
        return pmr.class;
    }

    @Override // com.huawei.health.sleep.SleepApi
    public void requestMonthlyProcessResult(fcz fczVar, fcw fcwVar, long j, long j2, SleepMonthlyProcessResultCallback<fdh> sleepMonthlyProcessResultCallback) {
        qnp.a(fczVar, fcwVar, j, j2, sleepMonthlyProcessResultCallback);
    }

    @Override // com.huawei.health.sleep.SleepApi
    public void requestQuestionnaireProcessResult(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        qnp.b(j, j2, iBaseResponseCallback);
    }

    /* renamed from: qnr$3, reason: invalid class name */
    class AnonymousClass3 implements HiDataClientListener {
        final /* synthetic */ long c;
        final /* synthetic */ CommonUiBaseResponse e;

        AnonymousClass3(long j, CommonUiBaseResponse commonUiBaseResponse) {
            this.c = j;
            this.e = commonUiBaseResponse;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataClientListener
        public void onResult(final List<HiHealthClient> list) {
            ThreadPoolManager d = ThreadPoolManager.d();
            final long j = this.c;
            final CommonUiBaseResponse commonUiBaseResponse = this.e;
            d.execute(new Runnable() { // from class: qnw
                @Override // java.lang.Runnable
                public final void run() {
                    qnr.AnonymousClass3.this.a(j, list, commonUiBaseResponse);
                }
            });
        }

        /* synthetic */ void a(long j, List list, CommonUiBaseResponse commonUiBaseResponse) {
            qnr.this.a(j, list, commonUiBaseResponse);
        }
    }

    @Override // com.huawei.health.sleep.SleepApi
    public void requestSleepDevice(Date date, CommonUiBaseResponse commonUiBaseResponse) {
        long e = jdl.e(jdl.v(date.getTime()), 20, 0) / 1000;
        qnl.b(e, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_DAY_HISTOGRAM, new AnonymousClass3(e, commonUiBaseResponse));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final long j, final List<HiHealthClient> list, final CommonUiBaseResponse commonUiBaseResponse) {
        long j2 = 1000 * j;
        HiHealthManager.d(BaseApplication.e()).fetchDataSourceByType(22000, new HiTimeInterval(j2, jdl.b(j2, 1) - 1), new HiDataClientListener() { // from class: qnr.2
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public void onResult(List<HiHealthClient> list2) {
                ArrayList<pwb> d = qnl.d((List<HiHealthClient>) list, list2);
                if (d.size() == 1) {
                    d.clear();
                    LogUtil.a("SleepServiceApi", "only one device not show icon");
                }
                LogUtil.a("SleepServiceApi", "devices is ", d);
                fch.b(new Time(j), d);
                commonUiBaseResponse.onResponse(0, d);
            }
        });
    }

    private void a(long j, IBaseResponseCallback iBaseResponseCallback) {
        this.f16508a = 0;
        int e = qnl.e(this.h, this.c, this.e);
        LogUtil.a("SleepServiceApi", "judgePriority priority ", Integer.valueOf(e));
        if (e == 1) {
            e(j, this.h, 1, new int[]{1, 3, 2}, iBaseResponseCallback);
            return;
        }
        if (e == 2) {
            e(j, this.h, 1, new int[]{1, 3}, iBaseResponseCallback);
            return;
        }
        if (e == 3) {
            b(j, this.h, 1, iBaseResponseCallback);
        } else {
            if (e == 4) {
                e(j, this.c, 3, new int[]{3, 2}, iBaseResponseCallback);
                return;
            }
            kor.a().c(j, 3, FitnessDataQueryDefine.a(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC), FitnessDataQueryDefine.e(FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC) * 2, iBaseResponseCallback);
        }
    }

    private void a(List<HiHealthData> list, int i) {
        if (koq.b(list)) {
            LogUtil.h("SleepServiceApi", "setHealthDataByType dataList ", list, " type ", Integer.valueOf(i));
            return;
        }
        if (i == 1) {
            this.f = list.get(0);
            return;
        }
        if (i == 2) {
            this.b = list.get(0);
        } else if (i == 3) {
            this.d = list.get(0);
        } else {
            LogUtil.h("SleepServiceApi", "setHealthDataByType type ", Integer.valueOf(i));
        }
    }

    private void e(final long j, List<String> list, int i, final int[] iArr, final IBaseResponseCallback iBaseResponseCallback) {
        qnl.b(i, list, j * 1000, ((j + ((FitnessDataQueryDefine.a(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC) * FitnessDataQueryDefine.e(FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC)) * 60)) * 1000) - 1, new HiAggregateListener() { // from class: qnr.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list2, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list2, int i2, int i3) {
                LogUtil.a("SleepServiceApi", "getDetailForDevice dataList ", HiJsonUtil.e(list2), " errorCode ", Integer.valueOf(i2));
                qnr.a(qnr.this);
                int[] iArr2 = iArr;
                int length = iArr2.length;
                if (length == 2) {
                    qnr.this.d(j, iArr2, list2, iBaseResponseCallback);
                } else if (length == 3) {
                    qnr.this.b(j, iArr2, list2, iBaseResponseCallback);
                } else {
                    LogUtil.h("SleepServiceApi", "getDetailForDevice length ", Integer.valueOf(length));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final long j, int[] iArr, List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = this.f16508a;
        if (i3 == 1) {
            a(list, i);
            if (i2 == 2) {
                e(j, this.e, 2, iArr, iBaseResponseCallback);
                return;
            } else if (i2 == 3) {
                e(j, this.c, 3, iArr, iBaseResponseCallback);
                return;
            } else {
                LogUtil.h("SleepServiceApi", "scenarioForTwoDevice typeSecond ", Integer.valueOf(i2));
                return;
            }
        }
        if (i3 == 2) {
            a(list, i2);
            Object[] objArr = new Object[3];
            if (i == 1 && i2 == 3) {
                HiHealthData hiHealthData = this.f;
                objArr = qnl.d(hiHealthData, this.d, 1, 3, hiHealthData, this.h, this.c, this.e);
            } else if (i == 1 && i2 == 2) {
                HiHealthData hiHealthData2 = this.f;
                objArr = qnl.d(hiHealthData2, this.b, 1, 2, hiHealthData2, this.h, this.c, this.e);
            } else if (i == 3 && i2 == 2) {
                objArr = qnl.d(this.d, this.b, 3, 2, this.f, this.h, this.c, this.e);
            } else {
                LogUtil.h("SleepServiceApi", "scenarioForTwoDevice typeFirst ", Integer.valueOf(i), " typeSecond ", Integer.valueOf(i2));
            }
            int a2 = FitnessDataQueryDefine.a(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC);
            int e = FitnessDataQueryDefine.e(FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC);
            if (koq.b(objArr, 1)) {
                return;
            }
            final Object obj = objArr[1];
            if (obj instanceof Number) {
                kor.a().c(j, 3, a2, e * 2, new IBaseResponseCallback() { // from class: qnt
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i4, Object obj2) {
                        qnr.this.c(j, obj, iBaseResponseCallback, i4, obj2);
                    }
                });
                return;
            }
            return;
        }
        LogUtil.h("SleepServiceApi", "scenarioForTwoDevice mRequestCount ", Integer.valueOf(i3));
    }

    /* synthetic */ void c(long j, Object obj, IBaseResponseCallback iBaseResponseCallback, int i, Object obj2) {
        d(j, ((Integer) obj).intValue(), obj2, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final long j, int[] iArr, List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback) {
        int i = this.f16508a;
        if (i == 1) {
            if (koq.c(list)) {
                this.f = list.get(0);
            }
            e(j, this.c, iArr[1], iArr, iBaseResponseCallback);
            return;
        }
        if (i == 2) {
            if (koq.c(list)) {
                this.d = list.get(0);
            }
            e(j, this.e, iArr[2], iArr, iBaseResponseCallback);
            return;
        }
        if (i == 3) {
            if (koq.c(list)) {
                this.b = list.get(0);
            }
            int a2 = FitnessDataQueryDefine.a(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC);
            int e = FitnessDataQueryDefine.e(FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC);
            Object[] c = qnl.c(this.f, this.d, this.b, this.h, this.c, this.e);
            if (koq.b(c, 1)) {
                return;
            }
            final Object obj = c[1];
            if (obj instanceof Number) {
                kor.a().c(j, 3, a2, e * 2, new IBaseResponseCallback() { // from class: qnv
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj2) {
                        qnr.this.e(j, obj, iBaseResponseCallback, i2, obj2);
                    }
                });
                return;
            }
            return;
        }
        LogUtil.h("SleepServiceApi", "scenarioForThreeDevice mRequestCount ", Integer.valueOf(i));
    }

    /* synthetic */ void e(long j, Object obj, IBaseResponseCallback iBaseResponseCallback, int i, Object obj2) {
        d(j, ((Integer) obj).intValue(), obj2, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j, List<String> list, int i, IBaseResponseCallback iBaseResponseCallback) {
        qnl.b(i, list, j * 1000, ((j + ((FitnessDataQueryDefine.a(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC) * FitnessDataQueryDefine.e(FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC)) * 60)) * 1000) - 1, new AnonymousClass4(j, iBaseResponseCallback));
    }

    /* renamed from: qnr$4, reason: invalid class name */
    class AnonymousClass4 implements HiAggregateListener {
        final /* synthetic */ IBaseResponseCallback b;
        final /* synthetic */ long e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass4(long j, IBaseResponseCallback iBaseResponseCallback) {
            this.e = j;
            this.b = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.a("SleepServiceApi", "getDetail dataList ", HiJsonUtil.e(list), " resultCode ", Integer.valueOf(i));
            qnr.a(qnr.this);
            int i3 = qnr.this.f16508a;
            if (i3 == 1) {
                if (koq.c(list)) {
                    qnr.this.f = list.get(0);
                }
                qnr qnrVar = qnr.this;
                qnrVar.b(this.e, (List<String>) qnrVar.e, 2, this.b);
                return;
            }
            if (i3 != 2) {
                LogUtil.h("SleepServiceApi", "getDetail mRequestCount ", Integer.valueOf(qnr.this.f16508a));
                return;
            }
            if (koq.c(list)) {
                qnr.this.b = list.get(0);
            }
            int a2 = FitnessDataQueryDefine.a(this.e, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC);
            int e = FitnessDataQueryDefine.e(FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC);
            kor a3 = kor.a();
            final long j = this.e;
            final IBaseResponseCallback iBaseResponseCallback = this.b;
            a3.c(j, 3, a2, e * 2, new IBaseResponseCallback() { // from class: qnz
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i4, Object obj) {
                    qnr.AnonymousClass4.this.b(j, iBaseResponseCallback, i4, obj);
                }
            });
        }

        /* synthetic */ void b(long j, IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
            Object[] d = qnl.d(qnr.this.f, qnr.this.b, qnr.this.h, qnr.this.e);
            if (koq.b(d, 1)) {
                return;
            }
            Object obj2 = d[1];
            if (obj2 instanceof Number) {
                qnr.this.d(j, ((Integer) obj2).intValue(), obj, iBaseResponseCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j, int i, Object obj, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("SleepServiceApi", "getData callback is null");
        }
    }
}
