package health.compact.a;

import android.util.Log;

/* loaded from: classes.dex */
public class BuildTypeConfig {
    private static int d = 1;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void c(String str) {
        char c;
        if (str == null) {
            com.huawei.hwlogsmodel.LogUtil.h("BuildTypeConfig", "modifyBuildType string null");
            d = 1;
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -2008409822:
                if (str.equals("speedup")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1594979737:
                if (str.equals("t3rdDevice")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1581702237:
                if (str.equals("customTest")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -599510460:
                if (str.equals("StoreDemo")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 71885:
                if (str.equals("HTY")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3020272:
                if (str.equals("beta")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 95458899:
                if (str.equals("debug")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1090594823:
                if (str.equals("release")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1447343224:
                if (str.equals("BetaPay")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                d = 2;
                break;
            case 1:
                d = 6;
                break;
            case 2:
                d = 4;
                break;
            case 3:
                d = 5;
                break;
            case 4:
            case 5:
                d = 3;
                break;
            case 6:
                d = 2;
                break;
            case 7:
                d = 1;
                break;
            case '\b':
                d = 3;
                break;
            default:
                d = 1;
                break;
        }
        Log.i("BuildTypeConfig", "modifyBuildType(): sBuildType = " + d);
    }

    public static boolean a() {
        return d == 1;
    }

    public static boolean e() {
        return d == 3;
    }

    public static boolean c() {
        return d == 2;
    }

    public static boolean i() {
        return d == 4;
    }

    public static boolean b() {
        return d == 5;
    }

    public static boolean d() {
        return d == 6;
    }
}
