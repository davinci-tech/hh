package com.huawei.health.impl;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.DeviceManagerApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.cej;
import defpackage.ceo;
import defpackage.cfe;
import defpackage.cfi;
import defpackage.cgt;
import defpackage.cjx;
import defpackage.coy;
import defpackage.cpa;
import defpackage.cpd;
import defpackage.cpl;
import defpackage.csb;
import defpackage.csf;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.dcq;
import defpackage.dds;
import defpackage.dhy;
import defpackage.dks;
import defpackage.koq;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ApiDefine(uri = DeviceManagerApi.class)
/* loaded from: classes3.dex */
public class DeviceManagerImpl implements DeviceManagerApi {
    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void destroyResourceDownloadTool() {
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void init() {
        cej.e().init(BaseApplication.getContext());
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void startMeasureBackground(Context context, int i, HealthDevice.HealthDeviceKind healthDeviceKind, MeasureResult.MeasureResultListener measureResultListener) {
        cej.e().c(healthDeviceKind, measureResultListener);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void stopMeasureBackground(String str, String str2) {
        cej.e().e(str, str2);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<String> getBondedProductsForDeviceOnly(HealthDevice.HealthDeviceKind healthDeviceKind) {
        return cjx.e().b(healthDeviceKind);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public com.huawei.hihealth.device.open.HealthDevice getBondedDeviceUniversal(String str, String str2) {
        return cjx.e().c(str, str2);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void syncDevice() {
        csb.a().d();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isRopeDeviceBtConnected() {
        return dds.c().f();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setSkippingTargetMode(SkippingTargetMode skippingTargetMode) {
        dds.c().c(skippingTargetMode);
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void setFitnessMachineControl(int i, int[] iArr) {
        dds.c().d(i, iArr);
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void setFitnessMachineControl(int i, int i2, int[] iArr) {
        dds.c().c(i, i2, iArr);
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void setMessageOrStateCallback(String str, MessageOrStateCallback messageOrStateCallback, boolean z) {
        dds.c().e(str, messageOrStateCallback, z);
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void removeMessageOrStateCallback(String str, boolean z) {
        dds.c().c(str, z);
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void releaseResource() {
        dds.c().h();
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public String getCurrentMacAddress() {
        return dds.c().d();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void requestDeviceInfoAndState(String str) {
        dds.c().e(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getProductId() {
        return dds.c().j();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setUserWeight(int i) {
        MultiUsersManager.INSTANCE.getMainUser().d(i);
        setHeightIsNotSet(false);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getUserName(String str) {
        for (cfi cfiVar : MultiUsersManager.INSTANCE.getAllUser()) {
            if (cfiVar == null || cfiVar.i() == null) {
                LogUtil.h("DeviceManagerImpl", "user or getUUIDOfUser() is null");
            } else if (cfiVar.i().equalsIgnoreCase(str)) {
                return cfiVar.h();
            }
        }
        return "";
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getMainUserUuid() {
        return MultiUsersManager.INSTANCE.getMainUser().i();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void initUserIfEmpty() {
        MultiUsersManager.INSTANCE.initUserIfEmpty();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void startSync() {
        ClaimWeightDataManager.INSTANCE.startSync();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void sendEventToKaKa(int i) {
        csf.a(i);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void confirmDevUserInfo() {
        csf.b();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<Long> getDeviceCodeFromWiFiDevice() {
        ArrayList<ctk> e = ctq.e();
        ArrayList<Long> arrayList = new ArrayList<>();
        if (!koq.b(e)) {
            Iterator<ctk> it = e.iterator();
            while (it.hasNext()) {
                arrayList.add(Long.valueOf(it.next().a()));
            }
            return arrayList;
        }
        return new ArrayList<>();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public MeasurableDevice getBondedDeviceByBroadcastName(String str, String str2) {
        return ceo.d().e(str, str2);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ContentValues getDeviceInfoByUniqueId(String str) {
        return ceo.d().Ed_(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<ContentValues> getBondedDeviceByProductId(String str) {
        return ceo.d().a(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<MeasurableDevice> getBondedDevices(String str, boolean z) {
        return ceo.d().a(str, z);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isMiniScaleDevice(String str) {
        return cpa.ah(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean getResetWifi() {
        return cgt.e().m();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void deliveringUserInformation(String str, String str2, HiUserInfo hiUserInfo) {
        cpl.a(str, str2, hiUserInfo);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void uploadDeviceToCloud(String str, String str2) {
        coy.b(str, str2);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setUserGender(int i, HiUserInfo hiUserInfo) {
        cpl.e(i, hiUserInfo);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<String> getBondedWiFiDevices() {
        return cjx.e().c();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isBondHuaweiBleScaleDevice() {
        return cpa.h();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean showConfirmUserInfo(String str, boolean z) {
        return cpl.a(str, z);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void sendUserInfo(String str) {
        csf.c(str, csf.g(str));
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isHonourWeightDevice(String str) {
        return cpa.ab(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void sendUserInfo(Bundle bundle) {
        EventBus.d(new EventBus.b("weight_measure_set_user", bundle));
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isHagridWiFiDevice(String str, String str2) {
        return cpa.b(str, str2);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setBindStatus(int i) {
        HagridDeviceBindResultFragment.setBindStatus(8);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isHonourScaleDevice(String str) {
        return cpa.aa(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getH5Path(String str) {
        return dcq.b().c(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getSubProductId(String str) {
        return dks.b(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void downloadBloodSugarDeviceResource(Activity activity) {
        if (activity == null) {
            return;
        }
        new dhy(activity, null, null, "167P").d("HDK_BLOOD_SUGAR", true);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setBeginMeasureBiAnalytics(String str) {
        dks.n(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setUserGoalWeight(float f) {
        MultiUsersManager.INSTANCE.getMainUser().d(f);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public float getLastestWeight() {
        float f;
        UserInfomation userInfo;
        List<cfe> singleUserWeightData = WeightDataManager.INSTANCE.getSingleUserWeightData(MultiUsersManager.INSTANCE.getMainUser().i(), true);
        if (koq.c(singleUserWeightData)) {
            for (cfe cfeVar : singleUserWeightData) {
                if (cfeVar.au() <= System.currentTimeMillis()) {
                    f = (float) cfeVar.ax();
                    break;
                }
            }
        } else {
            LogUtil.h("DeviceManagerImpl", "getLastestWeight weightList is Empty");
        }
        f = 0.0f;
        return (f > 0.0f || (userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo()) == null) ? f : userInfo.getWeight();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean getHeightIsNotSet() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("height_is_not_set");
        if (userPreference != null) {
            return "true".equals(userPreference.getValue());
        }
        health.compact.a.util.LogUtil.c("DeviceManagerImpl", "getHeightIsNotSet userPreference is null");
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("sleep_shared_pref_smart_msg", 0);
        if (sharedPreferences == null) {
            health.compact.a.util.LogUtil.c("DeviceManagerImpl", "getHeightIsNotSet mSharedPreferences is null");
            return false;
        }
        boolean z = sharedPreferences.getBoolean("height_is_not_set", true);
        setHeightIsNotSet(z);
        return z;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setHeightIsNotSet(final boolean z) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().d("setHeightIsNotSet", new Runnable() { // from class: com.huawei.health.impl.DeviceManagerImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    DeviceManagerImpl.this.setHeightIsNotSet(z);
                }
            });
            return;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("height_is_not_set");
        hiUserPreference.setValue(String.valueOf(z));
        HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void scaleUpdateJump(Context context, Uri uri) {
        cpd.Kb_(context, uri);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public MeasurableDevice getBondedDeviceByUniqueId(String str) {
        return ceo.d().d(str, false);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public MeasurableDevice getBondedDeviceByUniqueId(String str, boolean z) {
        return ceo.d().d(str, z);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isBondedDevice(String str) {
        return ceo.d().h(str);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public int updateBondedDevice(ContentValues contentValues, String str, String[] strArr) {
        return ceo.d().Eg_(contentValues, str, strArr);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setFitnessRopeConfig(int i, int i2, int[] iArr) {
        dds.c().d(i, i2, iArr);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void registerDeviceInfo(String str, HealthDevice healthDevice, String str2) {
        ceo.d().d(str, healthDevice, str2);
    }
}
