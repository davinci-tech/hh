package com.huawei.health.sportservice.manager;

import android.os.Bundle;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.sportservice.datasource.SkipDataAiSource;
import com.huawei.health.sportservice.inter.EcologySkipApi;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cei;
import defpackage.gve;
import defpackage.lau;
import defpackage.mwq;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.ECOLOGY_MANAGER)
/* loaded from: classes4.dex */
public class EcologyManager implements ManagerComponent, EcologySkipApi {
    private static final String TAG = "SportService_EcologyManager";
    private int mSportType;
    private final Map<Integer, List<IndoorToSource>> mSourceMap = new LinkedHashMap();
    private final MessageOrStateCallback mMessageOrStateCallback = new MessageOrStateCallback() { // from class: com.huawei.health.sportservice.manager.EcologyManager.1
        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onNewMessage(int i, Bundle bundle) {
            if (i == 905) {
                BaseSportManager.getInstance().m134x32b3e3a1();
            } else if (bundle == null) {
                LogUtil.a(EcologyManager.TAG, "bundle == null ");
            } else {
                LogUtil.a(EcologyManager.TAG, "MessageOrStateCallback ", bundle.toString());
                EcologyManager.this.updateDataToSource(bundle);
            }
        }

        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onStateChange(String str) {
            LogUtil.a(EcologyManager.TAG, "onStateChange() state: ", str);
            if (str != null) {
                EcologyManager.this.setBleStateListener(str);
            }
            if (AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED.equals(str)) {
                EcologyManager.this.destroy();
            }
        }
    };

    public boolean isIndoorEquipmentFtmp() {
        return true;
    }

    public void subscribeToIndoorEquipment(List<Integer> list, IndoorToSource indoorToSource) {
        List<IndoorToSource> list2;
        LogUtil.a(TAG, "subscribeToIndoorEquipment");
        for (Integer num : list) {
            if (!this.mSourceMap.containsKey(num)) {
                list2 = new ArrayList<>();
            } else {
                list2 = this.mSourceMap.get(num);
            }
            list2.add(indoorToSource);
            this.mSourceMap.put(num, list2);
        }
        LogUtil.a(TAG, "subscribeToIndoorEquipment tags:", list, " indoorToSource:", indoorToSource);
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        int sportType = BaseSportManager.getInstance().getSportType();
        this.mSportType = sportType;
        if (sportType == 283) {
            cei.b().setMessageOrStateCallback(TAG, this.mMessageOrStateCallback, true);
        } else {
            LogUtil.a(TAG, "MessageOrStateCallback ecologyManager");
            lau.d().e(TAG, this.mMessageOrStateCallback);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        lau.d().d(true);
        lau.d().l();
        gve.d();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a(TAG, "onStopSport Enter");
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        LogUtil.a(TAG, "destroy");
        if (this.mSportType == 283) {
            cei.b().removeMessageOrStateCallback(TAG, true);
        } else {
            lau.d().b(TAG, true);
        }
        gve.b();
    }

    @Override // com.huawei.health.sportservice.inter.EcologySkipApi
    public void updateDataToSource(Bundle bundle) {
        if (bundle != null && (bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA") instanceof HashMap)) {
            HashMap hashMap = (HashMap) bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA");
            if (hashMap == null) {
                ReleaseLogUtil.c(TAG, "setData indoorLinkData is null.");
                return;
            }
            LogUtil.a(TAG, "updateDataToSource data:", hashMap);
            for (Map.Entry entry : hashMap.entrySet()) {
                convertDataFromBle(((Integer) entry.getKey()).intValue(), entry.getValue());
            }
        }
    }

    private <E> void convertDataFromBle(int i, E e) {
        if (this.mSourceMap.containsKey(Integer.valueOf(i))) {
            Iterator<IndoorToSource> it = this.mSourceMap.get(Integer.valueOf(i)).iterator();
            while (it.hasNext()) {
                it.next().onIndoorDataChanged(i, e);
            }
        }
    }

    @Override // com.huawei.health.sportservice.inter.EcologySkipApi
    public void setAiRopeListener(SkipDataAiSource skipDataAiSource) {
        if (skipDataAiSource != null) {
            mwq.d().setRopeListener(skipDataAiSource);
        }
    }

    @Override // com.huawei.health.sportservice.inter.EcologySkipApi
    public void setBleStateListener(String str) {
        BaseSportManager.getInstance().setBleState(str);
    }
}
