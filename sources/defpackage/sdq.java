package defpackage;

import android.content.Context;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.HiValidatorUtil;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class sdq {
    private static int[] g = {6500, 5620, 7560, 8940, 12500, 9630, 4765, 7865, 3650, 9900, 25160, 8655, 7650, 7895, 18650, 6500, 5620, 7560, 8940, 12500, 9630, 4765, 7865};
    private static int[] i = {13650, 9900, 21060, 8655, 7650, 7895, 650};
    private static int[] d = {17, 47, 53, 34, 66, 30, 27, 65, 50, 20, 60, 55, 50, 45, 30, 52, 22, 42, 40, 25, 30, 65, 17};

    /* renamed from: a, reason: collision with root package name */
    private static int[] f17036a = {29, 46, 31, 40, 23, 16, 10};
    private static int[] b = {630, 150, 450, HwExerciseConstants.NINE_MINUTES_PACE, 720, 630, 120, GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN, 360, 90, a.C, 450, 450, GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN, 690, 150, 180, GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN, a.C, 420, GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN, 270, 300};
    private static int[] c = {450, 300, HwExerciseConstants.NINE_MINUTES_PACE, a.C, 420, 270, 120};
    private static int f = 0;
    private static int j = 0;
    private static int e = 1;
    private static SecureRandom h = new SecureRandom();

    public static double b(double d2) {
        return d2 * 0.5d * 0.735d * 68.0d;
    }

    public static double c(double d2) {
        return (d2 / 10.0d) * 68.0d * 0.04d;
    }

    public static double c(int i2) {
        if (i2 <= 0) {
            return 0.0d;
        }
        return i2 * 0.735d;
    }

    static /* synthetic */ int a() {
        int i2 = j;
        j = i2 + 1;
        return i2;
    }

    static /* synthetic */ int d() {
        int i2 = f;
        f = i2 + 1;
        return i2;
    }

    public static void a(final Context context) {
        if (context == null) {
            LogUtil.h("StepCountDataMockUtil", "insertWeekStepDetailToDB context = null");
            return;
        }
        HiDataInsertOption c2 = c(context);
        if (c2 != null) {
            HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(c2, new HiDataOperateListener() { // from class: sdq.3
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i2, Object obj) {
                    LogUtil.a("StepCountDataMockUtil", "insertWeekStepDetailToDB() type = ", Integer.valueOf(i2), " data=", obj);
                    if (obj != null) {
                        if (i2 == 0) {
                            LogUtil.a("StepCountDataMockUtil", "insertWeekStepDetailToDB insert onSuccess");
                            return;
                        }
                        LogUtil.a("StepCountDataMockUtil", "insertWeekStepDetailToDB insert failed error=", obj);
                        if (sdq.f < sdq.e) {
                            sdq.a(context);
                            sdq.d();
                        }
                    }
                }
            });
        }
    }

    public static void d(final Context context) {
        if (context == null) {
            LogUtil.h("StepCountDataMockUtil", "insertMonthStepDetailToDB context = null");
            return;
        }
        HiDataInsertOption b2 = b(context);
        if (b2 != null) {
            HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(b2, new HiDataOperateListener() { // from class: sdq.2
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i2, Object obj) {
                    LogUtil.a("StepCountDataMockUtil", "insertMonthStepDetailToDB() type = ", Integer.valueOf(i2), " data=", obj);
                    if (obj != null) {
                        if (i2 == 0) {
                            LogUtil.a("StepCountDataMockUtil", "insertMonthStepDetailToDB insert onSuccess");
                            return;
                        }
                        LogUtil.a("StepCountDataMockUtil", "insertMonthStepDetailToDB insert failed error=", obj);
                        if (sdq.j < sdq.e) {
                            sdq.d(context);
                            sdq.a();
                        }
                    }
                }
            });
        }
    }

    private static HiDataInsertOption c(Context context) {
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        ArrayList arrayList = new ArrayList();
        long time = jec.d(jec.b(), -6).getTime();
        for (int i2 = 6; i2 >= 0; i2--) {
            int i3 = (int) (((i2 * 86400000) + time) / 60000);
            LogUtil.a("StepCountDataMockUtil", "getWeekStepDetailOption startMin=", Integer.valueOf(i3), " i = ", Integer.valueOf(i2));
            c(context, i[i2], i3, arrayList);
            b(context, c[i2], i3, arrayList);
            d(context, f17036a[i2], i3, arrayList);
        }
        hiDataInsertOption.setDatas(arrayList);
        return hiDataInsertOption;
    }

    private static HiDataInsertOption b(Context context) {
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        ArrayList arrayList = new ArrayList();
        long time = jec.d(jec.b(), -29).getTime();
        for (int i2 = 0; i2 < 23; i2++) {
            int i3 = (int) (((i2 * 86400000) + time) / 60000);
            c(context, g[i2], i3, arrayList);
            b(context, b[i2], i3, arrayList);
            d(context, d[i2], i3, arrayList);
        }
        hiDataInsertOption.setDatas(arrayList);
        return hiDataInsertOption;
    }

    private static void d(Context context, int i2, int i3, List<HiHealthData> list) {
        for (int i4 = 0; i4 < i2; i4++) {
            HiHealthData hiHealthData = new HiHealthData();
            long j2 = (i3 + 360 + i4) * 60000;
            hiHealthData.setTimeInterval(j2, 60000 + j2);
            if (i4 > 0 && i4 <= 10) {
                hiHealthData.setValue(1);
            } else if (i4 > 10 && i4 <= 30) {
                hiHealthData.setValue(2);
            } else if (i4 > 30 && i4 <= 45) {
                hiHealthData.setValue(4);
            } else {
                hiHealthData.setValue(3);
            }
            hiHealthData.setType(7);
            hiHealthData.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
            list.add(hiHealthData);
        }
    }

    private static void c(Context context, int i2, int i3, List<HiHealthData> list) {
        int i4 = i3 + 360;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            long j2 = i4;
            if (j2 >= i3 + 1440) {
                i4 = i5;
                break;
            }
            i7 = h.nextInt(20) + 90;
            i6 += i7;
            if (i6 > i2) {
                break;
            }
            a(context, list, j2 * 60000, i7);
            i5 = i4;
            i4 += 4;
        }
        a(context, list, i4 * 60000, i2 - (i6 - i7));
    }

    private static void b(Context context, int i2, int i3, List<HiHealthData> list) {
        int i4 = i3 + 360;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            long j2 = i4;
            if (j2 >= i3 + 1440) {
                i4 = i5;
                break;
            }
            i7 = (h.nextInt(2) * 30) + 30;
            i6 += i7;
            if (i6 > i2) {
                break;
            }
            e(context, list, j2 * 60000, i7);
            i5 = i4;
            i4 += 10;
        }
        e(context, list, i4 * 60000, i2 - (i6 - i7));
    }

    private static void a(Context context, List<HiHealthData> list, long j2, int i2) {
        double d2 = i2;
        if (HiValidatorUtil.b(2, d2)) {
            HiHealthData hiHealthData = new HiHealthData(2);
            hiHealthData.setTimeInterval(j2, j2 + 60000);
            hiHealthData.setValue(i2);
            hiHealthData.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
            list.add(hiHealthData);
            LogUtil.a("StepCountDataMockUtil", "fillMinDetailData stepPointData=", hiHealthData);
        }
        if (HiValidatorUtil.b(4, b(d2))) {
            HiHealthData hiHealthData2 = new HiHealthData(4);
            hiHealthData2.setTimeInterval(j2, j2 + 60000);
            hiHealthData2.setValue(b(d2));
            hiHealthData2.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
            list.add(hiHealthData2);
            LogUtil.a("StepCountDataMockUtil", "fillMinDetailData calPointData=", hiHealthData2);
        }
        if (HiValidatorUtil.b(3, c(i2))) {
            HiHealthData hiHealthData3 = new HiHealthData(3);
            hiHealthData3.setTimeInterval(j2, j2 + 60000);
            hiHealthData3.setValue(c(i2));
            hiHealthData3.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
            list.add(hiHealthData3);
            LogUtil.a("StepCountDataMockUtil", "fillMinDetailData disPointData=", hiHealthData3);
        }
        if (HiValidatorUtil.b(20002, 20002.0d)) {
            HiHealthData hiHealthData4 = new HiHealthData(20002);
            hiHealthData4.setTimeInterval(j2, 60000 + j2);
            hiHealthData4.setValue(20002);
            hiHealthData4.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
            list.add(hiHealthData4);
        }
    }

    private static void e(Context context, List<HiHealthData> list, long j2, int i2) {
        double d2 = i2;
        if (HiValidatorUtil.b(5, d2)) {
            HiHealthData hiHealthData = new HiHealthData(5);
            hiHealthData.setTimeInterval(j2, j2 + 60000);
            hiHealthData.setValue(i2);
            hiHealthData.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
            list.add(hiHealthData);
            LogUtil.a("StepCountDataMockUtil", "fillDayFloorDetail pointData=", hiHealthData);
        }
        if (HiValidatorUtil.b(20004, 20004.0d)) {
            HiHealthData hiHealthData2 = new HiHealthData(20004);
            hiHealthData2.setTimeInterval(j2, j2 + 60000);
            hiHealthData2.setValue(20004);
            hiHealthData2.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
            list.add(hiHealthData2);
        }
        if (HiValidatorUtil.b(4, c(d2))) {
            HiHealthData hiHealthData3 = new HiHealthData(4);
            hiHealthData3.setTimeInterval(j2, 60000 + j2);
            hiHealthData3.setValue(c(d2));
            hiHealthData3.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
            list.add(hiHealthData3);
            LogUtil.a("StepCountDataMockUtil", "fillMinDetailData calPointData=", hiHealthData3);
        }
    }
}
