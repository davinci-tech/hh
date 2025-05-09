package org.eclipse.californium.scandium.dtls;

import com.huawei.operation.utils.Constants;

/* loaded from: classes7.dex */
public enum HandshakeType {
    HELLO_REQUEST(0),
    CLIENT_HELLO(1),
    SERVER_HELLO(2),
    HELLO_VERIFY_REQUEST(3),
    CERTIFICATE(11),
    SERVER_KEY_EXCHANGE(12),
    CERTIFICATE_REQUEST(13),
    SERVER_HELLO_DONE(14),
    CERTIFICATE_VERIFY(15),
    CLIENT_KEY_EXCHANGE(16),
    FINISHED(20);

    private final int code;

    HandshakeType(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public static HandshakeType getTypeByCode(int i) {
        for (HandshakeType handshakeType : values()) {
            if (handshakeType.code == i) {
                return handshakeType;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name() + " (" + getCode() + Constants.RIGHT_BRACKET_ONLY;
    }
}
