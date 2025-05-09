package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class CustomAdViewDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private int f8795a;
    private int b;
    private Context c;
    private int d;

    private CustomAdViewDialog(Context context, int i) {
        super(context, i, 0);
        this.b = 0;
        this.f8795a = 0;
        this.d = 0;
        this.c = context;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            LogUtil.a("CustomAdViewDialog", "mClickTime ", Integer.valueOf(this.b));
            if (cyb_(getContext(), motionEvent)) {
                this.b++;
            }
            if (this.b == 2) {
                LogUtil.a("CustomAdViewDialog", "dialog dismiss");
                dismiss();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        this.f8795a = i;
        this.d = i2;
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(16);
            WindowManager.LayoutParams attributes = window.getAttributes();
            int dimensionPixelSize = this.c.getResources().getDimensionPixelSize(R.dimen._2131362487_res_0x7f0a02b7);
            int dimensionPixelSize2 = this.c.getResources().getDimensionPixelSize(R.dimen._2131362470_res_0x7f0a02a6);
            int i = this.d;
            if (i > 0) {
                dimensionPixelSize2 = (int) ((dimensionPixelSize2 * this.f8795a) / i);
            }
            attributes.width = dimensionPixelSize2 + (dimensionPixelSize * 2);
            window.setAttributes(attributes);
        }
        super.show();
    }

    private boolean cyb_(Context context, MotionEvent motionEvent) {
        if (context == null) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int scaledWindowTouchSlop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        View decorView = getWindow().getDecorView();
        int i = -scaledWindowTouchSlop;
        return x < i || x > decorView.getWidth() + scaledWindowTouchSlop || y < i || y > decorView.getHeight() + scaledWindowTouchSlop;
    }

    public boolean c() {
        ImageView imageView = (ImageView) findViewById(R.id.ad_image);
        if (imageView == null) {
            LogUtil.a("CustomAdViewDialog", "isWidthHeightZero(), adImage = null");
            return true;
        }
        LogUtil.a("CustomAdViewDialog", "isWidthHeightZero(), width = ", Integer.valueOf(imageView.getWidth()), " Height = ", Integer.valueOf(imageView.getHeight()));
        return imageView.getWidth() <= 0 || imageView.getHeight() <= 0;
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private CustomAdViewDialog f8796a;
        private Bitmap b;
        private ImageView c;
        private View.OnClickListener d;
        private Context e;
        private View.OnClickListener j;

        public Builder(Context context) {
            this.e = context;
        }

        public Builder cye_(Bitmap bitmap) {
            this.b = bitmap;
            return this;
        }

        public Builder cyf_(View.OnClickListener onClickListener) {
            this.j = onClickListener;
            return this;
        }

        public Builder cyg_(View.OnClickListener onClickListener) {
            this.d = onClickListener;
            return this;
        }

        public CustomAdViewDialog a() {
            LayoutInflater layoutInflater = (LayoutInflater) this.e.getSystemService("layout_inflater");
            this.f8796a = new CustomAdViewDialog(this.e, R.style.CustomDialog);
            View inflate = layoutInflater.inflate(R.layout.layout_dialog_ad, (ViewGroup) null);
            this.c = (ImageView) inflate.findViewById(R.id.ad_image);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.ad_close);
            if (this.b != null) {
                RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(this.e.getResources(), this.b);
                create.setAntiAlias(true);
                create.setDither(true);
                create.setCornerRadius(nsn.c(this.e, 24.0f));
                this.c.setImageDrawable(create);
                this.f8796a.b(create.getBitmap().getWidth(), create.getBitmap().getHeight());
            } else {
                LogUtil.a("CustomAdViewDialog", "mBitmap = null");
            }
            this.c.setOnClickListener(new d());
            imageView.setOnClickListener(new b());
            this.f8796a.setContentView(inflate);
            return this.f8796a;
        }

        public boolean e() {
            ImageView imageView = this.c;
            if (imageView != null && imageView.getDrawable() != null) {
                LogUtil.a("CustomAdViewDialog", "mAdImage is null and mAdImage.getDrawable() is null");
                return true;
            }
            LogUtil.a("CustomAdViewDialog", "isImageShowSuccess error");
            return false;
        }

        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.f8796a != null) {
                    Builder.this.f8796a.dismiss();
                }
                if (Builder.this.d != null) {
                    Builder.this.d.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class d implements View.OnClickListener {
            d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.f8796a != null) {
                    Builder.this.f8796a.dismiss();
                }
                if (Builder.this.j != null) {
                    Builder.this.j.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
