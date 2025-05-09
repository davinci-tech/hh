package com.huawei.health.tradeservice.cloud;

import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.trade.ResourceCallBack;
import com.huawei.trade.datatype.ResourceSkipAddr;
import com.huawei.trade.datatype.ResourceSummary;
import defpackage.gla;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class ResourceManager {
    private static final String RESOURCE_URL_PREFIX = "huaweischeme://healthapp/router/trade?";
    private static final String TAG = "TradeService_ResourceManager";
    private static Map<String, ResourceCallBack> resourceCallBackHashMap = new ConcurrentHashMap(0);

    private ResourceManager() {
    }

    public static void getResourceSummaryInfo(String str, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "getResourceSummaryInfo enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        hashMap.put("country", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        hashMap.put("productId", str);
        TradeServiceCloudMgr.getInstance().resourceQueryInfo(new ResourceInfoReq(), hashMap, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.ResourceManager.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof ResourceInfoRsp) {
                    IBaseResponseCallback.this.d(0, ((ResourceInfoRsp) obj).getResourceSummary());
                } else {
                    IBaseResponseCallback.this.d(-1, null);
                }
            }
        });
    }

    public static void getResourceSkipAddr(ResourceSummary resourceSummary, IBaseResponseCallback iBaseResponseCallback) {
        if (resourceSummary == null) {
            LogUtil.b(TAG, "getResourceSkipAddr resourceSummary is null");
            return;
        }
        ResourceCallBack resourceCallBack = resourceCallBackHashMap.get(String.valueOf(resourceSummary.getResourceType()));
        ResourceSkipAddr skipAddr = resourceCallBack != null ? resourceCallBack.getSkipAddr(resourceSummary) : null;
        if (skipAddr == null) {
            skipAddr = new ResourceSkipAddr();
            skipAddr.setAddrType(1);
            skipAddr.setAddr("huaweischeme://healthapp/router/trade?type=" + resourceSummary.getResourceType() + "&id=" + resourceSummary.getResourceId());
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, skipAddr);
        }
    }

    public static void regResourceCallBack(String str, ResourceCallBack resourceCallBack) {
        resourceCallBackHashMap.put(str, resourceCallBack);
    }
}
