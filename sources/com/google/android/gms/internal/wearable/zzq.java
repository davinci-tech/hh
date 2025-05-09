package com.google.android.gms.internal.wearable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
final class zzq implements Cloneable {
    private Object value;
    private zzo<?, ?> zzhi;
    private List<zzv> zzhj = new ArrayList();

    zzq() {
    }

    final void zza(zzv zzvVar) throws IOException {
        List<zzv> list = this.zzhj;
        if (list != null) {
            list.add(zzvVar);
            return;
        }
        Object obj = this.value;
        if (obj instanceof zzt) {
            byte[] bArr = zzvVar.zzhm;
            zzk zza = zzk.zza(bArr, 0, bArr.length);
            int zzk = zza.zzk();
            if (zzk != bArr.length - zzl.zzi(zzk)) {
                throw zzs.zzu();
            }
            zzt zza2 = ((zzt) this.value).zza(zza);
            this.zzhi = this.zzhi;
            this.value = zza2;
            this.zzhj = null;
            return;
        }
        if (obj instanceof zzt[]) {
            Collections.singletonList(zzvVar);
            throw new NoSuchMethodError();
        }
        Collections.singletonList(zzvVar);
        throw new NoSuchMethodError();
    }

    final int zzg() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        int i = 0;
        for (zzv zzvVar : this.zzhj) {
            i += zzl.zzm(zzvVar.tag) + zzvVar.zzhm.length;
        }
        return i;
    }

    final void zza(zzl zzlVar) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (zzv zzvVar : this.zzhj) {
            zzlVar.zzl(zzvVar.tag);
            zzlVar.zzc(zzvVar.zzhm);
        }
    }

    public final boolean equals(Object obj) {
        List<zzv> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzq)) {
            return false;
        }
        zzq zzqVar = (zzq) obj;
        if (this.value != null && zzqVar.value != null) {
            zzo<?, ?> zzoVar = this.zzhi;
            if (zzoVar != zzqVar.zzhi) {
                return false;
            }
            if (!zzoVar.zzhd.isArray()) {
                return this.value.equals(zzqVar.value);
            }
            Object obj2 = this.value;
            if (obj2 instanceof byte[]) {
                return Arrays.equals((byte[]) obj2, (byte[]) zzqVar.value);
            }
            if (obj2 instanceof int[]) {
                return Arrays.equals((int[]) obj2, (int[]) zzqVar.value);
            }
            if (obj2 instanceof long[]) {
                return Arrays.equals((long[]) obj2, (long[]) zzqVar.value);
            }
            if (obj2 instanceof float[]) {
                return Arrays.equals((float[]) obj2, (float[]) zzqVar.value);
            }
            if (obj2 instanceof double[]) {
                return Arrays.equals((double[]) obj2, (double[]) zzqVar.value);
            }
            if (obj2 instanceof boolean[]) {
                return Arrays.equals((boolean[]) obj2, (boolean[]) zzqVar.value);
            }
            return Arrays.deepEquals((Object[]) obj2, (Object[]) zzqVar.value);
        }
        List<zzv> list2 = this.zzhj;
        if (list2 != null && (list = zzqVar.zzhj) != null) {
            return list2.equals(list);
        }
        try {
            return Arrays.equals(toByteArray(), zzqVar.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzg()];
        zza(zzl.zzb(bArr));
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzt, reason: merged with bridge method [inline-methods] */
    public final zzq clone() {
        zzq zzqVar = new zzq();
        try {
            zzqVar.zzhi = this.zzhi;
            List<zzv> list = this.zzhj;
            if (list == null) {
                zzqVar.zzhj = null;
            } else {
                zzqVar.zzhj.addAll(list);
            }
            Object obj = this.value;
            if (obj != null) {
                if (obj instanceof zzt) {
                    zzqVar.value = (zzt) ((zzt) obj).clone();
                } else if (obj instanceof byte[]) {
                    zzqVar.value = ((byte[]) obj).clone();
                } else {
                    int i = 0;
                    if (obj instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) obj;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzqVar.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (obj instanceof boolean[]) {
                        zzqVar.value = ((boolean[]) obj).clone();
                    } else if (obj instanceof int[]) {
                        zzqVar.value = ((int[]) obj).clone();
                    } else if (obj instanceof long[]) {
                        zzqVar.value = ((long[]) obj).clone();
                    } else if (obj instanceof float[]) {
                        zzqVar.value = ((float[]) obj).clone();
                    } else if (obj instanceof double[]) {
                        zzqVar.value = ((double[]) obj).clone();
                    } else if (obj instanceof zzt[]) {
                        zzt[] zztVarArr = (zzt[]) obj;
                        zzt[] zztVarArr2 = new zzt[zztVarArr.length];
                        zzqVar.value = zztVarArr2;
                        while (i < zztVarArr.length) {
                            zztVarArr2[i] = (zzt) zztVarArr[i].clone();
                            i++;
                        }
                    }
                }
            }
            return zzqVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
