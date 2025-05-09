package com.huawei.health.device.api;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.huawei.health.device.constants.WeightConstants;

/* loaded from: classes3.dex */
public interface DeviceMainActivityApi {
    void addFragment(Activity activity, Fragment fragment, Fragment fragment2);

    Bundle getBundleFromDeviceMainActivity(Activity activity);

    Class<?> getMeasureBackClass(Activity activity);

    Fragment getPluginDeviceFragment(WeightConstants.FragmentType fragmentType);

    Fragment getSelectFragment(Activity activity, Class<?> cls);

    boolean isFromHealthModel(Activity activity);

    void onBackPressed(Activity activity);

    void popupFragment(Activity activity, Class<?> cls);

    void popupMyDeviceFragment(Activity activity);

    void setCurrentFragment(Activity activity, Fragment fragment);

    void setMeasureBackClass(Activity activity, Class<?> cls);

    void switchFragment(Activity activity, Fragment fragment, Fragment fragment2);

    void switchFragment(Activity activity, Fragment fragment, Fragment fragment2, String str);
}
