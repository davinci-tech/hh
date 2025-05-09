package com.huawei.health.marketing.request;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import java.util.Map;

/* loaded from: classes8.dex */
public interface RecommendResourcesApi {
    @GET
    Submit<RecommendResourceRsp> getRecommendResourceInfos(@Url String str, @HeaderMap Map<String, String> map);
}
