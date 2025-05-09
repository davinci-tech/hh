package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.CourseNoticeStandardTemplate;
import com.huawei.health.marketing.datatype.templates.SingleCourseNoticeStandardContent;
import com.huawei.health.marketing.views.CoursePreviewLayout;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
class dlp implements ILayoutGenerator {
    dlp() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null || resourceBriefInfo == null || resourceBriefInfo.getContent() == null || TextUtils.isEmpty(resourceBriefInfo.getContent().getContent())) {
            LogUtil.h("CoursePreviewLayoutGenerator", "is null, CoursePreviewLayoutGenerator  return");
            return null;
        }
        LogUtil.a("CoursePreviewLayoutGenerator", "briefInfo.getContent().getContent():", resourceBriefInfo.getContent().getContent());
        String content = resourceBriefInfo.getContent().getContent();
        LogUtil.a("CoursePreviewLayoutGenerator", "combinationTemplateStr:", content);
        try {
            CourseNoticeStandardTemplate courseNoticeStandardTemplate = (CourseNoticeStandardTemplate) new Gson().fromJson(content, CourseNoticeStandardTemplate.class);
            LogUtil.a("CoursePreviewLayoutGenerator", "courseNoticeStandardTemplate:", courseNoticeStandardTemplate.toString());
            List<SingleCourseNoticeStandardContent> gridContents = courseNoticeStandardTemplate.getGridContents();
            Collections.sort(gridContents, new Comparator() { // from class: dln
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return dlp.a((SingleCourseNoticeStandardContent) obj, (SingleCourseNoticeStandardContent) obj2);
                }
            });
            if (koq.b(gridContents)) {
                LogUtil.h("CoursePreviewLayoutGenerator", "courseContents is empty");
                return null;
            }
            CoursePreviewLayout coursePreviewLayout = new CoursePreviewLayout(context);
            coursePreviewLayout.a(i, resourceBriefInfo, courseNoticeStandardTemplate);
            return coursePreviewLayout;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("CoursePreviewLayoutGenerator", "CourseNoticeStandardTemplate JsonSyntaxException");
            return null;
        }
    }

    static /* synthetic */ int a(SingleCourseNoticeStandardContent singleCourseNoticeStandardContent, SingleCourseNoticeStandardContent singleCourseNoticeStandardContent2) {
        return singleCourseNoticeStandardContent2.getTabPriority() - singleCourseNoticeStandardContent.getTabPriority();
    }
}
