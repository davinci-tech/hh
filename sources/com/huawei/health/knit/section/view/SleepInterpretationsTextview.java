package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes3.dex */
public class SleepInterpretationsTextview extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2754a;
    private int b;
    private int c;
    private Drawable d;
    private HealthTextView e;

    public SleepInterpretationsTextview(Context context) {
        super(context);
        this.d = getDefaultDrawable();
        this.b = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        this.c = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        b(context);
    }

    public SleepInterpretationsTextview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = getDefaultDrawable();
        this.b = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        this.c = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        b(context);
    }

    public SleepInterpretationsTextview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = getDefaultDrawable();
        this.b = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        this.c = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        b(context);
    }

    public void setupTextView(String str) {
        Drawable drawable = this.d;
        int i = (drawable == null || drawable.getBounds() == null) ? 0 : this.d.getBounds().right - this.d.getBounds().left;
        LeadingMarginSpan.Standard standard = new LeadingMarginSpan.Standard(i == 0 ? 0 : i + this.b, 0);
        SpannableString spannableString = new SpannableString(Html.fromHtml(str));
        spannableString.setSpan(standard, 0, spannableString.length(), 18);
        this.f2754a.setText(spannableString);
        this.e.setCompoundDrawables(null, null, this.d, null);
    }

    public void setupDrawable(Drawable drawable) {
        this.d = drawable;
    }

    public void d() {
        Drawable defaultDrawable = getDefaultDrawable();
        this.d = defaultDrawable;
        if (defaultDrawable == null) {
            LogUtil.h("SleepInterpretationsTextview", "drawable is null");
        } else {
            int i = this.c;
            defaultDrawable.setBounds(0, 0, i, i);
        }
    }

    private Drawable getDefaultDrawable() {
        return ContextCompat.getDrawable(BaseApplication.getContext(), R.drawable._2131431518_res_0x7f0b105e);
    }

    private void b(Context context) {
        LayoutInflater.from(context).inflate(R.layout.sleep_suggestions_text_view_layout, this);
        this.e = (HealthTextView) findViewById(R.id.indent_icon_text_view);
        this.f2754a = (HealthTextView) findViewById(R.id.content_text_view);
        Drawable drawable = this.d;
        if (drawable != null) {
            int i = this.c;
            drawable.setBounds(0, 0, i, i);
        }
    }
}
