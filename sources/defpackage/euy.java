package defpackage;

import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes8.dex */
public class euy {
    public static List<ffi> b(Plan plan) {
        List<PlanWorkout> acquireWorkouts = plan.acquireWorkouts();
        if (koq.b(acquireWorkouts)) {
            return new ArrayList(10);
        }
        ArrayList<ffi> c = c(gib.d(plan.acquireStartDate()), acquireWorkouts, c(plan));
        if (plan.acquireType() == 3) {
            evb.a(c, gib.d(plan.acquireStartDate()));
        } else {
            evb.d(c, gib.d(plan.acquireStartDate()));
        }
        return c;
    }

    private static ArrayList<ffi> c(int i, List<PlanWorkout> list, Map<Integer, fex> map) {
        if (i == -1) {
            return new ArrayList<>(10);
        }
        TreeMap treeMap = new TreeMap();
        for (PlanWorkout planWorkout : list) {
            if (planWorkout != null) {
                int acquireOrder = planWorkout.popWeekInfo().acquireOrder();
                if (treeMap.get(Integer.valueOf(acquireOrder)) == null) {
                    treeMap.put(Integer.valueOf(acquireOrder), new ffi(planWorkout.popWeekInfo()));
                }
            }
        }
        return c(i, (ArrayList<ffi>) new ArrayList(treeMap.values()), map);
    }

    private static Map<Integer, fex> c(Plan plan) {
        int d;
        HashMap hashMap = new HashMap(10);
        for (PlanWorkout planWorkout : plan.acquireWorkouts()) {
            if (planWorkout != null && (d = gib.d(planWorkout.popDayInfo().acquireDate())) != -1) {
                fex fexVar = (fex) hashMap.get(Integer.valueOf(d));
                if (fexVar == null) {
                    fexVar = new fex(d, planWorkout.popDayInfo());
                    hashMap.put(Integer.valueOf(d), fexVar);
                }
                fexVar.e().add(planWorkout);
            }
        }
        return hashMap;
    }

    private static ArrayList<ffi> c(int i, ArrayList<ffi> arrayList, Map<Integer, fex> map) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ffi ffiVar = arrayList.get(i2);
            for (int i3 = 0; i3 < 7; i3++) {
                ffiVar.d().set(i3, map.get(Integer.valueOf((i2 * 7) + i3 + i)));
            }
        }
        return arrayList;
    }
}
