package com.huawei.ui.main.stories.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.RecommendCardBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrf;
import defpackage.nsy;

/* loaded from: classes7.dex */
public class MemberCardUpdateDialog extends BaseDialog implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10537a;
    private HealthButton b;
    private ImageView c;
    private Context d;
    private HealthTextView e;
    private HealthTextView i;

    public MemberCardUpdateDialog(Context context) {
        super(context, R.style.MemberCardDialogStyle);
        this.d = context;
        b();
    }

    private void b() {
        Context context = this.d;
        if (context == null) {
            LogUtil.h("MemberCardUpdateDialog", "mContext is null");
            return;
        }
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.member_open_weight_change_guide, (ViewGroup) null);
        setContentView(inflate);
        this.c = (ImageView) inflate.findViewById(R.id.recommend_card_background);
        this.i = (HealthTextView) inflate.findViewById(R.id.card_title);
        this.f10537a = (HealthTextView) inflate.findViewById(R.id.card_subtitle);
        this.e = (HealthTextView) inflate.findViewById(R.id.card_subtitle_data_part);
        this.b = (HealthButton) inflate.findViewById(R.id.start_button);
        ((HealthButton) inflate.findViewById(R.id.light_weight_change_guide_button)).setOnClickListener(this);
    }

    public void d(Object obj) {
        if (obj instanceof RecommendCardBean) {
            RecommendCardBean recommendCardBean = (RecommendCardBean) obj;
            this.i.setTextColor(recommendCardBean.getTitleColor().intValue());
            this.i.setText(nsy.a(recommendCardBean.getTitle()));
            this.f10537a.setText(recommendCardBean.getSubtitle());
            this.e.setText(recommendCardBean.getSubtitleDataLine());
            this.b.setText(recommendCardBean.getButtonText());
            this.b.setBackground(dWd_(recommendCardBean.getButtonColor().intValue()));
            if (recommendCardBean.getBackGround() != null) {
                this.c.setImageDrawable(dWe_(recommendCardBean.getBackGround()));
                return;
            }
            return;
        }
        if (obj instanceof Bitmap) {
            this.c.setImageDrawable(dWe_((Bitmap) obj));
        } else if (obj instanceof String) {
            this.i.setText((String) obj);
        } else {
            LogUtil.h("MemberCardUpdateDialog", "error data");
        }
    }

    private Drawable dWe_(Bitmap bitmap) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(BaseApplication.e().getResources(), bitmap);
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.h("MemberCardUpdateDialog", "bitmap is null or is recycled");
            return bitmapDrawable;
        }
        try {
            if (Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888) == null) {
                LogUtil.h("MemberCardUpdateDialog", "roundRectangleBitmap is null");
                return bitmapDrawable;
            }
            RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(BaseApplication.e().getResources(), bitmap);
            create.setCornerRadius(nrf.d);
            create.setAntiAlias(true);
            return create;
        } catch (OutOfMemoryError unused) {
            LogUtil.b("MemberCardUpdateDialog", "outOfMemoryError");
            return bitmapDrawable;
        }
    }

    private GradientDrawable dWd_(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(nrf.d);
        gradientDrawable.setColor(i);
        return gradientDrawable;
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        if (isShowing()) {
            return;
        }
        super.show();
        LogUtil.a("MemberCardUpdateDialog", "show dialog");
        Window window = getWindow();
        window.setDimAmount(0.8f);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setGravity(16);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("MemberCardUpdateDialog", "onClick, view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view.getId() == R.id.light_weight_change_guide_button || view.getId() == R.id.button_layout) {
                dismiss();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
