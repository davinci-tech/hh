package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.IllegalFormatConversionException;
import java.util.Locale;
import java.util.MissingFormatArgumentException;

/* loaded from: classes6.dex */
public class nsf {
    public static int b(String str, String str2, Class cls) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("ResourcesUtils", "getStringResourcesId name ", str, " resourceType ", str2);
            return -1;
        }
        Context e = BaseApplication.e();
        int identifier = e.getResources().getIdentifier(str, str2, e.getPackageName());
        if (identifier > 0) {
            return identifier;
        }
        if (cls == null) {
            LogUtil.h("ResourcesUtils", "getStringResourcesId clazz is null resourceType ", str2);
            return -1;
        }
        try {
            try {
                return cls.getDeclaredField(str).getInt(cls);
            } catch (ExceptionInInitializerError | IllegalAccessException | IllegalArgumentException e2) {
                LogUtil.b("ResourcesUtils", "getStringResourcesId getInt exception ", ExceptionUtils.d(e2));
                return -1;
            }
        } catch (NoSuchFieldException | SecurityException e3) {
            LogUtil.b("ResourcesUtils", "getStringResourcesId getDeclaredField exception ", ExceptionUtils.d(e3));
            return -1;
        }
    }

    public static View cKr_(Context context, int i, ViewGroup viewGroup) {
        if (context == null) {
            LogUtil.h("ResourcesUtils", "inflate context is null");
            return null;
        }
        try {
            return View.inflate(context, i, viewGroup);
        } catch (Resources.NotFoundException | InflateException e) {
            LogUtil.b("ResourcesUtils", "inflate exception ", ExceptionUtils.d(e));
            return null;
        }
    }

    public static View cKs_(Context context, int i, ViewGroup viewGroup, boolean z) {
        if (context == null) {
            LogUtil.h("ResourcesUtils", "inflate context is null");
            return null;
        }
        try {
            return LayoutInflater.from(context).inflate(i, viewGroup, z);
        } catch (Resources.NotFoundException | InflateException e) {
            LogUtil.b("ResourcesUtils", "inflate exception ", ExceptionUtils.d(e));
            return null;
        }
    }

    public static float a(int i) {
        try {
            return BaseApplication.e().getResources().getDimension(i);
        } catch (Resources.NotFoundException e) {
            LogUtil.b("ResourcesUtils", "getDimension exception ", ExceptionUtils.d(e));
            return 0.0f;
        }
    }

    public static int b(int i) {
        try {
            return BaseApplication.e().getResources().getDimensionPixelSize(i);
        } catch (Resources.NotFoundException e) {
            LogUtil.b("ResourcesUtils", "getDimensionPixelSize exception ", ExceptionUtils.d(e));
            return 0;
        }
    }

    public static int e(int i) {
        try {
            return BaseApplication.e().getResources().getDimensionPixelOffset(i);
        } catch (Resources.NotFoundException e) {
            LogUtil.b("ResourcesUtils", "getDimensionPixelOffset exception ", ExceptionUtils.d(e));
            return 0;
        }
    }

    public static int c(int i) {
        try {
            return ContextCompat.getColor(BaseApplication.e(), i);
        } catch (Resources.NotFoundException e) {
            LogUtil.b("ResourcesUtils", "getColor exception ", ExceptionUtils.d(e));
            return 0;
        }
    }

    public static Drawable cKq_(int i) {
        try {
            return ContextCompat.getDrawable(BaseApplication.e(), i);
        } catch (Resources.NotFoundException e) {
            LogUtil.b("ResourcesUtils", "getDrawable exception ", ExceptionUtils.d(e));
            return null;
        }
    }

    public static String f(int i) {
        try {
            return BaseApplication.e().getResources().getResourceName(i);
        } catch (Resources.NotFoundException e) {
            ReleaseLogUtil.c("ResourcesUtils", "getResourceName exception ", DfxUtils.d(Thread.currentThread(), e));
            return "";
        }
    }

    public static CharSequence j(int i) {
        try {
            return BaseApplication.e().getResources().getText(i);
        } catch (Resources.NotFoundException e) {
            LogUtil.b("ResourcesUtils", "getText exception ", ExceptionUtils.d(e));
            return "";
        }
    }

    public static String h(int i) {
        try {
            return BaseApplication.e().getResources().getString(i);
        } catch (Resources.NotFoundException e) {
            LogUtil.b("ResourcesUtils", "getString exception ", ExceptionUtils.d(e));
            return "";
        }
    }

    public static String b(int i, Object... objArr) {
        try {
            return BaseApplication.e().getResources().getString(i, objArr);
        } catch (Resources.NotFoundException | IllegalFormatConversionException | MissingFormatArgumentException e) {
            LogUtil.b("ResourcesUtils", "getString exception ", ExceptionUtils.d(e));
            return "";
        }
    }

    public static String a(int i, int i2, Object... objArr) {
        try {
            return BaseApplication.e().getResources().getQuantityString(i, i2, objArr);
        } catch (Resources.NotFoundException | IllegalFormatConversionException | MissingFormatArgumentException e) {
            LogUtil.b("ResourcesUtils", "getQuantityString exception ", ExceptionUtils.d(e));
            return "";
        }
    }

    public static String[] i(int i) {
        try {
            return BaseApplication.e().getResources().getStringArray(i);
        } catch (Resources.NotFoundException | IllegalFormatConversionException | MissingFormatArgumentException e) {
            LogUtil.b("ResourcesUtils", "getStringArray exception ", ExceptionUtils.d(e));
            return new String[0];
        }
    }

    public static Resources cKp_(Context context) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(Locale.ENGLISH);
        return context.createConfigurationContext(configuration).getResources();
    }
}
