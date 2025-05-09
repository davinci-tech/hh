package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.info.CommonInfo;
import com.huawei.hwversionmgr.info.ComponentVersion;
import com.huawei.hwversionmgr.info.MarketFlag;
import com.huawei.hwversionmgr.info.NonceResponseInfo;
import com.huawei.hwversionmgr.info.PsiUploadRequestInfo;
import com.huawei.hwversionmgr.info.PsiUploadResponseInfo;
import com.huawei.hwversionmgr.info.SecurityInfo;
import com.huawei.hwversionmgr.info.SystemVersion;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes5.dex */
public class kxq {
    public static void d(final ResultCallback<NonceResponseInfo> resultCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kxq.4
            @Override // java.lang.Runnable
            public void run() {
                HashMap hashMap = new HashMap(16);
                String str = kxq.j() + "/v1/psi/nonce";
                LogUtil.c("PsiUpdateManager", "queryNonceDetail url= ", str);
                lqi.a(kxq.c()).c(str, kxq.b(), hashMap, NonceResponseInfo.class, ResultCallback.this);
            }
        });
    }

    public static void c(final DeviceInfo deviceInfo, final String str, final boolean z, final ResultCallback<PsiUploadResponseInfo> resultCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kxq.3
            @Override // java.lang.Runnable
            public void run() {
                String str2;
                String str3;
                String str4 = kxq.j() + "/v1/psi/upload";
                String str5 = "";
                if (z) {
                    str3 = "Y";
                    str2 = kxq.j() + "/v1/ecard/upload";
                } else if (Utils.o()) {
                    str2 = str4;
                    str3 = "";
                } else {
                    str2 = str4;
                    str5 = kxz.k(BaseApplication.getContext());
                    str3 = "";
                }
                String d = kxq.d(deviceInfo, str, str5, str3);
                LogUtil.c("PsiUpdateManager", "queryPsiUploadDetail url= ", str2);
                lqi.a(kxq.c()).b(str2, kxq.b(), d, PsiUploadResponseInfo.class, resultCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(DeviceInfo deviceInfo, String str, String str2, String str3) {
        CommonInfo commonInfo = new CommonInfo();
        if (TextUtils.isEmpty(str3)) {
            commonInfo.setCheckMode("PSI");
            commonInfo.setSaleInfo(deviceInfo.getSaleInfo() == null ? "|||||||" : deviceInfo.getSaleInfo());
        } else {
            commonInfo.setCheckMode("E-Card");
            commonInfo.setIsActivate(str3);
        }
        commonInfo.setClientType(com.huawei.haf.application.BaseApplication.f());
        commonInfo.setClientVersion(String.valueOf(com.huawei.haf.application.BaseApplication.c()));
        commonInfo.setDevModel(deviceInfo.getDeviceModel());
        commonInfo.setDeviceType("SportWatch");
        commonInfo.setPlmn("00000");
        commonInfo.setUsageInfo("||| |||");
        SecurityInfo securityInfo = new SecurityInfo();
        securityInfo.setChallenge(str);
        securityInfo.setDeviceCertificate(deviceInfo.getPsiSignature() != null ? deviceInfo.getPsiSignature() : "|||||||");
        securityInfo.setKeyAttestation("NA");
        securityInfo.setSecurityMode("pubKey");
        com.huawei.hwversionmgr.info.DeviceInfo deviceInfo2 = new com.huawei.hwversionmgr.info.DeviceInfo();
        deviceInfo2.setImei("");
        deviceInfo2.setUdid(deviceInfo.getDeviceUdid());
        deviceInfo2.setDeviceId(deviceInfo.getSecurityDeviceId());
        deviceInfo2.setLocation(str2);
        ComponentVersion componentVersion = new ComponentVersion();
        componentVersion.setBaseVersion(deviceInfo.getSoftVersion());
        componentVersion.setCustVersion(deviceInfo.getDeviceEnterpriseVersion());
        componentVersion.setPreloadVersion("");
        SystemVersion systemVersion = new SystemVersion();
        systemVersion.setCurrentVersion(deviceInfo.getSoftVersion());
        systemVersion.setSoftwareOSVersion(deviceInfo.getDeviceOStVersion());
        systemVersion.setSecurityPatchLevel("");
        MarketFlag marketFlag = new MarketFlag();
        marketFlag.setPostCustFlag("");
        marketFlag.setPreCustFlag("");
        PsiUploadRequestInfo psiUploadRequestInfo = new PsiUploadRequestInfo();
        psiUploadRequestInfo.setCommonInfo(commonInfo);
        psiUploadRequestInfo.setSecurityInfo(securityInfo);
        psiUploadRequestInfo.setDeviceInfo(deviceInfo2);
        psiUploadRequestInfo.setComponentVersion(componentVersion);
        psiUploadRequestInfo.setSystemVersion(systemVersion);
        psiUploadRequestInfo.setMarketFlag(marketFlag);
        psiUploadRequestInfo.setExtraInfo("");
        String b = lql.b(psiUploadRequestInfo);
        LogUtil.c("PsiUpdateManager", "getRequestBody body= ", b);
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String j() {
        return (CommonUtil.as() && TextUtils.equals(kxz.g(BaseApplication.getContext()), "test_mode")) ? "" : GRSManager.a(BaseApplication.getContext()).getUrl("psiUploadUrl");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, String> b() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put("x-productId", "Huawei Health");
        hashMap.put("x-deviceDescId", BaseApplication.getAppPackage());
        hashMap.put("x-devModel", kyp.a());
        String uuid = UUID.randomUUID().toString();
        LogUtil.a("PsiUpdateManager", "getHeaders x-requestId= ", uuid);
        hashMap.put("x-requestId", uuid);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HttpClient c() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.getContext());
        return new HttpClient.Builder().connectTimeout(8000).readTimeout(8000).build();
    }
}
