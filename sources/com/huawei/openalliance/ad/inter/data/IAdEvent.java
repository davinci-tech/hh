package com.huawei.openalliance.ad.inter.data;

import android.view.View;
import java.util.List;

/* loaded from: classes9.dex */
public interface IAdEvent {
    void onAdClosed(List<String> list);

    void onAdShowed(View view);
}
