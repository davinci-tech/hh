package com.huawei.pluginachievement.manager.service;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.LevelUpdateReturnBody;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import defpackage.mct;
import defpackage.mcz;
import defpackage.meh;
import defpackage.mes;

/* loaded from: classes9.dex */
public class AchieveLevelEventObserver implements AchieveObserver {

    /* renamed from: a, reason: collision with root package name */
    private Context f8372a;

    public AchieveLevelEventObserver(Context context) {
        this.f8372a = context;
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        if (i == -1 || userAchieveWrapper == null) {
            LogUtil.a("PLGACHIEVE_AchieveLevelEventObserver", "HttpErrCode or wrapper is error");
            return;
        }
        int contentType = userAchieveWrapper.getContentType();
        LogUtil.a("PLGACHIEVE_AchieveLevelEventObserver", "AchieveMedalResDownloadObserver|onDataChanged contentType = ", Integer.valueOf(contentType));
        if (contentType == 14) {
            for (mcz mczVar : userAchieveWrapper.toList()) {
                if (mczVar instanceof LevelUpdateReturnBody) {
                    LevelUpdateReturnBody levelUpdateReturnBody = (LevelUpdateReturnBody) mczVar;
                    String b = mct.b(this.f8372a, "levelEventKey");
                    mes c = mes.c(this.f8372a);
                    if (!TextUtils.isEmpty(b) && c != null) {
                        mes.c(this.f8372a).d(b, levelUpdateReturnBody.acquireTotalExperience());
                    }
                }
            }
            d();
        }
    }

    private void d() {
        meh.c(BaseApplication.getContext()).c(this);
    }
}
