package defpackage;

import com.huawei.ui.commonui.tablewidget.model.ItemData;

/* loaded from: classes4.dex */
public class hjc implements ItemData {
    private String b;

    public hjc(String str) {
        this.b = str;
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.ItemData
    public String getValue() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.ItemData
    public void setValue(String str) {
        this.b = str;
    }
}
