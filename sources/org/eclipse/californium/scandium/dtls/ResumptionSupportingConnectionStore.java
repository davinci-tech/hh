package org.eclipse.californium.scandium.dtls;

import defpackage.vcm;
import defpackage.vcp;
import defpackage.vct;
import defpackage.vej;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import org.eclipse.californium.elements.PersistentConnector;
import org.eclipse.californium.scandium.ConnectionListener;

/* loaded from: classes7.dex */
public interface ResumptionSupportingConnectionStore extends PersistentConnector {
    void attach(ConnectionIdGenerator connectionIdGenerator);

    void clear();

    vct find(vej vejVar);

    vcm get(InetSocketAddress inetSocketAddress);

    vcm get(vcp vcpVar);

    Iterator<vcm> iterator();

    @Override // org.eclipse.californium.elements.PersistentConnector
    int loadConnections(InputStream inputStream, long j) throws IOException;

    void markAllAsResumptionRequired();

    boolean put(vcm vcmVar);

    void putEstablishedSession(vcm vcmVar);

    int remainingCapacity();

    boolean remove(vcm vcmVar, boolean z);

    void removeFromEstablishedSessions(vcm vcmVar);

    boolean restore(vcm vcmVar);

    @Override // org.eclipse.californium.elements.PersistentConnector
    int saveConnections(OutputStream outputStream, long j) throws IOException;

    void setConnectionListener(ConnectionListener connectionListener);

    void stop(List<Runnable> list);

    boolean update(vcm vcmVar, InetSocketAddress inetSocketAddress);
}
