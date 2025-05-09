package defpackage;

import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import java.lang.ref.WeakReference;

/* loaded from: classes8.dex */
public class fnk extends UiCallback<Workout> {
    private WeakReference<TrainDetailContract.Ipresenter> e;

    @Override // com.huawei.basefitnessadvice.callback.UiCallback
    public void onFailure(int i, String str) {
    }

    public fnk(TrainDetailContract.Ipresenter ipresenter) {
        this.e = null;
        this.e = new WeakReference<>(ipresenter);
    }

    @Override // com.huawei.basefitnessadvice.callback.UiCallback
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onSuccess(Workout workout) {
        if (workout == null) {
            LogUtil.h("TrainDetailRefreshDataCallback", "data in onSuccess is null");
            return;
        }
        WeakReference<TrainDetailContract.Ipresenter> weakReference = this.e;
        if (weakReference == null) {
            LogUtil.h("TrainDetailRefreshDataCallback", "mTrainDetail in onSuccess is null");
            return;
        }
        TrainDetailContract.Ipresenter ipresenter = weakReference.get();
        if (ipresenter == null) {
            LogUtil.h("TrainDetailRefreshDataCallback", "trainDetail in onSuccess is null");
        } else if (workout instanceof FitWorkout) {
            ipresenter.initTradeView((FitWorkout) workout);
        }
    }
}
