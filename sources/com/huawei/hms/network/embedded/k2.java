package com.huawei.hms.network.embedded;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.d1;
import com.huawei.hms.network.httpclient.excutor.PolicyExecutor;
import org.chromium.net.CronetEngine;

/* loaded from: classes9.dex */
public class k2 implements d1.a {
    public static final String d = "CronetRequestTaskFactory";

    /* renamed from: a, reason: collision with root package name */
    public CronetEngine f5341a;
    public boolean b;
    public PolicyExecutor c;

    @Override // com.huawei.hms.network.embedded.d1.a
    public d1 newTask() {
        return new j2(this.f5341a, this);
    }

    public boolean isEnableConnectionMigrated() {
        return this.b;
    }

    @Override // com.huawei.hms.network.embedded.d1.a
    public boolean isAvailable() {
        return this.f5341a != null;
    }

    public PolicyExecutor getPolicyExecutor() {
        return this.c;
    }

    @Override // com.huawei.hms.network.embedded.d1.a
    public String getChannel() {
        return "type_cronet";
    }

    public k2(Context context, PolicyExecutor policyExecutor) {
        this.b = false;
        if (context == null) {
            Logger.w(d, "the context is null,and the CronetRequestTaskFactory cann't been Initialized!");
            return;
        }
        this.c = policyExecutor;
        c2 cronetEngineFeature = d2.getInstance().getCronetEngineFeature(context, policyExecutor);
        if (cronetEngineFeature != null) {
            this.f5341a = cronetEngineFeature.getCronetEngine();
            this.b = cronetEngineFeature.isEnableConnectionMigrated();
        }
    }
}
