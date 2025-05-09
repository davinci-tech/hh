package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class bbr {
    private static int d(int i) {
        if (i >= 1 && i <= 29) {
            return 1;
        }
        if (i <= 29 || i >= 60) {
            return (i < 60 || i >= 80) ? 4 : 3;
        }
        return 2;
    }

    private static HiDataReadOption b(int i) {
        int[] iArr;
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(String.valueOf(i), String.valueOf(i), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        if (b()) {
            iArr = new int[]{2034};
            hiDataReadOption.setCount(1);
            hiDataReadOption.setSortOrder(1);
        } else {
            iArr = new int[]{2034, 44306};
            hiDataReadOption.setSortOrder(0);
        }
        hiDataReadOption.setType(iArr);
        return hiDataReadOption;
    }

    private static HiDataReadOption b(int i, int i2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(String.valueOf(i), String.valueOf(i2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiDataReadOption.setType(new int[]{2034, 44306});
        hiDataReadOption.setSortOrder(0);
        return hiDataReadOption;
    }

    public static void e(final HealthLifeBean healthLifeBean, final ResponseCallback<HealthLifeBean> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_PressureUtils", "getPressureMeasureDay callback is null");
            return;
        }
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_PressureUtils", "getPressureMeasureDay healthLifeBean is null");
            responseCallback.onResponse(-1, null);
        } else if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: bbt
                @Override // java.lang.Runnable
                public final void run() {
                    bbr.e(HealthLifeBean.this, responseCallback);
                }
            });
        } else {
            LogUtil.a("HealthLife_PressureUtils", "achieveHandle isComplete=", Integer.valueOf(healthLifeBean.getComplete()), ",recordDay=", Integer.valueOf(healthLifeBean.getRecordDay()));
            a(healthLifeBean, responseCallback);
        }
    }

    public static void mN_(final SparseArray<HealthLifeBean> sparseArray, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_PressureUtils", "getPressureMeasureDay callback is null");
            return;
        }
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.h("HealthLife_PressureUtils", "getPressureMeasureDay healthLifeBean is null");
            responseCallback.onResponse(-1, null);
        } else if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: bbx
                @Override // java.lang.Runnable
                public final void run() {
                    bbr.mN_(sparseArray, responseCallback);
                }
            });
        } else {
            mS_(sparseArray, responseCallback);
        }
    }

    private static void a(final HealthLifeBean healthLifeBean, final ResponseCallback<HealthLifeBean> responseCallback) {
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthData(b(healthLifeBean.getRecordDay()), new HiDataReadResultListener() { // from class: bbr.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("HealthLife_PressureUtils", "readHiHealthData errorCode = ", Integer.valueOf(i));
                if (!(obj instanceof SparseArray)) {
                    ResponseCallback.this.onResponse(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    ResponseCallback.this.onResponse(-1, null);
                    return;
                }
                Object obj2 = sparseArray.get(2034);
                List list = koq.e(obj2, HiHealthData.class) ? (List) obj2 : null;
                if (bbr.b()) {
                    bbr.e(list, healthLifeBean, ResponseCallback.this);
                } else {
                    Object obj3 = sparseArray.get(44306);
                    bbr.b(list, koq.e(obj3, HiHealthData.class) ? (List) obj3 : null, healthLifeBean, ResponseCallback.this);
                }
            }
        });
    }

    private static void mS_(final SparseArray<HealthLifeBean> sparseArray, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthData(b(sparseArray.keyAt(0), sparseArray.keyAt(sparseArray.size() - 1)), new HiDataReadResultListener() { // from class: bbr.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("HealthLife_PressureUtils", "readHiHealthData errorCode = ", Integer.valueOf(i));
                if (!(obj instanceof SparseArray)) {
                    ResponseCallback.this.onResponse(-1, null);
                    return;
                }
                SparseArray sparseArray2 = (SparseArray) obj;
                if (sparseArray2.size() <= 0) {
                    ResponseCallback.this.onResponse(-1, null);
                    return;
                }
                SparseIntArray sparseIntArray = new SparseIntArray();
                Object obj2 = sparseArray2.get(44306);
                if (koq.e(obj2, HiHealthData.class)) {
                    bbr.mQ_(sparseIntArray, (List) obj2);
                    SparseArray sparseArray3 = new SparseArray();
                    Object obj3 = sparseArray2.get(2034);
                    if (koq.e(obj3, HiHealthData.class)) {
                        bbr.mR_(sparseArray3, (List) obj3);
                        bbr.mO_(sparseArray, sparseIntArray, sparseArray3, ResponseCallback.this);
                        return;
                    } else {
                        ResponseCallback.this.onResponse(-1, null);
                        return;
                    }
                }
                ResponseCallback.this.onResponse(-1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(List<HiHealthData> list, List<HiHealthData> list2, HealthLifeBean healthLifeBean, ResponseCallback<HealthLifeBean> responseCallback) {
        int i;
        boolean z = true;
        boolean z2 = healthLifeBean.getComplete() == 1;
        if (z2 || !koq.c(list2)) {
            z = z2;
            i = 0;
        } else {
            i = list2.get(0).getIntValue();
            if (i >= 60 || !c(healthLifeBean.getRecordDay())) {
                z = z2;
            } else {
                c(healthLifeBean);
            }
        }
        if (koq.c(list)) {
            c(z, i, list, healthLifeBean);
        }
        responseCallback.onResponse(0, healthLifeBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(List<HiHealthData> list, HealthLifeBean healthLifeBean, ResponseCallback<HealthLifeBean> responseCallback) {
        if (koq.b(list)) {
            responseCallback.onResponse(-1, healthLifeBean);
        } else {
            b(list.get(0), healthLifeBean);
            responseCallback.onResponse(0, healthLifeBean);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mO_(SparseArray<HealthLifeBean> sparseArray, SparseIntArray sparseIntArray, SparseArray<List<HiHealthData>> sparseArray2, ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        HealthLifeBean valueAt;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray.keyAt(i);
            int i2 = sparseIntArray.get(keyAt);
            if (i2 != 0 && (valueAt = sparseArray.valueAt(i)) != null) {
                List<HiHealthData> list = sparseArray2.get(keyAt);
                if (!koq.b(list)) {
                    boolean z = true;
                    boolean z2 = valueAt.getComplete() == 1;
                    if (z2) {
                        c(true, i2, list, valueAt);
                    } else {
                        if (i2 >= 60 || !c(keyAt)) {
                            z = z2;
                        } else {
                            c(valueAt);
                        }
                        c(z, i2, list, valueAt);
                    }
                }
            }
        }
        responseCallback.onResponse(0, sparseArray);
    }

    private static void c(HealthLifeBean healthLifeBean) {
        long currentTimeMillis = System.currentTimeMillis();
        int complete = healthLifeBean.getComplete();
        healthLifeBean.setTimestamp(currentTimeMillis);
        healthLifeBean.setComplete(1);
        healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
        healthLifeBean.setIsUpdated(true);
        healthLifeBean.setSyncStatus(0);
        if (TextUtils.isEmpty(healthLifeBean.getResult())) {
            healthLifeBean.setResult(String.valueOf(0));
        }
        if (complete > 0 || healthLifeBean.getRecordDay() != DateFormatUtil.b(currentTimeMillis)) {
            return;
        }
        aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mR_(SparseArray<List<HiHealthData>> sparseArray, List<HiHealthData> list) {
        for (HiHealthData hiHealthData : list) {
            int c = DateFormatUtil.c(hiHealthData.getEndTime(), jdl.d(hiHealthData.getTimeZone()));
            List<HiHealthData> list2 = sparseArray.get(c);
            if (list2 == null) {
                list2 = new ArrayList<>();
            }
            list2.add(hiHealthData);
            sparseArray.put(c, list2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mQ_(SparseIntArray sparseIntArray, List<HiHealthData> list) {
        for (HiHealthData hiHealthData : list) {
            sparseIntArray.put(DateFormatUtil.b(hiHealthData.getEndTime()), hiHealthData.getIntValue());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0080, code lost:
    
        c(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007e, code lost:
    
        if (r10 != false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void c(boolean r10, int r11, java.util.List<com.huawei.hihealth.HiHealthData> r12, com.huawei.health.healthmodel.bean.HealthLifeBean r13) {
        /*
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r10)
            java.lang.String r1 = ",averageValue"
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.String r2 = "handlePressureValue isAchieve="
            java.lang.Object[] r11 = new java.lang.Object[]{r2, r0, r1, r11}
            java.lang.String r0 = "HealthLife_PressureUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r0, r11)
            if (r10 == 0) goto L27
            int r10 = r12.size()
            int r10 = r10 + (-1)
            java.lang.Object r10 = r12.get(r10)
            com.huawei.hihealth.HiHealthData r10 = (com.huawei.hihealth.HiHealthData) r10
            b(r10, r13)
            return
        L27:
            r11 = 0
            java.lang.Object r0 = r12.get(r11)
            com.huawei.hihealth.HiHealthData r0 = (com.huawei.hihealth.HiHealthData) r0
            int r0 = r0.getIntValue()
            int r1 = r12.size()
            r2 = r11
            r3 = r2
            r4 = r3
        L39:
            int r5 = r12.size()
            if (r2 >= r5) goto L7e
            java.lang.Object r5 = r12.get(r2)
            com.huawei.hihealth.HiHealthData r5 = (com.huawei.hihealth.HiHealthData) r5
            long r6 = r5.getEndTime()
            r8 = 18
            long r8 = defpackage.jdl.e(r6, r8, r11)
            int r5 = r5.getIntValue()
            int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r8 > 0) goto L61
            int r8 = r1 + (-1)
            if (r2 == r8) goto L61
            int r3 = r3 + 1
            int r4 = r4 + r5
            int r0 = r4 / r3
            goto L7b
        L61:
            int r3 = r3 + 1
            int r4 = r4 + r5
            int r5 = r4 / r3
            int r0 = java.lang.Math.min(r0, r5)
            if (r0 <= 0) goto L7b
            r5 = 60
            if (r0 >= r5) goto L7b
            int r5 = com.huawei.hwcommonmodel.utils.DateFormatUtil.b(r6)
            boolean r5 = c(r5)
            if (r5 == 0) goto L7b
            goto L80
        L7b:
            int r2 = r2 + 1
            goto L39
        L7e:
            if (r10 == 0) goto L83
        L80:
            c(r13)
        L83:
            int r10 = r12.size()
            int r10 = r10 + (-1)
            java.lang.Object r10 = r12.get(r10)
            com.huawei.hihealth.HiHealthData r10 = (com.huawei.hihealth.HiHealthData) r10
            b(r10, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bbr.c(boolean, int, java.util.List, com.huawei.health.healthmodel.bean.HealthLifeBean):void");
    }

    private static void b(HiHealthData hiHealthData, HealthLifeBean healthLifeBean) {
        eyw eywVar = new eyw();
        int intValue = hiHealthData.getIntValue();
        eywVar.b(intValue);
        eywVar.e(hiHealthData.getEndTime() / 1000);
        eywVar.c(d(intValue));
        healthLifeBean.setExtendInfo(HiJsonUtil.e(eywVar));
        LogUtil.a("HealthLife_PressureUtils", "convertPressureData measureBean=", eywVar.toString());
    }

    private static boolean c(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        return jdl.e(currentTimeMillis, 18, 0) < currentTimeMillis || i != DateFormatUtil.b(currentTimeMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis < jdl.e(currentTimeMillis, 18, 0);
    }
}
