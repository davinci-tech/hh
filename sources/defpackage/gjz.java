package defpackage;

import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class gjz {
    public static Calendar b(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, i);
        int nextInt = msp.e().nextInt(((i3 - i2) * 60) + 1);
        calendar.set(11, i2 + (nextInt / 60));
        calendar.set(12, nextInt % 60);
        calendar.set(13, 0);
        ReleaseLogUtil.e("R_TC_ThreeCircleUtils", "getRandomTime:", Integer.valueOf(calendar.get(11)), " ", Integer.valueOf(calendar.get(12)), " ", Long.valueOf(calendar.getTimeInMillis()));
        return calendar;
    }

    public static String c(final String str) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String[] strArr = new String[1];
        njh.c(arrayList, new IBaseResponseCallback() { // from class: gjy
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                gjz.a(countDownLatch, strArr, str, i, obj);
            }
        });
        try {
            if (!countDownLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                ReleaseLogUtil.d("R_TC_ThreeCircleUtils", "getSwitchStatusFromDb wait timeout");
            }
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("R_TC_ThreeCircleUtils", "interrupted while waiting for getSwitchStatusFromDb");
        }
        ReleaseLogUtil.e("R_TC_ThreeCircleUtils", "IsRemindSwitchOpen id:", str, " status:", strArr[0]);
        if (strArr[0] == null) {
            strArr[0] = "1";
        }
        return strArr[0];
    }

    static /* synthetic */ void a(CountDownLatch countDownLatch, String[] strArr, String str, int i, Object obj) {
        if (!(obj instanceof HashMap)) {
            ReleaseLogUtil.d("R_TC_ThreeCircleUtils", "onResponse: objData is not instanceof HashMap");
            countDownLatch.countDown();
        } else {
            strArr[0] = (String) ((HashMap) obj).get(str);
            countDownLatch.countDown();
        }
    }

    public static void e(String str, long j, int i) {
        ReleaseLogUtil.e("R_TC_ThreeCircleUtils", "updateCacheState key:", str, " timeMills", Long.valueOf(j), " state:", Integer.valueOf(i));
        SharedPreferenceManager.c("threeCircleSp", str, DateFormatUtil.c(j, TimeZone.getDefault()) + "_" + i);
    }

    public static int e(String str, long j) {
        String e = SharedPreferenceManager.e("threeCircleSp", str, "");
        ReleaseLogUtil.e("R_TC_ThreeCircleUtils", "getCacheState key:", str, " timeMills", Long.valueOf(j), " spValue:", e);
        if (!TextUtils.isEmpty(e) && e.contains("_")) {
            String[] split = e.split("_");
            if (split.length != 2) {
                return 0;
            }
            String str2 = split[0];
            int h = CommonUtil.h(split[1]);
            if (Integer.toString(DateFormatUtil.b(j)).equals(str2)) {
                return h;
            }
        }
        return 0;
    }

    public static boolean b(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        int i3 = (calendar.get(11) * 100) + calendar.get(12);
        return i3 >= i * 100 && i3 <= i2 * 100;
    }

    public static boolean b() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(5) == 15) {
            return true;
        }
        return calendar.get(5) > 15 && calendar.get(7) == 1;
    }

    public static boolean a() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(7) == 4 || calendar.get(7) == 6;
    }
}
