package defpackage;

import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class qks {
    public static void c(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("LactateThresholdPStorage", "readDetailData callback is null");
        } else {
            HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(e(j, j2), new HiDataReadResultListener() { // from class: qks.3
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    IBaseResponseCallback.this.d(i, obj);
                }
            });
        }
    }

    public static void a(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("LactateThresholdPStorage", "readData callback is null");
        } else {
            HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(a(j, j2), new HiAggregateListener() { // from class: qks.4
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i, int i2) {
                    if (koq.b(list)) {
                        LogUtil.h("LactateThresholdPStorage", "datas is empty");
                        IBaseResponseCallback.this.d(100001, new ArrayList());
                    } else {
                        LogUtil.a("LactateThresholdPStorage", "readData data size = ", Integer.valueOf(list.size()));
                        IBaseResponseCallback.this.d(0, list);
                    }
                }
            });
        }
    }

    private static HiDataReadOption e(long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{42112, 42113});
        hiDataReadOption.setSortOrder(0);
        hiDataReadOption.setReadType(0);
        return hiDataReadOption;
    }

    private static HiAggregateOption a(long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setType(new int[]{42112, 42113});
        hiAggregateOption.setConstantsKey(new String[]{"lthrHr", "lthrPace"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    public static void b(HiAggregateListener hiAggregateListener) {
        if (hiAggregateListener == null) {
            LogUtil.h("LactateThresholdPStorage", "readLastOneData listener is null");
            return;
        }
        HiAggregateOption a2 = a(0L, System.currentTimeMillis());
        a2.setCount(1);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(a2, hiAggregateListener);
    }
}
