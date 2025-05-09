package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9List_02Adapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.eaw;
import defpackage.eet;
import defpackage.fbh;
import defpackage.koq;
import defpackage.nru;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class Section21_9List_02 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private Observer f2649a;
    private Context b;
    private OnClickSectionListener c;
    private List<eaw> d;
    private String e;
    private HealthSubHeader f;
    private View g;
    private HealthRecycleView i;

    public Section21_9List_02(Context context) {
        this(context, null);
    }

    public Section21_9List_02(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section21_9List_02(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section21_9list_02_layout, (ViewGroup) this, false);
        this.g = inflate;
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section21_9list_02_sub_header);
        this.f = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.f.setMoreLayoutVisibility(4);
        this.f.setSubHeaderBackgroundColor(ContextCompat.getColor(this.g.getContext(), R.color._2131296971_res_0x7f0902cb));
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.g.findViewById(R.id.section21_9list_02_recycle_view);
        this.i = healthRecycleView;
        healthRecycleView.setHasFixedSize(true);
        this.i.setNestedScrollingEnabled(false);
        this.i.a(false);
        this.i.d(false);
        eet.c(context, this.i, new HealthLinearLayoutManager(context), false, 0);
        return this.g;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            LogUtil.a("Section21_9List_02", "currentParams is null or empty");
            return;
        }
        this.f.setHeadTitleText(nru.b(hashMap, "TITLE", ""));
        this.c = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        String b = nru.b(hashMap, "SHOWMORE", null);
        final OnClickSectionListener a2 = nru.a(hashMap, "SHOW_MORE_CLICK_EVENT", null);
        if (b != null && a2 != null) {
            this.f.setMoreText(b);
            this.f.setMoreTextVisibility(0);
            this.f.setMoreLayoutVisibility(0);
            this.f.setMoreViewClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section21_9List_02.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    a2.onClick("SHOW_MORE_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            this.f.setMoreText("");
            this.f.setMoreTextVisibility(4);
            this.f.setMoreLayoutVisibility(4);
        }
        Object obj = hashMap.get("SECTION21_9LIST_02BEAN");
        this.d = koq.e(obj, eaw.class) ? (List) obj : null;
        String b2 = nru.b(hashMap, "BI_OBSERVER_LABEL", null);
        if (b2 != null && !TextUtils.equals(b2, this.e)) {
            String str = this.e;
            if (str != null && this.f2649a != null) {
                LogUtil.a("Section21_9List_02", "unregister old observer, old label: ", str);
                ObserverManagerUtil.e(this.f2649a, this.e);
            }
            Observer a3 = fbh.a(this.i);
            this.f2649a = a3;
            this.e = b2;
            ObserverManagerUtil.d(a3, b2);
            LogUtil.a("Section21_9List_02", "register biObserver, label: ", this.e);
        }
        if (this.d != null) {
            Section21_9List_02Adapter section21_9List_02Adapter = new Section21_9List_02Adapter(this.b, this.d);
            section21_9List_02Adapter.d(this.c);
            section21_9List_02Adapter.c(nru.b(hashMap, "HIGHLIGHTED_TEXT", ""));
            this.i.setAdapter(section21_9List_02Adapter);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section21_9List_02";
    }
}
