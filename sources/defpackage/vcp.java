package defpackage;

import org.eclipse.californium.scandium.dtls.ConnectionIdGenerator;

/* loaded from: classes7.dex */
public class vcp extends vbj {
    public static final vcp b = new vcp(vbj.c);

    public static boolean c(ConnectionIdGenerator connectionIdGenerator) {
        return connectionIdGenerator != null;
    }

    public vcp(byte[] bArr) {
        super(bArr);
    }

    @Override // defpackage.vbj
    public String toString() {
        return "CID=" + e();
    }

    public static boolean b(ConnectionIdGenerator connectionIdGenerator) {
        return connectionIdGenerator != null && connectionIdGenerator.useConnectionId();
    }

    public static boolean c(vcp vcpVar) {
        return (vcpVar == null || vcpVar.d()) ? false : true;
    }
}
