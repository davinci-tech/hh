package com.huawei.ui.homehealth.threecirclecard.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.manager.DaemonService;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.threecirclecard.model.StepsViewModel;
import defpackage.dss;
import defpackage.gnj;
import defpackage.pit;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class StepsViewModel extends ViewModel {

    /* renamed from: a, reason: collision with root package name */
    private Context f9630a;
    private e d;
    private MutableLiveData<HashMap<String, Integer>> e;
    private volatile IExecuteResult j;
    private MutableLiveData<Bundle> n;
    private volatile a o;
    private volatile boolean i = false;
    private HealthOpenSDK b = null;
    private Handler c = new b(this);
    private int h = 0;
    private boolean f = false;
    private volatile boolean g = false;

    public static class b extends BaseHandler<StepsViewModel> {
        public b(StepsViewModel stepsViewModel) {
            super(stepsViewModel);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: diN_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(StepsViewModel stepsViewModel, Message message) {
            if (message == null) {
                ReleaseLogUtil.d("SCUI_StepsViewModel", "msg == null");
                return;
            }
            int i = message.what;
            if (i == 2) {
                stepsViewModel.c();
                return;
            }
            if (i != 3) {
                if (i == 17 && !stepsViewModel.i) {
                    ReleaseLogUtil.d("SCUI_StepsViewModel", "opensdk not reg success, retry! ");
                    stepsViewModel.c();
                    return;
                }
                return;
            }
            stepsViewModel.m();
        }
    }

    public void e(HealthOpenSDK healthOpenSDK) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f9630a == null) {
            this.f9630a = BaseApplication.getContext();
        }
        this.b = healthOpenSDK;
        g();
        e();
        LogUtil.a("SCUI_StepsViewModel", "initData finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void e(LifecycleOwner lifecycleOwner, Observer<Bundle> observer) {
        if (lifecycleOwner == null || observer == null) {
            LogUtil.h("SCUI_StepsViewModel", "observeStepsData() is null");
            return;
        }
        if (this.n == null) {
            this.n = new MutableLiveData<>();
        }
        this.n.observe(lifecycleOwner, observer);
    }

    public void d(LifecycleOwner lifecycleOwner, Observer<HashMap<String, Integer>> observer) {
        if (lifecycleOwner == null || observer == null) {
            LogUtil.h("SCUI_StepsViewModel", "observeGoalData() is null");
            return;
        }
        if (this.e == null) {
            this.e = new MutableLiveData<>();
        }
        this.e.observe(lifecycleOwner, observer);
    }

    public void a(Observer<Bundle> observer) {
        if (observer == null) {
            LogUtil.h("SCUI_StepsViewModel", "removeStepsDataObserver() is null");
        } else if (this.n != null) {
            LogUtil.a("SCUI_StepsViewModel", "removeStepsDataObserver");
            this.n.removeObserver(observer);
        }
    }

    public void c(Observer<HashMap<String, Integer>> observer) {
        if (observer == null) {
            LogUtil.h("SCUI_StepsViewModel", "removeGoalDataObserver() is null");
        } else if (this.e != null) {
            LogUtil.a("SCUI_StepsViewModel", "removeGoalDataObserver");
            this.e.removeObserver(observer);
        }
    }

    private void g() {
        if (this.b == null) {
            ReleaseLogUtil.d("SCUI_StepsViewModel", "mHealthOpenSdk null");
            return;
        }
        if (this.o != null) {
            ReleaseLogUtil.d("SCUI_StepsViewModel", "init but mStepCallback not null");
            return;
        }
        this.o = new a(this);
        this.j = new c();
        dss.c(this.f9630a).c(this.j);
        ThreadPoolManager.d().execute(new Runnable() { // from class: ovz
            @Override // java.lang.Runnable
            public final void run() {
                StepsViewModel.this.i();
            }
        });
        Handler handler = this.c;
        if (handler == null || handler.hasMessages(17)) {
            return;
        }
        this.c.sendEmptyMessageDelayed(17, 3000L);
    }

    public /* synthetic */ void i() {
        this.b.b(this.o, this.j);
    }

    public void e() {
        if (this.d == null) {
            this.d = new e();
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: owd
            @Override // java.lang.Runnable
            public final void run() {
                StepsViewModel.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        pit.a().c(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        try {
            this.f9630a.startService(new Intent(this.f9630a, (Class<?>) DaemonService.class));
        } catch (Exception e2) {
            LogUtil.b("SCUI_StepsViewModel", "MSG_REBIND_OPENSDK", LogAnonymous.b((Throwable) e2));
        }
        HealthOpenSDK healthOpenSDK = this.b;
        if (healthOpenSDK != null) {
            healthOpenSDK.b(this.o, this.j);
        }
    }

    protected void c() {
        if (this.b == null || this.o == null || this.j == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: ovy
            @Override // java.lang.Runnable
            public final void run() {
                StepsViewModel.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        this.b.b(this.o, this.j);
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        LogUtil.a("SCUI_StepsViewModel", "destroy StepsCard");
        Handler handler = this.c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: owa
            @Override // java.lang.Runnable
            public final void run() {
                StepsViewModel.this.f();
            }
        });
        if (this.j != null) {
            dss.c(this.f9630a).e(this.j);
        }
    }

    public /* synthetic */ void f() {
        if (this.b == null) {
            ReleaseLogUtil.d("SCUI_StepsViewModel", "onDestroy mHealthOpenSdk null");
        } else if (this.o == null) {
            ReleaseLogUtil.d("SCUI_StepsViewModel", "onDestroy mStepCallback null!!!why?");
        } else {
            LogUtil.a("SCUI_StepsViewModel", "unRegister mStepCallback:", this.o);
            this.b.d((ICommonReport) this.o);
        }
    }

    class c implements IExecuteResult {
        private c() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            ReleaseLogUtil.e("SCUI_StepsViewModel", "RegisterResult onSuccess");
            StepsViewModel.this.i = true;
            if (StepsViewModel.this.f) {
                gnj.j();
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            ReleaseLogUtil.d("SCUI_StepsViewModel", "RegisterResult onFailed");
            StepsViewModel.this.l();
            if (StepsViewModel.this.f) {
                gnj.b();
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            ReleaseLogUtil.d("SCUI_StepsViewModel", "RegisterResult onServiceException");
            StepsViewModel.this.c.sendMessage(StepsViewModel.this.c.obtainMessage(3));
            if (StepsViewModel.this.f) {
                gnj.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        Handler handler = this.c;
        if (handler == null) {
            LogUtil.h("SCUI_StepsViewModel", "retryRegister mHandler == null");
            return;
        }
        if (handler.hasMessages(2)) {
            return;
        }
        int i = this.h + 1;
        this.h = i;
        ReleaseLogUtil.e("SCUI_StepsViewModel", "retryRegister mRegisterTimes=", Integer.valueOf(i));
        if (this.h <= 10) {
            this.c.sendEmptyMessageDelayed(2, r0 * 200);
        } else {
            h();
        }
    }

    private void h() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: owh
            @Override // java.lang.Runnable
            public final void run() {
                StepsViewModel.this.j();
            }
        });
    }

    public /* synthetic */ void j() {
        if (this.f) {
            ReleaseLogUtil.e("SCUI_StepsViewModel", "initSDK mIsReInitStepSdk true");
            return;
        }
        gnj.f();
        ReleaseLogUtil.e("SCUI_StepsViewModel", "retryInitStepSDK initSDK start");
        this.f = true;
        HealthOpenSDK healthOpenSDK = this.b;
        if (healthOpenSDK == null) {
            ReleaseLogUtil.d("SCUI_StepsViewModel", "retryInitStepSDK initSDK start");
        } else {
            healthOpenSDK.destorySDK();
            this.b.initSDK(this.f9630a, new IExecuteResult() { // from class: com.huawei.ui.homehealth.threecirclecard.model.StepsViewModel.2
                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onSuccess(Object obj) {
                    ReleaseLogUtil.e("SCUI_StepsViewModel", "retryInitStepSDK initSDK onSuccess");
                    StepsViewModel.this.c();
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onFailed(Object obj) {
                    ReleaseLogUtil.e("SCUI_StepsViewModel", "retryInitStepSDK initSDK onFailed");
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onServiceException(Object obj) {
                    ReleaseLogUtil.c("SCUI_StepsViewModel", "retryInitStepSDK initSDK onServiceException");
                }
            }, "HuaweiHealth");
        }
    }

    static class e implements IBaseResponseCallback {
        private WeakReference<StepsViewModel> c;

        private e(StepsViewModel stepsViewModel) {
            this.c = new WeakReference<>(stepsViewModel);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[2];
            objArr[0] = "SportAchievementGoalDataCallback ";
            objArr[1] = obj == null ? null : obj.toString();
            LogUtil.a("SCUI_StepsViewModel", objArr);
            StepsViewModel stepsViewModel = this.c.get();
            if (stepsViewModel == null) {
                ReleaseLogUtil.d("SCUI_StepsViewModel", "SportAchievementGoalDataCallback cardData is null ");
            } else if (obj instanceof HashMap) {
                stepsViewModel.e.postValue((HashMap) obj);
            } else {
                LogUtil.h("SCUI_StepsViewModel", "handleMsgUpdateDialog is not instanceof HashMap");
            }
        }
    }

    static class a implements ICommonReport {
        WeakReference<StepsViewModel> c;

        protected a(StepsViewModel stepsViewModel) {
            this.c = null;
            this.c = new WeakReference<>(stepsViewModel);
        }

        @Override // com.huawei.hihealth.motion.ICommonReport
        public void report(Bundle bundle) {
            ReleaseLogUtil.e("SCUI_StepsViewModel", "StepCallback start");
            StepsViewModel stepsViewModel = this.c.get();
            if (stepsViewModel != null && bundle != null) {
                if (stepsViewModel.n != null) {
                    stepsViewModel.n.postValue(bundle);
                    return;
                }
                return;
            }
            ReleaseLogUtil.d("SCUI_StepsViewModel", "StepCallback mStepsCard is null or bundle is null");
        }
    }

    public void e(boolean z) {
        this.g = z;
    }

    public boolean b() {
        return this.g;
    }
}
