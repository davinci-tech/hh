package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bdu;
import defpackage.nru;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionSmartWearableCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2739a;
    private HealthCardView b;
    private Context c;
    private ImageView d;
    private HealthTextView e;
    private HealthTextView g;
    private StorageParams h;

    @Override // com.huawei.health.knit.section.view.BaseSection
    public boolean isSupportShare() {
        return false;
    }

    public SectionSmartWearableCard(Context context) {
        this(context, null);
    }

    public SectionSmartWearableCard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionSmartWearableCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new StorageParams();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section_Smart_Wearable_Card", "onCreateView");
        this.c = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.sleep_no_data_smart_wearable_layout, (ViewGroup) this, false);
        this.b = (HealthCardView) inflate.findViewById(R.id.sleep_no_data_device_card);
        if (!LanguageUtil.m(context)) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.cancel_image);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = getResources().getDimensionPixelSize(R.dimen._2131362722_res_0x7f0a03a2);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable._2131430276_res_0x7f0b0b84);
            if (LanguageUtil.bc(context)) {
                imageView.setImageDrawable(nrz.cKm_(context, getResources().getDrawable(R.drawable._2131430276_res_0x7f0b0b84)));
            }
        }
        this.d = (ImageView) inflate.findViewById(R.id.cancel_image);
        this.g = (HealthTextView) inflate.findViewById(R.id.sleep_device_card_title);
        if (nsn.v(this.c)) {
            this.g.setTextColor(getResources().getColor(R.color._2131299238_res_0x7f090ba6, null));
        }
        this.f2739a = (HealthTextView) inflate.findViewById(R.id.sleep_device_card_description);
        this.e = (HealthTextView) inflate.findViewById(R.id.sleep_device_card_learn_more);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(final HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_Smart_Wearable_Card", "no need to bind");
            return;
        }
        LogUtil.a("Section_Smart_Wearable_Card", hashMap);
        Drawable drawable = (Drawable) nru.c(hashMap, "ICON_CANCEL", Drawable.class, null);
        if (drawable == null) {
            nsy.cMA_(this.d, 8);
        } else {
            nsy.cMj_(this.d, drawable);
        }
        nsy.cMr_(this.g, nru.b(hashMap, "CARD_TITLE", ""));
        nsy.cMr_(this.f2739a, nru.b(hashMap, "CARD_DESCRIPTION", ""));
        nsy.cMs_(this.e, nru.b(hashMap, "CARD_LEARN_MORE", ""), true);
        nsy.cMu_(this.e, getContext().getColor(nru.d((Map) hashMap, "LEARN_MORE_COLOR", R.color._2131296927_res_0x7f09029f)));
        nsy.cMn_(this.e, bdu.e(hashMap, "CLICK_EVENT_LISTENER", null));
        if (drawable != null) {
            nsy.cMn_(this.d, new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionSmartWearableCard.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SectionSmartWearableCard.this.b.setVisibility(8);
                    String b = nru.b(hashMap, "CLOSE_BUTTON_SP_KEY", "");
                    if (!TextUtils.isEmpty(b)) {
                        SharedPreferenceManager.e(SectionSmartWearableCard.this.c, String.valueOf(PrebakedEffectId.RT_COIN_DROP), b, "true", SectionSmartWearableCard.this.h);
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    LogUtil.b("Section_Smart_Wearable_Card", "sp key is invalid");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Smart_Wearable_Card";
    }
}
