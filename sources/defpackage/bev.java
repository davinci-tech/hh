package defpackage;

/* loaded from: classes3.dex */
public class bev {
    public static String e(String str) {
        return (str == null || !str.startsWith("https://")) ? "https://" + str : str;
    }

    public static String d(String str) {
        return str == null ? "" : str;
    }
}
