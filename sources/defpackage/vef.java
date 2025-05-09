package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.californium.scandium.dtls.HelloExtension;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;

/* loaded from: classes7.dex */
public class vef extends HelloExtension {
    private final List<SignatureAndHashAlgorithm> c;

    public vef(List<SignatureAndHashAlgorithm> list) {
        super(HelloExtension.ExtensionType.SIGNATURE_ALGORITHMS);
        this.c = list;
    }

    public List<SignatureAndHashAlgorithm> a() {
        return this.c;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public String toString(int i) {
        StringBuilder sb = new StringBuilder(super.toString(i));
        String b = vcb.b(i + 1);
        String b2 = vcb.b(i + 2);
        sb.append(b);
        sb.append("Signature Algorithms (");
        sb.append(this.c.size());
        sb.append(" algorithms):");
        sb.append(vcb.a());
        for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : this.c) {
            sb.append(b2);
            sb.append("Signature and Hash Algorithm: ");
            sb.append(signatureAndHashAlgorithm);
            sb.append(vcb.a());
        }
        return sb.toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public int getExtensionLength() {
        return (this.c.size() * 2) + 2;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public void writeExtensionTo(vbo vboVar) {
        vboVar.b(this.c.size() * 2, 16);
        for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : this.c) {
            vboVar.b(signatureAndHashAlgorithm.a().getCode(), 8);
            vboVar.b(signatureAndHashAlgorithm.e().getCode(), 8);
        }
    }

    public static vef d(vbn vbnVar) {
        ArrayList arrayList = new ArrayList();
        vbn b = vbnVar.b(vbnVar.c(16));
        while (b.e()) {
            arrayList.add(new SignatureAndHashAlgorithm(b.c(8), b.c(8)));
        }
        return new vef(Collections.unmodifiableList(arrayList));
    }
}
