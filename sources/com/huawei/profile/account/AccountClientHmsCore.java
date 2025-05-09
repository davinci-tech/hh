package com.huawei.profile.account;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.openalliance.ad.constant.Action;
import com.huawei.profile.coordinator.exception.ProfileRequestException;
import com.huawei.profile.coordinator.exception.ProfileRequestExceptionType;
import com.huawei.profile.utils.logger.DsLog;
import java.util.Map;

/* loaded from: classes6.dex */
public class AccountClientHmsCore implements AccountClientSpi {
    public static final int GET_ACCOUNT_INFO = 0;
    private static final String TAG = "AccountClientHmsCore";
    private static final long TIMEOUT_MS = 2000;
    private Context mContext;

    AccountClientHmsCore(Context context) {
        this.mContext = context;
    }

    private void requestHmsForAccount(long j) {
        RequestAccountExecutor.getInstance().executeTask(new RequestAccountSdkAction(this.mContext, 0), j);
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public void updateExpiredAccount() {
        Account account = AbstractRequestAccount.getAccount();
        long timestamp = account != null ? account.getTimestamp() : 0L;
        long currentTimeMillis = System.currentTimeMillis();
        if (account != null && currentTimeMillis < timestamp) {
            DsLog.dt(TAG, " " + currentTimeMillis + ":" + timestamp + " access token may be still valid.", new Object[0]);
            return;
        }
        DsLog.dt(TAG, "try to request AT from hms", new Object[0]);
        requestHmsForAccount(TIMEOUT_MS);
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public boolean isExpiredAccount() {
        DsLog.it(TAG, "start judge account info expired.", new Object[0]);
        Account accountInfo = getAccountInfo();
        if (accountInfo != null) {
            return accountInfo.getTimestamp() <= System.currentTimeMillis();
        }
        DsLog.it(TAG, "account is null", new Object[0]);
        return false;
    }

    private Account getAccountInfo() {
        updateExpiredAccount();
        return AbstractRequestAccount.getAccount();
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public Map<String, String> generateRequestHeader() throws ProfileRequestException {
        Account accountInfo = getAccountInfo();
        if (accountInfo == null) {
            throw new ProfileRequestException(ProfileRequestExceptionType.UNEXPECTED_NULL_POINTER, " account is null");
        }
        if (accountInfo.getTimestamp() <= System.currentTimeMillis()) {
            throw new ProfileRequestException(ProfileRequestExceptionType.AT_EXPIRED, "AT expired");
        }
        return AccountClientSdkUtil.generateRequestHeader(this.mContext, accountInfo.getAccessToken(), accountInfo.getUserId());
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public void setAccount(Account account) {
        setAccount(account);
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public void setAccountInvalid() {
        setAccount(null);
    }

    @Override // com.huawei.profile.account.AccountClientSpi
    public void registerAccountReceiver() {
        DsLog.dt(TAG, "register account sign out receiver.", new Object[0]);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.ACTION_HW_ACCOUNT_LOGOUT);
        this.mContext.registerReceiver(new AccountSignOutReceiverSdk(), intentFilter);
    }

    class AccountSignOutReceiverSdk extends BroadcastReceiver {
        private static final String TAG = "AccountSignOutReceiverSdk";

        AccountSignOutReceiverSdk() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DsLog.dt(TAG, "receive hw id sign out broadcast", new Object[0]);
            AccountClientHmsCore.this.accountSignOut();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void accountSignOut() {
        requestHmsForAccount(TIMEOUT_MS);
    }
}
