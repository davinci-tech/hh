package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Marker;

/* loaded from: classes7.dex */
public class jbc {
    public static boolean bEi_(Uri uri) {
        String queryParameter = uri.getQueryParameter("client_id");
        Map<String, String> d = d();
        HashMap hashMap = new HashMap();
        hashMap.put("appId", queryParameter);
        jbf jbfVar = (jbf) lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl(HealthEngineRequestManager.GRS_KEY) + "/healthkit-healthapp/v1/appInfos", d, hashMap, jbf.class);
        if (jbfVar == null) {
            ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "isAuthPermitted, thirdAuthPermissionRsp is null");
            return false;
        }
        if (CollectionUtils.d(jbfVar.e())) {
            ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "isAuthPermitted, appInfos size is 0");
            return false;
        }
        return "true".equals(jbfVar.e().get(0).a());
    }

    public static String bEh_(Uri uri) {
        String queryParameter = uri.getQueryParameter("scope");
        HashMap hashMap = new HashMap();
        try {
            String replace = URLEncoder.encode(queryParameter, StandardCharsets.UTF_8.name()).replace(Marker.ANY_NON_NULL_MARKER, Constants.PERCENT_20);
            Map<String, String> d = d();
            jba jbaVar = (jba) lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl(HealthEngineRequestManager.GRS_KEY) + "/healthkit-healthapp/v1/healthScopes?originalScopes=" + replace, d, hashMap, jba.class);
            if (jbaVar == null) {
                ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "getScopes, getScopeRsp is null");
                return null;
            }
            if (TextUtils.isEmpty(jbaVar.c())) {
                ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "getScopes, healthScopes is empty");
                return null;
            }
            return jbaVar.c();
        } catch (UnsupportedEncodingException unused) {
            ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "getScopes, encode scopes error");
            return null;
        }
    }

    public static String bEf_(Uri uri, String str, Context context) {
        String bEg_ = bEg_(uri, context, str);
        if (TextUtils.isEmpty(bEg_)) {
            ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "getOAuthUrl, queryUrl is empty");
            return "";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Content-type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015));
        String str2 = (String) lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl("domainMyHuaweiOauthLogin") + "/oauth2/v3/authorize?" + bEg_, hashMap, null, String.class);
        if (TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "getOAuthUrl, OAuthPage is empty");
            return null;
        }
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainMyHuaweiOauthLogin") + b(str2) + (d(context) ? "&forceDarkMode=1" : "");
    }

    private static String b(String str) {
        int indexOf = str.indexOf("action");
        if (indexOf < 0) {
            ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "parseOAuthPage, can't find attribute of action");
            return "";
        }
        int indexOf2 = str.indexOf("\"", indexOf) + 1;
        int indexOf3 = str.indexOf("\"", indexOf2);
        if (indexOf2 <= 0 || indexOf2 > indexOf3) {
            ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "parseOAuthPage, can't find action");
            return "";
        }
        String substring = str.substring(indexOf2, indexOf3);
        if (!TextUtils.isEmpty(substring)) {
            return substring;
        }
        ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "parseOAuthPage, can't find value of action");
        return "";
    }

    private static String bEg_(Uri uri, Context context, String str) {
        String encode;
        StringBuilder sb = new StringBuilder("client_id=");
        try {
            String encode2 = URLEncoder.encode(str, StandardCharsets.UTF_8.name());
            if (CommonUtil.cc()) {
                encode = URLEncoder.encode(GRSManager.a(BaseApplication.getContext()).getUrl("domainOAuthLogin") + "/sandbox/cch5/healthkit/oauth-h5/oauth-callback.html", StandardCharsets.UTF_8.name());
            } else {
                encode = URLEncoder.encode(GRSManager.a(BaseApplication.getContext()).getUrl("domainOAuthLogin") + "/cch5/healthkit/oauth-h5/oauth-callback.html", StandardCharsets.UTF_8.name());
            }
            String replace = encode2.replace(Marker.ANY_NON_NULL_MARKER, Constants.PERCENT_20);
            sb.append(uri.getQueryParameter("client_id"));
            sb.append("&response_type=code&isNeedLogin=true&access_type=offline&display=touch&redirect_uri=");
            sb.append(encode);
            sb.append("&scope=");
            sb.append(replace);
            sb.append("&");
            String queryParameter = uri.getQueryParameter("state");
            if (!TextUtils.isEmpty(queryParameter)) {
                sb.append("state=");
                sb.append(queryParameter);
                sb.append("&");
            }
            boolean d = d(context);
            sb.append("themeName=");
            sb.append(d ? "dark" : "light");
            sb.append("&lang=");
            String u = CommonUtil.u();
            if ("zh-CHT".equals(u)) {
                u = "zh-Hant";
            }
            sb.append(u);
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            ReleaseLogUtil.c("R_HiH_AutoLoginUtils", "getQueryUrl, encode scopes error, scopes:" + str);
            return "";
        }
    }

    private static boolean d(Context context) {
        if (context != null) {
            return Build.VERSION.SDK_INT > 28 && (context.getResources().getConfiguration().uiMode & 48) == 32;
        }
        LogUtil.h("AutoLoginUtils", "isDarkMode, contxet is null");
        return false;
    }

    private static Map<String, String> d() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-type", "application/json");
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + accountInfo);
        hashMap.put("x-client-id", HiScopeUtil.c(BaseApplication.getContext(), BaseApplication.getContext().getPackageName()));
        hashMap.put(CloudParamKeys.X_TS, String.valueOf(System.currentTimeMillis()));
        hashMap.put("x-version", CommonUtil.c(BaseApplication.getContext()));
        hashMap.put("x-caller-trace-id", String.valueOf(jec.h()) + Math.random());
        return hashMap;
    }

    public static void d(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        hashMap.put("cacheable", "false");
        lqi.d().c(GRSManager.a(BaseApplication.getContext()).getUrl(HealthEngineRequestManager.GRS_KEY) + "/healthkit-healthapp/v1/thirdAuthInfos", d(), hashMap, jbh.class, new ResultCallback<jbh>() { // from class: jbc.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(jbh jbhVar) {
                ReleaseLogUtil.e("R_HiH_AutoLoginUtils", "refreshAuthInfo, refreshAuthInfo succeed");
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.d("R_HiH_AutoLoginUtils", "refreshAuthInfo, refreshAuthInfo fail");
            }
        });
    }
}
