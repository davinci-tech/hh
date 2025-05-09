package com.huawei.ui.commonui.sport.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.koq;
import defpackage.nqj;
import defpackage.nql;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class GridBanner extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private nqj f8949a;
    private Context b;
    private HealthViewPager c;
    private LinearLayout d;
    private HealthDotsPageIndicator e;
    private int i;

    public GridBanner(Context context) {
        super(context);
        b(context);
    }

    public GridBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context);
    }

    public GridBanner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b(context);
    }

    private void b(Context context) {
        this.b = context;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_common_banner, (ViewGroup) this, false);
        this.d = linearLayout;
        addView(linearLayout);
        this.e = (HealthDotsPageIndicator) this.d.findViewById(R.id.common_banner_indicator);
    }

    public void setData(List<nql> list) {
        this.f8949a = new nqj();
        List<View> b = b(list);
        e();
        this.c.setAdapter(this.f8949a);
        this.f8949a.a(b);
        this.e.setViewPager(this.c);
    }

    private void e() {
        if (this.c != null) {
            return;
        }
        HealthViewPager healthViewPager = new HealthViewPager(this.b);
        this.c = healthViewPager;
        healthViewPager.setClipToPadding(false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.i);
        layoutParams.gravity = 1;
        this.c.setLayoutParams(layoutParams);
        this.d.addView(this.c, 0);
    }

    private List<View> b(List<nql> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            nql nqlVar = list.get(i);
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.layout_common_banner_grid, (ViewGroup) null);
            HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.common_banner_header);
            healthSubHeader.setHeadTitleText(nqlVar.d());
            healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(this.b, R$color.colorCardPanelBg));
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.common_banner_grid_recycler);
            recyclerView.setAdapter(new e(this.b, nqlVar.a()));
            recyclerView.setLayoutManager(new GridLayoutManager(this.b, 3, 1, false) { // from class: com.huawei.ui.commonui.sport.base.GridBanner.5
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollHorizontally() {
                    return false;
                }

                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollVertically() {
                    return false;
                }
            });
            inflate.measure(View.MeasureSpec.makeMeasureSpec(nsn.n(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(nsn.j(), Integer.MIN_VALUE));
            int measuredHeight = inflate.getMeasuredHeight();
            arrayList.add(inflate);
            this.i = Math.max(measuredHeight, this.i);
        }
        return arrayList;
    }

    static final class c extends RecyclerView.ViewHolder {
        private static final String[] d = {"ml", "si", "ja", "ar", "pt", "ca", "fr", "gl", "it", "jv", "lt", "ms", "sk", "sv"};

        /* renamed from: a, reason: collision with root package name */
        private final ImageView f8950a;
        private boolean b;
        private final HealthTextView c;
        private final HealthTextView e;

        public c(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.grid_title);
            this.e = (HealthTextView) view.findViewById(R.id.grid_sub_title);
            this.f8950a = (ImageView) view.findViewById(R.id.grid_divider);
            this.b = e();
        }

        public void c(nql.e eVar, int i, int i2) {
            this.c.setText(eVar.b);
            this.e.setText(eVar.d);
            if (i2 > 3 && i > 2 && this.b) {
                this.c.setHeight(nsn.c(BaseApplication.e(), 18.0f));
                this.c.setTextSize(1, 16.0f);
                this.c.setMaxLines(1);
                this.c.setEllipsize(TextUtils.TruncateAt.END);
                this.e.setHeight(nsn.c(BaseApplication.e(), 42.0f));
                this.e.setTextSize(1, 10.0f);
                this.e.setMaxLines(2);
                this.e.setEllipsize(TextUtils.TruncateAt.END);
            }
            if ((i + 1) % 3 == 0 || i == i2 - 1) {
                this.f8950a.setVisibility(8);
            }
        }

        private boolean e() {
            String language = BaseApplication.e().getResources().getConfiguration().locale.getLanguage();
            for (String str : d) {
                if (str.equalsIgnoreCase(language)) {
                    return true;
                }
            }
            return false;
        }
    }

    static final class e extends RecyclerView.Adapter<c> {

        /* renamed from: a, reason: collision with root package name */
        private final Context f8951a;
        private List<nql.e> c;

        public e(Context context, List<nql.e> list) {
            ArrayList arrayList = new ArrayList();
            this.c = arrayList;
            this.f8951a = context;
            arrayList.addAll(list);
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: cGc_, reason: merged with bridge method [inline-methods] */
        public c onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new c(LayoutInflater.from(this.f8951a).inflate(R.layout.layout_common_banner_grid_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(c cVar, int i) {
            if (koq.b(this.c, i)) {
                LogUtil.h("GridBanner", "mRecyclerDataList isOutOfBounds");
            } else {
                cVar.c(this.c.get(i), i, getItemCount());
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.c.size();
        }
    }
}
