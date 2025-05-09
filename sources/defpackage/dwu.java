package defpackage;

import java.util.LinkedList;

/* loaded from: classes3.dex */
public class dwu {
    public static void e(LinkedList linkedList) {
        if (linkedList == null) {
            return;
        }
        int size = linkedList.size();
        for (int i = 0; i < size - 15; i++) {
            linkedList.removeFirst();
        }
    }

    public static float b(dwl dwlVar, dwl dwlVar2) {
        if (dwlVar == null || dwlVar2 == null) {
            return 0.0f;
        }
        float d = d(dwlVar, dwlVar2);
        if (dwlVar.d() == 0.0f || dwlVar2.d() == 0.0f) {
            return d;
        }
        return (float) Math.sqrt(Math.pow(d, 2.0d) + Math.pow(Math.abs(dwlVar.d() - dwlVar2.d()), 2.0d));
    }

    public static float e(dwl dwlVar, dwl dwlVar2) {
        if (dwlVar == null || dwlVar2 == null) {
            return 0.0f;
        }
        float d = d(dwlVar, dwlVar2);
        long h = dwlVar2.h() - dwlVar.h();
        if (h <= 0) {
            return 0.0f;
        }
        return (d * 1000.0f) / h;
    }

    public static float d(dwl dwlVar, dwl dwlVar2) {
        if (dwlVar == null || dwlVar2 == null) {
            return 0.0f;
        }
        if (Math.abs(dwlVar.g() - dwlVar2.g()) < 1.0E-9d && Math.abs(dwlVar.j() - dwlVar2.j()) < 1.0E-9d) {
            return 0.0f;
        }
        double g = dwlVar.g() * 0.017453292519943295d;
        double g2 = dwlVar2.g() * 0.017453292519943295d;
        double sin = (Math.sin(g) * Math.sin(g2)) + (Math.cos(g) * Math.cos(g2) * Math.cos((dwlVar2.j() * 0.017453292519943295d) - (dwlVar.j() * 0.017453292519943295d)));
        if (sin > 1.0d) {
            sin = 1.0d;
        }
        if (sin < -1.0d) {
            sin = -1.0d;
        }
        return ((float) (Math.acos(sin) * 6371.0d)) * 1000.0f;
    }
}
