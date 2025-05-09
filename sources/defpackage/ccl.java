package defpackage;

import android.content.DialogInterface;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes.dex */
public final class ccl implements View.OnClickListener, DialogInterface.OnDismissListener {
    private View.OnClickListener b;
    private ccl d;

    public ccl(View.OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ccl cclVar = this.d;
        if (cclVar != null) {
            cclVar.e();
            this.d = null;
        }
        View.OnClickListener onClickListener = this.b;
        if (onClickListener != null) {
            onClickListener.onClick(view);
            e();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.b != null) {
            onClick(new View(BaseApplication.e()));
        }
    }

    void c(ccl cclVar) {
        this.d = cclVar;
    }

    private void e() {
        this.b = null;
    }
}
