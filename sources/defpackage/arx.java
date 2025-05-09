package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes.dex */
public class arx {
    public static Context b() {
        return BaseApplication.getContext();
    }

    public static String a() {
        return b().getResources().getConfiguration().locale.getLanguage();
    }
}
