package com.huawei.maps.offlinedata.service.cloud;

import android.text.TextUtils;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.handler.dto.persist.WorldMapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.health.init.OfflineMapPoint;
import com.huawei.maps.offlinedata.health.init.OnCheckRegionListListener;
import com.huawei.maps.offlinedata.service.cloud.dto.AuthRequestDTO;
import com.huawei.maps.offlinedata.service.cloud.dto.AuthResponseDTO;
import com.huawei.maps.offlinedata.service.cloud.dto.OfflineMapRequestDto;
import com.huawei.maps.offlinedata.service.cloud.dto.OfflineMapResponseDto;
import com.huawei.maps.offlinedata.utils.f;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.secure.android.common.sign.HiPkgSignManager;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static volatile c f6466a;

    private c() {
    }

    public static c a() {
        if (f6466a == null) {
            synchronized (c.class) {
                if (f6466a == null) {
                    f6466a = new c();
                }
            }
        }
        return f6466a;
    }

    public AuthResponseDTO b() {
        g.a("OfflineMapDataServiceImpl", "authenticate start.");
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        String packageName = com.huawei.maps.offlinedata.utils.a.a().getApplicationContext().getPackageName();
        String a2 = com.huawei.maps.offlinedata.service.cloud.utils.b.a(com.huawei.maps.offlinedata.utils.a.a());
        authRequestDTO.setAppId(a2);
        authRequestDTO.setRequestId(com.huawei.maps.offlinedata.service.cloud.utils.d.a("authenticate", a2));
        authRequestDTO.setPackageName(packageName);
        authRequestDTO.setCertificateFingerprint(HiPkgSignManager.getInstalledAppHash(com.huawei.maps.offlinedata.utils.a.a(), packageName));
        authRequestDTO.setDeviceModel(com.huawei.maps.offlinedata.service.cloud.utils.b.a());
        authRequestDTO.setNetworkCountry(com.huawei.maps.offlinedata.service.cloud.utils.c.d());
        authRequestDTO.setSimCardCountry(com.huawei.maps.offlinedata.service.cloud.utils.c.f());
        authRequestDTO.setDeviceLocaleCountry(com.huawei.maps.offlinedata.service.cloud.utils.c.g());
        authRequestDTO.setVendorCountry(com.huawei.maps.offlinedata.service.cloud.utils.c.h());
        try {
            Response<AuthResponseDTO> execute = ((CloudOfflineServiceApi) com.huawei.maps.offlinedata.network.c.a().a(CloudOfflineServiceApi.class)).authenticate(com.huawei.maps.offlinedata.network.c.a("/open-service/v1/offline-data/authenticate"), com.huawei.maps.offlinedata.network.converter.a.a("application/json; charset=utf-8", com.huawei.maps.offlinedata.utils.d.a(authRequestDTO).getBytes(StandardCharsets.UTF_8))).execute();
            if (execute != null && execute.getBody() != null) {
                try {
                    return (AuthResponseDTO) com.huawei.maps.offlinedata.utils.d.a(com.huawei.maps.offlinedata.utils.d.a(execute.getBody()), AuthResponseDTO.class);
                } catch (Exception e) {
                    g.d("OfflineMapDataServiceImpl", "response body exception:" + e);
                }
            }
            return null;
        } catch (IOException e2) {
            g.d("OfflineMapDataServiceImpl", "getAuthenticate failed. " + e2.getMessage());
            return null;
        }
    }

    public void c() {
        g.a("OfflineMapDataServiceImpl", "loadCloudGlobalList start.");
        OfflineMapRequestDto offlineMapRequestDto = new OfflineMapRequestDto();
        offlineMapRequestDto.setDeviceType(com.huawei.maps.offlinedata.service.config.a.a().f());
        offlineMapRequestDto.setP(a.a().c());
        offlineMapRequestDto.setLanguage(f.a());
        com.huawei.maps.offlinedata.network.c.a().a(((CloudOfflineServiceApi) com.huawei.maps.offlinedata.network.c.a().a(CloudOfflineServiceApi.class)).getWorldMap(com.huawei.maps.offlinedata.network.c.a("/open-service/v1/offline-data/getWorldMap"), com.huawei.maps.offlinedata.network.converter.a.a("application/json; charset=utf-8", com.huawei.maps.offlinedata.utils.d.a(offlineMapRequestDto).getBytes(StandardCharsets.UTF_8))), new com.huawei.maps.offlinedata.network.b<OfflineMapResponseDto>() { // from class: com.huawei.maps.offlinedata.service.cloud.c.1
            @Override // com.huawei.maps.offlinedata.network.b
            public void a(OfflineMapResponseDto offlineMapResponseDto) {
                g.a("OfflineMapDataServiceImpl", "loadCloudGlobalList success.");
                if (offlineMapResponseDto == null) {
                    g.d("OfflineMapDataServiceImpl", "response is null");
                    return;
                }
                WorldMapOfflineDataItemEntity dataItem = offlineMapResponseDto.getDataItem();
                MapOfflineDataItemEntity mapOfflineDataItemEntity = new MapOfflineDataItemEntity();
                mapOfflineDataItemEntity.setId(dataItem.getId());
                mapOfflineDataItemEntity.setDataType("watch_map");
                mapOfflineDataItemEntity.setCountryId(dataItem.getCountryId());
                mapOfflineDataItemEntity.setCountryName(dataItem.getCountryName());
                mapOfflineDataItemEntity.setRegionId(dataItem.getRegionId());
                mapOfflineDataItemEntity.setRegionName(dataItem.getRegionName());
                mapOfflineDataItemEntity.setCityId(dataItem.getCityId());
                mapOfflineDataItemEntity.setCityName(dataItem.getCityName());
                mapOfflineDataItemEntity.setPolitical(dataItem.getPolitical());
                mapOfflineDataItemEntity.setFileId(dataItem.getFileId());
                mapOfflineDataItemEntity.setVersion(dataItem.getVersion());
                mapOfflineDataItemEntity.setPackageSize(dataItem.getPackageSize());
                mapOfflineDataItemEntity.setOriginalSize(dataItem.getOriginalSize());
                mapOfflineDataItemEntity.setFileCheck(dataItem.getFileCheck());
                mapOfflineDataItemEntity.setLastModifiedTime(dataItem.getLastModifiedTime());
                com.huawei.maps.offlinedata.service.persist.b.a().c();
                com.huawei.maps.offlinedata.service.persist.b.a().a(dataItem);
                b.a().a(mapOfflineDataItemEntity);
            }

            @Override // com.huawei.maps.offlinedata.network.b
            public void a(int i, com.huawei.maps.offlinedata.network.d dVar, String str) {
                g.d("OfflineMapDataServiceImpl", "loadCloudGlobalList failed, code:" + i + ", subCode:" + dVar.getReturnCode() + "," + dVar.getReturnDesc());
                b.a().a((MapOfflineDataItemEntity) null);
            }
        });
    }

    public void a(final String str) {
        g.a("OfflineMapDataServiceImpl", str + " loadCloudReginList start.");
        OfflineMapRequestDto offlineMapRequestDto = new OfflineMapRequestDto();
        offlineMapRequestDto.setDeviceType(com.huawei.maps.offlinedata.service.config.a.a().f());
        offlineMapRequestDto.setP(a.a().c());
        offlineMapRequestDto.setLanguage(f.a());
        offlineMapRequestDto.setDataType(str);
        com.huawei.maps.offlinedata.network.c.a().a(((CloudOfflineServiceApi) com.huawei.maps.offlinedata.network.c.a().a(CloudOfflineServiceApi.class)).getCountryMapList(com.huawei.maps.offlinedata.network.c.a("/open-service/v1/offline-data/getCountryMapList"), com.huawei.maps.offlinedata.network.converter.a.a("application/json; charset=utf-8", com.huawei.maps.offlinedata.utils.d.a(offlineMapRequestDto).getBytes(StandardCharsets.UTF_8))), new com.huawei.maps.offlinedata.network.b<OfflineMapResponseDto>() { // from class: com.huawei.maps.offlinedata.service.cloud.c.2
            @Override // com.huawei.maps.offlinedata.network.b
            public void a(OfflineMapResponseDto offlineMapResponseDto) {
                g.a("OfflineMapDataServiceImpl", str + " loadCloudReginList success.");
                List<MapOfflineDataItemEntity> dataItemList = offlineMapResponseDto.getDataItemList();
                ArrayList arrayList = new ArrayList();
                for (MapOfflineDataItemEntity mapOfflineDataItemEntity : dataItemList) {
                    if (!TextUtils.isEmpty(mapOfflineDataItemEntity.getCountryId())) {
                        arrayList.add(mapOfflineDataItemEntity.getCountryId());
                    }
                }
                g.a("OfflineMapDataServiceImpl", str + " all country id list size: " + arrayList.size());
                if (arrayList.isEmpty()) {
                    if ("watch_map".equals(str)) {
                        b.a().a(new ArrayList());
                        return;
                    } else {
                        if ("watch_map_contour".equals(str)) {
                            b.a().b(new ArrayList());
                            return;
                        }
                        return;
                    }
                }
                c.this.a(arrayList, str);
            }

            @Override // com.huawei.maps.offlinedata.network.b
            public void a(int i, com.huawei.maps.offlinedata.network.d dVar, String str2) {
                g.d("OfflineMapDataServiceImpl", "loadCloudReginList failed. code:" + i + ", subCode:" + dVar.getReturnCode() + ", " + dVar.getReturnDesc());
                if ("watch_map".equals(str)) {
                    b.a().a(new ArrayList());
                } else if ("watch_map_contour".equals(str)) {
                    b.a().b(new ArrayList());
                }
            }
        });
    }

    public void a(List<String> list, final String str) {
        g.a("OfflineMapDataServiceImpl", str + " getOfflineRegionList start.");
        final List<MapOfflineDataItemEntity> b = b(list, str);
        g.a("OfflineMapDataServiceImpl", str + " getOfflineRegionList total size: " + b.size());
        com.huawei.maps.offlinedata.service.persist.b.a().a(str, b, new Runnable() { // from class: com.huawei.maps.offlinedata.service.cloud.c$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                c.a(str, b);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(String str, List list) {
        if ("watch_map".equals(str)) {
            b.a().a((List<MapOfflineDataItemEntity>) list);
        } else if ("watch_map_contour".equals(str)) {
            b.a().b(list);
        }
    }

    private List<MapOfflineDataItemEntity> b(List<String> list, String str) {
        OfflineMapRequestDto offlineMapRequestDto = new OfflineMapRequestDto();
        offlineMapRequestDto.setDeviceType(com.huawei.maps.offlinedata.service.config.a.a().f());
        offlineMapRequestDto.setP(a.a().c());
        offlineMapRequestDto.setLanguage(f.a());
        offlineMapRequestDto.setCountryIds(TextUtils.join(",", list.toArray()));
        offlineMapRequestDto.setDataType(str);
        try {
            Response<OfflineMapResponseDto> execute = ((CloudOfflineServiceApi) com.huawei.maps.offlinedata.network.c.a().a(CloudOfflineServiceApi.class)).getRegionMapList(com.huawei.maps.offlinedata.network.c.a("/open-service/v1/offline-data/getRegionMapList"), com.huawei.maps.offlinedata.network.converter.a.a("application/json; charset=utf-8", com.huawei.maps.offlinedata.utils.d.a(offlineMapRequestDto).getBytes(StandardCharsets.UTF_8))).execute();
            if (execute != null && execute.getBody() != null && execute.getBody().getDataItemList() != null && !execute.getBody().getDataItemList().isEmpty()) {
                List<MapOfflineDataItemEntity> dataItemList = execute.getBody().getDataItemList();
                Iterator<MapOfflineDataItemEntity> it = dataItemList.iterator();
                while (it.hasNext()) {
                    it.next().setDataType(str);
                }
                return dataItemList;
            }
        } catch (IOException e) {
            g.d("OfflineMapDataServiceImpl", "getRegionMapList failed. " + e.getMessage());
        }
        return new ArrayList();
    }

    public String b(String str) {
        g.a("OfflineMapDataServiceImpl", "getDownloadUrl start.");
        OfflineMapRequestDto offlineMapRequestDto = new OfflineMapRequestDto();
        offlineMapRequestDto.setFileId(str);
        try {
            Response<OfflineMapResponseDto> execute = ((CloudOfflineServiceApi) com.huawei.maps.offlinedata.network.c.a().a(CloudOfflineServiceApi.class)).getDownloadUrl(com.huawei.maps.offlinedata.network.c.a("/open-service/v1/offline-data/getDownloadUrl"), com.huawei.maps.offlinedata.network.converter.a.a("application/json; charset=utf-8", com.huawei.maps.offlinedata.utils.d.a(offlineMapRequestDto).getBytes(StandardCharsets.UTF_8))).execute();
            return (execute == null || execute.getBody() == null) ? "" : execute.getBody().getUrl();
        } catch (IOException e) {
            g.d("OfflineMapDataServiceImpl", "getDownloadUrl failed. " + e.getMessage());
            return "";
        }
    }

    public void a(List<OfflineMapPoint> list, OnCheckRegionListListener onCheckRegionListListener) {
        g.a("OfflineMapDataServiceImpl", "getRegionByLatLngs start.");
        OfflineMapRequestDto offlineMapRequestDto = new OfflineMapRequestDto();
        offlineMapRequestDto.setP(a.a().c());
        offlineMapRequestDto.setPoints(list);
        try {
            Response<OfflineMapResponseDto> execute = ((CloudOfflineServiceApi) com.huawei.maps.offlinedata.network.c.a().a(CloudOfflineServiceApi.class)).getRegionsByLatLngs(com.huawei.maps.offlinedata.network.c.a("/open-service/v1/offline-data/getRegionsByLatLngs"), com.huawei.maps.offlinedata.network.converter.a.a("application/json; charset=utf-8", com.huawei.maps.offlinedata.utils.d.a(offlineMapRequestDto).getBytes(StandardCharsets.UTF_8))).execute();
            if (execute != null && execute.getBody() != null) {
                com.huawei.maps.offlinedata.service.device.b.a().a(execute.getBody().getRegions(), onCheckRegionListListener);
            } else {
                onCheckRegionListListener.onResult(new ArrayList());
                g.a("OfflineMapDataServiceImpl", "getRegionByLatLngs response is null");
            }
        } catch (IOException e) {
            onCheckRegionListListener.onResult(new ArrayList());
            g.d("OfflineMapDataServiceImpl", "getRegionByLatLngs failed. " + e.getMessage());
        }
    }
}
