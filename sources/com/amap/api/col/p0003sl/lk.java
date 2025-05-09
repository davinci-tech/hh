package com.amap.api.col.p0003sl;

import com.huawei.android.airsharing.api.IEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class lk {
    private mo b;

    /* renamed from: a, reason: collision with root package name */
    private List<mp> f1327a = new ArrayList();
    private ArrayList<mp> c = new ArrayList<>();

    final List<mp> a(mo moVar, List<mp> list, boolean z, long j, long j2) {
        if (!b(moVar, list, z, j, j2)) {
            return null;
        }
        b(this.c, list);
        this.f1327a.clear();
        this.f1327a.addAll(list);
        this.b = moVar;
        return this.c;
    }

    private boolean b(mo moVar, List<mp> list, boolean z, long j, long j2) {
        if (!z || !a(moVar, j, j2) || list == null || list.size() <= 0) {
            return false;
        }
        if (this.b == null) {
            return true;
        }
        boolean a2 = a(moVar);
        return !a2 ? true ^ a(list, this.f1327a) : a2;
    }

    private static boolean a(mo moVar, long j, long j2) {
        return j > 0 && j2 - j < ((long) ((moVar.g > 10.0f ? 1 : (moVar.g == 10.0f ? 0 : -1)) >= 0 ? 2000 : IEventListener.EVENT_ID_NOTICE_DIALOG_SHOW));
    }

    private boolean a(mo moVar) {
        float f = 10.0f;
        if (moVar.g > 10.0f) {
            f = 200.0f;
        } else if (moVar.g > 2.0f) {
            f = 50.0f;
        }
        return moVar.a(this.b) > ((double) f);
    }

    private static boolean a(List<mp> list, List<mp> list2) {
        if (list != null && list2 != null) {
            if (list.size() <= list2.size()) {
                list2 = list;
                list = list2;
            }
            HashMap hashMap = new HashMap(list.size());
            Iterator<mp> it = list.iterator();
            while (it.hasNext()) {
                hashMap.put(Long.valueOf(it.next().f1338a), 1);
            }
            Iterator<mp> it2 = list2.iterator();
            int i = 0;
            while (it2.hasNext()) {
                if (((Integer) hashMap.get(Long.valueOf(it2.next().f1338a))) != null) {
                    i++;
                }
            }
            if (i * 2.0d >= (r1 + r2) * 0.5d) {
                return true;
            }
        }
        return false;
    }

    private void b(List<mp> list, List<mp> list2) {
        list.clear();
        if (list2 != null) {
            List<mp> b = b(a(list2));
            int size = b.size();
            if (size > 40) {
                size = 40;
            }
            for (int i = 0; i < size; i++) {
                list.add(b.get(i));
            }
        }
    }

    private static List<mp> a(List<mp> list) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            mp mpVar = list.get(i);
            hashMap.put(Integer.valueOf(mpVar.c), mpVar);
        }
        arrayList.addAll(hashMap.values());
        return arrayList;
    }

    private List<mp> b(List<mp> list) {
        Collections.sort(list, new Comparator<mp>() { // from class: com.amap.api.col.3sl.lk.1
            @Override // java.util.Comparator
            public final /* synthetic */ int compare(mp mpVar, mp mpVar2) {
                return a(mpVar, mpVar2);
            }

            private static int a(mp mpVar, mp mpVar2) {
                return mpVar2.c - mpVar.c;
            }
        });
        return list;
    }
}
