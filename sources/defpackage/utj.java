package defpackage;

import android.text.TextUtils;
import java.util.LinkedHashSet;

/* loaded from: classes7.dex */
final class utj {
    public static String a(Iterable<String> iterable) {
        if (iterable == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str : iterable) {
            utq.e(!TextUtils.isEmpty(str), "individual scopes cannot be null or empty");
            linkedHashSet.add(str);
        }
        if (linkedHashSet.isEmpty()) {
            return null;
        }
        return TextUtils.join(" ", linkedHashSet);
    }
}
