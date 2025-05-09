package com.google.android.gms.internal.wearable;

import java.io.IOException;

/* loaded from: classes2.dex */
public abstract class zzt {
    protected volatile int zzhl = -1;

    public abstract zzt zza(zzk zzkVar) throws IOException;

    public void zza(zzl zzlVar) throws IOException {
    }

    protected int zzg() {
        return 0;
    }

    public final int zzx() {
        int zzg = zzg();
        this.zzhl = zzg;
        return zzg;
    }

    public static final byte[] zzb(zzt zztVar) {
        int zzx = zztVar.zzx();
        byte[] bArr = new byte[zzx];
        try {
            zzl zzb = zzl.zzb(bArr, 0, zzx);
            zztVar.zza(zzb);
            zzb.zzr();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzt> T zza(T t, byte[] bArr, int i, int i2) throws zzs {
        try {
            zzk zza = zzk.zza(bArr, 0, i2);
            t.zza(zza);
            zza.zzc(0);
            return t;
        } catch (zzs e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public String toString() {
        return zzu.zzc(this);
    }

    @Override // 
    /* renamed from: zzs, reason: merged with bridge method [inline-methods] */
    public zzt clone() throws CloneNotSupportedException {
        return (zzt) super.clone();
    }
}
