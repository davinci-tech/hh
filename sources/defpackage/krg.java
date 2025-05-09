package defpackage;

/* loaded from: classes5.dex */
public class krg {
    public static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            ksy.c("CloudSettings-Util", "paseInt error " + e.getClass().getSimpleName(), true);
            return -1;
        }
    }
}
