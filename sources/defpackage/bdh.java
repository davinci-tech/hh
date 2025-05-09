package defpackage;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes.dex */
public class bdh {
    private static boolean d = false;
    private static int e = -1;

    public static SparseArray<HealthLifeBean> nU_(int i, SparseArray<HealthLifeBean> sparseArray, SparseIntArray sparseIntArray) {
        d = false;
        if (sparseIntArray == null || sparseArray == null) {
            LogUtil.h("HealthLife_WeekTaskHelper", "getWeekTaskNewBeanList list is null.");
            return sparseArray;
        }
        SparseArray<HealthLifeBean> sparseArray2 = new SparseArray<>(sparseArray.size());
        int nT_ = nT_(i, sparseArray);
        int i2 = 0;
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            HealthLifeBean valueAt = sparseArray.valueAt(i3);
            int recordDay = valueAt.getRecordDay();
            int complete = valueAt.getComplete();
            int rest = valueAt.getRest();
            int max = Math.max(sparseIntArray.get(recordDay), 0);
            valueAt.setResult(String.valueOf(max));
            if (max >= CommonUtil.h(valueAt.getTarget())) {
                b(valueAt);
                if (c(max, nT_, valueAt)) {
                    nT_++;
                }
            } else {
                if (complete < 1 || rest != 1) {
                    if (complete >= 1 && rest == 0) {
                        sparseArray2.put(recordDay, valueAt);
                    } else if (complete != 0 || recordDay == DateFormatUtil.b(System.currentTimeMillis())) {
                        LogUtil.c("HealthLife_WeekTaskHelper", "other branch.");
                    } else if (nT_ - i2 > 0) {
                        a(valueAt);
                    }
                }
                i2++;
            }
            sparseArray2.put(recordDay, valueAt);
        }
        if (i == 3) {
            e = nT_;
        }
        return sparseArray2;
    }

    private static boolean c(int i, int i2, HealthLifeBean healthLifeBean) {
        if (healthLifeBean.getId() != 3 || i < CommonUtil.h(healthLifeBean.getTarget()) * 2 || i2 >= 4) {
            return false;
        }
        if (healthLifeBean.getRecordDay() == DateFormatUtil.b(System.currentTimeMillis())) {
            d = true;
        }
        return true;
    }

    private static void a(HealthLifeBean healthLifeBean) {
        if (healthLifeBean.getComplete() <= 0) {
            aza.d(healthLifeBean.getId(), 1);
        }
        healthLifeBean.setComplete(1);
        healthLifeBean.setRest(1);
        long currentTimeMillis = System.currentTimeMillis();
        healthLifeBean.setTimestamp(currentTimeMillis);
        healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
        healthLifeBean.setSyncStatus(0);
        healthLifeBean.setIsUpdated(true);
    }

    private static void b(HealthLifeBean healthLifeBean) {
        int complete = healthLifeBean.getComplete();
        int rest = healthLifeBean.getRest();
        if (complete == 0) {
            healthLifeBean.setComplete(1);
            long currentTimeMillis = System.currentTimeMillis();
            healthLifeBean.setTimestamp(currentTimeMillis);
            healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
            healthLifeBean.setIsUpdated(true);
            healthLifeBean.setSyncStatus(0);
            aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
            return;
        }
        if (complete >= 1 && rest == 1) {
            healthLifeBean.setComplete(1);
            healthLifeBean.setRest(0);
            long currentTimeMillis2 = System.currentTimeMillis();
            healthLifeBean.setTimestamp(currentTimeMillis2);
            healthLifeBean.setTimeZone(jdl.q(currentTimeMillis2));
            healthLifeBean.setIsUpdated(true);
            healthLifeBean.setSyncStatus(0);
            return;
        }
        LogUtil.a("HealthLife_WeekTaskHelper", "Complete with excercise.");
    }

    public static int nS_(int i, SparseArray<HealthLifeBean> sparseArray) {
        int i2;
        return (i == 4 || i == 14 || (i2 = e) < 0) ? nT_(i, sparseArray) : i2;
    }

    public static boolean d() {
        return d;
    }

    private static int nT_(int i, SparseArray<HealthLifeBean> sparseArray) {
        if (i != 4 && i != 3 && i != 14) {
            LogUtil.h("HealthLife_WeekTaskHelper", "getAvailableRestDays not week task.");
            return 0;
        }
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.h("HealthLife_WeekTaskHelper", "getAvailableRestDays beanArray is empty");
            return 0;
        }
        HealthLifeBean valueAt = sparseArray.valueAt(0);
        HealthLifeBean valueAt2 = sparseArray.valueAt(sparseArray.size() - 1);
        if (valueAt == null || valueAt2 == null) {
            LogUtil.h("HealthLife_WeekTaskHelper", "getAvailableRestDays startBean or endBean is empty");
            return 0;
        }
        int recordDay = valueAt.getRecordDay();
        int recordDay2 = valueAt2.getRecordDay();
        List<Integer> f = azi.f(recordDay);
        if (koq.b(f) || f.size() != 7) {
            LogUtil.h("HealthLife_WeekTaskHelper", "getAvailableRestDays weekDays is empty or invalid");
            return 0;
        }
        int i2 = 0;
        for (Integer num : f) {
            if (num.intValue() >= recordDay && (num.intValue() > recordDay2 || sparseArray.get(num.intValue()) != null)) {
                i2++;
            }
        }
        int[] iArr = {0, 0, 1, 1, 1, 2, 2, 2};
        int[] iArr2 = {0, 0, 1, 2, 2, 3, 3, 4};
        if (i2 >= 8) {
            LogUtil.h("HealthLife_WeekTaskHelper", "getDefaultAvailableRestDays invalid parameter caused by variable subscriptionDays");
            return 0;
        }
        if (i == 4 || i == 14) {
            return iArr2[i2];
        }
        return iArr[i2];
    }
}
