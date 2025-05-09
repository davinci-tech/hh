package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.MusicInfo;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class khf extends HwBaseManager implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14368a = new Object();
    private static khf d;
    private static HwDeviceMgrInterface e;
    private Handler b;
    private boolean c;

    private khf(Context context) {
        super(context);
        this.c = false;
        this.b = new Handler(Looper.getMainLooper()) { // from class: khf.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 1) {
                    LogUtil.a("MusicControlManager", "handleMessage MSG_REPORT_MUSIC_INFO 5.37.1");
                    khf.this.f();
                    return;
                }
                if (i == 2) {
                    LogUtil.a("MusicControlManager", "handleMessage unregister callback 5.37.3 100");
                    khf.this.m();
                    khf.this.c = false;
                } else {
                    if (i == 3) {
                        LogUtil.a("MusicControlManager", "MESSAGE_SETTING_VOLUME_DELAY.");
                        if (message.obj instanceof cwd) {
                            khf.this.b((cwd) message.obj);
                            return;
                        }
                        return;
                    }
                    LogUtil.h("MusicControlManager", "Invalid music message.");
                }
            }
        };
        e = jsz.b(BaseApplication.getContext());
    }

    public static khf c() {
        khf khfVar;
        synchronized (f14368a) {
            if (d == null) {
                d = new khf(BaseApplication.getContext());
            }
            khfVar = d;
        }
        return khfVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 2037;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null || bArr == null || bArr.length < 2) {
            LogUtil.h("MusicControlManager", "device or dataContents is null");
            return;
        }
        String d2 = cvx.d(bArr);
        if (d2 != null && d2.length() >= 4) {
            try {
                List<cwd> e2 = new cwl().a(d2.substring(4)).e();
                boolean z = false;
                blt.d("MusicControlManager", bArr, "getResult(): ");
                DeviceCapability e3 = e(deviceInfo);
                if (e3 != null) {
                    LogUtil.a("MusicControlManager", "isSupportMusicControl deviceCapability is not null");
                    z = e3.isSupportMusicControl();
                }
                b(bArr, e2, z);
                return;
            } catch (cwg unused) {
                LogUtil.b("MusicControlManager", "TlvException");
                return;
            } catch (IndexOutOfBoundsException unused2) {
                LogUtil.b("MusicControlManager", "IndexOutOfBoundsException");
                return;
            }
        }
        LogUtil.b("MusicControlManager", "data lenth less 4");
    }

    private DeviceCapability e(DeviceInfo deviceInfo) {
        DeviceCapability deviceCapability = new DeviceCapability();
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "MusicControlManager");
        return (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) ? deviceCapability : queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }

    private void b(byte[] bArr, List<cwd> list, boolean z) {
        if (!z) {
            LogUtil.a("MusicControlManager", "not SupportMusicControl");
            return;
        }
        byte b = bArr[1];
        if (b != 1) {
            if (b == 3) {
                b(bArr, list);
                return;
            } else {
                LogUtil.h("MusicControlManager", "other command");
                return;
            }
        }
        if (bArr.length <= 3 || bArr[2] != 1) {
            return;
        }
        this.b.sendEmptyMessage(1);
    }

    private void b(byte[] bArr, List<cwd> list) {
        if (bArr == null || list == null) {
            return;
        }
        for (cwd cwdVar : list) {
            try {
                int parseInt = Integer.parseInt(cwdVar.e(), 16);
                if (parseInt == 1) {
                    b(bArr);
                } else if (parseInt == 2) {
                    this.b.removeMessages(3);
                    Message obtainMessage = this.b.obtainMessage();
                    obtainMessage.obj = cwdVar;
                    obtainMessage.what = 3;
                    this.b.sendMessageDelayed(obtainMessage, 50L);
                } else {
                    LogUtil.h("MusicControlManager", "Invalid control types");
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("MusicControlManager", "musicOperation NumberFormatException");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(cwd cwdVar) {
        int a2 = CommonUtil.a(cwdVar.c(), 16);
        if (a2 != -1 && a()) {
            khd.d().setVolume(a2);
            c(100000);
        } else {
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("MusicControlManager", "enter reportMusicInfo");
        if (!g()) {
            d(136001);
            return;
        }
        j();
        MusicInfo e2 = e();
        if (e2 == null || (e2.getPlayState() == 0 && e2.getMaxVolume() == 0)) {
            d(136002);
        } else {
            d(100000);
            b(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MusicInfo e() {
        return khd.d().getMusicInfo();
    }

    private void j() {
        if (!this.c) {
            LogUtil.a("MusicControlManager", "enter registerMusicCallback");
            i();
            this.c = true;
            return;
        }
        LogUtil.a("MusicControlManager", "have already registerMusicCallback!");
    }

    private void i() {
        khd.d().registerMusicCallback(new ControlInterface.MusicChangeCallback() { // from class: khf.1
            @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface.MusicChangeCallback
            public void onMusicChanged() {
                LogUtil.a("MusicControlManager", "onMusicChanged");
                MusicInfo e2 = khf.this.e();
                if (!khf.this.g() || e2 == null) {
                    return;
                }
                khf.this.b(e2);
            }

            @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface.MusicChangeCallback
            public void onMusicDiedTimeOut() {
                LogUtil.a("MusicControlManager", "onMusicDiedTimeOut");
                khf.this.c = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MusicInfo musicInfo) {
        if ((musicInfo == null || (musicInfo.getSingerName() == null && musicInfo.getSongName() == null)) && !khd.d().b()) {
            ReleaseLogUtil.e("R_MusicControlManager", "sendMusicInfo error");
            return;
        }
        ReleaseLogUtil.e("R_MusicControlManager", "sendMusicInfo musicInfo:", musicInfo.toString());
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(37);
        deviceCommand.setCommandID(2);
        byte[] b = khc.b(musicInfo);
        deviceCommand.setDataLen(b.length);
        deviceCommand.setDataContent(b);
        e.sendDeviceData(deviceCommand);
        khd.d().removeMusicUpdate();
    }

    private void d(int i) {
        LogUtil.a("MusicControlManager", "sendAppStatus errorCode:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(37);
        deviceCommand.setCommandID(1);
        byte[] c = khc.c(i);
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        e.sendDeviceData(deviceCommand);
    }

    private void l() {
        if (g()) {
            c(136002);
        } else {
            c(136001);
        }
    }

    private void c(int i) {
        LogUtil.a("MusicControlManager", "sendAppControlResponse errorCode:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(37);
        deviceCommand.setCommandID(3);
        byte[] c = khc.c(i);
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        e.sendDeviceData(deviceCommand);
    }

    private void b(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (d2 == null) {
            return;
        }
        try {
            int a2 = CommonUtil.a(new cwl().a(d2.substring(4, d2.length())).e().get(0).c(), 16);
            ReleaseLogUtil.e("R_MusicControlManager", "handleMusicControl :", Integer.valueOf(a2));
            if (a2 == 100) {
                this.b.sendEmptyMessage(2);
                c(100000);
                this.c = false;
                return;
            }
            h();
            if (!this.c && a2 != 5 && a2 != 6) {
                if (!g()) {
                    c(136001);
                } else if (e() == null) {
                    c(136002);
                } else {
                    LogUtil.a("MusicControlManager", "The music is playing.");
                }
                LogUtil.a("MusicControlManager", "registerMusicCall is false , can not control music!");
                return;
            }
            if (a2 == 1) {
                if (!g()) {
                    d(136001);
                    return;
                }
                j();
                khd.d().e(1);
                c(100000);
                return;
            }
            if (a2 == 2) {
                if (a()) {
                    khd.d().e(2);
                    c(100000);
                    return;
                } else {
                    l();
                    return;
                }
            }
            e(a2);
        } catch (cwg unused) {
            LogUtil.b("MusicControlManager", "handleMusicControl error");
        }
    }

    private void h() {
        if (this.c || !g()) {
            return;
        }
        this.b.sendEmptyMessage(1);
        try {
            TimeUnit.MILLISECONDS.sleep(700L);
        } catch (InterruptedException unused) {
            LogUtil.b("MusicControlManager", "RegisterMusicListener InterruptedException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (g()) {
            khd.d().unRegisterMusicCallback();
        }
    }

    private void e(int i) {
        boolean a2 = a();
        if (i == 3) {
            if (a2) {
                c(100000);
                khd.d().e(3);
                return;
            } else {
                l();
                return;
            }
        }
        if (i == 4) {
            if (a2) {
                c(100000);
                khd.d().e(4);
                return;
            } else {
                l();
                return;
            }
        }
        if (i == 5) {
            if (a2) {
                c(100000);
                d();
                return;
            } else {
                l();
                return;
            }
        }
        if (i != 6) {
            LogUtil.h("MusicControlManager", "Invalid switching music instructions.");
        } else if (a2) {
            c(100000);
            b();
        } else {
            l();
        }
    }

    private void d() {
        khd.d().controlVolume(true);
        c(100000);
    }

    private void b() {
        khd.d().controlVolume(false);
        c(100000);
    }

    private boolean a() {
        MusicInfo e2 = e();
        boolean z = e2 == null || (e2.getPlayState() == 0 && e2.getMaxVolume() == 0);
        if (!g()) {
            d(136001);
        } else if (z) {
            LogUtil.a("MusicControlManager", "checkState musicAPP is died!");
            d(136002);
        } else {
            LogUtil.a("MusicControlManager", "The music is playing.");
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        String switchSettingFromDb;
        return (!jrg.b() || (switchSettingFromDb = jqi.a().getSwitchSettingFromDb("music_control_status")) == null || "false".equals(switchSettingFromDb)) ? false : true;
    }
}
