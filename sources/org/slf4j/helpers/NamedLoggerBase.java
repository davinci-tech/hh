package org.slf4j.helpers;

import defpackage.vha;
import java.io.ObjectStreamException;
import java.io.Serializable;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
abstract class NamedLoggerBase implements Logger, Serializable {
    private static final long serialVersionUID = 7535258609338176893L;
    protected String name;

    NamedLoggerBase() {
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.name;
    }

    protected Object readResolve() throws ObjectStreamException {
        return vha.d(getName());
    }
}
