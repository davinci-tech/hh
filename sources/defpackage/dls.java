package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.FamousTeacherSeriesRecTemplate;
import com.huawei.health.marketing.datatype.templates.SingleFamousTeacherStandardContent;
import com.huawei.health.marketing.views.FamousTeacherLayout;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes3.dex */
class dls implements ILayoutGenerator {
    dls() {
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
            FamousTeacherSeriesRecTemplate famousTeacherSeriesRecTemplate = (FamousTeacherSeriesRecTemplate) new Gson().fromJson(content, FamousTeacherSeriesRecTemplate.class);
            LogUtil.a("", "famousTeacherSeriesRecTemplate:", famousTeacherSeriesRecTemplate.toString());
            if (koq.b(famousTeacherSeriesRecTemplate.getGridContents())) {
                LogUtil.h("", "famousTeacherSeriesRecTemplate GridContents is empty");
                return null;
            }
            Collections.sort(famousTeacherSeriesRecTemplate.getGridContents(), new Comparator() { // from class: dlt
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return dls.c((SingleFamousTeacherStandardContent) obj, (SingleFamousTeacherStandardContent) obj2);
                }
            });
            FamousTeacherLayout famousTeacherLayout = new FamousTeacherLayout(context);
            famousTeacherLayout.b(i, resourceBriefInfo, famousTeacherSeriesRecTemplate);
            return famousTeacherLayout;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("", "FamousTeacherLayoutGenerator JsonSyntaxException");
            return null;
        }
    }

    static /* synthetic */ int c(SingleFamousTeacherStandardContent singleFamousTeacherStandardContent, SingleFamousTeacherStandardContent singleFamousTeacherStandardContent2) {
        return singleFamousTeacherStandardContent2.getTabPriority() - singleFamousTeacherStandardContent.getTabPriority();
    }
}
