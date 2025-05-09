package defpackage;

import org.eclipse.californium.core.network.Outbox;
import org.eclipse.californium.core.network.stack.BaseCoapStack;
import org.eclipse.californium.core.network.stack.Layer;
import org.eclipse.californium.elements.EndpointContextMatcher;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public class uyz extends BaseCoapStack {
    public uyz(String str, Configuration configuration, EndpointContextMatcher endpointContextMatcher, Outbox outbox) {
        super(outbox);
        setLayers(new Layer[]{new uzi(), new uzg(configuration), new uyv(str, true, configuration, endpointContextMatcher), new uzh()});
    }
}
