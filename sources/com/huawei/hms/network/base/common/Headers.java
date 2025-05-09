package com.huawei.hms.network.base.common;

import com.huawei.hms.framework.common.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes4.dex */
public final class Headers {
    private static final String b = "\n";

    /* renamed from: a, reason: collision with root package name */
    private final String[] f5118a;

    public List<String> values(String str) {
        int size = size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase(name(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(value(i));
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public String value(int i) {
        return this.f5118a[(i * 2) + 1];
    }

    public String toString() {
        return super.toString();
    }

    public Map<String, List<String>> toMultimap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = size();
        for (int i = 0; i < size; i++) {
            String name = name(i);
            List list = (List) linkedHashMap.get(name);
            if (list == null) {
                list = new ArrayList(2);
                linkedHashMap.put(name, list);
            }
            list.add(value(i));
        }
        return linkedHashMap;
    }

    public int size() {
        return this.f5118a.length / 2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        Collections.addAll(builder.f5119a, this.f5118a);
        return builder;
    }

    public Set<String> names() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i = 0; i < size; i++) {
            treeSet.add(name(i));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        final List<String> f5119a = new ArrayList(20);

        public Builder set(String str, String str2) {
            a(str, str2);
            removeAll(str);
            addLenient(str, str2);
            return this;
        }

        public Builder removeAll(String str) {
            int i = 0;
            while (i < this.f5119a.size()) {
                if (str.equalsIgnoreCase(this.f5119a.get(i))) {
                    this.f5119a.remove(i);
                    this.f5119a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public String get(String str) {
            for (int size = this.f5119a.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase(this.f5119a.get(size))) {
                    return this.f5119a.get(size + 1);
                }
            }
            return null;
        }

        public Headers build() {
            return new Headers(this);
        }

        public Builder addLenient(String str, String str2) {
            this.f5119a.add(str);
            this.f5119a.add(str2.trim());
            return this;
        }

        public Builder addLenient(String str) {
            int indexOf = str.indexOf(":", 1);
            return -1 != indexOf ? addLenient(str.substring(0, indexOf), str.substring(indexOf + 1)) : str.startsWith(":") ? addLenient("", str.substring(1)) : addLenient("", str);
        }

        public Builder add(String str, String str2) {
            a(str, str2);
            return addLenient(str, str2);
        }

        public Builder add(String str) {
            int indexOf = str.indexOf(":");
            if (indexOf != -1) {
                return add(str.substring(0, indexOf).trim(), str.substring(indexOf + 1));
            }
            throw new IllegalArgumentException("Unexpected header: " + str);
        }

        private void a(String str, String str2) {
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
                    throw new IllegalArgumentException(StringUtils.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
                }
            }
            if (str2 == null) {
                throw new NullPointerException("value for name " + str + " == null");
            }
            int length2 = str2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                char charAt2 = str2.charAt(i2);
                if ((charAt2 <= 31 && charAt2 != '\t') || charAt2 >= 127) {
                    throw new IllegalArgumentException(StringUtils.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2));
                }
            }
        }
    }

    public String name(int i) {
        return this.f5118a[i * 2];
    }

    public int hashCode() {
        return Arrays.hashCode(this.f5118a);
    }

    public String get(String str) {
        return a(this.f5118a, str);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Headers) && Arrays.equals(((Headers) obj).f5118a, this.f5118a);
    }

    public long byteCount() {
        String[] strArr = this.f5118a;
        long length = strArr.length * 2;
        for (int i = 0; i < strArr.length; i++) {
            length += this.f5118a[i].length();
        }
        return length;
    }

    public static Headers of(String... strArr) {
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
                throw new IllegalArgumentException("header mustn't be null");
            }
            strArr2[i] = str.trim();
        }
        for (int i2 = 0; i2 < strArr2.length; i2 += 2) {
            String str2 = strArr2[i2];
            String str3 = strArr2[i2 + 1];
            if (str2.length() == 0 || str2.indexOf(0) != -1 || str3.indexOf(0) != -1) {
                throw new IllegalArgumentException("Unexpected header: " + str2 + ": " + str3);
            }
        }
        return new Headers(strArr2);
    }

    public static Headers of(Map<String, List<String>> map) {
        if (map == null) {
            throw new NullPointerException("headers == null");
        }
        ArrayList arrayList = new ArrayList(map.size() * 2);
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            String trim = entry.getKey().trim();
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                String trim2 = it.next().trim();
                if (trim.length() == 0 || trim.indexOf(0) != -1 || trim2.indexOf(0) != -1) {
                    throw new IllegalArgumentException("Unexpected header: " + trim + ": " + trim2);
                }
                arrayList.add(trim);
                arrayList.add(trim2);
            }
        }
        return new Headers((String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    private static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    private Headers(String[] strArr) {
        this.f5118a = strArr;
    }

    private Headers(Builder builder) {
        List<String> list = builder.f5119a;
        this.f5118a = (String[]) list.toArray(new String[list.size()]);
    }
}
