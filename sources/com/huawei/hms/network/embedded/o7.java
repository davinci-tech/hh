package com.huawei.hms.network.embedded;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class o7 {
    public static final String e = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    public static final String f = "\"([^\"]*)\"";
    public static final Pattern g = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    public static final Pattern h = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* renamed from: a, reason: collision with root package name */
    public final String f5399a;
    public final String b;
    public final String c;

    @Nullable
    public final String d;

    public String toString() {
        return this.f5399a;
    }

    public int hashCode() {
        return this.f5399a.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof o7) && ((o7) obj).f5399a.equals(this.f5399a);
    }

    public String c() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    @Nullable
    public Charset a(@Nullable Charset charset) {
        try {
            String str = this.d;
            return str != null ? Charset.forName(str) : charset;
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }

    @Nullable
    public Charset a() {
        return a((Charset) null);
    }

    @Nullable
    public static o7 b(String str) {
        try {
            return a(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static o7 a(String str) {
        Matcher matcher = g.matcher(str);
        if (!matcher.lookingAt()) {
            throw new IllegalArgumentException("No subtype found for: \"" + str + '\"');
        }
        String lowerCase = matcher.group(1).toLowerCase(Locale.US);
        String lowerCase2 = matcher.group(2).toLowerCase(Locale.US);
        Matcher matcher2 = h.matcher(str);
        String str2 = null;
        for (int end = matcher.end(); end < str.length(); end = matcher2.end()) {
            matcher2.region(end, str.length());
            if (!matcher2.lookingAt()) {
                throw new IllegalArgumentException("Parameter is not formatted correctly: \"" + str.substring(end) + "\" for: \"" + str + '\"');
            }
            String group = matcher2.group(1);
            if (group != null && group.equalsIgnoreCase("charset")) {
                String group2 = matcher2.group(2);
                if (group2 == null) {
                    group2 = matcher2.group(3);
                } else if (group2.startsWith("'") && group2.endsWith("'") && group2.length() > 2) {
                    group2 = group2.substring(1, group2.length() - 1);
                }
                if (str2 != null && !group2.equalsIgnoreCase(str2)) {
                    throw new IllegalArgumentException("Multiple charsets defined: \"" + str2 + "\" and: \"" + group2 + "\" for: \"" + str + '\"');
                }
                str2 = group2;
            }
        }
        return new o7(str, lowerCase, lowerCase2, str2);
    }

    public o7(String str, String str2, String str3, @Nullable String str4) {
        this.f5399a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }
}
