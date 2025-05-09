package defpackage;

import android.content.Context;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.util.Map;

/* loaded from: classes8.dex */
public class eer implements IChartStorageHelper {

    /* renamed from: a, reason: collision with root package name */
    private static eer f11988a;
    private IChartStorageHelper c;

    private eer() {
        e();
    }

    public static eer b() {
        eer eerVar;
        eer eerVar2 = f11988a;
        if (eerVar2 != null) {
            return eerVar2;
        }
        synchronized (eer.class) {
            if (f11988a == null) {
                f11988a = new eer();
            }
            eerVar = f11988a;
        }
        return eerVar;
    }

    protected final void e() {
        IChartStorageHelper iChartStorageHelper = (IChartStorageHelper) ReflectionUtils.e("com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateStorageHelper");
        this.c = iChartStorageHelper;
        if (iChartStorageHelper != null) {
            LogUtil.c("HeartRateHelperProxy", "create HeartRateHelper success.");
        }
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        IChartStorageHelper iChartStorageHelper = this.c;
        if (iChartStorageHelper == null) {
            LogUtil.c("HeartRateHelperProxy", "mHeartRateHelperImpl is null");
        } else {
            iChartStorageHelper.queryStepDayData(context, j, j2, dataInfos, bVar, responseCallback);
        }
    }
}
