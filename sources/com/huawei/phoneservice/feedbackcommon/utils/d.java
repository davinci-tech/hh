package com.huawei.phoneservice.feedbackcommon.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes6.dex */
public class d {
    private SortedMap<String, String> e = new TreeMap();

    public String toString() {
        StringBuilder sb = new StringBuilder(512);
        for (Map.Entry<String, String> entry : this.e.entrySet()) {
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

    public String d(String str) throws UnsupportedEncodingException {
        String str2 = this.e.get(URLEncoder.encode(str, "UTF-8"));
        if (str2 == null) {
            return null;
        }
        return URLDecoder.decode(str2, "UTF-8");
    }

    public d(String str) {
        if (str == null) {
            return;
        }
        for (String str2 : str.split("&")) {
            int indexOf = str2.indexOf("=");
            this.e.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
        }
    }
}
