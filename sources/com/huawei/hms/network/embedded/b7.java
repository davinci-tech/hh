package com.huawei.hms.network.embedded;

import androidx.core.location.LocationRequestCompat;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class b7 {
    public static final Pattern j = Pattern.compile("(\\d{2,4})[^\\d]*");
    public static final Pattern k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    public static final Pattern l = Pattern.compile("(\\d{1,2})[^\\d]*");
    public static final Pattern m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* renamed from: a, reason: collision with root package name */
    public final String f5187a;
    public final String b;
    public final long c;
    public final String d;
    public final String e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;

    public String toString() {
        return a(false);
    }

    public String i() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = this.f5187a.hashCode();
        int hashCode2 = this.b.hashCode();
        int hashCode3 = this.d.hashCode();
        int hashCode4 = this.e.hashCode();
        long j2 = this.c;
        return ((((((((((((((((hashCode + 527) * 31) + hashCode2) * 31) + hashCode3) * 31) + hashCode4) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31) + (!this.f ? 1 : 0)) * 31) + (!this.g ? 1 : 0)) * 31) + (!this.h ? 1 : 0)) * 31) + (!this.i ? 1 : 0);
    }

    public boolean h() {
        return this.f;
    }

    public boolean g() {
        return this.h;
    }

    public String f() {
        return this.e;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof b7)) {
            return false;
        }
        b7 b7Var = (b7) obj;
        return b7Var.f5187a.equals(this.f5187a) && b7Var.b.equals(this.b) && b7Var.d.equals(this.d) && b7Var.e.equals(this.e) && b7Var.c == this.c && b7Var.f == this.f && b7Var.g == this.g && b7Var.h == this.h && b7Var.i == this.i;
    }

    public String e() {
        return this.f5187a;
    }

    public boolean d() {
        return this.g;
    }

    public boolean c() {
        return this.i;
    }

    public long b() {
        return this.c;
    }

    public boolean a(m7 m7Var) {
        if ((this.i ? m7Var.h().equals(this.d) : a(m7Var.h(), this.d)) && b(m7Var, this.e)) {
            return !this.f || m7Var.i();
        }
        return false;
    }

    public String a(boolean z) {
        String a2;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5187a);
        sb.append('=');
        sb.append(this.b);
        if (this.h) {
            if (this.c == Long.MIN_VALUE) {
                a2 = "; max-age=0";
            } else {
                sb.append("; expires=");
                a2 = h9.a(new Date(this.c));
            }
            sb.append(a2);
        }
        if (!this.i) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.d);
        }
        sb.append("; path=");
        sb.append(this.e);
        if (this.f) {
            sb.append("; secure");
        }
        if (this.g) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public String a() {
        return this.d;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        @Nullable
        public String f5188a;

        @Nullable
        public String b;

        @Nullable
        public String d;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public long c = 253402300799999L;
        public String e = "/";

        public a e(String str) {
            if (str == null) {
                throw new NullPointerException("value == null");
            }
            if (!str.trim().equals(str)) {
                throw new IllegalArgumentException("value is not trimmed");
            }
            this.b = str;
            return this;
        }

        public a d(String str) {
            if (!str.startsWith("/")) {
                throw new IllegalArgumentException("path must start with '/'");
            }
            this.e = str;
            return this;
        }

        public a c(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (!str.trim().equals(str)) {
                throw new IllegalArgumentException("name is not trimmed");
            }
            this.f5188a = str;
            return this;
        }

        public a c() {
            this.f = true;
            return this;
        }

        public a b(String str) {
            return a(str, true);
        }

        public a b() {
            this.g = true;
            return this;
        }

        public b7 a() {
            return new b7(this);
        }

        public a a(String str) {
            return a(str, false);
        }

        public a a(long j) {
            if (j <= 0) {
                j = Long.MIN_VALUE;
            }
            if (j > 253402300799999L) {
                j = 253402300799999L;
            }
            this.c = j;
            this.h = true;
            return this;
        }

        private a a(String str, boolean z) {
            if (str == null) {
                throw new NullPointerException("domain == null");
            }
            String a2 = f8.a(str);
            if (a2 != null) {
                this.d = a2;
                this.i = z;
                return this;
            }
            throw new IllegalArgumentException("unexpected domain: " + str);
        }
    }

    public static boolean b(m7 m7Var, String str) {
        String c = m7Var.c();
        if (c.equals(str)) {
            return true;
        }
        if (c.startsWith(str)) {
            return str.endsWith("/") || c.charAt(str.length()) == '/';
        }
        return false;
    }

    public static long b(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e) {
            if (!str.matches("-?\\d+")) {
                throw e;
            }
            if (str.startsWith(Constants.LINK)) {
                return Long.MIN_VALUE;
            }
            return LocationRequestCompat.PASSIVE_INTERVAL;
        }
    }

    public static boolean a(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !f8.e(str);
    }

    public static List<b7> a(m7 m7Var, j7 j7Var) {
        List<String> d = j7Var.d("Set-Cookie");
        int size = d.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            b7 a2 = a(m7Var, d.get(i));
            if (a2 != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(a2);
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public static String a(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String a2 = f8.a(str);
        if (a2 != null) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    @Nullable
    public static b7 a(m7 m7Var, String str) {
        return a(System.currentTimeMillis(), m7Var, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f2  */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.network.embedded.b7 a(long r23, com.huawei.hms.network.embedded.m7 r25, java.lang.String r26) {
        /*
            Method dump skipped, instructions count: 313
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.b7.a(long, com.huawei.hms.network.embedded.m7, java.lang.String):com.huawei.hms.network.embedded.b7");
    }

    public static long a(String str, int i, int i2) {
        int a2 = a(str, i, i2, false);
        Matcher matcher = m.matcher(str);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        while (a2 < i2) {
            int a3 = a(str, a2 + 1, i2, true);
            matcher.region(a2, a3);
            if (i4 == -1 && matcher.usePattern(m).matches()) {
                i4 = Integer.parseInt(matcher.group(1));
                i7 = Integer.parseInt(matcher.group(2));
                i8 = Integer.parseInt(matcher.group(3));
            } else if (i5 == -1 && matcher.usePattern(l).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
            } else {
                if (i6 == -1) {
                    Pattern pattern = k;
                    if (matcher.usePattern(pattern).matches()) {
                        i6 = pattern.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
                    }
                }
                if (i3 == -1 && matcher.usePattern(j).matches()) {
                    i3 = Integer.parseInt(matcher.group(1));
                }
            }
            a2 = a(str, a3 + 1, i2, false);
        }
        if (i3 >= 70 && i3 <= 99) {
            i3 += 1900;
        }
        if (i3 >= 0 && i3 <= 69) {
            i3 += 2000;
        }
        if (i3 < 1601) {
            throw new IllegalArgumentException();
        }
        if (i6 == -1) {
            throw new IllegalArgumentException();
        }
        if (i5 < 1 || i5 > 31) {
            throw new IllegalArgumentException();
        }
        if (i4 < 0 || i4 > 23) {
            throw new IllegalArgumentException();
        }
        if (i7 < 0 || i7 > 59) {
            throw new IllegalArgumentException();
        }
        if (i8 < 0 || i8 > 59) {
            throw new IllegalArgumentException();
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(f8.i);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.set(1, i3);
        gregorianCalendar.set(2, i6 - 1);
        gregorianCalendar.set(5, i5);
        gregorianCalendar.set(11, i4);
        gregorianCalendar.set(12, i7);
        gregorianCalendar.set(13, i8);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTimeInMillis();
    }

    public static int a(String str, int i, int i2, boolean z) {
        while (i < i2) {
            char charAt = str.charAt(i);
            if (((charAt < ' ' && charAt != '\t') || charAt >= 127 || (charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == ':'))) == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public b7(String str, String str2, long j2, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f5187a = str;
        this.b = str2;
        this.c = j2;
        this.d = str3;
        this.e = str4;
        this.f = z;
        this.g = z2;
        this.i = z3;
        this.h = z4;
    }

    public b7(a aVar) {
        String str = aVar.f5188a;
        if (str == null) {
            throw new NullPointerException("builder.name == null");
        }
        String str2 = aVar.b;
        if (str2 == null) {
            throw new NullPointerException("builder.value == null");
        }
        String str3 = aVar.d;
        if (str3 == null) {
            throw new NullPointerException("builder.domain == null");
        }
        this.f5187a = str;
        this.b = str2;
        this.c = aVar.c;
        this.d = str3;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
    }
}
