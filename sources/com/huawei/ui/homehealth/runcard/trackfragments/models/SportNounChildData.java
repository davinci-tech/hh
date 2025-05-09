package com.huawei.ui.homehealth.runcard.trackfragments.models;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.health.R;
import defpackage.oro;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class SportNounChildData {
    private Resources b;
    private String[] d;
    private Context e;
    private int[] j = {R.string._2130844092_res_0x7f0219bc, R.string._2130844093_res_0x7f0219bd, R.string._2130844094_res_0x7f0219be, R.string._2130844095_res_0x7f0219bf, R.string._2130844096_res_0x7f0219c0};
    private int[] f = {R.string._2130844097_res_0x7f0219c1, R.string._2130844098_res_0x7f0219c2, R.string._2130844099_res_0x7f0219c3, R.string._2130844100_res_0x7f0219c4, R.string._2130844096_res_0x7f0219c0};
    private int[] c = {R.string._2130842946_res_0x7f021542, R.string._2130842947_res_0x7f021543, R.string._2130842948_res_0x7f021544, R.string._2130842949_res_0x7f021545, R.string._2130842950_res_0x7f021546};

    /* renamed from: a, reason: collision with root package name */
    private int[] f9580a = {R.string._2130842951_res_0x7f021547, R.string._2130842952_res_0x7f021548, R.string._2130842953_res_0x7f021549, R.string._2130842954_res_0x7f02154a, R.string._2130842950_res_0x7f021546};

    public SportNounChildData() {
    }

    public SportNounChildData(Context context) {
        this.e = context;
        this.b = context.getResources();
    }

    public List<oro> a() {
        String[] strArr = {this.b.getString(R.string._2130841891_res_0x7f021123), this.b.getString(R.string._2130842827_res_0x7f0214cb), this.b.getString(R.string._2130839938_res_0x7f020982)};
        String[] strArr2 = {String.format(this.b.getString(R.string._2130846627_res_0x7f0223a3), 60, 100, 60), this.b.getString(R.string._2130842762_res_0x7f02148a), this.b.getString(R.string._2130839939_res_0x7f020983)};
        ArrayList arrayList = new ArrayList(3);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    public List<oro> j() {
        String[] strArr = {this.b.getString(R.string._2130844075_res_0x7f0219ab), this.b.getString(R.string._2130839768_res_0x7f0208d8)};
        String[] strArr2 = {String.format(this.b.getString(R.string._2130842748_res_0x7f02147c), 180), this.b.getString(R.string._2130842749_res_0x7f02147d)};
        ArrayList arrayList = new ArrayList(2);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    public List<oro> g() {
        String[] strArr = {this.b.getString(R.string._2130842710_res_0x7f021456), this.b.getString(R.string._2130843148_res_0x7f02160c), this.b.getString(R.string._2130843723_res_0x7f02184b), this.b.getString(R.string._2130842712_res_0x7f021458), this.b.getString(R.string._2130842760_res_0x7f021488), this.b.getString(R.string._2130842758_res_0x7f021486), this.b.getString(R.string._2130842724_res_0x7f021464)};
        String[] strArr2 = {String.format(this.b.getString(R.string._2130842750_res_0x7f02147e), 200), String.format(this.b.getString(R.string._2130843732_res_0x7f021854), 125), String.format(this.b.getString(R.string._2130843737_res_0x7f021859), Float.valueOf(1.5f)), String.format(this.b.getString(R.string._2130842752_res_0x7f021480), 6, 20), String.format(this.b.getString(R.string._2130842761_res_0x7f021489), 5, 25), String.format(this.b.getString(R.string._2130842759_res_0x7f021487), 70, 140), this.b.getString(R.string._2130842754_res_0x7f021482)};
        ArrayList arrayList = new ArrayList(7);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    public List<oro> e(int i) {
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            c(arrayList);
        } else {
            b(arrayList);
        }
        d(arrayList);
        d(arrayList, i);
        return arrayList;
    }

    public List<oro> l() {
        ArrayList arrayList = new ArrayList();
        c(arrayList);
        b(arrayList);
        d(arrayList);
        d(arrayList, 1);
        return arrayList;
    }

    private List<oro> c(List<oro> list) {
        String[] c = c(this.c);
        String[] c2 = c(this.f9580a);
        b(list, new String[]{this.b.getString(R.string._2130842764_res_0x7f02148c), this.b.getString(R.string._2130842731_res_0x7f02146b)}, a(c, this.b.getString(R.string._2130842765_res_0x7f02148d)), a(c2, this.b.getString(R.string._2130842763_res_0x7f02148b)));
        return list;
    }

    private List<oro> b(List<oro> list) {
        String[] c = c(this.j);
        String[] c2 = c(this.f);
        b(list, new String[]{this.b.getString(R.string._2130844088_res_0x7f0219b8), this.b.getString(R.string._2130844089_res_0x7f0219b9)}, a(c, this.b.getString(R.string._2130844090_res_0x7f0219ba)), a(c2, this.b.getString(R.string._2130844091_res_0x7f0219bb)));
        return list;
    }

    private String[] c(int[] iArr) {
        String[] o = o();
        String[] strArr = new String[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            if (i == iArr.length - 1) {
                strArr[i] = String.format(this.e.getString(iArr[i]), o[i * 2]);
            } else {
                int i2 = i * 2;
                strArr[i] = String.format(this.e.getString(iArr[i]), o[i2], o[i2 + 1]);
            }
        }
        return strArr;
    }

    private List<oro> d(List<oro> list) {
        oro oroVar = new oro();
        oroVar.d(this.b.getString(R.string._2130842094_res_0x7f0211ee));
        oroVar.b(this.b.getString(R.string._2130842102_res_0x7f0211f6));
        list.add(oroVar);
        return list;
    }

    private List<oro> d(List<oro> list, int i) {
        oro oroVar = new oro();
        oroVar.d(this.b.getString(R.string._2130839786_res_0x7f0208ea));
        if (i == 0) {
            oroVar.b(this.b.getString(R.string._2130842766_res_0x7f02148e));
        } else {
            oroVar.b(this.b.getString(R.string._2130844087_res_0x7f0219b7));
        }
        list.add(oroVar);
        return list;
    }

    private String[] o() {
        String[] strArr = this.d;
        if (strArr != null) {
            return strArr;
        }
        String[] strArr2 = new String[9];
        this.d = strArr2;
        strArr2[0] = UnitUtil.e(1.0d, 1, 1);
        this.d[1] = UnitUtil.e(1.9d, 1, 1);
        this.d[2] = UnitUtil.e(2.0d, 1, 1);
        this.d[3] = UnitUtil.e(2.9d, 1, 1);
        this.d[4] = UnitUtil.e(3.0d, 1, 1);
        this.d[5] = UnitUtil.e(3.9d, 1, 1);
        this.d[6] = UnitUtil.e(4.0d, 1, 1);
        this.d[7] = UnitUtil.e(4.9d, 1, 1);
        this.d[8] = UnitUtil.e(5.0d, 1, 1);
        return this.d;
    }

    private void b(List<oro> list, String[] strArr, StringBuffer stringBuffer, StringBuffer stringBuffer2) {
        d(list, strArr, new String[]{stringBuffer.toString(), stringBuffer2.toString()});
    }

    private StringBuffer a(String[] strArr, String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        for (String str2 : strArr) {
            stringBuffer.append("\\n");
            stringBuffer.append(str2);
        }
        return stringBuffer;
    }

    public List<oro> i() {
        String format;
        String[] strArr = {this.b.getString(R.string._2130839818_res_0x7f02090a), this.b.getString(R.string._2130844080_res_0x7f0219b0), this.b.getString(R.string._2130844077_res_0x7f0219ad)};
        String string = this.b.getString(R.string._2130839923_res_0x7f020973);
        String string2 = this.b.getString(R.string._2130839924_res_0x7f020974);
        if (UnitUtil.h()) {
            format = String.format(this.b.getString(R.string._2130839840_res_0x7f020920), 50, this.b.getQuantityString(R.plurals._2130903227_res_0x7f0300bb, 50));
        } else {
            format = String.format(this.b.getString(R.string._2130839840_res_0x7f020920), 50, this.b.getString(R.string._2130841568_res_0x7f020fe0));
        }
        String[] strArr2 = {string, string2, format};
        ArrayList arrayList = new ArrayList(3);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    public List<oro> d() {
        String[] strArr = {this.b.getString(R.string._2130843145_res_0x7f021609), this.b.getString(R.string._2130843148_res_0x7f02160c)};
        String[] strArr2 = {c(new String[]{this.b.getString(R.string._2130843203_res_0x7f021643), this.b.getString(R.string._2130843204_res_0x7f021644), String.format(this.b.getString(R.string._2130843205_res_0x7f021645), 90)}), c(new String[]{this.b.getString(R.string._2130843206_res_0x7f021646), this.b.getString(R.string._2130843207_res_0x7f021647), String.format(this.b.getString(R.string._2130843208_res_0x7f021648), 900)})};
        ArrayList arrayList = new ArrayList(2);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    public List<oro> h() {
        String[] strArr = {this.b.getString(R.string._2130839919_res_0x7f02096f)};
        String[] strArr2 = {this.b.getString(R.string._2130839920_res_0x7f020970)};
        ArrayList arrayList = new ArrayList(1);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    public List<oro> c() {
        String[] strArr = {this.b.getString(R.string._2130844075_res_0x7f0219ab), this.b.getString(R.string._2130839919_res_0x7f02096f)};
        String[] strArr2 = {this.b.getString(R.string._2130839921_res_0x7f020971), this.b.getString(R.string._2130839922_res_0x7f020972)};
        ArrayList arrayList = new ArrayList(2);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    public List<oro> e() {
        String[] strArr = {this.b.getString(R.string._2130843294_res_0x7f02169e), this.b.getString(R.string._2130843296_res_0x7f0216a0), this.b.getString(R.string._2130843292_res_0x7f02169c), this.b.getString(R.string._2130843290_res_0x7f02169a), this.b.getString(R.string._2130843298_res_0x7f0216a2), this.b.getString(R.string._2130843300_res_0x7f0216a4)};
        String[] strArr2 = {this.b.getString(R.string._2130843295_res_0x7f02169f), this.b.getString(R.string._2130843297_res_0x7f0216a1), this.b.getString(R.string._2130843293_res_0x7f02169d), this.b.getString(R.string._2130843291_res_0x7f02169b), this.b.getString(R.string._2130843299_res_0x7f0216a3), this.b.getString(R.string._2130843301_res_0x7f0216a5)};
        ArrayList arrayList = new ArrayList(6);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    public List<oro> b() {
        String[] strArr = {this.b.getString(R.string._2130843677_res_0x7f02181d), this.b.getString(R.string._2130843679_res_0x7f02181f), this.b.getString(R.string._2130843680_res_0x7f021820), this.b.getString(R.string._2130843678_res_0x7f02181e)};
        String[] strArr2 = {this.b.getString(R.string._2130843681_res_0x7f021821, 3, 1), this.b.getString(R.string._2130843683_res_0x7f021823), this.b.getString(R.string._2130843684_res_0x7f021824), this.b.getString(R.string._2130843682_res_0x7f021822)};
        ArrayList arrayList = new ArrayList(4);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    public List<oro> f() {
        String[] strArr = {this.b.getString(R.string._2130847667_res_0x7f0227b3), this.b.getString(R.string._2130847669_res_0x7f0227b5), this.b.getString(R.string._2130843711_res_0x7f02183f), this.b.getString(R.string._2130847672_res_0x7f0227b8), this.b.getString(R.string._2130847674_res_0x7f0227ba)};
        String[] strArr2 = {this.b.getString(R.string._2130847668_res_0x7f0227b4), this.b.getString(R.string._2130847670_res_0x7f0227b6), this.b.getString(R.string._2130847671_res_0x7f0227b7), this.b.getString(R.string._2130847673_res_0x7f0227b9), this.b.getString(R.string._2130847675_res_0x7f0227bb)};
        ArrayList arrayList = new ArrayList(5);
        d(arrayList, strArr, strArr2);
        return arrayList;
    }

    private String c(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        int length = strArr.length;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = length - 1;
            if (i < i2) {
                sb.append(strArr[i]);
                sb.append(System.lineSeparator());
                i++;
            } else {
                sb.append(strArr[i2]);
                return sb.toString();
            }
        }
    }

    private void d(List<oro> list, String[] strArr, String[] strArr2) {
        for (int i = 0; i < strArr.length; i++) {
            oro oroVar = new oro();
            oroVar.d(strArr[i]);
            oroVar.b(strArr2[i]);
            list.add(oroVar);
        }
    }
}
