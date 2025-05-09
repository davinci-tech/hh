package defpackage;

import com.huawei.ui.commonui.tablewidget.model.ItemData;

/* loaded from: classes4.dex */
public class hjj implements ItemData {
    private String b;
    private String d;

    public hjj(String str, String str2) {
        this.d = str;
        this.b = str2;
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.ItemData
    public String getValue() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.ItemData
    public void setValue(String str) {
        this.d = str;
    }

    public String d() {
        return this.b;
    }
}
