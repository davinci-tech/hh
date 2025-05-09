package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.R;
import com.huawei.hihealth.data.model.RelativeSportData;
import java.util.List;

/* loaded from: classes7.dex */
public class rdn {

    /* renamed from: a, reason: collision with root package name */
    private float f16719a;
    private int aa;
    private int ac;
    private float d;
    private float e;
    private long f;
    private int g;
    private int h;
    private List<RelativeSportData> i;
    private int j;
    private RelativeSportData k;
    private long l;
    private long m;
    private int o;
    private String p;
    private boolean q;
    private String r;
    private long v;
    private int y;
    private int u = 0;
    private int c = 0;
    private int n = 0;
    private int s = 0;
    private String x = "";
    private String w = "";
    private String t = "";
    private String b = "";

    public float a() {
        return this.d;
    }

    public long f() {
        return this.m;
    }

    public float d() {
        return this.e;
    }

    public int m() {
        return this.y;
    }

    public int b() {
        return this.j;
    }

    public long k() {
        return this.v;
    }

    public long i() {
        return this.l;
    }

    public int j() {
        return this.n;
    }

    public int l() {
        return this.aa;
    }

    public void f(int i) {
        this.aa = i;
    }

    public List<RelativeSportData> s() {
        return this.i;
    }

    public Drawable dMu_(Context context) {
        return rdu.dMz_(context, this.y, Integer.toString(this.o));
    }

    public int e(Context context) {
        return rdu.b(context, this.y);
    }

    public int n() {
        int d = rdf.d(this.g, this.p);
        if (this.u == 2) {
            return R.drawable._2131430484_res_0x7f0b0c54;
        }
        if (d == 4) {
            return R.drawable._2131430485_res_0x7f0b0c55;
        }
        if (d == 5) {
            return R.drawable._2131430490_res_0x7f0b0c5a;
        }
        if (d == 6) {
            return R.drawable._2131430487_res_0x7f0b0c57;
        }
        if (d == 7) {
            return R.drawable._2131430554_res_0x7f0b0c9a;
        }
        int i = this.ac;
        if (i == 199) {
            return R.drawable._2131430486_res_0x7f0b0c56;
        }
        if (i == 200) {
            return R.drawable._2131430489_res_0x7f0b0c59;
        }
        switch (i) {
            case 1:
                return R.drawable._2131429997_res_0x7f0b0a6d;
            case 2:
                return R.drawable._2131430488_res_0x7f0b0c58;
            case 3:
            case 4:
                return R.drawable._2131430485_res_0x7f0b0c55;
            case 5:
                return R.drawable._2131430490_res_0x7f0b0c5a;
            case 6:
                return R.drawable._2131430487_res_0x7f0b0c57;
            case 7:
                return R.drawable._2131430554_res_0x7f0b0c9a;
            default:
                return 0;
        }
    }

    public int r() {
        return this.h;
    }

    public void j(int i) {
        this.h = i;
    }

    public int e() {
        return this.c;
    }

    public RelativeSportData h() {
        return this.k;
    }

    public String g() {
        return this.t;
    }

    public void c(String str) {
        if ("0".equals(str)) {
            this.t = ExifInterface.LONGITUDE_EAST;
        } else {
            this.t = str;
        }
    }

    public long c() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public void d(float f, int i) {
        this.d = f;
        this.j = i;
    }

    public float o() {
        return this.f16719a;
    }

    public void c(long j) {
        this.m = j;
    }

    public void a(float f) {
        this.e = f;
    }

    public void g(int i) {
        this.y = i;
    }

    public void h(int i) {
        this.ac = i;
    }

    public int ac() {
        return this.ac;
    }

    public void a(int i) {
        this.g = i;
    }

    public int p() {
        return this.g;
    }

    public void d(String str) {
        this.p = str;
    }

    public int ad() {
        return rdf.d(this.g, this.p);
    }

    public void b(long j) {
        this.v = j;
    }

    public void e(long j) {
        this.l = j;
    }

    public void e(int i) {
        this.u = i;
    }

    public int u() {
        return this.u;
    }

    public void c(int i) {
        this.c = i;
    }

    public void d(float f) {
        this.f16719a = f;
    }

    public void d(int i) {
        this.n = i;
    }

    public void c(RelativeSportData relativeSportData) {
        this.k = relativeSportData;
    }

    public void d(List<RelativeSportData> list) {
        this.i = list;
    }

    public void d(boolean z) {
        this.q = z;
    }

    public boolean w() {
        return this.q;
    }

    public void b(int i) {
        this.s = i;
    }

    public String y() {
        return this.x;
    }

    public void b(String str) {
        this.x = str;
    }

    public String v() {
        return this.w;
    }

    public void j(String str) {
        this.w = str;
    }

    public String x() {
        return this.r;
    }

    public void e(String str) {
        this.r = str;
    }

    public String t() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int q() {
        return this.o;
    }

    public void i(int i) {
        this.o = i;
    }
}
