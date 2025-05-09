package com.huawei.ui.commonui.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.nsn;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class EquityDialog extends QueueDialog {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8807a;
    private HealthTextView b;
    public HealthImageView c;
    private HealthTextView d;
    private HealthTextView e;
    private final CountDownLatch f;
    private Context i;

    public EquityDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.f = new CountDownLatch(2);
        this.i = context;
        b();
    }

    private void b() {
        View inflate = ((LayoutInflater) this.i.getSystemService("layout_inflater")).inflate(R.layout.commonui_dialog_equity, (ViewGroup) null);
        this.c = (HealthImageView) inflate.findViewById(R.id.dialog_inbox_card_img);
        this.b = (HealthTextView) inflate.findViewById(R.id.inbox_card_first);
        this.f8807a = (HealthTextView) inflate.findViewById(R.id.inbox_card_second);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.inbox_card_cancel);
        this.d = healthTextView;
        czi_(healthTextView);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.inbox_card_activate);
        this.e = healthTextView2;
        czi_(healthTextView2);
        if (nsn.e(this.i, 3.5f)) {
            ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.dialog_inbox_card_layout);
            ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
            layoutParams.height = 690;
            layoutParams.width = 1008;
            constraintLayout.setLayoutParams(layoutParams);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.dialog_inbox_card_button_layout);
            ViewGroup.LayoutParams layoutParams2 = linearLayout.getLayoutParams();
            layoutParams2.height = 108;
            linearLayout.setLayoutParams(layoutParams2);
        }
        setContentView(inflate);
    }

    private void czi_(final View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.dialog.EquityDialog.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                EquityDialog.this.f.countDown();
                if (EquityDialog.this.f.getCount() <= 0) {
                    EquityDialog.this.a();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        HealthTextView[] healthTextViewArr = {this.d, this.e};
        float f = 2.1474836E9f;
        for (int i = 0; i < 2; i++) {
            float textSize = healthTextViewArr[i].getTextSize();
            if (textSize < f) {
                f = textSize;
            }
        }
        for (int i2 = 0; i2 < 2; i2++) {
            healthTextViewArr[i2].setAutoTextSize(0, f);
        }
    }

    public void czl_(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
        this.c.setOnClickListener(onClickListener);
    }

    public void czk_(View.OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public void czj_(Drawable drawable) {
        this.c.setImageDrawable(drawable);
    }

    public void a(String str) {
        this.b.setText(str);
    }

    public void e(String str) {
        this.f8807a.setText(str);
    }

    @Override // com.huawei.ui.commonui.dialog.QueueDialog
    protected boolean isDialogAbandoned() {
        Context context = this.i;
        return context == null || ((Activity) context).isFinishing();
    }
}
