package com.huawei.healthcloud.plugintrack.ui.viewholder;

import android.util.SparseArray;
import com.huawei.healthcloud.plugintrack.ui.adapter.TrackGridViewAdapter;
import java.util.List;

/* loaded from: classes4.dex */
public interface ShowDataConfig {
    SparseArray<TrackMainFragmentShowType> getConfigFromLocal(int i, int i2, int i3);

    SparseArray<TrackMainFragmentShowType> getDefaultConfig(int i, int i2, int i3);

    List<TrackGridViewAdapter.b> getValueHolderList(int i, int i2);

    void saveConfigToLocal(SparseArray<TrackMainFragmentShowType> sparseArray, int i, int i2, int i3);
}
