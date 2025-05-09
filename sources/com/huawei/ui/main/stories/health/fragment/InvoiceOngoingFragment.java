package com.huawei.ui.main.stories.health.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.health.activity.healthdata.InvoiceApplicationActivity;
import defpackage.nsy;
import defpackage.qrk;

/* loaded from: classes8.dex */
public class InvoiceOngoingFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private Handler f10173a;
    private int b;
    private HealthSubHeader c;
    private HealthTextView d;
    private String e;
    private HealthTextView f;
    private View h;
    private String j;

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a("InvoiceOngoingFragment", "onCreate");
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        if (activity instanceof InvoiceApplicationActivity) {
            this.f10173a = ((InvoiceApplicationActivity) activity).dAN_();
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.j = arguments.getString("orderId");
            this.e = arguments.getString("orderCode");
            this.b = arguments.getInt("invoiceHeader");
        }
        LogUtil.a("InvoiceOngoingFragment", "mOrderId" + this.j);
        LogUtil.a("InvoiceOngoingFragment", "mOrderCode" + this.e);
        LogUtil.a("InvoiceOngoingFragment", "mInvoiceHeader" + this.b);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("InvoiceOngoingFragment", "onCreateView");
        this.h = layoutInflater.inflate(R.layout.activity_invoice_ongoing, viewGroup, false);
        a();
        qrk.dHJ_(this.e, this.f10173a);
        qrk.dHI_(this.e, this.f10173a);
        qrk.dHH_(this.e, this.f10173a, 20000L);
        return this.h;
    }

    private void a() {
        LogUtil.a("InvoiceOngoingFragment", "initView");
        HealthSubHeader healthSubHeader = (HealthSubHeader) nsy.cMd_(this.h, R.id.Invoicing_information_subheader_ongoing);
        this.c = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(BaseApplication.e(), R.color._2131296971_res_0x7f0902cb));
        HealthTextView healthTextView = (HealthTextView) this.h.findViewById(R.id.transaction_no_display);
        this.f = healthTextView;
        healthTextView.setText(this.j);
        HealthTextView healthTextView2 = (HealthTextView) this.h.findViewById(R.id.invoice_header_display);
        this.d = healthTextView2;
        healthTextView2.setText(getString(this.b));
    }
}
