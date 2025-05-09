package defpackage;

import com.huawei.operation.utils.Constants;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.californium.scandium.util.ServerName;

/* loaded from: classes7.dex */
public final class vfe implements Iterable<ServerName> {
    private final Set<ServerName> e;

    private vfe() {
        this.e = new LinkedHashSet();
    }

    private vfe(ServerName serverName) {
        this();
        e(serverName);
    }

    public static vfe e() {
        return new vfe();
    }

    public static vfe c(ServerName serverName) {
        if (serverName == null) {
            throw new NullPointerException("server name must not be null");
        }
        return new vfe(serverName);
    }

    public vfe e(ServerName serverName) {
        if (serverName == null) {
            throw new NullPointerException("server name must not be null");
        }
        if (b(serverName.b()) != null) {
            throw new IllegalArgumentException("there is already a name of the given type");
        }
        this.e.add(serverName);
        return this;
    }

    public int b() {
        return a() + 2;
    }

    public int a() {
        Iterator<ServerName> it = this.e.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = i + 3 + it.next().c();
        }
        return i;
    }

    public int c() {
        return this.e.size();
    }

    public void b(vbo vboVar) {
        vboVar.b(a(), 16);
        for (ServerName serverName : this.e) {
            vboVar.d(serverName.b().getCode());
            vboVar.d(serverName.a(), 16);
        }
    }

    public void a(vbn vbnVar) {
        vbn b = vbnVar.b(vbnVar.c(16));
        while (b.e()) {
            ServerName.NameType fromCode = ServerName.NameType.fromCode(b.c());
            if (AnonymousClass1.e[fromCode.ordinal()] == 1) {
                e(ServerName.d(fromCode, b.h(16)));
            } else {
                throw new IllegalArgumentException("ServerNames: unknown name_type!", new IllegalArgumentException(fromCode.name()));
            }
        }
    }

    /* renamed from: vfe$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[ServerName.NameType.values().length];
            e = iArr;
            try {
                iArr[ServerName.NameType.HOST_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public ServerName b(ServerName.NameType nameType) {
        for (ServerName serverName : this.e) {
            if (serverName.b().equals(nameType)) {
                return serverName;
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<ServerName> iterator() {
        return this.e.iterator();
    }

    public String d(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(vcb.b(i + 1));
        sb.append("Server Names[");
        sb.append(c());
        sb.append(" entries");
        Iterator<ServerName> it = iterator();
        while (it.hasNext()) {
            ServerName next = it.next();
            sb.append(", '");
            sb.append(next.e());
            sb.append("' (");
            sb.append(next.b());
            sb.append(Constants.RIGHT_BRACKET_ONLY);
        }
        sb.append("]");
        return sb.toString();
    }

    public String toString() {
        return d(0);
    }

    public int hashCode() {
        Iterator<ServerName> it = this.e.iterator();
        int i = 1;
        while (it.hasNext()) {
            i = (i * 31) + it.next().hashCode();
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        vfe vfeVar = (vfe) obj;
        if (this.e.size() != vfeVar.e.size()) {
            return false;
        }
        return this.e.containsAll(vfeVar.e);
    }

    public static boolean e(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }
}
