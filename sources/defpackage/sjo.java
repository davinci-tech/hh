package defpackage;

import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import com.huawei.ui.thirdpartservice.syncdata.strava.StravaConfig;

/* loaded from: classes7.dex */
public class sjo extends OauthManager {
    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public int getThirdAccountType() {
        return 27;
    }

    public static sjo d() {
        return e.c;
    }

    private sjo() {
    }

    static class e {
        private static sjo c = new sjo();
    }

    public void b(boolean z, StravaConfig stravaConfig, RefreshTokenCallback refreshTokenCallback) {
        super.checkConnectStatus(z, d(refreshTokenCallback, stravaConfig));
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public void checkConnectStatus(boolean z, RefreshTokenCallback refreshTokenCallback) {
        super.checkConnectStatus(z, d(refreshTokenCallback, new StravaConfig()));
    }

    private a d(RefreshTokenCallback refreshTokenCallback, StravaConfig stravaConfig) {
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010);
        a aVar = new a(refreshTokenCallback);
        stravaConfig.dYf_(null, accountInfo, aVar);
        return aVar;
    }

    static class a implements StravaConfig.StravaEnableCallback, RefreshTokenCallback {

        /* renamed from: a, reason: collision with root package name */
        private RefreshTokenCallback f17080a;
        private boolean b = false;
        private boolean e = false;

        a(RefreshTokenCallback refreshTokenCallback) {
            this.f17080a = refreshTokenCallback;
        }

        @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
        public void refreshResult(boolean z, boolean z2) {
            LogUtil.a("StravaOauthManager", "refreshResult isReady", Boolean.valueOf(this.b), Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(this.e));
            RefreshTokenCallback refreshTokenCallback = this.f17080a;
            if (refreshTokenCallback == null) {
                return;
            }
            if (this.b) {
                refreshTokenCallback.refreshResult(z, z2 && this.e);
            } else {
                this.b = true;
                this.e = z && z2;
            }
        }

        @Override // com.huawei.ui.thirdpartservice.syncdata.strava.StravaConfig.StravaEnableCallback
        public void onAbilityCallback(boolean z) {
            LogUtil.a("StravaOauthManager", "onAbilityCallback isReady", Boolean.valueOf(this.b), Boolean.valueOf(this.e), Boolean.valueOf(z));
            RefreshTokenCallback refreshTokenCallback = this.f17080a;
            if (refreshTokenCallback == null) {
                return;
            }
            if (this.b) {
                refreshTokenCallback.refreshResult(true, this.e && z);
            } else {
                this.b = true;
                this.e = z;
            }
        }
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public String getModuleId() {
        return String.valueOf(PrebakedEffectId.RT_SWING);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public String getConnectTimeKey() {
        return "key_strava_connect_time";
    }
}
