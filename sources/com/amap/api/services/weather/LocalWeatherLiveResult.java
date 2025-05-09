package com.amap.api.services.weather;

/* loaded from: classes2.dex */
public class LocalWeatherLiveResult {

    /* renamed from: a, reason: collision with root package name */
    private WeatherSearchQuery f1603a;
    private LocalWeatherLive b;

    public static LocalWeatherLiveResult createPagedResult(WeatherSearchQuery weatherSearchQuery, LocalWeatherLive localWeatherLive) {
        return new LocalWeatherLiveResult(weatherSearchQuery, localWeatherLive);
    }

    private LocalWeatherLiveResult(WeatherSearchQuery weatherSearchQuery, LocalWeatherLive localWeatherLive) {
        this.f1603a = weatherSearchQuery;
        this.b = localWeatherLive;
    }

    public WeatherSearchQuery getWeatherLiveQuery() {
        return this.f1603a;
    }

    public LocalWeatherLive getLiveResult() {
        return this.b;
    }
}
