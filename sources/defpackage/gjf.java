package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.threeCircle.remind.model.DeviceEventData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.TimeZone;

/* loaded from: classes4.dex */
public class gjf {
    public static String b(cvp cvpVar) {
        if (cvpVar.getCommandId() != 2) {
            ReleaseLogUtil.d("R_TC_3CircDevInt", "onDataReceived, message commandId: ", Integer.valueOf(cvpVar.getCommandId()));
            return "";
        }
        if (cvpVar.e() != 800200001) {
            ReleaseLogUtil.d("R_TC_3CircDevInt", "onDataReceived, sampleEvent EventId is error");
            return "";
        }
        int a2 = a(cvpVar.c());
        if (a2 == 1) {
            return "WeekSummary";
        }
        if (a2 == 2) {
            return "OverGoal";
        }
        if (a2 == 3) {
            return "PerfectMonth";
        }
        LogUtil.h("R_TC_3CircDevInt", "unknown event id");
        return "";
    }

    private static int a(byte[] bArr) {
        Object[] objArr = new Object[4];
        objArr[0] = "eventData byte size:";
        objArr[1] = Integer.valueOf(bArr != null ? bArr.length : 0);
        objArr[2] = " ";
        objArr[3] = cvx.d(bArr);
        ReleaseLogUtil.e("R_TC_3CircDevInt", objArr);
        if (bArr == null || bArr.length == 0) {
            return -1;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int i = wrap.getInt();
        byte b = wrap.get();
        ReleaseLogUtil.e("R_TC_3CircDevInt", "timeStamp:", Integer.valueOf(i), " eventId:", Byte.valueOf(b));
        return b;
    }

    public static boolean d() {
        int a2 = SharedPreferenceManager.a("threeCircleSp", "hw.sport.threecircle", 0);
        ReleaseLogUtil.e("R_TC_3CircDevInt", "connectDate:", Integer.valueOf(a2));
        return a2 != 0 && jdl.d(a2, DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault())) <= 7;
    }

    public static void c(DeviceInfo deviceInfo, DeviceEventData deviceEventData) {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.sport.threecircle");
        cvpVar.setWearPkgName(SyncStrategy.WEAR_PKG_NAME);
        cvpVar.setCommandId(2);
        cvpVar.a(800200001L);
        cvpVar.b(0);
        byte[] bytes = deviceEventData.getBytes();
        ReleaseLogUtil.e("R_TC_3CircDevInt", "eventData:", cvx.d(bytes));
        cvpVar.e(bytes);
        cuk.b().sendSampleEventCommand(deviceInfo, cvpVar, "R_TC_3CircDevInt");
    }

    public static void e(DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 154);
        boolean c2 = cwi.c(deviceInfo, 156);
        ReleaseLogUtil.e("R_TC_3CircDevInt", "isSupportThreeCircle:", Boolean.valueOf(c), " isLite:", Boolean.valueOf(c2));
        if (c && !c2) {
            SharedPreferenceManager.b("threeCircleSp", "hw.sport.threecircle", DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault()));
        }
        if (c || c2) {
            SharedPreferenceManager.e("threeCircleSp", "devicesConnectTime", System.currentTimeMillis());
        }
    }

    public static boolean b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("R_TC_3CircDevInt", "isSupportCompleteThreeCircle deviceInfo == null");
            return false;
        }
        boolean c = cwi.c(deviceInfo, 154);
        boolean c2 = cwi.c(deviceInfo, 156);
        ReleaseLogUtil.e("R_TC_3CircDevInt", "isSupportCompleteThreeCircle isSupportWriteBackThreeCircle:", Boolean.valueOf(c), "isThreeCircleLite:", Boolean.valueOf(c2));
        return c && !c2;
    }
}
