package org.apache.commons.io;

import java.io.IOException;

/* loaded from: classes7.dex */
public class IOIndexedException extends IOException {
    private static final long serialVersionUID = 1;
    private final int index;

    protected static String toMessage(int i, Throwable th) {
        return String.format("%s #%,d: %s", th == null ? "Null" : th.getClass().getSimpleName(), Integer.valueOf(i), th != null ? th.getMessage() : "Null");
    }

    public IOIndexedException(int i, Throwable th) {
        super(toMessage(i, th), th);
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }
}
