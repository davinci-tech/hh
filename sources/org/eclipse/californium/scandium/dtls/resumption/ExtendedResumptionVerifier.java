package org.eclipse.californium.scandium.dtls.resumption;

import defpackage.vck;
import org.eclipse.californium.scandium.dtls.ExtendedMasterSecretMode;

/* loaded from: classes7.dex */
public interface ExtendedResumptionVerifier extends ResumptionVerifier {
    boolean skipRequestHelloVerify(vck vckVar, boolean z, ExtendedMasterSecretMode extendedMasterSecretMode);
}
