package defpackage;

import com.huawei.factory.MedalTypeAccessible;

/* loaded from: classes8.dex */
public class bnp {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public MedalTypeAccessible b(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode != 65) {
            switch (hashCode) {
                case 2065:
                    if (str.equals("A2")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 2066:
                    if (str.equals("A3")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 2067:
                    if (str.equals("A4")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals("A")) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0 || c == 1 || c == 2) {
            return new bns();
        }
        if (c == 3) {
            return new bnw();
        }
        return new bns();
    }
}
