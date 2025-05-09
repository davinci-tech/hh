package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.healthmodel.bean.ImageBean;
import com.huawei.health.healthmodel.bean.PictureBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepTaskDialog;
import defpackage.dpd;
import defpackage.nrf;
import defpackage.nsn;
import java.util.Iterator;

/* loaded from: classes.dex */
public class SleepTaskDialog extends BaseDialog {
    public SleepTaskDialog(Context context) {
        super(context, R.style.CustomDialog, 0);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private ImageBean f9979a;
        private View.OnClickListener b;
        private String c;
        private final Activity d;
        private View e;
        private View.OnClickListener f;
        private String g;
        private View.OnClickListener h;
        private View.OnClickListener i;
        private String j;
        private String k;
        private SleepTaskDialog l;
        private String m;
        private View.OnClickListener n;
        private String o;

        public Builder(Context context) {
            if (context instanceof Activity) {
                this.d = (Activity) context;
            } else {
                this.d = BaseApplication.wa_();
            }
        }

        public Builder b(String str) {
            this.o = str;
            return this;
        }

        public Builder c(String str) {
            this.k = str;
            return this;
        }

        public Builder a(String str) {
            this.c = str;
            return this;
        }

        public Builder dxs_(View view) {
            this.e = view;
            return this;
        }

        public Builder a(ImageBean imageBean) {
            this.f9979a = imageBean;
            return this;
        }

        public Builder dxv_(String str, View.OnClickListener onClickListener) {
            this.m = str;
            this.i = onClickListener;
            return this;
        }

        public Builder dxu_(String str, View.OnClickListener onClickListener) {
            this.g = str;
            this.f = onClickListener;
            return this;
        }

        public Builder dxt_(String str, View.OnClickListener onClickListener) {
            this.j = str;
            this.h = onClickListener;
            return this;
        }

        public Builder dxr_(View.OnClickListener onClickListener) {
            this.b = onClickListener;
            return this;
        }

        public Builder dxw_(View.OnClickListener onClickListener) {
            this.n = onClickListener;
            return this;
        }

        public SleepTaskDialog b() {
            View inflate = View.inflate(this.d, R.layout.dialog_task, null);
            Size dxk_ = dxk_(this.f9979a, inflate.findViewById(R.id.dialog_base_task_background));
            int width = dxk_.getWidth();
            int height = dxk_.getHeight();
            this.l = new SleepTaskDialog(this.d);
            boolean y = nsn.y(this.d);
            int d = dpd.d(this.d, y);
            int a2 = dpd.a(this.d, y, (height * d) / width);
            Window window = this.l.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = d;
            attributes.height = a2;
            if (new HealthColumnSystem(BaseApplication.e(), 0).e()) {
                attributes.y = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362464_res_0x7f0a02a0);
                window.setGravity(80);
            } else {
                window.setGravity(17);
            }
            window.setAttributes(attributes);
            this.l.setContentView(inflate);
            dxo_(inflate);
            dxl_(inflate);
            dxj_(inflate, y, d, a2);
            dxn_(inflate);
            return this.l;
        }

        private void dxo_(View view) {
            a((HealthTextView) view.findViewById(R.id.dialog_base_task_title), this.o);
            a((HealthTextView) view.findViewById(R.id.dialog_base_task_subtitle), this.k);
            a((HealthTextView) view.findViewById(R.id.dialog_base_task_content), this.c);
        }

