package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.SingleHorizontalGridStandardContent;
import java.util.List;

/* loaded from: classes3.dex */
public class HorizontalThreeGridTemplate extends BaseSidingTemplate<SingleHorizontalGridStandardContent> {
    private List<SingleHorizontalGridStandardContent> gridContents;

    @Override // com.huawei.health.marketing.datatype.templates.BaseSidingTemplate
    public List<SingleHorizontalGridStandardContent> getGridContents() {
        return this.gridContents;
    }

    @Override // com.huawei.health.marketing.datatype.templates.BaseSidingTemplate
    public void setGridContents(List<SingleHorizontalGridStandardContent> list) {
        this.gridContents = list;
    }
}
