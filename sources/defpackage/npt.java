package defpackage;

import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.listener.OnClickSectionListener;

/* loaded from: classes6.dex */
public class npt implements OnClickSectionListener {
    @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
    public void onClick(int i) {
    }

    @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
    public void onClick(int i, int i2) {
    }

    @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
    public void onClick(int i, String str) {
    }

    @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
    public void onClick(String str) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }
}
