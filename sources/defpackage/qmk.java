package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public class qmk implements IChartStorageHelper {
    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (dataInfos.isDayData() && !nom.p(j)) {
            responseCallback.onResult(-1, null);
            return;
        }
        int i = AnonymousClass1.f16484a[dataInfos.ordinal()];
        if (i == 1) {
            a(context, j, j2, responseCallback);
            return;
        }
        if (i == 2) {
            d(context, j, j2, responseCallback);
            return;
        }
        if (i == 3) {
            d(context, j, j2, responseCallback);
        } else if (i == 4) {
            e(context, j, j2, responseCallback);
        } else {
            LogUtil.a("PressureLineChartStorageHelper", "no case match !");
        }
    }

    /* renamed from: qmk$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f16484a;

        static {
            int[] iArr = new int[DataInfos.values().length];
            f16484a = iArr;
            try {
                iArr[DataInfos.PressureDayDetail.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16484a[DataInfos.PressureWeekDetail.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f16484a[DataInfos.PressureMonthDetail.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f16484a[DataInfos.PressureYearDetail.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void a(Context context, long j, long j2, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2 - 1);
        hiDataReadOption.setType(new int[]{2034});
        HiHealthNativeApi.a(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: qmk.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj == null) {
                    LogUtil.a("PressureLineChartStorageHelper", "data is null");
                    ResponseCallback responseCallback2 = responseCallback;
                    if (responseCallback2 != null) {
                        responseCallback2.onResult(-1, null);
                        return;
                    }
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    ResponseCallback responseCallback3 = responseCallback;
                    if (responseCallback3 != null) {
                        responseCallback3.onResult(-1, null);
                        return;
                    }
                    return;
                }
                List list = (List) sparseArray.get(2034);
                HashMap hashMap = new HashMap(16);
                qmk.this.e(list, hashMap);
                LogUtil.a("PressureLineChartStorageHelper", "before resultMap: ", hashMap.toString());
                LogUtil.a("PressureLineChartStorageHelper", "pressureMeasureValueList size: ", Integer.valueOf(list.size()));
                qmk.this.d((List<HiHealthData>) list, hashMap);
                HashMap hashMap2 = new HashMap(16);
                qmk.this.d(hashMap, hashMap2);
                LogUtil.a("PressureLineChartStorageHelper", "after resultNewMap: ", hashMap2.toString());
                if (responseCallback != null) {
                    if (hashMap2.size() > 0) {
                        responseCallback.onResult(0, hashMap2);
                    } else {
                        responseCallback.onResult(-1, null);
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.c("PressureLineChartStorageHelper", "requestDayPressureData onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<Long, IStorageModel> map, Map<Long, IStorageModel> map2) {
        eda edaVar;
        float f;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, IStorageModel> entry : map.entrySet()) {
            long longValue = entry.getKey().longValue();
            int a2 = (int) ((eda) entry.getValue()).a();
            sb.append(longValue);
            sb.append(" ");
            sb.append(a2);
            sb.append(",");
            Calendar a3 = a(longValue);
            IStorageModel iStorageModel = map2.get(Long.valueOf(a3.getTimeInMillis()));
            if (iStorageModel instanceof eda) {
                edaVar = (eda) iStorageModel;
                f = edaVar.a();
            } else {
                f = 0.0f;
                edaVar = new eda(0.0f);
            }
            d(a2, a3, edaVar, f, map2);
        }
        LogUtil.a("PressureLineChartStorageHelper", "setHalfHourData all data = ", sb.toString());
    }

    private Calendar a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (calendar.get(12) / 30 == 0) {
            calendar.set(12, 14);
            calendar.set(13, 30);
        } else {
            calendar.set(12, 44);
            calendar.set(13, 30);
        }
        calendar.set(14, 0);
        return calendar;
    }

    private void d(int i, Calendar calendar, eda edaVar, float f, Map<Long, IStorageModel> map) {
        if (f == 0.0f) {
            edaVar.d(i);
            edaVar.d(1);
        } else {
            int e = edaVar.e();
            float f2 = e;
            int i2 = e + 1;
            edaVar.d(i2);
            edaVar.d(((f * f2) + i) / i2);
        }
        map.put(Long.valueOf(calendar.getTimeInMillis()), edaVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list, Map<Long, IStorageModel> map) {
        for (HiHealthData hiHealthData : list) {
            LogUtil.a("PressureLineChartStorageHelper", "measureType1 = ", Integer.valueOf(((HiStressMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiStressMetaData.class)).fetchStressMeasureType()));
            map.put(Long.valueOf(hiHealthData.getStartTime()), new eda(hiHealthData.getIntValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthData> list, Map<Long, IStorageModel> map) {
        for (HiHealthData hiHealthData : list) {
            long startTime = hiHealthData.getStartTime();
            int fetchStressMeasureType = ((HiStressMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiStressMetaData.class)).fetchStressMeasureType();
            LogUtil.a("PressureLineChartStorageHelper", "measureType2 = " + fetchStressMeasureType);
            if (fetchStressMeasureType == 0 || fetchStressMeasureType == 2) {
                LogUtil.a("PressureLineChartStorageHelper", "pressureMeasureValueList.size()2 = " + list.size());
                for (HiHealthData hiHealthData2 : list) {
                    int fetchStressMeasureType2 = ((HiStressMetaData) HiJsonUtil.e(hiHealthData2.getMetaData(), HiStressMetaData.class)).fetchStressMeasureType();
                    LogUtil.a("PressureLineChartStorageHelper", "measureTypeTemp = " + fetchStressMeasureType2);
                    if (fetchStressMeasureType2 != 0 && fetchStressMeasureType2 != 2) {
                        long startTime2 = hiHealthData2.getStartTime();
                        LogUtil.a("PressureLineChartStorageHelper", "pressureTimeTmp = " + startTime2);
                        if (Math.abs(startTime2 - startTime) <= 600000) {
                            LogUtil.a("PressureLineChartStorageHelper", "in range");
                            if (map.containsKey(Long.valueOf(startTime2))) {
                                map.remove(Long.valueOf(startTime2));
                            }
                        }
                    }
                }
            }
        }
    }

    private void d(Context context, long j, long j2, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(new int[]{44306});
        HiHealthNativeApi.a(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: qmk.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj == null) {
                    if (responseCallback != null) {
                        LogUtil.a("PressureLineChartStorageHelper", "data is null，callback -1");
                        responseCallback.onResult(-1, null);
                        return;
                    }
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    ResponseCallback responseCallback2 = responseCallback;
                    if (responseCallback2 != null) {
                        responseCallback2.onResult(-1, null);
                        return;
                    }
                    return;
                }
                List list = (List) sparseArray.get(44306);
                HashMap hashMap = new HashMap(16);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    hashMap.put(Long.valueOf(jec.g(((HiHealthData) it.next()).getStartTime() + 43200000)), new eda(r1.getIntValue()));
                }
                if (responseCallback != null) {
                    if (hashMap.size() > 0) {
                        responseCallback.onResult(0, hashMap);
                    } else {
                        responseCallback.onResult(-1, null);
                    }
                }
            }
        });
    }

    private void e(Context context, final long j, final long j2, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(new int[]{44306});
        HiHealthNativeApi.a(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: qmk.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj == null) {
                    ResponseCallback responseCallback2 = responseCallback;
                    if (responseCallback2 != null) {
                        responseCallback2.onResult(-1, null);
                        return;
                    }
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    if (responseCallback != null) {
                        LogUtil.a("PressureLineChartStorageHelper", "data size is zero ");
                        responseCallback.onResult(-1, null);
                        return;
                    }
                    return;
                }
                List list = (List) sparseArray.get(44306);
                if (responseCallback != null && list.size() <= 0) {
                    responseCallback.onResult(-1, null);
                }
                qmk.this.a((List<HiHealthData>) list, (ResponseCallback<Map<Long, IStorageModel>>) responseCallback, j, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback, long j, long j2) {
        Map<Long, IStorageModel> d = d(new Date(j), list, (int) ((j2 - j) / 2592000000L));
        if (responseCallback == null || d.size() <= 0) {
            return;
        }
        responseCallback.onResult(0, d);
    }

    private long d(Date date) {
        long time = date.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM", Locale.getDefault());
        String format = simpleDateFormat.format(Long.valueOf(time));
        if (format == null) {
            return 0L;
        }
        try {
            return simpleDateFormat.parse(format).getTime();
        } catch (ParseException unused) {
            LogUtil.b("PressureLineChartStorageHelper", "acquireMonthZeroTimeFromDate, wrong parse");
            return 0L;
        }
    }

    private Date e(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    private Map<Long, IStorageModel> d(Date date, List<HiHealthData> list, int i) {
        HashMap hashMap = new HashMap(16);
        Date e = e(date);
        int i2 = 0;
        while (i2 < i) {
            Date c = jec.c(e, 1);
            List<HiHealthData> a2 = a(jec.l(e), jec.l(jec.b(jec.d(c, -1))), list);
            int size = a2.size();
            if (a2.size() > 0) {
                Iterator<HiHealthData> it = a2.iterator();
                int i3 = 0;
                while (it.hasNext()) {
                    i3 += it.next().getIntValue();
                }
                hashMap.put(Long.valueOf(d(e) + 1296000000), new eda((int) (i3 / size)));
            }
            i2++;
            e = c;
        }
        return hashMap;
    }

    private List<HiHealthData> a(long j, long j2, List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(10);
        for (HiHealthData hiHealthData : list) {
            long startTime = hiHealthData.getStartTime() / 1000;
            if (startTime >= j && startTime <= j2) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }
}
