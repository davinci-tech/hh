package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;

/* loaded from: classes8.dex */
public class SettingsClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest locationSettingsRequest) {
        return PendingResultUtil.toResponseTask(LocationServices.SettingsApi.checkLocationSettings(asGoogleApiClient(), locationSettingsRequest), new LocationSettingsResponse());
    }

    public SettingsClient(Context context) {
        super(context, LocationServices.API, (Api.ApiOptions) null, new ApiExceptionMapper());
    }

    public SettingsClient(Activity activity) {
        super(activity, (Api<Api.ApiOptions>) LocationServices.API, (Api.ApiOptions) null, (StatusExceptionMapper) new ApiExceptionMapper());
    }
}
