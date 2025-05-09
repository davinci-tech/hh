package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud;

/* loaded from: classes9.dex */
public class DeviceTypeInfoResponse extends Response {
    private DeviceTypeInfo deviceTypeInfo;

    public DeviceTypeInfo getDeviceTypeInfo() {
        return this.deviceTypeInfo;
    }

    public void setDeviceTypeInfo(DeviceTypeInfo deviceTypeInfo) {
        this.deviceTypeInfo = deviceTypeInfo;
    }

    public static class DeviceTypeInfo {
        private String deviceType;
        private boolean isSupportDualFrame;

        public String getDeviceType() {
            return this.deviceType;
        }

        public void setDeviceType(String str) {
            this.deviceType = str;
        }

        public boolean isSupportDualFrame() {
            return this.isSupportDualFrame;
        }

        public void setSupportDualFrame(boolean z) {
            this.isSupportDualFrame = z;
        }
    }
}
