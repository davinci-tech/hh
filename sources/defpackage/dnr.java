package defpackage;

import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class dnr {
    private static final Map<Integer, ITemplateGenerator<? extends BaseTemplate>> e;

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(20230523, new dng());
        hashMap.put(10, new dnt());
        hashMap.put(23, new dnt());
        hashMap.put(78, new dnn());
        hashMap.put(95, new dnq());
        hashMap.put(106, new dnu());
        hashMap.put(113, new dnm());
        hashMap.put(114, new dnp());
        hashMap.put(134, new dnp());
        hashMap.put(117, new dns());
        hashMap.put(115, new dnl());
        hashMap.put(116, new dnk());
        hashMap.put(122, new dni());
    }

    public static BaseTemplate c(ResourceBriefInfo resourceBriefInfo) {
        int contentType = resourceBriefInfo.getContentType();
        Map<Integer, ITemplateGenerator<? extends BaseTemplate>> map = e;
        ITemplateGenerator<? extends BaseTemplate> iTemplateGenerator = map.get(Integer.valueOf(contentType));
        if (iTemplateGenerator == null) {
            iTemplateGenerator = map.get(20230523);
        }
        return iTemplateGenerator.generate(resourceBriefInfo);
    }
}
