package com.huawei.common.scope;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.network.restclient.anno.POST;

/* loaded from: classes7.dex */
public interface LoginApi {
    @POST
    Submit<GetAccessScopeRsp> getScopes(@Url String str);
}
