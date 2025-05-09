package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InputBloodPressureActivity;
import com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureButtonProvider;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import defpackage.gnm;
import defpackage.qqb;
import defpackage.qqe;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class BloodPressureButtonProvider extends BaseKnitDataProvider {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void c(Context context, SectionBean sectionBean) {
        LogUtil.a("BloodPressureButtonProvider", "loadData");
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("BloodPressureButtonProvider", "loadDefaultData");
        super.loadDefaultData(sectionBean);
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        hashMap.put("LEFT_BUTTON_TEXT", context.getString(R$string.IDS_blood_pressure_manual_input));
        hashMap.put("RIGHT_BUTTON_TEXT", context.getString(R$string.IDS_blood_pressure_measure));
        hashMap.put("LEFT_BUTTON_IMAGE", Integer.valueOf(R.drawable._2131430815_res_0x7f0b0d9f));
        hashMap.put("RIGHT_BUTTON_IMAGE", Integer.valueOf(R.drawable._2131427623_res_0x7f0b0127));
        if (BloodPressureUtil.e()) {
            hashMap.put("RIGHT_BUTTON_VISIBILITY", 8);
        }
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureButtonProvider.3
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (i == 1) {
                    BloodPressureButtonProvider.this.e(i);
                    qqb.b(1);
                } else if (i == 2) {
                    BloodPressureButtonProvider.this.e(i);
                    qqb.b(2);
                } else {
                    LogUtil.a("BloodPressureButtonProvider", "onClick, position is: " + i);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i) {
        LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: qho
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                BloodPressureButtonProvider.this.d(i, i2, obj);
            }
        }, "");
    }

    public /* synthetic */ void d(int i, int i2, Object obj) {
        if (i2 != 0) {
            LogUtil.a("BloodPressureButtonProvider", "browsingToLogin errorCode is not success", Integer.valueOf(i2));
        } else if (i == 1) {
            e();
        } else {
            qqe.a();
        }
    }

    private void e() {
        LogUtil.a("BloodPressureButtonProvider", "onClick, startInput");
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) InputBloodPressureActivity.class);
        intent.putExtra("isShowInput", true);
        gnm.aPB_(BaseApplication.e(), intent);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "BloodPressureButtonProvider";
    }
}
