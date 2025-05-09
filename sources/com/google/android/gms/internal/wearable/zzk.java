package com.google.android.gms.internal.wearable;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.haf.router.AppRouterExtras;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class zzk {
    private final byte[] buffer;
    private final int zzgr;
    private final int zzgs;
    private int zzgt;
    private int zzgu;
    private int zzgv;
    private int zzgw;
    private int zzgy;
    private int zzgx = Integer.MAX_VALUE;
    private int zzgz = 64;
    private int zzha = AppRouterExtras.COLDSTART;

    public static zzk zza(byte[] bArr, int i, int i2) {
        return new zzk(bArr, 0, i2);
    }

    public final int zzj() throws IOException {
        if (this.zzgv == this.zzgt) {
            this.zzgw = 0;
            return 0;
        }
        int zzk = zzk();
        this.zzgw = zzk;
        if (zzk != 0) {
            return zzk;
        }
        throw new zzs("Protocol message contained an invalid tag (zero).");
    }

    public final void zzc(int i) throws zzs {
        if (this.zzgw != i) {
            throw new zzs("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzd(int i) throws IOException {
        int zzj;
        int i2 = i & 7;
        if (i2 == 0) {
            zzk();
            return true;
        }
        if (i2 == 1) {
            zzn();
            return true;
        }
        if (i2 == 2) {
            zzh(zzk());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                zzm();
                return true;
            }
            throw new zzs("Protocol message tag had invalid wire type.");
        }
        do {
            zzj = zzj();
            if (zzj == 0) {
                break;
            }
        } while (zzd(zzj));
        zzc(((i >>> 3) << 3) | 4);
        return true;
    }

    public final String readString() throws IOException {
        int zzk = zzk();
        if (zzk < 0) {
            throw zzs.zzv();
        }
        int i = this.zzgt;
        int i2 = this.zzgv;
        if (zzk > i - i2) {
            throw zzs.zzu();
        }
        String str = new String(this.buffer, i2, zzk, zzr.UTF_8);
        this.zzgv += zzk;
        return str;
    }

    public final void zza(zzt zztVar) throws IOException {
        int zzk = zzk();
        if (this.zzgy >= this.zzgz) {
            throw new zzs("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int zze = zze(zzk);
        this.zzgy++;
        zztVar.zza(this);
        zzc(0);
        this.zzgy--;
        zzf(zze);
    }

    public final byte[] readBytes() throws IOException {
        int zzk = zzk();
        if (zzk < 0) {
            throw zzs.zzv();
        }
        if (zzk == 0) {
            return zzw.zzhy;
        }
        int i = this.zzgt;
        int i2 = this.zzgv;
        if (zzk > i - i2) {
            throw zzs.zzu();
        }
        byte[] bArr = new byte[zzk];
        System.arraycopy(this.buffer, i2, bArr, 0, zzk);
        this.zzgv += zzk;
        return bArr;
    }

    public final int zzk() throws IOException {
        int i;
        byte zzq = zzq();
        if (zzq >= 0) {
            return zzq;
        }
        int i2 = zzq & Byte.MAX_VALUE;
        byte zzq2 = zzq();
        if (zzq2 >= 0) {
            i = zzq2 << 7;
        } else {
            i2 |= (zzq2 & Byte.MAX_VALUE) << 7;
            byte zzq3 = zzq();
            if (zzq3 >= 0) {
                i = zzq3 << BaseType.Vector;
            } else {
                i2 |= (zzq3 & Byte.MAX_VALUE) << 14;
                byte zzq4 = zzq();
                if (zzq4 < 0) {
                    byte zzq5 = zzq();
                    int i3 = i2 | ((zzq4 & Byte.MAX_VALUE) << 21) | (zzq5 << 28);
                    if (zzq5 >= 0) {
                        return i3;
                    }
                    for (int i4 = 0; i4 < 5; i4++) {
                        if (zzq() >= 0) {
                            return i3;
                        }
                    }
                    throw zzs.zzw();
                }
                i = zzq4 << 21;
            }
        }
        return i2 | i;
    }

    public final long zzl() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            j |= (r3 & Byte.MAX_VALUE) << i;
            if ((zzq() & 128) == 0) {
                return j;
            }
        }
        throw zzs.zzw();
    }

    public final int zzm() throws IOException {
        return (zzq() & 255) | ((zzq() & 255) << 8) | ((zzq() & 255) << 16) | ((zzq() & 255) << 24);
    }

    public final long zzn() throws IOException {
        return ((zzq() & 255) << 8) | (zzq() & 255) | ((zzq() & 255) << 16) | ((zzq() & 255) << 24) | ((zzq() & 255) << 32) | ((zzq() & 255) << 40) | ((zzq() & 255) << 48) | ((zzq() & 255) << 56);
    }

    private zzk(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzgr = i;
        int i3 = i2 + i;
        this.zzgt = i3;
        this.zzgs = i3;
        this.zzgv = i;
    }

    public final int zze(int i) throws zzs {
        if (i < 0) {
            throw zzs.zzv();
        }
        int i2 = i + this.zzgv;
        int i3 = this.zzgx;
        if (i2 > i3) {
            throw zzs.zzu();
        }
        this.zzgx = i2;
        zzo();
        return i3;
    }

    private final void zzo() {
        int i = this.zzgt + this.zzgu;
        this.zzgt = i;
        int i2 = this.zzgx;
        if (i > i2) {
            int i3 = i - i2;
            this.zzgu = i3;
            this.zzgt = i - i3;
            return;
        }
        this.zzgu = 0;
    }

    public final void zzf(int i) {
        this.zzgx = i;
        zzo();
    }

    public final int zzp() {
        int i = this.zzgx;
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i - this.zzgv;
    }

    public final int getPosition() {
        return this.zzgv - this.zzgr;
    }

    public final byte[] zzb(int i, int i2) {
        if (i2 == 0) {
            return zzw.zzhy;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzgr + i, bArr, 0, i2);
        return bArr;
    }

    public final void zzg(int i) {
        zzc(i, this.zzgw);
    }

    final void zzc(int i, int i2) {
        int i3 = this.zzgv;
        int i4 = this.zzgr;
        if (i > i3 - i4) {
            StringBuilder sb = new StringBuilder(50);
            sb.append("Position ");
            sb.append(i);
            sb.append(" is beyond current ");
            sb.append(i3 - i4);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i < 0) {
            StringBuilder sb2 = new StringBuilder(24);
            sb2.append("Bad position ");
            sb2.append(i);
            throw new IllegalArgumentException(sb2.toString());
        }
        this.zzgv = i4 + i;
        this.zzgw = i2;
    }

    private final byte zzq() throws IOException {
        int i = this.zzgv;
        if (i == this.zzgt) {
            throw zzs.zzu();
        }
        byte[] bArr = this.buffer;
        this.zzgv = i + 1;
        return bArr[i];
    }

    private final void zzh(int i) throws IOException {
        if (i < 0) {
            throw zzs.zzv();
        }
        int i2 = this.zzgv;
        int i3 = this.zzgx;
        int i4 = i2 + i;
        if (i4 > i3) {
            zzh(i3 - i2);
            throw zzs.zzu();
        }
        if (i <= this.zzgt - i2) {
            this.zzgv = i4;
            return;
        }
        throw zzs.zzu();
    }
}
