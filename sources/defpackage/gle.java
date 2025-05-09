package defpackage;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes4.dex */
public class gle {
    private static long d = 1000;

    /* renamed from: a, reason: collision with root package name */
    private CountDownTimer f12847a;
    private long b;
    WeakReference<TextView> c;
    private Handler e;

    public gle(Handler handler, TextView textView, long j) {
        this.e = handler;
        this.c = new WeakReference<>(textView);
        this.b = j;
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [gle$4] */
    public void c() {
        this.f12847a = new CountDownTimer(this.b, d) { // from class: gle.4
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (gle.this.c.get() != null) {
                    gle.this.e.sendEmptyMessage(1103);
                }
                gle.this.f12847a.cancel();
                gle.this.f12847a = null;
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                String str;
                String format;
                TextView textView = gle.this.c.get();
                if (textView != null) {
                    int[] d2 = gle.d(j);
                    int i = d2[0];
                    if (i > 0) {
                        str = BaseApplication.getContext().getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, d2[0], UnitUtil.e(i, 1, 0)) + gle.c(d2[1]) + ":" + gle.c(d2[2]);
                        format = String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844779_res_0x7f021c6b), str);
                    } else {
                        str = gle.c(d2[1]) + ":" + gle.c(d2[2]) + ":" + gle.c(d2[3]);
                        format = String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844779_res_0x7f021c6b), str);
                    }
                    gla.aNK_(format, str, textView, R.color.emui_accent);
                    return;
                }
                LogUtil.h("TimeUtils", "textView = null");
            }
        }.start();
    }

    public void e() {
        CountDownTimer countDownTimer = this.f12847a;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f12847a = null;
        }
    }

    public static int[] d(long j) {
        int i = (int) (j / 86400000);
        long j2 = j - (i * 86400000);
        int i2 = (int) (j2 / 3600000);
        long j3 = j2 - (i2 * 3600000);
        int i3 = (int) (j3 / 60000);
        return new int[]{i, i2, i3, (int) ((j3 - (i3 * 60000)) / 1000)};
    }

    public static String c(int i) {
        String e = UnitUtil.e(i, 1, 0);
        String e2 = UnitUtil.e(0.0d, 1, 0);
        if (String.valueOf(i).length() != 1) {
            return e;
        }
        return e2 + e;
    }
}
