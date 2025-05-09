package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.j7;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes9.dex */
public final class i9 {

    /* renamed from: a, reason: collision with root package name */
    public static final eb f5311a = eb.d("\"\\");
    public static final eb b = eb.d("\t ,=");

    public static j7 e(v7 v7Var) {
        return a(v7Var.C().H().e(), v7Var.y());
    }

    public static Set<String> d(v7 v7Var) {
        return c(v7Var.y());
    }

    public static boolean c(v7 v7Var) {
        return b(v7Var.y());
    }

    public static boolean c(bb bbVar) {
        boolean z = false;
        while (!bbVar.i()) {
            byte j = bbVar.j(0L);
            if (j != 44) {
                if (j != 32 && j != 9) {
                    break;
                }
                bbVar.readByte();
            } else {
                try {
                    bbVar.readByte();
                    z = true;
                } catch (EOFException e) {
                    throw new AssertionError(e);
                }
            }
        }
        return z;
    }

    public static Set<String> c(j7 j7Var) {
        Set<String> emptySet = Collections.emptySet();
        int d = j7Var.d();
        for (int i = 0; i < d; i++) {
            if ("Vary".equalsIgnoreCase(j7Var.a(i))) {
                String b2 = j7Var.b(i);
                if (emptySet.isEmpty()) {
                    emptySet = new TreeSet<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
                }
                for (String str : b2.split(",")) {
                    emptySet.add(str.trim());
                }
            }
        }
        return emptySet;
    }

    public static boolean b(v7 v7Var) {
        if (v7Var.H().h().equals("HEAD")) {
            return false;
        }
        int w = v7Var.w();
        return (((w >= 100 && w < 200) || w == 204 || w == 304) && a(v7Var) == -1 && !"chunked".equalsIgnoreCase(v7Var.b("Transfer-Encoding"))) ? false : true;
    }

    public static boolean b(j7 j7Var) {
        return c(j7Var).contains("*");
    }

