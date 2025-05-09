package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hidatamanager.util.LogUtils;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.main.stories.health.util.BloodSugarTimeUtils;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class pzj implements IChartStorageHelper {
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private DecimalFormat f16355a;
    private final DataInfos b;
    private String c;
    private volatile boolean e = false;
    private final String f;
    private final long h;

    private static boolean d(int i) {
        return (i == 32 || i == 1) ? false : true;
    }

    public pzj(DataInfos dataInfos) {
        this.b = dataInfos;
        if (dataInfos.isDayData()) {
            this.h = 180000L;
            this.f = "isDayData";
        } else if (dataInfos.isWeekData()) {
            this.h = 600000L;
            this.f = "isWeekData";
        } else {
            this.h = 3600000L;
            this.f = "isMonthData";
        }
    }

    public void c(String str) {
        this.c = str;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (dataInfos.isDayData() && !nom.p(j)) {
            if (responseCallback != null) {
                responseCallback.onResult(-1, null);
            }
        } else if (!StringUtils.g(this.c)) {
            a(context, j, j2, responseCallback);
        } else if (responseCallback != null) {
            responseCallback.onResult(-1, null);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(Context context, long j, long j2, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        char c;
        String str = this.c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2010807240:
                if (str.equals("BLOOD_SUGAR_NIGHT_BLOOD_GLUGLUCOSE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1381998449:
                if (str.equals("BLOOD_SUGAR_BEFORE_SLEEP_BlOOD_GLUCOSE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -714580043:
                if (str.equals("BLOOD_SUGAR_BEFORE_MEAL")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -495139084:
                if (str.equals("BLOOD_SUGAR_AFTER_MEAL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 93616304:
                if (str.equals("BLOOD_SUGAR_LIMOSIS")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 165952772:
                if (str.equals("BLOOD_SUGAR_BEFORE_AFTER_MEAL_DIFFERENCE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            d(context, j, j2, new int[]{2015}, responseCallback);
            return;
        }
        if (c == 1) {
            d(context, j, j2, new int[]{2014}, responseCallback);
            return;
        }
        if (c == 2) {
            d(context, j, j2, new int[]{2010, 2012}, responseCallback);
            return;
        }
        if (c == 3) {
            d(context, j, j2, new int[]{2009, 2011, 2013}, responseCallback);
            return;
        }
        if (c == 4) {
            d(context, j, j2, new int[]{2008}, responseCallback);
        } else if (c == 5) {
            b(context, j, j2, responseCallback);
        } else {
            d(context, j, j2, responseCallback);
        }
    }

    private void d(Context context, long j, long j2, int[] iArr, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        a(context, j, j2, iArr, new e(this, responseCallback, System.currentTimeMillis(), false, this.f + ", requestDataByTimePeriod"));
    }

    private void b(Context context, long j, long j2, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        a(context, j, j2, new int[]{2008, 2009, 2010, 2011, 2012, 2013}, new e(this, responseCallback, System.currentTimeMillis(), true, this.f + ",requestMealDifferenceData"));
    }

    private void d(Context context, long j, long j2, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        a(context, j, j2, this.b.isDayData() ? qjv.b() : qjv.d(), new e(this, responseCallback, System.currentTimeMillis(), false, this.f + ", requestDataByTimeInterval"));
    }

    private void a(Context context, long j, long j2, int[] iArr, IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setSortOrder(0);
        if (this.b.isDayData()) {
            pyw.e().b(j, j2, iArr, iBaseResponseCallback);
        } else {
            kor.a().e(context, hiDataReadOption, iBaseResponseCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        LogUtil.a("BloodSugarLineChartStorageHelper", "handleBloodsugarData enter");
        List<qkg> c = c(list);
        HashMap hashMap = new HashMap(16);
        if (koq.b(c)) {
            if (responseCallback != null) {
                if (hashMap.size() <= 0) {
                    responseCallback.onResult(-1, hashMap);
                    return;
                } else {
                    responseCallback.onResult(0, hashMap);
                    return;
                }
            }
            return;
        }
        d(c, hashMap);
        if (responseCallback != null) {
            if (hashMap.size() <= 0) {
                responseCallback.onResult(-1, hashMap);
            } else {
                responseCallback.onResult(0, hashMap);
            }
        }
    }

    private void d(List<qkg> list, Map<Long, IStorageModel> map) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        ArrayList arrayList4 = new ArrayList(16);
        ArrayList arrayList5 = new ArrayList(16);
        ArrayList arrayList6 = new ArrayList(16);
        for (qkg qkgVar : list) {
            switch ((int) qkgVar.o()) {
                case 2008:
                    arrayList.add(qkgVar);
                    break;
                case 2009:
                    arrayList2.add(qkgVar);
                    break;
                case 2010:
                    arrayList3.add(qkgVar);
                    break;
                case 2011:
                    arrayList4.add(qkgVar);
                    break;
                case 2012:
                    arrayList5.add(qkgVar);
                    break;
                case 2013:
                    arrayList6.add(qkgVar);
                    break;
            }
        }
        Context context = BaseApplication.getContext();
        d(context, map, arrayList, arrayList2);
        d(context, map, arrayList3, arrayList4);
        d(context, map, arrayList5, arrayList6);
    }

    private void d(Context context, Map<Long, IStorageModel> map, List<qkg> list, List<qkg> list2) {
        qkg valueAt;
        if (list == null || list2 == null) {
            LogUtil.h("BloodSugarLineChartStorageHelper", "The beforeMealList or afterMealList is Null");
            return;
        }
        if (list.isEmpty() || list2.isEmpty()) {
            LogUtil.h("BloodSugarLineChartStorageHelper", "The beforeMealList or afterMealList is Empty");
            return;
        }
        SparseArray<qkg> dwg_ = dwg_(list);
        SparseArray<qkg> dwg_2 = dwg_(list2);
        if (dwg_ == null || dwg_2 == null) {
            LogUtil.h("BloodSugarLineChartStorageHelper", "The beforeMealUniqueData or afterMealUniqueData is Empty");
            return;
        }
        int size = dwg_.size();
        for (int i = 0; i < size; i++) {
            qkg qkgVar = dwg_2.get(dwg_.keyAt(i));
            if (qkgVar != null && (valueAt = dwg_.valueAt(i)) != null) {
                try {
                    float parseFloat = Float.parseFloat(a(Float.parseFloat(a((float) valueAt.m())) - Float.parseFloat(a((float) qkgVar.m()))));
                    int i2 = Math.abs(parseFloat) > 3.3f ? 2 : 1;
                    pzy pzyVar = new pzy(Math.abs(parseFloat), (int) valueAt.o(), i2);
                    pzyVar.b(c(context, i2));
                    pzyVar.b(valueAt.h());
                    pzyVar.b(valueAt.j());
                    pzyVar.e(this.c);
                    c(context, pzyVar, valueAt, qkgVar);
                    map.put(Long.valueOf(valueAt.h()), pzyVar);
                } catch (NumberFormatException unused) {
                    LogUtil.b("BloodSugarLineChartStorageHelper", "diff value NumberFormatException");
                }
            }
        }
    }

    private void c(Context context, pzy pzyVar, qkg qkgVar, qkg qkgVar2) {
        float m = (float) qkgVar.m();
        int o = (int) qkgVar.o();
        int c = c(context, m, o);
        boolean b2 = b(qkgVar);
        int c2 = c(context, c);
        pzy pzyVar2 = new pzy(m, o, c);
        pzyVar2.a(b2);
        pzyVar2.b(c2);
        pzyVar2.b(qkgVar.h());
        pzyVar2.b(qkgVar.j());
        pzyVar.e(pzyVar2);
        float m2 = (float) qkgVar2.m();
        int o2 = (int) qkgVar2.o();
        int c3 = c(context, m2, o2);
        boolean b3 = b(qkgVar2);
        int c4 = c(context, c3);
        pzy pzyVar3 = new pzy(m2, o2, c3);
        pzyVar3.a(b3);
        pzyVar3.b(c4);
        pzyVar3.b(qkgVar2.h());
        pzyVar3.b(qkgVar2.j());
        pzyVar.c(pzyVar3);
    }

    private SparseArray<qkg> dwg_(List<qkg> list) {
        if (list == null) {
            LogUtil.h("BloodSugarLineChartStorageHelper", "The healthDataList is NULL, return null");
            return new SparseArray<>(0);
        }
        b(list);
        SparseArray<qkg> sparseArray = new SparseArray<>(list.size());
        for (qkg qkgVar : list) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(qkgVar.h());
            sparseArray.put((calendar.get(1) * 10000) + (calendar.get(2) * 100) + calendar.get(5), qkgVar);
        }
        return sparseArray;
    }

    private void b(List<qkg> list) {
        Collections.sort(list, new Comparator<qkg>() { // from class: pzj.3
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(qkg qkgVar, qkg qkgVar2) {
                return Long.compare(qkgVar.h(), qkgVar2.h());
            }
        });
    }

    private List<qkg> c(List<HiHealthData> list) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(16);
        if (list != null && !list.isEmpty()) {
            LogUtil.a("BloodSugarLineChartStorageHelper", "getValidHealthData, hiHealthData.size = ", Integer.valueOf(list.size()));
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getEndTime() <= currentTimeMillis) {
                    qkg c = qkl.c(hiHealthData);
                    if (c == null) {
                        LogUtil.h("BloodSugarLineChartStorageHelper", "getValidHealthData, bloodSugarData is null");
                    } else {
                        if (TextUtils.isEmpty(c.f()) && !d(c.c())) {
                            c.a(BloodSugarTimeUtils.c(hiHealthData.getType(), c.h()));
                        }
                        arrayList.add(c);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        HashMap hashMap = new HashMap(16);
        if (koq.b(list)) {
            LogUtil.a("BloodSugarLineChartStorageHelper", "datas null or empty");
            responseCallback.onResult(-1, hashMap);
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            d(list, hashMap, currentTimeMillis);
            responseCallback.onResult(0, hashMap);
            LogUtil.a("BloodSugarLineChartStorageHelper", "handleBloodsugarData end time=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), ", thread=", Long.valueOf(Thread.currentThread().getId()));
        }
    }

    private void d(List<HiHealthData> list, Map<Long, IStorageModel> map, long j) {
        int c;
        long j2 = 0;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getEndTime() <= j) {
                if (TextUtils.isEmpty(hiHealthData.getMetaData()) && !d(hiHealthData.getInt("trackdata_deviceType"))) {
                    hiHealthData.setEndTime(BloodSugarTimeUtils.c(hiHealthData.getType(), hiHealthData.getEndTime()));
                }
                int type = hiHealthData.getType();
                float floatValue = BigDecimal.valueOf(hiHealthData.getValue()).setScale(1, RoundingMode.HALF_UP).floatValue();
                int c2 = c(BaseApplication.getContext(), floatValue, type);
                pzy pzyVar = new pzy(floatValue, type, c2);
                String str = "BLOOD_SUGAR_CONTINUE";
                if (type == 2108) {
                    if (hiHealthData.getEndTime() - j2 < this.h) {
                        pzyVar.e(true);
                    } else {
                        j2 = hiHealthData.getEndTime();
                    }
                    c = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296797_res_0x7f09021d);
                } else if (this.c.equals("BLOOD_SUGAR_CONTINUE")) {
                    c = c(BaseApplication.getContext(), c2);
                    str = "BLOOD_SUGAR_FINGER_TIP";
                } else {
                    str = this.c;
                    c = c(BaseApplication.getContext(), c2);
                }
                pzyVar.a(c(hiHealthData));
                pzyVar.b(c);
                pzyVar.b(hiHealthData.getEndTime());
                pzyVar.b(hiHealthData.getString("device_uniquecode"));
                pzyVar.e(str);
                pzyVar.c(hiHealthData.getClientId());
                pzyVar.e(hiHealthData.getModifiedTime());
                map.put(Long.valueOf(hiHealthData.getEndTime()), pzyVar);
            }
        }
    }

    private int c(Context context, float f, int i) {
        Map<String, String> a2 = qjv.a(context, i, f);
        if (!a2.containsKey("HEALTH_BLOOD_SUGAR_LEVEL_KEY")) {
            return 1000;
        }
        try {
            return Integer.parseInt(a2.get("HEALTH_BLOOD_SUGAR_LEVEL_KEY"));
        } catch (NumberFormatException unused) {
            LogUtil.h("BloodSugarLineChartStorageHelper", "levelValue is String, parse to Integer error");
            return 1000;
        }
    }

    private boolean c(HiHealthData hiHealthData) {
        if (TextUtils.isEmpty(hiHealthData.getMetaData())) {
            return false;
        }
        return !((HiBloodSugarMetaData) HiJsonUtil.e(r2, HiBloodSugarMetaData.class)).getConfirmed();
    }

    private boolean b(qkg qkgVar) {
        if (TextUtils.isEmpty(qkgVar.f())) {
            return false;
        }
        return !((HiBloodSugarMetaData) HiJsonUtil.e(r2, HiBloodSugarMetaData.class)).getConfirmed();
    }

    private int c(Context context, int i) {
        if (context == null) {
            LogUtils.w("BloodSugarLineChartStorageHelper", "context is null");
            return 0;
        }
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                }
                return 0;
            }
            return ContextCompat.getColor(context, R.color._2131296795_res_0x7f09021b);
        }
        return ContextCompat.getColor(context, R.color._2131296799_res_0x7f09021f);
    }

    private String a(float f) {
        String format;
        if (this.f16355a == null) {
            this.f16355a = new DecimalFormat("0.0");
        }
        synchronized (d) {
            format = this.f16355a.format(f);
        }
        return format;
    }

    public void dwh_(Context context, Handler handler) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(qjv.d());
        hiDataReadOption.setCount(1);
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setSortOrder(1);
        kor.a().e(context, hiDataReadOption, new b(handler));
    }

    public void a() {
        this.e = true;
    }

    static class e implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private ResponseCallback<Map<Long, IStorageModel>> f16356a;
        private final String b;
        private final long c;
        private final boolean d;
        private final WeakReference<pzj> e;

        e(pzj pzjVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback, long j, boolean z, String str) {
            this.e = new WeakReference<>(pzjVar);
            this.f16356a = responseCallback;
            this.c = j;
            this.d = z;
            this.b = str;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            pzj pzjVar = this.e.get();
            if (pzjVar == null || pzjVar.e) {
                this.f16356a = null;
                return;
            }
            LogUtil.a("BloodSugarLineChartStorageHelper", this.b, " mIsHandleMealDifferences=", Boolean.valueOf(this.d), ", time = ", Long.valueOf(System.currentTimeMillis() - this.c), ", thread=", Long.valueOf(Thread.currentThread().getId()));
            List list = obj instanceof List ? (List) obj : null;
            if (this.d) {
                pzjVar.d((List<HiHealthData>) list, this.f16356a);
            } else {
                pzjVar.a(list, this.f16356a);
            }
        }
    }

    static class b implements IBaseResponseCallback {
        private final WeakReference<Handler> e;

        b(Handler handler) {
            this.e = new WeakReference<>(handler);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            HiHealthData hiHealthData;
            Handler handler = this.e.get();
            if (handler != null && (obj instanceof List)) {
                List list = (List) obj;
                if (list.size() == 0) {
                    return;
                }
                int i2 = 0;
                if (list.size() == 2) {
                    hiHealthData = (HiHealthData) list.get(0);
                    HiHealthData hiHealthData2 = (HiHealthData) list.get(1);
                    if (hiHealthData.getStartTime() <= hiHealthData2.getStartTime()) {
                        hiHealthData = hiHealthData2;
                    }
                    i2 = 12109;
                } else if (list.size() == 1) {
                    hiHealthData = (HiHealthData) list.get(0);
                    i2 = hiHealthData.getType();
                    if (i2 != 2108) {
                        i2 = 10001;
                    }
                } else {
                    LogUtil.a("BloodSugarLineChartStorageHelper", "not any bloodsugar data");
                    hiHealthData = null;
                }
                Message obtain = Message.obtain();
                obtain.arg1 = i2;
                obtain.obj = hiHealthData;
                handler.sendMessage(obtain);
            }
        }
    }
}
