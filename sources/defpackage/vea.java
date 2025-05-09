package defpackage;

import java.util.List;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.CertificateTypeExtension;
import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public class vea extends CertificateTypeExtension {
    private vea(vbn vbnVar) {
        super(HelloExtension.ExtensionType.SERVER_CERT_TYPE, vbnVar);
    }

    public vea(List<CertificateType> list) {
        super(HelloExtension.ExtensionType.SERVER_CERT_TYPE, list);
    }

    public vea(CertificateType certificateType) {
        super(HelloExtension.ExtensionType.SERVER_CERT_TYPE, certificateType);
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public String toString(int i) {
        return super.toString(i, "Server");
    }

    public static vea b(vbn vbnVar) {
        return new vea(vbnVar);
    }
}
