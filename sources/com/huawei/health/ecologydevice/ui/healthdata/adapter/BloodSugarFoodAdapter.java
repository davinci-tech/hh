package com.huawei.health.ecologydevice.ui.healthdata.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dgk;
import defpackage.koq;
import defpackage.nrf;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class BloodSugarFoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<dgk> d = new ArrayList();
    private final Context e;

    public BloodSugarFoodAdapter(Context context) {
        this.e = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BloodSugarFoodViewHolder(LayoutInflater.from(this.e).inflate(R.layout.item_blood_sugar_food, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (!(viewHolder instanceof BloodSugarFoodViewHolder) || koq.b(this.d, i)) {
            LogUtil.b("BloodSugarFoodAdapter", "onBindViewHolder error!");
        } else {
            dgk dgkVar = this.d.get(i);
            ((BloodSugarFoodViewHolder) viewHolder).e(dgkVar.b(), dgkVar.d());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    public void a(List<dgk> list) {
        this.d.clear();
        this.d.addAll(list);
        LogUtil.c("BloodSugarFoodAdapter", "setUpData mSugarFoodReferences size = ", Integer.valueOf(this.d.size()));
        notifyDataSetChanged();
    }

    public static class BloodSugarFoodViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2319a;
        ImageView b;

        BloodSugarFoodViewHolder(View view) {
            super(view);
            this.b = (ImageView) view.findViewById(R.id.blood_sugar_food_picture);
            this.f2319a = (HealthTextView) view.findViewById(R.id.blood_sugar_food_name);
        }

        void e(String str, String str2) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                LogUtil.a("BloodSugarFoodAdapter", "setData foodName or foodImageUrl is null");
            } else {
                this.f2319a.setText(str);
                nrf.cIU_(str2, this.b, R.dimen._2131364629_res_0x7f0a0b15);
            }
        }
    }
}
