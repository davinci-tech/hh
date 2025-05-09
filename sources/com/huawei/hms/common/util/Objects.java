package com.huawei.hms.common.util;

import com.huawei.hms.common.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public final class Objects {

    /* loaded from: classes9.dex */
    public static final class ToStringHelper {

        /* renamed from: a, reason: collision with root package name */
        private final List<String> f4475a;
        private final Object b;

        public String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append(this.b.getClass().getSimpleName());
            sb.append('{');
            int size = this.f4475a.size();
            for (int i = 0; i < size; i++) {
                sb.append(this.f4475a.get(i));
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append('}');
            return sb.toString();
        }

        public ToStringHelper add(String str, Object obj) {
            String str2 = (String) Preconditions.checkNotNull(str);
            String valueOf = String.valueOf(obj);
            this.f4475a.add(str2 + "=" + valueOf);
            return this;
        }

        private ToStringHelper(Object obj) {
            this.b = Preconditions.checkNotNull(obj);
            this.f4475a = new ArrayList();
        }
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj);
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private Objects() {
        throw new AssertionError("illegal argument");
    }
}
