package com.huawei.indoorequip.device;

import android.os.Bundle;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.hmf.annotation.ApiDefine;
import defpackage.lau;
import java.util.Map;

@ApiDefine(uri = IndoorEquipManagerApi.class)
/* loaded from: classes5.dex */
public class IndoorEquipManagerImpl implements IndoorEquipManagerApi {
    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void init() {
        lau.d().c();
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void setMessageOrStateCallback(String str, MessageOrStateCallback messageOrStateCallback, boolean z) {
        lau.d().e(str, messageOrStateCallback);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void disconnect(boolean z) {
        lau.d().c(z);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void connectByMac(boolean z, String str) {
        lau.d().d(z, str);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public boolean reConnect() {
        return lau.d().h();
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void connectByName(boolean z, String str) {
        lau.d().a(z, str);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void onlyConnectByMacOrName(String str, String str2) {
        lau.d().e(str, str2);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void startDiscoverServices() {
        lau.d().m();
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public DeviceInformation getDeviceInformation() {
        return lau.d().e();
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void cancelPairing() {
        lau.d().a();
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void sendByteToEquip(byte[] bArr) {
        lau.d().e(bArr);
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void setFitnessMachineControl(int i, int[] iArr) {
        lau.d().b(i, iArr);
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void setFitnessMachineControl(int i, int i2, int[] iArr) {
        lau.d().b(i, i, iArr);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void setHeartRateFromWearable(int i) {
        lau.d().d(i);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void setRealStartWorkout() {
        lau.d().l();
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public String getCurrentMacAddress() {
        return lau.d().b();
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void removeMessageOrStateCallback(String str, boolean z) {
        lau.d().b(str, z);
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void releaseResource() {
        lau.d().o();
        lau.d().b(false);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void stopThreadsInCsafeController() {
        lau.d().q();
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void setHasExperiencedStateOfStartForFtmp(boolean z) {
        lau.d().d(z);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void setDeviceType(String str) {
        lau.d().c(str);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void setSuppressPause(boolean z) {
        lau.d().a(z);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public boolean isFitnessMachineProfile() {
        return lau.d().g();
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void stopSport() {
        lau.d().p();
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public boolean isDeviceBtConnected() {
        return lau.d().j();
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void unPair(String str) {
        lau.d().e(str);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void setUnlockCode(int[] iArr) {
        lau.d().a(iArr);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void setSendCalorieToDevice(boolean z) {
        lau.d().b(z);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public void sendExtensionCollectionControl(Map<String, String> map) {
        lau.d().a(map);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public boolean isStartByDevice(Bundle bundle) {
        return lau.d().bVg_(bundle);
    }

    @Override // com.huawei.health.device.api.IndoorEquipManagerApi
    public boolean isStopByDevice(Bundle bundle) {
        return lau.d().bVh_(bundle);
    }
}
