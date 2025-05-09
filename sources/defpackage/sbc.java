package defpackage;

import com.google.gson.JsonParseException;
import com.huawei.health.R;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;

/* loaded from: classes7.dex */
public class sbc {
    public static int d(PersonalData personalData) {
        return personalData.getPersonalLevel();
    }

    public static String a(PersonalData personalData) {
        return personalData.getPersonalLevelDesc();
    }

    public static int c(PersonalData personalData) {
        return personalData.getSumKakaNum();
    }

    public static String e(HiUserInfo hiUserInfo) {
        return hiUserInfo.getName();
    }

    public static String a(HiUserInfo hiUserInfo) {
        return hiUserInfo.getHeadImgUrl();
    }

    public static String c(UserInfomation userInfomation) {
        return userInfomation.getName();
    }

    public static String a(UserInfomation userInfomation) {
        return userInfomation.getPicPath();
    }

    public static String b(UserInfomation userInfomation) {
        return userInfomation.getPortraitUrl();
    }

    public static boolean a() {
        ReleaseLogUtil.e("R_PersonalCentetUtil", "isBlockedBetaSubmission");
        try {
            sav savVar = (sav) ixu.d(BaseApplication.getContext().getAssets().open("beta_submission_blocklist.json"), sav.class);
            ReleaseLogUtil.e("R_PersonalCentetUtil", "isBlockedBetaSubmission betaSubmissionConfigBean = ", savVar.toString());
            if (savVar.c()) {
                return true;
            }
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
            ReleaseLogUtil.e("R_PersonalCentetUtil", "isBlockedBetaSubmission countryCode = ", accountInfo, ", ifAllowLogin = ", Boolean.valueOf(Utils.i()), ", isNoLogin = ", Boolean.valueOf(h()));
            if (Utils.i() && !h()) {
                if (!savVar.a().contains(accountInfo)) {
                    return false;
                }
            }
            return true;
        } catch (JsonParseException unused) {
            ReleaseLogUtil.c("R_PersonalCentetUtil", "JsonParseException failed");
            ReleaseLogUtil.c("R_PersonalCentetUtil", "isBlockedBetaSubmission default true");
            return true;
        } catch (IOException e) {
            ReleaseLogUtil.c("R_PersonalCentetUtil", "loadBetaBlockListConfig failed, ", LogAnonymous.b((Throwable) e));
            ReleaseLogUtil.c("R_PersonalCentetUtil", "isBlockedBetaSubmission default true");
            return true;
        }
    }

    private static boolean h() {
        return Utils.i() && !Utils.h();
    }

    public static boolean f() {
        if (!CommonUtil.as() || a()) {
            return false;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).getIsLogined()) {
            int h = CommonUtil.h(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
            return h == 7 || h == 5;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        return CommonUtil.c(R.array._2130968671_res_0x7f04005f, accountInfo) || CommonUtil.c(R.array._2130968655_res_0x7f04004f, accountInfo);
    }

    public static boolean i() {
        return c() || f();
    }

    public static boolean c() {
        return CommonUtil.as() && CommonUtil.y(BaseApplication.getContext()) && !Utils.o();
    }

    public static boolean d() {
        return (c() && e()) || (f() && j());
    }

    public static boolean j() {
        LogUtil.a("PersonalCentetUtil", "isNotAgreeOverseaBetaPrivacy betaAgree:", SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "health_oversea_beta_user_agree"));
        return !"1".equals(r0);
    }

    public static boolean e() {
        LogUtil.a("PersonalCentetUtil", "isNotAgreeBetaVersion betaVersionAgree:", SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "health_beta_user_agree"));
        return !"1".equals(r0);
    }

    public static void b() {
        if (f()) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "health_oversea_beta_user_agree", "0", new StorageParams());
        }
    }
}
