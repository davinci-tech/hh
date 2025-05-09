package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.core.widget.NestedScrollView;
import com.huawei.health.R;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.v0;
import com.huawei.openalliance.ad.constant.Constants;
import huawei.android.hwcolorpicker.HwColorPicker;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class o0 {

    /* renamed from: a, reason: collision with root package name */
    public static final Map<Integer, Long> f3943a = new HashMap();
    public static final Set<String> b = new HashSet();
    public static boolean c = false;
    public static final boolean d;
    public static Class<?> e;
    public static Method f;
    public static SparseArray<String> g;

    static {
        boolean z = false;
        try {
            try {
                Class.forName("android.support.v7.widget.RecyclerView");
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            Class.forName("androidx.recyclerview.widget.RecyclerView");
        }
        z = true;
        d = z;
        Set<String> set = b;
        set.add("androidx*appcompat*widget");
        set.add("androidx*cardview*widget");
        set.add("androidx*emoji*widget");
        set.add("com*google*android*material");
        set.add("android*support*design*widget");
        set.add("android*support*text*emoji*widget");
        set.add("android*widget");
        set.add("android*support*v7*widget");
    }

    public static u0 a(Activity activity, View view, JSONObject jSONObject) {
        u0 u0Var;
        if (activity == null || view == null || b.a().b()) {
            return null;
        }
        if (b.a().i(activity.getClass())) {
            return null;
        }
        try {
            q0 a2 = q0.a();
            a2.getClass();
            try {
                u0Var = a2.a(view, false);
            } catch (Exception unused) {
                HiLog.w("HAViewNodeService", "fail to get view node");
                u0Var = null;
            }
            if (u0Var == null) {
                return null;
            }
            if (b.a().i()) {
                HiLog.d("ViewUtils", "viewPath: " + u0Var.e);
                jSONObject.put("$view_path", u0Var.e);
            }
            jSONObject.put("$view_position", u0Var.g);
            return u0Var;
        } catch (Exception e2) {
            HiLog.w("ViewUtils", "fail to acquire view path and selector, ex: " + e2.getMessage());
            return null;
        }
    }

    public static String b(View view) {
        return view == null ? "" : view instanceof CheckBox ? String.valueOf(((CheckBox) view).isChecked()) : view instanceof RadioButton ? String.valueOf(((RadioButton) view).isChecked()) : view instanceof ToggleButton ? String.valueOf(((ToggleButton) view).isChecked()) : view instanceof CompoundButton ? String.valueOf(((CompoundButton) view).isChecked()) : view instanceof CheckedTextView ? String.valueOf(((CheckedTextView) view).isChecked()) : "";
    }

    public static boolean c(Class<?> cls) {
        if (cls == null) {
            return true;
        }
        try {
            List<Class<?>> a2 = b.a().a();
            if (a2 == null) {
                return false;
            }
            Iterator<Class<?>> it = a2.iterator();
            while (it.hasNext()) {
                if (it.next().isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static u0 d(View view) {
        String a2;
        String str;
        String str2;
        String str3;
        Class<?> a3;
        Class<?> a4;
        if (view instanceof CheckBox) {
            a2 = a("", "CheckBox");
            CheckBox checkBox = (CheckBox) view;
            if (!TextUtils.isEmpty(checkBox.getText())) {
                str = checkBox.getText().toString();
                str2 = a2;
                str3 = str;
            }
            str = "";
            str2 = a2;
            str3 = str;
        } else if (view instanceof RadioButton) {
            a2 = a("", "RadioButton");
            RadioButton radioButton = (RadioButton) view;
            if (!TextUtils.isEmpty(radioButton.getText())) {
                str = radioButton.getText().toString();
                str2 = a2;
                str3 = str;
            }
            str = "";
            str2 = a2;
            str3 = str;
        } else {
            if (view instanceof ToggleButton) {
                a2 = a("", "ToggleButton");
                str = a(view);
            } else if (view instanceof CompoundButton) {
                if (view == null) {
                    a2 = "";
                } else {
                    a2 = v0.a().a(view.getClass());
                    Class<?> a5 = n0.a("android.widget.Switch");
                    if (a5 == null || !a5.isInstance(view)) {
                        Class<?> a6 = n0.a("android.support.v7.widget.SwitchCompat");
                        if (a6 == null || !a6.isInstance(view)) {
                            Class<?> a7 = n0.a("androidx.appcompat.widget.SwitchCompat");
                            if (a7 != null && a7.isInstance(view)) {
                                a2 = a(a2, "SwitchCompat");
                            }
                        } else {
                            a2 = a(a2, "SwitchCompat");
                        }
                    } else {
                        a2 = a(a2, "Switch");
                    }
                }
                str = a(view);
            } else if (view instanceof Button) {
                a2 = a("", "Button");
                Button button = (Button) view;
                if (!TextUtils.isEmpty(button.getText())) {
                    str = button.getText().toString();
                }
                str = "";
            } else if (view instanceof CheckedTextView) {
                a2 = a("", "CheckedTextView");
                CheckedTextView checkedTextView = (CheckedTextView) view;
                if (!TextUtils.isEmpty(checkedTextView.getText())) {
                    str = checkedTextView.getText().toString();
                }
                str = "";
            } else if (view instanceof EditText) {
                a2 = a("", "EditText");
                EditText editText = (EditText) view;
                if (!TextUtils.isEmpty(editText.getHint())) {
                    str = editText.getHint().toString();
                }
                str = "";
            } else if (view instanceof TextView) {
                a2 = a("", "TextView");
                TextView textView = (TextView) view;
                if (!TextUtils.isEmpty(textView.getText())) {
                    str = textView.getText().toString();
                }
                str = "";
            } else if (view instanceof ImageView) {
                a2 = a("", "ImageView");
                ImageView imageView = (ImageView) view;
                if (!TextUtils.isEmpty(imageView.getContentDescription())) {
                    str = imageView.getContentDescription().toString();
                }
                str = "";
            } else if (view instanceof RatingBar) {
                a2 = a("", "RatingBar");
                str = String.valueOf(((RatingBar) view).getRating());
            } else if (view instanceof SeekBar) {
                a2 = a("", "SeekBar");
                str = String.valueOf(((SeekBar) view).getProgress());
            } else if (view instanceof Spinner) {
                a2 = a("", "Spinner");
                Spinner spinner = (Spinner) view;
                Object selectedItem = spinner.getSelectedItem();
                if (selectedItem instanceof String) {
                    str = (String) selectedItem;
                } else {
                    View selectedView = spinner.getSelectedView();
                    if (selectedView instanceof TextView) {
                        TextView textView2 = (TextView) selectedView;
                        if (textView2.getText() != null) {
                            str = textView2.getText().toString();
                        }
                    }
                    str2 = a2;
                    str3 = "";
                }
            } else {
                Class<?> a8 = n0.a(new String[]{"android.support.design.widget.TabLayout$TabView", "com.google.android.material.tabs.TabLayout$TabView"});
                Object a9 = (a8 == null || !a8.isAssignableFrom(view.getClass())) ? null : n0.a(a8, view, "mTab", "tab");
                if (a9 != null) {
                    String a10 = a("", "TabLayout");
                    try {
                        a4 = n0.a(new String[]{"android.support.design.widget.TabLayout$Tab", "com.google.android.material.tabs.TabLayout$Tab"});
                    } catch (Exception unused) {
                    }
                    if (a4 == null) {
                        str3 = "";
                        str2 = a10;
                    } else {
                        Object a11 = n0.a(a9, "getText", new Object[0]);
                        str3 = a11 != null ? a11.toString() : "";
                        try {
                            View view2 = (View) n0.a(a4, a9, "mCustomView", "customView");
                            if (view2 != null && (view2 instanceof ViewGroup)) {
                                str3 = a(new StringBuilder(), (ViewGroup) view2);
                                if (!TextUtils.isEmpty(str3)) {
                                    str = str3.substring(0, str3.length() - 1);
                                    a2 = a10;
                                }
                            }
                        } catch (Exception unused2) {
                        }
                        str2 = a10;
                    }
                } else if (n0.a(view, "com.google.android.material.bottomnavigation.BottomNavigationItemView", "android.support.design.internal.NavigationMenuItemView")) {
                    Object a12 = n0.a(view, "getItemData", new Object[0]);
                    if (a12 != null && (a3 = n0.a(new String[]{"androidx.appcompat.view.menu.MenuItemImpl"})) != null) {
                        str = (String) n0.a(a3, a12, "mTitle");
                        a2 = "";
                    }
                    str2 = "";
                    str3 = str2;
                } else if (n0.a(view, "android.support.design.widget.NavigationView", "com.google.android.material.navigation.NavigationView")) {
                    a2 = a("", "NavigationView");
                    str = (view == null || view.getWindowVisibility() == 8 || (!p0.a(view.getClass()) && (view.getWidth() <= 0 || view.getHeight() <= 0 || view.getAlpha() <= 0.0f || !view.getLocalVisibleRect(new Rect()) || ((view.getVisibility() == 0 || view.getAnimation() == null || !view.getAnimation().getFillAfter()) && view.getVisibility() != 0)))) ? "Close" : "Open";
                } else if (view instanceof RadioGroup) {
                    a2 = a("", "RadioGroup");
                    RadioGroup radioGroup = (RadioGroup) view;
                    View findViewById = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                    if (findViewById instanceof RadioButton) {
                        RadioButton radioButton2 = (RadioButton) findViewById;
                        if (radioButton2.getText() != null) {
                            str = radioButton2.getText().toString();
                        }
                    }
                    str2 = a2;
                    str3 = "";
                } else {
                    if (view instanceof ViewGroup) {
                        String a13 = v0.a().a(view.getClass());
                        Class<?> a14 = n0.a("android.support.v7.widget.CardView");
                        if (a14 == null || !a14.isInstance(view)) {
                            Class<?> a15 = n0.a("androidx.cardview.widget.CardView");
                            if (a15 == null || !a15.isInstance(view)) {
                                Class<?> a16 = n0.a("android.support.design.widget.NavigationView");
                                if (a16 == null || !a16.isInstance(view)) {
                                    Class<?> a17 = n0.a("com.google.android.material.navigation.NavigationView");
                                    a2 = (a17 == null || !a17.isInstance(view)) ? a13 : a(a13, "NavigationView");
                                } else {
                                    a2 = a(a13, "NavigationView");
                                }
                            } else {
                                a2 = a(a13, "CardView");
                            }
                        } else {
                            a2 = a(a13, "CardView");
                        }
                        String obj = TextUtils.isEmpty(view.getContentDescription()) ? "" : view.getContentDescription().toString();
                        if (TextUtils.isEmpty(obj)) {
                            obj = a(new StringBuilder(), (ViewGroup) view);
                            if (!TextUtils.isEmpty(obj)) {
                                str = obj.substring(0, obj.length() - 1);
                            }
                        }
                        str = obj;
                    }
                    str2 = "";
                    str3 = str2;
                }
            }
            str2 = a2;
            str3 = str;
        }
        if (TextUtils.isEmpty(str3) && (view instanceof TextView)) {
            TextView textView3 = (TextView) view;
            str3 = TextUtils.isEmpty(textView3.getHint()) ? "" : textView3.getHint().toString();
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = TextUtils.isEmpty(view.getContentDescription()) ? "" : view.getContentDescription().toString();
        }
        v0 a18 = v0.a();
        a18.getClass();
        if (view != null && !TextUtils.isEmpty(str2)) {
            String valueOf = String.valueOf(view.hashCode());
            v0.a aVar = a18.f3955a.get(valueOf);
            if (aVar == null) {
                aVar = new v0.a();
            }
            a18.f3955a.put(valueOf, aVar);
        }
        return new u0(null, null, str2, str3, null, null, null, false, b(view));
    }

    public static String e(View view) {
        int id;
        Context context;
        Resources resources;
        if (view == null) {
            return "";
        }
        String str = (String) view.getTag(R.id.hianalytics_view_id_tag);
        if (TextUtils.isEmpty(str) && (id = view.getId()) != -1 && ((-16777216) & id) != 0 && (id & HwColorPicker.MASK_RESULT_STATE) != 0 && (context = view.getContext()) != null && (resources = context.getResources()) != null) {
            str = resources.getResourceEntryName(view.getId());
        }
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static u0 f(View view) {
        int indexOf;
        ArrayList arrayList = new ArrayList();
        arrayList.add(view);
        for (ViewParent parent = view.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            arrayList.add((ViewGroup) parent);
        }
        View view2 = (View) arrayList.get(arrayList.size() - 1);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if (!(view2 instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view2;
        String str = "";
        String str2 = "";
        for (int size = arrayList.size() - 2; size >= 0; size--) {
            View view3 = (View) arrayList.get(size);
            u0 a2 = a(view3, viewGroup.indexOfChild(view3));
            if (a2 != null) {
                if (!TextUtils.isEmpty(a2.e) && a2.e.contains(Constants.LINK) && !TextUtils.isEmpty(str2) && (indexOf = sb.indexOf(Constants.LINK)) != -1) {
                    sb.replace(indexOf, indexOf + 1, str2);
                }
                sb2.append(a2.f);
                sb.append(a2.e);
                str2 = a2.g;
                str = a2.d;
            }
            if (!(view3 instanceof ViewGroup)) {
                break;
            }
            viewGroup = (ViewGroup) view3;
        }
        return new u0(view, e(view), "", str, sb.toString(), sb2.toString(), str2, false, b(view));
    }

    public static boolean g(View view) {
        if (view == null) {
            return true;
        }
        try {
            List<Class<?>> a2 = b.a().a();
            if (a2 != null) {
                Iterator<Class<?>> it = a2.iterator();
                while (it.hasNext()) {
                    if (it.next().isAssignableFrom(view.getClass())) {
                        return true;
                    }
                }
            }
            return "disabled".equals(view.getTag(R.id.hianalytics_view_disabled_user_tag));
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean h(View view) {
        if (view != null && view.getWindowVisibility() != 8) {
            if (p0.a(view.getClass())) {
                return true;
            }
            boolean localVisibleRect = view.getLocalVisibleRect(new Rect());
            if (view.getWidth() > 0 && view.getHeight() > 0 && view.getAlpha() > 0.0f && localVisibleRect && ((view.getVisibility() != 0 && view.getAnimation() != null && view.getAnimation().getFillAfter()) || view.getVisibility() == 0)) {
                return true;
            }
        }
        return false;
    }

    public static boolean i(View view) {
        if (view == null || !h(view)) {
            return false;
        }
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            if (!h((View) parent) || (parent = parent.getParent()) == null) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0123 A[Catch: Exception -> 0x0139, TryCatch #2 {Exception -> 0x0139, blocks: (B:18:0x011d, B:20:0x0123, B:22:0x0127, B:27:0x0130), top: B:17:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0130 A[Catch: Exception -> 0x0139, TRY_LEAVE, TryCatch #2 {Exception -> 0x0139, blocks: (B:18:0x011d, B:20:0x0123, B:22:0x0127, B:27:0x0130), top: B:17:0x011d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(android.view.View r5) {
        /*
            Method dump skipped, instructions count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.o0.c(android.view.View):java.lang.String");
    }

    public static String b(Class<?> cls) {
        String str;
        v0 a2 = v0.a();
        a2.getClass();
        if (cls == null) {
            str = "";
        } else {
            String valueOf = String.valueOf(cls.hashCode());
            String str2 = a2.c.get(valueOf);
            if (TextUtils.isEmpty(str2)) {
                str2 = cls.getSimpleName();
                if (TextUtils.isEmpty(str2)) {
                    str2 = "NA";
                }
                a2.c.put(valueOf, str2);
            }
            str = str2;
        }
        if (!d && !c && str != null && str.contains("RecyclerView")) {
            try {
                if (a(cls) != null && f != null) {
                    e = cls;
                    c = true;
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }

    public static boolean a() {
        if (b.a().b()) {
            return true;
        }
        return b.a().a(EventType.VIEW_CLICK);
    }

    public static String a(StringBuilder sb, ViewGroup viewGroup) {
        if (viewGroup == null) {
            return sb.toString();
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null && childAt.getVisibility() == 0) {
                if (childAt instanceof ViewGroup) {
                    a(sb, (ViewGroup) childAt);
                } else if (!g(childAt)) {
                    String c2 = c(childAt);
                    if (!TextUtils.isEmpty(c2)) {
                        sb.append(c2);
                        sb.append(Constants.LINK);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static boolean b(Object obj) {
        return n0.a(obj, "androidx.appcompat.view.menu.ListMenuItemView");
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String replace = str.replace(".", "*");
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            if (replace.startsWith(it.next())) {
                return str2;
            }
        }
        return str;
    }

    public static boolean a(View view, boolean z) {
        if ((view instanceof CheckBox) || (view instanceof RadioButton) || (view instanceof ToggleButton) || (view instanceof CompoundButton) || (view instanceof RatingBar)) {
            return z;
        }
        return true;
    }

    public static Class<?> a(Class<?> cls) {
        while (cls != null && !cls.equals(ViewGroup.class)) {
            try {
                f = cls.getMethod("getChildAdapterPosition", View.class);
            } catch (NoSuchMethodException unused) {
            }
            if (f == null) {
                try {
                    f = cls.getMethod("getChildPosition", View.class);
                } catch (NoSuchMethodException unused2) {
                }
            }
            if (f != null) {
                return cls;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static boolean c(Object obj) {
        return n0.a(obj, "androidx.appcompat.view.menu.ActionMenuItem");
    }

    /* JADX WARN: Code restructure failed: missing block: B:135:0x00a9, code lost:
    
        if (r0 >= 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0113, code lost:
    
        if (android.text.TextUtils.isEmpty(r11) == false) goto L63;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0209  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:130:0x00a8 -> B:128:0x00a9). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hianalytics.visual.u0 a(android.view.View r18, int r19) {
        /*
            Method dump skipped, instructions count: 898
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.o0.a(android.view.View, int):com.huawei.hianalytics.visual.u0");
    }

    public static boolean d(Object obj) {
        Class<?> cls;
        boolean a2 = n0.a(obj, "android.support.v7.widget.RecyclerView", "androidx.recyclerview.widget.RecyclerView");
        return !a2 ? c && obj != null && (cls = e) != null && cls.isAssignableFrom(obj.getClass()) : a2;
    }

    public static String a(View view) {
        Method method;
        try {
            if (((CompoundButton) view).isChecked()) {
                method = view.getClass().getMethod("getTextOn", new Class[0]);
            } else {
                method = view.getClass().getMethod("getTextOff", new Class[0]);
            }
            Object invoke = method.invoke(view, new Object[0]);
            return invoke != null ? (String) invoke : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replaceAll("[0-9]", "*");
    }

    public static List<View> a(ViewGroup viewGroup) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null) {
                arrayList.add(childAt);
            }
            if (childAt instanceof ViewGroup) {
                arrayList.addAll(a((ViewGroup) childAt));
            }
        }
        return arrayList;
    }

    public static String a(List<View> list, Activity activity) {
        int i;
        int i2;
        int i3 = activity.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        Iterator<View> it = list.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                i2 = i3;
                break;
            }
            View next = it.next();
            if (next instanceof ScrollView) {
                ScrollView scrollView = (ScrollView) next;
                if (scrollView.getChildAt(0) != null && scrollView.getChildAt(0).getMeasuredHeight() >= i3) {
                    i2 = scrollView.getChildAt(0).getMeasuredHeight();
                    if (next.getTag(R.id.hianalytics_scroll_view_tag) != null) {
                        i = ((Integer) next.getTag(R.id.hianalytics_scroll_view_tag)).intValue();
                    }
                }
            }
            if (next instanceof NestedScrollView) {
                NestedScrollView nestedScrollView = (NestedScrollView) next;
                if (nestedScrollView.getChildAt(0) != null && nestedScrollView.getChildAt(0).getMeasuredHeight() >= i3) {
                    i2 = nestedScrollView.getChildAt(0).getMeasuredHeight();
                    if (next.getTag(R.id.hianalytics_scroll_view_tag) != null) {
                        i = ((Integer) next.getTag(R.id.hianalytics_scroll_view_tag)).intValue();
                    }
                }
            }
        }
        if (i2 == 0) {
            return "";
        }
        float f2 = ((i3 + i) * 100.0f) / i2;
        try {
            f2 = Float.parseFloat(new DecimalFormat("#.##").format(f2));
        } catch (NumberFormatException unused) {
            HiLog.i("ViewUtils", "format percent 2 digit failed, direct use false");
        }
        return f2 + "%";
    }

    public static g a(Object obj, View view, Class<?> cls) {
        if (c(cls)) {
            return null;
        }
        Context context = obj instanceof Context ? (Context) obj : null;
        if (view != null) {
            if (g(view)) {
                return null;
            }
            if (context == null && (context = view.getContext()) == null) {
                return null;
            }
        }
        Activity a2 = h0.a(context, view);
        if (a2 != null) {
            if (b.a().i(a2.getClass())) {
                return null;
            }
        }
        Object a3 = f0.a(view, a2);
        if (a3 != null) {
            if (b.a().h(a3.getClass())) {
                return null;
            }
        }
        return new g(view, a2, a3);
    }

    public static boolean a(Object obj) {
        Long l;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Map<Integer, Long> map = f3943a;
        if (map.containsKey(Integer.valueOf(obj.hashCode())) && (l = map.get(Integer.valueOf(obj.hashCode()))) != null) {
            map.put(Integer.valueOf(obj.hashCode()), Long.valueOf(elapsedRealtime));
            return elapsedRealtime - l.longValue() < 500;
        }
        map.put(Integer.valueOf(obj.hashCode()), Long.valueOf(elapsedRealtime));
        return false;
    }

    public static boolean a(View view, Class<?> cls) {
        if (g(view)) {
            return true;
        }
        if (cls != null) {
            return c(cls);
        }
        return a((Object) view);
    }
}
