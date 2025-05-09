package defpackage;

import com.huawei.operation.share.HiHealthError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class iox {

    /* renamed from: a, reason: collision with root package name */
    public static final List<Integer> f13525a;

    public static int a(int i) {
        if (i == -2) {
            return 201;
        }
        if (i != 0) {
            return i != 2 ? 4 : 2;
        }
        return 0;
    }

    public static int b(int i) {
        if (i == 3) {
            return 2;
        }
        if (i == 5 || i == 15 || i == 17 || i == 23) {
            return 4;
        }
        if (i == 24) {
            return 1;
        }
        switch (i) {
            case 7:
                return 2;
            case 8:
            case 9:
            case 10:
                return 4;
            default:
                return i;
        }
    }

    public static int c(int i) {
        if (i != 151) {
            return i;
        }
        return 1;
    }

    public static int d(int i) {
        if (i == 0) {
            return 0;
        }
        if (i != 1 && i != 2) {
            if (i == 500001) {
                return 2;
            }
            switch (i) {
                case 200:
                    return 0;
                case 201:
                case 202:
                case 203:
                case 204:
                    break;
                default:
                    return 4;
            }
        }
        return 1;
    }

    public static int f(int i) {
        if (i == 0) {
            return 0;
        }
        if (i != 1) {
            if (i == 4 || i == 5 || i == 150) {
                return i;
            }
            if (i == 100000) {
                return 0;
            }
            if (i == 100002) {
                return 5;
            }
            if (i != 300004) {
                return 4;
            }
        }
        return 1;
    }

    public static int h(int i) {
        if (i == 150 || i == 153) {
            return HiHealthError.ERR_WRONG_DEVICE;
        }
        if (i != 1007) {
            return i;
        }
        return 4;
    }

    public static int j(int i) {
        if (i == 150 || i == 153) {
            return HiHealthError.ERR_WRONG_DEVICE;
        }
        switch (i) {
            case 1005:
            case 1006:
            case 1007:
                return 4;
            default:
                return i;
        }
    }

    static {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(10002);
        f13525a = Collections.unmodifiableList(arrayList);
    }

    public static boolean e(int i) {
        return f13525a.contains(Integer.valueOf(i));
    }
}
