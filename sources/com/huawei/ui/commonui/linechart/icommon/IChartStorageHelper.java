package com.huawei.ui.commonui.linechart.icommon;

import android.content.Context;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import java.util.Map;

/* loaded from: classes6.dex */
public interface IChartStorageHelper {
    void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback);
}
