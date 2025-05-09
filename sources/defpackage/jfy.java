package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.Contact;
import com.huawei.datatype.DeviceCommand;
import com.huawei.datatype.PhoneNumber;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jfy extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static jfy e;
    private int g;
    private IBaseResponseCallback h;
    private e i;
    private jfq l;
    private jqi n;

    /* renamed from: a, reason: collision with root package name */
    private static final String f13800a = String.valueOf(true);
    private static final Object d = new Object();
    private static final Object c = new Object();
    private static String j = "";
    private static List<IBaseResponseCallback> b = new ArrayList(1);
    private static int f = 10;

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public boolean onDataMigrate() {
        return true;
    }

    private jfy(Context context) {
        super(context);
        this.g = 0;
        this.h = new IBaseResponseCallback() { // from class: jfy.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                Object[] objArr = new Object[2];
                objArr[0] = "onResultIntent object:";
                if (obj == null) {
                    obj = "appendToExistData onResult object is null";
                }
                objArr[1] = obj;
                LogUtil.a("HwAddressBookManager", objArr);
            }
        };
        jqi a2 = jqi.a();
        this.n = a2;
        if (a2 == null) {
            LogUtil.h("HwAddressBookManager", "mHwSwitchSettingManager is null");
        }
        jfq c2 = jfq.c();
        this.l = c2;
        if (c2 != null) {
            c2.e(3, this);
        } else {
            LogUtil.h("HwAddressBookManager", "mHwDeviceConfigManager is null");
        }
        this.i = new e(BaseApplication.getContext().getMainLooper());
    }

    public static jfy b() {
        jfy jfyVar;
        synchronized (d) {
            if (e == null) {
                e = new jfy(BaseApplication.getContext());
            }
            jfyVar = e;
        }
        return jfyVar;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        Object[] objArr = new Object[2];
        objArr[0] = "onResultIntent object:";
        objArr[1] = bArr != null ? bArr : "appendToExistData onResult object is null";
        LogUtil.a("HwAddressBookManager", objArr);
        if (bArr == null || bArr.length <= 1) {
            LogUtil.h("HwAddressBookManager", "onDataReceived dataInfos is null or dataInfos length less than 1");
        } else {
            b(bArr);
        }
    }

    static class e extends Handler {
        WeakReference<jfy> d;

        private e(jfy jfyVar, Looper looper) {
            super(looper);
            this.d = new WeakReference<>(jfyVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            jfy jfyVar = this.d.get();
            if (jfyVar == null) {
                LogUtil.h("HwAddressBookManager", "handleMessage, hwAddressBookManager is null");
                return;
            }
            LogUtil.a("HwAddressBookManager", "handleMessage message:", Integer.valueOf(message.what));
            if (message.what == 1) {
                jfyVar.d();
            } else {
                LogUtil.h("HwAddressBookManager", "unknown message type");
            }
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 3;
    }

    public static String a() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        j = accountInfo;
        if (accountInfo == null) {
            j = "";
        }
        return j;
    }

    private List<ByteBuffer> a(List<Contact> list) {
        ByteBuffer e2;
        ArrayList arrayList = new ArrayList(5);
        for (int i = 0; i < f; i++) {
            if (i >= list.size()) {
                e2 = ByteBuffer.allocate(5);
                e2.put((byte) -126);
                e2.put((byte) 3);
                e2.put((byte) 3);
                e2.put((byte) 1);
                e2.put((byte) (i + 1));
            } else {
                e2 = e(i, list);
            }
            this.g += e2.array().length;
            arrayList.add(e2);
        }
        return arrayList;
    }

    private ByteBuffer e(int i, List<Contact> list) {
        String c2 = cvx.c(list.get(i).getName());
        if (c2.length() > 60) {
            c2 = c2.substring(0, 60);
        }
        int length = c2.length() / 2;
        List<PhoneNumber> phoneNumbers = list.get(i).getPhoneNumbers();
        int b2 = b(phoneNumbers);
        int i2 = length + 7 + b2 + 2;
        String c3 = cvx.c(list.get(i).getNote());
        if (c3.length() > 60) {
            c3 = c3.substring(0, 60);
        }
        if (c3.length() != 0) {
            i2 += (c3.length() / 2) + 2;
        }
        if (!"-1".equals(list.get(i).getIconIndex())) {
            i2 += 3;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i2);
        allocate.put((byte) -126);
        allocate.put(cvx.a(cvx.d(i2 - 2)));
        allocate.put((byte) 3);
        allocate.put(cvx.a(cvx.e(1)));
        int i3 = i + 1;
        allocate.put(cvx.a(cvx.e(i3)));
        list.get(i).setIndex(i3);
        allocate.put((byte) 4);
        allocate.put(cvx.a(cvx.e(c2.length() / 2)));
        allocate.put(cvx.a(c2));
        allocate.put((byte) -123);
        allocate.put(cvx.a(cvx.d(b2)));
        a(phoneNumbers, allocate);
        if (c3.length() != 0) {
            allocate.put((byte) 9);
            allocate.put(cvx.a(cvx.e(c3.length() / 2)));
            allocate.put(cvx.a(c3));
        }
        if (!"-1".equals(list.get(i).getIconIndex())) {
            allocate.put((byte) -127);
            allocate.put((byte) 1);
            allocate.put((byte) 0);
        }
        return allocate;
    }

    private void a(List<PhoneNumber> list, ByteBuffer byteBuffer) {
        for (PhoneNumber phoneNumber : list) {
            String c2 = cvx.c(phoneNumber.getPhoneNumber() != null ? phoneNumber.getPhoneNumber().replaceAll(Constants.LINK, "") : "");
            if (c2.length() > 40) {
                c2 = c2.substring(0, 40);
            }
            String c3 = cvx.c(phoneNumber.getPhoneTag());
            byteBuffer.put((byte) -122);
            byteBuffer.put(cvx.a(cvx.d((c3.length() / 2) + 2 + (c2.length() / 2) + 2)));
            byteBuffer.put((byte) 7);
            byteBuffer.put(cvx.a(cvx.e(c3.length() / 2)));
            byteBuffer.put(cvx.a(c3));
            byteBuffer.put((byte) 8);
            byteBuffer.put(cvx.a(cvx.e(c2.length() / 2)));
            byteBuffer.put(cvx.a(c2));
        }
    }

    private int b(List<PhoneNumber> list) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            String c2 = cvx.c(list.get(i4).getPhoneTag());
            String c3 = cvx.c(list.get(i4).getPhoneNumber() != null ? list.get(i4).getPhoneNumber().replaceAll(Constants.LINK, "") : "");
            if (c3.length() > 40) {
                c3 = c3.substring(0, 40);
            }
            i2 += (c2.length() / 2) + 2;
            i3 += (c3.length() / 2) + 2;
            i += 2;
        }
        return i + i2 + i3;
    }

    public void b(List<Contact> list, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        String str;
        if (list == null) {
            ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "setContact contacts is null");
            sqo.b("setContact: contacts is null");
            return;
        }
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "setContact responseCallback is null");
            sqo.b("setContact: responseCallback is null");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(3);
        deviceCommand.setCommandID(1);
        this.g = 0;
        ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "setContact() sMaxContactNum :", Integer.valueOf(f));
        List<ByteBuffer> a2 = a(list);
        byte[] a3 = cvx.a(cvx.d(this.g));
        int length = this.g + a3.length + 1;
        this.g = length;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.put((byte) -127);
        allocate.put(a3);
        Iterator<ByteBuffer> it = a2.iterator();
        while (it.hasNext()) {
            allocate.put(it.next().array());
        }
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        this.l.b(deviceCommand);
        if (list.size() != 0) {
            if (f < list.size()) {
                int i = f;
                if (i - 1 > 0) {
                    list = list.subList(0, i);
                }
            }
            LogUtil.a("HwAddressBookManager", "setContact() gsonContacts :", list);
            str = new Gson().toJson(list);
        } else {
            str = "";
        }
        int a4 = a(str);
        ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "setContact() response :", Integer.valueOf(a4));
        b(a4, z, iBaseResponseCallback);
    }

    private void b(int i, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        if (z) {
            synchronized (g()) {
                b.add(iBaseResponseCallback);
            }
        } else if (i != -1) {
            iBaseResponseCallback.d(0, 100000);
        } else {
            iBaseResponseCallback.d(-1, 103001);
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "contactStorageUpdate callback is null");
            sqo.b("contactStorageUpdate: callback is null");
            return;
        }
        String f2 = f();
        if ("encrypto".equals(f2)) {
            LogUtil.a("HwAddressBookManager", "contactStorageUpdate no need update");
            iBaseResponseCallback.d(0, null);
        } else {
            if (f13800a.equals(f2)) {
                LogUtil.a("HwAddressBookManager", "contactStorageUpdate not crypto, need update");
                a(getSharedPreference(a()));
                j();
                this.n.setSwitchSettingToLocal("NOT_HAVE_ADDRESS_BOOK_ON_HIHEALTH_FLAG", "encrypto", 10000);
                iBaseResponseCallback.d(0, null);
                return;
            }
            d(iBaseResponseCallback);
        }
    }

    private void d(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwAddressBookManager", "contactStorageUpdate save in hihealth, need update");
        this.n.getSwitchSetting("custom.address_book", new IBaseResponseCallback() { // from class: jfy.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "COMMAND_ID_CONTACT_NUM_GET getAddressBook err_code :", Integer.valueOf(i));
                List arrayList = new ArrayList(16);
                if (i == 0 && (obj instanceof String)) {
                    try {
                        arrayList = (List) new Gson().fromJson((String) obj, new TypeToken<List<Contact>>() { // from class: jfy.3.3
                        }.getType());
                    } catch (JsonSyntaxException unused) {
                        LogUtil.b("HwAddressBookManager", "getAddressBook JsonSyntaxException");
                        sqo.b("getSwitchSetting: JsonSyntaxException");
                        jfy.this.d();
                        iBaseResponseCallback.d(0, null);
                        return;
                    }
                }
                if (arrayList != null && arrayList.size() != 0) {
                    jfy.this.e((List<Contact>) arrayList);
                } else {
                    ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "getAddressBook contactList is null");
                    jfy.this.n.setSwitchSettingToLocal("NOT_HAVE_ADDRESS_BOOK_ON_HIHEALTH_FLAG", "encrypto", 10000);
                }
                iBaseResponseCallback.d(0, null);
            }
        });
    }

    private String f() {
        String switchSettingFromLocal = this.n.getSwitchSettingFromLocal("NOT_HAVE_ADDRESS_BOOK_ON_HIHEALTH_FLAG", 10000);
        ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "notHavAddressBookOnHiHealth :", switchSettingFromLocal);
        return switchSettingFromLocal;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<Contact> list) {
        LogUtil.a("HwAddressBookManager", "saveContactAndSendMessage contactList :", list);
        a(new Gson().toJson(list));
        this.i.sendEmptyMessage(1);
    }

    public List<Contact> c() {
        synchronized (c) {
            if (StringUtils.i(a())) {
                List<Contact> l = l();
                if (!l.isEmpty()) {
                    ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "getContact normal cloud size is:", Integer.valueOf(l.size()));
                    return l;
                }
                String sharedPreference = getSharedPreference("ADDRESS_BOOK_ENCRYPTO0");
                if (StringUtils.i(sharedPreference)) {
                    ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "getContact cloud no result, transfer info.noCloudResult length:", Integer.valueOf(sharedPreference.length()));
                    a(sharedPreference);
                    deleteSharedPreference("ADDRESS_BOOK_ENCRYPTO0");
                } else {
                    ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "getContact noCloudResult is null");
                }
            } else {
                ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "getContact userId is null.");
            }
            return l();
        }
    }

    private List<Contact> l() {
        String sharedPreference = getSharedPreference("ADDRESS_BOOK_ENCRYPTO" + a());
        if (StringUtils.i(sharedPreference)) {
            try {
                return (List) new Gson().fromJson(sharedPreference, new TypeToken<List<Contact>>() { // from class: jfy.1
                }.getType());
            } catch (JsonSyntaxException unused) {
                ReleaseLogUtil.c("DEVMGR_HwAddressBookManager", "handleContact JsonSyntaxException");
                sqo.b("handleContact: JsonSyntaxException");
                return new ArrayList(5);
            }
        }
        ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "handleContact encryptId is empty");
        return new ArrayList(5);
    }

    public int e() {
        return f;
    }

    private int j() {
        int deleteSharedPreference;
        synchronized (this) {
            LogUtil.a("HwAddressBookManager", "deleteNoEncryptContact()");
            deleteSharedPreference = deleteSharedPreference(a());
        }
        return deleteSharedPreference;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.n.setSwitchSetting("custom.address_book", new Gson().toJson((JsonElement) null), new IBaseResponseCallback() { // from class: jfy.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                jfy.this.n.setSwitchSettingToLocal("NOT_HAVE_ADDRESS_BOOK_ON_HIHEALTH_FLAG", "encrypto", 10000);
            }
        });
    }

    private int a(String str) {
        int sharedPreference;
        synchronized (this) {
            LogUtil.a("HwAddressBookManager", "saveContact() enter");
            sharedPreference = setSharedPreference("ADDRESS_BOOK_ENCRYPTO" + a(), str, new StorageParams(2));
        }
        return sharedPreference;
    }

    private void i() {
        if (this.l == null) {
            ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "getContactNumber mHwDeviceConfigManager is null");
            sqo.b("getContactNumber: mHwDeviceConfigManager is null");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(3);
        deviceCommand.setCommandID(2);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        allocate.put((byte) 2);
        allocate.put((byte) 0);
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "getContactNumber send command");
        this.l.b(deviceCommand);
    }

    private void b(byte[] bArr) {
        LogUtil.a("HwAddressBookManager", "getResult() :", cvx.d(bArr));
        byte b2 = bArr[1];
        if (b2 != 1) {
            if (b2 == 2) {
                if (bArr.length <= 2) {
                    ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "getContact num data is error");
                    return;
                } else {
                    c(bArr);
                    return;
                }
            }
            LogUtil.h("HwAddressBookManager", "getResult not switch");
            return;
        }
        int a2 = a(bArr);
        int i = a2 == 100000 ? 0 : -1;
        ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "contact set errorCode:", Integer.valueOf(i));
        synchronized (g()) {
            if (b.size() != 0) {
                b.get(0).d(i, Integer.valueOf(a2));
                b.remove(0);
            }
        }
    }

    private void c(byte[] bArr) {
        if (bArr[2] != Byte.MAX_VALUE) {
            String d2 = cvx.d(bArr);
            try {
                if (d2.length() >= 10) {
                    f = Integer.parseInt(d2.substring(8, 10), 16);
                }
            } catch (NumberFormatException unused) {
                ReleaseLogUtil.c("DEVMGR_HwAddressBookManager", "sMaxContactNum NumberFormatException");
                sqo.b("getContactMaxNum: sMaxContactNum NumberFormatException");
            }
            ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "getResult(): sMaxContactNum :", Integer.valueOf(f));
            a(new IBaseResponseCallback() { // from class: jfy.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    List<Contact> c2 = jfy.this.c();
                    jfy jfyVar = jfy.this;
                    jfyVar.b(c2, jfyVar.h, true);
                }
            });
            return;
        }
        ReleaseLogUtil.d("DEVMGR_HwAddressBookManager", "getContact num data return errorCode");
        sqo.b("getContactMaxNum: return errorCode");
    }

    private int a(byte[] bArr) {
        String d2 = cvx.d(bArr);
        try {
            if (d2.length() > 8) {
                return Integer.parseInt(d2.substring(8, d2.length()), 16);
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("HwAddressBookManager", "messageHex.substring(COMMAND_TYPE_ERROR_START, messageHex.length()) NumberFormatException");
        }
        return 0;
    }

    private static List<IBaseResponseCallback> g() {
        List<IBaseResponseCallback> list;
        synchronized (jfy.class) {
            list = b;
        }
        return list;
    }

    public void c(DeviceInfo deviceInfo) {
        b(deviceInfo);
    }

    private void b(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "autoSendCommand");
        if (a(deviceInfo)) {
            i();
        }
    }

    private boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "isContacts deviceInfo is null");
            return false;
        }
        Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "HwAddressBookManager");
        if (a2 == null || a2.isEmpty()) {
            ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "isContacts deviceCapabilityhMap is null or empty");
            return false;
        }
        DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability != null && deviceCapability.isContacts()) {
            return true;
        }
        ReleaseLogUtil.e("DEVMGR_HwAddressBookManager", "isContacts device not support contacts");
        return false;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        h();
        synchronized (g()) {
            b.clear();
        }
        LogUtil.a("HwAddressBookManager", "onDestroy() finish");
    }

    private static void h() {
        synchronized (d) {
            e = null;
        }
    }
}
