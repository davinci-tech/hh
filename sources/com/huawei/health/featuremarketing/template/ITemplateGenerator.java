package com.huawei.health.featuremarketing.template;

import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;

/* loaded from: classes3.dex */
public interface ITemplateGenerator<T extends BaseTemplate> {
    T generate(ResourceBriefInfo resourceBriefInfo);
}
