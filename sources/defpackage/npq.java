package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* loaded from: classes6.dex */
public final class npq implements Cloneable {
    private static final DecimalFormat d = new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.ENGLISH));

    /* renamed from: a, reason: collision with root package name */
    public final double f15429a;
    public final double c;

    public npq(double d2, double d3) {
        if (-180.0d <= d3 && d3 < 180.0d) {
            this.c = e(d3);
        } else {
            this.c = e(((((d3 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d);
        }
        this.f15429a = e(Math.max(-90.0d, Math.min(90.0d, d2)));
    }

    private static double e(double d2) {
        try {
            return Double.parseDouble(d.format(d2));
        } catch (NumberFormatException e) {
            LogUtil.b("Track_LatLng", e.getMessage());
            return 0.0d;
        }
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public npq clone() {
        return new npq(this.f15429a, this.c);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f15429a);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.c);
        return ((i + 31) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    public boolean equals(Object obj) {
        return (obj instanceof npq) && hashCode() == ((npq) obj).hashCode();
    }

    public String toString() {
        return "lat/lng";
    }
}
