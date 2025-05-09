package com.huawei.pluginsocialshare.cloud.themecloud;

import android.content.Context;
import android.os.Build;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.w9;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import defpackage.lql;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes6.dex */
public class ThemeDataFactory implements ParamsFactory {
    private Context d;

    public ThemeDataFactory(Context context) {
        if (context == null) {
            this.d = BaseApplication.e();
        } else {
            this.d = context.getApplicationContext();
        }
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put(j2.v, "gzip, deflate");
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put("Connection", w9.j);
        hashMap.put(ProfileRequestConstants.X_APPID_KEY, "10013");
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> d = lql.d(obj);
        if (d == null) {
            d = new HashMap<>();
        }
        String str = "1.0";
        try {
            Matcher matcher = Pattern.compile("^[0-9.,x]*").matcher(CommonUtil.e(this.d));
            if (matcher.find()) {
                str = matcher.group(0).replace(".", "");
            }
        } catch (PatternSyntaxException e) {
            LogUtil.e("ThemeDataFactory", "getHeaders ex=", LogUtil.a(e));
        }
        d.put("versionCode", str);
        d.put(RecommendConstants.VER, "1.7");
        d.put("userToken", LoginInit.getInstance(this.d).getAccountInfo(1008));
        d.put("deviceType", "9");
        d.put("appId", "3");
        d.put(FaqConstants.FAQ_EMUIVERSION, CommonUtil.r());
        d.put("deviceModel", Build.MODEL);
        d.put("buildNumber", Build.DISPLAY);
        d.put("resourceType", "2");
        d.put("listType", "2");
        d.put("isNeedTotalCount", "1");
        return d;
    }
}
