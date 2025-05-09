package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class scf {
    public static void e(String str, DownloadCallback<File> downloadCallback) {
        a(str, true, downloadCallback);
    }

    private static void a(String str, boolean z, DownloadCallback<File> downloadCallback) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(nrt.a(BaseApplication.e()) ? "_dark" : "_light");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(MultiUsersManager.INSTANCE.getMainUser().c() == 0 ? "_girl" : "_boy");
        String sb4 = sb3.toString();
        if (z) {
            sb4 = sb4 + "_big";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("configType", "aiBodyShapePics");
        drd.e(new dql("com.huawei.health_Sport_Common", hashMap), sb4, 7, downloadCallback);
    }
}
