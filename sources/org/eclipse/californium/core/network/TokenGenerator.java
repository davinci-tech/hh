package org.eclipse.californium.core.network;

import defpackage.uxu;
import defpackage.uyc;

/* loaded from: classes7.dex */
public interface TokenGenerator {

    public enum Scope {
        LONG_TERM,
        SHORT_TERM,
        SHORT_TERM_CLIENT_LOCAL
    }

    uxu createToken(Scope scope);

    uyc getKeyToken(uxu uxuVar, Object obj);

    Scope getScope(uxu uxuVar);
}
