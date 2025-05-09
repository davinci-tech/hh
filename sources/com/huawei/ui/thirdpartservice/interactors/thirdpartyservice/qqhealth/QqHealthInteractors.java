package com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth;

import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthMgr;
import defpackage.shn;
import health.compact.a.Utils;
import java.util.Map;

/* loaded from: classes8.dex */
public class QqHealthInteractors {
    private static final Object OBJECT_LOCK = new Object();
    private static final String QQ_TYPE = "7";
    private static final String TAG = "QqHealthInteractors";
    private static final String URL_GETAUTHORIZETOKEN = "/profile/third/getAuthorizeToken";
    private static volatile QqHealthInteractors sInstance;
    private QqHealthMgr mQqHealthMgr = QqHealthMgr.getInstance();

    private QqHealthInteractors() {
    }

    public static QqHealthInteractors getInstance() {
        if (sInstance == null) {
            synchronized (OBJECT_LOCK) {
                if (sInstance == null) {
                    sInstance = new QqHealthInteractors();
                }
            }
        }
        return sInstance;
    }

    public void getAuthorizeToken(ICloudOperationResult<QqAuthorizeTokenResult> iCloudOperationResult) {
        if (Utils.o()) {
            return;
        }
        new shn<QqAuthorizeTokenResult>(QqAuthorizeTokenResult.class, URL_GETAUTHORIZETOKEN) { // from class: com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqHealthInteractors.1
            @Override // defpackage.shd
            public Map<String, Object> createParams() {
                Map<String, Object> createParams = super.createParams();
                createParams.put("thirdAccountType", "7");
                return createParams;
            }
        }.getAuthorizeToken(iCloudOperationResult);
    }

    public void sendQqHealthConnectSuccessBroadcast() {
        LogUtil.a(TAG, "sendQqHealthConnectSuccessBroadcast enter");
        QqHealthMgr qqHealthMgr = this.mQqHealthMgr;
        if (qqHealthMgr == null) {
            LogUtil.a(TAG, "sQqHealthMgr = null.");
        } else {
            qqHealthMgr.sendQqHealthConnectSuccessBroadcast();
        }
    }
}
