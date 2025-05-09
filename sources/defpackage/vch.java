package defpackage;

import java.util.List;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.CertificateTypeExtension;
import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public class vch extends CertificateTypeExtension {
    private vch(vbn vbnVar) {
        super(HelloExtension.ExtensionType.CLIENT_CERT_TYPE, vbnVar);
    }

    public vch(List<CertificateType> list) {
        super(HelloExtension.ExtensionType.CLIENT_CERT_TYPE, list);
    }

    public vch(CertificateType certificateType) {
        super(HelloExtension.ExtensionType.CLIENT_CERT_TYPE, certificateType);
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public String toString(int i) {
        return super.toString(i, "Client");
    }

    public static vch b(vbn vbnVar) {
        return new vch(vbnVar);
    }
}
