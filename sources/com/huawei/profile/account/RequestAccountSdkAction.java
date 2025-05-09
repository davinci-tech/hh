package com.huawei.profile.account;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.scheduler.thread.SdkThread;
import com.huawei.profile.utils.AnonymousUtil;
import com.huawei.profile.utils.SensitiveUtil;
import com.huawei.profile.utils.logger.DsLog;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class RequestAccountSdkAction extends AbstractRequestAccount implements Runnable {
    private static final String TAG = "RequestAccountSdkAction";

    RequestAccountSdkAction(Context context, int i) {
        super(context, i);
    }

    @Override // java.lang.Runnable
    public void run() {
        Account account = AbstractRequestAccount.getAccount();
        long timestamp = account != null ? account.getTimestamp() : 0L;
        long currentTimeMillis = System.currentTimeMillis();
        if (account != null && currentTimeMillis < timestamp) {
            DsLog.dt(TAG, "AT already updated, not request from hms again", new Object[0]);
            return;
        }
        if (!AccountUtil.hasLogin(this.context)) {
            ProfileUtilsSdk.getInstance(this.context).deleteAccountData();
            AbstractRequestAccount.setAccount(null);
            DsLog.dt(TAG, "user has not login return false", new Object[0]);
        } else {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            SignResultListener signResultListener = new SignResultListener(countDownLatch);
            silentSignIn(signResultListener, signResultListener, countDownLatch);
        }
    }

    class SignResultListener implements OnSuccessListener<AuthAccount>, OnFailureListener {
        private static final String TAG = "SignResultSDKListener";
        private final CountDownLatch silentSignInWaiter;

        SignResultListener(CountDownLatch countDownLatch) {
            this.silentSignInWaiter = countDownLatch;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        public void onSuccess(final AuthAccount authAccount) {
            new SdkThread("signInSuccess", new Runnable() { // from class: com.huawei.profile.account.RequestAccountSdkAction.SignResultListener.1
                @Override // java.lang.Runnable
                public void run() {
                    DsLog.it(SignResultListener.TAG, "sign in result is success", new Object[0]);
                    SignResultListener.this.processSilentSignInSuccess(authAccount);
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void processSilentSignInSuccess(AuthAccount authAccount) {
            String uid = authAccount.getUid();
            if (TextUtils.isEmpty(uid)) {
                DsLog.et(TAG, "userId is empty", new Object[0]);
                RequestAccountSdkAction requestAccountSdkAction = RequestAccountSdkAction.this;
                requestAccountSdkAction.processRequestHmsFailed(requestAccountSdkAction.requestType, authAccount.getStatus());
            } else {
                String accessToken = authAccount.getAccessToken();
                DsLog.it(TAG, "access token:" + AnonymousUtil.anonymousContent(accessToken), new Object[0]);
                long currentTimeMillis = System.currentTimeMillis();
                Account account = new Account();
                account.setUserId(uid);
                account.setAccessToken(accessToken);
                account.setTimestamp(currentTimeMillis + 3540000);
                AbstractRequestAccount.setAccount(account);
            }
            this.silentSignInWaiter.countDown();
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(final Exception exc) {
            new SdkThread("signInFailed", new Runnable() { // from class: com.huawei.profile.account.RequestAccountSdkAction.SignResultListener.2
                @Override // java.lang.Runnable
                public void run() {
                    DsLog.it(SignResultListener.TAG, "sign in result is failed, error: " + SensitiveUtil.getMessage(exc), new Object[0]);
                    SignResultListener.this.processSilentSignInFailed(exc);
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void processSilentSignInFailed(Exception exc) {
            if (exc instanceof ApiException) {
                int statusCode = ((ApiException) exc).getStatusCode();
                if (statusCode == 2001) {
                    ProfileUtilsSdk.getInstance(RequestAccountSdkAction.this.context).deleteAccountData();
                    AbstractRequestAccount.setAccount(null);
                }
                if (statusCode == 2002) {
                    DsLog.it(TAG, "account sign out or account sign in, authorization required", new Object[0]);
                } else if (statusCode == 2005 || statusCode == 2007) {
                    DsLog.et(TAG, "network error: " + statusCode, new Object[0]);
                } else {
                    DsLog.et(TAG, "other error", new Object[0]);
                }
                RequestAccountSdkAction requestAccountSdkAction = RequestAccountSdkAction.this;
                requestAccountSdkAction.processRequestHmsFailed(requestAccountSdkAction.requestType, statusCode);
            } else {
                DsLog.it(TAG, "unexpected exception: " + SensitiveUtil.getSimpleName(exc), new Object[0]);
            }
            this.silentSignInWaiter.countDown();
        }
    }
}
