package defpackage;

import java.util.Objects;
import org.eclipse.californium.elements.auth.AbstractExtensiblePrincipal;

/* loaded from: classes7.dex */
public final class vap extends AbstractExtensiblePrincipal<vap> {

    /* renamed from: a, reason: collision with root package name */
    private final String f17635a;
    private final boolean b;
    private final String c;
    private final String d;

    public vap(String str) {
        this(false, null, str, null);
    }

    public vap(String str, String str2) {
        this(true, str, str2, null);
    }

    private vap(boolean z, String str, String str2, var varVar) {
        super(varVar);
        if (str2 == null) {
            throw new NullPointerException("Identity must not be null");
        }
        this.b = z;
        if (z) {
            StringBuilder sb = new StringBuilder();
            if (str == null) {
                this.c = null;
            } else if (vcb.a(str)) {
                String lowerCase = str.toLowerCase();
                this.c = lowerCase;
                sb.append(lowerCase);
            } else {
                throw new IllegalArgumentException("virtual host is not a valid hostname");
            }
            sb.append(":");
            sb.append(str2);
            this.f17635a = sb.toString();
        } else {
            if (str != null) {
                throw new IllegalArgumentException("virtual host is not supported, if sni is disabled");
            }
            this.c = null;
            this.f17635a = str2;
        }
        this.d = str2;
    }

    private vap(boolean z, String str, String str2, String str3, var varVar) {
        super(varVar);
        this.b = z;
        this.c = str;
        this.d = str2;
        this.f17635a = str3;
    }

    @Override // org.eclipse.californium.elements.auth.ExtensiblePrincipal
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public vap amend(var varVar) {
        return new vap(this.b, this.c, this.d, this.f17635a, varVar);
    }

    public boolean b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    @Override // java.security.Principal
    public String getName() {
        return this.f17635a;
    }

    @Override // java.security.Principal
    public String toString() {
        if (this.b) {
            return "PreSharedKey Identity [virtual host: " + this.c + ", identity: " + this.d + "]";
        }
        return "PreSharedKey Identity [identity: " + this.d + "]";
    }

    @Override // java.security.Principal
    public int hashCode() {
        return this.f17635a.hashCode();
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return Objects.equals(this.f17635a, ((vap) obj).f17635a);
        }
        return false;
    }
}
