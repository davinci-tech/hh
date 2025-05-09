package defpackage;

import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class qms {
    private static Map<String, String> e;

    static {
        HashMap hashMap = new HashMap(16);
        e = hashMap;
        hashMap.put("08020200", "30,0");
        e.put("08020201", "30,0");
        e.put("08020202", "30,0");
        e.put("08030200", "30,0,3,0");
        e.put("08030201", "3,0");
        e.put("08030202", "30,0,3,0");
        e.put("08030203", "30,0");
        e.put("08100000", "30,0");
        e.put("08100001", "30,0");
        e.put("08100002", "30,0");
        e.put("08100003", "30,0");
        e.put("08100004", "30,0");
        e.put("08100005", "30,0");
        e.put("08100006", "30,0");
        e.put("08100007", "30,0");
        e.put("08100008", "30,0");
        e.put("08110000", "7,0,9,0");
        e.put("08110001", "7,0,9,0");
        e.put("08110002", "7,0,9,0");
        e.put("08110003", "15,1,7,0,9,0");
        e.put("08110004", "7,0,9,0");
        e.put("08110005", "7,0,9,0");
        e.put("08160000", "3,0");
        e.put("08160001", "3,0");
        e.put("08160002", "3,0");
        e.put("08160003", "3,0");
        e.put("08160004", "3,0");
        e.put("08160005", "3,0");
        e.put("08170004", "10,0");
        e.put("08190000", "15,0,30,0");
        e.put("08200000", "30,0");
        e.put("08200001", "30,0");
        e.put("08200002", "30,0");
        e.put("08200003", "30,0");
        e.put("08200004", "30,0");
        e.put("08200005", "30,0");
        e.put("08211101", "14,0");
        e.put("08240006", "21,0,24,0");
        e.put("04010301", "7,0");
        e.put("04010303", "7,0");
        e.put("04010201", "7,0");
        e.put("04010203", "7,0");
        e.put("04020301", "7,0");
        e.put("04020303", "7,0");
        e.put("04020201", "7,0");
        e.put("04020203", "7,0");
        e.put("04030101", "7,0");
        e.put("04030103", "7,0");
        e.put("04040301", "7,0");
        e.put("04040303", "7,0");
        e.put("04040201", "7,0");
        e.put("04040203", "7,0");
        e.put("04050101", "7,0");
        e.put("04050103", "7,0");
        e.put("04060101", "7,0");
        e.put("04060103", "7,0");
        e.put("04070301", "7,0");
        e.put("04070303", "7,0");
        e.put("04070201", "7,0");
        e.put("04070203", "7,0");
        e.put("04080301", "7,0");
        e.put("04080303", "7,0");
        e.put("04080201", "7,0");
        e.put("04080203", "7,0");
        e.put("04090101", "7,0");
        e.put("04090103", "7,0");
        e.put("04100101", "7,0");
        e.put("04100103", "7,0");
        e.put("04110101", "7,0");
        e.put("04110103", "7,0");
        e.put("06050000", "3,0");
        e.put("06050100", "3,0");
        e.put("06050200", "3,0");
        e.put("06050300", "3,0");
        e.put("06060000", "20,0");
        e.put("06060100", "20,0");
        e.put("06060200", "20,0");
        e.put("06060300", "20,0");
    }

    public static List<String> a(String str) {
        ArrayList arrayList = new ArrayList(16);
        if (!e.containsKey(str)) {
            return arrayList;
        }
        String[] split = e.get(str).split(",");
        for (int i = 1; i < split.length; i += 2) {
            if (Integer.valueOf(split[i]).intValue() == 1) {
                arrayList.add(UnitUtil.e(Integer.valueOf(split[i - 1]).intValue(), 2, 0));
            } else {
                arrayList.add(UnitUtil.e(Integer.valueOf(split[i - 1]).intValue(), 1, 0));
            }
        }
        return arrayList;
    }
}
