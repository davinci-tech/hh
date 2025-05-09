package com.huawei.featureuserprofile.media.account;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.featureuserprofile.util.AsyncSelectorSerialize;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.up.model.UserInfomation;
import defpackage.bsf;
import defpackage.glz;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class AccountReader implements UserInfoMedia.UserInfoReader {
    private Context c;
    private boolean e = true;

    public AccountReader(Context context) {
        this.c = context;
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader
    public void read(UserInfoMedia.UserInfoReader.Callback callback) {
        synchronized (this) {
            if (this.e) {
                LogUtil.h("AccountReader", "read block!!!");
            } else if (LoginInit.getInstance(this.c).isLoginedByWear()) {
                LogUtil.a("AccountReader", "K Scence logined by wear,dircet return null");
                bsf.d(callback, -1);
            } else {
                LogUtil.a("AccountReader", "sync(AccountReader) callback:", callback);
                e(callback);
            }
        }
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader
    public void block() {
        synchronized (this) {
            LogUtil.a("AccountReader", "block(AccountReader)");
            this.e = true;
        }
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader
    public void unBlock() {
        synchronized (this) {
            LogUtil.a("AccountReader", "unBlock(AccountReader)");
            this.e = false;
        }
    }

    private void e(final UserInfoMedia.UserInfoReader.Callback callback) {
        synchronized (this) {
            if (this.e) {
                LogUtil.h("AccountReader", "sync block!!!");
                return;
            }
            LogUtil.a("AccountReader", " downloadUserInfo Entry");
            final AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(new Handler(Looper.getMainLooper())) { // from class: com.huawei.featureuserprofile.media.account.AccountReader.4
                @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
                public void onFailed(int i) {
                    bsf.d(callback, i);
                }

                @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
                public void onSuccess(Map map) {
                    if (map.get("userInfomation") instanceof UserInfomation) {
                        bsf.d(callback, (UserInfomation) map.get("userInfomation"));
                    }
                }
            };
            asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: com.huawei.featureuserprofile.media.account.AccountReader.3
                @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
                public void execute(Map map) {
                    AccountReader.this.c(asyncSelectorSerialize);
                }
            });
            asyncSelectorSerialize.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AsyncSelectorSerialize asyncSelectorSerialize) {
        synchronized (this) {
            if (this.e) {
                LogUtil.h("AccountReader", "downloadUserInfoFromUp block!!!");
            } else {
                LogUtil.a("AccountReader", " downloadUserInfoFromUp Entry");
                a(asyncSelectorSerialize);
            }
        }
    }

    private void a(final AsyncSelectorSerialize asyncSelectorSerialize) {
        glz.b("AccountReader");
        ThirdPartyLoginManager.getInstance().queryUserInfo(new IBaseResponseCallback() { // from class: com.huawei.featureuserprofile.media.account.AccountReader.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            public void onResponse(int i, Object obj) {
                UserInfomation userInfomation;
                ReleaseLogUtil.e("R_PersonalInfo_AccountReader", "queryUserInfo onResponse errorCode is ", Integer.valueOf(i));
                if (i == -1 || obj == null || !(obj instanceof HiUserInfo)) {
                    LogUtil.h("AccountReader", "queryUserInfo fail");
                    asyncSelectorSerialize.postError();
                    return;
                }
                HiUserInfo hiUserInfo = (HiUserInfo) obj;
                if (UnitUtil.h()) {
                    userInfomation = new UserInfomation(1);
                } else {
                    userInfomation = new UserInfomation(0);
                }
                userInfomation.setPicPath(SharedPreferenceManager.b(AccountReader.this.c, String.valueOf(PrebakedEffectId.RT_FLY), "key_user_pic_path"));
                userInfomation.loadAccountData(hiUserInfo);
                HashMap hashMap = new HashMap();
                hashMap.put("userInfomation", userInfomation);
                asyncSelectorSerialize.next(hashMap);
            }
        }, false);
    }
}
