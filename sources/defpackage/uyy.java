package defpackage;

import org.eclipse.californium.core.network.Outbox;
import org.eclipse.californium.core.network.stack.BaseCoapStack;
import org.eclipse.californium.core.network.stack.CongestionControlLayer;
import org.eclipse.californium.core.network.stack.Layer;
import org.eclipse.californium.elements.EndpointContextMatcher;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public class uyy extends BaseCoapStack {
    public uyy(String str, Configuration configuration, EndpointContextMatcher endpointContextMatcher, Outbox outbox) {
        super(outbox);
        setLayers(new Layer[]{new uzc(configuration), new uzb(configuration), new uyv(str, false, configuration, endpointContextMatcher), CongestionControlLayer.newImplementation(str, configuration)});
    }
}
