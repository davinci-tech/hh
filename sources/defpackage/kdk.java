package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import defpackage.gmu;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class kdk implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private static volatile kdk f14306a;
    private static final Object c = new Object();
    private String b;
    private String d;
    private String e;
    private gmu f;
    private cwl g = new cwl();
    private Context j;

    private kdk(Context context) {
        this.j = context;
    }

    public String e() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String c() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public String b() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.d("HwSosManager", "handleResultFromDevice:", cvx.d(bArr));
        if (bArr == null || bArr.length <= 1) {
            LogUtil.c("HwSosManager", "data illegal");
            return;
        }
        ReleaseLogUtil.e("DEVMGR_HwSosManager", "handleResultFromDevice commandId:", Byte.valueOf(bArr[1]));
        byte b = bArr[1];
        if (b == 1) {
            e(bArr);
            return;
        }
        if (b == 2) {
            c(bArr, deviceInfo);
        } else if (b == 4) {
            b(bArr);
        } else {
            LogUtil.c("HwSosManager", "handleResultFromDevice nothing to do");
        }
    }

    public static kdk a() {
        kdk kdkVar;
        synchronized (c) {
            if (f14306a == null) {
                f14306a = new kdk(BaseApplication.getContext());
            }
            kdkVar = f14306a;
        }
        return kdkVar;
    }

    public void d(gmu gmuVar) {
        ReleaseLogUtil.e("DEVMGR_HwSosManager", "syncEmergencyInfoToDevice enter");
        b(kdn.e(kdn.a(gmuVar)));
    }

    public void b(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("DEVMGR_HwSosManager", "syncImageData enter");
        b(kdn.d(kdn.b()), deviceInfo);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void c(String str, gmu gmuVar) {
        char c2;
        String c3;
        ReleaseLogUtil.e("DEVMGR_HwSosManager", "syncSomeInfoToDevice enter updateKey:", str);
        if (TextUtils.isEmpty(str) || gmuVar == null) {
            ReleaseLogUtil.d("DEVMGR_HwSosManager", "syncSomeInfoToDevice updateKey is empty or emergencyInfo is null");
            sqo.aj("HwSosManager syncSomeInfoToDevice: updateKey is empty or emergencyInfo is null");
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1148703137:
                if (str.equals("blood_type")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1147692044:
                if (str.equals("address")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1099451720:
                if (str.equals("organ_donor")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -9479843:
                if (str.equals("key_update_all_emergency")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 3373707:
                if (str.equals("name")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 81679390:
                if (str.equals("allergies")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 218146849:
                if (str.equals("key_clear_all_emergency")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 522223425:
                if (str.equals("emergency_contacts")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 1838387076:
                if (str.equals("medications")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 2111429926:
                if (str.equals("medical_conditions")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                c3 = kdn.c(3, gmuVar.e());
                break;
            case 1:
                c3 = kdn.a(2, gmuVar.f());
                break;
            case 2:
                c3 = kdn.b(6, gmuVar.g());
                break;
            case 3:
            case 6:
                LogUtil.d("HwSosManager", "clear or update emergency info");
                c3 = kdn.a(gmuVar);
                break;
            case 4:
                c3 = kdn.a(1, gmuVar.h());
                break;
            case 5:
                c3 = kdn.a(4, gmuVar.b());
                break;
            case 7:
                LogUtil.d("HwSosManager", "buildUpSomeEmergencyInfo updateContact");
                c3 = kdn.e(gmuVar);
                break;
            case '\b':
                c3 = kdn.a(5, gmuVar.a());
                break;
            case '\t':
                c3 = kdn.a(7, gmuVar.c());
                break;
            default:
                LogUtil.d("HwSosManager", "buildUpSomeEmergencyInfo default");
                c3 = "";
                break;
        }
        b(kdn.e(c3));
    }

    private void b(DeviceCommand deviceCommand) {
        if (deviceCommand != null) {
            LogUtil.d("HwSosManager", "sendDeviceCommand, deviceCommand:", deviceCommand.toString());
            jsz.b(this.j).sendDeviceData(deviceCommand);
        }
    }

    private void b(DeviceCommand deviceCommand, DeviceInfo deviceInfo) {
        if (deviceCommand == null || deviceCommand.getDataLen() <= 0) {
            return;
        }
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.d("HwSosManager", "deviceCommand:", deviceCommand.toString());
        jsz.b(this.j).sendDeviceData(deviceCommand);
    }

    private void e(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.d("HwSosManager", "5.51.1 handleEmergencyInfo:", d);
        if (TextUtils.isEmpty(d) || d.length() <= 4) {
            ReleaseLogUtil.d("DEVMGR_HwSosManager", "handleEmergencyInfo data is error");
            sqo.aj("HwSosManager handleEmergencyInfo: data is error");
            return;
        }
        try {
            List<cwd> e = this.g.a(d.substring(4)).e();
            if (e != null && !e.isEmpty()) {
                for (cwd cwdVar : e) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c2 = cwdVar.c();
                    if (a2 != 127) {
                        ReleaseLogUtil.d("DEVMGR_HwSosManager", "handleEmergencyInfo, nothing to do");
                    } else {
                        ReleaseLogUtil.e("DEVMGR_HwSosManager", "handleEmergencyInfo ERROR_CODE:", Integer.valueOf(CommonUtil.a(c2, 16)));
                    }
                }
                return;
            }
            ReleaseLogUtil.d("DEVMGR_HwSosManager", "handleEmergencyInfo tlv error");
            sqo.aj("HwSosManager handleEmergencyInfo: tlvList is null or empty");
        } catch (cwg unused) {
            ReleaseLogUtil.c("DEVMGR_HwSosManager", "handleEmergencyInfo error");
            sqo.aj("HwSosManager handleEmergencyInfo: TlvException");
        }
    }

    private void c(String str, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("DEVMGR_HwSosManager", "sendEmergencyInfoMessage enter");
        d(deviceInfo);
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_HwSosManager", "sendEmergencyMessage messageContent is null or empty string");
            sqo.aj("HwSosManager sendEmergencyInfoMessage: msgContent is empty");
            return;
        }
        if (!j()) {
            ReleaseLogUtil.d("DEVMGR_HwSosManager", "sendEmergencyMessage canSendMessage is false");
            return;
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(this.j, 0, new Intent("SEND_SMS_ACTION"), AppRouterExtras.COLDSTART);
        SmsManager smsManager = SmsManager.getDefault();
        Iterator<gmu.e> it = this.f.d().iterator();
        while (it.hasNext()) {
            gmu.e next = it.next();
            if (next != null) {
                String d = next.d();
                ArrayList<String> divideMessage = smsManager.divideMessage(str);
                if (!TextUtils.isEmpty(d) && divideMessage != null && divideMessage.size() > 0) {
                    ArrayList<PendingIntent> arrayList = new ArrayList<>();
                    for (int i = 0; i < divideMessage.size(); i++) {
                        arrayList.add(broadcast);
                    }
                    smsManager.sendMultipartTextMessage(d, null, divideMessage, arrayList, null);
                }
            }
        }
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo) {
        String d = cvx.d(bArr);
        LogUtil.d("HwSosManager", "5.51.2 handleSosHelpingMessage info:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d("DEVMGR_HwSosManager", "handleSosHelpingMessage data is error");
            sqo.aj("HwSosManager sendSosMessage: data is error");
            return;
        }
        try {
            c(cvx.e(this.g.a(d.substring(4)).e().get(0).c()), deviceInfo);
        } catch (cwg unused) {
            ReleaseLogUtil.c("DEVMGR_HwSosManager", "handleSosHelpingMessage TlvException");
            sqo.aj("HwSosManager sendSosMessage: TlvException");
        }
    }

    private void b(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.d("HwSosManager", "5.51.4 handleImageData info:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d("DEVMGR_HwSosManager", "handleImageData data is error");
            sqo.aj("HwSosManager handleImageData: data is error");
            return;
        }
        try {
            List<cwd> e = this.g.a(d.substring(4)).e();
            if (e != null && e.size() > 0) {
                for (cwd cwdVar : e) {
                    if (cwdVar != null) {
                        int a2 = CommonUtil.a(cwdVar.e(), 16);
                        if (a2 == 1) {
                            a(cwdVar.c());
                            ReleaseLogUtil.e("DEVMGR_HwSosManager", "IMAGE_SIZE:" + cwdVar.c());
                        } else if (a2 == 2) {
                            e(cwdVar.c());
                            ReleaseLogUtil.e("DEVMGR_HwSosManager", "IMAGE_TYPE:" + cwdVar.c());
                        } else if (a2 == 3) {
                            c(cwdVar.c());
                            ReleaseLogUtil.e("DEVMGR_HwSosManager", "IMAGE_COLOR_TYPE:" + cwdVar.c());
                        } else {
                            a("");
                            e("");
                            c("");
                            ReleaseLogUtil.d("DEVMGR_HwSosManager", "handleImageData the default branch");
                        }
                    }
                }
            }
        } catch (cwg unused) {
            ReleaseLogUtil.c("DEVMGR_HwSosManager", "handleImageData TlvException");
            sqo.aj("HwSosManager handleImageData: TlvException");
        }
        HashMap<String, String> f = f();
        Intent intent = new Intent();
        intent.setAction("image_info_sync");
        intent.putExtra("image_info", f);
        BroadcastManagerUtil.bFG_(this.j, intent);
        d(((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getEmergencyInfo());
    }

    public HashMap<String, String> f() {
        ReleaseLogUtil.e("DEVMGR_HwSosManager", "syncImageInfo enter");
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("image_size_tag", e());
        hashMap.put("image_type_tag", c());
        hashMap.put("image_color_type_tag", b());
        return hashMap;
    }

    public void d(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("DEVMGR_HwSosManager", "reportMessageSendingState enter");
        String str = cvx.e(127) + cvx.e(4) + cvx.b(100000L);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(51);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.d("HwSosManager", "reportMessageSendingState, deviceCommand:", deviceCommand.toString());
        jsz.b(this.j).sendDeviceData(deviceCommand);
    }

    private boolean j() {
        gmu emergencyInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getEmergencyInfo();
        this.f = emergencyInfo;
        if (emergencyInfo == null || emergencyInfo.d() == null || this.f.d().size() == 0) {
            ReleaseLogUtil.d("DEVMGR_HwSosManager", "doesn't have any emergency contact");
            sqo.aj("HwSosManager canSendMessage: doesn't have any emergency contact");
            return false;
        }
        if (jdi.c(this.j, new String[]{"android.permission.SEND_SMS"})) {
            return true;
        }
        ReleaseLogUtil.d("DEVMGR_HwSosManager", "doesn't have send sms permission");
        sqo.aj("HwSosManager canSendMessage: doesn't have send sms permission");
        return false;
    }

    public void d() {
        ReleaseLogUtil.e("DEVMGR_HwSosManager", "HwSosManger onDestroy");
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        ReleaseLogUtil.e("DEVMGR_HwSosManager", "HwSosManger getResult");
        b(bArr, deviceInfo);
    }
}
