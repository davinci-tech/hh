package com.huawei.wearengine.service.api;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.huawei.wearengine.NotifyManager;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.core.common.ClientBinderDied;
import com.huawei.wearengine.core.device.PowerModeChangeManager;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.notify.NotificationHarmony;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotificationTemplate;
import com.huawei.wearengine.notify.NotifyHanrmonyCallback;
import com.huawei.wearengine.notify.NotifySendCallback;
import com.huawei.wearengine.utils.DeviceProcessor;
import defpackage.toh;
import defpackage.tom;
import defpackage.tor;
import defpackage.tos;
import defpackage.tot;
import defpackage.toz;
import defpackage.tpq;
import defpackage.tqo;
import defpackage.tri;
import defpackage.trj;

/* loaded from: classes9.dex */
public class NotifyManagerImpl extends NotifyManager.Stub implements ClientBinderDied {

    /* renamed from: a, reason: collision with root package name */
    private toz f11244a;
    private toh c;
    private tor d;

    public NotifyManagerImpl(toh tohVar, tor torVar) {
        this.f11244a = new toz(torVar);
        this.c = tohVar;
        this.d = torVar;
    }

    @Override // com.huawei.wearengine.NotifyManager
    public int notify(Device device, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) {
        tos.a("NotifyManagerImpl", "Start notify");
        tom.e(device, "deviceId must not be null!");
        tom.e(notificationParcel, "notificationParcel must not be null!");
        tom.e(notifySendCallback, "notifySendCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.d.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            this.c.a(c, "notify", tqo.d, Permission.NOTIFY);
            this.f11244a.c(DeviceProcessor.processInputDevice(c, device), notificationParcel, notifySendCallback);
            wearEngineBiOperate.biApiCalling(a2, c, "notify", String.valueOf(0));
            return 0;
        } catch (IllegalStateException e) {
            tos.e("NotifyManagerImpl", "notify illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, "notify", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.NotifyManager
    public int notifyHarmonyEx(Device device, NotificationHarmony notificationHarmony, final NotifyHanrmonyCallback notifyHanrmonyCallback) throws RemoteException {
        tos.b("NotifyManagerImpl", "Start notifyHarmonyEx");
        tom.e(device, "deviceId must not be null!");
        tom.e(notificationHarmony, "notificationHarmony must not be null!");
        tom.e(notifyHanrmonyCallback, "notifyHanrmonyCallback must not be null!");
        return notify(device, a(notificationHarmony), new NotifySendCallback.Stub() { // from class: com.huawei.wearengine.service.api.NotifyManagerImpl.1
            @Override // com.huawei.wearengine.notify.NotifySendCallback
            public void onResult(NotificationParcel notificationParcel, int i) throws RemoteException {
                notifyHanrmonyCallback.onResult(NotifyManagerImpl.this.a(notificationParcel), i);
            }

            @Override // com.huawei.wearengine.notify.NotifySendCallback
            public void onError(NotificationParcel notificationParcel, int i) throws RemoteException {
                notifyHanrmonyCallback.onError(NotifyManagerImpl.this.a(notificationParcel), i);
            }
        });
    }

    @Override // com.huawei.wearengine.core.common.ClientBinderDied
    public void handleClientBinderDied(String str) {
        tos.b("NotifyManagerImpl", "handleClientBinderDied clientPkgName is: " + str);
    }

    private NotificationParcel a(NotificationHarmony notificationHarmony) {
        return new NotificationParcel(new tpq.c().e(NotificationTemplate.getTemplateForTemplateId(notificationHarmony.getTemplateId())).d(notificationHarmony.getPackageName()).b(notificationHarmony.getTitle()).e(notificationHarmony.getText()).e(notificationHarmony.getButtonContents()).c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NotificationHarmony a(NotificationParcel notificationParcel) {
        return new NotificationHarmony(new tpq.c().e(NotificationTemplate.getTemplateForTemplateId(notificationParcel.getTemplateId())).d(notificationParcel.getPackageName()).b(notificationParcel.getTitle()).e(notificationParcel.getText()).e(notificationParcel.getButtonContents()).c());
    }
}
