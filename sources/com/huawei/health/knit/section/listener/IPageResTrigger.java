package com.huawei.health.knit.section.listener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentManager;
import com.huawei.health.knit.data.IKnitLifeCycle;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hmf.tasks.OnFailureListener;
import java.util.List;

/* loaded from: classes3.dex */
public interface IPageResTrigger extends Parcelable, IKnitLifeCycle {
    public static final int PAGE_CATEGORY_SPORT = 1;
    public static final int PAGE_CATEGORY_UNKNOWN = 0;
    public static final int PAGE_MEMBER_INTRO = 2;

    Bundle getExtra();

    MajorProvider getMajorProvider();

    List<MinorProvider> getMinorProviderList();

    int getPageCategory();

    int getResPosId();

    boolean isNeedToLoadEmptyLayout();

    boolean isUsingCacheBeanList();

    void onActivityResult(int i, int i2, Intent intent);

    void onDestroy(Activity activity);

    void onPageCreated(Activity activity, View view);

    List<SectionBean> onPageLoadOfflineSections(String str, boolean z);

    void onPageLoadOnlineSections(Consumer<List<SectionBean>> consumer, FragmentManager fragmentManager);

    void onPageVisibilityChanged(Activity activity, boolean z, View view);

    IPageResTrigger setExtra(Bundle bundle);

    IPageResTrigger setIsNeedToLoadEmptyLayout(boolean z);

    void setOnFailureListener(OnFailureListener onFailureListener);
}
