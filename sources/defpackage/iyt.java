package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class iyt {

    /* renamed from: a, reason: collision with root package name */
    private static final HashMap<String, String> f13674a;
    private static final HashMap<String, String> b;
    private static final String[][] c = {new String[]{"1_2_44", "1_4_1"}, new String[]{"1_2_45", "1_4_2"}, new String[]{"1_2_46", "1_4_3"}, new String[]{"1_2_4", "1_5_1"}, new String[]{"1_2_5", "1_5_2"}, new String[]{"1_2_35", "1_13_1"}, new String[]{"1_1_4", "1_6_1"}, new String[]{"1_1_5", "1_6_2"}, new String[]{"5_1_1", "1_7_1"}, new String[]{"5_1_2", "1_7_2"}, new String[]{"5_1_3", "1_7_3"}, new String[]{"5_1_4", "1_7_4"}, new String[]{"5_1_5", "1_7_5"}, new String[]{"5_1_6", "1_7_6"}, new String[]{"5_1_7", "1_7_7"}, new String[]{"5_1_9", "1_7_8"}, new String[]{"5_1_13", "1_7_9"}, new String[]{"5_1_14", "1_7_10"}, new String[]{"5_1_15", "1_7_11"}, new String[]{"1_1_36", "1_8_1"}, new String[]{"1_2_15", "1_9_1"}, new String[]{"1_3_53", "1_10_1"}, new String[]{"1_3_54", "1_10_2"}, new String[]{"1_3_55", "1_10_3"}, new String[]{"1_3_56", "1_10_4"}, new String[]{"1_3_57", "1_10_5"}, new String[]{"1_3_58", "1_10_6"}, new String[]{"1_3_59", "1_10_7"}, new String[]{"1_4_53", "1_11_1"}, new String[]{"1_4_54", "1_11_2"}, new String[]{"1_2_53", "1_12_1"}, new String[]{"1_2_54", "1_12_2"}, new String[]{"1_2_55", "1_12_3"}, new String[]{"1_2_56", "1_12_4"}, new String[]{"1_2_57", "1_12_5"}, new String[]{"1_2_58", "1_12_6"}, new String[]{"1_2_59", "1_12_7"}, new String[]{"4_1_40", "1_20_1"}, new String[]{"5_0_16", "1_16_1"}, new String[]{"5_0_17", "1_16_2"}, new String[]{"5_0_18", "1_16_3"}, new String[]{"5_0_19", "1_16_4"}, new String[]{"5_0_20", "1_16_5"}, new String[]{"5_0_21", "1_16_6"}, new String[]{"5_0_22", "1_16_7"}, new String[]{"5_0_23", "1_16_8"}, new String[]{"5_0_24", "1_16_9"}, new String[]{"5_0_28", "1_16_10"}, new String[]{"6_2_5", "1_14_1"}, new String[]{"6_2_6", "1_14_2"}, new String[]{"6_2_7", "1_14_3"}, new String[]{"6_2_8", "1_14_4"}, new String[]{"6_2_12", "1_14_5"}, new String[]{"6_2_13", "1_14_6"}, new String[]{"6_1_9", "1_15_1"}, new String[]{"6_1_10", "1_15_2"}, new String[]{"6_1_12", "1_15_3"}, new String[]{"6_1_14", "1_15_4"}, new String[]{"6_1_15", "1_15_5"}, new String[]{"6_1_13", "1_15_6"}, new String[]{"6_1_17", "1_15_7"}, new String[]{"8_2_26", "2_1_1"}, new String[]{"8_2_1", "2_1_2"}, new String[]{"8_2_2", "2_1_3"}, new String[]{"8_2_3", "2_1_4"}, new String[]{"8_2_27", "2_1_5"}, new String[]{"8_2_5", "2_1_6"}, new String[]{"8_2_28", "2_1_7"}, new String[]{"8_2_6", "2_1_8"}, new String[]{"8_2_7", "2_1_9"}, new String[]{"8_2_8", "2_1_10"}, new String[]{"8_2_9", "2_1_11"}, new String[]{"8_2_29", "2_1_12"}, new String[]{"8_2_10", "2_1_13"}, new String[]{"8_2_30", "2_1_14"}, new String[]{"8_2_11", "2_1_15"}, new String[]{"8_2_12", "2_1_16"}, new String[]{"8_1_38", "2_2_1"}, new String[]{"8_1_24", "2_2_2"}, new String[]{"8_1_1", "2_2_3"}, new String[]{"8_1_36", "2_2_4"}, new String[]{"8_1_52", "2_2_5"}, new String[]{"8_1_53", "2_2_6"}, new String[]{"8_1_31", "2_2_7"}, new String[]{"8_1_32", "2_2_8"}, new String[]{"8_1_33", "2_2_9"}, new String[]{"8_1_34", "2_2_10"}, new String[]{"8_1_35", "2_2_11"}, new String[]{"8_1_6", "2_2_12"}, new String[]{"8_1_7", "2_2_13"}, new String[]{"8_1_8", "2_2_14"}, new String[]{"8_1_20", "2_2_15"}, new String[]{"8_1_54", "2_2_16"}, new String[]{"8_1_23", "2_2_17"}, new String[]{"8_1_30", "2_2_18"}, new String[]{"8_1_25", "2_2_19"}, new String[]{"8_1_21", "2_2_20"}, new String[]{"8_2_47", "2_4_1"}, new String[]{"8_2_48", "2_4_2"}, new String[]{"8_2_49", "2_4_3"}, new String[]{"8_1_42", "15_2_1"}, new String[]{"8_2_39", "15_1_1"}, new String[]{"8_2_40", "15_1_2"}, new String[]{"8_2_41", "15_1_3"}, new String[]{"8_2_43", "15_1_4"}, new String[]{"8_2_44", "15_1_5"}, new String[]{"8_2_45", "15_1_6"}, new String[]{"8_2_46", "15_1_7"}, new String[]{"7_2_1", "7_1_1"}, new String[]{"7_2_2", "7_1_2"}, new String[]{"7_2_3", "7_1_3"}, new String[]{"7_2_15", "7_1_4"}, new String[]{"7_2_4", "7_1_5"}, new String[]{"7_2_5", "7_1_6"}, new String[]{"7_2_6", "7_1_7"}, new String[]{"7_2_39", "7_1_8"}, new String[]{"7_2_7", "7_2_1"}, new String[]{"7_2_8", "7_2_2"}, new String[]{"7_2_9", "7_2_3"}, new String[]{"7_2_99", "7_2_4"}, new String[]{"7_2_10", "7_2_5"}, new String[]{"7_2_11", "7_2_6"}, new String[]{"7_2_12", "7_2_7"}, new String[]{"17_2_7", "7_9_1"}, new String[]{"17_2_8", "7_9_2"}, new String[]{"17_2_9", "7_9_3"}, new String[]{"17_2_99", "7_9_4"}, new String[]{"17_2_10", "7_9_5"}, new String[]{"17_2_11", "7_9_6"}, new String[]{"17_2_12", "7_9_7"}, new String[]{"7_1_13", "7_3_1"}, new String[]{"7_1_57", "7_3_2"}, new String[]{"7_1_14", "7_3_3"}, new String[]{"7_1_15_13", "7_3_4"}, new String[]{"7_1_16_13", "7_3_5"}, new String[]{"7_1_17_13", "7_3_6"}, new String[]{"7_1_18_13", "7_3_7"}, new String[]{"7_1_46", "7_3_8"}, new String[]{"7_1_50", "7_4_1"}, new String[]{"7_1_19", "7_4_2"}, new String[]{"7_1_51", "7_4_3"}, new String[]{"7_1_52", "7_4_4"}, new String[]{"7_1_48_50", "7_4_5"}, new String[]{"7_1_22", "7_8_1"}, new String[]{"7_1_20", "7_8_2"}, new String[]{"7_1_21", "7_8_3"}, new String[]{"7_1_23", "7_8_4"}, new String[]{"7_1_64", "7_8_5"}, new String[]{"7_1_25", "7_8_6"}, new String[]{"7_1_24", "7_8_7"}, new String[]{"7_1_15_22", "7_8_8"}, new String[]{"7_1_16_22", "7_8_9"}, new String[]{"7_1_17_22", "7_8_10"}, new String[]{"7_1_18_22", "7_8_11"}, new String[]{"7_1_30", "7_8_12"}, new String[]{"7_1_47", "7_5_1"}, new String[]{"7_1_48_47", "7_5_2"}, new String[]{"7_1_49", "7_5_3"}, new String[]{"7_2_33", "7_7_1"}, new String[]{"7_2_34", "7_7_2"}, new String[]{"7_2_35", "7_7_3"}, new String[]{"7_2_36", "7_7_4"}, new String[]{"7_2_37", "7_7_5"}, new String[]{"7_2_38", "7_7_6"}, new String[]{"1_2_6", "8_1_1"}, new String[]{"1_2_7", "8_1_2"}, new String[]{"1_2_8_6", "8_1_3"}, new String[]{"1_2_9_6", "8_1_4"}, new String[]{"1_2_10_6", "8_1_5"}, new String[]{"1_2_11_6", "8_1_6"}, new String[]{"1_2_42", "8_1_7"}, new String[]{"1_2_43", "8_2_1"}, new String[]{"1_2_40", "8_2_2"}, new String[]{"1_2_8_43", "8_2_3"}, new String[]{"1_2_9_43", "8_2_4"}, new String[]{"1_2_10_43", "8_2_5"}, new String[]{"1_2_11_43", "8_2_6"}, new String[]{"1_2_41", "8_2_7"}, new String[]{"12_2_14", "12_1_1"}, new String[]{"6_2_1", "11_2_1"}, new String[]{"6_0_4", "11_1_1"}, new String[]{"16_2_26", "2_3_1"}, new String[]{"16_2_1", "2_3_2"}, new String[]{"16_2_2", "2_3_3"}, new String[]{"16_2_3", "2_3_4"}, new String[]{"16_2_27", "2_3_5"}, new String[]{"16_2_5", "2_3_6"}, new String[]{"16_2_28", "2_3_7"}, new String[]{"16_2_6", "2_3_8"}, new String[]{"16_2_7", "2_3_9"}, new String[]{"16_2_8", "2_3_10"}, new String[]{"16_2_9", "2_3_11"}, new String[]{"16_2_29", "2_3_12"}, new String[]{"16_2_10", "2_3_13"}, new String[]{"16_2_30", "2_3_14"}, new String[]{"16_2_11", "2_3_15"}, new String[]{"16_2_12", "2_3_16"}, new String[]{"8_0_51", "4_1_1"}};

    static {
        int i = 198;
        b = new HashMap<String, String>(i) { // from class: iyt.3
            private static final long serialVersionUID = -2301542898724043125L;

            {
                for (int i2 = 0; i2 < iyt.c.length; i2++) {
                    put(iyt.c[i2][0], iyt.c[i2][1]);
                }
            }
        };
        f13674a = new HashMap<String, String>(i) { // from class: iyt.4
            private static final long serialVersionUID = 5837739866070029930L;

            {
                for (int i2 = 0; i2 < iyt.c.length; i2++) {
                    put(iyt.c[i2][1], iyt.c[i2][0]);
                }
            }
        };
    }

    public static int c(String str) {
        String str2;
        int indexOf;
        if (str == null || (str2 = b.get(str)) == null || (indexOf = str2.indexOf(95)) < 0) {
            return -1;
        }
        try {
            return Integer.parseInt(str2.substring(0, indexOf));
        } catch (NumberFormatException unused) {
            LogUtil.e("CommandTransfer", "getServiceID NumberFormatException");
            return -1;
        }
    }

    public static int a(String str) {
        String str2;
        int indexOf;
        String substring;
        int indexOf2;
        if (str == null || (str2 = b.get(str)) == null || (indexOf = str2.indexOf(95)) < 0 || (indexOf2 = (substring = str2.substring(indexOf + 1, str2.length())).indexOf(95)) < 0) {
            return -1;
        }
        try {
            return Integer.parseInt(substring.substring(0, indexOf2));
        } catch (NumberFormatException unused) {
            LogUtil.e("CommandTransfer", "getCommandID NumberFormatException");
            return -1;
        }
    }

    public static int g(String str) {
        String str2;
        int indexOf;
        String substring;
        int indexOf2;
        if (str == null || (str2 = b.get(str)) == null || (indexOf = str2.indexOf(95)) < 0 || (indexOf2 = (substring = str2.substring(indexOf + 1, str2.length())).indexOf(95)) < 0) {
            return -1;
        }
        String substring2 = substring.substring(indexOf2 + 1, substring.length());
        int indexOf3 = substring2.indexOf(95);
        if (indexOf3 < 0) {
            try {
                return Integer.parseInt(substring2);
            } catch (NumberFormatException unused) {
                LogUtil.e("CommandTransfer", "getV2TypeID NumberFormatException");
                return -1;
            }
        }
        return bli.h(substring2.substring(0, indexOf3));
    }

    public static int e(String str) {
        String str2;
        int indexOf;
        if (str == null || (str2 = f13674a.get(str)) == null || (indexOf = str2.indexOf(95)) < 0) {
            return -1;
        }
        try {
            return Integer.parseInt(str2.substring(0, indexOf));
        } catch (NumberFormatException unused) {
            LogUtil.e("CommandTransfer", "getServiceType NumberFormatException");
            return -1;
        }
    }

    public static int d(String str) {
        String str2;
        int indexOf;
        String substring;
        int indexOf2;
        if (str == null || (str2 = f13674a.get(str)) == null || (indexOf = str2.indexOf(95)) < 0 || (indexOf2 = (substring = str2.substring(indexOf + 1, str2.length())).indexOf(95)) < 0) {
            return -1;
        }
        try {
            return Integer.parseInt(substring.substring(0, indexOf2));
        } catch (NumberFormatException unused) {
            LogUtil.e("CommandTransfer", "getOperatorInfo NumberFormatException");
            return -1;
        }
    }

    public static int b(String str) {
        String str2;
        int indexOf;
        String substring;
        int indexOf2;
        int parseInt;
        int i = -1;
        if (str == null || (str2 = f13674a.get(str)) == null || (indexOf = str2.indexOf(95)) < 0 || (indexOf2 = (substring = str2.substring(indexOf + 1, str2.length())).indexOf(95)) < 0) {
            return -1;
        }
        String substring2 = substring.substring(indexOf2 + 1, substring.length());
        int indexOf3 = substring2.indexOf(95);
        try {
            if (indexOf3 < 0) {
                parseInt = Integer.parseInt(substring2);
            } else {
                parseInt = Integer.parseInt(substring2.substring(0, indexOf3));
            }
            i = parseInt;
            return i;
        } catch (NumberFormatException unused) {
            LogUtil.e("CommandTransfer", "getV1TypeID NumberFormatException");
            return i;
        }
    }

    private static List<bmp> c(byte[] bArr, int i) {
        int i2;
        bmn bmnVar = new bmn();
        bmp bmpVar = new bmp();
        String d = blq.d(bArr);
        if (d == null || d.length() < (i2 = i * 2)) {
            LogUtil.a("CommandTransfer", "getTlvTransfers valid data");
            return null;
        }
        try {
            bmp e = bmnVar.e(bmpVar, d.substring(i2, d.length()), 0);
            if (e != null) {
                return e.c();
            }
            return null;
        } catch (bmk e2) {
            LogUtil.e("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "CommandTransfer", "Exception:", ExceptionUtils.d(e2));
            return null;
        }
    }

    public static byte[] a(int i, int i2, int i3, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length - 1];
        System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
        List<bmp> c2 = c(bArr, 3);
        if (c2 == null) {
            return bArr2;
        }
        String format = String.format(Locale.ENGLISH, "%d_%d_%s", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        int c3 = c(format);
        int a2 = a(format);
        bArr2[0] = (byte) c3;
        bArr2[1] = (byte) a2;
        for (bmp bmpVar : c2) {
            String d = bmpVar.d();
            try {
                if (127 == Integer.parseInt(d)) {
                    break;
                }
                int g = g(c(i, i2, i3, d));
                if (-1 != g) {
                    bmpVar.e(String.valueOf(g));
                    if (128 == (bArr[bmpVar.e() + 3] & 128)) {
                        bArr2[bmpVar.e() + 2] = (byte) (g | 128);
                    } else {
                        bArr2[bmpVar.e() + 2] = (byte) g;
                    }
                }
            } catch (NumberFormatException unused) {
                LogUtil.e("CommandTransfer", "transferV1ToV2Protocol NumberFormatException");
            }
        }
        return bArr2;
    }

    private static String c(int i, int i2, int i3, String str) {
        if (1 == i && 2 == i2 && h(str)) {
            return String.format(Locale.ENGLISH, "%d_%d_%s_%s", Integer.valueOf(i), Integer.valueOf(i2), str, Integer.valueOf(i3));
        }
        if (7 == i && 1 == i2 && j(str)) {
            return String.format(Locale.ENGLISH, "%d_%d_%s_%s", Integer.valueOf(i), Integer.valueOf(i2), str, Integer.valueOf(i3));
        }
        return String.format(Locale.ENGLISH, "%d_%d_%s", Integer.valueOf(i), Integer.valueOf(i2), str);
    }

    private static boolean h(String str) {
        return (str.equals(String.valueOf(8)) || str.equals(String.valueOf(9))) || (str.equals(String.valueOf(10)) || str.equals(String.valueOf(11)));
    }

    private static boolean j(String str) {
        return f(str) || i(str);
    }

    private static boolean f(String str) {
        return str.equals(String.valueOf(48)) || str.equals(String.valueOf(15)) || str.equals(String.valueOf(16));
    }

    private static boolean i(String str) {
        return str.equals(String.valueOf(17)) || str.equals(String.valueOf(18));
    }

    public static byte[] c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        byte b3 = bArr[1];
        byte[] bArr2 = new byte[bArr.length + 1];
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        List<bmp> c2 = c(bArr, 2);
        if (c2 != null && c2.size() > 0) {
            String format = String.format(Locale.ENGLISH, "%d_%d_%s", Integer.valueOf(b2), Integer.valueOf(b3), c2.get(0).a());
            bArr2[1] = (byte) e(format);
            bArr2[2] = (byte) d(format);
            if (1 == b2 && 16 == b3) {
                bArr2[2] = 2;
            }
            for (bmp bmpVar : c2) {
                int b4 = b(String.format(Locale.ENGLISH, "%d_%d_%s", Integer.valueOf(b2), Integer.valueOf(b3), bmpVar.a()));
                if (-1 != b4) {
                    bmpVar.c(String.valueOf(b4));
                    if (128 == (bArr[bmpVar.e() + 2] & 128)) {
                        bArr2[bmpVar.e() + 3] = (byte) b4;
                        int e = bmpVar.e() + 3;
                        bArr2[e] = (byte) (bArr2[e] | 128);
                    } else {
                        bArr2[bmpVar.e() + 3] = (byte) b4;
                    }
                }
            }
        }
        return bArr2;
    }
}
