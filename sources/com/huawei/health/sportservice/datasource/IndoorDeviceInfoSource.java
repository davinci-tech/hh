package com.huawei.health.sportservice.datasource;

import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.INDOOR_DEVICE_INFO_SOURCE)
/* loaded from: classes8.dex */
public class IndoorDeviceInfoSource extends BaseSource implements SportLifecycle, IndoorToSource {
    private static final String TAG = "SportService_IndoorDeviceInfoSource";
    private DeviceInformation mDeviceInformation = new DeviceInformation();

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void generateData(Object obj) {
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(getLogTag(), "INDOOR_DEVICE_INFO_DATA", this.mDeviceInformation);
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        if (obj == null) {
            ReleaseLogUtil.c(TAG, "value == null type = ", Integer.valueOf(i));
            return;
        }
        if (i == 20010) {
            this.mDeviceInformation.setSerialNumber(obj.toString());
            return;
        }
        if (i != 20014) {
            switch (i) {
                case 20005:
                    this.mDeviceInformation.setDeviceType(((Integer) obj).intValue());
                    break;
                case 20006:
                    this.mDeviceInformation.setManufacturerString(obj.toString());
                    break;
                case 20007:
                    this.mDeviceInformation.setModelString(obj.toString());
                    updateSourceData();
                    break;
            }
            return;
        }
        this.mDeviceInformation.setBleDeviceName(obj.toString());
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(20005);
        arrayList.add(20006);
        arrayList.add(20010);
        arrayList.add(20014);
        arrayList.add(20007);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
