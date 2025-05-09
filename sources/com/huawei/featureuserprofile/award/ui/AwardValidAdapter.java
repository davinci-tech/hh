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
import com.huawei.featureuserprofile.award.listener.OnClickAddressButtonListener;
import com.huawei.featureuserprofile.award.listener.OnClickCopyListener;
import com.huawei.featureuserprofile.award.model.AddressInfo;
import com.huawei.featureuserprofile.award.model.SaveInfoItem;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.AwardRecordsBean;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.brk;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsj;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class AwardValidAdapter extends RecyclerView.Adapter<d> {

    /* renamed from: a, reason: collision with root package name */
    private OnClickAddressButtonListener f1972a;
    private List<AwardRecordsBean> b;
    private OnClickCopyListener c;
    private Context d;
    private LayoutInflater e;

    AwardValidAdapter(Context context) {
        if (context != null) {
            this.d = context;
            if (context instanceof Activity) {
                this.e = ((Activity) context).getLayoutInflater();
                return;
            } else {
                this.e = LayoutInflater.from(context);
                return;
            }
        }
        LogUtil.b("AwardValidAdapter", "constructor context is null");
    }

    void a(OnClickAddressButtonListener onClickAddressButtonListener) {
        this.f1972a = onClickAddressButtonListener;
    }

    void a(OnClickCopyListener onClickCopyListener) {
        this.c = onClickCopyListener;
    }

    void e(List<AwardRecordsBean> list) {
        List<AwardRecordsBean> list2 = this.b;
        if (list2 == null) {
            this.b = new ArrayList();
        } else {
            list2.clear();
        }
        this.b.addAll(list);
    }

    List<AwardRecordsBean> d() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AwardRecordsBean> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: tF_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(this.e.inflate(R.layout.item_my_award, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
        if (koq.b(this.b, i)) {
            LogUtil.h("AwardValidAdapter", "mAwardRecordsBeanList isOutOfBounds");
            return;
        }
        AwardRecordsBean awardRecordsBean = this.b.get(i);
        j(dVar, awardRecordsBean);
        if ("1".equals(awardRecordsBean.getAwardType())) {
            q(dVar, awardRecordsBean);
            l(dVar, awardRecordsBean);
            h(dVar, awardRecordsBean);
            g(dVar, awardRecordsBean);
            return;
        }
        if ("4".equals(awardRecordsBean.getAwardType())) {
            r(dVar, awardRecordsBean);
            return;
        }
        n(dVar, awardRecordsBean);
        if ("1".equals(awardRecordsBean.getTicketType())) {
            k(dVar, awardRecordsBean);
            h(dVar, awardRecordsBean);
            g(dVar, awardRecordsBean);
            return;
        }
        f(dVar, awardRecordsBean);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void r(d dVar, final AwardRecordsBean awardRecordsBean) {
        char c;
        q(dVar, awardRecordsBean);
        dVar.d.setVisibility(8);
        dVar.e.setVisibility(8);
        String exchangeStatus = awardRecordsBean.getExchangeStatus();
        LogUtil.a("AwardValidAdapter", "setVaMallGood exchangeStatus: ", exchangeStatus);
        if (TextUtils.isEmpty(exchangeStatus)) {
            dVar.f1975a.setVisibility(4);
            dVar.f1975a.setEnabled(false);
            return;
        }
        dVar.f1975a.setVisibility(0);
        exchangeStatus.hashCode();
        switch (exchangeStatus.hashCode()) {
            case -1309235419:
                if (exchangeStatus.equals("expired")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 180654607:
                if (exchangeStatus.equals("not_exchange")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1254744472:
                if (exchangeStatus.equals("is_exchange")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2082219389:
                if (exchangeStatus.equals("is_send")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            dVar.f1975a.setText(R.string._2130846693_res_0x7f0223e5);
            dVar.f1975a.setEnabled(false);
            return;
        }
        if (c == 1) {
            dVar.f1975a.setText(R.string._2130846715_res_0x7f0223fb);
            dVar.f1975a.setEnabled(true);
        } else if (c == 2) {
            dVar.f1975a.setText(R.string._2130845204_res_0x7f021e14);
            dVar.f1975a.setEnabled(true);
        } else if (c == 3) {
            dVar.f1975a.setText(R.string._2130846679_res_0x7f0223d7);
            dVar.f1975a.setEnabled(true);
        } else {
            dVar.f1975a.setVisibility(4);
            dVar.f1975a.setEnabled(false);
            return;
        }
        if (TextUtils.isEmpty(awardRecordsBean.getDetailUrl())) {
            LogUtil.h("AwardValidAdapter", "detailUrl isEmpty");
        } else {
            dVar.f1975a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.award.ui.AwardValidAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (AwardValidAdapter.this.f1972a != null) {
                        AwardValidAdapter.this.f1972a.onClickLinkButton(awardRecordsBean.getDetailUrl(), awardRecordsBean.getExchangeCode());
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void f(d dVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getSource() == 2 && awardRecordsBean.getSourceDetail() == 20002) {
            k(dVar, awardRecordsBean);
        } else {
            dVar.f1975a.setVisibility(4);
        }
        dVar.d.setVisibility(8);
        dVar.e.setVisibility(8);
    }

    private void j(d dVar, AwardRecordsBean awardRecordsBean) {
        m(dVar, awardRecordsBean);
        i(dVar, awardRecordsBean);
        e(dVar, awardRecordsBean);
        c(dVar, awardRecordsBean);
        o(dVar, awardRecordsBean);
        t(dVar, awardRecordsBean);
    }

    private void m(d dVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getPictures() == null || TextUtils.isEmpty(awardRecordsBean.getPictures().getDefaultImg())) {
            return;
        }
        nrf.cIv_(awardRecordsBean.getPictures().getMiddle(), RequestOptions.bitmapTransform(new RoundedCorners(8)).centerInside(), dVar.i);
    }

    private void i(d dVar, AwardRecordsBean awardRecordsBean) {
        if (!TextUtils.isEmpty(awardRecordsBean.getAwardName())) {
            dVar.f.setText(awardRecordsBean.getAwardName());
            dVar.f.setVisibility(0);
        } else {
            dVar.f.setVisibility(8);
        }
    }

    private void l(d dVar, AwardRecordsBean awardRecordsBean) {
        if (TextUtils.isEmpty(awardRecordsBean.getExchangeStatus())) {
            dVar.f1975a.setVisibility(4);
            return;
        }
        if ("is_exchange".equalsIgnoreCase(awardRecordsBean.getExchangeStatus())) {
            d(dVar, awardRecordsBean);
            return;
        }
        if ("is_send".equalsIgnoreCase(awardRecordsBean.getExchangeStatus())) {
            dVar.f1975a.setEnabled(false);
            dVar.f1975a.setText(R.string._2130838146_res_0x7f020282);
            dVar.f1975a.setVisibility(0);
        } else if ("not_exchange".equalsIgnoreCase(awardRecordsBean.getExchangeStatus())) {
            b(dVar, awardRecordsBean);
        } else {
            dVar.f1975a.setVisibility(4);
            LogUtil.b("AwardValidAdapter", "award exchange status error: ", awardRecordsBean.getExchangeStatus());
        }
    }

    private void d(final d dVar, final AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getExpireTime() > System.currentTimeMillis()) {
            dVar.f1975a.setEnabled(true);
            dVar.f1975a.setText(R.string._2130838152_res_0x7f020288);
            dVar.f1975a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.award.ui.AwardValidAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AwardValidAdapter.this.a(dVar, awardRecordsBean);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            dVar.f1975a.setEnabled(false);
            dVar.f1975a.setText(R.string._2130838153_res_0x7f020289);
        }
        dVar.f1975a.setVisibility(0);
    }

    private void b(final d dVar, final AwardRecordsBean awardRecordsBean) {
        dVar.f1975a.setEnabled(true);
        if (awardRecordsBean.getMailling() != null && !TextUtils.isEmpty(awardRecordsBean.getMailling().getName())) {
            dVar.f1975a.setText(R.string._2130838152_res_0x7f020288);
        } else {
            dVar.f1975a.setText(R.string._2130838151_res_0x7f020287);
        }
        dVar.f1975a.setVisibility(0);
        dVar.f1975a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.award.ui.AwardValidAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AwardValidAdapter.this.a(dVar, awardRecordsBean);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(d dVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getMailling() != null && !TextUtils.isEmpty(awardRecordsBean.getMailling().getName())) {
            if (this.f1972a == null) {
                LogUtil.h("AwardValidAdapter", "mOnClickAddressButtonListener = null");
                return;
            }
            SaveInfoItem saveInfoItem = new SaveInfoItem();
            if (awardRecordsBean.getSource() == 1) {
                saveInfoItem.setActivityId(awardRecordsBean.getActivityId());
            } else {
                saveInfoItem.setActivityId(awardRecordsBean.getActivityCode());
            }
            saveInfoItem.setAwardRecordId(awardRecordsBean.getAwardId());
            saveInfoItem.setName(awardRecordsBean.getMailling().getName());
            saveInfoItem.setTelephone(awardRecordsBean.getMailling().getTelephone());
            saveInfoItem.setAddress(awardRecordsBean.getMailling().getAddress());
            saveInfoItem.setRemark(awardRecordsBean.getMailling().getRemark());
            this.f1972a.onClickAddressButton(new AddressInfo(awardRecordsBean.getSource() == 1 ? awardRecordsBean.getSource() : awardRecordsBean.getSourceDetail(), saveInfoItem, dVar.getAdapterPosition()));
            return;
        }
        if (this.f1972a == null) {
            LogUtil.h("AwardValidAdapter", "mOnClickAddressButtonListener = null");
            return;
        }
        SaveInfoItem saveInfoItem2 = new SaveInfoItem();
        if (awardRecordsBean.getSource() == 1) {
            saveInfoItem2.setActivityId(awardRecordsBean.getActivityId());
        } else {
            saveInfoItem2.setActivityId(awardRecordsBean.getActivityCode());
        }
        saveInfoItem2.setAwardRecordId(awardRecordsBean.getAwardId());
        saveInfoItem2.setName("");
        saveInfoItem2.setTelephone("");
        saveInfoItem2.setAddress("");
        saveInfoItem2.setRemark("");
        this.f1972a.onClickAddressButton(new AddressInfo(awardRecordsBean.getSource() == 1 ? awardRecordsBean.getSource() : awardRecordsBean.getSourceDetail(), saveInfoItem2, dVar.getAdapterPosition()));
    }

    private void e(d dVar, AwardRecordsBean awardRecordsBean) {
        if (TextUtils.isEmpty(awardRecordsBean.getActivityName())) {
            if (awardRecordsBean.getSource() != 2) {
                dVar.c.setVisibility(8);
                return;
            }
            dVar.c.setVisibility(0);
            if (awardRecordsBean.getSourceDetail() == 20001) {
                dVar.c.setText(BaseApplication.getContext().getString(R.string._2130838142_res_0x7f02027e, BaseApplication.getContext().getString(R.string._2130841920_res_0x7f021140)));
                return;
            } else {
                dVar.c.setText(BaseApplication.getContext().getString(R.string._2130838142_res_0x7f02027e, BaseApplication.getContext().getString(R.string._2130840877_res_0x7f020d2d)));
                return;
            }
        }
        if (awardRecordsBean.getSource() == 1) {
            dVar.c.setText(BaseApplication.getContext().getString(R.string._2130838142_res_0x7f02027e, awardRecordsBean.getActivityName()));
            dVar.c.setVisibility(0);
        } else {
            dVar.c.setVisibility(8);
        }
    }

    private void q(d dVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getWonTime() > 0 && awardRecordsBean.getExpireTime() > 0) {
            dVar.h.setText(BaseApplication.getContext().getString(R.string._2130838143_res_0x7f02027f, brk.e(awardRecordsBean.getWonTime()), brk.e(awardRecordsBean.getExpireTime())));
            dVar.h.setVisibility(0);
        } else {
            dVar.h.setVisibility(8);
        }
    }

    private void n(d dVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getEffectiveStartTime() > 0 && awardRecordsBean.getEffectiveEndTime() > 0) {
            dVar.h.setText(BaseApplication.getContext().getString(R.string._2130838143_res_0x7f02027f, brk.e(awardRecordsBean.getEffectiveStartTime()), brk.e(awardRecordsBean.getEffectiveEndTime())));
            dVar.h.setVisibility(0);
        } else {
            dVar.h.setVisibility(8);
        }
    }

    private void c(final d dVar, final AwardRecordsBean awardRecordsBean) {
        dVar.b.setVisibility(0);
        dVar.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.award.ui.AwardValidAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (awardRecordsBean.isExpand()) {
                    dVar.j.setVisibility(8);
                    dVar.b.setImageResource(R.drawable._2131429713_res_0x7f0b0951);
                    ((AwardRecordsBean) AwardValidAdapter.this.b.get(dVar.getAdapterPosition())).setExpand(false);
                } else {
                    dVar.j.setVisibility(0);
                    dVar.b.setImageResource(R.drawable._2131429719_res_0x7f0b0957);
                    ((AwardRecordsBean) AwardValidAdapter.this.b.get(dVar.getAdapterPosition())).setExpand(true);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void h(d dVar, AwardRecordsBean awardRecordsBean) {
        if (!TextUtils.isEmpty(awardRecordsBean.getExchangeCode())) {
            dVar.d.setText(BaseApplication.getContext().getString(R.string._2130838144_res_0x7f020280, awardRecordsBean.getExchangeCode()));
            dVar.d.setVisibility(0);
        } else {
            dVar.d.setVisibility(8);
        }
    }

    private void g(d dVar, final AwardRecordsBean awardRecordsBean) {
        if (!TextUtils.isEmpty(awardRecordsBean.getExchangeCode())) {
            dVar.e.setVisibility(0);
            dVar.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.award.ui.AwardValidAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (AwardValidAdapter.this.c != null) {
                        AwardValidAdapter.this.c.onClickCopy(awardRecordsBean.getExchangeCode());
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            dVar.e.setVisibility(8);
        }
    }

    private void o(d dVar, AwardRecordsBean awardRecordsBean) {
        if (!TextUtils.isEmpty(awardRecordsBean.getDescription())) {
            dVar.g.setText(awardRecordsBean.getDescription());
            dVar.g.setVisibility(0);
        } else {
            dVar.g.setVisibility(8);
        }
    }

    private void t(d dVar, AwardRecordsBean awardRecordsBean) {
        if (awardRecordsBean.getWonTime() > 0) {
            dVar.l.setText(BaseApplication.getContext().getString(R.string._2130838145_res_0x7f020281, nsj.a(awardRecordsBean.getWonTime())));
            dVar.l.setVisibility(0);
        } else {
            dVar.l.setVisibility(8);
        }
    }

    private void k(d dVar, final AwardRecordsBean awardRecordsBean) {
        if (!TextUtils.isEmpty(awardRecordsBean.getDetailUrl())) {
            dVar.f1975a.setVisibility(0);
            dVar.f1975a.setEnabled(true);
            if (!TextUtils.isEmpty(awardRecordsBean.getExchangeCode()) && LanguageUtil.h(this.d)) {
                dVar.f1975a.setText(R.string._2130849499_res_0x7f022edb);
            } else {
                dVar.f1975a.setText(R.string._2130838150_res_0x7f020286);
            }
            dVar.f1975a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.award.ui.AwardValidAdapter.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (AwardValidAdapter.this.f1972a != null) {
                        AwardValidAdapter.this.f1972a.onClickLinkButton(awardRecordsBean.getDetailUrl(), awardRecordsBean.getExchangeCode());
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            return;
        }
        dVar.f1975a.setVisibility(4);
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthButton f1975a;
        ImageView b;
        HealthTextView c;
        HealthTextView d;
        HealthTextView e;
        HealthTextView f;
        HealthTextView g;
        HealthTextView h;
        ImageView i;
        LinearLayout j;
        HealthTextView l;

        d(View view) {
            super(view);
            this.i = (ImageView) view.findViewById(R.id.my_award_icon);
            this.f = (HealthTextView) view.findViewById(R.id.my_award_title);
            this.c = (HealthTextView) view.findViewById(R.id.my_award_activity_name);
            this.f1975a = (HealthButton) view.findViewById(R.id.my_award_button);
            this.h = (HealthTextView) view.findViewById(R.id.my_award_valid_date);
            this.b = (ImageView) view.findViewById(R.id.my_award_arrow);
            this.j = (LinearLayout) view.findViewById(R.id.expand_container);
            this.d = (HealthTextView) view.findViewById(R.id.my_award_code);
            this.e = (HealthTextView) view.findViewById(R.id.my_award_copy);
            this.g = (HealthTextView) view.findViewById(R.id.my_award_description);
            this.l = (HealthTextView) view.findViewById(R.id.my_award_won_time);
        }
    }
}
