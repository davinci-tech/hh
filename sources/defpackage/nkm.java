package defpackage;

import java.io.File;

/* loaded from: classes9.dex */
public class nkm {
    public static String b(String str) {
        if (str.endsWith(File.separator)) {
            str = str.substring(0, str.length() - 2);
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf);
    }
}
