package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalAllShareInfoDesc;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.mfm;
import defpackage.mlb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class ShareMedalRecyclerAdapter extends RecyclerView.Adapter<a> {
    private ArrayList<MedalAllShareInfoDesc> c;
    private Context d;
    private Map<String, Integer> e;

    public ShareMedalRecyclerAdapter(Context context, ArrayList<MedalAllShareInfoDesc> arrayList) {
        HashMap hashMap = new HashMap(0);
        this.e = hashMap;
        this.d = context;
        this.c = arrayList;
        mlb.c(hashMap);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ciC_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(this.d).inflate(R.layout.share_medal_list_item, (ViewGroup) null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        int i2;
        ArrayList<MedalAllShareInfoDesc> arrayList = this.c;
        if (arrayList == null || aVar == null) {
            return;
        }
        if (i < 0 || i >= arrayList.size()) {
            LogUtil.a("PLGACHIEVE_ShareMedalRecyclerAdapter", "IndexOutOfBoundsException");
            return;
        }
        aVar.setIsRecyclable(true);
        MedalAllShareInfoDesc medalAllShareInfoDesc = this.c.get(i);
        String acquireMedalId = medalAllShareInfoDesc.acquireMedalId();
        aVar.b.setText(medalAllShareInfoDesc.acquireMedalText());
        Bitmap cko_ = mlb.cko_(acquireMedalId, true, true);
        if (cko_ != null) {
            aVar.e.setVisibility(0);
            aVar.e.setImageBitmap(cko_);
            return;
        }
        try {
            i2 = Integer.parseInt(acquireMedalId);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_ShareMedalRecyclerAdapter", "NumberFormatException");
            i2 = 0;
        }
        if (i2 > 0 && i2 <= 19) {
            int intValue = this.e.get(acquireMedalId).intValue();
            aVar.e.setVisibility(0);
            aVar.e.setImageResource(intValue);
        } else {
            aVar.f8433a.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            aVar.f8433a.setVisibility(0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<MedalAllShareInfoDesc> arrayList = this.c;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        LinearLayout f8433a;
        HealthTextView b;
        ImageView e;

        a(View view) {
            super(view);
            this.f8433a = (LinearLayout) mfm.cgM_(view, R.id.share_medal_list_item_ll);
            this.e = (ImageView) mfm.cgM_(view, R.id.share_medal_list_item_iv);
            this.b = (HealthTextView) mfm.cgM_(view, R.id.share_medal_list_item_tv);
        }
    }
}
