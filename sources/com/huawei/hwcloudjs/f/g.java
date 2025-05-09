package com.huawei.hwcloudjs.f;

import android.content.Context;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import java.lang.reflect.Field;

/* loaded from: classes9.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6226a = "ReflectUtils";

    public static final int i(Context context, String str) {
        return a(context, a.RES_TYPE_STYLE, str);
    }

    public static final int h(Context context, String str) {
        return a(context, a.RES_TYPE_STRING, str);
    }

    public static final int g(Context context, String str) {
        return a(context, a.RES_TYPE_MENU, str);
    }

    public static final int f(Context context, String str) {
        return a(context, a.RES_TYPE_LAYOUT, str);
    }

    public static final int e(Context context, String str) {
        return a(context, a.RES_TYPE_ID, str);
    }

    public enum a {
        RES_TYPE_ID("id"),
        RES_TYPE_LAYOUT(CommonUtil.LAYOUT),
        RES_TYPE_STRING("string"),
        RES_TYPE_DRAWABLE("drawable"),
        RES_TYPE_STYLE(TemplateStyleRecord.STYLE),
        RES_TYPE_COLOR("color"),
        RES_TYPE_DIMEN("dimen"),
        RES_TYPE_MENU("menu"),
        RES_TYPE_ARRAY("array");

        private String k;

        @Override // java.lang.Enum
        public String toString() {
            return this.k;
        }

        a(String str) {
            this.k = str;
        }
    }

    public static final int d(Context context, String str) {
        return a(context, a.RES_TYPE_DRAWABLE, str);
    }

    public static final int c(Context context, String str) {
        return a(context, a.RES_TYPE_DIMEN, str);
    }

    public static final int b(Context context, String str) {
        return a(context, a.RES_TYPE_COLOR, str);
    }

    public static final int a(Context context, String str) {
        return a(context, a.RES_TYPE_ARRAY, str);
    }

    public static final int a(Context context, a aVar, String str) {
        StringBuilder sb;
        try {
            Field field = Class.forName(context.getPackageName() + ".R$" + aVar.toString()).getField(str);
            return Integer.parseInt(field.get(field.getName()).toString());
        } catch (ClassNotFoundException unused) {
            sb = new StringBuilder("reflect ClassNotFoundException! resourceType=");
            sb.append(aVar);
            sb.append("--resourceName=");
            sb.append(str);
            d.b(f6226a, sb.toString(), true);
            return 0;
        } catch (IllegalAccessException unused2) {
            sb = new StringBuilder("reflect ClassNotFoundException! resourceType=");
            sb.append(aVar);
            sb.append("--resourceName=");
            sb.append(str);
            d.b(f6226a, sb.toString(), true);
            return 0;
        } catch (NoSuchFieldException unused3) {
            sb = new StringBuilder("reflect ClassNotFoundException! resourceType=");
            sb.append(aVar);
            sb.append("--resourceName=");
            sb.append(str);
            d.b(f6226a, sb.toString(), true);
            return 0;
        }
    }
}
