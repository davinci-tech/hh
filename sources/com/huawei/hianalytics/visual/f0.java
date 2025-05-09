package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import com.huawei.health.R;
import com.huawei.hianalytics.core.log.HiLog;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class f0 {

    /* renamed from: a, reason: collision with root package name */
    public static final LruCache<String, WeakReference<Object>> f3917a = new LruCache<>(128);

    public static Object a(View view, Activity activity) {
        Window window;
        if (view == null) {
            return null;
        }
        try {
            String str = (String) view.getTag(R.id.hianalytics_fragment_view_sdk_tag);
            String str2 = (String) view.getTag(R.id.hianalytics_fragment_view_user_tag);
            if (str2 != null && !str2.isEmpty()) {
                str = str2;
            }
            if (TextUtils.isEmpty(str)) {
                if (activity == null) {
                    activity = h0.a(view.getContext(), view);
                }
                if (activity != null && (window = activity.getWindow()) != null && window.isActive() && window.getDecorView() != null && window.getDecorView().getRootView() != null && window.getDecorView().getRootView().getTag(R.id.hianalytics_fragment_view_sdk_tag) != null) {
                    str = a(view);
                }
            }
            return a(str);
        } catch (Exception e) {
            HiLog.w("FragmentUtils", "fail to get fragment from view, ex: " + e.getMessage());
            return null;
        }
    }

    public static boolean b(Object obj) {
        if (obj == null) {
            return true;
        }
        try {
            if (obj.getClass().getMethod("isHidden", new Class[0]).invoke(obj, new Object[0]) == null) {
                return true;
            }
            return !((Boolean) r5).booleanValue();
        } catch (Exception e) {
            HiLog.w("FragmentUtils", "fail to get hidden value, ex: " + e.getMessage());
            return true;
        }
    }

    public static boolean c(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            Object invoke = obj.getClass().getMethod("isResumed", new Class[0]).invoke(obj, new Object[0]);
            if (invoke == null) {
                return false;
            }
            return ((Boolean) invoke).booleanValue();
        } catch (Exception e) {
            HiLog.w("FragmentUtils", "fail to get resumed value, ex: " + e.getMessage());
            return false;
        }
    }

    public static boolean d(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            Object invoke = obj.getClass().getMethod("getUserVisibleHint", new Class[0]).invoke(obj, new Object[0]);
            if (invoke == null) {
                return false;
            }
            return ((Boolean) invoke).booleanValue();
        } catch (Exception e) {
            HiLog.w("FragmentUtils", "fail to get userVisibleHint value, ex: " + e.getMessage());
            return false;
        }
    }

    public static boolean e(Object obj) {
        Class<?> cls;
        Class<?> cls2;
        if (obj == null) {
            return false;
        }
        Class<?> cls3 = null;
        try {
            try {
                cls = Class.forName("android.app.Fragment");
            } catch (Exception e) {
                HiLog.w("FragmentUtils", "load class for android fragment, ex: " + e.getMessage());
                cls = null;
            }
            try {
                cls2 = Class.forName("android.support.v4.app.Fragment");
            } catch (Exception e2) {
                HiLog.w("FragmentUtils", "load class for support fragment, ex: " + e2.getMessage());
                cls2 = null;
            }
            try {
                cls3 = Class.forName("androidx.fragment.app.Fragment");
            } catch (Exception e3) {
                HiLog.w("FragmentUtils", "load class for support fragment, ex: " + e3.getMessage());
            }
        } catch (Exception e4) {
            HiLog.w("FragmentUtils", "load class for fragment, ex: " + e4.getMessage());
        }
        if (cls == null && cls2 == null && cls3 == null) {
            return false;
        }
        if (cls != null && cls.isInstance(obj)) {
            return true;
        }
        if (cls2 != null && cls2.isInstance(obj)) {
            return true;
        }
        if (cls3 != null) {
            if (cls3.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    public static boolean f(Object obj) {
        Object obj2;
        if (obj == null) {
            return false;
        }
        try {
            obj2 = obj.getClass().getMethod("getParentFragment", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception e) {
            HiLog.w("FragmentUtils", "fail to get parent fragment, ex: " + e.getMessage());
            obj2 = null;
        }
        try {
            if (obj2 == null) {
                if (c(obj) && d(obj) && b(obj)) {
                    return true;
                }
            } else if (c(obj) && d(obj) && b(obj) && c(obj2) && d(obj2) && b(obj2)) {
                return true;
            }
        } catch (Exception e2) {
            HiLog.w("FragmentUtils", "fail to get visible value, ex: " + e2.getMessage());
        }
        return false;
    }

    public static void a(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f3917a.put(str, new WeakReference<>(obj));
    }

    public static Object a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            LruCache<String, WeakReference<Object>> lruCache = f3917a;
            WeakReference<Object> weakReference = lruCache.get(str);
            if (weakReference == null) {
                return null;
            }
            Object obj = weakReference.get();
            if (obj != null) {
                return obj;
            }
            Object newInstance = Class.forName(str).newInstance();
            lruCache.put(str, new WeakReference<>(newInstance));
            return newInstance;
        } catch (Exception e) {
            HiLog.w("FragmentUtils", "fail to get fragment from cache, ex: " + e.getMessage());
            return null;
        }
    }

    public static JSONObject a(Object obj, Activity activity) {
        String str;
        String str2;
        JSONObject jSONObject = new JSONObject();
        if (obj == null) {
            return jSONObject;
        }
        if (activity == null) {
            activity = a(obj);
        }
        if (activity != null) {
            str = h0.a(activity);
            str2 = String.format(Locale.ENGLISH, "%s|%s", activity.getClass().getCanonicalName(), obj.getClass().getCanonicalName());
        } else {
            str = "";
            str2 = "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = obj.getClass().getCanonicalName();
        }
        try {
            jSONObject.put("$page_title", str);
            jSONObject.put("$page_name", str2);
        } catch (Exception e) {
            HiLog.w("FragmentUtils", "fail to acquire fragment name and title, ex: " + e.getMessage());
        }
        return jSONObject;
    }

    public static Activity a(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            Method method = obj.getClass().getMethod("getActivity", new Class[0]);
            if (method != null) {
                return (Activity) method.invoke(obj, new Object[0]);
            }
        } catch (Exception e) {
            HiLog.w("FragmentUtils", "fail to acquire activity from fragment: " + obj.getClass() + ", ex: " + e.getMessage());
        }
        return null;
    }

    public static String a(View view) {
        String str;
        String str2 = "";
        try {
            ViewParent parent = view.getParent();
            while (TextUtils.isEmpty(str2) && (parent instanceof View)) {
                str = (String) ((View) parent).getTag(R.id.hianalytics_fragment_view_sdk_tag);
                try {
                    parent = parent.getParent();
                    str2 = str;
                } catch (Exception e) {
                    e = e;
                    HiLog.w("FragmentUtils", "fail to get fragment name from parent, ex: " + e.getMessage());
                    return str;
                }
            }
            return str2;
        } catch (Exception e2) {
            e = e2;
            str = str2;
        }
    }
}
