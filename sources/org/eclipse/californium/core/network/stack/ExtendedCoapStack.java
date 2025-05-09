package org.eclipse.californium.core.network.stack;

/* loaded from: classes7.dex */
public interface ExtendedCoapStack extends CoapStack {
    <T extends Layer> T getLayer(Class<T> cls);
}
