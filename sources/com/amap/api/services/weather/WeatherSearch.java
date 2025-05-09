package com.amap.api.services.weather;

import android.content.Context;
import com.amap.api.col.p0003sl.hd;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IWeatherSearch;

/* loaded from: classes2.dex */
public class WeatherSearch {

    /* renamed from: a, reason: collision with root package name */
    private IWeatherSearch f1604a;

    public interface OnWeatherSearchListener {
        void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i);

        void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i);
    }

    public WeatherSearch(Context context) throws AMapException {
        this.f1604a = null;
        try {
            this.f1604a = new hd(context);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof AMapException) {
                throw ((AMapException) e);
            }
        }
    }

    public WeatherSearchQuery getQuery() {
        IWeatherSearch iWeatherSearch = this.f1604a;
        if (iWeatherSearch != null) {
            return iWeatherSearch.getQuery();
        }
        return null;
    }

    public void setQuery(WeatherSearchQuery weatherSearchQuery) {
        IWeatherSearch iWeatherSearch = this.f1604a;
        if (iWeatherSearch != null) {
            iWeatherSearch.setQuery(weatherSearchQuery);
        }
    }

    public void searchWeatherAsyn() {
        IWeatherSearch iWeatherSearch = this.f1604a;
        if (iWeatherSearch != null) {
            iWeatherSearch.searchWeatherAsyn();
        }
    }

    public void setOnWeatherSearchListener(OnWeatherSearchListener onWeatherSearchListener) {
        IWeatherSearch iWeatherSearch = this.f1604a;
        if (iWeatherSearch != null) {
            iWeatherSearch.setOnWeatherSearchListener(onWeatherSearchListener);
        }
    }
}
