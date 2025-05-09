package com.huawei.health.monitor.proc.prompt;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.StepCounterSupport;

/* loaded from: classes.dex */
public class PromptRecord {
    private Context e;
    private boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f2918a = false;
    private boolean b = false;

    public PromptRecord(Context context) {
        this.e = null;
        if (context == null) {
            LogUtil.h("Step_PromptRecord", "PromptRecord context is null.");
            this.e = BaseApplication.getContext();
        }
        this.e = context;
    }

    public void d() {
        LogUtil.a("Step_PromptRecord", "handleBootCompleteMessage");
        this.f2918a = true;
    }

    public void c() {
        LogUtil.a("Step_PromptRecord", "handleShutDownMessage");
        this.b = true;
    }

    public void a(boolean z) {
        this.c = z;
        LogUtil.a("Step_PromptRecord", "makePromptSense:", Boolean.valueOf(z));
    }

    public void a() {
        if (!this.c) {
            LogUtil.h("Step_PromptRecord", "makePromptNoSense invoke,but find itself has no sense,warnning!!!");
        } else {
            this.c = false;
            LogUtil.a("Step_PromptRecord", "makePromptNoSense,success");
        }
    }

    public boolean b() {
        if (!StepCounterSupport.h(this.e)) {
            LogUtil.h("Step_PromptRecord", "not support step counter,isPromptHasSense return false");
            return false;
        }
        if (this.f2918a) {
            LogUtil.a("Step_PromptRecord", "Has Received BootComplete,isPromptHasSense return false,Avoid misJudgment");
            return false;
        }
        if (this.b) {
            LogUtil.a("Step_PromptRecord", "Has Received ShutDown,isPromptHasSense return false,Avoid misJudgment!!!");
            return false;
        }
        return this.c;
    }
}
