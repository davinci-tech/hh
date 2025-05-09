package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class kdz extends HwBaseManager implements ParserInterface {
    private kdz() {
        super(BaseApplication.getContext());
    }

    public static kdz b() {
        return a.f14311a;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("AlarmData_AlarmDataSyncManager", bArr, "getResult(): ");
        if (bArr == null || bArr.length < 2) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_AlarmDataSyncManager", "processResult data is null.");
        } else if (bArr.length < 3) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_AlarmDataSyncManager", "dataInfos length is less than 3.");
        } else {
            b(bArr, deviceInfo);
        }
    }

    public void b(byte[] bArr, DeviceInfo deviceInfo) {
        blt.d("AlarmData_AlarmDataSyncManager", bArr, "getResult(): ");
        byte b = bArr[1];
        if (b == 30) {
            LogUtil.a("AlarmData_AlarmDataSyncManager", "5.7.30 response : ", cvx.d(bArr));
            kee.c().e(bArr);
        } else if (b == 38) {
            kej.e().a(bArr, deviceInfo);
        } else if (b == 39) {
            kej.e().d(bArr, deviceInfo);
        } else {
            blt.d("AlarmData_AlarmDataSyncManager", bArr, "command id is other value", Byte.valueOf(b), "; dataInfos is ");
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final kdz f14311a = new kdz();
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 7303839;
    }
}
