package org.eclipse.californium.scandium.dtls;

import defpackage.vcm;
import defpackage.vdb;
import defpackage.vdz;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.List;

/* loaded from: classes7.dex */
public interface RecordLayer {
    public static final int DEFAULT_ETH_MTU = 1500;
    public static final int DEFAULT_IPV4_MTU = 576;
    public static final int DEFAULT_IPV6_MTU = 1280;
    public static final int IPV4_HEADER_LENGTH = 64;
    public static final int IPV6_HEADER_LENGTH = 128;
    public static final int MAX_MTU = 65535;

    void dropReceivedRecord(vdz vdzVar);

    int getMaxDatagramSize(boolean z);

    boolean isRunning();

    void processHandshakeException(vcm vcmVar, vdb vdbVar);

    void processRecord(vdz vdzVar, vcm vcmVar);

    void sendFlight(List<DatagramPacket> list) throws IOException;
}
