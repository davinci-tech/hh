package defpackage;

import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealthservice.hihealthkit.cpcheck.BaseRequest;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class ipe<T> extends BaseRequest<T> {
    public ipe(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        ReleaseLogUtil.e("R_AppInfoRequest", "AppInfoRequest site: ", accountInfo);
        this.mUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, accountInfo) + "/healthkit/v1/appInfos";
        if (!this.mUrl.startsWith("https://") && Utils.c(BaseApplication.getContext())) {
            StringBuilder sb = new StringBuilder();
            sb.append(GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, "CN"));
            sb.append("/healthkit/v1/appInfos");
            this.mUrl = sb.toString();
            ReleaseLogUtil.e("R_AppInfoRequest", "AppInfoRequest site is Russia, changed to a site in China");
        }
        this.mParams.put("appId", str);
        this.mParams.put("includeAuditInfo", "true");
    }
}
