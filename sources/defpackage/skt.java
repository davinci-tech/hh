package defpackage;

import android.content.Context;
import android.view.Menu;
import android.view.View;
import com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class skt {
    public static void d(Context context, HwBottomNavigationView hwBottomNavigationView) {
        if (context == null || hwBottomNavigationView == null) {
            return;
        }
        Method b = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b == null) {
            hwBottomNavigationView.setExtendedNextTabEnabled(true, true);
            hwBottomNavigationView.setExtendedNextTabEnabled(false, true);
            return;
        }
        Boolean bool = Boolean.TRUE;
        Object c = slc.c((Object) null, b, new Object[]{context, hwBottomNavigationView, "switchTabEnabled", bool});
        if (c instanceof Boolean) {
            hwBottomNavigationView.setExtendedNextTabEnabled(true, ((Boolean) c).booleanValue());
        } else {
            hwBottomNavigationView.setExtendedNextTabEnabled(true, true);
        }
        Object c2 = slc.c((Object) null, b, new Object[]{context, hwBottomNavigationView, "switchTabWhenFocusedEnabled", bool});
        if (c2 instanceof Boolean) {
            hwBottomNavigationView.setExtendedNextTabEnabled(false, ((Boolean) c2).booleanValue());
        } else {
            hwBottomNavigationView.setExtendedNextTabEnabled(false, true);
        }
    }

    public static void dZQ_(HwBottomNavigationView.BottomNavigationItemView bottomNavigationItemView, boolean z, boolean z2, HwBottomNavigationView.BottomNavListener bottomNavListener, Menu menu) {
        if (bottomNavigationItemView == null || menu == null) {
            return;
        }
        int itemIndex = bottomNavigationItemView.getItemIndex();
        boolean isChecked = bottomNavigationItemView.getIsChecked();
        if (z) {
            if (isChecked) {
                bottomNavigationItemView.setChecked(false, false);
                if (bottomNavListener != null) {
                    bottomNavListener.onBottomNavItemUnselected(menu.getItem(itemIndex), itemIndex);
                    return;
                }
                return;
            }
            bottomNavigationItemView.setChecked(true, false);
            if (bottomNavListener != null) {
                bottomNavListener.onBottomNavItemSelected(menu.getItem(itemIndex), itemIndex);
                return;
            }
            return;
        }
        if (z2) {
            bottomNavigationItemView.setChecked(true, false);
            if (isChecked || bottomNavListener == null) {
                return;
            }
            bottomNavListener.onBottomNavItemSelected(menu.getItem(itemIndex), itemIndex);
            return;
        }
        bottomNavigationItemView.setChecked(false, false);
        if (!isChecked || bottomNavListener == null) {
            return;
        }
        bottomNavListener.onBottomNavItemUnselected(menu.getItem(itemIndex), itemIndex);
    }
}
