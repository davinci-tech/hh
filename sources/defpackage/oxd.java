package defpackage;

import android.content.Context;
import android.os.Handler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes6.dex */
public class oxd {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f15999a = new Object();
    private static oxd b;
    private boolean c;
    private boolean d;
    private DeviceCapability f;
    private DeviceSettingsInteractors h;
    private oxa j;
    private Handler l = null;
    private MessageObserver g = new MessageObserver() { // from class: oxd.5
        @Override // com.huawei.pluginmessagecenter.service.MessageObserver
        public void onChange(int i, MessageChangeEvent messageChangeEvent) {
            LogUtil.a("NotificationCardInteractors", "MessageObserver onChange start");
            if (messageChangeEvent == null) {
                return;
            }
            oxd.this.d();
        }
    };
    private Runnable n = new Runnable() { // from class: oxd.2
        @Override // java.lang.Runnable
        public void run() {
            oxd.this.c = false;
            LogUtil.a("NotificationCardInteractors", "pushMessageToDevice weakHandler.postDelayed mPushFaqMessageState = false");
        }
    };
    private Context e = BaseApplication.getContext();
    private MessageCenterApi i = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);

    public void b() {
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
    }

    private oxd() {
        this.f = null;
        this.d = false;
        this.c = false;
        this.d = false;
        this.c = false;
        DeviceSettingsInteractors d = DeviceSettingsInteractors.d(this.e);
        this.h = d;
        this.f = d.e();
        this.j = oxa.a();
    }

    public static oxd a() {
        oxd oxdVar;
        synchronized (f15999a) {
            if (b == null) {
                b = new oxd();
            }
            oxdVar = b;
        }
        return oxdVar;
    }

    public void djp_(Handler handler) {
        if (handler == null) {
            return;
        }
        this.l = handler;
        if (this.i == null || this.g == null) {
            return;
        }
        LogUtil.a("NotificationCardInteractors", "openMessageObserver registerMessageObserver");
        this.i.registerMessageObserver(this.g);
    }

    public void d() {
        if (this.d || this.c) {
            LogUtil.a("NotificationCardInteractors", "getFAQMessageProcess mGetFaqMessgeState is true return!!!");
        } else {
            this.d = true;
            ThreadPoolManager.d().execute(new Runnable() { // from class: oxc
                @Override // java.lang.Runnable
                public final void run() {
                    oxd.this.e();
                }
            });
        }
    }

    /* synthetic */ void e() {
        List<MessageObject> messages = this.i.getMessages(31);
        this.d = false;
        if (messages.isEmpty() || messages.size() <= 0) {
            return;
        }
        b(messages.get(0));
    }

    public void b(MessageObject messageObject) {
        oxa oxaVar;
        if (messageObject == null || (oxaVar = this.j) == null) {
            LogUtil.a("NotificationCardInteractors", "return MessageObject is null !!!");
            return;
        }
        LogUtil.a("NotificationCardInteractors", "Enter pushMessageToDevice !!!!! mDeviceStateInteractors.getCurrentDeviceType() = ", Integer.valueOf(oxaVar.j()));
        DeviceCapability deviceCapability = this.f;
        if (deviceCapability == null || !deviceCapability.isSupportMessageCenterPushDevice()) {
            LogUtil.a("NotificationCardInteractors", "return deviceCapability notSupport pushMessageToDevice !!!!! msgObj = ", messageObject.toString());
            if (this.j.j() != 10) {
                LogUtil.a("NotificationCardInteractors", "return deviceCapability notSupport pushMessageToDevice && not Leo!!!");
                return;
            }
        }
        LogUtil.a("NotificationCardInteractors", "pushMessageToDevice MessageObject = ", messageObject.toString());
        if (this.c) {
            return;
        }
        long expireTime = messageObject.getExpireTime() / 1000;
        LogUtil.a("NotificationCardInteractors", "pushMessageToDevice !!!!! exprTime = " + expireTime);
        if (this.j.c() == 2) {
            this.c = true;
            a(30000);
            jpd jpdVar = new jpd();
            jpdVar.e(messageObject.getMsgId());
            jpdVar.d(messageObject.getMsgType());
            jpdVar.a(expireTime);
            jpdVar.c(1);
            jpdVar.c(messageObject.getMsgTitle());
            jpdVar.a(messageObject.getMsgContent());
            jpdVar.a((byte[]) null);
            final String msgId = messageObject.getMsgId();
            joh.a().c(jpdVar, new IBaseResponseCallback() { // from class: oxd.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("NotificationCardInteractors", "onResponse errCode = ", Integer.valueOf(i), "  msgID = ", msgId);
                    if (i == 0) {
                        oxd.this.i.setMessageRead(msgId);
                    }
                    oxd.this.a(5000);
                }
            });
            return;
        }
        this.c = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacks(this.n);
            this.l.postDelayed(this.n, i);
        }
    }
}
