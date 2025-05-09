package com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqLogin;

/* loaded from: classes8.dex */
public class QqLoginMgr {
    public static final String APP_ID_HW_ACCOUNT = "246EEE3DEB39B92A8A5401AFAFB7A145+St7Toa8/8bYhaXnNQHVSSTd0ad2hguXwKeUK9fBK/g= ";
    public static final String MARKET_URI = "market://details?id=com.tencent.mobileqq";
    public static final String PACKAGE_NAME = "com.tencent.mobileqq";
    private static final String TAG = "QqLoginMgr";
    private AuthorizeCallback mAuthorizeCallback;
    private AuthorizeCallback mAuthorizeCallbackTemp = new AuthorizeCallback() { // from class: com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqLoginMgr.1
        @Override // com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback
        public void initCallback(boolean z) {
            if (QqLoginMgr.this.mAuthorizeCallback != null) {
                LogUtil.a(QqLoginMgr.TAG, "initCallback(", Boolean.valueOf(z), ") ");
                QqLoginMgr.this.mAuthorizeCallback.initCallback(z);
            }
        }

        @Override // com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback
        public void logoutCallback(boolean z) {
            if (QqLoginMgr.this.mAuthorizeCallback != null) {
                LogUtil.a(QqLoginMgr.TAG, "logoutCallback(", Boolean.valueOf(z), ") ");
                QqLoginMgr.this.mAuthorizeCallback.logoutCallback(z);
            }
        }

        @Override // com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback
        public void loginCallback(String str, String str2, String str3) {
            if (QqLoginMgr.this.mAuthorizeCallback != null) {
                LogUtil.a(QqLoginMgr.TAG, "loginCallback() success.");
                QqLoginMgr.this.mAuthorizeCallback.loginCallback(str, str2, str3);
            }
        }
    };
    private QqLogin mQqLogin;

    public QqLoginMgr(Activity activity, AuthorizeCallback authorizeCallback, String str) {
        this.mQqLogin = null;
        this.mAuthorizeCallback = null;
        if (authorizeCallback != null) {
            this.mAuthorizeCallback = authorizeCallback;
        }
        if (!TextUtils.isEmpty(str)) {
            this.mQqLogin = new QqLogin(activity, this.mAuthorizeCallbackTemp, str);
        }
        LogUtil.c(TAG, "QqLogin() mAuthorizeCallback=", this.mAuthorizeCallback, ", mQQLoginManager=", this.mQqLogin);
    }

    public void login() {
        if (this.mQqLogin != null) {
            LogUtil.a(TAG, "login() ");
            this.mQqLogin.login();
        }
    }

    public void logout() {
        if (this.mQqLogin != null) {
            LogUtil.a(TAG, "logout() ");
            this.mQqLogin.logout();
        }
    }

    public void getUserName(Context context, QqLogin.QqNameCallback qqNameCallback) {
        LogUtil.c(TAG, "getUserName mQqLogin=", this.mQqLogin);
        QqLogin qqLogin = this.mQqLogin;
        if (qqLogin != null) {
            qqLogin.getUserName(context, qqNameCallback);
        } else {
            qqNameCallback.onQqNameCallback("");
        }
    }

    public void saveQqUserInfo() {
        LogUtil.c(TAG, "saveQqUserInfo mQqLogin=", this.mQqLogin);
        QqLogin qqLogin = this.mQqLogin;
        if (qqLogin != null) {
            qqLogin.saveQqUserInfo();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mQqLogin.onActivityResult(i, i2, intent);
    }
}
