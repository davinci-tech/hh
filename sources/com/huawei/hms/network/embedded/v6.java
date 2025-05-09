package com.huawei.hms.network.embedded;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;

/* loaded from: classes9.dex */
public final class v6 {
    public static final v6 c = new a().a();

    /* renamed from: a, reason: collision with root package name */
    public final Set<b> f5540a;

    @Nullable
    public final qa b;

    public int hashCode() {
        return (Objects.hashCode(this.b) * 31) + this.f5540a.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof v6) {
            v6 v6Var = (v6) obj;
            if (Objects.equals(this.b, v6Var.b) && this.f5540a.equals(v6Var.f5540a)) {
                return true;
            }
        }
        return false;
    }

    public void a(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        a(str, Arrays.asList(certificateArr));
    }

    public void a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List<b> a2 = a(str);
        if (a2.isEmpty()) {
            return;
        }
        qa qaVar = this.b;
        if (qaVar != null) {
            list = qaVar.a(list, str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i);
            int size2 = a2.size();
            eb ebVar = null;
            eb ebVar2 = null;
            for (int i2 = 0; i2 < size2; i2++) {
                b bVar = a2.get(i2);
                if (bVar.c.equals("sha256/")) {
                    if (ebVar == null) {
                        ebVar = b(x509Certificate);
                    }
                    if (bVar.d.equals(ebVar)) {
                        return;
                    }
                } else {
                    if (!bVar.c.equals("sha1/")) {
                        throw new AssertionError("unsupported hashAlgorithm: " + bVar.c);
                    }
                    if (ebVar2 == null) {
                        ebVar2 = a(x509Certificate);
                    }
                    if (bVar.d.equals(ebVar2)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i3 = 0; i3 < size3; i3++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
            sb.append("\n    ");
            sb.append(a((Certificate) x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(":");
        int size4 = a2.size();
        for (int i4 = 0; i4 < size4; i4++) {
            b bVar2 = a2.get(i4);
            sb.append("\n    ");
            sb.append(bVar2);
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    public List<b> a(String str) {
        List<b> emptyList = Collections.emptyList();
        for (b bVar : this.f5540a) {
            if (bVar.a(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList<>();
                }
                emptyList.add(bVar);
            }
        }
        return emptyList;
    }

    public static final class b {
        public static final String e = "*.";

        /* renamed from: a, reason: collision with root package name */
        public final String f5542a;
        public final String b;
        public final String c;
        public final eb d;

        public String toString() {
            return this.c + this.d.b();
        }

        public int hashCode() {
            return ((((this.f5542a.hashCode() + 527) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (this.f5542a.equals(bVar.f5542a) && this.c.equals(bVar.c) && this.d.equals(bVar.d)) {
                    return true;
                }
            }
            return false;
        }

        public boolean a(String str) {
            if (!this.f5542a.startsWith(e)) {
                return str.equals(this.b);
            }
            int indexOf = str.indexOf(46);
            if ((str.length() - indexOf) - 1 == this.b.length()) {
                String str2 = this.b;
                if (str.regionMatches(false, indexOf + 1, str2, 0, str2.length())) {
                    return true;
                }
            }
            return false;
        }

        public b(String str, String str2) {
            StringBuilder sb;
            int i;
            this.f5542a = str;
            if (str.startsWith(e)) {
                sb = new StringBuilder("http://");
                str = str.substring(2);
            } else {
                sb = new StringBuilder("http://");
            }
            sb.append(str);
            this.b = m7.f(sb.toString()).h();
            if (str2.startsWith("sha1/")) {
                this.c = "sha1/";
                i = 5;
            } else {
                if (!str2.startsWith("sha256/")) {
                    throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + str2);
                }
                this.c = "sha256/";
                i = 7;
            }
            eb a2 = eb.a(str2.substring(i));
            this.d = a2;
            if (a2 != null) {
                return;
            }
            throw new IllegalArgumentException("pins must be base64: " + str2);
        }
    }

    public v6 a(@Nullable qa qaVar) {
        return Objects.equals(this.b, qaVar) ? this : new v6(this.f5540a, qaVar);
    }

    public static eb b(X509Certificate x509Certificate) {
        return eb.e(x509Certificate.getPublicKey().getEncoded()).h();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final List<b> f5541a = new ArrayList();

        public v6 a() {
            return new v6(new LinkedHashSet(this.f5541a), null);
        }

        public a a(String str, String... strArr) {
            if (str == null) {
                throw new NullPointerException("pattern == null");
            }
            for (String str2 : strArr) {
                this.f5541a.add(new b(str, str2));
            }
            return this;
        }
    }

    public static String a(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        return "sha256/" + b((X509Certificate) certificate).b();
    }

    public static eb a(X509Certificate x509Certificate) {
        return eb.e(x509Certificate.getPublicKey().getEncoded()).g();
    }

    public v6(Set<b> set, @Nullable qa qaVar) {
        this.f5540a = set;
        this.b = qaVar;
    }
}
