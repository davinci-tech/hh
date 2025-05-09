package defpackage;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.devicepair.api.PermissionsApi;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homewear21.home.devicepairjs.PairPermissionType;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@ApiDefine(uri = PermissionsApi.class)
@Singleton
/* loaded from: classes6.dex */
public class pej implements PermissionsApi {
    @Override // com.huawei.devicepair.api.PermissionsApi
    public Map<String, Boolean> requestPermissionsForPair(String str) {
        DeviceInfo b;
        HashMap hashMap = new HashMap(16);
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_PermissionsApiImpl", "deviceMac is empty");
            b = oxa.a().f();
        } else {
            b = oxa.a().b(str);
        }
        if (b == null) {
            ReleaseLogUtil.d("DEVMGR_PermissionsApiImpl", "deviceInfo is null");
            return hashMap;
        }
        DeviceCapability e = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(str);
        d(e, hashMap);
        if (pep.e(b) || pep.f(b)) {
            ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission sms");
            hashMap.put(PairPermissionType.SMS.key(), Boolean.valueOf(PermissionUtil.e(BaseApplication.getContext(), PairPermissionType.SMS.value())));
        } else if (e != null && e.isSupportSendSosSms()) {
            ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission sms");
            hashMap.put(PairPermissionType.SMS.key(), Boolean.valueOf(PermissionUtil.e(BaseApplication.getContext(), new String[]{"android.permission.SEND_SMS"})));
        }
        if (cwi.c(b, 171)) {
            ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission calendar");
            hashMap.put(PairPermissionType.CALENDAR.key(), Boolean.valueOf(PermissionUtil.e(BaseApplication.getContext(), PairPermissionType.CALENDAR.value())));
        }
        LogUtil.a("PermissionsApiImpl", "requestPermissionsForPair permissionMap:", hashMap.toString());
        return hashMap;
    }

    private void d(DeviceCapability deviceCapability, Map<String, Boolean> map) {
        if (deviceCapability == null) {
            ReleaseLogUtil.d("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission deviceCapability is null");
            return;
        }
        if (deviceCapability.isMessageAlert() && oae.c(BaseApplication.getContext()).g()) {
            ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission outgoing");
            map.put(PairPermissionType.READ_CALL_LOG.key(), Boolean.valueOf(PermissionUtil.e(BaseApplication.getContext(), new String[]{"android.permission.PROCESS_OUTGOING_CALLS"})));
        }
        if (pep.f() && deviceCapability.isSupportCallingOperationType()) {
            ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission answer and phone");
            map.put(PairPermissionType.CALL_PHONE.key(), Boolean.valueOf(PermissionUtil.e(BaseApplication.getContext(), PairPermissionType.CALL_PHONE.value())));
        }
        if (deviceCapability.isMessageAlert() && pep.f()) {
            ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission call log");
            map.put(PairPermissionType.READ_CALL_LOG.key(), Boolean.valueOf(PermissionUtil.e(BaseApplication.getContext(), new String[]{"android.permission.READ_CALL_LOG"})));
        }
        if (deviceCapability.isMessageAlert() || deviceCapability.isContacts() || deviceCapability.isSupportSosTransmission()) {
            ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission contacts");
            map.put(PairPermissionType.READ_CONTACTS.key(), Boolean.valueOf(PermissionUtil.e(BaseApplication.getContext(), PairPermissionType.READ_CONTACTS.value())));
        }
        if (deviceCapability.isMessageAlert() || deviceCapability.isSupportCallingOperationType() || deviceCapability.isSupportEsim()) {
            ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission phone state");
            map.put(PairPermissionType.READ_PHONE_STATE.key(), Boolean.valueOf(PermissionUtil.e(BaseApplication.getContext(), PairPermissionType.READ_PHONE_STATE.value())));
        }
        if (deviceCapability.isWeatherPush()) {
            ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "addDeviceCapabilityPermission location");
            map.put(PairPermissionType.LOCATION.key(), Boolean.valueOf(PermissionUtil.e(BaseApplication.getContext(), PairPermissionType.LOCATION.value())));
        }
    }

    @Override // com.huawei.devicepair.api.PermissionsApi
    public void requestPermissionsGrantStatus(List<String> list, final String str, final Context context, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_PermissionsApiImpl", "requestPermissionsGrantStatus callback is null");
            return;
        }
        if (!(context instanceof Activity)) {
            iBaseResponseCallback.d(-1, "context is error");
            ReleaseLogUtil.d("DEVMGR_PermissionsApiImpl", "requestPermissionsGrantStatus context is error");
            return;
        }
        if (koq.b(list)) {
            iBaseResponseCallback.d(-1, "param is error");
            ReleaseLogUtil.d("DEVMGR_PermissionsApiImpl", "requestPermissionsGrantStatus param is error");
            return;
        }
        final HashMap hashMap = new HashMap(16);
        for (String str2 : list) {
            PairPermissionType[] values = PairPermissionType.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    PairPermissionType pairPermissionType = values[i];
                    if (pairPermissionType.key().equals(str2)) {
                        hashMap.put(str2, pairPermissionType.value());
                        break;
                    }
                    i++;
                }
            }
        }
        if (hashMap.isEmpty()) {
            iBaseResponseCallback.d(-1, "requestPermissionMap is error");
            ReleaseLogUtil.d("DEVMGR_PermissionsApiImpl", "requestPermissionsGrantStatus requestPermissionMap is error");
        } else {
            ThreadPoolManager.d().d("PermissionsApiImpl", new Runnable() { // from class: pej.3
                @Override // java.lang.Runnable
                public void run() {
                    pej.this.c(iBaseResponseCallback, context, hashMap, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IBaseResponseCallback iBaseResponseCallback, Context context, Map<String, String[]> map, String str) {
        final HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            final String key = entry.getKey();
            String[] value = entry.getValue();
            if (PermissionUtil.PermissionResult.FOREVER_DENIED == PermissionUtil.b(context, value)) {
                LogUtil.a("PermissionsApiImpl", "requestPermission forever onDenied:", key);
                hashMap.put(key, -2);
            } else {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                PermissionUtil.bFX_((Activity) context, value, new CustomPermissionAction(BaseApplication.getContext()) { // from class: pej.1
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        LogUtil.a("PermissionsApiImpl", "requestPermission onGranted:", key);
                        hashMap.put(key, 0);
                        countDownLatch.countDown();
                    }

                    @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onDenied(String str2) {
                        LogUtil.a("PermissionsApiImpl", "requestPermission onDenied:", key);
                        hashMap.put(key, -1);
                        countDownLatch.countDown();
                    }

                    @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                        LogUtil.a("PermissionsApiImpl", "requestPermission onForeverDenied:", key);
                        hashMap.put(key, -2);
                        countDownLatch.countDown();
                    }
                });
                try {
                    countDownLatch.await();
                } catch (InterruptedException unused) {
                    ReleaseLogUtil.c("DEVMGR_PermissionsApiImpl", "requestPermission InterruptedException");
                }
            }
        }
        ReleaseLogUtil.e("DEVMGR_PermissionsApiImpl", "requestPermission requestResultMap:", Integer.valueOf(hashMap.size()));
        iBaseResponseCallback.d(0, hashMap);
    }

    @Override // com.huawei.devicepair.api.PermissionsApi
    public void permissionGrantRegisterContactObserver(String str) {
        DeviceCapability e = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(str);
        if (e == null || !e.isSupportSendSosSms()) {
            return;
        }
        LogUtil.a("PermissionsApiImpl", "permissionGrantRegisterContactObserver");
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).registerContactChangeObserver();
    }
}
