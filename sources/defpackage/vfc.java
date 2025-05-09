package defpackage;

import org.eclipse.californium.scandium.dtls.ExtendedMasterSecretMode;
import org.eclipse.californium.scandium.dtls.HandshakeResultHandler;
import org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore;
import org.eclipse.californium.scandium.dtls.resumption.ExtendedResumptionVerifier;

/* loaded from: classes7.dex */
public class vfc implements ExtendedResumptionVerifier {

    /* renamed from: a, reason: collision with root package name */
    private volatile ResumptionSupportingConnectionStore f17703a;

    @Override // org.eclipse.californium.scandium.dtls.resumption.ResumptionVerifier
    public void setResultHandler(HandshakeResultHandler handshakeResultHandler) {
    }

    public boolean e() {
        return this.f17703a != null;
    }

    public void a(ResumptionSupportingConnectionStore resumptionSupportingConnectionStore) {
        if (resumptionSupportingConnectionStore == null) {
            throw new NullPointerException("Connection store must not be null!");
        }
        this.f17703a = resumptionSupportingConnectionStore;
    }

    @Override // org.eclipse.californium.scandium.dtls.resumption.ResumptionVerifier
    public boolean skipRequestHelloVerify(vej vejVar) {
        ResumptionSupportingConnectionStore resumptionSupportingConnectionStore = this.f17703a;
        if (resumptionSupportingConnectionStore != null) {
            vct find = resumptionSupportingConnectionStore.find(vejVar);
            r1 = find != null;
            vfh.a(find);
        }
        return r1;
    }

    @Override // org.eclipse.californium.scandium.dtls.resumption.ExtendedResumptionVerifier
    public boolean skipRequestHelloVerify(vck vckVar, boolean z, ExtendedMasterSecretMode extendedMasterSecretMode) {
        vct find;
        ResumptionSupportingConnectionStore resumptionSupportingConnectionStore = this.f17703a;
        if (resumptionSupportingConnectionStore == null || (find = resumptionSupportingConnectionStore.find(vckVar.getSessionId())) == null) {
            return false;
        }
        boolean d = vec.d(find, vckVar, z, extendedMasterSecretMode);
        vfh.a(find);
        return d;
    }

    @Override // org.eclipse.californium.scandium.dtls.resumption.ResumptionVerifier
    public ved verifyResumptionRequest(vcp vcpVar, vfe vfeVar, vej vejVar) {
        return new ved(vcpVar, this.f17703a.find(vejVar), null);
    }
}
