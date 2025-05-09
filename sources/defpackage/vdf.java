package defpackage;

import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public final class vdf extends HelloExtension {
    public static vdf b = new vdf();

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public int getExtensionLength() {
        return 0;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public void writeExtensionTo(vbo vboVar) {
    }

    private vdf() {
        super(HelloExtension.ExtensionType.EXTENDED_MASTER_SECRET);
    }

    public static vdf c(vbn vbnVar) {
        if (vbnVar == null) {
            throw new NullPointerException("record size limit must not be null!");
        }
        return b;
    }
}
