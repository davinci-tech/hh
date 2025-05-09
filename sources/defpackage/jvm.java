package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.trackprocess.api.TrackPostProcessApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jvm {
    private static jvm b;
    private static final Object d = new Object();
    private ExtendHandler l;
    private final TrackPostProcessApi r;
    private int s;
    private List<Integer> t;
    private int u;
    private jvi m = jvi.a();
    private jvy n = new jvy();
    private Map<Integer, Map<Long, double[]>> e = new HashMap(16);
    private Map<Integer, Integer> g = new HashMap(16);
    private Map<Integer, List<Long>> i = new HashMap(16);
    private ArrayList<byte[]> o = new ArrayList<>(16);
    private List<jvp> c = new ArrayList(16);
    private jrw f = null;
    private boolean p = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f14132a = false;
    private int j = 0;
    private int q = 0;
    private List<kbe> k = new ArrayList(16);
    private BtDeviceResponseCallback h = new BtDeviceResponseCallback() { // from class: jvm.4
        @Override // com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback
        public void onResponse(int i, Object obj) {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                kbe kbeVar = new kbe();
                kbeVar.c(0);
                kbeVar.c(bArr);
                if (bArr.length != 8 || bArr[2] != Byte.MAX_VALUE) {
                    jvk.b().c(jvm.this.l, 15);
                    jvm.this.b(0, kbeVar);
                } else {
                    blt.d("HwGpsFileTransferTaskManager", bArr, "mFileCallback() onResponse error: ");
                }
            }
        }
    };

    private jvm() {
        ExtendHandler yt_ = HandlerCenter.yt_(new c(), "GPS_HwGpsFileTransferTaskManager");
        this.l = yt_;
        this.m.e(yt_);
        this.r = (TrackPostProcessApi) Services.c("Module_Track_Post_Process_Service", TrackPostProcessApi.class);
    }

    public static jvm a() {
        jvm jvmVar;
        synchronized (d) {
            if (b == null) {
                b = new jvm();
            }
            jvmVar = b;
        }
        return jvmVar;
    }

    public class c implements Handler.Callback {
        public c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            try {
                int i = message.what;
                if (i == 1) {
                    jvm.this.bKt_(message);
                    return true;
                }
                if (i == 2) {
                    jvm.this.bKo_(message);
                    return true;
                }
                if (i == 3) {
                    jvm.this.bKv_(message);
                    return true;
                }
                if (i == 4) {
                    jvm.this.bKr_(message);
                    return true;
                }
                if (i == 5) {
                    jvm.this.bKu_(message);
                    return true;
                }
                if (i == 7) {
                    jvm.this.f();
                    return true;
                }
                if (i != 127) {
                    switch (i) {
                        case 14:
                            jvm.this.bKp_(message);
                            break;
                        case 15:
                            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle data transfer time out");
                            jvm.this.o();
                            break;
                        case 16:
                            jvm.this.j();
                            break;
                        default:
                            Object[] objArr = new Object[1];
                            objArr[0] = "GpsHandle default";
                            ReleaseLogUtil.d("BTSYNC_HwGpsFileTransferTaskManager", objArr);
                            break;
                    }
                    return true;
                }
                jvm.this.bKn_(message);
                return true;
            } catch (ClassCastException e) {
                ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle ClassCastException:", ExceptionUtils.d(e));
                return true;
            } catch (NumberFormatException e2) {
                ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle NumberFormatException:", ExceptionUtils.d(e2));
                return true;
            } catch (Exception e3) {
                ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle Exception:", ExceptionUtils.d(e3));
                return true;
            }
        }
    }

    public void b(TransferFileInfo transferFileInfo) {
        if (transferFileInfo == null) {
            ReleaseLogUtil.d("BTSYNC_HwGpsFileTransferTaskManager", "transferFileGps transferFileInfo is null.");
            return;
        }
        this.e.clear();
        this.g.clear();
        this.i.clear();
        this.m.g(0);
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "request GPS");
        this.f = jrw.a();
        jss.b(this.h);
        this.t = transferFileInfo.getRecordId();
        this.u = transferFileInfo.getGpsType();
        this.s = 0;
        List<Integer> list = this.t;
        if (list != null && list.size() != 0 && this.t.size() > this.s) {
            jvk.b().d(this.t.get(this.s).intValue(), this.u, this.l);
            return;
        }
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "mRecordIdList.size is 0");
        ExtendHandler extendHandler = this.l;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(16);
        } else {
            ReleaseLogUtil.d("BTSYNC_HwGpsFileTransferTaskManager", "mHandler is null");
        }
    }

    public void b(int i, kbe kbeVar) {
        if (kbeVar == null) {
            ReleaseLogUtil.d("BTSYNC_HwGpsFileTransferTaskManager", "processApplyGpsData maintenanceLog is null.");
            return;
        }
        if (kbeVar.b().length == 8 && kbeVar.b()[2] == Byte.MAX_VALUE && kbeVar.b()[0] == 10 && kbeVar.b()[1] == 5 && kbeVar.b()[3] == 4) {
            this.m.x();
            this.o.clear();
            this.m.i(0);
            this.m.c("All file:");
            this.m.a("Done file:");
            int i2 = this.q + 1;
            this.q = i2;
            LogUtil.c("HwGpsFileTransferTaskManager", "applyDataFromDeviceHandle() mSendNum = ", Integer.valueOf(i2));
            if (this.q == 4) {
                jvk.b().c(this.l, 15);
                o();
                this.q = 0;
                return;
            } else {
                List<Integer> list = this.t;
                if (list == null || this.s >= list.size()) {
                    return;
                }
                jvk.b().d(this.t.get(this.s).intValue(), i, this.l);
                return;
            }
        }
        a(kbeVar);
    }

    private void a(kbe kbeVar) {
        int d2 = kbeVar.d();
        if (d2 == this.m.c()) {
            e(kbeVar);
            b();
        } else {
            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "addPlayDataToList() lost index = ", Integer.valueOf(d2));
            this.k.add(kbeVar);
        }
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "addPlayDataToList() mDonePackageSize = ", Integer.valueOf(this.m.g()), ",mCurrentFrameNum", Integer.valueOf(this.m.c()), ",index ", Integer.valueOf(d2), " mDoneTotalSize:", Integer.valueOf(this.m.j()));
        String a2 = this.m.r().a();
        boolean contains = a2 == null ? false : a2.contains("AW");
        if (this.l != null) {
            if (this.m.d() == this.m.g() || contains) {
                this.m.i(0);
                this.m.e(0);
                this.o.addAll(this.m.n());
                this.m.n().clear();
                LogUtil.a("HwGpsFileTransferTaskManager", "send ok writeLogToFile mFileName = ", this.m.k(), ";mMaintenanceLogList = ", Integer.valueOf(this.m.n().size()), ";mGpsLogList = ", Integer.valueOf(this.o.size()), ",mDoneTotalSize = ", Integer.valueOf(this.m.j()), ",mFileTotalSize = ", Integer.valueOf(this.m.j()));
                this.l.removeMessages(7);
                this.f14132a = false;
                e();
            }
        }
    }

    private void e(kbe kbeVar) {
        byte[] b2 = kbeVar.b();
        if (b2 != null) {
            jvi jviVar = this.m;
            jviVar.e(jviVar.g() + b2.length);
            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "addPlayDataToList() mFileType = ", Integer.valueOf(this.m.m()));
            d(b2);
            jvi jviVar2 = this.m;
            jviVar2.b(jviVar2.j() + b2.length);
        } else {
            ReleaseLogUtil.d("BTSYNC_HwGpsFileTransferTaskManager", "addPlayDataToList() byteData is null");
        }
        jvi jviVar3 = this.m;
        jviVar3.d(jviVar3.c() + 1);
    }

    private void b() {
        if (this.k.isEmpty()) {
            LogUtil.h("HwGpsFileTransferTaskManager", "getDataFrameCache mFrameDataList isEmpty.");
            return;
        }
        Collections.sort(this.k, new Comparator<kbe>() { // from class: jvm.3
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(kbe kbeVar, kbe kbeVar2) {
                return kbeVar.d() - kbeVar2.d();
            }
        });
        Iterator<kbe> it = this.k.iterator();
        while (it.hasNext()) {
            kbe next = it.next();
            if (!(next instanceof kbe)) {
                LogUtil.h("HwGpsFileTransferTaskManager", "getDataFrameCache iterator data error");
                return;
            }
            kbe kbeVar = next;
            if (kbeVar.d() != this.m.c()) {
                return;
            }
            e(kbeVar);
            it.remove();
        }
    }

    private void d(byte[] bArr) {
        if (this.m.m() != 1) {
            this.m.n().add(bArr);
            return;
        }
        try {
            this.m.n().add(cvx.d(bArr).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "exception unsupportedEncodingException:", ExceptionUtils.d(e));
        }
    }

    private void e() {
        if (this.m.j() >= this.m.l()) {
            d();
        } else {
            c();
        }
    }

    private void d() {
        this.l.removeMessages(7);
        this.m.a(this.m.f() + this.m.k() + ",");
        List<jvp> list = this.c;
        if (list != null && !list.isEmpty()) {
            Iterator<byte[]> it = this.o.iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().length;
            }
            ByteBuffer allocate = ByteBuffer.allocate(i);
            Iterator<byte[]> it2 = this.o.iterator();
            while (it2.hasNext()) {
                allocate.put(it2.next());
            }
            b(allocate);
            this.o.clear();
            this.m.d(0);
            this.k.clear();
        }
        jvk.b().e(this.m.o(), this.l);
    }

    private void b(ByteBuffer byteBuffer) {
        if (this.c.isEmpty()) {
            LogUtil.h("HwGpsFileTransferTaskManager", "gpsTrackPostProcessingHandle mAllFileList is empty.");
            return;
        }
        jvp jvpVar = this.c.get(0);
        if (jvpVar == null) {
            LogUtil.h("HwGpsFileTransferTaskManager", "gpsTrackPostProcessingHandle gpsFileInfo is null.");
            return;
        }
        if (!this.n.e()) {
            try {
                this.n.d(cvx.a(new String(byteBuffer.array(), "utf-8")), jvpVar.b());
            } catch (Exception e) {
                ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "addPlayData2List3()：", ExceptionUtils.d(e));
            }
            int d2 = this.f.d(this.f.a(byteBuffer.array()));
            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "addPlayDataToList()!,mapType:", Integer.valueOf(d2));
            this.g.put(Integer.valueOf(jvpVar.c()), Integer.valueOf(d2));
            this.e.put(Integer.valueOf(jvpVar.c()), this.f.c(byteBuffer.array(), this.m.m()));
            this.c.remove(0);
            return;
        }
        try {
            byte[] a2 = cvx.a(new String(byteBuffer.array(), "utf-8"));
            if (jvpVar.d() == 1) {
                kbf a3 = this.f.a(byteBuffer.array());
                int d3 = this.f.d(a3);
                this.p = this.f.a(a3);
                ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "addPlayDataToList()!,mapType=", Integer.valueOf(d3), " ,isSupportAltitude=", Boolean.valueOf(this.p));
                this.g.put(Integer.valueOf(jvpVar.c()), Integer.valueOf(d3));
                jvpVar.e(2);
                this.n.d(a2, jvpVar.b());
                this.i.put(Integer.valueOf(jvpVar.c()), this.n.b(a2));
            } else {
                c(a2);
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "addPlayData2List2()：", ExceptionUtils.d(e2));
        }
    }

    private void c(byte[] bArr) {
        this.n.d(bArr, this.c.get(0).a());
        int h = h();
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "addPlayDataToList()!,tatatee trackProcess result =", Integer.valueOf(h));
        if (h != 0) {
            this.c.remove(0);
            this.o.clear();
            this.m.d(0);
            this.k.clear();
        }
    }

    private int h() {
        return this.r.getPostProcessingTrack(this.c.get(0).b(), this.c.get(0).a(), this.p, new IBaseResponseCallback() { // from class: jvq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jvm.this.c(i, obj);
            }
        });
    }

    /* synthetic */ void c(int i, Object obj) {
        if (obj instanceof Map) {
            this.e.put(Integer.valueOf(this.c.get(0).c()), (Map) obj);
        }
        this.c.remove(0);
        this.o.clear();
        this.m.d(0);
        this.k.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKp_(Message message) {
        if (!this.c.isEmpty()) {
            if (this.c.get(0).d() == 1) {
                this.m.e(this.c.get(0).b());
            } else {
                this.m.e(this.c.get(0).a());
            }
            this.m.f(this.c.get(0).e());
            LogUtil.a("HwGpsFileTransferTaskManager", "GpsHandle start queryFileInformation mFileName = ", this.m.k(), " mFileType = ", Integer.valueOf(this.m.m()));
            if (TextUtils.isEmpty(this.m.k())) {
                Message obtain = Message.obtain();
                obtain.what = 14;
                obtain.arg1 = this.c.get(0).c();
                this.c.remove(0);
                obtain.arg2 = 10003;
                this.l.sendMessage(obtain);
                return;
            }
            d(this.m.k());
            return;
        }
        bKq_(message);
    }

    private void bKq_(Message message) {
        IBaseResponseCallback b2 = this.m.b();
        this.m.z();
        g();
        jvk.b().bKa_(b2, message, this.e, this.g, this.i);
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle maintenance success");
    }

    private void b(List<jvp> list) {
        try {
            if (list == null) {
                this.m.z();
                g();
                if (this.m.b() != null) {
                    this.m.b().d(10001, "error fileList is null");
                    return;
                }
                return;
            }
            for (jvp jvpVar : list) {
                ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "isSupportTrackPostProcessing()", Boolean.valueOf(this.n.e()));
                if (!this.n.e() || !TextUtils.isEmpty(jvpVar.a())) {
                    this.m.c(this.m.e() + jvpVar.b());
                    this.c.add(jvpVar);
                }
            }
            LogUtil.a("HwGpsFileTransferTaskManager", "getMaintenanceFileNameHandle() mAllFileListName:", this.m.e());
            if (list.size() == 0) {
                ReleaseLogUtil.d("BTSYNC_HwGpsFileTransferTaskManager", "getMaintenanceFileNameHandle fileList() = 0");
                this.m.z();
                g();
                if (this.m.b() != null) {
                    this.m.b().d(10001, jvk.b().a(this.m.e(), this.m.f()));
                    return;
                }
                return;
            }
            if (this.s + 1 < this.t.size()) {
                this.s++;
                jvk.b().d(this.t.get(this.s).intValue(), this.u, this.l);
            } else {
                ReleaseLogUtil.d("BTSYNC_HwGpsFileTransferTaskManager", "Enter getMaintenanceFileNameHandle()");
                jvk.b().d(this.l);
            }
        } catch (ClassCastException e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "getMaintenanceFileNameHandle ClassCastException:", ExceptionUtils.d(e));
        }
    }

    private void c(kbb kbbVar) {
        try {
            if (kbbVar == null) {
                ReleaseLogUtil.d("BTSYNC_HwGpsFileTransferTaskManager", "dataMaintParameters is null");
                return;
            }
            this.m.d(kbbVar);
            jvi jviVar = this.m;
            jviVar.m(jviVar.r().b());
            if (this.m.y() == 0) {
                this.m.m(5000);
            }
            jvi jviVar2 = this.m;
            jviVar2.k(jviVar2.r().c());
            jvi jviVar3 = this.m;
            jviVar3.j(jviVar3.r().e());
            if (this.m.p() == 0) {
                this.m.j(732);
            }
            jvi jviVar4 = this.m;
            jviVar4.c(jviVar4.p());
            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", " getMaintenanceParametersHandle() ok protocalVersion :", this.m.r().a(), System.lineSeparator(), "mTransferUnitSize:", Integer.valueOf(this.m.v()), System.lineSeparator(), "mMaxApplyDataSize:", Integer.valueOf(this.m.p()), System.lineSeparator(), " mWaitTimeout:", Integer.valueOf(this.m.y()), System.lineSeparator(), "breakPointEnable:", Boolean.valueOf(this.m.r().d()));
            ExtendHandler extendHandler = this.l;
            if (extendHandler != null) {
                extendHandler.sendEmptyMessage(14);
            }
        } catch (Exception e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "getMaintenanceParametersHandle Exception:", ExceptionUtils.d(e));
        }
    }

    private void d(kay kayVar) {
        int i;
        String str;
        int i2;
        try {
            this.m.a((int) kayVar.e());
            this.m.d(kayVar.a());
            LogUtil.a("HwGpsFileTransferTaskManager", " queryFileInformationHandle() ok, mFileTotalSize:", Integer.valueOf(this.m.l()), ", mFileCrc:", Long.valueOf(this.m.i()));
            if (this.m.l() == 0) {
                List<jvp> list = this.c;
                if (list == null || list.isEmpty()) {
                    i = -1;
                    str = null;
                    i2 = -1;
                } else {
                    i = this.c.get(0).d();
                    str = this.c.get(0).b();
                    i2 = this.c.get(0).c();
                    this.c.remove(0);
                }
                ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "file modelType is:GPS; transferFile size:", Integer.valueOf(this.m.l()));
                if (i == 2) {
                    d(i2, str);
                    return;
                } else {
                    if (this.l != null) {
                        jvk.b().c(i2, this.l);
                        return;
                    }
                    return;
                }
            }
            c();
        } catch (ClassCastException e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "queryFileInformationHandle ClassCastException:", ExceptionUtils.d(e));
        }
    }

    private void d(final int i, String str) {
        int postProcessingTrack = this.r.getPostProcessingTrack(str, null, this.p, new IBaseResponseCallback() { // from class: jvn
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                jvm.this.d(i, i2, obj);
            }
        });
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "addPlayDataToList()!,tatatee trackProcess result:", Integer.valueOf(postProcessingTrack));
        if (postProcessingTrack != 0) {
            this.o.clear();
            this.m.d(0);
            this.k.clear();
            if (this.l != null) {
                jvk.b().c(i, this.l);
            }
        }
    }

    /* synthetic */ void d(int i, int i2, Object obj) {
        if (obj instanceof Map) {
            this.e.put(Integer.valueOf(i), (Map) obj);
        }
        this.o.clear();
        this.m.d(0);
        this.k.clear();
        if (this.l != null) {
            Message obtain = Message.obtain();
            obtain.what = 14;
            obtain.arg1 = i;
            this.l.sendMessage(obtain);
        }
    }

    private void d(String str) {
        ExtendHandler extendHandler = this.l;
        if (extendHandler != null) {
            extendHandler.removeMessages(15);
            this.l.sendEmptyMessage(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        this.m.x();
        this.o.clear();
        this.m.d(0);
        this.k.clear();
        jvk.b().c(str);
    }

    private void a(int[] iArr, int i) {
        ExtendHandler extendHandler;
        try {
            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "applyDataHandle error code:", Integer.valueOf(iArr[0]), "; offset:", Integer.valueOf(i));
            if (iArr[0] == 100000 && (i == this.m.j() || i == 0)) {
                this.m.d(0);
                this.k.clear();
                this.f14132a = true;
                ExtendHandler extendHandler2 = this.l;
                if (extendHandler2 != null) {
                    extendHandler2.removeMessages(7);
                    this.l.sendEmptyMessage(7, this.m.y());
                }
            } else if (i < this.m.j() && i != 0 && (extendHandler = this.l) != null) {
                extendHandler.removeMessages(7);
                this.l.sendEmptyMessage(7, this.m.y());
            } else {
                jvk.b().c(this.l, 15);
                o();
            }
        } catch (Exception e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "applyDataHandle Exception:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        jvi jviVar = this.m;
        jviVar.i(jviVar.q() + 1);
        if (this.m.q() == 3) {
            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle more than 3 failures mReTransferTime = 3");
            o();
            ExtendHandler extendHandler = this.l;
            if (extendHandler != null) {
                extendHandler.removeMessages(7);
                return;
            }
            return;
        }
        if (this.m.n().size() != 0 && this.m.j() - this.m.g() >= 0 && this.m.g() != 0) {
            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle packet loss write");
            jvi jviVar2 = this.m;
            jviVar2.b(jviVar2.j() - this.m.g());
            this.m.d(0);
            this.k.clear();
            this.m.e(0);
            this.m.n().clear();
        }
        if (this.l != null) {
            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle packet loss retry");
            c();
            this.l.sendEmptyMessage(7, this.m.y());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "PROCESS_NEXT_TASK");
        IBaseResponseCallback b2 = this.m.b();
        this.m.z();
        g();
        jvk.b().a(b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKt_(Message message) {
        jvk.b().c(this.l, 15);
        try {
            if (message.obj instanceof byte[]) {
                byte[] bArr = (byte[]) message.obj;
                if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
                    e(bArr);
                } else {
                    b(this.m.w().h(bArr));
                }
            }
        } catch (cwg e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle gpsMaintenanceGetFileName() TlvException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKo_(Message message) {
        jvk.b().c(this.l, 15);
        try {
            if (message.obj instanceof byte[]) {
                c(this.m.w().g((byte[]) message.obj));
            }
        } catch (cwg e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle gpsGetParameter() TlvException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKv_(Message message) {
        jvk.b().c(this.l, 15);
        try {
            if (message.obj instanceof byte[]) {
                byte[] bArr = (byte[]) message.obj;
                if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
                    o();
                    return;
                }
                kay i = this.m.w().i(bArr);
                if (i != null) {
                    kay kayVar = i;
                    d(i);
                }
            }
        } catch (cwg e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle gpsQueryFileInformation() TlvException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKr_(Message message) {
        jvk.b().c(this.l, 15);
        try {
            if (message.obj instanceof byte[]) {
                int[] b2 = this.m.w().b((byte[]) message.obj);
                bKs_(message);
                if (!(b2 instanceof int[]) || b2.length <= 0) {
                    return;
                }
                a(b2, this.j);
            }
        } catch (cwg e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle gpsMaintenanceApplyData() TlvException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKu_(Message message) {
        try {
            if (message.obj instanceof byte[]) {
                kbe j = this.m.w().j((byte[]) message.obj);
                if (this.f14132a) {
                    b(0, j);
                }
            }
        } catch (cwg e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle gpsMaintenanceReportData() TlvException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKn_(Message message) {
        try {
            if (message.obj instanceof byte[]) {
                c(this.m.w().a((byte[]) message.obj));
            }
        } catch (cwg e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "GpsHandle gpsErrorCode() TlvException:", ExceptionUtils.d(e));
        }
    }

    private void c(int[] iArr) {
        if (iArr != null) {
            try {
                if (iArr.length > 0 && iArr[0] != 100000) {
                    this.m.z();
                    g();
                    b(iArr);
                }
            } catch (Exception e) {
                ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "errorCodeHandle Exception:", ExceptionUtils.d(e));
            }
        }
    }

    private void b(int[] iArr) {
        if (this.m.b() != null) {
            this.m.b().d(iArr[0], jvk.b().c(iArr[0]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.m.z();
        g();
        jvk.b().d(this.m.b(), this.e, this.g, this.i);
    }

    private void e(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (!TextUtils.isEmpty(d2) && d2.contains("000186A7") && this.t.size() > 0) {
            jvk.b().c(this.l, this.t);
            return;
        }
        this.m.z();
        g();
        jvk.b().e(this.m.b(), this.e, this.g, this.i);
    }

    private void bKs_(Message message) {
        if (message.obj instanceof byte[]) {
            String d2 = cvx.d((byte[]) message.obj);
            if (d2 == null || d2.length() < 5) {
                ReleaseLogUtil.d("BTSYNC_HwGpsFileTransferTaskManager", "gpsMaintenanceApplyDataPart tlvHex is error");
            } else {
                c(d2.substring(4, d2.length()));
            }
        }
    }

    private void c(String str) {
        try {
            for (cwd cwdVar : new cwl().a(str).e()) {
                if (CommonUtil.w(cwdVar.e()) == 2) {
                    e(cwdVar);
                    return;
                }
            }
        } catch (cwg e) {
            ReleaseLogUtil.c("BTSYNC_HwGpsFileTransferTaskManager", "parseGpsTlvData tlv exception:", ExceptionUtils.d(e));
        }
    }

    private void e(cwd cwdVar) {
        int w = CommonUtil.w(cwdVar.c());
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "gpsMaintenanceApplyDataPartHandle response mCurrentApplyDataSize:", Integer.valueOf(this.m.d()), ";mDonePackageSize:", Integer.valueOf(this.m.g()));
        if (this.m.d() != this.m.g() && this.m.g() != 0) {
            i();
        }
        this.j = w;
    }

    private void i() {
        if (this.m.j() - this.m.g() < 0 || this.m.g() == 0) {
            return;
        }
        jvi jviVar = this.m;
        jviVar.b(jviVar.j() - this.m.g());
        this.m.d(0);
        this.k.clear();
        this.m.e(0);
        this.m.n().clear();
    }

    private void c() {
        int l = this.m.l() - this.m.j();
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "getApplyDataFromDevice spareSize:", Integer.valueOf(l), ",mCurrentApplyDataSize:", Integer.valueOf(this.m.d()), ",mDoneTotalSize", Integer.valueOf(this.m.j()));
        if (this.m.j() % this.m.p() == 0 || l == this.m.l()) {
            if (l < this.m.p()) {
                this.m.c(l);
                a(this.m.k(), this.m.j(), this.m.d());
                return;
            } else {
                jvi jviVar = this.m;
                jviVar.c(jviVar.p());
                a(this.m.k(), this.m.j(), this.m.d());
                return;
            }
        }
        ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "getApplyDataFromDevice mFileTotalSize:", Integer.valueOf(this.m.l()), ", mMaxApplyDataSize:", Integer.valueOf(this.m.p()));
    }

    private void a(String str, int i, int i2) {
        this.m.h(0);
        if (jcr.e() != null && jcr.e().getSuspend() == 1) {
            this.m.t().setSuspend(1);
            ReleaseLogUtil.e("BTSYNC_HwGpsFileTransferTaskManager", "applyDataFromDevice Tasks are interrupted, and the higher priority is GPS");
            this.m.z();
            g();
            if (this.m.b() != null) {
                this.m.b().d(104003, jvk.b().a(this.m.e(), this.m.f()));
            }
            ExtendHandler extendHandler = this.l;
            if (extendHandler != null) {
                extendHandler.removeMessages(7);
                return;
            }
            return;
        }
        ExtendHandler extendHandler2 = this.l;
        if (extendHandler2 != null) {
            extendHandler2.removeMessages(15);
            this.l.sendEmptyMessage(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        if (this.m.t().getType() == 0) {
            jvk.b().a(str, i, i2, this.m.r(), this.m.h());
        } else {
            jvk.b().a(str, i, i2, this.m.r(), "");
        }
    }

    private void g() {
        this.t = null;
        this.u = 0;
        this.s = 0;
        this.c.clear();
    }
}
