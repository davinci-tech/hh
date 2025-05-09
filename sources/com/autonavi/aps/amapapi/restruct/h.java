package com.autonavi.aps.amapapi.restruct;

import android.content.Context;
import android.os.Handler;
import com.amap.api.col.p0003sl.mp;

/* loaded from: classes2.dex */
public final class h extends a<mp> {
    @Override // com.autonavi.aps.amapapi.restruct.a
    final /* bridge */ /* synthetic */ void a(mp mpVar, long j) {
        a2(mpVar, j);
    }

    @Override // com.autonavi.aps.amapapi.restruct.a
    public final /* synthetic */ String b(mp mpVar) {
        return a(mpVar);
    }

    @Override // com.autonavi.aps.amapapi.restruct.a
    final /* synthetic */ int c(mp mpVar) {
        return b2(mpVar);
    }

    @Override // com.autonavi.aps.amapapi.restruct.a
    final /* synthetic */ long d(mp mpVar) {
        return c2(mpVar);
    }

    public h(Context context, String str, Handler handler) {
        super(context, str, handler);
    }

    private static String a(mp mpVar) {
        return mpVar == null ? "" : mpVar.a();
    }

    /* renamed from: b, reason: avoid collision after fix types in other method */
    private static int b2(mp mpVar) {
        if (mpVar == null) {
            return -113;
        }
        return mpVar.c;
    }

    /* renamed from: c, reason: avoid collision after fix types in other method */
    private static long c2(mp mpVar) {
        if (mpVar == null) {
            return 0L;
        }
        return mpVar.f;
    }

    @Override // com.autonavi.aps.amapapi.restruct.a
    final long b() {
        return com.autonavi.aps.amapapi.config.a.e;
    }

    @Override // com.autonavi.aps.amapapi.restruct.a
    final long c() {
        return com.autonavi.aps.amapapi.config.a.f;
    }

    /* renamed from: a, reason: avoid collision after fix types in other method */
    private static void a2(mp mpVar, long j) {
        if (mpVar != null) {
            mpVar.f = j;
        }
    }
}
