package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dij;
import defpackage.nsn;
import java.io.File;

/* loaded from: classes3.dex */
public class DeviceMeasureGuideShowFragment extends BaseFragment {
    private static final int LRU_CACHE_SIZE = 1048576;
    private static final String TAG = "DeviceMeasureGuideShowFragment";
    private static final int THREE_FOLD_FONTS_SERVICE_AREA_TEXT_SIZE = 40;
    private final LruCache<String, Bitmap> mBitmapLruCache = new LruCache<>(1048576);
    private String mProductId;
    private dcz mProductInfo;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a(TAG, "onCreate");
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
            }
            this.mProductInfo = ResourceManager.e().b(this.mProductId);
            return;
        }
        LogUtil.h(TAG, "onCreate bundle is null");
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (layoutInflater == null) {
            LogUtil.h(TAG, "onCreateView inflater is null!");
            return onCreateView;
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_device_measure_guide_show, viewGroup, false);
        if (inflate != null && onCreateView != null) {
            initView(inflate);
            if (onCreateView instanceof ViewGroup) {
                ((ViewGroup) onCreateView).addView(inflate);
            }
        } else {
            LogUtil.h(TAG, "onCreateView child is null or view is null!");
        }
        return onCreateView;
    }

    private void showDeviceImage(String str, String str2, ImageView imageView) {
        Bitmap bitmap;
        if (this.mBitmapLruCache.get(str) == null) {
            String a2 = dcq.b().a(str, str2);
            bitmap = dcx.TK_(a2);
            if (new File(a2).exists() && bitmap != null) {
                LogUtil.a(TAG, "cache Image");
                this.mBitmapLruCache.put(str, bitmap);
            }
        } else {
            LogUtil.a(TAG, "load exists Image");
            bitmap = this.mBitmapLruCache.get(str);
        }
        if (bitmap == null) {
            LogUtil.a(TAG, "showDeviceImage bitmap == null");
        } else {
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }

    private void initView(View view) {
        dcz dczVar = this.mProductInfo;
        if (dczVar == null) {
            LogUtil.h(TAG, "initView mProductInfo is null");
            return;
        }
        if (dczVar.n() != null) {
            setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()));
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.device_show_image_iv);
        showDeviceImage(this.mProductId, this.mProductInfo.e().get(0).e(), imageView);
        dij.UX_(getActivity(), imageView, 0.7d, true);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.device_prepare_measure_tv);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.device_measure_tip_tv);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.device_measure_tip2_tv);
        HealthTextView healthTextView4 = (HealthTextView) view.findViewById(R.id.device_measure_tip3_tv);
        HealthDevice.HealthDeviceKind l = this.mProductInfo.l();
        LogUtil.c(TAG, "initView mKind = ", l);
        if (l == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
            healthTextView3.setVisibility(0);
            healthTextView4.setVisibility(0);
        } else if (l == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
            healthTextView2.setText(R.string.IDS_device_blood_sugar_measure_tip);
        } else {
            LogUtil.a(TAG, "initView mKind is not exist!");
        }
        if (nsn.s()) {
            healthTextView.setTextSize(1, 40.0f);
            healthTextView2.setTextSize(1, 40.0f);
            healthTextView3.setTextSize(1, 40.0f);
            healthTextView4.setTextSize(1, 40.0f);
            ((HealthTextView) view.findViewById(R.id.device_start_measure_tip_tv)).setTextSize(1, 40.0f);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "onBackPressed");
        popupFragment(ProductIntroductionFragment.class);
        return false;
    }
}
