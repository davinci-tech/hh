package com.huawei.hms.scankit.p;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
final class o1 {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f5842a = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] b;
    private static final char[] c;
    private static final char[] d;
    private static final char[] e;

    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5843a;

        static {
            int[] iArr = new int[b.values().length];
            f5843a = iArr;
            try {
                iArr[b.C40_ENCODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5843a[b.TEXT_ENCODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5843a[b.ANSIX12_ENCODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5843a[b.EDIFACT_ENCODE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5843a[b.BASE256_ENCODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    enum b {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE,
        UPPER_ENCODE
    }

    static {
        char[] cArr = {'!', '\"', '#', '$', '%', '&', '\'', com.huawei.hms.network.embedded.g4.k, com.huawei.hms.network.embedded.g4.l, '*', '+', ',', '-', FilenameUtils.EXTENSION_SEPARATOR, '/', ':', ';', '<', '=', '>', '?', '@', '[', IOUtils.DIR_SEPARATOR_WINDOWS, ']', '^', '_'};
        b = cArr;
        c = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        d = cArr;
        e = new char[]{'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', 127};
    }

    static w1 a(byte[] bArr, Map<l1, ?> map) throws com.huawei.hms.scankit.p.a {
        w wVar = new w(bArr);
        StringBuilder sb = new StringBuilder(100);
        StringBuilder sb2 = new StringBuilder(0);
        ArrayList arrayList = new ArrayList(1);
        b bVar = b.ASCII_ENCODE;
        do {
            b bVar2 = b.ASCII_ENCODE;
            if (bVar == bVar2) {
                bVar = a(wVar, sb, sb2);
            } else {
                int i = a.f5843a[bVar.ordinal()];
                if (i == 1) {
                    b(wVar, sb);
                } else if (i == 2) {
                    d(wVar, sb);
                } else if (i == 3) {
                    a(wVar, sb);
                } else if (i == 4) {
                    c(wVar, sb);
                } else {
                    if (i != 5) {
                        throw com.huawei.hms.scankit.p.a.a("AIScanException");
                    }
                    a(wVar, sb, arrayList);
                }
                bVar = bVar2;
            }
            if (bVar == b.PAD_ENCODE) {
                break;
            }
        } while (wVar.a() > 0);
        if (sb2.length() > 0) {
            sb.append((CharSequence) sb2);
        }
        int length = sb.length();
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[i2] = (byte) sb.charAt(i2);
        }
        try {
            String str = new String(bArr2, c7.a(bArr2, map));
            if (arrayList.isEmpty()) {
                arrayList = null;
            }
            return new w1(bArr, str, arrayList, null);
        } catch (UnsupportedEncodingException unused) {
            throw com.huawei.hms.scankit.p.a.a();
        }
    }

    private static void c(w wVar, StringBuilder sb) {
        while (wVar.a() > 16) {
            for (int i = 0; i < 4; i++) {
                int a2 = wVar.a(6);
                if (a2 == 31) {
                    int b2 = 8 - wVar.b();
                    if (b2 != 8) {
                        wVar.a(b2);
                        return;
                    }
                    return;
                }
                if ((a2 & 32) == 0) {
                    a2 |= 64;
                }
                sb.append((char) a2);
            }
            if (wVar.a() <= 0) {
                return;
            }
        }
    }

    private static void d(w wVar, StringBuilder sb) throws com.huawei.hms.scankit.p.a {
        int a2;
        int[] iArr = new int[3];
        int i = 0;
        boolean z = false;
        while (wVar.a() != 8 && (a2 = wVar.a(8)) != 254) {
            a(a2, wVar.a(8), iArr);
            int i2 = 0;
            while (i2 < 3) {
                int[] a3 = a(sb, i, iArr[i2], z);
                int i3 = a3[0];
                boolean z2 = true;
                if (a3[1] != 1) {
                    z2 = false;
                }
                i2++;
                i = i3;
                z = z2;
            }
            if (wVar.a() <= 0) {
                return;
            }
        }
    }

    private static void b(w wVar, StringBuilder sb) throws com.huawei.hms.scankit.p.a {
        int a2;
        int[] iArr = new int[3];
        int i = 0;
        boolean z = false;
        while (wVar.a() != 8 && (a2 = wVar.a(8)) != 254) {
            a(a2, wVar.a(8), iArr);
            int i2 = 0;
            while (i2 < 3) {
                int[] b2 = b(sb, i, iArr[i2], z);
                int i3 = b2[0];
                boolean z2 = true;
                if (b2[1] != 1) {
                    z2 = false;
                }
                i2++;
                i = i3;
                z = z2;
            }
            if (wVar.a() <= 0) {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int[] b(StringBuilder sb, int i, int i2, boolean z) throws com.huawei.hms.scankit.p.a {
        int i3;
        boolean z2;
        if (i != 0) {
            if (i == 1) {
                if (z) {
                    i2 += 128;
                }
                sb.append((char) i2);
            } else if (i == 2) {
                char[] cArr = b;
                if (i2 < cArr.length) {
                    char c2 = cArr[i2];
                    if (z) {
                        c2 = (char) (c2 + 128);
                    }
                    sb.append(c2);
                    z2 = false;
                } else if (i2 == 27) {
                    sb.append((char) 29);
                    z2 = z;
                } else {
                    if (i2 != 30) {
                        throw com.huawei.hms.scankit.p.a.a("AIScanException");
                    }
                    z2 = true;
                }
                i = 0;
                i3 = z2;
            } else {
                if (i != 3) {
                    throw com.huawei.hms.scankit.p.a.a("AIScanException");
                }
                sb.append((char) (z ? i2 + 224 : i2 + 96));
            }
            i = 0;
            i3 = 0;
        } else if (i2 < 3) {
            i = i2 + 1;
            i3 = z;
        } else {
            char[] cArr2 = f5842a;
            if (i2 < cArr2.length) {
                char c3 = cArr2[i2];
                if (z) {
                    c3 = (char) (c3 + 128);
                }
                sb.append(c3);
                i3 = 0;
            } else {
                throw com.huawei.hms.scankit.p.a.a("AIScanException");
            }
        }
        return new int[]{i, i3};
    }

    private static b a(w wVar, StringBuilder sb, StringBuilder sb2) throws com.huawei.hms.scankit.p.a {
        boolean z = false;
        do {
            int a2 = wVar.a(8);
            if (a2 == 0) {
                throw com.huawei.hms.scankit.p.a.a("AIScanException");
            }
            if (a2 <= 128) {
                if (z) {
                    a2 += 128;
                }
                sb.append((char) (a2 - 1));
                return b.ASCII_ENCODE;
            }
            if (a2 == 129) {
                return b.PAD_ENCODE;
            }
            if (a2 <= 229) {
                int i = a2 - 130;
                if (i < 10) {
                    sb.append('0');
                }
                sb.append(i);
            } else {
                b a3 = a(a2, sb, sb2, wVar);
                if (a3 != null) {
                    if (a3 != b.UPPER_ENCODE) {
                        return a3;
                    }
                    z = true;
                }
            }
        } while (wVar.a() > 0);
        return b.ASCII_ENCODE;
    }

    private static b a(int i, StringBuilder sb, StringBuilder sb2, w wVar) throws com.huawei.hms.scankit.p.a {
        switch (i) {
            case 230:
                return b.C40_ENCODE;
            case 231:
                return b.BASE256_ENCODE;
            case 232:
                sb.append((char) 29);
                return null;
            case 233:
            case FitnessSleepType.HW_FITNESS_WAKE /* 234 */:
            case 241:
                return null;
            case 235:
                return b.UPPER_ENCODE;
            case FitnessSleepType.HW_FITNESS_DREAM /* 236 */:
                sb.append("[)>\u001e05\u001d");
                sb2.insert(0, "\u001e\u0004");
                return null;
            case FitnessSleepType.HW_FITNESS_NOON /* 237 */:
                sb.append("[)>\u001e06\u001d");
                sb2.insert(0, "\u001e\u0004");
                return null;
            case 238:
                return b.ANSIX12_ENCODE;
            case 239:
                return b.TEXT_ENCODE;
            case GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN /* 240 */:
                return b.EDIFACT_ENCODE;
            default:
                if (i == 254 && wVar.a() == 0) {
                    return null;
                }
                throw com.huawei.hms.scankit.p.a.a("AIScanException");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int[] a(StringBuilder sb, int i, int i2, boolean z) throws com.huawei.hms.scankit.p.a {
        int i3;
        boolean z2;
        if (i != 0) {
            if (i == 1) {
                if (z) {
                    i2 = (char) (i2 + 128);
                }
                sb.append(i2);
            } else if (i == 2) {
                char[] cArr = d;
                if (i2 < cArr.length) {
                    char c2 = cArr[i2];
                    if (z) {
                        c2 = (char) (c2 + 128);
                    }
                    sb.append(c2);
                    z2 = false;
                } else if (i2 == 27) {
                    sb.append((char) 29);
                    z2 = z;
                } else {
                    if (i2 != 30) {
                        throw com.huawei.hms.scankit.p.a.a("AIScanException");
                    }
                    z2 = true;
                }
                i = 0;
                i3 = z2;
            } else {
                if (i != 3) {
                    throw com.huawei.hms.scankit.p.a.a("AIScanException");
                }
                char[] cArr2 = e;
                if (i2 < cArr2.length) {
                    char c3 = cArr2[i2];
                    if (z) {
                        c3 = (char) (c3 + 128);
                    }
                    sb.append(c3);
                } else {
                    throw com.huawei.hms.scankit.p.a.a("AIScanException");
                }
            }
            i = 0;
            i3 = 0;
        } else if (i2 < 3) {
            i = i2 + 1;
            i3 = z;
        } else {
            char[] cArr3 = c;
            if (i2 < cArr3.length) {
                char c4 = cArr3[i2];
                if (z) {
                    c4 = (char) (c4 + 128);
                }
                sb.append(c4);
                i3 = 0;
            } else {
                throw com.huawei.hms.scankit.p.a.a("AIScanException");
            }
        }
        return new int[]{i, i3};
    }

    private static void a(w wVar, StringBuilder sb) throws com.huawei.hms.scankit.p.a {
        int a2;
        int[] iArr = new int[3];
        while (wVar.a() != 8 && (a2 = wVar.a(8)) != 254) {
            a(a2, wVar.a(8), iArr);
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                if (i2 == 0) {
                    sb.append('\r');
                } else if (i2 == 1) {
                    sb.append('*');
                } else if (i2 == 2) {
                    sb.append('>');
                } else if (i2 == 3) {
                    sb.append(' ');
                } else if (i2 < 14) {
                    sb.append((char) (i2 + 44));
                } else if (i2 < 40) {
                    sb.append((char) (i2 + 51));
                } else {
                    throw com.huawei.hms.scankit.p.a.a("AIScanException");
                }
            }
            if (wVar.a() <= 0) {
                return;
            }
        }
    }

    private static void a(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / KakaConstants.TASK_MEASURE_TODAY_BLOOD_PRESSURE;
        iArr[0] = i4;
        int i5 = i3 - (i4 * KakaConstants.TASK_MEASURE_TODAY_BLOOD_PRESSURE);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    private static void a(w wVar, StringBuilder sb, Collection<byte[]> collection) throws com.huawei.hms.scankit.p.a {
        int c2 = wVar.c();
        int i = c2 + 2;
        int a2 = a(wVar.a(8), c2 + 1);
        if (a2 == 0) {
            a2 = wVar.a() / 8;
        } else if (a2 >= 250) {
            a2 = ((a2 - 249) * 250) + a(wVar.a(8), i);
            i = c2 + 3;
        }
        if (a2 >= 0) {
            byte[] bArr = new byte[a2];
            int i2 = 0;
            while (i2 < a2) {
                if (wVar.a() >= 8) {
                    bArr[i2] = (byte) a(wVar.a(8), i);
                    i2++;
                    i++;
                } else {
                    throw com.huawei.hms.scankit.p.a.a("AIScanException");
                }
            }
            collection.add(bArr);
            try {
                sb.append(new String(bArr, "ISO8859_1"));
                return;
            } catch (UnsupportedEncodingException e2) {
                throw new IllegalStateException("Platform does not support required encoding: " + e2);
            }
        }
        throw com.huawei.hms.scankit.p.a.a("AIScanException");
    }

    private static int a(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }
}
