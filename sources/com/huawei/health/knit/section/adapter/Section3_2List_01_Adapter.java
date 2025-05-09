package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseBiItemView;
import com.huawei.health.search.SearchResultItemView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ccq;
import defpackage.dpf;
import defpackage.eet;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section3_2List_01_Adapter extends RecyclerView.Adapter<d> {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, List<Object>> f2556a;
    private List<Map<String, Object>> b;
    private Context c;
    private List<Object> d;
    private List<Object> e;
    private String f;
    private int g = Integer.MAX_VALUE;
    private OnClickSectionListener i;

    public Section3_2List_01_Adapter(Context context) {
        this.c = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: adh_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(new SearchResultItemView(this.c, R.layout.config_scholastic_page_item));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, final int i) {
        if ((dVar.itemView instanceof BaseBiItemView) && eet.b(this.b, i)) {
            ((BaseBiItemView) dVar.itemView).b(this.b.get(i));
        }
        if ((this.e.get(i) instanceof String) && eet.b(this.e, i)) {
            e(dVar, i);
        } else if ((this.e.get(i) instanceof Integer) && eet.b(this.e, i)) {
            d(dVar, ((Integer) this.e.get(i)).intValue());
        } else {
            dVar.e.setVisibility(4);
        }
        if ((this.d.get(i) instanceof String) && eet.b(this.d, i)) {
            nsy.cMs_(dVar.f2557a, dpf.Ym_((String) this.d.get(i), this.f), true);
        }
        if (eet.b(this.d, i)) {
            a(dVar, i);
        }
        dVar.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.Section3_2List_01_Adapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Section3_2List_01_Adapter.this.i != null) {
                    Section3_2List_01_Adapter.this.i.onClick(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e(d dVar, int i) {
        b(dVar);
        if ((this.e.get(i) instanceof String) && eet.b(this.e, i)) {
            nrf.cIS_(dVar.e, (String) this.e.get(i), nrf.e, 0, 0);
        }
    }

    private void b(d dVar) {
        ViewGroup.LayoutParams layoutParams = dVar.e.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.height = nrr.e(BaseApplication.getContext(), 56.0f);
            layoutParams2.width = (layoutParams2.height * 16) / 9;
            dVar.e.setLayoutParams(layoutParams2);
        }
    }

    private void d(d dVar, int i) {
        b(dVar);
        nrf.cIR_(dVar.e, BaseApplication.getContext().getResources().getDrawable(i), nrf.d, 0);
    }

    private void a(d dVar, int i) {
        if (getItemCount() == 0) {
            return;
        }
        if (getItemCount() == 1) {
            d(dVar.c);
            return;
        }
        if (nsn.ag(BaseApplication.getContext())) {
            if (i == getItemCount() - 1 || i == getItemCount() - 2) {
                d(dVar.c);
                return;
            }
            return;
        }
        if (i == getItemCount() - 1) {
            d(dVar.c);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return Math.min(a(this.f2556a), this.g);
    }

    private int a(Map<String, List<Object>> map) {
        return ccq.b(map, "Section3_2List_01_Adapter");
    }

    class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2557a;
        HealthDivider c;
        LinearLayout d;
        ImageView e;

        d(View view) {
            super(view);
            this.c = (HealthDivider) view.findViewById(R.id.right_img_item_line);
            this.e = (ImageView) view.findViewById(R.id.left_img_item_configure);
            this.f2557a = (HealthTextView) view.findViewById(R.id.text_area_title);
            this.d = (LinearLayout) view.findViewById(R.id.item_configure_image_text_layout);
        }
    }

    public void d(Map<String, List<Object>> map) {
        this.f2556a = map;
        this.e = new ArrayList();
        this.d = new ArrayList();
        for (Map.Entry<String, List<Object>> entry : this.f2556a.entrySet()) {
            String key = entry.getKey();
            List<Object> value = entry.getValue();
            if (key.equals("SCHOLASTIC_TYPE_CONTENT")) {
                this.d = value;
            } else if (key.equals("SCHOLASTIC_TYPE_IMAGE")) {
                this.e = value;
            } else {
                LogUtil.h("Section3_2List_01_Adapter", "setAdapterData listContent or listLeftImage does not obtained!");
                return;
            }
        }
    }

    public void d(int i) {
        this.g = i;
    }

    private void d(HealthDivider healthDivider) {
        if (healthDivider == null) {
            LogUtil.h("Section3_2List_01_Adapter", "setHealthDividerIsVisible healthDivider is null.");
        } else {
            healthDivider.setVisibility(4);
        }
    }

    public void a(OnClickSectionListener onClickSectionListener) {
        this.i = onClickSectionListener;
    }

    public void b(String str) {
        this.f = str;
    }

    public void e(List<Map<String, Object>> list) {
        this.b = list;
    }
}
