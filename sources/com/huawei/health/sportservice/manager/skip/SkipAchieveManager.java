package com.huawei.health.sportservice.manager.skip;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.manager.AchieveModule;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.ManagerComponent;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwsportmodel.SkipAchieveType;
import com.huawei.hwsportmodel.SportAchieveSubscribe;
import defpackage.fgm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.SKIP_ACHIEVE_MANAGER)
/* loaded from: classes8.dex */
public class SkipAchieveManager implements ManagerComponent, AchieveModule {
    private static final String TAG = "SportService_SkipAchieveManager";
    private Map<Integer, SkipAchieveType> mAchieveVoiceMap = new HashMap();
    private Map<String, SportAchieveSubscribe> mSubscribeMap = new HashMap();
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        SportDataNotify sportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.skip.SkipAchieveManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                SkipAchieveManager.this.m485xc1d9ec5f(list, map);
            }
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add("SKIP_NUM_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, sportDataNotify);
        boolean z = ((double) (BaseSportManager.getInstance().getTargetValue() - 100.0f)) < 1.0E-7d && BaseSportManager.getInstance().getTargetType() == 5;
        for (SkipAchieveType skipAchieveType : SkipAchieveType.values()) {
            if (z) {
                this.mAchieveVoiceMap.put(Integer.valueOf(skipAchieveType.getSkipTimesForOneMinute()), skipAchieveType);
            } else {
                this.mAchieveVoiceMap.put(Integer.valueOf(skipAchieveType.getSkipTimes()), skipAchieveType);
            }
        }
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-skip-SkipAchieveManager, reason: not valid java name */
    /* synthetic */ void m485xc1d9ec5f(List list, Map map) {
        if (list.contains("SKIP_NUM_DATA")) {
            int intValue = ((Integer) map.get("SKIP_NUM_DATA")).intValue();
            if (this.mAchieveVoiceMap.containsKey(Integer.valueOf(intValue))) {
                notifyMap(this.mAchieveVoiceMap.get(Integer.valueOf(intValue)));
            }
        }
    }

    private void notifyMap(SkipAchieveType skipAchieveType) {
        Iterator<SportAchieveSubscribe> it = this.mSubscribeMap.values().iterator();
        while (it.hasNext()) {
            it.next().onChange(skipAchieveType);
        }
    }

    @Override // com.huawei.health.sportservice.manager.AchieveModule
    public void registerAchieveLevel(String str, SportAchieveSubscribe sportAchieveSubscribe) {
        if (str == null || sportAchieveSubscribe == null || this.mSubscribeMap.containsKey(str)) {
            return;
        }
        this.mSubscribeMap.put(str, sportAchieveSubscribe);
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }
}
