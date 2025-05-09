package com.huawei.phoneservice.feedback.widget;

import android.content.Context;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.widget.TextView;
import com.huawei.health.R;

/* loaded from: classes9.dex */
public class ClickUrlSpan extends URLSpan {

    /* renamed from: a, reason: collision with root package name */
    private final Context f8300a;

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
        Context context = this.f8300a;
        if (context != null) {
            textPaint.setColor(context.getResources().getColor(R.color.feedback_sdk_privacy_link_color));
        }
    }

    public static void a(TextView textView) {
        Spannable spannable;
        if (textView == null) {
            return;
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        if (!(textView.getText() instanceof Spannable) || (spannable = (Spannable) textView.getText()) == null) {
            return;
        }
        for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
            int spanStart = spannable.getSpanStart(uRLSpan);
            int spanEnd = spannable.getSpanEnd(uRLSpan);
            spannable.removeSpan(uRLSpan);
            spannable.setSpan(new ClickUrlSpan(uRLSpan.getURL(), textView.getContext()), spanStart, spanEnd, 33);
        }
        textView.setAutoLinkMask(0);
        textView.setText(spannable);
        textView.setClickable(false);
        textView.setLongClickable(false);
    }

    public ClickUrlSpan(String str, Context context) {
        super(str);
        this.f8300a = context;
    }
}
