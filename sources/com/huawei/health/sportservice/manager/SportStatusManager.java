package com.huawei.health.sportservice.manager;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.SPORT_STATUS_MANAGER)
/* loaded from: classes4.dex */
public class SportStatusManager implements ManagerComponent {
    private static final String TAG = "SportService_SportStatusManager";
    private int mStatus = 0;
    private final Map<String, SportLifecycle> mListeners = Collections.synchronizedMap(new LinkedHashMap());

    public int registerStatusListener(String str, SportLifecycle sportLifecycle) {
        if (sportLifecycle != null && str != null && this != sportLifecycle) {
            LogUtil.c(TAG, "registerStatusListener() tag = ", str, " sportLifecycle = ", sportLifecycle);
            this.mListeners.put(str, sportLifecycle);
        } else {
            Object[] objArr = new Object[4];
            objArr[0] = "registerStatusListener() wrong ";
            objArr[1] = str;
            objArr[2] = "sportLifecycle is null";
            objArr[3] = Boolean.valueOf(sportLifecycle == null);
            ReleaseLogUtil.b(TAG, objArr);
        }
        return this.mStatus;
    }

    public void unRegisterStatus(String str) {
        if (str != null && this.mListeners.containsKey(str)) {
            LogUtil.c(TAG, "unRegisterStatus  mListeners, tag = ", str, " observe = ", this.mListeners.get(str));
            this.mListeners.remove(str);
        } else {
            ReleaseLogUtil.b(TAG, "unRegisterStatus wrong, tag = ", str);
        }
    }

    public int getSportStatus() {
        return this.mStatus;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport(String str) {
        this.mStatus = 7;
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.mListeners);
        LogUtil.c(TAG, "onPreSport() tempListeners ", linkedHashMap, " source ", str);
        for (SportLifecycle sportLifecycle : linkedHashMap.values()) {
            try {
                sportLifecycle.onPreSport(str);
            } catch (Exception e) {
                LogUtil.e(TAG, "onPreSport() listener: ", sportLifecycle, ", caused by: ", LogUtil.a(e));
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown(String str) {
        this.mStatus = 6;
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.mListeners);
        LogUtil.c(TAG, "onCountDown() tempListeners ", linkedHashMap, " source ", str);
        for (SportLifecycle sportLifecycle : linkedHashMap.values()) {
            try {
                sportLifecycle.onCountDown(str);
            } catch (Exception e) {
                LogUtil.e(TAG, "onCountDown() listener: ", sportLifecycle, ", caused by: ", LogUtil.a(e));
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport(String str) {
        this.mStatus = 1;
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.mListeners);
        LogUtil.c(TAG, "onStartSport() tempListeners ", linkedHashMap, " source ", str);
        for (SportLifecycle sportLifecycle : linkedHashMap.values()) {
            try {
                sportLifecycle.onStartSport(str);
            } catch (Exception e) {
                LogUtil.e(TAG, "onStartSport() listener: ", sportLifecycle, ", caused by: ", LogUtil.a(e));
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport(String str) {
        this.mStatus = 1;
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.mListeners);
        LogUtil.c(TAG, "onResumeSport() tmpListenerMap ", linkedHashMap, " source ", str);
        for (SportLifecycle sportLifecycle : linkedHashMap.values()) {
            try {
                sportLifecycle.onResumeSport(str);
            } catch (Exception e) {
                LogUtil.e(TAG, "onResumeSport() listener: ", sportLifecycle, ", caused by: ", LogUtil.a(e));
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport(String str) {
        this.mStatus = 2;
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.mListeners);
        LogUtil.c(TAG, "onPauseSport() tmpListenerMap ", linkedHashMap, " source ", str);
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            try {
                ((SportLifecycle) entry.getValue()).onPauseSport(str);
            } catch (Exception e) {
                LogUtil.e(TAG, "onPauseSport() listener: ", entry.getValue(), ", caused by: ", LogUtil.a(e));
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport(String str) {
        this.mStatus = 3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.mListeners);
        LogUtil.c(TAG, "onStopSport() tmpListenerMap: ", linkedHashMap, " source ", str);
        for (SportLifecycle sportLifecycle : linkedHashMap.values()) {
            try {
                sportLifecycle.onStopSport(str);
            } catch (Exception e) {
                LogUtil.e(TAG, "onStopSport() listener: ", sportLifecycle, ", caused by: ", LogUtil.a(e));
            }
        }
    }
}
