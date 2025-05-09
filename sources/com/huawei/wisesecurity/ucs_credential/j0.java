package com.huawei.wisesecurity.ucs_credential;

import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.anno.Body;
import com.huawei.hms.network.restclient.anno.GET;
import com.huawei.hms.network.restclient.anno.HeaderMap;
import com.huawei.hms.network.restclient.anno.POST;
import com.huawei.hms.network.restclient.anno.Url;
import java.util.Map;

/* loaded from: classes7.dex */
public interface j0 {
    @GET
    Submit<String> a(@Url String str, @HeaderMap Map<String, String> map);

    @POST
    Submit<String> a(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);
}
