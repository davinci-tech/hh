package com.huawei.wear.oversea.httputil.restclient;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.ClientConfig;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.QueryMap;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.ClientConfiguration;
import java.util.Map;

/* loaded from: classes7.dex */
public interface StringGetRestClientService {
    @GET
    Submit<String> executeRestClientRequest(@HeaderMap Map<String, String> map, @Url String str, @QueryMap Map<String, String> map2);

    @GET
    Submit<String> executeRestClientRequest(@HeaderMap Map<String, String> map, @Url String str, @QueryMap Map<String, String> map2, @ClientConfig ClientConfiguration clientConfiguration);
}
