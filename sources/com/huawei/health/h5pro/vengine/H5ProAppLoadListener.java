package com.huawei.health.h5pro.vengine;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes3.dex */
public interface H5ProAppLoadListener {
    void onException(H5ProInstance h5ProInstance, String str);

    void onFloatingBarRequested(RecyclerView.Adapter adapter);

    void onNewPageLoaded(H5ProInstance h5ProInstance, String str);

    void onNewPageStartLoad(H5ProInstance h5ProInstance, String str);

    void onProgressChanged(H5ProInstance h5ProInstance, int i);

    void onReceiveTitle(H5ProInstance h5ProInstance, String str);

    void onResourceLoadErr(H5ProInstance h5ProInstance, String str, int i);

    void onViewCreated(H5ProInstance h5ProInstance, View view);

    void onViewPreCreate(H5ProInstance h5ProInstance, View view);
}
