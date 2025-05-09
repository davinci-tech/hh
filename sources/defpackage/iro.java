package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.hihealth.HiHealthDataQuery;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class iro {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13557a = new Object();
    private static ipx e = new ipx();

    public static void a(HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener, HealthOpenSDK healthOpenSDK) throws RemoteException {
        ReleaseLogUtil.e("HiH_RealSportUtil", "enter queryRealSport");
        long t = HiDateUtil.t(System.currentTimeMillis());
        if (hiHealthDataQuery.getStartTime() < hiHealthDataQuery.getEndTime() && hiHealthDataQuery.getStartTime() >= t && hiHealthDataQuery.getEndTime() < System.currentTimeMillis()) {
            ArrayList arrayList = new ArrayList();
            HiHealthKitData a2 = a(healthOpenSDK, "step");
            if (a2.getIntValue() == -1) {
                ReleaseLogUtil.d("HiH_RealSportUtil", "queryRealSport type is error");
                iDataReadResultListener.onResult(null, 4, 2);
                return;
            } else {
                arrayList.add(a2);
                iDataReadResultListener.onResult(arrayList, 40002, 1);
                iDataReadResultListener.onResult(null, 0, 2);
                return;
            }
        }
        ReleaseLogUtil.d("HiH_RealSportUtil", "only today data is supported");
        iDataReadResultListener.onResult(null, 2, 2);
    }

    public static HiHealthKitData a(HealthOpenSDK healthOpenSDK, final String str) {
        long t = HiDateUtil.t(System.currentTimeMillis());
        final HiHealthKitData hiHealthKitData = new HiHealthKitData();
        hiHealthKitData.setValue(-1);
        hiHealthKitData.setStartTime(t);
        hiHealthKitData.setEndTime(t);
        if (healthOpenSDK == null) {
            ReleaseLogUtil.d("HiH_RealSportUtil", "step sdk is null");
            return hiHealthKitData;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        healthOpenSDK.b(new IExecuteResult() { // from class: iro.2
            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onSuccess(Object obj) {
                if (obj instanceof Bundle) {
                    int i = ((Bundle) obj).getInt(str, -1);
                    if (i > -1) {
                        hiHealthKitData.setValue(i);
                    } else {
                        ReleaseLogUtil.d("HiH_RealSportUtil", "today sport data is invalid for type ", str);
                    }
                } else {
                    ReleaseLogUtil.d("HiH_RealSportUtil", "object is not instance of bundle", obj);
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onFailed(Object obj) {
                ReleaseLogUtil.c("HiH_RealSportUtil", "failed to get today sport data", obj);
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onServiceException(Object obj) {
                ReleaseLogUtil.c("HiH_RealSportUtil", "service error when get today sport data", obj);
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(1000L, TimeUnit.MILLISECONDS)) {
                ReleaseLogUtil.c("HiH_RealSportUtil", "getTodaySportData wait timeout");
            }
            return hiHealthKitData;
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("HiH_RealSportUtil", "query today sport data process is interrupted", LogAnonymous.b((Throwable) e2));
            return hiHealthKitData;
        }
    }
}
