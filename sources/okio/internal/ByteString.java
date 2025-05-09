package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.embedded.g4;
import defpackage.ueu;
import defpackage.uez;
import defpackage.uhy;
import defpackage.ujy;
import java.util.Arrays;
import kotlin.Metadata;
import okio.Base64;
import okio.Buffer;
import okio.SegmentedByteString;
import okio._JvmPlatformKt;

@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0080\b\u001a\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\r\u0010\u0011\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a\r\u0010\u0013\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a\u0015\u0010\u0014\u001a\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0080\b\u001a-\u0010\u0016\u001a\u00020\u0017*\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0080\b\u001a\u000f\u0010\u001c\u001a\u0004\u0018\u00010\f*\u00020\u0012H\u0080\b\u001a\r\u0010\u001d\u001a\u00020\f*\u00020\u0012H\u0080\b\u001a\r\u0010\u001e\u001a\u00020\f*\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020 *\u00020\f2\u0006\u0010!\u001a\u00020\tH\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020 *\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0080\b\u001a\u0017\u0010\"\u001a\u00020 *\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010#H\u0080\b\u001a\u0015\u0010$\u001a\u00020%*\u00020\f2\u0006\u0010&\u001a\u00020\u0007H\u0080\b\u001a\r\u0010'\u001a\u00020\u0007*\u00020\fH\u0080\b\u001a\r\u0010(\u001a\u00020\u0007*\u00020\fH\u0080\b\u001a\r\u0010)\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a\u001d\u0010*\u001a\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010+\u001a\u00020\u0007H\u0080\b\u001a\r\u0010,\u001a\u00020\t*\u00020\fH\u0080\b\u001a\u001d\u0010-\u001a\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010+\u001a\u00020\u0007H\u0080\b\u001a\u001d\u0010-\u001a\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010+\u001a\u00020\u0007H\u0080\b\u001a-\u0010.\u001a\u00020 *\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0080\b\u001a-\u0010.\u001a\u00020 *\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0080\b\u001a\u0015\u00100\u001a\u00020 *\u00020\f2\u0006\u00101\u001a\u00020\tH\u0080\b\u001a\u0015\u00100\u001a\u00020 *\u00020\f2\u0006\u00101\u001a\u00020\fH\u0080\b\u001a\u001d\u00102\u001a\u00020\f*\u00020\f2\u0006\u00103\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u0007H\u0080\b\u001a\r\u00105\u001a\u00020\f*\u00020\fH\u0080\b\u001a\r\u00106\u001a\u00020\f*\u00020\fH\u0080\b\u001a\r\u00107\u001a\u00020\t*\u00020\fH\u0080\b\u001a\u001d\u00108\u001a\u00020\f*\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0080\b\u001a\r\u00109\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a\r\u0010:\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a$\u0010;\u001a\u00020\u0017*\u00020\f2\u0006\u0010<\u001a\u00020=2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0000\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006>"}, d2 = {"HEX_DIGIT_CHARS", "", "getHEX_DIGIT_CHARS$annotations", "()V", "getHEX_DIGIT_CHARS", "()[C", "codePointIndexToCharIndex", "", "s", "", "codePointCount", "commonOf", "Lokio/ByteString;", "data", "decodeHexDigit", "c", "", "commonBase64", "", "commonBase64Url", "commonCompareTo", "other", "commonCopyInto", "", TypedValues.CycleType.S_WAVE_OFFSET, "target", "targetOffset", "byteCount", "commonDecodeBase64", "commonDecodeHex", "commonEncodeUtf8", "commonEndsWith", "", "suffix", "commonEquals", "", "commonGetByte", "", "pos", "commonGetSize", "commonHashCode", "commonHex", "commonIndexOf", "fromIndex", "commonInternalArray", "commonLastIndexOf", "commonRangeEquals", "otherOffset", "commonStartsWith", "prefix", "commonSubstring", "beginIndex", "endIndex", "commonToAsciiLowercase", "commonToAsciiUppercase", "commonToByteArray", "commonToByteString", "commonToString", "commonUtf8", "commonWrite", "buffer", "Lokio/Buffer;", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: okio.internal.-ByteString, reason: invalid class name */
/* loaded from: classes.dex */
public final class ByteString {
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static /* synthetic */ void getHEX_DIGIT_CHARS$annotations() {
    }

    public static final String commonUtf8(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        String utf8 = byteString.getUtf8();
        if (utf8 != null) {
            return utf8;
        }
        String utf8String = _JvmPlatformKt.toUtf8String(byteString.internalArray$okio());
        byteString.setUtf8$okio(utf8String);
        return utf8String;
    }

    public static final String commonBase64(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        return Base64.encodeBase64$default(byteString.getData(), null, 1, null);
    }

    public static final String commonBase64Url(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        return Base64.encodeBase64(byteString.getData(), Base64.getBASE64_URL_SAFE());
    }

    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }

    public static final String commonHex(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        char[] cArr = new char[byteString.getData().length * 2];
        int i = 0;
        for (byte b : byteString.getData()) {
            int i2 = i + 1;
            cArr[i] = getHEX_DIGIT_CHARS()[(b >> 4) & 15];
            i += 2;
            cArr[i2] = getHEX_DIGIT_CHARS()[b & BaseType.Obj];
        }
        return ujy.d(cArr);
    }

    public static final okio.ByteString commonToAsciiLowercase(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        for (int i = 0; i < byteString.getData().length; i++) {
            byte b = byteString.getData()[i];
            if (b >= 65 && b <= 90) {
                byte[] data = byteString.getData();
                byte[] copyOf = Arrays.copyOf(data, data.length);
                uhy.a(copyOf, "");
                copyOf[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b2 = copyOf[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        copyOf[i2] = (byte) (b2 + 32);
                    }
                }
                return new okio.ByteString(copyOf);
            }
        }
        return byteString;
    }

    public static final okio.ByteString commonToAsciiUppercase(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        for (int i = 0; i < byteString.getData().length; i++) {
            byte b = byteString.getData()[i];
            if (b >= 97 && b <= 122) {
                byte[] data = byteString.getData();
                byte[] copyOf = Arrays.copyOf(data, data.length);
                uhy.a(copyOf, "");
                copyOf[i] = (byte) (b - 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b2 = copyOf[i2];
                    if (b2 >= 97 && b2 <= 122) {
                        copyOf[i2] = (byte) (b2 - 32);
                    }
                }
                return new okio.ByteString(copyOf);
            }
        }
        return byteString;
    }

    public static final okio.ByteString commonSubstring(okio.ByteString byteString, int i, int i2) {
        uhy.e((Object) byteString, "");
        int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(byteString, i2);
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        }
        if (resolveDefaultParameter <= byteString.getData().length) {
            if (resolveDefaultParameter - i >= 0) {
                return (i == 0 && resolveDefaultParameter == byteString.getData().length) ? byteString : new okio.ByteString(uez.a(byteString.getData(), i, resolveDefaultParameter));
            }
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        throw new IllegalArgumentException(("endIndex > length(" + byteString.getData().length + g4.l).toString());
    }

    public static final byte commonGetByte(okio.ByteString byteString, int i) {
        uhy.e((Object) byteString, "");
        return byteString.getData()[i];
    }

    public static final int commonGetSize(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        return byteString.getData().length;
    }

    public static final byte[] commonToByteArray(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        byte[] data = byteString.getData();
        byte[] copyOf = Arrays.copyOf(data, data.length);
        uhy.a(copyOf, "");
        return copyOf;
    }

    public static final byte[] commonInternalArray(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        return byteString.getData();
    }

    public static final boolean commonRangeEquals(okio.ByteString byteString, int i, okio.ByteString byteString2, int i2, int i3) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) byteString2, "");
        return byteString2.rangeEquals(i2, byteString.getData(), i, i3);
    }

    public static final boolean commonRangeEquals(okio.ByteString byteString, int i, byte[] bArr, int i2, int i3) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) bArr, "");
        return i >= 0 && i <= byteString.getData().length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && SegmentedByteString.arrayRangeEquals(byteString.getData(), i, bArr, i2, i3);
    }

    public static final void commonCopyInto(okio.ByteString byteString, int i, byte[] bArr, int i2, int i3) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) bArr, "");
        uez.d(byteString.getData(), bArr, i2, i, i3 + i);
    }

    public static final boolean commonStartsWith(okio.ByteString byteString, okio.ByteString byteString2) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) byteString2, "");
        return byteString.rangeEquals(0, byteString2, 0, byteString2.size());
    }

    public static final boolean commonStartsWith(okio.ByteString byteString, byte[] bArr) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) bArr, "");
        return byteString.rangeEquals(0, bArr, 0, bArr.length);
    }

    public static final boolean commonEndsWith(okio.ByteString byteString, okio.ByteString byteString2) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) byteString2, "");
        return byteString.rangeEquals(byteString.size() - byteString2.size(), byteString2, 0, byteString2.size());
    }

    public static final boolean commonEndsWith(okio.ByteString byteString, byte[] bArr) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) bArr, "");
        return byteString.rangeEquals(byteString.size() - bArr.length, bArr, 0, bArr.length);
    }

    public static final int commonIndexOf(okio.ByteString byteString, byte[] bArr, int i) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) bArr, "");
        int length = byteString.getData().length - bArr.length;
        int max = Math.max(i, 0);
        if (max > length) {
            return -1;
        }
        while (!SegmentedByteString.arrayRangeEquals(byteString.getData(), max, bArr, 0, bArr.length)) {
            if (max == length) {
                return -1;
            }
            max++;
        }
        return max;
    }

    public static final int commonLastIndexOf(okio.ByteString byteString, okio.ByteString byteString2, int i) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) byteString2, "");
        return byteString.lastIndexOf(byteString2.internalArray$okio(), i);
    }

    public static final int commonLastIndexOf(okio.ByteString byteString, byte[] bArr, int i) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) bArr, "");
        for (int min = Math.min(SegmentedByteString.resolveDefaultParameter(byteString, i), byteString.getData().length - bArr.length); -1 < min; min--) {
            if (SegmentedByteString.arrayRangeEquals(byteString.getData(), min, bArr, 0, bArr.length)) {
                return min;
            }
        }
        return -1;
    }

    public static final boolean commonEquals(okio.ByteString byteString, Object obj) {
        uhy.e((Object) byteString, "");
        if (obj != byteString) {
            if (!(obj instanceof okio.ByteString)) {
                return false;
            }
            okio.ByteString byteString2 = (okio.ByteString) obj;
            if (byteString2.size() != byteString.getData().length || !byteString2.rangeEquals(0, byteString.getData(), 0, byteString.getData().length)) {
                return false;
            }
        }
        return true;
    }

    public static final int commonHashCode(okio.ByteString byteString) {
        uhy.e((Object) byteString, "");
        int hashCode = byteString.getHashCode();
        if (hashCode != 0) {
            return hashCode;
        }
        int hashCode2 = Arrays.hashCode(byteString.getData());
        byteString.setHashCode$okio(hashCode2);
        return hashCode2;
    }

    public static final int commonCompareTo(okio.ByteString byteString, okio.ByteString byteString2) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) byteString2, "");
        int size = byteString.size();
        int size2 = byteString2.size();
        int min = Math.min(size, size2);
        for (int i = 0; i < min; i++) {
            int i2 = byteString.getByte(i) & 255;
            int i3 = byteString2.getByte(i) & 255;
            if (i2 != i3) {
                return i2 < i3 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public static final okio.ByteString commonOf(byte[] bArr) {
        uhy.e((Object) bArr, "");
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        uhy.a(copyOf, "");
        return new okio.ByteString(copyOf);
    }

    public static final okio.ByteString commonToByteString(byte[] bArr, int i, int i2) {
        uhy.e((Object) bArr, "");
        int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(bArr, i2);
        SegmentedByteString.checkOffsetAndCount(bArr.length, i, resolveDefaultParameter);
        return new okio.ByteString(uez.a(bArr, i, resolveDefaultParameter + i));
    }

    public static final okio.ByteString commonEncodeUtf8(String str) {
        uhy.e((Object) str, "");
        okio.ByteString byteString = new okio.ByteString(_JvmPlatformKt.asUtf8ToByteArray(str));
        byteString.setUtf8$okio(str);
        return byteString;
    }

    public static final okio.ByteString commonDecodeBase64(String str) {
        uhy.e((Object) str, "");
        byte[] decodeBase64ToArray = Base64.decodeBase64ToArray(str);
        if (decodeBase64ToArray != null) {
            return new okio.ByteString(decodeBase64ToArray);
        }
        return null;
    }

    public static final okio.ByteString commonDecodeHex(String str) {
        uhy.e((Object) str, "");
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException(("Unexpected hex string: " + str).toString());
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((decodeHexDigit(str.charAt(i2)) << 4) + decodeHexDigit(str.charAt(i2 + 1)));
        }
        return new okio.ByteString(bArr);
    }

    public static final void commonWrite(okio.ByteString byteString, Buffer buffer, int i, int i2) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) buffer, "");
        buffer.write(byteString.getData(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int decodeHexDigit(char c) {
        if ('0' <= c && c < ':') {
            return c - '0';
        }
        char c2 = 'a';
        if ('a' > c || c >= 'g') {
            c2 = 'A';
            if ('A' > c || c >= 'G') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c);
            }
        }
        return (c - c2) + 10;
    }

    public static final String commonToString(okio.ByteString byteString) {
        okio.ByteString byteString2 = byteString;
        uhy.e((Object) byteString2, "");
        if (byteString.getData().length == 0) {
            return "[size=0]";
        }
        int codePointIndexToCharIndex = codePointIndexToCharIndex(byteString.getData(), 64);
        if (codePointIndexToCharIndex == -1) {
            if (byteString.getData().length <= 64) {
                return "[hex=" + byteString.hex() + ']';
            }
            StringBuilder sb = new StringBuilder("[size=");
            sb.append(byteString.getData().length);
            sb.append(" hex=");
            int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(byteString2, 64);
            if (resolveDefaultParameter > byteString.getData().length) {
                throw new IllegalArgumentException(("endIndex > length(" + byteString.getData().length + g4.l).toString());
            }
            if (resolveDefaultParameter < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex".toString());
            }
            if (resolveDefaultParameter != byteString.getData().length) {
                byteString2 = new okio.ByteString(uez.a(byteString.getData(), 0, resolveDefaultParameter));
            }
            sb.append(byteString2.hex());
            sb.append("…]");
            return sb.toString();
        }
        String utf8 = byteString.utf8();
        String substring = utf8.substring(0, codePointIndexToCharIndex);
        uhy.a(substring, "");
        String c = ujy.c(ujy.c(ujy.c(substring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
        if (codePointIndexToCharIndex < utf8.length()) {
            return "[size=" + byteString.getData().length + " text=" + c + "…]";
        }
        return "[text=" + c + ']';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int codePointIndexToCharIndex(byte[] bArr, int i) {
        byte b;
        int i2;
        int length = bArr.length;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        loop0: while (i3 < length) {
            byte b2 = bArr[i3];
            if (b2 >= 0) {
                int i6 = i5 + 1;
                if (i5 == i) {
                    return i4;
                }
                if ((b2 != 10 && b2 != 13 && ((b2 >= 0 && b2 < 32) || (Byte.MAX_VALUE <= b2 && b2 < 160))) || b2 == 65533) {
                    return -1;
                }
                i4 += b2 < 65536 ? 1 : 2;
                i3++;
                while (true) {
                    i5 = i6;
                    if (i3 < length && (b = bArr[i3]) >= 0) {
                        i3++;
                        i6 = i5 + 1;
                        if (i5 == i) {
                            return i4;
                        }
                        if ((b == 10 || b == 13 || ((b < 0 || b >= 32) && (Byte.MAX_VALUE > b || b >= 160))) && b != 65533) {
                            i4 += b < 65536 ? 1 : 2;
                        }
                    }
                }
            } else if ((b2 >> 5) == -2) {
                int i7 = i3 + 1;
                if (length <= i7) {
                    if (i5 == i) {
                        return i4;
                    }
                    return -1;
                }
                byte b3 = bArr[i7];
                if ((b3 & 192) != 128) {
                    if (i5 == i) {
                        return i4;
                    }
                    return -1;
                }
                int i8 = (b2 << 6) ^ (b3 ^ 3968);
                if (i8 < 128) {
                    if (i5 == i) {
                        return i4;
                    }
                    return -1;
                }
                int i9 = i5 + 1;
                if (i5 == i) {
                    return i4;
                }
                if ((i8 != 10 && i8 != 13 && ((i8 >= 0 && i8 < 32) || (127 <= i8 && i8 < 160))) || i8 == 65533) {
                    return -1;
                }
                i4 += i8 < 65536 ? 1 : 2;
                ueu ueuVar = ueu.d;
                i3 += 2;
                i5 = i9;
            } else {
                if ((b2 >> 4) == -2) {
                    int i10 = i3 + 2;
                    if (length <= i10) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b4 = bArr[i3 + 1];
                    if ((b4 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b5 = bArr[i10];
                    if ((b5 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i11 = (b2 << BaseType.Double) ^ ((b5 ^ (-123008)) ^ (b4 << 6));
                    if (i11 < 2048) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    if (55296 <= i11 && i11 < 57344) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    i2 = i5 + 1;
                    if (i5 == i) {
                        return i4;
                    }
                    if ((i11 != 10 && i11 != 13 && ((i11 >= 0 && i11 < 32) || (127 <= i11 && i11 < 160))) || i11 == 65533) {
                        return -1;
                    }
                    i4 += i11 < 65536 ? 1 : 2;
                    ueu ueuVar2 = ueu.d;
                    i3 += 3;
                } else {
                    if ((b2 >> 3) != -2) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i12 = i3 + 3;
                    if (length <= i12) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b6 = bArr[i3 + 1];
                    if ((b6 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b7 = bArr[i3 + 2];
                    if ((b7 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b8 = bArr[i12];
                    if ((b8 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i13 = (b2 << BaseType.Vector64) ^ (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << BaseType.Double));
                    if (i13 > 1114111) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    if (55296 <= i13 && i13 < 57344) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    if (i13 < 65536) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    i2 = i5 + 1;
                    if (i5 == i) {
                        return i4;
                    }
                    if ((i13 != 10 && i13 != 13 && ((i13 >= 0 && i13 < 32) || (127 <= i13 && i13 < 160))) || i13 == 65533) {
                        return -1;
                    }
                    i4 += i13 < 65536 ? 1 : 2;
                    ueu ueuVar3 = ueu.d;
                    i3 += 4;
                }
                i5 = i2;
            }
        }
        return i4;
    }
}
