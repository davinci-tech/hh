package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwonesdk.process.HiHealthProcess;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes9.dex */
public class kvl implements HiHealthProcess {
    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public String postProcess(Object obj, Object obj2) {
        return null;
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public HiDataInsertOption preProcess(String str) {
        kup kupVar = (kup) moj.e(str, kup.class);
        LogUtil.d("HiHealthProcess__SaveDataProcess", "SaveDataProcess request: ", kupVar);
        if (kupVar == null) {
            ReleaseLogUtil.c("HiHealthProcess__SaveDataProcess", "preProcess request is empty");
            return null;
        }
        kun l = kupVar.l();
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, Object>> it = kupVar.n().entrySet().iterator();
        while (it.hasNext()) {
            for (int i : kuf.a().c(l.a(), it.next().getKey())) {
                HiHealthData hiHealthData = new HiHealthData(i);
                hiHealthData.setTimeInterval(kupVar.e(), kupVar.d());
                String g = kupVar.g();
                if (TextUtils.isEmpty(g)) {
                    g = "-1";
                }
                hiHealthData.setDeviceUuid(g);
                hiHealthData.setValue(Math.round(((Double) r3.getValue()).doubleValue()));
                hiHealthData.setTimeZone(kupVar.f());
                arrayList.add(hiHealthData);
            }
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        return hiDataInsertOption;
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public void doAction(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.c("HiHealthProcess__SaveDataProcess", "doAction callback is null");
            return;
        }
        HiDataInsertOption preProcess = preProcess(str);
        LogUtil.d("HiHealthProcess__SaveDataProcess", "preProcess insertOption", preProcess);
        if (preProcess == null) {
            iBaseResponseCallback.d(-1, "");
            ReleaseLogUtil.c("HiHealthProcess__SaveDataProcess", "doAction insertOption is null");
        } else {
            HiHealthManager.d(BaseApplication.e()).insertHiHealthData(preProcess, new b(iBaseResponseCallback));
        }
    }

    static class b implements HiDataOperateListener {
        private final IBaseResponseCallback b;

        b(IBaseResponseCallback iBaseResponseCallback) {
            this.b = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            LogUtil.d("HiHealthProcess__SaveDataProcess", "insertHiHealthData ", Integer.valueOf(i));
            IBaseResponseCallback iBaseResponseCallback = this.b;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i, obj);
            }
        }
    }
}
