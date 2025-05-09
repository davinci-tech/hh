package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.healthcloud.plugintrack.manager.inteface.IActivityRecognitionStateListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy;
import com.huawei.healthcloud.plugintrack.manager.inteface.IUpGpsStatusListener;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gvt implements ITrackStrategy {
    private static final String b = null;
    private static boolean d = false;

    /* renamed from: a, reason: collision with root package name */
    private IActivityRecognitionStateListener f12966a;
    private IUpGpsStatusListener c;
    private int i = 0;
    private boolean g = false;
    private boolean h = false;
    private Handler e = new Handler(new Handler.Callback() { // from class: gvt.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (gvt.this.g) {
                return true;
            }
            if (message == null) {
                return false;
            }
            int i = message.what;
            if (i != 0) {
                if (i == 100) {
                    gvt.this.e.removeCallbacksAndMessages(gvt.b);
                    LogUtil.a("Track_PowerSaveStrategy", "handleMessage is registerGps");
                    gvt.this.d();
                    return true;
                }
                if (i != 102) {
                    return false;
                }
            }
            gvt.this.e.removeCallbacksAndMessages(gvt.b);
            gvt.this.b();
            LogUtil.a("Track_PowerSaveStrategy", "handleMessage is ENTER_STATE_STILL");
            return true;
        }
    });

    public gvt(IUpGpsStatusListener iUpGpsStatusListener, IActivityRecognitionStateListener iActivityRecognitionStateListener) {
        this.c = iUpGpsStatusListener;
        this.f12966a = iActivityRecognitionStateListener;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy
    public void start() {
        LogUtil.a("Track_PowerSaveStrategy", "start");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        IUpGpsStatusListener iUpGpsStatusListener = this.c;
        if (iUpGpsStatusListener != null) {
            iUpGpsStatusListener.registerGps();
            d = false;
            LogUtil.a("Track_PowerSaveStrategy", "registerGps");
            return;
        }
        LogUtil.h("Track_PowerSaveStrategy", "registerGps GpsControler is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        IActivityRecognitionStateListener iActivityRecognitionStateListener = this.f12966a;
        if (iActivityRecognitionStateListener != null) {
            String currentState = iActivityRecognitionStateListener.getCurrentState();
            if (this.f12966a.isCurrentStateIsStill()) {
                IUpGpsStatusListener iUpGpsStatusListener = this.c;
                if (iUpGpsStatusListener != null) {
                    iUpGpsStatusListener.unregisterGps();
                    d = true;
                    LogUtil.a("Track_PowerSaveStrategy", "unRegisterGps", "and CurState is still");
                    return;
                }
                LogUtil.h("Track_PowerSaveStrategy", "unRegisterGps GpsController is null");
                return;
            }
            LogUtil.a("Track_PowerSaveStrategy", " CurState is not still", " and CurState is", currentState);
            return;
        }
        LogUtil.h("Track_PowerSaveStrategy", "unRegisterGps mActivityRecognitionStateListener is null");
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy
    public void stop() {
        this.e.removeCallbacksAndMessages(b);
        this.c = null;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy
    public void notifyUserOperateSportState(int i) {
        if (this.e == null) {
            LogUtil.h("Track_PowerSaveStrategy", "notifyUserOperateSportState mHandler is null");
            return;
        }
        if (i != 1) {
            if (i != 2) {
                return;
            }
            LogUtil.a("Track_PowerSaveStrategy", "mIsPause");
            this.e.removeCallbacksAndMessages(b);
            this.g = true;
            return;
        }
        LogUtil.a("Track_PowerSaveStrategy", "is sporting");
        if (this.h) {
            this.e.sendMessageDelayed(this.e.obtainMessage(102), 120000L);
        }
        this.g = false;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy
    public void dispatchPhoneCurrentState(int i) {
        Handler handler = this.e;
        if (handler == null) {
            LogUtil.h("Track_PowerSaveStrategy", "dispatchPhoneCurrentState handler null");
            return;
        }
        if (i == 0) {
            this.i = 0;
            if (handler.hasMessages(0)) {
                return;
            }
            this.e.sendMessageDelayed(this.e.obtainMessage(this.i), 120000L);
            this.h = true;
            LogUtil.a("Track_PowerSaveStrategy", "getPhoneCurrentState is ENTER_STATE_STILL");
            return;
        }
        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            this.i = 100;
            if (handler.hasMessages(100)) {
                return;
            }
            if (!this.e.sendMessage(this.e.obtainMessage(this.i))) {
                LogUtil.a("Track_PowerSaveStrategy", "send failed");
                this.e.sendMessage(this.e.obtainMessage(this.i));
            }
            this.h = false;
            LogUtil.a("Track_PowerSaveStrategy", "getPhoneCurrentState not still");
        }
    }

    public static boolean e() {
        return d;
    }

    public static void e(boolean z) {
        d = z;
    }
}
