package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDeleteListenerEx;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwonesdk.process.HiHealthProcess;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class kvc implements HiHealthProcess {
    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public String postProcess(Object obj, Object obj2) {
        return null;
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public List<HiDataDeleteProOption> preProcess(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (str.contains("fields")) {
            for (kva kvaVar : moj.b(str, kva[].class)) {
                HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
                hiDataDeleteOption.setTypes(CommonUtil.b(kuf.a().e(new int[]{kvaVar.a().a()})));
                hiDataDeleteOption.setTimeInterval(kvaVar.d(), kvaVar.d());
                arrayList.add(HiDataDeleteProOption.builder().d(hiDataDeleteOption).c(0).e((Integer) 0).d((Integer) 0).d());
            }
        } else {
            List<kur> b = moj.b(str, kur[].class);
            if (b.size() == 0) {
                kur kurVar = (kur) moj.e(str, kur.class);
                if (kurVar == null) {
                    return null;
                }
                a(kurVar, arrayList);
            } else if (b.size() == 1) {
                kur kurVar2 = (kur) b.get(0);
                if (kurVar2 == null) {
                    return null;
                }
                a(kurVar2, arrayList);
            } else {
                int[] b2 = CommonUtil.b(kuf.a().e(((kur) b.get(0)).j()));
                HiDataDeleteOption hiDataDeleteOption2 = new HiDataDeleteOption();
                hiDataDeleteOption2.setTypes(b2);
                for (kur kurVar3 : b) {
                    if (kurVar3 != null) {
                        hiDataDeleteOption2.addTimeInterval(new HiTimeInterval(kurVar3.e(), kurVar3.d()));
                    }
                }
                arrayList.add(HiDataDeleteProOption.builder().d(hiDataDeleteOption2).c(0).e((Integer) 0).d((Integer) 1).d());
            }
        }
        LogUtil.d("HiHealthProcess_deleteData", "deleteDataProcess preProcess options:", arrayList.toString());
        return arrayList;
    }

    private void a(kur kurVar, List<HiDataDeleteProOption> list) {
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTypes(CommonUtil.b(kuf.a().e(kurVar.j())));
        hiDataDeleteOption.setTimeInterval(kurVar.e(), kurVar.d());
        list.add(HiDataDeleteProOption.builder().d(hiDataDeleteOption).c(0).e((Integer) 0).d((Integer) 1).d(kurVar.a().b()).e(kurVar.a().c()).d());
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public void doAction(String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.c("HiHealthProcess_deleteData", "doAction callback is null");
            return;
        }
        List<HiDataDeleteProOption> preProcess = preProcess(str);
        if (koq.b(preProcess)) {
            LogUtil.d("HiHealthProcess_deleteData", "HiDataAggregateProOption list is empty");
            iBaseResponseCallback.d(-1, "");
        } else {
            LogUtil.d("HiHealthProcess_deleteData", "deleteHiHealthDataProEx ", preProcess);
            HiHealthManager.d(BaseApplication.e()).deleteHiHealthDataProEx(preProcess, new HiDeleteListenerEx() { // from class: kvc.5
                @Override // com.huawei.hihealth.data.listener.HiDeleteListenerEx
                public void onResult(Map<Integer, List<Object>> map) {
                    LogUtil.d("HiHealthProcess_deleteData", "deleteData result", map);
                    iBaseResponseCallback.d(0, "");
                }
            });
        }
    }
}
