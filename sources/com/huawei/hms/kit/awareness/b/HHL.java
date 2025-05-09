package com.huawei.hms.kit.awareness.b;

import com.huawei.hms.kit.awareness.a.a.r;
import com.huawei.hms.kit.awareness.a.a.s;
import com.huawei.hms.kit.awareness.a.a.t;
import com.huawei.hms.kit.awareness.a.a.u;
import com.huawei.hms.kit.awareness.barrier.BarrierQueryResponse;
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
import com.huawei.hms.kit.awareness.capture.WeatherStatusResponse;
import com.huawei.hms.kit.awareness.capture.WifiStatusResponse;
import com.huawei.hms.kit.awareness.capture.internal.SidInternalResult;
import com.huawei.hms.kit.awareness.donate.DonateResponse;
import com.huawei.hms.kit.awareness.donate.ServiceOpenIdResponse;

/* loaded from: classes4.dex */
final class HHL {
    static ServiceOpenIdResponse a(SidInternalResult sidInternalResult) {
        ServiceOpenIdResponse serviceOpenIdResponse = new ServiceOpenIdResponse(sidInternalResult.getSidStatus());
        serviceOpenIdResponse.checkStatus(sidInternalResult.c());
        return serviceOpenIdResponse;
    }

    static DonateResponse a(u uVar) {
        DonateResponse donateResponse = new DonateResponse(uVar.a());
        donateResponse.checkStatus(uVar.c());
        return donateResponse;
    }

    static WifiStatusResponse a(t tVar) {
        WifiStatusResponse wifiStatusResponse = new WifiStatusResponse(tVar.a());
        wifiStatusResponse.checkStatus(tVar.c());
        return wifiStatusResponse;
    }

    static WeatherStatusResponse a(com.huawei.hms.kit.awareness.a.a.j jVar) {
        WeatherStatusResponse weatherStatusResponse = new WeatherStatusResponse(jVar.a());
        weatherStatusResponse.checkStatus(jVar.c());
        return weatherStatusResponse;
    }

    static TimeCategoriesResponse a(com.huawei.hms.kit.awareness.a.a.i iVar) {
        TimeCategoriesResponse timeCategoriesResponse = new TimeCategoriesResponse(iVar.a());
        timeCategoriesResponse.checkStatus(iVar.c());
        return timeCategoriesResponse;
    }

    static ScreenStatusResponse a(s sVar) {
        ScreenStatusResponse screenStatusResponse = new ScreenStatusResponse(sVar.a());
        screenStatusResponse.checkStatus(sVar.c());
        return screenStatusResponse;
    }

    static LocationResponse a(com.huawei.hms.kit.awareness.a.a.g gVar) {
        LocationResponse locationResponse = new LocationResponse(gVar.a());
        locationResponse.checkStatus(gVar.c());
        return locationResponse;
    }

    static HeadsetStatusResponse a(com.huawei.hms.kit.awareness.a.a.e eVar) {
        HeadsetStatusResponse headsetStatusResponse = new HeadsetStatusResponse(eVar.a());
        headsetStatusResponse.checkStatus(eVar.c());
        return headsetStatusResponse;
    }

    static DarkModeStatusResponse a(r rVar) {
        DarkModeStatusResponse darkModeStatusResponse = new DarkModeStatusResponse(rVar.a());
        darkModeStatusResponse.checkStatus(rVar.c());
        return darkModeStatusResponse;
    }

    static CapabilityResponse a(com.huawei.hms.kit.awareness.a.a.n nVar) {
        CapabilityResponse capabilityResponse = new CapabilityResponse(nVar.a());
        capabilityResponse.checkStatus(nVar.c());
        return capabilityResponse;
    }

    static BluetoothStatusResponse a(com.huawei.hms.kit.awareness.a.a.l lVar) {
        BluetoothStatusResponse bluetoothStatusResponse = new BluetoothStatusResponse(lVar.a());
        bluetoothStatusResponse.checkStatus(lVar.c());
        return bluetoothStatusResponse;
    }

    static BehaviorResponse a(com.huawei.hms.kit.awareness.a.a.d dVar) {
        BehaviorResponse behaviorResponse = new BehaviorResponse(dVar.a());
        behaviorResponse.checkStatus(dVar.c());
        return behaviorResponse;
    }

    static BeaconStatusResponse a(com.huawei.hms.kit.awareness.a.a.c cVar) {
        BeaconStatusResponse beaconStatusResponse = new BeaconStatusResponse(cVar.a());
        beaconStatusResponse.checkStatus(cVar.c());
        return beaconStatusResponse;
    }

    static ApplicationStatusResponse a(com.huawei.hms.kit.awareness.a.a.q qVar) {
        ApplicationStatusResponse applicationStatusResponse = new ApplicationStatusResponse(qVar.a());
        applicationStatusResponse.checkStatus(qVar.c());
        return applicationStatusResponse;
    }

    static AmbientLightResponse a(com.huawei.hms.kit.awareness.a.a.a aVar) {
        AmbientLightResponse ambientLightResponse = new AmbientLightResponse(aVar.a());
        ambientLightResponse.checkStatus(aVar.c());
        return ambientLightResponse;
    }

    static BarrierQueryResponse a(com.huawei.hms.kit.awareness.barrier.internal.b bVar) {
        BarrierQueryResponse barrierQueryResponse = new BarrierQueryResponse(bVar.b());
        barrierQueryResponse.checkStatus(bVar.c());
        return barrierQueryResponse;
    }

    private HHL() {
    }
}
