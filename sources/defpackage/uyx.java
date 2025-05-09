package defpackage;

import org.eclipse.californium.core.coap.CoAP;

/* loaded from: classes7.dex */
public class uyx extends Exception {
    private static final long serialVersionUID = 1357;
    private final CoAP.ResponseCode c;
    private final boolean e;

    public uyx(String str) {
        super(str);
        this.e = false;
        this.c = null;
    }

    public uyx(String str, CoAP.ResponseCode responseCode) {
        super(str);
        this.e = false;
        this.c = responseCode;
    }

    public uyx(String str, boolean z) {
        super(str);
        this.e = z;
        this.c = null;
    }

    public boolean c() {
        return this.e;
    }

    public CoAP.ResponseCode a() {
        return this.c;
    }
}
