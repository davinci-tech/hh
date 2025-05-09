package com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthDb;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthTable;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class QqLogin {
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_FIGURE_LOGO = "figureurl_qq_2";
    private static final String KEY_NICK_NAME = "nickname";
    private static final String KEY_OPEN_ID = "openid";
    private static final String KEY_QQ_NAME = "QQ";
    private static final String TAG = "QqLogin";
    private Activity mActivity;
    private AuthorizeCallback mAuthorizeCallback;
    private IUiListener mListener;
    private Tencent mTencent;
    private String mOpenId = "";
    private String mAccessToken = "";
    private String mExpireTime = "";
    private String mNickName = " ";
    private String mUserLogoPath = "";

    public interface QqNameCallback {
        void onQqNameCallback(String str);
    }

    QqLogin(Activity activity, AuthorizeCallback authorizeCallback, String str) {
        if (activity == null) {
            LogUtil.a(TAG, "activity == null");
            return;
        }
        this.mActivity = activity;
        if (!TextUtils.isEmpty(str)) {
            this.mTencent = Tencent.createInstance(str, this.mActivity);
        }
        this.mAuthorizeCallback = authorizeCallback;
        if (authorizeCallback != null) {
            authorizeCallback.initCallback(this.mTencent != null);
        }
    }

    public void login() {
        LogUtil.a(TAG, "login() enter.mTencent = ", this.mTencent);
        if (this.mTencent == null) {
            return;
        }
        this.mListener = new BaseUiListener();
        Tencent.setIsPermissionGranted(true);
        this.mTencent.login(this.mActivity, "all", this.mListener);
    }

    public void logout() {
        LogUtil.a(TAG, "logout() enter.mTencent = ", this.mTencent);
        Tencent tencent = this.mTencent;
        if (tencent == null) {
            return;
        }
        tencent.logout(this.mActivity);
        LogUtil.a(TAG, "logout() leave.");
    }

    class BaseUiListener implements IUiListener {
        private BaseUiListener() {
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            if (obj instanceof JSONObject) {
                try {
                    JSONObject jSONObject = (JSONObject) obj;
                    if (jSONObject.isNull("openid") || jSONObject.isNull("access_token")) {
                        return;
                    }
                    QqLogin.this.mOpenId = jSONObject.getString("openid");
                    QqLogin.this.mAccessToken = jSONObject.getString("access_token");
                    QqLogin.this.mExpireTime = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                    QqLogin qqLogin = QqLogin.this;
                    qqLogin.initOpenIdAndToken(qqLogin.mOpenId, QqLogin.this.mAccessToken, QqLogin.this.mExpireTime);
                    if (QqLogin.this.mAuthorizeCallback != null) {
                        QqLogin.this.mAuthorizeCallback.loginCallback(QqLogin.this.mAccessToken, QqLogin.this.mOpenId, "QQ");
                    }
                } catch (JSONException unused) {
                    LogUtil.b(QqLogin.TAG, "onComplete() JSONException");
                }
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            if (uiError == null) {
                LogUtil.b(QqLogin.TAG, "onError() uiError = null");
            } else {
                LogUtil.b(QqLogin.TAG, "onError() errorCode = ", Integer.valueOf(uiError.errorCode));
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            LogUtil.b(QqLogin.TAG, "onCancel()");
        }

        @Override // com.tencent.tauth.IUiListener
        public void onWarning(int i) {
            LogUtil.b(QqLogin.TAG, "onWarning()");
        }
    }

    void getUserName(Context context, final QqNameCallback qqNameCallback) {
        LogUtil.a(TAG, "enter getUserName");
        if (context == null) {
            LogUtil.a(TAG, "getUserName context == null");
            qqNameCallback.onQqNameCallback("");
        }
        if (this.mTencent != null) {
            new UserInfo(context, this.mTencent.getQQToken()).getUserInfo(new IUiListener() { // from class: com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqLogin.1
                @Override // com.tencent.tauth.IUiListener
                public void onError(UiError uiError) {
                    LogUtil.b(QqLogin.TAG, "onError() uiError = ", uiError);
                    qqNameCallback.onQqNameCallback("");
                }

                @Override // com.tencent.tauth.IUiListener
                public void onComplete(Object obj) {
                    if (obj instanceof JSONObject) {
                        JSONObject jSONObject = (JSONObject) obj;
                        QqLogin.this.mNickName = jSONObject.optString(QqLogin.KEY_NICK_NAME);
                        QqLogin.this.mUserLogoPath = jSONObject.optString(QqLogin.KEY_FIGURE_LOGO);
                    }
                    qqNameCallback.onQqNameCallback(QqLogin.this.mNickName == null ? "" : QqLogin.this.mNickName);
                }

                @Override // com.tencent.tauth.IUiListener
                public void onCancel() {
                    qqNameCallback.onQqNameCallback("");
                }

                @Override // com.tencent.tauth.IUiListener
                public void onWarning(int i) {
                    qqNameCallback.onQqNameCallback("");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOpenIdAndToken(String str, String str2, String str3) {
        LogUtil.c(TAG, "initQQSDK() enter. mTencent=", this.mTencent);
        this.mOpenId = str;
        this.mAccessToken = str2;
        this.mExpireTime = str3;
        Tencent tencent = this.mTencent;
        if (tencent != null) {
            tencent.setAccessToken(str2, str3);
            this.mTencent.setOpenId(str);
        }
    }

    public void saveQqUserInfo() {
        QqHealthTable qqHealthTable = new QqHealthTable();
        qqHealthTable.setOpenId(this.mOpenId);
        qqHealthTable.setToken(this.mAccessToken);
        qqHealthTable.setExpireTime(this.mExpireTime);
        qqHealthTable.setNickName(this.mNickName);
        qqHealthTable.setQqLogoPath(this.mUserLogoPath);
        QqHealthDb qqHealthDb = new QqHealthDb();
        qqHealthDb.delete();
        qqHealthDb.insert(qqHealthTable);
        LogUtil.a(TAG, "saveQQUserInfo() leave.");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Tencent.onActivityResultData(i, i2, intent, this.mListener);
        LogUtil.c(TAG, "onActivityResult requestCode = ", Integer.valueOf(i), " resultCode = ", Integer.valueOf(i2));
    }
}
