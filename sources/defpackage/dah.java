package defpackage;

import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class dah implements HealthDataParser {
    @Override // com.huawei.health.device.open.data.HealthDataParser
    public HealthData parseData(byte[] bArr) {
        return c(bArr);
    }

    private ddz c(byte[] bArr) {
        LogUtil.a("HdpBloodPressureDataParser", "Enter getBloodPressureData()");
        if (bArr != null && bArr.length >= 63) {
            short s = (short) (bArr[45] & 255);
            short s2 = (short) (bArr[47] & 255);
            short s3 = (short) (bArr[63] & 255);
            long a2 = a(bArr);
            if (s < Short.MAX_VALUE && s > 0) {
                if (s2 >= Short.MAX_VALUE || s2 <= 0) {
                    LogUtil.h("HdpBloodPressureDataParser", "getBloodPressureData() diastolic invalid");
                    return null;
                }
                if (s3 >= Short.MAX_VALUE || s3 <= 0) {
                    LogUtil.h("HdpBloodPressureDataParser", "getBloodPressureData() heartRate invalid");
                    return null;
                }
                ddz ddzVar = new ddz();
                ddzVar.e(s, s2, s3, a2);
                return ddzVar;
            }
            LogUtil.h("HdpBloodPressureDataParser", "getBloodPressureData() systolic invalid");
        }
        return null;
    }

    private long a(byte[] bArr) {
        Calendar calendar = Calendar.getInstance();
        int d = d(bArr[50]);
        calendar.set(d(bArr[51]) + (d * 100), d(bArr[52]) - 1, d(bArr[53]), d(bArr[54]), d(bArr[55]), d(bArr[56]));
        long timeInMillis = calendar.getTimeInMillis();
        long j = timeInMillis - 9249352595000L;
        if (j < 1000 && j > -1000) {
            timeInMillis = System.currentTimeMillis();
        }
        LogUtil.c("HdpBloodPressureDataParser", "getTime() time = ", Long.valueOf(timeInMillis));
        return timeInMillis;
    }

    private int d(byte b) {
        try {
            return Integer.parseInt(Integer.toString(b & 255, 16));
        } catch (NumberFormatException e) {
            LogUtil.b("HdpBloodPressureDataParser", "hexadecimalToDecimal e = ", e.getMessage());
            return 0;
        }
    }
}
