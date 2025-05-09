package defpackage;

import android.util.SparseArray;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class qgx {

    /* renamed from: a, reason: collision with root package name */
    private String f16417a;
    private long b;
    private boolean c = true;
    private long d;

    public void a(boolean z, long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("BloodPressureStorage", "readData");
        this.c = z;
        d(j, j2, iBaseResponseCallback);
    }

    public void d(final long j, final long j2, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("BloodPressureStorage", "readData callback is null");
            return;
        }
        LogUtil.a("BloodPressureStorage", "startTime: " + j + ", endTime: " + j2);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(c(j, j2), new HiAggregateListener() { // from class: qgx.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (koq.b(list)) {
                    LogUtil.h("BloodPressureStorage", "datas is empty");
                    iBaseResponseCallback.d(100001, new ArrayList());
                } else {
                    LogUtil.a("BloodPressureStorage", "readData data size = ", Integer.valueOf(list.size()));
                    qgx.this.a(j, j2, list, iBaseResponseCallback);
                }
            }
        });
    }

    public static void b(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("BloodPressureStorage", "readData callback is null");
            return;
        }
        HiAggregateOption d = d();
        d.setCount(1);
        d.setTimeRange(0L, System.currentTimeMillis());
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(d, new HiAggregateListener() { // from class: qgx.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (koq.b(list)) {
                    LogUtil.h("BloodPressureStorage", "datas is empty");
                    IBaseResponseCallback.this.d(100001, new ArrayList());
                } else {
                    LogUtil.a("BloodPressureStorage", "readData data size = ", Integer.valueOf(list.size()));
                    IBaseResponseCallback.this.d(0, list);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final long j, final long j2, final List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback) {
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            if (!arrayList.contains(Integer.valueOf(list.get(i).getClientId()))) {
                arrayList.add(Integer.valueOf(list.get(i).getClientId()));
            }
        }
        final int[] b = CommonUtil.b(arrayList);
        HiDataSourceFetchOption e = HiDataSourceFetchOption.builder().a(1).c(b).e();
        LogUtil.a("BloodPressureStorage", "setSourceType start");
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchDataSource(e, new HiDataClientListener() { // from class: qhe
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public final void onResult(List list2) {
                qgx.this.d(b, list, iBaseResponseCallback, j, j2, list2);
            }
        });
    }

    /* synthetic */ void d(int[] iArr, List list, IBaseResponseCallback iBaseResponseCallback, long j, long j2, List list2) {
        if (koq.b(list2)) {
            LogUtil.h("BloodPressureStorage", "getDataSourceType clientList is empty");
        } else {
            HashMap hashMap = new HashMap(iArr.length);
            for (int i = 0; i < iArr.length; i++) {
                hashMap.put(Integer.valueOf(iArr[i]), (HiHealthClient) list2.get(i));
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (rrb.c((HiHealthClient) hashMap.get(Integer.valueOf(((HiHealthData) list.get(i2)).getClientId())))) {
                    ((HiHealthData) list.get(i2)).setDeviceUuid("-1");
                }
            }
        }
        LogUtil.a("BloodPressureStorage", "setSourceType end");
        List<qkg> c = c(list);
        if (koq.b(c)) {
            LogUtil.h("BloodPressureStorage", "resultList is empty");
            iBaseResponseCallback.d(100001, new ArrayList());
        } else {
            LogUtil.h("BloodPressureStorage", "resultList size: ", Integer.valueOf(c.size()));
            d(j, j2, c, iBaseResponseCallback);
        }
    }

    private void d(long j, long j2, List<qkg> list, IBaseResponseCallback iBaseResponseCallback) {
        String b = nsj.b(j);
        this.f16417a = b;
        this.d = j;
        this.b = j2;
        SparseArray<List<HiHealthData>> dDp_ = qgu.dDp_(b);
        if (this.c && dDp_.size() > 0) {
            LogUtil.a("BloodPressureStorage", "queryDayDayData use cache, resultList size： " + list.size());
            b(j, j2, list, iBaseResponseCallback);
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(new int[]{46018});
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new a(this, iBaseResponseCallback, list, dDp_));
    }

    static class a implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private SparseArray<List<HiHealthData>> f16420a;
        private final WeakReference<qgx> c;
        private List<qkg> d;
        private final IBaseResponseCallback e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public a(qgx qgxVar, IBaseResponseCallback iBaseResponseCallback, List<qkg> list, SparseArray<List<HiHealthData>> sparseArray) {
            this.c = new WeakReference<>(qgxVar);
            this.e = iBaseResponseCallback;
            this.d = list;
            this.f16420a = sparseArray;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("BloodPressureStorage", "readHiHealthData onResult");
            if (obj == null) {
                LogUtil.a("BloodPressureStorage", "data is null");
                return;
            }
            qgx qgxVar = this.c.get();
            if (qgxVar == null) {
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() > 0) {
                if (qgxVar.c) {
                    qgxVar.c = false;
                    long currentTimeMillis = System.currentTimeMillis();
                    boolean dDr_ = qgu.dDr_(this.f16420a, sparseArray);
                    LogUtil.a("BloodPressureStorage", "queryDayDayData diff time: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "ms");
                    if (dDr_) {
                        qgu.dDs_(qgxVar.f16417a, sparseArray);
                    } else {
                        LogUtil.a("BloodPressureStorage", "queryDayDayData same cache");
                        return;
                    }
                }
                qgx.b(qgxVar.d, qgxVar.b, this.d, this.e);
                return;
            }
            LogUtil.a("BloodPressureStorage", "map is null");
            this.e.d(0, this.d);
        }
    }

    private static List<qkg> c(List<HiHealthData> list) {
        LogUtil.a("BloodPressureStorage", "handleBloodPressData enter");
        ArrayList arrayList = new ArrayList(31);
        for (HiHealthData hiHealthData : list) {
            qkg qkgVar = new qkg();
            qkgVar.a(hiHealthData.getStartTime());
            qkgVar.c(hiHealthData.getDouble("BLOOD_PRESSURE_SYSTOLIC"));
            qkgVar.b(hiHealthData.getDouble("BLOOD_PRESSURE_DIASTOLIC"));
            qkgVar.a(hiHealthData.getDouble(BleConstants.BLOODPRESSURE_SPHYGMUS));
            qkgVar.d(hiHealthData.getFloat("beforeMeasureActivity"));
            qkgVar.e(hiHealthData.getFloat("measurementAnomalyFlag"));
            qkgVar.b(hiHealthData.getMetaData());
            qkgVar.b(hiHealthData.getModifiedTime());
            qkgVar.b(hiHealthData.getClientId());
            qkgVar.d(hiHealthData.getDeviceUuid());
            if (Math.abs(qkgVar.o()) >= 0.5d) {
                arrayList.add(qkgVar);
            } else {
                LogUtil.h("BloodPressureStorage", "invalid bloodPressure value");
            }
        }
        LogUtil.a("BloodPressureStorage", "handleBloodPressData end");
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(long j, long j2, final List<qkg> list, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("BloodPressureStorage", "readHeart start");
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(j, j2);
        hiAggregateOption.setType(new int[]{2018});
        hiAggregateOption.setConstantsKey(new String[]{"rest_heart_rate_max"});
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setGroupUnitType(0);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: qgx.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list2, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list2, int i, int i2) {
                LogUtil.a("BloodPressureStorage", "readHeart end");
                if (koq.b(list2)) {
                    LogUtil.h("BloodPressureStorage", "heart is empty");
                    IBaseResponseCallback.this.d(0, list);
                    return;
                }
                for (qkg qkgVar : list) {
                    long h = qkgVar.h();
                    if (qkgVar.n() <= 0.0d) {
                        Iterator<HiHealthData> it = list2.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                HiHealthData next = it.next();
                                long startTime = next.getStartTime();
                                double d = next.getDouble("rest_heart_rate_max");
                                if (h == startTime && d > 0.0d) {
                                    qkgVar.a(d);
                                    break;
                                }
                            }
                        }
                    }
                }
                LogUtil.a("BloodPressureStorage", "read heart callback, list size: " + list.size());
                IBaseResponseCallback.this.d(0, list);
            }
        });
    }

    private static HiAggregateOption d() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{2006, 2007});
        hiAggregateOption.setConstantsKey(new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    private static HiAggregateOption c(long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_ABNORMAL.value(), DicDataTypeUtil.DataType.BLOOD_BEFORE_ACTIVITY.value()});
        hiAggregateOption.setConstantsKey(new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC", BleConstants.BLOODPRESSURE_SPHYGMUS, "measurementAnomalyFlag", "beforeMeasureActivity"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }
}
