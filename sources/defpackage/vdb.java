package defpackage;

import org.eclipse.californium.scandium.dtls.AlertMessage;

/* loaded from: classes7.dex */
public class vdb extends Exception {
    private static final long serialVersionUID = 1123415935894222594L;
    private final AlertMessage c;

    public vdb(String str, AlertMessage alertMessage) {
        super(str);
        if (alertMessage == null) {
            throw new NullPointerException("Alert must not be null!");
        }
        this.c = alertMessage;
    }

    public vdb(String str, AlertMessage alertMessage, Throwable th) {
        super(str, th);
        if (alertMessage == null) {
            throw new NullPointerException("Alert must not be null!");
        }
        this.c = alertMessage;
    }

    public AlertMessage e() {
        return this.c;
    }
}
