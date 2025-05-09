package com.huawei.hms.opendevice;

import android.content.Context;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.support.api.entity.opendevice.HuaweiOpendeviceNaming;
import com.huawei.hms.support.api.opendevice.OdidResult;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes9.dex */
public class OpenDeviceClientImpl extends HuaweiApi<OpenDeviceOptions> implements OpenDeviceClient {

    /* renamed from: a, reason: collision with root package name */
    private static final OpenDeviceHmsClientBuilder f5649a = new OpenDeviceHmsClientBuilder();
    private static final Api<OpenDeviceOptions> b = new Api<>(HuaweiApiAvailability.HMS_API_NAME_OD);
    private static OpenDeviceOptions c = new OpenDeviceOptions();

    OpenDeviceClientImpl(Context context) {
        super(context, b, c, f5649a);
        super.setKitSdkVersion(61200300);
    }

    @Override // com.huawei.hms.opendevice.OpenDeviceClient
    public Task<OdidResult> getOdid() {
        return doWrite(new OpenDeviceTaskApiCall(HuaweiOpendeviceNaming.GET_ODID, JsonUtil.createJsonString(null), HiAnalyticsClient.reportEntry(getContext(), HuaweiOpendeviceNaming.GET_ODID, 61200300)));
    }
}
