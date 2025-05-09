package defpackage;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class nsy {
    public static <T extends View> T cMd_(View view, int i) {
        if (view == null) {
            return null;
        }
        return (T) view.findViewById(i);
    }

    public static <T extends View> T cMc_(Activity activity, int i) {
        if (activity == null) {
            return null;
        }
        return (T) activity.findViewById(i);
    }

    public static void cMA_(View view, int i) {
        if (view == null) {
            LogUtil.h("ViewUtils", "setVisibility view is null");
        } else {
            view.setVisibility(i);
        }
    }

    public static void cMj_(View view, Drawable drawable) {
        if (view == null) {
            LogUtil.h("ViewUtils", "setBackground view is null");
        } else {
            view.setBackground(drawable);
        }
    }

    public static void cMp_(ImageView imageView, int i) {
        if (imageView == null) {
            LogUtil.h("ViewUtils", "setSrc view is null");
        } else {
            imageView.setImageResource(i);
        }
    }

    public static void cMm_(ImageView imageView, Drawable drawable) {
        if (imageView == null) {
            LogUtil.h("ViewUtils", "setImageDrawable view is null");
        } else {
            imageView.setImageDrawable(drawable);
        }
    }

    public static void cMk_(View view, int i) {
        if (view == null) {
            LogUtil.h("ViewUtils", "setBackground view is null");
        } else {
            view.setBackgroundColor(i);
        }
    }

    public static void cMz_(View view, int i) {
        if (view == null) {
            LogUtil.h("ViewUtils", "setCardBackgroundColor view is null");
        } else {
            view.setBackgroundResource(i);
        }
    }

    public static void cMn_(View view, View.OnClickListener onClickListener) {
        if (view == null) {
            LogUtil.h("ViewUtils", "setOnClickListener view is null");
        } else {
            view.setOnClickListener(onClickListener);
        }
    }

    public static void cMo_(View view, final OnClickSectionListener onClickSectionListener, final String str) {
        if (view == null || onClickSectionListener == null) {
            LogUtil.h("ViewUtils", "setOnSectionClickListener view or listener is null");
        } else {
            view.setOnClickListener(new View.OnClickListener() { // from class: nsy.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    OnClickSectionListener.this.onClick(str);
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }
    }

    public static void cMq_(TextView textView, int i) {
        if (textView == null) {
            LogUtil.h("ViewUtils", "setText view is null");
        } else {
            textView.setText(i);
        }
    }

    public static void cMr_(TextView textView, CharSequence charSequence) {
        cMs_(textView, charSequence, false);
    }

    public static void cMt_(TextView textView, Drawable drawable) {
        if (textView == null) {
            LogUtil.h("ViewUtils", "setTextBackground view is null");
        } else {
            textView.setBackground(drawable);
        }
    }

    public static void cMu_(TextView textView, int i) {
        if (textView == null) {
            LogUtil.h("ViewUtils", "setTextColor view is null");
        } else {
            textView.setTextColor(i);
        }
    }

    public static void cMy_(TextView textView, Typeface typeface) {
        if (textView == null) {
            LogUtil.h("ViewUtils", "setTypeface view is null");
        } else {
            textView.setTypeface(typeface);
        }
    }

    public static void cMs_(TextView textView, CharSequence charSequence, boolean z) {
        if (textView == null) {
            LogUtil.h("ViewUtils", "setText view is null");
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            if (z) {
                textView.setVisibility(8);
            }
        } else {
            textView.setText(charSequence);
            if (z) {
                textView.setVisibility(0);
            }
        }
    }

    public static void cMv_(TextView textView, CharSequence charSequence, boolean z) {
        if (textView == null) {
            LogUtil.h("ViewUtils", "setText view is null");
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            if (z) {
                textView.setVisibility(4);
            }
        } else {
            textView.setText(charSequence);
            if (z) {
                textView.setVisibility(0);
            }
        }
    }

    public static void cMw_(TextView textView, float f) {
        cMx_(textView, f, 1);
    }

    public static void cMx_(TextView textView, float f, int i) {
        if (textView == null || f < 0.0f) {
            LogUtil.h("ViewUtils", "setTextSize param is invalid");
        } else {
            textView.setTextSize(i, f);
        }
    }

    public static void cMh_(View view, float f) {
        if (view == null) {
            LogUtil.h("ViewUtils", "setAlpha view is null");
        } else {
            view.setAlpha(f);
        }
    }

    public static void e(HealthSubHeader healthSubHeader, String str) {
        if (healthSubHeader != null) {
            healthSubHeader.setHeadTitleText(str);
        }
    }

    public static void d(HealthSubHeader healthSubHeader, int i, String str, int i2, int i3) {
        healthSubHeader.setVisibility(i);
        healthSubHeader.setHeadTitleText(str);
        healthSubHeader.setMoreTextVisibility(i2);
        healthSubHeader.setSubHeaderBackgroundColor(0);
        healthSubHeader.setRightArrayVisibility(i3);
    }

    public static void cMl_(HealthTextView healthTextView, Drawable drawable) {
        if (healthTextView == null || drawable == null) {
            LogUtil.h("ViewUtils", "setCompoundDrawablesRelative view is null");
        } else {
            healthTextView.setCompoundDrawablesRelative(drawable, null, null, null);
        }
    }

    public static void cMi_(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            LogUtil.h("ViewUtils", "setAnimateParentHierarchy view is null");
            return;
        }
        LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
        if (layoutTransition != null) {
            layoutTransition.setAnimateParentHierarchy(z);
        }
    }

    public static void e(HealthTextView healthTextView, boolean z) {
        if (healthTextView == null) {
            return;
        }
        healthTextView.setSplittable(z);
    }

    public static void cMa_(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        ViewTreeObserver viewTreeObserver = view != null ? view.getViewTreeObserver() : null;
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void cMf_(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        ViewTreeObserver viewTreeObserver = view != null ? view.getViewTreeObserver() : null;
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void cMb_(View view, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        if (view == null) {
            ReleaseLogUtil.a("ViewUtils", "addOnScrollChangedListener view is null");
        } else if (view.getViewTreeObserver() != null && view.getViewTreeObserver().isAlive()) {
            view.getViewTreeObserver().addOnScrollChangedListener(onScrollChangedListener);
        } else {
            ReleaseLogUtil.a("ViewUtils", "addOnScrollChangedListener observer is null or killed");
        }
    }

    public static void cMg_(View view, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        if (view == null) {
            ReleaseLogUtil.a("ViewUtils", "removeOnScrollChangedListener view is null");
        } else if (view.getViewTreeObserver() != null && view.getViewTreeObserver().isAlive()) {
            view.getViewTreeObserver().removeOnScrollChangedListener(onScrollChangedListener);
        } else {
            ReleaseLogUtil.a("ViewUtils", "removeOnScrollChangedListener observer is null or killed");
        }
    }

    public static boolean cMe_(TextView textView) {
        if (textView == null) {
            LogUtil.b("ViewUtils", "text is null");
            return false;
        }
        TextPaint paint = textView.getPaint();
        paint.setTextSize(textView.getTextSize());
        return ((int) paint.measureText(textView.getText().toString())) > textView.getWidth();
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("，")) {
            return str;
        }
        return str.split("，")[0].trim() + System.lineSeparator() + str.split("，")[1].trim();
    }
}
