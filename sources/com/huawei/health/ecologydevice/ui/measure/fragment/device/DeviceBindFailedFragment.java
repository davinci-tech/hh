package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.res.ResourcesCompat;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dij;
import defpackage.dks;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes3.dex */
public class DeviceBindFailedFragment extends BaseFragment {
    public static final String IS_FROM_BIND = "isFromBind";
    private static final String TAG = "DeviceBindFailedFragment";
    private String mBindFailed;
    private CustomViewDialog mDialog;
    private int mErrorCode = Integer.MIN_VALUE;
    private boolean mIsFromBind;
    private String mNumberResult;
    private String mProductId;
    private dcz mProductInfo;

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        LogUtil.a(TAG, "DeviceBindFailedFragment onAttach");
        super.onAttach(context);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a(TAG, "DeviceBindFailedFragment onCreate");
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mProductId = arguments.getString("productId");
            this.mIsFromBind = arguments.getBoolean(IS_FROM_BIND, false);
            this.mErrorCode = arguments.getInt("errorCode", Integer.MIN_VALUE);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "DeviceBindFailedFragment onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.a(TAG, "DeviceBindFailedFragment onCreateView inflater is null");
            return viewGroup2;
        }
        View inflate = layoutInflater.inflate(R.layout.failed_bind_fragment_layout, viewGroup, false);
        if (inflate != null && viewGroup2 != null) {
            ((ImageView) inflate.findViewById(R.id.device_measure_top_square)).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable._2131429880_res_0x7f0b09f8, null));
            setImageView(inflate);
            dij.UX_(getActivity(), (LinearLayout) inflate.findViewById(R.id.image_view_container), 0.8d, true);
            setDialogView();
            viewGroup2.addView(inflate);
        } else {
            LogUtil.a(TAG, "DeviceBindFailedFragment onCreateView:child == null");
        }
        setTitle(getString(R.string.IDS_device_bind_connect));
        return viewGroup2;
    }

    private void setImageView(View view) {
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        ImageView imageView = (ImageView) view.findViewById(R.id.fail_bind_show_image);
        dcz dczVar = this.mProductInfo;
        imageView.setImageBitmap((dczVar == null || koq.b(dczVar.e())) ? null : dcx.TK_(dcq.b().a(this.mProductId, this.mProductInfo.e().get(0).e())));
        dij.UX_(getActivity(), imageView, 0.7d, true);
    }

    private void setDialogView() {
        boolean ae = nsn.ae(getContext());
        if (dks.g(this.mProductId)) {
            this.mBindFailed = getResources().getString(R.string.IDS_device_common_err_bind_failed_prompts_2);
            this.mNumberResult = String.format(Locale.ENGLISH, this.mBindFailed, 1, 1, 2);
        } else if (this.mErrorCode == -1) {
            this.mNumberResult = getString(R.string.IDS_device_bind_fail_rescan);
        } else if (this.mIsFromBind) {
            this.mBindFailed = getResources().getString(ae ? R.string.IDS_device_bind_fail_hint_pad : R.string.IDS_device_bind_failed_pin);
            this.mNumberResult = String.format(Locale.ENGLISH, this.mBindFailed, 1, 2, 1, 3);
        } else {
            this.mBindFailed = getResources().getString(ae ? R.string._2130849071_res_0x7f022d2f : R.string.IDS_device_bind_failed_wo_pin);
            this.mNumberResult = String.format(Locale.ENGLISH, this.mBindFailed, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0), UnitUtil.e(1.0d, 1, 0));
        }
        View inflate = View.inflate(getActivity(), R.layout.failed_bind_view_dialog, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.fail_bind_title);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.fail_bind_text_title);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.fail_bind_text_content);
        healthTextView.setText(getString(R.string.IDS_device_bind_fail));
        if (this.mErrorCode == -1) {
            healthTextView2.setVisibility(8);
        } else {
            healthTextView2.setText(R.string.IDS_device_err_connect_timeout_tips_title);
        }
        healthTextView3.setText(this.mNumberResult);
        inflate.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        CustomViewDialog e = new CustomViewDialog.Builder(requireContext()).c(false).czh_(inflate, 24, 24).a((String) null).czd_(getString(R.string.IDS_device_permisson).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindFailedFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindFailedFragment.this.m358xcb5a974(view);
            }
        }).e();
        this.mDialog = e;
        e.setCancelable(false);
        this.mDialog.show();
    }

    /* renamed from: lambda$setDialogView$0$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceBindFailedFragment, reason: not valid java name */
    /* synthetic */ void m358xcb5a974(View view) {
        finishActivity();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void finishActivity() {
        if (this.mainActivity != null) {
            this.mainActivity.finish();
        } else {
            LogUtil.h(TAG, "finishActivity mainActivity is null");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "DeviceBindFailedFragment onBackPressed");
        popupFragment(ProductIntroductionFragment.class);
        return true;
    }
}
