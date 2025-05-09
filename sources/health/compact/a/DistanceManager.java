package health.compact.a;

import android.content.Context;
import com.huawei.health.manager.common.SingleDayBaseManager;
import com.huawei.health.manager.util.UserInfo;

/* loaded from: classes.dex */
public class DistanceManager extends SingleDayBaseManager {
    private static final Object b = new Object();
    private static DistanceManager e;

    /* renamed from: a, reason: collision with root package name */
    private double f13112a;
    private long c;
    private UserInfo d;

    private DistanceManager(Context context) {
        super(context);
        this.f13112a = 0.0d;
        this.d = null;
        this.c = 0L;
        this.d = UserInfo.d();
    }

    public static DistanceManager a(Context context) {
        DistanceManager distanceManager;
        synchronized (b) {
            if (e == null) {
                e = new DistanceManager(context);
            }
            distanceManager = e;
        }
        return distanceManager;
    }

    public int e(int i) {
        return b(i);
    }

    private int b(int i) {
        double a2 = CalculateDistanceUtils.a(i, this.d.c()) * 1000.0d;
        int i2 = (int) (this.f13112a + a2);
        long b2 = com.huawei.hwlogsmodel.LogUtil.b(1000, this.c);
        if (b2 != -1) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_DistanceManager", "computeDistanceStatic", " stepDis = ", Double.valueOf(a2), " mDisDiffStand = ", Double.valueOf(this.f13112a), " distanceStatic = ", Integer.valueOf(i2));
            this.c = b2;
        }
        return i2;
    }

    public void e(int i, int i2) {
        int e2 = e(i);
        com.huawei.hwlogsmodel.LogUtil.a("Step_DistanceManager", "syncDistance", " stepDis = ", Integer.valueOf(e2), " mDisDiffStand = ", Double.valueOf(this.f13112a), " distanceFromDb = ", Integer.valueOf(i2));
        if (e2 < i2) {
            double d = this.f13112a + (i2 - e2);
            this.f13112a = d;
            com.huawei.hwlogsmodel.LogUtil.a("Step_DistanceManager", "saveDiffDistanceToFile total db mDistanceDiffStand ", Double.valueOf(d));
        }
    }

    public void e() {
        this.f13112a = 0.0d;
    }
}
