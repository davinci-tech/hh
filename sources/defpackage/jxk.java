package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jxk {
    public static void e(DeviceInfo deviceInfo) {
        LogUtil.a("BasicDataSendCommandUtil", "sendSyncTodayData enter.");
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.e(deviceInfo));
    }

    public static void c(long j, long j2, DeviceInfo deviceInfo) {
        LogUtil.a("BasicDataSendCommandUtil", "sendHealthDataByFrameCountCmd enter.");
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.a(j, j2, deviceInfo));
    }

    public static void a(int i, DeviceInfo deviceInfo) {
        LogUtil.a("BasicDataSendCommandUtil", "sendHealthDataByFrameCompressedCmd enter index:", Integer.valueOf(i));
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.d(i, deviceInfo));
    }

    public static void b(int i, DeviceInfo deviceInfo) {
        LogUtil.a("BasicDataSendCommandUtil", "sendHealthDataByFrameCmd enter index:", Integer.valueOf(i));
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.c(i, deviceInfo));
    }

    public static void a(long j, long j2) {
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.c(j, j2));
    }

    public static void c(int i, DeviceInfo deviceInfo) {
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.a(i, deviceInfo));
    }

    public static void b(long j, long j2) {
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.b(j, j2));
    }

    public static void e(int i, DeviceInfo deviceInfo) {
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.e(i, deviceInfo));
    }

    public static void b(List<jqe> list) {
        LogUtil.a("BasicDataSendCommandUtil", "sendSetDeviceReportThreshold thresholdList: ", list);
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.b(list));
    }

    public static void d() {
        ReleaseLogUtil.e("R_BasicDataSendCommandUtil", "sendDeviceReport.");
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.a());
    }

    public static void c(long j, long j2, int i) {
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.a(j, j2, i));
    }

    public static void d(int i, DeviceInfo deviceInfo) {
        jsz.b(BaseApplication.getContext()).sendDeviceData(jxg.b(i, deviceInfo));
    }
}
