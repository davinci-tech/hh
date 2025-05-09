package com.huawei.hwcloudmodel.healthdatacloud;

import android.content.Context;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Map;

/* loaded from: classes5.dex */
public class HealthDataCloudGzipFactory extends HealthDataCloudFactory {
    public HealthDataCloudGzipFactory(Context context) {
        super(context);
    }

    @Override // com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory, com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        Map<String, String> headers = super.getHeaders();
        headers.put("Content-Encoding", Constants.GZIP);
        return headers;
    }
}
