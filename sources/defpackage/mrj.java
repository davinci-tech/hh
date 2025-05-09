package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginachievement.AchieveNavigationApi;
import com.huawei.pluginachievement.manager.model.PersonalData;
import health.compact.a.Services;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class mrj {
    public static void com_(Context context, Uri uri, int i) {
        if (uri == null) {
            LogUtil.h("KaKaJumpUtil", "achieveJump, kakaMessage, and detailUri is null.");
            return;
        }
        AchieveNavigationApi achieveNavigationApi = (AchieveNavigationApi) Services.a("PluginAchievement", AchieveNavigationApi.class);
        String queryParameter = uri.getQueryParameter("type");
        if ("unclaimedKaka".equals(queryParameter)) {
            LogUtil.a("KaKaJumpUtil", "achieveJump, and type is: ", queryParameter);
            HashMap hashMap = new HashMap(2);
            hashMap.put("from", "2");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_KAKA_1100007.value(), hashMap, 0);
            if (achieveNavigationApi != null) {
                achieveNavigationApi.showAchieveKaka(context);
                return;
            } else {
                LogUtil.a("KaKaJumpUtil", "host: kakaMessage", ",achieveNavigationApi is null");
                return;
            }
        }
        if ("kakaExpiration".equals(queryParameter)) {
            LogUtil.a("KaKaJumpUtil", "achieveJump, and type is: ", queryParameter);
            if (i == 2) {
                HashMap hashMap2 = new HashMap(3);
                hashMap2.put("click", 1);
                hashMap2.put("event", 0);
                hashMap2.put("from", Integer.valueOf(i));
                ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.SUCCESSES_KAKA_1100082.value(), hashMap2, 0);
            }
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getContext(), PersonalData.CLASS_NAME_PERSONAL_KAKA_DETAIL);
            Bundle bundle = new Bundle();
            bundle.putString("tag", JsbMapKeyNames.H5_TEXT_DETAIL);
            intent.putExtra("tag", bundle);
            intent.putExtra("from", i);
            intent.setFlags(268435456);
            jdw.bGh_(intent, BaseApplication.getContext());
            return;
        }
        LogUtil.h("KaKaJumpUtil", "achieveJump, unknown type. no jump");
    }
}
