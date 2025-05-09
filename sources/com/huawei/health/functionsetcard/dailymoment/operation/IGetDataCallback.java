package com.huawei.health.functionsetcard.dailymoment.operation;

import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import java.util.List;

/* loaded from: classes3.dex */
public interface IGetDataCallback {
    void onComplete(List<SingleDailyMomentContent> list, ResourceBriefInfo resourceBriefInfo);
}
