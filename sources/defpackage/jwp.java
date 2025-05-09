package defpackage;

import com.google.android.gms.common.util.CollectionUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hms.network.embedded.j2;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.HiDateUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class jwp extends jwj {
    @Override // defpackage.jwj, com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory
    public Map<String, String> getCommonHeaders() {
        HashMap hashMap = new HashMap();
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.e());
        String accountInfo = loginInit.getAccountInfo(1015);
        String accountInfo2 = loginInit.getAccountInfo(1011);
        String d = HiDateUtil.d((String) null);
        long currentTimeMillis = System.currentTimeMillis();
        String a2 = BaseApplication.a();
        String d2 = BaseApplication.d();
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + accountInfo);
        hashMap.put("Content-type", "application/json; charset=UTF-8");
        hashMap.put("x-client-timezone", d);
        hashMap.put(CloudParamKeys.X_TS, String.valueOf(currentTimeMillis));
        hashMap.put(CloudParamKeys.X_APP_ID, d2);
        hashMap.put(j2.v, "gzip, deflate");
        hashMap.put("x-huid", accountInfo2);
        hashMap.put("x-proxy-version", "Android_wear_" + a2);
        return hashMap;
    }

    @Override // defpackage.jwj, com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory
    public Map<String, String> getCommonBody(DeviceInfo deviceInfo) {
        String hiLinkDeviceId = deviceInfo.getHiLinkDeviceId();
        List<ProductMapInfo> b = ProductMap.b("smartProductId", hiLinkDeviceId);
        String e = !CollectionUtils.isEmpty(b) ? b.get(0).e() : "";
        HashMap hashMap = new HashMap();
        hashMap.put("deviceIdentity", deviceInfo.getDeviceIdentify());
        hashMap.put("deviceTypeId", e);
        hashMap.put("prodId", hiLinkDeviceId);
        return hashMap;
    }
}
