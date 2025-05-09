package com.huawei.ui.homewear21.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homewear21.home.bean.WearHomeXmlParseBean;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.pbq;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeLegalRecyclerAdapter extends RecyclerView.Adapter<LegalViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<?> f9667a;
    private WearHomeListener b;
    private String c;
    private Context d;

    public WearHomeLegalRecyclerAdapter(Context context, List<?> list, String str) {
        this.d = context;
        this.f9667a = list;
        this.c = str;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dlu_, reason: merged with bridge method [inline-methods] */
    public LegalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LegalViewHolder(LayoutInflater.from(this.d).inflate(R.layout.activity_device_legal_information_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(LegalViewHolder legalViewHolder, final int i) {
        if (i >= this.f9667a.size()) {
            return;
        }
        if (this.c.equals("legalAdapter")) {
            legalViewHolder.b.setText(((pbq) this.f9667a.get(i)).d());
        } else {
            Object obj = this.f9667a.get(i);
            if (obj instanceof WearHomeXmlParseBean) {
                legalViewHolder.b.setText(((WearHomeXmlParseBean) obj).getXmlName());
            }
        }
        if (this.f9667a.size() - 1 == i) {
            legalViewHolder.c.setVisibility(8);
        }
        if (LanguageUtil.bc(this.d)) {
            legalViewHolder.e.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            legalViewHolder.e.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        legalViewHolder.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homewear21.home.adapter.WearHomeLegalRecyclerAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WearHomeLegalRecyclerAdapter.this.b != null) {
                    if (!nsn.o()) {
                        WearHomeLegalRecyclerAdapter.this.b.onItemClick(i);
                    } else {
                        LogUtil.h(WearHomeLegalRecyclerAdapter.this.c, "click too fast");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.f9667a.size() > 0) {
            return this.f9667a.size();
        }
        return 0;
    }

    public void c(WearHomeListener wearHomeListener) {
        this.b = wearHomeListener;
    }

    public static class LegalViewHolder extends RecyclerView.ViewHolder {
        private HealthTextView b;
        private HealthDivider c;
        private RelativeLayout d;
        private ImageView e;

        LegalViewHolder(View view) {
            super(view);
            this.c = (HealthDivider) nsy.cMd_(view, R.id.legal_divider);
            this.b = (HealthTextView) nsy.cMd_(view, R.id.legal_information_content);
            this.d = (RelativeLayout) nsy.cMd_(view, R.id.relative_setting_legal_information);
            this.e = (ImageView) nsy.cMd_(view, R.id.legal_image);
        }
    }
}
