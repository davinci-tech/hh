package com.huawei.health.marketing.request;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import java.util.Map;

/* loaded from: classes8.dex */
public interface GlobalSearchApi {
    @POST
    Submit<RespSearchSuggest> getSearchSuggest(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<RespGlobalSearchResult> searchContent(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<KnowledgeSearchResultWrapper> searchContentForKnowledge(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<SceneSearchResult> searchContentForScene(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);
}
