package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.tradeservice.view.TradeInformationLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.ProductSummary;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class glc extends Dialog implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f12846a;
    private final ProductSummary b;
    private View c;
    private final ProductSummary d;
    private final Context e;
    private final TradeInformationLayout i;

    public glc(Context context, TradeInformationLayout tradeInformationLayout, IBaseResponseCallback iBaseResponseCallback, ProductSummary productSummary, ProductSummary productSummary2) {
        super(context, R.style.retain_dialog_dim_style);
        this.e = context;
        this.i = tradeInformationLayout;
        this.d = productSummary;
        this.b = productSummary2;
        this.f12846a = iBaseResponseCallback;
        b();
        setCanceledOnTouchOutside(false);
    }

    private void b() {
        String a2;
        View inflate = ((LayoutInflater) this.e.getSystemService("layout_inflater")).inflate(R.layout.trade_retain_vip, (ViewGroup) null);
        this.c = inflate;
        setContentView(inflate);
        HealthTextView healthTextView = (HealthTextView) this.c.findViewById(R.id.origin_price);
        String currency = this.b.getCurrency();
        double microPrice = this.b.getMicroPrice() / 1000000.0f;
        healthTextView.setText(gla.a(currency, microPrice));
        healthTextView.setPaintFlags(16);
        double microPrice2 = this.d.getMicroPrice() / 1000000.0f;
        String a3 = gla.a(currency, microPrice2);
        SpannableString spannableString = new SpannableString(a3);
        String b = gla.b(currency);
        int indexOf = a3.indexOf(b);
        if (indexOf != -1) {
            spannableString.setSpan(new AbsoluteSizeSpan(this.e.getResources().getDimensionPixelSize(R.dimen._2131365078_res_0x7f0a0cd6)), indexOf, b.length() + indexOf, 33);
        }
        ((HealthTextView) this.c.findViewById(R.id.current_price)).setText(spannableString);
        String e = gla.e(gla.d(microPrice, 2) - gla.d(microPrice2, 2));
        if (LanguageUtil.m(this.e)) {
            a2 = e + " 元";
        } else {
            a2 = gla.a(currency, microPrice - microPrice2);
        }
        SpannableString spannableString2 = new SpannableString(this.e.getResources().getString(R.string._2130847222_res_0x7f0225f6, a2));
        int indexOf2 = spannableString2.toString().indexOf(e);
        if (indexOf2 != -1) {
            spannableString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.e, R.color._2131299381_res_0x7f090c35)), indexOf2, e.length() + indexOf2, 33);
            spannableString2.setSpan(new AbsoluteSizeSpan(this.e.getResources().getDimensionPixelSize(R.dimen._2131365075_res_0x7f0a0cd3)), indexOf2, e.length() + indexOf2, 33);
        } else {
            LogUtil.h("RetainDialog", "price not found");
        }
        ((HealthTextView) this.c.findViewById(R.id.save_money_message)).setText(spannableString2);
        ((HealthButton) this.c.findViewById(R.id.buy_now)).setOnClickListener(this);
        ((HealthButton) this.c.findViewById(R.id.cancel_buy_vip)).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.buy_now) {
            LogUtil.a("RetainDialog", "buy now");
            dismiss();
            this.i.a();
            IBaseResponseCallback iBaseResponseCallback = this.f12846a;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(1, null);
            }
            gla.e(2, this.e);
        } else if (view.getId() == R.id.cancel_buy_vip) {
            LogUtil.a("RetainDialog", "cancel buying");
            dismiss();
            IBaseResponseCallback iBaseResponseCallback2 = this.f12846a;
            if (iBaseResponseCallback2 != null) {
                iBaseResponseCallback2.d(0, null);
            }
            gla.e(3, this.e);
        } else {
            LogUtil.h("RetainDialog", "nothing to do");
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
