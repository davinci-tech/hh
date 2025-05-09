package defpackage;

import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class msv {
    private static String[] b = {"sr-Latn", "jv-Latn"};
    private static HashMap<String, String> c = new HashMap() { // from class: msv.3
        private static final long serialVersionUID = 1212859686823339267L;

        {
            put("sr-Latn", "b+sr+Latn");
            put("jv-Latn", "b+jv+Latn");
        }
    };

    public static String c() {
        return mrv.d + "index_all" + File.separator + "done";
    }

    public static String a() {
        return mrv.d + "index_all" + File.separator + "index_all.json";
    }

    public static String b() {
        return mrv.d + "img_index_all" + File.separator;
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return mrv.d + str + File.separator;
    }
}
