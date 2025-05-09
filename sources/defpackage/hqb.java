package defpackage;

import android.content.Context;
import com.huawei.health.basesport.wearengine.DeviceStateListener;
import com.huawei.health.basesport.wearengine.SportHiWearBusinessType;
import com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.unite.RunLevelDetail;
import com.huawei.hwcloudmodel.model.unite.UserRunLevelData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cba;
import defpackage.spn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public class hqb implements DeviceStateListener {

    /* renamed from: a, reason: collision with root package name */
    private static hqb f13319a;
    private static final Object c = new Object();
    private hqc e = hqc.c();

    @Override // com.huawei.health.basesport.wearengine.DeviceStateListener
    public void onConnected() {
    }

    @Override // com.huawei.health.basesport.wearengine.DeviceStateListener
    public void onConnecting() {
    }

    @Override // com.huawei.health.basesport.wearengine.DeviceStateListener
    public void onDisconnected() {
    }

    private hqb() {
        b(this);
    }

    private void b(DeviceStateListener deviceStateListener) {
        if (deviceStateListener == null) {
            ReleaseLogUtil.c("RQDataDeviceManager", "registerDeviceStateListener failed with null params");
        } else {
            this.e.registerDeviceStateListener(deviceStateListener);
        }
    }

    public static hqb d(Context context) {
        hqb hqbVar;
        synchronized (c) {
            if (f13319a == null) {
                LogUtil.a("RQDataDeviceManager", "getInstance");
                f13319a = new hqb();
            }
            hqbVar = f13319a;
        }
        return hqbVar;
    }

    public void a(ffp ffpVar, int i, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("RQDataDeviceManager", "pushRQData enter.");
        if (ffpVar == null) {
            iBaseResponseCallback.d(1, 1);
            return;
        }
        int b = cba.b();
        int typeValue = SportHiWearBusinessType.TRACK_RQ_DATA_INFO_FILE.getTypeValue();
        String str = "userRQData_" + typeValue + "_" + b;
        byte[] byteArray = d(ffpVar, i).build().toByteArray();
        cba.b bVar = new cba.b();
        bVar.e(typeValue).a(byteArray.length).b(1).c(b);
        cba a2 = bVar.a();
        LogUtil.c("RQDataDeviceManager", str, a2.toString());
        ByteBuffer allocate = ByteBuffer.allocate(a2.j());
        allocate.put(a2.e());
        allocate.put(byteArray);
        allocate.flip();
        spn.b bVar2 = new spn.b();
        bVar2.c(allocate.array());
        cbc cbcVar = z ? new cbc(this.e, "RQDataDeviceManager", typeValue, b, iBaseResponseCallback) : null;
        this.e.registerCallback(cbcVar, typeValue);
        this.e.sendCommandToDevice(str, bVar2.e(), cbcVar, iBaseResponseCallback);
    }

    private UserRunLevelDataForDevice.RunLevelInfo.Builder d(ffp ffpVar, int i) {
        UserRunLevelDataForDevice.RunLevelInfo.Builder newBuilder = UserRunLevelDataForDevice.RunLevelInfo.newBuilder();
        UserRunLevelData d = ffpVar.d();
        RunLevelDetail runLevelDetail = ffpVar.b().get(Integer.valueOf(i));
        newBuilder.setModifiedTime(ffpVar.c());
        newBuilder.setKm1(d.getTimeForOneKm());
        newBuilder.setKm3(d.getTimeForThreeKm());
        newBuilder.setKm5(d.getTimeForFiveKm());
        newBuilder.setKm10(d.getTimeForTenKm());
        newBuilder.setHalfMarathon(d.getHalfMarathon());
        newBuilder.setMarathon(d.getMarathon());
        newBuilder.setCurrentRunLevel(d.getCurrentRunLevel());
        newBuilder.setRunLevelPercent(d.getRanking());
        newBuilder.setCondition(runLevelDetail.getCondition());
        newBuilder.setFitness(runLevelDetail.getFitness());
        newBuilder.setFatigue(runLevelDetail.getFatigue());
        newBuilder.setTotalPoint(runLevelDetail.getTotalPoint());
        newBuilder.build().toByteArray();
        LogUtil.a("RQDataDeviceManager", "data convert success", newBuilder.build().toString());
        return newBuilder;
    }
}
