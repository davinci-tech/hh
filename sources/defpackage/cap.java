package defpackage;

import android.bluetooth.BluetoothAdapter;
import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.model.OperatorStatus;
import com.huawei.health.healthlinkage.api.HWhealthLinkageApi;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public final class cap {
    public static boolean a() {
        boolean isEnabled = BluetoothAdapter.getDefaultAdapter() != null ? BluetoothAdapter.getDefaultAdapter().isEnabled() : false;
        boolean a2 = ggx.a(BaseApplication.e(), isEnabled);
        boolean c = ggx.c(BaseApplication.e());
        VersionMgrApi versionMgrApi = (VersionMgrApi) Services.a("HWVersionMgr", VersionMgrApi.class);
        boolean bandOtaStatus = versionMgrApi != null ? versionMgrApi.getBandOtaStatus(BaseApplication.e().getApplicationContext()) : false;
        ReleaseLogUtil.e("HWhealthLinkage_SportDeviceUtils", "isBlueToothEnable:", Boolean.valueOf(isEnabled), " isConnectHeartRateDevice:", Boolean.valueOf(a2), " isIndependentSportDevice:", Boolean.valueOf(c), " isUpdating:", Boolean.valueOf(bandOtaStatus));
        return a2 && c && !bandOtaStatus;
    }

    public static void b(final IBaseResponseCallback iBaseResponseCallback) {
        HWhealthLinkageApi hWhealthLinkageApi = (HWhealthLinkageApi) Services.a("HWhealthLinkage", HWhealthLinkageApi.class);
        if (iBaseResponseCallback == null || hWhealthLinkageApi == null || hWhealthLinkageApi.isMediatorExist()) {
            ReleaseLogUtil.e("HWhealthLinkage_SportDeviceUtils", "getDeviceBusyStatus with error params.");
        } else {
            mwu.d().a(new IBaseResponseCallback() { // from class: can
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    cap.a(IBaseResponseCallback.this, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void a(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        if (i == 100000 && obj != null) {
            OperatorStatus operatorStatus = (OperatorStatus) new Gson().fromJson(CommonUtil.z((String) obj), OperatorStatus.class);
            iBaseResponseCallback.d(100000, Integer.valueOf(operatorStatus.getTrainMonitorState()));
            ReleaseLogUtil.e("HWhealthLinkage_SportDeviceUtils", "getDeviceBusyStatus :", Integer.valueOf(operatorStatus.getTrainMonitorState()));
        } else {
            iBaseResponseCallback.d(i, -1);
            ReleaseLogUtil.e("HWhealthLinkage_SportDeviceUtils", "getDeviceBusyStatus failed with errorCode:", Integer.valueOf(i));
        }
    }
}
