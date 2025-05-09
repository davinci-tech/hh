package defpackage;

import com.huawei.health.R;
import com.huawei.health.suggestion.ui.voice.IVoiceContentConstructor;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class gfu implements IVoiceContentConstructor {

    /* renamed from: a, reason: collision with root package name */
    private static volatile gfu f12792a;
    private int b = R.raw._2131886248_res_0x7f1200a8;
    private int g = R.raw._2131886247_res_0x7f1200a7;
    private int d = R.raw._2131886244_res_0x7f1200a4;
    private int c = R.raw._2131886245_res_0x7f1200a5;
    private int e = R.raw._2131886246_res_0x7f1200a6;

    private gfu() {
    }

    public static gfu e() {
        if (f12792a == null) {
            synchronized (gfu.class) {
                if (f12792a == null) {
                    f12792a = new gfu();
                }
            }
        }
        return f12792a;
    }

    private Integer e(int i) {
        if (i == 1) {
            return Integer.valueOf(this.b);
        }
        if (i == 2) {
            return Integer.valueOf(this.g);
        }
        if (i == 3) {
            return Integer.valueOf(this.d);
        }
        if (i == 4) {
            return Integer.valueOf(this.c);
        }
        if (i != 5) {
            return null;
        }
        return Integer.valueOf(this.e);
    }

    private Object c(Integer num) {
        ArrayList arrayList = new ArrayList();
        int intValue = num.intValue();
        if (intValue == 1) {
            arrayList.add(Integer.valueOf(R.raw._2131886293_res_0x7f1200d5));
            arrayList.add(Integer.valueOf(R.raw._2131886223_res_0x7f12008f));
        } else if (intValue == 2) {
            arrayList.add(Integer.valueOf(R.raw._2131886243_res_0x7f1200a3));
            arrayList.add(Integer.valueOf(R.raw._2131886222_res_0x7f12008e));
        }
        return arrayList;
    }

    @Override // com.huawei.health.suggestion.ui.voice.IVoiceContentConstructor
    public Object constructContent(int i, Object obj) {
        if (i == 1) {
            if (obj instanceof Integer) {
                return e(((Integer) obj).intValue());
            }
        } else if (i == 101 && (obj instanceof Integer)) {
            return c((Integer) obj);
        }
        return null;
    }
}
