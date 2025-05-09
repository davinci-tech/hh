package defpackage;

import android.text.TextUtils;
import androidx.core.util.Consumer;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.tabtemplate.BaseTemplateConfig;
import com.huawei.ui.main.stories.template.BaseParser;
import health.compact.a.IoUtils;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class rxx {
    private static final rxx c = new rxx();
    private e b;

    private rxx() {
    }

    public static rxx b() {
        return c;
    }

    public <T extends BaseTemplateConfig> void e(String str, Consumer<T> consumer) {
        this.b = new e(str, -1, consumer);
        ThreadPoolManager.d().execute(this.b);
    }

    public <T extends BaseTemplateConfig> void d(int i, Consumer<T> consumer) {
        this.b = new e(null, i, consumer);
        ThreadPoolManager.d().execute(this.b);
    }

    public static <T extends BaseTemplateConfig> T c(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            str2 = new JSONObject(str).optString("template_id");
        } catch (JSONException unused) {
            LogUtil.b("ConfigTemplateManager", "createTemplateParser json error");
            str2 = null;
        }
        rxy a2 = a(str2, str);
        if (a2 == null) {
            return null;
        }
        return (T) a2.b();
    }

    public static String a(InputStream inputStream) {
        try {
            try {
                return FileUtils.a(inputStream, 5242880L);
            } catch (IOException | IllegalStateException unused) {
                LogUtil.b("ConfigTemplateManager", "getString IOException or IllegalStateException");
                IoUtils.e(inputStream);
                return "";
            }
        } finally {
            IoUtils.e(inputStream);
        }
    }

    public static String b(String str) {
        return rya.e(str, "template_config.json");
    }

    private static <T extends BaseTemplateConfig> rxy<T> a(String str, String str2) {
        BaseParser a2 = ryd.a(str);
        if (a2 == null) {
            return null;
        }
        return a2.parse(str2);
    }

    public void c() {
        if (this.b != null) {
            ThreadPoolManager.d().a(this.b);
            this.b = null;
        }
    }

    static class e<T> implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private Consumer<T> f16960a;
        private int b;
        private String c;

        e(String str, int i, Consumer<T> consumer) {
            this.c = str;
            this.b = i;
            this.f16960a = consumer;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = this.c;
            if (str != null) {
                this.f16960a.accept(rxx.c(rxx.b(str)));
            } else if (this.b != -1) {
                this.f16960a.accept(rxx.c(rxx.a(BaseApplication.getContext().getResources().openRawResource(this.b))));
            } else {
                LogUtil.h("ConfigTemplateManager", "no case run");
            }
        }
    }
}
