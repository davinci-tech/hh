package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.skinner.attrentry.SkinAttr;
import defpackage.ncm;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;

/* loaded from: classes6.dex */
public class ndk {

    /* renamed from: a, reason: collision with root package name */
    private static final String f15266a = "StyleHelper";
    private static final Map<String, ncm> c;
    private static final Map<String, Map<String, Field>> d;
    private static final Map<String, ncm> e;
    private String b;
    private ncm h;
    private String i;

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        HashMap hashMap2 = new HashMap();
        e = hashMap2;
        d = new HashMap();
        ncm d2 = new ncm.e().d(TemplateStyleRecord.STYLE).e("android.widget.Switch").c("com.android.internal.R$styleable").a("Switch").e(new String[]{"Switch_thumb", "Switch_track"}).d(new String[]{"thumb", "track"}).d();
        hashMap.put(d2.e(), d2);
        ncm d3 = new ncm.e().d(TemplateStyleRecord.STYLE).e("huawei.widget.HwSwitch").c("com.android.internal.R$styleable").a("Switch").e(new String[]{"Switch_thumb", "Switch_track"}).d(new String[]{"thumb", "track"}).d();
        hashMap.put(d3.e(), d3);
        ncm d4 = new ncm.e().d(TemplateStyleRecord.STYLE).e("android.support.v7.widget.SwitchCompat").c("android.support.v7.appcompat.R$styleable").a("Switch").e(new String[]{"Switch_thumb", "Switch_track"}).d(new String[]{"thumb", "track"}).d();
        hashMap.put(d4.e(), d4);
        ncm d5 = new ncm.e().d(TemplateStyleRecord.STYLE).e("android.widget.ProgressBar").c("com.android.internal.R$styleable").a("ProgressBar").e(new String[]{"ProgressBar_progressDrawable", "ProgressBar_indeterminateDrawable"}).d(new String[]{"progressDrawable", "indeterminateDrawable"}).d();
        hashMap.put(d5.e(), d5);
        ncm d6 = new ncm.e().d("textAppearance").e("android.widget.TextView").c("com.android.internal.R$styleable").a("TextAppearance").e(new String[]{"TextAppearance_textColor", "TextAppearance_textColorHint"}).d(new String[]{"textColor", "textColorHint"}).d();
        hashMap2.put(d6.e(), d6);
        ncm d7 = new ncm.e().d("textAppearance").e("android.support.v7.widget.AppCompatTextView").c("com.android.internal.R$styleable").a("TextAppearance").e(new String[]{"TextAppearance_textColor", "TextAppearance_textColorHint"}).d(new String[]{"textColor", "textColorHint"}).d();
        hashMap2.put(d7.e(), d7);
        ncm d8 = new ncm.e().d("textAppearance").e("android.widget.Button").c("com.android.internal.R$styleable").a("TextAppearance").e(new String[]{"TextAppearance_textColor", "TextAppearance_textColorHint"}).d(new String[]{"textColor", "textColorHint"}).d();
        hashMap2.put(d8.e(), d8);
        ncm d9 = new ncm.e().d("textAppearance").e("android.widget.EditText").c("com.android.internal.R$styleable").a("TextAppearance").e(new String[]{"TextAppearance_textColor", "TextAppearance_textColorHint"}).d(new String[]{"textColor", "textColorHint"}).d();
        hashMap2.put(d9.e(), d9);
        ncm d10 = new ncm.e().d("textAppearance").e("huawei.widget.HwTextView").c("com.android.internal.R$styleable").a("TextAppearance").e(new String[]{"TextAppearance_textColor", "TextAppearance_textColorHint"}).d(new String[]{"textColor", "textColorHint"}).d();
        hashMap2.put(d10.e(), d10);
        ncm d11 = new ncm.e().d("textAppearance").e("huawei.widget.HwButton").c("com.android.internal.R$styleable").a("TextAppearance").e(new String[]{"TextAppearance_textColor", "TextAppearance_textColorHint"}).d(new String[]{"textColor", "textColorHint"}).d();
        hashMap2.put(d11.e(), d11);
    }

    public ndk(View view, String str) {
        this.b = str;
        this.i = f15266a + " " + view.getClass().getName() + "@" + view.hashCode();
        cti_(view);
    }

    public void ctk_(Context context, View view, Resources resources, int i, ConcurrentSkipListSet<SkinAttr> concurrentSkipListSet) {
        TypedArray typedArray;
        ncm ncmVar = this.h;
        if (ncmVar == null) {
            ncs.d.info(this.i, "parseStylefailed(mStyleBean is null): have register style for this view? : " + view.getClass().getName());
            return;
        }
        int[][] d2 = d(ncmVar.a(), this.h.b(), this.h.c());
        if (d2.length != 2 || d2[0] == null) {
            ncs.d.info(this.i, "parseStylegetStyleable() failed! ");
            return;
        }
        concurrentSkipListSet.clear();
        TypedArray typedArray2 = null;
        try {
            try {
                typedArray = context.getTheme().obtainStyledAttributes(i, d2[0]);
            } catch (Resources.NotFoundException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            typedArray = typedArray2;
        }
        try {
            cth_(view, typedArray, resources, d2[1], this.h.d(), concurrentSkipListSet);
            ctj_(typedArray);
        } catch (Resources.NotFoundException e3) {
            e = e3;
            typedArray2 = typedArray;
            ncs.d.error(this.i, "parseStylegetStyleable() failed! ", e);
            ctj_(typedArray2);
        } catch (Throwable th2) {
            th = th2;
            ctj_(typedArray);
            throw th;
        }
    }

    private void ctj_(TypedArray typedArray) {
        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    private void cti_(View view) {
        if (this.b.equals(TemplateStyleRecord.STYLE)) {
            ncm ncmVar = c.get(view.getClass().getName());
            if (ncmVar != null) {
                this.h = ncmVar;
                return;
            }
            return;
        }
        if (this.b.equals("textAppearance")) {
            ncm ncmVar2 = e.get(view.getClass().getName());
            if (ncmVar2 != null) {
                this.h = ncmVar2;
                return;
            }
            return;
        }
        ncs.d.warn(this.i, "prepare(view) Unknown attr:" + this.b);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x016b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void cth_(android.view.View r18, android.content.res.TypedArray r19, android.content.res.Resources r20, int[] r21, java.lang.String[] r22, java.util.concurrent.ConcurrentSkipListSet<com.huawei.skinner.attrentry.SkinAttr> r23) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ndk.cth_(android.view.View, android.content.res.TypedArray, android.content.res.Resources, int[], java.lang.String[], java.util.concurrent.ConcurrentSkipListSet):void");
    }

    private int[][] d(String str, String str2, String[] strArr) {
        Object d2 = d(str, str2);
        int[] iArr = d2 instanceof int[] ? (int[]) d2 : null;
        int length = strArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            Object d3 = d(str, strArr[i]);
            if (!(d3 instanceof Integer)) {
                iArr2[i] = -1;
            } else {
                iArr2[i] = ((Integer) d3).intValue();
            }
        }
        return new int[][]{iArr, iArr2};
    }

    private Object d(String str, String str2) {
        Map<String, Map<String, Field>> map = d;
        Map<String, Field> map2 = map.get(str);
        if (map2 != null) {
            Field field = map2.get(str2);
            if (field != null) {
                return ndd.d(field, null);
            }
            try {
                Field a2 = ndd.a(Class.forName(str), str2);
                map2.put(str2, a2);
                return ndd.d(a2, null);
            } catch (ClassNotFoundException e2) {
                ncs.d.error(this.i, "getFieldValue(className,styleableField)Error1", e2);
            }
        } else {
            HashMap hashMap = new HashMap();
            map.put(str, hashMap);
            try {
                Field a3 = ndd.a(Class.forName(str), str2);
                hashMap.put(str2, a3);
                return ndd.d(a3, null);
            } catch (ClassNotFoundException e3) {
                ncs.d.error(this.i, "getFieldValue(className,styleableField)Error2", e3);
            }
        }
        return null;
    }
}
