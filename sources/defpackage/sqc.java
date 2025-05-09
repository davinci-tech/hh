package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.Normalizer;

/* loaded from: classes7.dex */
public class sqc {
    public static void c(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("FileSetSecurityLabelUtil", "setFileSecurityLevel filePath or riskLevel is empty.");
            return;
        }
        try {
            Class<?> cls = Class.forName("com.huawei.fileprotect.HwSfpPolicyManager");
            Object invoke = cls.getMethod("setLabel", Context.class, String.class, String.class, String.class, Integer.TYPE).invoke(cls.getMethod("getDefault", new Class[0]).invoke(cls, new Object[0]), BaseApplication.getContext(), str, "SecurityLevel", str2, Integer.valueOf(i));
            if ((invoke instanceof Integer ? ((Integer) invoke).intValue() : -1) == 0) {
                LogUtil.a("FileSetSecurityLabelUtil", "setFileSecurityLevel success!");
            }
        } catch (SecurityException unused) {
            LogUtil.b("FileSetSecurityLabelUtil", "setFileSecurityLevel SecurityException");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            LogUtil.b("FileSetSecurityLabelUtil", "setFileSecurityLevel other Exception");
        } catch (ClassNotFoundException unused3) {
            LogUtil.b("FileSetSecurityLabelUtil", "setFileSecurityLevel ClassNotFoundException");
        } catch (NoSuchMethodException unused4) {
            LogUtil.b("FileSetSecurityLabelUtil", "setFileSecurityLevel NoSuchMethodException");
        } finally {
            LogUtil.a("FileSetSecurityLabelUtil", "setFileSecurityLevel failed! result = ", -1);
        }
    }

    public static boolean a(File file, String str) throws IOException {
        if (file == null) {
            LogUtil.a("FileSetSecurityLabelUtil", "validateFilename", "file is null");
            return false;
        }
        if (file.getCanonicalPath().startsWith(Normalizer.normalize(new File(str).getCanonicalPath(), Normalizer.Form.NFKC))) {
            return true;
        }
        LogUtil.b("FileSetSecurityLabelUtil", "File is outside extraction target directory.");
        return false;
    }
}
