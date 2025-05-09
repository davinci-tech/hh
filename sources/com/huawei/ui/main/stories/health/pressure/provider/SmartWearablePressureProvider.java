package com.huawei.ui.main.stories.health.pressure.provider;

import android.content.Context;
import android.content.Intent;
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
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import defpackage.gge;
import defpackage.gnm;
import defpackage.nkx;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class SmartWearablePressureProvider extends BaseKnitDataProvider {
    private SectionBean b;
    private Context e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void c(Context context, SectionBean sectionBean) {
        this.b = sectionBean;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        LogUtil.a("SmartWearablePressureProvider", "SharedPreference");
        this.e = context;
        return !"true".equals(SharedPreferenceManager.b(context, String.valueOf(PrebakedEffectId.RT_COIN_DROP), "IS_CLOSED_SP_KEY"));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("SmartWearablePressureProvider", "parseParams");
        if (hashMap == null || context == null) {
            return;
        }
        this.e = context;
        if (!Utils.o()) {
            hashMap.put("ICON_CANCEL", ContextCompat.getDrawable(context, R.drawable._2131430276_res_0x7f0b0b84));
        }
        hashMap.put("CARD_TITLE", context.getResources().getString(R$string.IDS_pressure_device_card_title));
        hashMap.put("CARD_DESCRIPTION", context.getResources().getString(R$string.IDS_health_no_data_pressure));
        hashMap.put("CLOSE_BUTTON_SP_KEY", "IS_CLOSED_SP_KEY");
        if (Utils.o()) {
            return;
        }
        hashMap.put("CARD_LEARN_MORE", context.getResources().getString(R$string.IDS_hw_pressure_learn_more));
        hashMap.put("LEARN_MORE_COLOR", Integer.valueOf(R.color._2131298967_res_0x7f090a97));
        OnClickSectionListener onClickSectionListener = new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.pressure.provider.SmartWearablePressureProvider.5
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
                LogUtil.a("SmartWearablePressureProvider", "click");
                if (view.getId() == R.id.sleep_device_card_learn_more) {
                    LogUtil.a("SmartWearablePressureProvider", "sleep no data device card is clicked!");
                    SmartWearablePressureProvider.this.a(GRSManager.a(SmartWearablePressureProvider.this.e).getUrl("messageCenterUrl") + "/messageH5/sleephtml/salesPromotion.html");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        Context context2 = this.e;
        if (context2 instanceof BaseActivity) {
            hashMap.put("CLICK_EVENT_LISTENER", nkx.cwZ_(onClickSectionListener, (BaseActivity) context2, true, ""));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", "1");
        gge.e(AnalyticsValue.HEALTH_PRESSURE_DATA_ANALYSE_CLICK_2160016.value(), hashMap);
        Intent intent = new Intent(this.e, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        gnm.aPB_(this.e, intent);
    }
}
