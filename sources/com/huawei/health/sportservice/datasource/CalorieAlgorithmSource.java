package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.fgm;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 3, name = ComponentName.CALORIE_ALGORITHM_SOURCE_SOURCE)
/* loaded from: classes8.dex */
public class CalorieAlgorithmSource extends BaseSource<Integer> implements SportLifecycle {
    private static final float FAST_METS = 12.0f;
    private static final int FAST_SPEED = 150;
    private static final float MID_METS = 10.0f;
    protected static final float PARAM = 0.0167f;
    private static final float SLOW_METS = 8.0f;
    private static final int SLOW_SPEED = 80;
    private static final String TAG = "SportService_CalorieAlgorithmSource";
    private static final float THOUSAND_CONVERSION = 1000.0f;
    protected float mCaloriesValue;
    private SportLaunchParams mSportLaunchParams;
    protected float mUserWeight = 60.0f;
    private fgm mSportCallbackOption = new fgm();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo == null) {
            LogUtil.b(TAG, "accountInfo is null");
            return;
        }
        float weightOrDefaultValue = userInfo.getWeightOrDefaultValue();
        if (weightOrDefaultValue < 1.0E-7d) {
            this.mUserWeight = 60.0f;
        } else {
            this.mUserWeight = weightOrDefaultValue;
            LogUtil.a(TAG, "mUserWeight: ", Float.valueOf(weightOrDefaultValue));
        }
        this.mSportLaunchParams = (SportLaunchParams) BaseSportManager.getInstance().getParas(SportParamsType.SPORT_LAUNCH_PARAS);
        this.mSportCallbackOption.a(getSubscribeList());
        this.mSportCallbackOption.c(getLogTag());
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.datasource.CalorieAlgorithmSource$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                CalorieAlgorithmSource.this.m455x8bcb0f9b(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-datasource-CalorieAlgorithmSource, reason: not valid java name */
    /* synthetic */ void m455x8bcb0f9b(List list, Map map) {
        LogUtil.a(TAG, "tagList: ", list, ", sportDataList: ", map);
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            Object obj = map.get("SKIP_SPEED_DATA");
            if (obj instanceof Float) {
                this.mCaloriesValue += calculateSkipCalorie(((Float) obj).floatValue());
                updateSourceData();
            }
        }
    }

    private List<String> getSubscribeList() {
        ArrayList arrayList = new ArrayList();
        if (BaseSportManager.getInstance().getSportType() == 700001) {
            arrayList.add("AI_TRAIN_VALID_TIMES");
            return arrayList;
        }
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("SKIP_SPEED_DATA");
        return arrayList;
    }

    protected float calculateSkipCalorie(float f) {
        float f2;
        float f3;
        if (f > 150.0f) {
            f2 = this.mUserWeight;
            f3 = 0.2004f;
        } else if (f > 80.0f) {
            f2 = this.mUserWeight;
            f3 = 0.167f;
        } else {
            if (f <= 0.0f) {
                return 0.0f;
            }
            f2 = this.mUserWeight;
            f3 = 0.1336f;
        }
        return (f2 * f3) / 60.0f;
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(TAG, "CALORIES_DATA", Integer.valueOf(Math.round(this.mCaloriesValue)));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
