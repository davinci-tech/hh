package com.huawei.maps.offlinedata.service.cloud;

import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.anno.Body;
import com.huawei.hms.network.restclient.anno.POST;
import com.huawei.hms.network.restclient.anno.Url;
import com.huawei.maps.offlinedata.service.cloud.dto.AuthResponseDTO;
import com.huawei.maps.offlinedata.service.cloud.dto.OfflineMapResponseDto;

/* loaded from: classes5.dex */
public interface CloudOfflineServiceApi {
    @POST
    Submit<AuthResponseDTO> authenticate(@Url String str, @Body RequestBody requestBody);

    @POST
    Submit<OfflineMapResponseDto> getCountryMapList(@Url String str, @Body RequestBody requestBody);

    @POST
    Submit<OfflineMapResponseDto> getDownloadUrl(@Url String str, @Body RequestBody requestBody);

    @POST
    Submit<OfflineMapResponseDto> getRegionMapList(@Url String str, @Body RequestBody requestBody);

    @POST
    Submit<OfflineMapResponseDto> getRegionsByLatLngs(@Url String str, @Body RequestBody requestBody);

    @POST
    Submit<OfflineMapResponseDto> getWorldMap(@Url String str, @Body RequestBody requestBody);
}
