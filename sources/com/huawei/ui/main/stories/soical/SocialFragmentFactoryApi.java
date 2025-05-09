package com.huawei.ui.main.stories.soical;

import android.content.Context;
import androidx.fragment.app.Fragment;

/* loaded from: classes7.dex */
public interface SocialFragmentFactoryApi {
    BaseSocialFragment getNewSocialFragment();

    Fragment getStoreDemoNewSocialFragment();

    void goFunctionMenu(Context context, int i);

    void goHealthEvaluation(Context context);

    boolean isValidResource(long j, long j2);
}
