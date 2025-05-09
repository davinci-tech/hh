package defpackage;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
class utc {
    static Set<String> b(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(strArr)));
    }

    static Map<String, String> e(Map<String, String> map, Set<String> set) {
        if (map == null) {
            return Collections.emptyMap();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            utq.d(key, (Object) "additional parameter keys cannot be null");
            utq.d(value, (Object) "additional parameter values cannot be null");
            utq.e(!set.contains(key), "Parameter %s is directly supported via the authorization request builder, use the builder method instead", key);
            linkedHashMap.put(key, value);
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    static Map<String, String> b(JSONObject jSONObject, Set<String> set) throws JSONException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!set.contains(next)) {
                linkedHashMap.put(next, jSONObject.get(next).toString());
            }
        }
        return linkedHashMap;
    }

    static Map<String, String> ffS_(Uri uri, Set<String> set) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : uri.getQueryParameterNames()) {
            if (!set.contains(str)) {
                linkedHashMap.put(str, uri.getQueryParameter(str));
            }
        }
        return linkedHashMap;
    }
}
