package defpackage;

import android.app.Activity;
import android.content.Context;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.notify.Action;
import com.huawei.wearengine.notify.NotificationTemplate;
import defpackage.tpq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class fyx {
    public static void a(final String str, final IntPlan intPlan) {
        final Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        tnu.c(activity).b().addOnSuccessListener(new OnSuccessListener() { // from class: fzc
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                fyx.e(arrayList, activity, intPlan, str, (List) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: fzg
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LogUtil.a("IntPlanWatchUtil", "get BondedDevices Failure: ", exc.getMessage());
            }
        });
    }

    static /* synthetic */ void e(List list, Context context, IntPlan intPlan, String str, List list2) {
        list.addAll(list2);
        Device device = null;
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Device device2 = (Device) it.next();
                if (device2.isConnected()) {
                    LogUtil.a("IntPlanWatchUtil", "the device is Connected.");
                    device = device2;
                }
            }
        }
        if (device != null) {
            a(device, context, intPlan.getMetaInfo().getName(), str);
        }
    }

    private static void a(Device device, Context context, String str, final String str2) {
        tpu d = tnu.d(context);
        tpq.c cVar = new tpq.c();
        cVar.a(new Action() { // from class: fyx.4
            @Override // com.huawei.wearengine.notify.Action
            public void onResult(tpq tpqVar, int i) {
                LogUtil.a("IntPlanWatchUtil", "notifyMsgToWatch onResult feedback=", Integer.valueOf(i));
            }

            @Override // com.huawei.wearengine.notify.Action
            public void onError(tpq tpqVar, int i, String str3) {
                LogUtil.a("IntPlanWatchUtil", "notifyMsgToWatch onError errorCode= ", Integer.valueOf(i), " errorMsg= ", str3);
            }
        });
        cVar.e(NotificationTemplate.NOTIFICATION_TEMPLATE_NO_BUTTON);
        cVar.d(context.getPackageName());
        cVar.b(fyw.b(str, 28));
        cVar.e(str2);
        tpq c = cVar.c();
        if (device == null || !device.isConnected()) {
            return;
        }
        d.b(device, c).addOnSuccessListener(new OnSuccessListener() { // from class: fzd
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                LogUtil.a("IntPlanWatchUtil", "the message is notified as: ", str2);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: fzf
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LogUtil.a("IntPlanWatchUtil", exc.getMessage(), " failure on notifying the message:", str2);
            }
        });
    }
}
