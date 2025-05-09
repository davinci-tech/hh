package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homewear21.wearjoin.JoinRuleParse;
import com.huawei.ui.main.stories.lightcloud.service.LightCloudCallBack;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ong {
    private otk c;

    public void d() {
        LogUtil.a("LightCloudInteractor", "refreshServiceProcess begin");
        ThreadPoolManager.d().c("LightCloudInteractor", new Runnable() { // from class: ong.5
            @Override // java.lang.Runnable
            public void run() {
                if (ong.this.c == null) {
                    ong.this.c = new otk();
                }
                ong.this.c.c();
            }
        });
    }

    public void a(boolean z) {
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.a("LightCloudInteractor", "no net to lightcloud");
        } else {
            rea.c(BaseApplication.e()).b(new LightCloudCallBack() { // from class: ong.4
                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                @Override // com.huawei.ui.main.stories.lightcloud.service.LightCloudCallBack
                public void onResponce(String str, int i) {
                    char c;
                    LogUtil.a("LightCloudInteractor", "LightCloud doRefreshBatch ", str, " resCode = ", Integer.valueOf(i));
                    if (str == null) {
                        return;
                    }
                    str.hashCode();
                    switch (str.hashCode()) {
                        case -1519497327:
                            if (str.equals("servicefwo_v1")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case -692792971:
                            if (str.equals("ecg_blocklist")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 366611220:
                            if (str.equals("servicefw_v1")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 971526302:
                            if (str.equals("healthconfig")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1997054059:
                            if (str.equals("oversea_app_update")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    if (c == 0) {
                        ong.this.a("servicefwo_v1", i);
                        return;
                    }
                    if (c == 1) {
                        owj.c(i);
                        return;
                    }
                    if (c == 2) {
                        ong.this.a("servicefw_v1", i);
                        return;
                    }
                    if (c == 3) {
                        if (CommonUtil.ce()) {
                            JoinRuleParse.parseResult(BaseApplication.e());
                        }
                    } else if (c == 4) {
                        kxt.b(i);
                    } else {
                        LogUtil.b("LightCloudInteractor", "Unrecognized id");
                    }
                }
            }, c(z));
        }
    }

    private List<String> c(boolean z) {
        ArrayList arrayList = new ArrayList(6);
        if (z) {
            arrayList.add("servicefwo_v1");
        } else {
            arrayList.add("servicefw_v1");
            arrayList.add("ecg_blocklist");
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        if (i == 0) {
            a();
            e(str, "1");
        } else {
            e(str, "0");
        }
    }

    private void e(String str, String str2) {
        SharedPreferenceManager.e(BaseApplication.e(), String.valueOf(10000), str, str2, (StorageParams) null);
    }

    private void a() {
        LogUtil.a("LightCloudInteractor", "initServiceFW begin");
        ThreadPoolManager.d().c("LightCloudInteractor", new Runnable() { // from class: ong.1
            @Override // java.lang.Runnable
            public void run() {
                if (ong.this.c == null) {
                    ong.this.c = new otk();
                }
                ong.this.c.a();
            }
        });
    }
}
