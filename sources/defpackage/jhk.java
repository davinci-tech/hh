package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy;
import health.compact.a.HEXUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class jhk implements DataReceiveCallback {
    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.c("UIDV_SportGoalListMgr", "onDataReceived errorCode ", Integer.valueOf(i));
    }

    public void b() {
        a("900200006");
    }

    public void a(final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: jhl
            @Override // java.lang.Runnable
            public final void run() {
                jhk.this.c(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void c(String str) {
        if (!e(str) && jhc.e()) {
            LogUtil.c("UIDV_SportGoalListMgr", "setThreeCircleGoal enter ");
            d();
            DeviceInfo d = jpt.d("UIDV_SportGoalListMgr");
            if (d != null) {
                ArrayList arrayList = new ArrayList(3);
                arrayList.add("900200006");
                arrayList.add("900200008");
                arrayList.add("900200009");
                nip.a(arrayList, new c(d));
            }
        }
    }

    private boolean e(String str) {
        return str.equals("900200011") || str.equals("900200004") || str.equals("900200010");
    }

    private void d() {
        cuk.b().registerDeviceSampleListener(SyncStrategy.SRC_PKG_NAME, this);
    }

    static class c implements IBaseResponseCallback {
        private DeviceInfo b;

        private c(DeviceInfo deviceInfo) {
            this.b = deviceInfo;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (this.b == null) {
                LogUtil.a("UIDV_SportGoalListMgr", "SportAchievementGoalDataCallback mWeakReference|mDeviceInfo == null ");
                return;
            }
            if (!(obj instanceof HashMap)) {
                LogUtil.a("UIDV_SportGoalListMgr", "setThreeCircleGoal objData is not instanceof HashMap");
                return;
            }
            HashMap hashMap = (HashMap) obj;
            int e = nip.e(hashMap, "900200006", 10000);
            int e2 = nip.e(hashMap, "900200008", 25);
            int e3 = nip.e(hashMap, "900200009", 12);
            ReleaseLogUtil.b("UIDV_SportGoalListMgr", "setThreeCircleGoal stepValue is ", Integer.valueOf(e), " intensityValue is ", Integer.valueOf(e2), " standValue is ", Integer.valueOf(e3));
            LogUtil.c("UIDV_SportGoalListMgr", "setThreeCircleGoal isSuccess ", Boolean.valueOf(cuk.b().sendSampleConfigCommand(this.b, jhk.d(new int[]{e, e2, e3}), "UIDV_SportGoalListMgr")));
        }
    }

    public void a() {
        c();
    }

    private void c() {
        cuk.b().unRegisterDeviceSampleListener(SyncStrategy.SRC_PKG_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static cvq d(int[] iArr) {
        LogUtil.c("UIDV_SportGoalListMgr", "constructSampleConfig enter ");
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName(SyncStrategy.SRC_PKG_NAME);
        cvqVar.setWearPkgName(SyncStrategy.WEAR_PKG_NAME);
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(900200002L);
        cvnVar.d(1);
        StringBuilder sb = new StringBuilder(16);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        sb.append(HEXUtils.e(1));
        sb.append(HEXUtils.e(HEXUtils.e(currentTimeMillis).length() / 2));
        sb.append(HEXUtils.e(currentTimeMillis));
        int[] iArr2 = {1, 3, 4};
        StringBuilder sb2 = new StringBuilder(16);
        for (int i = 0; i < 3; i++) {
            StringBuilder sb3 = new StringBuilder(16);
            sb3.append(HEXUtils.e(4));
            sb3.append(HEXUtils.e(HEXUtils.e(iArr2[i]).length() / 2));
            sb3.append(HEXUtils.e(iArr2[i]));
            sb3.append(HEXUtils.e(5));
            sb3.append(HEXUtils.e(HEXUtils.e(iArr[i]).length() / 2));
            sb3.append(HEXUtils.e(iArr[i]));
            sb2.append(HEXUtils.e(131));
            sb2.append(HEXUtils.e(sb3.toString().length() / 2));
            sb2.append((CharSequence) sb3);
        }
        sb.append(HEXUtils.e(OldToNewMotionPath.SPORT_TYPE_TENNIS));
        sb.append(HEXUtils.e(sb2.toString().length() / 2));
        sb.append((CharSequence) sb2);
        LogUtil.c("UIDV_SportGoalListMgr", "constructSampleConfig structStringBuilder ", Integer.valueOf(sb.length()), " structStringBuilder is ", sb);
        cvnVar.c(HEXUtils.c(sb.toString()));
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }
}
