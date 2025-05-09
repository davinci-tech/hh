package com.huawei.ui.main.stories.health.temperature.view.levelcard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.temperature.view.levelcard.MultiViewDataObserverView;
import defpackage.ggs;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nrs;
import defpackage.nsn;
import defpackage.qpr;
import defpackage.qqa;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class TemperatureCardView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f10254a;
    private boolean b;
    private MultiViewDataObserverView c;
    private String[] d;
    private MultiViewDataObserverView.OnSelectListener e;
    private Map<String, String> f;
    private String g;
    private List<ScrollCardParentView> j;

    public TemperatureCardView(Context context) {
        super(context);
        this.d = new String[]{"TEMPERATURE_MIN_MAX", "SKIN_TEMPERATURE_MIN_MAX"};
        this.b = false;
        this.g = "TEMPERATURE_MIN_MAX";
        c();
    }

    public TemperatureCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new String[]{"TEMPERATURE_MIN_MAX", "SKIN_TEMPERATURE_MIN_MAX"};
        this.b = false;
        this.g = "TEMPERATURE_MIN_MAX";
        this.f10254a = context;
        c();
    }

    public void a(String str) {
        this.g = str;
        removeAllViews();
        c();
    }

    private Map<String, String> b() {
        HashMap hashMap = new HashMap(2);
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                hashMap.put("TEMPERATURE_MIN_MAX", "--");
            } else {
                hashMap.put("SKIN_TEMPERATURE_MIN_MAX", "--");
            }
        }
        return hashMap;
    }

    private void c() {
        Utils.init(this.f10254a);
        this.b = nsn.ag(this.f10254a);
        e(a(getTemperatureList()));
    }

    private Map<String, qqa> a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.c("TemperatureCardView", "getTemperatureCardContent temperatureList is null");
            map = b();
        }
        HashMap hashMap = new HashMap(16);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            qqa qqaVar = new qqa();
            qqaVar.b(entry.getValue());
            if (entry.getKey() == "TEMPERATURE_MIN_MAX") {
                qqaVar.a(getResources().getString(R$string.IDS_settings_health_temperature));
            } else if (entry.getKey() == "SKIN_TEMPERATURE_MIN_MAX") {
                qqaVar.a(getResources().getString(R$string.IDS_health_skin_temperature));
            } else {
                LogUtil.d("TemperatureCardView", "other card");
            }
            qqaVar.e(qpr.c());
            hashMap.put(entry.getKey(), qqaVar);
        }
        return hashMap;
    }

    public void d(final Map<String, String> map) {
        post(new Runnable() { // from class: com.huawei.ui.main.stories.health.temperature.view.levelcard.TemperatureCardView.2
            @Override // java.lang.Runnable
            public void run() {
                if (TemperatureCardView.this.f10254a == null) {
                    LogUtil.c("TemperatureCardView", "notifyCardContent, the context is invalid");
                } else {
                    TemperatureCardView.this.b(map);
                }
            }
        });
    }

    public void b(Map<String, String> map) {
        if (koq.b(this.j) || map == null || map.isEmpty()) {
            LogUtil.c("TemperatureCardView", "updateContent mScrollCardParentViewList is null");
            return;
        }
        LogUtil.b("TemperatureCardView", "updateContent ", Integer.valueOf(map.size()));
        for (int i = 0; i < this.j.size(); i++) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                ScrollCardParentView scrollCardParentView = this.j.get(i);
                if (scrollCardParentView.getType().equals(entry.getKey())) {
                    scrollCardParentView.a(entry.getValue());
                    a(scrollCardParentView, entry.getValue());
                    LogUtil.d("TemperatureCardView", "updateContent ", entry.getValue());
                }
            }
        }
    }

    private void a(ScrollCardParentView scrollCardParentView, String str) {
        if ("--".equals(str)) {
            scrollCardParentView.getUnitTextView().setVisibility(8);
        } else {
            scrollCardParentView.getUnitTextView().setVisibility(0);
        }
    }

    private int getMargin() {
        Context context = this.f10254a;
        if (context == null) {
            LogUtil.c("TemperatureCardView", "getMargin() mContext is null.");
            return 0;
        }
        return context.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
    }

    private int getGutter() {
        return nrr.b(this.f10254a);
    }

    private int getCardWidth() {
        int margin = getMargin();
        int gutter = getGutter();
        return this.b ? ((((ggs.a(this.f10254a) - (margin * 2)) - (gutter * 7)) / 8) * 2) + gutter : (int) Utils.convertDpToPixel(getNormalWidth());
    }

    private int getNormalWidth() {
        if (this.f10254a == null) {
            LogUtil.c("TemperatureCardView", "getNormalWidth() mContext is null.");
            return MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY;
        }
        LogUtil.b("TemperatureCardView", "getNormalWidth() safeWidth is ", Integer.valueOf(((Integer) BaseActivity.getSafeRegionWidth().first).intValue() * 2));
        return nrr.d(this.f10254a, ((nrs.c(this.f10254a) - ((getMargin() + r0) * 2)) - getGutter()) / 2);
    }

    private void e(Map<String, qqa> map) {
        MultiViewDataObserverView multiViewDataObserverView = new MultiViewDataObserverView(this.f10254a);
        this.c = multiViewDataObserverView;
        multiViewDataObserverView.setDefaultTitleColor(R.color._2131299241_res_0x7f090ba9);
        this.c.setDefaultColor(R.color._2131297370_res_0x7f09045a);
        this.c.setDefaultBackground(R.drawable._2131427568_res_0x7f0b00f0);
        this.c.setSelectColor(R.color._2131299238_res_0x7f090ba6);
        this.c.setSelectBackground(R.drawable._2131427583_res_0x7f0b00ff);
        this.c.dHn_().setMinimumWidth(getGutter());
        this.c.dHo_().setMinimumWidth(getMargin());
        this.j = new ArrayList(16);
        for (int i = 0; i < this.d.length; i++) {
            for (Map.Entry<String, qqa> entry : map.entrySet()) {
                if (entry != null && entry.getKey() != null && entry.getKey().equals(this.d[i])) {
                    qqa value = entry.getValue();
                    ScrollCardParentView scrollCardParentView = new ScrollCardParentView(this.f10254a, value.e(), value.c(), entry.getKey(), value.a());
                    a(scrollCardParentView, value.a());
                    this.j.add(scrollCardParentView);
                }
            }
        }
        this.c.setCardWidth(getCardWidth());
        this.c.b(this.j);
        if (this.d[0].equals(this.g)) {
            this.c.setSelectIndex(this.j.get(0));
        } else if (this.d[1].equals(this.g)) {
            this.c.setSelectIndex(this.j.get(1));
        } else {
            LogUtil.c("TemperatureCardView", "TemperatureType Is UnKnow");
        }
        MultiViewDataObserverView.OnSelectListener onSelectListener = this.e;
        if (onSelectListener != null) {
            this.c.setListener(onSelectListener);
        }
        addView(this.c, -1, -2);
    }

    public void setListener(MultiViewDataObserverView.OnSelectListener onSelectListener) {
        MultiViewDataObserverView multiViewDataObserverView;
        this.e = onSelectListener;
        if (onSelectListener == null || (multiViewDataObserverView = this.c) == null) {
            return;
        }
        multiViewDataObserverView.setListener(onSelectListener);
    }

    public Map<String, String> getTemperatureList() {
        return this.f;
    }
}
