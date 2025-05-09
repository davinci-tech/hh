package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import defpackage.gge;
import defpackage.mtp;
import defpackage.nkx;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class SmartWearableSleepProvider extends BaseKnitDataProvider {
    private Context b;
    private SectionBean e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void c(Context context, SectionBean sectionBean) {
        this.e = sectionBean;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        LogUtil.a("SmartWearableSleepProvider", "SharedPreference");
        if (Utils.o()) {
            LogUtil.a("SmartWearableSleepProvider", "isOversea");
            return false;
        }
        this.b = context;
        return !"true".equals(SharedPreferenceManager.b(context, String.valueOf(PrebakedEffectId.RT_COIN_DROP), "IS_FIRST_ENTRY_SLEEP"));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("SmartWearableSleepProvider", "parseParams");
        if (hashMap == null || context == null) {
            return;
        }
        this.b = context;
        hashMap.put("ICON_CANCEL", ContextCompat.getDrawable(context, R.drawable._2131430276_res_0x7f0b0b84));
        hashMap.put("CARD_TITLE", context.getResources().getString(R$string.IDS_sleep_device_card_title));
        hashMap.put("CARD_DESCRIPTION", context.getResources().getString(R$string.IDS_sleep_device_card_description));
        hashMap.put("CARD_LEARN_MORE", context.getResources().getString(R$string.IDS_hw_pressure_learn_more));
        hashMap.put("LEARN_MORE_COLOR", Integer.valueOf(R.color._2131299098_res_0x7f090b1a));
        hashMap.put("CLOSE_BUTTON_SP_KEY", "IS_FIRST_ENTRY_SLEEP");
        OnClickSectionListener onClickSectionListener = new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SmartWearableSleepProvider.2
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
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SmartWearableSleepProvider", "click");
                HashMap hashMap2 = new HashMap(16);
                hashMap2.put("click", "1");
                hashMap2.put("hasdate", "true");
                gge.e(AnalyticsValue.MANUALLY_MONITOR_SLEEP_21300044.value(), hashMap2);
                if (view.getId() == R.id.section_root_view) {
                    mtp.d().e(SmartWearableSleepProvider.this.b, "0");
                }
                if (view.getId() == R.id.sleep_device_card_learn_more) {
                    LogUtil.a("SmartWearableSleepProvider", "sleep no data device card is clicked!");
                    String url = GRSManager.a(SmartWearableSleepProvider.this.b).getUrl("messageCenterUrl");
                    if (TextUtils.isEmpty(url)) {
                        LogUtil.h("SmartWearableSleepProvider", "onClick messageCenter is empty");
                        url = "https:/";
                    }
                    String str = url + "/messageH5/sleephtml/sleepDateGuide.html";
                    if (CommonUtil.cc()) {
                        str = url + "/recommendH5/sleephtml/sleepDateGuide.html";
                    }
                    SmartWearableSleepProvider.this.c(str);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        Context context2 = this.b;
        if (context2 instanceof BaseActivity) {
            hashMap.put("CLICK_EVENT_LISTENER", nkx.cwZ_(onClickSectionListener, (BaseActivity) context2, true, ""));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        Intent intent = new Intent(this.b, (Class<?>) WebViewActivity.class);
        intent.putExtra("EXTRA_BI_SOURCE", "SLEEPDETAIL");
        intent.putExtra("EXTRA_BI_NAME", "sleep_service");
        intent.putExtra("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
        intent.putExtra("url", str);
        intent.putExtra(Constants.IS_GUIDE, true);
        intent.setFlags(268435456);
        try {
            this.b.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("SmartWearableSleepProvider", "ActivityNotFound" + e.getMessage());
        }
    }
}
