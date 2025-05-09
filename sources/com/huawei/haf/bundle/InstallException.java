package com.huawei.haf.bundle;

/* loaded from: classes.dex */
public class InstallException extends RuntimeException {
    private static final long serialVersionUID = 3544204721266317471L;
    private final int b;

    public InstallException(int i) {
        this(i, null);
    }

    public InstallException(int i, Throwable th) {
        super("Module Install Error: " + i, th);
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String localizedMessage = getLocalizedMessage();
        if (localizedMessage == null) {
            return "InstallException";
        }
        return "InstallException: " + localizedMessage;
    }
}
