package defpackage;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sport.utils.RunPopularRoutesUtil;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteConstants;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.runningroute.view.RouteAutoRemindDialog;
import com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import defpackage.emy;
import health.compact.a.HiDateUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gze {

    /* renamed from: a, reason: collision with root package name */
    private enj f13018a;
    private final int b;
    private final Context c;
    private final MotionPathSimplify i;
    private boolean e = true;
    private boolean d = false;

    public gze(Context context, MotionPathSimplify motionPathSimplify, int i) {
        this.c = context;
        this.i = motionPathSimplify;
        this.b = i;
    }

    public void c() {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gzd
                @Override // java.lang.Runnable
                public final void run() {
                    gze.this.c();
                }
            });
            return;
        }
        if (!RunningRouteUtils.a(this.i.requestSportType()) || this.i.requestExtendDataMap().containsKey("hotPathId")) {
            LogUtil.a("Track_RouteAutoHelper", "not support auto punch sport type or manual track");
            return;
        }
        if (b()) {
            LogUtil.a("Track_RouteAutoHelper", "Auto dialog have shown");
            return;
        }
        if (!h()) {
            ReleaseLogUtil.b("Track_RouteAutoHelper", "Auto switch is update with cloud");
            j();
            return;
        }
        if (!this.d) {
            a();
        }
        if (e()) {
            LogUtil.a("Track_RouteAutoHelper", "this track not auto punch");
        } else {
            f();
        }
    }

    private void f() {
        enf a2 = this.f13018a.a();
        if (a2 == null) {
            ReleaseLogUtil.a("Track_RouteAutoHelper", "HotPathOperationInfo is null");
            return;
        }
        enm f = a2.f();
        final int b = a2.b();
        final String b2 = f != null ? f.b() : "";
        final String h = a2.h();
        final long d2 = a2.d();
        final String i = a2.i();
        HandlerExecutor.a(new Runnable() { // from class: gzc
            @Override // java.lang.Runnable
            public final void run() {
                gze.this.e(b2, h, d2, i, b);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(String str, String str2, long j, final String str3, final int i) {
        if (this.c == null) {
            ReleaseLogUtil.a("Track_RouteAutoHelper", "context is null in init dialog");
            return;
        }
        final RouteAutoRemindDialog routeAutoRemindDialog = new RouteAutoRemindDialog(this.c);
        routeAutoRemindDialog.a(str);
        routeAutoRemindDialog.c(str2);
        routeAutoRemindDialog.c(j);
        routeAutoRemindDialog.aXs_(new View.OnClickListener() { // from class: gzf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gze.this.aXg_(str3, routeAutoRemindDialog, i, view);
            }
        });
        routeAutoRemindDialog.aXr_(new View.OnClickListener() { // from class: gzg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gze.this.aXh_(routeAutoRemindDialog, view);
            }
        });
        routeAutoRemindDialog.show();
        j();
        c(0, routeAutoRemindDialog.c());
    }

    /* synthetic */ void aXg_(String str, RouteAutoRemindDialog routeAutoRemindDialog, int i, View view) {
        if (nsn.ae(this.c)) {
            LogUtil.a("Track_RouteAutoHelper", "Pad not support Popular Routes");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            b(str, routeAutoRemindDialog, i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void aXh_(RouteAutoRemindDialog routeAutoRemindDialog, View view) {
        c(1, routeAutoRemindDialog.c());
        routeAutoRemindDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(final String str, final RouteAutoRemindDialog routeAutoRemindDialog, final int i) {
        RunPopularRoutesUtil.e(this.c, 4, new RunPopularRoutesUtil.DialogCallBack() { // from class: gze.4
            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void goNext() {
                LogUtil.a("Track_RouteAutoHelper", "Start jump To ClockingRankActivity");
                Intent intent = new Intent(gze.this.c, (Class<?>) ClockingRankActivity.class);
                intent.putExtra("PATH_ID", str);
                intent.putExtra("pathClass", i);
                intent.putExtra("ENTRANCE_ACTIVITY", gze.this.d());
                gnm.aPB_(gze.this.c, intent);
                routeAutoRemindDialog.dismiss();
            }

            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void notGoNext() {
                LogUtil.a("Track_RouteAutoHelper", "Cancle start jump To ClockingRankActivity");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d() {
        if (this.b == 2) {
            return RunningRouteConstants.BiFromActivity.FROM_RECORD_DIALOG.getIndex();
        }
        return RunningRouteConstants.BiFromActivity.FROM_RECORD_LIST_DIALOG.getIndex();
    }

    private void c(int i, boolean z) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(this.b));
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("sportPage", Integer.valueOf(this.i.requestSportType()));
        hashMap.put("remindStatus", String.valueOf(z));
        ixx.d().d(BaseApplication.e(), "1040117", hashMap, 0);
    }

    private boolean e() {
        enj enjVar = this.f13018a;
        return enjVar == null || enjVar.a() == null;
    }

    private boolean b() {
        return SharedPreferenceManager.a(Integer.toString(20002), "routeAutoDialogShown", false);
    }

    private void j() {
        SharedPreferenceManager.e(Integer.toString(20002), "routeAutoDialogShown", true);
    }

    private boolean h() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        gzl.b(new a(countDownLatch));
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("Track_RouteAutoHelper", "interrupted while waiting for requestData");
        }
        return this.e;
    }

    public enj a() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        emy.d dVar = new emy.d();
        dVar.e(this.i.requestEndTime()).c(this.i.requestStartTime()).b(HiDateUtil.d((String) null));
        RunningRouteUtils.a(this.c).e(dVar.e(), new d(countDownLatch));
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("Track_RouteAutoHelper", "interrupted while waiting for requestData");
        }
        this.d = true;
        return this.f13018a;
    }

    static class a implements IBaseResponseCallback {
        private final WeakReference<gze> c;
        private final CountDownLatch d;

        private a(gze gzeVar, CountDownLatch countDownLatch) {
            this.c = new WeakReference<>(gzeVar);
            this.d = countDownLatch;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Track_RouteAutoHelper", "errorCode is ", Integer.valueOf(i), "objData is ", obj);
            gze gzeVar = this.c.get();
            if (gzeVar == null) {
                ReleaseLogUtil.c("Track_RouteAutoHelper", "RouteAutoHelper is null in InnerIBaseResponseCallback");
                this.d.countDown();
            } else {
                if (!(obj instanceof enh)) {
                    this.d.countDown();
                    return;
                }
                enh enhVar = (enh) obj;
                if (enhVar.c() != null && enhVar.a() != null) {
                    gzeVar.e = false;
                }
                this.d.countDown();
            }
        }
    }

    static class d implements ResultCallback<emw> {
        private final WeakReference<gze> d;
        private final CountDownLatch e;

        private d(gze gzeVar, CountDownLatch countDownLatch) {
            this.d = new WeakReference<>(gzeVar);
            this.e = countDownLatch;
        }

        @Override // com.huawei.networkclient.ResultCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(emw emwVar) {
            LogUtil.a("Track_RouteAutoHelper", "GetHotPathParticipateInfoRsp", emwVar);
            gze gzeVar = this.d.get();
            if (gzeVar != null) {
                gzeVar.f13018a = emwVar.d();
                this.e.countDown();
            } else {
                ReleaseLogUtil.c("Track_RouteAutoHelper", "RouteAutoHelper is null");
                this.e.countDown();
            }
        }

        @Override // com.huawei.networkclient.ResultCallback
        public void onFailure(Throwable th) {
            ReleaseLogUtil.c("Track_RouteAutoHelper", "GetHotPathParticipateInfoRsp onFailure");
            this.e.countDown();
        }
    }
}
