package com.huawei.health.sportservice.manager.supinelegraise;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.manager.AchieveModule;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.ManagerComponent;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.SportAchieveSubscribe;
import com.huawei.hwsportmodel.SupineLegAchieveType;
import defpackage.fgm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.SUPINE_LEG_ACHIEVE_MANAGER)
/* loaded from: classes8.dex */
public class SupineLegRaiseAchieveManager implements ManagerComponent, AchieveModule {
    private static final String TAG = "SportService_SupineLegRaiseAchieveManager";
    private Map<Integer, SupineLegAchieveType> mAchieveVoiceMap = new HashMap();
    private Map<String, SportAchieveSubscribe> mSubscribeMap = new HashMap();
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        SportDataNotify sportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.supinelegraise.SupineLegRaiseAchieveManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                SupineLegRaiseAchieveManager.this.m491x149dbfbf(list, map);
            }
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add("SPORT_EXAM_SCORE");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, sportDataNotify);
        for (SupineLegAchieveType supineLegAchieveType : SupineLegAchieveType.values()) {
            if (supineLegAchieveType != null) {
                this.mAchieveVoiceMap.put(Integer.valueOf(supineLegAchieveType.getLegRaiseTimes()), supineLegAchieveType);
            }
        }
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-supinelegraise-SupineLegRaiseAchieveManager, reason: not valid java name */
    /* synthetic */ void m491x149dbfbf(List list, Map map) {
        if (list == null || map == null) {
            LogUtil.h(TAG, "tagList == null || sportDataMap == null");
        } else if (list.contains("SPORT_EXAM_SCORE")) {
            int intValue = ((Integer) map.get("SPORT_EXAM_SCORE")).intValue();
            if (this.mAchieveVoiceMap.containsKey(Integer.valueOf(intValue))) {
                notifyMap(this.mAchieveVoiceMap.get(Integer.valueOf(intValue)));
            }
        }
    }

    private void notifyMap(SupineLegAchieveType supineLegAchieveType) {
        Iterator<SportAchieveSubscribe> it = this.mSubscribeMap.values().iterator();
        while (it.hasNext()) {
            it.next().onChange(supineLegAchieveType);
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
