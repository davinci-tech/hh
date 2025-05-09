package com.huawei.phoneservice.feedback.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.phoneservice.faq.base.util.k;
import com.huawei.phoneservice.faq.base.util.o;
import com.huawei.phoneservice.faq.base.widget.ListItemRelativeLayout;
import com.huawei.phoneservice.faq.base.widget.SimpleBaseAdapter;
import com.huawei.phoneservice.feedback.widget.ItemView;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import com.huawei.uikit.hwresources.R$dimen;

/* loaded from: classes5.dex */
public class a extends SimpleBaseAdapter<FeedBackResponse.ProblemEnity> {
    @Override // com.huawei.phoneservice.faq.base.widget.SimpleBaseAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        e eVar;
        if (view == null) {
            eVar = new e();
            view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedback_sdk_item_feedlist, viewGroup, false);
            eVar.f8253a = (TextView) view2.findViewById(R.id.feed_title);
            eVar.c = (TextView) view2.findViewById(R.id.feed_time);
            eVar.d = (TextView) view2.findViewById(R.id.feed_content);
            eVar.b = (ImageView) view2.findViewById(R.id.iv_question_state);
            eVar.e = view2.findViewById(R.id.feedback_split_line);
            eVar.f = (ListItemRelativeLayout) view2.findViewById(R.id.feed_item_rl);
            eVar.g = (ItemView) view2.findViewById(R.id.item_view);
            view2.setTag(eVar);
        } else {
            view2 = view;
            eVar = (e) view.getTag();
        }
        FeedBackResponse.ProblemEnity problemEnity = (FeedBackResponse.ProblemEnity) getItem(i);
        if (problemEnity != null) {
            eVar.f8253a.setText(problemEnity.getProblemDesc());
            if (com.huawei.phoneservice.feedback.photolibrary.internal.utils.c.d(problemEnity.getCreateTime(), view2.getContext())) {
                eVar.c.setText(com.huawei.phoneservice.feedback.photolibrary.internal.utils.c.a(problemEnity.getCreateTime(), "HH:mm", view2.getContext()));
            } else {
                eVar.c.setText(k.a(com.huawei.phoneservice.feedback.photolibrary.internal.utils.c.a(problemEnity.getCreateTime(), "yyyy-MM-dd HH:mm", view2.getContext()), view2.getContext()).replace(Constants.LINK, "/"));
            }
            if (TextUtils.isEmpty(problemEnity.getAnswer())) {
                eVar.d.setSingleLine(false);
                eVar.d.setText(viewGroup.getContext().getResources().getString(R.string._2130850916_res_0x7f023464));
            } else {
                eVar.d.setText(viewGroup.getContext().getString(R.string._2130850917_res_0x7f023465, problemEnity.getAnswer()));
                eVar.d.setSingleLine(true);
            }
            eVar.b.setVisibility(problemEnity.getIsRead() ? 4 : 0);
        }
        a(viewGroup, eVar);
        eVar.e.setVisibility(i == getCount() - 1 ? 4 : 0);
        return view2;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        TextView f8253a;
        ImageView b;
        TextView c;
        TextView d;
        View e;
        ListItemRelativeLayout f;
        ItemView g;

        e() {
        }
    }

    private void a(ViewGroup viewGroup, e eVar) {
        Context context = viewGroup.getContext();
        if (context != null) {
            o.cdm_(eVar.f, 0, context.getResources().getDimensionPixelOffset(R$dimen.emui_dimens_max_end));
            o.cdm_(eVar.e, context.getResources().getDimensionPixelOffset(R$dimen.emui_dimens_max_start), 0);
            o.cdn_(eVar.g, context.getResources().getDimensionPixelOffset(R$dimen.emui_dimens_max_start));
        }
    }
}
