package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.fitness.activity.sportintensity.SportIntensityDataInteractor;
import com.huawei.ui.openservice.OpenServiceUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class pvc {
    private static pvc b;
    private static final Object c = new Object();
    private Context d = BaseApplication.getContext();

    public static pvc a() {
        pvc pvcVar;
        synchronized (c) {
            if (b == null) {
                b = new pvc();
            }
            pvcVar = b;
        }
        return pvcVar;
    }

    public void e(long j, long j2, int i, final SportIntensityDataInteractor.e eVar) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setType(new int[]{47101, 47102, 47103, 47104, 47105, 47107, 47108, 47109});
        final String[] strArr = {"TOTAL", OpenServiceUtil.Location.STEP, "RUN", "CYCLE", "FITNESS", "CLIMB", "SWIM", "UNKNOWHIGH"};
        hiAggregateOption.setConstantsKey(strArr);
        if (i == 3 || i == 4 || i == 5) {
            i = 3;
        } else if (i == 6) {
            i = 5;
        }
        hiAggregateOption.setGroupUnitType(i);
        hiAggregateOption.setReadType(0);
        LogUtil.a("Step_FitnessSportIntensityInteractor", "getFitnessDataDetailByData aggregateOption = ", hiAggregateOption.toString());
        HiHealthManager.d(this.d).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: pvc.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (koq.b(list)) {
                    eVar.onResponse(0, null);
                    return;
                }
                LogUtil.a("Step_FitnessSportIntensityInteractor", " hiHealthDataList = ", list, " errorCode", Integer.valueOf(i2), " anchor", Integer.valueOf(i3));
                ArrayList arrayList = new ArrayList(10);
                pvc.this.c(list, arrayList, strArr);
                eVar.onResponse(0, arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list, ArrayList<pva> arrayList, String[] strArr) {
        pva pvaVar = new pva();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                i += hiHealthData.getInt(strArr[0]);
                i2 += hiHealthData.getInt(strArr[1]);
                i3 += hiHealthData.getInt(strArr[2]);
                i4 += hiHealthData.getInt(strArr[3]);
                i5 += hiHealthData.getInt(strArr[4]);
                i6 += hiHealthData.getInt(strArr[5]);
                i7 += hiHealthData.getInt(strArr[6]);
                i8 += hiHealthData.getInt(strArr[7]);
            }
        }
        pvaVar.a(i);
        pvaVar.h(i2);
        pvaVar.b(i3);
        pvaVar.e(i4);
        pvaVar.c(i5);
        pvaVar.d(i6);
        pvaVar.i(i7);
        pvaVar.f(i8);
        arrayList.add(pvaVar);
    }
}
