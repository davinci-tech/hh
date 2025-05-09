package org.eclipse.californium.scandium;

import defpackage.vcm;
import defpackage.vdz;
import java.net.DatagramPacket;

/* loaded from: classes7.dex */
public interface DatagramFilter {
    boolean onMacError(vdz vdzVar, vcm vcmVar);

    boolean onReceiving(DatagramPacket datagramPacket);

    boolean onReceiving(vdz vdzVar, vcm vcmVar);
}
