package com.huawei.openalliance.ad.dynamictemplate;

import android.widget.ImageView;
import com.huawei.openalliance.ad.dynamictemplate.view.IDrawableSetter;

/* loaded from: classes9.dex */
public interface IImageLoader {
    void load(ImageView imageView, String str);

    void loadDrawable(IDrawableSetter iDrawableSetter, String str);
}
