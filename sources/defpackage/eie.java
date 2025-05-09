package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.FragmentForViewPager;
import com.huawei.ui.commonui.utils.ScrollUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class eie {
    private static int b = -1;
    private static int c;
    private static int d;
    private static int e;

    private static int c(int i) {
        return (i < 5 || i > 8) ? i > 8 ? 8 : 4 : i;
    }

    private static List<SingleGridContent> e(int i, List<SingleGridContent> list) {
        return list;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.huawei.health.marketing.datatype.SingleGridContent> b(int r3, int r4, java.util.List<com.huawei.health.marketing.datatype.SingleGridContent> r5) {
        /*
            boolean r0 = defpackage.koq.b(r5)
            r1 = 10
            if (r0 == 0) goto L19
            java.lang.String r3 = "getConfiguredDataIsShown list is empty."
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "LayoutUtils"
            com.huawei.hwlogsmodel.LogUtil.h(r4, r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r1)
            return r3
        L19:
            r0 = 24
            if (r3 == r0) goto L8a
            r0 = 53
            if (r3 == r0) goto L8a
            r0 = 59
            if (r3 == r0) goto L8a
            r0 = 76
            if (r3 == r0) goto L8a
            r0 = 92
            if (r3 == r0) goto L8a
            r0 = 94
            if (r3 == r0) goto L8a
            r0 = 96
            if (r3 == r0) goto L8a
            r0 = 107(0x6b, float:1.5E-43)
            if (r3 == r0) goto L8a
            r0 = 9999(0x270f, float:1.4012E-41)
            if (r3 == r0) goto L8a
            r0 = 35
            if (r3 == r0) goto L8a
            r0 = 36
            if (r3 == r0) goto L8a
            r0 = 39
            if (r3 == r0) goto L8a
            r0 = 40
            if (r3 == r0) goto L84
            r0 = 79
            r2 = 3
            if (r3 == r0) goto L7b
            r0 = 80
            if (r3 == r0) goto L76
            switch(r3) {
                case 6: goto L8a;
                case 7: goto L8a;
                case 8: goto L71;
                case 9: goto L6b;
                default: goto L59;
            }
        L59:
            switch(r3) {
                case 15: goto L8a;
                case 16: goto L8a;
                case 17: goto L8a;
                default: goto L5c;
            }
        L5c:
            switch(r3) {
                case 19: goto L8a;
                case 20: goto L8a;
                case 21: goto L76;
                case 22: goto L8a;
                default: goto L5f;
            }
        L5f:
            switch(r3) {
                case 47: goto L8a;
                case 48: goto L71;
                case 49: goto L6b;
                default: goto L62;
            }
        L62:
            switch(r3) {
                case 82: goto L8a;
                case 83: goto L8a;
                case 84: goto L76;
                case 85: goto L76;
                default: goto L65;
            }
        L65:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r1)
            return r3
        L6b:
            r3 = 4
            java.util.List r3 = e(r5, r3)
            return r3
        L71:
            java.util.List r3 = b(r5, r2)
            return r3
        L76:
            java.util.List r3 = e(r4, r5)
            return r3
        L7b:
            if (r4 != r2) goto L83
            r3 = 6
            java.util.List r3 = e(r5, r3)
            return r3
        L83:
            return r5
        L84:
            r3 = 5
            java.util.List r3 = e(r5, r3)
            return r3
        L8a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eie.b(int, int, java.util.List):java.util.List");
    }

    private static List<SingleGridContent> b(List<SingleGridContent> list, int i) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("LayoutUtils", "getConfiguredPageShowList() list is empty.");
            return arrayList;
        }
        if (list.size() >= i) {
            return list.subList(0, i);
        }
        LogUtil.h("LayoutUtils", "getConfiguredPageShowList() ");
        return arrayList;
    }

    private static List<SingleGridContent> e(List<SingleGridContent> list, int i) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("LayoutUtils", "getHorizonThreeGirdShowList() list is empty.");
            return arrayList;
        }
        if (list.size() >= i) {
            return list.subList(0, i);
        }
        LogUtil.h("LayoutUtils", "getHorizonThreeGirdShowList() ");
        return list;
    }

    public static int e(Context context, int i, int i2) {
        c = 0;
        d = 0;
        e = nsn.c(context, 8.0f);
        if (i != 19) {
            if (i != 20) {
                if (i != 24) {
                    if (i == 35) {
                        return 0;
                    }
                    if (i != 53) {
                        if (i != 59) {
                            if (i != 82 && i != 9999) {
                                if (i == 39 || i == 40) {
                                    c = 2;
                                    d = 4;
                                    e = context.getResources().getDimensionPixelSize(R.dimen._2131362564_res_0x7f0a0304);
                                } else {
                                    if (i != 48) {
                                        if (i != 49) {
                                            switch (i) {
                                                case 6:
                                                    break;
                                                case 7:
                                                    c = 2;
                                                    d = Math.min(i2, 4);
                                                    e = context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                                                    break;
                                                case 8:
                                                    break;
                                                case 9:
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 15:
                                                            break;
                                                        case 16:
                                                        case 17:
                                                            break;
                                                        default:
                                                            a(i, context, i2);
                                                            break;
                                                    }
                                            }
                                        }
                                        c = 2;
                                        d = 4;
                                        e = context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                                    }
                                    c = 2;
                                    d = 3;
                                    e = context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                                }
                                return e(i, c, d, e, i2);
                            }
                        }
                    }
                }
                c = 1;
                d = 2;
                return e(i, c, d, e, i2);
            }
            return context.getResources().getDimensionPixelSize(R.dimen._2131363616_res_0x7f0a0720);
        }
        return context.getResources().getDimensionPixelSize(R.dimen._2131362977_res_0x7f0a04a1);
    }

    private static void a(int i, Context context, int i2) {
        if (i == 10) {
            c = 5;
            d = c(i2);
            e = 0;
            return;
        }
        if (i != 36) {
            if (i != 47) {
                if (i != 92) {
                    if (i == 94) {
                        c = 2;
                        d = 5;
                        e = context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                        return;
                    }
                    if (i == 96) {
                        c = 2;
                        d = 4;
                        e = context.getResources().getDimensionPixelSize(R.dimen._2131362573_res_0x7f0a030d);
                        return;
                    }
                    if (i != 116) {
                        if (i != 79) {
                            if (i != 80) {
                                switch (i) {
                                    case 21:
                                        c = 3;
                                        d = 5;
                                        e = context.getResources().getDimensionPixelSize(R.dimen._2131362589_res_0x7f0a031d);
                                        break;
                                    case 22:
                                        break;
                                    case 23:
                                        break;
                                    default:
                                        c = 1;
                                        d = 1;
                                        break;
                                }
                                return;
                            }
                            c = 2;
                            d = 5;
                            e = context.getResources().getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306);
                            return;
                        }
                    }
                    c = 4;
                    d = 8;
                    e = 0;
                    return;
                }
                c = 3;
                d = 5;
                e = context.getResources().getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306);
                return;
            }
            c = 4;
            d = 7;
            e = e();
            return;
        }
        c = Math.min(i2, 5);
        d = Math.min(i2, 8);
        e = 0;
    }

    private static int e(int i, int i2, int i3, int i4, int i5) {
        Context context = BaseApplication.getContext();
        int c2 = c();
        if (i == 116) {
            c2 -= (BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446) + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue()) + (BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446) + ((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
        }
        int i6 = c2;
        boolean ag = nsn.ag(BaseApplication.getActivity());
        if (i2 < 1 || i3 < 1) {
            return i6;
        }
        int a2 = a(i, context, ag, i5);
        if (i == 22) {
            return e(context, i, ag, i5, i6, i3, i2, i4);
        }
        if (i == 47) {
            return d(ag, i5, i6);
        }
        if (i == 115) {
            return (i6 - ((i2 - 1) * i4)) / i2;
        }
        if (ag) {
            return ((i6 - ((i3 - 1) * nrr.b(context))) - a2) / i3;
        }
        return ((i6 - ((i2 - 1) * i4)) - a2) / i2;
    }

    public static int c() {
        return a(BaseApplication.getActivity());
    }

    public static int a(Context context) {
        if (!(context instanceof Activity)) {
            LogUtil.h("LayoutUtils", "context is not activity");
            context = BaseApplication.getActivity();
        }
        if (context == null) {
            LogUtil.h("LayoutUtils", "getActivity = null");
            context = BaseApplication.getContext();
        }
        int c2 = ScrollUtil.c(context);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        float f = c2;
        try {
            float dimension = context.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
            return (int) ((((f - dimension) - context.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d)) - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue());
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("LayoutUtils", "getItemsTotalWidth dimen id is not found.");
            return c2;
        }
    }

    public static int d() {
        Context activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h("LayoutUtils", "getActivity = null");
            activity = BaseApplication.getContext();
        }
        int c2 = ScrollUtil.c(activity);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        try {
            return (c2 - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue();
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("LayoutUtils", "getItemsTotalWidth dimen id is not found.");
            return c2;
        }
    }

    public static int c(int i, boolean z, int i2) {
        int i3;
        if (i != 19 && i != 20 && i != 24 && i != 53 && i != 59 && i != 9999) {
            if (i != 39 && i != 40) {
                if (i == 48) {
                    return 1;
                }
                if (i != 49) {
                    if (i != 82 && i != 83) {
                        switch (i) {
                            case 6:
                                break;
                            case 7:
                                if (z) {
                                    i3 = Math.min(i2, 4);
                                    return i3;
                                }
                                i3 = 2;
                                return i3;
                            case 8:
                                return z ? 3 : 1;
                            case 9:
                                break;
                            default:
                                switch (i) {
                                    case 15:
                                        break;
                                    case 16:
                                    case 17:
                                        break;
                                    default:
                                        return b(i, z, i2);
                                }
                                i3 = 2;
                                return i3;
                        }
                    }
                }
            }
            if (z) {
                return 4;
            }
            i3 = 2;
            return i3;
        }
        if (!z) {
            return 1;
        }
        i3 = 2;
        return i3;
    }

    private static int b(int i, boolean z, int i2) {
        if (i == 10) {
            return z ? 8 : 5;
        }
        if (i != 47) {
            if (i != 92) {
                if (i != 116) {
                    if (i == 35) {
                        return z ? 5 : 3;
                    }
                    if (i == 36) {
                        return Math.min(i2, z ? 8 : 5);
                    }
                    if (i != 79 && i != 80 && i != 84 && i != 85) {
                        switch (i) {
                            case 21:
                                break;
                            case 22:
                                break;
                            case 23:
                                break;
                            default:
                                return 1;
                        }
                    }
                }
                return z ? 8 : 4;
            }
            return z ? 5 : 3;
        }
        return z ? 7 : 4;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int b(int i, boolean z) {
        int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        if (i != 35) {
            if (i != 36) {
                if (i != 39 && i != 40) {
                    if (i != 53) {
                        if (i != 59) {
                            if (i != 92) {
                                if (i != 94) {
                                    if (i != 96) {
                                        if (i != 113) {
                                            if (i != 116 && i != 9999) {
                                                if (i != 79 && i != 80) {
                                                    switch (i) {
                                                        case 6:
                                                        case 8:
                                                        case 10:
                                                            break;
                                                        case 7:
                                                        case 9:
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 15:
                                                                    break;
                                                                case 16:
                                                                case 17:
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case 19:
                                                                        case 20:
                                                                            break;
                                                                        case 21:
                                                                            break;
                                                                        case 22:
                                                                            return 0;
                                                                        case 23:
                                                                        case 24:
                                                                            break;
                                                                        default:
                                                                            switch (i) {
                                                                                case 47:
                                                                                    return 0;
                                                                                case 48:
                                                                                    break;
                                                                                case 49:
                                                                                    break;
                                                                                default:
                                                                                    switch (i) {
                                                                                        case 82:
                                                                                        case 83:
                                                                                            break;
                                                                                        case 84:
                                                                                        case 85:
                                                                                            break;
                                                                                        default:
                                                                                            return 0;
                                                                                    }
                                                                                    return dimensionPixelSize;
                                                                            }
                                                                    }
                                                            }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!z) {
                            dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                        }
                        return dimensionPixelSize;
                    }
                }
            }
            if (!z) {
                dimensionPixelSize = 0;
            }
            return dimensionPixelSize;
        }
        if (!z) {
            dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362564_res_0x7f0a0304);
        }
        return dimensionPixelSize;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x003e, code lost:
    
        if (r1 != 83) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(int r1) {
        /*
            r0 = 35
            if (r1 == r0) goto L51
            r0 = 36
            if (r1 == r0) goto L41
            r0 = 39
            if (r1 == r0) goto L51
            r0 = 40
            if (r1 == r0) goto L51
            r0 = 53
            if (r1 == r0) goto L41
            r0 = 59
            if (r1 == r0) goto L41
            r0 = 92
            if (r1 == r0) goto L51
            r0 = 116(0x74, float:1.63E-43)
            if (r1 == r0) goto L41
            r0 = 9999(0x270f, float:1.4012E-41)
            if (r1 == r0) goto L41
            r0 = 79
            if (r1 == r0) goto L51
            r0 = 80
            if (r1 == r0) goto L51
            switch(r1) {
                case 6: goto L41;
                case 7: goto L41;
                case 8: goto L51;
                case 9: goto L41;
                case 10: goto L51;
                default: goto L2f;
            }
        L2f:
            switch(r1) {
                case 15: goto L41;
                case 16: goto L41;
                case 17: goto L41;
                default: goto L32;
            }
        L32:
            switch(r1) {
                case 19: goto L51;
                case 20: goto L51;
                case 21: goto L51;
                case 22: goto L51;
                case 23: goto L41;
                case 24: goto L51;
                default: goto L35;
            }
        L35:
            switch(r1) {
                case 47: goto L51;
                case 48: goto L51;
                case 49: goto L41;
                default: goto L38;
            }
        L38:
            r0 = 82
            if (r1 == r0) goto L41
            r0 = 83
            if (r1 == r0) goto L41
            goto L51
        L41:
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            android.content.res.Resources r1 = r1.getResources()
            r0 = 2131362008(0x7f0a00d8, float:1.8343784E38)
            int r1 = r1.getDimensionPixelSize(r0)
            goto L52
        L51:
            r1 = 0
        L52:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eie.a(int):int");
    }

    public static int[] a(int i, int i2) {
        int[] iArr = new int[4];
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (i == 24) {
            iArr[0] = 0;
            iArr[2] = 0;
            iArr[1] = nsn.c(BaseApplication.getContext(), 4.0f);
            iArr[3] = nsn.c(BaseApplication.getContext(), 4.0f);
        } else if (i == 22) {
            iArr[1] = 0;
            iArr[3] = 0;
            if (e(i2)) {
                iArr[0] = 0;
                iArr[2] = 0;
            } else {
                iArr[0] = ((Integer) safeRegionWidth.first).intValue();
                iArr[2] = ((Integer) safeRegionWidth.second).intValue();
            }
        } else if (i == 47) {
            iArr[0] = 0;
            iArr[2] = 0;
            iArr[1] = 0;
            iArr[3] = 0;
        } else {
            iArr[1] = 0;
            iArr[3] = 0;
            if (e(i2)) {
                iArr[0] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
                iArr[2] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
            } else {
                iArr[0] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) + ((Integer) safeRegionWidth.first).intValue();
                iArr[2] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d) + ((Integer) safeRegionWidth.second).intValue();
            }
        }
        return iArr;
    }

    public static long b() {
        return System.currentTimeMillis();
    }

    public static boolean b(String str) {
        String e2 = e(str);
        if (TextUtils.isEmpty(e2.trim())) {
            LogUtil.h("LayoutUtils", "isNeedLogin linkValue is null");
            return false;
        }
        String e3 = nsa.e(e2);
        if (e2.contains("localeUrl:moduleName=openService")) {
            return false;
        }
        String queryParameter = Uri.parse(nsa.g(e2)).getQueryParameter("h5pro");
        if (!TextUtils.equals(e3, "3") && !TextUtils.equals(e3, "2")) {
            if (!TextUtils.equals(e3, "1") && !TextUtils.isEmpty(e3) && !TextUtils.equals(e3, "0")) {
                return (TextUtils.equals(e3, "4") && (str.contains("huaweischeme://") || str.contains("huaweihealth://") || str.contains("heart_scheme://") || str.contains("vascular_scheme://"))) || str.contains("needLogin");
            }
            if (!"true".equals(queryParameter) && !d(str)) {
                return false;
            }
        }
        return true;
    }

    private static String e(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("LayoutUtils", "decodeUrl() UnsupportedEncodingException");
            return "";
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("LayoutUtils", "decodeUrl() IllegalArgumentException");
            return "";
        }
    }

    private static boolean d(String str) {
        if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("LayoutUtils", "isNotSupportBrowseUrl url is null!");
            return true;
        }
        if (str.contains("vmall.com") || str.contains("needLogin")) {
            return true;
        }
        Iterator it = new ArrayList(Arrays.asList(BaseApplication.getContext().getResources().getStringArray(R.array._2130968686_res_0x7f04006e))).iterator();
        while (it.hasNext()) {
            if (str.contains((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean e(int i) {
        return i == 4010 || i == 4033 || i == 4035 || i == 4011 || i == 4034 || i == 4028 || i == 4032 || i == 4009 || i == 8000 || i == 4029 || i == 4041 || i == 4046 || i == 4047 || i == 4048 || i == 4018 || i == 4017 || i == 4168 || b(i);
    }

    public static boolean b(int i) {
        if (i != 4085 && (i < 50000 || i > 52000)) {
            return false;
        }
        LogUtil.a("LayoutUtils", "isAllCourseRecommend() is true");
        return true;
    }

    public static void d(int i) {
        if (b != i) {
            b = i;
        }
    }

    public static int b(Context context, int i) {
        if (!(context instanceof Activity)) {
            LogUtil.h("LayoutUtils", "getViewGroupWidth context is not activity");
            context = BaseApplication.getActivity();
        }
        if (context == null) {
            LogUtil.h("LayoutUtils", "getViewGroupWidth getActivity = null");
            context = BaseApplication.getContext();
        }
        return e(i) ? d() : ScrollUtil.c(context);
    }

    private static int e() {
        return nsn.r() ? 8 : 0;
    }

    private static int a(int i, Context context, boolean z, int i2) {
        if (i == 21) {
            return context.getResources().getDimensionPixelSize(R.dimen._2131362575_res_0x7f0a030f);
        }
        if (i == 80 || i == 79 || i == 92) {
            return context.getResources().getDimensionPixelSize(R.dimen._2131362584_res_0x7f0a0318);
        }
        if ((i == 39 || i == 40) && ((z && i2 > 4) || (!z && i2 > 2))) {
            return context.getResources().getDimensionPixelSize(R.dimen._2131362564_res_0x7f0a0304);
        }
        return 0;
    }

    private static int d(boolean z, int i, int i2) {
        float f;
        float f2;
        if (z) {
            if (i <= 7) {
                return i2 / i;
            }
            f = i2;
            f2 = 7.5f;
        } else if (i > 4) {
            if (nsn.r()) {
                f = i2;
                f2 = 2.5f;
            } else {
                f = i2;
                f2 = 4.5f;
            }
        } else {
            return i2 / i;
        }
        return (int) (f / f2);
    }

    private static int e(Context context, int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        float f;
        float f2;
        int dimension = (int) context.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d);
        if (i == 22) {
            i7 = (int) context.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d);
            i8 = (int) context.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
        } else {
            i7 = 0;
            i8 = 0;
        }
        if (z) {
            if (i2 > 7) {
                f = (i3 - (i4 * nrr.b(context))) + i8 + dimension;
                f2 = 7.5f;
            } else {
                return (((i3 - ((i4 - 1) * nrr.b(context))) + i7) + i8) / i4;
            }
        } else {
            if (i2 <= 4) {
                return (((i3 - ((i5 - 1) * i6)) + i7) + i8) / i5;
            }
            f = (i3 - (i5 * i6)) + i8 + dimension;
            f2 = 4.5f;
        }
        return (int) (f / f2);
    }

    public static boolean alE_(View view) {
        int visibility = view.getVisibility();
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        return visibility == 0 && rect.left == 0 && rect.top >= 0 && rect.top <= view.getHeight() / 2 && view.getLocalVisibleRect(rect);
    }

    public static boolean alF_(View view, int i) {
        return i == 0 || view.getWindowVisibility() == 0;
    }

    public static boolean alG_(View view) {
        int visibility = view.getVisibility();
        Rect rect = new Rect();
        return visibility == 0 && view.getLocalVisibleRect(rect) && rect.right - rect.left >= view.getWidth() && rect.bottom - rect.top >= view.getHeight();
    }

    public static boolean a() {
        Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
        if (!(wa_ instanceof BaseActivity) || !wa_.getLocalClassName().contains("MainActivity")) {
            LogUtil.h("LayoutUtils", "topActivity is not MainActivity");
            return false;
        }
        Iterator it = new ArrayList(((BaseActivity) wa_).getSupportFragmentManager().getFragments()).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Fragment fragment = (Fragment) it.next();
            if (fragment instanceof FragmentForViewPager) {
                String fragmentTag = ((FragmentForViewPager) fragment).getFragmentTag();
                if (fragment.getUserVisibleHint() && !TextUtils.isEmpty(fragmentTag) && fragmentTag.contains("HomeFragment")) {
                    LogUtil.c("LayoutUtils", "HomeFragment is visible");
                    return true;
                }
            }
        }
        LogUtil.c("LayoutUtils", "HomeFragment is not visible");
        return false;
    }
}
