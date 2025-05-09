package org.eclipse.californium.elements.auth;

import defpackage.var;
import java.security.Principal;

/* loaded from: classes7.dex */
public interface ExtensiblePrincipal<T extends Principal> extends Principal {
    T amend(var varVar);

    var getExtendedInfo();
}
