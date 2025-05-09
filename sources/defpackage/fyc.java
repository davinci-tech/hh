package defpackage;

import android.content.Context;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.fyc;
import defpackage.nrh;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class fyc {
    public static void b(IntPlan intPlan, Context context) {
        boolean x = fyw.x(intPlan);
        ReleaseLogUtil.e("Suggestion_PushDataManager", "pushDataToDevice isPlanOverdue ", Boolean.valueOf(x));
        if (x) {
            return;
        }
        d(intPlan, new AnonymousClass3(context, intPlan));
    }

    /* renamed from: fyc$3, reason: invalid class name */
    public class AnonymousClass3 extends UiCallback<List<FitWorkout>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f12689a;
        final /* synthetic */ IntPlan d;

        AnonymousClass3(Context context, IntPlan intPlan) {
            this.f12689a = context;
            this.d = intPlan;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            final Context context = this.f12689a;
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.ui.plan.manage.PushDataManager$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    nrh.b(context, R.string._2130844898_res_0x7f021ce2);
                }
            });
            LogUtil.h("Suggestion_PushDataManager", " pushDataToDevice failed with ", Integer.valueOf(i), " errorInfo:", str);
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_PUSH_PLAN_DEVICE_60010007.value(), i);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<FitWorkout> list) {
            gim c = gim.c(this.d.getPlanType().getType());
            final IntPlan intPlan = this.d;
            final Context context = this.f12689a;
            c.d(intPlan, list, 2, true, new IBaseResponseCallback() { // from class: fyg
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    fyc.AnonymousClass3.a(IntPlan.this, context, i, obj);
                }
            });
        }

        static /* synthetic */ void a(final IntPlan intPlan, final Context context, final int i, Object obj) {
            LogUtil.a("Suggestion_PushDataManager", "errorCode:", Integer.valueOf(i), " objData", obj);
            HandlerExecutor.e(new Runnable() { // from class: fyh
                @Override // java.lang.Runnable
                public final void run() {
                    fyc.AnonymousClass3.b(i, intPlan, context);
                }
            });
        }

        static /* synthetic */ void b(int i, IntPlan intPlan, Context context) {
            if (i == 0) {
                ase.q(intPlan);
                nrh.b(context, R.string._2130844899_res_0x7f021ce3);
            } else {
                nrh.b(context, R.string._2130844898_res_0x7f021ce2);
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_PUSH_PLAN_DEVICE_60010007.value(), i);
            }
        }
    }

    public static void d(IntPlan intPlan, final UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_PushDataManager", " getCurrentWeekCourse failed with callback is null.");
            return;
        }
        int g = ase.g(intPlan);
        ase.a(g, intPlan);
        final ArrayList arrayList = new ArrayList(ase.a(g, intPlan));
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        List<ffl> d = mod.d(arrayList);
        if (koq.b(d)) {
            uiCallback.onSuccess(new ArrayList());
        } else {
            courseApi.getCourseByIds(d, true, new UiCallback<List<FitWorkout>>() { // from class: fyc.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    UiCallback.this.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list) {
                    if (list != null && arrayList.size() == list.size()) {
                        UiCallback.this.onSuccess(list);
                    } else {
                        UiCallback.this.onFailure(0, "get courses result data size error.");
                    }
                }
            });
        }
    }

    public static boolean d() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_PushDataManager");
        return deviceInfo != null && deviceInfo.getDeviceConnectState() == 2;
    }
}
