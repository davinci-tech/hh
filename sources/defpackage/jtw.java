package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jtw implements DataReceiveCallback {
    private static volatile jtw c;

    public static jtw e() {
        if (c == null) {
            synchronized (jtw.class) {
                if (c == null) {
                    c = new jtw();
                }
            }
        }
        return c;
    }

    public void a() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.accountinfo", this);
    }

    public void c() {
        cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.accountinfo");
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (deviceInfo == null) {
            LogUtil.h("HwAccountInfoManager", "onDataReceived deviceInfo is null");
            return;
        }
        LogUtil.a("HwAccountInfoManager", "message = ", cvrVar.toString());
        if (cvrVar instanceof cvp) {
            cvp cvpVar = (cvp) cvrVar;
            if (c(cvpVar)) {
                try {
                    int a2 = new bmt().b(cvpVar.c(), 0).a((byte) 1, -1);
                    ReleaseLogUtil.e("R_HwAccountInfoManager", "reqEvent = ", Integer.valueOf(a2));
                    HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
                    hwGetDevicesParameter.setDeviceIdentify(deviceInfo.getDeviceIdentify());
                    List<DeviceInfo> deviceList = cuo.d().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "HwAccountInfoManager");
                    if (deviceList == null || deviceList.size() == 0) {
                        LogUtil.h("HwAccountInfoManager", "getDeviceList is null");
                        return;
                    } else {
                        a(deviceList.get(0), a2);
                        return;
                    }
                } catch (bmk unused) {
                    ReleaseLogUtil.c("R_HwAccountInfoManager", "handleSampleEvent error EventData = ", cvpVar.c());
                    return;
                }
            }
            return;
        }
        LogUtil.h("HwAccountInfoManager", "onDataReceived sampleBase not instanceof SampleEvent");
    }

    private boolean c(cvp cvpVar) {
        if (!cvpVar.getSrcPkgName().equals("hw.watch.accountinfo")) {
            LogUtil.h("HwAccountInfoManager", "wearPkgName not match.");
            return false;
        }
        if (cvpVar.getCommandId() != 2) {
            LogUtil.h("HwAccountInfoManager", "command not match.");
            return false;
        }
        if (cvpVar.e() != 800100015) {
            LogUtil.h("HwAccountInfoManager", "eventId not match.");
            return false;
        }
        byte[] c2 = cvpVar.c();
        if (c2 != null && c2.length != 0) {
            return true;
        }
        LogUtil.h("HwAccountInfoManager", "sampleEvent data is empty.");
        return false;
    }

    /* synthetic */ void e(DeviceInfo deviceInfo, int i, Object obj) {
        ReleaseLogUtil.e("R_HwAccountInfoManager", "refreshUserInfo onFinish, errorCode = ", Integer.valueOf(i));
        a(deviceInfo, d());
    }

    private void a(final DeviceInfo deviceInfo, int i) {
        if (i == 2) {
            a(deviceInfo, d());
        } else if (i == 3) {
            ThirdPartyLoginManager.getInstance().queryUserInfo(new IBaseResponseCallback() { // from class: jty
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    jtw.this.e(deviceInfo, i2, obj);
                }
            }, false);
        } else {
            LogUtil.h("HwAccountInfoManager", "reqEvent is invalid");
        }
    }

    private byte[] d() {
        int i;
        try {
            i = Integer.parseInt(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1019));
        } catch (NumberFormatException e) {
            LogUtil.b("HwAccountInfoManager", "NumberFormatException = ", e.getMessage());
            i = -1;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1014);
        boolean isKidAccount = LoginInit.getInstance(BaseApplication.getContext()).isKidAccount();
        LogUtil.a("HwAccountInfoManager", "ageGroupFlag = ", Integer.valueOf(i), ",registrationCountryCode = ", accountInfo, ",serviceCountryCode = ", accountInfo2, ",kidAccount = ", Boolean.valueOf(isKidAccount));
        bms bmsVar = new bms();
        bmsVar.j(1, 1).j(2, i).d(3, accountInfo).d(4, accountInfo2).j(5, isKidAccount ? 1 : 0);
        return bmsVar.d();
    }

    private void a(DeviceInfo deviceInfo, byte[] bArr) {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.accountinfo");
        cvpVar.setWearPkgName("hw.watch.accountinfo");
        cvpVar.a(800100015L);
        cvpVar.setCommandId(2);
        cvpVar.b(0);
        cvpVar.e(bArr);
        cuk.b().sendSampleEventCommand(deviceInfo, cvpVar, "HwAccountInfoManager");
    }
}
