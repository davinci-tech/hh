package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorItem;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class dtr {
    public static void b(final ResponseCallback<Boolean> responseCallback) {
        if (responseCallback == null) {
            return;
        }
        final tpc c = tnu.c(BaseApplication.e());
        c.a().addOnSuccessListener(new OnSuccessListener() { // from class: dtx
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                dtr.a(ResponseCallback.this, c, (Boolean) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: dtt
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                dtr.e(ResponseCallback.this, exc);
            }
        });
    }

    static /* synthetic */ void a(final ResponseCallback responseCallback, tpc tpcVar, Boolean bool) {
        if (!bool.booleanValue()) {
            responseCallback.onResponse(-2, false);
        } else {
            tpcVar.b().addOnSuccessListener(new OnSuccessListener() { // from class: dtu
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    dtr.d((List<Device>) obj, (ResponseCallback<Boolean>) ResponseCallback.this);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: dtz
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    dtr.c(ResponseCallback.this, exc);
                }
            });
        }
    }

    static /* synthetic */ void c(ResponseCallback responseCallback, Exception exc) {
        LogUtil.b("WearCheckUtil", "checkWearStatus: call getBondedDevices exception: ", exc);
        responseCallback.onResponse(-1, false);
    }

    static /* synthetic */ void e(ResponseCallback responseCallback, Exception exc) {
        LogUtil.b("WearCheckUtil", "checkWearStatus: call hasAvailableDevices exception: ", exc);
        responseCallback.onResponse(-1, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(List<Device> list, final ResponseCallback<Boolean> responseCallback) {
        Device device;
        if (list == null || list.isEmpty()) {
            responseCallback.onResponse(-2, false);
            return;
        }
        Iterator<Device> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                device = null;
                break;
            }
            device = it.next();
            if (device != null && device.isConnected()) {
                break;
            }
        }
        if (device == null) {
            responseCallback.onResponse(-2, false);
        } else {
            tnu.a(BaseApplication.e()).c(device, MonitorItem.MONITOR_ITEM_WEAR).addOnSuccessListener(new OnSuccessListener() { // from class: dtw
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    dtr.e(ResponseCallback.this, (MonitorData) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: dtv
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    dtr.a(ResponseCallback.this, exc);
                }
            });
        }
    }

    static /* synthetic */ void e(ResponseCallback responseCallback, MonitorData monitorData) {
        int asInt = monitorData.asInt();
        responseCallback.onResponse(asInt, Boolean.valueOf(asInt == 1));
    }

    static /* synthetic */ void a(ResponseCallback responseCallback, Exception exc) {
        LogUtil.b("WearCheckUtil", "handleSuccess: call query exception: ", exc);
        responseCallback.onResponse(-1, false);
    }
}
