package com.huawei.hms.kit.awareness.capture;

import com.huawei.hms.kit.awareness.b.HHI;
import com.huawei.hms.kit.awareness.status.WeatherStatus;

/* loaded from: classes9.dex */
public class WeatherStatusResponse extends HHI<WeatherStatus> {
    public WeatherStatus getWeatherStatus() {
        return getStatus();
    }

    @Override // com.huawei.hms.kit.awareness.b.HHI
    public String getErrorMsg() {
        return "getWeatherStatus failed: ";
    }

    public WeatherStatusResponse(WeatherStatus weatherStatus) {
        super(weatherStatus);
    }
}
