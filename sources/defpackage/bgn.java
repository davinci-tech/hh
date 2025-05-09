package defpackage;

import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class bgn {
    private static final Map<String, String> e;

    static {
        HashMap hashMap = new HashMap(5);
        e = hashMap;
        hashMap.put(bip.i.toString(), bip.g.toString());
    }

    public static String a(String str) {
        Map<String, String> map = e;
        if (!map.containsKey(str)) {
            return str;
        }
        String str2 = map.get(str);
        LogUtil.c("CharacteristicConfig", "getCharacteristic ", str, " to ", str2);
        return str2;
    }
}
