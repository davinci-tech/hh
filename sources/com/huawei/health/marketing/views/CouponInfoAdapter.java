package com.huawei.health.marketing.views;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.Coupon;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.njn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class CouponInfoAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context b;
    private List<Coupon> c;

    public CouponInfoAdapter(Context context, List<Coupon> list) {
        new ArrayList();
        this.b = context;
        this.c = list;
    }

    public void c(List<Coupon> list) {
        this.c = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aoT_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_coupon_info, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (koq.b(this.c, i)) {
            LogUtil.h("TradeOrderAdapter", "mCurrentPlansList is null");
        } else {
            d(viewHolder, this.c.get(i));
        }
    }

    private void d(ViewHolder viewHolder, Coupon coupon) {
        String e;
        String format;
        LogUtil.a("TradeOrderAdapter", "enter showView", Integer.valueOf(coupon.getFeeMode()));
        if (coupon.getFeeMode() == 2) {
            try {
                if (LanguageUtil.ak(this.b)) {
                    e = c(Double.valueOf(coupon.getDiscount()).doubleValue() * 10.0d);
                    format = String.format(Locale.ROOT, this.b.getString(R$string.IDS_discount), e);
                } else {
                    e = UnitUtil.e((1.0d - Double.valueOf(coupon.getDiscount()).doubleValue()) * 100.0d, 2, 0);
                    format = String.format(Locale.ROOT, this.b.getString(R$string.IDS_discount), e);
                }
                a(format, e, viewHolder.d);
            } catch (NumberFormatException unused) {
                LogUtil.b("TradeOrderAdapter", "DISCOUNT_COUPON NumberFormatException");
            }
        } else if (coupon.getFeeMode() == 1) {
            String e2 = njn.e(coupon.getCurrency());
            try {
                String c = c(Long.parseLong(coupon.getSubFee()) / 100.0f);
                if (LanguageUtil.as(this.b)) {
                    c = njn.b(c);
                }
                a(String.format(Locale.ROOT, this.b.getString(R$string.IDS_currency_money), e2, c), c, viewHolder.d);
            } catch (NumberFormatException unused2) {
                LogUtil.b("TradeOrderAdapter", "FULL_DISCOUNT_COUPON NumberFormatException");
            }
        } else {
            String e3 = njn.e(coupon.getCurrency());
            try {
                String c2 = c(Long.parseLong(coupon.getFaceValue()) / 100.0f);
                if (LanguageUtil.as(this.b)) {
                    c2 = njn.b(c2);
                }
                a(String.format(Locale.ROOT, this.b.getString(R$string.IDS_currency_money), e3, c2), c2, viewHolder.d);
            } catch (NumberFormatException unused3) {
                LogUtil.b("TradeOrderAdapter", "NumberFormatException");
            }
        }
        viewHolder.c.setText(coupon.getCouponName());
        if (TextUtils.isEmpty(coupon.getCouponSubTitle())) {
            viewHolder.b.setVisibility(8);
        } else {
            viewHolder.b.setText(coupon.getCouponSubTitle());
            viewHolder.b.setVisibility(0);
        }
        a(coupon, viewHolder);
    }

    private void a(Coupon coupon, ViewHolder viewHolder) {
        if (coupon.isOutOfStockFlag()) {
            LogUtil.h("TradeOrderAdapter", "limit:", coupon.getCouponId());
            viewHolder.f2855a.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_limit_status));
        } else {
            viewHolder.f2855a.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_received));
        }
    }

    private String c(double d) {
        if (Math.round(d) - d == 0.0d) {
            return UnitUtil.e(d, 1, 0);
        }
        return UnitUtil.e(d, 1, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2855a;
        private HealthTextView b;
        private HealthTextView c;
        private HealthTextView d;

        ViewHolder(View view) {
            super(view);
            this.d = (HealthTextView) view.findViewById(R.id.tv_money);
            this.c = (HealthTextView) view.findViewById(R.id.tv_title);
            this.b = (HealthTextView) view.findViewById(R.id.tv_detail);
            this.f2855a = (HealthTextView) view.findViewById(R.id.tv_status);
        }
    }

    private void a(String str, String str2, HealthTextView healthTextView) {
        if (TextUtils.isEmpty(str) || healthTextView == null) {
            return;
        }
        Context e = com.huawei.haf.application.BaseApplication.e();
        SpannableString spannableString = new SpannableString(str);
        int indexOf = spannableString.toString().indexOf(str2);
        if (indexOf != -1) {
            spannableString.setSpan(new AbsoluteSizeSpan(e.getResources().getDimensionPixelSize(R.dimen._2131362720_res_0x7f0a03a0)), indexOf, str2.length() + indexOf, 33);
        }
        healthTextView.setText(spannableString);
    }
}
