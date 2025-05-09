package com.google.android.gms.fitness.data;

/* loaded from: classes2.dex */
final class zzg implements com.google.android.gms.internal.fitness.zzh<DataType> {
    public static final zzg zzan = new zzg();

    private zzg() {
    }

    @Override // com.google.android.gms.internal.fitness.zzh
    public final boolean zzb(String str) {
        return zzm.zzc(str) != null;
    }

    private static Field zza(DataType dataType, int i) {
        return dataType.getFields().get(i);
    }

    @Override // com.google.android.gms.internal.fitness.zzh
    public final /* synthetic */ int zzd(DataType dataType, int i) {
        return zza(dataType, i).getFormat();
    }

    @Override // com.google.android.gms.internal.fitness.zzh
    public final /* synthetic */ boolean zze(DataType dataType, int i) {
        return Boolean.TRUE.equals(zza(dataType, i).isOptional());
    }

    @Override // com.google.android.gms.internal.fitness.zzh
    public final /* synthetic */ String zzf(DataType dataType, int i) {
        return zza(dataType, i).getName();
    }

    @Override // com.google.android.gms.internal.fitness.zzh
    public final /* synthetic */ int zzc(DataType dataType) {
        return dataType.getFields().size();
    }

    @Override // com.google.android.gms.internal.fitness.zzh
    public final /* synthetic */ String zzd(DataType dataType) {
        return dataType.getName();
    }
}
