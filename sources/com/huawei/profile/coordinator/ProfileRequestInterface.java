package com.huawei.profile.coordinator;

import android.content.Context;
import com.huawei.profile.coordinator.exception.ProfileRequestException;

/* loaded from: classes6.dex */
public interface ProfileRequestInterface {
    String prepareRequestBody() throws ProfileRequestException;

    String prepareRequestUrl(ProfileRequestInputParams profileRequestInputParams) throws ProfileRequestException;

    void request(Context context, ProfileRequestInputParams profileRequestInputParams, ProfileRequestCallback profileRequestCallback);
}
