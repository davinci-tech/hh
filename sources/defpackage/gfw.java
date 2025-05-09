package defpackage;

import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.pluginfitnessadvice.TargetConfig;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;

/* loaded from: classes4.dex */
public class gfw extends gfy {

    /* renamed from: a, reason: collision with root package name */
    private UserInfomation f12793a;

    public gfw(IFitRunVoiceContentConstructor iFitRunVoiceContentConstructor) {
        super(iFitRunVoiceContentConstructor);
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.c("Suggestion_IntensityMaf180Guider", "init : userProfileMgrApi is null.");
        } else {
            this.f12793a = userProfileMgrApi.getUserInfo();
        }
    }

    @Override // defpackage.gfy, com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public void initStatusUtil(TargetConfig targetConfig) {
        int valueH = (int) targetConfig.getValueH();
        int valueL = (int) targetConfig.getValueL();
        this.mStatusUtil = new ghx((valueH - valueL) - this.f12793a.getAgeOrDefaultValue(), (valueH + valueL) - this.f12793a.getAgeOrDefaultValue(), ghx.e());
    }

    @Override // defpackage.gfy, com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public String getTag() {
        return "Suggestion_IntensityMaf180Guider";
    }
}
