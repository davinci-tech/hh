package defpackage;

import com.huawei.health.ecologydevice.fitness.datastruct.RopeRealData;
import health.compact.a.util.LogUtil;

/* loaded from: classes3.dex */
public class cyq extends cyt {
    public RopeRealData e() {
        b();
        byte[] a2 = a();
        RopeRealData ropeRealData = null;
        if (a2 != null && a2.length > 0) {
            if (!a(a2, 0, a2.length - 1)) {
                LogUtil.c("PDROPE_RealTimeDataPackets", "The data is not received.");
                return null;
            }
            LogUtil.d("PDROPE_RealTimeDataPackets", "real-time protocolData = ", dis.d(a2, ""));
            ropeRealData = d(a2, 1);
            if (ropeRealData == null) {
                LogUtil.d("PDROPE_RealTimeDataPackets", "The data is error");
                return ropeRealData;
            }
            int e = cyw.e(a2, 17, 16);
            c(e);
            LogUtil.b("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, totalPublicLength:" + e);
            int e2 = cyw.e(a2, 17, 17);
            ropeRealData.setRopeSkippingMode(e2);
            LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, ropeType:" + e2);
            int e3 = cyw.e(a2, 18, 18);
            ropeRealData.setSkippingSpeedSeconds(e3);
            LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, skippingSpeedSeconds:" + e3);
            d(a2, ropeRealData, 20);
        } else {
            LogUtil.d("PDROPE_RealTimeDataPackets", "protocolData is null");
        }
        return ropeRealData;
    }

    private void d(byte[] bArr, RopeRealData ropeRealData, int i) {
        if (ropeRealData.getRopeSkippingMode() == 4 || ropeRealData.getRopeSkippingMode() == 5) {
            int e = cyw.e(bArr, 17, i);
            ropeRealData.setRopeIntermissionGroupNo(e);
            int e2 = cyw.e(bArr, 18, i + 1);
            LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, ropeIntermissionGroup:" + e, ", ropeIntermissionTime:" + e2);
            if (ropeRealData.getStatus() == 1) {
                ropeRealData.setRopeIntermissionTime(e2);
                return;
            } else {
                if (ropeRealData.getStatus() == 2) {
                    ropeRealData.setRopeIntermissionRestTime(e2);
                    return;
                }
                return;
            }
        }
        if (ropeRealData.getRopeSkippingMode() == 3) {
            ropeRealData.setRopeSkippingTrick(e(bArr, i));
        } else {
            LogUtil.e("PDROPE_RealTimeDataPackets", "protocolData is error");
        }
    }

    public RopeRealData d(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || bArr.length < 14) {
            LogUtil.d("PDROPE_RealTimeDataPackets", "RopeRealData is error");
            return null;
        }
        RopeRealData ropeRealData = new RopeRealData();
        int e = cyw.e(bArr, 17, i);
        ropeRealData.setStatus(e);
        LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, status:" + e);
        int e2 = cyw.e(bArr, 18, i + 1);
        ropeRealData.setElapsedTime(e2);
        LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, elapsedTime:" + e2);
        int e3 = cyw.e(bArr, 18, i + 3);
        ropeRealData.setTotalSkip(e3);
        LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, totalSkip:" + e3);
        int e4 = cyw.e(bArr, 18, i + 5);
        ropeRealData.setInstantaneousSpeed(e4);
        LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, instantaneousSpeed:" + e4);
        int e5 = cyw.e(bArr, 18, i + 7);
        ropeRealData.setInterruptTimes(e5);
        LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, interruptTimes:" + e5);
        int e6 = cyw.e(bArr, 18, i + 9);
        ropeRealData.setCurrentContinueSkip(e6);
        LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, currentContinueSkip:" + e6);
        int e7 = cyw.e(bArr, 18, i + 11);
        ropeRealData.setTotalEnergy(e7);
        LogUtil.d("PDROPE_RealTimeDataPackets", "RopeDataCharacteristic, totalEnergy:" + e7);
        return ropeRealData;
    }
}
