package com.huawei.health.tradeservice.cloud;

import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import defpackage.gla;
import defpackage.mtj;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class ProductManager {
    private static final String TAG = "TradeService_ProductManager";

    private ProductManager() {
    }

    public static void getProductSummaryInfo(String str, int i, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "getProductSummaryInfo enter");
        if (TextUtils.isEmpty(str) || i < 0) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        hashMap.put("territory", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        hashMap.put("locale", mtj.e(null));
        hashMap.put("resType", String.valueOf(i));
        hashMap.put("resId", str);
        TradeServiceCloudMgr.getInstance().getProductInfo(new ProductInfoReq(), hashMap, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.ProductManager.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (obj instanceof ProductInfoRsp) {
                    IBaseResponseCallback.this.d(0, ((ProductInfoRsp) obj).getProductSummaryList());
                } else {
                    IBaseResponseCallback.this.d(i2, null);
                }
            }
        });
    }

    public static void getProductDetails(String str, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "getProductDetails enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        hashMap.put("country", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        hashMap.put("language", mtj.e(null));
        hashMap.put("productId", str);
        TradeServiceCloudMgr.getInstance().productQueryDetails(new ProductDetailsReq(), hashMap, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.ProductManager.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof ProductDetailsRsp) {
                    IBaseResponseCallback.this.d(0, ((ProductDetailsRsp) obj).getProductInfo());
                } else {
                    IBaseResponseCallback.this.d(i, null);
                }
            }
        });
    }
}
