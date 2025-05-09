package com.huawei.hms.iapfull.pay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.hms.iapfull.a1;
import com.huawei.secure.android.common.intent.SafeBundle;

/* loaded from: classes9.dex */
public class k extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private TextView f4757a;
    private TextView b;
    private View.OnClickListener c = new a();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_trade_pay_result, viewGroup, false);
        this.f4757a = (TextView) inflate.findViewById(R.id.product_name_text);
        this.b = (TextView) inflate.findViewById(R.id.product_price_text);
        ((TextView) inflate.findViewById(R.id.pay_result_ok)).setOnClickListener(this.c);
        SafeBundle safeBundle = new SafeBundle(getArguments());
        this.f4757a.setText(safeBundle.getString("product_name"));
        this.b.setText(a1.a(getContext(), safeBundle.getString("product_price"), "string_cny_normal", safeBundle.getString("product_currency")));
        return inflate;
    }

    class a implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            k.this.getActivity().finish();
        }

        a() {
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }
}
