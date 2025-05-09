package com.huawei.health.tradeservice.cloud;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import defpackage.gla;
import defpackage.koq;
import defpackage.mtj;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class CardManager {
    private static final String TAG = "TradeService_CardManager";
    private static final String VERSION_DIVIDED_SYMBOL = "-";

    private CardManager() {
    }

    public static void cardQueryInfo(String str, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "getCardInfo enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("language", mtj.e(null));
        hashMap.put("orderCode", str);
        TradeServiceCloudMgr.getInstance().cardQueryInfo(new CardQueryInfoReq(), gla.c((HashMap<String, String>) hashMap), new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.CardManager.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof CardQueryInfoRsp) {
                    IBaseResponseCallback.this.d(0, ((CardQueryInfoRsp) obj).getThirdCardInfos());
                } else {
                    IBaseResponseCallback.this.d(i, null);
                }
            }
        });
    }

    public static void giftCardListQuery(List<Integer> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "giftCardListQuery callBack is null");
            return;
        }
        LogUtil.a(TAG, "giftCardListQuery enter");
        HashMap<String, String> params = getParams(list);
        TradeServiceCloudMgr.getInstance().giftCardListQuery(new GiftCardListReq(), params, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.CardManager.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof GiftCardListRsp) {
                    IBaseResponseCallback.this.d(0, ((GiftCardListRsp) obj).getGiftCardList());
                } else {
                    IBaseResponseCallback.this.d(i, null);
                }
            }
        });
    }

    public static void awardListQuery(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "awardListQuery callBack is null");
            return;
        }
        LogUtil.a(TAG, "awardListQuery enter");
        TradeServiceCloudMgr.getInstance().awardListQuery(new AwardListReq(), getActivityParams(), new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.CardManager.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof AwardListRsp) {
                    IBaseResponseCallback.this.d(0, ((AwardListRsp) obj).getAwardRecords());
                } else {
                    IBaseResponseCallback.this.d(i, null);
                }
            }
        });
    }

    public static void couponListQuery(List<Integer> list, String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "giftCardListQuery callBack is null");
            return;
        }
        LogUtil.a(TAG, "giftCardListQuery enter");
        HashMap<String, String> params = getParams(list);
        if (!TextUtils.isEmpty(str)) {
            params.put("productId", str);
        }
        TradeServiceCloudMgr.getInstance().couponListQuery(new CouponListReq(), params, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.CardManager.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof CouponListRsp) {
                    IBaseResponseCallback.this.d(0, ((CouponListRsp) obj).getCouponList());
                } else {
                    IBaseResponseCallback.this.d(i, null);
                }
            }
        });
    }

    public static void receiveCouponList(List<String> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null || koq.b(list)) {
            LogUtil.h(TAG, "receiveCouponList callBack is null");
            return;
        }
        LogUtil.a(TAG, "receiveCouponList enter");
        ReceiveCouponReq receiveCouponReq = new ReceiveCouponReq();
        receiveCouponReq.setCouponIdList(list);
        receiveCouponReq.setClientType(gla.d());
        receiveCouponReq.setClientVersion(getClientVersion(BaseApplication.getContext()));
        receiveCouponReq.setTerritory(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        TradeServiceCloudMgr.getInstance().receiveCoupon(receiveCouponReq, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.CardManager.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a(CardManager.TAG, "errorCode:", Integer.valueOf(i));
                IBaseResponseCallback.this.d(i, obj);
            }
        });
    }

    private static HashMap<String, String> getParams(List<Integer> list) {
        HashMap<String, String> hashMap = new HashMap<>(0);
        if (!koq.b(list)) {
            hashMap.put("statusList", listToString(list));
        }
        hashMap.put("territory", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        hashMap.put("clientVersion", getClientVersion(BaseApplication.getContext()));
        hashMap.put("locale", mtj.e(null));
        return hashMap;
    }

    private static JSONObject getActivityParams() {
        return new JSONObject(new ActivityInfoListFactory(BaseApplication.getContext()).getBody(null));
    }

    public static String getClientVersion(Context context) {
        String e = CommonUtil.e(context);
        return e.contains("-") ? e.substring(0, e.indexOf("-")) : e;
    }

    private static String listToString(List<Integer> list) {
        if (koq.b(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : list) {
            sb.append(",");
            sb.append(num);
        }
        return sb.substring(1);
    }
}
