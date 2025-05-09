package com.google.android.gms.internal.wearable;

import java.io.IOException;

/* loaded from: classes2.dex */
public final class zzh extends zzn<zzh> {
    private static volatile zzh[] zzfz;
    public String name = "";
    public zzi zzga = null;

    public static zzh[] zzh() {
        if (zzfz == null) {
            synchronized (zzr.zzhk) {
                if (zzfz == null) {
                    zzfz = new zzh[0];
                }
            }
        }
        return zzfz;
    }

    public zzh() {
        this.zzhc = null;
        this.zzhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh zzhVar = (zzh) obj;
        String str = this.name;
        if (str == null) {
            if (zzhVar.name != null) {
                return false;
            }
        } else if (!str.equals(zzhVar.name)) {
            return false;
        }
        zzi zziVar = this.zzga;
        if (zziVar == null) {
            if (zzhVar.zzga != null) {
                return false;
            }
        } else if (!zziVar.equals(zzhVar.zzga)) {
            return false;
        }
        if (this.zzhc == null || this.zzhc.isEmpty()) {
            return zzhVar.zzhc == null || zzhVar.zzhc.isEmpty();
        }
        return this.zzhc.equals(zzhVar.zzhc);
    }

    public final int hashCode() {
        int hashCode = getClass().getName().hashCode();
        String str = this.name;
        int i = 0;
        int hashCode2 = str == null ? 0 : str.hashCode();
        zzi zziVar = this.zzga;
        int hashCode3 = zziVar == null ? 0 : zziVar.hashCode();
        if (this.zzhc != null && !this.zzhc.isEmpty()) {
            i = this.zzhc.hashCode();
        }
        return ((((((hashCode + 527) * 31) + hashCode2) * 31) + hashCode3) * 31) + i;
    }

    @Override // com.google.android.gms.internal.wearable.zzn, com.google.android.gms.internal.wearable.zzt
    public final void zza(zzl zzlVar) throws IOException {
        zzlVar.zza(1, this.name);
        zzi zziVar = this.zzga;
        if (zziVar != null) {
            zzlVar.zza(2, zziVar);
        }
        super.zza(zzlVar);
    }

    @Override // com.google.android.gms.internal.wearable.zzn, com.google.android.gms.internal.wearable.zzt
    protected final int zzg() {
        int zzg = super.zzg() + zzl.zzb(1, this.name);
        zzi zziVar = this.zzga;
        return zziVar != null ? zzg + zzl.zzb(2, zziVar) : zzg;
    }

    @Override // com.google.android.gms.internal.wearable.zzt
    public final /* synthetic */ zzt zza(zzk zzkVar) throws IOException {
        while (true) {
            int zzj = zzkVar.zzj();
            if (zzj == 0) {
                return this;
            }
            if (zzj == 10) {
                this.name = zzkVar.readString();
            } else if (zzj != 18) {
                if (!super.zza(zzkVar, zzj)) {
                    return this;
                }
            } else {
                if (this.zzga == null) {
                    this.zzga = new zzi();
                }
                zzkVar.zza(this.zzga);
            }
        }
    }
}
