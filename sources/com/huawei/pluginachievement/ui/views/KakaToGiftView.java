package com.huawei.pluginachievement.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.GiftInfo;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.mle;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Calendar;
import java.util.Map;

/* loaded from: classes9.dex */
public class KakaToGiftView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8466a;
    private Context b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private LinearLayout h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private ImageView l;
    private HealthTextView m;
    private ImageView n;
    private GiftInfo o;
    private LinearLayout q;

    public KakaToGiftView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        e();
    }

    private void e() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.kaka_exchange_item_layout, this);
        this.q = (LinearLayout) inflate.findViewById(R.id.left_days_layout);
        this.f = (HealthTextView) inflate.findViewById(R.id.exchange_end_text);
        this.c = (HealthTextView) inflate.findViewById(R.id.exchange_end_left_day);
        this.f8466a = (HealthTextView) inflate.findViewById(R.id.exchange_end_left_hour);
        this.i = (HealthTextView) inflate.findViewById(R.id.exchange_end_left_sec_point);
        this.d = (HealthTextView) inflate.findViewById(R.id.exchange_end_left_min);
        this.e = (HealthTextView) inflate.findViewById(R.id.exchange_end_left_sec);
        this.l = (ImageView) inflate.findViewById(R.id.exchange_goods_image);
        this.m = (HealthTextView) inflate.findViewById(R.id.exchange_goods_name);
        this.g = (HealthTextView) inflate.findViewById(R.id.exchange_goods_detail);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.exchange_goods_detail_layout);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.kaka_gift_detail);
        this.g.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.pluginachievement.ui.views.KakaToGiftView.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (KakaToGiftView.this.g.getMeasuredWidth() == linearLayout.getMeasuredWidth() && KakaToGiftView.this.g.getMeasuredWidth() != 0) {
                    LogUtil.a("KakaToGiftView", "detailLayout.getMeasuredWidth() = ", Integer.valueOf(linearLayout.getMeasuredWidth()));
                    LogUtil.a("KakaToGiftView", "kakaGiftDetail.getMeasuredWidth() = ", Integer.valueOf(imageView.getMeasuredWidth()));
                    KakaToGiftView.this.g.setMaxWidth(linearLayout.getMeasuredWidth() - imageView.getMeasuredWidth());
                }
                if (KakaToGiftView.this.g.getMeasuredWidth() != 0) {
                    KakaToGiftView.this.g.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
        this.j = (HealthTextView) inflate.findViewById(R.id.exchange_goods_has_exchanged);
        this.h = (LinearLayout) inflate.findViewById(R.id.exchange_goods_btn);
        this.k = (HealthTextView) inflate.findViewById(R.id.exchange_goods_text);
        this.n = (ImageView) inflate.findViewById(R.id.exchange_goods_iv);
        if (LanguageUtil.bc(this.b)) {
            imageView.setImageDrawable(nrz.cKn_(this.b, R.mipmap._2131820550_res_0x7f110006));
        }
    }

    public void setGiftInfo(GiftInfo giftInfo) {
        LogUtil.c("KakaToGiftView", "setGiftInfo giftInfo getAwardItemId= ", giftInfo.getAwardItemId());
        this.o = giftInfo;
        b();
    }

    public void setExchangeBtnClickListener(View.OnClickListener onClickListener) {
        this.h.setOnClickListener(onClickListener);
    }

    private void b() {
        if (this.o == null) {
            LogUtil.h("KakaToGiftView", "updateAllView mGiftInfo == null");
            return;
        }
        a();
        this.m.setText(this.o.getAwardName());
        if ("1".equals(this.o.getAwardType())) {
            this.g.setText(this.b.getString(R.string._2130840881_res_0x7f020d31));
        } else if ("2".equals(this.o.getAwardType())) {
            this.g.setText(this.b.getString(R.string._2130840882_res_0x7f020d32));
        } else {
            LogUtil.h("KakaToGiftView", "updateTimeView error gift type");
        }
        this.j.setText(this.b.getString(R.string._2130841019_res_0x7f020dbb, UnitUtil.e(a(this.o), 2, 1)));
        this.k.setText(UnitUtil.e(this.o.getKakaPoint(), 1, 0));
        d();
    }

    private float a(GiftInfo giftInfo) {
        float f;
        float f2;
        if (giftInfo == null) {
            return 0.0f;
        }
        int dayNum = giftInfo.getDayNum();
        int dayConsumed = giftInfo.getDayConsumed();
        int num = giftInfo.getNum();
        int remainingNum = giftInfo.getRemainingNum();
        LogUtil.a("KakaToGiftView", "getQuotient DayNum ", Integer.valueOf(dayNum), " total ", Integer.valueOf(num), " DayConsumed ", Integer.valueOf(dayConsumed), "remainingNum ", Integer.valueOf(remainingNum));
        if (dayNum != 0 && remainingNum != 0) {
            f = dayConsumed * 100.0f;
            f2 = dayNum;
        } else {
            if (num == 0) {
                return 0.0f;
            }
            f = (num - remainingNum) * 100.0f;
            f2 = num;
        }
        return f / f2;
    }

    private void a() {
        GiftInfo giftInfo = this.o;
        if (giftInfo == null) {
            return;
        }
        Map<String, String> pictures = giftInfo.getPictures();
        String str = (pictures == null || !pictures.containsKey("MIDDLE")) ? "" : pictures.get("MIDDLE");
        RequestOptions requestOptions = new RequestOptions();
        nrf.e(requestOptions, R.mipmap._2131820549_res_0x7f110005);
        nrf.cIv_(str, requestOptions, this.l);
    }

    public void d() {
        GiftInfo giftInfo = this.o;
        if (giftInfo == null) {
            LogUtil.h("KakaToGiftView", "updateTimeView mGiftInfo == null");
            return;
        }
        long startTime = giftInfo.getStartTime();
        long endTime = this.o.getEndTime();
        long currentTimeMillis = System.currentTimeMillis();
        if (startTime > currentTimeMillis) {
            e(startTime);
            return;
        }
        if (startTime <= currentTimeMillis && currentTimeMillis <= endTime) {
            b(endTime);
            return;
        }
        this.f.setText(this.b.getString(R.string._2130840880_res_0x7f020d30));
        this.c.setText("");
        this.h.setBackgroundResource(R.drawable._2131430746_res_0x7f0b0d5a);
        this.j.setText(this.b.getString(R.string._2130841924_res_0x7f021144));
        String e = mle.e(0);
        this.f8466a.setText(e);
        this.d.setText(e);
        this.e.setText(e);
    }

    private void e(long j) {
        String a2;
        this.h.setBackgroundResource(R.drawable._2131430746_res_0x7f0b0d5a);
        long currentTimeMillis = System.currentTimeMillis();
        if (LanguageUtil.b(this.b) && j - currentTimeMillis > 86400000) {
            this.f.setText(this.b.getString(R.string._2130840997_res_0x7f020da5, UnitUtil.a("MM/dd", j)));
            this.q.setVisibility(8);
            return;
        }
        this.q.setVisibility(0);
        this.f.setText(this.b.getString(R.string._2130840879_res_0x7f020d2f));
        if (LanguageUtil.j(this.b)) {
            String b = nsj.b(j);
            a2 = b.length() > 5 ? b.substring(5) : "";
        } else {
            a2 = UnitUtil.a("MM/dd", j);
        }
        this.c.setText(a2);
        if (LanguageUtil.b(this.b) && !mle.a(currentTimeMillis, j)) {
            this.c.setVisibility(8);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(11);
        int i2 = calendar.get(12);
        this.f8466a.setText(mle.e(i));
        this.d.setText(mle.e(i2));
        this.e.setVisibility(8);
        this.i.setVisibility(8);
    }

    private void b(long j) {
        int color;
        if (this.o.getRemainingNum() > 0) {
            this.h.setBackgroundResource(R.drawable._2131430743_res_0x7f0b0d57);
            long currentTimeMillis = System.currentTimeMillis();
            if (LanguageUtil.b(this.b)) {
                long j2 = j - currentTimeMillis;
                if (j2 > 86400000) {
                    int i = (int) (j2 / 86400000);
                    this.f.setText(getResources().getQuantityString(R.plurals._2130903185_res_0x7f030091, i, UnitUtil.e(i, 1, 0)));
                    this.q.setVisibility(8);
                    return;
                }
            }
            this.q.setVisibility(0);
            this.f.setText(this.b.getString(R.string._2130840880_res_0x7f020d30));
            int[] e = mle.e(j - currentTimeMillis);
            String e2 = UnitUtil.e(e[0], 1, 0);
            if (e[0] == 0) {
                this.c.setVisibility(8);
            } else {
                this.c.setText(getResources().getQuantityString(R.plurals._2130903164_res_0x7f03007c, e[0], e2));
            }
            this.f8466a.setText(mle.e(e[1]));
            this.d.setText(mle.e(e[2]));
            this.e.setText(mle.e(e[3]));
            return;
        }
        boolean a2 = nrt.a(this.b);
        this.h.setBackgroundResource(R.drawable._2131430745_res_0x7f0b0d59);
        this.h.getBackground().setAlpha(a2 ? 102 : 153);
        if (a2) {
            color = this.b.getColor(R.color._2131296995_res_0x7f0902e3);
        } else {
            color = this.b.getColor(R.color._2131297000_res_0x7f0902e8);
        }
        this.k.setTextColor(color);
        this.n.setAlpha(a2 ? 0.4f : 0.7f);
        this.f.setText(this.b.getString(R.string._2130841020_res_0x7f020dbc));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, nrr.e(this.b, 10.0f), 0, 0);
        this.f.setLayoutParams(layoutParams);
        this.q.setVisibility(8);
    }
}
