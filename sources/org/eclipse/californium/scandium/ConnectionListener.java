package org.eclipse.californium.scandium;

import defpackage.vcm;

/* loaded from: classes7.dex */
public interface ConnectionListener {
    void afterExecution(vcm vcmVar);

    void beforeExecution(vcm vcmVar);

    void onConnectionEstablished(vcm vcmVar);

    boolean onConnectionMacError(vcm vcmVar);

    void onConnectionRemoved(vcm vcmVar);

    boolean onConnectionUpdatesSequenceNumbers(vcm vcmVar, boolean z);

    void updateExecution(vcm vcmVar);
}
