package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gvr implements ITrackStrategy {
    private static final String e = null;

    /* renamed from: a, reason: collision with root package name */
    private boolean f12964a;
    private Handler b = new Handler(new Handler.Callback() { // from class: gvr.5
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                return false;
            }
            int i = message.what;
            if (i != 0) {
                if (i == 100) {
                    gvr.this.b.removeCallbacksAndMessages(gvr.e);
                    if (gso.e().i() == 2 && gvr.this.c) {
                        LogUtil.a("Track_AutoPauseResumeStrategy", "mMotion = TrackConstants.EXIT_STATE_STILL AND AUTO_RESUME_SPORT ", 1);
                        gvr.this.c();
                    }
                    return true;
                }
                if (i != 102) {
                    return false;
                }
            }
            gvr.this.b.removeCallbacksAndMessages(gvr.e);
            if (gso.e().i() == 1 && !gvr.this.f12964a) {
                LogUtil.a("Track_AutoPauseResumeStrategy", "mMotion =ENTER_STATE_STILL ", 0);
                gvr.this.e();
            }
            return true;
        }
    });
    private boolean c;
    private boolean d;
    private int j;

    public gvr() {
        LogUtil.a("Track_AutoPauseResumeStrategy", "AutoPauseResumeStrategy");
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy
    public void start() {
        LogUtil.a("Track_AutoPauseResumeStrategy", "start AutoPauseResumeStrategy");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("Track_AutoPauseResumeStrategy", "autoPauseSport at ", Long.valueOf(System.currentTimeMillis()));
        this.c = true;
        gso.e().v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("Track_AutoPauseResumeStrategy", "autoResumeSport at ", Long.valueOf(System.currentTimeMillis()));
        this.c = false;
        gso.e().y();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy
    public void notifyUserOperateSportState(int i) {
        if (this.b == null) {
            LogUtil.h("Track_AutoPauseResumeStrategy", "notifyUserOperateSportState handler is null");
            return;
        }
        if (i == 1) {
            this.c = false;
            this.f12964a = false;
            if (this.d) {
                LogUtil.a("Track_AutoPauseResumeStrategy", "IS isIndoorSport");
                this.b.sendMessageDelayed(this.b.obtainMessage(102), PreConnectManager.CONNECT_INTERNAL);
            }
            LogUtil.a("Track_AutoPauseResumeStrategy", "IS SPORT_STATUS_SPORTING");
            return;
        }
        if (i != 2) {
            if (i != 5) {
                return;
            }
            LogUtil.a("Track_AutoPauseResumeStrategy", "notifyUserOperateSportState is SPORT_STATUS_STOP_END");
        } else {
            if (!this.c) {
                this.f12964a = true;
                LogUtil.a("Track_AutoPauseResumeStrategy", "IS mIsPauseByUser");
            } else {
                this.f12964a = false;
                LogUtil.a("Track_AutoPauseResumeStrategy", "IS mIsPauseByAuto");
            }
            this.b.removeCallbacksAndMessages(e);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy
    public void dispatchPhoneCurrentState(int i) {
        Handler handler = this.b;
        if (handler == null) {
            LogUtil.h("Track_AutoPauseResumeStrategy", "dispatchPhoneCurrentState handler is null");
            return;
        }
        if (i == 0) {
            this.j = 0;
            if (handler.hasMessages(0)) {
                return;
            }
            this.b.sendMessageDelayed(this.b.obtainMessage(this.j), PreConnectManager.CONNECT_INTERNAL);
            this.d = true;
            LogUtil.a("Track_AutoPauseResumeStrategy", "getPhoneCurrentState is ENTER_STATE_STILL");
            return;
        }
        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            this.d = false;
            this.j = 100;
            if (handler.hasMessages(100)) {
                return;
            }
            this.b.sendMessage(this.b.obtainMessage(this.j));
            LogUtil.a("Track_AutoPauseResumeStrategy", "getPhoneCurrentState is AUTO_RESUME_SPORT");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy
    public void stop() {
        this.b.removeCallbacksAndMessages(e);
        this.b = null;
    }
}
