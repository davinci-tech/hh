package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager;
import com.huawei.watchface.utils.HwLog;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class aq {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10904a = "aq";

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i(f10904a, "containXssChar url is empty");
            return false;
        }
        String b = am.b(str, "UTF-8");
        String a2 = NewSystemParamManager.a("client_url_xss_filter");
        if (TextUtils.isEmpty(a2)) {
            HwLog.i(f10904a, "containXssChar xssFilter is empty");
            return false;
        }
        return Pattern.compile(a2).matcher(b).find();
    }
}
