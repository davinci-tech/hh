package defpackage;

import org.eclipse.californium.core.coap.MessageObserverAdapter;
import org.eclipse.californium.core.network.Exchange;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uyw extends MessageObserverAdapter {
    protected static final Logger d = vha.a((Class<?>) uyw.class);
    protected final Exchange c;

    @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
    public boolean isInternal() {
        return true;
    }

    protected uyw(Exchange exchange) {
        super(true);
        this.c = exchange;
    }

    @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
    public void onCancel() {
        b("canceled");
    }

    @Override // org.eclipse.californium.core.coap.MessageObserverAdapter
    public void failed() {
        b("failed");
    }

    protected void b(String str) {
        if (this.c.c()) {
            if (this.c.w()) {
                uxt i = this.c.i();
                d.debug("{}, {} request [MID={}, {}]", str, this.c, Integer.valueOf(i.getMID()), i.getToken());
            } else {
                uxr f = this.c.f();
                d.debug("{}, {} response [MID={}, {}]", str, this.c, Integer.valueOf(f.getMID()), f.getToken());
            }
        }
    }
}
