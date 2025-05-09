package com.huawei.hms.iapfull.network;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.iapfull.network.model.QueryOrderRequest;
import com.huawei.hms.iapfull.network.model.QueryOrderResponse;
import com.huawei.hms.iapfull.network.model.QueryWithholdResultRequest;
import com.huawei.hms.iapfull.network.model.QueryWithholdResultResponse;

/* loaded from: classes4.dex */
public interface IAPFullQueryResultService {
    @POST("/TradeServer/client/auth/async/queryOrder.action")
    Submit<QueryOrderResponse> pollQueryOrder(@Body QueryOrderRequest queryOrderRequest);

    @POST("/TradeServer/client/auth/async/queryPact.action")
    Submit<QueryWithholdResultResponse> pollQueryWithhold(@Body QueryWithholdResultRequest queryWithholdResultRequest);

    @POST("/TradeServer/client/auth/queryOrder.action")
    Submit<QueryOrderResponse> queryOrder(@Body QueryOrderRequest queryOrderRequest);

    @POST("/TradeServer/client/auth/queryPact.action")
    Submit<QueryWithholdResultResponse> queryWithhold(@Body QueryWithholdResultRequest queryWithholdResultRequest);
}
