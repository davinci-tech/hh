package com.google.android.gms.internal.wearable;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* loaded from: classes2.dex */
public final class zzl {
    private final ByteBuffer zzhb;

    private zzl(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzm(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzn(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private zzl(ByteBuffer byteBuffer) {
        this.zzhb = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static zzl zzb(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static zzl zzb(byte[] bArr, int i, int i2) {
        return new zzl(bArr, 0, i2);
    }

    public final void zza(int i, float f) throws IOException {
        zzf(i, 5);
        int floatToIntBits = Float.floatToIntBits(f);
        if (this.zzhb.remaining() < 4) {
            throw new zzm(this.zzhb.position(), this.zzhb.limit());
        }
        this.zzhb.putInt(floatToIntBits);
    }

    public final void zza(int i, long j) throws IOException {
        zzf(i, 0);
        zza(j);
    }

    public final void zzd(int i, int i2) throws IOException {
        zzf(i, 0);
        if (i2 >= 0) {
            zzl(i2);
        } else {
            zza(i2);
        }
    }

    public final void zza(int i, String str) throws IOException {
        zzf(i, 2);
        try {
            int zzm = zzm(str.length());
            if (zzm == zzm(str.length() * 3)) {
                int position = this.zzhb.position();
                if (this.zzhb.remaining() < zzm) {
                    throw new zzm(position + zzm, this.zzhb.limit());
                }
                this.zzhb.position(position + zzm);
                zza(str, this.zzhb);
                int position2 = this.zzhb.position();
                this.zzhb.position(position);
                zzl((position2 - position) - zzm);
                this.zzhb.position(position2);
                return;
            }
            zzl(zza(str));
            zza(str, this.zzhb);
        } catch (BufferOverflowException e) {
            zzm zzmVar = new zzm(this.zzhb.position(), this.zzhb.limit());
            zzmVar.initCause(e);
            throw zzmVar;
        }
    }

    public final void zza(int i, zzt zztVar) throws IOException {
        zzf(i, 2);
        if (zztVar.zzhl < 0) {
            zztVar.zzx();
        }
        zzl(zztVar.zzhl);
        zztVar.zza(this);
    }

    private static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i2);
                                throw new IllegalArgumentException(sb.toString());
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(i3 + 4294967296L);
        throw new IllegalArgumentException(sb2.toString());
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        char charAt;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        char c = 57343;
        int i3 = 0;
        if (byteBuffer.hasArray()) {
            try {
                byte[] array = byteBuffer.array();
                int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                int remaining = byteBuffer.remaining();
                int length = charSequence.length();
                int i4 = remaining + arrayOffset;
                while (i3 < length) {
                    int i5 = i3 + arrayOffset;
                    if (i5 >= i4 || (charAt = charSequence.charAt(i3)) >= 128) {
                        break;
                    }
                    array[i5] = (byte) charAt;
                    i3++;
                }
                if (i3 == length) {
                    i = arrayOffset + length;
                } else {
                    i = arrayOffset + i3;
                    while (i3 < length) {
                        char charAt2 = charSequence.charAt(i3);
                        if (charAt2 >= 128 || i >= i4) {
                            if (charAt2 < 2048 && i <= i4 - 2) {
                                int i6 = i + 1;
                                array[i] = (byte) ((charAt2 >>> 6) | 960);
                                i += 2;
                                array[i6] = (byte) ((charAt2 & '?') | 128);
                            } else {
                                if ((charAt2 >= 55296 && c >= charAt2) || i > i4 - 3) {
                                    if (i <= i4 - 4) {
                                        int i7 = i3 + 1;
                                        if (i7 != charSequence.length()) {
                                            char charAt3 = charSequence.charAt(i7);
                                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                                array[i] = (byte) ((codePoint >>> 18) | GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
                                                array[i + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                                                int i8 = i + 3;
                                                array[i + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                                                i += 4;
                                                array[i8] = (byte) ((codePoint & 63) | 128);
                                                i3 = i7;
                                            } else {
                                                i3 = i7;
                                            }
                                        }
                                        StringBuilder sb = new StringBuilder(39);
                                        sb.append("Unpaired surrogate at index ");
                                        sb.append(i3 - 1);
                                        throw new IllegalArgumentException(sb.toString());
                                    }
                                    StringBuilder sb2 = new StringBuilder(37);
                                    sb2.append("Failed writing ");
                                    sb2.append(charAt2);
                                    sb2.append(" at index ");
                                    sb2.append(i);
                                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                                }
                                array[i] = (byte) ((charAt2 >>> '\f') | 480);
                                array[i + 1] = (byte) (((charAt2 >>> 6) & 63) | 128);
                                i2 = i + 3;
                                array[i + 2] = (byte) ((charAt2 & '?') | 128);
                            }
                            i3++;
                            c = 57343;
                        } else {
                            i2 = i + 1;
                            array[i] = (byte) charAt2;
                        }
                        i = i2;
                        i3++;
                        c = 57343;
                    }
                }
                byteBuffer.position(i - byteBuffer.arrayOffset());
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        }
        int length2 = charSequence.length();
        while (i3 < length2) {
            char charAt4 = charSequence.charAt(i3);
            if (charAt4 < 128) {
                byteBuffer.put((byte) charAt4);
            } else if (charAt4 < 2048) {
                byteBuffer.put((byte) ((charAt4 >>> 6) | 960));
                byteBuffer.put((byte) ((charAt4 & '?') | 128));
            } else {
                if (charAt4 < 55296 || 57343 < charAt4) {
                    byteBuffer.put((byte) ((charAt4 >>> '\f') | 480));
                    byteBuffer.put((byte) (((charAt4 >>> 6) & 63) | 128));
                    byteBuffer.put((byte) ((charAt4 & '?') | 128));
                } else {
                    int i9 = i3 + 1;
                    if (i9 != charSequence.length()) {
                        char charAt5 = charSequence.charAt(i9);
                        if (Character.isSurrogatePair(charAt4, charAt5)) {
                            int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                            byteBuffer.put((byte) ((codePoint2 >>> 18) | GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN));
                            byteBuffer.put((byte) (((codePoint2 >>> 12) & 63) | 128));
                            byteBuffer.put((byte) (((codePoint2 >>> 6) & 63) | 128));
                            byteBuffer.put((byte) ((codePoint2 & 63) | 128));
                            i3 = i9;
                        } else {
                            i3 = i9;
                        }
                    }
                    StringBuilder sb3 = new StringBuilder(39);
                    sb3.append("Unpaired surrogate at index ");
                    sb3.append(i3 - 1);
                    throw new IllegalArgumentException(sb3.toString());
                }
                i3++;
            }
            i3++;
        }
    }

    public static int zzb(int i, long j) {
        return zzk(i) + (((-128) & j) == 0 ? 1 : ((-16384) & j) == 0 ? 2 : ((-2097152) & j) == 0 ? 3 : ((-268435456) & j) == 0 ? 4 : ((-34359738368L) & j) == 0 ? 5 : ((-4398046511104L) & j) == 0 ? 6 : ((-562949953421312L) & j) == 0 ? 7 : ((-72057594037927936L) & j) == 0 ? 8 : (j & Long.MIN_VALUE) == 0 ? 9 : 10);
    }

    public static int zze(int i, int i2) {
        return zzk(i) + zzi(i2);
    }

    public static int zzb(int i, String str) {
        return zzk(i) + zzg(str);
    }

    public static int zzb(int i, zzt zztVar) {
        int zzk = zzk(i);
        int zzx = zztVar.zzx();
        return zzk + zzm(zzx) + zzx;
    }

    public static int zzi(int i) {
        if (i >= 0) {
            return zzm(i);
        }
        return 10;
    }

    public static int zzg(String str) {
        int zza = zza(str);
        return zzm(zza) + zza;
    }

    public final void zzr() {
        if (this.zzhb.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", Integer.valueOf(this.zzhb.remaining())));
        }
    }

    public final void zza(byte b) throws IOException {
        if (!this.zzhb.hasRemaining()) {
            throw new zzm(this.zzhb.position(), this.zzhb.limit());
        }
        this.zzhb.put(b);
    }

    private final void zzj(int i) throws IOException {
        byte b = (byte) i;
        if (!this.zzhb.hasRemaining()) {
            throw new zzm(this.zzhb.position(), this.zzhb.limit());
        }
        this.zzhb.put(b);
    }

    public final void zzc(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzhb.remaining() >= length) {
            this.zzhb.put(bArr, 0, length);
            return;
        }
        throw new zzm(this.zzhb.position(), this.zzhb.limit());
    }

    public final void zzf(int i, int i2) throws IOException {
        zzl((i << 3) | i2);
    }

    public static int zzk(int i) {
        return zzm(i << 3);
    }

    public final void zzl(int i) throws IOException {
        while ((i & (-128)) != 0) {
            zzj((i & 127) | 128);
            i >>>= 7;
        }
        zzj(i);
    }

    private final void zza(long j) throws IOException {
        while (((-128) & j) != 0) {
            zzj((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzj((int) j);
    }

    public final void zzb(long j) throws IOException {
        if (this.zzhb.remaining() < 8) {
            throw new zzm(this.zzhb.position(), this.zzhb.limit());
        }
        this.zzhb.putLong(j);
    }
}
