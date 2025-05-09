package com.huawei.hms.network.embedded;

import androidx.webkit.ProxyConfig;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import java.io.EOFException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.slf4j.Marker;

/* loaded from: classes9.dex */
public final class m7 {
    public static final char[] j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final String k = " \"':;<=>@[]^`{}|/\\?#";
    public static final String l = " \"':;<=>@[]^`{}|/\\?#";
    public static final String m = " \"<>^`{}|/\\?#";
    public static final String n = "[]";
    public static final String o = " \"'<>#";
    public static final String p = " \"'<>#&=";
    public static final String q = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
    public static final String r = "\\^`{|}";
    public static final String s = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    public static final String t = "";
    public static final String u = " \"#<>\\^`{|}";

    /* renamed from: a, reason: collision with root package name */
    public final String f5379a;
    public final String b;
    public final String c;
    public final String d;
    public final int e;
    public final List<String> f;

    @Nullable
    public final List<String> g;

    @Nullable
    public final String h;
    public final String i;

    public String w() {
        return this.b;
    }

    public URL v() {
        try {
            return new URL(this.i);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public URI u() {
        String aVar = j().c().toString();
        try {
            return new URI(aVar);
        } catch (URISyntaxException e) {
            try {
                return URI.create(aVar.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e);
            }
        }
    }

    public String toString() {
        return this.i;
    }

    @Nullable
    public String t() {
        if (f8.e(this.d)) {
            return null;
        }
        return PublicSuffixDatabase.a().a(this.d);
    }

    public String s() {
        return this.f5379a;
    }

    public String r() {
        return a("/...").q("").l("").a().toString();
    }

    public static final class a {
        public static final String i = "Invalid URL host";

        /* renamed from: a, reason: collision with root package name */
        @Nullable
        public String f5380a;

        @Nullable
        public String d;
        public final List<String> f;

        @Nullable
        public List<String> g;

        @Nullable
        public String h;
        public String b = "";
        public String c = "";
        public int e = -1;

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            String str2 = this.f5380a;
            if (str2 != null) {
                sb.append(str2);
                str = "://";
            } else {
                str = "//";
            }
            sb.append(str);
            if (!this.b.isEmpty() || !this.c.isEmpty()) {
                sb.append(this.b);
                if (!this.c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.c);
                }
                sb.append('@');
            }
            String str3 = this.d;
            if (str3 != null) {
                if (str3.indexOf(58) != -1) {
                    sb.append('[');
                    sb.append(this.d);
                    sb.append(']');
                } else {
                    sb.append(this.d);
                }
            }
            if (this.e != -1 || this.f5380a != null) {
                int b = b();
                String str4 = this.f5380a;
                if (str4 == null || b != m7.e(str4)) {
                    sb.append(':');
                    sb.append(b);
                }
            }
            m7.b(sb, this.f);
            if (this.g != null) {
                sb.append('?');
                m7.a(sb, this.g);
            }
            if (this.h != null) {
                sb.append('#');
                sb.append(this.h);
            }
            return sb.toString();
        }

        public a q(String str) {
            if (str == null) {
                throw new NullPointerException("username == null");
            }
            this.b = m7.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a p(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            String str2 = "http";
            if (!str.equalsIgnoreCase("http")) {
                str2 = ProxyConfig.MATCH_HTTPS;
                if (!str.equalsIgnoreCase(ProxyConfig.MATCH_HTTPS)) {
                    throw new IllegalArgumentException("unexpected scheme: " + str);
                }
            }
            this.f5380a = str2;
            return this;
        }

        public a o(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (this.g == null) {
                return this;
            }
            t(m7.a(str, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true));
            return this;
        }

        public a n(String str) {
            if (str == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.g == null) {
                return this;
            }
            t(m7.a(str, " \"'<>#&=", true, false, true, true));
            return this;
        }

        public a m(@Nullable String str) {
            this.g = str != null ? m7.h(m7.a(str, " \"'<>#", false, false, true, true)) : null;
            return this;
        }

        public a l(String str) {
            if (str == null) {
                throw new NullPointerException("password == null");
            }
            this.c = m7.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a k(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            String a2 = a(str, 0, str.length());
            if (a2 != null) {
                this.d = a2;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }

        public a j(@Nullable String str) {
            this.h = str != null ? m7.a(str, "", false, false, false, false) : null;
            return this;
        }

        public a i(String str) {
            if (str == null) {
                throw new NullPointerException("encodedUsername == null");
            }
            this.b = m7.a(str, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public a h(@Nullable String str) {
            this.g = str != null ? m7.h(m7.a(str, " \"'<>#", true, false, true, true)) : null;
            return this;
        }

        public a g(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPath == null");
            }
            if (str.startsWith("/")) {
                d(str, 0, str.length());
                return this;
            }
            throw new IllegalArgumentException("unexpected encodedPath: " + str);
        }

        public a f(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPassword == null");
            }
            this.c = m7.a(str, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public a e(@Nullable String str) {
            this.h = str != null ? m7.a(str, "", true, false, false, false) : null;
            return this;
        }

        public a d(String str, @Nullable String str2) {
            o(str);
            b(str, str2);
            return this;
        }

        public a d(String str) {
            if (str != null) {
                return a(str, false);
            }
            throw new NullPointerException("pathSegments == null");
        }

        public a c(String str, @Nullable String str2) {
            n(str);
            a(str, str2);
            return this;
        }

        public a c(String str) {
            if (str == null) {
                throw new NullPointerException("pathSegment == null");
            }
            a(str, 0, str.length(), false, false);
            return this;
        }

        public a c() {
            int size = this.f.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f.set(i2, m7.a(this.f.get(i2), "[]", true, true, false, true));
            }
            List<String> list = this.g;
            if (list != null) {
                int size2 = list.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    String str = this.g.get(i3);
                    if (str != null) {
                        this.g.set(i3, m7.a(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            String str2 = this.h;
            if (str2 != null) {
                this.h = m7.a(str2, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        public a b(String str, @Nullable String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(m7.a(str, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true));
            this.g.add(str2 != null ? m7.a(str2, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true) : null);
            return this;
        }

        public a b(String str) {
            if (str != null) {
                return a(str, true);
            }
            throw new NullPointerException("encodedPathSegments == null");
        }

        public a b(int i2, String str) {
            if (str == null) {
                throw new NullPointerException("pathSegment == null");
            }
            String a2 = m7.a(str, 0, str.length(), " \"<>^`{}|/\\?#", false, false, false, true, null);
            if (!r(a2) && !s(a2)) {
                this.f.set(i2, a2);
                return this;
            }
            throw new IllegalArgumentException("unexpected path segment: " + str);
        }

        public a b(int i2) {
            this.f.remove(i2);
            if (this.f.isEmpty()) {
                this.f.add("");
            }
            return this;
        }

        public int b() {
            int i2 = this.e;
            return i2 != -1 ? i2 : m7.e(this.f5380a);
        }

        public m7 a() {
            if (this.f5380a == null) {
                throw new IllegalStateException("scheme == null");
            }
            if (this.d != null) {
                return new m7(this);
            }
            throw new IllegalStateException("host == null");
        }

        public a a(String str, @Nullable String str2) {
            if (str == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(m7.a(str, " \"'<>#&=", true, false, true, true));
            this.g.add(str2 != null ? m7.a(str2, " \"'<>#&=", true, false, true, true) : null);
            return this;
        }

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            a(str, 0, str.length(), false, true);
            return this;
        }

        public a a(@Nullable m7 m7Var, String str) {
            int a2;
            int i2;
            int b = f8.b(str, 0, str.length());
            int c = f8.c(str, b, str.length());
            int e = e(str, b, c);
            if (e != -1) {
                if (str.regionMatches(true, b, "https:", 0, 6)) {
                    this.f5380a = ProxyConfig.MATCH_HTTPS;
                    b += 6;
                } else {
                    if (!str.regionMatches(true, b, "http:", 0, 5)) {
                        throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + str.substring(0, e) + "'");
                    }
                    this.f5380a = "http";
                    b += 5;
                }
            } else {
                if (m7Var == null) {
                    throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
                }
                this.f5380a = m7Var.f5379a;
            }
            int f = f(str, b, c);
            char c2 = '?';
            char c3 = '#';
            if (f >= 2 || m7Var == null || !m7Var.f5379a.equals(this.f5380a)) {
                boolean z = false;
                boolean z2 = false;
                int i3 = b + f;
                while (true) {
                    a2 = f8.a(str, i3, c, "@/\\?#");
                    char charAt = a2 != c ? str.charAt(a2) : (char) 65535;
                    if (charAt == 65535 || charAt == c3 || charAt == '/' || charAt == '\\' || charAt == c2) {
                        break;
                    }
                    if (charAt == '@') {
                        if (z) {
                            i2 = a2;
                            this.c += "%40" + m7.a(str, i3, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                        } else {
                            int a3 = f8.a(str, i3, a2, ':');
                            i2 = a2;
                            String a4 = m7.a(str, i3, a3, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                            if (z2) {
                                a4 = this.b + "%40" + a4;
                            }
                            this.b = a4;
                            if (a3 != i2) {
                                this.c = m7.a(str, a3 + 1, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                z = true;
                            }
                            z2 = true;
                        }
                        i3 = i2 + 1;
                        c2 = '?';
                        c3 = '#';
                    }
                }
                int c4 = c(str, i3, a2);
                int i4 = c4 + 1;
                if (i4 < a2) {
                    this.d = a(str, i3, c4);
                    int b2 = b(str, i4, a2);
                    this.e = b2;
                    if (b2 == -1) {
                        throw new IllegalArgumentException("Invalid URL port: \"" + str.substring(i4, a2) + '\"');
                    }
                } else {
                    this.d = a(str, i3, c4);
                    this.e = m7.e(this.f5380a);
                }
                if (this.d == null) {
                    throw new IllegalArgumentException("Invalid URL host: \"" + str.substring(i3, c4) + '\"');
                }
                b = a2;
            } else {
                this.b = m7Var.f();
                this.c = m7Var.b();
                this.d = m7Var.d;
                this.e = m7Var.e;
                this.f.clear();
                this.f.addAll(m7Var.d());
                if (b == c || str.charAt(b) == '#') {
                    h(m7Var.e());
                }
            }
            int a5 = f8.a(str, b, c, "?#");
            d(str, b, a5);
            if (a5 < c && str.charAt(a5) == '?') {
                int a6 = f8.a(str, a5, c, '#');
                this.g = m7.h(m7.a(str, a5 + 1, a6, " \"'<>#", true, false, true, true, null));
                a5 = a6;
            }
            if (a5 < c && str.charAt(a5) == '#') {
                this.h = m7.a(str, 1 + a5, c, "", true, false, false, false, null);
            }
            return this;
        }

        public a a(int i2, String str) {
            if (str == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            String a2 = m7.a(str, 0, str.length(), " \"<>^`{}|/\\?#", true, false, false, true, null);
            this.f.set(i2, a2);
            if (!r(a2) && !s(a2)) {
                return this;
            }
            throw new IllegalArgumentException("unexpected path segment: " + str);
        }

        public a a(int i2) {
            if (i2 > 0 && i2 <= 65535) {
                this.e = i2;
                return this;
            }
            throw new IllegalArgumentException("unexpected port: " + i2);
        }

        private void t(String str) {
            for (int size = this.g.size() - 2; size >= 0; size -= 2) {
                if (str.equals(this.g.get(size))) {
                    this.g.remove(size + 1);
                    this.g.remove(size);
                    if (this.g.isEmpty()) {
                        this.g = null;
                        return;
                    }
                }
            }
        }

        private boolean s(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private boolean r(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        public static int f(String str, int i2, int i3) {
            int i4 = 0;
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i4++;
                i2++;
            }
            return i4;
        }

        public static int e(String str, int i2, int i3) {
            if (i3 - i2 < 2) {
                return -1;
            }
            char charAt = str.charAt(i2);
            if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                while (true) {
                    i2++;
                    if (i2 >= i3) {
                        break;
                    }
                    char charAt2 = str.charAt(i2);
                    if (charAt2 < 'a' || charAt2 > 'z') {
                        if (charAt2 < 'A' || charAt2 > 'Z') {
                            if (charAt2 < '0' || charAt2 > '9') {
                                if (charAt2 != '+' && charAt2 != '-' && charAt2 != '.') {
                                    if (charAt2 == ':') {
                                        return i2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        private void d(String str, int i2, int i3) {
            if (i2 == i3) {
                return;
            }
            char charAt = str.charAt(i2);
            if (charAt == '/' || charAt == '\\') {
                this.f.clear();
                this.f.add("");
                i2++;
            } else {
                List<String> list = this.f;
                list.set(list.size() - 1, "");
            }
            while (true) {
                int i4 = i2;
                if (i4 >= i3) {
                    return;
                }
                i2 = f8.a(str, i4, i3, "/\\");
                boolean z = i2 < i3;
                a(str, i4, i2, z, true);
                if (z) {
                    i2++;
                }
            }
        }

        private void d() {
            if (!this.f.remove(r0.size() - 1).isEmpty() || this.f.isEmpty()) {
                this.f.add("");
            } else {
                this.f.set(r0.size() - 1, "");
            }
        }

        public static int c(String str, int i2, int i3) {
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt == ':') {
                    return i2;
                }
                if (charAt == '[') {
                    do {
                        i2++;
                        if (i2 < i3) {
                        }
                    } while (str.charAt(i2) != ']');
                }
                i2++;
            }
            return i3;
        }

        public static int b(String str, int i2, int i3) {
            try {
                int parseInt = Integer.parseInt(m7.a(str, i2, i3, "", false, false, false, true, null));
                if (parseInt <= 0 || parseInt > 65535) {
                    return -1;
                }
                return parseInt;
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        private void a(String str, int i2, int i3, boolean z, boolean z2) {
            String a2 = m7.a(str, i2, i3, " \"<>^`{}|/\\?#", z2, false, false, true, null);
            if (r(a2)) {
                return;
            }
            if (s(a2)) {
                d();
                return;
            }
            if (this.f.get(r11.size() - 1).isEmpty()) {
                this.f.set(r11.size() - 1, a2);
            } else {
                this.f.add(a2);
            }
            if (z) {
                this.f.add("");
            }
        }

        @Nullable
        public static String a(String str, int i2, int i3) {
            return f8.a(m7.a(str, i2, i3, false));
        }

        private a a(String str, boolean z) {
            int i2 = 0;
            do {
                int a2 = f8.a(str, i2, str.length(), "/\\");
                a(str, i2, a2, a2 < str.length(), z);
                i2 = a2 + 1;
            } while (i2 <= str.length());
            return this;
        }

        public a() {
            ArrayList arrayList = new ArrayList();
            this.f = arrayList;
            arrayList.add("");
        }
    }

    public int q() {
        List<String> list = this.g;
        if (list != null) {
            return list.size() / 2;
        }
        return 0;
    }

    public Set<String> p() {
        if (this.g == null) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = this.g.size();
        for (int i = 0; i < size; i += 2) {
            linkedHashSet.add(this.g.get(i));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    @Nullable
    public String o() {
        if (this.g == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        a(sb, this.g);
        return sb.toString();
    }

    public int n() {
        return this.e;
    }

    public int m() {
        return this.f.size();
    }

    public List<String> l() {
        return this.f;
    }

    public String k() {
        return this.c;
    }

    public a j() {
        a aVar = new a();
        aVar.f5380a = this.f5379a;
        aVar.b = f();
        aVar.c = b();
        aVar.d = this.d;
        aVar.e = this.e != e(this.f5379a) ? this.e : -1;
        aVar.f.clear();
        aVar.f.addAll(d());
        aVar.h(e());
        aVar.h = a();
        return aVar;
    }

    public boolean i() {
        return this.f5379a.equals(ProxyConfig.MATCH_HTTPS);
    }

    public int hashCode() {
        return this.i.hashCode();
    }

    public String h() {
        return this.d;
    }

    @Nullable
    public String g() {
        return this.h;
    }

    public String f() {
        if (this.b.isEmpty()) {
            return "";
        }
        int length = this.f5379a.length() + 3;
        String str = this.i;
        return this.i.substring(length, f8.a(str, length, str.length(), ":@"));
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof m7) && ((m7) obj).i.equals(this.i);
    }

    @Nullable
    public String e() {
        if (this.g == null) {
            return null;
        }
        int indexOf = this.i.indexOf(63) + 1;
        String str = this.i;
        return this.i.substring(indexOf, f8.a(str, indexOf, str.length(), '#'));
    }

    public List<String> d() {
        int indexOf = this.i.indexOf(47, this.f5379a.length() + 3);
        String str = this.i;
        int a2 = f8.a(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < a2) {
            int i = indexOf + 1;
            int a3 = f8.a(this.i, i, a2, '/');
            arrayList.add(this.i.substring(i, a3));
            indexOf = a3;
        }
        return arrayList;
    }

    @Nullable
    public m7 d(String str) {
        a a2 = a(str);
        if (a2 != null) {
            return a2.a();
        }
        return null;
    }

    public List<String> c(String str) {
        if (this.g == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = this.g.size();
        for (int i = 0; i < size; i += 2) {
            if (str.equals(this.g.get(i))) {
                arrayList.add(this.g.get(i + 1));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String c() {
        int indexOf = this.i.indexOf(47, this.f5379a.length() + 3);
        String str = this.i;
        return this.i.substring(indexOf, f8.a(str, indexOf, str.length(), "?#"));
    }

    @Nullable
    public String b(String str) {
        List<String> list = this.g;
        if (list == null) {
            return null;
        }
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            if (str.equals(this.g.get(i))) {
                return this.g.get(i + 1);
            }
        }
        return null;
    }

    public String b(int i) {
        List<String> list = this.g;
        if (list != null) {
            return list.get((i * 2) + 1);
        }
        throw new IndexOutOfBoundsException();
    }

    public String b() {
        if (this.c.isEmpty()) {
            return "";
        }
        int indexOf = this.i.indexOf(58, this.f5379a.length() + 3);
        return this.i.substring(indexOf + 1, this.i.indexOf(64));
    }

    public String a(int i) {
        List<String> list = this.g;
        if (list != null) {
            return list.get(i * 2);
        }
        throw new IndexOutOfBoundsException();
    }

    @Nullable
    public String a() {
        if (this.h == null) {
            return null;
        }
        return this.i.substring(this.i.indexOf(35) + 1);
    }

    @Nullable
    public a a(String str) {
        try {
            return new a().a(this, str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static List<String> h(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i, indexOf));
                str2 = null;
            } else {
                arrayList.add(str.substring(i, indexOf2));
                str2 = str.substring(indexOf2 + 1, indexOf);
            }
            arrayList.add(str2);
            i = indexOf + 1;
        }
        return arrayList;
    }

    @Nullable
    public static m7 g(String str) {
        try {
            return f(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static m7 f(String str) {
        return new a().a((m7) null, str).a();
    }

    public static int e(String str) {
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals(ProxyConfig.MATCH_HTTPS)) {
            return g2.n;
        }
        return -1;
    }

    public static void b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append('/');
            sb.append(list.get(i));
        }
    }

    public static boolean a(String str, int i, int i2) {
        int i3 = i + 2;
        return i3 < i2 && str.charAt(i) == '%' && f8.a(str.charAt(i + 1)) != -1 && f8.a(str.charAt(i3)) != -1;
    }

    public static void a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = list.get(i);
            String str2 = list.get(i + 1);
            if (i > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    public static void a(bb bbVar, String str, int i, int i2, boolean z) {
        int i3;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt != 37 || (i3 = i + 2) >= i2) {
                if (codePointAt == 43 && z) {
                    bbVar.writeByte(32);
                }
                bbVar.c(codePointAt);
            } else {
                int a2 = f8.a(str.charAt(i + 1));
                int a3 = f8.a(str.charAt(i3));
                if (a2 != -1 && a3 != -1) {
                    bbVar.writeByte((a2 << 4) + a3);
                    i = i3;
                }
                bbVar.c(codePointAt);
            }
            i += Character.charCount(codePointAt);
        }
    }

    public static void a(bb bbVar, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, @Nullable Charset charset) {
        bb bbVar2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!z || (codePointAt != 9 && codePointAt != 10 && codePointAt != 12 && codePointAt != 13)) {
                if (codePointAt == 43 && z3) {
                    bbVar.a(z ? Marker.ANY_NON_NULL_MARKER : "%2B");
                } else if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && (!z || (z2 && !a(str, i, i2)))))) {
                    if (bbVar2 == null) {
                        bbVar2 = new bb();
                    }
                    if (charset == null || charset.equals(StandardCharsets.UTF_8)) {
                        bbVar2.c(codePointAt);
                    } else {
                        bbVar2.a(str, i, Character.charCount(codePointAt) + i, charset);
                    }
                    while (!bbVar2.i()) {
                        try {
                            byte readByte = bbVar2.readByte();
                            bbVar.writeByte(37);
                            char[] cArr = j;
                            bbVar.writeByte((int) cArr[((readByte & 255) >> 4) & 15]);
                            bbVar.writeByte((int) cArr[readByte & BaseType.Obj]);
                        } catch (EOFException e) {
                            throw new AssertionError(e);
                        }
                    }
                } else {
                    bbVar.c(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    private List<String> a(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            arrayList.add(str != null ? a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static String a(String str, boolean z) {
        return a(str, 0, str.length(), z);
    }

    public static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, @Nullable Charset charset) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, charset);
    }

    public static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, null);
    }

    public static String a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '%' || (charAt == '+' && z)) {
                bb bbVar = new bb();
                bbVar.a(str, i, i3);
                a(bbVar, str, i3, i2, z);
                return bbVar.o();
            }
        }
        return str.substring(i, i2);
    }

    public static String a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, @Nullable Charset charset) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt >= 32 && codePointAt != 127 && (codePointAt < 128 || !z4)) {
                if (str2.indexOf(codePointAt) == -1 && ((codePointAt != 37 || (z && (!z2 || a(str, i3, i2)))) && (codePointAt != 43 || !z3))) {
                    i3 += Character.charCount(codePointAt);
                }
            }
            bb bbVar = new bb();
            bbVar.a(str, i, i3);
            a(bbVar, str, i3, i2, str2, z, z2, z3, z4, charset);
            return bbVar.o();
        }
        return str.substring(i, i2);
    }

    @Nullable
    public static m7 a(URL url) {
        return g(url.toString());
    }

    @Nullable
    public static m7 a(URI uri) {
        return g(uri.toString());
    }

    public m7(a aVar) {
        this.f5379a = aVar.f5380a;
        this.b = a(aVar.b, false);
        this.c = a(aVar.c, false);
        this.d = aVar.d;
        this.e = aVar.b();
        this.f = a(aVar.f, false);
        List<String> list = aVar.g;
        this.g = list != null ? a(list, true) : null;
        String str = aVar.h;
        this.h = str != null ? a(str, false) : null;
        this.i = aVar.toString();
    }
}