        private void dxl_(View view) {
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.dialog_base_task_positive);
            dxm_(healthTextView, this.m, this.i);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.dialog_base_task_negative);
            dxm_(healthTextView2, this.g, this.f);
            HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.dialog_base_task_know);
            dxm_(healthTextView3, this.j, this.h);
            d(healthTextView, healthTextView2, healthTextView3);
            b(healthTextView, healthTextView2, healthTextView3);
            view.findViewById(R.id.dialog_base_task_about).setOnClickListener(new View.OnClickListener() { // from class: qal
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SleepTaskDialog.Builder.this.dxp_(view2);
                }
            });
            View findViewById = view.findViewById(R.id.dialog_base_task_share);
            if (this.n != null) {
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.SleepTaskDialog.Builder.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Builder.this.l.dismiss();
                        if (Builder.this.n != null) {
                            Builder.this.n.onClick(view2);
                        }
                        ViewClickInstrumentation.clickOnView(view2);
                    }
                });
            }
        }

        public /* synthetic */ void dxp_(View view) {
            this.l.dismiss();
            View.OnClickListener onClickListener = this.b;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        private void dxm_(HealthTextView healthTextView, String str, final View.OnClickListener onClickListener) {
            if (TextUtils.isEmpty(str)) {
                healthTextView.setVisibility(8);
                return;
            }
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
            healthTextView.setOnClickListener(new View.OnClickListener() { // from class: qaj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SleepTaskDialog.Builder.this.dxq_(onClickListener, view);
                }
            });
        }

        public /* synthetic */ void dxq_(View.OnClickListener onClickListener, View view) {
            this.l.dismiss();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        private void d(HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3) {
            if (healthTextView.getVisibility() != 8) {
                d(healthTextView, BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306));
            } else if (healthTextView2.getVisibility() != 8) {
                d(healthTextView2, BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306));
            } else if (healthTextView3.getVisibility() != 8) {
                d(healthTextView3, BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362363_res_0x7f0a023b));
            }
        }

        private void d(HealthTextView healthTextView, int i) {
            ViewGroup.LayoutParams layoutParams = healthTextView.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.topMargin = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362367_res_0x7f0a023f);
                layoutParams2.bottomMargin = i;
                healthTextView.setLayoutParams(layoutParams2);
            }
        }

        private void b(HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3) {
            if (healthTextView.getVisibility() == 0) {
                healthTextView.setBackground(ContextCompat.getDrawable(BaseApplication.e(), R$drawable.button_background_model_dialog));
            } else if (healthTextView2.getVisibility() == 0) {
                healthTextView2.setBackground(ContextCompat.getDrawable(BaseApplication.e(), R$drawable.button_background_model_dialog));
            } else if (healthTextView3.getVisibility() == 0) {
                healthTextView3.setBackground(ContextCompat.getDrawable(BaseApplication.e(), R$drawable.button_background_model_dialog));
            }
        }

        private void dxj_(View view, boolean z, int i, int i2) {
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dialog_base_task_background);
            ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i2;
            frameLayout.setLayoutParams(layoutParams);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dialog_base_task_layout);
            ViewGroup.LayoutParams layoutParams2 = linearLayout.getLayoutParams();
            if (layoutParams2 instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) layoutParams2;
                layoutParams3.gravity = z ? 48 : 80;
                linearLayout.setLayoutParams(layoutParams3);
            }
        }

        private void dxn_(View view) {
            if (this.e == null) {
                return;
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dialog_base_task_custom);
            linearLayout.setVisibility(0);
            linearLayout.addView(this.e);
        }

        private void a(HealthTextView healthTextView, String str) {
            if (TextUtils.isEmpty(str)) {
                healthTextView.setVisibility(8);
            } else {
                healthTextView.setVisibility(0);
                healthTextView.setText(str);
            }
        }

        private Size dxk_(ImageBean imageBean, View view) {
            Drawable dxh_ = dxh_(imageBean);
            if (dxh_ != null) {
                view.setBackground(dxh_);
                return new Size(dxh_.getIntrinsicWidth(), dxh_.getIntrinsicHeight());
            }
            Bitmap cHR_ = nrf.cHR_(R$drawable.health_life_background);
            RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(BaseApplication.e().getResources(), cHR_);
            create.setAntiAlias(true);
            create.setDither(true);
            create.setCornerRadius(BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362601_res_0x7f0a0329));
            view.setBackground(create);
            return new Size(cHR_.getWidth(), cHR_.getHeight());
        }

        private Drawable dxh_(ImageBean imageBean) {
            if (imageBean == null) {
                return null;
            }
            Iterator<PictureBean> it = imageBean.getPictureList().iterator();
            while (it.hasNext()) {
                PictureBean next = it.next();
                if (next.getScenario() == 100) {
                    return dxi_(nrf.cHQ_(next.getPath()));
                }
            }
            return null;
        }

        private Drawable dxi_(Bitmap bitmap) {
            RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(BaseApplication.e().getResources(), bitmap);
            create.setAntiAlias(true);
            create.setDither(true);
            create.setCornerRadius(BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362601_res_0x7f0a0329));
            return create;
        }
    }
}
