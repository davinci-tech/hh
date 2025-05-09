package com.huawei.up.api;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class UpApi {
    private static final String HWID_URI = "hwid://com.huawei.hwid/AccountCenter";
    public static final int LENGTH_EIGHIT = 8;
    public static final int LENGTH_FIVE = 5;
    public static final int LENGTH_FOUR = 4;
    public static final int LENGTH_SIX = 6;
    public static final int LENGTH_TEN = 10;
    public static final int LENGTH_TWENTY = 20;
    public static final int LENGTH_TWO = 2;
    public static final String TAG = "PLGLOGIN_UpApi";
    private Context mContext;

    public UpApi(Context context) {
        this.mContext = context;
    }

    public int jumpToHwIdAccountCenter(Fragment fragment, int i) {
        int i2 = -1;
        if (fragment == null) {
            return -1;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(HWID_URI));
        try {
            intent.setPackage(HMSPackageManager.getInstance(BaseApplication.getContext()).getHMSPackageName());
            if (HuaweiLoginManager.checkIsInstallHuaweiAccount(BaseApplication.getContext())) {
                fragment.startActivityForResult(intent, i);
                i2 = 0;
            } else {
                ReleaseLogUtil.e(TAG, "jumpToHwIdAccountCenter not install hmscore");
            }
        } catch (ActivityNotFoundException | SecurityException e) {
            LogUtil.b(TAG, "jumpToHwIdAccountCenter ", e.getClass().getName());
        }
        return i2;
    }

    public String getLegalAccountName() {
        return getAccountName(20, true);
    }

    public String getAccountName() {
        return getAccountName(0, false);
    }

    public String getCurrentUserAccount() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1001);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h(TAG, "getCurrentUserAccount accountInfo is null");
            return "";
        }
        int length = accountInfo.length();
        if (length <= 20) {
            return handleUserAccount(accountInfo);
        }
        return handleUserAccount(accountInfo.substring(0, 10) + accountInfo.substring(length - 10, length));
    }

    public String handleAccount(String str) {
        return handleUserAccount(str);
    }

    private String getAccountName(int i, boolean z) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1002);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h(TAG, "getAccountName = null");
            return accountInfo;
        }
        int length = accountInfo.length();
        if (z && length > i && i == 20) {
            accountInfo = accountInfo.substring(0, 10) + accountInfo.substring(length - 10, length);
        }
        LogUtil.c(TAG, "getAccountName: ", accountInfo, " newLength = ", Integer.valueOf(accountInfo.length()));
        return handleUserAccount(accountInfo);
    }

    private String handleUserAccount(String str) {
        LogUtil.c(TAG, "handleUserAccount: ", str);
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (isValidEmail(str)) {
            return getIsValidEmail(str);
        }
        if (isValidAllPhoneNumber(str)) {
            return getIsValidAllPhoneNumber(str);
        }
        return getOtherPhoneNumber(str);
    }

    private String getIsValidEmail(String str) {
        String[] split = str.split("@");
        if (split.length != 2 || split[0].length() <= 0 || split[1].length() <= 0) {
            return str;
        }
        String str2 = split[0];
        String str3 = split[1];
        if (str2.length() > 6 && isValid(str2, "[0-9]+")) {
            if (str2.length() > 8) {
                return str2.substring(0, str2.length() - 8) + "****" + str2.substring(str2.length() - 4) + "@" + str3;
            }
            return generateString("*", str2.length() - 4) + str2.substring(str2.length() - 4) + "@" + str3;
        }
        if (str2.length() > 8) {
            return str2.substring(0, str2.length() - 4) + "****@" + str3;
        }
        if (str2.length() > 2) {
            return str2.substring(0, str2.length() - 2) + "**@" + str3;
        }
        return generateString("*", str2.length()) + "@" + str3;
    }

    private String getIsValidAllPhoneNumber(String str) {
        if (str.length() < 5) {
            return str;
        }
        if (str.length() < 8) {
            return generateString("*", str.length() - 4) + str.substring(str.length() - 4);
        }
        return str.substring(0, str.length() - 8) + "****" + str.substring(str.length() - 4);
    }

    private String getOtherPhoneNumber(String str) {
        if (str.length() < 5) {
            return str;
        }
        if (str.length() < 8) {
            return generateString("*", str.length() - 4) + str.substring(str.length() - 4);
        }
        return str.substring(0, str.length() - 8) + "****" + str.substring(str.length() - 4);
    }

    private String generateString(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    private boolean isValidEmail(String str) {
        if (str.endsWith("@inner.up.huawei")) {
            return false;
        }
        return isValid(str, "^\\s*([A-Za-z0-9_-]+(\\.\\w+)*@(\\w+\\.)+\\w+)\\s*$");
    }

    private boolean isValid(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return Pattern.compile(str2).matcher(str).matches();
    }

    private boolean isValidAllPhoneNumber(String str) {
        return Pattern.compile("^1[0-9]{10}$").matcher(str).matches();
    }
}
