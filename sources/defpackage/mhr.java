package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class mhr {
    public static void a(Context context, int i, final CountDownLatch countDownLatch, final List<HiHealthData> list) {
        mcv.d(context).getAdapter().getSleepDatas(mht.b(i, true), mht.b(i, false), new AchieveCallback() { // from class: mhr.5
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    list.addAll((List) obj);
                } else {
                    LogUtil.h("PLGACHIEVE_InitialCalRule", "getAnnualSleepRecords data is not correct");
                }
                countDownLatch.countDown();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> int d(T t, List<HiHealthData> list) {
        if (!(t instanceof SparseArray)) {
            LogUtil.h("PLGACHIEVE_InitialCalRule", "saveAchieveSumDays: !(data instanceof SparseArray)");
            return 0;
        }
        try {
            SparseArray sparseArray = (SparseArray) t;
            if (sparseArray.size() <= 0) {
                LogUtil.h("PLGACHIEVE_InitialCalRule", "totalDetailTask: complete with empty data [countDown(-3)]");
                return 0;
            }
            LogUtil.a("PLGACHIEVE_InitialCalRule", "totalDetailTask: success query totalDetailData from sport health");
            Object obj = sparseArray.get(40002);
            List list2 = obj instanceof List ? (List) obj : null;
            Object obj2 = sparseArray.get(40003);
            List list3 = obj2 instanceof List ? (List) obj2 : null;
            Object obj3 = sparseArray.get(40004);
            List list4 = obj3 instanceof List ? (List) obj3 : null;
            HashSet hashSet = new HashSet(16);
            hashSet.addAll(e(list2, false));
            hashSet.addAll(e(list3, false));
            hashSet.addAll(e(list4, false));
            hashSet.addAll(e(list, true));
            ArrayList arrayList = new ArrayList(hashSet);
            if (koq.b(arrayList)) {
                LogUtil.h("PLGACHIEVE_InitialCalRule", "calculateTotalDays list isEmpty!");
                return 0;
            }
            return arrayList.size();
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_InitialCalRule", "saveAchieveSumDays data ClassCastException");
            return 0;
        }
    }

    private static Set<Long> e(List<HiHealthData> list, boolean z) {
        HashSet hashSet = new HashSet(16);
        if (!koq.b(list)) {
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getFloat("point_value") > 0.0f || z) {
                    hashSet.add(Long.valueOf(hiHealthData.getStartTime()));
                }
            }
        }
        return hashSet;
    }

    public static int e(float f) {
        LogUtil.a("PLGACHIEVE_InitialCalRule", "getCalculateDistance totalStep ", Float.valueOf(f));
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        return mht.d(userInfo != null ? kok.c((int) f, userInfo.getHeightOrDefaultValue()) * 1000.0d : 0.0d);
    }
}
