package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.RawDataFromCsafeTreadmill;
import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class lah {
    private static boolean c = false;
    private static boolean e = false;

    public static double d(int i, int i2) {
        double d;
        double d2;
        if (i2 != 82 && i2 != 83) {
            double d3 = 0.1d;
            double d4 = 0.01d;
            switch (i2) {
                case 16:
                    d = i;
                    d3 = 1.609344d;
                    return d * d3;
                case 17:
                    d = i * 0.1d;
                    d3 = 1.609344d;
                    return d * d3;
                case 18:
                    d = i * 0.01d;
                    d3 = 1.609344d;
                    return d * d3;
                default:
                    switch (i2) {
                        case 48:
                            break;
                        case 49:
                            d = i;
                            return d * d3;
                        case 50:
                            d2 = i;
                            return d2 * d4;
                        case 51:
                            d2 = i * 60;
                            d4 = 0.001d;
                            return d2 * d4;
                        default:
                            return -1.0d;
                    }
            }
        }
        return i;
    }

    public static double e(int i, int i2) {
        double d;
        double d2;
        if (i2 == 1) {
            d = i;
        } else if (i2 == 2) {
            d = i * 0.1d;
        } else if (i2 == 3) {
            d = i * 0.01d;
        } else {
            if (i2 != 4) {
                if (i2 == 5) {
                    d = i;
                    d2 = 3.048E-4d;
                } else if (i2 == 10) {
                    d = i;
                    d2 = 0.003048d;
                } else {
                    if (i2 == 69) {
                        return i * 0.0011f;
                    }
                    switch (i2) {
                        case 33:
                            return i;
                        case 34:
                            return i * 0.1d;
                        case 35:
                            return i * 0.01d;
                        case 36:
                            return i * 0.001d;
                        case 37:
                            d = i;
                            d2 = 1.0E-4d;
                            break;
                        default:
                            return -1.0d;
                    }
                }
                return d * d2;
            }
            d = i * 0.001d;
        }
        d2 = 1.609344d;
        return d * d2;
    }

    public static void d() {
        e = true;
        c = true;
    }

    public static void d(byte[] bArr, RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill) {
        if (rawDataFromCsafeTreadmill == null || bArr == null) {
            LogUtil.h("Track_IDEQ_CsafeDataAnalyzer", "analysisCsafeCommand, rawDataFromCsafeTreadmill is null");
            return;
        }
        byte[][] c2 = c(bArr, bArr.length);
        if (c2 == null || c2.length == 0) {
            return;
        }
        d(rawDataFromCsafeTreadmill, c2);
    }

    private static boolean d(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, byte[][] bArr) {
        if (rawDataFromCsafeTreadmill == null || bArr == null) {
            LogUtil.h("Track_IDEQ_CsafeDataAnalyzer", "analysisCsafeCommand, rawDataFromCsafeTreadmill is null");
            return false;
        }
        byte b = bArr[bArr.length - 1][0];
        for (int i = 0; i <= (b & 255); i++) {
            byte[] bArr2 = bArr[i];
            int i2 = (bArr2[bArr2.length - 1] & 255) + 1;
            byte b2 = bArr2[2];
            LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "getCommandFromCsafe validLength=", Integer.valueOf(i2), "command=", Byte.valueOf(b2));
            if (i2 == 4) {
                int i3 = bArr[i][1] & BaseType.Obj;
                rawDataFromCsafeTreadmill.setStateOfTreadmill(i3);
                if (c) {
                    rawDataFromCsafeTreadmill.setFirstStateOfTm(i3);
                    c = false;
                }
            } else if (d(b2, rawDataFromCsafeTreadmill, bArr, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean d(byte b, RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, byte[][] bArr, int i) {
        if (b == -91) {
            return h(rawDataFromCsafeTreadmill, bArr, i);
        }
        if (b == -80) {
            d(rawDataFromCsafeTreadmill, bArr, i);
            return false;
        }
        if (b != -76) {
            switch (b) {
                case -96:
                    return e(rawDataFromCsafeTreadmill, bArr, i);
                case -95:
                    return c(rawDataFromCsafeTreadmill, bArr, i);
                case -94:
                    return b(rawDataFromCsafeTreadmill, bArr, i);
                case -93:
                    return a(rawDataFromCsafeTreadmill, bArr, i);
                default:
                    return false;
            }
        }
        return j(rawDataFromCsafeTreadmill, bArr, i);
    }

    private static boolean e(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, byte[][] bArr, int i) {
        if (rawDataFromCsafeTreadmill == null || bArr == null || i < 0) {
            LogUtil.h("Track_IDEQ_CsafeDataAnalyzer", "isCommandDistance, rawDataFromCsafeTreadmill is null");
            return false;
        }
        byte[] bArr2 = bArr[i];
        if (bArr2[3] == 3 && bArr2[8] == -14) {
            int i2 = bArr2[4] & 255;
            int i3 = bArr2[5] & 255;
            int i4 = bArr2[6] & 255;
            int i5 = bArr2[1] & BaseType.Obj;
            if (i2 >= 255 || i3 >= 255 || i4 >= 60) {
                LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "analysisCsafeCommand, get a invalid time:", Integer.valueOf(i2), ":", Integer.valueOf(i3), ":", Integer.valueOf(i4));
                return true;
            }
            if (e && i5 != 7) {
                rawDataFromCsafeTreadmill.setStartSecond(i4, i3, i2);
                e = false;
            }
            if (i5 != 7 && i5 != 2 && i5 != 0 && i5 != 1) {
                rawDataFromCsafeTreadmill.setDuringTime(i4, i3, i2);
            }
            rawDataFromCsafeTreadmill.setStateOfTreadmill(i5);
            if (c) {
                rawDataFromCsafeTreadmill.setFirstStateOfTm(i5);
                c = false;
            }
        }
        return false;
    }

    private static boolean c(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, byte[][] bArr, int i) {
        if (rawDataFromCsafeTreadmill == null || bArr == null || i < 0) {
            LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "isCommandDistance, rawDataFromCsafeTreadmill is null");
            return false;
        }
        byte[] bArr2 = bArr[i];
        if (bArr2[3] == 3 && bArr2[8] == -14) {
            byte b = bArr2[6];
            int i2 = bArr2[5] & 255;
            int i3 = bArr2[4] & 255;
            int i4 = bArr2[1] & BaseType.Obj;
            if (i2 >= 255 && i3 >= 255) {
                LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "analysisCsafeCommand, get a invalid distance FF FF");
                return true;
            }
            if (i4 != 7 && i4 != 2 && i4 != 0 && i4 != 1) {
                rawDataFromCsafeTreadmill.setDistance((i2 * 255) + i3, b & 255);
            }
            rawDataFromCsafeTreadmill.setStateOfTreadmill(bArr[i][1] & BaseType.Obj);
            if (c) {
                rawDataFromCsafeTreadmill.setFirstStateOfTm(i4);
                c = false;
            }
        }
        return false;
    }

    private static boolean a(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, byte[][] bArr, int i) {
        if (rawDataFromCsafeTreadmill == null || bArr == null || i < 0) {
            LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "isCommandHearRate, rawDataFromCsafeTreadmill is null");
            return false;
        }
        byte[] bArr2 = bArr[i];
        if (bArr2[3] == 2 && bArr2[7] == -14) {
            int i2 = bArr2[5] & 255;
            int i3 = bArr2[4] & 255;
            int i4 = bArr2[1] & BaseType.Obj;
            LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "isCommandCalorie calorieHigh=", Integer.valueOf(i2), "calorieLow=", Integer.valueOf(i3), "state=", Integer.valueOf(i4));
            if (i2 >= 255 && i3 >= 255) {
                LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "analysisCsafeCommand, get a invalid calorie FF FF");
                return true;
            }
            if (i4 != 7 && i4 != 2 && i4 != 0 && i4 != 1) {
                rawDataFromCsafeTreadmill.setCalorie((i2 * 255) + i3);
            }
            rawDataFromCsafeTreadmill.setStateOfTreadmill(bArr[i][1] & BaseType.Obj);
            if (c) {
                rawDataFromCsafeTreadmill.setFirstStateOfTm(i4);
                c = false;
            }
        }
        return false;
    }

    private static void d(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, byte[][] bArr, int i) {
        if (rawDataFromCsafeTreadmill == null || bArr == null || i < 0) {
            LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "isCommandHearRate, rawDataFromCsafeTreadmill is null");
            return;
        }
        byte[] bArr2 = bArr[i];
        if (bArr2[3] == 1 && bArr2[6] == -14) {
            int i2 = bArr2[1] & BaseType.Obj;
            if (i2 != 7 && i2 != 2 && i2 != 0 && i2 != 1) {
                rawDataFromCsafeTreadmill.setHeartRateFromTreadmill(bArr2[4] & 255);
            }
            rawDataFromCsafeTreadmill.setStateOfTreadmill(bArr[i][1] & BaseType.Obj);
            if (c) {
                rawDataFromCsafeTreadmill.setFirstStateOfTm(i2);
                c = false;
            }
        }
    }

    private static boolean h(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, byte[][] bArr, int i) {
        if (rawDataFromCsafeTreadmill == null || bArr == null || i < 0) {
            LogUtil.h("Track_IDEQ_CsafeDataAnalyzer", "isCommandPower, rawDataFromCsafeTreadmill is null");
            return false;
        }
        byte[] bArr2 = bArr[i];
        if (bArr2[3] == 3 && bArr2[8] == -14) {
            int i2 = bArr2[5] & 255;
            int i3 = bArr2[4] & 255;
            byte b = bArr2[6];
            int i4 = bArr2[1] & BaseType.Obj;
            if (i2 >= 255 && i3 >= 255) {
                LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "analysisCsafeCommand, get a invalid speed FF FF");
                return true;
            }
            if (i4 != 7 && i4 != 2 && i4 != 0 && i4 != 1) {
                rawDataFromCsafeTreadmill.setSpeed((i2 * 255) + i3, b & 255);
            }
            rawDataFromCsafeTreadmill.setStateOfTreadmill(bArr[i][1] & BaseType.Obj);
            if (c) {
                rawDataFromCsafeTreadmill.setFirstStateOfTm(i4);
                c = false;
            }
        }
        return false;
    }

    private static boolean j(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, byte[][] bArr, int i) {
        if (rawDataFromCsafeTreadmill == null || bArr == null || i < 0) {
            LogUtil.h("Track_IDEQ_CsafeDataAnalyzer", "isCommandPower, rawDataFromCsafeTreadmill is null");
            return false;
        }
        byte[] bArr2 = bArr[i];
        if (bArr2[3] == 3 && bArr2[8] == -14) {
            int i2 = bArr2[5] & 255;
            int i3 = bArr2[4] & 255;
            byte b = bArr2[6];
            int i4 = bArr2[1] & BaseType.Obj;
            if (i2 >= 255 && i3 >= 255) {
                LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "analysisCsafeCommand, get a invalid power FF FF");
                return true;
            }
            if (i4 != 7 && i4 != 2 && i4 != 0 && i4 != 1) {
                rawDataFromCsafeTreadmill.setPower((i2 * 255) + i3, b & 255);
            }
            rawDataFromCsafeTreadmill.setStateOfTreadmill(bArr[i][1] & BaseType.Obj);
            if (c) {
                rawDataFromCsafeTreadmill.setFirstStateOfTm(i4);
                c = false;
            }
        }
        return false;
    }

    private static boolean b(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, byte[][] bArr, int i) {
        if (rawDataFromCsafeTreadmill == null || bArr == null || i < 0) {
            LogUtil.h("Track_IDEQ_CsafeDataAnalyzer", "isCommandCreep, rawDataFromCsafeTreadmill is null");
            return false;
        }
        byte[] bArr2 = bArr[i];
        if (bArr2[3] == 3 && bArr2[8] == -14) {
            int i2 = bArr2[5] & 255;
            int i3 = bArr2[4] & 255;
            byte b = bArr2[6];
            int i4 = bArr2[1] & BaseType.Obj;
            if (i2 >= 255 && i3 >= 255) {
                LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "analysisCsafeCommand, get a invalid creep FF FF");
                return true;
            }
            if (i4 != 7 && i4 != 2 && i4 != 0 && i4 != 1) {
                rawDataFromCsafeTreadmill.setCreep((i2 * 255) + i3, b & 255);
            }
            rawDataFromCsafeTreadmill.setStateOfTreadmill(bArr[i][1] & BaseType.Obj);
            if (c) {
                rawDataFromCsafeTreadmill.setFirstStateOfTm(i4);
                c = false;
            }
        }
        return false;
    }

    private static byte[][] c(byte[] bArr, int i) {
        if (bArr != null && i > 2) {
            LogUtil.a("Track_IDEQ_CsafeDataAnalyzer", "getValidData data=", Arrays.toString(bArr), "length=", Integer.valueOf(i));
            int i2 = i / 2;
            byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i2 + 2, i + 1);
            int i3 = 0;
            int i4 = 0;
            int i5 = -1;
            for (int i6 = 0; i6 < i; i6++) {
                byte b = bArr[i6];
                if (b == -15 && i5 == -1) {
                    i5 = i6;
                }
                if (i5 != -1) {
                    bArr2[i3][i4] = b;
                    i4++;
                }
                if (i5 != -1 && bArr[i6] == -14) {
                    bArr2[i3][i] = (byte) (i4 - 1);
                    i3++;
                    i4 = 0;
                    i5 = -1;
                }
            }
            int i7 = i3 - 1;
            bArr2[i2 + 1][0] = (byte) i7;
            if (i7 >= 0) {
                return bArr2;
            }
            return (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 0, 0);
        }
        return (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 0, 0);
    }
}
