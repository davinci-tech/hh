package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;

/* loaded from: classes6.dex */
public class puq {
    private static String d(int i) {
        if (i == -8) {
            return BaseApplication.getContext().getString(R$string.sug_server_disconnect);
        }
        if (i == 0) {
            return BaseApplication.getContext().getString(R$string.sug_sucess);
        }
        if (i == 500) {
            return BaseApplication.getContext().getString(R$string.sug_http_server_internal_error);
        }
        if (i == 503) {
            return BaseApplication.getContext().getString(R$string.sug_server_is_temporarily_inaccessible);
        }
        if (i == 1003) {
            return BaseApplication.getContext().getString(R$string.sug_time_out);
        }
        if (i == 200024) {
            return BaseApplication.getContext().getString(R$string.sug_parament_invalid);
        }
        LogUtil.a("ResultUtil", "getResultInfo1 no err code an eerInfo = null");
        return "";
    }

    private static String e(int i) {
        if (i == -404) {
            return BaseApplication.getContext().getString(R$string.IDS_plugin_suggestion_have_no_network);
        }
        if (i == -2) {
            return BaseApplication.getContext().getString(R$string.IDS_press_auto_monitor_next);
        }
        if (i == 9999) {
            return BaseApplication.getContext().getString(R$string.sug_system_error);
        }
        LogUtil.a("ResultUtil", "getResultInfo2 no err code an eerInfo = null");
        return "";
    }

    public static String b(int i) {
        if (TextUtils.isEmpty(d(i))) {
            return !TextUtils.isEmpty(e(i)) ? e(i) : "";
        }
        return d(i);
    }
}
