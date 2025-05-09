package org.eclipse.californium.scandium.dtls;

import defpackage.vct;
import defpackage.vej;

/* loaded from: classes7.dex */
public interface SessionStore {
    vct get(vej vejVar);

    void put(vct vctVar);

    void remove(vej vejVar);
}
