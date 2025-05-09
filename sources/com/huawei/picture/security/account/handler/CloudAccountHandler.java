package com.huawei.picture.security.account.handler;

import com.huawei.picture.security.account.bean.ErrorStatus;
import com.huawei.picture.security.account.bean.SignInRequest;
import com.huawei.picture.security.account.bean.SignInResponse;
import com.huawei.picture.security.account.bean.UserAccountInfo;

/* loaded from: classes6.dex */
public interface CloudAccountHandler {
    boolean isLoginAccount();

    void logFromSdk(String str);

    void onRequestError(ErrorStatus errorStatus);

    void onRequestFinish(UserAccountInfo userAccountInfo);

    SignInResponse requestRefresh(SignInRequest signInRequest);

    SignInResponse requestSignIn(SignInRequest signInRequest, String str);
}
