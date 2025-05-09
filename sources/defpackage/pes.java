package defpackage;

import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class pes {
    public static void c() {
        boolean e = peg.e();
        LogUtil.c("CalendarSettingUtil", "setCalendarSwitch supportHealthCalendarSync:", Boolean.valueOf(e));
        if (e) {
            d(new IBaseResponseCallback() { // from class: pes.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    boolean d = nrk.d(BaseApplication.getContext());
                    LogUtil.c("CalendarSettingUtil", "getCalendarSampleConfig hasCalendarPermission : ", Boolean.valueOf(d), "errorCode:", Integer.valueOf(i), "objData:", obj);
                    if (d && i == 0 && (obj instanceof String)) {
                        d = "1".equals(obj);
                    }
                    pes.b(d, new IBaseResponseCallback() { // from class: pes.2.4
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj2) {
                            LogUtil.c("CalendarSettingUtil", "setSyncCalendarSampleConfig errorCode:", Integer.valueOf(i2), "objData:", obj2);
                        }
                    });
                }
            });
        }
    }

    public static final void d(final IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900100019");
        njj.d("9001", arrayList, new HiDataReadResultListener() { // from class: pes.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.b("R_CalendarSettingUtil", "onResult errorCode: ", Integer.valueOf(i), ", data: ", obj);
                if (obj instanceof List) {
                    List list = (List) obj;
                    if (!koq.b(list) && koq.e(obj, HiSampleConfig.class)) {
                        HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
                        LogUtil.c("CalendarSettingUtil", "calendar switch num : ", Integer.valueOf(list.size()), ", config : ", hiSampleConfig.getConfigData(), ", modifyTime : ", Long.valueOf(hiSampleConfig.getModifiedTime()));
                        IBaseResponseCallback.this.d(0, dsl.c(hiSampleConfig.getConfigData(), "calendarToDeviceSwitch"));
                        return;
                    }
                }
                IBaseResponseCallback.this.d(-1, null);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                ReleaseLogUtil.b("R_CalendarSettingUtil", "onResultIntent errorCode: ", Integer.valueOf(i2), ", data: ", obj);
            }
        });
    }

    public static final void b(final boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        njj.a("9001", "900100019", dsl.e("calendarToDeviceSwitch", z ? "1" : "0"), new HiDataOperateListener() { // from class: pes.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.b("R_CalendarSettingUtil", "saveSampleConfig errorCode: ", Integer.valueOf(i), ", object: ", obj);
                if (1 != i) {
                    if (z) {
                        LogUtil.c("CalendarSettingUtil", "isSupportCalendarPermission true.");
                        nhu.b().syncCalendarSwitch(1);
                    } else {
                        LogUtil.c("CalendarSettingUtil", "isSupportCalendarPermission false.");
                        nhu.b().syncCalendarSwitch(7);
                    }
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(0, null);
                        return;
                    }
                    return;
                }
                IBaseResponseCallback iBaseResponseCallback3 = iBaseResponseCallback;
                if (iBaseResponseCallback3 != null) {
                    iBaseResponseCallback3.d(-1, null);
                }
            }
        }, System.currentTimeMillis());
    }
}
