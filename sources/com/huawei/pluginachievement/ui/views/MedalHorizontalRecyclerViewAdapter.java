package com.huawei.pluginachievement.ui.views;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.ui.views.MedalHorizontalRecyclerViewAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mlb;
import defpackage.mlu;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class MedalHorizontalRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, Integer> f8469a;
    private Pair<Integer, Integer> b;
    private Context c;
    private List<MedalInfoDesc> d;
    private List<String> e;

    public MedalHorizontalRecyclerViewAdapter(Context context, List<MedalInfoDesc> list, List<String> list2) {
        HashMap hashMap = new HashMap(16);
        this.f8469a = hashMap;
        this.b = BaseActivity.getSafeRegionWidth();
        this.d = list;
        this.c = context;
        this.e = list2;
        if (hashMap.isEmpty()) {
            mlb.c(hashMap);
        }
    }

    public void d(List<MedalInfoDesc> list, List<String> list2) {
        if (koq.b(list) || koq.b(list2)) {
            return;
        }
        this.d = list;
        this.e = list2;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: clk_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.medal_horizontal_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        clh_(viewHolder.itemView, i, getItemCount());
        if (koq.b(this.d, i)) {
            LogUtil.h("PLGACHIEVE_MedalHorizontalRecyclerViewAdapter", "onBindViewHolder: isOutOfBounds -> " + i);
            return;
        }
        if (this.f8469a.isEmpty()) {
            LogUtil.h("PLGACHIEVE_MedalHorizontalRecyclerViewAdapter", "onBindViewHolder: mMedalImages is empty");
            return;
        }
        String acquireMedalId = this.d.get(i).acquireMedalId();
        String d = mlb.d(acquireMedalId, ParsedFieldTag.LIGHT_DETAIL_STYLE);
        if (!mlb.d(d)) {
            int l = mlb.l(acquireMedalId);
            if (l > 0 && l <= 19) {
                Integer num = this.f8469a.get(acquireMedalId);
                if (num == null) {
                    LogUtil.h("PLGACHIEVE_MedalHorizontalRecyclerViewAdapter", "onBindViewHolder: medalImageResourceId is null");
                } else {
                    viewHolder.d.setImageResource(num.intValue());
                }
            }
        } else {
            Glide.with(this.c).load(d).into(viewHolder.d);
        }
        viewHolder.d.setOnClickListener(new View.OnClickListener() { // from class: mlp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedalHorizontalRecyclerViewAdapter.this.clj_(i, view);
            }
        });
    }

    public /* synthetic */ void clj_(int i, View view) {
        if (koq.d(this.d, i)) {
            a(this.d.get(i));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(MedalInfoDesc medalInfoDesc) {
        String str;
        Intent intent = new Intent();
        intent.setClassName(this.c, PersonalData.CLASS_NAME_PERSONAL_MEDAL_DETAIL);
        intent.putExtra("medal_res_id", String.valueOf(medalInfoDesc.acquireMedalId()));
        if (medalInfoDesc.acquireGainCount() > 0) {
            intent.putExtra("medal_des_id", String.valueOf(medalInfoDesc.acquireLightDescription()));
        } else {
            intent.putExtra("medal_des_id", String.valueOf(medalInfoDesc.acquireGrayDescription()));
        }
        intent.putExtra("medal_content_id", String.valueOf(medalInfoDesc.acquireText()));
        intent.putExtra("medal_type_level", String.valueOf(medalInfoDesc.acquireMedalTypeLevel()));
        intent.putExtra("medal_gain_time", String.valueOf(medalInfoDesc.acquireGainTime()));
        intent.putExtra("medal_gain_count", medalInfoDesc.acquireGainCount());
        intent.putExtra("click_x", mlu.f(this.c) / 2);
        intent.putExtra("click_y", mlu.j(this.c) / 2);
        intent.putExtra("medal_type", medalInfoDesc.acquireMedalType());
        if (medalInfoDesc.acquireGainCount() >= 1) {
            intent.putExtra("medal_obtain_id", "true");
        } else {
            intent.putExtra("medal_obtain_id", "false");
        }
        intent.putExtra("promotion_name", String.valueOf(medalInfoDesc.acquirePromotionName()));
        intent.putExtra("promotion_url", String.valueOf(medalInfoDesc.acquirePromotionURL()));
        this.c.startActivity(intent);
        if (!koq.c(this.e) || this.e.size() <= 1) {
            str = "";
        } else if (medalInfoDesc.acquireMedalType() != null && medalInfoDesc.acquireMedalType().length() < 3) {
            str = this.e.get(0);
        } else {
            str = this.e.get(1);
        }
        d(medalInfoDesc, str);
    }

    private void d(MedalInfoDesc medalInfoDesc, String str) {
        HashMap hashMap = new HashMap(8);
        hashMap.put("cilck", 1);
        hashMap.put("name", medalInfoDesc.acquireText());
        hashMap.put("className", str);
        hashMap.put("type", medalInfoDesc.acquireMedalType());
        hashMap.put("label", Integer.valueOf(medalInfoDesc.acquireMedalLabel()));
        hashMap.put("from", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_REPORT_1100010.value(), hashMap, 0);
    }

    private void clh_(View view, int i, int i2) {
        int i3 = mlu.i(view.getContext());
        view.setPadding(i3 - ((Integer) this.b.first).intValue(), 0, i3 - ((Integer) this.b.second).intValue(), 0);
        int b = i3 + (mlu.b(view.getContext()) / 2);
        int i4 = i == 0 ? b : 0;
        int i5 = i == i2 + (-1) ? b : 0;
        if (LanguageUtil.bc(view.getContext())) {
            cli_(view, i5, 0, i4, 0);
        } else {
            cli_(view, i4, 0, i5, 0);
        }
    }

    private void cli_(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams.leftMargin == i && marginLayoutParams.topMargin == i2 && marginLayoutParams.rightMargin == i3 && marginLayoutParams.bottomMargin == i4) {
            return;
        }
        marginLayoutParams.setMargins(i, i2, i3, i4);
        view.setLayoutParams(marginLayoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return this.d.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView d;

        public ViewHolder(View view) {
            super(view);
            this.d = (ImageView) view.findViewById(R.id.iv_big);
        }
    }
}
