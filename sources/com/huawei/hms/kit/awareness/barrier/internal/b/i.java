package com.huawei.hms.kit.awareness.barrier.internal.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.weather.Aqi;
import com.huawei.hms.kit.awareness.status.weather.City;
import com.huawei.hms.kit.awareness.status.weather.DailyWeather;
import com.huawei.hms.kit.awareness.status.weather.HourlyWeather;
import com.huawei.hms.kit.awareness.status.weather.LiveInfo;
import com.huawei.hms.kit.awareness.status.weather.Situation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class i extends f implements Parcelable {
    public static final Parcelable.Creator<i> CREATOR = new Parcelable.Creator<i>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.b.i.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i[] newArray(int i) {
            return new i[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i createFromParcel(Parcel parcel) {
            return new i(parcel);
        }
    };
    private long b;
    private City c;
    private Aqi d;
    private Situation e;
    private List<DailyWeather> f;
    private List<HourlyWeather> g;
    private List<LiveInfo> h;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeParcelable(this.d, i);
        parcel.writeParcelable(this.e, i);
        parcel.writeTypedList(this.f);
        parcel.writeTypedList(this.g);
        parcel.writeTypedList(this.h);
    }

    public List<LiveInfo> h() {
        return com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.h) ? new ArrayList() : Collections.unmodifiableList(this.h);
    }

    public List<HourlyWeather> g() {
        return com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.g) ? new ArrayList() : Collections.unmodifiableList(this.g);
    }

    public List<DailyWeather> f() {
        return com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.f) ? new ArrayList() : Collections.unmodifiableList(this.f);
    }

    public Situation e() {
        return this.e;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public com.huawei.hms.kit.awareness.barrier.internal.type.f d() {
        return com.huawei.hms.kit.awareness.barrier.internal.type.f.WEATHER;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public boolean c() {
        if (h.k() - this.b >= 3600000) {
            return false;
        }
        return (com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.c) && com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.e) && com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.d) && com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.f) && com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.g) && com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.h)) ? false : true;
    }

    public void c(List<LiveInfo> list) {
        this.h = list;
    }

    public void b(List<HourlyWeather> list) {
        this.g = list;
    }

    public Aqi b() {
        return this.d;
    }

    public void a(List<DailyWeather> list) {
        this.f = list;
    }

    public void a(Situation situation) {
        this.e = situation;
    }

    public void a(City city) {
        this.c = city;
    }

    public void a(Aqi aqi) {
        this.d = aqi;
    }

    public void a(long j) {
        this.b = j;
    }

    public City a() {
        return this.c;
    }

    private i(Parcel parcel) {
        this.c = (City) parcel.readParcelable(City.class.getClassLoader());
        this.d = (Aqi) parcel.readParcelable(Aqi.class.getClassLoader());
        this.e = (Situation) parcel.readParcelable(Situation.class.getClassLoader());
        this.f = parcel.createTypedArrayList(DailyWeather.CREATOR);
        this.g = parcel.createTypedArrayList(HourlyWeather.CREATOR);
        this.h = parcel.createTypedArrayList(LiveInfo.CREATOR);
    }

    public i() {
    }
}
