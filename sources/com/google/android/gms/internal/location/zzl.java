package com.google.android.gms.internal.location;

import android.os.DeadObjectException;

/* loaded from: classes8.dex */
final class zzl implements zzbj<zzao> {
    private final /* synthetic */ zzk zzcc;

    @Override // com.google.android.gms.internal.location.zzbj
    public final /* synthetic */ zzao getService() throws DeadObjectException {
        return (zzao) this.zzcc.getService();
    }

    @Override // com.google.android.gms.internal.location.zzbj
    public final void checkConnected() {
        this.zzcc.checkConnected();
    }

    zzl(zzk zzkVar) {
        this.zzcc = zzkVar;
    }
}
