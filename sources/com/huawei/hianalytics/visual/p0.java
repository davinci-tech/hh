package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TabHost;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public class p0 {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f3944a = false;
    public static Field b = null;
    public static boolean c = false;
    public static boolean d = false;
    public static Object e;
    public static Class<?> f;
    public static Method g;
    public static Class<?> h;
    public static Class<?> i;

    public static boolean a(Class<?> cls) {
        if (!f3944a) {
            c();
        }
        return h == cls || i == cls;
    }

    public static View[] b() {
        View[] a2 = a();
        if (a2.length == 0) {
            return new View[0];
        }
        Arrays.sort(a2, new Comparator() { // from class: com.huawei.hianalytics.visual.p0$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return p0.a((View) obj, (View) obj2);
            }
        });
        return a2;
    }

    public static void c() {
        if (f3944a) {
            return;
        }
        try {
            Class<?> cls = Class.forName("android.view.WindowManagerGlobal");
            Field declaredField = cls.getDeclaredField("mViews");
            b = declaredField;
            declaredField.setAccessible(true);
            if (b.getType() == ArrayList.class) {
                c = true;
            } else if (b.getType() == View[].class) {
                d = true;
            }
            Field declaredField2 = cls.getDeclaredField("sDefaultWindowManager");
            declaredField2.setAccessible(true);
            e = declaredField2.get(null);
        } catch (Exception unused) {
        }
        try {
            f = Class.forName("com.android.internal.view.menu.ListMenuItemView");
            g = Class.forName("com.android.internal.view.menu.MenuView$ItemView").getDeclaredMethod("getItemData", new Class[0]);
        } catch (Exception unused2) {
        }
        try {
            try {
                h = Class.forName("com.android.internal.policy.PhoneWindow$DecorView");
            } catch (Exception unused3) {
            }
        } catch (Exception unused4) {
            h = Class.forName("com.android.internal.policy.DecorView");
        }
        try {
            i = Class.forName("android.widget.PopupWindow$PopupDecorView");
        } catch (Exception unused5) {
        }
        f3944a = true;
    }

    public static int a(View view, View view2) {
        int hashCode = view.hashCode();
        int hashCode2 = view2.hashCode();
        u a2 = u.a();
        int i2 = a2.f3952a;
        if (i2 == -1) {
            Activity b2 = v.c.b();
            if (b2 == null) {
                i2 = a2.f3952a;
            } else {
                if (b2.getWindow() != null && b2.getWindow().isActive()) {
                    a2.f3952a = b2.getWindow().getDecorView().hashCode();
                }
                i2 = a2.f3952a;
            }
        }
        if (hashCode == i2) {
            return -1;
        }
        if (hashCode2 == i2) {
            return 1;
        }
        return (view2.getWidth() * view2.getHeight()) - (view.getWidth() * view.getHeight());
    }

    public static View[] a() {
        View[] viewArr;
        Object obj = e;
        if (obj == null) {
            u.a().getClass();
            Activity b2 = v.c.b();
            return b2 == null ? new View[0] : new View[]{(b2 == null || b2.getWindow() == null || !b2.getWindow().isActive()) ? null : b2.getWindow().getDecorView()};
        }
        View[] viewArr2 = new View[0];
        if (c) {
            viewArr = (View[]) ((List) Objects.requireNonNull(b.get(obj))).toArray(viewArr2);
        } else {
            if (d) {
                viewArr = (View[]) b.get(obj);
            }
            if (viewArr2 != null || viewArr2.length == 0) {
                return new View[0];
            }
            ArrayList arrayList = new ArrayList();
            for (View view : viewArr2) {
                if (view != null) {
                    arrayList.add(view);
                }
            }
            return (View[]) arrayList.toArray(new View[arrayList.size()]);
        }
        viewArr2 = viewArr;
        if (viewArr2 != null) {
        }
        return new View[0];
    }

    public static View a(View view, MenuItem menuItem) {
        Object obj;
        View view2;
        if (o0.c(menuItem) && menuItem.getItemId() == 16908332 && n0.a(view.getParent(), "androidx.appcompat.widget.Toolbar", "android.support.v7.widget.Toolbar", "android.widget.Toolbar") && (view instanceof ImageButton) && (view2 = (View) n0.a(n0.a(new String[]{"androidx.appcompat.widget.Toolbar", "android.support.v7.widget.Toolbar", "android.widget.Toolbar"}), view.getParent(), "mNavButtonView")) != null && view2 == view) {
            return view;
        }
        if (f == view.getClass()) {
            obj = g.invoke(view, new Object[0]);
        } else {
            if (o0.b((Object) view) || n0.a(view, "android.support.v7.view.menu.ListMenuItemView") || n0.a(view, "com.google.android.material.bottomnavigation.BottomNavigationItemView", "android.support.design.internal.NavigationMenuItemView")) {
                obj = n0.a(view, "getItemData", new Object[0]);
            }
            obj = null;
        }
        if (obj == menuItem) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View a2 = a(viewGroup.getChildAt(i2), menuItem);
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return null;
    }

    public static View a(View view, String str) {
        String str2 = view instanceof TabHost ? (String) n0.a(view, "getCurrentTabTag", new Object[0]) : "";
        if (!TextUtils.isEmpty(str) && str.equals(str2)) {
            return (View) n0.a(view, "getCurrentTabView", new Object[0]);
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View a2 = a(viewGroup.getChildAt(i2), str);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }
}
