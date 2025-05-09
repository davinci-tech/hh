package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes8.dex */
public class fht {
    public static Map<Integer, Float> e(Map<Integer, Float> map) {
        ArrayList arrayList = new ArrayList();
        ArrayList<Integer> arrayList2 = new ArrayList();
        TreeMap treeMap = new TreeMap();
        if (map == null) {
            LogUtil.c("Track_SportPaceUtils", "paceMap is null");
            return null;
        }
        for (Map.Entry<Integer, Float> entry : map.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
            int intValue = entry.getKey().intValue();
            if (intValue < 100000) {
                if (!arrayList2.contains(Integer.valueOf(intValue))) {
                    arrayList2.add(Integer.valueOf(intValue));
                }
            } else {
                a(arrayList, arrayList2, intValue);
            }
        }
        for (Integer num : arrayList2) {
            if (treeMap.containsKey(num)) {
                treeMap.remove(num);
            }
        }
        if (treeMap.size() != 0) {
            LogUtil.c("Track_SportPaceUtils", "The validate paceMap", treeMap.toString());
        }
        return treeMap;
    }

    private static void a(List<Integer> list, List<Integer> list2, int i) {
        int i2 = i / 100000;
        if (i2 % 100 != 0) {
            if (list2.contains(Integer.valueOf(i))) {
                return;
            }
            list2.add(Integer.valueOf(i));
        } else {
            int i3 = i2 / 100;
            if (list.contains(Integer.valueOf(i3))) {
                list2.add(Integer.valueOf(i));
            } else {
                list.add(Integer.valueOf(i3));
            }
        }
    }

    public static Float[] d(Map<Integer, Float> map) {
        Float[] fArr = new Float[2];
        if (map == null) {
            return fArr;
        }
        Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            float floatValue = it.next().getValue().floatValue();
            if (!z) {
                fArr[0] = Float.valueOf(floatValue);
                fArr[1] = Float.valueOf(floatValue);
                z = true;
            }
            if (floatValue > fArr[1].floatValue()) {
                fArr[1] = Float.valueOf(floatValue);
            }
            if (floatValue < fArr[0].floatValue()) {
                fArr[0] = Float.valueOf(floatValue);
            }
        }
        return fArr;
    }
}
