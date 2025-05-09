package com.huawei.codevalueplatform.coderule.api;

import com.huawei.codevalueplatform.coderule.bean.response.CodeRulesResponse;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.anno.Body;
import com.huawei.hms.network.restclient.anno.HeaderMap;
import com.huawei.hms.network.restclient.anno.Headers;
import com.huawei.hms.network.restclient.anno.POST;
import java.util.Map;

/* loaded from: classes3.dex */
public interface CodeValuePlatformApi {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/barcode/v1/rules/cache")
    Submit<CodeRulesResponse> queryCodeRulesApi(@HeaderMap Map<String, String> map, @Body RequestBody requestBody);
}
