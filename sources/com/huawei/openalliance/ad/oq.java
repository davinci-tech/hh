package com.huawei.openalliance.ad;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class oq {
    private static oq c;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private final int l = 100;
    private final int m;
    private final gc n;

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7394a = new byte[0];
    private static final byte[] b = new byte[0];
    private static Map<String, String[]> d = new ConcurrentHashMap();

    public void b(final Context context) {
        com.huawei.openalliance.ad.utils.m.j(new Runnable() { // from class: com.huawei.openalliance.ad.oq.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!oq.this.n.ab()) {
                        oq.d.clear();
                        ho.b("CCP", "disabled");
                        return;
                    }
                    int ac = oq.this.n.ac() * 1000;
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - oq.this.n.cc() <= ac && ac != 0) {
                        ho.b("CCP", "check failed in %s", Integer.valueOf(ac));
                        return;
                    }
                    ho.b("CCP", "check pass");
                    oq.this.n.l(currentTimeMillis);
                    Context context2 = context;
                    if (context2 instanceof Activity) {
                        oq.this.a((Activity) context2);
                    } else {
                        ho.b("CCP", "not target");
                    }
                } catch (Throwable th) {
                    ho.d("CCP", "process error: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    public String a() {
        if (this.n.ab()) {
            ho.a("CCP", "get AutoContentBundle");
            return com.huawei.openalliance.ad.utils.be.b(d);
        }
        ho.a("CCP", "get AutoContentBundle off");
        d.clear();
        return null;
    }

    private static oq c(Context context) {
        oq oqVar;
        synchronized (f7394a) {
            if (c == null) {
                c = new oq(context);
            }
            oqVar = c;
        }
        return oqVar;
    }

    private String[] b(Activity activity) {
        this.e = 0;
        String[] strArr = new String[0];
        List<String> a2 = a(activity.getWindow().getDecorView(), this.j);
        if (!com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return (String[]) a2.toArray(new String[a2.size()]);
        }
        ho.a("CCP", "get contentAuto is null");
        return strArr;
    }

    private boolean a(Map<String, List<String>> map, String str, Map<String, String[]> map2) {
        if (!map.containsKey(str)) {
            return false;
        }
        List<String> list = map.get(str);
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            ho.a("CCP", "get %s is null ", str);
            return false;
        }
        map2.put(str, (String[]) list.toArray(new String[list.size()]));
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(Map<Integer, String> map, Map<String, List<String>> map2, View view) {
        char c2;
        int i;
        int i2;
        int id = view.getId();
        if (!map.containsKey(Integer.valueOf(id))) {
            ho.b("CCP", "invalid id");
            return;
        }
        TextView textView = (TextView) view;
        String obj = textView.getText() != null ? textView.getText().toString() : "";
        int length = obj.length();
        String str = map.get(Integer.valueOf(id));
        List<String> arrayList = new ArrayList<>();
        if (map2.containsKey(str)) {
            arrayList = map2.get(str);
        }
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        String str2 = str != null ? str : "";
        str2.hashCode();
        switch (str2.hashCode()) {
            case 50511102:
                if (str2.equals("category")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 110371416:
                if (str2.equals("title")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 951530617:
                if (str2.equals("content")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1300380478:
                if (str2.equals(Constants.AUTOCONTENT_SUBCATEGORY)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            i = this.h;
            i2 = length + i;
            this.h = i2;
        } else if (c2 == 1) {
            i = this.g;
            i2 = length + i;
            this.g = i2;
        } else if (c2 == 2) {
            i = this.f;
            i2 = length + i;
            this.f = i2;
        } else if (c2 != 3) {
            i = 0;
            i2 = 0;
        } else {
            i = this.i;
            i2 = length + i;
            this.i = i2;
        }
        int i3 = this.m;
        if (i < i3) {
            if (i2 >= i3) {
                int length2 = (obj.length() + this.m) - i2;
                if (!TextUtils.isEmpty(obj.substring(0, length2))) {
                    arrayList.add(obj.substring(0, length2));
                }
            } else if (!TextUtils.isEmpty(obj)) {
                arrayList.add(obj);
            }
        }
        map2.put(str2, arrayList);
    }

    private void a(View view, Map<Integer, String> map, Map<String, List<String>> map2, int i) {
        String str;
        this.k++;
        if (view instanceof ViewGroup) {
            int i2 = i + 1;
            if (i2 < 100) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                    View childAt = viewGroup.getChildAt(i3);
                    if (childAt instanceof TextView) {
                        a(map, map2, childAt);
                    }
                    if (this.k >= 100) {
                        str = "clctCfgContentRur outer round " + this.k;
                    } else {
                        a(childAt, map, map2, i2);
                    }
                }
                return;
            }
            str = "clctCfgContentDepth outer round 100";
            ho.a("CCP", str);
        }
    }

    private void a(Activity activity, Map<Integer, String> map) {
        HashMap hashMap = new HashMap();
        this.f = 0;
        this.h = 0;
        this.i = 0;
        this.g = 0;
        this.k = 0;
        a(activity.getWindow().getDecorView(), map, hashMap, this.j);
        if (com.huawei.openalliance.ad.utils.bl.a(hashMap)) {
            ho.a("CCP", "no get cfg, getAutoContent");
            String[] b2 = b(activity);
            if (b2 == null || b2.length <= 0) {
                return;
            }
            d.put(Constants.AUTOCONTENT_CONTENT_AUTO, b2);
            return;
        }
        a(hashMap, "title", d);
        if (!a(hashMap, "content", d)) {
            ho.a("CCP", "no get cfg content, getAutoContent");
            String[] b3 = b(activity);
            if (b3 != null && b3.length > 0) {
                d.put(Constants.AUTOCONTENT_CONTENT_AUTO, b3);
            }
        }
        a(hashMap, "category", d);
        a(hashMap, Constants.AUTOCONTENT_SUBCATEGORY, d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity) {
        String simpleName = activity.getClass().getSimpleName();
        ho.a("CCP", "getActivityInfo-name: %s", activity.getClass().getSimpleName());
        if (!com.huawei.openalliance.ad.utils.cz.b(simpleName)) {
            d.clear();
            d.put("activityName", new String[]{simpleName});
        }
        Map<Integer, String> a2 = com.huawei.openalliance.ad.utils.u.a(activity, this.n.ae());
        if (!com.huawei.openalliance.ad.utils.bl.a(a2)) {
            a(activity, a2);
            return;
        }
        ho.b("CCP", "auto process");
        String[] b2 = b(activity);
        if (b2 == null || b2.length <= 0) {
            return;
        }
        d.put(Constants.AUTOCONTENT_CONTENT_AUTO, b2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x008f, code lost:
    
        if (android.text.TextUtils.isEmpty(r4) == false) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<java.lang.String> a(android.view.View r8, int r9) {
        /*
            r7 = this;
            if (r8 != 0) goto L4
            r8 = 0
            return r8
        L4:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = r8 instanceof android.view.ViewGroup
            if (r1 == 0) goto Lbc
            int r9 = r9 + 1
            r1 = 100
            if (r9 < r1) goto L1b
            java.lang.String r8 = "CCP"
            java.lang.String r9 = "autoContentRurDepth outer round 100"
            com.huawei.openalliance.ad.ho.a(r8, r9)
            return r0
        L1b:
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            r9 = 0
            r1 = r9
        L1f:
            int r2 = r8.getChildCount()
            if (r1 >= r2) goto Lbc
            android.view.View r2 = r8.getChildAt(r1)
            boolean r3 = r2 instanceof com.huawei.openalliance.ad.views.PPSNativeView
            if (r3 == 0) goto L2f
            goto Lb8
        L2f:
            boolean r3 = r2 instanceof android.widget.TextView
            if (r3 == 0) goto L99
            boolean r3 = r2.isShown()
            if (r3 == 0) goto L99
            byte[] r3 = com.huawei.openalliance.ad.oq.b
            monitor-enter(r3)
            r4 = r2
            android.widget.TextView r4 = (android.widget.TextView) r4     // Catch: java.lang.Throwable -> L96
            java.lang.CharSequence r4 = r4.getText()     // Catch: java.lang.Throwable -> L96
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L96
            int r5 = r7.e     // Catch: java.lang.Throwable -> L96
            int r6 = r7.m     // Catch: java.lang.Throwable -> L96
            if (r5 >= r6) goto L7e
            int r6 = r4.length()     // Catch: java.lang.Throwable -> L96
            int r5 = r5 + r6
            int r6 = r7.m     // Catch: java.lang.Throwable -> L96
            if (r5 <= r6) goto L7e
            int r5 = r7.e     // Catch: java.lang.Throwable -> L96
            int r6 = r4.length()     // Catch: java.lang.Throwable -> L96
            int r5 = r5 + r6
            r7.e = r5     // Catch: java.lang.Throwable -> L96
            int r5 = r4.length()     // Catch: java.lang.Throwable -> L96
            int r6 = r7.e     // Catch: java.lang.Throwable -> L96
            int r5 = r5 - r6
            int r6 = r7.m     // Catch: java.lang.Throwable -> L96
            int r5 = r5 + r6
            int r6 = r4.length()     // Catch: java.lang.Throwable -> L96
            if (r5 <= r6) goto L73
            int r5 = r4.length()     // Catch: java.lang.Throwable -> L96
        L73:
            java.lang.String r4 = r4.substring(r9, r5)     // Catch: java.lang.Throwable -> L96
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L96
            if (r5 != 0) goto L94
            goto L91
        L7e:
            int r5 = r7.e     // Catch: java.lang.Throwable -> L96
            int r6 = r7.m     // Catch: java.lang.Throwable -> L96
            if (r5 >= r6) goto L94
            int r6 = r4.length()     // Catch: java.lang.Throwable -> L96
            int r5 = r5 + r6
            r7.e = r5     // Catch: java.lang.Throwable -> L96
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L96
            if (r5 != 0) goto L94
        L91:
            r0.add(r4)     // Catch: java.lang.Throwable -> L96
        L94:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L96
            goto L99
        L96:
            r8 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L96
            throw r8
        L99:
            int r3 = r7.e
            int r4 = r7.m
            if (r3 < r4) goto Laf
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.String r9 = "CCP"
            java.lang.String r1 = "autoContentSize maxSize %s"
            com.huawei.openalliance.ad.ho.a(r9, r1, r8)
            return r0
        Laf:
            int r3 = r7.j
            java.util.List r2 = r7.a(r2, r3)
            r0.addAll(r2)
        Lb8:
            int r1 = r1 + 1
            goto L1f
        Lbc:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.oq.a(android.view.View, int):java.util.List");
    }

    public static oq a(Context context) {
        return c(context);
    }

    private oq(Context context) {
        gc b2 = fh.b(context);
        this.n = b2;
        this.m = b2.ad();
    }
}
