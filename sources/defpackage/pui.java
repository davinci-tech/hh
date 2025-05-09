package defpackage;

import android.os.Handler;
import android.os.Looper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.DataApi;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback;
import java.util.List;

/* loaded from: classes6.dex */
public class pui implements DataApi {
    private Handler c = new Handler(Looper.getMainLooper());

    @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.DataApi
    public void downloadWorkoutMediaFiles(UiCallback<String> uiCallback) {
        List<pus> d = puk.d();
        LogUtil.a("DataImpl", "downloadWorkoutMediaFiles files");
        a(d, 10485760L, uiCallback);
    }

    public void a(List<pus> list, long j, UiCallback<String> uiCallback) {
        if (puh.e(list) == null) {
            uiCallback.onSuccess(this.c, "");
        } else {
            LogUtil.a("DataImpl", "DownloadTask");
            new pum(list, j, uiCallback).e();
        }
    }
}
