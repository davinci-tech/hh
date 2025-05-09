package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import health.compact.a.Services;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class ouh {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f15956a = new Object();
    private static final Object c = new Object();
    private static volatile ouh e;
    private ExecutorService b;
    private MessageObserver g = new MessageObserver() { // from class: ouh.1
        @Override // com.huawei.pluginmessagecenter.service.MessageObserver
        public void onChange(int i, MessageChangeEvent messageChangeEvent) {
            LogUtil.a("UIHLH_NotificationInteractors", "MessageObserver onChange start");
            ouh.this.b();
        }
    };
    private MessageCenterApi d = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);

    private ouh() {
    }

    public static ouh d(Context context) {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new ouh();
                }
            }
        }
        return e;
    }

    public void e(ExecutorService executorService) {
        synchronized (f15956a) {
            this.b = executorService;
            c();
        }
    }

    public static boolean b(ExecutorService executorService) {
        synchronized (f15956a) {
            if (executorService == null) {
                return false;
            }
            executorService.shutdown();
            return true;
        }
    }

    private void c() {
        LogUtil.a("UIHLH_NotificationInteractors", "startMessageNotificationObserver enter");
        ExecutorService executorService = this.b;
        if (executorService == null || executorService.isShutdown()) {
            this.b = Executors.newSingleThreadExecutor();
        }
        this.b.execute(new Runnable() { // from class: ouh.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("UIHLH_NotificationInteractors", "startMessageNotificationObserver doRefresh()");
                if (ouh.this.g != null) {
                    LogUtil.a("UIHLH_NotificationInteractors", "openMessageObserver registerMessageObserver");
                    ouh.this.d.registerMessageObserver(ouh.this.g);
                }
                ouh.this.d.refreshMessages();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("UIHLH_NotificationInteractors", "refreshNotificationMessage enter");
        synchronized (f15956a) {
            ExecutorService executorService = this.b;
            if (executorService == null || executorService.isShutdown()) {
                this.b = Executors.newSingleThreadExecutor();
            }
            this.b.execute(new Runnable() { // from class: ouh.3
                @Override // java.lang.Runnable
                public void run() {
                    List a2 = ouh.this.a();
                    if (a2 != null && !a2.isEmpty()) {
                        LogUtil.a("UIHLH_NotificationInteractors", "refreshNotificationMessage notificationMessageList show count = ", Integer.valueOf(a2.size()));
                        Iterator it = a2.iterator();
                        while (it.hasNext()) {
                            ouh.this.a((MessageObject) it.next());
                        }
                        return;
                    }
                    LogUtil.a("UIHLH_NotificationInteractors", "refreshNotificationMessage notificationMessageList empty");
                }
            });
        }
    }

    public void d() {
        MessageObserver messageObserver = this.g;
        if (messageObserver != null) {
            this.d.unregisterMessageObserver(messageObserver);
        }
        synchronized (f15956a) {
            ExecutorService executorService = this.b;
            if (executorService != null && !executorService.isShutdown()) {
                this.b.shutdown();
                this.b = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MessageObject messageObject) {
        LogUtil.a("UIHLH_NotificationInteractors", "showNotificationMsg");
        this.d.showNotification(BaseApplication.e(), messageObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MessageObject> a() {
        LogUtil.a("UIHLH_NotificationInteractors", "getNotificationMessage result");
        return this.d.getMessages(2);
    }
}
