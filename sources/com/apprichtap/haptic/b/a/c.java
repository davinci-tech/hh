package com.apprichtap.haptic.b.a;

import defpackage.ms;
import defpackage.mu;
import defpackage.mw;
import defpackage.na;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public interface c {
    int a();

    int getDuration();

    static boolean a(c cVar) {
        na naVar;
        ArrayList<mw> arrayList;
        ArrayList<ms> arrayList2;
        if (cVar == null) {
            return false;
        }
        if (1 != cVar.a()) {
            return (2 != cVar.a() || (naVar = (na) cVar) == null || (arrayList = naVar.b) == null || arrayList.size() < 1 || naVar.b.get(0).b == null || naVar.b.get(0).b.size() < 1 || naVar.b.get(0).b.get(0).d == null) ? false : true;
        }
        mu muVar = (mu) cVar;
        return (muVar == null || (arrayList2 = muVar.c) == null || arrayList2.size() < 1 || muVar.c.get(0).d == null) ? false : true;
    }
}
