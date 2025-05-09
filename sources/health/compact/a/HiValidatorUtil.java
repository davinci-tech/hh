package health.compact.a;

import android.text.TextUtils;
import com.huawei.hihealth.HiHealthData;

/* loaded from: classes.dex */
public class HiValidatorUtil {
    public static boolean a(double d) {
        return d > 0.0d;
    }

    public static boolean a(int i) {
        return i < 450000;
    }

    public static boolean b(int i) {
        return i > 0;
    }

    public static boolean c(int i) {
        return i < 500;
    }

    public static boolean d(double d) {
        return d - 1500.0d < 1.0E-6d;
    }

    public static boolean d(int i) {
        return i < 65536;
    }

    public static boolean e(double d) {
        return d - 2160000.0d < 1.0E-6d;
    }

    public static boolean e(float f) {
        return f > 0.0f;
    }

    public static boolean e(int i) {
        return i < 94371840;
    }

    private static boolean g(int i) {
        return i >= 1 && i <= 99;
    }

    public static boolean b(int i, double d) {
        return i != 2 ? i != 4 ? i != 5 ? a(d) && c(d) : d(d) && a(d) : d((int) d) && a(d) : c((int) d) && a(d);
    }

    public static boolean e(int i, HiHealthData hiHealthData) {
        double value = hiHealthData.getValue();
        if (i == 2) {
            return c((int) value) && a(value);
        }
        if (i == 7) {
            return g((int) value);
        }
        if (i != 901) {
            if (i != 2106) {
                if (i == 2109) {
                    return l(value);
                }
                if (i == 4) {
                    return d((int) value) && a(value);
                }
                if (i == 5) {
                    return d(value) && a(value);
                }
                switch (i) {
                    case 2008:
                    case 2009:
                    case 2010:
                    case 2011:
                    case 2012:
                    case 2013:
                    case 2014:
                    case 2015:
                        break;
                    default:
                        switch (i) {
                            case 2034:
                                return a(value) && !TextUtils.isEmpty(hiHealthData.getMetaData()) && c(value);
                            case 2035:
                            case 2036:
                            case 2037:
                                break;
                            default:
                                switch (i) {
                                    case 2101:
                                    case 2102:
                                        break;
                                    case 2103:
                                        return n(value);
                                    default:
                                        return a(value) && c(value);
                                }
                        }
                        return !TextUtils.isEmpty(hiHealthData.getMetaData());
                }
            }
            return h(value);
        }
        return f(value);
    }

    public static boolean e(int i, double d) {
        if (i == 40005) {
            return e(d) && a(d);
        }
        switch (i) {
            case 40002:
                break;
            case 40003:
                return e((int) d) && a(d);
            default:
                switch (i) {
                    case 40011:
                    case 40012:
                    case 40013:
                        break;
                    default:
                        switch (i) {
                            case 47401:
                                return k(d);
                            case 47402:
                                return i(d);
                            case 47403:
                                return j(d);
                            case 47404:
                                return g(d);
                            case 47405:
                                return o(d);
                            default:
                                return true;
                        }
                }
        }
        return a((int) d) && a(d);
    }

    public static boolean a(int i, double d) {
        switch (i) {
            case 61001:
            case 61002:
                return b(d);
            default:
                return true;
        }
    }

    public static boolean b(double d) {
        return Double.compare(d, 0.0d) == 0 || Double.compare(d, 10.0d) == 0 || Double.compare(d, 20.0d) == 0 || Double.compare(d, 30.0d) == 0 || Double.compare(d, 40.0d) == 0 || Double.compare(d, 50.0d) == 0;
    }

    public static boolean h(double d) {
        return Double.compare(d, 1.0d) >= 0 && Double.compare(d, 33.0d) <= 0;
    }

    public static boolean n(double d) {
        return Double.compare(d, 0.0d) > 0 && Double.compare(d, 100.0d) <= 0;
    }

    public static boolean l(double d) {
        return Double.compare(d, 0.0d) > 0;
    }

    public static boolean c(double d) {
        return Double.compare(d, Double.MAX_VALUE) <= 0;
    }

    public static boolean k(double d) {
        return Double.compare(d, 0.0d) >= 0 && Double.compare(d, 1000.0d) <= 0;
    }

    public static boolean i(double d) {
        return Double.compare(d, 0.0d) >= 0 && Double.compare(d, 10.0d) <= 0;
    }

    public static boolean j(double d) {
        return Double.compare(d, 0.0d) >= 0 && Double.compare(d, 10.0d) <= 0;
    }

    public static boolean g(double d) {
        return Double.compare(d, 0.0d) >= 0 && Double.compare(d, Double.MAX_VALUE) <= 0;
    }

    public static boolean o(double d) {
        return Double.compare(d, 0.0d) >= 0 && Double.compare(d, Double.MAX_VALUE) <= 0;
    }

    public static boolean f(double d) {
        return Double.compare(d, 0.0d) >= 0 && ((int) d) <= 450000;
    }
}
