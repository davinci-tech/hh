package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Locale;

/* loaded from: classes5.dex */
public class dk {
    public static View d(Context context) {
        if (context == null) {
            return null;
        }
        View view = new View(context);
        view.setBackgroundResource(R.color._2131297893_res_0x7f090665);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        return view;
    }

    public static int c(Context context) {
        return a(context).heightPixels;
    }

    public static Drawable b(Context context, int i) {
        return a(context.getResources().getDrawable(i));
    }

    public static int b(Context context) {
        return a(context).widthPixels;
    }

    public static void a(View view, View view2) {
        if (view == null || view2 == null) {
            return;
        }
        view.setPaddingRelative(view2.getPaddingStart(), view2.getPaddingTop(), view2.getPaddingEnd(), view2.getPaddingBottom());
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        if (layoutParams != null) {
            view.setLayoutParams(layoutParams);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                a(childAt, childAt == null ? null : view2.findViewById(childAt.getId()));
            }
        }
    }

    public static DisplayMetrics a(Context context) {
        WindowManager windowManager;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context != null && (windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)) != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }

    public static Drawable a(Drawable drawable) {
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            drawable.setLayoutDirection(1);
        }
        return drawable;
    }

    public static float a(Context context, int i) {
        return i * a(context).density;
    }
}
