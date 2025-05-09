package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrf;
import defpackage.nsn;

/* loaded from: classes8.dex */
public class UnlockSliderView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private SliderListener f3813a;
    private float b;
    private ImageView c;
    private ImageView d;
    private int e;
    private float i;
    private HealthTextView j;

    /* loaded from: classes4.dex */
    public interface SliderListener {
        void siderRight();
    }

    public UnlockSliderView(Context context) {
        super(context);
        this.e = 0;
        b(context);
    }

    public UnlockSliderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 0;
        b(context);
    }

    public UnlockSliderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        b(context);
    }

    public void setSliderListener(SliderListener sliderListener) {
        this.f3813a = sliderListener;
    }

    private void b(Context context) {
        ImageView imageView = new ImageView(context);
        this.c = imageView;
        imageView.setMinimumWidth(300);
        addView(this.c);
        HealthTextView healthTextView = new HealthTextView(context);
        this.j = healthTextView;
        healthTextView.setText(getResources().getString(R.string._2130839725_res_0x7f0208ad));
        this.j.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.j.setMaxLines(2);
        ((ViewGroup.MarginLayoutParams) this.j.getLayoutParams()).setMarginStart(nsn.c(context, 8.0f));
        this.j.setEllipsize(TextUtils.TruncateAt.END);
        this.j.setGravity(17);
        this.j.setAutoTextInfo(9, 1, 1);
        this.d = new ImageView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        if (e()) {
            linearLayout.setGravity(8388629);
            this.j.setPadding(0, 0, nsn.c(context, 4.0f), 0);
        } else {
            linearLayout.setGravity(8388627);
            this.j.setPadding(nsn.c(context, 4.0f), 0, 0, 0);
        }
        this.d.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131427843_res_0x7f0b0203), getResources().getColor(R.color._2131296687_res_0x7f0901af)));
        ((ViewGroup.MarginLayoutParams) this.d.getLayoutParams()).setMarginEnd(nsn.c(context, 8.0f));
        this.e = (int) this.d.getX();
        bjP_(linearLayout);
        if (getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager) {
            Display defaultDisplay = ((WindowManager) getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            linearLayout.setGravity(17);
            linearLayout.addView(this.d);
            linearLayout.addView(this.j);
            linearLayout.setMinimumHeight(600);
            linearLayout.setMinimumWidth(i);
            addView(linearLayout);
        }
    }

    private void bjP_(LinearLayout linearLayout) {
        linearLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.healthcloud.plugintrack.ui.view.UnlockSliderView.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    UnlockSliderView.this.i = motionEvent.getRawX();
                } else if (action == 1) {
                    if (UnlockSliderView.this.b - UnlockSliderView.this.i >= 200.0f) {
                        UnlockSliderView.this.f3813a.siderRight();
                    }
                    view.setX(UnlockSliderView.this.e);
                } else if (action == 2) {
                    UnlockSliderView.this.b = motionEvent.getX();
                    UnlockSliderView unlockSliderView = UnlockSliderView.this;
                    unlockSliderView.bjQ_(view, motionEvent, unlockSliderView.i, UnlockSliderView.this.b);
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bjQ_(View view, MotionEvent motionEvent, float f, float f2) {
        if (Math.abs(f2 - f) <= 1.0E-6d || motionEvent.getRawX() > this.c.getScrollX() + this.c.getWidth() || motionEvent.getRawX() <= view.getWidth()) {
            return;
        }
        view.setX(motionEvent.getRawX() - view.getWidth());
    }

    private boolean e() {
        String language = getResources().getConfiguration().locale.getLanguage();
        return language.endsWith("iw") || language.endsWith("ar") || language.endsWith("fa") || language.endsWith(Constants.URDU_LANG);
    }
}
