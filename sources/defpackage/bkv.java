package defpackage;

import android.text.TextUtils;
import java.util.List;

/* loaded from: classes3.dex */
public class bkv {
    public static bim c(bir birVar, List<bil> list) {
        if (birVar == null || list == null) {
            return null;
        }
        bim bimVar = new bim();
        bimVar.b(birVar.j());
        bimVar.c(birVar.b());
        bimVar.a(list);
        bimVar.b(birVar.c());
        bimVar.a(birVar.f());
        return bimVar;
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return bjz.b().e(str);
    }
}
