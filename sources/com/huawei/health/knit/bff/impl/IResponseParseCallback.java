package com.huawei.health.knit.bff.impl;

import com.huawei.health.knit.section.model.SectionBean;
import java.util.List;

/* loaded from: classes3.dex */
public interface IResponseParseCallback {
    void onCompleted(List<SectionBean> list);
}
