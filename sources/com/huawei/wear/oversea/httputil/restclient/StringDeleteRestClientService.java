package com.huawei.wear.oversea.httputil.restclient;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.ClientConfig;
import com.huawei.hms.framework.network.restclient.annotate.DELETE;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.QueryMap;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.ClientConfiguration;
import java.util.Map;

/* loaded from: classes7.dex */
public interface StringDeleteRestClientService {
    @DELETE
    Submit<String> executeRestClientRequest(@HeaderMap Map<String, String> map, @Url String str, @QueryMap Map<String, String> map2);

    @DELETE
    Submit<String> executeRestClientRequest(@HeaderMap Map<String, String> map, @Url String str, @QueryMap Map<String, String> map2, @ClientConfig ClientConfiguration clientConfiguration);
}
