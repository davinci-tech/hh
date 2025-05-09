package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class hjd implements Cloneable, Serializable {
    private static final DecimalFormat c = new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.ENGLISH));
    private static final long serialVersionUID = 1;
    public final double b;
    public final double d;

    public hjd(double d, double d2) {
        if (d2 >= -180.0d && d2 < 180.0d) {
            this.d = d(d2);
        } else {
            this.d = d(((((d2 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d);
        }
        this.b = d(Math.max(-90.0d, Math.min(90.0d, d)));
    }

    private static double d(double d) {
        try {
            return Double.parseDouble(c.format(d));
        } catch (NumberFormatException e) {
            LogUtil.b("Track_LatLng", e.getMessage());
            return 0.0d;
        }
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public hjd clone() {
        return new hjd(this.b, this.d);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.b);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.d);
        return ((i + 31) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    public boolean equals(Object obj) {
        return (obj instanceof hjd) && hashCode() == ((hjd) obj).hashCode();
    }

    public String toString() {
        return "lat/lng";
    }
}
