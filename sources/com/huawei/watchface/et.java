package com.huawei.watchface;

import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.anno.Body;
import com.huawei.hms.network.restclient.anno.HeaderMap;
import com.huawei.hms.network.restclient.anno.NetworkParameters;
import com.huawei.hms.network.restclient.anno.POST;
import com.huawei.hms.network.restclient.anno.Url;
import java.util.Map;

/* loaded from: classes7.dex */
public interface et {
    @POST
    Submit<String> a(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @POST
    Submit<String> a(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody, @NetworkParameters String str2);
}
