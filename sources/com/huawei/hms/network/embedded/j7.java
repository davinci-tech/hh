package com.huawei.hms.network.embedded;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class j7 {

    /* renamed from: a, reason: collision with root package name */
    public final String[] f5326a;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int d = d();
        for (int i = 0; i < d; i++) {
            String a2 = a(i);
            String b = b(i);
            sb.append(a2);
            sb.append(": ");
            if (f8.d(a2)) {
                b = "";
            }
            sb.append(b);
            sb.append("\n");
        }
        return sb.toString();
    }

    public int hashCode() {
        return Arrays.hashCode(this.f5326a);
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof j7) && Arrays.equals(((j7) obj).f5326a, this.f5326a);
    }

    public Map<String, List<String>> e() {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        int d = d();
        for (int i = 0; i < d; i++) {
            String lowerCase = a(i).toLowerCase(Locale.US);
            List list = (List) treeMap.get(lowerCase);
            if (list == null) {
                list = new ArrayList(2);
                treeMap.put(lowerCase, list);
            }
            list.add(b(i));
        }
        return treeMap;
    }

    public List<String> d(String str) {
        int d = d();
        ArrayList arrayList = null;
        for (int i = 0; i < d; i++) {
            if (str.equalsIgnoreCase(a(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(b(i));
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final List<String> f5327a = new ArrayList(20);

        public a d(String str, String str2) {
            j7.e(str);
            j7.a(str2, str);
            d(str);
            b(str, str2);
            return this;
        }

        public a d(String str) {
            int i = 0;
            while (i < this.f5327a.size()) {
                if (str.equalsIgnoreCase(this.f5327a.get(i))) {
                    this.f5327a.remove(i);
                    this.f5327a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        @Nullable
        public String c(String str) {
            for (int size = this.f5327a.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase(this.f5327a.get(size))) {
                    return this.f5327a.get(size + 1);
                }
            }
            return null;
        }

        public a c(String str, String str2) {
            j7.e(str);
            return b(str, str2);
        }

        public a b(String str, Date date) {
            if (date != null) {
                d(str, h9.a(date));
                return this;
            }
            throw new NullPointerException("value for name " + str + " == null");
        }

        public a b(String str, Instant instant) {
            if (instant != null) {
                return b(str, new Date(instant.toEpochMilli()));
            }
            throw new NullPointerException("value for name " + str + " == null");
        }

        public a b(String str, String str2) {
            this.f5327a.add(str);
            this.f5327a.add(str2.trim());
            return this;
        }

        public a b(String str) {
            int indexOf = str.indexOf(":", 1);
            return indexOf != -1 ? b(str.substring(0, indexOf), str.substring(indexOf + 1)) : str.startsWith(":") ? b("", str.substring(1)) : b("", str);
        }

        public j7 a() {
            return new j7(this);
        }

        public a a(String str, Date date) {
            if (date != null) {
                a(str, h9.a(date));
                return this;
            }
            throw new NullPointerException("value for name " + str + " == null");
        }

        public a a(String str, Instant instant) {
            if (instant != null) {
                return a(str, new Date(instant.toEpochMilli()));
            }
            throw new NullPointerException("value for name " + str + " == null");
        }

        public a a(String str, String str2) {
            j7.e(str);
            j7.a(str2, str);
            return b(str, str2);
        }

        public a a(String str) {
            int indexOf = str.indexOf(":");
            if (indexOf != -1) {
                return a(str.substring(0, indexOf).trim(), str.substring(indexOf + 1));
            }
            throw new IllegalArgumentException("Unexpected header: " + str);
        }

        public a a(j7 j7Var) {
            int d = j7Var.d();
            for (int i = 0; i < d; i++) {
                b(j7Var.a(i), j7Var.b(i));
            }
            return this;
        }
    }

    public int d() {
        return this.f5326a.length / 2;
    }

    @Nullable
    public Instant c(String str) {
        Date b = b(str);
        if (b != null) {
            return b.toInstant();
        }
        return null;
    }

    public a c() {
        a aVar = new a();
        Collections.addAll(aVar.f5327a, this.f5326a);
        return aVar;
    }

    public Set<String> b() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int d = d();
        for (int i = 0; i < d; i++) {
            treeSet.add(a(i));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    @Nullable
    public Date b(String str) {
        String a2 = a(str);
        if (a2 != null) {
            return h9.a(a2);
        }
        return null;
    }

    public String b(int i) {
        return this.f5326a[(i * 2) + 1];
    }

    @Nullable
    public String a(String str) {
        return a(this.f5326a, str);
    }

    public String a(int i) {
        return this.f5326a[i * 2];
    }

    public long a() {
        String[] strArr = this.f5326a;
        long length = strArr.length * 2;
        for (int i = 0; i < strArr.length; i++) {
            length += this.f5326a[i].length();
        }
        return length;
    }

    public static void e(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt <= ' ' || charAt >= 127) {
                throw new IllegalArgumentException(f8.a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
            }
        }
    }

    public static void a(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("value for name " + str2 + " == null");
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt <= 31 && charAt != '\t') || charAt >= 127) {
                Object[] objArr = new Object[4];
                objArr[0] = Integer.valueOf(charAt);
                objArr[1] = Integer.valueOf(i);
                objArr[2] = str2;
                if (f8.d(str2)) {
                    str = "";
                }
                objArr[3] = str;
                throw new IllegalArgumentException(f8.a("Unexpected char %#04x at %d in %s value: %s", objArr));
            }
        }
    }

    @Nullable
    public static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static j7 a(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("namesAndValues == null");
        }
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        for (int i = 0; i < strArr2.length; i++) {
            String str = strArr2[i];
            if (str == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i] = str.trim();
        }
        for (int i2 = 0; i2 < strArr2.length; i2 += 2) {
            String str2 = strArr2[i2];
            String str3 = strArr2[i2 + 1];
            e(str2);
            a(str3, str2);
        }
        return new j7(strArr2);
    }

    public static j7 a(Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException("headers == null");
        }
        String[] strArr = new String[map.size() * 2];
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            String trim = entry.getKey().trim();
            String trim2 = entry.getValue().trim();
            e(trim);
            a(trim2, trim);
            strArr[i] = trim;
            strArr[i + 1] = trim2;
            i += 2;
        }
        return new j7(strArr);
    }

    public j7(String[] strArr) {
        this.f5326a = strArr;
    }

    public j7(a aVar) {
        List<String> list = aVar.f5327a;
        this.f5326a = (String[]) list.toArray(new String[list.size()]);
    }
}
