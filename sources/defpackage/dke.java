package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class dke {
    public static void e(msa msaVar) {
        String str = msaVar.b() + "_version";
        String h = msaVar.h();
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(cpp.a(), String.valueOf(1003), str, h, storageParams);
    }

    public static List<String> e(List<msa> list) {
        ArrayList arrayList = new ArrayList(10);
        try {
            Iterator<msa> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().b());
            }
            return CommonUtil.cg() ? d(arrayList) : arrayList;
        } catch (ConcurrentModificationException unused) {
            LogUtil.b("DownloadDeviceResourceUtil", "occur a Exception");
            return arrayList;
        }
    }

    private static List<String> d(List<String> list) {
        list.add(BaseApplication.getContext().getSharedPreferences("data", 0).getString("productId", ""));
        return list;
    }
}
