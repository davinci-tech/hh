package com.huawei.hms.network.embedded;

import android.support.v4.media.session.PlaybackStateCompat;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class wb {

    /* renamed from: a, reason: collision with root package name */
    public static final long f5562a = 65536;

    @Nullable
    public static vb b;
    public static long c;

    public static void a(vb vbVar) {
        if (vbVar.f != null || vbVar.g != null) {
            throw new IllegalArgumentException();
        }
        if (vbVar.d) {
            return;
        }
        synchronized (wb.class) {
            long j = c + PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            if (j > 65536) {
                return;
            }
            c = j;
            vbVar.f = b;
            vbVar.c = 0;
            vbVar.b = 0;
            b = vbVar;
        }
    }

    public static vb a() {
        synchronized (wb.class) {
            vb vbVar = b;
            if (vbVar == null) {
                return new vb();
            }
            b = vbVar.f;
            vbVar.f = null;
            c -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            return vbVar;
        }
    }
}
