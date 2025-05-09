package com.amap.api.services.weather;

import com.amap.api.col.p0003sl.fd;

/* loaded from: classes8.dex */
public class WeatherSearchQuery implements Cloneable {
    public static final int WEATHER_TYPE_FORECAST = 2;
    public static final int WEATHER_TYPE_LIVE = 1;

    /* renamed from: a, reason: collision with root package name */
    private String f1605a;
    private int b;

    public WeatherSearchQuery(String str, int i) {
        this.f1605a = str;
        this.b = i;
    }

    public WeatherSearchQuery() {
        this.b = 1;
    }

    public String getCity() {
        return this.f1605a;
    }

    public int getType() {
        return this.b;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public WeatherSearchQuery m108clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            fd.a(e, "WeatherSearchQuery", "clone");
        }
        return new WeatherSearchQuery(this.f1605a, this.b);
    }
}
