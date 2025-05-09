package defpackage;

import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiRegisterClientListener;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class djo {
    public HiDeviceInfo e(c cVar) {
        return cVar.d();
    }

    public void a(HiDeviceInfo hiDeviceInfo, HiRegisterClientListener hiRegisterClientListener) {
        HiHealthManager.d(BaseApplication.getContext()).registerDataClient(hiDeviceInfo, b(), hiRegisterClientListener);
    }

    public List<Integer> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        return arrayList;
    }

    public static class c {
        private HiDeviceInfo d;
        private String e;

        public c(String str, String str2) {
            this.e = str2;
            String e = dij.e(str);
            ProductMapInfo d = ProductMap.d(e);
            HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(d != null ? d.c() : 1);
            this.d = hiDeviceInfo;
            hiDeviceInfo.setProdId(e);
            this.d.setDeviceUniqueCode(this.e);
        }

        public c d(DeviceInformation deviceInformation) {
            this.d.setDeviceSN(deviceInformation.getSerialNumber());
            this.d.setDeviceName(deviceInformation.getBleDeviceName());
            this.d.setFirmwareVersion(deviceInformation.getFirmwareVersion());
            this.d.setSoftwareVersion(deviceInformation.getSoftwareVersion());
            this.d.setHardwareVersion(deviceInformation.getHardwareVersion());
            this.d.setModel(deviceInformation.getModelString());
            this.d.setManufacturer(deviceInformation.getManufacturerString());
            return this;
        }

        public c c(com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation deviceInformation) {
            this.d.setDeviceSN(deviceInformation.getSerialNumber());
            MeasurableDevice bondedDeviceByUniqueId = cei.b().getBondedDeviceByUniqueId(this.e, false);
            this.d.setDeviceName(bondedDeviceByUniqueId != null ? bondedDeviceByUniqueId.getDeviceName() : "");
            this.d.setSoftwareVersion(deviceInformation.getSoftwareVersion());
            this.d.setHardwareVersion(deviceInformation.getHardwareVersion());
            this.d.setModel(deviceInformation.getModelString());
            this.d.setManufacturer(deviceInformation.getManufacturerString());
            return this;
        }

        public HiDeviceInfo d() {
            return this.d;
        }
    }
}
