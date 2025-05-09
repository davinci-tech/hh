package org.eclipse.californium.scandium.dtls;

/* loaded from: classes7.dex */
public interface DTLSMessage {
    ContentType getContentType();

    int size();

    byte[] toByteArray();

    String toString(int i);
}
