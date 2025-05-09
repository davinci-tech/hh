package defpackage;

import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;

/* loaded from: classes7.dex */
public class sjd extends OauthManager {
    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public int getThirdAccountType() {
        return 25;
    }

    private sjd() {
    }

    public static sjd d() {
        return b.d;
    }

    static class b {
        private static sjd d = new sjd();
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public String getModuleId() {
        return String.valueOf(PrebakedEffectId.RT_LIGHTNING);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public String getConnectTimeKey() {
        return "key_runtastic_connect_time";
    }
}
