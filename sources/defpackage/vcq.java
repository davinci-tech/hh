package defpackage;

import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public final class vcq extends HelloExtension {
    private final vcp d;

    private vcq(vcp vcpVar, HelloExtension.ExtensionType extensionType) {
        super(extensionType);
        this.d = vcpVar;
    }

    public vcp a() {
        return this.d;
    }

    public boolean c() {
        return getType() != HelloExtension.ExtensionType.CONNECTION_ID;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "DTLS Conection ID: " + this.d + vcb.a();
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public int getExtensionLength() {
        return this.d.b() + 1;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public void writeExtensionTo(vbo vboVar) {
        vboVar.b(this.d, 8);
    }

    public static vcq b(vcp vcpVar, HelloExtension.ExtensionType extensionType) {
        if (vcpVar == null) {
            throw new NullPointerException("cid must not be null!");
        }
        if (extensionType == null) {
            throw new NullPointerException("type must not be null!");
        }
        if (extensionType != HelloExtension.ExtensionType.CONNECTION_ID && extensionType.getReplacementType() != HelloExtension.ExtensionType.CONNECTION_ID) {
            throw new IllegalArgumentException(extensionType + " type is not supported as Connection ID!");
        }
        return new vcq(vcpVar, extensionType);
    }

    public static vcq a(vbn vbnVar, HelloExtension.ExtensionType extensionType) throws vdb {
        if (vbnVar == null) {
            throw new NullPointerException("cid must not be null!");
        }
        if (extensionType == null) {
            throw new NullPointerException("type must not be null!");
        }
        if (extensionType != HelloExtension.ExtensionType.CONNECTION_ID && extensionType.getReplacementType() != HelloExtension.ExtensionType.CONNECTION_ID) {
            throw new IllegalArgumentException(extensionType + " type is not supported as Connection ID!");
        }
        int a2 = vbnVar.a() / 8;
        if (a2 == 0) {
            throw new vdb("Connection id length must be provided!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        if (a2 > 256) {
            StringBuilder sb = new StringBuilder("Connection id length too large! 255 max, but has ");
            sb.append(a2 - 1);
            throw new vdb(sb.toString(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        int c = vbnVar.c(8);
        int i = a2 - 1;
        if (c == i) {
            if (c == 0) {
                return new vcq(vcp.b, extensionType);
            }
            return new vcq(new vcp(vbnVar.a(c)), extensionType);
        }
        throw new vdb("Connection id length " + c + " doesn't match " + i + "!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
    }
}
