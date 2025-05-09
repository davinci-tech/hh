package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section1_1Card_02_Adapter;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.nru;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section1_1Card_02 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private Context f2644a;
    private OnClickSectionListener b;
    private HealthRecycleView c;
    private int d;
    private HealthSubHeader e;
    private String f;
    private List<Object> g;
    private Section1_1Card_02_Adapter i;
    private View j;

    public Section1_1Card_02(Context context) {
        this(context, null);
    }

    public Section1_1Card_02(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section1_1Card_02(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.f2644a = context;
        c();
        return this.j;
    }

    private void c() {
        if (this.j == null) {
            LogUtil.h("Section1_1Card_02", "initView mainView is null, start to inflate");
            View inflate = LayoutInflater.from(this.f2644a).inflate(R.layout.section1_1card_02_layout, (ViewGroup) this, false);
            this.j = inflate;
            this.e = (HealthSubHeader) inflate.findViewById(R.id.section1_1_card_02_subheader);
            HealthRecycleView healthRecycleView = (HealthRecycleView) this.j.findViewById(R.id.section1_1card_02_recyclerview);
            this.c = healthRecycleView;
            ccq.b(healthRecycleView);
            this.c.setLayoutManager(new LinearLayoutManager(this.f2644a, 0, false));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        char c;
        LogUtil.a("Section1_1Card_02", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section1_1Card_02", "no need to bind");
            return;
        }
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            key.hashCode();
            switch (key.hashCode()) {
                case -1277871080:
                    if (key.equals("SUBTITLE")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1151659172:
                    if (key.equals("SECTION1_1CARD_02_BEAN")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -594925846:
                    if (key.equals("PAGE_TYPE")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 79833656:
                    if (key.equals("TITLE")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1675868304:
                    if (key.equals("CLICK_EVENT_LISTENER")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c == 2) {
                        this.d = nru.d((Map) hashMap, key, 0);
                    } else if (c == 3) {
                        setTittle(value);
                    } else if (c == 4) {
                        setClickEvent(value);
                    }
                } else if (value instanceof List) {
                    LogUtil.a("Section1_1Card_02", "start to set section1_1Card_02_BeanList");
                    this.g = (List) value;
                }
            } else if (value instanceof String) {
                LogUtil.a("Section1_1Card_02", "start to set mSubHeader subtitle");
                this.e.setMoreTextVisibility(8);
                this.e.setMoreText(String.valueOf(value));
                this.e.setRightArrayVisibility(8);
            }
        }
        Section1_1Card_02_Adapter section1_1Card_02_Adapter = new Section1_1Card_02_Adapter(this.f2644a, this.g, this.b);
        this.i = section1_1Card_02_Adapter;
        this.c.setAdapter(section1_1Card_02_Adapter);
        this.c.setOnScrollListener(new MarketingBiUtils.FitnessOnScrollListener(this.d, this.f, 12));
    }

    private void setTittle(Object obj) {
        if (obj instanceof String) {
            LogUtil.a("Section1_1Card_02", "start to set mSubHeader title");
            this.f = String.valueOf(obj);
            this.e.setVisibility(0);
            this.e.setHeadTitleText(this.f);
            this.e.setMoreTextVisibility(8);
            this.e.setSubHeaderBackgroundColor(this.f2644a.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
            this.e.setRightArrayVisibility(8);
        }
    }

    private void setClickEvent(Object obj) {
        HealthRecycleView healthRecycleView = this.c;
        if (healthRecycleView == null || !(obj instanceof OnClickSectionListener)) {
            return;
        }
        this.b = (OnClickSectionListener) obj;
        healthRecycleView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section1_1Card_02.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Section1_1Card_02", "set subtitleClickEvent onClick");
                Section1_1Card_02.this.b.onClick("SUBTITLE");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section1_1Card_02";
    }
}
