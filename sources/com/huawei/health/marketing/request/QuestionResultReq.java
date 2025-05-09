package com.huawei.health.marketing.request;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class QuestionResultReq implements IRequest {
    private static final String TAG = "QuestionResultReq";
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
        StringBuilder sb = new StringBuilder(url);
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            sb.append("/dataRecommend/v1/questionnaire/getQuestionnaireResultsByVisitor");
        } else {
            sb.append("/dataRecommend/v1/questionnaire/getQuestionnaireResults");
        }
        return sb.toString();
    }

    public void setResourceId(String str) {
        this.resourceId = str;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public void setThemeId(int i) {
        this.themeId = i;
    }

    public String toString() {
        return "QuestionResultReq{resourceId='" + this.resourceId + "', theme='" + this.theme + "', themeId=" + this.themeId + '}';
    }
}
