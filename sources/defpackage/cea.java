package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class cea {
    public static Map<String, String> d() {
        HashMap hashMap = new HashMap(9);
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.e());
        if (!loginInit.isBrowseMode()) {
            hashMap.put("x-huid", loginInit.getAccountInfo(1011));
            String accountInfo = loginInit.getAccountInfo(1008);
            hashMap.put(CloudParamKeys.X_TOKEN, accountInfo);
            hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + accountInfo);
        }
        hashMap.put(CloudParamKeys.X_SITE_ID, String.valueOf(loginInit.getAccountInfo(1009)));
        String c = CommonUtil.c(BaseApplication.e());
        hashMap.put("x-version", c);
        hashMap.put(CloudParamKeys.X_CLIENT_VERSION, c);
        hashMap.put(CloudParamKeys.X_TOKEN_TYPE, String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put(CloudParamKeys.X_APP_ID, BaseApplication.d());
        return hashMap;
    }
}
