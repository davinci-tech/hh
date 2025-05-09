package defpackage;

import android.content.Context;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class ivf {
    static int c(int i) {
        return (i == 1 || i == 2 || i == 3 || i == 4) ? 0 : 1;
    }

    static boolean b(Context context, int i, List<Integer> list) {
        if (PowerKitManager.e().b() || !CommonUtil.aa(context)) {
            LogUtil.h("HiH_HiSyncUtil", "User is sleeping or network is break, can not check data status");
            return false;
        }
        try {
            List<SyncKey> b = iuz.b(context, 2, list);
            if (HiCommonUtil.d(b)) {
                LogUtil.h("HiH_HiSyncUtil", "checkDataStatus ansSyncKeys == null or ansSyncKeys.isEmpty()");
                return false;
            }
            for (SyncKey syncKey : b) {
                int intValue = syncKey.getType().intValue();
                long longValue = syncKey.getVersion().longValue();
                igq c = ijr.d().c(i, 0L, intValue);
                if (c == null) {
                    LogUtil.a("HiH_HiSyncUtil", "checkDataStatus no such data in db ,type is ", Integer.valueOf(intValue));
                    if (longValue > 0) {
                        LogUtil.c("HiH_HiSyncUtil", "checkDataStatus syncAnchorTable = null and version > 0 ");
                        return true;
                    }
                } else {
                    long a2 = c.a();
                    if (a2 < longValue) {
                        LogUtil.c("HiH_HiSyncUtil", "checkDataStatus local version is ", Long.valueOf(a2), " cloud version is ", Long.valueOf(longValue), " type is ", Integer.valueOf(intValue));
                        return true;
                    }
                }
            }
            return false;
        } catch (iut e) {
            ReleaseLogUtil.c("HiH_HiSyncUtil", "checkDataStatus error ,e is ", e.getMessage());
            return false;
        }
    }

    static HiHealthData e(Context context, int i) {
        List<Integer> a2 = iks.e().a(i);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("HiH_HiSyncUtil", "getTodayNoSyncTotalValue clients = null");
            return null;
        }
        HiAggregateOption d = ivi.d();
        List<HiHealthData> c = ivu.b(context, d.getType()[0]).c(a2, d);
        if (HiCommonUtil.d(c)) {
            LogUtil.h("HiH_HiSyncUtil", "getTodayNoSyncTotalValue datas is null or empty");
            return null;
        }
        return c.get(0);
    }

    static List<HiHealthData> e(Context context, int i, int[] iArr, String[] strArr, int[] iArr2) {
        int[] e = ivu.e(context, iArr);
        int[] c = ivu.c(context, iArr);
        ArrayList arrayList = new ArrayList(10);
        List<HiHealthData> d = d(context, i, e, strArr, iArr2);
        List<HiHealthData> d2 = d(context, i, c, strArr, iArr2);
        if (d != null) {
            arrayList.addAll(d);
        }
        if (d2 != null) {
            arrayList.addAll(d2);
        }
        return arrayList;
    }

    private static List<HiHealthData> d(Context context, int i, int[] iArr, String[] strArr, int[] iArr2) {
        boolean z;
        if (iArr == null || iArr.length == 0) {
            return null;
        }
        int i2 = iArr[0];
        List<Integer> b = ivu.a(context, i2).b(i, iArr);
        if (HiCommonUtil.d(b)) {
            LogUtil.h("HiH_HiSyncUtil", "getUploadStat no syncDays get !", " types is ", Arrays.toString(iArr));
            return null;
        }
        if (i2 == 44101) {
            List<Integer> a2 = ivu.a(context, i2).a(i, iArr);
            if (!HiCommonUtil.d(a2)) {
                b.removeAll(a2);
            }
            if (HiCommonUtil.d(b)) {
                LogUtil.h("HiH_HiSyncUtil", "getUploadStat no syncDays get !", " types is ", Arrays.toString(iArr));
                return null;
            }
            z = true;
        } else {
            z = false;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setCount(iArr2[0]);
        hiAggregateOption.setSortOrder(iArr2[1]);
        List<HiHealthData> e = ivu.b(context, i2).e(i, b, hiAggregateOption, z);
        if (HiCommonUtil.d(e)) {
            LogUtil.h("HiH_HiSyncUtil", "getUploadStat no stat get !", " types is ", Arrays.toString(iArr));
            return null;
        }
        LogUtil.a("HiH_HiSyncUtil", "getUploadStat syncDays size is ", Integer.valueOf(b.size()), " types is ", Arrays.toString(iArr));
        return e;
    }

    static List<Integer> b(Context context, int i) {
        return ijd.c(context).e(i);
    }
}
