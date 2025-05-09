package defpackage;

import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.HealthWorkout;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class gbr {
    public static boolean b(int i) {
        return HealthWorkout.isDisplayBitOne(i, 1);
    }

    public static boolean d(int i) {
        return HealthWorkout.isDisplayBitOne(i, 2);
    }

    public static boolean e(int i) {
        return HealthWorkout.isDisplayBitOne(i, 4);
    }

    public static boolean i(int i) {
        return HealthWorkout.isDisplayBitOne(i, 8);
    }

    public static boolean a(int i) {
        return HealthWorkout.isDisplayBitOne(i, 16);
    }

    public static boolean c(int i) {
        return (d(i) || b(i) || e(i)) ? false : true;
    }

    public static void e(List<c> list, int i) {
        c cVar;
        if (koq.b(list)) {
            LogUtil.h("Suggestion_DisPlaySwitch", "showView viewList empty");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Boolean.valueOf(a(i)));
        arrayList.add(Boolean.valueOf(i(i)));
        arrayList.add(Boolean.valueOf(e(i)));
        arrayList.add(Boolean.valueOf(d(i)));
        arrayList.add(Boolean.valueOf(b(i)));
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (!koq.b(list, i2) && (cVar = list.get(i2)) != null) {
                c(((Boolean) arrayList.get(i2)).booleanValue(), cVar);
            }
        }
    }

    private static void c(boolean z, c cVar) {
        View aJS_ = cVar.aJS_();
        if (aJS_ == null) {
            LogUtil.h("Suggestion_DisPlaySwitch", "controlViewDisplay view == null");
        } else if (z) {
            aJS_.setVisibility(0);
        } else {
            aJS_.setVisibility(cVar.c());
        }
    }

    public static class c {
        private View d;
        private int e;

        public c(View view, int i) {
            this.d = view;
            this.e = i;
        }

        public c(View view) {
            this.d = view;
            this.e = 8;
        }

        public View aJS_() {
            return this.d;
        }

        public int c() {
            return this.e;
        }
    }
}
