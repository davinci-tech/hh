package com.huawei.ui.main.stories.fitness.activity.active.writehelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.threecircle.ThreeCircleConfigUtil;
import com.huawei.ui.main.stories.fitness.activity.active.writehelper.ThreeCircleDataManager;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nhj;
import defpackage.nip;
import defpackage.nix;
import defpackage.pit;
import defpackage.piw;
import health.compact.a.HiBroadcastManager;
import health.compact.a.HiDateUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class ThreeCircleDataManager implements ThreeCircleDataInterface {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ThreeCircleDataManager f9739a;
    private static final Object e = new Object();
    private long c;
    private SyncSportDataReceiver g;
    private final HashMap<Long, HiHealthData> h = new HashMap<>();
    private CopyOnWriteArrayList<Integer> b = new CopyOnWriteArrayList<>();
    private long f = 0;
    private long d = 0;

    public static ThreeCircleDataManager a() {
        ThreeCircleDataManager threeCircleDataManager;
        if (f9739a == null) {
            synchronized (e) {
                if (f9739a == null) {
                    f9739a = new ThreeCircleDataManager();
                }
                threeCircleDataManager = f9739a;
            }
            return threeCircleDataManager;
        }
        return f9739a;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.active.writehelper.ThreeCircleDataInterface
    public void requestAggregateData(long j, long j2, final ResponseCallback<List<HiHealthData>> responseCallback) {
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(dqc_(new Pair<>(Long.valueOf(j), Long.valueOf(j2))), new HiAggregateListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.writehelper.ThreeCircleDataManager.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                responseCallback.onResponse(i, list);
            }
        });
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.active.writehelper.ThreeCircleDataInterface
    public void clearData() {
        this.h.clear();
        if (this.g != null) {
            HiBroadcastManager.bwm_(BaseApplication.e(), this.g);
            this.g = null;
        }
        this.c = 0L;
    }

    public void d() {
        e();
        HandlerCenter.d().e(new Runnable() { // from class: pje
            @Override // java.lang.Runnable
            public final void run() {
                ThreeCircleDataManager.c();
            }
        }, 500L);
    }

    public static /* synthetic */ void c() {
        ReleaseLogUtil.b("Track_ThreeCircleDataManager", "init synCloud");
        nhj.b(nhj.c(), System.currentTimeMillis());
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void a(final long j, final long j2, final ResponseCallback<List<HiHealthData>> responseCallback) {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: pja
                @Override // java.lang.Runnable
                public final void run() {
                    ThreeCircleDataManager.this.a(j, j2, responseCallback);
                }
            });
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(4);
        a(countDownLatch);
        HashMap hashMap = new HashMap(2);
        LogUtil.c("Track_ThreeCircleDataManager", "startTime", HiDateUtil.b(j, "yyyy-MM-dd HH:mm:ss"), "endTime", HiDateUtil.b(j2, "yyyy-MM-dd HH:mm:ss"));
        if (a(j, j2, countDownLatch, hashMap)) {
            ReleaseLogUtil.c("Track_ThreeCircleDataManager", "search Data out of time");
            responseCallback.onResponse(5, null);
        } else {
            a(hashMap.containsKey(0) ? hashMap.get(0) : null, hashMap.containsKey(1) ? hashMap.get(1) : null, j, j2, responseCallback);
        }
    }

    public long b() {
        return this.c;
    }

    private boolean a(long j, long j2, final CountDownLatch countDownLatch, final Map<Integer, List<HiHealthData>> map) {
        requestAggregateData(j, j2, new ResponseCallback() { // from class: pix
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ThreeCircleDataManager.a(map, countDownLatch, i, (List) obj);
            }
        });
        nix.c().c(j, j2, new ResponseCallback() { // from class: pjb
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ThreeCircleDataManager.e(map, countDownLatch, i, (List) obj);
            }
        });
        if (this.c == 0) {
            nix.c().d(new IBaseResponseCallback() { // from class: pjd
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ThreeCircleDataManager.this.c(countDownLatch, i, obj);
                }
            });
        } else {
            countDownLatch.countDown();
        }
        try {
            if (!countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                LogUtil.a("Track_ThreeCircleDataManager", "getHealthTrackData wait timeout");
                return true;
            }
        } catch (InterruptedException unused) {
            LogUtil.e("Track_ThreeCircleDataManager", "interrupted while waiting for getHealthTrackData");
        }
        return false;
    }

    public static /* synthetic */ void a(Map map, CountDownLatch countDownLatch, int i, List list) {
        map.put(0, list);
        countDownLatch.countDown();
    }

    public static /* synthetic */ void e(Map map, CountDownLatch countDownLatch, int i, List list) {
        map.put(1, list);
        countDownLatch.countDown();
    }

    public /* synthetic */ void c(CountDownLatch countDownLatch, int i, Object obj) {
        this.c = ((Long) obj).longValue();
        countDownLatch.countDown();
    }

    private void a(List<HiHealthData> list, List<HiHealthData> list2, long j, long j2, ResponseCallback<List<HiHealthData>> responseCallback) {
        HiHealthData hiHealthData;
        Map<Long, Pair<HiHealthData, HiHealthData>> c = c(list, list2, j, j2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList(4);
        arrayList3.addAll(this.b);
        piw.b(arrayList3);
        for (Map.Entry<Long, Pair<HiHealthData, HiHealthData>> entry : c.entrySet()) {
            boolean z = entry.getKey().longValue() >= this.c;
            Pair<HiHealthData, HiHealthData> value = entry.getValue();
            if (value == null) {
                value = new Pair<>(null, null);
            }
            if (value.second == null) {
                hiHealthData = new HiHealthData();
                hiHealthData.setStartTime(entry.getKey().longValue());
            } else {
                hiHealthData = (HiHealthData) value.second;
            }
            arrayList2.add(piw.a(value.first != null ? (HiHealthData) value.first : hiHealthData, hiHealthData, arrayList, z, false));
        }
        responseCallback.onResponse(0, arrayList2);
    }

    private Map<Long, Pair<HiHealthData, HiHealthData>> c(List<HiHealthData> list, List<HiHealthData> list2, long j, long j2) {
        HashMap hashMap = new HashMap();
        while (j < j2) {
            hashMap.put(Long.valueOf(j), null);
            j = jdl.d(j, 1);
        }
        d(list2, hashMap);
        b(list, hashMap);
        return hashMap;
    }

    private void d(List<HiHealthData> list, Map<Long, Pair<HiHealthData, HiHealthData>> map) {
        if (koq.b(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                if (map.containsKey(Long.valueOf(hiHealthData.getStartTime()))) {
                    map.put(Long.valueOf(hiHealthData.getStartTime()), new Pair<>(hiHealthData, null));
                } else {
                    long c = DateFormatUtil.c(DateFormatUtil.c(hiHealthData.getStartTime(), jdl.d(hiHealthData.getTimeZone())));
                    if (map.containsKey(Long.valueOf(c)) && map.get(Long.valueOf(c)) == null) {
                        hiHealthData.setStartTime(c);
                        map.put(Long.valueOf(c), new Pair<>(hiHealthData, null));
                    }
                }
            }
        }
    }

    private void b(List<HiHealthData> list, Map<Long, Pair<HiHealthData, HiHealthData>> map) {
        Pair<HiHealthData, HiHealthData> pair;
        if (koq.b(list)) {
            LogUtil.c("Track_ThreeCircleDataManager", "aggregateList is empty");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.c("Track_ThreeCircleDataManager", "data is null");
            } else if (map.containsKey(Long.valueOf(hiHealthData.getStartTime()))) {
                Pair<HiHealthData, HiHealthData> pair2 = map.get(Long.valueOf(hiHealthData.getStartTime()));
                if (pair2 == null) {
                    pair = new Pair<>(null, hiHealthData);
                } else {
                    pair = new Pair<>((HiHealthData) pair2.first, hiHealthData);
                }
                map.put(Long.valueOf(hiHealthData.getStartTime()), pair);
            }
        }
    }

    private void e() {
        this.f = SharedPreferenceManager.b("three_circle", "reliable_start_time", 0L);
        this.d = SharedPreferenceManager.b("three_circle", "reliable_end_time", 0L);
        LogUtil.c("Track_ThreeCircleDataManager", "mReliableStartTime ", Long.valueOf(this.f), "mReliableEndTime ", Long.valueOf(this.d));
    }

    private void a(final CountDownLatch countDownLatch) {
        pit.a().c(new IBaseResponseCallback() { // from class: pjc
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ThreeCircleDataManager.this.a(countDownLatch, i, obj);
            }
        });
    }

    public /* synthetic */ void a(CountDownLatch countDownLatch, int i, Object obj) {
        if (!(obj instanceof HashMap)) {
            LogUtil.a("Track_ThreeCircleDataManager", "handleMsgUpdateDialog is not instanceof HashMap");
            return;
        }
        HashMap<String, Integer> hashMap = (HashMap) obj;
        this.b.clear();
        LogUtil.c("Track_ThreeCircleDataManager", "insertSportAchievementGoalVal stepValue is ", Integer.valueOf(e(hashMap, "900200006", 10000, ThreeCircleConfigUtil.ThreeCircleConfig.STEP)), " intensityValue is ", Integer.valueOf(e(hashMap, "900200008", 25, ThreeCircleConfigUtil.ThreeCircleConfig.INTENSITY)), " standValue is ", Integer.valueOf(e(hashMap, "900200009", 12, ThreeCircleConfigUtil.ThreeCircleConfig.ACTIVE_HOUR)), " calorieGoalValue is ", Integer.valueOf(e(hashMap, "900200007", 270000, ThreeCircleConfigUtil.ThreeCircleConfig.CALORIE)));
        countDownLatch.countDown();
    }

    private int e(HashMap<String, Integer> hashMap, String str, int i, ThreeCircleConfigUtil.ThreeCircleConfig threeCircleConfig) {
        int e2 = nip.e(hashMap, str, i);
        this.b.add(Integer.valueOf(e2));
        return e2;
    }

    private HiAggregateOption dqc_(Pair<Long, Long> pair) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(((Long) pair.first).longValue());
        hiAggregateOption.setEndTime(((Long) pair.second).longValue());
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setType(new int[]{40002, 47101, DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value(), 40004, 40003, SmartMsgConstant.MSG_TYPE_RIDE_USER});
        hiAggregateOption.setConstantsKey(new String[]{"stepUserValue", "durationUserValue", "activeUserValue", "sport_distance_sum", "sport_calorie_sum", "sport_altitude_offset_sum"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    public class SyncSportDataReceiver extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private boolean f9741a;
        private final long b;
        final /* synthetic */ ThreeCircleDataManager c;
        private final long d;
        private boolean e;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a("Track_ThreeCircleDataManager", "SyncSportDataReceiver onReceive intent is null or context is null");
                return;
            }
            LogUtil.c("Track_ThreeCircleDataManager", "intent.getAction()", intent.getAction());
            if ("com.huawei.hihealth.action_sync_sport_stat_data".equals(intent.getAction())) {
                this.e = true;
                LogUtil.c("Track_ThreeCircleDataManager", "into STAT_DATA");
            }
            if ("com.huawei.hihealth.action_sync_all_data".equals(intent.getAction())) {
                this.f9741a = true;
                LogUtil.c("Track_ThreeCircleDataManager", "into ALL_DATA");
            }
            if (this.e && this.f9741a) {
                LogUtil.c("Track_ThreeCircleDataManager", "all finish");
                this.c.a(this.b, this.d, new ResponseCallback() { // from class: pjg
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i, Object obj) {
                        LogUtil.c("Track_ThreeCircleDataManager", "pre search finish");
                    }
                });
            }
        }
    }
}
