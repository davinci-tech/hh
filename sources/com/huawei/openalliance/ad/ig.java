package com.huawei.openalliance.ad;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class ig {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f6941a = Pattern.compile("GET /(.*) HTTP");
    private static final Pattern b = Pattern.compile("Range: (.*)");
    private static final Pattern c = Pattern.compile("bytes=(\\d+)");
    private String d;
    private String e;
    private String f;
    private String g;
    private int h = 0;

    public int f() {
        return this.h;
    }

    public String e() {
        return this.g;
    }

    public Long d() {
        Matcher matcher = c.matcher(a());
        if (!matcher.find()) {
            return 0L;
        }
        ho.b("HttpGetRequest", "request Range bytes:" + matcher.group(1));
        return Long.valueOf(matcher.group(1));
    }

    public String c() {
        return this.f;
    }

    public String b() {
        return this.d;
    }

    public String a() {
        return this.e;
    }

    private String c(String str) {
        Matcher matcher = b.matcher(str);
        if (!matcher.find()) {
            ho.b("HttpGetRequest", "request header not have range");
            return "";
        }
        ho.b("HttpGetRequest", "request Range:" + matcher.group(1));
        return matcher.group(1);
    }

    private String b(String str) {
        Matcher matcher = f6941a.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Invalid request, url not found");
    }

    private void a(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("&")) {
            String[] split = str2.split("=");
            if (split.length >= 2) {
                String str3 = split[0];
                String str4 = split[1];
                try {
                    hashMap.put(str3, URLDecoder.decode(str4, "UTF-8"));
                } catch (Throwable th) {
                    ho.d("HttpGetRequest", "decode failed error: %s for key = %s ,rawValue = %s", th.getClass().getSimpleName(), str3, str4);
                }
            }
        }
        this.f = (String) hashMap.get("nonsense");
        this.g = (String) hashMap.get("sha256");
        this.h = com.huawei.openalliance.ad.utils.cz.a((String) hashMap.get("checkFlag"), 0);
    }

    public static ig a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (com.huawei.openalliance.ad.utils.cz.b(readLine)) {
                return new ig(sb.toString());
            }
            sb.append(readLine);
            sb.append('\n');
        }
    }

    private ig(String str) {
        ho.a("HttpGetRequest", "Media Player request header: %s", str);
        this.e = c(str);
        String b2 = b(str);
        if (com.huawei.openalliance.ad.utils.cz.b(b2)) {
            ho.d("HttpGetRequest", "parse uri failed, source is empty");
            return;
        }
        int indexOf = b2.indexOf("?");
        try {
            this.d = URLDecoder.decode(b2.substring(0, indexOf == -1 ? b2.length() : indexOf), "UTF-8");
        } catch (Throwable th) {
            ho.d("HttpGetRequest", "decode failed error: %s", th.getClass().getSimpleName());
        }
        if (indexOf != -1) {
            a(b2.substring(indexOf + 1));
            ho.a("HttpGetRequest", "url: %s, nonsense: %s", this.d, this.f);
        }
    }
}
