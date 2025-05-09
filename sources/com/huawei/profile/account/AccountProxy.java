package com.huawei.profile.account;

import android.content.Context;
import com.huawei.profile.utils.logger.DsLog;

/* loaded from: classes6.dex */
public final class AccountProxy {
    private static final int FAILURE_CODE = 1;
    private static final Object LOCK = new Object();
    public static final int SIGN_IN_SUCCESS = 0;
    public static final int SIGN_UN_LOGIN = 2001;
    private static final int SUCCESS_CODE = 0;
    private static final String TAG = "AccountProxy";
    private static AccountProxy proxy;
    private Context context;
    private volatile boolean isActivated;
    private volatile AccountProvider provider;

    private AccountProxy(Context context) {
        this.context = context;
    }

    public static AccountProxy getInstance(Context context) {
        AccountProxy accountProxy;
        synchronized (LOCK) {
            if (proxy == null) {
                proxy = new AccountProxy(context);
            }
            accountProxy = proxy;
        }
        return accountProxy;
    }

    public boolean isActivated() {
        return this.isActivated;
    }

    public void setActivated(boolean z) {
        DsLog.it(TAG, "start to call setActivated function, the activated is " + z, new Object[0]);
        this.isActivated = z;
    }

    public AccountProvider getProvider() {
        return this.provider;
    }

    public int setProvider(AccountProvider accountProvider) {
        if (accountProvider == null) {
            DsLog.et(TAG, "account provider is null", new Object[0]);
            return 1;
        }
        this.provider = accountProvider;
        return 0;
    }

    public int accountLogout() {
        DsLog.dt(TAG, "receive hw id sign out notification", new Object[0]);
        AccountClientHmsLite.accountSignOut(this.context);
        return 0;
    }
}
