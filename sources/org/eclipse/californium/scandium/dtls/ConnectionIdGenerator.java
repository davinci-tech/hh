package org.eclipse.californium.scandium.dtls;

import defpackage.vbn;
import defpackage.vcp;

/* loaded from: classes7.dex */
public interface ConnectionIdGenerator {
    vcp createConnectionId();

    vcp read(vbn vbnVar);

    boolean useConnectionId();
}
