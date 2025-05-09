package com.huawei.hms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.health.R;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.maps.internal.IMapFragmentDelegate;
import com.huawei.hms.maps.model.RuntimeRemoteException;
import com.huawei.hms.maps.utils.LogM;
import com.huawei.hms.maps.utils.MapsAdvUtil;

/* loaded from: classes9.dex */
public class mad {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.content.Context] */
    public static View a(Activity activity, IMapFragmentDelegate iMapFragmentDelegate, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            Bundle a2 = com.huawei.hms.maps.internal.mac.a(bundle);
            Activity d = MapsAdvUtil.containsMapsBasic() ? activity : com.huawei.hms.maps.internal.mab.d(activity);
            if (iMapFragmentDelegate == null) {
                return LayoutInflater.from(d).inflate(R.layout.hms_map_empty_layout_sdk, (ViewGroup) null);
            }
            LogM.d("MapFragmentUtil", "sdk onCreateView iMapFragmentDelegate" + iMapFragmentDelegate);
            View view = (View) ObjectWrapper.unwrap(iMapFragmentDelegate.onCreateView(ObjectWrapper.wrap(LayoutInflater.from(d)), ObjectWrapper.wrap(viewGroup), a2));
            if (view == null) {
                return view;
            }
            ViewParent parent = view.getParent();
            if (!(parent instanceof ViewGroup)) {
                return view;
            }
            LogM.d("MapFragmentUtil", "onCreateView: removeView");
            ((ViewGroup) parent).removeView(view);
            return view;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
