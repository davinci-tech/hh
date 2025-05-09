package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.d;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.inner.LocationSwitches;
import com.huawei.openalliance.ad.utils.bm;

/* loaded from: classes5.dex */
public class Location {
    private int clctSource;
    private Long clctTime;
    private Integer lastfix;

    @d(a = "lat")
    @a
    private Double latitude;

    @f
    private LocationSwitches locationSwitches;

    @d(a = "lon")
    @a
    private Double longitude;

    public boolean h() {
        return (this.longitude == null || this.latitude == null) ? false : true;
    }

    public int g() {
        return this.clctSource;
    }

    public LocationSwitches f() {
        return this.locationSwitches;
    }

    public Integer e() {
        return this.lastfix;
    }

    public Long d() {
        return this.clctTime;
    }

    public Double c() {
        return this.latitude;
    }

    public void b(Double d) {
        this.latitude = bm.a(d, 4, 4);
    }

    public Double b() {
        return this.longitude;
    }

    public void a(Long l) {
        this.clctTime = l;
    }

    public void a(Double d) {
        this.longitude = bm.a(d, 4, 4);
    }

    public void a(LocationSwitches locationSwitches) {
        this.locationSwitches = locationSwitches;
    }

    public void a(long j) {
        Long l = this.clctTime;
        if (l == null || l.longValue() >= j) {
            return;
        }
        this.lastfix = Integer.valueOf(Math.round((j - this.clctTime.longValue()) / 1000.0f));
    }

    public void a(int i) {
        this.clctSource = i;
    }

    public Location a() {
        Location location = new Location();
        location.longitude = this.longitude;
        location.latitude = this.latitude;
        location.lastfix = this.lastfix;
        location.clctTime = this.clctTime;
        location.clctSource = this.clctSource;
        return location;
    }

    public Location(Double d, Double d2) {
        this.longitude = bm.a(d, 4, 4);
        this.latitude = bm.a(d2, 4, 4);
    }

    public Location() {
    }
}
