package com.huawei.ui.commonui.utils;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import defpackage.nri;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public interface ResourcesRequestApi {
    @POST
    Submit<List<nri>> getResourcesConfigList(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);
}
