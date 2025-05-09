package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class kcb {
    public static String d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return "";
        }
        return deviceInfo.getSecurityUuid() + "#ANDROID21";
    }

    public static long b(String str, int i, int i2, String str2) {
        String str3 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + bgv.e(str) + "_" + i;
        long b = SharedPreferenceManager.b(String.valueOf(i2), str3, 0L);
        LogUtil.a("SampleSequenceUtil", "getLastTimeFromStorage identify:", blt.a(str), ",dicId:", Integer.valueOf(i), ",storageKey:", str3, ",lastStorageTime:", Long.valueOf(b), ",tag:", str2);
        return b;
    }

    public static void e(String str, int i, long j, int i2, String str2) {
        String str3 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + bgv.e(str) + "_" + i;
        LogUtil.a("SampleSequenceUtil", "saveLastTimeToStorage storageKey:", blt.a(str), ",dicId:", Integer.valueOf(i), ",storageKey:", str3, ",tag:", str2);
        SharedPreferenceManager.e(String.valueOf(i2), str3, j);
    }

    public static boolean e(String str, int i, String str2) {
        String str3 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + bgv.e(str) + "_CoreSleepProcess";
        boolean a2 = SharedPreferenceManager.a(String.valueOf(i), str3, false);
        LogUtil.a("SampleSequenceUtil", "getCodeFromStorage identify:", blt.a(str), ",storageKey:", str3, ",lastStorageTime:", Boolean.valueOf(a2), ",tag:", str2);
        return a2;
    }

    public static void e(String str, boolean z, int i, String str2) {
        String str3 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + bgv.e(str) + "_CoreSleepProcess";
        LogUtil.a("SampleSequenceUtil", "saveCodeToStorage storageKey:", blt.a(str), ",storageKey:", str3, ",tag:", str2);
        SharedPreferenceManager.e(String.valueOf(i), str3, z);
    }

    public static void a(final int i, List<HiHealthData> list) {
        if (list == null || list.size() == 1) {
            return;
        }
        Collections.sort(list, new Comparator<HiHealthData>() { // from class: kcb.3
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                if (i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()) {
                    return Long.compare(hiHealthData.getStartTime(), hiHealthData2.getStartTime());
                }
                return Long.compare(hiHealthData.getEndTime(), hiHealthData2.getEndTime());
            }
        });
        LogUtil.a("SampleSequenceUtil", "processHiHealthSubtype:", list);
    }

    public static void e(String str, LinkedList<kbm> linkedList) {
        a(str, linkedList, null);
    }

    public static void a(String str, LinkedList<kbm> linkedList, IBaseResponseCallback iBaseResponseCallback) {
        if (linkedList.isEmpty()) {
            LogUtil.h("SampleSequenceUtil", "onDataReceived singleInfosList is Empty");
            return;
        }
        LinkedList linkedList2 = new LinkedList();
        LinkedList linkedList3 = new LinkedList();
        e(linkedList, (LinkedList<kbm>) linkedList2, (LinkedList<kbm>) linkedList3);
        if (!linkedList2.isEmpty()) {
            keu d = d(str, linkedList2);
            LogUtil.a("SampleSequenceUtil", "startToSync PointData");
            ket.a().e("DIC_SYNC_TASK", d, iBaseResponseCallback);
        }
        if (linkedList3.isEmpty()) {
            return;
        }
        keu d2 = d(str, linkedList3);
        LogUtil.a("SampleSequenceUtil", "startToSync SequenceData");
        ket.a().e("DIC_SEQUENCE_SYNC_TASK", d2, iBaseResponseCallback);
    }

    private static void e(LinkedList<kbm> linkedList, LinkedList<kbm> linkedList2, LinkedList<kbm> linkedList3) {
        Iterator<kbm> it = linkedList.iterator();
        while (it.hasNext()) {
            kbm next = it.next();
            int d = kbk.d(next.d());
            int d2 = kbq.d(next.d());
            if (d != -1) {
                linkedList2.add(next);
            }
            if (d2 != -1) {
                linkedList3.add(next);
            }
        }
    }

    private static keu d(String str, LinkedList<kbm> linkedList) {
        keu keuVar = new keu();
        keuVar.d(str);
        keuVar.c(2);
        keuVar.b(linkedList);
        return keuVar;
    }
}
