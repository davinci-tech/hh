package com.huawei.common.scope;

import com.huawei.common.OpAnalyticsApi;
import com.huawei.common.util.Utils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.lqi;
import java.io.IOException;
import java.net.URLEncoder;

/* loaded from: classes7.dex */
public class AccessScopeReport {
    private static final String PATH = "?nsp_svc=huawei.oauth2.user.getTokenInfo&open_id=OPENID&access_token=";
    private static final String TAG = "AccessScopeReport";

    public static void reportErrorScope(String str) {
        LoginApi loginApi = (LoginApi) lqi.d().b(LoginApi.class);
        if (loginApi == null) {
            LogUtil.h(TAG, "reportErrorScope callApi is null");
            return;
        }
        try {
            Response<GetAccessScopeRsp> execute = loginApi.getScopes(getUrl() + URLEncoder.encode(str, "utf-8")).execute();
            if (execute.isOK()) {
                GetAccessScopeRsp body = execute.getBody();
                String scope = body.getScope();
                String uid = body.getUid();
                OpAnalyticsApi opAnalyticsApi = LoginInit.getInstance(BaseApplication.getContext()).getOpAnalyticsApi();
                LogUtil.a(TAG, "scopes:", scope);
                if (opAnalyticsApi != null) {
                    opAnalyticsApi.onReport("AT_SCOPE_UNSATISIFIED", uid + scope);
                }
            } else {
                LogUtil.b(TAG, "reportErrorScope network error");
            }
        } catch (IOException e) {
            LogUtil.b(TAG, "reportErrorScope IOException:", ExceptionUtils.d(e));
        }
    }

    private static String getUrl() {
        return Utils.getUrl("com.huawei.health", "domainScopeOauth") + PATH;
    }
}
