package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.api.OfflineMapApi;
import com.huawei.health.device.callback.DownloadCityCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.init.OfflineMapInitManager;
import com.huawei.maps.offlinedata.health.init.OfflineMapInitOptions;
import com.huawei.maps.offlinedata.health.init.OfflineMapPoint;
import com.huawei.maps.offlinedata.health.init.OnCheckRegionListListener;
import com.huawei.maps.offlinedata.health.init.OnCreateResultListener;
import com.huawei.operation.h5pro.H5proUtil;
import java.util.ArrayList;
import java.util.List;

@ApiDefine(uri = OfflineMapApi.class)
/* loaded from: classes6.dex */
public class oap implements OfflineMapApi {
    static /* synthetic */ void b(boolean z) {
    }

    @Override // com.huawei.health.device.api.OfflineMapApi
    public void initOfflineMap(String str) {
        LogUtil.a("OfflineMapImpl", "initOfflineMap");
        oat.c().d(BaseApplication.e(), str);
    }

    @Override // com.huawei.health.device.api.OfflineMapApi
    public void queryingDownloadCity(List<hjd> list, final DownloadCityCallback downloadCityCallback) {
        if (koq.b(list)) {
            LogUtil.b("OfflineMapImpl", "points is null in queryingDownloadCity");
            return;
        }
        LogUtil.a("OfflineMapImpl", "queryingDownloadCity points size ", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList();
        for (hjd hjdVar : list) {
            OfflineMapPoint offlineMapPoint = new OfflineMapPoint();
            offlineMapPoint.setLat(hjdVar.b);
            offlineMapPoint.setLng(hjdVar.d);
            arrayList.add(offlineMapPoint);
        }
        OfflineMapInitManager.getInstance().getRegionByLatLngs(BaseApplication.e(), arrayList, new OnCheckRegionListListener() { // from class: oap.4
            @Override // com.huawei.maps.offlinedata.health.init.OnCheckRegionListListener
            public void onResult(List<String> list2) {
                LogUtil.a("OfflineMapImpl", "cityList = ", list2);
                DownloadCityCallback downloadCityCallback2 = downloadCityCallback;
                if (downloadCityCallback2 != null) {
                    downloadCityCallback2.onResponse(list2);
                }
            }
        });
    }

    @Override // com.huawei.health.device.api.OfflineMapApi
    public void jumpToOfflineMap(final DeviceInfo deviceInfo, final List<String> list) {
        LogUtil.a("OfflineMapImpl", "jumpToOfflineMap cityIdList ", list);
        if (deviceInfo == null) {
            LogUtil.b("OfflineMapImpl", "deviceInfo is null");
        } else if (koq.b(list)) {
            LogUtil.b("OfflineMapImpl", "cityIdList is empty");
        } else {
            jdx.b(new Runnable() { // from class: oaw
                @Override // java.lang.Runnable
                public final void run() {
                    oap.c(DeviceInfo.this, list);
                }
            });
        }
    }

    static /* synthetic */ void c(DeviceInfo deviceInfo, List list) {
        H5proUtil.initH5pro();
        OfflineMapInitOptions offlineMapInitOptions = new OfflineMapInitOptions();
        offlineMapInitOptions.setContext(BaseApplication.vZ_());
        offlineMapInitOptions.setActivity(BaseApplication.wa_());
        offlineMapInitOptions.setDeviceName(deviceInfo.getDeviceName());
        offlineMapInitOptions.setUniqueDevice(deviceInfo.getDeviceUdid());
        offlineMapInitOptions.setH5proUrl(EnvironmentHelper.getInstance().getUrl());
        offlineMapInitOptions.setRecommendCityIds((String[]) list.toArray(new String[0]));
        if (OfflineMapInitManager.getInstance().createOfflineDataMap(offlineMapInitOptions, new OnCreateResultListener() { // from class: oax
            @Override // com.huawei.maps.offlinedata.health.init.OnCreateResultListener
            public final void onCreateResult(boolean z) {
                oap.b(z);
            }
        })) {
            return;
        }
        sqo.ad("open map page error");
    }
}
