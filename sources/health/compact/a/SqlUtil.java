package health.compact.a;

/* loaded from: classes.dex */
public class SqlUtil {
    private SqlUtil() {
    }

    public static String d(String str) {
        return str == null ? "" : str.replaceAll(".*([';]+|(--)+).*", " ");
    }
}
