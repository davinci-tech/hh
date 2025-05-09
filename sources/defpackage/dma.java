package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.QuestionnaireTemplate;
import com.huawei.health.marketing.views.QuestionAndAnswerLayout;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
class dma implements ILayoutGenerator {
    dma() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null || resourceBriefInfo == null || resourceBriefInfo.getContent() == null || TextUtils.isEmpty(resourceBriefInfo.getContent().getContent())) {
            LogUtil.h("", "is null, setQuestionAndAnswer return");
            return null;
        }
        LogUtil.a("", "briefInfo.getContent().getContent():", resourceBriefInfo.getContent().getContent());
        String content = resourceBriefInfo.getContent().getContent();
        LogUtil.a("", "combinationTemplateStr:", content);
        try {
            QuestionnaireTemplate questionnaireTemplate = (QuestionnaireTemplate) new Gson().fromJson(content, QuestionnaireTemplate.class);
            LogUtil.a("", "questionnaireTemplate:", questionnaireTemplate.toString());
            QuestionAndAnswerLayout questionAndAnswerLayout = new QuestionAndAnswerLayout(context);
            questionAndAnswerLayout.d(i, resourceBriefInfo, questionnaireTemplate);
            return questionAndAnswerLayout;
        } catch (JsonSyntaxException unused) {
            LogUtil.a("", "QuestionnaireTemplate JsonSyntaxException");
            return null;
        }
    }
}
