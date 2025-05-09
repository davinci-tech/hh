package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.OptionNumberRegistry;

/* loaded from: classes7.dex */
public final class uxv {

    /* renamed from: a, reason: collision with root package name */
    private List<byte[]> f17580a;
    private uxh b;
    private Integer c;
    private uxh d;
    private Integer e;
    private boolean f;
    private Long g;
    private List<String> h;
    private List<String> i;
    private List<byte[]> j;
    private List<uxs> k;
    private String l;
    private Integer m;
    private uxp n;
    private byte[] o;
    private String p;
    private String q;
    private List<String> r;
    private Integer s;
    private Integer t;
    private List<String> v;
    private Integer w;

    public static boolean a(int i) {
        return i >= 0 && i <= 16777215;
    }

    public uxv() {
        this.j = null;
        this.p = null;
        this.f17580a = null;
        this.f = false;
        this.w = null;
        this.i = null;
        this.r = null;
        this.e = null;
        this.g = null;
        this.v = null;
        this.c = null;
        this.h = null;
        this.q = null;
        this.l = null;
        this.d = null;
        this.b = null;
        this.s = null;
        this.t = null;
        this.m = null;
        this.o = null;
        this.n = null;
        this.k = null;
    }

    public uxv(uxv uxvVar) {
        if (uxvVar == null) {
            throw new NullPointerException("option set must not be null!");
        }
        this.j = d(uxvVar.j);
        this.p = uxvVar.p;
        this.f17580a = d(uxvVar.f17580a);
        this.f = uxvVar.f;
        this.w = uxvVar.w;
        this.i = d(uxvVar.i);
        this.r = d(uxvVar.r);
        this.e = uxvVar.e;
        this.g = uxvVar.g;
        this.v = d(uxvVar.v);
        this.c = uxvVar.c;
        this.h = d(uxvVar.h);
        this.q = uxvVar.q;
        this.l = uxvVar.l;
        this.d = uxvVar.d;
        this.b = uxvVar.b;
        this.s = uxvVar.s;
        this.t = uxvVar.t;
        this.m = uxvVar.m;
        byte[] bArr = uxvVar.o;
        if (bArr != null) {
            this.o = (byte[]) bArr.clone();
        }
        this.n = uxvVar.n;
        this.k = d(uxvVar.k);
    }

    public void c() {
        List<byte[]> list = this.j;
        if (list != null) {
            list.clear();
        }
        this.p = null;
        List<byte[]> list2 = this.f17580a;
        if (list2 != null) {
            list2.clear();
        }
        this.f = false;
        this.w = null;
        List<String> list3 = this.i;
        if (list3 != null) {
            list3.clear();
        }
        List<String> list4 = this.r;
        if (list4 != null) {
            list4.clear();
        }
        this.e = null;
        this.g = null;
        List<String> list5 = this.v;
        if (list5 != null) {
            list5.clear();
        }
        this.c = null;
        List<String> list6 = this.h;
        if (list6 != null) {
            list6.clear();
        }
        this.q = null;
        this.l = null;
        this.d = null;
        this.b = null;
        this.s = null;
        this.t = null;
        this.m = null;
        this.o = null;
        this.n = null;
        List<uxs> list7 = this.k;
        if (list7 != null) {
            list7.clear();
        }
    }

    private <T> List<T> d(List<T> list) {
        if (list == null) {
            return null;
        }
        return new LinkedList(list);
    }

    public List<byte[]> n() {
        synchronized (this) {
            if (this.j == null) {
                this.j = new LinkedList();
            }
        }
        return this.j;
    }

    public uxv c(byte[] bArr) {
        a(1, bArr);
        n().add(bArr);
        return this;
    }

    public String y() {
        return this.p;
    }

    public boolean aq() {
        return this.p != null;
    }

    public uxv i(String str) {
        d(3, str);
        this.p = str;
        return this;
    }

    public uxv au() {
        this.p = null;
        return this;
    }

