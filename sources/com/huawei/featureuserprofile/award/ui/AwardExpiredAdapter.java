package com.huawei.featureuserprofile.award.ui;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.AwardRecordsBean;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.brk;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsj;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class AwardExpiredAdapter extends RecyclerView.Adapter<a> {
    private List<AwardRecordsBean> b;
    private LayoutInflater d;

    AwardExpiredAdapter(Context context) {
        if (context != null) {
            if (context instanceof Activity) {
                this.d = ((Activity) context).getLayoutInflater();
                return;
            } else {
                this.d = LayoutInflater.from(context);
                return;
            }
        }
        LogUtil.b("AwardExpiredAdapter", "constructor context is null");
    }

    void a(List<AwardRecordsBean> list) {
        List<AwardRecordsBean> list2 = this.b;
        if (list2 == null) {
            this.b = new ArrayList();
        } else {
            list2.clear();
        }
        this.b.addAll(list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: tE_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this.d.inflate(R.layout.item_my_award_expired, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        if (koq.b(this.b, i)) {
            LogUtil.h("AwardExpiredAdapter", "mAwardRecordsBeanList isOutOfBounds");
            return;
        }
        AwardRecordsBean awardRecordsBean = this.b.get(i);
        f(aVar, awardRecordsBean);
        if ("1".equals(awardRecordsBean.getAwardType())) {
            h(aVar, awardRecordsBean);
            a(aVar, awardRecordsBean);
        } else if ("4".equals(awardRecordsBean.getAwardType())) {
            h(aVar, awardRecordsBean);
            aVar.f1970a.setVisibility(8);
        } else {
            i(aVar, awardRecordsBean);
            b(aVar, awardRecordsBean);
        }
    }

    private void b(a aVar, AwardRecordsBean awardRecordsBean) {
        if ("1".equals(awardRecordsBean.getTicketType())) {
            a(aVar, awardRecordsBean);
        } else {
            aVar.f1970a.setVisibility(8);
        }
    }

    private void f(a aVar, AwardRecordsBean awardRecordsBean) {
        g(aVar, awardRecordsBean);
        e(aVar, awardRecordsBean);
        d(aVar, awardRecordsBean);
        c(aVar, awardRecordsBean);
        j(aVar, awardRecordsBean);
        m(aVar, awardRecordsBean);
    }

    private void g(a aVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getPictures() == null || TextUtils.isEmpty(awardRecordsBean.getPictures().getDefaultImg())) {
            return;
        }
        nrf.cIv_(awardRecordsBean.getPictures().getMiddle(), RequestOptions.bitmapTransform(new RoundedCorners(8)).centerInside(), aVar.g);
    }

    private void e(a aVar, AwardRecordsBean awardRecordsBean) {
        if (!TextUtils.isEmpty(awardRecordsBean.getAwardName())) {
            aVar.h.setText(awardRecordsBean.getAwardName());
            aVar.h.setVisibility(0);
        } else {
            LogUtil.b("AwardExpiredAdapter", "expired award name is empty");
            aVar.h.setVisibility(8);
        }
    }

    private void d(a aVar, AwardRecordsBean awardRecordsBean) {
        if (!TextUtils.isEmpty(awardRecordsBean.getActivityName())) {
            if (awardRecordsBean.getSource() == 1) {
                aVar.d.setText(BaseApplication.getContext().getString(R.string._2130838142_res_0x7f02027e, awardRecordsBean.getActivityName()));
                aVar.d.setVisibility(0);
                return;
            } else {
                aVar.d.setVisibility(8);
                return;
            }
        }
        if (awardRecordsBean.getSource() == 2) {
            aVar.d.setVisibility(0);
            if (awardRecordsBean.getSourceDetail() == 20001) {
                aVar.d.setText(BaseApplication.getContext().getString(R.string._2130838142_res_0x7f02027e, BaseApplication.getContext().getString(R.string._2130841920_res_0x7f021140)));
                return;
            } else {
                aVar.d.setText(BaseApplication.getContext().getString(R.string._2130838142_res_0x7f02027e, BaseApplication.getContext().getString(R.string._2130840877_res_0x7f020d2d)));
                return;
            }
        }
        aVar.d.setVisibility(8);
    }

    private void h(a aVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getWonTime() > 0 && awardRecordsBean.getExpireTime() > 0) {
            aVar.j.setText(BaseApplication.getContext().getString(R.string._2130838143_res_0x7f02027f, brk.e(awardRecordsBean.getWonTime()), brk.e(awardRecordsBean.getExpireTime())));
            aVar.j.setVisibility(0);
        } else {
            aVar.j.setVisibility(8);
        }
    }

    private void i(a aVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getEffectiveStartTime() > 0 && awardRecordsBean.getEffectiveEndTime() > 0) {
            aVar.j.setText(BaseApplication.getContext().getString(R.string._2130838143_res_0x7f02027f, brk.e(awardRecordsBean.getEffectiveStartTime()), brk.e(awardRecordsBean.getEffectiveEndTime())));
            aVar.j.setVisibility(0);
        } else {
            aVar.j.setVisibility(8);
        }
    }

    private void c(final a aVar, final AwardRecordsBean awardRecordsBean) {
        aVar.c.setVisibility(0);
        aVar.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.award.ui.AwardExpiredAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (awardRecordsBean.isExpand()) {
                    aVar.b.setVisibility(8);
                    aVar.c.setImageResource(R.drawable._2131429713_res_0x7f0b0951);
                    awardRecordsBean.setExpand(false);
                } else {
                    aVar.b.setVisibility(0);
                    aVar.c.setImageResource(R.drawable._2131429719_res_0x7f0b0957);
                    awardRecordsBean.setExpand(true);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a(a aVar, AwardRecordsBean awardRecordsBean) {
        if (!TextUtils.isEmpty(awardRecordsBean.getExchangeCode())) {
            aVar.f1970a.setText(BaseApplication.getContext().getString(R.string._2130838144_res_0x7f020280, awardRecordsBean.getExchangeCode()));
            aVar.f1970a.setVisibility(0);
        } else {
            aVar.f1970a.setVisibility(8);
        }
    }

    private void j(a aVar, AwardRecordsBean awardRecordsBean) {
        if (!TextUtils.isEmpty(awardRecordsBean.getDescription())) {
            aVar.e.setText(awardRecordsBean.getDescription());
            aVar.e.setVisibility(0);
        } else {
            aVar.e.setVisibility(8);
        }
    }

    private void m(a aVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getWonTime() > 0) {
            aVar.i.setText(BaseApplication.getContext().getString(R.string._2130838145_res_0x7f020281, nsj.a(awardRecordsBean.getWonTime())));
            aVar.i.setVisibility(0);
        } else {
            aVar.i.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AwardRecordsBean> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f1970a;
        LinearLayout b;
        ImageView c;
        HealthTextView d;
        HealthTextView e;
        ImageView g;
        HealthTextView h;
        HealthTextView i;
        HealthTextView j;

        a(View view) {
            super(view);
            this.g = (ImageView) view.findViewById(R.id.my_award_icon);
            this.h = (HealthTextView) view.findViewById(R.id.my_award_title);
            this.d = (HealthTextView) view.findViewById(R.id.my_award_activity_name);
            this.j = (HealthTextView) view.findViewById(R.id.my_award_valid_date);
            this.c = (ImageView) view.findViewById(R.id.my_award_arrow);
            this.b = (LinearLayout) view.findViewById(R.id.expand_container);
            this.f1970a = (HealthTextView) view.findViewById(R.id.my_award_code);
            this.e = (HealthTextView) view.findViewById(R.id.my_award_description);
            this.i = (HealthTextView) view.findViewById(R.id.my_award_won_time);
        }
    }
}
