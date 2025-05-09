package com.huawei.hms.support.account;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.hwid.ap;
import com.huawei.hms.hwid.as;
import com.huawei.hms.hwid.f;
import com.huawei.hms.support.account.common.AccountAuthException;
import com.huawei.hms.support.account.request.AccountAuthExtendedParams;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.result.AccountAuthResult;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.account.service.AccountAuthServiceImpl;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.util.List;

/* loaded from: classes.dex */
public final class AccountAuthManager {
    public static AccountAuthService getService(Context context, AccountAuthParams accountAuthParams) {
        return new AccountAuthServiceImpl(context, accountAuthParams, AuthInternalPickerConstant.HMS_SDK_VERSION);
    }

    public static AccountAuthService getService(Activity activity, AccountAuthParams accountAuthParams) {
        return new AccountAuthServiceImpl(activity, accountAuthParams, AuthInternalPickerConstant.HMS_SDK_VERSION);
    }

    public static Task<AuthAccount> parseAuthResultFromIntent(Intent intent) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        AccountAuthResult a2 = f.a(intent);
        if (a2 == null) {
            taskCompletionSource.setException(new ApiException(new Status(8)));
        } else if (!a2.isSuccess() || a2.getAccount() == null) {
            taskCompletionSource.setException(new ApiException(a2.getStatus()));
        } else {
            taskCompletionSource.setResult(a2.getAccount());
        }
        return taskCompletionSource.getTask();
    }

    public static AuthAccount getAuthResult() {
        return f.b();
    }

    public static AuthAccount getAuthResultWithScopes(List<Scope> list) throws AccountAuthException {
        if (ap.a(list).booleanValue()) {
            throw new AccountAuthException("ScopeList should not be empty");
        }
        AuthAccount b = f.b();
        if (b == null) {
            b = new AuthAccount();
        }
        b.requestExtraScopes(list);
        return b;
    }

    public static AuthAccount getExtendedAuthResult(AccountAuthExtendedParams accountAuthExtendedParams) {
        if (accountAuthExtendedParams == null) {
            throw new NullPointerException("AccountAuthExtendedParams should not be null");
        }
        List<Scope> extendedScopes = accountAuthExtendedParams.getExtendedScopes();
        AuthAccount b = f.b();
        if (b == null) {
            b = new AuthAccount();
        }
        return b.requestExtraScopes(extendedScopes);
    }

    public static boolean containScopes(AuthAccount authAccount, AccountAuthExtendedParams accountAuthExtendedParams) {
        if (accountAuthExtendedParams == null) {
            return false;
        }
        return containScopes(authAccount, accountAuthExtendedParams.getExtendedScopes());
    }

    public static boolean containScopes(AuthAccount authAccount, List<Scope> list) {
        if (authAccount == null) {
            return false;
        }
        if (ap.a(list).booleanValue()) {
            return true;
        }
        return authAccount.getAuthorizedScopes().containsAll(list);
    }

    public static void addAuthScopes(Activity activity, int i, AccountAuthExtendedParams accountAuthExtendedParams) {
        if (accountAuthExtendedParams == null) {
            throw new NullPointerException("AccountAuthExtendedParams should not be null");
        }
        addAuthScopes(activity, i, accountAuthExtendedParams.getExtendedScopes());
    }

    public static void addAuthScopes(Fragment fragment, int i, AccountAuthExtendedParams accountAuthExtendedParams) {
        if (accountAuthExtendedParams == null) {
            throw new NullPointerException("AccountAuthExtendedParams should not be null");
        }
        addAuthScopes(fragment, i, accountAuthExtendedParams.getExtendedScopes());
    }

    public static void addAuthScopes(Activity activity, int i, List<Scope> list) {
        if (activity == null) {
            throw new NullPointerException("Activity should not be null");
        }
        if (list == null) {
            throw new NullPointerException("ScopeList should not be null");
        }
        try {
            activity.startActivityForResult(a(activity, list), i);
        } catch (Exception e) {
            as.d("AccountAuthManager", "Exception：" + e.getClass().getSimpleName(), true);
        }
    }

    public static void addAuthScopes(Fragment fragment, int i, List<Scope> list) {
        if (fragment == null) {
            throw new NullPointerException("Fragment should not be null");
        }
        if (list == null) {
            throw new NullPointerException("ScopeList should not be null");
        }
        try {
            fragment.startActivityForResult(a(fragment.getActivity(), list), i);
        } catch (Exception e) {
            as.d("AccountAuthManager", "Exception：" + e.getClass().getSimpleName(), true);
        }
    }

    private static Intent a(Activity activity, List<Scope> list) {
        return getService(activity, f.a(list)).getSignInIntent();
    }
}
