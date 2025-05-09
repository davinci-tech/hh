package com.huawei.health.marketing.request;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import java.util.Map;

/* loaded from: classes3.dex */
public interface SetCustomConfigApi {
    @POST
    Submit<RespSetCustomConfig> setCustomConfig(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);
}
