package com.huawei.hms.support.hwid;

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
import com.huawei.hms.hwid.x;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.hwid.common.HuaweiIdAuthException;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthExtendedParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.result.HuaweiIdAuthResult;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthServiceImpl;
import java.util.List;

/* loaded from: classes4.dex */
public final class HuaweiIdAuthManager {
    public static HuaweiIdAuthService getService(Context context, HuaweiIdAuthParams huaweiIdAuthParams) {
        return new HuaweiIdAuthServiceImpl(context, huaweiIdAuthParams, AuthInternalPickerConstant.HMS_SDK_VERSION);
    }

    public static HuaweiIdAuthService getService(Activity activity, HuaweiIdAuthParams huaweiIdAuthParams) {
        return new HuaweiIdAuthServiceImpl(activity, huaweiIdAuthParams, AuthInternalPickerConstant.HMS_SDK_VERSION);
    }

    public static Task<AuthHuaweiId> parseAuthResultFromIntent(Intent intent) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        HuaweiIdAuthResult a2 = x.a(intent);
        if (a2 == null) {
            taskCompletionSource.setException(new ApiException(new Status(8)));
        } else if (!a2.isSuccess() || a2.getHuaweiId() == null) {
            taskCompletionSource.setException(new ApiException(a2.getStatus()));
        } else {
            taskCompletionSource.setResult(a2.getHuaweiId());
        }
        return taskCompletionSource.getTask();
    }

    public static AuthHuaweiId getAuthResult() {
        return x.b();
    }

    public static AuthHuaweiId getAuthResultWithScopes(List<Scope> list) throws HuaweiIdAuthException {
        if (ap.a(list).booleanValue()) {
            throw new HuaweiIdAuthException("ScopeList should not be empty");
        }
        AuthHuaweiId b = x.b();
        if (b == null) {
            b = new AuthHuaweiId();
        }
        b.requestExtraScopes(list);
        return b;
    }

    public static AuthHuaweiId getExtendedAuthResult(HuaweiIdAuthExtendedParams huaweiIdAuthExtendedParams) {
        if (huaweiIdAuthExtendedParams == null) {
            throw new NullPointerException("HuaweiIdAuthExtendedParams should not be null");
        }
        List<Scope> extendedScopes = huaweiIdAuthExtendedParams.getExtendedScopes();
        AuthHuaweiId b = x.b();
        if (b == null) {
            b = new AuthHuaweiId();
        }
        return b.requestExtraScopes(extendedScopes);
    }

    public static boolean containScopes(AuthHuaweiId authHuaweiId, HuaweiIdAuthExtendedParams huaweiIdAuthExtendedParams) {
        if (huaweiIdAuthExtendedParams == null) {
            return false;
        }
        return containScopes(authHuaweiId, huaweiIdAuthExtendedParams.getExtendedScopes());
    }

    public static boolean containScopes(AuthHuaweiId authHuaweiId, List<Scope> list) {
        if (authHuaweiId == null) {
            return false;
        }
        if (ap.a(list).booleanValue()) {
            return true;
        }
        return authHuaweiId.getAuthorizedScopes().containsAll(list);
    }

    public static void addAuthScopes(Activity activity, int i, HuaweiIdAuthExtendedParams huaweiIdAuthExtendedParams) {
        if (huaweiIdAuthExtendedParams == null) {
            throw new NullPointerException("HuaweiIdAuthExtendedParams should not be null");
        }
        addAuthScopes(activity, i, huaweiIdAuthExtendedParams.getExtendedScopes());
    }

    public static void addAuthScopes(Fragment fragment, int i, HuaweiIdAuthExtendedParams huaweiIdAuthExtendedParams) {
        if (huaweiIdAuthExtendedParams == null) {
            throw new NullPointerException("HuaweiIdAuthExtendedParams should not be null");
        }
        addAuthScopes(fragment, i, huaweiIdAuthExtendedParams.getExtendedScopes());
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
            as.d("HuaweiIdAuthManager", "Exception：" + e.getClass().getSimpleName(), true);
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
            as.d("HuaweiIdAuthManager", "Exception：" + e.getClass().getSimpleName(), true);
        }
    }

    private static Intent a(Activity activity, List<Scope> list) {
        return getService(activity, x.a(list)).getSignInIntent();
    }
}
