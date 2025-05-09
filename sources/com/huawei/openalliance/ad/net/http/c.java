package com.huawei.openalliance.ad.net.http;

import com.huawei.openalliance.ad.utils.cz;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, String> f7302a = new HashMap();

    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.f7302a.put(str, str2);
    }

    public void a(c cVar) {
        this.f7302a.putAll(cVar.f7302a);
    }

    public Map<String, String> a() {
        return new HashMap(this.f7302a);
    }

    public static String b(Map<String, List<String>> map) {
        if (map == null || map.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            sb.append("{");
            sb.append(entry.getKey());
            sb.append(":");
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("|");
            }
            cz.a(sb, '|');
            sb.append("}");
        }
        return sb.toString();
    }
}
