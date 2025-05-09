package defpackage;

import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes3.dex */
public class cjk implements HealthDataParser {
    private float e(float f) {
        return f * 0.4535924f;
    }

    public HealthData d(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("WspMeasureDataParser", "parseWeightData result is null");
            return null;
        }
        ckm ckmVar = new ckm();
        byte[] c = c(bArr[0]);
        byte[] bArr2 = {bArr[2], bArr[1]};
        if (c[0] == 0) {
            ckmVar.setWeight(d(b(bArr2), 0.005f));
        } else {
            ckmVar.setWeight(e(d(b(bArr2), 0.01f)));
        }
        if (c[1] == 1) {
            bArr2[0] = bArr[4];
            bArr2[1] = bArr[3];
            String d = d(b(bArr2), 3, bArr);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            try {
                date = simpleDateFormat.parse(d);
            } catch (ParseException unused) {
                LogUtil.b("WspMeasureDataParser", "parseWeightData ParseException");
            }
            ckmVar.setEndTime(date.getTime());
            ckmVar.setStartTime(date.getTime());
        }
        return ckmVar;
    }

    @Override // com.huawei.health.device.open.data.HealthDataParser
    public HealthData parseData(byte[] bArr) {
        if (bArr != null) {
            int i = 11;
            if (bArr.length == 11) {
                ckm ckmVar = new ckm();
                byte[] bArr2 = {bArr[3], bArr[2]};
                ckmVar.setBodyFatRat(b(bArr2) / 10.0f);
                byte[] c = c(bArr[0]);
                if (c[1] == 1.0f) {
                    bArr2[0] = bArr[5];
                    bArr2[1] = bArr[4];
                    int b = b(bArr2);
                    LogUtil.c("WspMeasureDataParser", "parseData year ", Integer.valueOf(b));
                    String d = d(b, 4, bArr);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    try {
                        date = simpleDateFormat.parse(d);
                    } catch (ParseException unused) {
                        LogUtil.b("WspMeasureDataParser", "parseData exception");
                    }
                    ckmVar.setEndTime(date.getTime());
                    ckmVar.setStartTime(date.getTime());
                } else {
                    i = 4;
                }
                byte[] c2 = c(bArr[1]);
                if (c2[1] == 1.0f) {
                    bArr2[0] = bArr[i + 1];
                    bArr2[1] = bArr[i];
                    i += 2;
                }
                if (c2[2] == 1.0f) {
                    bArr2[0] = bArr[i + 1];
                    bArr2[1] = bArr[i];
                    if (c[0] == 0) {
                        ckmVar.setWeight(d(b(bArr2), 0.005f));
                    } else {
                        ckmVar.setWeight(e(d(b(bArr2), 0.01f)));
                    }
                }
                return ckmVar;
            }
        }
        LogUtil.h("WspMeasureDataParser", "parseData resultData is null or resultData.length != 11");
        return null;
    }

    private String d(int i, int i2, byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i).append(Constants.LINK).append((int) bArr[i2 + 2]).append(Constants.LINK).append((int) bArr[i2 + 3]).append(" ").append((int) bArr[i2 + 4]).append(":").append((int) bArr[i2 + 5]).append(":").append((int) bArr[i2 + 6]);
        return stringBuffer.toString();
    }

    private byte[] c(byte b) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return bArr;
    }

    private int b(byte[] bArr) {
        String d = dis.d(bArr, null);
        int i = 0;
        try {
            if (d != null) {
                i = Integer.parseInt(d, 16);
            } else {
                LogUtil.h("WspMeasureDataParser", "bytesToInt hexString is null");
            }
        } catch (NumberFormatException e) {
            LogUtil.b("WspMeasureDataParser", "bytesToInt NumberFormatException = ", e.getMessage());
        }
        return i;
    }

    private float d(int i, float f) {
        try {
            return Float.parseFloat(new BigDecimal(String.valueOf(i)).multiply(new BigDecimal(String.valueOf(f))).toString());
        } catch (NumberFormatException e) {
            LogUtil.b("WspMeasureDataParser", "getWeightForConversionRate NumberFormatException = ", e.getMessage());
            return 0.0f;
        }
    }
}
