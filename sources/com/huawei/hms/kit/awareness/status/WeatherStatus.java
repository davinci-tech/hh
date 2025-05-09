package com.huawei.hms.kit.awareness.status;

import com.huawei.hms.kit.awareness.status.weather.Aqi;
import com.huawei.hms.kit.awareness.status.weather.DailyWeather;
import com.huawei.hms.kit.awareness.status.weather.HourlyWeather;
import com.huawei.hms.kit.awareness.status.weather.LiveInfo;
import com.huawei.hms.kit.awareness.status.weather.WeatherSituation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* loaded from: classes9.dex */
public interface WeatherStatus {
    public static final String BE_SUSCEPTIBLE_COLD = "3";
    public static final String CAR_WASH_INDEX = "4";
    public static final String CLOTHING_INDEX = "1";
    public static final String COLD_INDEX = "3";
    public static final String DOWN_JACKET = "1";
    public static final String EASIER_CATCH_COLD = "2";
    public static final String EXTREMELY_SUSCEPTIBLE_COLD = "4";
    public static final String HEAVY_COAT = "2";
    public static final String LONG_SLEEVES = "5";
    public static final String MEDIUM = "3";
    public static final String MORE_SUITABLE = "3";
    public static final String MORE_SUITABLE_SPORT = "2";
    public static final String NOT_EASY_CATCH_COLD = "1";
    public static final String NOT_SUITABLE_SPORT = "3";
    public static final String NOT_VERY_SUITABLE = "2";
    public static final String SHORT_SLEEVE = "6";
    public static final String SPORT_INDEX = "2";
    public static final String STRONG = "4";
    public static final String SUITABLE = "4";
    public static final String SUITABLE_SPORT = "1";
    public static final String SWEATER = "3";
    public static final String THIN_COAT = "4";
    public static final String THIN_SHORT_SLEEVE = "7";
    public static final String TOURISM_INDEX = "5";
    public static final String UNSUITABLE = "1";
    public static final String UV_INDEX = "6";
    public static final String VERY_STRONG = "5";
    public static final String WEAK = "2";
    public static final String WEAKEST = "1";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface CarWashIndex {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ClothingIndex {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ColdIndex {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface LiveCodeDef {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface SportIndex {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TourismIndex {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface UVIndex {
    }

    Aqi getAqi();

    List<DailyWeather> getDailyWeather();

    List<HourlyWeather> getHourlyWeather();

    List<LiveInfo> getLiveInfo();

    WeatherSituation getWeatherSituation();
}
