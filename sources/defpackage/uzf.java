package defpackage;

import java.net.InetSocketAddress;
import org.eclipse.californium.core.network.stack.CongestionControlLayer;
import org.eclipse.californium.core.network.stack.RemoteEndpoint;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public class uzf extends CongestionControlLayer {
    public uzf(String str, Configuration configuration) {
        super(str, configuration);
    }

    @Override // org.eclipse.californium.core.network.stack.CongestionControlLayer
    public RemoteEndpoint createRemoteEndpoint(InetSocketAddress inetSocketAddress) {
        return new RemoteEndpoint(inetSocketAddress, this.defaultReliabilityLayerParameters.e(), this.defaultReliabilityLayerParameters.g(), false) { // from class: uzf.5
            @Override // org.eclipse.californium.core.network.stack.RemoteEndpoint
            public void processRttMeasurement(RemoteEndpoint.RtoType rtoType, long j) {
                updateRTO(j + (j / 2));
            }
        };
    }
}
