package defpackage;

import android.content.Context;
import com.huawei.health.R;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/* loaded from: classes8.dex */
public class aba {
    public static String b(Context context, long j) {
        if (j < 0) {
            return "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("0.##");
        if ((j >> 40) != 0) {
            return context.getString(R.string._2130851189_res_0x7f023575, decimalFormat.format((j / 1048576.0f) / 1048576.0f));
        }
        if (((j >> 30) & 1023) != 0) {
            return context.getString(R.string._2130851185_res_0x7f023571, decimalFormat.format(j / 1.0737418E9f));
        }
        if (((j >> 20) & 1023) != 0) {
            return context.getString(R.string._2130851187_res_0x7f023573, decimalFormat.format(j / 1048576.0f));
        }
        float f = j / 1024.0f;
        return f < 1.0f ? context.getString(R.string._2130851186_res_0x7f023572, decimalFormat.format(f)) : context.getString(R.string._2130851186_res_0x7f023572, new BigDecimal(f).setScale(0, 4));
    }
}
