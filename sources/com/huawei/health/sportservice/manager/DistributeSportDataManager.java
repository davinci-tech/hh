package com.huawei.health.sportservice.manager;

import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cei;
import defpackage.ffd;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.lau;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.DISTRIBUTE_MANAGER)
/* loaded from: classes8.dex */
public class DistributeSportDataManager implements ManagerComponent, ITargetUpdateListener {
    private static final String TAG = "SportService_DistributeSportDataManager";
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("STEP_DATA");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        final boolean i = lau.d().i();
        LogUtil.a(TAG, " instant calorie is  ", Boolean.valueOf(i));
        if (i) {
            arrayList.add("CALORIES_DATA");
        }
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.DistributeSportDataManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                DistributeSportDataManager.lambda$onPreSport$0(i, list, map);
            }
        });
        if (BaseSportManager.getInstance().getSportType() == 283) {
            SportLaunchParams sportLaunchParams = (SportLaunchParams) BaseSportManager.getInstance().getParas(SportParamsType.SPORT_LAUNCH_PARAS);
            cei.b().setSkippingTargetMode(new SkippingTargetMode(BaseSportManager.getInstance().getTargetType(), (int) BaseSportManager.getInstance().getTargetValue(), sportLaunchParams != null ? (IntermitentJumpData) sportLaunchParams.getExtra("type_intermittent_jump_mode_data", IntermitentJumpData.class) : null));
        }
    }

    static /* synthetic */ void lambda$onPreSport$0(boolean z, List list, Map map) {
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            BaseSportManager baseSportManager = BaseSportManager.getInstance();
            int intValue = ((Integer) baseSportManager.getData("STEP_DATA")).intValue();
            int intValue2 = ((Integer) baseSportManager.getData("HEART_RATE_DATA")).intValue();
            HashMap hashMap = new HashMap();
            LogUtil.a(TAG, " instant step is  ", Integer.valueOf(intValue), "and heart rate is ", Integer.valueOf(intValue2));
            if (fhs.c(intValue2)) {
                hashMap.put(IndoorEquipManagerApi.KEY_HEART_RATE, String.valueOf(intValue2));
            }
            if (intValue > 0) {
                hashMap.put(IndoorEquipManagerApi.KEY_STEP_COUNT, String.valueOf(intValue));
            }
            if (z) {
                int intValue3 = ((Integer) baseSportManager.getData("CALORIES_DATA")).intValue();
                LogUtil.a(TAG, " instant calorie is  ", Integer.valueOf(intValue3));
                if (intValue3 > 0) {
                    hashMap.put(IndoorEquipManagerApi.KEY_TOTAL_ENERGY, String.valueOf(intValue3));
                }
            }
            if (hashMap.size() > 0) {
                ((IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class)).sendExtensionCollectionControl(hashMap);
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a(TAG, "onStartSport");
        BaseSportManager.getInstance().subscribeTargetStatus(TAG, this);
    }

    private void notifyDataWhenTargetFinished() {
        if (lau.d().i()) {
            int intValue = ((Integer) BaseSportManager.getInstance().getData("CALORIES_DATA")).intValue();
            HashMap hashMap = new HashMap();
            LogUtil.a(TAG, " notifyDataWhenTargetFinished calorie is  ", Integer.valueOf(intValue));
            if (intValue > 0) {
                hashMap.put(IndoorEquipManagerApi.KEY_TOTAL_ENERGY, String.valueOf(intValue));
                ((IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class)).sendExtensionCollectionControl(hashMap);
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onStateUpdate(int i, String str) {
        LogUtil.a(TAG, "type is ", Integer.valueOf(i), " command is ", str);
        if (i == 200) {
            notifyDataWhenTargetFinished();
        }
    }
}
