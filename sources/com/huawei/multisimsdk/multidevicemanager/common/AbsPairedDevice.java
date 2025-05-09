package com.huawei.multisimsdk.multidevicemanager.common;

import defpackage.lnk;

/* loaded from: classes5.dex */
public abstract class AbsPairedDevice {
    public String getDeviceID() {
        return null;
    }

    public abstract String getPairedDeviceName();

    public abstract String getPairedID();

    public abstract int getPairedIDType();

    public abstract lnk getSecondaryDeviceId();

    public abstract String getTerminalEid();

    public abstract String getTerminalIccid();

    public abstract String getTerminalId();

    public abstract String getTerminalModel();

    public abstract String getTerminalMsisdn();

    public abstract String getTerminalSwVersion();

    public abstract String getTerminalVendor();
}