    public static String b(bb bbVar) {
        try {
            long c = bbVar.c(b);
            if (c == -1) {
                c = bbVar.B();
            }
            if (c != 0) {
                return bbVar.f(c);
            }
            return null;
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public static int b(String str, int i) {
        char charAt;
        while (i < str.length() && ((charAt = str.charAt(i)) == ' ' || charAt == '\t')) {
            i++;
        }
        return i;
    }

    public static boolean a(v7 v7Var, j7 j7Var, t7 t7Var) {
        for (String str : d(v7Var)) {
            if (!Objects.equals(j7Var.d(str), t7Var.b(str))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x007d, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x007d, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.util.List<com.huawei.hms.network.embedded.w6> r8, com.huawei.hms.network.embedded.bb r9) {
        /*
        L0:
            r0 = 0
            r1 = r0
        L2:
            if (r1 != 0) goto Le
            c(r9)
            java.lang.String r1 = b(r9)
            if (r1 != 0) goto Le
            return
        Le:
            boolean r2 = c(r9)
            java.lang.String r3 = b(r9)
            if (r3 != 0) goto L2c
            boolean r9 = r9.i()
            if (r9 != 0) goto L1f
            return
        L1f:
            com.huawei.hms.network.embedded.w6 r9 = new com.huawei.hms.network.embedded.w6
            java.util.Map r0 = java.util.Collections.emptyMap()
            r9.<init>(r1, r0)
            r8.add(r9)
            return
        L2c:
            r4 = 61
            int r5 = a(r9, r4)
            boolean r6 = c(r9)
            if (r2 != 0) goto L60
            if (r6 != 0) goto L40
            boolean r2 = r9.i()
            if (r2 == 0) goto L60
        L40:
            com.huawei.hms.network.embedded.w6 r2 = new com.huawei.hms.network.embedded.w6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r3)
            java.lang.String r3 = a(r4, r5)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            java.util.Map r0 = java.util.Collections.singletonMap(r0, r3)
            r2.<init>(r1, r0)
            r8.add(r2)
            goto L0
        L60:
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            int r6 = a(r9, r4)
            int r5 = r5 + r6
        L6a:
            if (r3 != 0) goto L7b
            java.lang.String r3 = b(r9)
            boolean r5 = c(r9)
            if (r5 == 0) goto L77
            goto L7d
        L77:
            int r5 = a(r9, r4)
        L7b:
            if (r5 != 0) goto L88
        L7d:
            com.huawei.hms.network.embedded.w6 r4 = new com.huawei.hms.network.embedded.w6
            r4.<init>(r1, r2)
            r8.add(r4)
            r1 = r3
            goto L2
        L88:
            r6 = 1
            if (r5 <= r6) goto L8c
            return
        L8c:
            boolean r6 = c(r9)
            if (r6 == 0) goto L93
            return
        L93:
            boolean r6 = r9.i()     // Catch: java.io.EOFException -> Lc7
            if (r6 != 0) goto La8
            r6 = 0
            byte r6 = r9.j(r6)     // Catch: java.io.EOFException -> Lc7
            r7 = 34
            if (r6 != r7) goto La8
            java.lang.String r6 = a(r9)     // Catch: java.io.EOFException -> Lc7
            goto Lac
        La8:
            java.lang.String r6 = b(r9)     // Catch: java.io.EOFException -> Lc7
        Lac:
            if (r6 != 0) goto Laf
            return
        Laf:
            java.lang.Object r3 = r2.put(r3, r6)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto Lb8
            return
        Lb8:
            boolean r3 = c(r9)
            if (r3 != 0) goto Lc5
            boolean r3 = r9.i()
            if (r3 != 0) goto Lc5
            return
        Lc5:
            r3 = r0
            goto L6a
        Lc7:
            r8 = move-exception
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.i9.a(java.util.List, com.huawei.hms.network.embedded.bb):void");
    }

    public static void a(c7 c7Var, m7 m7Var, j7 j7Var) {
        if (c7Var == c7.f5202a) {
            return;
        }
        List<b7> a2 = b7.a(m7Var, j7Var);
        if (a2.isEmpty()) {
            return;
        }
        c7Var.a(m7Var, a2);
    }

    public static List<w6> a(j7 j7Var, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < j7Var.d(); i++) {
            if (str.equalsIgnoreCase(j7Var.a(i))) {
                a(arrayList, new bb().a(j7Var.b(i)));
            }
        }
        return arrayList;
    }

    public static String a(bb bbVar) throws EOFException {
        if (bbVar.readByte() != 34) {
            throw new IllegalArgumentException();
        }
        bb bbVar2 = new bb();
        while (true) {
            long c = bbVar.c(f5311a);
            if (c == -1) {
                return null;
            }
            if (bbVar.j(c) == 34) {
                bbVar2.write(bbVar, c);
                bbVar.readByte();
                return bbVar2.o();
            }
            if (bbVar.B() == c + 1) {
                return null;
            }
            bbVar2.write(bbVar, c);
            bbVar.readByte();
            bbVar2.write(bbVar, 1L);
        }
    }

    public static String a(char c, int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, c);
        return new String(cArr);
    }

    public static j7 a(j7 j7Var, j7 j7Var2) {
        Set<String> c = c(j7Var2);
        if (c.isEmpty()) {
            return f8.c;
        }
        j7.a aVar = new j7.a();
        int d = j7Var.d();
        for (int i = 0; i < d; i++) {
            String a2 = j7Var.a(i);
            if (c.contains(a2)) {
                aVar.a(a2, j7Var.b(i));
            }
        }
        return aVar.a();
    }

    public static long a(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static long a(v7 v7Var) {
        return a(v7Var.y());
    }

    public static long a(j7 j7Var) {
        return a(j7Var.a("Content-Length"));
    }

    public static int a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int a(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static int a(bb bbVar, byte b2) {
        int i = 0;
        while (!bbVar.i() && bbVar.j(0L) == b2) {
            i++;
            try {
                bbVar.readByte();
            } catch (EOFException e) {
                throw new AssertionError(e);
            }
        }
        return i;
    }
}
