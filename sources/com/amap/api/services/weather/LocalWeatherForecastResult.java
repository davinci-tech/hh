package com.amap.api.services.weather;

/* loaded from: classes2.dex */
public class LocalWeatherForecastResult {

    /* renamed from: a, reason: collision with root package name */
    private WeatherSearchQuery f1601a;
    private LocalWeatherForecast b;

    public static LocalWeatherForecastResult createPagedResult(WeatherSearchQuery weatherSearchQuery, LocalWeatherForecast localWeatherForecast) {
        return new LocalWeatherForecastResult(weatherSearchQuery, localWeatherForecast);
    }

    private LocalWeatherForecastResult(WeatherSearchQuery weatherSearchQuery, LocalWeatherForecast localWeatherForecast) {
        this.f1601a = weatherSearchQuery;
        this.b = localWeatherForecast;
    }

    public WeatherSearchQuery getWeatherForecastQuery() {
        return this.f1601a;
    }

    public LocalWeatherForecast getForecastResult() {
        return this.b;
    }
}
