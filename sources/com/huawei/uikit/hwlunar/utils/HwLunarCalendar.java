package com.huawei.uikit.hwlunar.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.huawei.health.R;
import com.huawei.wearengine.sensor.DataResult;
import com.tencent.connect.common.Constants;
import defpackage.slu;
import defpackage.slv;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes7.dex */
public class HwLunarCalendar {
    private boolean h;
    private GregorianCalendar k;
    private Context l;
    private Calendar m;
    private boolean n;
    private int o;
    private int q;
    private final Object r;
    private int s;
    private boolean t;
    private static final int[] c = {27296, 47779, 43856, 19416, 19168, 42352, 21717, 53856, 55632, 21844, 22191, 39632, 21970, 19168, 42422, 42192, 53840, 53909, 46415, 54944, 44450, 38320, 18807, 18815, 42160, 46261, 27216, 27968, 43860, 11119, 38256, 21234, 18800, 25958, 54432, 59984, 27285, 23263, Constants.REQUEST_OLD_QZSHARE, 34531, 37615, 51415, 51551, 54432, 55462, 46431, 22176, 42420, 9695, 37584, 53938, 43344, 46423, 27808, 46416, 21333, 19887, 42416, 17779, 21183, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46752, 38310, 38335, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 23232, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 20854, 21183, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19195, 19152, 42192, 53430, 53855, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 45653, 27951, 44448, 19299, 37759, 18936, 18800, 25776, 26790, 59999, 27424, 42692, 43759, 37600, 53987, 51552, 54615, 54432, 55888, 23893, 22176, 42704, 21972, 21200, 43448, 43344, 46240, 46758, 44368, 21920, 43940, 42416, 21168, 45683, 26928, 29495, 27296, 44368, 19285, 19311, 42352, 21732, 53856, 59752, 54560, 55968, 27302, 22239, 19168, 43476, 42192, 53584, 62034, 54560, 56645, 46496, 22224};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f10678a = {R.string._2130850963_res_0x7f023493, R.string._2130850967_res_0x7f023497, R.string._2130850958_res_0x7f02348e, R.string._2130850959_res_0x7f02348f, R.string._2130850965_res_0x7f023495, R.string._2130850962_res_0x7f023492, R.string._2130850960_res_0x7f023490, R.string._2130850966_res_0x7f023496, R.string._2130850964_res_0x7f023494, R.string._2130850961_res_0x7f023491};
    private static final int[] d = {R.string._2130851353_res_0x7f023619, R.string._2130851343_res_0x7f02360f, R.string._2130851351_res_0x7f023617, R.string._2130851345_res_0x7f023611, R.string._2130851342_res_0x7f02360e, R.string._2130851347_res_0x7f023613, R.string._2130851349_res_0x7f023615, R.string._2130851348_res_0x7f023614, R.string._2130851346_res_0x7f023612, R.string._2130851352_res_0x7f023618, R.string._2130851350_res_0x7f023616, R.string._2130851344_res_0x7f023610};
    private static final int[] b = {R.string._2130851329_res_0x7f023601, R.string._2130851320_res_0x7f0235f8, R.string._2130851340_res_0x7f02360c, R.string._2130851333_res_0x7f023605, R.string._2130851323_res_0x7f0235fb, R.string._2130851337_res_0x7f023609, R.string._2130851327_res_0x7f0235ff, R.string._2130851335_res_0x7f023607, R.string._2130851328_res_0x7f023600, R.string._2130851321_res_0x7f0235f9, R.string._2130851322_res_0x7f0235fa, R.string._2130851332_res_0x7f023604};
    private static final int[] e = {R.string._2130851399_res_0x7f023647, R.string._2130850777_res_0x7f0233d9, R.string._2130851584_res_0x7f023700, R.string._2130851460_res_0x7f023684, R.string._2130850831_res_0x7f02340f, R.string._2130851507_res_0x7f0236b3, R.string._2130851227_res_0x7f02359b, R.string._2130851503_res_0x7f0236af, R.string._2130851397_res_0x7f023645, R.string._2130850780_res_0x7f0233dc, R.string._2130850828_res_0x7f02340c, R.string._2130851456_res_0x7f023680};
    private static final int[] g = {R.string._2130851662_res_0x7f02374e, R.string._2130851427_res_0x7f023663, R.string._2130851596_res_0x7f02370c, R.string._2130851583_res_0x7f0236ff, R.string._2130850957_res_0x7f02348d, R.string._2130850956_res_0x7f02348c, R.string._2130851505_res_0x7f0236b1, R.string._2130851502_res_0x7f0236ae, R.string._2130850835_res_0x7f023413, R.string._2130851412_res_0x7f023654, R.string._2130851580_res_0x7f0236fc};
    private static final int[] i = {R.string._2130851331_res_0x7f023603, R.string._2130851341_res_0x7f02360d, R.string._2130851339_res_0x7f02360b, R.string._2130851326_res_0x7f0235fe, R.string._2130851325_res_0x7f0235fd, R.string._2130851336_res_0x7f023608, R.string._2130851334_res_0x7f023606, R.string._2130851324_res_0x7f0235fc, R.string._2130851330_res_0x7f023602, R.string._2130851338_res_0x7f02360a};
    private static final int[] f = {1, 3, 5, 7, 8, 10, 12};
    private static final int[] j = {4, 6, 9, 11};

