package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kow {
    private static final Object b = new Object();
    private static kow d;

    /* renamed from: a, reason: collision with root package name */
    private Context f14507a = BaseApplication.getContext();

    private kow() {
    }

    public static kow e() {
        kow kowVar;
        synchronized (b) {
            if (d == null) {
                d = new kow();
            }
            kowVar = d;
        }
        return kowVar;
    }

    private boolean d(Object obj, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Track_HWHealthDataManager", "getVo2maxDetail onResult callback is null");
            return false;
        }
        if (obj != null) {
            return true;
        }
        iBaseResponseCallback.d(-1, null);
        LogUtil.h("Track_HWHealthDataManager", "getVo2maxDetail return null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        if (!d(obj, iBaseResponseCallback)) {
            LogUtil.h("Track_HWHealthDataManager", "dealHealthDataResult param is invalid.");
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            LogUtil.h("Track_HWHealthDataManager", "dealHealthDataResult size = 0");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        Object obj2 = sparseArray.get(42006);
        LogUtil.c("Track_HWHealthDataManager", "getVo2maxDetail list", obj2);
        List<HiHealthData> list = (List) obj2;
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (HiHealthData hiHealthData : list) {
                Vo2maxDetail vo2maxDetail = new Vo2maxDetail();
                vo2maxDetail.setTimestamp(hiHealthData.getStartTime());
                vo2maxDetail.setVo2maxValue(hiHealthData.getIntValue());
                arrayList.add(vo2maxDetail);
            }
        }
        LogUtil.c("Track_HWHealthDataManager", "getVo2maxDetail return list", arrayList);
        Collections.sort(arrayList, new Comparator<Vo2maxDetail>() { // from class: kow.1
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(Vo2maxDetail vo2maxDetail2, Vo2maxDetail vo2maxDetail3) {
                if (vo2maxDetail2.getTimestamp() == vo2maxDetail3.getTimestamp()) {
                    return 0;
                }
                return ((double) (vo2maxDetail2.getTimestamp() - vo2maxDetail3.getTimestamp())) < 1.0E-6d ? -1 : 1;
            }
        });
        if (arrayList.size() > 0) {
            iBaseResponseCallback.d(0, arrayList);
        } else {
            iBaseResponseCallback.d(-1, -1);
            LogUtil.a("Track_HWHealthDataManager", "getVo2maxDetail wrong list");
        }
    }

    public void a(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        synchronized (b) {
            HiDataReadOption hiDataReadOption = new HiDataReadOption();
            hiDataReadOption.setTimeInterval(j, j2);
            LogUtil.a("Track_HWHealthDataManager", "getVo2maxDetail ", Long.valueOf(j), " ", Long.valueOf(j2));
            hiDataReadOption.setType(new int[]{42006});
            hiDataReadOption.setReadType(0);
            HiHealthManager.d(this.f14507a).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kow.2
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    kow.this.e(obj, i, i2, iBaseResponseCallback);
                }
            });
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Track_HWHealthDataManager", "enter getLastVo2maxForMaxMet.");
        c(true, iBaseResponseCallback);
    }

    public void c(final boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        synchronized (b) {
            final long currentTimeMillis = System.currentTimeMillis();
            HiDataReadOption hiDataReadOption = new HiDataReadOption();
            hiDataReadOption.setStartTime(currentTimeMillis);
            hiDataReadOption.setEndTime(currentTimeMillis);
            hiDataReadOption.setType(new int[]{42007});
            hiDataReadOption.setReadType(0);
            HiHealthManager.d(this.f14507a).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kow.3
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    synchronized (kow.b) {
                        if (iBaseResponseCallback == null) {
                            LogUtil.h("Track_HWHealthDataManager", "getLastVo2max onResult callback is null");
                            return;
                        }
                        if (obj == null) {
                            LogUtil.h("Track_HWHealthDataManager", "getLastVo2max data is null.");
                            iBaseResponseCallback.d(-1, null);
                            return;
                        }
                        SparseArray sparseArray = (SparseArray) obj;
                        if (sparseArray.size() > 0) {
                            kow.this.bPG_(sparseArray, iBaseResponseCallback, z, currentTimeMillis);
                        } else {
                            LogUtil.h("Track_HWHealthDataManager", "getLastVo2max map is empty.");
                            iBaseResponseCallback.d(-1, null);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPG_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback, boolean z, long j) {
        Object obj = sparseArray.get(42007);
        List<HiHealthData> list = (obj == null || !d(obj)) ? null : (List) obj;
        if (list == null || list.isEmpty()) {
            LogUtil.h("Track_HWHealthDataManager", "getLastVo2max vo2MaxTrackData is null");
            iBaseResponseCallback.d(-1, null);
        } else {
            LogUtil.c("Track_HWHealthDataManager", "getLastVo2max ", list);
            if (list.size() > 1) {
                Collections.sort(list, new Comparator<HiHealthData>() { // from class: kow.5
                    @Override // java.util.Comparator
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                        long startTime = hiHealthData.getStartTime() - hiHealthData2.getStartTime();
                        if (startTime == 0) {
                            return 0;
                        }
                        return startTime > 0 ? 1 : -1;
                    }
                });
            }
            c(list, iBaseResponseCallback, z, j);
        }
    }

    private void c(List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback, final boolean z, long j) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(list.get(list.size() - 1).getStartTime(), j);
        hiDataReadOption.setType(new int[]{42006});
        hiDataReadOption.setReadType(0);
        HiHealthManager.d(this.f14507a).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kow.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj == null) {
                    iBaseResponseCallback.d(-1, null);
                    LogUtil.h("Track_HWHealthDataManager", "getLastVo2max getVo2maxDetail return null");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() > 0) {
                    kow.this.bPH_(sparseArray, iBaseResponseCallback, z);
                } else {
                    LogUtil.h("Track_HWHealthDataManager", "getLastVo2max getVo2maxDetail size = 0");
                    iBaseResponseCallback.d(-1, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPH_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        Object obj = sparseArray.get(42006);
        if (obj == null) {
            LogUtil.h("Track_HWHealthDataManager", "getLastVo2max sportVo2maxList is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        LogUtil.c("Track_HWHealthDataManager", "getLastVo2max getVo2maxDetail list", obj);
        List list = d(obj) ? (List) obj : null;
        if (list == null || list.isEmpty()) {
            LogUtil.h("Track_HWHealthDataManager", "getLastVo2max vo2maxListSport is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        Collections.sort(list, new Comparator<HiHealthData>() { // from class: kow.9
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                long startTime = hiHealthData.getStartTime() - hiHealthData2.getStartTime();
                if (startTime == 0) {
                    return 0;
                }
                return startTime > 0 ? 1 : -1;
            }
        });
        HiHealthData hiHealthData = (HiHealthData) list.get(list.size() - 1);
        Vo2maxDetail vo2maxDetail = new Vo2maxDetail();
        vo2maxDetail.setTimestamp(hiHealthData.getEndTime());
        vo2maxDetail.setVo2maxValue(hiHealthData.getIntValue());
        LogUtil.c("Track_HWHealthDataManager", "getLastVo2max TimeStamp", Long.valueOf(hiHealthData.getStartTime()));
        if (z) {
            kpm.c().c(HiDateUtil.t(hiHealthData.getStartTime()), HiDateUtil.f(hiHealthData.getStartTime()), 0, true, iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(0, vo2maxDetail);
        }
    }

    public boolean d(Object obj) {
        if (!(obj instanceof List)) {
            return false;
        }
        Iterator it = ((List) obj).iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (!(it.next() instanceof HiHealthData)) {
                z = false;
            }
        }
        return z;
    }

    public void a(Context context, long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Track_HWHealthDataManager", "getRestHeartRateData enter");
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setType(new int[]{2018});
        hiAggregateOption.setConstantsKey(new String[]{"rest_heart_rate_max"});
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setCount(1);
        HiHealthManager.d(context).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kow.8
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (iBaseResponseCallback == null) {
                    LogUtil.h("Track_HWHealthDataManager", "getRestHeartRateData onResult callback is null");
                    return;
                }
                if (list == null || list.size() <= 0) {
                    iBaseResponseCallback.d(0, Double.valueOf(0.0d));
                    LogUtil.h("Track_HWHealthDataManager", "getRestHeartRateData no data");
                } else {
                    iBaseResponseCallback.d(0, Double.valueOf(list.get(0).getDouble("rest_heart_rate_max")));
                    LogUtil.a("Track_HWHealthDataManager", "getRestHeartRateData hasData");
                }
            }
        });
    }

    public void c(Context context, long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setType(new int[]{46019});
        hiAggregateOption.setConstantsKey(new String[]{"heart_rate_last"});
        hiAggregateOption.setAggregateType(0);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setCount(1);
        HiHealthManager.d(context).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kow.7
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (iBaseResponseCallback == null) {
                    LogUtil.h("Track_HWHealthDataManager", "getLastTimeHeartRateData onResult callback is null");
                    return;
                }
                if (list == null || list.size() <= 0) {
                    iBaseResponseCallback.d(0, null);
                    LogUtil.h("Track_HWHealthDataManager", "getLastTimeHeartRateData no data");
                } else {
                    iBaseResponseCallback.d(0, list);
                    LogUtil.a("Track_HWHealthDataManager", "getLastTimeHeartRateData hasData");
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                LogUtil.a("Track_HWHealthDataManager", "onResultIntent called");
            }
        });
    }
}
