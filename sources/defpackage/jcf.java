package defpackage;

import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.accessibility.AccessibilityNodeInfoCallback;
import health.compact.a.ReleaseLogUtil;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public final class jcf {
    /* JADX INFO: Access modifiers changed from: private */
    public static void e(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, CharSequence charSequence, boolean z, Class<?> cls) {
        if (accessibilityNodeInfoCompat == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setAccessibilityNodeInfoCompat nodeInfo is null");
            return;
        }
        accessibilityNodeInfoCompat.setContentDescription(charSequence);
        accessibilityNodeInfoCompat.setClickable(z);
        if (z) {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        } else {
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }
        accessibilityNodeInfoCompat.setFocusable(true);
        accessibilityNodeInfoCompat.setImportantForAccessibility(true);
        if (cls == null) {
            return;
        }
        String name = cls.getName();
        if (TextUtils.isEmpty(name)) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setAccessibilityNodeInfoCompat className ", name);
        } else {
            accessibilityNodeInfoCompat.setClassName(name);
        }
    }

    public static AccessibilityManager bEl_() {
        Object systemService = BaseApplication.e().getSystemService("accessibility");
        if (!(systemService instanceof AccessibilityManager)) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "getAccessibilityManager object ", systemService);
            return null;
        }
        return (AccessibilityManager) systemService;
    }

    public static boolean c() {
        AccessibilityManager bEl_ = bEl_();
        if (bEl_ != null) {
            return bEl_.isEnabled() && bEl_.isTouchExplorationEnabled();
        }
        ReleaseLogUtil.a("R_AccessibilityUtil", "isTouchExplorationEnabled accessibilityManager is null");
        return false;
    }

    public static void bEj_(AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        if (touchExplorationStateChangeListener == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "addTouchExplorationStateChangeListener listener is null");
            return;
        }
        AccessibilityManager bEl_ = bEl_();
        if (bEl_ == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "addTouchExplorationStateChangeListener accessibilityManager is null");
        } else {
            bEl_.addTouchExplorationStateChangeListener(touchExplorationStateChangeListener);
        }
    }

    public static void bEu_(AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        if (touchExplorationStateChangeListener == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "removeTouchExplorationStateChangeListener listener is null");
            return;
        }
        AccessibilityManager bEl_ = bEl_();
        if (bEl_ == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "removeTouchExplorationStateChangeListener accessibilityManager is null");
        } else {
            bEl_.removeTouchExplorationStateChangeListener(touchExplorationStateChangeListener);
        }
    }

    public static void bEE_(View view, int i) {
        bEF_(view, i, false);
    }

    public static void bEF_(View view, int i, boolean z) {
        if (view == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setImportantForAccessibility view is null");
            return;
        }
        view.setImportantForAccessibility(i);
        if (c() && z) {
            view.setFocusable(true);
            view.setEnabled(true);
        }
    }

    public static void bEM_(View view, int i, boolean z) {
        if (view == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setImportantForAccessibility view is null");
            return;
        }
        view.setImportantForAccessibility(i);
        if (c()) {
            view.setFocusable(z);
            view.setEnabled(z);
        }
    }

    public static void bEv_(View view, int i) {
        if (view == null) {
            return;
        }
        view.sendAccessibilityEvent(i);
    }

    public static void bEk_(View view, CharSequence charSequence) {
        if (view == null || TextUtils.isEmpty(charSequence)) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "announceForAccessibility view ", view, " text ", charSequence);
        } else {
            view.announceForAccessibility(charSequence);
        }
    }

    private static void bEw_(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        if (view == null || accessibilityDelegateCompat == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setAccessibilityDelegate view ", view, " accessibilityDelegateCompat ", accessibilityDelegateCompat);
        } else {
            ViewCompat.setAccessibilityDelegate(view, accessibilityDelegateCompat);
        }
    }

    public static void bEt_(View view, final AccessibilityNodeInfoCallback accessibilityNodeInfoCallback) {
        if (accessibilityNodeInfoCallback == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setAccessibilityDelegate callback is null");
        } else {
            bEw_(view, new AccessibilityDelegateCompat() { // from class: jcf.3
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                    AccessibilityNodeInfoCallback.this.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                }
            });
        }
    }

    public static void bEz_(View view, CharSequence charSequence) {
        bEC_(view, charSequence, true, null);
    }

    public static void bEA_(View view, CharSequence charSequence, Class<?> cls) {
        bEC_(view, charSequence, true, cls);
    }

    public static void bEB_(View view, CharSequence charSequence, boolean z) {
        bEC_(view, charSequence, z, null);
    }

    public static void bEC_(View view, final CharSequence charSequence, final boolean z, final Class<?> cls) {
        if (TextUtils.isEmpty(charSequence)) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setContentDescription contentDescription ", charSequence);
        } else {
            bEt_(view, new AccessibilityNodeInfoCallback() { // from class: jci
                @Override // com.huawei.hwcommonmodel.accessibility.AccessibilityNodeInfoCallback
                public final void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    jcf.e(accessibilityNodeInfoCompat, charSequence, z, cls);
                }
            });
        }
    }

    public static void bEJ_(View view, CharSequence charSequence, boolean z) {
        bEL_(view, charSequence, z, true, null);
    }

    public static void bEK_(View view, CharSequence charSequence, boolean z, Class<?> cls) {
        bEL_(view, charSequence, z, true, cls);
    }

    public static void bEL_(View view, final CharSequence charSequence, final boolean z, final boolean z2, final Class<?> cls) {
        if (TextUtils.isEmpty(charSequence)) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setSelectedContentDescription contentDescription ", charSequence);
        } else {
            bEt_(view, new AccessibilityNodeInfoCallback() { // from class: jck
                @Override // com.huawei.hwcommonmodel.accessibility.AccessibilityNodeInfoCallback
                public final void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    jcf.bEr_(z, charSequence, z2, cls, view2, accessibilityNodeInfoCompat);
                }
            });
        }
    }

    static /* synthetic */ void bEr_(boolean z, CharSequence charSequence, boolean z2, Class cls, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setSelectedContentDescription info is null");
        } else {
            accessibilityNodeInfoCompat.setSelected(z);
            e(accessibilityNodeInfoCompat, charSequence, z2, cls);
        }
    }

    public static void bEx_(View view, CharSequence charSequence, boolean z, Class<?> cls) {
        bEy_(view, charSequence, z, true, cls);
    }

    public static void bEy_(View view, final CharSequence charSequence, final boolean z, final boolean z2, final Class<?> cls) {
        if (TextUtils.isEmpty(charSequence)) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setCheckedContentDescription contentDescription ", charSequence);
        } else {
            bEt_(view, new AccessibilityNodeInfoCallback() { // from class: jcp
                @Override // com.huawei.hwcommonmodel.accessibility.AccessibilityNodeInfoCallback
                public final void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    jcf.bEm_(z, charSequence, z2, cls, view2, accessibilityNodeInfoCompat);
                }
            });
        }
    }

    static /* synthetic */ void bEm_(boolean z, CharSequence charSequence, boolean z2, Class cls, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setCheckedContentDescription info is null");
            return;
        }
        accessibilityNodeInfoCompat.setCheckable(true);
        accessibilityNodeInfoCompat.setChecked(z);
        e(accessibilityNodeInfoCompat, charSequence, z2, cls);
    }

    public static void bEI_(View view, final boolean z) {
        bEt_(view, new AccessibilityNodeInfoCallback() { // from class: jcj
            @Override // com.huawei.hwcommonmodel.accessibility.AccessibilityNodeInfoCallback
            public final void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                accessibilityNodeInfoCompat.setSelected(z);
            }
        });
    }

    public static void bEG_(View view, CharSequence charSequence) {
        bEH_(view, charSequence, charSequence);
    }

    public static void bEH_(View view, final CharSequence charSequence, final CharSequence charSequence2) {
        if (TextUtils.isEmpty(charSequence2)) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setLongClickContentDescription label ", charSequence2);
        } else {
            bEt_(view, new AccessibilityNodeInfoCallback() { // from class: jcl
                @Override // com.huawei.hwcommonmodel.accessibility.AccessibilityNodeInfoCallback
                public final void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    jcf.bEp_(charSequence, charSequence2, view2, accessibilityNodeInfoCompat);
                }
            });
        }
    }

    static /* synthetic */ void bEp_(CharSequence charSequence, CharSequence charSequence2, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setLongClickContentDescription info is null");
            return;
        }
        accessibilityNodeInfoCompat.setClickable(false);
        accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        accessibilityNodeInfoCompat.setContentDescription(charSequence);
        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(32, charSequence2));
        accessibilityNodeInfoCompat.setFocusable(true);
        accessibilityNodeInfoCompat.setLongClickable(true);
        accessibilityNodeInfoCompat.setImportantForAccessibility(true);
    }

    public static void bED_(View view, final CharSequence charSequence, final boolean z) {
        if (TextUtils.isEmpty(charSequence)) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setContentDescriptionForExpandOrCollapse contentDescription ", charSequence, " isExpand ", Boolean.valueOf(z));
        } else {
            bEt_(view, new AccessibilityNodeInfoCallback() { // from class: jch
                @Override // com.huawei.hwcommonmodel.accessibility.AccessibilityNodeInfoCallback
                public final void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    jcf.bEo_(charSequence, z, view2, accessibilityNodeInfoCompat);
                }
            });
        }
    }

    static /* synthetic */ void bEo_(CharSequence charSequence, boolean z, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "setContentDescriptionForExpandOrCollapse info is null");
            return;
        }
        accessibilityNodeInfoCompat.setClickable(true);
        accessibilityNodeInfoCompat.setFocusable(true);
        accessibilityNodeInfoCompat.setImportantForAccessibility(true);
        accessibilityNodeInfoCompat.setContentDescription(charSequence);
        if (z) {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND);
        } else {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE);
        }
    }

    public static void bEs_(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        if (accessibilityNodeInfoCompat == null || view == null) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "nodeInfoAdjustOrder accessibilityNodeInfoCompat ", accessibilityNodeInfoCompat, " view ", view);
            return;
        }
        bEE_(view, 1);
        accessibilityNodeInfoCompat.removeChild(view);
        accessibilityNodeInfoCompat.addChild(view);
    }

    public static CharSequence a(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "removeSpaceAfterForNumber charSequence ", charSequence);
            return charSequence;
        }
        char[] charArray = String.valueOf(charSequence).toCharArray();
        int length = charArray.length;
        if (length == 0) {
            ReleaseLogUtil.a("R_AccessibilityUtil", "removeSpaceAfterForNumber length is 0");
            return charSequence;
        }
        StringBuilder sb = new StringBuilder();
        Pattern compile = Pattern.compile("\\d");
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (" ".equals(String.valueOf(c))) {
                int i2 = i - 1;
                if (i2 < 0) {
                    sb.append(c);
                } else if (!compile.matcher(String.valueOf(charArray[i2])).find()) {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }
        return sb;
    }
}
