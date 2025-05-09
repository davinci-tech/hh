package defpackage;

import com.careforeyou.library.enums.Weight_Digit;
import com.careforeyou.library.enums.Weight_Unit;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes2.dex */
public class oh {
    public static float b(float f) {
        return f * 0.5f;
    }

    public static float c(float f) {
        return f * 0.4535924f;
    }

    public static float b(String str) {
        return new BigDecimal(str.split(":").length == 2 ? c((Integer.parseInt(r3[0]) * 14) + Float.parseFloat(r3[1])) : 0.0f).setScale(3, 4).floatValue();
    }

    public static ny a(byte b, byte b2, byte b3, boolean z) {
        Weight_Digit i;
        if (z) {
            i = oc.e(b3);
        } else {
            i = oc.i(b3);
        }
        return e(b, b2, b3, i, false);
    }

    public static ny e(byte b, byte b2, byte b3, Weight_Digit weight_Digit, boolean z) {
        Weight_Unit g;
        float a2;
        if (z) {
            g = oc.j(b3);
        } else {
            g = oc.g(b3);
        }
        ny nyVar = new ny();
        int i = 0;
        byte[] bArr = {b, b2};
        if (weight_Digit == Weight_Digit.ONE) {
            a2 = oc.a(bArr) / 10.0f;
            i = 1;
        } else if (weight_Digit == Weight_Digit.TWO) {
            a2 = oc.a(bArr) / 100.0f;
            i = 2;
        } else {
            a2 = oc.a(bArr);
        }
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(FilenameUtils.EXTENSION_SEPARATOR);
        float floatValue = new BigDecimal(a2).setScale(i, 4).floatValue();
        if (g == Weight_Unit.ST) {
            nyVar.e = (b & 255) + ":" + ((b2 & 255) / 10.0f);
        } else if (i == 0) {
            nyVar.e = "" + ((int) floatValue);
        } else if (i == 1) {
            nyVar.e = new DecimalFormat("#0.0", decimalFormatSymbols).format(floatValue);
        } else if (i == 2) {
            nyVar.e = new DecimalFormat("#0.00", decimalFormatSymbols).format(floatValue);
        }
        if (g == Weight_Unit.JIN) {
            nyVar.f15551a = b(floatValue);
        } else if (g == Weight_Unit.LB) {
            nyVar.f15551a = c(floatValue);
        } else if (g == Weight_Unit.ST) {
            nyVar.f15551a = b(nyVar.e);
        } else {
            nyVar.f15551a = floatValue;
        }
        nyVar.f15551a = new BigDecimal(nyVar.f15551a).setScale(i, 4).doubleValue();
        return nyVar;
    }
}
