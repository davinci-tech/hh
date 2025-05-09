package defpackage;

import java.net.InetSocketAddress;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.californium.elements.EndpointContext;

/* loaded from: classes7.dex */
public class vab extends uzw {
    public static final vac<uzx<?>> p = new vac<uzx<?>>("EndpointContextAttributes") { // from class: vab.3
        @Override // defpackage.vac
        public uzx<?> e(uzx<?> uzxVar) {
            uzxVar.getClass();
            Class<?> valueType = uzxVar.getValueType();
            if (valueType != String.class && valueType != Integer.class && valueType != Long.class && valueType != Boolean.class && valueType != InetSocketAddress.class && !vbj.class.isAssignableFrom(valueType)) {
                throw new IllegalArgumentException(valueType + " is not supported, only String, Integer, Long, Boolean, InetSocketAddress and Bytes!");
            }
            return super.e(uzxVar);
        }
    };
    private final Map<uzx<?>, Object> d;
    private final boolean e;

    public vab(InetSocketAddress inetSocketAddress, Principal principal, d dVar) {
        this(inetSocketAddress, null, principal, dVar);
    }

    public vab(InetSocketAddress inetSocketAddress, String str, Principal principal, d dVar) {
        super(inetSocketAddress, str, principal);
        if (dVar == null) {
            throw new NullPointerException("missing attributes map, must not be null!");
        }
        dVar.e();
        Map<uzx<?>, Object> unmodifiableMap = Collections.unmodifiableMap(dVar.d);
        this.d = unmodifiableMap;
        this.e = a(unmodifiableMap);
    }

    private static final boolean a(Map<uzx<?>, Object> map) {
        Iterator<uzx<?>> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!it.next().getKey().startsWith("*")) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.uzw, org.eclipse.californium.elements.EndpointContext
    public <T> T get(uzx<T> uzxVar) {
        return (T) this.d.get(uzxVar);
    }

    @Override // defpackage.uzw, org.eclipse.californium.elements.EndpointContext
    public Map<uzx<?>, Object> entries() {
        return this.d;
    }

    @Override // defpackage.uzw, org.eclipse.californium.elements.EndpointContext
    public boolean hasCriticalEntries() {
        return this.e;
    }

    @Override // defpackage.uzw
    public String toString() {
        return String.format("MAP(%s)", e());
    }

    public static vab e(EndpointContext endpointContext, d dVar) {
        return new vab(endpointContext.getPeerAddress(), endpointContext.getVirtualHost(), endpointContext.getPeerIdentity(), dVar);
    }

    public static vab a(EndpointContext endpointContext, d dVar) {
        d dVar2 = new d(endpointContext.entries());
        dVar2.a(dVar);
        return e(endpointContext, dVar2);
    }

    public static final class d {
        private volatile boolean c;
        private final Map<uzx<?>, Object> d;

        public d() {
            this.d = new HashMap();
        }

        private d(Map<uzx<?>, Object> map) {
            HashMap hashMap = new HashMap();
            this.d = hashMap;
            hashMap.putAll(map);
        }

        public d e() {
            this.c = true;
            return this;
        }

        public d a(d dVar) {
            if (this.c) {
                throw new IllegalStateException("Already in use!");
            }
            this.d.putAll(dVar.d);
            return this;
        }

        public <T> d d(uzx<T> uzxVar, T t) {
            if (this.c) {
                throw new IllegalStateException("Already in use!");
            }
            if (uzxVar == null) {
                throw new NullPointerException("key is null");
            }
            if (t == null && !uzxVar.getKey().startsWith("*")) {
                throw new NullPointerException("value is null");
            }
            if (!vab.p.b(uzxVar)) {
                throw new IllegalArgumentException(uzxVar + " is not supported!");
            }
            if (t == null) {
                this.d.remove(uzxVar);
            } else if (this.d.put(uzxVar, t) != null) {
                throw new IllegalArgumentException("'" + uzxVar + "' already contained!");
            }
            return this;
        }

        public int hashCode() {
            return this.d.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof d)) {
                return this.d.equals(((d) obj).d);
            }
            return false;
        }
    }
}
