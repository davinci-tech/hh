package com.google.android.gms.internal.wearable;

import com.google.android.gms.internal.wearable.zzn;
import java.io.IOException;

/* loaded from: classes2.dex */
public abstract class zzn<M extends zzn<M>> extends zzt {
    protected zzp zzhc;

    @Override // com.google.android.gms.internal.wearable.zzt
    protected int zzg() {
        if (this.zzhc == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzhc.size(); i2++) {
            i += this.zzhc.zzp(i2).zzg();
        }
        return i;
    }

    @Override // com.google.android.gms.internal.wearable.zzt
    public void zza(zzl zzlVar) throws IOException {
        if (this.zzhc == null) {
            return;
        }
        for (int i = 0; i < this.zzhc.size(); i++) {
            this.zzhc.zzp(i).zza(zzlVar);
        }
    }

    protected final boolean zza(zzk zzkVar, int i) throws IOException {
        zzq zzo;
        int position = zzkVar.getPosition();
        if (!zzkVar.zzd(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzv zzvVar = new zzv(i, zzkVar.zzb(position, zzkVar.getPosition() - position));
        zzp zzpVar = this.zzhc;
        if (zzpVar == null) {
            this.zzhc = new zzp();
            zzo = null;
        } else {
            zzo = zzpVar.zzo(i2);
        }
        if (zzo == null) {
            zzo = new zzq();
            this.zzhc.zza(i2, zzo);
        }
        zzo.zza(zzvVar);
        return true;
    }

    @Override // com.google.android.gms.internal.wearable.zzt
    /* renamed from: zzs */
    public final /* synthetic */ zzt clone() throws CloneNotSupportedException {
        return (zzn) clone();
    }

    @Override // com.google.android.gms.internal.wearable.zzt
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzn zznVar = (zzn) super.clone();
        zzr.zza(this, zznVar);
        return zznVar;
    }
}
