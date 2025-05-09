package com.huawei.hwcloudmodel.model.wjx;

import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class SurveyIdRequest implements IRequest {
    private static final String SURVEY_PATH = "/healthExpansion/survey/obtain";

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + SURVEY_PATH;
    }
}
