package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.up.model.UserInfomation;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;

/* loaded from: classes4.dex */
public class gni {
    public static UserInfomation c() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            ReleaseLogUtil.a("UserInformationUtils", "getUserInformation api is null");
            return null;
        }
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (userInfo != null) {
            return userInfo;
        }
        boolean c = HandlerExecutor.c();
        ReleaseLogUtil.a("UserInformationUtils", "getUserInformation userInformation is null isMainTread ", Boolean.valueOf(c));
        return !c ? LoginInit.getInstance(BaseApplication.e()).getUserInfoFromDbSync() : userInfo;
    }

    public static int e() {
        UserInfomation c = c();
        if (c == null) {
            return -100;
        }
        return c.getAge();
    }

    public static int a() {
        UserInfomation c = c();
        if (c == null) {
            return -100;
        }
        return c.getGender();
    }

    public static int b() {
        UserInfomation c = c();
        if (c == null) {
            return -100;
        }
        return c.getHeight();
    }
}
