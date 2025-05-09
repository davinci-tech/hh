package defpackage;

import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.notify.Action;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotifySendCallback;
import defpackage.toq;
import defpackage.tov;
import defpackage.tpq;
import java.util.concurrent.Callable;

/* loaded from: classes7.dex */
public final class tpu {
    private tpz e;

    private tpu() {
        this.e = new tpz();
    }

    static class a {
        private static final tpu d = new tpu();
    }

    public static tpu e() {
        return a.d;
    }

    public Task<Void> b(final Device device, final tpq tpqVar) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: tpu.2
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Void call() {
                top.a(device, "deviceId can not be null!");
                top.a(tpqVar, "notification can not be null!");
                tpu.this.b(tpqVar);
                NotifySendCallback.Stub a2 = tpu.this.a(tpqVar.a());
                int notify = tpu.this.e.notify(device, new NotificationParcel(tpqVar), a2);
                if (notify == 0) {
                    return null;
                }
                throw new tnx(notify);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NotifySendCallback.Stub a(final Action action) {
        return new NotifySendCallback.Stub() { // from class: com.huawei.wearengine.notify.NotifyClient$2
            @Override // com.huawei.wearengine.notify.NotifySendCallback
            public void onResult(NotificationParcel notificationParcel, int i) {
                if (notificationParcel == null) {
                    tov.c("NotifyClient", "getNotifySendCallback onResult NotificationParcel is null");
                    return;
                }
                Action action2 = action;
                if (action2 == null) {
                    tov.c("NotifyClient", "action is null");
                } else {
                    action2.onResult(new tpq.c().e(NotificationTemplate.getTemplateForTemplateId(notificationParcel.getTemplateId())).d(notificationParcel.getPackageName()).b(notificationParcel.getTitle()).e(notificationParcel.getText()).c(), i);
                }
            }

            @Override // com.huawei.wearengine.notify.NotifySendCallback
            public void onError(NotificationParcel notificationParcel, int i) {
                if (notificationParcel == null) {
                    tov.c("NotifyClient", "getNotifySendCallback onError NotificationParcel is null");
                    return;
                }
                Action action2 = action;
                if (action2 == null) {
                    tov.c("NotifyClient", "action is null");
                } else {
                    action2.onError(new tpq.c().e(NotificationTemplate.getTemplateForTemplateId(notificationParcel.getTemplateId())).d(notificationParcel.getPackageName()).b(notificationParcel.getTitle()).e(notificationParcel.getText()).c(), toq.b(String.valueOf(i)), toq.c(i));
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(tpq tpqVar) {
        trs.c(tpqVar.e());
        top.d(tpqVar.d(), 28);
        top.d(tpqVar.h(), 28);
        top.d(tpqVar.c(), 400);
        trs.a(tpqVar);
    }
}
