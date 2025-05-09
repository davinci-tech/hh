package com.huawei.hms.support.picker.common;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.UserManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.huawei.hms.a.a.c.a;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.common.utils.AccountPickerEmuiUtil;
import com.huawei.hms.support.api.entity.hwid.AccountPickerSignInRequest;
import com.huawei.hms.support.picker.activity.AccountPickerSignInHubActivity;
import com.huawei.hms.support.picker.request.AccountPickerParams;
import com.huawei.hms.support.picker.result.AccountPickerResult;
import com.huawei.hms.support.picker.result.AuthAccountPicker;
import com.huawei.hms.utils.Util;
import defpackage.ksy;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AccountPickerUtil {
    private static final String DISPLAYSIDEREGIONEX = "com.huawei.android.view.DisplaySideRegionEx";
    private static final String HUAWEI_ANDROID_VIEW_LAYOUTPARAMSEX = "com.huawei.android.view.LayoutParamsEx";
    private static final String HWFLAGS = "addHwFlags";
    private static final int LAYOUT_IN_DISPLAY = 1;
    private static final int SECURE_SCREENSHOT = 4096;
    private static final String TAG = "[ACCOUNTSDK]AccountPickerUtil";
    private static final String WINDOW_MANAGER_EX_LAYOUT_PARAMS_EX = "com.huawei.android.view.WindowManagerEx$LayoutParamsEx";
    private static Boolean isPhoneStillInLockMode;

    public static void clearSignInAccountCache() {
        a.a().c();
    }

    public static AuthAccountPicker getCachedSignInAccount() {
        return a.a().b();
    }

    public static AccountPickerResult getSignInResultFromIntent(Intent intent) {
        ksy.b(TAG, "getSignInResultFromIntent", true);
        if (intent == null || !intent.hasExtra("HUAWEIID_SIGNIN_RESULT")) {
            ksy.c(TAG, "data or signInResult is null", true);
            return null;
        }
        try {
            return new AccountPickerResult().fromJson(intent.getStringExtra("HUAWEIID_SIGNIN_RESULT"));
        } catch (JSONException unused) {
            ksy.c(TAG, "JSONException", true);
            return null;
        }
    }

    public static Intent getSignInIntent(Context context, AccountPickerParams accountPickerParams, String str, String str2, int i, boolean z) {
        ksy.b(TAG, "getSignInIntent", true);
        Intent intent = new Intent(AuthInternalPickerConstant.IntentAction.ACTION_SIGN_IN_HUB);
        try {
            intent.setPackage(context.getPackageName());
            if (z) {
                ksy.b(TAG, "pickerType is " + i, true);
                try {
                    JSONObject jSONObject = new JSONObject(accountPickerParams.getSignInParams());
                    jSONObject.put(AuthInternalPickerConstant.SignInReqKey.PICKER_TYPE, i);
                    accountPickerParams.setSignInParams(jSONObject.toString());
                } catch (JSONException e) {
                    ksy.c(TAG, "JSONException:" + e.getClass().getSimpleName(), true);
                }
            }
            intent.setClass(context, AccountPickerSignInHubActivity.class);
            intent.putExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDSIGNINREQUEST, str2);
            intent.putExtra(AuthInternalPickerConstant.SignInReqKey.IS_NEW_SERVICE, z);
            String appId = Util.getAppId(context);
            String packageName = context.getPackageName();
            com.huawei.hms.a.a.a.a aVar = new com.huawei.hms.a.a.a.a();
            aVar.setAppId(appId);
            aVar.setPackageName(packageName);
            aVar.setHmsSdkVersion(61200300L);
            aVar.setSubAppId(str);
            AccountPickerSignInRequest accountPickerSignInRequest = new AccountPickerSignInRequest();
            accountPickerSignInRequest.setAccountPickerParams(accountPickerParams);
            try {
                intent.putExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDCPCLIENTINFO, aVar.toJson());
                intent.putExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDSIGNINREQUEST, accountPickerSignInRequest.toJson());
            } catch (JSONException e2) {
                ksy.c(TAG, "JSONException:" + e2.getClass().getSimpleName(), true);
            }
        } catch (Exception e3) {
            ksy.c(TAG, "Exception:" + e3.getClass().getSimpleName(), true);
        }
        return intent;
    }

    public static Intent getSignInByMcpIntent(Context context, AccountPickerParams accountPickerParams, String str, String str2, int i, boolean z) {
        Intent signInIntent = getSignInIntent(context, accountPickerParams, str, str2, i, z);
        signInIntent.putExtra(AuthInternalPickerConstant.SignInReqKey.MCP_SIGN_IN, true);
        return signInIntent;
    }

    public static boolean networkIsAvaiable(Context context) {
        ksy.b(TAG, "enter networkIsAvaiable", true);
        if (context == null) {
            ksy.c(TAG, "context is null", true);
            return false;
        }
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        if (systemService == null) {
            ksy.c(TAG, "connectivity is null,so networkIsAvaiable is unaviable", true);
            return false;
        }
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) systemService).getAllNetworkInfo();
        if (allNetworkInfo == null || allNetworkInfo.length == 0) {
            ksy.b(TAG, "NetworkInfo is null,so networkIsAvaiable is unaviable", true);
            return false;
        }
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        ksy.b(TAG, "NetworkInfo state is unaviable", true);
        return false;
    }

    public static void setEMUI10StatusBarColor(Activity activity) {
        if (AccountPickerEmuiUtil.isAboveEMUI100()) {
            initOnApplyWindowInsets(activity);
        }
    }

    private static void initOnApplyWindowInsets(Activity activity) {
        ksy.b(TAG, "begin initOnApplyWindowInsets", true);
        final ViewGroup viewGroup = (ViewGroup) activity.getWindow().findViewById(R.id.content);
        if (viewGroup == null) {
            ksy.c(TAG, "sRootView is null", true);
        } else {
            setLayoutDisplaySide(activity);
            activity.getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.huawei.hms.support.picker.common.AccountPickerUtil.1
                @Override // android.view.View.OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    try {
                        Object invoke = Class.forName(AccountPickerUtil.WINDOW_MANAGER_EX_LAYOUT_PARAMS_EX).getMethod("getDisplaySideRegion", WindowInsets.class).invoke(null, windowInsets);
                        if (invoke == null) {
                            ksy.b(AccountPickerUtil.TAG, "Non-hypersurface equipment", true);
                        } else {
                            Rect rect = (Rect) Class.forName(AccountPickerUtil.DISPLAYSIDEREGIONEX).getMethod("getSafeInsets", new Class[0]).invoke(invoke, new Object[0]);
                            ksy.b(AccountPickerUtil.TAG, "safeInsets Left and Right: " + rect.left + ":" + rect.right, true);
                            ksy.b(AccountPickerUtil.TAG, "safeInsets Top and BOttom: " + rect.top + ":" + rect.bottom, true);
                            ViewGroup viewGroup2 = viewGroup;
                            if (viewGroup2 != null) {
                                viewGroup2.setPadding(rect.left, 0, rect.right, 0);
                            }
                        }
                    } catch (ClassNotFoundException unused) {
                        ksy.c(AccountPickerUtil.TAG, "ClassNotFoundException", true);
                    } catch (IllegalAccessException unused2) {
                        ksy.c(AccountPickerUtil.TAG, "IllegalAccessException", true);
                    } catch (NoSuchMethodException unused3) {
                        ksy.c(AccountPickerUtil.TAG, "NoSuchMethodException", true);
                    } catch (InvocationTargetException unused4) {
                        ksy.c(AccountPickerUtil.TAG, "InvocationTargetException", true);
                    } catch (Throwable th) {
                        ksy.c(AccountPickerUtil.TAG, "onApplyWindowInsets: " + th.getClass().getSimpleName(), true);
                    }
                    return view.onApplyWindowInsets(windowInsets);
                }
            });
        }
    }

    private static void setLayoutDisplaySide(Activity activity) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        try {
            Class<?> cls = Class.forName(WINDOW_MANAGER_EX_LAYOUT_PARAMS_EX);
            cls.getMethod("setDisplaySideMode", Integer.TYPE).invoke(cls.getDeclaredConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 1);
        } catch (ClassNotFoundException unused) {
            ksy.c(TAG, "ClassNotFoundException", true);
        } catch (IllegalAccessException unused2) {
            ksy.c(TAG, "IllegalAccessException", true);
        } catch (InstantiationException unused3) {
            ksy.c(TAG, "InstantiationException", true);
        } catch (NoSuchMethodException unused4) {
            ksy.c(TAG, "NoSuchMethodException", true);
        } catch (InvocationTargetException unused5) {
            ksy.c(TAG, "InvocationTargetException", true);
        } catch (Throwable th) {
            ksy.c(TAG, "setDisplaySideMode: " + th.getClass().getSimpleName(), true);
        }
    }

    public static boolean isPhoneStillInLockMode(Context context) {
        if (context == null) {
            ksy.c(TAG, "context is null", true);
            return false;
        }
        Boolean bool = isPhoneStillInLockMode;
        if (bool != null && !bool.booleanValue()) {
            ksy.b(TAG, "isPhoneStillInLockMode is already false", true);
            return false;
        }
        UserManager userManager = (UserManager) context.getApplicationContext().getSystemService(UserManager.class);
        if (userManager == null) {
            ksy.c(TAG, "userManager is null.", true);
            return false;
        }
        if (!userManager.isUserUnlocked()) {
            ksy.c(TAG, "isPhoneStillInLockMode true", true);
            isPhoneStillInLockMode = true;
        } else {
            isPhoneStillInLockMode = false;
            ksy.b(TAG, "isPhoneStillInLockMode false", true);
        }
        ksy.c(TAG, "Build.VERSION.SDK_INT : " + Build.VERSION.SDK_INT + " isPhoneStillInLockMode " + isPhoneStillInLockMode, true);
        Boolean bool2 = isPhoneStillInLockMode;
        if (bool2 == null) {
            return false;
        }
        return bool2.booleanValue();
    }

    public static boolean isPcSimulator(String str) {
        ksy.b(TAG, "isPcSimulator start.", true);
        try {
            return "true".equalsIgnoreCase(new JSONObject(str).optString("isPcSimulator", ""));
        } catch (JSONException unused) {
            ksy.c(TAG, "isPcSimulator: JsonException", true);
            return false;
        } catch (Exception unused2) {
            ksy.c(TAG, "isPcSimulator: Exception", true);
            return false;
        }
    }

    public static void setFullScreenAdaptCutout(Window window) {
        if (window == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            ksy.b(TAG, "android version is Higher than 9.0", true);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
            window.getDecorView().setSystemUiVisibility(1280);
            return;
        }
        ksy.b(TAG, "android version is lower than 9.0", true);
        WindowManager.LayoutParams attributes2 = window.getAttributes();
        try {
            Class<?> cls = Class.forName(HUAWEI_ANDROID_VIEW_LAYOUTPARAMSEX);
            cls.getMethod(HWFLAGS, Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes2), 4096);
        } catch (RuntimeException e) {
            ksy.c(TAG, "RuntimeException occured" + e.getClass().getSimpleName(), true);
        } catch (Exception e2) {
            ksy.c(TAG, "exception occured" + e2.getClass().getSimpleName(), true);
        }
    }

    public static void setStatusBarColor(Activity activity) {
        try {
            int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility | 1024);
            activity.getWindow().setStatusBarColor(0);
            activity.getWindow().setNavigationBarColor(0);
            if (Build.VERSION.SDK_INT >= 29) {
                activity.getWindow().setNavigationBarContrastEnforced(false);
            }
        } catch (Exception e) {
            ksy.c(TAG, "exception occured:" + e.getClass().getSimpleName(), true);
        }
    }
}
