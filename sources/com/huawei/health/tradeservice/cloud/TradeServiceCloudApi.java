package com.huawei.health.tradeservice.cloud;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.QueryMap;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import java.util.Map;

/* loaded from: classes4.dex */
public interface TradeServiceCloudApi {
    @GET
    Submit<AssetMsgListRsp> assetMsgListQuery(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @POST
    Submit<AssetMsgNotifyRsp> assetMsgNotify(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<AssetMsgNotifyRsp> assetMsgVisited(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @GET
    Submit<CardQueryInfoRsp> cardQueryInfo(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<CouponListRsp> couponListQuery(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<FunctionAuthRsp> functionAuthInfo(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<PromotionPolicyRsq> getPromotionPolicy(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<GiftCardListRsp> giftCardListQuery(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @POST
    Submit<OrderCancelRsp> orderCancel(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<OrderCreateRsp> orderCreate(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @GET
    Submit<OrderQueryDetailsRsp> orderQueryDetails(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<OrderHistoryRsp> orderQueryHistory(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<OrderUnpaidRsp> orderQueryUnpaid(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @POST
    Submit<OrderRedeliveryRsp> orderRedelivery(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<OrderReportResultRsp> orderReportPurchaseResult(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @GET
    Submit<ProductDetailsRsp> productQueryDetails(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<ProductInfoRsp> productQueryInfo(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @POST
    Submit<PromotionInfoRsp> promotionInfoQuery(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<OrderAudiosPackageRsq> queryAudiosPackageBySeriesId(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<AwardListRsp> receiveAward(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<ReceiveCouponRsp> receiveCoupon(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @GET
    Submit<ResourceAuthRsp> resourceAuthInfo(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<ResourceInfoRsp> resourceQueryInfo(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);
}
