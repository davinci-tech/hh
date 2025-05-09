package com.huawei.hms.iapfull.pay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.secure.android.common.intent.SafeBundle;

/* loaded from: classes9.dex */
public class n extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private TextView f4761a;
    private TextView b;
    private TextView c;
    private View.OnClickListener d = new a();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_trade_sign_result, viewGroup, false);
        this.f4761a = (TextView) inflate.findViewById(R.id.pay_product_name);
        this.b = (TextView) inflate.findViewById(R.id.pay_order_id);
        this.c = (TextView) inflate.findViewById(R.id.tv_payType);
        ((TextView) inflate.findViewById(R.id.sign_result_ok)).setOnClickListener(this.d);
        SafeBundle safeBundle = new SafeBundle(getArguments());
        this.f4761a.setText(safeBundle.getString("product_name"));
        this.b.setText(safeBundle.getString("pay_order"));
        this.c.setText(safeBundle.getString("pay_type"));
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    class a implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            n.this.getActivity().finish();
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
