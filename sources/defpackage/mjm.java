package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mjm {
    public static mko e(List<MedalLocation> list) {
        if (list == null) {
            return null;
        }
        List<String> d = d(list);
        Map<String, ArrayList<String>> b = b(list);
        Map<String, ArrayList<String>> a2 = a(list);
        mko mkoVar = new mko();
        mkoVar.d(d);
        mkoVar.a(b);
        mkoVar.c(a2);
        return mkoVar;
    }

    private static List<String> d(List<MedalLocation> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        HashMap hashMap = new HashMap(5);
        for (MedalLocation medalLocation : list) {
            hashMap.put(medalLocation.acquireFirstTabDesc(), Integer.valueOf(medalLocation.acquireFirstTabPriority()));
        }
        return e(hashMap);
    }

    private static Map<String, ArrayList<String>> b(List<MedalLocation> list) {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        HashMap hashMap3 = new HashMap(16);
        for (MedalLocation medalLocation : list) {
            hashMap3.put(medalLocation.acquireSecondTabDesc(), Integer.valueOf(medalLocation.acquireSecondTabPriority()));
            String acquireFirstTabDesc = medalLocation.acquireFirstTabDesc();
            if (hashMap2.get(acquireFirstTabDesc) != null) {
                Map map = (Map) hashMap2.get(acquireFirstTabDesc);
                map.put(medalLocation.acquireSecondTabDesc(), "1");
                hashMap2.put(medalLocation.acquireFirstTabDesc(), map);
            } else {
                HashMap hashMap4 = new HashMap(16);
                hashMap4.put(medalLocation.acquireSecondTabDesc(), "1");
                hashMap2.put(medalLocation.acquireFirstTabDesc(), hashMap4);
            }
        }
        for (Map.Entry entry : hashMap2.entrySet()) {
            hashMap.put((String) entry.getKey(), a((Map<String, String>) entry.getValue()));
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            hashMap.put((String) entry2.getKey(), b((ArrayList) entry2.getValue(), hashMap3));
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalInitLayoutManager", "sortByPriority() firstTabRelationShip2=", hashMap.toString());
        return hashMap;
    }

    private static Map<String, ArrayList<String>> a(List<MedalLocation> list) {
        long j;
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        ArrayList arrayList = new ArrayList(16);
        HashMap hashMap3 = new HashMap(16);
        for (MedalLocation medalLocation : list) {
            String acquireSecondTabDesc = medalLocation.acquireSecondTabDesc();
            int acquireFirstTabPriority = medalLocation.acquireFirstTabPriority();
            String str = acquireFirstTabPriority + "_" + acquireSecondTabDesc;
            hashMap2.put(medalLocation.acquireMedalID(), Integer.valueOf(d(acquireFirstTabPriority, medalLocation.acquireSecondTabPriority(), medalLocation.acquireMedalWeight())));
            if (hashMap.get(str) != null) {
                ArrayList arrayList2 = (ArrayList) hashMap.get(str);
                arrayList2.add(medalLocation.acquireMedalID());
                hashMap.put(str, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList(16);
                arrayList3.add(medalLocation.acquireMedalID());
                hashMap.put(str, arrayList3);
            }
            if (medalLocation.acquireGainedCount() > 0) {
                try {
                    j = Long.parseLong(medalLocation.acquireMedalGainedTime());
                } catch (NumberFormatException unused) {
                    LogUtil.b("PLGACHIEVE_AchieveMedalInitLayoutManager", "getData() NumberFormatException");
                    j = 0;
                }
                arrayList.add(medalLocation.acquireMedalID());
                hashMap3.put(medalLocation.acquireMedalID(), Long.valueOf(j));
            }
        }
        LogUtil.c("PLGACHIEVE_AchieveMedalInitLayoutManager", "getMedalData() MedalRelationShip=", hashMap.toString());
        return d(hashMap, hashMap2, arrayList, hashMap3);
    }

    private static ArrayList<String> a(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        ArrayList<String> arrayList = new ArrayList<>(16);
        while (it.hasNext()) {
            arrayList.add(it.next().getKey());
        }
        return arrayList;
    }

    private static ArrayList<String> e(Map<String, Integer> map) {
        if (map == null) {
            return new ArrayList<>();
        }
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() { // from class: mjm.5
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                return entry.getValue().intValue() - entry2.getValue().intValue();
            }
        });
        ArrayList<String> arrayList2 = new ArrayList<>(16);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((String) ((Map.Entry) it.next()).getKey());
        }
        return arrayList2;
    }

    private static int d(int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(i);
        sb.append(i2);
        sb.append(i3);
        String trim = sb.toString().trim();
        if (trim.length() > 10) {
            trim = trim.substring(0, 8);
        }
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveMedalInitLayoutManager", "NumberFormatException");
            return 0;
        }
    }

    public static ArrayList<String> d(ArrayList<String> arrayList, Map<String, Long> map) {
        if (arrayList == null || map == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalInitLayoutManager", "sortByMedalGainTime: list/map is null");
            return new ArrayList<>(0);
        }
        ArrayList<Map.Entry> arrayList2 = new ArrayList(map.entrySet());
        Collections.sort(arrayList2, new Comparator<Map.Entry<String, Long>>() { // from class: mjm.3
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
                if (entry.getValue().longValue() - entry2.getValue().longValue() > 0) {
                    return -1;
                }
                if (entry.getValue().longValue() - entry2.getValue().longValue() != 0) {
                    return 1;
                }
                String key = entry.getKey();
                String key2 = entry2.getKey();
                int length = key.length();
                int length2 = key2.length();
                return length2 == length ? key2.compareTo(key) : length2 - length;
            }
        });
        ArrayList<String> arrayList3 = new ArrayList<>(16);
        for (Map.Entry entry : arrayList2) {
            if (arrayList.contains(entry.getKey())) {
                arrayList3.add((String) entry.getKey());
            }
        }
        return arrayList3;
    }

    private static Map<String, ArrayList<String>> d(Map<String, ArrayList<String>> map, Map<String, Integer> map2, ArrayList<String> arrayList, Map<String, Long> map3) {
        String str;
        HashMap hashMap = new HashMap(16);
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] split = key.split("_");
            if (split == null || split.length <= 1) {
                str = "1";
            } else {
                String str2 = split[0];
                String str3 = split[1];
                str = str2;
                key = str3;
            }
            hashMap.put(key, b(entry.getValue(), map2, arrayList, map3, str));
        }
        return hashMap;
    }

    private static ArrayList<String> b(ArrayList<String> arrayList, Map<String, Integer> map, ArrayList<String> arrayList2, Map<String, Long> map2, String str) {
        if (koq.b(arrayList2)) {
            return b(arrayList, map);
        }
        ArrayList<String> arrayList3 = new ArrayList<>(16);
        ArrayList arrayList4 = new ArrayList(16);
        ArrayList arrayList5 = new ArrayList(16);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList2.contains(arrayList.get(i))) {
                arrayList4.add(arrayList.get(i));
            } else {
                arrayList5.add(arrayList.get(i));
            }
        }
        ArrayList<String> d = d(arrayList4, map2);
        if ("1".equals(str)) {
            arrayList3.addAll(d);
            arrayList3.addAll(b(arrayList5, map));
        } else {
            arrayList3.addAll(b(arrayList5, map));
            arrayList3.addAll(d);
        }
        return arrayList3;
    }

    private static ArrayList<String> b(ArrayList<String> arrayList, Map<String, Integer> map) {
        ArrayList<String> arrayList2 = new ArrayList<>(16);
        if (arrayList != null && map != null) {
            LogUtil.c("PLGACHIEVE_AchieveMedalInitLayoutManager", "sortMapByWeight list ", arrayList.toString());
            ArrayList<Map.Entry> arrayList3 = new ArrayList(map.entrySet());
            Collections.sort(arrayList3, new Comparator<Map.Entry<String, Integer>>() { // from class: mjm.4
                @Override // java.util.Comparator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                    return entry.getValue().intValue() - entry2.getValue().intValue();
                }
            });
            for (Map.Entry entry : arrayList3) {
                if (arrayList.contains(entry.getKey())) {
                    arrayList2.add((String) entry.getKey());
                }
            }
            LogUtil.c("PLGACHIEVE_AchieveMedalInitLayoutManager", "sortMapByWeight result ", arrayList2.toString());
        }
        return arrayList2;
    }

    public static ArrayList<String> d(ArrayList<String> arrayList, ArrayList<String> arrayList2, List<MedalLocation> list) {
        ArrayList<String> arrayList3 = new ArrayList<>(16);
        if (arrayList == null) {
            return arrayList3;
        }
        ArrayList<String> b = b(arrayList, c(list));
        if (arrayList2 == null) {
            return b;
        }
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (arrayList2.contains(next)) {
                arrayList3.add(next);
            }
        }
        Iterator<String> it2 = b.iterator();
        while (it2.hasNext()) {
            String next2 = it2.next();
            if (!arrayList2.contains(next2)) {
                arrayList3.add(next2);
            }
        }
        return arrayList3;
    }

    private static Map<String, Integer> c(List<MedalLocation> list) {
        HashMap hashMap = new HashMap(16);
        for (MedalLocation medalLocation : list) {
            hashMap.put(medalLocation.acquireMedalID(), Integer.valueOf(d(medalLocation.acquireFirstTabPriority(), medalLocation.acquireSecondTabPriority(), medalLocation.acquireMedalWeight())));
        }
        return hashMap;
    }
}
