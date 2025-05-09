package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class rub {
    public static void a(final int i, final ResultCallback<rhv> resultCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ruc
            @Override // java.lang.Runnable
            public final void run() {
                rub.b(i, resultCallback);
            }
        });
    }

    static /* synthetic */ void b(int i, ResultCallback resultCallback) {
        rhw rhwVar = new rhw(i, 200, 0, 0);
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.e());
        LogUtil.d("LabelNetWorkUtil", "url", rhwVar.getUrl());
        lqi.d().b(rhwVar.getUrl(), healthDataCloudFactory.getHeaders(), lql.b(healthDataCloudFactory.getBody(rhwVar)), rhv.class, resultCallback);
    }

    public static void a(final List<rhz> list, final ResultCallback<CloudCommonReponse> resultCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: rty
            @Override // java.lang.Runnable
            public final void run() {
                rub.b(list, resultCallback);
            }
        });
    }

    static /* synthetic */ void b(List list, ResultCallback resultCallback) {
        ria riaVar = new ria(list);
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.e());
        LogUtil.d("LabelNetWorkUtil", "url", riaVar.getUrl());
        lqi.d().b(riaVar.getUrl(), healthDataCloudFactory.getHeaders(), lql.b(healthDataCloudFactory.getBody(riaVar)), CloudCommonReponse.class, resultCallback);
    }
}
