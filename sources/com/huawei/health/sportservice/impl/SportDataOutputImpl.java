package com.huawei.health.sportservice.impl;

import android.os.Bundle;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sportservice.ISportDataSaveStatusCallback;
import com.huawei.health.sportservice.SportBleStatus;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportObserverType;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwsportmodel.SportAchieveSubscribe;
import defpackage.fgm;
import defpackage.koq;
import java.util.Collections;
import java.util.List;

@ApiDefine(uri = SportDataOutputApi.class)
@Singleton
/* loaded from: classes4.dex */
public class SportDataOutputImpl implements SportDataOutputApi {
    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void onSportBleStateChange(String str) {
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean registerSportStatus(String str, SportLifecycle sportLifecycle) {
        return BaseSportManager.getInstance().registerSportStatus(str, sportLifecycle);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean unRegisterSportStatus(String str) {
        return BaseSportManager.getInstance().unRegisterSportStatus(str);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public int getStatus() {
        return BaseSportManager.getInstance().getStatus();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public int getSportType() {
        return BaseSportManager.getInstance().getSportType();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public Object getData(String str) {
        return BaseSportManager.getInstance().getData(str);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean isToSave() {
        return BaseSportManager.getInstance().isToSave();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void initModel(Bundle bundle) {
        BaseSportManager.getInstance().initModel(bundle);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void setParas(SportParamsType sportParamsType, Object obj) {
        BaseSportManager.getInstance().setParas(sportParamsType, obj);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public <T> T getParas(SportParamsType sportParamsType) {
        return (T) BaseSportManager.getInstance().getParas(sportParamsType);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean isAlreadyInit() {
        return BaseSportManager.getInstance().isAlreadyInit();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void destroyModel() {
        BaseSportManager.getInstance().destroyModel();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean subscribeNotify(fgm fgmVar, SportDataNotify sportDataNotify) {
        return BaseSportManager.getInstance().subscribeNotify(fgmVar, sportDataNotify);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean cancelSubscribeNotify(fgm fgmVar) {
        return BaseSportManager.getInstance().cancelSubscribeNotify(fgmVar);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void subscribeTargetStatus(String str, ITargetUpdateListener iTargetUpdateListener) {
        BaseSportManager.getInstance().subscribeTargetStatus(str, iTargetUpdateListener);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void registerAchieveLevel(String str, SportAchieveSubscribe sportAchieveSubscribe) {
        BaseSportManager.getInstance().registerAchieveLevel(str, sportAchieveSubscribe);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public int getDataSource() {
        return BaseSportManager.getInstance().getDataSource();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void onDestroy() {
        BaseSportManager.getInstance().onDestroy();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void registerDeviceDataCallback(ISportDataCallback iSportDataCallback) {
        BaseSportManager.getInstance().registerDeviceDataCallback(iSportDataCallback);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean registerSportBleStatus(String str, SportBleStatus sportBleStatus) {
        return BaseSportManager.getInstance().registerSportBleStatus(str, sportBleStatus);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean unRegisterSportBleStatus(String str) {
        return BaseSportManager.getInstance().unRegisterSportBleStatus(str);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean isSportServiceRunning() {
        return BaseSportManager.getInstance().isSportServiceRunning();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public List<Integer> connectSubDeviceStatus(List<Integer> list) {
        if (koq.b(list)) {
            return Collections.EMPTY_LIST;
        }
        return BaseSportManager.getInstance().subDeviceConnectNumber(list);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void addDeviceStatusCallback(String str, IBaseResponseCallback iBaseResponseCallback) {
        BaseSportManager.getInstance().addDeviceStatusCallback(str, iBaseResponseCallback);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public void removeDeviceStatusCallback(String str) {
        BaseSportManager.getInstance().removeDeviceStatusCallback(str);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean isRestart() {
        return BaseSportManager.getInstance().isRestart();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean registerSportDataSavedCallback(String str, ISportDataSaveStatusCallback iSportDataSaveStatusCallback) {
        return BaseSportManager.getInstance().registerSportDataSavedCallback(str, iSportDataSaveStatusCallback);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean unregisterSportDataSavedCallback(String str) {
        return BaseSportManager.getInstance().unregisterSportDataSavedCallback(str);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public int getTargetSportStatus() {
        return BaseSportManager.getInstance().getTargetSportStatus();
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean addObserver(SportObserverType sportObserverType, String str, Object obj) {
        return BaseSportManager.getInstance().addObserver(sportObserverType, str, obj);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean removeObserver(SportObserverType sportObserverType, String str) {
        return BaseSportManager.getInstance().removeObserver(sportObserverType, str);
    }

    @Override // com.huawei.health.sportservice.SportDataOutputApi
    public boolean isDeviceMirrorLink() {
        return BaseSportManager.getInstance().getDataSource() == 100;
    }
}
