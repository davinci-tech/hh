package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;

/* loaded from: classes5.dex */
public class kyn implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private String f14703a;
    private Context b;
    private String d;

    public kyn(Context context, String str, String str2) {
        this.b = context;
        this.d = str;
        this.f14703a = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        LogUtil.c("AppInstallThread", "run mPath=", this.d, ", mPackageName=", this.f14703a);
        if (this.d == null) {
            LogUtil.b("AppInstallThread", "run mPath is null");
        } else {
            a();
        }
    }

    private void a() {
        String c = CommonUtil.c(this.d);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("AppInstallThread", "installApkByGuide safePath is null");
            return;
        }
        File file = new File(c);
        if (file.exists()) {
            Intent intent = new Intent();
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            LogUtil.a("AppInstallThread", "installApkByGuide more than Android N");
            Uri uriForFile = FileProvider.getUriForFile(this.b, jcu.f13746a, file);
            LogUtil.a("AppInstallThread", "installApkByGuide content uri is : ", uriForFile);
            intent.setFlags(268435456);
            intent.addFlags(1);
            intent.addFlags(2);
            intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
            try {
                intent.putExtra("android.intent.extra.ORIGINATING_UID", BaseApplication.getContext().getPackageManager().getApplicationInfo(BaseApplication.getContext().getPackageName(), 128).uid);
            } catch (PackageManager.NameNotFoundException e) {
                ReleaseLogUtil.e("R_AppInstallThread", "install err:", ExceptionUtils.d(e));
            }
            Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
            if (wa_ != null) {
                bSL_(wa_, intent);
                return;
            } else {
                LogUtil.h("AppInstallThread", "installApkByGuide topActivity is null");
                this.b.startActivity(intent);
                return;
            }
        }
        LogUtil.b("AppInstallThread", "installApkByGuide file not exist");
    }

    private void bSL_(final Activity activity, final Intent intent) {
        activity.runOnUiThread(new Runnable() { // from class: kyn.4
            @Override // java.lang.Runnable
            public void run() {
                activity.startActivityForResult(intent, 0);
            }
        });
    }

    public void e() {
        Thread thread = new Thread(this);
        thread.setName("Ver-AppInstall");
        thread.start();
    }
}
