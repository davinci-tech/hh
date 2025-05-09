package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSportType;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kop {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14453a = new Object();
    private static String c = "SCUI_FitnessDetailInteractor";
    private static kop d;
    private Context b = BaseApplication.getContext();

    private kop() {
    }

    public static kop b() {
        kop kopVar;
        synchronized (f14453a) {
            if (d == null) {
                d = new kop();
            }
            kopVar = d;
        }
        return kopVar;
    }

    public void b(final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(HiDateUtil.t(System.currentTimeMillis()), HiDateUtil.f(System.currentTimeMillis()));
        hiDataReadOption.setType(new int[]{40002, 40003, 40004, SmartMsgConstant.MSG_TYPE_RIDE_USER});
        HiHealthManager.d(this.b).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kop.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj == null) {
                    iBaseResponseCallback.d(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() > 0) {
                    kop.this.bPB_(sparseArray, iBaseResponseCallback);
                } else {
                    iBaseResponseCallback.d(-1, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPB_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        FitnessTotalData fitnessTotalData = new FitnessTotalData();
        List list = (List) sparseArray.get(40002);
        fitnessTotalData.setSportType(FitnessSportType.HW_FITNESS_SPORT_ALL);
        if (list != null && list.size() > 0) {
            fitnessTotalData.setSteps(((HiHealthData) list.get(0)).getIntValue());
        }
        List list2 = (List) sparseArray.get(40003);
        if (list2 != null && list2.size() > 0) {
            fitnessTotalData.setCalorie(((HiHealthData) list2.get(0)).getIntValue());
        }
        List list3 = (List) sparseArray.get(40004);
        if (list3 != null && list3.size() > 0) {
            fitnessTotalData.setDistance(((HiHealthData) list3.get(0)).getIntValue());
        }
        List list4 = (List) sparseArray.get(SmartMsgConstant.MSG_TYPE_RIDE_USER);
        if (list4 != null && list4.size() > 0) {
            fitnessTotalData.setHeight(((HiHealthData) list4.get(0)).getIntValue());
        }
        arrayList.add(fitnessTotalData);
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, arrayList);
        }
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(HiDateUtil.t(System.currentTimeMillis()));
        hiDataReadOption.setEndTime(HiDateUtil.f(System.currentTimeMillis()));
        hiDataReadOption.setType(new int[]{2, 4, 3});
        HiHealthManager.d(this.b).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kop.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray == null || sparseArray.size() <= 0) {
                    return;
                }
                List list = (List) sparseArray.get(2);
                List list2 = (List) sparseArray.get(4);
                List list3 = (List) sparseArray.get(3);
                ArrayList arrayList = new ArrayList();
                arrayList.add(list);
                arrayList.add(list2);
                arrayList.add(list3);
                iBaseResponseCallback.d(0, arrayList);
            }
        });
    }

    public void a(final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(HiDateUtil.t(System.currentTimeMillis()));
        hiDataReadOption.setEndTime(HiDateUtil.f(System.currentTimeMillis()));
        hiDataReadOption.setType(new int[]{20002, 20003, 20004, 20005, 22003, 22001, 22002, 22104, 22103, 22101, 22102, 22105});
        HiHealthManager.d(this.b).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kop.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray == null || sparseArray.size() <= 0) {
                    return;
                }
                kop.this.bPA_(sparseArray, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPA_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        List list = (List) sparseArray.get(20002);
        List list2 = (List) sparseArray.get(20003);
        List list3 = (List) sparseArray.get(20004);
        List list4 = (List) sparseArray.get(20005);
        List list5 = (List) sparseArray.get(22003);
        List list6 = (List) sparseArray.get(22001);
        List list7 = (List) sparseArray.get(22002);
        List list8 = (List) sparseArray.get(22104);
        List<HiHealthData> list9 = (List) sparseArray.get(22103);
        List<HiHealthData> list10 = (List) sparseArray.get(22101);
        List<HiHealthData> list11 = (List) sparseArray.get(22102);
        List<HiHealthData> list12 = (List) sparseArray.get(22105);
        ArrayList arrayList = new ArrayList();
        arrayList.add(list);
        arrayList.add(list2);
        arrayList.add(list3);
        arrayList.add(list4);
        if (b(list9, list10, list11, list12)) {
            LogUtil.a(c, "getTodayDetailSegentDat get core sleep.");
            arrayList.add(list8);
            arrayList.add(list9);
            arrayList.add(list10);
            arrayList.add(list11);
            arrayList.add(list12);
        } else {
            arrayList.add(list5);
            arrayList.add(list6);
            arrayList.add(list7);
        }
        iBaseResponseCallback.d(0, arrayList);
    }

    private boolean b(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3, List<HiHealthData> list4) {
        return (list != null && list.size() > 0) || (list2 != null && list2.size() > 0) || (list3 != null && list3.size() > 0) || (list4 != null && list4.size() > 0);
    }

    public void e(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{40002, 40003});
        HiHealthManager.d(this.b).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kop.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj == null) {
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() == 0) {
                    LogUtil.a(kop.c, "triggered but the data is null");
                    iBaseResponseCallback.d(8, null);
                    return;
                }
                Object obj2 = sparseArray.get(40002);
                List list = (List) sparseArray.get(40003);
                ArrayList arrayList = new ArrayList();
                arrayList.add((List) obj2);
                arrayList.add(list);
                iBaseResponseCallback.d(0, arrayList);
            }
        });
    }
}
