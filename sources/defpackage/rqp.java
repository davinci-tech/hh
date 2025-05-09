package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class rqp {

    /* renamed from: a, reason: collision with root package name */
    private static final String f16881a = "DataTypesOfSource";
    private final Context d = BaseApplication.getContext();
    private final Handler e = new Handler(Looper.getMainLooper());

    protected void c(int i, String str, String str2, int i2, DataSourceCallback<rjz> dataSourceCallback) {
        HiDataReadProOption b = b(i, str, str2, i2);
        HiHealthNativeApi a2 = HiHealthNativeApi.a(this.d);
        LogUtil.a(f16881a, "getDataTypes start");
        a2.readHiHealthDataPro(b, new AnonymousClass3(dataSourceCallback, str));
    }

    /* renamed from: rqp$3, reason: invalid class name */
    class AnonymousClass3 implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f16882a;
        final /* synthetic */ DataSourceCallback e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        AnonymousClass3(DataSourceCallback dataSourceCallback, String str) {
            this.e = dataSourceCallback;
            this.f16882a = str;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a(rqp.f16881a, "getDataTypes end errorCode = ", Integer.valueOf(i));
            if (i != 0) {
                Handler handler = rqp.this.e;
                final DataSourceCallback dataSourceCallback = this.e;
                handler.post(new Runnable() { // from class: rqv
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, null);
                    }
                });
                return;
            }
            SparseArray sparseArray = new SparseArray();
            if (obj instanceof SparseArray) {
                sparseArray = (SparseArray) obj;
            }
            if (sparseArray.size() == 0) {
                LogUtil.b(rqp.f16881a, "has no data");
                Handler handler2 = rqp.this.e;
                final DataSourceCallback dataSourceCallback2 = this.e;
                handler2.post(new Runnable() { // from class: rqs
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, null);
                    }
                });
                return;
            }
            HashMap hashMap = new HashMap(sparseArray.size());
            for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                hashMap.put(Integer.valueOf(sparseArray.keyAt(i3)), Integer.valueOf(i3));
            }
            final rjz e = rqp.this.e(hashMap);
            e.e(this.f16882a);
            Handler handler3 = rqp.this.e;
            final DataSourceCallback dataSourceCallback3 = this.e;
            handler3.post(new Runnable() { // from class: rqu
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(0, e);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public rjz e(HashMap<Integer, Integer> hashMap) {
        rjz rjzVar = new rjz();
        rjzVar.h(d(hashMap, new int[]{2}));
        rjzVar.i(d(hashMap, new int[]{3}));
        rjzVar.a(d(hashMap, new int[]{4}));
        rjzVar.d(d(hashMap, new int[]{5}));
        rjzVar.g(d(hashMap, new int[]{2002, 2018, 2105}));
        rjzVar.n(d(hashMap, new int[]{10006}));
        rjzVar.j(d(hashMap, new int[]{22100, 22000, 22107, 22106}));
        rjzVar.f(d(hashMap, new int[]{2034, 44307}));
        rjzVar.e(d(hashMap, new int[]{2103, 47204}));
        rjzVar.c(d(hashMap, new int[]{DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value()}));
        rjzVar.b(d(hashMap, new int[]{2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2106}));
        rjzVar.o(d(hashMap, new int[]{2104, DicDataTypeUtil.DataType.BODY_TEMPERATURE.value(), DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value(), DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value()}));
        return rjzVar;
    }

    private boolean d(HashMap<Integer, Integer> hashMap, int[] iArr) {
        for (int i : iArr) {
            if (hashMap.containsKey(Integer.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    private HiDataReadProOption b(int i, String str, String str2, int i2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(c(i2, str, str2));
        hiDataReadOption.setReadType(i);
        hiDataReadOption.setDeviceUuid(str);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(System.currentTimeMillis());
        hiDataReadOption.setCount(1);
        return HiDataReadProOption.builder().e(hiDataReadOption).a(str2).e();
    }

    private int[] c(int i, String str, String str2) {
        if (rrb.e(i, this.d)) {
            return new int[]{DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value()};
        }
        if (rrb.c(str2, str)) {
            return new int[]{DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value(), 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2106, 2104, 2104, DicDataTypeUtil.DataType.BODY_TEMPERATURE.value(), DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value(), DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value(), 10006, 22107, 22106};
        }
        return new int[]{2, 3, 4, 5, 2002, 2018, 2105, 10006, 22100, 22000, 2034, 44307, 2103, 47204, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2106, 2104, DicDataTypeUtil.DataType.BODY_TEMPERATURE.value(), DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value(), DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value()};
    }
}
