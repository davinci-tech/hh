package defpackage;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes5.dex */
public class jrv {
    private SortedMap<String, String> d = new TreeMap();

    jrv(String str) {
        if (str == null) {
            return;
        }
        for (String str2 : str.split("&")) {
            int indexOf = str2.indexOf("=");
            if (indexOf != -1) {
                this.d.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(512);
        for (Map.Entry<String, String> entry : this.d.entrySet()) {
            String key = entry.getKey();
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(key);
            sb.append("=");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    String b(String str) throws UnsupportedEncodingException {
        String str2 = this.d.get(URLEncoder.encode(str, "UTF-8"));
        if (str2 == null) {
            return null;
        }
        return URLDecoder.decode(str2, "UTF-8");
    }
}
