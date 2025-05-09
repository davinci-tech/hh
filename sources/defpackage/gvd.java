package defpackage;

import android.location.Location;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gvd extends gvh {
    private List<GpsPoint> d;
    private int[] f;
    private int h;
    private int j = Integer.MAX_VALUE;
    private int b = -1;
    private b c = new b();

    @Override // defpackage.gvh
    public void b(enc encVar) {
        super.b(encVar);
        emf a2 = encVar.a();
        if (a2 == null) {
            LogUtil.a("Track_RouteCalculate", "no route draw cpPoint");
        } else {
            this.d = a2.e();
            this.j = a2.b();
        }
    }

    @Override // defpackage.gvh
    public boolean b(Map<Long, double[]> map, int i) {
        return i();
    }

    @Override // defpackage.gvh
    public Object b() {
        if (this.f == null) {
            return null;
        }
        b bVar = new b();
        bVar.b(this.f);
        bVar.c(this.h);
        bVar.e(this.b);
        bVar.b(a());
        bVar.a(i());
        return bVar;
    }

    @Override // defpackage.gvh
    public void aUw_(Location location) {
        if (koq.b(this.d)) {
            return;
        }
        int size = this.d.size();
        if (h() || this.f.length != size) {
            this.f = new int[size];
        }
        this.h = 0;
        hjd hjdVar = new hjd(location.getLatitude(), location.getLongitude());
        if (TextUtils.equals(AMapLocation.COORD_TYPE_GCJ02, this.e)) {
            hjdVar = gwf.c(hjdVar);
        }
        for (int i = 0; i < size; i++) {
            if (gwe.e(gwe.c(this.d.get(i)), hjdVar) < 60.0f) {
                this.f[i] = 1;
                this.h = 1;
            }
        }
        this.b = d();
    }

    @Override // defpackage.gvh
    public void b(Object obj) {
        if (obj instanceof int[]) {
            this.f = (int[]) obj;
            this.b = d();
        }
    }

    @Override // defpackage.gvh
    public void e() {
        super.e();
        List<GpsPoint> list = this.d;
        if (list != null) {
            list.clear();
        }
    }

    private boolean i() {
        return this.b >= this.j;
    }

    private int a() {
        if (f()) {
            return 0;
        }
        return (this.b * 100) / this.d.size();
    }

    private int d() {
        if (h()) {
            return 0;
        }
        int i = 0;
        for (int i2 : this.f) {
            if (i2 == 1) {
                i++;
            }
        }
        return i;
    }

    private boolean h() {
        int[] iArr = this.f;
        return iArr == null || iArr.length == 0;
    }

    private boolean f() {
        return koq.b(this.d);
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private int[] f12953a;
        private int b;
        private int c;
        private boolean d;
        private int e;

        public int[] b() {
            return this.f12953a;
        }

        public void b(int[] iArr) {
            this.f12953a = iArr;
        }

        public int d() {
            return this.e;
        }

        public void c(int i) {
            this.e = i;
        }

        public int a() {
            return this.c;
        }

        public void b(int i) {
            this.c = i;
        }

        public int c() {
            return this.b;
        }

        public void e(int i) {
            this.b = i;
        }

        public boolean e() {
            return this.d;
        }

        public void a(boolean z) {
            this.d = z;
        }
    }
}
