package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.tradeservice.activity.TradeSureOrderActivity;
import com.huawei.health.tradeservice.cloud.AssetMessageManager;
import com.huawei.health.tradeservice.cloud.AuthManager;
import com.huawei.health.tradeservice.cloud.CardManager;
import com.huawei.health.tradeservice.cloud.DeviceBindRightsManager;
import com.huawei.health.tradeservice.cloud.OrderManager;
import com.huawei.health.tradeservice.cloud.ProductManager;
import com.huawei.health.tradeservice.cloud.PromotionInfoRsp;
import com.huawei.health.tradeservice.cloud.ResourceManager;
import com.huawei.health.tradeservice.pay.PayActivity;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.PayApi;
import com.huawei.trade.ResourceCallBack;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.PayRequest;
import com.huawei.trade.datatype.ResourceSummary;
import java.util.List;

@ApiDefine(uri = PayApi.class)
@Singleton
/* loaded from: classes4.dex */
public class gky implements PayApi {
    @Override // com.huawei.trade.PayApi
    public void buyByProduct(Activity activity, int i, String str, String str2) {
        Intent intent = new Intent(activity, (Class<?>) PayActivity.class);
        intent.putExtra("productId", str);
        intent.putExtra("purchasePolicy", str2);
        activity.startActivityForResult(intent, i);
    }

