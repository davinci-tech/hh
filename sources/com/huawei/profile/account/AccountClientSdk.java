package com.huawei.profile.account;

import android.content.Context;
import com.huawei.profile.coordinator.exception.ProfileRequestException;
import java.util.Map;

/* loaded from: classes6.dex */
public final class AccountClientSdk {
    private static final Object LOCK = new Object();
    private static AccountClientSpi clientSpi;
    private static AccountClientSdk sAccountClientSdk;

    private AccountClientSdk(Context context) {
        if (AccountProxy.getInstance(context).isActivated()) {
            clientSpi = new AccountClientHmsLite(context);
        } else {
            clientSpi = new AccountClientHmsCore(context);
        }
    }

    public static AccountClientSdk getInstance(Context context) {
        AccountClientSdk accountClientSdk;
        synchronized (LOCK) {
            if (sAccountClientSdk == null) {
                sAccountClientSdk = new AccountClientSdk(context);
            }
            accountClientSdk = sAccountClientSdk;
        }
        return accountClientSdk;
    }

    public void updateExpiredAccount() {
        clientSpi.updateExpiredAccount();
    }

    public boolean isExpiredAccount() {
        return clientSpi.isExpiredAccount();
    }

    public Map<String, String> generateRequestHeader() throws ProfileRequestException {
        return clientSpi.generateRequestHeader();
    }

    public void setAccount(Account account) {
        clientSpi.setAccount(account);
    }

    public void setAccountInvalid() {
        clientSpi.setAccountInvalid();
    }

    public void registerAccountReceiver() {
        clientSpi.registerAccountReceiver();
    }
}