    public List<byte[]> g() {
        synchronized (this) {
            if (this.f17580a == null) {
                this.f17580a = new LinkedList();
            }
        }
        return this.f17580a;
    }

    public int i() {
        return g().size();
    }

    public boolean d(byte[] bArr) {
        List<byte[]> list = this.f17580a;
        if (list == null) {
            return false;
        }
        Iterator<byte[]> it = list.iterator();
        while (it.hasNext()) {
            if (Arrays.equals(it.next(), bArr)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public uxv a(byte[] bArr) {
        a(4, bArr);
        if (!d(bArr)) {
            g().add(bArr.clone());
        }
        return this;
    }

    public boolean ag() {
        return this.f;
    }

    public uxv b(boolean z) {
        this.f = z;
        return this;
    }

    public Integer ac() {
        return this.w;
    }

    public boolean ar() {
        return this.w != null;
    }

    public uxv g(int i) {
        OptionNumberRegistry.c(7, i);
        this.w = Integer.valueOf(i);
        return this;
    }

    public uxv aw() {
        this.w = null;
        return this;
    }

    public List<String> k() {
        synchronized (this) {
            if (this.i == null) {
                this.i = new LinkedList();
            }
        }
        return this.i;
    }

    public uxv a(String str) {
        d(8, str);
        k().add(str);
        return this;
    }

    public String aa() {
        StringBuilder sb = new StringBuilder();
        sb.append('/');
        a(sb, x(), '/');
        if (w() > 0) {
            sb.append('?');
            a(sb, ad(), '&');
        }
        return sb.toString();
    }

    public List<String> x() {
        synchronized (this) {
            if (this.r == null) {
                this.r = new LinkedList();
            }
        }
        return this.r;
    }

    public String u() {
        return e(x(), '/');
    }

    public uxv h(String str) {
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        b();
        for (String str2 : str.split("/")) {
            c(str2);
        }
        return this;
    }

    public uxv c(String str) {
        d(11, str);
        x().add(str);
        return this;
    }

    public uxv b() {
        x().clear();
        return this;
    }

    public int h() {
        if (ah()) {
            return this.e.intValue();
        }
        return -1;
    }

    public boolean ah() {
        return this.e != null;
    }

    public uxv b(int i) {
        if (-1 == i) {
            this.e = null;
        } else {
            OptionNumberRegistry.c(12, i);
            this.e = Integer.valueOf(i);
        }
        return this;
    }

    public Long l() {
        Long l = this.g;
        return Long.valueOf(l != null ? l.longValue() : 60L);
    }

    public boolean af() {
        return this.g != null;
    }

    public uxv d(long j) {
        OptionNumberRegistry.c(14, j);
        this.g = Long.valueOf(j);
        return this;
    }

    public List<String> ad() {
        synchronized (this) {
            if (this.v == null) {
                this.v = new LinkedList();
            }
        }
        return this.v;
    }

    public int w() {
        return ad().size();
    }

    public String z() {
        return e(ad(), '&');
    }

    public uxv f(String str) {
        while (str.startsWith("?")) {
            str = str.substring(1);
        }
        a();
        for (String str2 : str.split("&")) {
            if (!str2.isEmpty()) {
                b(str2);
            }
        }
        return this;
    }

    public uxv b(String str) {
        d(15, str);
        ad().add(str);
        return this;
    }

    public uxv a() {
        ad().clear();
        return this;
    }

    public int e() {
        if (ab()) {
            return this.c.intValue();
        }
        return -1;
    }

    public boolean ab() {
        return this.c != null;
    }

    public uxv d(int i) {
        OptionNumberRegistry.c(17, i);
        this.c = Integer.valueOf(i);
        return this;
    }

    public List<String> m() {
        synchronized (this) {
            if (this.h == null) {
                this.h = new LinkedList();
            }
        }
        return this.h;
    }

    public uxv e(String str) {
        d(20, str);
        m().add(str);
        return this;
    }

    public String q() {
        return this.q;
    }

    public boolean ak() {
        return this.q != null;
    }

    public uxv j(String str) {
        d(35, str);
        this.q = str;
        return this;
    }

    public String p() {
        return this.l;
    }

    public boolean am() {
        return this.l != null;
    }

    public uxv d(String str) {
        d(39, str);
        this.l = str;
        return this;
    }

    public uxh f() {
        return this.d;
    }

    public boolean ai() {
        return this.d != null;
    }

    public uxv b(int i, boolean z, int i2) {
        this.d = new uxh(i, z, i2);
        return this;
    }

    public uxv e(byte[] bArr) {
        this.d = new uxh(bArr);
        return this;
    }

    public uxv d(uxh uxhVar) {
        this.d = uxhVar;
        return this;
    }

    public uxv as() {
        this.d = null;
        return this;
    }

    public uxh j() {
        return this.b;
    }

    public boolean ae() {
        return this.b != null;
    }

    public uxv e(int i, boolean z, int i2) {
        this.b = new uxh(i, z, i2);
        return this;
    }

    public uxv b(byte[] bArr) {
        this.b = new uxh(bArr);
        return this;
    }

    public uxv a(uxh uxhVar) {
        this.b = uxhVar;
        return this;
    }

    public uxv ax() {
        this.b = null;
        return this;
    }

    public Integer s() {
        return this.s;
    }

    public boolean ao() {
        return this.s != null;
    }

    public uxv h(int i) {
        this.s = Integer.valueOf(i);
        return this;
    }

    public uxv av() {
        this.s = null;
        return this;
    }

    public Integer v() {
        return this.t;
    }

    public boolean ap() {
        return this.t != null;
    }

    public uxv f(int i) {
        this.t = Integer.valueOf(i);
        return this;
    }

    public Integer t() {
        return this.m;
    }

    public boolean an() {
        return this.m != null;
    }

    public uxv e(int i) {
        OptionNumberRegistry.c(6, i);
        this.m = Integer.valueOf(i);
        return this;
    }

    public uxv at() {
        this.m = null;
        return this;
    }

    public byte[] r() {
        return this.o;
    }

    public boolean aj() {
        return this.o != null;
    }

    public uxv h(byte[] bArr) {
        a(9, bArr);
        this.o = (byte[]) bArr.clone();
        return this;
    }

    public uxp o() {
        return this.n;
    }

    public boolean al() {
        return this.n != null;
    }

    public uxv c(int i) {
        this.n = new uxp(i);
        return this;
    }

    private List<uxs> bb() {
        synchronized (this) {
            if (this.k == null) {
                this.k = new LinkedList();
            }
        }
        return this.k;
    }

    public List<uxs> d() {
        ArrayList arrayList = new ArrayList();
        List<byte[]> list = this.j;
        if (list != null) {
            Iterator<byte[]> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new uxs(1, it.next()));
            }
        }
        if (aq()) {
            arrayList.add(new uxs(3, y()));
        }
        List<byte[]> list2 = this.f17580a;
        if (list2 != null) {
            Iterator<byte[]> it2 = list2.iterator();
            while (it2.hasNext()) {
                arrayList.add(new uxs(4, it2.next()));
            }
        }
        if (ag()) {
            arrayList.add(new uxs(5, vbj.c));
        }
        if (ar()) {
            arrayList.add(new uxs(7, ac().intValue()));
        }
        List<String> list3 = this.i;
        if (list3 != null) {
            Iterator<String> it3 = list3.iterator();
            while (it3.hasNext()) {
                arrayList.add(new uxs(8, it3.next()));
            }
        }
        List<String> list4 = this.r;
        if (list4 != null) {
            Iterator<String> it4 = list4.iterator();
            while (it4.hasNext()) {
                arrayList.add(new uxs(11, it4.next()));
            }
        }
        if (ah()) {
            arrayList.add(new uxs(12, h()));
        }
        if (af()) {
            arrayList.add(new uxs(14, l().longValue()));
        }
        List<String> list5 = this.v;
        if (list5 != null) {
            Iterator<String> it5 = list5.iterator();
            while (it5.hasNext()) {
                arrayList.add(new uxs(15, it5.next()));
            }
        }
        if (ab()) {
            arrayList.add(new uxs(17, e()));
        }
        List<String> list6 = this.h;
        if (list6 != null) {
            Iterator<String> it6 = list6.iterator();
            while (it6.hasNext()) {
                arrayList.add(new uxs(20, it6.next()));
            }
        }
        if (ak()) {
            arrayList.add(new uxs(35, q()));
        }
        if (am()) {
            arrayList.add(new uxs(39, p()));
        }
        if (an()) {
            arrayList.add(new uxs(6, t().intValue()));
        }
        if (ai()) {
            arrayList.add(new uxs(27, f().e()));
        }
        if (ae()) {
            arrayList.add(new uxs(23, j().e()));
        }
        if (ao()) {
            arrayList.add(new uxs(60, s().intValue()));
        }
        if (ap()) {
            arrayList.add(new uxs(28, v().intValue()));
        }
        if (aj()) {
            arrayList.add(new uxs(9, r()));
        }
        if (al()) {
            arrayList.add(o().d());
        }
        List<uxs> list7 = this.k;
        if (list7 != null) {
            arrayList.addAll(list7);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public uxv d(uxs uxsVar) {
        int e = uxsVar.e();
        if (e == 1) {
            c(uxsVar.b());
        } else if (e == 17) {
            d(uxsVar.c());
        } else if (e == 20) {
            e(uxsVar.a());
        } else if (e == 23) {
            b(uxsVar.b());
        } else if (e == 35) {
            j(uxsVar.a());
        } else if (e == 39) {
            d(uxsVar.a());
        } else if (e == 60) {
            h(uxsVar.c());
        } else if (e == 258) {
            c(uxsVar.c());
        } else if (e == 11) {
            c(uxsVar.a());
        } else if (e == 12) {
            b(uxsVar.c());
        } else if (e == 14) {
            d(uxsVar.d());
        } else if (e == 15) {
            b(uxsVar.a());
        } else if (e == 27) {
            e(uxsVar.b());
        } else if (e != 28) {
            switch (e) {
                case 3:
                    i(uxsVar.a());
                    break;
                case 4:
                    a(uxsVar.b());
                    break;
                case 5:
                    b(true);
                    break;
                case 6:
                    e(uxsVar.c());
                    break;
                case 7:
                    g(uxsVar.c());
                    break;
                case 8:
                    a(uxsVar.a());
                    break;
                case 9:
                    h(uxsVar.b());
                    break;
                default:
                    bb().add(uxsVar);
                    break;
            }
        } else {
            f(uxsVar.c());
        }
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        StringBuilder sb2 = new StringBuilder();
        int i = -1;
        boolean z = false;
        for (uxs uxsVar : d()) {
            if (uxsVar.e() != i) {
                if (i != -1) {
                    if (z) {
                        sb2.append(']');
                    }
                    sb.append(sb2.toString());
                    sb.append(", ");
                    sb2.setLength(0);
                }
                sb.append('\"');
                sb.append(OptionNumberRegistry.a(uxsVar.e()));
                sb.append("\":");
                z = false;
            } else {
                if (!z) {
                    sb2.insert(0, '[');
                }
                sb2.append(",");
                z = true;
            }
            sb2.append(uxsVar.j());
            i = uxsVar.e();
        }
        if (z) {
            sb2.append(']');
        }
        sb.append(sb2.toString());
        sb.append('}');
        return sb.toString();
    }

    private String e(List<String> list, char c) {
        StringBuilder sb = new StringBuilder();
        a(sb, list, c);
        return sb.toString();
    }

    private void a(StringBuilder sb, List<String> list, char c) {
        if (list.isEmpty()) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(c);
        }
        sb.setLength(sb.length() - 1);
    }

    private static void d(int i, String str) {
        a(i, str == null ? null : str.getBytes(CoAP.d));
    }

    private static void a(int i, byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException(OptionNumberRegistry.a(i) + " option must not be null!");
        }
        OptionNumberRegistry.a(i, bArr.length);
    }
}
