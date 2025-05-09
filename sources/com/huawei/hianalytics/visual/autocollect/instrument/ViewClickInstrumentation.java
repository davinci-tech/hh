package com.huawei.hianalytics.visual.autocollect.instrument;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.huawei.health.R;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.b;
import com.huawei.hianalytics.visual.f0;
import com.huawei.hianalytics.visual.g;
import com.huawei.hianalytics.visual.h0;
import com.huawei.hianalytics.visual.m0;
import com.huawei.hianalytics.visual.n0;
import com.huawei.hianalytics.visual.o0;
import com.huawei.hianalytics.visual.u0;
import java.lang.reflect.Field;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ViewClickInstrumentation {

    /* loaded from: classes8.dex */
    public static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final String f3909a;

        public a(String str) {
            this.f3909a = str;
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0067, code lost:
        
            if (com.huawei.hianalytics.visual.o0.g(r1) == false) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0069, code lost:
        
            return;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r9 = this;
                boolean r0 = com.huawei.hianalytics.visual.o0.a()
                if (r0 != 0) goto Ld4
                java.lang.Class<android.widget.TabHost> r0 = android.widget.TabHost.class
                boolean r0 = com.huawei.hianalytics.visual.o0.c(r0)
                if (r0 == 0) goto L10
                goto Ld4
            L10:
                org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lcd
                r0.<init>()     // Catch: java.lang.Exception -> Lcd
                java.lang.String r1 = "$view_type"
                java.lang.String r2 = "TabHost"
                r0.put(r1, r2)     // Catch: java.lang.Exception -> Lcd
                java.lang.String r1 = r9.f3909a     // Catch: java.lang.Exception -> Lcd
                boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> Lcd
                r3 = 0
                if (r2 == 0) goto L26
                goto L45
            L26:
                com.huawei.hianalytics.visual.p0.c()     // Catch: java.lang.Exception -> Lcd
                android.view.View[] r2 = com.huawei.hianalytics.visual.p0.a()     // Catch: java.lang.Exception -> Lcd
                int r4 = r2.length     // Catch: java.lang.Exception -> L45
                r5 = 0
            L2f:
                if (r5 >= r4) goto L45
                r6 = r2[r5]     // Catch: java.lang.Exception -> L45
                java.lang.Class r7 = r6.getClass()     // Catch: java.lang.Exception -> L45
                java.lang.Class<?> r8 = com.huawei.hianalytics.visual.p0.i     // Catch: java.lang.Exception -> L45
                if (r7 == r8) goto L42
                android.view.View r6 = com.huawei.hianalytics.visual.p0.a(r6, r1)     // Catch: java.lang.Exception -> L45
                if (r6 == 0) goto L42
                goto L46
            L42:
                int r5 = r5 + 1
                goto L2f
            L45:
                r6 = r3
            L46:
                if (r6 != 0) goto L49
                return
            L49:
                r1 = r3
                r2 = r6
            L4b:
                if (r1 != 0) goto L61
                if (r2 == 0) goto L61
                android.view.ViewParent r4 = r2.getParent()     // Catch: java.lang.Exception -> Lcd
                if (r4 == 0) goto L61
                android.view.ViewParent r2 = r2.getParent()     // Catch: java.lang.Exception -> Lcd
                android.view.View r2 = (android.view.View) r2     // Catch: java.lang.Exception -> Lcd
                boolean r4 = r2 instanceof android.widget.TabHost     // Catch: java.lang.Exception -> Lcd
                if (r4 == 0) goto L4b
                r1 = r2
                goto L4b
            L61:
                if (r1 == 0) goto L6a
                boolean r1 = com.huawei.hianalytics.visual.o0.g(r1)     // Catch: java.lang.Exception -> Lcd
                if (r1 == 0) goto L6a
                return
            L6a:
                java.lang.String r1 = com.huawei.hianalytics.visual.o0.c(r6)     // Catch: java.lang.Exception -> Lcd
                boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> Lcd
                if (r2 == 0) goto L76
                java.lang.String r1 = r9.f3909a     // Catch: java.lang.Exception -> Lcd
            L76:
                java.lang.String r2 = "$view_content"
                r0.put(r2, r1)     // Catch: java.lang.Exception -> Lcd
                android.content.Context r1 = r6.getContext()     // Catch: java.lang.Exception -> Lcd
                if (r1 != 0) goto L82
                return
            L82:
                boolean r2 = r1 instanceof android.app.Activity     // Catch: java.lang.Exception -> Lcd
                if (r2 == 0) goto L89
                r3 = r1
                android.app.Activity r3 = (android.app.Activity) r3     // Catch: java.lang.Exception -> Lcd
            L89:
                if (r3 != 0) goto L8c
                return
            L8c:
                java.lang.Class r1 = r3.getClass()     // Catch: java.lang.Exception -> Lcd
                com.huawei.hianalytics.visual.c r2 = com.huawei.hianalytics.visual.b.a()     // Catch: java.lang.Exception -> Lcd
                boolean r1 = r2.i(r1)     // Catch: java.lang.Exception -> Lcd
                if (r1 == 0) goto L9b
                return
            L9b:
                org.json.JSONObject r1 = com.huawei.hianalytics.visual.h0.b(r3)     // Catch: java.lang.Exception -> Lcd
                com.huawei.hianalytics.visual.n0.a(r1, r0)     // Catch: java.lang.Exception -> Lcd
                java.lang.Object r1 = com.huawei.hianalytics.visual.f0.a(r6, r3)     // Catch: java.lang.Exception -> Lcd
                if (r1 != 0) goto La9
                return
            La9:
                java.lang.Class r2 = r1.getClass()     // Catch: java.lang.Exception -> Lcd
                com.huawei.hianalytics.visual.c r4 = com.huawei.hianalytics.visual.b.a()     // Catch: java.lang.Exception -> Lcd
                boolean r2 = r4.i(r2)     // Catch: java.lang.Exception -> Lcd
                if (r2 == 0) goto Lb8
                return
            Lb8:
                org.json.JSONObject r1 = com.huawei.hianalytics.visual.f0.a(r1, r3)     // Catch: java.lang.Exception -> Lcd
                com.huawei.hianalytics.visual.n0.a(r1, r0)     // Catch: java.lang.Exception -> Lcd
                com.huawei.hianalytics.visual.u0 r1 = com.huawei.hianalytics.visual.o0.a(r3, r6, r0)     // Catch: java.lang.Exception -> Lcd
                com.huawei.hianalytics.visual.c r2 = com.huawei.hianalytics.visual.b.a()     // Catch: java.lang.Exception -> Lcd
                java.lang.String r3 = "$ViewClick"
                r2.a(r3, r0, r1)     // Catch: java.lang.Exception -> Lcd
                goto Ld4
            Lcd:
                java.lang.String r0 = "HAVCI"
                java.lang.String r1 = "fail to report TabHost click data"
                com.huawei.hianalytics.core.log.HiLog.w(r0, r1)
            Ld4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation.a.run():void");
        }
    }

    public static void a(ExpandableListView expandableListView, View view) {
        g a2;
        if (expandableListView == null || view == null || o0.a() || o0.a(expandableListView, (Class<?>) ExpandableListView.class) || o0.a(view, (Class<?>) null) || (a2 = o0.a((Object) null, expandableListView, (Class<?>) ExpandableListView.class)) == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$view_type", "ExpandableListView");
            String e = o0.e(view);
            jSONObject.put("$view_id", e);
            jSONObject.put("$event_name", e);
            jSONObject.put("$view_content", a(view));
            n0.a(h0.b(a2.f3918a), jSONObject);
            n0.a(f0.a(a2.b, a2.f3918a), jSONObject);
            u0 a3 = o0.a(a2.f3918a, view, jSONObject);
            JSONObject jSONObject2 = (JSONObject) view.getTag(R.id.hianalytics_view_custom_property_tag);
            if (jSONObject2 != null) {
                jSONObject.put("$custom_property", jSONObject2);
            }
            b.a().a("$ViewClick", jSONObject, a3);
        } catch (Exception unused) {
            HiLog.w("HAVCI", "fail to report click on ExpandableListView event");
        }
    }

    public static void b(View view) {
        g a2;
        boolean isPressed = view.isPressed();
        if (o0.a() || o0.a(view, (Class<?>) null) || !o0.a(view, isPressed) || com.huawei.hianalytics.visual.a.a(view) || (a2 = o0.a((Object) null, view, view.getClass())) == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$view_id", o0.e(view));
            jSONObject.put("$event_name", o0.e(view));
            String b = o0.b(view);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("$view_checked", b);
            }
            u0 d = o0.d(view);
            jSONObject.put("$view_type", d.c);
            jSONObject.put("$view_content", o0.a(d.d));
            n0.a(h0.b(a2.f3918a), jSONObject);
            n0.a(f0.a(a2.b, a2.f3918a), jSONObject);
            a(view, jSONObject);
            b.a().a("$ViewClick", jSONObject, o0.a(a2.f3918a, view, jSONObject));
        } catch (Exception unused) {
            HiLog.w("HAVCI", "fail to report click event");
        }
    }

    public static void childClickOnExpandableListView(ExpandableListView expandableListView, View view, int i, int i2) {
        groupClickOnExpandableListView(expandableListView, view, -1);
    }

    public static void clickOnDialog(final DialogInterface dialogInterface, final int i) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                ViewClickInstrumentation.a(dialogInterface, i);
            }
        });
    }

    public static void clickOnDrawerClosed(View view) {
        if (view == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$view_content", "Close");
            b.a().a(view, jSONObject);
            clickOnView(view);
        } catch (Exception unused) {
            HiLog.w("HAVCI", "fail to report drawer closed click data");
        }
    }

    public static void clickOnDrawerOpened(View view) {
        if (view == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$view_content", "Open");
            b.a().a(view, jSONObject);
            clickOnView(view);
        } catch (Exception unused) {
            HiLog.w("HAVCI", "fail to report drawer opened click data");
        }
    }

    public static void clickOnListView(final AdapterView<?> adapterView, final View view, int i) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ViewClickInstrumentation.a(adapterView, view);
            }
        });
    }

    public static void clickOnMenuItem(MenuItem menuItem) {
        clickOnMenuItem(null, menuItem);
    }

    public static void clickOnRadioGroup(final RadioGroup radioGroup, final int i) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ViewClickInstrumentation.a(radioGroup, i);
            }
        });
    }

    public static void clickOnTabHost(String str) {
        m0.a().execute(new a(str));
    }

    public static void clickOnView(final View view) {
        if (view == null) {
            return;
        }
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                ViewClickInstrumentation.b(view);
            }
        });
    }

    public static void groupClickOnExpandableListView(final ExpandableListView expandableListView, final View view, int i) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ViewClickInstrumentation.a(expandableListView, view);
            }
        });
    }

    public static void selectClickOnTabLayout(final Object obj, final Object obj2) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                ViewClickInstrumentation.a(obj, obj2);
            }
        });
    }

    public static void clickOnMenuItem(final Object obj, final MenuItem menuItem) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ViewClickInstrumentation.a(menuItem, obj);
            }
        });
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:25|(1:27)(16:114|115|116|(4:118|119|(2:121|122)(2:124|(2:126|127)(2:128|(1:130)(1:131)))|123)|135|29|(2:31|(1:33))|(2:35|(1:37))|38|39|40|41|42|43|(1:45)|(1:105)(8:47|48|(3:96|97|(5:99|51|(9:56|57|(2:87|(2:89|(1:91)(5:92|61|(1:86)|(3:68|69|(5:71|(1:73)(1:79)|74|(1:76)(1:78)|77)(1:80))|(1:84)))(1:93))(1:59)|60|61|(1:63)|86|(4:66|68|69|(0)(0))|(2:82|84))|53|55))|50|51|(0)|53|55))|28|29|(0)|(0)|38|39|40|41|42|43|(0)|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(9:(1:27)(16:114|115|116|(4:118|119|(2:121|122)(2:124|(2:126|127)(2:128|(1:130)(1:131)))|123)|135|29|(2:31|(1:33))|(2:35|(1:37))|38|39|40|41|42|43|(1:45)|(1:105)(8:47|48|(3:96|97|(5:99|51|(9:56|57|(2:87|(2:89|(1:91)(5:92|61|(1:86)|(3:68|69|(5:71|(1:73)(1:79)|74|(1:76)(1:78)|77)(1:80))|(1:84)))(1:93))(1:59)|60|61|(1:63)|86|(4:66|68|69|(0)(0))|(2:82|84))|53|55))|50|51|(0)|53|55))|38|39|40|41|42|43|(0)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x00df, code lost:
    
        com.huawei.hianalytics.core.log.HiLog.d("HAVCI", "fail to find support tab class");
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x00e4, code lost:
    
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x00d4, code lost:
    
        com.huawei.hianalytics.core.log.HiLog.d("HAVCI", "fail to find android tab class");
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x00d9, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0152, code lost:
    
        if (android.text.TextUtils.isEmpty(r4) != false) goto L95;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ed A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0119 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0193 A[Catch: Exception -> 0x01cc, TRY_ENTER, TryCatch #2 {Exception -> 0x01cc, blocks: (B:57:0x0119, B:61:0x0155, B:63:0x016d, B:66:0x0183, B:68:0x0189, B:71:0x0193, B:74:0x01a4, B:77:0x01af, B:80:0x01b3, B:82:0x01bb, B:84:0x01c6, B:86:0x0173, B:87:0x0129, B:89:0x012d, B:92:0x0140, B:93:0x014a), top: B:56:0x0119, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01b3 A[Catch: Exception -> 0x01cc, TryCatch #2 {Exception -> 0x01cc, blocks: (B:57:0x0119, B:61:0x0155, B:63:0x016d, B:66:0x0183, B:68:0x0189, B:71:0x0193, B:74:0x01a4, B:77:0x01af, B:80:0x01b3, B:82:0x01bb, B:84:0x01c6, B:86:0x0173, B:87:0x0129, B:89:0x012d, B:92:0x0140, B:93:0x014a), top: B:56:0x0119, outer: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.Object r12, java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 481
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation.a(java.lang.Object, java.lang.Object):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0043, code lost:
    
        r2 = r0.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0044, code lost:
    
        if (r3 >= r2) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0046, code lost:
    
        r4 = r0[r3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x004e, code lost:
    
        if (r4.getClass() == com.huawei.hianalytics.visual.p0.i) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0050, code lost:
    
        r5 = com.huawei.hianalytics.visual.p0.a(r4, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0054, code lost:
    
        if (r5 == null) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0057, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.view.MenuItem r8, java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation.a(android.view.MenuItem, java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00ab A[Catch: Exception -> 0x00ec, TRY_LEAVE, TryCatch #0 {Exception -> 0x00ec, blocks: (B:16:0x0032, B:18:0x0060, B:23:0x0074, B:26:0x007f, B:27:0x008e, B:29:0x00ab, B:34:0x00bf, B:36:0x00b6, B:37:0x00c3, B:39:0x00ce, B:40:0x00d3, B:42:0x00dd, B:43:0x00e2, B:48:0x006b, B:20:0x0064, B:31:0x00af), top: B:15:0x0032, inners: #1, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ce A[Catch: Exception -> 0x00ec, TryCatch #0 {Exception -> 0x00ec, blocks: (B:16:0x0032, B:18:0x0060, B:23:0x0074, B:26:0x007f, B:27:0x008e, B:29:0x00ab, B:34:0x00bf, B:36:0x00b6, B:37:0x00c3, B:39:0x00ce, B:40:0x00d3, B:42:0x00dd, B:43:0x00e2, B:48:0x006b, B:20:0x0064, B:31:0x00af), top: B:15:0x0032, inners: #1, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd A[Catch: Exception -> 0x00ec, TryCatch #0 {Exception -> 0x00ec, blocks: (B:16:0x0032, B:18:0x0060, B:23:0x0074, B:26:0x007f, B:27:0x008e, B:29:0x00ab, B:34:0x00bf, B:36:0x00b6, B:37:0x00c3, B:39:0x00ce, B:40:0x00d3, B:42:0x00dd, B:43:0x00e2, B:48:0x006b, B:20:0x0064, B:31:0x00af), top: B:15:0x0032, inners: #1, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.widget.RadioGroup r6, int r7) {
        /*
            java.lang.String r0 = "HAVCI"
            if (r6 == 0) goto Lf1
            boolean r1 = com.huawei.hianalytics.visual.o0.a()
            if (r1 != 0) goto Lf1
            boolean r1 = com.huawei.hianalytics.visual.o0.g(r6)
            if (r1 == 0) goto L12
            goto Lf1
        L12:
            r1 = 0
            android.view.View r7 = r6.findViewById(r7)     // Catch: java.lang.Exception -> L18
            goto L1e
        L18:
            java.lang.String r7 = "clickOnRadioGroupThread failed"
            com.huawei.hianalytics.core.log.HiLog.e(r0, r7)
            r7 = r1
        L1e:
            if (r7 == 0) goto Lf1
            boolean r2 = r7.isPressed()
            if (r2 != 0) goto L28
            goto Lf1
        L28:
            java.lang.Class<android.widget.RadioGroup> r2 = android.widget.RadioGroup.class
            com.huawei.hianalytics.visual.g r2 = com.huawei.hianalytics.visual.o0.a(r1, r7, r2)
            if (r2 != 0) goto L32
            goto Lf1
        L32:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lec
            r3.<init>()     // Catch: java.lang.Exception -> Lec
            java.lang.String r4 = "$view_id"
            java.lang.String r5 = com.huawei.hianalytics.visual.o0.e(r6)     // Catch: java.lang.Exception -> Lec
            r3.put(r4, r5)     // Catch: java.lang.Exception -> Lec
            java.lang.String r4 = "$event_name"
            java.lang.String r5 = com.huawei.hianalytics.visual.o0.e(r6)     // Catch: java.lang.Exception -> Lec
            r3.put(r4, r5)     // Catch: java.lang.Exception -> Lec
            java.lang.Class r4 = r7.getClass()     // Catch: java.lang.Exception -> Lec
            java.lang.String r4 = r4.getCanonicalName()     // Catch: java.lang.Exception -> Lec
            java.lang.String r5 = "RadioButton"
            java.lang.String r4 = com.huawei.hianalytics.visual.o0.a(r4, r5)     // Catch: java.lang.Exception -> Lec
            java.lang.String r5 = "$view_type"
            r3.put(r5, r4)     // Catch: java.lang.Exception -> Lec
            android.app.Activity r4 = r2.f3918a     // Catch: java.lang.Exception -> Lec
            if (r4 == 0) goto L8c
            int r5 = r6.getCheckedRadioButtonId()     // Catch: java.lang.Exception -> Lec
            android.view.View r4 = r4.findViewById(r5)     // Catch: java.lang.Exception -> L6b
            android.widget.RadioButton r4 = (android.widget.RadioButton) r4     // Catch: java.lang.Exception -> L6b
            goto L71
        L6b:
            java.lang.String r4 = "getRadioGroupContent error"
            com.huawei.hianalytics.core.log.HiLog.e(r0, r4)     // Catch: java.lang.Exception -> Lec
            r4 = r1
        L71:
            if (r4 != 0) goto L74
            goto L8c
        L74:
            java.lang.CharSequence r5 = r4.getText()     // Catch: java.lang.Exception -> Lec
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> Lec
            if (r5 == 0) goto L7f
            goto L8c
        L7f:
            java.lang.CharSequence r4 = r4.getText()     // Catch: java.lang.Exception -> Lec
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> Lec
            java.lang.String r4 = com.huawei.hianalytics.visual.o0.a(r4)     // Catch: java.lang.Exception -> Lec
            goto L8e
        L8c:
            java.lang.String r4 = ""
        L8e:
            java.lang.String r5 = "$view_content"
            r3.put(r5, r4)     // Catch: java.lang.Exception -> Lec
            android.app.Activity r4 = r2.f3918a     // Catch: java.lang.Exception -> Lec
            org.json.JSONObject r4 = com.huawei.hianalytics.visual.h0.b(r4)     // Catch: java.lang.Exception -> Lec
            com.huawei.hianalytics.visual.n0.a(r4, r3)     // Catch: java.lang.Exception -> Lec
            java.lang.Object r4 = r2.b     // Catch: java.lang.Exception -> Lec
            android.app.Activity r5 = r2.f3918a     // Catch: java.lang.Exception -> Lec
            org.json.JSONObject r4 = com.huawei.hianalytics.visual.f0.a(r4, r5)     // Catch: java.lang.Exception -> Lec
            com.huawei.hianalytics.visual.n0.a(r4, r3)     // Catch: java.lang.Exception -> Lec
            android.app.Activity r2 = r2.f3918a     // Catch: java.lang.Exception -> Lec
            if (r2 == 0) goto Lc3
            int r4 = r6.getCheckedRadioButtonId()     // Catch: java.lang.Exception -> Lec
            android.view.View r4 = r2.findViewById(r4)     // Catch: java.lang.Exception -> Lb6
            android.widget.RadioButton r4 = (android.widget.RadioButton) r4     // Catch: java.lang.Exception -> Lb6
            goto Lbc
        Lb6:
            java.lang.String r4 = "getRadioGroupViewNode failed"
            com.huawei.hianalytics.core.log.HiLog.e(r0, r4)     // Catch: java.lang.Exception -> Lec
            r4 = r1
        Lbc:
            if (r4 != 0) goto Lbf
            goto Lc3
        Lbf:
            com.huawei.hianalytics.visual.u0 r1 = com.huawei.hianalytics.visual.o0.a(r2, r4, r3)     // Catch: java.lang.Exception -> Lec
        Lc3:
            r2 = 2131563255(0x7f0d12f7, float:1.8751962E38)
            java.lang.Object r6 = r6.getTag(r2)     // Catch: java.lang.Exception -> Lec
            org.json.JSONObject r6 = (org.json.JSONObject) r6     // Catch: java.lang.Exception -> Lec
            if (r6 == 0) goto Ld3
            java.lang.String r2 = "$custom_property"
            r3.put(r2, r6)     // Catch: java.lang.Exception -> Lec
        Ld3:
            java.lang.String r6 = com.huawei.hianalytics.visual.o0.b(r7)     // Catch: java.lang.Exception -> Lec
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Exception -> Lec
            if (r7 != 0) goto Le2
            java.lang.String r7 = "$view_checked"
            r3.put(r7, r6)     // Catch: java.lang.Exception -> Lec
        Le2:
            com.huawei.hianalytics.visual.c r6 = com.huawei.hianalytics.visual.b.a()     // Catch: java.lang.Exception -> Lec
            java.lang.String r7 = "$ViewClick"
            r6.a(r7, r3, r1)     // Catch: java.lang.Exception -> Lec
            goto Lf1
        Lec:
            java.lang.String r6 = "fail to report click on RadioGroup event"
            com.huawei.hianalytics.core.log.HiLog.w(r0, r6)
        Lf1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation.a(android.widget.RadioGroup, int):void");
    }

    public static void a(DialogInterface dialogInterface, int i) {
        Button button;
        u0 a2;
        if (dialogInterface == null || o0.a()) {
            return;
        }
        u0 u0Var = null;
        Dialog dialog = dialogInterface instanceof Dialog ? (Dialog) dialogInterface : null;
        if (dialog == null || o0.a(dialog)) {
            return;
        }
        Activity a3 = h0.a(dialog.getContext(), null);
        if (a3 == null) {
            a3 = dialog.getOwnerActivity();
        }
        if ((a3 == null || !b.a().i(a3.getClass())) && !o0.c((Class<?>) Dialog.class)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("$view_type", "Dialog");
                jSONObject.put("$view_id", a(dialog));
                jSONObject.put("$event_name", a(dialog));
                n0.a(h0.b(a3), jSONObject);
                Class<?> a4 = n0.a();
                String str = "";
                if (dialog instanceof AlertDialog) {
                    AlertDialog alertDialog = (AlertDialog) dialog;
                    Button button2 = alertDialog.getButton(i);
                    if (button2 != null) {
                        if (!TextUtils.isEmpty(button2.getText())) {
                            str = o0.a(button2.getText().toString());
                        }
                        jSONObject.put("$view_content", str);
                        a2 = o0.a(a3, button2, jSONObject);
                    } else {
                        ListView listView = alertDialog.getListView();
                        if (listView != null) {
                            jSONObject.put("$view_content", Long.valueOf(listView.getAdapter().getItemId(i)));
                            a2 = o0.a(a3, listView.getChildAt(i), jSONObject);
                        }
                        b.a().a("$ViewClick", jSONObject, u0Var);
                    }
                } else {
                    if (a4 != null && a4.isInstance(dialog)) {
                        try {
                            button = (Button) dialog.getClass().getMethod("getButton", Integer.TYPE).invoke(dialog, Integer.valueOf(i));
                        } catch (Exception unused) {
                            HiLog.w("HAVCI", "fail to find getButton method on dialog");
                            button = null;
                        }
                        if (button != null) {
                            if (!TextUtils.isEmpty(button.getText())) {
                                str = button.getText().toString();
                            }
                            jSONObject.put("$view_content", str);
                            a2 = o0.a(a3, button, jSONObject);
                        } else {
                            try {
                                ListView listView2 = (ListView) dialog.getClass().getMethod("getListView", new Class[0]).invoke(dialog, new Object[0]);
                                if (listView2 != null) {
                                    jSONObject.put("$view_content", Long.valueOf(listView2.getAdapter().getItemId(i)));
                                    a2 = o0.a(a3, listView2.getChildAt(i), jSONObject);
                                }
                            } catch (Exception unused2) {
                                HiLog.w("HAVCI", "fail to find getListView method on dialog");
                            }
                        }
                    }
                    b.a().a("$ViewClick", jSONObject, u0Var);
                }
                u0Var = a2;
                b.a().a("$ViewClick", jSONObject, u0Var);
            } catch (Exception unused3) {
                HiLog.w("HAVCI", "fail to report click dialog event");
            }
        }
    }

    public static void a(AdapterView adapterView, View view) {
        String str;
        Object obj;
        if (view == null || o0.a() || o0.g(adapterView) || com.huawei.hianalytics.visual.a.a(view)) {
            return;
        }
        if (adapterView instanceof ListView) {
            obj = ListView.class;
            str = "ListView";
        } else if (adapterView instanceof GridView) {
            obj = GridView.class;
            str = "GridView";
        } else if (adapterView instanceof Spinner) {
            obj = Spinner.class;
            str = "Spinner";
        } else {
            str = "";
            obj = null;
        }
        Pair pair = new Pair(str, obj);
        g a2 = o0.a(adapterView, view, (Class<?>) pair.second);
        if (a2 == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$view_type", pair.first);
            jSONObject.put("$view_id", o0.e(view));
            jSONObject.put("$event_name", o0.e(adapterView));
            jSONObject.put("$view_content", a(view));
            n0.a(h0.b(a2.f3918a), jSONObject);
            n0.a(f0.a(a2.b, a2.f3918a), jSONObject);
            u0 a3 = o0.a(a2.f3918a, view, jSONObject);
            JSONObject jSONObject2 = (JSONObject) view.getTag(R.id.hianalytics_view_custom_property_tag);
            if (jSONObject2 != null) {
                jSONObject.put("$custom_property", jSONObject2);
            }
            b.a().a("$ViewClick", jSONObject, a3);
        } catch (Exception unused) {
            HiLog.w("HAVCI", "fail to report click on Listview event");
        }
    }

    public static Field a(Class<?> cls, String str, String str2) {
        try {
            try {
                return cls.getDeclaredField(str);
            } catch (Exception unused) {
                return null;
            }
        } catch (Exception unused2) {
            return cls.getDeclaredField(str2);
        }
    }

    public static View a(Field field, Object obj) {
        if (field == null) {
            return null;
        }
        try {
            field.setAccessible(true);
            Object obj2 = field.get(obj);
            if (obj2 != null) {
                return (View) obj2;
            }
        } catch (Exception unused) {
            HiLog.w("HAVCI", "fail to get tab view");
        }
        return null;
    }

    public static String a(View view) {
        String c;
        if (view instanceof ViewGroup) {
            c = o0.a(new StringBuilder(), (ViewGroup) view);
            if (!TextUtils.isEmpty(c)) {
                c = c.substring(0, c.length() - 1);
            }
        } else {
            c = o0.c(view);
        }
        return o0.a(c);
    }

    public static String a(Context context, MenuItem menuItem) {
        return (context == null || context.getResources() == null) ? "" : context.getResources().getResourceEntryName(menuItem.getItemId());
    }

    public static String a(Dialog dialog) {
        Window window;
        Object tag;
        return (dialog == null || (window = dialog.getWindow()) == null || !window.isActive() || (tag = window.getDecorView().getTag(R.id.hianalytics_view_id_tag)) == null) ? "" : (String) tag;
    }

    public static void a(View view, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        Object tag = view.getTag(R.id.hianalytics_view_custom_property_tag);
        if (tag instanceof JSONObject) {
            jSONObject2 = (JSONObject) tag;
        } else {
            jSONObject2 = new JSONObject();
        }
        Map<String, String> clickParams = ParamCollectorInstrumentation.getClickParams(view);
        for (String str : clickParams.keySet()) {
            jSONObject2.put(str, clickParams.get(str));
        }
        if (jSONObject2.length() > 0) {
            jSONObject.put("$custom_property", jSONObject2);
        }
    }
}
