package org.eclipse.californium.scandium;

/* loaded from: classes7.dex */
public interface DtlsHealthExtended2 {
    void receivingMacError();

    void setPendingHandshakeJobs(int i);

    void setPendingIncomingJobs(int i);

    void setPendingOutgoingJobs(int i);
}
