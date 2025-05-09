package org.eclipse.californium.scandium;

import java.net.InetSocketAddress;
import org.eclipse.californium.scandium.dtls.AlertMessage;

/* loaded from: classes7.dex */
public interface AlertHandler {
    void onAlert(InetSocketAddress inetSocketAddress, AlertMessage alertMessage);
}
