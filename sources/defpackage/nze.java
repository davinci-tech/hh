package defpackage;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class nze extends nzc {
    private static final long serialVersionUID = 3686256241400139560L;
    private Map<String, String> b = new HashMap(16);

    public void b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        this.b.put(str, str2);
    }

    public String a(String str) {
        return (!TextUtils.isEmpty(str) && this.b.containsKey(str)) ? this.b.get(str) : "";
    }
}
