package defpackage;

import android.text.TextUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealthservice.hihealthkit.cpcheck.BaseRequest;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import defpackage.ipj;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class ipg<T> extends BaseRequest<T> {
    private Map<String, Object> b;

    public ipg(String str, String str2) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        ReleaseLogUtil.e("R_TrustedUserRequest", "TrustedUserRequest site: ", accountInfo);
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, accountInfo);
        if ((TextUtils.isEmpty(noCheckUrl) || !noCheckUrl.startsWith("https://")) && Utils.c(BaseApplication.getContext())) {
            noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, "CN");
            ReleaseLogUtil.e("R_TrustedUserRequest", "TrustedUserRequest site is Russia, changed to a site in China");
        }
        this.mUrl = String.format(Locale.ENGLISH, noCheckUrl + "/healthkit/v1/appInfos/%s/trustedUsers", str);
        HashMap hashMap = new HashMap(10);
        this.b = hashMap;
        hashMap.put(JsbMapKeyNames.H5_USER_ID, str2);
    }

    @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.BaseRequest
    public ipj.e<T> getRequestParamsBuilder() {
        return super.getRequestParamsBuilder().a(ProfileRequestConstants.PUT_TYPE).d(this.b);
    }
}
