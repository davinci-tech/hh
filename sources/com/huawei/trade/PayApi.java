package com.huawei.trade;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.PayRequest;
import com.huawei.trade.datatype.ResourceSummary;
import defpackage.njp;
import java.util.List;

/* loaded from: classes.dex */
public interface PayApi {
    void activateDeviceBenefitPage(DeviceBenefitQueryParam deviceBenefitQueryParam);

    void assetMsgListQuery(int i, long j, IBaseResponseCallback iBaseResponseCallback);

    void assetMsgNotify(int i, IBaseResponseCallback iBaseResponseCallback);

    void assetMsgVisited(List<String> list, IBaseResponseCallback iBaseResponseCallback);

    void awardListQuery(IBaseResponseCallback iBaseResponseCallback);

    void bindDeviceBenefit(DeviceBenefitQueryParam deviceBenefitQueryParam, IBaseResponseCallback iBaseResponseCallback);

    void buyByProduct(Activity activity, int i, String str, String str2);

    void buyByProductId(Activity activity, int i, String str);

    void buyByProductId(Context context, Fragment fragment, int i, String str);

    void buyThenJumpTo(Context context, PayRequest payRequest);

    void cardQueryInfo(String str, IBaseResponseCallback iBaseResponseCallback);

    void checkDeviceBenefitMessage(DeviceBenefitQueryParam deviceBenefitQueryParam);

    void couponListQuery(List<Integer> list, String str, IBaseResponseCallback iBaseResponseCallback);

    void enableBenefitAutoActivation(DeviceBenefitQueryParam deviceBenefitQueryParam);

    void functionAuth(String str, IBaseResponseCallback iBaseResponseCallback);

    void getAllDeviceBenefits(List<DeviceBenefitQueryParam> list, IBaseResponseCallback iBaseResponseCallback);

    void getChallenge(IBaseResponseCallback iBaseResponseCallback);

    void getDeviceBenefits(DeviceBenefitQueryParam deviceBenefitQueryParam, IBaseResponseCallback iBaseResponseCallback);

    void getProductDetails(String str, IBaseResponseCallback iBaseResponseCallback);

    void getProductState(String str, IBaseResponseCallback iBaseResponseCallback);

    void getProductSummaryInfo(String str, int i, IBaseResponseCallback iBaseResponseCallback);

    void getPromotionPolicy(IBaseResponseCallback iBaseResponseCallback);

    void getResourceSkipAddr(ResourceSummary resourceSummary, IBaseResponseCallback iBaseResponseCallback);

    void getResourceSummaryInfo(String str, IBaseResponseCallback iBaseResponseCallback);

    void giftCardListQuery(List<Integer> list, IBaseResponseCallback iBaseResponseCallback);

    void orderCancel(String str, IBaseResponseCallback iBaseResponseCallback);

    void orderCreate(String str, IBaseResponseCallback iBaseResponseCallback);

    void orderQueryDetails(String str, IBaseResponseCallback iBaseResponseCallback);

    void orderQueryHistory(long j, long j2, long j3, IBaseResponseCallback iBaseResponseCallback);

    void orderQueryUnpaid(String str, IBaseResponseCallback iBaseResponseCallback);

    void orderReportPurchaseResult(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback);

    void productQueryByType(njp njpVar, IBaseResponseCallback iBaseResponseCallback);

    void promotionInfoQuery(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback);

    void queryBenefitInfo(int i, String str, IBaseResponseCallback iBaseResponseCallback);

    void receiveCouponList(List<String> list, IBaseResponseCallback iBaseResponseCallback);

    void regResourceCallBack(String str, ResourceCallBack resourceCallBack);

    void resourceAuth(int i, String str, IBaseResponseCallback iBaseResponseCallback);

    void startRetrievingOrders();

    void startTradeSureOrderActivity(Context context, String str, String str2);
}
