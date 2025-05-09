package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jyq {

    /* renamed from: a, reason: collision with root package name */
    private static volatile jyq f14203a;
    private static final Object b = new Object();
    private Context d;

    private jyq(Context context) {
        this.d = context;
    }

    public static jyq d() {
        jyq jyqVar;
        synchronized (b) {
            if (f14203a == null) {
                f14203a = new jyq(BaseApplication.getContext());
            }
            jyqVar = f14203a;
        }
        return jyqVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(defpackage.jys r13, boolean r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jyq.d(jys, boolean):boolean");
    }

    public String d(String str, int i) throws IOException {
        if (jyu.a(str, i)) {
            return this.d.getFilesDir().getPath() + File.separator + "commonFileRequest" + File.separator + str;
        }
        return CommonUtil.c(this.d.getFilesDir().getCanonicalPath() + File.separator + "commonFileRequest" + File.separator + str);
    }

    private FileOutputStream e(int i, boolean z, File file) throws IOException {
        if (z) {
            if (i > 0) {
                return FileUtils.openOutputStream(file, true);
            }
            return FileUtils.openOutputStream(file);
        }
        if (i <= 1) {
            return FileUtils.openOutputStream(file);
        }
        return FileUtils.openOutputStream(file, true);
    }

    public boolean c(String str, String str2) {
        return TextUtils.equals(str, str2) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str);
    }
}
