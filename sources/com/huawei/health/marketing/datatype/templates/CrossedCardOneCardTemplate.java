package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.CrossedCardOneCardContent;
import java.util.List;

/* loaded from: classes3.dex */
public class CrossedCardOneCardTemplate extends BaseSidingTemplate<CrossedCardOneCardContent> {
    private List<CrossedCardOneCardContent> gridContents;

    @Override // com.huawei.health.marketing.datatype.templates.BaseSidingTemplate
    public List<CrossedCardOneCardContent> getGridContents() {
        return this.gridContents;
    }

    @Override // com.huawei.health.marketing.datatype.templates.BaseSidingTemplate
    public void setGridContents(List<CrossedCardOneCardContent> list) {
        this.gridContents = list;
    }
}
