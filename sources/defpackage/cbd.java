package defpackage;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class cbd {
    public static List<Integer> e(String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        ArrayList arrayList = new ArrayList(bytes.length);
        for (byte b : bytes) {
            arrayList.add(Integer.valueOf(b & 255));
        }
        return arrayList;
    }
}
