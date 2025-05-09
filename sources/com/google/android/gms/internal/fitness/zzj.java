package com.google.android.gms.internal.fitness;

import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class zzj {
    public static <DP, DT> String zza(DP dp, zzg<DP, DT> zzgVar) {
        double zza;
        zzh<DT> zzb = zzgVar.zzb();
        if (!zzb.zzb(zzgVar.zzb(dp))) {
            return null;
        }
        DT zza2 = zzgVar.zza(dp);
        for (int i = 0; i < zzb.zzc(zza2); i++) {
            String zzf = zzb.zzf(zza2, i);
            if (zzgVar.zzc(dp, i)) {
                double zzd = zzb.zzd(zza2, i);
                if (zzd == 1.0d) {
                    zza = zzgVar.zzb(dp, i);
                } else if (zzd == 2.0d) {
                    zza = zzgVar.zza((zzg<DP, DT>) dp, i);
                } else {
                    continue;
                }
                zzm zzk = zzk.zzs().zzk(zzf);
                if (zzk != null && !zzk.zza(zza)) {
                    return "Field out of range";
                }
                zzm zza3 = zzk.zzs().zza(zzb.zzd(zza2), zzf);
                if (zza3 != null) {
                    long zza4 = zzgVar.zza((zzg<DP, DT>) dp, TimeUnit.NANOSECONDS);
                    if (zza4 == 0) {
                        if (zza == 0.0d) {
                            return null;
                        }
                        return "DataPoint out of range";
                    }
                    if (!zza3.zza(zza / zza4)) {
                        return "DataPoint out of range";
                    }
                } else {
                    continue;
                }
            } else if (!zzb.zze(zza2, i) && !zzk.zzep.contains(zzf)) {
                return String.valueOf(zzf).concat(" not set");
            }
        }
        return null;
    }
}
