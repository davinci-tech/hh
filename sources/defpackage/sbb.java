package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.up.model.UserInfomation;
import health.compact.a.SharedPreferenceManager;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes7.dex */
public class sbb {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16995a = false;
    private boolean b = false;
    private final UserProfileMgrApi d;
    private final Handler e;

    public sbb(Handler handler, UserProfileMgrApi userProfileMgrApi) {
        this.d = userProfileMgrApi;
        this.e = handler;
    }

    public void b() {
        d();
        e();
    }

    private void d() {
        LogUtil.a("PersonalUserInfoManager", "getAccountInfo");
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1006);
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1005);
        boolean isEmpty = TextUtils.isEmpty(accountInfo);
        boolean z = ("0".equals(accountInfo2) || "1".equals(accountInfo2)) ? false : true;
        if (isEmpty || z) {
            this.f16995a = true;
            Handler handler = this.e;
            if (handler != null) {
                this.e.sendMessage(handler.obtainMessage(8));
            }
        } else {
            this.f16995a = false;
        }
        Handler handler2 = this.e;
        if (handler2 == null || this.f16995a || this.b) {
            return;
        }
        this.e.sendMessage(handler2.obtainMessage(14));
    }

    private void e() {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: sbb.4
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (!(obj instanceof List)) {
                    LogUtil.h("PersonalUserInfoManager", "getDataPlatformInfo data is null or error");
                    return;
                }
                List list = (List) obj;
                if (koq.b(list)) {
                    LogUtil.h("PersonalUserInfoManager", "getDataPlatformInfo userInfos is empty");
                    return;
                }
                LogUtil.a("PersonalUserInfoManager", "fetchUserData onSuccess");
                HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                if (hiUserInfo == null) {
                    LogUtil.h("PersonalUserInfoManager", "userInfos.get(0) is null");
                    return;
                }
                if (hiUserInfo.isHeightValid() || hiUserInfo.isWeightValid()) {
                    sbb.this.b = false;
                } else {
                    LogUtil.a("PersonalUserInfoManager", "enter set by hw");
                    sbb.this.b = true;
                    if (sbb.this.e != null) {
                        sbb.this.e.sendMessage(sbb.this.e.obtainMessage(8));
                    }
                }
                sbb.this.b(hiUserInfo);
                if (sbb.this.e == null || sbb.this.f16995a || sbb.this.b) {
                    return;
                }
                sbb.this.e.sendMessage(sbb.this.e.obtainMessage(14));
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b("PersonalUserInfoManager", "fetchUserData onFailure");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HiUserInfo hiUserInfo) {
        if (hiUserInfo == null) {
            LogUtil.h("PersonalUserInfoManager", "hiUserInfo is null");
            return;
        }
        if ("1".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN))) {
            LogUtil.a("PersonalUserInfoManager", "third");
            if (hiUserInfo.getGender() != 1 && hiUserInfo.getGender() != 0) {
                LogUtil.a("PersonalUserInfoManager", "enter set by gender");
                this.f16995a = true;
                Handler handler = this.e;
                if (handler != null) {
                    this.e.sendMessage(handler.obtainMessage(8));
                }
            } else {
                this.f16995a = false;
            }
            if (!hiUserInfo.isBirthdayValid()) {
                LogUtil.a("PersonalUserInfoManager", "enter set by birthday");
                this.f16995a = true;
                Handler handler2 = this.e;
                if (handler2 != null) {
                    this.e.sendMessage(handler2.obtainMessage(8));
                    return;
                }
                return;
            }
            this.f16995a = false;
        }
    }

    public void a() {
        if ("1".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN))) {
            LogUtil.a("PersonalUserInfoManager", "accountmigrate: isthirdlogin == 1 and return!");
            c();
        } else {
            this.d.getUserInfo(new e(this.e));
        }
    }

    private void c() {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: sbb.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj == null || !(obj instanceof List)) {
                    LogUtil.h("PersonalUserInfoManager", "fetchUserData data is error");
                }
                LogUtil.c("PersonalUserInfoManager", "fetchUserData data = ", obj);
                List list = (List) obj;
                if (koq.b(list)) {
                    LogUtil.h("PersonalUserInfoManager", "fetchUserData userInfos is empty");
                    return;
                }
                LogUtil.a("PersonalUserInfoManager", "fetchUserData onSuccess");
                HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                if (LoginInit.getInstance(BaseApplication.getContext()).isLoginedByWear()) {
                    cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
                    String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1002);
                    if (accountInfo != null) {
                        hiUserInfo.setName(accountInfo);
                        if (mainUser != null) {
                            mainUser.b(accountInfo);
                        }
                    } else {
                        LogUtil.a("PersonalUserInfoManager", "updateUserNameFromLocal userName is null");
                    }
                    String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1003);
                    if (mainUser != null) {
                        mainUser.e(accountInfo2);
                    }
                    hiUserInfo.setHeadImgUrl(accountInfo2);
                }
                if (sbb.this.e != null) {
                    Message obtainMessage = sbb.this.e.obtainMessage(4);
                    obtainMessage.obj = hiUserInfo;
                    sbb.this.e.sendMessage(obtainMessage);
                    return;
                }
                LogUtil.a("PersonalUserInfoManager", "Handler is null");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a("PersonalUserInfoManager", "fetchUserData onFailure");
            }
        });
    }

    public String e(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String str2 = BaseApplication.getContext().getFilesDir() + "/photos/headimage/" + str.split("/")[r5.length - 1];
        if (new File(str2).exists()) {
            LogUtil.a("PersonalUserInfoManager", "accountmigrate: getHeadImageFromLocal file.exists() yes");
        } else {
            LogUtil.a("PersonalUserInfoManager", "accountmigrate: getHeadImageFromLocal file.exists() no");
            File[] listFiles = new File(BaseApplication.getContext().getFilesDir() + "/photos/headimage").listFiles();
            if (listFiles != null && listFiles.length > 0) {
                LogUtil.c("PersonalUserInfoManager", "accountmigrate: getHeadImageFromLocal files num:", Integer.valueOf(listFiles.length));
                try {
                    str2 = listFiles[listFiles.length - 1].getCanonicalPath();
                } catch (IOException unused) {
                    LogUtil.a("PersonalUserInfoManager", "accountmigrate: get localpath io exception");
                }
            }
        }
        LogUtil.c("PersonalUserInfoManager", "accountmigrate: getHeadImageFromLocal localpath = ", str2);
        return str2;
    }

    static class e implements BaseResponseCallback<UserInfomation> {
        private final WeakReference<Handler> e;

        e(Handler handler) {
            this.e = new WeakReference<>(handler);
        }

        @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, UserInfomation userInfomation) {
            Handler handler = this.e.get();
            if (handler == null) {
                return;
            }
            if (i != 0) {
                handler.sendEmptyMessage(1);
                return;
            }
            if (userInfomation == null) {
                LogUtil.a("PersonalUserInfoManager", "get userinfo success but obtain null objData");
                return;
            }
            LogUtil.a("PersonalUserInfoManager", "get userinfo success objData：", userInfomation.toString());
            Message obtain = Message.obtain();
            obtain.obj = userInfomation;
            obtain.what = 0;
            handler.sendMessage(obtain);
        }
    }
}
