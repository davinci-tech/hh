package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes3.dex */
public abstract class ListFragment extends BaseFragment {
    private static final String TAG = "ListFragment";
    protected ListView myDevicesListview;

    public void showButton(boolean z, View.OnClickListener onClickListener) {
        HealthButton healthButton = (HealthButton) this.child.findViewById(R.id.bind_new_device);
        if (z) {
            healthButton.setVisibility(0);
            healthButton.setOnClickListener(onClickListener);
        } else {
            healthButton.setVisibility(8);
        }
    }

    protected void showMoreButton(boolean z, View.OnClickListener onClickListener) {
        LinearLayout linearLayout = (LinearLayout) this.child.findViewById(R.id.device_list_ll);
        HealthTextView healthTextView = (HealthTextView) this.child.findViewById(R.id.hw_device_bottom_btn_text);
        if (z) {
            healthTextView.setVisibility(0);
            linearLayout.setVisibility(0);
            linearLayout.setOnClickListener(onClickListener);
        } else {
            healthTextView.setVisibility(8);
            linearLayout.setVisibility(8);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.a(TAG, "onCreateView inflater is null");
            return viewGroup2;
        }
        this.child = layoutInflater.inflate(R.layout.device_list_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        ListView listView = (ListView) this.child.findViewById(R.id.device_list_view);
        this.myDevicesListview = listView;
        listView.setOverScrollMode(2);
        return viewGroup2;
    }
}
