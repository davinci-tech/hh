package com.huawei.ui.main.stories.me.views.builder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.me.views.builder.LabelBuilder;
import defpackage.nmk;
import defpackage.nsn;
import defpackage.rhz;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes7.dex */
public class LabelBuilder implements ITagTextViewBuilder {

    /* renamed from: a, reason: collision with root package name */
    private Context f10376a;
    private int c;
    private LabelListener d;
    private int e;

    public interface LabelListener {
        void onItemClick(rhz rhzVar, int i);
    }

    public LabelBuilder(Context context, int i, LabelListener labelListener) {
        this.f10376a = context;
        this.c = i;
        this.d = labelListener;
        this.e = ((context.getResources().getDisplayMetrics().widthPixels - this.f10376a.getResources().getDimensionPixelSize(R.dimen._2131363060_res_0x7f0a04f4)) - nsn.c(this.f10376a, 24.0f)) - nsn.c(this.f10376a, 24.0f);
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public HealthTextView build(nmk nmkVar) {
        final HealthTextView healthTextView = new HealthTextView(this.f10376a);
        healthTextView.setGravity(17);
        healthTextView.setTextSize(textSize());
        healthTextView.setMaxLines(1);
        healthTextView.setEllipsize(TextUtils.TruncateAt.END);
        healthTextView.setMaxWidth(this.e);
        healthTextView.setText(nmkVar.e());
        healthTextView.setTextColor(ContextCompat.getColor(healthTextView.getContext(), R.color._2131299236_res_0x7f090ba4));
        healthTextView.setBackgroundResource(R.drawable._2131431291_res_0x7f0b0f7b);
        healthTextView.setTag(nmkVar);
        healthTextView.setIncludeFontPadding(false);
        Resources resources = healthTextView.getResources();
        healthTextView.setCompoundDrawablePadding(resources.getDimensionPixelSize(R.dimen._2131363039_res_0x7f0a04df));
        Drawable drawable = ContextCompat.getDrawable(this.f10376a, this.c);
        if (drawable == null) {
            LogUtil.c("LabelBuilder", "drawable == null");
            return healthTextView;
        }
        final int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen._2131362030_res_0x7f0a00ee);
        drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen._2131363003_res_0x7f0a04bb);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
        if (LanguageUtil.bc(this.f10376a)) {
            healthTextView.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize2);
            healthTextView.setCompoundDrawables(drawable, null, null, null);
        } else {
            healthTextView.setCompoundDrawables(null, null, drawable, null);
            healthTextView.setPadding(dimensionPixelSize3, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
        }
        if (this.d == null || !(nmkVar instanceof rhz)) {
            return healthTextView;
        }
        final rhz rhzVar = (rhz) nmkVar;
        healthTextView.setText(rhzVar.i());
        healthTextView.setOnTouchListener(new View.OnTouchListener() { // from class: ric
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return LabelBuilder.this.dOH_(healthTextView, dimensionPixelSize, rhzVar, view, motionEvent);
            }
        });
        return healthTextView;
    }

    public /* synthetic */ boolean dOH_(HealthTextView healthTextView, int i, rhz rhzVar, View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        dOG_(healthTextView, i, rhzVar, motionEvent);
        return false;
    }

    private void dOG_(HealthTextView healthTextView, int i, rhz rhzVar, MotionEvent motionEvent) {
        if (LanguageUtil.bc(this.f10376a)) {
            if (motionEvent.getX() >= i) {
                return;
            }
        } else if (motionEvent.getX() <= healthTextView.getWidth() - i) {
            return;
        }
        if (nsn.a(500)) {
            return;
        }
        int l = rhzVar.l();
        LabelListener labelListener = this.d;
        if (labelListener == null) {
            return;
        }
        if (l == 1) {
            labelListener.onItemClick(rhzVar, 0);
        } else {
            labelListener.onItemClick(rhzVar, 1);
        }
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textSize() {
        return (nsn.c(this.f10376a, 12.0f) * 1.0f) / nsn.g(this.f10376a);
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textViewWidth(nmk nmkVar) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize((textSize() * nsn.g(this.f10376a)) + 0.5f);
        textPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
        return Math.min(textPaint.measureText(nmkVar.e()), this.e) + nsn.c(this.f10376a, 24.0f);
    }
}
