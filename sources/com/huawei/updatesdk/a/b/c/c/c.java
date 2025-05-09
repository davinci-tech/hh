package com.huawei.updatesdk.a.b.c.c;

import com.huawei.updatesdk.a.a.d.g;
import com.huawei.updatesdk.a.a.d.h;
import com.huawei.updatesdk.service.appmgr.bean.SDKNetTransmission;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class c {
    public static final String CLIENT_API = "clientApi";
    private static final String END_FLAG = "_";
    private static String url;

    @SDKNetTransmission
    private String method;

    @SDKNetTransmission
    private String ver = "1.1";

    protected void e() {
    }

    public String d() {
        return url + CLIENT_API;
    }

    protected Map<String, Field> c() {
        HashMap hashMap = new HashMap();
        for (Field field : g.a(getClass())) {
            field.setAccessible(true);
            String name = field.getName();
            if (name.endsWith("_") || field.isAnnotationPresent(SDKNetTransmission.class)) {
                if (name.endsWith("_")) {
                    name = name.substring(0, name.length() - 1);
                }
                hashMap.put(name, field);
            }
        }
        return hashMap;
    }

    public void b(String str) {
        this.ver = str;
    }

    public String b() {
        return this.method;
    }

    public void a(String str) {
        this.method = str;
    }

    public String a() {
        e();
        Map<String, Field> c = c();
        int size = c.size();
        String[] strArr = new String[size];
        c.keySet().toArray(strArr);
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        do {
            String a2 = a(c.get(strArr[i]));
            if (a2 != null) {
                String a3 = h.a(a2);
                sb.append(strArr[i]);
                sb.append("=");
                sb.append(a3);
                sb.append("&");
            }
            i++;
        } while (i < size);
        int length = sb.length();
        if (length > 0) {
            int i2 = length - 1;
            if (sb.charAt(i2) == '&') {
                sb.deleteCharAt(i2);
            }
        }
        return sb.toString();
    }

    public static void c(String str) {
        url = str;
    }

    private String a(Field field) {
        Object obj = field.get(this);
        if (obj instanceof b) {
            return ((b) obj).toJson();
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }
}
