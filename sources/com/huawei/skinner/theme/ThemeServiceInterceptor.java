package com.huawei.skinner.theme;

import android.view.View;
import com.huawei.skinner.internal.IInterceptor;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class ThemeServiceInterceptor implements IInterceptor<View> {
    public static final int HWMUSIC_THEME_SERVICE_PRIORITY = 1;
    public ThemeServiceInterceptor prev;
    protected Class<? extends View> supportWidgetClz;
    public List<ThemeServiceInterceptor> children = new ArrayList();
    protected int priority = 1;

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }
}
