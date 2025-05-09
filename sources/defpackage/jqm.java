package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
class jqm {
    jqm() {
    }

    public static void e(String str, String str2, int i) {
        ReleaseLogUtil.e("DEVMGR_SwitchLocalStorageUtil", "setSwitchSettingToLocal switchKey = ", str, ",switchSetting = ", str2);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SwitchLocalStorageUtil", "setSwitchSettingToLocal switchKey is null");
        } else {
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(i), str, str2, new StorageParams(0));
        }
    }

    public static String a(String str, int i) {
        ReleaseLogUtil.e("DEVMGR_SwitchLocalStorageUtil", "getSwitchSettingFromLocal switchKey = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SwitchLocalStorageUtil", "getSwitchSettingFromLocal switchKey is null");
            return "";
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(i), str);
        ReleaseLogUtil.e("DEVMGR_SwitchLocalStorageUtil", "getSwitchSettingFromLocal switchSetting = ", b);
        return b;
    }

    public static void c(String str, String str2) {
        ReleaseLogUtil.e("DEVMGR_SwitchLocalStorageUtil", "setSwitchSettingToDb switchKey = ", str, ",switchSetting = ", CommonUtil.l(str2));
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SwitchLocalStorageUtil", "setSwitchSettingToDb switchKey is null");
        } else {
            KeyValDbManager.b(BaseApplication.getContext()).e(str, str2);
        }
    }

    public static String e(String str) {
        ReleaseLogUtil.e("DEVMGR_SwitchLocalStorageUtil", "getSwitchSettingFromDb switchKey = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SwitchLocalStorageUtil", "getSwitchSettingFromDb switchKey is null");
            return "";
        }
        String e = KeyValDbManager.b(BaseApplication.getContext()).e(str);
        ReleaseLogUtil.e("DEVMGR_SwitchLocalStorageUtil", "getSwitchSettingFromDb switchSetting = ", CommonUtil.l(e));
        return e;
    }
}
