package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class rrb {

    /* renamed from: a, reason: collision with root package name */
    private static List<String> f16888a;

    public static boolean b(int i, int i2) {
        return i == (i2 != 0 ? i2 + 1 : 0) + 1;
    }

    public static boolean e(int i, int i2, int i3) {
        return i == (i2 != 0 ? i2 + 1 : 0) + i3;
    }

    static {
        ArrayList arrayList = new ArrayList(10);
        f16888a = arrayList;
        arrayList.add("sinocare");
        f16888a.add("VivachekCloud");
        f16888a.add("www.bioland.com.cn");
    }

    public static String b(int i, HiHealthData hiHealthData, rkb rkbVar) {
        String str = "";
        if (hiHealthData == null) {
            return "";
        }
        if (i == 103) {
            return a(hiHealthData.getEndTime(), hiHealthData.getStartTime());
        }
        if (i != 107) {
            return "";
        }
        int i2 = hiHealthData.getInt(BleConstants.BLOODPRESSURE_SYSTOLIC);
        int i3 = hiHealthData.getInt(BleConstants.BLOODPRESSURE_DIASTOLIC);
        if (Math.abs(i2) >= 0.5d) {
            str = UnitUtil.e(i2, 1, 0) + "/" + UnitUtil.e(i3, 1, 0);
        } else {
            LogUtil.h("OperateUtil", "invalid bloodPressure value");
        }
        return rre.a(str);
    }

    public static String d(int i, HiHealthData hiHealthData, rkb rkbVar) {
        if (hiHealthData == null) {
            return "";
        }
        if (i == 103) {
            if (rsr.d(hiHealthData.getStartTime(), hiHealthData.getEndTime())) {
                return d(hiHealthData, 1004);
            }
            return d(hiHealthData, 1003);
        }
        if (i != 107) {
            return "";
        }
        if (rkbVar != null && rkbVar.c() == 3) {
            return rsr.e(hiHealthData.getStartTime());
        }
        return rsr.c(hiHealthData.getStartTime());
    }

    public static String d(HiHealthData hiHealthData, int i) {
        switch (i) {
            case 1000:
                return rsr.a(hiHealthData.getStartTime());
            case 1001:
                return rsr.e(hiHealthData.getStartTime()) + Constants.LINK + rsr.e(hiHealthData.getEndTime());
            case 1002:
            default:
                return "";
            case 1003:
                return rsr.c(hiHealthData.getStartTime()) + Constants.LINK + rsr.c(hiHealthData.getEndTime());
            case 1004:
                return rsr.c(hiHealthData.getStartTime()) + Constants.LINK + rsr.e(hiHealthData.getEndTime());
            case 1005:
                return rsr.e(hiHealthData.getStartTime());
        }
    }

    public static long c(long j) {
        Date date = new Date(j);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }

    public static long e(long j) {
        Date date = new Date(j);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime().getTime();
    }

    public static long b(long j) {
        return c(j + 14400000) - 14400000;
    }

    public static long a(long j) {
        return e(j + 14400000) - 14400000;
    }

    public static String a(long j, long j2) {
        return rre.i((int) ((j - j2) / 60000));
    }

    public static boolean c(HiHealthClient hiHealthClient) {
        HiDeviceInfo hiDeviceInfo = hiHealthClient.getHiDeviceInfo();
        HiAppInfo hiAppInfo = hiHealthClient.getHiAppInfo();
        if (hiDeviceInfo == null || hiAppInfo == null) {
            LogUtil.a("OperateUtil", "deviceInfo or appInfo is null");
            return false;
        }
        return c(hiAppInfo.getPackageName(), hiDeviceInfo.getDeviceUniqueCode());
    }

    public static boolean c(String str, String str2) {
        return rju.f16792a.equals(str) && "-1".equals(str2);
    }

    public static boolean d(String str) {
        return (str == null || str.isEmpty() || str.equals(rju.f16792a) || str.equals("com.huawei.ohos.health.device") || str.startsWith("com.huawei.health.cloud.device") || str.startsWith("com.huawei.health.device") || f16888a.contains(str)) ? false : true;
    }

    public static boolean e(int i, Context context) {
        return spx.d("02B").contains(Integer.valueOf(i));
    }

    public static void b(List<SourceInfo> list, Context context) {
        if (koq.b(list)) {
            return;
        }
        HashSet<Integer> d = spx.d("095");
        Iterator<SourceInfo> it = list.iterator();
        while (it.hasNext()) {
            if (d.contains(Integer.valueOf(it.next().getDeviceType()))) {
                it.remove();
            }
        }
    }

    public static int[] a(HashSet<Integer> hashSet) {
        int[] iArr = new int[hashSet.size()];
        Iterator<Integer> it = hashSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        return iArr;
    }
}
