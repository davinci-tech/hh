package org.json.alipay;

import com.huawei.operation.utils.Constants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes8.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final Object f15916a = new a(0);
    public Map b;

    public String toString() {
        try {
            Iterator a2 = a();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (a2.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = a2.next();
                stringBuffer.append(c(next.toString()));
                stringBuffer.append(':');
                stringBuffer.append(a(this.b.get(next)));
            }
            stringBuffer.append('}');
            return stringBuffer.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public final boolean b(String str) {
        return this.b.containsKey(str);
    }

    public final Iterator a() {
        return this.b.keySet().iterator();
    }

    public final Object a(String str) {
        Object obj = str == null ? null : this.b.get(str);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONObject[" + c(str) + "] not found.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0082, code lost:
    
        if (r4 == '<') goto L35;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0034. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(java.lang.String r8) {
        /*
            if (r8 == 0) goto L9f
            int r0 = r8.length()
            if (r0 != 0) goto La
            goto L9f
        La:
            int r0 = r8.length()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            int r2 = r0 + 4
            r1.<init>(r2)
            r2 = 34
            r1.append(r2)
            r3 = 0
            r4 = r3
        L1c:
            if (r3 >= r0) goto L97
            char r5 = r8.charAt(r3)
            r6 = 12
            if (r5 == r6) goto L8e
            r6 = 13
            if (r5 == r6) goto L8b
            r6 = 92
            if (r5 == r2) goto L84
            r7 = 47
            if (r5 == r7) goto L80
            if (r5 == r6) goto L84
            switch(r5) {
                case 8: goto L4a;
                case 9: goto L47;
                case 10: goto L44;
                default: goto L37;
            }
        L37:
            r4 = 32
            if (r5 < r4) goto L55
            r4 = 128(0x80, float:1.8E-43)
            if (r5 < r4) goto L4d
            r4 = 160(0xa0, float:2.24E-43)
            if (r5 < r4) goto L55
            goto L4d
        L44:
            java.lang.String r4 = "\\n"
            goto L90
        L47:
            java.lang.String r4 = "\\t"
            goto L90
        L4a:
            java.lang.String r4 = "\\b"
            goto L90
        L4d:
            r4 = 8192(0x2000, float:1.148E-41)
            if (r5 < r4) goto L87
            r4 = 8448(0x2100, float:1.1838E-41)
            if (r5 >= r4) goto L87
        L55:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "000"
            r4.<init>(r6)
            java.lang.String r6 = java.lang.Integer.toHexString(r5)
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "\\u"
            r6.<init>(r7)
            int r7 = r4.length()
            int r7 = r7 + (-4)
            java.lang.String r4 = r4.substring(r7)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            goto L90
        L80:
            r7 = 60
            if (r4 != r7) goto L87
        L84:
            r1.append(r6)
        L87:
            r1.append(r5)
            goto L93
        L8b:
            java.lang.String r4 = "\\r"
            goto L90
        L8e:
            java.lang.String r4 = "\\f"
        L90:
            r1.append(r4)
        L93:
            int r3 = r3 + 1
            r4 = r5
            goto L1c
        L97:
            r1.append(r2)
            java.lang.String r8 = r1.toString()
            return r8
        L9f:
            java.lang.String r8 = "\"\""
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.b.c(java.lang.String):java.lang.String");
    }

    public static void b(Object obj) {
        if (obj != null) {
            if (obj instanceof Double) {
                Double d = (Double) obj;
                if (d.isInfinite() || d.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
                return;
            }
            if (obj instanceof Float) {
                Float f = (Float) obj;
                if (f.isInfinite() || f.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
        }
    }

    public static final class a {
        public final Object clone() {
            return this;
        }

        public final boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public final String toString() {
            return Constants.NULL;
        }

        public /* synthetic */ a(byte b) {
            this();
        }

        public a() {
        }
    }

    public static String a(Object obj) {
        if (obj == null || obj.equals(null)) {
            return Constants.NULL;
        }
        if (!(obj instanceof Number)) {
            return ((obj instanceof Boolean) || (obj instanceof b) || (obj instanceof org.json.alipay.a)) ? obj.toString() : obj instanceof Map ? new b((Map) obj).toString() : obj instanceof Collection ? new org.json.alipay.a((Collection) obj).toString() : obj.getClass().isArray() ? new org.json.alipay.a(obj).toString() : c(obj.toString());
        }
        Number number = (Number) obj;
        if (number == null) {
            throw new JSONException("Null pointer");
        }
        b(number);
        String obj2 = number.toString();
        if (obj2.indexOf(46) <= 0 || obj2.indexOf(101) >= 0 || obj2.indexOf(69) >= 0) {
            return obj2;
        }
        while (obj2.endsWith("0")) {
            obj2 = obj2.substring(0, obj2.length() - 1);
        }
        return obj2.endsWith(".") ? obj2.substring(0, obj2.length() - 1) : obj2;
    }

    public b(c cVar) {
        this();
        if (cVar.c() != '{') {
            throw cVar.a("A JSONObject text must begin with '{'");
        }
        while (true) {
            char c = cVar.c();
            if (c == 0) {
                throw cVar.a("A JSONObject text must end with '}'");
            }
            if (c == '}') {
                return;
            }
            cVar.a();
            String obj = cVar.d().toString();
            char c2 = cVar.c();
            if (c2 == '=') {
                if (cVar.b() != '>') {
                    cVar.a();
                }
            } else if (c2 != ':') {
                throw cVar.a("Expected a ':' after a key");
            }
            Object d = cVar.d();
            if (obj == null) {
                throw new JSONException("Null key.");
            }
            if (d != null) {
                b(d);
                this.b.put(obj, d);
            } else {
                this.b.remove(obj);
            }
            char c3 = cVar.c();
            if (c3 != ',' && c3 != ';') {
                if (c3 != '}') {
                    throw cVar.a("Expected a ',' or '}'");
                }
                return;
            } else if (cVar.c() == '}') {
                return;
            } else {
                cVar.a();
            }
        }
    }

    public b(Map map) {
        this.b = map == null ? new HashMap() : map;
    }

    public b(String str) {
        this(new c(str));
    }

    public b() {
        this.b = new HashMap();
    }
}
