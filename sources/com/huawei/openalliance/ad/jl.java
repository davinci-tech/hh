package com.huawei.openalliance.ad;

import android.content.Context;
import android.view.View;
import cn.com.miaozhen.mobile.tracking.api.MzCountly;
import cn.com.miaozhen.mobile.tracking.viewability.origin.MzCallBack;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public enum jl {
    INSTANCE;

    private boolean b;
    private Context c;

    public boolean b() {
        return this.b;
    }

    public void a(final String str, final ContentRecord contentRecord) {
        if (!this.b) {
            ho.b("MZSdkManager", "onClick, not support mz");
        } else {
            if (com.huawei.openalliance.ad.utils.cz.b(str)) {
                ho.b("MZSdkManager", "onClick, url is blank");
                return;
            }
            ho.b("MZSdkManager", "invoke onClick");
            a(0, "click", b(str), null, contentRecord, "");
            MzCountly.sharedInstance().onClick(str, new MzCallBack() { // from class: com.huawei.openalliance.ad.jl.3
                public void onSuccess(String str2) {
                    ho.b("MZSdkManager", "onClick onSuccess. %s", str2);
                    jl jlVar = jl.this;
                    jlVar.a(1, "click", jlVar.b(str), null, contentRecord, str2);
                }

                public void onFailed(String str2) {
                    ho.b("MZSdkManager", "onClick failed: %s.", str2);
                    jl jlVar = jl.this;
                    jlVar.a(2, "click", jlVar.b(str), str2, contentRecord, "");
                }
            });
        }
    }

    public void a(final String str, View view, final ContentRecord contentRecord) {
        if (!this.b) {
            ho.b("MZSdkManager", "displayImp, not support mz");
        } else {
            if (com.huawei.openalliance.ad.utils.cz.b(str)) {
                ho.b("MZSdkManager", "displayImp, url is blank");
                return;
            }
            ho.b("MZSdkManager", "invoke displayImp");
            a(0, "displayImp", b(str), null, contentRecord, "");
            MzCountly.sharedInstance().disPlayImp(str, view, 1, new MzCallBack() { // from class: com.huawei.openalliance.ad.jl.1
                public void onSuccess(String str2) {
                    ho.b("MZSdkManager", "displayImp onSuccess. %s", str2);
                    jl jlVar = jl.this;
                    jlVar.a(1, "displayImp", jlVar.b(str), null, contentRecord, str2);
                }

                public void onFailed(String str2) {
                    ho.b("MZSdkManager", "displayImp onError %s.", str2);
                    jl jlVar = jl.this;
                    jlVar.a(2, "displayImp", jlVar.b(str), str2, contentRecord, "");
                }
            });
        }
    }

    public void a(String str) {
        if (!this.b) {
            ho.b("MZSdkManager", "stopMz, not support mz");
        } else {
            ho.b("MZSdkManager", "stop mz monitor.");
            MzCountly.sharedInstance().stop(str);
        }
    }

    public void a(Context context) {
        this.c = context.getApplicationContext();
        try {
            MzCountly.sharedInstance().setLogState(ho.a());
            MzCountly.sharedInstance().init(context, "");
            ho.b("MZSdkManager", "init mz sdk success");
            this.b = true;
        } catch (Throwable th) {
            ho.c("MZSdkManager", "init mz sdk, err %s", th.getClass().getSimpleName());
            this.b = false;
        }
    }

    public void a(int i, final String str, View view, final ContentRecord contentRecord) {
        if (!this.b) {
            ho.b("MZSdkManager", "videoImp, not support mz");
        } else {
            if (com.huawei.openalliance.ad.utils.cz.b(str)) {
                ho.b("MZSdkManager", "videoImp, url is blank");
                return;
            }
            ho.b("MZSdkManager", "invoke videoImp");
            a(0, "videoImp", b(str), null, contentRecord, "");
            MzCountly.sharedInstance().videoImp(str, view, 1, i, new MzCallBack() { // from class: com.huawei.openalliance.ad.jl.2
                public void onSuccess(String str2) {
                    ho.b("MZSdkManager", "videoImp onSuccess. %s", str2);
                    jl jlVar = jl.this;
                    jlVar.a(1, "videoImp", jlVar.b(str), null, contentRecord, str2);
                }

                public void onFailed(String str2) {
                    ho.b("MZSdkManager", "videoImp failed: %s.", str2);
                    jl jlVar = jl.this;
                    jlVar.a(2, "videoImp", jlVar.b(str), str2, contentRecord, "");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(String str) {
        return "monitorUrl:" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final String str, final String str2, final String str3, final ContentRecord contentRecord, final String str4) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.jl.4
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.c(jl.this.c).a(i, str, str2, str3, contentRecord, str4);
            }
        });
    }

    public static jl a() {
        return INSTANCE;
    }
}
