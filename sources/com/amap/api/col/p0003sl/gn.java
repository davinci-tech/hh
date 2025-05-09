package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.WeatherSearchQuery;

/* loaded from: classes8.dex */
public final class gn extends go<WeatherSearchQuery, LocalWeatherLive> {
    private LocalWeatherLive k;

    @Override // com.amap.api.col.p0003sl.go, com.amap.api.col.p0003sl.ka
    public final /* bridge */ /* synthetic */ String getURL() {
        return super.getURL();
    }

    public gn(Context context, WeatherSearchQuery weatherSearchQuery) {
        super(context, weatherSearchQuery);
        this.k = new LocalWeatherLive();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("output=json");
        String city = ((WeatherSearchQuery) this.b).getCity();
        if (!fl.g(city)) {
            stringBuffer.append("&city=").append(b(city));
        }
        stringBuffer.append("&extensions=base");
        stringBuffer.append("&key=" + hn.f(this.i));
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public LocalWeatherLive a(String str) throws AMapException {
        LocalWeatherLive e = fl.e(str);
        this.k = e;
        return e;
    }
}
