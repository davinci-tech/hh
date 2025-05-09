package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.CommonUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes7.dex */
public class sdo {
    public static void a(Properties properties, Map<Integer, Integer> map) {
        for (Map.Entry entry : properties.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if ((key instanceof String) && (value instanceof String)) {
                map.put(Integer.valueOf(CommonUtil.h((String) key)), Integer.valueOf(nsf.b((String) value, "string", R$string.class)));
            } else {
                LogUtil.h("StringUtil", "initAdviceString class type error");
            }
        }
    }

    public static Properties b(String str) {
        Context context = BaseApplication.getContext();
        Properties properties = new Properties();
        try {
            InputStream open = context.getAssets().open(str);
            try {
                properties.load(open);
                if (open != null) {
                    open.close();
                }
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("StringUtil", "getProperties IOException");
        }
        return properties;
    }
}
