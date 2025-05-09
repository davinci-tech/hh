package health.compact.a;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.huawei.haf.application.BaseApplication;
import java.io.File;

/* loaded from: classes.dex */
public class LogPathConfig {
    public static final String d;
    private static final String e;

    /* renamed from: a, reason: collision with root package name */
    private String f13127a;
    private String b;
    private String c;

    static {
        String absolutePath = c(null).getAbsolutePath();
        d = absolutePath;
        e = absolutePath + "/huaweisystem/";
    }

    public LogPathConfig() {
        this(e);
    }

    private LogPathConfig(String str) {
        this.c = str;
        this.f13127a = this.c + BaseApplication.d() + "/";
        this.b = this.c + BaseApplication.d() + ".otalog/";
    }

    private static File c(Context context) {
        if (context == null) {
            context = BaseApplication.e();
        }
        File externalFilesDir = ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalFilesDir(null) : null;
        if (externalFilesDir != null) {
            return externalFilesDir;
        }
        Log.w("LogPathConfig", "getExternalFilesDirectory:innerContext.getFilesDir");
        return context.getFilesDir();
    }

    public String a() {
        String i = BuildTypeConfig.a() ? CommonUtils.i(null) : e;
        Log.d("LogPathConfig", "obtainSavePath(): path = " + i);
        return i;
    }

    public String c() {
        String str;
        if (BuildTypeConfig.a()) {
            str = CommonUtils.i(BaseApplication.d());
        } else {
            str = this.f13127a;
        }
        Log.d("LogPathConfig", "obtainSavePathDetail(): path = " + str);
        return str;
    }

    public String e() {
        if (BuildTypeConfig.a()) {
            return CommonUtils.i(BaseApplication.d() + ".otalog/");
        }
        return this.b;
    }

    public File b() {
        File file = new File(c());
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        Log.w("LogPathConfig", "obtainSaveFileDetail,create log directory failed");
        return null;
    }
}
