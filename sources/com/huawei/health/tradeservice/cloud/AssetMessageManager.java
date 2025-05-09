package com.huawei.health.tradeservice.cloud;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import defpackage.koq;
import defpackage.mtj;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class AssetMessageManager {
    private static final String TAG = "TradeService_AssetManager";

    public static void assetMsgNotify(int i, IBaseResponseCallback iBaseResponseCallback) {
        AssetMsgNotifyReq assetMsgNotifyReq = new AssetMsgNotifyReq();
        assetMsgNotifyReq.setMessageType(i);
        assetMsgNotifyReq.setTerritory(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        LogUtil.a(TAG, "assetMsgNotify: ", assetMsgNotifyReq.toString());
        TradeServiceCloudMgr.getInstance().assetMsgNotify(assetMsgNotifyReq, iBaseResponseCallback);
    }

    public static void assetMsgListQuery(int i, long j, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.a(TAG, "assetMsgListQuery callBack is null");
            return;
        }
        LogUtil.a(TAG, "assetMsgListQuery enter");
        HashMap<String, String> hashMap = new HashMap<>(0);
        if (i > 0) {
            hashMap.put(BleConstants.LIMIT, String.valueOf(i));
        }
        if (j > 0) {
            hashMap.put("cutOffTime", String.valueOf(j));
        }
        hashMap.put("locale", mtj.e(null));
        TradeServiceCloudMgr.getInstance().assetMsgListQuery(new AssetMsgListReq(), hashMap, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.AssetMessageManager.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (obj instanceof AssetMsgListRsp) {
                    IBaseResponseCallback.this.d(i2, ((AssetMsgListRsp) obj).getMessages());
                } else {
                    IBaseResponseCallback.this.d(i2, null);
                }
            }
        });
    }

    public static void assetMsgVisited(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        if (koq.b(list)) {
            LogUtil.a(TAG, "assetMsgVisited param is null");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        AssetMsgVisitedReq assetMsgVisitedReq = new AssetMsgVisitedReq();
        assetMsgVisitedReq.setMessageIds(list);
        LogUtil.a(TAG, "assetMsgVisited: ", assetMsgVisitedReq.toString());
        TradeServiceCloudMgr.getInstance().assetMsgVisited(assetMsgVisitedReq, iBaseResponseCallback);
    }
}
