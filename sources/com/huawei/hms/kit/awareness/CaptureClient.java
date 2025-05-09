package com.huawei.hms.kit.awareness;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.kit.awareness.capture.AmbientLightResponse;
import com.huawei.hms.kit.awareness.capture.ApplicationStatusResponse;
import com.huawei.hms.kit.awareness.capture.BeaconStatusResponse;
import com.huawei.hms.kit.awareness.capture.BehaviorResponse;
import com.huawei.hms.kit.awareness.capture.BluetoothStatusResponse;
import com.huawei.hms.kit.awareness.capture.CapabilityResponse;
import com.huawei.hms.kit.awareness.capture.DarkModeStatusResponse;
import com.huawei.hms.kit.awareness.capture.HeadsetStatusResponse;
import com.huawei.hms.kit.awareness.capture.LocationResponse;
import com.huawei.hms.kit.awareness.capture.ScreenStatusResponse;
import com.huawei.hms.kit.awareness.capture.TimeCategoriesResponse;
import com.huawei.hms.kit.awareness.capture.WeatherPosition;
import com.huawei.hms.kit.awareness.capture.WeatherStatusResponse;
import com.huawei.hms.kit.awareness.capture.WifiStatusResponse;
import com.huawei.hms.kit.awareness.status.BeaconStatus;
import java.util.Collection;

/* loaded from: classes4.dex */
public interface CaptureClient extends Client {
    Task<ApplicationStatusResponse> getApplicationStatus(String str);

    Task<BeaconStatusResponse> getBeaconStatus(Collection<BeaconStatus.Filter> collection);

    Task<BeaconStatusResponse> getBeaconStatus(BeaconStatus.Filter... filterArr);

    Task<BehaviorResponse> getBehavior();

    Task<BluetoothStatusResponse> getBluetoothStatus(int i);

    Task<LocationResponse> getCurrentLocation();

    Task<DarkModeStatusResponse> getDarkModeStatus();

    Task<HeadsetStatusResponse> getHeadsetStatus();

    Task<AmbientLightResponse> getLightIntensity();

    Task<LocationResponse> getLocation();

    Task<ScreenStatusResponse> getScreenStatus();

    Task<TimeCategoriesResponse> getTimeCategories();

    Task<TimeCategoriesResponse> getTimeCategoriesByCountryCode(String str);

    Task<TimeCategoriesResponse> getTimeCategoriesByIP();

    Task<TimeCategoriesResponse> getTimeCategoriesByUser(double d, double d2);

    Task<TimeCategoriesResponse> getTimeCategoriesForFuture(long j);

    Task<WeatherStatusResponse> getWeatherByDevice();

    @Deprecated
    Task<WeatherStatusResponse> getWeatherByDevice(String str);

    @Deprecated
    Task<WeatherStatusResponse> getWeatherByIP();

    Task<WeatherStatusResponse> getWeatherByPosition(WeatherPosition weatherPosition);

    Task<WifiStatusResponse> getWifiStatus();

    Task<CapabilityResponse> querySupportingCapabilities();
}
