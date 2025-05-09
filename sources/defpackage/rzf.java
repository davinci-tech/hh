package defpackage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/* loaded from: classes7.dex */
public class rzf {
    public static void e(FragmentManager fragmentManager, Fragment fragment, int i) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(i, fragment);
        beginTransaction.commitNowAllowingStateLoss();
    }
}
