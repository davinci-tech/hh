package org.eclipse.californium.scandium.dtls;

import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import defpackage.vfb;
import defpackage.vha;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.eclipse.californium.scandium.dtls.HelloExtension;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public abstract class CertificateTypeExtension extends HelloExtension {
    protected static final int EXTENSION_TYPE_BITS = 8;
    protected static final int LIST_FIELD_LENGTH_BITS = 8;
    protected final List<CertificateType> certificateTypes;
    private final boolean isClientExtension;
    private static final Logger LOG = vha.a((Class<?>) CertificateTypeExtension.class);
    public static final List<CertificateType> EMPTY = Collections.emptyList();
    public static final List<CertificateType> DEFAULT_X509 = asList(CertificateType.X_509);

    public CertificateTypeExtension(HelloExtension.ExtensionType extensionType, vbn vbnVar) {
        super(extensionType);
        List asList;
        if (vbnVar == null) {
            throw new NullPointerException("extension data must not be null!");
        }
        if (!vbnVar.e()) {
            throw new IllegalArgumentException("extension data must not be empty!");
        }
        boolean z = vbnVar.a() > 8;
        this.isClientExtension = z;
        if (z) {
            int c = vbnVar.c(8);
            asList = new ArrayList(c);
            vbn b = vbnVar.b(c);
            while (b.e()) {
                int c2 = b.c(8);
                CertificateType typeFromCode = CertificateType.getTypeFromCode(c2);
                if (typeFromCode != null) {
                    asList.add(typeFromCode);
                } else {
                    LOG.debug("Client indicated preference for unknown {} certificate type code [{}]", getType().equals(HelloExtension.ExtensionType.CLIENT_CERT_TYPE) ? "client" : "server", Integer.valueOf(c2));
                }
            }
            if (asList.isEmpty()) {
                throw new IllegalArgumentException("Empyt client certificate types!");
            }
        } else {
            int c3 = vbnVar.c(8);
            CertificateType typeFromCode2 = CertificateType.getTypeFromCode(c3);
            if (typeFromCode2 != null) {
                asList = asList(typeFromCode2);
            } else {
                LOG.debug("Server selected an unknown {} certificate type code [{}]", getType().equals(HelloExtension.ExtensionType.CLIENT_CERT_TYPE) ? "client" : "server", Integer.valueOf(c3));
                throw new IllegalArgumentException("unknown certificate type code " + c3 + "!");
            }
        }
        this.certificateTypes = vfb.a(asList);
    }

    public CertificateTypeExtension(HelloExtension.ExtensionType extensionType, List<CertificateType> list) {
        super(extensionType);
        if (list == null) {
            throw new NullPointerException("certificate types must not be null!");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("certificate types data must not be empty!");
        }
        this.isClientExtension = true;
        this.certificateTypes = list;
    }

    public CertificateTypeExtension(HelloExtension.ExtensionType extensionType, CertificateType certificateType) {
        super(extensionType);
        if (certificateType == null) {
            throw new NullPointerException("certificate type must not be null!");
        }
        this.isClientExtension = false;
        this.certificateTypes = asList(certificateType);
    }

    public boolean isClientExtension() {
        return this.isClientExtension;
    }

    public List<CertificateType> getCertificateTypes() {
        return this.certificateTypes;
    }

    public CertificateType getCertificateType() {
        return this.certificateTypes.get(0);
    }

    public boolean contains(CertificateType certificateType) {
        return this.certificateTypes.contains(certificateType);
    }

    public List<CertificateType> getCommonCertificateTypes(List<CertificateType> list) {
        ArrayList arrayList = new ArrayList();
        for (CertificateType certificateType : this.certificateTypes) {
            if (list.contains(certificateType)) {
                arrayList.add(certificateType);
            }
        }
        return arrayList;
    }

    public String toString(int i, String str) {
        StringBuilder sb = new StringBuilder(super.toString(i));
        String b = vcb.b(i + 1);
        if (isClientExtension()) {
            sb.append(b);
            sb.append(str);
            sb.append(" certificate types: (");
            sb.append(getCertificateTypes().size());
            sb.append(" types)");
            sb.append(vcb.a());
            String b2 = vcb.b(i + 2);
            for (CertificateType certificateType : getCertificateTypes()) {
                sb.append(b2);
                sb.append(str);
                sb.append(" certificate type: ");
                sb.append(certificateType);
                sb.append(vcb.a());
            }
        } else {
            sb.append(b);
            sb.append(str);
            sb.append(" certificate type: ");
            sb.append(getCertificateType());
            sb.append(vcb.a());
        }
        return sb.toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    protected int getExtensionLength() {
        if (this.isClientExtension) {
            return this.certificateTypes.size() + 1;
        }
        return 1;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    protected void writeExtensionTo(vbo vboVar) {
        if (this.isClientExtension) {
            vboVar.b(this.certificateTypes.size(), 8);
            Iterator<CertificateType> it = this.certificateTypes.iterator();
            while (it.hasNext()) {
                vboVar.b(it.next().getCode(), 8);
            }
            return;
        }
        vboVar.b(this.certificateTypes.get(0).getCode(), 8);
    }

    private static List<CertificateType> asList(CertificateType certificateType) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(certificateType);
        return arrayList;
    }
}
