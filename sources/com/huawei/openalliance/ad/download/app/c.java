package com.huawei.openalliance.ad.download.app;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.y;

/* loaded from: classes5.dex */
public abstract class c {
    public static void b(Context context, y.a aVar) {
        y.a(context, context.getString(R.string._2130851073_res_0x7f023501), cz.a(context, R.string._2130851116_res_0x7f02352c, "hiad_non_wifi_download_prompt", new Object[0]), context.getString(R.string._2130851058_res_0x7f0234f2), context.getString(R.string._2130851070_res_0x7f0234fe), aVar);
    }

    public static void a(Context context, boolean z, y.a aVar) {
        y.a(context, "", context.getString(z ? R.string._2130851050_res_0x7f0234ea : R.string._2130851049_res_0x7f0234e9), context.getString(R.string._2130851081_res_0x7f023509), context.getString(R.string._2130851070_res_0x7f0234fe), aVar);
    }

    public static void a(Context context, y.a aVar) {
        y.a(context, context.getString(R.string._2130851073_res_0x7f023501), context.getString(R.string._2130851181_res_0x7f02356d), context.getString(R.string._2130851058_res_0x7f0234f2), context.getString(R.string._2130851070_res_0x7f0234fe), aVar);
    }

    public static void a(Context context, long j, y.a aVar) {
        y.a(context, context.getString(R.string._2130851132_res_0x7f02353c), context.getString(R.string._2130851110_res_0x7f023526, ae.a(context, j)), context.getString(R.string._2130851067_res_0x7f0234fb), cz.a(context, R.string._2130851131_res_0x7f02353b, "hiad_prepare_download", new Object[0]), aVar);
    }
}
