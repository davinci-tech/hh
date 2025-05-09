package defpackage;

import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;

/* loaded from: classes4.dex */
class fim {
    static float c() {
        return ggs.b();
    }

    static String d() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            return userInfo.getPortraitUrl();
        }
        return null;
    }
}
