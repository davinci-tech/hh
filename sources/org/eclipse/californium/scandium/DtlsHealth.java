package org.eclipse.californium.scandium;

/* loaded from: classes7.dex */
public interface DtlsHealth {
    void dump(String str, int i, int i2, int i3);

    void endHandshake(boolean z);

    boolean isEnabled();

    void receivingRecord(boolean z);

    void sendingRecord(boolean z);

    void startHandshake();
}
