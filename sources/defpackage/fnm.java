package defpackage;

import com.huawei.health.suggestion.ui.fitness.activity.TrainDetail;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class fnm implements IBaseResponseCallback {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<TrainDetail> f12573a;
    private int e;

    public fnm(TrainDetail trainDetail, int i) {
        this.f12573a = new WeakReference<>(trainDetail);
        this.e = i;
    }

    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
    /* renamed from: onResponse */
    public void d(int i, Object obj) {
        WeakReference<TrainDetail> weakReference = this.f12573a;
        if (weakReference == null) {
            LogUtil.h("Suggestion_TrainDetail", "errorCode is not success", Integer.valueOf(i));
            return;
        }
        TrainDetail trainDetail = weakReference.get();
        if (trainDetail == null) {
            LogUtil.h("Suggestion_TrainDetail", "onResponse detail is null.");
        } else {
            if (i == 0) {
                if (this.e == 0) {
                    trainDetail.e();
                }
                trainDetail.finish();
                return;
            }
            LogUtil.h("Suggestion_TrainDetail", "errorCode is not success", Integer.valueOf(i));
        }
    }
}
