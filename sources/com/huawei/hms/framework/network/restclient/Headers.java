package com.huawei.hms.framework.network.restclient;

import com.huawei.hms.network.base.common.Headers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Deprecated
/* loaded from: classes.dex */
public final class Headers {
    private com.huawei.hms.network.base.common.Headers headers;

    private Headers(com.huawei.hms.network.base.common.Headers headers) {
        this.headers = headers;
    }

    public static Headers of(String... strArr) {
        return new Headers(com.huawei.hms.network.base.common.Headers.of(strArr));
    }

    public static String[] toArray(Map<String, List<String>> map) {
        if (map == null) {
            throw new IllegalArgumentException("Headers cannot be null");
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
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static Headers of(Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException("headers == null");
        }
        String[] strArr = new String[map.size() * 2];
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            strArr[i] = entry.getKey().trim();
            strArr[i + 1] = entry.getValue().trim();
            i += 2;
        }
        return new Headers(com.huawei.hms.network.base.common.Headers.of(strArr));
    }

    public String get(String str) {
        return this.headers.get(str);
    }

    public int size() {
        return this.headers.size();
    }

    public String name(int i) {
        return this.headers.name(i);
    }

    public String value(int i) {
        return this.headers.value(i);
    }

    public Set<String> names() {
        return this.headers.names();
    }

    public List<String> values(String str) {
        return this.headers.values(str);
    }

    public long byteCount() {
        return this.headers.byteCount();
    }

    public Builder newBuilder() {
        return new Builder(this.headers.newBuilder());
    }

    public boolean equals(Object obj) {
        return this.headers.equals(obj);
    }

    public int hashCode() {
        return this.headers.hashCode();
    }

    public String toString() {
        return this.headers.toString();
    }

    public Map<String, List<String>> toMultimap() {
        return this.headers.toMultimap();
    }

    @Deprecated
    /* loaded from: classes4.dex */
    public static final class Builder {
        private Headers.Builder builder;

        public Builder() {
            this.builder = new Headers.Builder();
        }

        public Builder(Headers.Builder builder) {
            new Headers.Builder();
            this.builder = builder;
        }

        public Builder addLenient(String str) {
            this.builder.addLenient(str);
            return this;
        }

        public Builder add(String str) {
            this.builder.add(str);
            return this;
        }

        public Builder add(String str, String str2) {
            this.builder.add(str, str2);
            return this;
        }

        public Builder addLenient(String str, String str2) {
            this.builder.addLenient(str, str2);
            return this;
        }

        public Builder removeAll(String str) {
            this.builder.removeAll(str);
            return this;
        }

        public Builder set(String str, String str2) {
            this.builder.set(str, str2);
            return this;
        }

        public String get(String str) {
            return this.builder.get(str);
        }

        public Headers build() {
            return new Headers(this.builder.build());
        }
    }
}
