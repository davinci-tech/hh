package defpackage;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uxy {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f17583a = vha.a((Class<?>) uxy.class);
    private static final uxy d = new uxy();
    private final Map<String, Endpoint> b = new ConcurrentHashMap();

    public static uxy c() {
        return d;
    }

    public Endpoint e(String str) {
        Endpoint endpoint;
        synchronized (this) {
            if (str == null) {
                str = "coap";
            }
            if (!CoAP.c(str)) {
                throw new IllegalArgumentException("URI scheme " + str + " not supported!");
            }
            String lowerCase = str.toLowerCase();
            endpoint = this.b.get(lowerCase);
            if (endpoint == null) {
                if ("coaps".equalsIgnoreCase(lowerCase)) {
                    throw new IllegalStateException("URI scheme " + lowerCase + " requires a previous set connector!");
                }
                if ("coap+tcp".equalsIgnoreCase(lowerCase)) {
                    throw new IllegalStateException("URI scheme " + lowerCase + " requires a previous set connector!");
                }
                if ("coaps+tcp".equalsIgnoreCase(lowerCase)) {
                    throw new IllegalStateException("URI scheme " + lowerCase + " requires a previous set connector!");
                }
                endpoint = new CoapEndpoint.e().e();
                try {
                    endpoint.start();
                    f17583a.info("created implicit endpoint {} for {}", endpoint.getUri(), lowerCase);
                } catch (IOException e) {
                    f17583a.error("could not create {} endpoint", lowerCase, e);
                }
                this.b.put(lowerCase, endpoint);
            }
        }
        return endpoint;
    }

    public static class a implements MessageDeliverer {
        @Override // org.eclipse.californium.core.server.MessageDeliverer
        public void deliverRequest(Exchange exchange) {
            uxy.f17583a.error("Default endpoint without CoapServer has received a request.");
            exchange.z();
        }

        @Override // org.eclipse.californium.core.server.MessageDeliverer
        public void deliverResponse(Exchange exchange, uxr uxrVar) {
            if (exchange == null) {
                throw new NullPointerException("no CoAP exchange!");
            }
            if (exchange.p() == null) {
                throw new NullPointerException("no CoAP request!");
            }
            if (uxrVar == null) {
                throw new NullPointerException("no CoAP response!");
            }
            exchange.p().b(uxrVar);
        }
    }
}
