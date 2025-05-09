package com.huawei.health.tradeservice.cloud;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.beb;
import defpackage.lqi;
import defpackage.lql;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class TradeServiceCloudMgr {
    private static final String TAG = "TradeService_CloudMgr";
    private static volatile TradeServiceCloudMgr sCloudMgr;
    private TradeServiceCloudApi callApi = (TradeServiceCloudApi) lqi.d().b(TradeServiceCloudApi.class);
    private TradeServiceCloudFactory paramsFactory = new TradeServiceCloudFactory(BaseApplication.getContext());

    private TradeServiceCloudMgr(Context context) {
    }

    public static TradeServiceCloudMgr getInstance() {
        if (sCloudMgr == null) {
            sCloudMgr = new TradeServiceCloudMgr(BaseApplication.getContext());
        }
        return sCloudMgr;
    }

    public void getProductInfo(ProductInfoReq productInfoReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.productQueryInfo(productInfoReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void productQueryDetails(ProductDetailsReq productDetailsReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.productQueryDetails(productDetailsReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void resourceQueryInfo(ResourceInfoReq resourceInfoReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.resourceQueryInfo(resourceInfoReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void orderCreate(OrderCreateReq orderCreateReq, Map<String, String> map, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.orderCreate(orderCreateReq.getUrl(), this.paramsFactory.getHeaders(), lql.b(map)).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    static class NetWorkReqCallBack implements ResultCallback {
        private IBaseResponseCallback mCallBack;

        private NetWorkReqCallBack(IBaseResponseCallback iBaseResponseCallback) {
            this.mCallBack = iBaseResponseCallback;
        }

        @Override // com.huawei.hms.framework.network.restclient.ResultCallback
        public void onResponse(Response response) {
            if (response != null && (response.getBody() instanceof BaseRsp)) {
                BaseRsp baseRsp = (BaseRsp) response.getBody();
                int resultCode = baseRsp.getResultCode();
                LogUtil.a(TradeServiceCloudMgr.TAG, "NetWorkReqCallBack resultCode= ", Integer.valueOf(resultCode));
                if (this.mCallBack == null) {
                    return;
                }
                if (baseRsp.getResultCode() == 3040046) {
                    this.mCallBack.d(resultCode, baseRsp);
                    return;
                } else if (baseRsp.getResultCode() != 0) {
                    this.mCallBack.d(resultCode, null);
                    return;
                } else {
                    this.mCallBack.d(0, baseRsp);
                    return;
                }
            }
            LogUtil.b(TradeServiceCloudMgr.TAG, "NetWorkReqCallBack rsp is null");
            IBaseResponseCallback iBaseResponseCallback = this.mCallBack;
            if (iBaseResponseCallback == null) {
                return;
            }
            iBaseResponseCallback.d(-1, null);
        }

        @Override // com.huawei.hms.framework.network.restclient.ResultCallback
        public void onFailure(Throwable th) {
            LogUtil.b(TradeServiceCloudMgr.TAG, "NetWorkReqCallBack IOException ", th.getMessage());
            IBaseResponseCallback iBaseResponseCallback = this.mCallBack;
            if (iBaseResponseCallback == null) {
                return;
            }
            iBaseResponseCallback.d(-1, null);
        }
    }

    public void orderCancel(OrderCancelReq orderCancelReq, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.orderCancel(orderCancelReq.getUrl(), this.paramsFactory.getHeaders(), lql.b(orderCancelReq)).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void orderQueryUnpaid(OrderUnpaidReq orderUnpaidReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.orderQueryUnpaid(orderUnpaidReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void orderReportPurchaseResult(OrderReportResultReq orderReportResultReq, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.orderReportPurchaseResult(orderReportResultReq.getUrl(), this.paramsFactory.getHeaders(), lql.b(orderReportResultReq)).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void orderRedelivery(OrderRedeliveryReq orderRedeliveryReq, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.orderRedelivery(orderRedeliveryReq.getUrl(), this.paramsFactory.getHeaders(), lql.b(orderRedeliveryReq)).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void orderQueryHistory(OrderHistoryReq orderHistoryReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.orderQueryHistory(orderHistoryReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void orderQueryDetails(OrderQueryDetailsReq orderQueryDetailsReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.orderQueryDetails(orderQueryDetailsReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void queryAudiosPackageBySeriesId(OrderAudiosPackageReq orderAudiosPackageReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        beb bebVar = new beb();
        this.callApi.queryAudiosPackageBySeriesId(orderAudiosPackageReq.getUrl(), bebVar.getHeaders(), lql.b(bebVar.getBody(hashMap))).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void cardQueryInfo(CardQueryInfoReq cardQueryInfoReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.cardQueryInfo(cardQueryInfoReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void resourceAuthInfo(ResourceAuthReq resourceAuthReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.resourceAuthInfo(resourceAuthReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void functionAuthInfo(FunctionAuthReq functionAuthReq, String str, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.functionAuthInfo(functionAuthReq.getUrl() + str + "?", this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void assetMsgNotify(AssetMsgNotifyReq assetMsgNotifyReq, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.assetMsgNotify(assetMsgNotifyReq.getUrl(), this.paramsFactory.getHeaders(), lql.b(assetMsgNotifyReq)).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void assetMsgListQuery(AssetMsgListReq assetMsgListReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.assetMsgListQuery(assetMsgListReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void assetMsgVisited(AssetMsgVisitedReq assetMsgVisitedReq, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.assetMsgVisited(assetMsgVisitedReq.getUrl(), this.paramsFactory.getHeaders(), lql.b(assetMsgVisitedReq)).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void promotionInfoQuery(PromotionInfoReq promotionInfoReq, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.promotionInfoQuery(promotionInfoReq.getUrl(), this.paramsFactory.getHeaders(), lql.b(promotionInfoReq)).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void giftCardListQuery(GiftCardListReq giftCardListReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.giftCardListQuery(giftCardListReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void awardListQuery(AwardListReq awardListReq, JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.receiveAward(awardListReq.getUrl(), this.paramsFactory.getHeaders(), jSONObject.toString()).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void couponListQuery(CouponListReq couponListReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.couponListQuery(couponListReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }

    public void receiveCoupon(ReceiveCouponReq receiveCouponReq, IBaseResponseCallback iBaseResponseCallback) {
        try {
            ReceiveCouponRsp body = this.callApi.receiveCoupon(receiveCouponReq.getUrl(), this.paramsFactory.getHeaders(), lql.b(receiveCouponReq)).execute().getBody();
            LogUtil.a(TAG, "receiveCoupon");
            if (body != null) {
                LogUtil.a(TAG, Integer.valueOf(body.getResultCode()));
                LogUtil.a(TAG, body.getCouponResultList());
                iBaseResponseCallback.d(body.getResultCode(), body.getCouponResultList());
            } else {
                iBaseResponseCallback.d(-1, null);
            }
        } catch (IOException unused) {
            LogUtil.a(TAG, "IOException");
            iBaseResponseCallback.d(-1, null);
        }
    }

    public void getPromotionPolicy(PromotionPolicyReq promotionPolicyReq, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        this.callApi.getPromotionPolicy(promotionPolicyReq.getUrl(), this.paramsFactory.getHeaders(), hashMap).enqueue(new NetWorkReqCallBack(iBaseResponseCallback));
    }
}
