package defpackage;

import android.content.SharedPreferences;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes3.dex */
public class cri {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void b(String str, T t) {
        LogUtil.a("PluginDevice_ScaleSharedPreferenceHelper", "enter putDataByKey spFieldName: ", str, " spFieldValue: ", CommonUtil.l(t.toString()));
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("scaleSp", 0).edit();
        if (t instanceof Integer) {
            edit.putInt(str, ((Integer) t).intValue());
        } else if (t instanceof String) {
            edit.putString(str, (String) t);
        } else {
            LogUtil.h("PluginDevice_ScaleSharedPreferenceHelper", "putDataByKey else");
        }
        edit.commit();
    }

    public static <T> T a(String str, T t) {
        LogUtil.a("PluginDevice_ScaleSharedPreferenceHelper", "enter getDataByKey spFieldName: ", str, " dataType: ", CommonUtil.l(t.toString()));
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("scaleSp", 0);
        if (t instanceof Integer) {
            return (T) Integer.valueOf(sharedPreferences.getInt(str, 0));
        }
        if (t instanceof String) {
            return (T) sharedPreferences.getString(str, "");
        }
        return (T) sharedPreferences.getString(str, "");
    }
}
