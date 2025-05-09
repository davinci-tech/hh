package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.floatview.HealthFloatingView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class nmh {
    private static List<Integer> d = new ArrayList();
    private static List<Integer> c = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, WeakReference<HealthFloatingView>> f15387a = new HashMap();
    private static int b = 3001;

    public static void c(int i) {
        b = i;
    }

    public static void a(List<Integer> list) {
        d = list;
    }

    public static List<Integer> d() {
        return d;
    }

    public static void d(List<Integer> list) {
        c = list;
    }

    public static void a(int i, HealthFloatingView healthFloatingView) {
        if (healthFloatingView == null) {
            return;
        }
        LogUtil.a("FloatingViewMgr", "mTabList: ", c);
        if (!c.contains(Integer.valueOf(i))) {
            LogUtil.a("FloatingViewMgr", "not in mSportsTabList, not add.");
            return;
        }
        LogUtil.a("FloatingViewMgr", "addView");
        f15387a.put(Integer.valueOf(i), new WeakReference<>(healthFloatingView));
        LogUtil.a("FloatingViewMgr", "mViewMap.size(): ", Integer.valueOf(f15387a.size()));
    }

    public static void b(int i) {
        if ((f15387a.get(Integer.valueOf(i)) != null ? f15387a.get(Integer.valueOf(i)).get() : null) == null) {
            return;
        }
        if (!c.contains(Integer.valueOf(i))) {
            LogUtil.a("FloatingViewMgr", "not in mSportsTabList, not add.");
        } else {
            LogUtil.a("FloatingViewMgr", "removeView");
            f15387a.put(Integer.valueOf(i), null);
        }
    }

    public static void a(int i) {
        LogUtil.a("FloatingViewMgr", "setView, mViewPositionId: ", Integer.valueOf(b), " positionId: ", Integer.valueOf(i));
        if ((f15387a.get(Integer.valueOf(i)) != null ? f15387a.get(Integer.valueOf(i)).get() : null) == null) {
            LogUtil.a("FloatingViewMgr", "view is null");
            return;
        }
        int i2 = b;
        if (i2 == i) {
            if (d.contains(Integer.valueOf(i))) {
                LogUtil.a("FloatingViewMgr", "is sportTab, set fulls.");
                e(i);
                return;
            } else {
                LogUtil.a("FloatingViewMgr", "not sportTab.");
                d(i);
                return;
            }
        }
        if (d.contains(Integer.valueOf(i2)) && d.contains(Integer.valueOf(i))) {
            e(i);
            return;
        }
        HealthFloatingView healthFloatingView = f15387a.get(Integer.valueOf(i)) != null ? f15387a.get(Integer.valueOf(i)).get() : null;
        if (healthFloatingView != null) {
            healthFloatingView.c();
        }
    }

    private static void e(int i) {
        LogUtil.a("FloatingViewMgr", "setSportTabFloatingBoxVisible, positionId: ", Integer.valueOf(i));
        if ((f15387a.get(3044) != null ? f15387a.get(3044).get() : null) != null) {
            d(3044);
        } else {
            d(i);
        }
    }

    private static void d(int i) {
        LogUtil.a("FloatingViewMgr", "setOtherViewInvisible, positionId: ", Integer.valueOf(i));
        for (Map.Entry<Integer, WeakReference<HealthFloatingView>> entry : f15387a.entrySet()) {
            HealthFloatingView healthFloatingView = entry.getValue() != null ? entry.getValue().get() : null;
            if (healthFloatingView != null && entry.getKey().intValue() != i) {
                healthFloatingView.c();
            } else {
                h(i);
            }
        }
    }

    public static void a(HealthFloatingView healthFloatingView) {
        LogUtil.a("FloatingViewMgr", "setOtherViewInvisible floatingView");
        for (Map.Entry<Integer, WeakReference<HealthFloatingView>> entry : f15387a.entrySet()) {
            HealthFloatingView healthFloatingView2 = entry.getValue() != null ? entry.getValue().get() : null;
            if (healthFloatingView2 != null && healthFloatingView2 != healthFloatingView && !healthFloatingView2.i()) {
                LogUtil.a("FloatingViewMgr", "hide other floating view, positionId: ", entry.getKey());
                healthFloatingView2.c();
            }
        }
    }

    private static void h(int i) {
        LogUtil.a("FloatingViewMgr", "setViewShow");
        HealthFloatingView healthFloatingView = f15387a.get(Integer.valueOf(i)) != null ? f15387a.get(Integer.valueOf(i)).get() : null;
        if (healthFloatingView != null) {
            healthFloatingView.j();
        }
    }
}
