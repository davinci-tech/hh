package defpackage;

import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;

/* loaded from: classes7.dex */
public class sir extends OauthManager {
    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public int getThirdAccountType() {
        return 26;
    }

    private sir() {
    }

    public static sir c() {
        return e.f17073a;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static sir f17073a = new sir();
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public String getModuleId() {
        return String.valueOf(PrebakedEffectId.RT_SPRING);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager
    public String getConnectTimeKey() {
        return "key_komoot_connect_time";
    }
}
