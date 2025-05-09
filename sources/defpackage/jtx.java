package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class jtx {
    public static void c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwGetAccountIdUtil", "sendAccount deviceInfo is null");
            return;
        }
        DeviceCapability d = jst.d(deviceInfo.getDeviceIdentify());
        if (d == null || !d.isSupportAccount()) {
            return;
        }
        ReleaseLogUtil.e("R_HwGetAccountIdUtil", "support sendAccount");
        HiHealthManager.d(BaseApplication.getContext()).fetchAccountInfo(new c());
    }

    static class c implements HiCommonListener {
        c() {
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            String huid = obj instanceof HiAccountInfo ? ((HiAccountInfo) obj).getHuid() : "";
            if (huid == null || "".equals(huid) || "com.huawei.health".equals(huid)) {
                LogUtil.h("HwGetAccountIdUtil", "AccountLoginReceiver userId is empty");
                return;
            }
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(26);
            deviceCommand.setCommandID(1);
            String c = cvx.c(huid);
            String e = cvx.e(c.length() / 2);
            String e2 = cvx.e(1);
            ByteBuffer allocate = ByteBuffer.allocate((e2.length() / 2) + (e.length() / 2) + (c.length() / 2));
            allocate.put(cvx.a(e2));
            allocate.put(cvx.a(e));
            allocate.put(cvx.a(c));
            deviceCommand.setDataLen(allocate.array().length);
            deviceCommand.setDataContent(allocate.array());
            LogUtil.a("HwGetAccountIdUtil", "sendDeviceData(): Command:", cvx.e(deviceCommand.getServiceID()), cvx.e(deviceCommand.getCommandID()), cvx.d(deviceCommand.getDataContent()));
            jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.h("HwGetAccountIdUtil", "get account: onFailure");
        }
    }
}
