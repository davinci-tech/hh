package com.huawei.ads.adsrec;

import android.content.Context;
import com.huawei.ads.adsrec.bean.SampleData;
import com.huawei.ads.adsrec.db.table.DsContentRelRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public abstract class v0 {

    class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Map f1687a;
        final /* synthetic */ Map c;
        final /* synthetic */ Map d;
        final /* synthetic */ Map e;

        @Override // java.lang.Runnable
        public void run() {
            j.a((Map<String, Integer>) this.f1687a, (Map<String, Integer>) this.d, (Map<String, Integer>) this.c, (Map<String, Integer>) this.e);
        }

        c(Map map, Map map2, Map map3, Map map4) {
            this.f1687a = map;
            this.d = map2;
            this.c = map3;
            this.e = map4;
        }
    }

    private static List<SampleData> a(List<DsContentRelRecord> list, double d) {
        ArrayList arrayList = new ArrayList();
        Iterator<DsContentRelRecord> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new SampleData(it.next(), d));
        }
        return arrayList;
    }

    private static List<SampleData> a(Context context, Map<String, Double> map, long j, int i, Map<String, Integer> map2, Map<String, Integer> map3) {
        int b = o0.b();
        e eVar = new e(context);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            List<DsContentRelRecord> c2 = 1 == i ? eVar.c(key, j) : eVar.e(key, j);
            int size = c2.size();
            if (size > 0) {
                if (size > b) {
                    map3.put(key, Integer.valueOf(size));
                } else {
                    arrayList.addAll(a(c2, value.doubleValue()));
                    map2.put(key, Integer.valueOf(size));
                }
            }
        }
        arrayList.size();
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r17) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ads.adsrec.v0.a(android.content.Context):java.lang.String");
    }
}
