package defpackage;

/* loaded from: classes.dex */
public class ixn {
    private static final String[] c = {""};

    public static boolean c(String str) {
        if (str == null) {
            return false;
        }
        for (String str2 : c) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }
}
