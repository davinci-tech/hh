package com.huawei.healthcloud.plugintrack.golf.cloud;

import com.huawei.healthcloud.plugintrack.golf.cloud.beans.HandshakeInfo;
import java.util.Observable;

/* loaded from: classes8.dex */
public class GolfDownloadObservable extends Observable {
    private HandshakeInfo handshakeInfo;

    protected HandshakeInfo getData() {
        return this.handshakeInfo;
    }

    protected void setData(HandshakeInfo handshakeInfo) {
        this.handshakeInfo = handshakeInfo;
        setChanged();
        notifyObservers();
    }
}
