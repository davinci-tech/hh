package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.info.EnterpriseRequestInfo;
import com.huawei.hwversionmgr.info.EnterpriseResponseInfo;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class kxr {
    public static void b(final int i, final String str, final ResultCallback<EnterpriseResponseInfo> resultCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kxr.4
            @Override // java.lang.Runnable
            public void run() {
                EnterpriseRequestInfo enterpriseRequestInfo = new EnterpriseRequestInfo();
                enterpriseRequestInfo.setDeviceCertificate("");
                enterpriseRequestInfo.setKeyAttestation("");
                enterpriseRequestInfo.setDeviceId(str);
                enterpriseRequestInfo.setExpiredTime(String.valueOf(i));
                LogUtil.h("EnterpriseUpdateManager", "RequestHelper.deviceSn = ", CommonUtil.l(str));
                lqi.a(kxr.j()).b(kxr.e(), kxr.c(), lql.b(enterpriseRequestInfo), EnterpriseResponseInfo.class, kxr.d(resultCallback));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e() {
        String str;
        if (CommonUtil.as() && TextUtils.equals(kxz.g(BaseApplication.getContext()), "test_mode")) {
            str = "/cqs/service/out/sds/v1/queryEnterpriseByDeviceId";
        } else {
            str = GRSManager.a(BaseApplication.getContext()).getUrl("enterpriseUrl") + "/service/out/sds/v1/queryEnterpriseByDeviceId";
        }
        LogUtil.h("EnterpriseUpdateManager", "getEnterpriseUrl = ", str);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ResultCallback<EnterpriseResponseInfo> d(final ResultCallback<EnterpriseResponseInfo> resultCallback) {
        return new ResultCallback<EnterpriseResponseInfo>() { // from class: kxr.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(EnterpriseResponseInfo enterpriseResponseInfo) {
                if (ResultCallback.this != null) {
                    LogUtil.h("EnterpriseUpdateManager", "RequestHelper.toJson = ", enterpriseResponseInfo.toString());
                    ResultCallback.this.onSuccess(enterpriseResponseInfo);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ResultCallback resultCallback2 = ResultCallback.this;
                if (resultCallback2 != null) {
                    resultCallback2.onFailure(th);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, String> c() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put("Connection", w9.j);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HttpClient j() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.getContext());
        return new HttpClient.Builder().connectTimeout(8000).readTimeout(8000).build();
    }
}
