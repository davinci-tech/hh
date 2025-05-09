package com.huawei.ui.main.stories.health.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.health.activity.healthdata.InvoiceApplicationActivity;
import com.huawei.ui.main.stories.health.fragment.InvoiceFailedFragment;
import defpackage.nsy;

/* loaded from: classes8.dex */
public class InvoiceFailedFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private String f10172a;
    private View b;
    private int c;
    private String d;
    private String e;

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a("InvoiceFailedFragment", "onCreate");
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.d = arguments.getString("orderId");
            this.f10172a = arguments.getString("orderCode");
            this.c = arguments.getInt("invoiceHeader");
            this.e = arguments.getString(HwPayConstant.KEY_AMOUNT);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("InvoiceFailedFragment", "onCreateView");
        this.b = layoutInflater.inflate(R.layout.activity_invoice_falied, viewGroup, false);
        a();
        return this.b;
    }

    private void a() {
        LogUtil.a("InvoiceFailedFragment", "initView");
        HealthTextView healthTextView = (HealthTextView) this.b.findViewById(R.id.invoice_header_failed);
        int i = this.c;
        healthTextView.setText(i != 0 ? getString(i) : "");
        ((HealthTextView) this.b.findViewById(R.id.transaction_no_failed)).setText(this.d);
        ((HealthButton) this.b.findViewById(R.id.btn_reapply)).setOnClickListener(new View.OnClickListener() { // from class: qir
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InvoiceFailedFragment.this.dEg_(view);
            }
        });
        ((HealthSubHeader) nsy.cMd_(this.b, R.id.Invoicing_information_subheader_failed)).setSubHeaderBackgroundColor(ContextCompat.getColor(BaseApplication.e(), R.color._2131296971_res_0x7f0902cb));
    }

    public /* synthetic */ void dEg_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        LogUtil.a("InvoiceFailedFragment", "switchActivity");
        Intent intent = new Intent(getActivity(), (Class<?>) InvoiceApplicationActivity.class);
        intent.putExtra("orderId", this.d);
        intent.putExtra("orderCode", this.f10172a);
        intent.putExtra(ParsedFieldTag.PRICE, Double.valueOf(this.e));
        startActivity(intent);
    }
}
