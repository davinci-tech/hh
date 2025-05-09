package org.eclipse.californium.elements.config;

/* loaded from: classes7.dex */
public enum CertificateAuthenticationMode {
    NONE(false),
    WANTED(true),
    NEEDED(true);

    private final boolean useCertificateRequest;

    CertificateAuthenticationMode(boolean z) {
        this.useCertificateRequest = z;
    }

    public boolean useCertificateRequest() {
        return this.useCertificateRequest;
    }
}
