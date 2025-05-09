package com.huawei.health.sportservice.dataproducer;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.cei;
import defpackage.lau;
import defpackage.lbd;
import defpackage.lbv;
import health.compact.a.CommonUtils;

@SportComponentType(classify = 2, name = ComponentName.INDOOR_DEVICE_INFO_PRODUCER)
/* loaded from: classes8.dex */
public class IndoorDeviceInfoProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_IndoorDeviceInfoProducer";
    private static final int TIME_1000MS = 1000;
    private DeviceInformation mDeviceInformation;
    private String mDeviceMacAddress = Constants.LINK;
    private boolean mIsFitnessMachineProfile;
    private lbd mSportBiReportController;

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        if (obj instanceof DeviceInformation) {
            this.mDeviceInformation = (DeviceInformation) obj;
            onStagingAndNotification();
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        DeviceInformation deviceInformation = this.mDeviceInformation;
        if (deviceInformation == null) {
            return;
        }
        LogUtil.a(TAG, "onStagingAndNotification, mDeviceInformation = ", deviceInformation);
        BaseSportManager.getInstance().stagingAndNotification("INDOOR_DEVICE_INFO_DATA", this.mDeviceInformation);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        this.mIsFitnessMachineProfile = lau.d().g();
        this.mDeviceMacAddress = lau.d().b();
        BaseSportManager.getInstance().subscribeToSource("INDOOR_DEVICE_INFO_DATA", this);
        lbd d = lbd.d(BaseApplication.e());
        this.mSportBiReportController = d;
        d.b();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        if (this.mSportBiReportController != null) {
            reportFinishSportBiEvent();
            this.mSportBiReportController.e(this.mDeviceMacAddress);
        }
    }

    private void reportFinishSportBiEvent() {
        DeviceInformation deviceInformation = this.mDeviceInformation;
        if (deviceInformation == null || deviceInformation.getDeviceType() != 283) {
            LogUtil.a(TAG, "mDeviceInformation is null or device is not ropeSkipping device");
        } else {
            lbv.e(this.mSportBiReportController.e(this.mDeviceInformation.getModelString(), this.mDeviceInformation.getDeviceType()), CommonUtils.h(String.valueOf(BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION"))) / 1000, cei.b().getCurrentMacAddress(), 283);
        }
    }
}
