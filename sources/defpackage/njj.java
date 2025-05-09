package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class njj {
    public static void a(String str, String str2, String str3, HiDataOperateListener hiDataOperateListener, long j) {
        if (hiDataOperateListener == null) {
            LogUtil.h("SampleConfigUtils", "setSampleConfig listener is null");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            hiDataOperateListener.onResult(-1, "setSampleConfig type " + str + " configId " + str2 + " configData " + str3);
            return;
        }
        HiSampleConfig.Builder builder = new HiSampleConfig.Builder();
        builder.c(j);
        builder.g(String.valueOf(0));
        builder.c(String.valueOf(-1));
        builder.h(String.valueOf(0));
        builder.j(str);
        builder.d(str2);
        builder.b(str3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiSampleConfig(builder));
        HiHealthManager.d(BaseApplication.e()).setSampleConfig(arrayList, hiDataOperateListener);
    }

    public static void d(final String str, final List<String> list, final HiDataReadResultListener hiDataReadResultListener) {
        if (hiDataReadResultListener == null) {
            LogUtil.h("SampleConfigUtils", "getSampleConfig listener is null");
            return;
        }
        if (TextUtils.isEmpty(str) || koq.b(list)) {
            hiDataReadResultListener.onResult("getSampleConfig type " + str + " configId " + list, -1, -1);
            return;
        }
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: njk
                @Override // java.lang.Runnable
                public final void run() {
                    njj.d(str, list, hiDataReadResultListener);
                }
            });
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new HiSampleConfigKey(d(str, it.next())));
        }
        HiSampleConfigProcessOption hiSampleConfigProcessOption = new HiSampleConfigProcessOption();
        hiSampleConfigProcessOption.setSampleConfigKeyList(arrayList);
        HiHealthManager.d(BaseApplication.e()).getSampleConfig(hiSampleConfigProcessOption, hiDataReadResultListener);
    }

    private static HiSampleConfigKey.Builder d(String str, String str2) {
        HiSampleConfigKey.Builder builder = new HiSampleConfigKey.Builder();
        builder.a(String.valueOf(0));
        builder.e(String.valueOf(0));
        builder.b(str);
        builder.d(str2);
        return builder;
    }
}
