package defpackage;

import android.content.Intent;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class owk {
    public static void b() {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            LogUtil.a("ColdStartUtils", "no net to HealthCloud");
            return;
        }
        ArrayList arrayList = new ArrayList(6);
        arrayList.add("airule");
        arrayList.add("dict_config");
        arrayList.add("pageConfigs");
        HashMap hashMap = new HashMap();
        hashMap.put("configType", "coldStart");
        dql dqlVar = new dql("com.huawei.health_common_config", hashMap);
        DownloadCallback<List<File>> d = d("coldStart");
        drd.d(dqlVar, "coldStart", arrayList, d);
        if (!Utils.o()) {
            drd.e(dqlVar, "coldStart", 1, Collections.singletonList("shortcuts"), d);
        }
        drl.e(null);
    }

    private static DownloadCallback<List<File>> d(final String str) {
        return new DownloadCallback<List<File>>() { // from class: owk.3
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onFinish(List<File> list) {
                owk.e(list, str);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str2) {
                LogUtil.a("ColdStartUtils", "initHealthCloud onProgress, isDone is: ", Boolean.valueOf(z), ", fileId is: ", str2);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                LogUtil.b("ColdStartUtils", "initHealthCloud onFail, errCode is", String.valueOf(i), ", throwable is: ", th.getMessage());
                rvy.d(BaseApplication.getContext(), -1, null);
                nbr.c(BaseApplication.getContext(), -1, null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(List<File> list, String str) {
        char c;
        if (list == null) {
            LogUtil.h("ColdStartUtils", "initHealthCloud onFinish: No file has been download");
            return;
        }
        LogUtil.a("ColdStartUtils", "initHealthCloud onFinish, data is: ", list.toString());
        for (int i = 0; i < list.size(); i++) {
            File file = list.get(i);
            String name = file.getName();
            try {
                String canonicalPath = file.getCanonicalPath();
                name.hashCode();
                int hashCode = name.hashCode();
                if (hashCode == -1923752617) {
                    if (name.equals("airule.zip")) {
                        c = 0;
                    }
                    c = 65535;
                } else if (hashCode != -382420499) {
                    if (hashCode == -242078048 && name.equals("shortcuts.zip")) {
                        c = 2;
                    }
                    c = 65535;
                } else {
                    if (name.equals("dict_config.txt")) {
                        c = 1;
                    }
                    c = 65535;
                }
                if (c == 0) {
                    b(str, canonicalPath);
                } else if (c == 1) {
                    a();
                } else if (c == 2) {
                    c(str, canonicalPath);
                } else {
                    LogUtil.a("ColdStartUtils", "no post-process, path is: ", canonicalPath);
                }
            } catch (IOException e) {
                LogUtil.b("ColdStartUtils", "initHealthCloud, exception is: ", e.getMessage());
                return;
            }
        }
    }

    private static void a() {
        HiHealthDictManager.d(HuaweiHealth.a()).e(true);
        Intent intent = new Intent("com.huawei.health.action.DATA_DICTIONARY_SHOULD_RELOAD");
        intent.setPackage(BaseApplication.getAppPackage());
        BaseApplication.getContext().sendBroadcast(intent);
    }

    private static void b(String str, String str2) {
        if (dro.e(str2, drd.d("com.huawei.health_common_config", (String) null, (String) null)) != -1) {
            FileUtils.d(new File(str2));
            rvy.d(BaseApplication.getContext(), 0, drd.d("com.huawei.health_common_config", "airule", "json"));
            return;
        }
        drd.a(0L, str);
    }

    private static void c(String str, String str2) {
        if (dro.e(str2, drd.d("com.huawei.health_common_config", (String) null, (String) null)) != -1) {
            FileUtils.d(new File(str2));
            nbr.c(BaseApplication.getContext(), 0, drd.d("com.huawei.health_common_config", "shortcuts", "json"));
            return;
        }
        drd.a(0L, str);
    }
}
