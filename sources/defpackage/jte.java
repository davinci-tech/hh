package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.DbManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes5.dex */
public class jte {
    private static int c(int i) {
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 20 && i != 21 && i != 34 && i != 35 && i != 60 && i != 61 && i != 65) {
                        switch (i) {
                            case 8:
                            case 10:
                                break;
                            case 9:
                                break;
                            case 11:
                                break;
                            default:
                                return -1;
                        }
                    }
                }
                return 3;
            }
            return 2;
        }
        return 4;
    }

    public static Date c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).parse(str);
            }
        } catch (ParseException unused) {
            LogUtil.b("HwDmsUtil", "exception parseException");
        }
        return null;
    }

    private static int b(int i) {
        if (!cvt.c(i) && i != -2 && i != 5 && i != 7 && i != 58 && i != 64 && i != 0 && i != 1 && i != 18 && i != 19 && i != 44 && i != 45) {
            switch (i) {
            }
            return 1;
        }
        return 1;
    }

    public static int e(int i) {
        LogUtil.a("HwDmsUtil", "getDeviceClassification() deviceType", Integer.valueOf(i));
        int b = b(i);
        if (b == -1) {
            b = c(i);
        }
        if (b == -1) {
            b = cwc.e(i);
        }
        LogUtil.a("HwDmsUtil", "getDeviceClassification() deviceClassification", Integer.valueOf(b));
        return b;
    }

    public static boolean a(int i) {
        if (i == 2) {
            return true;
        }
        return PermissionUtil.c() && i == 1;
    }

    public static boolean e() {
        boolean z;
        boolean z2;
        LocationManager locationManager = BaseApplication.getContext().getSystemService("location") instanceof LocationManager ? (LocationManager) BaseApplication.getContext().getSystemService("location") : null;
        if (locationManager != null) {
            z2 = locationManager.isProviderEnabled(GeocodeSearch.GPS);
            LogUtil.a("HwDmsUtil", "isGPSLocationEnable：", Boolean.valueOf(z2));
            if (iyg.e() || iyg.b()) {
                return z2;
            }
            z = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
        } else {
            z = true;
            z2 = true;
        }
        LogUtil.a("HwDmsUtil", "isNetWorkLocationEnable：", Boolean.valueOf(z));
        return z2 || z;
    }

    public static void e(int i, int i2, DeviceParameter deviceParameter) {
        LogUtil.a("HwDmsUtil", "showDialogTip, style: ", Integer.valueOf(i), " ,content: ", Integer.valueOf(i2));
        Bundle bundle = new Bundle();
        bundle.putInt(TemplateStyleRecord.STYLE, i);
        bundle.putInt("content", i2);
        bundle.putParcelable("device_parameter", deviceParameter);
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pairing.HwBtDialogActivity"));
            intent.setFlags(268435456);
            intent.putExtras(bundle);
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("HwDmsUtil", "showDialogTip ActivityNotFoundException.");
        }
    }

    public static DeviceCommand b(izf izfVar) {
        DeviceCommand deviceCommand = new DeviceCommand();
        if (izfVar == null) {
            LogUtil.h("HwDmsUtil", "convertToDeviceCommand btDeviceCommand is null.");
            return deviceCommand;
        }
        deviceCommand.setServiceId(izfVar.g());
        deviceCommand.setCommandId(izfVar.a());
        deviceCommand.setDataLength(izfVar.e());
        byte[] d = izfVar.d();
        if (d.length < 2) {
            return null;
        }
        byte[] bArr = new byte[d.length - 2];
        System.arraycopy(d, 2, bArr, 0, d.length - 2);
        deviceCommand.setDataContent(bArr);
        deviceCommand.setNeedAck(izfVar.f());
        deviceCommand.setNeedEncrypt(izfVar.i());
        deviceCommand.setmIdentify(izfVar.h());
        deviceCommand.setPriority(izfVar.j());
        return deviceCommand;
    }

    public static void e(DeviceCommand deviceCommand) {
        if (deviceCommand == null) {
            LogUtil.h("HwDmsUtil", "checkSyncTimeCommand deviceCommand is null.");
            return;
        }
        int serviceID = deviceCommand.getServiceID();
        int commandID = deviceCommand.getCommandID();
        if (serviceID == 1 && commandID == 5) {
            LogUtil.a("HwDmsUtil", "checkSyncTimeCommand sync time.");
            byte[] c = iyo.c();
            if (c.length < 2) {
                LogUtil.h("HwDmsUtil", "checkSyncTimeCommand srcData length is error.");
                return;
            }
            byte[] bArr = new byte[c.length - 2];
            System.arraycopy(c, 2, bArr, 0, c.length - 2);
            deviceCommand.setDataContent(bArr);
        }
    }

    public static DeviceCapability a(String str) {
        UniteDevice a2 = jtd.b().a(str);
        if (a2 == null || a2.getCapability() == null) {
            return new DeviceCapability();
        }
        return a2.getCapability().getCompatibleCapacity();
    }

    public static boolean e(DeviceInfo deviceInfo) {
        kcw a2 = kcw.a();
        for (DeviceInfo deviceInfo2 : jtd.b().d()) {
            if (a2.a(deviceInfo2)) {
                LogUtil.a("HwDmsUtil", "disconnectNewChannelDevice isSupportMultiConnect:", blt.b(deviceInfo2.getDeviceIdentify()));
            } else if (deviceInfo != null && deviceInfo2.getDeviceActiveState() == 1 && !deviceInfo2.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && !a2.c(deviceInfo2.getProductType(), deviceInfo2.getDeviceIdentify())) {
                jtd.b().c(deviceInfo2.getDeviceIdentify());
                return true;
            }
        }
        return false;
    }

    public static void a(boolean z, boolean z2) {
        String str;
        LogUtil.a("HwDmsUtil", "enter updateHasDeviceDbInfo with hasDevice = " + z);
        DbManager.c(BaseApplication.getContext(), String.valueOf(1000), "has_device_table", 2, "hasDeviceKey varchar primary key ,hasDeviceValue hasDeviceValue varchar");
        final ContentValues contentValues = new ContentValues();
        if (z2) {
            contentValues.put("hasDeviceKey", "hasUdsDeviceInfo");
            str = "hasDeviceKey='hasUdsDeviceInfo'";
        } else {
            contentValues.put("hasDeviceKey", "hasDeviceInfo");
            str = "hasDeviceKey='hasDeviceInfo'";
        }
        if (z) {
            contentValues.put("hasDeviceValue", String.valueOf(1));
        } else {
            contentValues.put("hasDeviceValue", String.valueOf(0));
        }
        if (e(str)) {
            DbManager.b bVar = new DbManager.b();
            bVar.b(BaseApplication.getContext());
            bVar.e(String.valueOf(1000));
            bVar.c("has_device_table");
            bVar.a(2);
            DbManager.bGH_(bVar, contentValues, str);
            return;
        }
        ThreadPoolManager.d().d("HwDmsUtil", new Runnable() { // from class: jte.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HwDmsUtil", "enter updateHasDeviceDbInfo insert.");
                DbManager.bGC_(BaseApplication.getContext(), String.valueOf(1000), "has_device_table", 2, contentValues);
            }
        });
    }

    private static boolean e(String str) {
        LogUtil.a("HwDmsUtil", "enter isHaveKeyInfo");
        Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), String.valueOf(1000), "has_device_table", 2, str);
        if (bGE_ != null) {
            r0 = bGE_.getCount() > 0;
            bGE_.close();
        }
        LogUtil.c("HwDmsUtil", "isHaveKeyInfo:", Boolean.valueOf(r0));
        return r0;
    }

    public static UniteDevice d(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            return jtd.b().a(deviceInfo.getDeviceIdentify());
        }
        return null;
    }
}
