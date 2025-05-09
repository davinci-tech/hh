package defpackage;

import android.os.Looper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.data.listener.HealthTrendListener;
import com.huawei.hihealth.data.type.HealthTrendDataType;
import com.huawei.hihealth.model.HealthTrendReport;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.HiDateUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class ika {
    /* JADX INFO: Access modifiers changed from: protected */
    public static void e(final List<String> list, final int[] iArr, final int i, final HealthTrendListener healthTrendListener, final boolean z) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ikd
                @Override // java.lang.Runnable
                public final void run() {
                    ika.e(list, iArr, i, healthTrendListener, z);
                }
            });
            return;
        }
        if ((!z && a()) || !NetworkUtil.i()) {
            ReleaseLogUtil.e("R_HlthTrendCacheUt", "cache is valid or network unavailable");
            healthTrendListener.onResult(0, e(list, iArr));
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        List<String> e = HealthTrendDataType.e();
        iki.e(e, e, new ResultCallback<ikj>() { // from class: ika.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ikj ikjVar) {
                countDownLatch.countDown();
                if (ikjVar.getResultCode().intValue() == 0) {
                    ika.d(ikjVar.b());
                    healthTrendListener.onResult(0, ika.e(list, iArr));
                } else {
                    healthTrendListener.onResult(0, ika.e(list, iArr));
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                countDownLatch.countDown();
                healthTrendListener.onResult(0, ika.e(list, iArr));
            }
        });
        if (i == 0) {
            return;
        }
        try {
            boolean await = countDownLatch.await(i, TimeUnit.MILLISECONDS);
            ReleaseLogUtil.e("R_HlthTrendCacheUt", "getHealthTrend isCountZero = ", Boolean.valueOf(await));
            if (await) {
                return;
            }
            healthTrendListener.onResult(1023, e(list, iArr));
        } catch (InterruptedException unused) {
            ReleaseLogUtil.d("R_HlthTrendCacheUt", "getHealthTrend exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void d(final List<String> list, final int[] iArr, final int i, final HealthTrendListener healthTrendListener, final String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ikh
                @Override // java.lang.Runnable
                public final void run() {
                    ika.d(list, iArr, i, healthTrendListener, str);
                }
            });
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 : iArr) {
            if (i2 == 1 && arrayList.isEmpty()) {
                arrayList.addAll(list);
            } else if (i2 == 2 && arrayList2.isEmpty()) {
                arrayList2.addAll(list);
            } else {
                ReleaseLogUtil.d("R_HlthTrendCacheUt", "getHealthTrendWithRecordDay, periodType is wrong");
            }
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        iki.e(arrayList, arrayList2, str, new ResultCallback<ikj>() { // from class: ika.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ikj ikjVar) {
                countDownLatch.countDown();
                if (ikjVar.getResultCode().intValue() == 0) {
                    healthTrendListener.onResult(0, ikjVar.b());
                } else {
                    healthTrendListener.onResult(1004, new ArrayList());
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                countDownLatch.countDown();
                healthTrendListener.onResult(1004, new ArrayList());
            }
        });
        if (i <= 0) {
            return;
        }
        try {
            boolean await = countDownLatch.await(i, TimeUnit.MILLISECONDS);
            ReleaseLogUtil.e("R_HlthTrendCacheUt", "getHealthTrendWithRecordDay isCountZero = ", Boolean.valueOf(await));
            if (await) {
                return;
            }
            healthTrendListener.onResult(1023, new ArrayList());
        } catch (InterruptedException unused) {
            ReleaseLogUtil.d("R_HlthTrendCacheUt", "getHealthTrendWithRecordDay exception");
        }
    }

    private static void e(String str, String str2, String str3) {
        SharedPreferenceManager.c("HealthKit", str + "_" + str2, str3);
    }

    private static String b(String str, String str2) {
        return SharedPreferenceManager.e("HealthKit", str + "_" + str2, "");
    }

    private static long c(String str) {
        long b = SharedPreferenceManager.b("HealthKit", str + "_HealthTrendUpdateTime_V3", 0L);
        ReleaseLogUtil.e("R_HlthTrendCacheUt", "getHealthTrend getCacheUpdateTime = ", Long.valueOf(b));
        return b;
    }

    private static void d(String str, long j) {
        ReleaseLogUtil.e("R_HlthTrendCacheUt", "getHealthTrend saveCacheUpdateTime = ", Long.valueOf(j));
        SharedPreferenceManager.e("HealthKit", str + "_HealthTrendUpdateTime_V3", j);
    }

    private static boolean a() {
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        int c = HiDateUtil.c(System.currentTimeMillis());
        int c2 = HiDateUtil.c(c(e));
        LogUtil.a("HealthTrendCacheUtil", "getHealthTrend isCacheValid currentDay = ", Integer.valueOf(c), ", updateDay = ", Integer.valueOf(c2));
        boolean z = c == c2;
        ReleaseLogUtil.e("R_HlthTrendCacheUt", "getHealthTrend isCacheValid = ", Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(List<HealthTrendReport> list) {
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        for (Map.Entry<String, List<HealthTrendReport>> entry : e(list).entrySet()) {
            e(e, entry.getKey(), new Gson().toJson(entry.getValue()));
        }
        d(e, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<HealthTrendReport> e(List<String> list, int[] iArr) {
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            List<HealthTrendReport> a2 = a((List) new Gson().fromJson(b(e, it.next()), new TypeToken<List<HealthTrendReport>>() { // from class: ika.1
            }.getType()), iArr);
            if (koq.c(a2)) {
                arrayList.addAll(a2);
            }
        }
        LogUtil.a("HealthTrendCacheUtil", "getHealthTrendFromCache result = ", arrayList);
        return arrayList;
    }

    private static List<HealthTrendReport> a(List<HealthTrendReport> list, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (int i : iArr) {
            Iterator<HealthTrendReport> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    HealthTrendReport next = it.next();
                    if (next.getTrendPeriod() == i) {
                        arrayList.add(next);
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    private static Map<String, List<HealthTrendReport>> e(List<HealthTrendReport> list) {
        HashMap hashMap = new HashMap();
        if (koq.b(list)) {
            return hashMap;
        }
        for (HealthTrendReport healthTrendReport : list) {
            String item = healthTrendReport.getItem();
            if (hashMap.containsKey(item)) {
                ((List) hashMap.get(item)).add(healthTrendReport);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(healthTrendReport);
                hashMap.put(item, arrayList);
            }
        }
        return hashMap;
    }
}
