package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import androidx.core.util.Consumer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.CustomH5ProWebview;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.main.R$string;
import defpackage.bzs;
import defpackage.eev;
import defpackage.fdp;
import defpackage.jdl;
import defpackage.mtp;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class SleepWebviewProvider extends MinorProvider<fdp> {
    private SectionBean b;
    private boolean c = false;
    private CustomH5ProWebview d;
    private fdp e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        LogUtil.a("SleepWebviewProvider", "provider: " + this);
        if (Utils.l() || this.e == null) {
            LogUtil.a("SleepWebviewProvider", "set SleepWebviewProvider invisible");
            return false;
        }
        LogUtil.a("SleepWebviewProvider", "isActive: " + this.e.f().o());
        return this.e.f().o();
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("SleepWebviewProvider", "loadData");
        this.b = sectionBean;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        this.e = fdpVar;
        sectionBean.e(this, fdpVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepWebviewProvider", "parseParams");
        this.e = fdpVar;
        if (hashMap == null || context == null) {
            return;
        }
        hashMap.put("TITLE", context.getResources().getString(R$string.IDS_sleep_featured_sleep_talk_snoring));
        fdp fdpVar2 = this.e;
        if (fdpVar2 != null) {
            long e = jdl.e(jdl.b(fdpVar2.g(), -1), 20, 0);
            eev.a(e);
            hashMap.put("SLEEP_START_TIME", Long.valueOf(e));
            long e2 = jdl.e(this.e.g(), 20, 0);
            eev.b(e2);
            hashMap.put("SLEEP_END_TIME", Long.valueOf(e2));
        }
        hashMap.put("LEFT_TEXT", context.getString(R$string.IDS_clear_sleep_data_cache));
        hashMap.put("RIGHT_TEXT", context.getString(R$string.IDS_rq_no_record));
        hashMap.put("CALL_BACK", new Consumer<CustomH5ProWebview>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepWebviewProvider.3
            @Override // androidx.core.util.Consumer
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void accept(CustomH5ProWebview customH5ProWebview) {
                bzs.e().initH5Pro();
                LogUtil.a("SleepWebviewProvider", "start to load, JsModule: " + mtp.d().getJsModule());
                SleepWebviewProvider.this.d = customH5ProWebview;
                SleepWebviewProvider.this.c(customH5ProWebview);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(CustomH5ProWebview customH5ProWebview) {
        Object[] objArr = new Object[4];
        objArr[0] = "loadH5proMiniProgram: ";
        objArr[1] = customH5ProWebview;
        objArr[2] = ", instance: ";
        objArr[3] = customH5ProWebview != null ? customH5ProWebview.h : Constants.NULL;
        LogUtil.a("SleepWebviewProvider", objArr);
        if (this.c) {
            return;
        }
        H5ProClient.startH5MiniProgram("com.huawei.health.h5.sleepdetection", customH5ProWebview, new H5ProLaunchOption.Builder().addPath("#/featured").setForceDarkMode(1).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("sectionApi", eev.class).addCustomizeJsModule("SleepDetection", mtp.d().getJsModule()).build());
        this.c = true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        LogUtil.a("SleepWebviewProvider", "SleepWebviewProvider clear");
        CustomH5ProWebview customH5ProWebview = this.d;
        if (customH5ProWebview != null) {
            customH5ProWebview.d();
        }
        ObserverManagerUtil.e("CHART_START_END_TIME");
        ObserverManagerUtil.e("REFRESH_H5_HEIGHT");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "SleepWebviewProvider";
    }
}
