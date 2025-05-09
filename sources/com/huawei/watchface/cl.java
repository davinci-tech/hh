package com.huawei.watchface;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class cl extends ad {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10962a = "cl";
    private static final byte[] b = new byte[1];
    private static boolean c;
    private static AnimationDrawable d;

    public cl(Context context, int i) {
        super(context, i);
    }

    public cl(Context context) {
        super(context, R.style.watch_face_customDialog);
    }

    private void a(boolean z) {
        synchronized (cl.class) {
            c = z;
        }
    }

    public static class a extends ah {

        /* renamed from: a, reason: collision with root package name */
        private cl f10963a;
        private Context b;
        private HwProgressBar c;
        private TextView d;
        private TextView e;
        private ImageView f;
        private View.OnClickListener g;
        private String h;

        public a(Context context) {
            this.b = context;
        }

        public a a(String str) {
            this.h = str;
            TextView textView = this.e;
            if (textView != null && str != null) {
                textView.setText(str);
            }
            return this;
        }

        public a a(int i) {
            HwProgressBar hwProgressBar = this.c;
            if (hwProgressBar != null) {
                hwProgressBar.setProgress(i);
            }
            return this;
        }

        public a b(String str) {
            TextView textView;
            if (str != null && (textView = this.d) != null) {
                textView.setText(str);
            }
            return this;
        }

        public a a(View.OnClickListener onClickListener) {
            this.g = onClickListener;
            return this;
        }

        public cl a() {
            View inflate;
            LayoutInflater layoutInflater = (LayoutInflater) this.b.getSystemService("layout_inflater");
            this.b.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, new TypedValue(), true);
            Drawable drawable = ContextCompat.getDrawable(this.b, R$drawable.watchface_shape_dialog_bg);
            if (cl.c) {
                this.f10963a = new cl(this.b, R.style.watch_face_progress_data_insert_dialog);
                inflate = layoutInflater.inflate(R$layout.watchface_dialog_custom_loading, (ViewGroup) null);
                AnimationDrawable unused = cl.d = (AnimationDrawable) ((ImageView) inflate.findViewById(R$id.watch_face_data_loading_img)).getDrawable();
            } else {
                this.f10963a = new cl(this.b, R.style.watch_face_customDialog_process);
                inflate = layoutInflater.inflate(R$layout.watchface_dialog_custom_progress, (ViewGroup) null);
                this.d = (TextView) inflate.findViewById(R$id.custom_progress_dialog_percent);
                this.c = (HwProgressBar) inflate.findViewById(R$id.custom_progress_dialog_progressbar);
                ImageView imageView = (ImageView) inflate.findViewById(R$id.custom_progress_dialog_cancel);
                this.f = imageView;
                if (this.g != null) {
                    imageView.setOnClickListener(new ViewOnClickListenerC0282a());
                    this.f.setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                }
                TextView textView = (TextView) inflate.findViewById(R$id.custom_progress_dialog_desc);
                this.e = textView;
                textView.setText(this.h);
                HwLog.i(cl.f10962a, "mProgressDesc :" + this.h);
                this.c.setMax(100);
            }
            inflate.setBackground(drawable);
            this.f10963a.setContentView(inflate);
            return this.f10963a;
        }

        /* renamed from: com.huawei.watchface.cl$a$a, reason: collision with other inner class name */
        class ViewOnClickListenerC0282a implements View.OnClickListener {
            ViewOnClickListenerC0282a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (a.this.f10963a != null) {
                    HwLog.i(cl.f10962a, "mCustomProgressDialog.dismiss");
                    a.this.f10963a.dismiss();
                }
                if (a.this.g != null) {
                    a.this.g.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }

    @Override // com.huawei.watchface.ad, android.app.Dialog
    public void show() {
        super.show();
        AnimationDrawable animationDrawable = d;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
    }

    @Override // com.huawei.watchface.ad, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        synchronized (b) {
            a(false);
            AnimationDrawable animationDrawable = d;
            if (animationDrawable != null && animationDrawable.isRunning()) {
                d.stop();
                d = null;
            }
        }
    }
}
