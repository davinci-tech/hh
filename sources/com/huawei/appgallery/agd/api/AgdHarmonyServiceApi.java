package com.huawei.appgallery.agd.api;

import com.huawei.appmarket.service.externalservice.distribution.opendetail.OpenFADetailRequest;
import com.huawei.appmarket.service.externalservice.distribution.opendetail.OpenFADetailResponse;
import com.huawei.appmarket.service.externalservice.distribution.startability.StartAbilityRequest;
import com.huawei.appmarket.service.externalservice.distribution.startability.StartAbilityResponse;

/* loaded from: classes2.dex */
public class AgdHarmonyServiceApi {
    public static PendingResult<StartAbilityRequest, StartAbilityResponse> startAbility(AgdApiClient agdApiClient, StartAbilityRequest startAbilityRequest) {
        return new PendingResult<>(agdApiClient, startAbilityRequest);
    }

    public static PendingResult<OpenFADetailRequest, OpenFADetailResponse> openDetail(AgdApiClient agdApiClient, OpenFADetailRequest openFADetailRequest) {
        return new PendingResult<>(agdApiClient, openFADetailRequest);
    }
}
