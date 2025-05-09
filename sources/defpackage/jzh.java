package defpackage;

import android.database.ContentObserver;
import android.os.Handler;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jzh extends ContentObserver {
    public jzh(Handler handler) {
        super(handler);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        LogUtil.a("ContactsObserver", "onChange: start");
        jze a2 = jze.a();
        a2.d();
        if (kae.c()) {
            a2.b(kal.a(), 0);
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "ContactsObserver");
            DeviceInfo deviceInfo = deviceList.size() != 0 ? deviceList.get(0) : null;
            if (deviceInfo == null) {
                LogUtil.h("ContactsObserver", "onChange deviceInfo is null");
                return;
            }
            DeviceCapability deviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(2, "", "ContactsObserver").get(deviceInfo.getDeviceIdentify());
            if (deviceCapability == null || !deviceCapability.isSupportSyncContacts()) {
                LogUtil.h("ContactsObserver", "onChange is not support");
                return;
            }
            a2.e(deviceInfo);
        } else {
            LogUtil.h("ContactsObserver", "onChange: cannot sync contacts without contact permissions.");
        }
        a2.b(BaseApplication.getContext(), kal.a());
    }
}
