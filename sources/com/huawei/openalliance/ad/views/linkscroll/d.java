package com.huawei.openalliance.ad.views.linkscroll;

import android.view.View;
import android.view.ViewParent;

/* loaded from: classes5.dex */
public class d {
    public static void b(ViewParent viewParent, View view, View view2, int i) {
        if (viewParent instanceof b) {
            ((b) viewParent).b(view, view2, i);
        }
    }

    public static boolean a(ViewParent viewParent, View view, View view2, int i) {
        if (viewParent instanceof b) {
            return ((b) viewParent).a(view, view2, i);
        }
        return false;
    }

    public static boolean a(ViewParent viewParent, View view, float f, float f2) {
        if (viewParent instanceof b) {
            return ((b) viewParent).a(view, f, f2);
        }
        return false;
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
        if (viewParent instanceof b) {
            ((b) viewParent).a(view, i, i2, iArr);
        }
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
        if (viewParent instanceof b) {
            ((b) viewParent).a(view, i, i2, i3, i4);
        }
    }

    public static void a(ViewParent viewParent, View view) {
        if (viewParent instanceof b) {
            ((b) viewParent).a(view);
        }
    }
}
