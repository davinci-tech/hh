package com.huawei.ui.commonui.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes6.dex */
public class VibrantLifeDialog extends QueueDialog {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8824a;
    public LinearLayout b;
    private ImageView c;
    private Context d;
    private HealthTextView e;
    private View g;
    private HealthTextView i;
    private HealthTextView j;

    public VibrantLifeDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.d = context;
        d();
    }

    @Override // com.huawei.ui.commonui.dialog.QueueDialog
    protected boolean isDialogAbandoned() {
        Context context = this.d;
        return context == null || ((Activity) context).isFinishing();
    }

    public void d(String str) {
        this.j.setText(str);
    }

    public void c(String str) {
        this.f8824a.setText(str);
    }

    public void czY_(View.OnClickListener onClickListener) {
        this.i.setOnClickListener(onClickListener);
    }

    public void czX_(Drawable drawable) {
        this.c.setImageDrawable(drawable);
    }

    private void d() {
        View inflate = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.common_ui_vibrant_life, (ViewGroup) null);
        this.g = inflate;
        this.c = (ImageView) inflate.findViewById(R.id.dialog_bg_image);
        this.j = (HealthTextView) this.g.findViewById(R.id.user_type_vibrant_life);
        this.f8824a = (HealthTextView) this.g.findViewById(R.id.user_content_vibrant_life);
        this.i = (HealthTextView) this.g.findViewById(R.id.vibrant_life_view_now);
        this.e = (HealthTextView) this.g.findViewById(R.id.vibrant_life_view_cancel);
        this.b = (LinearLayout) this.g.findViewById(R.id.icon_parent);
        this.j.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.dialog.VibrantLifeDialog.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int lineCount = VibrantLifeDialog.this.j.getLayout().getLineCount();
                LogUtil.a("OnGlobalLayoutListener,", Integer.valueOf(lineCount));
                if (lineCount > 1) {
                    VibrantLifeDialog.this.j.setTextSize(1, 16.0f);
                }
                VibrantLifeDialog.this.j.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.VibrantLifeDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VibrantLifeDialog.this.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        setContentView(this.g);
    }
}
