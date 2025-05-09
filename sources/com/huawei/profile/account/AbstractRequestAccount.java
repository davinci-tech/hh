package com.huawei.profile.account;

import android.content.Context;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.profile.utils.logger.DsLog;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public abstract class AbstractRequestAccount {
    static final int BACKGROUND_NETWORK_RESTRICT_CODE = 2007;
    public static final int GET_ACCOUNT_INFO = 0;
    static final String TAG = "AbstractRequestAccount";
    static final long TIMEOUT = 5000;
    static final long TOKEN_EXPIRED_TIME = 3540000;
    static volatile Account account = new Account();
    private AccountAuthService authService;
    Context context;
    int requestType;

    AbstractRequestAccount(Context context, int i) {
        this.context = context;
        this.requestType = i;
    }

    void processRequestHmsFailed(int i, int i2) {
        if (i == 0) {
            DsLog.et(TAG, "failed to get account info, error code = " + i2, new Object[0]);
        }
    }

    static void setAccount(Account account2) {
        synchronized (AbstractRequestAccount.class) {
            if (account2 == null) {
                account2 = new Account();
            }
            account = account2;
        }
    }

    static Account getAccount() {
        return account;
    }

    protected void silentSignIn(OnSuccessListener<AuthAccount> onSuccessListener, OnFailureListener onFailureListener, CountDownLatch countDownLatch) {
        DsLog.dt(TAG, "entering account silent sign in", new Object[0]);
        AccountAuthService idAuthService = getIdAuthService();
        if (idAuthService == null) {
            DsLog.et(TAG, "authService is null", new Object[0]);
            return;
        }
        Task<AuthAccount> silentSignIn = idAuthService.silentSignIn();
        if (silentSignIn == null) {
            DsLog.et(TAG, "sign task is null", new Object[0]);
            return;
        }
        silentSignIn.addOnSuccessListener(onSuccessListener);
        silentSignIn.addOnFailureListener(onFailureListener);
        waitForCallback(countDownLatch);
        DsLog.dt(TAG, "leaving account silent sign in", new Object[0]);
    }

    private AccountAuthService getIdAuthService() {
        AccountAuthService accountAuthService;
        synchronized (this) {
            if (this.authService == null) {
                this.authService = AccountAuthManager.getService(this.context, new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setUid().setAuthorizationCode().setAccessToken().createParams());
            }
            accountAuthService = this.authService;
        }
        return accountAuthService;
    }

    private static void waitForCallback(CountDownLatch countDownLatch) {
        try {
            countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            DsLog.et(TAG, "Waiting for call back is interrupted!", new Object[0]);
        }
    }
}
