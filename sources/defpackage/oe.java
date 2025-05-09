package defpackage;

import java.math.BigDecimal;

/* loaded from: classes2.dex */
public class oe {
    public static float b(float f) {
        return new BigDecimal(Double.toString(Double.parseDouble(String.valueOf(f)))).setScale(1, 4).floatValue();
    }
}
