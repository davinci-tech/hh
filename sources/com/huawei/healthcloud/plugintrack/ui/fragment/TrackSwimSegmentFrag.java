package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.TrackSwimSegmentListAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hjw;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class TrackSwimSegmentFrag extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (layoutInflater == null) {
            throw new RuntimeException("LayoutInflater not found.");
        }
        LogUtil.a("Track_TrackSwimPaceFrag", "onCreate");
        View inflate = layoutInflater.inflate(R.layout.track_fragment_swim_segment, viewGroup, false);
        ListView listView = (ListView) inflate.findViewById(R.id.list_segments);
        FragmentActivity activity = getActivity();
        if (!(activity instanceof TrackDetailActivity)) {
            LogUtil.b("Track_TrackSwimPaceFrag", "object is not TrackDetailActivity");
            return null;
        }
        hjw e = ((TrackDetailActivity) activity).e();
        listView.setDivider(null);
        if (e != null && !e.ap()) {
            if (UnitUtil.h()) {
                listView.setAdapter((ListAdapter) new TrackSwimSegmentListAdapter(getContext(), e.e().requestBritishSwimSegments()));
            } else {
                listView.setAdapter((ListAdapter) new TrackSwimSegmentListAdapter(getContext(), e.e().requestSwimSegments()));
            }
        }
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
