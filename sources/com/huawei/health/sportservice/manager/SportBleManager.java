package com.huawei.health.sportservice.manager;

import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.sportservice.SportBleStatus;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.SPORT_BLE_MANAGER)
/* loaded from: classes4.dex */
public class SportBleManager implements SportBleStatus, ManagerComponent {
    private static final String TAG = "SportService_SportBleManager";
    private final Map<String, SportBleStatus> mListeners = new LinkedHashMap();
    private String mState;

    public void setBleState(String str) {
        this.mState = str;
        LogUtil.a(TAG, "setBleState() state: ", str);
        if (AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING.equals(str)) {
            onConnecting();
            return;
        }
        if (AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING.equals(str)) {
            onReconnecting();
            return;
        }
        if (AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED.equals(str)) {
            onConnected();
            return;
        }
        if (AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED.equals(str)) {
            onInterrupted();
            BaseSportManager.getInstance().onStopSport();
        } else {
            if (AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED.equals(str)) {
                onStateChanged(str);
                return;
            }
            if (AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED.equals(str)) {
                onRechecking();
            } else if (AbstractFitnessClient.ACTION_READ_SUCCESS.equals(str)) {
                onReadSuccess();
            } else {
                onStateChanged(str);
            }
        }
    }

    public String getBleState() {
        return this.mState;
    }

    public String registerBleStatusListener(String str, SportBleStatus sportBleStatus) {
        Map<String, SportBleStatus> map;
        if (sportBleStatus != null && str != null && (map = this.mListeners) != null && !map.containsKey(str)) {
            LogUtil.a(TAG, "registerBleStatusListener() tag: ", str, ", sportBleStatus: ", sportBleStatus);
            this.mListeners.put(str, sportBleStatus);
        }
        return this.mState;
    }

    public void unregisterBleStatusListener(String str) {
        if (str == null || this.mListeners == null) {
            return;
        }
        LogUtil.a(TAG, "unregisterBleStatusListener() tag: ", str);
        this.mListeners.remove(str);
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onConnecting() {
        LogUtil.a(TAG, "onConnecting() mListeners: ", this.mListeners);
        Iterator<SportBleStatus> it = this.mListeners.values().iterator();
        while (it.hasNext()) {
            it.next().onConnecting();
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onConnected() {
        LogUtil.a(TAG, "onConnected() mListeners: ", this.mListeners);
        Iterator<SportBleStatus> it = this.mListeners.values().iterator();
        while (it.hasNext()) {
            it.next().onConnected();
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onReconnecting() {
        LogUtil.a(TAG, "onReconnecting() mListeners: ", this.mListeners);
        Iterator<SportBleStatus> it = this.mListeners.values().iterator();
        while (it.hasNext()) {
            it.next().onReconnecting();
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onChecking() {
        LogUtil.a(TAG, "onChecking() mListeners: ", this.mListeners);
        Iterator<SportBleStatus> it = this.mListeners.values().iterator();
        while (it.hasNext()) {
            it.next().onChecking();
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onRechecking() {
        LogUtil.a(TAG, "onRechecking() mListeners: ", this.mListeners);
        Iterator<SportBleStatus> it = this.mListeners.values().iterator();
        while (it.hasNext()) {
            it.next().onRechecking();
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onInterrupted() {
        LogUtil.a(TAG, "onInterrupted() mListeners: ", this.mListeners);
        Iterator<SportBleStatus> it = this.mListeners.values().iterator();
        while (it.hasNext()) {
            it.next().onInterrupted();
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onReadSuccess() {
        LogUtil.a(TAG, "onReadSuccess() mListeners: ", this.mListeners);
        Iterator<SportBleStatus> it = this.mListeners.values().iterator();
        while (it.hasNext()) {
            it.next().onReadSuccess();
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onStateChanged(String str) {
        LogUtil.a(TAG, "onStateChanged() mListeners: ", this.mListeners);
        Iterator<SportBleStatus> it = this.mListeners.values().iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(str);
        }
    }
}
