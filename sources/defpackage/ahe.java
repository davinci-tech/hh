package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ahe {
    public String c(List<ahb> list) {
        List<ahb> e = e(d(b(a(list))));
        String str = "";
        if (e.size() > 0) {
            int i = 0;
            for (ahb ahbVar : e) {
                if (ahbVar.i() >= i) {
                    i = ahbVar.i();
                    str = ahbVar.b();
                }
            }
        }
        return str;
    }

    private List<ahb> a(List<ahb> list) {
        ArrayList arrayList = new ArrayList();
        for (ahb ahbVar : list) {
            if (ahbVar.e() > -1) {
                arrayList.add(ahbVar);
            }
        }
        return !arrayList.isEmpty() ? arrayList : list;
    }

    private List<ahb> b(List<ahb> list) {
        ArrayList arrayList = new ArrayList();
        for (ahb ahbVar : list) {
            if (ahbVar.d() == 1) {
                arrayList.add(ahbVar);
            }
        }
        return !arrayList.isEmpty() ? arrayList : list;
    }

    private List<ahb> d(List<ahb> list) {
        ArrayList arrayList = new ArrayList();
        for (ahb ahbVar : list) {
            if (ahbVar.c() == 0) {
                arrayList.add(ahbVar);
            }
        }
        return !arrayList.isEmpty() ? arrayList : list;
    }

    private List<ahb> e(List<ahb> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (ahb ahbVar : list) {
            if (ahbVar.a() > i) {
                arrayList.clear();
                i = ahbVar.a();
            } else if (ahbVar.a() != i) {
                ahi.e.a("OptimizationCenter", "condition Low level");
            }
            arrayList.add(ahbVar);
        }
        return !arrayList.isEmpty() ? arrayList : list;
    }
}
