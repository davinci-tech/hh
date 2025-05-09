package com.huawei.profile.account;

import android.content.Context;
import com.huawei.profile.coordinator.exception.ProfileRequestException;
import com.huawei.profile.coordinator.exception.ProfileRequestExceptionType;
import com.huawei.profile.coordinator.task.ProfileTaskPoolSdk;
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.scheduler.thread.SdkThread;
import com.huawei.profile.utils.logger.DsLog;
import java.util.Map;

/* loaded from: classes6.dex */
public class AccountClientHmsLite implements AccountClientSpi {
    private static final int AT_REFRESH_WAIT_TIME = 300;
    private static final String ERROR_CODE = " Failed to get account, error code:";
    private static final String TAG = "AccountClientHmsLite";
    private Context mContext;

    @Override // com.huawei.profile.account.AccountClientSpi
    public void registerAccountReceiver() {
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public void setAccount(Account account) {
    }

    public AccountClientHmsLite(Context context) {
        this.mContext = context;
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public void updateExpiredAccount() {
        AccountProvider provider = AccountProxy.getInstance(this.mContext).getProvider();
        if (provider == null) {
            DsLog.et(TAG, "the account provider is null when update account", new Object[0]);
            return;
        }
        AccountInfo accountInfo = provider.getAccountInfo();
        if (accountInfo == null) {
            DsLog.et(TAG, "the account info is null", new Object[0]);
            return;
        }
        if (accountInfo.getRetCode() == 0) {
            DsLog.it(TAG, "get accountInfo successfully", new Object[0]);
        } else {
            if (accountInfo.getRetCode() == 2001) {
                DsLog.dt(TAG, ERROR_CODE + accountInfo.getRetCode(), new Object[0]);
                return;
            }
            DsLog.wt(TAG, "the retCode from hms lite is illegal", new Object[0]);
        }
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public boolean isExpiredAccount() {
        AccountProvider provider = AccountProxy.getInstance(this.mContext).getProvider();
        if (provider == null) {
            DsLog.et(TAG, "the account provider is null when get account", new Object[0]);
            return true;
        }
        AccountInfo accountInfo = provider.getAccountInfo();
        if (accountInfo == null) {
            DsLog.et(TAG, "the account info is null when get account", new Object[0]);
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (accountInfo.getExpireTime() <= currentTimeMillis) {
            try {
                Thread.sleep(300L);
                return provider.getAccountInfo().getExpireTime() <= currentTimeMillis;
            } catch (InterruptedException unused) {
                DsLog.et(TAG, "isExpiredAccount InterruptedException exception", new Object[0]);
            }
        }
        return false;
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public Map<String, String> generateRequestHeader() throws ProfileRequestException {
        AccountProvider provider = AccountProxy.getInstance(this.mContext).getProvider();
        if (provider == null) {
            DsLog.et(TAG, "the account provider is null when generate request header", new Object[0]);
            throw new ProfileRequestException(ProfileRequestExceptionType.UNEXPECTED_NULL_POINTER, " provider is null");
        }
        AccountInfo accountInfo = provider.getAccountInfo();
        if (accountInfo == null) {
            DsLog.et(TAG, "the account info is null", new Object[0]);
            throw new ProfileRequestException(ProfileRequestExceptionType.UNEXPECTED_NULL_POINTER, " account is null");
        }
        if (accountInfo.getRetCode() == 0) {
            return AccountClientSdkUtil.generateRequestHeader(this.mContext, accountInfo.getAccessToken(), accountInfo.getUserId());
        }
        throw new ProfileRequestException(ProfileRequestExceptionType.UNEXPECTED_NULL_POINTER, " the retCode from hms lite is illegal");
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public void setAccountInvalid() {
        DsLog.wt(TAG, "the AT expired", new Object[0]);
        AccountProvider provider = AccountProxy.getInstance(this.mContext).getProvider();
        if (provider == null) {
            DsLog.et(TAG, "the account provider is null when set account invalid", new Object[0]);
        } else {
            provider.setAccessTokenInvalid();
        }
    }

    public static void accountSignOut(final Context context) {
        if (context == null) {
            DsLog.et(TAG, "the context is null", new Object[0]);
        } else {
            new SdkThread("accountSignOut", new Runnable() { // from class: com.huawei.profile.account.AccountClientHmsLite.1
                @Override // java.lang.Runnable
                public void run() {
                    ProfileTaskPoolSdk.getInstance().execute(context, 1, true, 2);
                    ProfileUtilsSdk.getInstance(context).deleteAccountData();
                }
            }).start();
        }
    }
}
