package defpackage;

import android.content.Context;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class jld extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static jld c;
    private static final Object d = new Object();
    private jlg e;

    private jld(Context context) {
        super(context);
        this.e = jlg.d();
        jfq c2 = jfq.c();
        if (c2 == null) {
            LogUtil.h("HwPressAutoMonitorManager", "hwDeviceConfigManager is null");
        } else {
            c2.e(100032, this);
        }
    }

    public static jld b() {
        jld jldVar;
        synchronized (d) {
            if (c == null) {
                c = new jld(BaseApplication.getContext());
            }
            jldVar = c;
        }
        return jldVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 32;
    }

    public void a(int i) {
        this.e.d(i);
    }

    public void d(HiStressMetaData hiStressMetaData) {
        this.e.d(hiStressMetaData);
    }

    public void d(boolean z) {
        ReleaseLogUtil.e("DEVMGR_HwPressAutoMonitorManager", "setPressAutoMonitorSwitchStatus, isEnable :", Boolean.valueOf(z));
        jqi a2 = jqi.a();
        if (a2 == null) {
            ReleaseLogUtil.d("DEVMGR_HwPressAutoMonitorManager", "switchSettingManager is null");
        } else {
            a2.setSwitchSetting("press_auto_monitor_switch_status", String.valueOf(z), new IBaseResponseCallback() { // from class: jle
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.a("HwPressAutoMonitorManager", "setPressAutoMonitorSwitchStatus errorCode = ", Integer.valueOf(i));
                }
            });
        }
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        ReleaseLogUtil.e("DEVMGR_HwPressAutoMonitorManager", "onDataReceived errorCode:", Integer.valueOf(i));
        if (i == 0 && bArr != null) {
            if (bArr.length < 2) {
                ReleaseLogUtil.d("DEVMGR_HwPressAutoMonitorManager", "error tlv");
                return;
            }
            LogUtil.a("HwPressAutoMonitorManager", "onDataReceived BT data is ", cvx.d(bArr));
            ReleaseLogUtil.e("DEVMGR_HwPressAutoMonitorManager", "onDataReceived commandId:", Byte.valueOf(bArr[1]));
            byte b = bArr[1];
            if (b == 9) {
                LogUtil.a("HwPressAutoMonitorManager", "5.35.1 success.");
                return;
            } else {
                LogUtil.h("HwPressAutoMonitorManager", "onDataReceived BT data commandId is wrong : commandId : ", Byte.valueOf(b));
                return;
            }
        }
        ReleaseLogUtil.d("DEVMGR_HwPressAutoMonitorManager", "onDataReceived data error");
    }
}
