package defpackage;

import java.util.HashMap;

/* loaded from: classes6.dex */
public class nde extends HashMap<String, String> {
    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String put(String str, String str2) {
        String str3 = get(str);
        if (str3 != null) {
            if (str3.contains(str2)) {
                return str2;
            }
            return (String) super.put(str, str3 + "HWSKINNERGS" + str2);
        }
        return (String) super.put(str, str2);
    }

    public String[] b(String str) {
        String str2 = get(str);
        if (str2 != null) {
            return str2.contains("HWSKINNERGS") ? str2.split("HWSKINNERGS") : new String[]{str2};
        }
        return null;
    }
}
