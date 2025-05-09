package com.huawei.health.marketing.request;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class QuestionOptionReq implements IRequest {
    private static final String TAG = "QuestionOptionReq";
    private String optionContent;
    private String optionId;
    private String resourceId;
    private String theme;
    private int themeId;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl");
        if (TextUtils.isEmpty(url)) {
            LogUtil.h(TAG, "host is empty");
            return "";
        }
        return url + "/dataRecommend/v1/questionnaire/postUserQuestionnaireResults";
    }

    public void setResourceId(String str) {
        this.resourceId = str;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public void setOptionContent(String str) {
        this.optionContent = str;
    }

    public void setThemeId(int i) {
        this.themeId = i;
    }

    public void setOptionId(String str) {
        this.optionId = str;
    }
}
