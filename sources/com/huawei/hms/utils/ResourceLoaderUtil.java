package com.huawei.hms.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;

/* loaded from: classes4.dex */
public abstract class ResourceLoaderUtil {

    /* renamed from: a, reason: collision with root package name */
    private static Context f6152a;
    private static String b;

    public static int getAnimId(String str) {
        Context context = f6152a;
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, "anim", b);
    }

    public static int getColorId(String str) {
        Context context = f6152a;
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, "color", b);
    }

    public static int getDimenId(String str) {
        Context context = f6152a;
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, "dimen", b);
    }

    public static Drawable getDrawable(String str) {
        Context context = f6152a;
        if (context == null) {
            return null;
        }
        return context.getResources().getDrawable(getDrawableId(str));
    }

    public static int getDrawableId(String str) {
        Context context = f6152a;
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, "drawable", b);
    }

    public static int getIdId(String str) {
        Context context = f6152a;
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, "id", b);
    }

    public static int getLayoutId(String str) {
        Context context = f6152a;
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, CommonUtil.LAYOUT, b);
    }

    public static String getString(String str) {
        Context context = f6152a;
        return context == null ? "" : context.getResources().getString(getStringId(str));
    }

    public static int getStringId(String str) {
        Context context = f6152a;
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, "string", b);
    }

    public static int getStyleId(String str) {
        Context context = f6152a;
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, TemplateStyleRecord.STYLE, b);
    }

    public static Context getmContext() {
        return f6152a;
    }

    public static void setmContext(Context context) {
        f6152a = context;
        if (context != null) {
            b = context.getPackageName();
        } else {
            b = null;
            HMSLog.e("ResourceLoaderUtil", "context is null");
        }
    }

    public static String getString(String str, Object... objArr) {
        Context context = f6152a;
        return context == null ? "" : context.getResources().getString(getStringId(str), objArr);
    }
}
