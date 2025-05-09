package com.huawei.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.exercise.modle.ITreadmillStyleCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.motion.RealStepCallback;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.treadmill.CallBackToReportStepsOrEvent;
import com.huawei.up.model.UserInfomation;
import defpackage.njl;
import defpackage.sqr;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;

/* loaded from: classes7.dex */
public class TreadmillManager {
    private njl c;
    private ExtendHandler d;
    private int g;
    private sqr i;
    private RealStepCallback e = null;
    private ITreadmillStyleCallback f = null;
    private int j = 3;
    private int b = 1000;

    /* renamed from: a, reason: collision with root package name */
    private CallBackToReportStepsOrEvent f10878a = new CallBackToReportStepsOrEvent() { // from class: com.huawei.utils.TreadmillManager.4
        @Override // com.huawei.treadmill.CallBackToReportStepsOrEvent
        public void onUpdateStepsOrEvent(int i, int i2) {
            PluginHealthTrackAdapterImpl.getInstance(BaseApplication.e()).updateStepPoint(i, System.currentTimeMillis());
            if (!TreadmillManager.this.c(i2) || TreadmillManager.this.d == null) {
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1001;
            obtain.arg1 = i2;
            TreadmillManager.this.d.sendMessage(obtain);
        }
    };

    public TreadmillManager(Context context) {
        this.g = 1;
        LogUtil.a("Track_TreadmillManager", " new TreadmillManager");
        if (this.i == null) {
            sqr d = sqr.d(context);
            this.i = d;
            if (d != null) {
                this.c = new njl();
                a();
                boolean c = this.i.c(this.c, this.f10878a, 1000);
                if (c) {
                    LogUtil.a("Track_TreadmillManager", " TreadmillManager result of refreshWorkoutParameters:", Boolean.valueOf(this.i.e(30000, 0, 0)));
                }
                LogUtil.a("Track_TreadmillManager", " TreadmillManager result of initAndStartVibraStepCount:", Boolean.valueOf(c));
            } else {
                LogUtil.h("Track_TreadmillManager", "getInstance of VibraStepCounterHelper failed");
            }
        }
        this.g = 1;
    }

    public void c() {
        LogUtil.a("Track_TreadmillManager", " stopTreadmillStep");
        sqr sqrVar = this.i;
        if (sqrVar != null) {
            sqrVar.b();
            this.i = null;
        }
    }

    public boolean a(IExecuteResult iExecuteResult) {
        LogUtil.a("Track_TreadmillManager", "getStandSteps callback:", iExecuteResult);
        sqr sqrVar = this.i;
        if (sqrVar == null) {
            LogUtil.h("Track_TreadmillManager", "reportCallback mVibraStepCounterHelper is null");
            return false;
        }
        if (iExecuteResult != null) {
            try {
                LogUtil.a("Track_TreadmillManager", "getStandSteps ", Integer.valueOf(sqrVar.c()));
                Bundle bundle = new Bundle();
                bundle.putInt("standSteps", this.i.c());
                iExecuteResult.onSuccess(bundle);
            } catch (Exception e) {
                LogUtil.h("Track_TreadmillManager", "getStandSteps ", LogAnonymous.b((Throwable) e));
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        sqr sqrVar = this.i;
        if (sqrVar == null) {
            LogUtil.h("Track_TreadmillManager", "reportCallback mVibraStepCounterHelper is null");
            return;
        }
        if (this.e != null) {
            try {
                this.e.onReport(sqrVar.c(), this.j);
            } catch (Exception e) {
                LogUtil.b("Track_TreadmillManager", "Exception", LogAnonymous.b((Throwable) e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        ITreadmillStyleCallback iTreadmillStyleCallback = this.f;
        if (iTreadmillStyleCallback != null) {
            try {
                iTreadmillStyleCallback.onTreadmillStyleChange(i, System.currentTimeMillis());
                return;
            } catch (Exception e) {
                LogUtil.b("Track_TreadmillManager", "Exception", LogAnonymous.b((Throwable) e));
                return;
            }
        }
        LogUtil.b("Track_TreadmillManager", "reportToTreadmillStyleCallback mTreadmillStyleCallback is null");
    }

    public boolean e(ITreadmillStyleCallback iTreadmillStyleCallback) {
        LogUtil.a("Track_TreadmillManager", "registerFreeIndoorRunningStyle");
        if (iTreadmillStyleCallback == null) {
            LogUtil.b("Track_TreadmillManager", "registerFreeIndoorRunningStyle callback is null");
            return false;
        }
        this.f = iTreadmillStyleCallback;
        return true;
    }

    public boolean e() {
        LogUtil.a("Track_TreadmillManager", "unregisterFreeIndoorRunningStyle");
        this.f = null;
        return true;
    }

    public boolean c(RealStepCallback realStepCallback, int i) {
        if (realStepCallback == null || i < 1000) {
            return false;
        }
        this.e = realStepCallback;
        this.b = i;
        this.d = HandlerCenter.yt_(new Handler.Callback() { // from class: com.huawei.utils.TreadmillManager.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1000) {
                    TreadmillManager.this.d();
                    if (TreadmillManager.this.d == null) {
                        return true;
                    }
                    TreadmillManager.this.d.sendEmptyMessage(1000, TreadmillManager.this.b);
                    return true;
                }
                if (i2 != 1001) {
                    return false;
                }
                int i3 = message.arg1;
                LogUtil.a("Track_TreadmillManager", "MSG_REPORT_TREADMILL_STYLE treadmill newStyle is ", Integer.valueOf(i3));
                TreadmillManager.this.e(i3);
                return true;
            }
        }, "flush_worker_thread");
        Message obtain = Message.obtain();
        obtain.what = 1000;
        obtain.obj = "refresh";
        this.d.sendMessage(obtain);
        return true;
    }

    public boolean b() {
        this.e = null;
        ExtendHandler extendHandler = this.d;
        if (extendHandler == null) {
            return true;
        }
        extendHandler.removeMessages(1000);
        this.d.quit(false);
        this.d = null;
        return true;
    }

    private void a() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        int i = 1;
        if (userInfo != null && userInfo.getGenderOrDefaultValue() == 1) {
            i = 0;
        }
        int round = (userInfo == null || userInfo.getWeightOrDefaultValue() <= 0.0f) ? 60 : Math.round(userInfo.getWeightOrDefaultValue());
        int heightOrDefaultValue = (userInfo == null || userInfo.getHeightOrDefaultValue() <= 0) ? 170 : userInfo.getHeightOrDefaultValue();
        njl njlVar = this.c;
        if (njlVar != null) {
            njlVar.d(i, round, heightOrDefaultValue);
            this.c.e(new int[]{20480, 110, 720, 5, 20});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(int i) {
        if ((i != 2 && i != 1) || i == this.g) {
            return false;
        }
        this.g = i;
        return true;
    }
}
