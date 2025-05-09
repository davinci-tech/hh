package defpackage;

import com.careforeyou.library.bean.CsFatScale;
import com.careforeyou.library.enums.EnumProcessResult;
import com.careforeyou.library.enums.Weight_Digit;
import com.careforeyou.library.enums.Weight_Unit;
import com.careforeyou.library.protocal.iStraightFrame;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes2.dex */
public class nw implements iStraightFrame {
    private CsFatScale c = null;

    @Override // com.careforeyou.library.protocal.iStraightFrame
    public EnumProcessResult process(byte[] bArr, String str) throws nq {
        int i;
        CsFatScale csFatScale = new CsFatScale();
        this.c = csFatScale;
        byte b = bArr[0];
        if (b == -64) {
            i = 1;
        } else {
            if (b != -60 && b != -56 && b != -59) {
                throw new nq("帧格式错误 -- 类型不对");
            }
            i = 0;
        }
        csFatScale.setDeviceType(i);
        if (str.equalsIgnoreCase("0000fff1-0000-1000-8000-00805f9b34fb")) {
            this.c.setLockFlag((byte) 0);
            this.c.weighingDate = new Date();
        } else {
            this.c.setLockFlag((byte) 1);
            this.c.weighingDate = new Date(oc.a(oc.a(bArr, 2, 4)) * 1000);
        }
        this.c.setHistoryData(false);
        if (str.compareToIgnoreCase("0000fa9c-0000-1000-8000-00805f9b34fb") == 0) {
            this.c.setHistoryData(true);
        }
        byte b2 = bArr[1];
        ny a2 = oh.a(bArr[7], bArr[6], b2, true);
        this.c.setWeight(a2.f15551a * 10.0d);
        this.c.setScaleWeightC(a2.e);
        this.c.setScaleProperty(b(b2, bArr[0]));
        byte b3 = bArr[0];
        if (b3 != -64) {
            if (b3 == -59) {
                String a3 = oc.a(b2);
                this.c.setImpedanceMeasuringType(Integer.parseInt(a3.substring(6, 7)));
                this.c.setHeartRateMeasuringType(Integer.parseInt(a3.substring(7)));
                this.c.setImpedance(oc.a(new byte[]{bArr[9], bArr[8]}) * 0.1f);
                int b4 = oc.b(bArr[10]);
                if (b4 > 0) {
                    this.c.setRemark("2:" + b4);
                } else {
                    this.c.setRemark(null);
                }
            } else if (b3 == -60) {
                this.c.setImpedance(oc.a(new byte[]{bArr[9], bArr[8]}) * 0.1f);
            } else if (b3 == -56) {
                float a4 = oc.a(new byte[]{bArr[9], bArr[8]}) * 0.1f;
                this.c.setImpedance(a4);
                if (a4 > 0.0f) {
                    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
                    decimalFormatSymbols.setDecimalSeparator(FilenameUtils.EXTENSION_SEPARATOR);
                    DecimalFormat decimalFormat = new DecimalFormat("#0.0", decimalFormatSymbols);
                    float a5 = oc.a(new byte[]{bArr[11], bArr[10]});
                    float a6 = oc.a(new byte[]{bArr[13], bArr[12]});
                    float a7 = oc.a(new byte[]{bArr[15], bArr[14]});
                    float a8 = oc.a(new byte[]{bArr[17], bArr[16]});
                    float a9 = oc.a(new byte[]{bArr[19], bArr[18]});
                    this.c.setRemark("1:" + decimalFormat.format(a5 * 0.1f) + "," + decimalFormat.format(a6 * 0.1f) + "," + decimalFormat.format(a7 * 0.1f) + "," + decimalFormat.format(a8 * 0.1f) + "," + decimalFormat.format(a9 * 0.1f));
                }
            } else {
                return EnumProcessResult.Wait_Scale_Data;
            }
        }
        return EnumProcessResult.Received_Scale_Data;
    }

    private byte b(byte b, byte b2) {
        String str;
        String str2;
        Weight_Unit g = oc.g(b);
        Weight_Digit e = oc.e(b);
        if (g == Weight_Unit.JIN) {
            str = "00001";
        } else if (g == Weight_Unit.LB) {
            str = "00010";
        } else {
            str = g == Weight_Unit.ST ? "00011" : "00000";
        }
        if (e == Weight_Digit.ONE) {
            str2 = str + "001";
        } else if (e == Weight_Digit.TWO) {
            str2 = str + "101";
        } else {
            str2 = str + "011";
        }
        return (byte) Integer.parseInt(str2, 2);
    }

    @Override // com.careforeyou.library.protocal.iStraightFrame
    public CsFatScale getFatScale() {
        return this.c;
    }
}
