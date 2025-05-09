package defpackage;

/* loaded from: classes5.dex */
public class ktl {
    private static final a[] e = {new a(49.2204d, 79.4462d, 42.8899d, 96.33d), new a(54.1415d, 109.6872d, 39.3742d, 135.0002d), new a(42.8899d, 73.1246d, 29.5297d, 124.143255d), new a(29.5297d, 82.9684d, 26.7186d, 97.0352d), new a(29.5297d, 97.0253d, 20.414096d, 124.367395d), new a(20.414096d, 107.975793d, 17.871542d, 111.744104d), new a(40.167271d, 124.158707d, 39.964563d, 124.430575d)};
    private static final a[] d = {new a(25.398623d, 119.921265d, 21.785006d, 122.497559d), new a(22.284d, 101.8652d, 20.0988d, 106.665d), new a(21.5422d, 106.4525d, 20.4878d, 108.051d), new a(55.8175d, 109.0323d, 50.3257d, 119.127d), new a(55.8175d, 127.4568d, 49.5574d, 137.0227d), new a(44.8922d, 131.2662d, 42.5692d, 137.0227d), new a(40.106277d, 124.379711d, 39.964563d, 124.430575d)};

    /* renamed from: a, reason: collision with root package name */
    private static final a[] f14584a = {new a(50.26252d, 127.44838d, 50.222656d, 127.55693d), new a(21.561273d, 107.948502d, 21.537023d, 107.969624d), new a(21.560673d, 107.969334d, 21.540219d, 107.978721d), new a(21.56157d, 107.978041d, 21.545004d, 107.996404d), new a(22.158318d, 113.538441d, 22.162086d, 113.540142d)};
    private static final a[] b = {new a(22.158318d, 113.538441d, 22.213538d, 113.598688d), new a(22.109501d, 113.553527d, 22.158318d, 113.598688d), new a(22.154752d, 113.834609d, 22.444842d, 114.407653d), new a(22.444842d, 113.955365d, 22.502398d, 114.407653d)};

    public static int b(double d2, double d3) {
        for (a aVar : b) {
            if (aVar.e(d2, d3) && !d(d2, d3)) {
                return 3;
            }
        }
        for (a aVar2 : e) {
            if (aVar2.e(d2, d3)) {
                return (!e(d2, d3) || d(d2, d3)) ? 1 : 2;
            }
        }
        return 2;
    }

    private static boolean e(double d2, double d3) {
        for (a aVar : d) {
            if (aVar.e(d2, d3)) {
                return true;
            }
        }
        return false;
    }

    private static boolean d(double d2, double d3) {
        for (a aVar : f14584a) {
            if (aVar.e(d2, d3)) {
                return true;
            }
        }
        return false;
    }

    static class a {
        private double b;
        private double c;
        private double d;
        private double e;

        a(double d, double d2, double d3, double d4) {
            this.d = Math.min(d2, d4);
            this.c = Math.max(d, d3);
            this.b = Math.max(d2, d4);
            this.e = Math.min(d, d3);
        }

        boolean e(double d, double d2) {
            return this.d <= d2 && this.b >= d2 && this.c >= d && this.e <= d;
        }
    }
}
