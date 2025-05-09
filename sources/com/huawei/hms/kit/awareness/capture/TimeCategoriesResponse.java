package com.huawei.hms.kit.awareness.capture;

import com.huawei.hms.kit.awareness.b.HHI;
import com.huawei.hms.kit.awareness.status.TimeCategories;

/* loaded from: classes9.dex */
public class TimeCategoriesResponse extends HHI<TimeCategories> {
    public TimeCategories getTimeCategories() {
        return getStatus();
    }

    @Override // com.huawei.hms.kit.awareness.b.HHI
    public String getErrorMsg() {
        return "getTimeCategories failed: ";
    }

    public TimeCategoriesResponse(TimeCategories timeCategories) {
        super(timeCategories);
    }
}
