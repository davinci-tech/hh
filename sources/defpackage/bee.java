package defpackage;

import com.google.gson.Gson;
import com.huawei.codevalueplatform.coderule.api.CodeValuePlatformApi;
import com.huawei.codevalueplatform.coderule.bean.request.DeviceInfo;
import com.huawei.codevalueplatform.coderule.bean.request.QueryCacheReq;
import com.huawei.codevalueplatform.coderule.bean.response.CodeRulesResponse;
import com.huawei.hms.network.base.common.MediaType;
import com.huawei.hms.network.base.common.RequestBodyProviders;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.Submit;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class bee {
    private static CodeValuePlatformApi c;
    private String d = "";

    public void c(String str) {
        this.d = str;
    }

    public Submit<CodeRulesResponse> c(int i) {
        bes.e("CodeRulesApiWrapper", "queryCloudCodeRulesApi start request hwServer");
        RequestBody a2 = a(i);
        CodeValuePlatformApi codeValuePlatformApi = c;
        if (codeValuePlatformApi != null) {
            return codeValuePlatformApi.queryCodeRulesApi(e(), a2);
        }
        CodeValuePlatformApi codeValuePlatformApi2 = (CodeValuePlatformApi) bel.b(CodeValuePlatformApi.class, bev.e(this.d));
        c = codeValuePlatformApi2;
        return codeValuePlatformApi2.queryCodeRulesApi(e(), a2);
    }

    private Map<String, String> e() {
        HashMap hashMap = new HashMap();
        hashMap.put("grayMark", beq.f());
        hashMap.put("appName", beq.b());
        hashMap.put("appVersion", beq.c());
        hashMap.put("deviceModel", beq.a());
        return hashMap;
    }

    private RequestBody a(int i) {
        QueryCacheReq queryCacheReq = new QueryCacheReq();
        queryCacheReq.setTransactionID(beq.f());
        queryCacheReq.setStartIdx(i);
        queryCacheReq.setPageSize(bec.b());
        queryCacheReq.setDeviceInfo(d());
        queryCacheReq.setAppPkgName(beq.b());
        queryCacheReq.setAppVersion(beq.c());
        return RequestBodyProviders.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(queryCacheReq));
    }

    private DeviceInfo d() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setTerminalType(beq.a());
        deviceInfo.setAndroidVersion(beq.e());
        deviceInfo.setEmuiVersion(beq.d());
        return deviceInfo;
    }
}
