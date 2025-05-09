package org.eclipse.californium.core.coap;

import com.huawei.hms.network.embedded.r1;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes7.dex */
public final class OptionNumberRegistry {
    private static volatile CustomOptionNumberRegistry e;

    public interface CustomOptionNumberRegistry {
        void assertValue(int i, long j);

        int[] getCriticalCustomOptions();

        OptionFormat getFormatByNr(int i);

        int[] getValueLengths(int i);

        boolean isSingleValue(int i);

        int toNumber(String str);

        String toString(int i);
    }

    public enum OptionFormat {
        INTEGER,
        STRING,
        OPAQUE,
        UNKNOWN,
        EMPTY
    }

    public static boolean b(int i) {
        return (i & 1) != 0;
    }

    public static boolean c(int i) {
        if (i == 1 || i == 17 || i == 20 || i == 23 || i == 35 || i == 39 || i == 60 || i == 258 || i == 11 || i == 12 || i == 14 || i == 15 || i == 27 || i == 28) {
            return false;
        }
        switch (i) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            default:
                return true;
        }
    }

    public static OptionFormat e(int i) {
        OptionFormat formatByNr;
        if (i != 1) {
            if (i != 17) {
                if (i != 20) {
                    if (i != 23) {
                        if (i != 35 && i != 39) {
                            if (i != 60 && i != 258) {
                                if (i != 11) {
                                    if (i != 12 && i != 14) {
                                        if (i != 15) {
                                            if (i != 27 && i != 28) {
                                                switch (i) {
                                                    case 3:
                                                    case 8:
                                                        break;
                                                    case 4:
                                                    case 9:
                                                        break;
                                                    case 5:
                                                        return OptionFormat.EMPTY;
                                                    case 6:
                                                    case 7:
                                                        break;
                                                    default:
                                                        CustomOptionNumberRegistry customOptionNumberRegistry = e;
                                                        return (customOptionNumberRegistry == null || (formatByNr = customOptionNumberRegistry.getFormatByNr(i)) == null) ? OptionFormat.UNKNOWN : formatByNr;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return OptionFormat.STRING;
            }
            return OptionFormat.INTEGER;
        }
        return OptionFormat.OPAQUE;
    }

    public static void c(int i, long j) {
        CustomOptionNumberRegistry customOptionNumberRegistry;
        if (c(i) && (customOptionNumberRegistry = e) != null) {
            customOptionNumberRegistry.assertValue(i, j);
        }
        try {
            a(i, (71 - Long.numberOfLeadingZeros(j)) / 8);
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException(e2.getMessage() + " Value " + j);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0070 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(int r6, int r7) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.core.coap.OptionNumberRegistry.a(int, int):void");
    }

    public static String a(int i) {
        String customOptionNumberRegistry;
        if (i == 0) {
            return "Reserved";
        }
        if (i == 1) {
            return "If-Match";
        }
        if (i == 11) {
            return "Uri-Path";
        }
        if (i == 12) {
            return "Content-Format";
        }
        if (i == 14) {
            return "Max-Age";
        }
        if (i == 15) {
            return "Uri-Query";
        }
        if (i == 27) {
            return "Block1";
        }
        if (i == 28) {
            return "Size2";
        }
        switch (i) {
            case 3:
                return "Uri-Host";
            case 4:
                return "ETag";
            case 5:
                return r1.b.n;
            case 6:
                return "Observe";
            case 7:
                return "Uri-Port";
            case 8:
                return "Location-Path";
            case 9:
                return "Object-Security";
            default:
                switch (i) {
                    case 17:
                        return "Accept";
                    case 20:
                        return "Location-Query";
                    case 23:
                        return "Block2";
                    case 35:
                        return "Proxy-Uri";
                    case 39:
                        return "Proxy-Scheme";
                    case 60:
                        return "Size1";
                    case 128:
                    case UserInfomation.WEIGHT_DEFAULT_ENGLISH /* 132 */:
                    case 136:
                    case 140:
                        return "Reserved";
                    case 258:
                        return "No-Response";
                    default:
                        CustomOptionNumberRegistry customOptionNumberRegistry2 = e;
                        return (customOptionNumberRegistry2 == null || (customOptionNumberRegistry = customOptionNumberRegistry2.toString(i)) == null) ? String.format("Unknown (%d)", Integer.valueOf(i)) : customOptionNumberRegistry;
                }
        }
    }

    public static int[] e() {
        CustomOptionNumberRegistry customOptionNumberRegistry = e;
        if (customOptionNumberRegistry != null) {
            return customOptionNumberRegistry.getCriticalCustomOptions();
        }
        return null;
    }
}
