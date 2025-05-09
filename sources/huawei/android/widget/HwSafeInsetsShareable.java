package huawei.android.widget;

import android.view.View;

/* loaded from: classes7.dex */
public interface HwSafeInsetsShareable {
    public static final int TYPE_MARGIN = 1;
    public static final int TYPE_PADDING = 2;

    void addSharedView(View view, int i);

    void removeSharedView(View view);
}
