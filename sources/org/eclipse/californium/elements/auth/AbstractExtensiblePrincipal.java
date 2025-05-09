package org.eclipse.californium.elements.auth;

import defpackage.var;
import java.security.Principal;

/* loaded from: classes7.dex */
public abstract class AbstractExtensiblePrincipal<T extends Principal> implements ExtensiblePrincipal<T> {
    private final var additionalInfo;

    protected AbstractExtensiblePrincipal() {
        this(null);
    }

    public AbstractExtensiblePrincipal(var varVar) {
        if (varVar == null) {
            this.additionalInfo = var.c();
        } else {
            this.additionalInfo = varVar;
        }
    }

    @Override // org.eclipse.californium.elements.auth.ExtensiblePrincipal
    public final var getExtendedInfo() {
        return this.additionalInfo;
    }
}
