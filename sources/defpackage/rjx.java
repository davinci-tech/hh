package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealthservice.util.HttpRequestApi;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class rjx {
    public static String d(String str, String str2, String str3) {
        Response<String> execute;
        HttpRequestApi httpRequestApi = (HttpRequestApi) lqi.d().b(HttpRequestApi.class);
        Response<String> response = null;
        if (httpRequestApi == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Content-type", "application/json");
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + str2);
        try {
        } catch (IOException e) {
            LogUtil.b("HMSAuthHttps", "getReq fail : ", ExceptionUtils.d(e));
        }
        if (str3.equals("GET")) {
            execute = httpRequestApi.getReq(str, hashMap).execute();
        } else if (str3.equals(ProfileRequestConstants.DELETE_TYPE)) {
            execute = httpRequestApi.deleteReq(str, hashMap).execute();
        } else {
            LogUtil.a("HMSAuthHttps", "getReq with unknown method");
            return d(response);
        }
        response = execute;
        return d(response);
    }

    private static String d(Response<String> response) {
        if (response == null) {
            return null;
        }
        if (response.isOK()) {
            String body = response.getRawResponse().getBody();
            LogUtil.a("HMSAuthHttps", "getReq statusCode: Success");
            return body;
        }
        if (response.getCode() == 401) {
            String e = e(response.getCode());
            LogUtil.b("HMSAuthHttps", "http request statusCode is ", Integer.valueOf(response.getCode()));
            return e;
        }
        LogUtil.b("HMSAuthHttps", "Wrong statusCode = ", Integer.valueOf(response.getCode()));
        return null;
    }

    public static String e(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(VastAttribute.ERROR, i);
            return jSONObject.toString();
        } catch (JSONException unused) {
            LogUtil.b("HMSAuthHttps", "JSONException occurs");
            return null;
        }
    }
}
