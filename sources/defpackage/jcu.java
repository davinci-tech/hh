package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import java.io.IOException;

/* loaded from: classes5.dex */
public class jcu {
    public static final boolean g = CompileParameterUtil.a("IS_SUPPORT_NYX", false);
    public static final boolean b = CompileParameterUtil.a("IS_SUPPORT_GRUS", false);
    public static final boolean e = CompileParameterUtil.a("IS_SUPPORT_JANUS", false);
    public static final boolean c = CompileParameterUtil.a("IS_SUPPORT_ERIS", false);
    public static final boolean d = CompileParameterUtil.a("SUPPORT_A1", false);
    public static final boolean j = CompileParameterUtil.a("SUPPORT_METIS", false);
    public static final boolean i = CompileParameterUtil.a("SUPPORT_R1", false);
    public static final String f = a() + "/Huawei/Health/";

    /* renamed from: a, reason: collision with root package name */
    public static final String f13746a = BaseApplication.getAppPackage() + ".fileprovider";

    private static String a() {
        try {
            return CommonUtil.j((Context) null).getCanonicalPath();
        } catch (IOException unused) {
            LogUtil.b("BTDialogActivity", "getCanonicalPath Failed");
            return "";
        }
    }
}
