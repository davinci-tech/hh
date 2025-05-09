package com.huawei.ui.main.stories.history.adapter;

import android.content.res.Resources;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import defpackage.rds;
import defpackage.rdy;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class SearchTypeAdapter extends BaseRecyclerAdapter<rdy> {

    /* renamed from: a, reason: collision with root package name */
    private int f10299a;
    private int b;
    private String e;

    public SearchTypeAdapter(List<rdy> list) {
        super(list, R.layout.item_search_motion_type_info);
        this.b = BaseApplication.getContext().getResources().getColor(R.color._2131296651_res_0x7f09018b);
        this.f10299a = BaseApplication.getContext().getResources().getColor(R.color._2131299386_res_0x7f090c3a);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, rdy rdyVar) {
        if (rdyVar != null) {
            d(recyclerHolder, rdyVar);
            e(recyclerHolder, i);
        }
    }

    private void d(RecyclerHolder recyclerHolder, rdy rdyVar) {
        if (!TextUtils.isEmpty(this.e)) {
            recyclerHolder.c(R.id.select_sport_content, dJW_(this.b, rds.c(rdyVar), this.e));
        }
        int i = rdyVar.g() ? R.drawable._2131430888_res_0x7f0b0de8 : R.drawable._2131430887_res_0x7f0b0de7;
        View cwK_ = recyclerHolder.cwK_(R.id.select_sport_motion_item_layout);
        if (cwK_ instanceof LinearLayout) {
            ((LinearLayout) cwK_).setBackgroundResource(i);
        }
        b(recyclerHolder, rdyVar);
    }

    private void e(RecyclerHolder recyclerHolder, int i) {
        if (i + 1 >= getItemCount()) {
            recyclerHolder.a(R.id.select_sport_summary_line, 8);
        } else {
            recyclerHolder.a(R.id.select_sport_summary_line, 0);
        }
    }

    private void b(RecyclerHolder recyclerHolder, rdy rdyVar) {
        Resources resources = recyclerHolder.itemView.getResources();
        View cwK_ = recyclerHolder.cwK_(R.id.select_sport_icon);
        if (cwK_ == null || resources == null) {
            LogUtil.h("Track_SearchTypeAdapter", "view or resource is null");
            return;
        }
        if (!(cwK_ instanceof ImageView)) {
            LogUtil.h("Track_SearchTypeAdapter", "view is not instanceof ImageView");
            return;
        }
        ((ImageView) cwK_).setPadding(4, 4, 4, 4);
        if (rdyVar.g()) {
            recyclerHolder.cwN_(R.id.select_sport_icon, rds.dMx_(rdyVar.dMA_(), this.f10299a));
        } else {
            recyclerHolder.cwN_(R.id.select_sport_icon, rds.dMx_(rdyVar.dMA_(), rds.d()));
        }
    }

    public void a(String str) {
        this.e = str;
    }

    private SpannableString dJW_(int i, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("Track_SearchTypeAdapter", "deviceText or searchKey is empty");
            return new SpannableString("");
        }
        String replaceAll = str2.toLowerCase(Locale.ENGLISH).replaceAll("\\s*", "");
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        SpannableString spannableString = new SpannableString(str);
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            if (length < i3) {
                break;
            }
            String substring = lowerCase.substring(i2, i3);
            if (!TextUtils.isEmpty(substring) && replaceAll.contains(substring)) {
                spannableString.setSpan(new ForegroundColorSpan(i), i2, i3, 33);
            }
            i2 = i3;
        }
        return spannableString;
    }
}
