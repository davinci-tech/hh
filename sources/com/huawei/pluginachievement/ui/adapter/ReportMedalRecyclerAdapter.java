package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalInfoReport;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.mla;
import defpackage.mlb;
import java.io.File;
import java.util.List;

/* loaded from: classes8.dex */
public class ReportMedalRecyclerAdapter extends RecyclerView.Adapter<a> {

    /* renamed from: a, reason: collision with root package name */
    private Context f8432a;
    private List<MedalInfoReport> d;
    private LayoutInflater e;

    public ReportMedalRecyclerAdapter(Context context, List<MedalInfoReport> list) {
        this.f8432a = context;
        this.e = LayoutInflater.from(context);
        this.d = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ciB_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this.e.inflate(R.layout.item_single_medal, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        if (koq.b(this.d, i)) {
            LogUtil.h("PLGACHIEVE_ReportMedalRecyclerAdapter", "onBindViewHolder() mMedalList is empty.");
            return;
        }
        MedalInfoReport medalInfoReport = this.d.get(i);
        if (medalInfoReport == null) {
            LogUtil.h("PLGACHIEVE_ReportMedalRecyclerAdapter", "onBindViewHolder() medalInfoDesc is null.");
            return;
        }
        aVar.d.setText(medalInfoReport.getMedalName());
        String c = mlb.c(medalInfoReport.getMedalId(), ParsedFieldTag.LIGHT_LIST_STYLE);
        Bitmap ciA_ = ciA_(mlb.c(mlb.b(medalInfoReport.getMedalId())) + File.separator + c + ".png");
        if (ciA_ != null) {
            LogUtil.a("PLGACHIEVE_ReportMedalRecyclerAdapter", "bitmap not null");
            aVar.b.setImageBitmap(ciA_);
        } else {
            ciz_(medalInfoReport, aVar.b);
        }
    }

    private void ciz_(MedalInfoReport medalInfoReport, ImageView imageView) {
        Bitmap cko_ = mlb.cko_(medalInfoReport.getMedalId(), true, false);
        if (cko_ != null) {
            LogUtil.a("PLGACHIEVE_ReportMedalRecyclerAdapter", "bmp not null");
            imageView.setImageBitmap(cko_);
            return;
        }
        String medalTypeLevel = medalInfoReport.getMedalTypeLevel();
        LogUtil.a("PLGACHIEVE_ReportMedalRecyclerAdapter", "typeLevel is ", medalTypeLevel);
        MedalInfo medalInfo = mla.e().c(false).get(medalTypeLevel);
        if (medalInfo != null) {
            imageView.setImageResource(medalInfo.getEnableResId());
        } else {
            LogUtil.h("PLGACHIEVE_ReportMedalRecyclerAdapter", "medalInfoReport.getMedalId() medalInfo is null.");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return Math.min(this.d.size(), 6);
    }

    public void a(List<MedalInfoReport> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_ReportMedalRecyclerAdapter", "onBindViewHolder() mMedalList is empty.");
        } else if (koq.c(this.d)) {
            this.d.clear();
            this.d.addAll(list);
            notifyDataSetChanged();
        }
    }

    public Bitmap ciA_(String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_ReportMedalRecyclerAdapter", "loadImage: untrusted -> " + str);
            return null;
        }
        if (!new File(str).exists()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("PLGACHIEVE_ReportMedalRecyclerAdapter", "loadImage:OutOfMemoryError");
            return null;
        }
    }

    static class a extends RecyclerView.ViewHolder {
        ImageView b;
        HealthTextView d;

        a(View view) {
            super(view);
            this.d = (HealthTextView) view.findViewById(R.id.medal_title_text);
            this.b = (ImageView) view.findViewById(R.id.imageview_medal);
        }
    }
}
