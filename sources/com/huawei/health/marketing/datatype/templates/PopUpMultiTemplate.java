package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.SinglePopUp;
import java.util.List;

/* loaded from: classes3.dex */
public class PopUpMultiTemplate extends BaseTemplate {
    private List<SinglePopUp> gridContents;

    public void setGridContents(List<SinglePopUp> list) {
        this.gridContents = list;
    }

    public List<SinglePopUp> getGridContents() {
        return this.gridContents;
    }
}
