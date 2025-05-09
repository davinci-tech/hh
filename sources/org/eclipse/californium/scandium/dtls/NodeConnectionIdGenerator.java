package org.eclipse.californium.scandium.dtls;

import defpackage.vcp;

/* loaded from: classes7.dex */
public interface NodeConnectionIdGenerator extends ConnectionIdGenerator {
    int getNodeId();

    int getNodeId(vcp vcpVar);
}
