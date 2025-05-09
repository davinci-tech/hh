package com.huawei.pluginachievement.manager.service;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import defpackage.mcz;
import defpackage.meh;
import defpackage.mes;

/* loaded from: classes6.dex */
public class AchieveLevelInfoObserver implements AchieveObserver {
    private Context b;

    public AchieveLevelInfoObserver(Context context) {
        this.b = context;
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        if (i == -1 || userAchieveWrapper == null) {
            LogUtil.a("PLGACHIEVE_AchieveLevelInfoObserver", "HttpErrCode or wrapper is error");
            return;
        }
        int contentType = userAchieveWrapper.getContentType();
        LogUtil.a("PLGACHIEVE_AchieveLevelInfoObserver", "AchieveLevelInfoObserver|onDataChanged contentType = ", Integer.valueOf(contentType));
        if (contentType == 13) {
            for (mcz mczVar : userAchieveWrapper.toList()) {
                if (mczVar != null && (mczVar instanceof AchieveUserLevelInfo)) {
                    AchieveUserLevelInfo achieveUserLevelInfo = (AchieveUserLevelInfo) mczVar;
                    mes c = mes.c(this.b);
                    if (c != null) {
                        c.b(achieveUserLevelInfo);
                    }
                }
            }
            c();
        }
    }

    private void c() {
        LogUtil.a("PLGACHIEVE_AchieveLevelInfoObserver", "enter releaseManager");
        meh.c(BaseApplication.getContext()).c(this);
    }
}
