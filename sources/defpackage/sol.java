package defpackage;

import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.TransferFileQueueManager;
import com.huawei.unitedevice.hwcommonfilemgr.bean.Priority;
import java.io.BufferedOutputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes7.dex */
public class sol {

    /* renamed from: a, reason: collision with root package name */
    private int f17180a;
    private BufferedOutputStream aa;
    private boolean ab;
    private String ae;
    private String af;
    private ParcelFileDescriptor ag;
    private Priority ah;
    private String ai;
    private long al;
    private long an;
    private TransferFileReqType ap;
    private String aq;
    private TransferFileQueueManager.TaskMode au;
    private long av;
    private List<Integer> b;
    private int d;
    private boolean g;
    private int i;
    private ByteBuffer j;
    private UniteDevice k;
    private String l;
    private String m;
    private String n;
    private int p;
    private int q;
    private int r;
    private int u;
    private int v;
    private int w;
    private String x;
    private int z;
    private String t = "";
    private boolean ac = true;
    private String ad = "";
    private long aj = -1;
    private int ak = 0;
    private int am = 0;
    private int o = 0;
    private ITransferFileCallback h = null;
    private IResultAIDLCallback s = null;
    private ITransferFileCallback y = null;
    private boolean e = false;
    private boolean f = true;
    private int ax = -1;
    private int ao = 0;
    private int as = 0;
    private int at = 0;
    private int ar = 0;
    private int aw = 1;
    private int c = 1;

    public int ar() {
        return this.aw;
    }

    public void r(int i) {
        this.aw = i;
    }

    public boolean at() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public ITransferFileCallback d() {
        return this.h;
    }

    public void b(ITransferFileCallback iTransferFileCallback) {
        this.h = iTransferFileCallback;
    }

    public int v() {
        return this.v;
    }

    public void i(int i) {
        this.v = i;
    }

    public long ap() {
        return this.aj;
    }

    public void e(long j) {
        this.aj = j;
    }

    public IResultAIDLCallback n() {
        return this.s;
    }

    public void b(IResultAIDLCallback iResultAIDLCallback) {
        this.s = iResultAIDLCallback;
    }

    public ITransferFileCallback p() {
        return this.y;
    }

    public void a(ITransferFileCallback iTransferFileCallback) {
        this.y = iTransferFileCallback;
    }

    public TransferFileReqType ab() {
        return this.ap;
    }

    public void a(TransferFileReqType transferFileReqType) {
        this.ap = transferFileReqType;
    }

    public List<Integer> a() {
        return this.b;
    }

    public void a(List<Integer> list) {
        this.b = list;
    }

    public int l() {
        return this.r;
    }

    public void c(int i) {
        this.r = i;
    }

    public String m() {
        return this.t;
    }

    public void a(String str) {
        this.t = str;
    }

    public int u() {
        return this.z;
    }

    public void m(int i) {
        this.z = i;
    }

    public String s() {
        return this.x;
    }

    public void e(String str) {
        this.x = str;
    }

    public boolean aw() {
        return this.ac;
    }

    public void b(boolean z) {
        this.ac = z;
    }

    public String x() {
        return this.ad;
    }

    public void i(String str) {
        this.ad = str;
    }

    public int t() {
        return this.p;
    }

    public void g(int i) {
        this.p = i;
    }

    public int o() {
        return this.q;
    }

    public void j(int i) {
        this.q = i;
    }

    public int r() {
        return this.u;
    }

    public void h(int i) {
        this.u = i;
    }

    public int q() {
        return this.w;
    }

    public void f(int i) {
        this.w = i;
    }

    public ByteBuffer b() {
        return this.j;
    }

    public void e(ByteBuffer byteBuffer) {
        this.j = byteBuffer;
    }

    public int aq() {
        return this.ak;
    }

    public void q(int i) {
        this.ak = i;
    }

    public int ak() {
        return this.am;
    }

    public void p(int i) {
        this.am = i;
    }

    public int k() {
        return this.o;
    }

    public void d(int i) {
        this.o = i;
    }

    public String ae() {
        return this.ae;
    }

    public void h(String str) {
        this.ae = str;
    }

    public String g() {
        return this.n;
    }

    public void d(String str) {
        this.n = str;
    }

    public String j() {
        return this.m;
    }

    public void c(String str) {
        this.m = str;
    }

    public int c() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    public void b(Priority priority) {
        this.ah = priority;
    }

    public long aj() {
        return this.al;
    }

    public void a(long j) {
        this.al = j;
    }

    public long am() {
        return this.an;
    }

    public void d(long j) {
        this.an = j;
    }

    public ParcelFileDescriptor ejT_() {
        return this.ag;
    }

    public void ejU_(ParcelFileDescriptor parcelFileDescriptor) {
        this.ag = parcelFileDescriptor;
    }

    public String ai() {
        return this.ai;
    }

    public void g(String str) {
        this.ai = str;
    }

    public String ah() {
        return this.af;
    }

    public void f(String str) {
        this.af = str;
    }

    public String h() {
        return this.l;
    }

    public void b(String str) {
        this.l = str;
    }

    public boolean ax() {
        return this.ab;
    }

    public void d(boolean z) {
        this.ab = z;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof sol)) {
            return false;
        }
        sol solVar = (sol) obj;
        return u() == solVar.u() && TextUtils.equals(ae(), solVar.ae()) && TextUtils.equals(g(), solVar.g()) && TextUtils.equals(m(), solVar.m()) && f() == solVar.f();
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean ao() {
        return this.e;
    }

    public void c(boolean z) {
        this.e = z;
    }

    public String ac() {
        return this.aq;
    }

    public void j(String str) {
        this.aq = str;
    }

    public long af() {
        return this.av;
    }

    public void c(long j) {
        this.av = j;
    }

    public void e(boolean z) {
        this.f = z;
    }

    public UniteDevice i() {
        return this.k;
    }

    public void d(UniteDevice uniteDevice) {
        this.k = uniteDevice;
    }

    public int y() {
        return this.d;
    }

    public void k(int i) {
        this.d = i;
    }

    public int f() {
        return this.f17180a;
    }

    public void b(int i) {
        this.f17180a = i;
    }

    public int as() {
        return this.ax;
    }

    public void t(int i) {
        this.ax = i;
    }

    public int ad() {
        return this.ao;
    }

    public void l(int i) {
        this.ao = i;
    }

    public TransferFileQueueManager.TaskMode an() {
        return this.au;
    }

    public void e(TransferFileQueueManager.TaskMode taskMode) {
        this.au = taskMode;
    }

    public int w() {
        return this.as;
    }

    public void o(int i) {
        this.as = i;
    }

    public int al() {
        return this.at;
    }

    public void s(int i) {
        this.at = i;
    }

    public BufferedOutputStream z() {
        return this.aa;
    }

    public void b(BufferedOutputStream bufferedOutputStream) {
        this.aa = bufferedOutputStream;
    }

    public int ag() {
        return this.ar;
    }

    public void n(int i) {
        this.ar = i;
    }

    public int e() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }
}
