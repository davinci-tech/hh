package okio.internal;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import defpackage.uhy;
import java.util.Arrays;
import kotlin.Metadata;
import okio.Utf8;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0003\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "beginIndex", "", "endIndex", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class _Utf8Kt {
    public static /* synthetic */ String commonToUtf8String$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        return commonToUtf8String(bArr, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ef, code lost:
    
        if ((r12[r2] & 192) == 128) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0084, code lost:
    
        if ((r12[r2] & 192) == 128) goto L91;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String commonToUtf8String(byte[] r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 428
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._Utf8Kt.commonToUtf8String(byte[], int, int):java.lang.String");
    }

    public static final byte[] commonAsUtf8ToByteArray(String str) {
        int i;
        char charAt;
        uhy.e((Object) str, "");
        byte[] bArr = new byte[str.length() * 4];
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char charAt2 = str.charAt(i2);
            if (uhy.c(charAt2, 128) >= 0) {
                int length2 = str.length();
                int i3 = i2;
                while (i2 < length2) {
                    char charAt3 = str.charAt(i2);
                    if (uhy.c(charAt3, 128) < 0) {
                        int i4 = i3 + 1;
                        bArr[i3] = (byte) charAt3;
                        while (true) {
                            i2++;
                            i3 = i4;
                            if (i2 < length2 && uhy.c(str.charAt(i2), 128) < 0) {
                                i4 = i3 + 1;
                                bArr[i3] = (byte) str.charAt(i2);
                            }
                        }
                    } else {
                        if (uhy.c(charAt3, 2048) < 0) {
                            bArr[i3] = (byte) ((charAt3 >> 6) | 192);
                            i3 += 2;
                            bArr[i3 + 1] = (byte) ((charAt3 & '?') | 128);
                        } else if (55296 > charAt3 || charAt3 >= 57344) {
                            bArr[i3] = (byte) ((charAt3 >> '\f') | 224);
                            bArr[i3 + 1] = (byte) (((charAt3 >> 6) & 63) | 128);
                            i3 += 3;
                            bArr[i3 + 2] = (byte) ((charAt3 & '?') | 128);
                        } else if (uhy.c(charAt3, 56319) > 0 || length2 <= (i = i2 + 1) || 56320 > (charAt = str.charAt(i)) || charAt >= 57344) {
                            bArr[i3] = Utf8.REPLACEMENT_BYTE;
                            i3++;
                        } else {
                            int charAt4 = ((charAt3 << '\n') + str.charAt(i)) - 56613888;
                            bArr[i3] = (byte) ((charAt4 >> 18) | GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
                            bArr[i3 + 1] = (byte) (((charAt4 >> 12) & 63) | 128);
                            bArr[i3 + 2] = (byte) (((charAt4 >> 6) & 63) | 128);
                            i3 += 4;
                            bArr[i3 + 3] = (byte) ((charAt4 & 63) | 128);
                            i2 += 2;
                        }
                        i2++;
                    }
                }
                byte[] copyOf = Arrays.copyOf(bArr, i3);
                uhy.a(copyOf, "");
                return copyOf;
            }
            bArr[i2] = (byte) charAt2;
            i2++;
        }
        byte[] copyOf2 = Arrays.copyOf(bArr, str.length());
        uhy.a(copyOf2, "");
        return copyOf2;
    }
}
