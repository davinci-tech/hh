package defpackage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class koa implements Serializable {
    private static final long serialVersionUID = -406367943615323220L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mType")
    private int f14446a;

    @SerializedName("mOptionData")
    private Map<String, String> b = new HashMap();

    @SerializedName("mEventId")
    private int c;

    @SerializedName("mEmergence")
    private int d;

    @SerializedName("mMessageTime")
    private long e;

    public koa(String[] strArr) {
        if (strArr.length < 6) {
            LogUtil.a("Track_SportEventMessage", "SportEventMessage is too short");
            return;
        }
        this.e = CommonUtils.g(strArr[1].split("=")[1]);
        this.f14446a = CommonUtils.h(strArr[2].split("=")[1]);
        this.d = CommonUtils.h(strArr[3].split("=")[1]);
        this.c = CommonUtils.h(strArr[4].split("=")[1]);
        for (int i = 5; i < strArr.length; i++) {
            c(strArr[i].split("=")[0], strArr[i].split("=")[1]);
        }
    }

    public koa(String str) {
        if (str == null || str.length() != 32) {
            LogUtil.h("Track_SportEventMessage", "eventDataStr is valid");
            return;
        }
        this.e = CommonUtils.b(c(str, 0, 8), 16);
        this.f14446a = CommonUtils.c(c(str, 16, 18), 16);
        this.c = CommonUtils.c(c(str, 18, 20), 16);
        this.d = CommonUtils.c(c(str, 20, 22), 16);
        a(str);
    }

    private void a(String str) {
        String str2;
        c("depth", Integer.toString(CommonUtils.c(c(str, 12, 16), 16)));
        int i = this.c;
        if (i == 1 || i == 11) {
            str2 = "m-r-s";
        } else if (i == 10 || i == 16) {
            str2 = HwExerciseConstants.EXTRA_CNS;
        } else if (i == 13 || i == 14) {
            str2 = HwExerciseConstants.EXTRA_PRESSURE_OXYGEN;
        } else if (i == 21) {
            str2 = "angle";
        } else {
            if (i == 24) {
                c("gas", CommonUtils.c(c(str, 28, 30), 16) + "/" + CommonUtils.c(c(str, 30, 32), 16));
                return;
            }
            str2 = i == 25 ? "ndl" : "";
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        c(str2, Integer.toString(CommonUtils.c(c(str, 28, 32), 16)));
    }

    public koa c(String str, String str2) {
        if (!this.b.containsKey(str) && str != null) {
            this.b.put(str, str2);
        }
        return this;
    }

    public String toString() {
        return "tp=msg;t=" + this.e + ";ty=" + this.f14446a + ";e=" + this.d + ";id=" + this.c + ";" + ((CharSequence) a()) + System.lineSeparator();
    }

    private StringBuilder a() {
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = this.b;
        if (map == null) {
            return sb;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append(";");
        }
        return sb;
    }

    private static String c(String str, int i, int i2) {
        String substring = str.substring(i, i2);
        StringBuilder sb = new StringBuilder();
        while (substring.length() > 2) {
            sb.append(substring.substring(substring.length() - 2));
            substring = substring.substring(0, substring.length() - 2);
        }
        sb.append(substring);
        return sb.toString();
    }
}
