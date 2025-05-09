package defpackage;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.sport.model.CourseEnvParams;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public final class ghp {
    public static float d(float f, int i) {
        float f2 = (f * 100.0f) / i;
        if (f2 > 100.0f) {
            return 100.0f;
        }
        return f2;
    }

    public static boolean d(int i) {
        return i >= 40 && i <= 220;
    }

    public static float e(int i, float f) {
        return (i * f) / 100.0f;
    }

    public static int e(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 0;
        }
        if (i != 3) {
            return i;
        }
        return 2;
    }

    public static List<ffd> e(List<ChoreographedSingleAction> list, CourseEnvParams courseEnvParams) {
        ffg ffgVar;
        int i;
        boolean z;
        HeartZoneConf heartZoneConf;
        if (courseEnvParams != null) {
            ffgVar = courseEnvParams.g();
            heartZoneConf = courseEnvParams.c();
            z = courseEnvParams.f();
            i = heartZoneConf != null ? courseEnvParams.c().getMaxThreshold() : 0;
        } else {
            ffgVar = null;
            i = 0;
            z = false;
            heartZoneConf = null;
        }
        int size = list.size();
        int i2 = size + 1;
        ArrayList arrayList = new ArrayList(i2);
        ffu ffuVar = new ffu();
        for (int i3 = 0; i3 < size; i3++) {
            ChoreographedSingleAction choreographedSingleAction = list.get(i3);
            if (choreographedSingleAction != null && choreographedSingleAction.getTargetConfig() != null) {
                int e = (choreographedSingleAction.getAction() == null || choreographedSingleAction.getAction().getId() == null) ? 0 : ffuVar.e(choreographedSingleAction.getAction().getId().trim());
                if (e != 0) {
                    choreographedSingleAction.getAction().setName(BaseApplication.getContext().getString(e));
                }
                ffd b = b(i, size, i3, choreographedSingleAction);
                d(ffgVar, z, heartZoneConf, choreographedSingleAction, b);
                b.a(choreographedSingleAction.getAction().getId());
                arrayList.add(b);
            }
        }
        arrayList.add(new ffd(BaseApplication.getContext().getResources().getString(R.string._2130851556_res_0x7f0236e4), 200, 1.0f, i2, i2));
        return arrayList;
    }

    private static void d(ffg ffgVar, boolean z, HeartZoneConf heartZoneConf, ChoreographedSingleAction choreographedSingleAction, ffd ffdVar) {
        if (choreographedSingleAction.getIntensityConfig() == null || TextUtils.isEmpty(choreographedSingleAction.getIntensityConfig().getId())) {
            return;
        }
        ffdVar.b(fhq.d(choreographedSingleAction.getIntensityConfig(), ffgVar, heartZoneConf, z));
    }

    private static ffd b(int i, int i2, int i3, ChoreographedSingleAction choreographedSingleAction) {
        int i4;
        int d = ggs.d(choreographedSingleAction);
        int h = CommonUtil.h(choreographedSingleAction.getTargetConfig().getId());
        long j = d;
        if (h == 0) {
            i4 = 1;
        } else {
            int i5 = 0;
            if (h == 1) {
                j = TimeUnit.SECONDS.toMillis(j);
            } else if (h == 3) {
                d *= 1000;
                j = d;
                i5 = 2;
            } else {
                if (h == 5 || h == 4) {
                    int a2 = (int) UnitUtil.a((i * d) / 100.0f, 0);
                    j = a2;
                    d = a2;
                }
                i4 = h;
            }
            i4 = i5;
        }
        return new ffd(b(choreographedSingleAction, d, h), i4, j, i3 + 1, i2 + 1);
    }

    private static String b(ChoreographedSingleAction choreographedSingleAction, long j, int i) {
        String string;
        String str;
        String str2 = "";
        if (choreographedSingleAction.getAction() == null) {
            return "";
        }
        if (i == 0) {
            if (UnitUtil.h()) {
                str2 = UnitUtil.e((long) UnitUtil.e(j, 1), 1, 0);
                str = BaseApplication.getContext().getResources().getString(R.string._2130841417_res_0x7f020f49);
            } else {
                str2 = NumberFormat.getInstance().format(j);
                str = BaseApplication.getContext().getResources().getString(R.string._2130841568_res_0x7f020fe0);
            }
        } else {
            if (i == 1) {
                return BaseApplication.getContext().getResources().getString(R.string._2130842426_res_0x7f02133a, choreographedSingleAction.getAction().getName(), c(j));
            }
            if (i == 3) {
                str2 = String.valueOf(j / 1000);
                str = BaseApplication.getContext().getResources().getString(R.string._2130841384_res_0x7f020f28);
            } else {
                if (i == 5 || i == 4) {
                    str2 = new StringBuffer(" ").append(a(i, j)).toString();
                    string = BaseApplication.getContext().getResources().getString(R.string._2130841430_res_0x7f020f56);
                } else if (i == 255) {
                    str = "";
                } else {
                    string = UnitUtil.e(j, 1, 0);
                }
                String str3 = str2;
                str2 = string;
                str = str3;
            }
        }
        return b(choreographedSingleAction.getAction().getName(), str2, str);
    }

    private static String a(int i, long j) {
        if (i == 4) {
            int a2 = (int) UnitUtil.a(j, 0);
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903322_res_0x7f03011a, a2, Integer.valueOf(a2));
        }
        int a3 = (int) UnitUtil.a(j, 0);
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903323_res_0x7f03011b, a3, Integer.valueOf(a3));
    }

    private static String c(long j) {
        int i = (int) j;
        int i2 = i / 3600;
        int i3 = ((int) (j % 3600)) / 60;
        int i4 = i % 60;
        NumberFormat numberFormat = NumberFormat.getInstance();
        StringBuilder sb = new StringBuilder();
        if (i2 != 0) {
            sb.append(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903223_res_0x7f0300b7, i2, numberFormat.format(i2)));
        }
        if (i3 != 0) {
            sb.append(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903305_res_0x7f030109, i3, numberFormat.format(i3)));
        }
        if (i4 != 0) {
            sb.append(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903107_res_0x7f030043, i4, numberFormat.format(i4)));
        }
        return sb.toString();
    }

    private static String b(String str, String str2, String str3) {
        return BaseApplication.getContext().getResources().getString(R.string._2130842426_res_0x7f02133a, str, BaseApplication.getContext().getResources().getString(R.string._2130842426_res_0x7f02133a, str2, str3));
    }

    public static float c(HeartZoneConf heartZoneConf, float f) {
        if (heartZoneConf != null) {
            return (heartZoneConf.getMaxThreshold() * f) / 100.0f;
        }
        return 0.0f;
    }

    public static float e(HeartZoneConf heartZoneConf, float f) {
        if (heartZoneConf != null) {
            return e(heartZoneConf.getMaxThreshold() - heartZoneConf.getRestHeartRate(), f) + heartZoneConf.getRestHeartRate();
        }
        return 0.0f;
    }

    public static int b(HeartZoneConf heartZoneConf, int i, int i2, boolean z) {
        float c;
        if (i != 1) {
            if (i == 4) {
                return c(heartZoneConf, i2, z, 0);
            }
            if (i != 14) {
                if (i == 17) {
                    return b(heartZoneConf, i2, z);
                }
                if (i == 7) {
                    c = e(heartZoneConf, i2);
                } else if (i != 8) {
                    return 0;
                }
            }
            return c(heartZoneConf, i2, z, 1);
        }
        c = c(heartZoneConf, i2);
        return (int) c;
    }

    public static int c(HeartZoneConf heartZoneConf, int i, boolean z, int i2) {
        if (heartZoneConf == null) {
            return 0;
        }
        return heartZoneConf.getZoneValue(i - 1, z, i2);
    }

    public static int b(HeartZoneConf heartZoneConf, int i, boolean z) {
        if (heartZoneConf == null) {
            return 0;
        }
        return heartZoneConf.getZoneValue(i - 1, z);
    }
}
