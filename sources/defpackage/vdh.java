package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public final class vdh {
    private final List<HelloExtension> d = new ArrayList();

    boolean c() {
        return this.d.isEmpty();
    }

    public int a() {
        if (this.d.isEmpty()) {
            return 0;
        }
        return b() + 2;
    }

    public int b() {
        Iterator<HelloExtension> it = this.d.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().getLength();
        }
        return i;
    }

    public void d(HelloExtension helloExtension) {
        if (helloExtension != null) {
            if (e(helloExtension.getType()) == null) {
                this.d.add(helloExtension);
                return;
            }
            throw new IllegalArgumentException("Hello Extension of type " + helloExtension.getType() + " already added!");
        }
    }

    public <T extends HelloExtension> T e(HelloExtension.ExtensionType extensionType) {
        if (extensionType == null) {
            throw new NullPointerException("Extension type must not be null!");
        }
        Iterator<HelloExtension> it = this.d.iterator();
        T t = null;
        while (it.hasNext()) {
            T t2 = (T) it.next();
            if (extensionType.equals(t2.getType())) {
                return t2;
            }
            if (extensionType.equals(t2.getType().getReplacementType())) {
                t = t2;
            }
        }
        return t;
    }

    public List<HelloExtension> e() {
        return Collections.unmodifiableList(this.d);
    }

    public String a(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(vcb.b(i));
        sb.append("Extensions Length: ");
        sb.append(b());
        sb.append(" bytes");
        sb.append(vcb.a());
        Iterator<HelloExtension> it = this.d.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString(i + 1));
        }
        return sb.toString();
    }

    public String toString() {
        return a(0);
    }

    public void a(vbo vboVar) {
        if (this.d.isEmpty()) {
            return;
        }
        vboVar.b(b(), 16);
        Iterator<HelloExtension> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().writeTo(vboVar);
        }
    }

    public void a(vbn vbnVar) throws vdb {
        if (vbnVar.e()) {
            try {
                vbn b = vbnVar.b(vbnVar.c(16));
                while (b.e()) {
                    HelloExtension readFrom = HelloExtension.readFrom(b);
                    if (readFrom != null) {
                        if (e(readFrom.getType()) == null) {
                            d(readFrom);
                        } else {
                            throw new vdb("Hello message contains extension " + readFrom.getType() + " more than once!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new vdb("Hello message contained malformed extensions, " + e.getMessage(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
            }
        }
    }
}
