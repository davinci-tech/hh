package org.eclipse.californium.scandium.dtls;

/* loaded from: classes7.dex */
public enum ExtendedMasterSecretMode {
    NONE,
    OPTIONAL,
    ENABLED,
    REQUIRED;

    public boolean is(ExtendedMasterSecretMode extendedMasterSecretMode) {
        return ordinal() >= extendedMasterSecretMode.ordinal();
    }
}
