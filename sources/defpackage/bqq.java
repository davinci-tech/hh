package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.featureuserprofile.account.data.IAccountDataMgr;
import com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.featureuserprofile.util.AsyncSelectorSerialize;
import com.huawei.featureuserprofile.util.EventBus;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class bqq {

    /* renamed from: a, reason: collision with root package name */
    private static bqq f462a;
    private static final int[] e = {1000, 3000, 5000};
    private IAccountDataMgr b;
    private Context c;
    private IAccountDataExtMgr d;
    private EventBus g;
    private long j = 0;

    private bqq(Context context) {
        this.c = null;
        this.b = new bqt();
        this.d = new bqv();
        this.g = null;
        this.c = context;
        a aVar = new a();
        this.g = aVar;
        aVar.init(this.c);
        this.g.loadSceneIfExist();
        if (CommonUtil.bu()) {
            bqm bqmVar = new bqm();
            this.b = bqmVar;
            bqmVar.init(this.c);
            bqs bqsVar = new bqs();
            this.d = bqsVar;
            bqsVar.init(this.c);
        }
    }

    class a extends EventBus {
        private a() {
        }

        @Override // com.huawei.featureuserprofile.util.EventBus
        public void onLoginIfAllow() {
            if (Utils.l()) {
                bqq.this.b = new bqr();
                LogUtil.h("UserProfileMgrNative", "onLoginIfAllow isOverseaNoCloudVersion");
            } else {
                bqq.this.b = new bqm();
            }
            bqq.this.b.init(bqq.this.c);
            bqq.this.d = new bqs();
            bqq.this.d.init(bqq.this.c);
        }

        @Override // com.huawei.featureuserprofile.util.EventBus
        public void onDuplicateLoginIfAllow() {
            if (bqq.this.b != null) {
                bqq.this.b.sync();
            }
        }

        @Override // com.huawei.featureuserprofile.util.EventBus
        public void onLogoutIfAllow() {
            bqq.this.b.destroy();
            bqq.this.b = new bqt();
            bqq.this.d.destroy(bqq.this.c);
            bqq.this.d = new bqv();
        }

        @Override // com.huawei.featureuserprofile.util.EventBus
        public void onRecognizeNotAllow() {
            bqq.this.b = new bqr();
            bqq.this.b.init(bqq.this.c);
            bqq.this.d = new bqs();
            bqq.this.d.init(bqq.this.c);
        }
    }

    public static bqq d(Context context) {
        bqq bqqVar;
        if (context == null) {
            LogUtil.h("UserProfileMgrNative", "getInstance context is null");
            return null;
        }
        synchronized (bqq.class) {
            if (f462a == null) {
                f462a = new bqq(context.getApplicationContext());
            }
            bqqVar = f462a;
        }
        return bqqVar;
    }

    public void d() {
        this.g.loginAccount();
    }

    public void e() {
        this.g.loginStorage();
    }

    public void c() {
        this.g.logoutAcount();
    }

    public void i() {
        this.g.logoutStorage();
    }

    public void h() {
        this.g.setAllowArea();
    }

    public void g() {
        this.g.setNotAllowArea();
    }

    public boolean j() {
        LogUtil.a("UserProfileMgrNative", "waitInit checkInit before mAccountDataMgr.checkInit(): ", Boolean.valueOf(this.b.checkInit()), " mAccountDataExtMgr.checkInit(): ", Boolean.valueOf(this.d.checkInit()));
        int[] iArr = e;
        for (int length = iArr.length; length != 0 && (!this.b.checkInit() || !this.d.checkInit()); length--) {
            try {
                int i = iArr[3 - length];
                LogUtil.a("UserProfileMgrNative", "waitInit sleepArg: ", Integer.valueOf(i));
                Thread.sleep(i);
            } catch (InterruptedException unused) {
                LogUtil.h("UserProfileMgrNative", "waitInit interrupt");
            }
        }
        LogUtil.a("UserProfileMgrNative", "waitInit checkInit after mAccountDataMgr.checkInit(): ", Boolean.valueOf(this.b.checkInit()), " mAccountDataExtMgr.checkInit(): ", Boolean.valueOf(this.d.checkInit()));
        return this.b.checkInit() && this.d.checkInit();
    }

    public boolean a() {
        return this.b.checkInit() && this.d.checkInit();
    }

    public void e(UserInfomation userInfomation, final ICloudOperationResult<Boolean> iCloudOperationResult) {
        LogUtil.a("UserProfileMgrNative", "setUserInfo");
        boolean z = false;
        if (userInfomation == null) {
            if (iCloudOperationResult != null) {
                iCloudOperationResult.operationResult(false, null, false);
                return;
            }
            return;
        }
        UserInfomation copyFrom = userInfomation.copyFrom();
        AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(new Handler(Looper.getMainLooper())) { // from class: bqq.1
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onFailed(int i) {
                ICloudOperationResult iCloudOperationResult2 = iCloudOperationResult;
                if (iCloudOperationResult2 != null) {
                    iCloudOperationResult2.operationResult(null, null, false);
                }
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                ICloudOperationResult iCloudOperationResult2 = iCloudOperationResult;
                if (iCloudOperationResult2 != null) {
                    iCloudOperationResult2.operationResult(null, null, true);
                }
                if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
                    LogUtil.a("UserProfileMgrNative", "setUserInfo by HWUserProfileMgrNative");
                    Intent intent = new Intent();
                    intent.setPackage(bqq.this.c.getPackageName());
                    intent.setAction("com.huawei.health.action.ACTION_WIFI_USERINFO_ACTION");
                    BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
                }
            }
        };
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        boolean z2 = copyFrom.isGenderValid() && !String.valueOf(copyFrom.getGender()).equals(loginInit.getAccountInfo(1005));
        boolean z3 = copyFrom.isBirthdayValid() && !copyFrom.getBirthday().equals(loginInit.getAccountInfo(1006));
        if (!TextUtils.isEmpty(copyFrom.getName()) && !copyFrom.getName().equals(loginInit.getAccountInfo(1002))) {
            z = true;
        }
        if (z2 || z3 || z) {
            asyncSelectorSerialize.add(d(copyFrom, asyncSelectorSerialize));
        }
        if (copyFrom.isHeightValid() || copyFrom.isWeightValid()) {
            asyncSelectorSerialize.add(e(copyFrom, asyncSelectorSerialize));
        }
        asyncSelectorSerialize.run();
    }

    private AsyncSelectorSerialize.BaseAction d(final UserInfomation userInfomation, final AsyncSelectorSerialize asyncSelectorSerialize) {
        return new AsyncSelectorSerialize.BaseAction() { // from class: bqq.5
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                LogUtil.a("UserProfileMgrNative", "getAccountAction modify AccountData");
                bqq.this.b.setUserInfo(userInfomation, new UserInfoMedia.UserInfoWriter.Callback() { // from class: bqq.5.4
                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                    public void onSuccess() {
                        LogUtil.a("UserProfileMgrNative", "getAccountAction AccountDataChange success");
                        asyncSelectorSerialize.next(null);
                    }

                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                    public void onFail(int i) {
                        LogUtil.h("UserProfileMgrNative", "getAccountAction AccountDataChange fail");
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        };
    }

    private AsyncSelectorSerialize.BaseAction e(final UserInfomation userInfomation, final AsyncSelectorSerialize asyncSelectorSerialize) {
        return new AsyncSelectorSerialize.BaseAction() { // from class: bqq.2
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                LogUtil.a("UserProfileMgrNative", "getExtendDataAction modify AccountDataExt");
                bqq.this.d.setUserInfo(userInfomation, new UserInfoMedia.UserInfoWriter.Callback() { // from class: bqq.2.3
                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                    public void onSuccess() {
                        LogUtil.a("UserProfileMgrNative", "getExtendDataAction AccountDataExtChange setUserInfo success");
                        asyncSelectorSerialize.next(null);
                    }

                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                    public void onFail(int i) {
                        LogUtil.h("UserProfileMgrNative", "getExtendDataAction AccountDataExtChange setUserInfo fail");
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        };
    }

    public UserInfomation b() {
        Object[] objArr = new Object[2];
        objArr[0] = "enter getUserInfo mAccountDataMgr|mAccountDataExtMgr null is ";
        objArr[1] = Boolean.valueOf(this.b == null || this.d == null);
        LogUtil.a("UserProfileMgrNative", objArr);
        IAccountDataMgr iAccountDataMgr = this.b;
        if (iAccountDataMgr == null || this.d == null) {
            return null;
        }
        UserInfomation userInfo = iAccountDataMgr.getUserInfo();
        UserInfomation userInfo2 = this.d.getUserInfo();
        if (userInfo == null || userInfo2 == null) {
            LogUtil.h("UserProfileMgrNative", "getUserInfo accountData|accountDataExt null");
            return null;
        }
        UserInfomation userInfomation = new UserInfomation(0);
        userInfomation.loadAccountData(userInfo);
        userInfomation.loadAccountExtData(userInfo2);
        if (TextUtils.isEmpty(userInfomation.getPicPath()) || "default".equals(userInfomation.getPicPath())) {
            userInfomation.setPicPath(bvx.a());
        }
        return userInfomation;
    }

    private UserInfomation f() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.j;
        if (j > 0 && elapsedRealtime - j < 5000) {
            LogUtil.a("UserProfileMgrNative", "isSync false");
            return null;
        }
        this.j = elapsedRealtime;
        return b();
    }

    public void b(BaseResponseCallback<UserInfomation> baseResponseCallback) {
        UserInfomation f = f();
        if (a() && f != null) {
            LogUtil.a("UserProfileMgrNative", "isSync true");
            bsf.a(baseResponseCallback, 0, f);
        } else {
            HiHealthNativeApi.a(this.c).fetchUserData(new b(baseResponseCallback));
        }
    }

    public void c(UserInfomation userInfomation) {
        IAccountDataMgr iAccountDataMgr = this.b;
        if (iAccountDataMgr != null) {
            iAccountDataMgr.refreshAccountDataCache(userInfomation);
        }
    }

    public void c(final CommonCallback commonCallback) {
        this.b.sync(new UserInfoMedia.UserInfoReader.Callback() { // from class: bqq.4
            @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
            public void onSuccess(UserInfomation userInfomation) {
                CommonCallback commonCallback2 = commonCallback;
                if (commonCallback2 != null) {
                    commonCallback2.onSuccess(new Bundle());
                }
            }

            @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
            public void onFail(int i) {
                CommonCallback commonCallback2 = commonCallback;
                if (commonCallback2 != null) {
                    commonCallback2.onFail(i);
                }
            }
        });
    }

    static class b implements HiCommonListener {
        private final BaseResponseCallback<UserInfomation> d;

        b(BaseResponseCallback<UserInfomation> baseResponseCallback) {
            this.d = baseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            if (!koq.e(obj, HiUserInfo.class)) {
                LogUtil.h("UserProfileMgrNative", "getUserInfo isListTypeMatch = false");
                bsf.a(this.d, -1, null);
                return;
            }
            List list = (List) obj;
            if (koq.b(list)) {
                LogUtil.h("UserProfileMgrNative", "getUserInfo userList is null or empty");
                bsf.a(this.d, -1, null);
                return;
            }
            HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
            if (hiUserInfo == null) {
                LogUtil.h("UserProfileMgrNative", "getUserInfo hiUserInfo is null");
                bsf.a(this.d, -1, null);
                return;
            }
            ReleaseLogUtil.e("R_PersonalInfo_UserProfileMgrNative", "verify userInfo, isGenderValid: ", Boolean.valueOf(hiUserInfo.isGenderValid()), " isBirthdayValid: ", Boolean.valueOf(hiUserInfo.isBirthdayValid()), " isHeightValid: ", Boolean.valueOf(hiUserInfo.isHeightValid()), " isWeightValid: ", Boolean.valueOf(hiUserInfo.isWeightValid()));
            UserInfomation userInfomation = new UserInfomation(0);
            userInfomation.loadAccountData(hiUserInfo);
            userInfomation.loadAccountExtData(hiUserInfo);
            if (TextUtils.isEmpty(userInfomation.getPicPath()) || "default".equals(userInfomation.getPicPath())) {
                userInfomation.setPicPath(bvx.a());
            }
            bsf.a(this.d, 0, userInfomation);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.a("UserProfileMgrNative", "getUserInfo fetchUserData errorCode = ", Integer.valueOf(i), " errorMessage = ", obj);
            bsf.a(this.d, -1, null);
        }
    }
}
