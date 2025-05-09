package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicControllerCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicFolderStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicHistoryCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicIndexCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicPageStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationData;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.OperationStruct;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.HwMusicMgrCallback;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jjd extends HwBaseManager implements BluetoothDataReceiveCallback {

    /* renamed from: a, reason: collision with root package name */
    private static jjd f13886a;
    private jfq aa;
    private int b;
    private int c;
    private List<MusicFolderStruct> f;
    private int g;
    private List<int[]> h;
    private IBaseResponseCallback i;
    private Map<OperationStruct, IBaseResponseCallback> j;
    private IBaseResponseCallback k;
    private IBaseResponseCallback l;
    private IBaseResponseCallback m;
    private IBaseResponseCallback n;
    private IBaseResponseCallback o;
    private MusicControllerCallbackInterface p;
    private ExtendHandler q;
    private boolean r;
    private int s;
    private int t;
    private List<jjw> u;
    private int v;
    private MusicIndexCallbackInterface w;
    private List<MusicStruct> x;
    private boolean y;
    private static final Object e = new Object();
    private static final Object d = new Object();

    private jjd(Context context) {
        super(context);
        this.s = 0;
        this.v = 65535;
        this.t = 0;
        this.b = 0;
        this.h = new ArrayList(16);
        this.g = 0;
        this.c = 0;
        this.j = new HashMap(16);
        this.f = new ArrayList(16);
        this.x = new ArrayList(16);
        this.u = new ArrayList(16);
        this.y = true;
        this.q = HandlerCenter.yt_(new c(), "HwMusicControlManager");
        e();
    }

    public static jjd b(Context context) {
        jjd jjdVar;
        synchronized (d) {
            if (f13886a == null && context != null) {
                f13886a = new jjd(BaseApplication.getContext());
            }
            jjdVar = f13886a;
        }
        return jjdVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 37;
    }

    public void b(HwMusicMgrCallback hwMusicMgrCallback) {
        this.aa.d(hwMusicMgrCallback);
    }

    public void e() {
        jfq c2 = jfq.c();
        this.aa = c2;
        c2.e(37, this);
        jjj.c(this.aa);
    }

    public void e(MusicControllerCallbackInterface musicControllerCallbackInterface) {
        LogUtil.a("HwMusicControlManager", "musicFullSynchronizationFromWear");
        this.p = musicControllerCallbackInterface;
        l();
        g();
    }

    public void b(MusicHistoryCallbackInterface musicHistoryCallbackInterface, int i, String str) {
        LogUtil.a("HwMusicControlManager", "getMusicHistory");
        jjj.a(musicHistoryCallbackInterface, i, str, e);
    }

    public void b(NegotiationData negotiationData, NegotiationCallbackInterface negotiationCallbackInterface) {
        jjj.a(negotiationData, negotiationCallbackInterface);
    }

    public void c(jjz jjzVar) {
        if (jjzVar == null) {
            return;
        }
        synchronized (e) {
            jjj.b(jjzVar);
        }
    }

    public void b(List<Integer> list) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(37);
        deviceCommand.setCommandID(8);
        String d2 = cvx.d(1);
        String e2 = cvx.e(1);
        String e3 = cvx.e(list.get(0).intValue());
        String d3 = cvx.d(2);
        String e4 = cvx.e(2);
        String a2 = cvx.a(list.get(1).intValue());
        StringBuilder sb = new StringBuilder(16);
        sb.append(e2);
        sb.append(d2);
        sb.append(e3);
        sb.append(e4);
        sb.append(d3);
        sb.append(a2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        jfq jfqVar = this.aa;
        if (jfqVar != null) {
            jfqVar.b(deviceCommand);
        } else {
            LogUtil.h("HwMusicControlManager", "sendMusicDeleteAudioInfo deviceConfigManager is null");
        }
    }

    public void e(MusicIndexCallbackInterface musicIndexCallbackInterface) {
        LogUtil.a("HwMusicControlManager", "registerMusicIndexCallback");
        this.w = musicIndexCallbackInterface;
    }

    public void a(OperationStruct operationStruct, IBaseResponseCallback iBaseResponseCallback) {
        if (operationStruct == null || iBaseResponseCallback == null) {
            LogUtil.h("HwMusicControlManager", "folderMusicOperate parameters callback is null");
            return;
        }
        LogUtil.a("HwMusicControlManager", "folderMusicOpetate parameters:", operationStruct.toString());
        if (!d(operationStruct)) {
            iBaseResponseCallback.d(100007, "");
        }
        b(operationStruct, iBaseResponseCallback);
    }

    private void l() {
        this.x.clear();
        this.f.clear();
        this.h.clear();
        this.u.clear();
        this.j.clear();
        this.s = 0;
        this.c = 0;
        this.g = 0;
        this.t = 0;
        this.b = 0;
        this.r = false;
    }

    private void g() {
        c(new IBaseResponseCallback() { // from class: jjh
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jjd.this.e(i, obj);
            }
        });
    }

    /* synthetic */ void e(int i, Object obj) {
        if (obj == null) {
            LogUtil.h("HwMusicControlManager", "getMusicFrameCount objectData is null.");
            return;
        }
        this.t = 0;
        try {
            this.t = Integer.parseInt(obj.toString());
        } catch (NumberFormatException e2) {
            LogUtil.b("HwMusicControlManager", "getMusicFrameCount, exception:", ExceptionUtils.d(e2));
        }
        LogUtil.a("HwMusicControlManager", "getMusicFrameCount, musicFrameCount:", Integer.valueOf(this.t));
        this.y = true;
        this.v = 65535;
        j();
    }

    private void j() {
        LogUtil.a("HwMusicControlManager", "Enter getMusicInfo(): musicFrameCount:", Integer.valueOf(this.t), " musicFrameCurrentIndex: ", Integer.valueOf(this.s));
        boolean z = this.y;
        if (!z) {
            LogUtil.h("HwMusicControlManager", "stop getMusicInfo. isGetMusicInfo = ", Boolean.valueOf(z));
            this.s = 0;
            return;
        }
        int i = this.t;
        if (jjm.e().i()) {
            i = Math.min(this.t, 250);
        }
        int i2 = this.s;
        if (i2 < i) {
            c(this.v, i2, new IBaseResponseCallback() { // from class: jji
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i3, Object obj) {
                    jjd.this.c(i3, obj);
                }
            });
        } else {
            d();
        }
    }

    /* synthetic */ void c(int i, Object obj) {
        if (!(obj instanceof jkd)) {
            LogUtil.h("HwMusicControlManager", "Enter getMusicInfo() objectData is null.");
            return;
        }
        jkd jkdVar = (jkd) obj;
        this.x.addAll(jkdVar.d());
        if (jjm.e().i() && (jkdVar.e() == this.v || jkdVar.d().size() == 1)) {
            LogUtil.a("HwMusicControlManager", "all music is loaded. musicFrameStruct.getMusicIndex() = ", Integer.valueOf(jkdVar.e()), "; mMusicIndex: ", Integer.valueOf(this.v));
            d();
        } else {
            e(jkdVar);
            this.s++;
            j();
        }
    }

    private void e(jkd jkdVar) {
        List<MusicStruct> d2 = jkdVar.d();
        if (d2.size() == 2) {
            this.v = d2.get(1).getMusicIndex();
        }
    }

    private void d() {
        LogUtil.a("HwMusicControlManager", "mMusicStructList.size:", Integer.valueOf(this.x.size()));
        StringBuilder sb = new StringBuilder(16);
        Iterator<MusicStruct> it = this.x.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        LogUtil.a("HwMusicControlManager", "music:", sb.toString());
        this.s = 0;
        f();
    }

    public void a() {
        this.y = false;
        LogUtil.a("HwMusicControlManager", "set isGetMusicInfo = ", false);
    }

    public void b(final int i, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwMusicControlManager", "getMusicInfoByPageFrame callback is null");
        } else {
            this.r = true;
            c(this.v, i, new IBaseResponseCallback() { // from class: jjf
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    jjd.this.b(iBaseResponseCallback, i, i2, obj);
                }
            });
        }
    }

    /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, int i, int i2, Object obj) {
        LogUtil.a("HwMusicControlManager", "getMusicInfoByPageFrame onResponse errorCode : ", Integer.valueOf(i2));
        this.r = false;
        if (i2 != 100000) {
            LogUtil.h("HwMusicControlManager", "getMusicInfoByPageFrame onResponse errorCode isn't success.");
            iBaseResponseCallback.d(2, null);
            return;
        }
        if (!(obj instanceof jkd)) {
            LogUtil.h("HwMusicControlManager", "getMusicInfoByPageFrame onResponse object isn't  MusicFrameStruct.");
            iBaseResponseCallback.d(2, null);
            return;
        }
        jkd jkdVar = (jkd) obj;
        e(jkdVar);
        if (jkdVar.c() != i) {
            LogUtil.h("HwMusicControlManager", "getMusicInfoByPageFrame onResponse musicFrameIndex no eq.");
            iBaseResponseCallback.d(2, null);
        } else {
            LogUtil.a("HwMusicControlManager", "getMusicInfoByPageFrame onResponse errorCode LOAD_STATUS_FINISHED.");
            iBaseResponseCallback.d(1, jkdVar.d());
        }
    }

    private void f() {
        b(new IBaseResponseCallback() { // from class: jjd.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HwMusicControlManager", "getFolderInfo onResponse()");
                jjd.this.i();
                LogUtil.a("HwMusicControlManager", "mFolderMusicAssociationFrameList", Integer.valueOf(jjd.this.h.size()));
                jjd jjdVar = jjd.this;
                jjdVar.b = jjdVar.h.size();
                jjd.this.h();
            }
        });
    }

    public void d(String str, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (e) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("HwMusicControlManager", "getMusicFolderList packageName is null");
                return;
            }
            l();
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(37);
            deviceCommand.setCommandID(6);
            String c2 = cvx.c(str);
            String str2 = cvx.e(6) + cvx.e(c2.length() / 2) + c2;
            StringBuilder sb = new StringBuilder(16);
            sb.append(str2);
            String sb2 = sb.toString();
            LogUtil.a("HwMusicControlManager", "getMusicFolderList :", sb2);
            byte[] a2 = cvx.a(sb2);
            deviceCommand.setDataLen(a2.length);
            deviceCommand.setDataContent(a2);
            this.aa.b(deviceCommand);
            this.n = iBaseResponseCallback;
            c(2001, 30);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("HwMusicControlManager", "Enter getFolderMusic: folderMusicAssociationFrameListLength: ", Integer.valueOf(this.b));
        int i = this.g;
        if (i < this.b) {
            int[] iArr = this.h.get(i);
            if (iArr.length == 2) {
                b(iArr[0], iArr[1]);
                return;
            } else {
                LogUtil.h("HwMusicControlManager", "currentAssociation.length :", Integer.valueOf(iArr.length));
                return;
            }
        }
        MusicFolderStruct musicFolderStruct = new MusicFolderStruct();
        musicFolderStruct.setFolderIndex(0);
        musicFolderStruct.setMusicStructList(this.x);
        this.f.add(musicFolderStruct);
        StringBuilder sb = new StringBuilder(16);
        Iterator<MusicFolderStruct> it = this.f.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        LogUtil.a("HwMusicControlManager", "folderList: ", sb.toString());
        this.p.onGetFolders(this.f, 100000);
        this.g = 0;
        this.u.clear();
        this.j.clear();
    }

    private void b(final int i, final int i2) {
        LogUtil.a("HwMusicControlManager", "Enter getOneFolderMusic folderIndex:", Integer.valueOf(i), " frameCount:", Integer.valueOf(i2), " mFolderMusicAssociationFrameCurrentCount:", Integer.valueOf(this.c));
        int i3 = this.c;
        if (i3 < i2) {
            d(i, i3, new IBaseResponseCallback() { // from class: jjg
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i4, Object obj) {
                    jjd.this.a(i, i2, i4, obj);
                }
            });
        } else {
            a(i, i2);
        }
    }

    /* synthetic */ void a(int i, int i2, int i3, Object obj) {
        this.c++;
        b(i, i2);
    }

    private void a(int i, int i2) {
        Iterator<MusicFolderStruct> it = this.f.iterator();
        MusicFolderStruct musicFolderStruct = null;
        while (it.hasNext()) {
            musicFolderStruct = it.next();
            if (musicFolderStruct.getFolderIndex() == i) {
                break;
            }
        }
        ArrayList arrayList = new ArrayList(16);
        LogUtil.a("HwMusicControlManager", "mOneFolderMusicFrameAssociationList.size():", Integer.valueOf(this.u.size()));
        if (this.u.size() == i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                arrayList.addAll(this.u.get(i3).a());
            }
        }
        LogUtil.a("HwMusicControlManager", "musicIndexList:", arrayList.toString());
        ArrayList arrayList2 = new ArrayList(16);
        HashMap hashMap = new HashMap(16);
        for (MusicStruct musicStruct : this.x) {
            hashMap.put(Integer.valueOf(musicStruct.getMusicIndex()), musicStruct);
        }
        Iterator<Integer> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            MusicStruct musicStruct2 = (MusicStruct) hashMap.get(it2.next());
            if (musicStruct2 != null) {
                arrayList2.add(musicStruct2);
            }
        }
        LogUtil.a("HwMusicControlManager", "GetOneFolderInstance oneFoldermusicStructList");
        if (musicFolderStruct != null) {
            musicFolderStruct.setMusicStructList(arrayList2);
            musicFolderStruct.setMusicIndexList(arrayList);
        }
        this.g++;
        this.c = 0;
        this.u.clear();
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.h.clear();
        if (this.f.size() > 0) {
            for (MusicFolderStruct musicFolderStruct : this.f) {
                int folderIndex = musicFolderStruct.getFolderIndex();
                int folderMusicAssociationFrameCount = musicFolderStruct.getFolderMusicAssociationFrameCount();
                LogUtil.a("HwMusicControlManager", "folderIndex : ", Integer.valueOf(folderIndex), " folderMusicAssociationFrameCount : ", Integer.valueOf(folderMusicAssociationFrameCount));
                this.h.add(new int[]{folderIndex, folderMusicAssociationFrameCount});
            }
        }
    }

    private boolean d(OperationStruct operationStruct) {
        if (operationStruct == null) {
            LogUtil.h("HwMusicControlManager", "operationParametersIsValid parameters is null");
            return false;
        }
        if (operationStruct.getOperationType() < 0 || operationStruct.getOperationType() > 4) {
            LogUtil.h("HwMusicControlManager", "operationParametersIsValid parameters.getOperationType()：", Integer.valueOf(operationStruct.getOperationType()));
            return false;
        }
        if (operationStruct.getOperationType() >= 0) {
            return true;
        }
        if (operationStruct.getFolderName() != null && !operationStruct.getFolderName().equals("")) {
            return true;
        }
        LogUtil.h("HwMusicControlManager", "operationParametersIsValid parameters.getFolderName()：", operationStruct.getFolderName());
        return false;
    }

    public void c(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr == null || bArr.length < 2) {
            return;
        }
        String d2 = cvx.d(bArr);
        if (d2 != null && d2.length() >= 4) {
            try {
                cwe a2 = new cwl().a(d2.substring(4, d2.length()));
                List<cwd> e2 = a2.e();
                List<cwe> a3 = a2.a();
                LogUtil.a("HwMusicControlManager", "getResult(): ", cvx.d(bArr));
                a(e2, a3, bArr[1], deviceInfo);
                return;
            } catch (cwg unused) {
                LogUtil.b("HwMusicControlManager", "TlvException");
                return;
            } catch (IndexOutOfBoundsException unused2) {
                LogUtil.b("HwMusicControlManager", "IndexOutOfBoundsException");
                return;
            }
        }
        LogUtil.h("HwMusicControlManager", "data lenth less 4");
    }

    private void a(List<cwd> list, List<cwe> list2, int i, DeviceInfo deviceInfo) {
        d(i);
        if (list != null && list.size() > 0) {
            try {
                if (Integer.parseInt(list.get(0).e(), 16) == 127 && i != 10) {
                    c(list, i);
                    return;
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("HwMusicControlManager", "handleMusicMessage NumberFormatException");
            }
        }
        if (i != 15) {
            switch (i) {
                case 4:
                    d(list, list2);
                    break;
                case 5:
                    e(list, list2);
                    break;
                case 6:
                    d(list2);
                    break;
                case 7:
                    c(list);
                    break;
                case 8:
                    e(list);
                    break;
                case 9:
                    g(list);
                    break;
                case 10:
                    a(list, deviceInfo);
                    break;
                default:
                    jjj.a(list, list2, i);
                    LogUtil.h("HwMusicControlManager", "Invalid command:", Integer.valueOf(i));
                    break;
            }
            return;
        }
        i(list);
    }

    private void d(List<cwd> list, List<cwe> list2) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        int i = -1;
        int i2 = 0;
        int i3 = 0;
        int i4 = -1;
        for (cwd cwdVar : list) {
            int c2 = jds.c(cwdVar.e(), 16);
            if (c2 == 1) {
                try {
                    i3 = Integer.parseInt(cwdVar.c(), 16);
                } catch (NumberFormatException unused) {
                    LogUtil.b("HwMusicControlManager", "tlv.getValue() NumberFormatException");
                }
            } else if (c2 == 2) {
                try {
                    i2 = Integer.parseInt(cwdVar.c(), 16);
                } catch (NumberFormatException unused2) {
                    LogUtil.b("HwMusicControlManager", "tlv.getValue() NumberFormatException");
                }
            } else if (c2 == 3) {
                jjj.b(cwdVar, arrayList);
            } else if (c2 == 4) {
                i = jds.c(cwdVar.c(), 16);
            } else if (c2 == 5) {
                i4 = jds.c(cwdVar.c(), 16);
            } else {
                LogUtil.h("HwMusicControlManager", "HandleNegotiationMessage invalid type:", Integer.valueOf(jds.c(cwdVar.e(), 16)));
            }
        }
        NegotiationData negotiationData = new NegotiationData();
        negotiationData.setMusicFormatList(arrayList);
        negotiationData.setStorageSpace(i2);
        negotiationData.setMaxMusicNumber(i);
        negotiationData.setMusicCount(i4);
        e(list2, negotiationData);
        LogUtil.a("HwMusicControlManager", "musicFormatList :", arrayList.toString());
        this.p.onGetNegotiationData(negotiationData, 100000);
        this.o.d(100000, Integer.valueOf(i3));
    }

    private void e(List<cwe> list, NegotiationData negotiationData) {
        if (list == null) {
            LogUtil.h("HwMusicControlManager", "unPackageMusicPageStruct, tlvFatherList is null.");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                b(it2.next().e(), arrayList);
            }
        }
        LogUtil.a("HwMusicControlManager", "unPackageMusicPageStruct, musicPageStructList size:", Integer.valueOf(arrayList.size()));
        negotiationData.setMusicPageStructList(arrayList);
    }

    private void b(List<cwd> list, List<MusicPageStruct> list2) {
        MusicPageStruct musicPageStruct = new MusicPageStruct();
        for (cwd cwdVar : list) {
            switch (jds.c(cwdVar.e(), 16)) {
                case 8:
                    musicPageStruct.setMusicStartFrameIndex(jds.c(cwdVar.c(), 16));
                    break;
                case 9:
                    musicPageStruct.setMusicEndFrameIndex(jds.c(cwdVar.c(), 16));
                    break;
                case 10:
                    musicPageStruct.setMusicPageCount(jds.c(cwdVar.c(), 16));
                    break;
                case 11:
                    musicPageStruct.setMusicPageHashCode(jds.c(cwdVar.c(), 16));
                    break;
                default:
                    LogUtil.h("HwMusicControlManager", "other case");
                    break;
            }
        }
        list2.add(musicPageStruct);
    }

    private void e(List<cwd> list, List<cwe> list2) {
        if (list == null || list2 == null) {
            return;
        }
        LogUtil.a("HwMusicControlManager", "MusicConstants.COMMAND_ID_GET_MUSIC_LIST");
        jkd jkdVar = new jkd();
        for (cwd cwdVar : list) {
            try {
                int parseInt = Integer.parseInt(cwdVar.e(), 16);
                if (parseInt == 1) {
                    jkdVar.b(Integer.parseInt(cwdVar.c(), 16));
                } else if (parseInt == 4) {
                    jkdVar.d(Integer.parseInt(cwdVar.c(), 16));
                } else {
                    LogUtil.h("HwMusicControlManager", "HandleMuisc type:", Integer.valueOf(Integer.parseInt(cwdVar.e(), 16)));
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("HwMusicControlManager", "handleMuiscListMessage exception");
            }
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                MusicStruct e2 = e(it2.next());
                if (e2 != null && e2.getMusicIndex() != -1) {
                    arrayList.add(e2);
                    LogUtil.a("HwMusicControlManager", "musicStruct:", e2.toString());
                }
            }
        }
        jkdVar.c(arrayList);
        this.m.d(100000, jkdVar);
    }

    private MusicStruct e(cwe cweVar) {
        if (cweVar == null) {
            return null;
        }
        MusicStruct musicStruct = new MusicStruct();
        musicStruct.setMusicIndex(-1);
        for (cwd cwdVar : cweVar.e()) {
            try {
                int parseInt = Integer.parseInt(cwdVar.e(), 16);
                if (parseInt == 4) {
                    musicStruct.setMusicIndex(Integer.parseInt(cwdVar.c(), 16));
                } else if (parseInt == 5) {
                    musicStruct.setMusicName(cvx.e(cwdVar.c()));
                } else if (parseInt == 6) {
                    musicStruct.setMusicSinger(cvx.e(cwdVar.c()));
                } else if (parseInt != 7) {
                    LogUtil.h("HwMusicControlManager", "MuiscList type:", Integer.valueOf(Integer.parseInt(cwdVar.e(), 16)));
                } else {
                    musicStruct.setFileName(cvx.e(cwdVar.c()));
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("HwMusicControlManager", "unPackageMusicInfo exception");
            }
        }
        return musicStruct;
    }

    private void d(List<cwe> list) {
        boolean z;
        if (list == null) {
            return;
        }
        ExtendHandler extendHandler = this.q;
        if (extendHandler == null || !extendHandler.hasMessages(2001)) {
            z = false;
        } else {
            this.q.removeMessages(2001);
            this.f.clear();
            z = true;
        }
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                cwe next = it2.next();
                MusicFolderStruct a2 = next != null ? a(next.e()) : null;
                if (a2 != null && (a2.getFolderIndex() != 0 || z)) {
                    LogUtil.a("HwMusicControlManager", "folderStruct:getFolderIndex: ", Integer.valueOf(a2.getFolderIndex()), " getFolderName: ", a2.getFolderName(), " getFolderMusicAssociationFrameCount: ", Integer.valueOf(a2.getFolderMusicAssociationFrameCount()));
                    this.f.add(a2);
                }
            }
        }
        IBaseResponseCallback iBaseResponseCallback = this.n;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, this.f);
        }
        IBaseResponseCallback iBaseResponseCallback2 = this.i;
        if (iBaseResponseCallback2 != null) {
            iBaseResponseCallback2.d(100000, "");
        }
    }

    private MusicFolderStruct a(List<cwd> list) {
        MusicFolderStruct musicFolderStruct = new MusicFolderStruct();
        if (list == null) {
            return musicFolderStruct;
        }
        for (cwd cwdVar : list) {
            if (cwdVar != null) {
                int c2 = jds.c(cwdVar.e(), 16);
                if (c2 == 3) {
                    musicFolderStruct.setFolderIndex(CommonUtil.w(cwdVar.c()));
                } else if (c2 == 4) {
                    musicFolderStruct.setFolderName(cvx.e(cwdVar.c()));
                } else if (c2 == 5) {
                    musicFolderStruct.setFolderMusicAssociationFrameCount(CommonUtil.w(cwdVar.c()));
                } else {
                    LogUtil.h("HwMusicControlManager", "Folder type:", Integer.valueOf(CommonUtil.w(cwdVar.e())));
                }
            }
        }
        return musicFolderStruct;
    }

    private void c(List<cwd> list) {
        if (list == null) {
            return;
        }
        jjw jjwVar = new jjw();
        for (cwd cwdVar : list) {
            try {
                int parseInt = Integer.parseInt(cwdVar.e(), 16);
                if (parseInt == 1) {
                    jjwVar.a(Integer.parseInt(cwdVar.c(), 16));
                } else if (parseInt != 2) {
                    int i = 0;
                    if (parseInt == 3) {
                        String c2 = cwdVar.c();
                        ArrayList arrayList = new ArrayList(16);
                        while (true) {
                            int i2 = i + 4;
                            if (c2.length() < i2) {
                                break;
                            }
                            arrayList.add(Integer.valueOf(Integer.parseInt(c2.substring(i, i2), 16)));
                            i = i2;
                        }
                        jjwVar.e(arrayList);
                    } else {
                        LogUtil.h("HwMusicControlManager", "Association type:", Integer.valueOf(Integer.parseInt(cwdVar.e(), 16)));
                    }
                } else {
                    jjwVar.d(Integer.parseInt(cwdVar.c(), 16));
                }
            } catch (NumberFormatException unused) {
                LogUtil.h("HwMusicControlManager", "handleAssociationMessage exception");
            }
        }
        LogUtil.a("HwMusicControlManager", "folderMusicFrameAssociation", jjwVar.toString());
        this.u.add(jjwVar);
        this.k.d(100000, "");
    }

    private void e(List<cwd> list) {
        int i;
        if (list == null) {
            return;
        }
        OperationStruct operationStruct = new OperationStruct();
        for (cwd cwdVar : list) {
            int i2 = 0;
            try {
                i = CommonUtil.w(cwdVar.e());
            } catch (NumberFormatException unused) {
                LogUtil.b("HwMusicControlManager", "tlv.getTag() NumberFormatException");
                i = 0;
            }
            if (i == 1) {
                operationStruct.setOperationType(CommonUtil.w(cwdVar.c()));
            } else if (i == 2) {
                operationStruct.setFolderIndex(CommonUtil.w(cwdVar.c()));
            } else if (i == 3) {
                operationStruct.setFolderName(cvx.e(cwdVar.c()));
            } else if (i == 4) {
                String c2 = cwdVar.c();
                ArrayList arrayList = new ArrayList(16);
                while (true) {
                    int i3 = i2 + 4;
                    if (c2.length() < i3) {
                        break;
                    }
                    arrayList.add(Integer.valueOf(CommonUtil.w(c2.substring(i2, i3))));
                    i2 = i3;
                }
                operationStruct.setMusicIndexs(arrayList);
            } else if (i == 127) {
                operationStruct.setOperateResult(CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.h("HwMusicControlManager", "Invalid type:", cwdVar.e());
            }
        }
        f(operationStruct);
    }

    private void g(List<cwd> list) {
        if (list == null) {
            return;
        }
        String str = "";
        int i = 0;
        for (cwd cwdVar : list) {
            try {
                int parseInt = Integer.parseInt(cwdVar.e(), 16);
                if (parseInt == 1) {
                    i = Integer.parseInt(cwdVar.c(), 16);
                } else if (parseInt != 2) {
                    LogUtil.h("HwMusicControlManager", "Invalid type:", Integer.valueOf(Integer.parseInt(cwdVar.e(), 16)));
                } else {
                    str = cvx.e(cwdVar.c());
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("HwMusicControlManager", "handleRequestMusicInfoMessage NumberFormatException");
            }
        }
        MusicIndexCallbackInterface musicIndexCallbackInterface = this.w;
        if (musicIndexCallbackInterface != null) {
            musicIndexCallbackInterface.onGetMusicFileInfo(i, str, 100000);
        } else {
            LogUtil.h("HwMusicControlManager", "mMusicIndexCallback is null");
        }
    }

    private void a(List<cwd> list, DeviceInfo deviceInfo) {
        if (koq.b(list)) {
            LogUtil.h("HwMusicControlManager", "RequestMusicAccountInfoMessage tlvList is empty.");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.h("HwMusicControlManager", "RequestMusicAccountInfoMessage deviceInfo is null.");
            return;
        }
        Iterator<cwd> it = list.iterator();
        while (it.hasNext()) {
            if (jds.c(it.next().c(), 16) == 100000) {
                LogUtil.a("HwMusicControlManager", "RequestMusicAccountInfoMessage success.");
                SharedPreferenceManager.e(Integer.toString(10000), "setMusicInfo" + deviceInfo.getSecurityDeviceId(), System.currentTimeMillis());
                return;
            }
        }
    }

    private void i(List<cwd> list) {
        LogUtil.a("HwMusicControlManager", "Enter handleRequestAuthorizationStatus");
        ExtendHandler extendHandler = this.q;
        if (extendHandler != null) {
            extendHandler.removeMessages(2002);
        }
        if (list == null) {
            return;
        }
        int i = 0;
        for (cwd cwdVar : list) {
            try {
                if (CommonUtil.w(cwdVar.e()) == 1) {
                    i = CommonUtil.w(cwdVar.c());
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("HwMusicControlManager", "handleRequestAuthorizationStatus NumberFormatException");
            }
        }
        IBaseResponseCallback iBaseResponseCallback = this.l;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, Integer.valueOf(i));
        } else {
            LogUtil.h("HwMusicControlManager", "mMusicIndexCallback is null");
        }
    }

    private void f(OperationStruct operationStruct) {
        if (operationStruct == null) {
            return;
        }
        LogUtil.a("HwMusicControlManager", "Enter operationOnResponse operationStruct:", operationStruct.toString());
        if (!e(operationStruct) && this.j.size() > 0) {
            Iterator<Map.Entry<OperationStruct, IBaseResponseCallback>> it = this.j.entrySet().iterator();
            while (it.hasNext()) {
                this.j.get(it.next().getKey()).d(100007, "Wearing data errors");
            }
            this.j.clear();
        }
        b(operationStruct);
        c(operationStruct);
        a(operationStruct);
    }

    private void b(OperationStruct operationStruct) {
        if (operationStruct != null && operationStruct.getOperationType() == 0) {
            Iterator<Map.Entry<OperationStruct, IBaseResponseCallback>> it = this.j.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<OperationStruct, IBaseResponseCallback> next = it.next();
                OperationStruct key = next.getKey();
                LogUtil.a("HwMusicControlManager", "Enter onResponseAddFolder operation:", key.toString());
                if (key.getOperationType() == operationStruct.getOperationType() && key.getFolderName().equals(operationStruct.getFolderName())) {
                    next.getValue().d(operationStruct.getOperateResult(), Integer.valueOf(operationStruct.getFolderIndex()));
                    it.remove();
                    return;
                }
            }
        }
    }

    private void c(OperationStruct operationStruct) {
        if (operationStruct.getOperationType() == 1 || operationStruct.getOperationType() == 2) {
            h(operationStruct);
        }
    }

    private void a(OperationStruct operationStruct) {
        if (operationStruct == null) {
            return;
        }
        if (operationStruct.getOperationType() == 3 || operationStruct.getOperationType() == 4) {
            h(operationStruct);
        }
    }

    private void h(OperationStruct operationStruct) {
        Iterator<Map.Entry<OperationStruct, IBaseResponseCallback>> it = this.j.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<OperationStruct, IBaseResponseCallback> next = it.next();
            OperationStruct key = next.getKey();
            LogUtil.a("HwMusicControlManager", "Enter operationOnResponse operation:", key.toString());
            if (key.getOperationType() == operationStruct.getOperationType() && key.getFolderIndex() == operationStruct.getFolderIndex()) {
                next.getValue().d(operationStruct.getOperateResult(), "");
                it.remove();
                return;
            }
        }
    }

    private boolean e(OperationStruct operationStruct) {
        if (operationStruct == null) {
            return false;
        }
        if (operationStruct.getOperationType() < 0 || operationStruct.getOperationType() > 4) {
            LogUtil.h("HwMusicControlManager", "wearOperationParametersIsValid parameters.getOperationType()：", Integer.valueOf(operationStruct.getOperationType()));
            return false;
        }
        if (operationStruct.getOperateResult() == 0) {
            LogUtil.h("HwMusicControlManager", "wearOperationParametersIsValid parameters.getOperateResult()：", Integer.valueOf(operationStruct.getOperateResult()), " ", "parameters.getFolderIndex()", Integer.valueOf(operationStruct.getFolderIndex()));
            return false;
        }
        if (operationStruct.getOperationType() >= 0) {
            return true;
        }
        if (operationStruct.getFolderName() != null && !operationStruct.getFolderName().equals("")) {
            return true;
        }
        LogUtil.h("HwMusicControlManager", "wearOperationParametersIsValid parameters.getFolderName()：", operationStruct.getFolderName());
        return false;
    }

    private void c(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMusicControlManager", "sendMusicFrameCount");
        synchronized (e) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(37);
            deviceCommand.setCommandID(4);
            String e2 = cvx.e(1);
            String d2 = cvx.d(0);
            String e3 = cvx.e(2);
            String d3 = cvx.d(0);
            String e4 = cvx.e(3);
            String d4 = cvx.d(0);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e2);
            sb.append(d2);
            sb.append(e3);
            sb.append(d3);
            sb.append(e4);
            sb.append(d4);
            String sb2 = sb.toString();
            LogUtil.a("HwMusicControlManager", "sendMusicFrameCount packageMusicCommond :", sb2);
            byte[] a2 = cvx.a(sb2);
            deviceCommand.setDataLen(a2.length);
            deviceCommand.setDataContent(a2);
            this.aa.b(deviceCommand);
            this.o = iBaseResponseCallback;
            c(4, 30);
        }
    }

    private void c(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMusicControlManager", "sendGetMusicInfo musicFrameindex: ", Integer.valueOf(i2));
        synchronized (e) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(37);
            deviceCommand.setCommandID(5);
            String a2 = cvx.a(i2);
            String d2 = cvx.d(a2.length() / 2);
            String e2 = cvx.e(1);
            String a3 = cvx.a(i);
            String d3 = cvx.d(a3.length() / 2);
            String e3 = cvx.e(4);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e2);
            sb.append(d2);
            sb.append(a2);
            sb.append(e3);
            sb.append(d3);
            sb.append(a3);
            String sb2 = sb.toString();
            LogUtil.a("HwMusicControlManager", "sendGetMusicInfo package packageMusicCommond :", sb2);
            byte[] a4 = cvx.a(sb2);
            deviceCommand.setDataLen(a4.length);
            deviceCommand.setDataContent(a4);
            this.aa.b(deviceCommand);
            this.m = iBaseResponseCallback;
            c(5, 30);
        }
    }

    private void b(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMusicControlManager", "getFolderInfo musicInfo");
        synchronized (e) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(37);
            deviceCommand.setCommandID(6);
            String e2 = cvx.e(1);
            String d2 = cvx.d(0);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e2);
            sb.append(d2);
            String sb2 = sb.toString();
            LogUtil.a("HwMusicControlManager", "package sendGetFolderInfo :", sb2);
            byte[] a2 = cvx.a(sb2);
            deviceCommand.setDataLen(a2.length);
            deviceCommand.setDataContent(a2);
            this.aa.b(deviceCommand);
            this.i = iBaseResponseCallback;
            c(6, 30);
        }
    }

    private void d(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMusicControlManager", "getOneFolderOneFrameMusicAssociation folderIndex:", Integer.valueOf(i), " currentFrameIndex:", Integer.valueOf(i2));
        synchronized (e) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(37);
            deviceCommand.setCommandID(7);
            String a2 = cvx.a(i);
            String d2 = cvx.d(a2.length() / 2);
            String e2 = cvx.e(1);
            String a3 = cvx.a(i2);
            String d3 = cvx.d(a2.length() / 2);
            String e3 = cvx.e(2);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e2);
            sb.append(d2);
            sb.append(a2);
            sb.append(e3);
            sb.append(d3);
            sb.append(a3);
            String sb2 = sb.toString();
            LogUtil.a("HwMusicControlManager", "package packagefolderMusicAssociationInfoListCommond :", sb2);
            byte[] a4 = cvx.a(sb2);
            deviceCommand.setDataLen(a4.length);
            deviceCommand.setDataContent(a4);
            this.aa.b(deviceCommand);
            this.k = iBaseResponseCallback;
            c(7, 30);
        }
    }

    private void b(OperationStruct operationStruct, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMusicControlManager", "Enter sendFolderMusicOperate");
        synchronized (e) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(37);
            deviceCommand.setCommandID(8);
            StringBuilder sb = new StringBuilder(16);
            d(operationStruct, sb);
            String sb2 = sb.toString();
            LogUtil.a("HwMusicControlManager", "package sendFolderMusicOperate Command :", sb2);
            byte[] a2 = cvx.a(sb2);
            deviceCommand.setDataLen(a2.length);
            deviceCommand.setDataContent(a2);
            this.aa.b(deviceCommand);
            if (this.j.size() > 16) {
                this.j.clear();
            }
            this.j.put(operationStruct, iBaseResponseCallback);
        }
    }

    private void d(OperationStruct operationStruct, StringBuilder sb) {
        String d2;
        String e2;
        if (operationStruct == null || sb == null) {
            return;
        }
        String e3 = cvx.e(operationStruct.getOperationType());
        String d3 = cvx.d(e3.length() / 2);
        sb.append(cvx.e(1));
        sb.append(d3);
        sb.append(e3);
        a(operationStruct, sb);
        StringBuffer stringBuffer = new StringBuffer(16);
        if (operationStruct.getOperationType() == 3 || operationStruct.getOperationType() == 4) {
            Iterator<Integer> it = operationStruct.getMusicIndexs().iterator();
            while (it.hasNext()) {
                stringBuffer.append(cvx.a(it.next().intValue()));
            }
            d2 = cvx.d(stringBuffer.length() / 2);
            e2 = cvx.e(4);
        } else {
            e2 = "";
            d2 = "";
        }
        sb.append(e2);
        sb.append(d2);
        sb.append(stringBuffer.toString());
    }

    private void a(OperationStruct operationStruct, StringBuilder sb) {
        String a2;
        String d2;
        String e2;
        String d3;
        String str;
        if (operationStruct == null || sb == null) {
            return;
        }
        boolean z = operationStruct.getOperationType() == 1 || operationStruct.getOperationType() == 2;
        boolean z2 = operationStruct.getOperationType() == 3 || operationStruct.getOperationType() == 4;
        String str2 = "";
        if (z || z2) {
            a2 = cvx.a(operationStruct.getFolderIndex());
            d2 = cvx.d(a2.length() / 2);
            e2 = cvx.e(2);
        } else {
            a2 = "";
            d2 = a2;
            e2 = d2;
        }
        if (operationStruct.getOperationType() == 0 || operationStruct.getOperationType() == 2) {
            String c2 = cvx.c(operationStruct.getFolderName());
            d3 = cvx.d(c2.length() / 2);
            str = c2;
            str2 = cvx.e(3);
        } else {
            d3 = "";
            str = d3;
        }
        sb.append(e2);
        sb.append(d2);
        sb.append(a2);
        sb.append(str2);
        sb.append(d3);
        sb.append(str);
    }

    public void e(MusicStruct musicStruct) {
        synchronized (e) {
            jjj.a(musicStruct);
        }
    }

    public byte[] c(int i) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(1) + cvx.e(1) + cvx.e(i));
        String stringBuffer2 = stringBuffer.toString();
        LogUtil.a("HwMusicControlManager", "package status Commond:", stringBuffer2);
        return cvx.a(stringBuffer2);
    }

    private void c(List<cwd> list, int i) {
        int c2;
        if (list == null || list.size() == 0) {
            return;
        }
        LogUtil.a("HwMusicControlManager", "Enter handleMusicErrorMessage. commandId : ", Integer.valueOf(i));
        if (list.get(0) == null || (c2 = jds.c(list.get(0).c(), 16)) == -1 || c2 == 100000) {
            return;
        }
        if (i == 4) {
            MusicControllerCallbackInterface musicControllerCallbackInterface = this.p;
            if (musicControllerCallbackInterface != null) {
                musicControllerCallbackInterface.onGetFolders(null, c2);
                this.p.onGetNegotiationData(null, c2);
                return;
            }
            return;
        }
        if (i == 5) {
            if (this.r) {
                IBaseResponseCallback iBaseResponseCallback = this.m;
                if (iBaseResponseCallback != null) {
                    iBaseResponseCallback.d(c2, null);
                    return;
                }
                return;
            }
            MusicControllerCallbackInterface musicControllerCallbackInterface2 = this.p;
            if (musicControllerCallbackInterface2 != null) {
                musicControllerCallbackInterface2.onGetFolders(null, c2);
                return;
            }
            return;
        }
        e(i, c2);
    }

    private void e(int i, int i2) {
        switch (i) {
            case 6:
            case 7:
                MusicControllerCallbackInterface musicControllerCallbackInterface = this.p;
                if (musicControllerCallbackInterface != null) {
                    musicControllerCallbackInterface.onGetFolders(null, i2);
                    break;
                }
                break;
            case 8:
                if (this.j.size() > 0) {
                    Iterator<Map.Entry<OperationStruct, IBaseResponseCallback>> it = this.j.entrySet().iterator();
                    while (it.hasNext()) {
                        IBaseResponseCallback iBaseResponseCallback = this.j.get(it.next().getKey());
                        if (iBaseResponseCallback != null) {
                            iBaseResponseCallback.d(i2, "handleOperationMessage error");
                        }
                    }
                    this.j.clear();
                    break;
                }
                break;
            case 9:
                MusicControllerCallbackInterface musicControllerCallbackInterface2 = this.p;
                if (musicControllerCallbackInterface2 != null) {
                    musicControllerCallbackInterface2.onGetMusicFileInfo(0, null, i2);
                    break;
                }
                break;
            default:
                LogUtil.h("HwMusicControlManager", "handleMusicErrorMessage Invalid type:", Integer.valueOf(i));
                break;
        }
    }

    private void c(int i, int i2) {
        if (this.q != null) {
            if (i2 != 0) {
                Message obtain = Message.obtain();
                obtain.what = i;
                this.q.sendMessage(obtain, i2 * 1000);
                return;
            }
            return;
        }
        this.q = HandlerCenter.yt_(new c(), "HwMusicControlManager");
        if (i2 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = i;
            this.q.sendMessage(obtain2, i2 * 1000);
        }
    }

    private void d(int i) {
        if (i == 4 || i == 5 || i == 6 || i == 7) {
            b(i);
        } else {
            LogUtil.h("HwMusicControlManager", "handleMusicErrorMessage Invalid type:");
        }
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMusicControlManager", "Enter getAuthorizationStatus");
        synchronized (e) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(37);
            deviceCommand.setCommandID(15);
            this.aa.b(deviceCommand);
            this.l = iBaseResponseCallback;
            c(2002, 30);
        }
    }

    public void b() {
        LogUtil.a("HwMusicControlManager", "removeMusicPermissionCallback");
        this.l = null;
    }

    private void b(int i) {
        ExtendHandler extendHandler = this.q;
        if (extendHandler != null) {
            extendHandler.removeMessages(i);
        }
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        c(bArr, deviceInfo);
    }

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 4) {
                if (i == 5) {
                    if (jjd.this.r) {
                        if (jjd.this.m != null) {
                            jjd.this.m.d(100009, null);
                        }
                    } else if (jjd.this.p != null) {
                        jjd.this.p.onGetFolders(null, 100009);
                    }
                    return true;
                }
                if (i != 6 && i != 7) {
                    if (i == 2001) {
                        if (jjd.this.n != null) {
                            jjd.this.n.d(1, "");
                        }
                        return true;
                    }
                    if (i == 2002) {
                        if (jjd.this.l != null) {
                            jjd.this.l.d(0, 100009);
                        }
                        return true;
                    }
                    LogUtil.h("HwMusicControlManager", "handleMessage Invalid type:");
                    return false;
                }
            }
            if (jjd.this.p != null) {
                jjd.this.p.onGetFolders(null, 100009);
            }
            return true;
        }
    }

    public void c(final boolean z, final boolean z2) {
        LogUtil.a("HwMusicControlManager", "enter setMusicStatus result :", Boolean.valueOf(z));
        ThreadPoolManager.d().execute(new Runnable() { // from class: jjd.5
            @Override // java.lang.Runnable
            public void run() {
                String str = z ? "true" : "false";
                if (z2) {
                    jqi.a().setSwitchSettingToLocal("music_control_status", str, 10000);
                }
                jqi.a().setSwitchSettingToDb("music_control_status", str);
            }
        });
        boolean j = jjj.j();
        LogUtil.a("HwMusicControlManager", "setLocalMusicSwitch, isSupportSendSwitchStatus:", Boolean.valueOf(j));
        if (j) {
            jqi.a().sendSetSwitchSettingCmd(b(BaseApplication.getContext()).c(z ? 1 : 0), "", 37, 14);
        }
    }

    public boolean c() {
        String switchSettingFromLocal;
        return (!jrg.b() || (switchSettingFromLocal = jqi.a().getSwitchSettingFromLocal("music_control_status", 10000)) == null || "false".equals(switchSettingFromLocal)) ? false : true;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
    }
}
