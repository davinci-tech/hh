package com.huawei.wear.oversea.httputil.restclient;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.ClientConfig;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.ClientConfiguration;
import java.util.Map;

/* loaded from: classes7.dex */
public interface StringRestClientService {
    @POST
    Submit<String> executeRestClientRequest(@HeaderMap Map<String, String> map, @Url String str, @Body String str2);

    @POST
    Submit<String> executeRestClientRequest(@HeaderMap Map<String, String> map, @Url String str, @Body String str2, @ClientConfig ClientConfiguration clientConfiguration);
}
