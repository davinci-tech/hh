package com.huawei.hwdevice.mainprocess.mgr.intelligentlifemgr;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.profile.account.AccountInfo;
import com.huawei.profile.account.AccountProvider;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class IntellLifeProviderImpl implements AccountProvider {
    private static String b = "";
    private static String c = "";
    private Context d;

    public IntellLifeProviderImpl(Context context) {
        this.d = context;
    }

    @Override // com.huawei.profile.account.AccountProvider
    public AccountInfo getAccountInfo() {
        LogUtil.a("IntellLifeProviderImpl", "getAccountInfo enter");
        LoginInit loginInit = LoginInit.getInstance(this.d);
        String accountInfo = loginInit.getAccountInfo(1008);
        String accountInfo2 = loginInit.getAccountInfo(1011);
        int i = 0;
        if (!TextUtils.isEmpty(accountInfo) && !TextUtils.isEmpty(accountInfo2)) {
            a(accountInfo);
            b(accountInfo2);
        } else if (!TextUtils.isEmpty(c) && !TextUtils.isEmpty(b)) {
            LogUtil.h("IntellLifeProviderImpl", "getAccountInfo return local at and id");
            accountInfo2 = b;
            accountInfo = c;
        } else {
            LogUtil.h("IntellLifeProviderImpl", "getAccountInfo accessToken or userId is empty");
            i = 2001;
        }
        AccountInfo accountInfo3 = new AccountInfo();
        accountInfo3.setUserId(accountInfo2);
        accountInfo3.setAccessToken(accountInfo);
        accountInfo3.setRetCode(i);
        accountInfo3.setExpireTime(CommonUtil.g(LoginInit.getInstance(this.d).getAccountInfo(1016)));
        return accountInfo3;
    }

    @Override // com.huawei.profile.account.AccountProvider
    public void setAccessTokenInvalid() {
        LogUtil.h("IntellLifeProviderImpl", "setAccessTokenInvalid start to call setAccessTokenInvalid");
    }

    public static void b(String str) {
        b = str;
    }

    public static void a(String str) {
        c = str;
    }
}
