package com.huawei.ui.main.stories.me.util;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StepCounterSupport;
import health.compact.a.StorageParams;

/* loaded from: classes7.dex */
public class StepCounterSupportUtil {

    public interface StepCounterClassCallback {
        void getDeviceClass(int i);
    }

    public interface StepCounterSupportCallback {
        void onIsSupportStepCounter(boolean z);
    }

    public static boolean d(final Context context, final StepCounterSupportCallback stepCounterSupportCallback) {
        final String b = SharedPreferenceManager.b(context, Integer.toString(10000), "step_counter_support_class_key");
        LogUtil.a("Main_StepCounterSupportUtil", "getIsSupportStepCounter sharedPreference = ", b);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.me.util.StepCounterSupportUtil.5
            @Override // java.lang.Runnable
            public void run() {
                int d = StepCounterSupport.d(context);
                if (Integer.toString(d).equals(b)) {
                    return;
                }
                stepCounterSupportCallback.onIsSupportStepCounter(d != 3);
                SharedPreferenceManager.e(context, Integer.toString(10000), "step_counter_support_class_key", Integer.toString(d), (StorageParams) null);
                LogUtil.a("Main_StepCounterSupportUtil", "getIsSupportStepCounter supportClass = ", Integer.valueOf(d));
            }
        });
        return Integer.toString(1).equals(b) || Integer.toString(2).equals(b);
    }

    public static void c(final StepCounterClassCallback stepCounterClassCallback) {
        final String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "step_counter_support_class_key");
        LogUtil.a("Main_StepCounterSupportUtil", "getDeviceSupportClass sharedPreference = ", b);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.me.util.StepCounterSupportUtil.4
            @Override // java.lang.Runnable
            public void run() {
                int d = StepCounterSupport.d(BaseApplication.getContext());
                if (Integer.toString(d).equals(b)) {
                    return;
                }
                stepCounterClassCallback.getDeviceClass(d);
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "step_counter_support_class_key", Integer.toString(d), (StorageParams) null);
                LogUtil.a("Main_StepCounterSupportUtil", "getDeviceSupportClass supportClass = ", Integer.valueOf(d));
            }
        });
    }

    public static int a(Context context) {
        return CommonUtil.h(SharedPreferenceManager.b(context, Integer.toString(10000), "step_counter_support_class_key"));
    }
}
