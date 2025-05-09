package com.huawei.phoneservice.faq.base.widget;

import android.app.ActionBar;
import android.content.res.ColorStateList;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.health.R;

/* loaded from: classes5.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static float f8247a;
    private static Boolean b;
    private static boolean c;
    private static CharSequence d;
    private static ColorStateList e;

    private static void cdE_(ActionBar actionBar) {
        View customView = actionBar.getCustomView();
        if (customView == null || customView.getTag() == null) {
            return;
        }
        TextView textView = (TextView) cdy_(customView, R.id.title);
        ColorStateList colorStateList = e;
        if (colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
        float f = f8247a;
        if (f != 0.0f) {
            textView.setTextSize(0, f);
        }
    }

    public static void cdD_(ActionBar actionBar, CharSequence charSequence) {
        d = charSequence;
        View customView = actionBar.getCustomView();
        if (customView == null || customView.getTag() == null) {
            return;
        }
        ((TextView) cdy_(customView, R.id.title)).setText(d);
    }

    public static void cdC_(ActionBar actionBar, boolean z) {
        actionBar.setHomeButtonEnabled(d() && z);
    }

    public static void cdB_(ActionBar actionBar, boolean z, View.OnClickListener onClickListener) {
        actionBar.setDisplayHomeAsUpEnabled(d() && z);
        View customView = d() ? actionBar.getCustomView() : cdz_(actionBar);
        if (customView != null && customView.getTag() != null) {
            View cdy_ = cdy_(customView, R.id.btn_start);
            cdy_.setOnClickListener(onClickListener);
            cdy_.setVisibility(z ? 0 : 8);
        }
        c = z;
    }

    public static boolean d() {
        synchronized (d.class) {
            if (b == null) {
                b = Boolean.valueOf(c("com.huawei.android.app.ActionBarEx") && c("huawei.com.android.internal.app.HwActionBarImpl"));
            }
        }
        return b.booleanValue();
    }

    private static boolean c(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static void cdA_(ActionBar actionBar, TextView textView) {
        e = textView.getTextColors();
        f8247a = textView.getTextSize();
        cdE_(actionBar);
    }

    private static View cdz_(ActionBar actionBar) {
        View customView = actionBar.getCustomView();
        if (customView != null) {
            return customView;
        }
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(-1, -1);
        View inflate = LayoutInflater.from(actionBar.getThemedContext()).inflate(R.layout.faq_base_sdk_actionbar_layout, (ViewGroup) new LinearLayout(actionBar.getThemedContext()), false);
        actionBar.setCustomView(inflate, layoutParams);
        TextView textView = (TextView) cdy_(inflate, R.id.title);
        CharSequence charSequence = d;
        if (charSequence == null) {
            charSequence = actionBar.getTitle();
        }
        textView.setText(charSequence);
        cdE_(actionBar);
        cdy_(inflate, R.id.btn_start).setVisibility(c ? 0 : 8);
        return inflate;
    }

    protected static <T extends View> T cdy_(View view, int i) {
        SparseArray sparseArray = (SparseArray) view.getTag();
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            view.setTag(sparseArray);
        }
        T t = (T) sparseArray.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) view.findViewById(i);
        sparseArray.put(i, t2);
        return t2;
    }
}
