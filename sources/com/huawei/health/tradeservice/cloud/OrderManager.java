package com.huawei.health.tradeservice.cloud;

import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.CloudParamKeys;
import defpackage.eaa;
import defpackage.gla;
import defpackage.mtj;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class OrderManager {
    private static final String TAG = "TradeService_OrderManager";

    private OrderManager() {
    }

    public static void orderCreate(String str, Map<String, String> map, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "orderCreate enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        OrderCreateReq orderCreateReq = new OrderCreateReq();
        map.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        map.put("country", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        map.put("productId", str);
        TradeServiceCloudMgr.getInstance().orderCreate(orderCreateReq, map, iBaseResponseCallback);
    }

    public static void orderCancel(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "orderCancel enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        OrderCancelReq orderCancelReq = new OrderCancelReq();
        orderCancelReq.setOrderCode(str);
        TradeServiceCloudMgr.getInstance().orderCancel(orderCancelReq, iBaseResponseCallback);
    }

    public static void orderQueryUnpaid(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "orderQueryUnpaid enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        hashMap.put("country", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        hashMap.put("language", mtj.e(null));
        hashMap.put("orderCode", str);
        TradeServiceCloudMgr.getInstance().orderQueryUnpaid(new OrderUnpaidReq(), hashMap, iBaseResponseCallback);
    }

    public static void orderReportPurchaseResult(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "orderReportPurchaseResult enter");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        OrderReportResultReq orderReportResultReq = new OrderReportResultReq();
        orderReportResultReq.setOrderCode(str);
        orderReportResultReq.setPurchaseData(str2);
        orderReportResultReq.setDataSignature(str3);
        TradeServiceCloudMgr.getInstance().orderReportPurchaseResult(orderReportResultReq, iBaseResponseCallback);
    }

    public static void orderRedelivery(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "orderRedelivery enter");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        OrderRedeliveryReq orderRedeliveryReq = new OrderRedeliveryReq();
        orderRedeliveryReq.setOrderCode(str);
        orderRedeliveryReq.setPurchaseData(str2);
        orderRedeliveryReq.setDataSignature(str3);
        TradeServiceCloudMgr.getInstance().orderRedelivery(orderRedeliveryReq, iBaseResponseCallback);
    }

    public static void orderQueryHistory(long j, long j2, long j3, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "orderQueryHistory enter");
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("language", mtj.e(null));
        if (j > 0) {
            hashMap.put("startTime", String.valueOf(j));
        }
        if (j2 > 0) {
            hashMap.put("endTime", String.valueOf(j2));
        }
        if (j3 > 0) {
            hashMap.put(BleConstants.LIMIT, String.valueOf(j3));
        }
        TradeServiceCloudMgr.getInstance().orderQueryHistory(new OrderHistoryReq(), hashMap, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.OrderManager.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof OrderHistoryRsp) {
                    IBaseResponseCallback.this.d(0, ((OrderHistoryRsp) obj).getHistoryOrders());
                } else {
                    IBaseResponseCallback.this.d(-1, null);
                }
            }
        });
    }

    public static void orderQueryDetails(String str, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "orderQueryDetails enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("language", mtj.e(null));
        hashMap.put("orderCode", str);
        TradeServiceCloudMgr.getInstance().orderQueryDetails(new OrderQueryDetailsReq(), gla.c((HashMap<String, String>) hashMap), new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.OrderManager.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof OrderQueryDetailsRsp) {
                    IBaseResponseCallback.this.d(0, ((OrderQueryDetailsRsp) obj).getOrderDetail());
                } else {
                    IBaseResponseCallback.this.d(i, null);
                }
            }
        });
    }

    public static void queryAudiosPackageBySeriesId(String str, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "queryAudiosPackageBySeriesId enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("ids", str);
        TradeServiceCloudMgr.getInstance().queryAudiosPackageBySeriesId(new OrderAudiosPackageReq(), gla.c((HashMap<String, String>) hashMap), new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.OrderManager.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof OrderAudiosPackageRsq) {
                    IBaseResponseCallback.this.d(0, (OrderAudiosPackageRsq) obj);
                } else {
                    IBaseResponseCallback.this.d(i, null);
                }
            }
        });
    }

    public static void promotionInfoQuery(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "promotionInfoQuery: callBack is null");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h(TAG, "promotionInfoQuery: param is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        LogUtil.a(TAG, "promotionInfoQuery enter");
        PromotionInfoReq promotionInfoReq = new PromotionInfoReq();
        promotionInfoReq.setProductId(str);
        promotionInfoReq.setPromotionId(str2);
        promotionInfoReq.setCountry(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        promotionInfoReq.setClientType(gla.d());
        promotionInfoReq.setClientVersion(eaa.c(BaseApplication.getContext()));
        if (!TextUtils.isEmpty(str3)) {
            promotionInfoReq.setPromotionPolicyId(str3);
        }
        TradeServiceCloudMgr.getInstance().promotionInfoQuery(promotionInfoReq, iBaseResponseCallback);
    }

    public static void getPromotionPolicy(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "getPromotionPolicy: callback is null");
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("territory", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        hashMap.put("clientVersion", eaa.c(BaseApplication.getContext()));
        TradeServiceCloudMgr.getInstance().getPromotionPolicy(new PromotionPolicyReq(), hashMap, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.OrderManager$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                OrderManager.lambda$getPromotionPolicy$0(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    static /* synthetic */ void lambda$getPromotionPolicy$0(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        if (obj instanceof PromotionPolicyRsq) {
            iBaseResponseCallback.d(0, ((PromotionPolicyRsq) obj).getPromotionPolicyInfo());
        } else {
            iBaseResponseCallback.d(i, null);
        }
    }
}