    private HwLunarCalendar() {
        this.r = new Object();
    }

    private int a(int i2) {
        int b2 = b(i2) + 348;
        if (i2 < 1897 || i2 > 2102) {
            Log.e("HwLunarCalendar", "getLunarYearDays: lunarYear index out of boundry, lunarYear = " + i2);
            return 0;
        }
        for (int i3 = 32768; i3 > 8; i3 >>= 1) {
            b2 += (c[i2 + (-1897)] & i3) != 0 ? 1 : 0;
        }
        return b2;
    }

    private boolean a(int i2, int i3, int i4) {
        return i2 >= i3 && i2 <= i4;
    }

    private boolean a(int i2, int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int i3 : iArr) {
            if (i2 == i3) {
                return true;
            }
        }
        return false;
    }

    private void b(int i2, int i3, int i4) {
        this.n = true;
        boolean z = this.h;
        int i5 = z ? 1897 : 1900;
        int i6 = z ? 2102 : 2100;
        if (i2 == i5 && i3 < 1 && i4 < 31) {
            this.n = false;
            return;
        }
        if (i2 == i6 + 1 && i3 < 1 && i4 <= 28) {
            this.n = true;
            return;
        }
        if (i2 < i5 || i2 > i6) {
            this.n = false;
            return;
        }
        if (i3 < 0 || i3 > 12) {
            this.n = false;
            return;
        }
        int i7 = i3 + 1;
        if (i7 == 2) {
            if (new GregorianCalendar().isLeapYear(i2)) {
                this.n = a(i4, 1, 29);
                return;
            } else {
                this.n = a(i4, 1, 28);
                return;
            }
        }
        if (a(i7, f)) {
            this.n = a(i4, 1, 31);
        } else if (a(i7, j)) {
            this.n = a(i4, 1, 30);
        } else {
            Log.e("HwLunarCalendar", "wrong month index");
        }
    }

    public int a() {
        return this.q;
    }

    public String b() {
        if (!this.n) {
            return "";
        }
        return i() + e(true) + this.l.getString(R.string._2130850790_res_0x7f0233e6) + this.o;
    }

    public String c() {
        return c(false, true);
    }

    public String d() {
        String str = "";
        if (!this.n) {
            return "";
        }
        if (this.s <= 10) {
            str = "" + this.l.getString(R.string._2130850782_res_0x7f0233de);
        }
        return str + a(this.s, false);
    }

    public int e() {
        return this.s;
    }

    public void e(int i2, int i3, int i4) {
        synchronized (this.r) {
            Calendar calendar = Calendar.getInstance();
            int i5 = i3 - 1;
            calendar.set(i2, i5, i4);
            a(i2 == 0 ? System.currentTimeMillis() : calendar.getTimeInMillis());
            b(i2, i5, i4);
        }
    }

    public int h() {
        return this.o;
    }

    private void a(long j2) {
        this.m.setTimeInMillis(j2);
        this.o = 1897;
        slu sluVar = new slu(Calendar.getInstance().getTimeZone());
        sluVar.d(j2);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(sluVar.a(), sluVar.e(), sluVar.b());
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar(1897, 1, 2);
        gregorianCalendar2.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        a(gregorianCalendar, gregorianCalendar2);
    }

    public HwLunarCalendar(Context context) {
        this.r = new Object();
        this.l = context;
        this.n = true;
        this.k = new GregorianCalendar(TimeZone.getTimeZone(DataResult.UTC));
        this.m = Calendar.getInstance();
        this.h = true;
    }

    private void a(Calendar calendar, Calendar calendar2) {
        long b2 = b((calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / 86400000, a(this.o));
        int d2 = d(this.o);
        boolean z = false;
        boolean z2 = false;
        int i2 = 1;
        loop0: while (true) {
            boolean z3 = z2;
            while (i2 < 13 && b2 > 0) {
                long b3 = b(i2, z3, z2);
                if (b2 < b3) {
                    break loop0;
                }
                b2 -= b3;
                if (z2) {
                    i2++;
                    z3 = false;
                } else if (d2 == i2) {
                    break;
                } else {
                    i2++;
                }
            }
            z2 = true;
        }
        this.q = i2;
        if (i2 == d2 && z2) {
            z = true;
        }
        this.t = z;
        this.s = ((int) b2) + 1;
    }

    private int d(int i2) {
        if (i2 >= 1897 && i2 <= 2102) {
            int i3 = c[i2 - 1897] & 15;
            if (i3 == 15) {
                return 0;
            }
            return i3;
        }
        Log.e("HwLunarCalendar", "getLunarLeapMonth: lunarYear index out of boundry, lunarYear = " + i2);
        return 0;
    }

    private int b(int i2, boolean z, boolean z2) {
        if (z2 && z) {
            return b(this.o);
        }
        return d(this.o, i2);
    }

    private long b(long j2, int i2) {
        while (true) {
            int i3 = this.o;
            if (i3 >= 2102) {
                break;
            }
            long j3 = i2;
            if (j2 < j3) {
                break;
            }
            j2 -= j3;
            int i4 = i3 + 1;
            this.o = i4;
            i2 = a(i4);
        }
        return j2;
    }

    private int b(int i2) {
        if (i2 >= 1896 && i2 < 2103) {
            int i3 = c[i2 - 1896];
            if (d(i2) > 0) {
                return (i3 & 15) == 15 ? 30 : 29;
            }
            return 0;
        }
        Log.e("HwLunarCalendar", "getLunarLeapDays: lunarYear index out of boundry, lunarYear = " + i2);
        return 0;
    }

    private int d(int i2, int i3) {
        if (i2 >= 1897 && i2 <= 2102) {
            return (c[i2 - 1897] & (65536 >> i3)) != 0 ? 30 : 29;
        }
        Log.e("HwLunarCalendar", "getLunarMonthDays: lunarYear index out of boundry, lunarYear = " + i2);
        return 0;
    }

    private String e(boolean z) {
        if (z) {
            return this.l.getString(b[(this.o - 4) % 12]);
        }
        return this.l.getString(e[(this.o - 4) % 12]);
    }

    private String i() {
        if (!this.n) {
            return "";
        }
        int i2 = this.o - 1864;
        try {
            return this.l.getString(f10678a[i2 % 10]) + this.l.getString(d[i2 % 12]);
        } catch (Resources.NotFoundException unused) {
            return "";
        }
    }

    private String c(boolean z, boolean z2) {
        String str;
        String str2 = "";
        if (!this.n) {
            return "";
        }
        if (this.t) {
            if (z2) {
                str2 = "" + this.l.getString(R.string._2130851470_res_0x7f02368e);
            } else {
                str2 = "" + this.l.getString(R.string._2130851469_res_0x7f02368d);
            }
        }
        if (z2) {
            str = a(this.q, z2) + this.l.getString(R.string._2130850784_res_0x7f0233e0);
        } else {
            str = a(this.q, z2) + this.l.getString(R.string._2130850783_res_0x7f0233df);
        }
        int i2 = this.q;
        if (i2 == 1) {
            return str2 + b(z, this.l.getString(R.string._2130851663_res_0x7f02374f), str);
        }
        if (i2 == 11) {
            if (z2) {
                return str2 + b(z, this.l.getString(R.string._2130850830_res_0x7f02340e), str);
            }
            return str2 + b(z, this.l.getString(R.string._2130850829_res_0x7f02340d), str);
        }
        if (i2 != 12) {
            return str2 + str;
        }
        if (z2) {
            return str2 + b(z, this.l.getString(R.string._2130851371_res_0x7f02362b), str);
        }
        return str2 + b(z, this.l.getString(R.string._2130851370_res_0x7f02362a), str);
    }

    private String a(int i2, boolean z) {
        String str = "";
        if (i2 < 0) {
            return "";
        }
        int i3 = i2 / 10;
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 == 3) {
                    if (z) {
                        str = this.l.getString(R.string._2130850786_res_0x7f0233e2);
                    } else {
                        str = this.l.getString(R.string._2130850785_res_0x7f0233e1);
                    }
                }
            } else if (z) {
                str = this.l.getString(R.string._2130850788_res_0x7f0233e4);
            } else {
                str = this.l.getString(R.string._2130850787_res_0x7f0233e3);
            }
        } else if (z) {
            str = this.l.getString(i[9]);
        } else {
            str = this.l.getString(R.string._2130851580_res_0x7f0236fc);
        }
        int i4 = i2 % 10;
        if (i4 != 0) {
            str = str + this.l.getString(g[i4]);
        }
        return i2 == 20 ? slv.e[i2 - 1] : str;
    }

    private String b(boolean z, String str, String str2) {
        if (!z) {
            return str;
        }
        return str2 + String.format(Locale.ROOT, this.l.getString(R.string._2130851374_res_0x7f02362e), str);
    }
}
