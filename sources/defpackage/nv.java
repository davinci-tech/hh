package defpackage;

import com.careforeyou.library.bean.CsFatScale;
import com.careforeyou.library.enums.EnumProcessResult;
import com.careforeyou.library.enums.Weight_Digit;
import com.careforeyou.library.enums.Weight_Unit;
import com.careforeyou.library.protocal.iStraightFrame;
import com.huawei.openalliance.ad.constant.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class nv implements iStraightFrame {
    private CsFatScale b = null;

    @Override // com.careforeyou.library.protocal.iStraightFrame
    public EnumProcessResult process(byte[] bArr, String str) throws nq {
        int i;
        if (bArr == null) {
            throw new nq("帧格式错误 -- 帧为空");
        }
        if (bArr.length < 14) {
            throw new nq("帧格式错误 -- 帧长度不对");
        }
        byte b = bArr[0];
        if (b == 2 && bArr[1] == 0) {
            i = 1;
        } else {
            if ((b != 6 || bArr[1] != 3) && (b != 6 || bArr[1] != 35)) {
                throw new nq("帧格式错误 -- 类型不对");
            }
            i = 0;
        }
        CsFatScale csFatScale = new CsFatScale();
        this.b = csFatScale;
        csFatScale.setDeviceType(i);
        if (str.equalsIgnoreCase("0000fff1-0000-1000-8000-00805f9b34fb")) {
            this.b.setLockFlag((byte) 0);
            this.b.weighingDate = new Date();
        } else {
            this.b.setLockFlag((byte) 1);
            String str2 = oc.a(new byte[]{bArr[3], bArr[2]}) + Constants.LINK + ((int) bArr[4]) + Constants.LINK + ((int) bArr[5]) + " " + ((int) bArr[6]) + ":" + ((int) bArr[7]) + ":" + ((int) bArr[8]);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            try {
                date = simpleDateFormat.parse(str2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.b.weighingDate = date;
        }
        this.b.setHistoryData(false);
        if (str.compareToIgnoreCase("0000fa9c-0000-1000-8000-00805f9b34fb") == 0) {
            this.b.setHistoryData(true);
        }
        if (i == 0) {
            this.b.setImpedance(oc.a(new byte[]{bArr[10], bArr[9]}) * 0.1f);
        }
        byte b2 = bArr[15];
        ny a2 = oh.a(bArr[12], bArr[11], b2, true);
        this.b.setWeight(a2.f15551a * 10.0d);
        this.b.setScaleWeightC(a2.e);
        this.b.setScaleProperty(b(b2));
        return EnumProcessResult.Received_Scale_Data;
    }

    private byte b(byte b) {
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
        return this.b;
    }
}
