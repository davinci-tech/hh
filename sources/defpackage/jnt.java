package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.utils.ListUtil;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class jnt {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, String> f13974a = new HashMap(16);

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwWheelSizeInfo", "configData id is empty");
        } else {
            f13974a.put(str, str2);
            c(str);
        }
    }

    private static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwWheelSizeInfo", "fetchRimSizeCode id is empty");
            return;
        }
        String str2 = f13974a.get(str);
        LogUtil.a("HwWheelSizeInfo", "rimSizeCode: ", str2);
        if (str2 == null) {
            LogUtil.h("HwWheelSizeInfo", "rimSizeCode is null");
        } else {
            c(str, str2);
        }
    }

    private static void c(String str, String str2) {
        try {
            List<cwd> e = new cwl().a(str2).e();
            if (ListUtil.isEmpty(e)) {
                LogUtil.h("HwWheelSizeInfo", "parseConfigData tlvList is null or empty");
                return;
            }
            jnr.b().e().b(str);
            for (cwd cwdVar : e) {
                switch (CommonUtil.w(cwdVar.e())) {
                    case 8:
                        jnr.b().e().b(CommonUtil.y(cwdVar.c()));
                        break;
                    case 9:
                        jnr.b().e().b(CommonUtil.w(cwdVar.c()));
                        break;
                    case 10:
                        jnr.b().e().e(CommonUtil.w(cwdVar.c()));
                        break;
                    case 11:
                        jnr.b().e().a(CommonUtil.w(cwdVar.c()));
                        break;
                    default:
                        Object[] objArr = new Object[1];
                        objArr[0] = "parseConfigData default branches.";
                        LogUtil.h("HwWheelSizeInfo", objArr);
                        break;
                }
            }
        } catch (cwg e2) {
            LogUtil.b("HwWheelSizeInfo", "parseConfigData tlvException11: ", ExceptionUtils.d(e2));
        } catch (NumberFormatException e3) {
            LogUtil.b("HwWheelSizeInfo", "parseConfigData numberFormatException: ", ExceptionUtils.d(e3));
        }
    }
}
