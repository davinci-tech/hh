package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.fgm;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class CalorieDataSource extends DataSource {
    private static final float FAST_METS = 12.0f;
    private static final int FAST_SPEED = 150;
    private static final float MID_METS = 10.0f;
    private static final float PARAM = 0.0167f;
    private static final float SLOW_METS = 8.0f;
    private static final int SLOW_SPEED = 80;
    private static final String TAG = "CalorieDataSource";
    protected float mCaloriesValue;
    protected float mUserWeight = 60.0f;

    @Override // com.huawei.health.sportservice.datasource.DataSource
    public void init() {
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
            LogUtil.a(TAG, "UserWeight", Float.valueOf(weightOrDefaultValue));
        }
        fgm fgmVar = new fgm();
        SportDataNotify sportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.datasource.CalorieDataSource$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                CalorieDataSource.this.m456x1f20ccd4(list, map);
            }
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_ONE_SECOND_TIMESTAMP");
        fgmVar.a(arrayList);
        fgmVar.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(fgmVar, sportDataNotify);
    }

    /* renamed from: lambda$init$0$com-huawei-health-sportservice-datasource-CalorieDataSource, reason: not valid java name */
    /* synthetic */ void m456x1f20ccd4(List list, Map map) {
        if (BaseSportManager.getInstance().getData("SKIP_SPEED_DATA") != null) {
            this.mCaloriesValue += calculateSkipCalorie(((Float) BaseSportManager.getInstance().getData("SKIP_SPEED_DATA")).floatValue());
            this.mDataSourceCallback.onCalorieChange(this.mCaloriesValue);
        }
    }

    private float calculateSkipCalorie(float f) {
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

    @Override // com.huawei.health.sportservice.datasource.DataSource
    public void destroy() {
        super.destroy();
    }
}
