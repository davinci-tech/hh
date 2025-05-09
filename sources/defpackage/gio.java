package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class gio implements ParamsFactory {
    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.e());
        HashMap hashMap = new HashMap(16);
        hashMap.put(ProfileRequestConstants.X_APPID_KEY, "10013");
        hashMap.put("versionCode", String.valueOf(BaseApplication.c()));
        hashMap.put(WatchFaceConstant.X_UID, loginInit.getAccountInfo(1011));
        hashMap.put("x-hc", loginInit.getAccountInfo(1010));
        hashMap.put(WatchFaceConstant.X_CLIENTTRACEID, String.valueOf(System.currentTimeMillis()));
        hashMap.put("usertoken", loginInit.getAccountInfo(1015));
        hashMap.put("authtype", "AT");
        hashMap.put("x-accountBrand", "0");
        hashMap.put("x-appBrand", a());
        return hashMap;
    }

    private String a() {
        return CommonUtil.bh() ? "1" : CommonUtil.bf() ? "2" : "3";
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        return new HashMap();
    }
}
