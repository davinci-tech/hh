package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.server.AppDataCollectionReq;
import com.huawei.openalliance.ad.beans.server.AppDataCollectionRsp;
import com.huawei.openalliance.ad.net.http.Response;
import java.util.Map;

/* loaded from: classes5.dex */
public interface jv {
    @kl(a = "appDataServer")
    @kg
    Response<AppDataCollectionRsp> a(@kc boolean z, @jz AppDataCollectionReq appDataCollectionReq, @ke Map<String, String> map);
}
