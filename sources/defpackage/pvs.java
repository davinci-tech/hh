package defpackage;

import com.huawei.ui.commonui.dialog.ThreeCircleShareCallback;
import java.io.Serializable;

/* loaded from: classes6.dex */
public final /* synthetic */ class pvs implements ThreeCircleShareCallback, Serializable {
    public final /* synthetic */ int d;

    @Override // com.huawei.ui.commonui.dialog.ThreeCircleShareCallback
    public final void onCallback(boolean z) {
        pvn.a(this.d, z);
    }

    public /* synthetic */ pvs(int i) {
        this.d = i;
    }
}
