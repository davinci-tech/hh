package com.huawei.ui.main.stories.guide.interactors;

import android.content.Context;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes6.dex */
public class GuideInteractors extends HwBaseManager {
    private Context e;

    public GuideInteractors(Context context) {
        super(BaseApplication.getContext());
        this.e = BaseApplication.getContext();
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 10005;
    }

    public boolean h() {
        LogUtil.a("GuideInteractors", "isShowSuccessFlagInSharePreference enter");
        return "true".equals(getSharedPreference("KEY_GUIDE_SET_SHOW_HIHEALTH_DOWNLOAD_ACTIVITY_FLAG"));
    }

    public void c(String str) {
        LogUtil.a("GuideInteractors", "setOutsideOpenActivityFlagInSharePreference enter:", str);
        setSharedPreference("KEY_OUTSIDE_OPEN_ACTIVITY_FLAG", str, null);
    }

    public String d() {
        String sharedPreference = getSharedPreference("KEY_OUTSIDE_OPEN_ACTIVITY_FLAG");
        LogUtil.a("GuideInteractors", "getOutsideOpenActivityFlagInSharePreference enter:", sharedPreference);
        return sharedPreference;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        CommonUtil.a(this.e);
    }

    public void b(boolean z) {
        LogUtil.a("GuideInteractors", "setPairedEarSuccessFlagInSharePreference", Boolean.valueOf(z));
        setSharedPreference("key_ear_pair_success_from_pair_activity", String.valueOf(z), null);
    }

    public boolean a() {
        LogUtil.a("GuideInteractors", "isPairedEarFlagInSharePreference");
        return "true".equals(getSharedPreference("key_ear_pair_success_from_pair_activity"));
    }

    public void d(boolean z) {
        LogUtil.a("GuideInteractors", "setDeviceConnectFlagInSharePreference enter isBind is ", Boolean.valueOf(z));
        setSharedPreference("key_device_connect_success", String.valueOf(z), null);
    }

    public boolean e() {
        LogUtil.a("GuideInteractors", "isConnectFlagInSharePreference enter");
        return "true".equals(getSharedPreference("key_device_connect_success"));
    }

    public void e(boolean z) {
        LogUtil.a("GuideInteractors", "setAndroidWearOpen eSim FlagInSharePreference enter", Boolean.valueOf(z));
        setSharedPreference("key_pair_success_from_pair_activity", String.valueOf(z), null);
    }

    public boolean c() {
        LogUtil.a("GuideInteractors", "getAndroidWearOpen eSim FlagInSharePreference enter");
        return "true".equals(getSharedPreference("key_pair_success_from_pair_activity"));
    }

    public void a(boolean z) {
        LogUtil.a("GuideInteractors", "setPairedGuideFlagInSharePreference", Boolean.valueOf(z));
        setSharedPreference("key_guide_pair_success_from_pair_activity", String.valueOf(z), null);
    }

    public boolean j() {
        LogUtil.a("GuideInteractors", "isPairedGuideFlagInSharePreference");
        return "true".equals(getSharedPreference("key_guide_pair_success_from_pair_activity"));
    }

    public boolean e(String str) {
        LogUtil.a("GuideInteractors", "isShowUserGuideFlagInSharePreference");
        return "true".equals(getSharedPreference(str));
    }

    public void b(String str) {
        LogUtil.a("GuideInteractors", "setShowUserGuideFlagInSharePreference");
        setSharedPreference("keyUserGuidePage" + str, "true", null);
    }

    public void d(String str, boolean z) {
        LogUtil.a("GuideInteractors", "setsetShowSyncAccountSharePreference enter", Boolean.valueOf(z));
        setSharedPreference("KEY_SHOW_SYNC_ACCOUNT_ON_HOME_ACTIVITY_FLAG".concat(str), String.valueOf(z), null);
    }

    public boolean d(String str) {
        LogUtil.a("GuideInteractors", "isShowSyncAccountFlagInSharePreference enter");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10008), "currentIsNoCloud");
        LogUtil.a("GuideInteractors", "isNoCloudToWithCloud:", b);
        if ("1".equals(b)) {
            LogUtil.a("GuideInteractors", "this is noCloud with cloud scenes");
            SharedPreferenceManager.e(this.e, Integer.toString(10008), "currentIsNoCloud", "0", (StorageParams) null);
            return true;
        }
        HiUserPreference userPreference = HiHealthManager.d(this.e).getUserPreference("sync_account_dialog_mark");
        if (userPreference != null) {
            String value = userPreference.getValue();
            LogUtil.a("GuideInteractors", "getValueFromDevice:", value);
            if ("1".equals(value)) {
                LogUtil.a("GuideInteractors", "getValueFromDevice scenes");
                HiHealthManager.d(this.e).setUserPreference(new HiUserPreference("sync_account_dialog_mark", "0"), false);
                return true;
            }
        } else {
            LogUtil.a("GuideInteractors", "userPreference is null");
        }
        return "true".equals(getSharedPreference("KEY_SHOW_SYNC_ACCOUNT_ON_HOME_ACTIVITY_FLAG".concat(str)));
    }

    public void c(boolean z) {
        LogUtil.a("GuideInteractors", "setAndroidWearOpen eSim Flag InSharePreference enter", Boolean.valueOf(z));
        setSharedPreference("key_device_auto_checked_tips", String.valueOf(z), null);
    }

    public boolean b() {
        LogUtil.a("GuideInteractors", "getAndroidWearOpen eSim Flag InSharePreference enter");
        return "true".equals(getSharedPreference("key_device_auto_checked_tips"));
    }
}