    @Override // com.huawei.trade.PayApi
    public void buyThenJumpTo(Context context, PayRequest payRequest) {
        Intent intent = new Intent(context, (Class<?>) PayActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("payRequest", new Gson().toJson(payRequest));
        context.startActivity(intent);
    }

    @Override // com.huawei.trade.PayApi
    public void buyByProductId(Activity activity, int i, String str) {
        Intent intent = new Intent(activity, (Class<?>) PayActivity.class);
        intent.putExtra("productInfo", str);
        activity.startActivityForResult(intent, i);
    }

    @Override // com.huawei.trade.PayApi
    public void buyByProductId(Context context, Fragment fragment, int i, String str) {
        Intent intent = new Intent(context, (Class<?>) PayActivity.class);
        intent.putExtra("productInfo", str);
        fragment.startActivityForResult(intent, i);
    }

    @Override // com.huawei.trade.PayApi
    public void getProductSummaryInfo(String str, int i, IBaseResponseCallback iBaseResponseCallback) {
        ProductManager.getProductSummaryInfo(str, i, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void getProductState(String str, IBaseResponseCallback iBaseResponseCallback) {
        gkx.e().a(str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void getProductDetails(String str, IBaseResponseCallback iBaseResponseCallback) {
        ProductManager.getProductDetails(str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void getResourceSummaryInfo(String str, IBaseResponseCallback iBaseResponseCallback) {
        ResourceManager.getResourceSummaryInfo(str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void getResourceSkipAddr(ResourceSummary resourceSummary, IBaseResponseCallback iBaseResponseCallback) {
        ResourceManager.getResourceSkipAddr(resourceSummary, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void orderCreate(String str, IBaseResponseCallback iBaseResponseCallback) {
        OrderManager.orderCreate(str, null, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void orderCancel(String str, IBaseResponseCallback iBaseResponseCallback) {
        OrderManager.orderCancel(str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void orderQueryUnpaid(String str, IBaseResponseCallback iBaseResponseCallback) {
        OrderManager.orderQueryUnpaid(str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void orderReportPurchaseResult(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback) {
        OrderManager.orderReportPurchaseResult(str, str2, str3, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void orderQueryHistory(long j, long j2, long j3, IBaseResponseCallback iBaseResponseCallback) {
        OrderManager.orderQueryHistory(j, j2, j3, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void orderQueryDetails(String str, IBaseResponseCallback iBaseResponseCallback) {
        OrderManager.orderQueryDetails(str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void promotionInfoQuery(String str, String str2, String str3, final IBaseResponseCallback iBaseResponseCallback) {
        OrderManager.promotionInfoQuery(str, str2, str3, new IBaseResponseCallback() { // from class: gky.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof PromotionInfoRsp) {
                    iBaseResponseCallback.d(i, Integer.valueOf(((PromotionInfoRsp) obj).getResultCode()));
                } else {
                    iBaseResponseCallback.d(i, -1);
                }
            }
        });
    }

    @Override // com.huawei.trade.PayApi
    public void cardQueryInfo(String str, IBaseResponseCallback iBaseResponseCallback) {
        CardManager.cardQueryInfo(str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void regResourceCallBack(String str, ResourceCallBack resourceCallBack) {
        ResourceManager.regResourceCallBack(str, resourceCallBack);
    }

    @Override // com.huawei.trade.PayApi
    public void resourceAuth(int i, String str, IBaseResponseCallback iBaseResponseCallback) {
        AuthManager.resourceAuthInfo(i, str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void functionAuth(String str, IBaseResponseCallback iBaseResponseCallback) {
        AuthManager.functionAuthInfo(str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void assetMsgNotify(int i, IBaseResponseCallback iBaseResponseCallback) {
        AssetMessageManager.assetMsgNotify(i, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void assetMsgListQuery(int i, long j, IBaseResponseCallback iBaseResponseCallback) {
        AssetMessageManager.assetMsgListQuery(i, j, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void assetMsgVisited(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        AssetMessageManager.assetMsgVisited(list, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void startRetrievingOrders() {
        gkx.e().e(LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011));
    }

    @Override // com.huawei.trade.PayApi
    public void giftCardListQuery(List<Integer> list, IBaseResponseCallback iBaseResponseCallback) {
        CardManager.giftCardListQuery(list, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void couponListQuery(List<Integer> list, String str, IBaseResponseCallback iBaseResponseCallback) {
        CardManager.couponListQuery(list, str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void awardListQuery(IBaseResponseCallback iBaseResponseCallback) {
        CardManager.awardListQuery(iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void bindDeviceBenefit(DeviceBenefitQueryParam deviceBenefitQueryParam, IBaseResponseCallback iBaseResponseCallback) {
        DeviceBindRightsManager.bindDeviceBenefit(deviceBenefitQueryParam, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void activateDeviceBenefitPage(DeviceBenefitQueryParam deviceBenefitQueryParam) {
        DeviceBindRightsManager.activateDeviceBenefitPage(deviceBenefitQueryParam);
    }

    @Override // com.huawei.trade.PayApi
    public void checkDeviceBenefitMessage(DeviceBenefitQueryParam deviceBenefitQueryParam) {
        DeviceBindRightsManager.checkDeviceBenefitMessage(deviceBenefitQueryParam);
    }

    @Override // com.huawei.trade.PayApi
    public void getAllDeviceBenefits(List<DeviceBenefitQueryParam> list, IBaseResponseCallback iBaseResponseCallback) {
        DeviceBindRightsManager.getDeviceBenefitsBatch(list, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void getDeviceBenefits(DeviceBenefitQueryParam deviceBenefitQueryParam, IBaseResponseCallback iBaseResponseCallback) {
        DeviceBindRightsManager.getDeviceBenefits(deviceBenefitQueryParam, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void receiveCouponList(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        CardManager.receiveCouponList(list, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void queryBenefitInfo(int i, String str, IBaseResponseCallback iBaseResponseCallback) {
        DeviceBindRightsManager.queryBenefitInfo(i, str, iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void enableBenefitAutoActivation(DeviceBenefitQueryParam deviceBenefitQueryParam) {
        DeviceBindRightsManager.enableBenefitAutoActivation(deviceBenefitQueryParam);
    }

    @Override // com.huawei.trade.PayApi
    public void getChallenge(IBaseResponseCallback iBaseResponseCallback) {
        DeviceBindRightsManager.getChallenge(iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void startTradeSureOrderActivity(Context context, String str, String str2) {
        if (context == null) {
            LogUtil.h("TradePay", "startTradeSureOrderActivity context == null");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) TradeSureOrderActivity.class);
        intent.putExtra("productId", str2);
        intent.putExtra("productInfo", str);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    @Override // com.huawei.trade.PayApi
    public void getPromotionPolicy(IBaseResponseCallback iBaseResponseCallback) {
        OrderManager.getPromotionPolicy(iBaseResponseCallback);
    }

    @Override // com.huawei.trade.PayApi
    public void productQueryByType(njp njpVar, IBaseResponseCallback iBaseResponseCallback) {
        giv.b(njpVar, iBaseResponseCallback);
    }
}
