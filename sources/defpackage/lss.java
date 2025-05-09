package defpackage;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.widget.TextView;

/* loaded from: classes9.dex */
public class lss extends LinkMovementMethod {
    private int d;
    private int e;

    @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1) {
            ClickableSpan[] cah_ = cah_(textView, spannable, motionEvent);
            if (cah_.length != 0) {
                cah_[0].onClick(textView);
                if (this.d != 0) {
                    spannable.setSpan(new ForegroundColorSpan(this.d), spannable.getSpanStart(cah_[0]), spannable.getSpanEnd(cah_[0]), 33);
                }
                return true;
            }
        } else {
            if (action != 0) {
                return Touch.onTouchEvent(textView, spannable, motionEvent);
            }
            ClickableSpan[] cah_2 = cah_(textView, spannable, motionEvent);
            if (cah_2.length != 0) {
                if (this.e != 0) {
                    spannable.setSpan(new ForegroundColorSpan(this.e), spannable.getSpanStart(cah_2[0]), spannable.getSpanEnd(cah_2[0]), 33);
                }
                return true;
            }
        }
        Selection.removeSelection(spannable);
        Touch.onTouchEvent(textView, spannable, motionEvent);
        return false;
    }

    private ClickableSpan[] cah_(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int totalPaddingLeft = textView.getTotalPaddingLeft();
        int totalPaddingTop = textView.getTotalPaddingTop();
        int scrollX = textView.getScrollX();
        int scrollY = textView.getScrollY();
        Layout layout = textView.getLayout();
        int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical((y - totalPaddingTop) + scrollY), (x - totalPaddingLeft) + scrollX);
        return (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
    }

    public lss(int i, int i2) {
        this.d = i;
        this.e = i2;
    }
}
