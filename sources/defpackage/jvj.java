package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class jvj {
    private static jvj b;
    private static final Object d = new Object();

    public static jvj e() {
        jvj jvjVar;
        synchronized (d) {
            if (b == null) {
                b = new jvj();
            }
            jvjVar = b;
        }
        return jvjVar;
    }

    public boolean e(TransferFileInfo transferFileInfo) {
        long j;
        LogUtil.a("HwFileTransferTaskManager", "enter isResetContinueTask");
        if (transferFileInfo != null && jvk.b().b(transferFileInfo)) {
            long currentTimeMillis = System.currentTimeMillis();
            String str = "LAST_CONTINUE_TIME" + knl.d(transferFileInfo.getDeviceSn());
            String e = jvk.b().e(str);
            if (TextUtils.isEmpty(e)) {
                LogUtil.a("HwFileTransferTaskManager", "lastTimeString is empty");
                jvk.b().c(str, String.valueOf(currentTimeMillis));
                return false;
            }
            try {
                j = Long.parseLong(e);
            } catch (NumberFormatException unused) {
                LogUtil.b("HwFileTransferTaskManager", "isResetContinueTask NumberFormatException.");
                j = 0;
            }
            LogUtil.a("HwFileTransferTaskManager", "last time is:", Long.valueOf(j), ", current time is:", Long.valueOf(currentTimeMillis));
            if (currentTimeMillis - j > 86400000) {
                jvk.b().c(str, "");
                return true;
            }
        }
        return false;
    }

    public void e(ArrayList arrayList, TransferFileInfo transferFileInfo) {
        if (!jvk.b().b(transferFileInfo) || arrayList == null || arrayList.isEmpty()) {
            LogUtil.h("HwFileTransferTaskManager", "compareList Invalid argument.");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (int i = 0; i < arrayList.size() - 1; i++) {
            stringBuffer.append(arrayList.get(i).toString() + ',');
        }
        stringBuffer.append(arrayList.get(arrayList.size() - 1).toString());
        String e = jvk.b().e("CONTINUE_LOG_LIST");
        if (e == null) {
            jvk.b().e(stringBuffer, arrayList);
            return;
        }
        ArrayList arrayList2 = new ArrayList(Arrays.asList(e.split(",")));
        ArrayList<String> arrayList3 = new ArrayList<>(arrayList2);
        if (koq.b(arrayList, arrayList2, false)) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "filelist is same with the previou");
            String e2 = jvk.b().e("CONTINUE_LOG_NAME");
            if (e2 != null && e.indexOf(e2) != -1) {
                d(e2, arrayList3, arrayList);
                return;
            }
        }
        jvk.b().c(stringBuffer, arrayList);
    }

    private void d(String str, ArrayList<String> arrayList, ArrayList arrayList2) {
        if (arrayList == null) {
            return;
        }
        String str2 = arrayList.get(0);
        while (!str.equals(str2)) {
            arrayList.remove(0);
            if (!arrayList.isEmpty()) {
                str2 = arrayList.get(0);
            }
        }
        arrayList2.clear();
        Object clone = arrayList.clone();
        if (clone instanceof ArrayList) {
            arrayList2.addAll((ArrayList) clone);
        }
    }
}
