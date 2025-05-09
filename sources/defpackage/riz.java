package defpackage;

import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.ResultCallback;

/* loaded from: classes9.dex */
public class riz {

    /* renamed from: a, reason: collision with root package name */
    private static riz f16779a = new riz();
    private final HealthDataCloudFactory d = new HealthDataCloudFactory(BaseApplication.getContext());

    private riz() {
    }

    public static riz d() {
        return f16779a;
    }

    public void a(rjf rjfVar, ResultCallback<rjg> resultCallback) {
        lqi.d().b(rjfVar.getUrl(), this.d.getHeaders(), lql.b(this.d.getBody(rjfVar)), rjg.class, resultCallback);
    }
}
