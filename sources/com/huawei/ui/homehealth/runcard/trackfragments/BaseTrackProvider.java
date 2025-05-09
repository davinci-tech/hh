package com.huawei.ui.homehealth.runcard.trackfragments;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.homehealth.runcard.utils.TargetChoicePickerUtils;
import com.zhangyue.iReader.sdk.scheme.ISchemeListener;
import defpackage.gts;
import defpackage.koq;
import defpackage.kor;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.owp;
import defpackage.qrp;
import defpackage.tye;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class BaseTrackProvider extends BaseKnitDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private Activity f9541a;
    protected int b;
    protected Resources c;
    public Context d;
    private SectionBean g;
    private Drawable j;
    private String m;
    private String q;
    private int h = 16;
    private int k = -1;
    private float n = -1.0f;
    private HashMap<String, Object> l = new HashMap<>();
    private HiHealthData i = new HiHealthData();
    private Handler f = new e(Looper.getMainLooper(), this);
    private d o = new d(this);
    private List<Drawable> e = new ArrayList(2);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        sectionBean.e(this.i);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void updateGpsSignal(int i) {
        Message obtainMessage = this.f.obtainMessage();
        obtainMessage.what = 100;
        obtainMessage.arg1 = i;
        this.f.sendMessage(obtainMessage);
    }

    protected void c() {
        this.b = 258;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.g = sectionBean;
        this.d = context;
        this.c = context.getResources();
        c();
        d();
        e();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        Context context2 = context == null ? BaseApplication.getContext() : context;
        this.d = context2;
        this.f9541a = dfC_(context2);
        this.c = this.d.getResources();
        this.h = nsn.c(this.d, 4.0f);
        b(context, hashMap, obj);
        if (obj instanceof HiHealthData) {
            d((HashMap<String, Object>) hashMap, (HiHealthData) obj);
        }
        if (obj.equals("CLIMB_NULL_DATA")) {
            c((HashMap<String, Object>) hashMap);
        }
    }

    private void c(HashMap<String, Object> hashMap) {
        String a2;
        if (UnitUtil.h()) {
            a2 = TargetChoicePickerUtils.a(UnitUtil.e(0.0d, 3), this.d);
        } else {
            a2 = TargetChoicePickerUtils.a(0.0d, this.d);
        }
        hashMap.put("SUM_VALUE_TEXT", a2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void d(java.util.HashMap<java.lang.String, java.lang.Object> r5, com.huawei.hihealth.HiHealthData r6) {
        /*
            r4 = this;
            java.lang.String r0 = "updateDistance"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "BaseTrackProvider"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            if (r6 == 0) goto L30
            int r0 = r4.b
            r1 = 260(0x104, float:3.64E-43)
            if (r0 == r1) goto L1f
            r1 = 282(0x11a, float:3.95E-43)
            if (r0 == r1) goto L18
            goto L30
        L18:
            java.lang.String r0 = "Track_Walk_Distance_Sum"
            double r0 = r6.getDouble(r0)
            goto L32
        L1f:
            android.content.Context r0 = r4.d
            java.lang.String[] r0 = defpackage.hkc.a(r0, r1)
            int r1 = r0.length
            if (r1 <= 0) goto L30
            r1 = 0
            r0 = r0[r1]
            double r0 = r6.getDouble(r0)
            goto L32
        L30:
            r0 = 0
        L32:
            boolean r6 = health.compact.a.UnitUtil.h()
            r2 = 4652007308841189376(0x408f400000000000, double:1000.0)
            if (r6 == 0) goto L4a
            double r0 = r0 / r2
            r6 = 3
            double r0 = health.compact.a.UnitUtil.e(r0, r6)
            android.content.Context r6 = r4.d
            java.lang.String r6 = com.huawei.ui.homehealth.runcard.utils.TargetChoicePickerUtils.a(r0, r6)
            goto L51
        L4a:
            double r0 = r0 / r2
            android.content.Context r6 = r4.d
            java.lang.String r6 = com.huawei.ui.homehealth.runcard.utils.TargetChoicePickerUtils.a(r0, r6)
        L51:
            java.lang.String r0 = "SUM_VALUE_TEXT"
            r5.put(r0, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.runcard.trackfragments.BaseTrackProvider.d(java.util.HashMap, com.huawei.hihealth.HiHealthData):void");
    }

    protected void e() {
        int i = this.b;
        if (i == 282) {
            kor.a().d(0L, System.currentTimeMillis(), 7, this.b, this.o);
        } else if (i == 260) {
            gts.b(this.d).e(0L, System.currentTimeMillis(), 7, this.b, this.o);
        } else {
            LogUtil.a("BaseTrackProvider", "no data sporttype");
        }
    }

    protected void b(Context context, HashMap hashMap, Object obj) {
        Drawable drawable;
        String string;
        Log.i("BaseTrackProvider", "setViewData: " + obj);
        if (obj instanceof HiHealthData) {
            if (UnitUtil.h()) {
                string = this.c.getString(R.string._2130841383_res_0x7f020f27);
            } else {
                string = this.c.getString(R.string._2130837660_res_0x7f02009c);
            }
            hashMap.put("ACCUMULATED_DURATION_TEXT", this.c.getString(R.string._2130844760_res_0x7f021c58, string));
        }
        hashMap.put("RIGHT_ICON_TEXT", Integer.valueOf(R.string._2130842049_res_0x7f0211c1));
        hashMap.put("MIDDLE_TIP_TEXT", Integer.valueOf(R.string._2130841846_res_0x7f0210f6));
        hashMap.put("RIGHT_ICON_IMAGE", Integer.valueOf(R.drawable._2131430458_res_0x7f0b0c3a));
        hashMap.put("RIGHT_ICON_BACKGROUND", Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad));
        hashMap.put("SUM_TITLE_IMAGE", Integer.valueOf(R.drawable._2131431569_res_0x7f0b1091));
        if (nsn.ag(this.d)) {
            drawable = this.c.getDrawable(R.drawable._2131430479_res_0x7f0b0c4f);
        } else {
            drawable = this.c.getDrawable(R.drawable._2131430478_res_0x7f0b0c4e);
        }
        Bitmap cHF_ = nrf.cHF_(drawable);
        if (cHF_ == null) {
            Log.i("BaseTrackProvider", "Bitmap is null");
            return;
        }
        Drawable cHq_ = nrf.cHq_(nrf.cJq_(cHF_, cHF_.getWidth(), cHF_.getHeight(), nrf.d));
        Drawable drawable2 = this.c.getDrawable(R.drawable._2131430480_res_0x7f0b0c50);
        this.e.add(cHq_);
        this.e.add(drawable2);
        hashMap.put("BACKGROUND_MASK_DEFULT", this.e);
        e(obj, hashMap);
    }

    public void d() {
        this.k = owp.e(this.d, this.b);
        float a2 = owp.a(this.d, this.b);
        this.n = a2;
        b(this.k, a2, false, false);
    }

    private void b(HashMap hashMap, int i) {
        Drawable drawable = this.j;
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
        if (i == 0) {
            drawable = this.c.getDrawable(R.drawable._2131431841_res_0x7f0b11a1);
        } else if (i == 1) {
            drawable = this.c.getDrawable(R.drawable._2131431842_res_0x7f0b11a2);
        } else if (i == 2) {
            drawable = this.c.getDrawable(R.drawable._2131431838_res_0x7f0b119e);
        } else if (i == 3) {
            drawable = this.c.getDrawable(R.drawable._2131431839_res_0x7f0b119f);
        } else if (i == 4) {
            drawable = this.c.getDrawable(R.drawable._2131431840_res_0x7f0b11a0);
        } else {
            LogUtil.b("BaseTrackProvider", "Wrong GPS signal");
        }
        this.j = drawable;
        hashMap.put("RIGHT_TOP_IMAGE", drawable);
        hashMap.put("RIGHT_TOP_TEXT", Integer.valueOf(R.string._2130839793_res_0x7f0208f1));
        hashMap.put("RIGHT_TOP_TEXT_WIDTH", Integer.valueOf(qrp.a(this.d, 73.0f)));
        hashMap.put("RIGHT_TOP_TEXT_PADDING", Integer.valueOf(this.h));
    }

    static class e extends BaseHandler<BaseTrackProvider> {
        e(Looper looper, BaseTrackProvider baseTrackProvider) {
            super(looper, baseTrackProvider);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dfD_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BaseTrackProvider baseTrackProvider, Message message) {
            if (message.what != 100) {
                return;
            }
            baseTrackProvider.d(message.arg1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (this.g != null) {
            this.l.put("GPS_SIGNAL_DATA", Integer.valueOf(i));
            this.g.e(this.l);
        }
    }

    public static void b() {
        nsn.cLN_("com.huawei.hwireader", com.huawei.haf.application.BaseApplication.wa_(), nsf.h(R.string._2130850371_res_0x7f023243), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.BaseTrackProvider.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                tye.d(new ISchemeListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.BaseTrackProvider.5.4
                    @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                    public void onSuccess(Object obj) {
                        LogUtil.a("BaseTrackProvider", "openTingChannel is onSuccess");
                    }

                    @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                    public void onError(int i, String str) {
                        LogUtil.a("BaseTrackProvider", "openTingChannel is onError");
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HiHealthData hiHealthData) {
        if (hiHealthData != null) {
            this.i = hiHealthData.copyData();
            Log.i("BaseTrackProvider", "updateHealthData: " + this.i);
        }
        this.g.e(this.i);
    }

    private void b(int i, float f, boolean z, boolean z2) {
        this.l.put("GOAL_TIPS_VISIBILITY_DATA", 0);
        this.l.put("GOAL_UNIT_VISIBILITY_DATA", 0);
        this.g.e(this.l);
        if (i == -1) {
            a();
        } else if (i == 0) {
            float f2 = f / 60.0f;
            this.q = UnitUtil.e(f2, 1, 0);
            this.m = this.c.getQuantityString(R.plurals._2130903233_res_0x7f0300c1, (int) f2);
            this.l.put("GOAL_VALUE_TEXT_DATA", this.q);
            this.l.put("GOAL_UNIT_TEXT_DATA", this.m);
            h();
        } else if (i == 1) {
            b(f);
            h();
        } else if (i == 2) {
            this.q = UnitUtil.e(f / 1000.0f, 1, 0);
            this.m = this.c.getString(R.string._2130837659_res_0x7f02009b);
            this.l.put("GOAL_VALUE_TEXT_DATA", this.q);
            this.l.put("GOAL_UNIT_TEXT_DATA", this.m);
            h();
        }
        if (z) {
            owp.c(this.d, this.b, i);
            owp.c(this.d, f, this.b);
            owp.e(this.d, this.b, z2);
            if (z2) {
                owp.b(this.d, this.b, i, f);
            } else {
                owp.b(this.d, this.b, i, -1.0f);
            }
        }
    }

    private void h() {
        if (LoginInit.getInstance(this.f9541a).getIsLogined()) {
            this.l.put("GOAL_VISIBILITY_DATA", 0);
        } else {
            this.l.put("GOAL_VISIBILITY_DATA", 4);
        }
        this.g.e(this.l);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a() {
        /*
            r4 = this;
            int r0 = r4.b
            r1 = 264(0x108, float:3.7E-43)
            if (r0 == r1) goto L10
            r1 = 282(0x11a, float:3.95E-43)
            if (r0 == r1) goto L10
            switch(r0) {
                case 257: goto L10;
                case 258: goto L10;
                case 259: goto L10;
                case 260: goto L10;
                default: goto Ld;
            }
        Ld:
            java.lang.String r0 = ""
            goto L19
        L10:
            android.content.res.Resources r0 = r4.c
            r1 = 2130842526(0x7f02139e, float:1.729015E38)
            java.lang.String r0 = r0.getString(r1)
        L19:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 8
            if (r1 != 0) goto L42
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = r4.l
            java.lang.String r3 = "GOAL_VALUE_TEXT_DATA"
            r1.put(r3, r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r4.l
            java.lang.String r1 = "GOAL_UNIT_VISIBILITY_DATA"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r3)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r4.l
            java.lang.String r1 = "GOAL_TIPS_VISIBILITY_DATA"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            r4.h()
            goto L54
        L42:
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r4.l
            java.lang.String r1 = "GOAL_VISIBILITY_DATA"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            com.huawei.health.knit.section.model.SectionBean r0 = r4.g
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = r4.l
            r0.e(r1)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.runcard.trackfragments.BaseTrackProvider.a():void");
    }

    private void b(float f) {
        if (UnitUtil.h()) {
            this.q = UnitUtil.e(UnitUtil.e(f, 3), 1, 2);
            this.l.put("GOAL_UNIT_VISIBILITY_DATA", 0);
            this.m = this.c.getString(R.string._2130841383_res_0x7f020f27);
        } else {
            this.m = this.c.getString(R.string._2130837660_res_0x7f02009c);
            double d2 = f;
            if (Math.abs(d2 - 42.195d) < 1.0E-5d) {
                this.q = UnitUtil.e(d2, 1, 3);
            } else if (Math.abs(d2 - 21.0975d) >= 1.0E-5d) {
                this.l.put("GOAL_UNIT_VISIBILITY_DATA", 0);
                this.q = UnitUtil.e(d2, 1, 2);
            } else {
                this.q = UnitUtil.e(d2, 1, 4);
            }
        }
        this.l.put("GOAL_VALUE_TEXT_DATA", this.q);
        this.l.put("GOAL_UNIT_TEXT_DATA", this.m);
        this.g.e(this.l);
    }

    private Activity dfC_(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return dfC_(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    static class d implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<BaseTrackProvider> f9543a;

        d(BaseTrackProvider baseTrackProvider) {
            this.f9543a = new WeakReference<>(baseTrackProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BaseTrackProvider baseTrackProvider = this.f9543a.get();
            if (baseTrackProvider == null) {
                return;
            }
            if (!LoginInit.getInstance(null).getIsLogined()) {
                baseTrackProvider.a(new HiHealthData());
                return;
            }
            LogUtil.a("BaseTrackProvider", "onResponse ", Integer.valueOf(baseTrackProvider.b));
            if (!(obj instanceof SparseArray)) {
                if (koq.e(obj, HiHealthData.class)) {
                    List list = (List) obj;
                    if (koq.c(list)) {
                        baseTrackProvider.a((HiHealthData) list.get(0));
                    } else {
                        baseTrackProvider.a(new HiHealthData());
                    }
                    LogUtil.a("BaseTrackProvider", "data is " + list);
                    return;
                }
                LogUtil.a("BaseTrackProvider", "wrong data : ", Integer.valueOf(i));
                baseTrackProvider.j();
                return;
            }
            try {
                List list2 = (List) ((SparseArray) obj).get(0);
                if (koq.c(list2)) {
                    baseTrackProvider.a((HiHealthData) list2.get(0));
                } else {
                    baseTrackProvider.a(new HiHealthData());
                }
                LogUtil.a("BaseTrackProvider", "data is " + list2);
            } catch (ClassCastException e) {
                LogUtil.h("BaseTrackProvider", e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.g.e("CLIMB_NULL_DATA");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void e(Object obj, HashMap hashMap) {
        char c;
        if (obj instanceof HashMap) {
            for (Map.Entry entry : ((HashMap) obj).entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                str.hashCode();
                switch (str.hashCode()) {
                    case -1749746852:
                        if (str.equals("GOAL_TIPS_VISIBILITY_DATA")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -727649891:
                        if (str.equals("GOAL_VALUE_VISIBILITY_DATA")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 207303563:
                        if (str.equals("GOAL_VISIBILITY_DATA")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1184947501:
                        if (str.equals("GOAL_UNIT_TEXT_DATA")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1522440876:
                        if (str.equals("GPS_SIGNAL_DATA")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1591263458:
                        if (str.equals("GOAL_VALUE_TEXT_DATA")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1951885736:
                        if (str.equals("GOAL_UNIT_VISIBILITY_DATA")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        if (value instanceof Integer) {
                            hashMap.put("MIDDLE_TIP_TEXT_VISIBILITY", (Integer) value);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (value instanceof Integer) {
                            hashMap.put("MIDDLE_VALUE_TEXT_VISIBILITY", (Integer) value);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (value instanceof Integer) {
                            hashMap.put("MIDDLE_TEXT_LAYOUT_VISIBILITY", (Integer) value);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (value instanceof String) {
                            hashMap.put("MIDDLE_UNIT_TEXT", (String) value);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (value instanceof Integer) {
                            b(hashMap, ((Integer) value).intValue());
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (value instanceof String) {
                            hashMap.put("MIDDLE_VALUE_TEXT", (String) value);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (value instanceof Integer) {
                            hashMap.put("MIDDLE_UNIT_TEXT_VISIBILITY", (Integer) value);
                            break;
                        } else {
                            break;
                        }
                }
            }
        }
    }
}
