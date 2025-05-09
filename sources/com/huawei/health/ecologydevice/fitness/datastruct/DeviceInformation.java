package com.huawei.health.ecologydevice.fitness.datastruct;

import android.text.TextUtils;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class DeviceInformation extends BaseRopeData {
    private static final String DEVICE_INFO_DEFAULT = "unknown";
    private static final int FLAG_ALL_SET = 31;
    private static final int FLAG_HARDWARE_VERSION = 4;
    private static final int FLAG_MANUFACTURER = 1;
    private static final int FLAG_MODEL = 2;
    private static final int FLAG_SN = 16;
    private static final int FLAG_SOFTWARE_VERSION = 8;
    private int flag;

    public DeviceInformation() {
        super(23);
        this.mFitnessData = new HashMap<>(10);
        this.mFitnessData.clear();
    }

    public String getManufacturerString() {
        return this.mFitnessData.get(20006) instanceof String ? (String) this.mFitnessData.get(20006) : "unknown";
    }

    public void setManufacturerString(String str) {
        this.flag |= 1;
        this.mFitnessData.put(20006, str);
    }

    public String getModelString() {
        return this.mFitnessData.get(20007) instanceof String ? (String) this.mFitnessData.get(20007) : "unknown";
    }

    public void setModelString(String str) {
        this.flag |= 2;
        this.mFitnessData.put(20007, str);
    }

    public String getHardwareVersion() {
        return String.valueOf(this.mFitnessData.get(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI)));
    }

    public void setHardwareVersion(String str) {
        this.flag |= 4;
        this.mFitnessData.put(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), str);
    }

    public String getSoftwareVersion() {
        String valueOf = String.valueOf(this.mFitnessData.get(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_APPLICATION)));
        return TextUtils.isEmpty(valueOf) ? "unknown" : valueOf;
    }

    public void setSoftwareVersion(String str) {
        this.flag |= 8;
        this.mFitnessData.put(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_APPLICATION), str);
    }

    public String getSerialNumber() {
        String valueOf = String.valueOf(this.mFitnessData.get(20010));
        return TextUtils.isEmpty(valueOf) ? "unknown" : valueOf;
    }

    public void setSerialNumber(String str) {
        this.flag |= 16;
        this.mFitnessData.put(20010, str);
    }

    public void setDeviceType(int i) {
        this.mFitnessData.put(20005, Integer.valueOf(i));
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData
    public void clearData() {
        super.clearData();
        this.flag = 0;
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public boolean isAllSet() {
        return this.flag == 31;
    }
}
