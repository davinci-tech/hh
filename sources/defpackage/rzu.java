package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcrowdtestapi.HealthFeedbackCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.ProcessUtil;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class rzu implements HealthFeedbackCallback {
    private final Handler c;

    public rzu(Handler handler) {
        this.c = handler;
    }

    @Override // com.huawei.hwcrowdtestapi.HealthFeedbackCallback
    public void onFailed(String str) {
        LogUtil.h("HealthFeedbackListener", "onFailed to feedback issues");
    }

    @Override // com.huawei.hwcrowdtestapi.HealthFeedbackCallback
    public jer collectLogs(int i, String str, String str2, boolean z) {
        LogUtil.a("HealthFeedbackListener", "collectLogs bugTypeId:", Integer.valueOf(i), ", dtsNumber:", str2);
        jsd.a(z);
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(jsd.c + "MaintenanceLog");
        arrayList.add(jsd.c + BaseApplication.getAppPackage());
        arrayList.add(jsd.c + BaseApplication.getAppPackage() + ".h5pro");
        StringBuilder sb = new StringBuilder();
        sb.append(jsd.c);
        sb.append(ProcessUtil.b("_PhoneService"));
        arrayList.add(sb.toString());
        arrayList.add(jsd.c + ProcessUtil.b("_DaemonService"));
        arrayList.add(jsd.c + ProcessUtil.b("_leakcanary"));
        arrayList.add(jsd.c + "com.huawei.version.json");
        arrayList.add(jsd.c + "huawei_crashLog_0.txt");
        arrayList.add(jsd.c + "huawei_crashLog_1.txt");
        arrayList.add(jsd.c + "huawei_crashLog_2.txt");
        arrayList.add(jsd.c + "app_config_value.txt");
        arrayList.add(jsd.c + "leak.txt");
        if (CommonUtil.as()) {
            arrayList.add(jsd.c + "hihealth_sensitive_export.zip");
            arrayList.add(jsd.c + "hihealth_003_export.zip");
        }
        DfxBaseHandler.getAllDfxLogFileToPathList(new File(jsd.c), arrayList);
        jer jerVar = new jer();
        jerVar.a(arrayList);
        jerVar.c(200);
        Message obtain = Message.obtain();
        obtain.what = 37;
        Bundle bundle = new Bundle();
        bundle.putInt("bugTypeId", i);
        bundle.putString("fileLogId", str);
        bundle.putString("dtsNumber", str2);
        obtain.setData(bundle);
        this.c.sendMessage(obtain);
        LogUtil.a("HealthFeedbackListener", "collectLogs mFeedbackHandler sendMessage MSG_GET_DEVICE_LOG, bugTypeId = " + i + ", fileLogId = " + str + ", dtsNumber = " + str2);
        return jerVar;
    }
}
