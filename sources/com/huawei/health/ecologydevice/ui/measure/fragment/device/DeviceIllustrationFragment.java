package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dij;
import defpackage.dks;
import defpackage.nsn;

/* loaded from: classes3.dex */
public class DeviceIllustrationFragment extends BaseFragment {
    private static final String TAG = "DeviceIllustrationFragment";
    private static final int THREE_FOLD_FONTS_SERVICE_AREA_TEXT_SIZE = 40;
    private String mProductId;
    private dcz mProductInfo;
    private String mUniqueId;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a(TAG, "DeviceIllustrationFragment onCreate");
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = contentValues.getAsString("uniqueId");
            }
            this.mProductInfo = ResourceManager.e().d(this.mProductId);
            return;
        }
        LogUtil.h(TAG, "onCreate bundle is null");
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "DeviceIllustrationFragment onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (layoutInflater == null) {
            LogUtil.h(TAG, "DeviceIllustrationFragment onCreateView inflater is null");
            return onCreateView;
        }
        View inflate = layoutInflater.inflate(R.layout.illustrate_device_fragment_layout, viewGroup, false);
        if (inflate != null && onCreateView != null) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.hw_device_rope_skipping_img_1);
            imageView.setBackground(getActivity().getResources().getDrawable(R.drawable._2131431505_res_0x7f0b1051));
            dij.UX_(getActivity(), imageView, 0.7d, true);
            TextView textView = (TextView) inflate.findViewById(R.id.hw_device_start_rope_tip);
            TextView textView2 = (TextView) inflate.findViewById(R.id.hw_device_use_instructions);
            HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.hw_device_ok);
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
            if (nsn.s()) {
                textView.setTextSize(1, 40.0f);
                textView2.setTextSize(1, 40.0f);
                healthButton.setTextSize(1, 40.0f);
            }
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceIllustrationFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceIllustrationFragment.this.m368x7098f935(view);
                }
            });
            if (onCreateView instanceof ViewGroup) {
                ((ViewGroup) onCreateView).addView(inflate);
            }
        } else {
            LogUtil.h(TAG, "DeviceIllustrationFragment onCreateView:child == null");
        }
        dcz dczVar = this.mProductInfo;
        if (dczVar != null && dczVar.n() != null) {
            setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()));
        }
        return onCreateView;
    }

    /* renamed from: lambda$onCreateView$0$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceIllustrationFragment, reason: not valid java name */
    /* synthetic */ void m368x7098f935(View view) {
        switchToH5Intro();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void switchToH5Intro() {
        dcz dczVar = this.mProductInfo;
        if (dczVar != null && "1".equals(dczVar.j())) {
            dks.d(getContext(), this.mProductInfo, this.mProductId, this.mUniqueId);
            return;
        }
        Intent Wx_ = dks.Wx_(this.mProductInfo, this.mProductId, this.mUniqueId);
        if (Wx_ != null) {
            startActivity(Wx_);
            popupFragment(null);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "DeviceIllustrationFragment onBackPressed");
        popupFragment(null);
        return false;
    }
}
