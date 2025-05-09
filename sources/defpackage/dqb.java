package defpackage;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.QueueDialog;

/* loaded from: classes3.dex */
public class dqb extends QueueDialog {

    /* renamed from: a, reason: collision with root package name */
    private final Context f11777a;
    private final View c;

    @Override // com.huawei.ui.commonui.dialog.QueueDialog
    public boolean isCheckTypeInQueue() {
        return true;
    }

    public dqb(Context context, View view) {
        super(context, R.style.DialogFullScreen, 0);
        this.c = view;
        this.f11777a = context;
        b();
    }

    private void b() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        setContentView(this.c);
        Zk_(this.c);
        window.setType(1000);
        window.setBackgroundDrawableResource(R.color._2131296919_res_0x7f090297);
        ((ImageView) findViewById(R.id.close_img)).setOnClickListener(new View.OnClickListener() { // from class: dpz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dqb.this.Zl_(view);
            }
        });
    }

    /* synthetic */ void Zl_(View view) {
        LogUtil.a("MemberGuideDialog", "close btn clicked");
        dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void Zk_(View view) {
        if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.bottomMargin = (nsn.ab(getContext()) ? nsn.q(getContext()) : 0) + this.f11777a.getResources().getDimensionPixelOffset(c());
            view.setLayoutParams(layoutParams);
        }
    }

    private int c() {
        return nsn.aa(this.f11777a) ? R.dimen._2131362886_res_0x7f0a0446 : R.dimen._2131363060_res_0x7f0a04f4;
    }

    @Override // com.huawei.ui.commonui.dialog.QueueDialog, com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        enqueueShowing(this);
        super.show();
    }
}
