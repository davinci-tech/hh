package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes8.dex */
public final class zzbm {
    public static Looper zzc() {
        Preconditions.checkState(Looper.myLooper() != null, "Can't create handler inside thread that has not called Looper.prepare()");
        return Looper.myLooper();
    }

    public static Looper zza(Looper looper) {
        return looper != null ? looper : zzc();
    }
}
