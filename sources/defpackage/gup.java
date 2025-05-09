package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.healthcloud.plugintrack.manager.inteface.IActivityRecognitionStateListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.ITrackStrategy;
import com.huawei.healthcloud.plugintrack.manager.inteface.IUpGpsStatusListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class gup {
    private gtd c;
    private List<ITrackStrategy> b = new ArrayList(10);
    private int d = 0;

    /* renamed from: a, reason: collision with root package name */
    private IActivityRecognitionStateListener f12946a = new IActivityRecognitionStateListener() { // from class: gup.5
        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IActivityRecognitionStateListener
        public void onStateChange(int i) {
            gup.this.a(i);
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IActivityRecognitionStateListener
        public String getCurrentState() {
            if (gup.this.c != null) {
                return gup.this.c.a();
            }
            LogUtil.h("Track_StrategyManager", "getCurrentState mActivityRecognitionMotionDetector null");
            return "unkwon";
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IActivityRecognitionStateListener
        public boolean isCurrentStateIsStill() {
            if (gup.this.c != null) {
                return gup.this.c.b();
            }
            LogUtil.h("Track_StrategyManager", "isCurrentStateIsStill mActivityRecognitionMotionDetector null");
            return false;
        }
    };

    public gup(IUpGpsStatusListener iUpGpsStatusListener, int i) {
        if ((CommonUtil.aw() || CommonUtil.bf()) && iUpGpsStatusListener != null) {
            e(iUpGpsStatusListener, i);
            e();
            c();
        }
    }

    private void e() {
        for (ITrackStrategy iTrackStrategy : this.b) {
            if (iTrackStrategy != null) {
                iTrackStrategy.start();
            } else {
                LogUtil.h("Track_StrategyManager", " start iTrackStrategy is null");
            }
        }
    }

    public void a() {
        d();
    }

    public void e(int i) {
        LogUtil.a("Track_StrategyManager", "notifyUserOperateSportState is ", Integer.valueOf(i));
        this.d = i;
        if (i == 4) {
            LogUtil.a("Track_StrategyManager", " dispatchPhoneState IS mPhoneState=", Integer.valueOf(i));
            return;
        }
        List<ITrackStrategy> list = this.b;
        if (list == null) {
            LogUtil.h("Track_StrategyManager", "notifyUserOperateSportState null");
            return;
        }
        for (ITrackStrategy iTrackStrategy : list) {
            if (iTrackStrategy != null) {
                iTrackStrategy.notifyUserOperateSportState(i);
            } else {
                LogUtil.a("Track_StrategyManager", "notifyUserOperateSportState is null");
            }
        }
    }

    private void e(IUpGpsStatusListener iUpGpsStatusListener, int i) {
        if (new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002)).b()) {
            this.b.add(new gvr());
        }
        if (i != 264) {
            this.b.add(new gvt(iUpGpsStatusListener, this.f12946a));
        } else {
            gvt.e(false);
        }
        LogUtil.a("Track_StrategyManager", "initAllStrategy");
    }

    private void d() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: guo
            @Override // java.lang.Runnable
            public final void run() {
                gup.this.b();
            }
        });
        List<ITrackStrategy> list = this.b;
        if (list == null) {
            return;
        }
        for (ITrackStrategy iTrackStrategy : list) {
            if (iTrackStrategy != null) {
                iTrackStrategy.stop();
            } else {
                LogUtil.a("Track_StrategyManager", " stop iTrackStategy is null");
            }
        }
        this.b.clear();
        ReleaseLogUtil.e("Track_StrategyManager", "destoryAllStrategy");
    }

    /* synthetic */ void b() {
        gtd gtdVar = this.c;
        if (gtdVar != null) {
            gtdVar.c();
            this.c = null;
            ReleaseLogUtil.e("Track_StrategyManager", "stop mActivityRecognitionMotionDetector is success");
            return;
        }
        ReleaseLogUtil.e("Track_StrategyManager", "stop mActivityRecognitionMotionDetector is null");
    }

    private void c() {
        gtd gtdVar = new gtd(this.f12946a);
        this.c = gtdVar;
        gtdVar.d();
        ReleaseLogUtil.e("Track_StrategyManager", "initMotionDetector");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        List<ITrackStrategy> list = this.b;
        if (list == null) {
            LogUtil.h("Track_StrategyManager", "dispatchPhoneState null");
            return;
        }
        int i2 = this.d;
        if (i2 == 4) {
            LogUtil.a("Track_StrategyManager", " dispatchPhoneState IS mPhoneState=", Integer.valueOf(i2));
            return;
        }
        for (ITrackStrategy iTrackStrategy : list) {
            if (iTrackStrategy != null) {
                iTrackStrategy.dispatchPhoneCurrentState(i);
            } else {
                LogUtil.a("Track_StrategyManager", "dispatchPhoneState is null");
            }
        }
    }
}
