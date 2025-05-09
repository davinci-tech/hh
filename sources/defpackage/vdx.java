package defpackage;

import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public final class vdx extends HelloExtension {
    private final int b;

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public int getExtensionLength() {
        return 2;
    }

    public vdx(int i) {
        super(HelloExtension.ExtensionType.RECORD_SIZE_LIMIT);
        this.b = c(i);
    }

    public int a() {
        return this.b;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "Record Size Limit: " + this.b + " bytes" + vcb.a();
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public void writeExtensionTo(vbo vboVar) {
        vboVar.b(this.b, 16);
    }

    public static vdx a(vbn vbnVar) throws vdb {
        if (vbnVar == null) {
            throw new NullPointerException("record size limit must not be null!");
        }
        int c = vbnVar.c(16);
        if (c < 64) {
            throw new vdb("record size limit must be at last 64 bytes, not only " + c + "!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        return new vdx(c);
    }

    public static int c(int i) {
        if (i >= 64 && i <= 65535) {
            return i;
        }
        throw new IllegalArgumentException("Record size limit must be within [64...65535], not " + i + "!");
    }
}
