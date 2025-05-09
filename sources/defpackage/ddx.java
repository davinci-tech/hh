package defpackage;

import android.content.Context;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class ddx {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Integer, Integer> f11618a;
    private final Map<HealthDevice.HealthDeviceKind, Integer> c;
    private final Context e;

    private ddx() {
        int i = 4;
        this.c = new HashMap<HealthDevice.HealthDeviceKind, Integer>(i) { // from class: ddx.2
            {
                put(HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE, 5);
                put(HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR, 4);
                put(HealthDevice.HealthDeviceKind.HDK_WEIGHT, Integer.valueOf(HiSyncType.HiSyncDataType.c));
            }
        };
        this.f11618a = new HashMap<Integer, Integer>(i) { // from class: ddx.4
            {
                put(5, Integer.valueOf(HiSubscribeType.f4119a));
                put(4, 10);
                put(Integer.valueOf(HiSyncType.HiSyncDataType.c), Integer.valueOf(HiSubscribeType.c));
                put(20000, 200);
            }
        };
        this.e = BaseApplication.getContext();
    }

    static class a {
        private static final ddx b = new ddx();
    }

    public static ddx a() {
        return a.b;
    }

    public void a(HealthDevice.HealthDeviceKind healthDeviceKind, IBaseResponseCallback iBaseResponseCallback) {
        c(healthDeviceKind, false, iBaseResponseCallback);
    }

    public void c(HealthDevice.HealthDeviceKind healthDeviceKind, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        b(this.c.get(healthDeviceKind) == null ? 20000 : this.c.get(healthDeviceKind).intValue(), z, iBaseResponseCallback);
    }

    public void b(int i, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("DEVMGR_SyncCloudManager", "synCloud dataType：", Integer.valueOf(i));
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_SyncCloudManager", "syncCloud callback is null!");
            return;
        }
        if (!this.f11618a.containsKey(Integer.valueOf(i))) {
            ReleaseLogUtil.d("DEVMGR_SyncCloudManager", "dataType undefined!");
            iBaseResponseCallback.d(2, null);
        } else if (Utils.g()) {
            ReleaseLogUtil.d("DEVMGR_SyncCloudManager", "syncCloud isNoCloudVersion = true");
            iBaseResponseCallback.d(Integer.parseInt(BleConstants.ERRCODE_COMMON_ERR), null);
        } else if (!CommonUtil.aa(this.e)) {
            ReleaseLogUtil.d("DEVMGR_SyncCloudManager", "syncCloud network error!");
            iBaseResponseCallback.d(Integer.parseInt(BleConstants.ERRCODE_COMMON_ERR), null);
        } else {
            c(this.f11618a.get(Integer.valueOf(i)).intValue(), iBaseResponseCallback);
            b(i, z);
        }
    }

    private void b(int i, boolean z) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setForceSync(z);
        hiSyncOption.setSyncDataType(i);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(cpp.a()).synCloud(hiSyncOption, new HiCommonListener() { // from class: ddx.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i2, Object obj) {
                LogUtil.d("SyncCloudManager", "syncCloud onSuccess");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i2, Object obj) {
                LogUtil.d("SyncCloudManager", "syncCloud onFailure errCode: ", Integer.valueOf(i2));
            }
        });
    }

    private void c(final int i, final IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(8);
        arrayList.add(Integer.valueOf(i));
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(arrayList, new HiSubscribeListener() { // from class: ddx.3
            private Timer b;
            private List<Integer> e;

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                ReleaseLogUtil.e("DEVMGR_SyncCloudManager", "subscribeData, onResult");
                if (list == null) {
                    list = list2;
                }
                this.e = list;
                TimerTask timerTask = new TimerTask() { // from class: ddx.3.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        ddx.this.c(AnonymousClass3.this.e);
                        LogUtil.d("SyncCloudManager", "timeout, type = ", Integer.valueOf(i));
                        iBaseResponseCallback.d(Integer.parseInt(BleConstants.ERRCODE_TIMEOUT), Integer.valueOf(i));
                        AnonymousClass3.this.b.cancel();
                    }
                };
                Timer timer = new Timer("SyncCloudManager");
                this.b = timer;
                timer.schedule(timerTask, 12000L);
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i2, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                this.b.cancel();
                ReleaseLogUtil.e("DEVMGR_SyncCloudManager", "subscribeData onChange, type = ", Integer.valueOf(i2));
                iBaseResponseCallback.d(Integer.parseInt("0"), Integer.valueOf(i2));
                ddx.this.c(this.e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<Integer> list) {
        if (list == null) {
            return;
        }
        HiHealthNativeApi.a(this.e).unSubscribeHiHealthData(list, new HiUnSubscribeListener() { // from class: ddx.5
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public void onResult(boolean z) {
                LogUtil.d("SyncCloudManager", "unSubscribeData onResult isSuccess = ", Boolean.valueOf(z));
            }
        });
    }
}
