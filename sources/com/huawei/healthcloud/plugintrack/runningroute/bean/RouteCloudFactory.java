package com.huawei.healthcloud.plugintrack.runningroute.bean;

import android.content.Context;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import defpackage.bed;
import java.util.Map;

/* loaded from: classes4.dex */
public class RouteCloudFactory extends HealthDataCloudFactory {
    public RouteCloudFactory(Context context) {
        super(context);
    }

    @Override // com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory, com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> body = super.getBody(obj);
        body.put("language", bed.b());
        return body;
    }
}
