package com.huawei.haf.common.utils;

import android.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes.dex */
public final class ViewUtils {
    private ViewUtils() {
    }

    public static void xQ_(Activity activity) {
        if (activity == null) {
            return;
        }
        if (!activity.isFinishing() || !activity.isDestroyed()) {
            LogUtil.a("HAF_ViewUtils", "removeAllViews, activity not finish or destroy");
            return;
        }
        int xP_ = xP_(activity.findViewById(R.id.content), true);
        if (xP_ > 0) {
            LogUtil.c("HAF_ViewUtils", "removeAllViews, num=", Integer.valueOf(xP_), ", ", activity.getClass().getName());
        }
    }

    public static int xP_(View view, boolean z) {
        if (view == null) {
            return 0;
        }
        if (z) {
            xS_(view, false);
        }
        if (!(view instanceof ViewGroup) || (view instanceof AdapterView)) {
            return 0;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            childCount += xP_(viewGroup.getChildAt(i), z);
        }
        try {
            viewGroup.removeAllViews();
        } catch (Exception e) {
            LogUtil.a("HAF_ViewUtils", "removeAllViews ", viewGroup.getClass().getName(), ", ex=", LogUtil.a(e));
        }
        return childCount;
    }

    public static void xS_(View view, boolean z) {
        if (view == null) {
            return;
        }
        Drawable background = view.getBackground();
        if (background != null) {
            background.setCallback(null);
        }
        view.setBackground(null);
        if (view instanceof ImageView) {
            xT_((ImageView) view, z);
        }
    }

    private static void xT_(ImageView imageView, boolean z) {
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            return;
        }
        drawable.setCallback(null);
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            imageView.setImageBitmap(null);
            if (!z || bitmap == null) {
                return;
            }
            bitmap.recycle();
            return;
        }
        imageView.setImageDrawable(null);
    }

    public static void xO_(Activity activity) {
        int e;
        if (activity == null) {
            return;
        }
        if (!activity.isFinishing() || !activity.isDestroyed()) {
            LogUtil.a("HAF_ViewUtils", "removeAllFragments, activity not finish or destroy");
            return;
        }
        if ((activity instanceof FragmentActivity) && (e = e(((FragmentActivity) activity).getSupportFragmentManager(), 1)) > 0) {
            LogUtil.c("HAF_ViewUtils", "removeAllFragments new, num=", Integer.valueOf(e), ", ", activity.getClass().getName());
        }
        int xR_ = xR_(activity.getFragmentManager(), 1);
        if (xR_ > 1) {
            LogUtil.c("HAF_ViewUtils", "removeAllFragments old, num=", Integer.valueOf(xR_), ", ", activity.getClass().getName());
        }
    }

    private static int e(FragmentManager fragmentManager, int i) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return 0;
        }
        int size = fragments.size();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            size += e(fragment.getChildFragmentManager(), i + 1);
            beginTransaction.remove(fragment);
            LogUtil.d("HAF_ViewUtils", "removeFragments new, layer=", Integer.valueOf(i), ", ", fragment);
        }
        beginTransaction.commitNowAllowingStateLoss();
        return size;
    }

    private static int xR_(android.app.FragmentManager fragmentManager, int i) {
        List<android.app.Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return 0;
        }
        int size = fragments.size();
        android.app.FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        for (android.app.Fragment fragment : fragments) {
            beginTransaction.remove(fragment);
            LogUtil.d("HAF_ViewUtils", "removeFragments old, layer=", Integer.valueOf(i), ", ", fragment);
        }
        beginTransaction.commitNowAllowingStateLoss();
        return size;
    }
}
