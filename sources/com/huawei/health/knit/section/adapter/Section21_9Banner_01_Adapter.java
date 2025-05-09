package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ccq;
import defpackage.eet;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class Section21_9Banner_01_Adapter extends RecyclerView.Adapter<a> {

    /* renamed from: a, reason: collision with root package name */
    private List<Object> f2534a;
    private List<Object> b;
    private List<Object> c;
    private List<Object> d;
    private List<Object> e;
    private Map<String, List<Object>> f;
    private Context g;
    private int h;
    private OnClickSectionListener i;
    private Pair<Integer, Integer> j = BaseActivity.getSafeRegionWidth();

    public Section21_9Banner_01_Adapter(Context context, OnClickSectionListener onClickSectionListener) {
        this.i = onClickSectionListener;
        this.g = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acz_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.configured_activity_page_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, final int i) {
        acy_(aVar.c);
        aVar.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.Section21_9Banner_01_Adapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Section21_9Banner_01_Adapter.this.i != null && eet.b(Section21_9Banner_01_Adapter.this.b, i)) {
                    Section21_9Banner_01_Adapter.this.i.onClick(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if ((this.b.get(i) instanceof String) && eet.b(this.b, i)) {
            b((String) this.b.get(i), aVar.h);
        }
        if ((this.e.get(i) instanceof String) && eet.b(this.e, i)) {
            e((String) this.e.get(i), aVar.f2536a);
        }
        if ((this.d.get(i) instanceof String) && eet.b(this.d, i)) {
            a((String) this.d.get(i), aVar.b);
        } else if ((this.d.get(i) instanceof Integer) && eet.b(this.d, i)) {
            acw_(aVar.b, ((Integer) this.d.get(i)).intValue());
        } else {
            aVar.b.setVisibility(4);
        }
        if ((this.c.get(i) instanceof String) && eet.b(this.c, i)) {
            d((String) this.c.get(i), aVar.d);
        }
        if ((this.f2534a.get(i) instanceof Map) && eet.b(this.f2534a, i)) {
            b((Map<String, Integer>) this.f2534a.get(i), aVar.e);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return c(this.f);
    }

    class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2536a;
        HealthImageView b;
        RelativeLayout c;
        HealthTextView d;
        HealthTextView e;
        HealthTextView h;

        a(View view) {
            super(view);
            this.c = (RelativeLayout) view.findViewById(R.id.activity_layout);
            this.b = (HealthImageView) view.findViewById(R.id.activity_image);
            this.f2536a = (HealthTextView) view.findViewById(R.id.activity_description);
            this.h = (HealthTextView) view.findViewById(R.id.activity_title);
            this.e = (HealthTextView) view.findViewById(R.id.activity_status);
            this.d = (HealthTextView) view.findViewById(R.id.activity_join_num);
        }
    }

    private void acy_(RelativeLayout relativeLayout) {
        int a2 = a();
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = a2;
        layoutParams.height = (a2 * 9) / 21;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private int a() {
        this.h = nrr.b(this.g);
        if (nsn.ag(this.g)) {
            return (((this.g.getResources().getDisplayMetrics().widthPixels - this.g.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e)) - this.g.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e)) - this.h) / 2;
        }
        int i = this.g.getResources().getDisplayMetrics().widthPixels;
        int dimensionPixelSize = this.g.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        return (((i - dimensionPixelSize) - this.g.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e)) - ((Integer) this.j.first).intValue()) - ((Integer) this.j.second).intValue();
    }

    public void e(Map<String, List<Object>> map) {
        this.f = map;
        this.b = new ArrayList();
        this.e = new ArrayList();
        this.f2534a = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        for (Map.Entry<String, List<Object>> entry : this.f.entrySet()) {
            String key = entry.getKey();
            List<Object> value = entry.getValue();
            if (key.equals("ACTIVITY_TYPE_CONTENT")) {
                this.b = value;
            } else if (key.equals("ACTIVITY_TYPE_DESCRIPTION")) {
                this.e = value;
            } else if (key.equals(CommonConstant.RETKEY.STATUS)) {
                this.f2534a = value;
            } else if (key.equals("NUMBER")) {
                this.c = value;
            } else if (key.equals("IMAGE")) {
                this.d = value;
            } else {
                Log.w("Section21_9Banner_01_Adapter", "mDataList does not obtained!");
                return;
            }
        }
    }

    private int c(Map<String, List<Object>> map) {
        return ccq.b(map, "Section21_9Banner_01_Adapter");
    }

    private void a(String str, HealthImageView healthImageView) {
        acx_(healthImageView);
        nrf.c(healthImageView, str, nrf.d, 0, 0);
    }

    private void acw_(ImageView imageView, int i) {
        acx_(imageView);
        nrf.cIR_(imageView, this.g.getResources().getDrawable(i), nrf.d, 0);
    }

    private void acx_(ImageView imageView) {
        int a2 = a();
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(a2, (a2 * 9) / 21));
    }

    public void e(String str, HealthTextView healthTextView) {
        if (TextUtils.isEmpty(str)) {
            healthTextView.setVisibility(4);
            Log.w("Section21_9Banner_01_Adapter", "activityDescriptionText is null.");
        } else {
            healthTextView.setText(str);
        }
    }

    private void d(String str, HealthTextView healthTextView) {
        if (TextUtils.isEmpty(str)) {
            healthTextView.setVisibility(4);
            Log.w("Section21_9Banner_01_Adapter", "numOfPeople is null.");
        } else {
            healthTextView.setText(str);
        }
    }

    private void b(String str, HealthTextView healthTextView) {
        if (TextUtils.isEmpty(str)) {
            healthTextView.setVisibility(4);
            Log.w("Section21_9Banner_01_Adapter", "activityTitleText is null.");
        } else {
            healthTextView.setText(str);
        }
    }

    private void b(Map<String, Integer> map, HealthTextView healthTextView) {
        if (map.isEmpty()) {
            Log.w("Section21_9Banner_01_Adapter", "status is null.");
            return;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            CharSequence charSequence = (String) entry.getKey();
            Integer value = entry.getValue();
            healthTextView.setVisibility(0);
            healthTextView.setText(charSequence);
            if (healthTextView.getBackground() instanceof GradientDrawable) {
                GradientDrawable gradientDrawable = (GradientDrawable) healthTextView.getBackground();
                gradientDrawable.setColor(this.g.getResources().getColor(value.intValue()));
                healthTextView.setBackground(gradientDrawable);
            }
        }
    }
}
