package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.featureuserprofile.UserInfoBroadcastReceiver;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.UnitUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class bqi extends HwBaseManager {
    private static bqi c;
    private gmz b;
    private boolean d;
    private bqq h;
    private UserInfoBroadcastReceiver j;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static HashSet<String> f449a = new HashSet<>(Arrays.asList("com.huawei.bone.action.CLOUD_SWITCH_CHANGED", "com.huawei.bone.action.FITNESS_USERINFO_UPDATED"));

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public boolean onDataMigrate() {
        return true;
    }

    private bqi(Context context) {
        super(context);
        this.h = null;
        this.b = null;
        this.d = false;
        LogUtil.a("UserProfileMgr", "HWUserProfileMgr init");
        this.h = bqq.d(context);
        this.b = gmz.d();
        this.j = new UserInfoBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        BroadcastManagerUtil.bFA_(context, this.j, intentFilter, BroadcastManagerUtil.f13106a, null);
    }

    public static bqi c(Context context) {
        bqi bqiVar;
        if (context == null) {
            LogUtil.h("UserProfileMgr", "getInstance context is null");
            return null;
        }
        if (!EnvironmentUtils.c()) {
            LogUtil.h("UserProfileMgr", "getInstance isMainProcess false, UserProfileMgr error.");
        }
        synchronized (e) {
            if (c == null) {
                c = new bqi(context.getApplicationContext());
            }
            bqiVar = c;
        }
        return bqiVar;
    }

    public void a(gnc gncVar) {
        if (this.d) {
            LogUtil.h("UserProfileMgr", "init should be called only once");
            return;
        }
        if (gncVar.e()) {
            LogUtil.a("UserProfileMgr", "init allow login area");
            i();
            a();
        } else {
            LogUtil.a("UserProfileMgr", "init not allow login area");
            a(BaseApplication.getContext());
        }
        f();
        this.d = true;
    }

    public boolean o() {
        return this.h.j();
    }

    public boolean c() {
        return this.h.a();
    }

    public void a() {
        this.h.d();
        this.b.a();
    }

    void f() {
        this.h.e();
    }

    public void j() {
        this.h.c();
        this.b.b();
    }

    public void h() {
        this.h.i();
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        bvz.a(iBaseResponseCallback);
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        bvz.b(iBaseResponseCallback);
    }

    public void g() {
        bvz.b();
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 1004;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Set<String> getAvailableBroadcastSet() {
        return f449a;
    }

    public void c(UserInfomation userInfomation, ICloudOperationResult<Boolean> iCloudOperationResult) {
        this.h.e(userInfomation, iCloudOperationResult);
    }

    public UserInfomation e() {
        return this.h.b();
    }

    public void d(BaseResponseCallback<UserInfomation> baseResponseCallback) {
        LogUtil.a("UserProfileMgr", "getUserInfo() callback");
        if (baseResponseCallback == null) {
            LogUtil.h("UserProfileMgr", "getUserInfo callback == null");
        } else {
            this.h.b(baseResponseCallback);
        }
    }

    public void a(CommonCallback commonCallback) {
        this.h.c(commonCallback);
    }

    public void i() {
        this.h.h();
    }

    public void a(Context context) {
        LogUtil.a("UserProfileMgr", "setNoAllowLoginArea() enter");
        this.h.g();
        LoginInit loginInit = LoginInit.getInstance(context);
        loginInit.setIsLogined(true);
        loginInit.setAccountInfo(1011, "0", null);
        loginInit.setAccountInfo(1004, "0", null);
        loginInit.setIsBrowseModeToPd(context, false);
    }

    public gmu b() {
        return EmergencyInfoManager.c().a();
    }

    public static Bitmap tA_(Context context) {
        UserInfomation e2 = c(context).e();
        String picPath = e2 != null ? e2.getPicPath() : null;
        if (!TextUtils.isEmpty(picPath)) {
            return nrf.cIe_(context, picPath);
        }
        LogUtil.h("UserProfileMgr", "initHeadIcon, imgUrl is null");
        return null;
    }

    public static UserInfomation d() {
        bqi c2 = c(BaseApplication.getContext());
        if (c2 != null) {
            UserInfomation e2 = c2.e();
            if (e2 != null) {
                LogUtil.a("UserProfileMgr", "getLocalUserInfo return  MerryuserInformation");
                return e2;
            }
            UserInfomation userInfomation = new UserInfomation(UnitUtil.h() ? 1 : 0);
            LogUtil.a("UserProfileMgr", "getLocalUserInfo is null");
            return userInfomation;
        }
        UserInfomation userInfomation2 = new UserInfomation(UnitUtil.h() ? 1 : 0);
        LogUtil.b("UserProfileMgr", "getLocalUserInfo hwUserProfileMgr is null");
        return userInfomation2;
    }
}
