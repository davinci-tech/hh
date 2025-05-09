package com.huawei.hmf.orb.exception;

/* loaded from: classes9.dex */
public class ConnectRemoteException extends Exception {
    private Status mStatus;

    public enum Status {
        NotFoundService,
        UnableBindService,
        RejectBindService,
        UnknownConnector,
        NotFoundRepository
    }

    public ConnectRemoteException(Status status, String str) {
        super(str);
        this.mStatus = status;
    }

    public ConnectRemoteException(Status status) {
        this(status, null);
    }

    public Status getStatus() {
        return this.mStatus;
    }
}
