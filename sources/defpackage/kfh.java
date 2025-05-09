package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask;
import com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.ProfileUpdate;
import com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.TwsExecutorListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.client.connect.ServiceConnectCallback;

/* loaded from: classes5.dex */
public class kfh {
    private TwsExecutorListener c;
    private String i;
    private a e = new a();
    private volatile boolean b = false;
    private BaseTwsTask d = null;
    private ServiceConnectCallback f = new ServiceConnectCallback() { // from class: kfh.4
        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onConnect() {
            LogUtil.a("HwTwsExecutor", "profile connected.");
            if (kfh.this.f14340a != null) {
                kfh.this.f14340a.sendEmptyMessage(2);
            }
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onDisconnect() {
            LogUtil.a("HwTwsExecutor", "profileCallback disconnected.");
            if (kfh.this.f14340a != null) {
                kfh.this.f14340a.sendEmptyMessage(3);
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private ExtendHandler f14340a = HandlerCenter.yt_(this.e, "HwTwsExecutor");
    private ProfileUpdate j = ProfileUpdate.PROFILE_UPDATE_BY_APK;

    kfh(TwsExecutorListener twsExecutorListener) {
        this.c = twsExecutorListener;
    }

    public void a() {
        synchronized (this) {
            if (this.b) {
                LogUtil.h("HwTwsExecutor", "startExecutor but is running");
                return;
            }
            if (this.f14340a != null) {
                this.b = true;
                this.f14340a.sendEmptyMessage(1);
            }
        }
    }

    public void c() {
        this.b = false;
        this.j.disconnectProfile();
    }

    public void d(final String str, final byte[] bArr) {
        String str2 = this.i;
        if (str2 != null && str2.equals(str)) {
            LogUtil.a("HwTwsExecutor", "doWaitMessage :", str);
            ExtendHandler extendHandler = this.f14340a;
            if (extendHandler != null) {
                extendHandler.postTask(new Runnable() { // from class: kfh.3
                    @Override // java.lang.Runnable
                    public void run() {
                        kfh.this.c(str, bArr);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, byte[] bArr) {
        BaseTwsTask baseTwsTask = this.d;
        if (baseTwsTask != null && !baseTwsTask.onWaitFor(str, bArr) && f()) {
            LogUtil.a("HwTwsExecutor", "doWaitMessage find another wait massage");
            return;
        }
        this.c.onTaskFinish(this.d);
        j();
        ExtendHandler extendHandler = this.f14340a;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(1);
        }
    }

    private void j() {
        ExtendHandler extendHandler = this.f14340a;
        if (extendHandler != null) {
            extendHandler.removeMessages(4);
        }
        this.d = null;
        this.i = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("HwTwsExecutor", "dealTaskCheck entry");
        while (true) {
            if (this.d == null) {
                BaseTwsTask onGetNextTask = this.c.onGetNextTask();
                if (onGetNextTask == null) {
                    LogUtil.a("HwTwsExecutor", "dealTaskCheck find all task finish");
                    this.c.onAllTaskFinish();
                    break;
                }
                this.d = onGetNextTask;
            }
            if (!this.j.isConnected()) {
                LogUtil.a("HwTwsExecutor", "dealTaskCheck first use profile,wait for connect");
                this.j.connectProfile(this.f);
                break;
            } else {
                LogUtil.a("HwTwsExecutor", "dealTaskCheck start do task");
                if (!g()) {
                    break;
                }
            }
        }
        LogUtil.a("HwTwsExecutor", "dealTaskCheck exit");
    }

    private boolean g() {
        this.c.onTaskBegin(this.d);
        if (!this.d.onExecutor(this.j.getProfileClient()) && f()) {
            LogUtil.a("HwTwsExecutor", "doTask need wait message:", this.d.getTaskName());
            return false;
        }
        this.c.onTaskFinish(this.d);
        j();
        return true;
    }

    private boolean f() {
        String waitMessage = this.d.getWaitMessage();
        if (TextUtils.isEmpty(waitMessage)) {
            LogUtil.h("HwTwsExecutor", "sendWaitMessage ,but not find wait message,maybe error");
            return false;
        }
        this.i = waitMessage;
        if (this.f14340a != null) {
            LogUtil.a("HwTwsExecutor", "sendWaitMessage find wait message:", this.d.getWaitMessage(), ",wait time:", Long.valueOf(this.d.getWaitTimeout()));
            this.f14340a.sendEmptyMessage(4, this.d.getWaitTimeout());
            return true;
        }
        LogUtil.h("HwTwsExecutor", "sendWaitMessage not finish ,but handle is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("HwTwsExecutor", "dealTaskTimeout taskName:", this.d.getTaskName(), ",waitFor:", this.i);
        this.c.onTaskTimeout(this.d);
        this.c.onTaskFinish(this.d);
        j();
        ExtendHandler extendHandler = this.f14340a;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(1);
        }
    }

    public void d() {
        ExtendHandler extendHandler = this.f14340a;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.f14340a = null;
        }
    }

    class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                LogUtil.a("HwTwsExecutor", "dealMessage start check");
                kfh.this.e();
                return true;
            }
            if (i == 2) {
                LogUtil.a("HwTwsExecutor", "dealMessage profile connected");
                kfh.this.e();
                return true;
            }
            if (i == 3) {
                LogUtil.a("HwTwsExecutor", "dealMessage profile disconnect");
                return true;
            }
            if (i == 4) {
                kfh.this.b();
                return true;
            }
            LogUtil.h("HwTwsExecutor", "dealMessage find default:", Integer.valueOf(message.what));
            return false;
        }
    }
}
