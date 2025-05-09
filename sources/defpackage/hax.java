package defpackage;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;

/* loaded from: classes4.dex */
public class hax {
    private LatLong b;
    private double c;
    private LatLngBounds.Builder d = new LatLngBounds.Builder();

    /* renamed from: a, reason: collision with root package name */
    private LatLngBounds f13056a = null;

    public hax() {
        this.b = null;
        this.c = 0.0d;
        this.c = 0.0d;
        this.b = null;
    }

    public void d() {
        this.c = 0.0d;
        this.b = null;
        this.d = new LatLngBounds.Builder();
        this.f13056a = null;
    }

    public void b(LatLong latLong) {
        if (latLong == null || hbb.c(latLong.getLatLng().b, latLong.getLatLng().d) || hbb.e(latLong.getLatLng().b, latLong.getLatLng().d)) {
            return;
        }
        this.d.include(new LatLng(latLong.getLatLng().b, latLong.getLatLng().d));
        e(latLong);
    }

    public LatLong a() {
        if (this.f13056a == null) {
            this.f13056a = this.d.build();
        }
        if (this.f13056a == null) {
            return new LatLong();
        }
        return new LatLong(this.f13056a.southwest.latitude, this.f13056a.southwest.longitude);
    }

    public LatLong e() {
        if (this.f13056a == null) {
            this.f13056a = this.d.build();
        }
        if (this.f13056a == null) {
            return new LatLong();
        }
        return new LatLong(this.f13056a.northeast.latitude, this.f13056a.northeast.longitude);
    }

    public LatLong b() {
        if (this.f13056a == null) {
            this.f13056a = this.d.build();
        }
        if (this.f13056a == null) {
            return new LatLong();
        }
        return new LatLong((this.f13056a.northeast.latitude + this.f13056a.southwest.latitude) / 2.0d, (this.f13056a.northeast.longitude + this.f13056a.southwest.longitude) / 2.0d);
    }

    public double c() {
        return this.c;
    }

    private void e(LatLong latLong) {
        if (latLong == null) {
            return;
        }
        LatLong latLong2 = this.b;
        if (latLong2 == null) {
            this.b = latLong;
        } else {
            this.c += hau.b(latLong2, latLong);
            this.b = latLong;
        }
    }
}
