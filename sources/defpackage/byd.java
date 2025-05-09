package defpackage;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class byd {
    public static boolean e(Context context) {
        int i;
        if (context == null) {
            LogUtil.a("MainActivityHelper", "get gesture navigation state failed, context is null");
            return false;
        }
        try {
            i = Settings.Secure.getInt(context.getContentResolver(), "secure_gesture_navigation");
        } catch (Settings.SettingNotFoundException e) {
            LogUtil.a("MainActivityHelper", "isGestureNavigationOpen SettingNotFoundException : ", e.getMessage());
            i = -1;
        }
        LogUtil.a("MainActivityHelper", "isGestureNavigationOpen getSystemSetting state : ", Integer.valueOf(i));
        return i == 1;
    }

    public static void d(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    public static void b(final Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: byd.2
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("MainActivityHelper", "Enter initWeightUserData callback onSuccess");
                LogUtil.a("PluginDevice_PluginDevice", "getHealthAPI HiCommonListener onSuccess pid = ", Integer.valueOf(Process.myPid()), ",tid = ", Integer.valueOf(Process.myTid()));
                if (obj != null) {
                    List list = (List) obj;
                    if (koq.b(list)) {
                        LogUtil.h("MainActivityHelper", "initWeightUserData onSuccess userInfos is null or size is zero");
                        return;
                    }
                    HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                    if (hiUserInfo == null) {
                        LogUtil.h("MainActivityHelper", "initWeightUserData onSuccess HiUserInfo is null");
                        return;
                    }
                    int gender = hiUserInfo.getGender();
                    if (gender != 2 && gender != 0 && gender != 1) {
                        gender = -100;
                    }
                    cfi cfiVar = new cfi();
                    cfiVar.a(hiUserInfo.getAge());
                    cfiVar.a((byte) gender);
                    cfiVar.d(hiUserInfo.getHeight());
                    cfiVar.b(hiUserInfo.getWeight());
                    cfiVar.b(hiUserInfo.getBirthday());
                    cfiVar.d(LoginInit.getInstance(context).getAccountInfo(1011));
                    MultiUsersManager.INSTANCE.initMultiUsers(cfiVar);
                    WeightDataManager.INSTANCE.readAllWeightDataOfUser();
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b("MainActivityHelper", "initWeightUserData falied to retrieve user info, all weight data won't be displayed!");
            }
        });
        LogUtil.a("MainActivityHelper", "initWeightUserData finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }
}
