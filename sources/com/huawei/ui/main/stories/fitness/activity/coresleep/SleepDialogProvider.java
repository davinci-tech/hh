package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.view.View;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import defpackage.bzs;
import defpackage.efb;
import defpackage.fdp;
import defpackage.mtp;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class SleepDialogProvider extends MinorProvider<fdp> {
    private Context b;
    private boolean e = true;
    private boolean d = false;
    private int c = 0;

    /* renamed from: a, reason: collision with root package name */
    private OnClickSectionListener f9814a = new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepDialogProvider.4
        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
            if ("LEFT_BUTTON_TEXT".equals(str)) {
                if (SleepDialogProvider.this.d) {
                    SharedPreferenceManager.e(SleepDialogProvider.this.b, Integer.toString(10000), "CANCEL_3", "1", (StorageParams) null);
                    return;
                }
                if (SleepDialogProvider.this.c == 1) {
                    SharedPreferenceManager.e(SleepDialogProvider.this.b, Integer.toString(10000), "CANCEL_1", "1", (StorageParams) null);
                    return;
                } else if (SleepDialogProvider.this.c == 2) {
                    SharedPreferenceManager.e(SleepDialogProvider.this.b, Integer.toString(10000), "CANCEL_2", "1", (StorageParams) null);
                    return;
                } else if (SleepDialogProvider.this.c == 3) {
                    SharedPreferenceManager.e(SleepDialogProvider.this.b, Integer.toString(10000), "CANCEL_3", "1", (StorageParams) null);
                    return;
                }
            }
            if ("RIGHT_BUTTON_TEXT".equals(str)) {
                SleepDialogProvider.this.d();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        bzs.e().loadH5ProApp(this.b, "com.huawei.health.h5.sleepdetection", new H5ProLaunchOption.Builder().addPath("#/SleepSetting").addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("SleepDetection", mtp.d().getJsModule()).setForceDarkMode(1).setImmerse().showStatusBar().setStatusBarTextBlack(true));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        int c = c(BaseApplication.getContext());
        this.c = c;
        if (c == 0) {
            this.e = false;
            sectionBean.e(this, fdpVar);
        } else if (!efb.b(BaseApplication.getContext())) {
            this.e = false;
            sectionBean.e(this, fdpVar);
        } else if (fdpVar.i() != SleepViewConstants.SleepTypeEnum.PHONE) {
            this.e = false;
            sectionBean.e(this, fdpVar);
        } else {
            this.e = true;
            sectionBean.e(this, fdpVar);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        this.b = context;
        LogUtil.a("SleepDialogProvider", "parseParams");
        if (fdpVar == null) {
            return;
        }
        hashMap.put("LEFT_BUTTON_TEXT", context.getResources().getString(R$string.IDS_main_btn_state_ignore));
        hashMap.put("RIGHT_BUTTON_TEXT", context.getResources().getString(R$string.IDS_sleep_input_text21));
        int i = this.c;
        if (i == 1) {
            hashMap.put("REPORT_TEXT", context.getResources().getString(R$string.IDS_sleep_input_text18));
        } else if (i == 2) {
            hashMap.put("REPORT_TEXT", context.getResources().getString(R$string.IDS_sleep_input_text20));
        } else if (i == 3) {
            hashMap.put("REPORT_TEXT", context.getResources().getString(R$string.IDS_sleep_input_text19));
        } else {
            LogUtil.a("SleepDialogProvider", "no type");
        }
        hashMap.put("CLICK_EVENT_LISTENER", this.f9814a);
    }

    private int c(Context context) {
        try {
            JSONObject jSONObject = new JSONObject(SharedPreferenceManager.b(context, Integer.toString(10000), "SLEEP_RECORD_ALARM_INFO"));
            boolean optBoolean = jSONObject.optBoolean("enabled");
            boolean optBoolean2 = jSONObject.optBoolean("enableRecord");
            boolean optBoolean3 = jSONObject.optBoolean("isSleepRemind");
            LogUtil.a("SleepDialogProvider", "plan: ", Boolean.valueOf(optBoolean), ", record: ", Boolean.valueOf(optBoolean2), ", notice: ", Boolean.valueOf(optBoolean3));
            if (!optBoolean && !"1".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "CANCEL_1"))) {
                return 1;
            }
            if (!optBoolean2 && !"1".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "CANCEL_2"))) {
                return 2;
            }
            if (optBoolean3 || "1".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "CANCEL_3"))) {
                return 0;
            }
            if (optBoolean) {
                return 3;
            }
            this.d = true;
            return 1;
        } catch (JSONException unused) {
            LogUtil.b("SleepDialogProvider", "JSONException");
            return 0;
        }
    }
}
