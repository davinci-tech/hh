package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class ptt {

    /* renamed from: a, reason: collision with root package name */
    private static ptt f16258a;
    private static final byte[] c = new byte[1];
    private int b;
    private long g;
    private int h;
    private String p;
    private String w;
    private long x;
    private final List<ptb> t = new ArrayList(12);
    private final Map<Integer, String> d = new HashMap(16);
    private final List<Integer> r = new ArrayList(16);
    private boolean l = false;
    private boolean k = false;
    private boolean i = false;
    private boolean s = true;
    private boolean q = false;
    private boolean m = false;
    private boolean o = false;
    private int f = 1;
    private boolean n = false;
    private boolean j = false;
    private String e = "unknown";

    private ptt() {
    }

    public static ptt d() {
        ptt pttVar;
        synchronized (c) {
            if (f16258a == null) {
                f16258a = new ptt();
            }
            pttVar = f16258a;
        }
        return pttVar;
    }

    public List<ptb> q() {
        return this.t;
    }

    public void c(boolean z) {
        this.l = z;
    }

    public boolean g() {
        return this.l;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public boolean c() {
        return this.j;
    }

    private void d(int i) {
        this.f = i;
    }

    private int w() {
        return this.f;
    }

    public void b(boolean z) {
        this.k = z;
    }

    public boolean j() {
        return this.k;
    }

    public void f(boolean z) {
        this.o = z;
    }

    public boolean l() {
        return this.o;
    }

    public void d(boolean z) {
        this.i = z;
    }

    public boolean i() {
        return this.i;
    }

    public void i(boolean z) {
        this.s = z;
    }

    public boolean m() {
        return this.s;
    }

    public void j(boolean z) {
        this.q = z;
    }

    public boolean o() {
        return this.q;
    }

    public void e(boolean z) {
        this.m = z;
    }

    public boolean f() {
        return this.m;
    }

    public void e(long j) {
        this.x = j;
    }

    public long t() {
        return this.x;
    }

    public void b(long j) {
        this.g = j;
    }

    public long b() {
        return this.g;
    }

    public void b(String str) {
        this.e = str;
    }

    public String n() {
        return this.e;
    }

    public void c(String str) {
        this.w = str;
    }

    public String r() {
        return this.w;
    }

    public String p() {
        return this.p;
    }

    public void d(String str) {
        this.p = str;
    }

    public void b(int i) {
        this.b = i;
    }

    public int e() {
        return this.b;
    }

    public void h(boolean z) {
        this.n = z;
    }

    public boolean h() {
        return this.n;
    }

    public int a() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public void s() {
        if (w() == 1) {
            d(0);
            LogUtil.a("AnswerQuestionUtil", "initList() success");
            x();
        }
    }

    private void x() {
        String string = BaseApplication.getContext().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_item_1);
        String string2 = BaseApplication.getContext().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_item_2);
        String string3 = BaseApplication.getContext().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_item_3);
        String string4 = BaseApplication.getContext().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_item_4);
        String[] y = y();
        int i = 0;
        while (i < 12) {
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(new pti("A", string));
            arrayList.add(new pti("B", string2));
            arrayList.add(new pti(TypeParams.SEARCH_CODE, string3));
            arrayList.add(new pti("D", string4));
            int i2 = i + 1;
            this.t.add(new ptb(i2, y[i], arrayList));
            i = i2;
        }
    }

    private String[] y() {
        Context context = BaseApplication.getContext();
        return new String[]{context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_1), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_2), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_3), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_4), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_5), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_6), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_7), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_8), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_9), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_10), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_11), context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_question_text_12)};
    }

    private void e(List<?> list) {
        if (list != null) {
            list.clear();
        }
    }

    private void c(Map<?, ?> map) {
        if (map != null) {
            map.clear();
        }
    }

    public int e(int i) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return -1;
        }
        String str = this.d.get(Integer.valueOf(i));
        LogUtil.a("AnswerQuestionUtil", "getAnswerMap() questionId = ", Integer.valueOf(i));
        return b(str, i == 5 || i == 7 || i == 10);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int b(String str, boolean z) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case 65:
                if (str.equals("A")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 66:
                if (str.equals("B")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 67:
                if (str.equals(TypeParams.SEARCH_CODE)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 68:
                if (str.equals("D")) {
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
            return z ? 3 : 0;
        }
        if (c2 == 1) {
            return z ? 2 : 1;
        }
        if (c2 == 2) {
            return z ? 1 : 2;
        }
        if (c2 == 3) {
            return z ? 0 : 3;
        }
        LogUtil.h("AnswerQuestionUtil", "matchSelected is error name, name is ", str);
        return -1;
    }

    public void a(int i, String str) {
        this.d.put(Integer.valueOf(i), str);
        LogUtil.a("AnswerQuestionUtil", "setAnswerMap() questionId = ", Integer.valueOf(i), ", checkedItem = ", str);
        if (i == 5 || i == 7 || i == 10) {
            a(this.d, i);
        }
    }

    private void a(Map<Integer, String> map, int i) {
        String str = map.get(Integer.valueOf(i));
        LogUtil.a("AnswerQuestionUtil", "changeMap() value = ", str);
        if ("A".equals(str)) {
            map.put(Integer.valueOf(i), "D");
            return;
        }
        if ("B".equals(str)) {
            map.put(Integer.valueOf(i), TypeParams.SEARCH_CODE);
            return;
        }
        if (TypeParams.SEARCH_CODE.equals(str)) {
            map.put(Integer.valueOf(i), "B");
        } else if ("D".equals(str)) {
            map.put(Integer.valueOf(i), "A");
        } else {
            LogUtil.a("AnswerQuestionUtil", "changeMap() value err");
        }
    }

    public float k() {
        Iterator<Map.Entry<Integer, String>> it = this.d.entrySet().iterator();
        while (it.hasNext()) {
            String value = it.next().getValue();
            LogUtil.a("AnswerQuestionUtil", "getLastScore() value = ", value);
            if ("A".equals(value)) {
                this.r.add(0);
            } else if ("B".equals(value)) {
                this.r.add(1);
            } else if (TypeParams.SEARCH_CODE.equals(value)) {
                this.r.add(2);
            } else if ("D".equals(value)) {
                this.r.add(3);
            } else {
                LogUtil.a("AnswerQuestionUtil", "getLastScore() value err ");
            }
        }
        return d(this.r);
    }

    private float d(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().intValue();
        }
        return (float) ((i * 2.722d) + 0.5d + 1.0d);
    }

    public void u() {
        LogUtil.a("AnswerQuestionUtil", " AnswerQuestionUtil reset()");
        e(this.r);
        c(this.d);
        e(this.t);
        d(1);
        b(false);
        c(false);
        i(true);
        j(false);
        e(false);
        a(false);
        a(0);
    }
}
