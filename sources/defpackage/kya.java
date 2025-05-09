package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.info.EnterpriseResponseInfo;
import com.huawei.hwversionmgr.utils.callback.EnterpriseResultCallback;
import com.huawei.networkclient.ResultCallback;

/* loaded from: classes5.dex */
public class kya {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f14692a = new byte[1];
    private static volatile kya b;

    public static kya d() {
        if (b == null) {
            synchronized (f14692a) {
                if (b == null) {
                    b = new kya();
                }
            }
        }
        return b;
    }

    public void c(DeviceInfo deviceInfo, final EnterpriseResultCallback enterpriseResultCallback) {
        kxr.b(0, cvx.e(deviceInfo.getUuid()), new ResultCallback<EnterpriseResponseInfo>() { // from class: kya.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(EnterpriseResponseInfo enterpriseResponseInfo) {
                EnterpriseResultCallback enterpriseResultCallback2 = enterpriseResultCallback;
                if (enterpriseResultCallback2 != null) {
                    enterpriseResultCallback2.onSuccess(enterpriseResponseInfo);
                }
                LogUtil.h("EnterpriseUpdateUtil", "query,resp resultCode:", enterpriseResponseInfo.getExpiredTime());
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("EnterpriseUpdateUtil", "queryRouteDetail onFailure:", th.getMessage());
                EnterpriseResultCallback enterpriseResultCallback2 = enterpriseResultCallback;
                if (enterpriseResultCallback2 != null) {
                    enterpriseResultCallback2.onFailure(th);
                }
            }
        });
    }
}
