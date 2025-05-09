package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.haf.router.AppRouter;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.AchieveNavigationApi;
import com.huawei.pluginachievement.manager.model.PersonalData;
import health.compact.a.HuaweiHealth;

@ApiDefine(uri = AchieveNavigationApi.class)
/* loaded from: classes6.dex */
public class mcw implements AchieveNavigationApi {
    @Override // com.huawei.pluginachievement.AchieveNavigationApi
    public void showAchieveReport(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showAchieveReport()");
        Intent intent = new Intent();
        intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_REPORT);
        context.startActivity(intent);
    }

    @Override // com.huawei.pluginachievement.AchieveNavigationApi
    public void showAchieveKaka(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showAchieveKaka()");
        Intent intent = new Intent();
        intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_KAKA);
        context.startActivity(intent);
        mfg.c(context);
    }

    @Override // com.huawei.pluginachievement.AchieveNavigationApi
    public void showMyAwardPage(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showMyAwardPage()");
        AppRouter.b("/HWUserProfileMgr/MyAwardActivity").c(context);
    }

    @Override // com.huawei.pluginachievement.AchieveNavigationApi
    public void showAchieveMedal(Context context) {
        if (context == null) {
            return;
        }
        if (mlb.b()) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "showAchieveMedal isMedalFastClick!");
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showAchieveMedal()");
        Intent intent = new Intent();
        intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_NEW_MEDALS);
        gnm.aPB_(context, intent);
    }

    @Override // com.huawei.pluginachievement.AchieveNavigationApi
    public void showAchieveMedalById(Context context, String str, String str2) {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showAchieveMedalById()");
        if (context == null) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "context is null");
        } else {
            mcv.d(HuaweiHealth.a()).c(context, str, str2);
        }
    }
}
