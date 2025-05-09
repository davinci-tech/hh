package defpackage;

import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public final class veg extends HelloExtension {
    private static veg b = new veg(null);
    private final vfe d;

    private veg(vfe vfeVar) {
        super(HelloExtension.ExtensionType.SERVER_NAME);
        this.d = vfeVar;
    }

    public static veg e() {
        return b;
    }

    public static veg e(vfe vfeVar) {
        if (vfeVar == null) {
            throw new NullPointerException("server names must not be null");
        }
        if (vfeVar.c() == 0) {
            throw new IllegalArgumentException("server names must not be empty");
        }
        return new veg(vfeVar);
    }

    public vfe b() {
        return this.d;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public String toString(int i) {
        String helloExtension = super.toString(i);
        if (this.d == null) {
            return helloExtension;
        }
        return helloExtension + this.d.d(i + 1) + vcb.a();
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public int getExtensionLength() {
        vfe vfeVar = this.d;
        if (vfeVar != null) {
            return vfeVar.b();
        }
        return 0;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public void writeExtensionTo(vbo vboVar) {
        vfe vfeVar = this.d;
        if (vfeVar != null) {
            vfeVar.b(vboVar);
        }
    }

    public static veg b(vbn vbnVar) throws vdb {
        if (!vbnVar.e()) {
            return e();
        }
        vfe e = vfe.e();
        try {
            e.a(vbnVar);
            return new veg(e);
        } catch (IllegalArgumentException e2) {
            if (e2.getCause() instanceof IllegalArgumentException) {
                throw new vdb("Server Name Indication extension contains unknown name_type", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
            }
            throw new vdb("malformed Server Name Indication extension", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
        }
    }
}
