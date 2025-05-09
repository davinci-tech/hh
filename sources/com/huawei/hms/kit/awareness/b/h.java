package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.WeatherStatus;
import com.huawei.hms.kit.awareness.status.weather.Aqi;
import com.huawei.hms.kit.awareness.status.weather.DailyWeather;
import com.huawei.hms.kit.awareness.status.weather.HourlyWeather;
import com.huawei.hms.kit.awareness.status.weather.LiveInfo;
import com.huawei.hms.kit.awareness.status.weather.WeatherSituation;
import java.util.List;

/* loaded from: classes9.dex */
public class h implements Parcelable, WeatherStatus {
    public static final Parcelable.Creator<h> CREATOR = new Parcelable.Creator<h>() { // from class: com.huawei.hms.kit.awareness.b.h.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public h[] newArray(int i) {
            return new h[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public h createFromParcel(Parcel parcel) {
            return new h(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final com.huawei.hms.kit.awareness.barrier.internal.b.i f4838a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f4838a, i);
    }

    @Override // com.huawei.hms.kit.awareness.status.WeatherStatus
    public WeatherSituation getWeatherSituation() {
        return new WeatherSituation(this.f4838a.a(), this.f4838a.e());
    }

    @Override // com.huawei.hms.kit.awareness.status.WeatherStatus
    public List<LiveInfo> getLiveInfo() {
        return this.f4838a.h();
    }

    @Override // com.huawei.hms.kit.awareness.status.WeatherStatus
    public List<HourlyWeather> getHourlyWeather() {
        return this.f4838a.g();
    }

    @Override // com.huawei.hms.kit.awareness.status.WeatherStatus
    public List<DailyWeather> getDailyWeather() {
        return this.f4838a.f();
    }

    @Override // com.huawei.hms.kit.awareness.status.WeatherStatus
    public Aqi getAqi() {
        return this.f4838a.b();
    }

    public h(com.huawei.hms.kit.awareness.barrier.internal.b.i iVar) {
        this.f4838a = iVar;
    }

    private h(Parcel parcel) {
        this.f4838a = (com.huawei.hms.kit.awareness.barrier.internal.b.i) parcel.readParcelable(com.huawei.hms.kit.awareness.barrier.internal.b.i.class.getClassLoader());
    }
}
