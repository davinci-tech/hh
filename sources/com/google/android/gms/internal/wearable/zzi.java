package com.google.android.gms.internal.wearable;

import java.io.IOException;

/* loaded from: classes2.dex */
public final class zzi extends zzn<zzi> {
    private static volatile zzi[] zzgb;
    public int type = 1;
    public zzj zzgc = null;

    public static zzi[] zzi() {
        if (zzgb == null) {
            synchronized (zzr.zzhk) {
                if (zzgb == null) {
                    zzgb = new zzi[0];
                }
            }
        }
        return zzgb;
    }

    public zzi() {
        this.zzhc = null;
        this.zzhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zziVar = (zzi) obj;
        if (this.type != zziVar.type) {
            return false;
        }
        zzj zzjVar = this.zzgc;
        if (zzjVar == null) {
            if (zziVar.zzgc != null) {
                return false;
            }
        } else if (!zzjVar.equals(zziVar.zzgc)) {
            return false;
        }
        if (this.zzhc == null || this.zzhc.isEmpty()) {
            return zziVar.zzhc == null || zziVar.zzhc.isEmpty();
        }
        return this.zzhc.equals(zziVar.zzhc);
    }

    public final int hashCode() {
        int hashCode = getClass().getName().hashCode();
        int i = this.type;
        zzj zzjVar = this.zzgc;
        int i2 = 0;
        int hashCode2 = zzjVar == null ? 0 : zzjVar.hashCode();
        if (this.zzhc != null && !this.zzhc.isEmpty()) {
            i2 = this.zzhc.hashCode();
        }
        return ((((((hashCode + 527) * 31) + i) * 31) + hashCode2) * 31) + i2;
    }

    @Override // com.google.android.gms.internal.wearable.zzn, com.google.android.gms.internal.wearable.zzt
    public final void zza(zzl zzlVar) throws IOException {
        zzlVar.zzd(1, this.type);
        zzj zzjVar = this.zzgc;
        if (zzjVar != null) {
            zzlVar.zza(2, zzjVar);
        }
        super.zza(zzlVar);
    }

    @Override // com.google.android.gms.internal.wearable.zzn, com.google.android.gms.internal.wearable.zzt
    protected final int zzg() {
        int zzg = super.zzg() + zzl.zze(1, this.type);
        zzj zzjVar = this.zzgc;
        return zzjVar != null ? zzg + zzl.zzb(2, zzjVar) : zzg;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.wearable.zzt
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzi zza(zzk zzkVar) throws IOException {
        while (true) {
            int zzj = zzkVar.zzj();
            if (zzj == 0) {
                return this;
            }
            if (zzj == 8) {
                int position = zzkVar.getPosition();
                try {
                    int zzk = zzkVar.zzk();
                    if (zzk <= 0 || zzk > 15) {
                        StringBuilder sb = new StringBuilder(36);
                        sb.append(zzk);
                        sb.append(" is not a valid enum Type");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.type = zzk;
                } catch (IllegalArgumentException unused) {
                    zzkVar.zzg(position);
                    zza(zzkVar, zzj);
                }
            } else if (zzj != 18) {
                if (!super.zza(zzkVar, zzj)) {
                    return this;
                }
            } else {
                if (this.zzgc == null) {
                    this.zzgc = new zzj();
                }
                zzkVar.zza(this.zzgc);
            }
        }
    }
}
