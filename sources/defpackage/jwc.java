package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ErrorCode;
import com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpProxyInterface;
import com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpRequestInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.bmt;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jwc {

    /* renamed from: a, reason: collision with root package name */
    private ExtendHandler f14144a;
    private final DataReceiveCallback b;
    private final HttpProxyInterface c;
    private LinkedList<Long> d;
    private final Object e;
    private int i;
    private final Map<ResultCallback<byte[]>, Object> j;

    /* synthetic */ void d(int i, final DeviceInfo deviceInfo, cvr cvrVar) {
        if (deviceInfo == null || StringUtils.g(deviceInfo.getDeviceIdentify())) {
            LogUtil.h("HttpProxyManager", "deviceInfo or deviceIdentify is null");
            return;
        }
        if (cvrVar == null) {
            LogUtil.h("HttpProxyManager", "message is null");
            return;
        }
        if (cvrVar instanceof cvp) {
            final cvp cvpVar = (cvp) cvrVar;
            if (d(cvpVar)) {
                LogUtil.a("HttpProxyManager", "start parseReceiveData");
                this.f14144a.postTask(new Runnable() { // from class: jwl
                    @Override // java.lang.Runnable
                    public final void run() {
                        jwc.this.b(deviceInfo, cvpVar);
                    }
                });
            }
        }
    }

    /* synthetic */ void b(DeviceInfo deviceInfo, cvp cvpVar) {
        e(deviceInfo, cvpVar.c());
    }

    private jwc() {
        this.e = new Object();
        this.c = new jwd();
        this.j = new ConcurrentHashMap();
        this.d = new LinkedList<>();
        this.i = 0;
        this.b = new DataReceiveCallback() { // from class: jwh
            @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
            public final void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
                jwc.this.d(i, deviceInfo, cvrVar);
            }
        };
        this.f14144a = HandlerCenter.e("HttpProxyManager");
    }

    public static jwc a() {
        return a.b;
    }

    public void c(DeviceInfo deviceInfo) {
        h();
    }

    public void d(DeviceInfo deviceInfo) {
        g();
    }

    private void h() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.httpProxy", this.b);
    }

    private void g() {
        cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.httpProxy");
    }

    private boolean d(cvp cvpVar) {
        if (cvpVar.getCommandId() != 2) {
            LogUtil.h("HttpProxyManager", "command not match.");
            return false;
        }
        if (cvpVar.e() != 800100011) {
            LogUtil.h("HttpProxyManager", "eventId not match.");
            return false;
        }
        byte[] c = cvpVar.c();
        if (c != null && c.length != 0) {
            return true;
        }
        LogUtil.h("HttpProxyManager", "sampleEvent data is empty.");
        return false;
    }

    private void e(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("HttpProxyManager", "enter parseReceiveData.");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HttpProxyManager");
        if (deviceList != null && deviceList.size() > 0) {
            deviceInfo = deviceList.get(0);
        }
        HttpRequestInfo httpRequestInfo = new HttpRequestInfo();
        httpRequestInfo.a(deviceInfo);
        try {
            bmt.b b = new bmt().b(bArr, 0);
            boolean z = true;
            httpRequestInfo.c(b.b((byte) 1, -1L));
            httpRequestInfo.c(b.b((byte) 2, ""));
            httpRequestInfo.b(b.b((byte) 3, ""));
            httpRequestInfo.d(b.b((byte) 4, ""));
            httpRequestInfo.c(b.a((byte) 5, -1));
            httpRequestInfo.e(b.b((byte) 6, ""));
            httpRequestInfo.a(b.b((byte) 7, ""));
            httpRequestInfo.b(HttpRequestInfo.CloudType.from(b.a((byte) 8, -1)));
            httpRequestInfo.e(b.a((byte) 9, -1));
            httpRequestInfo.d(b.a((byte) 10, 0));
            ReleaseLogUtil.b("R_HttpProxyManager", "Receive httpProxy request from device, requestId:", Long.valueOf(httpRequestInfo.k()), ", request method is: ", Integer.valueOf(httpRequestInfo.h()));
            StringBuilder sb = new StringBuilder("timestamp: ");
            sb.append(System.currentTimeMillis());
            sb.append(", isForeground: ");
            sb.append(httpRequestInfo.j() == 1);
            sb.append(", Platform: Android");
            sqo.u(sb.toString());
            LogUtil.a("HttpProxyManager", "SampleEvent send by device: ", httpRequestInfo.toString());
            Object[] objArr = new Object[1];
            StringBuilder sb2 = new StringBuilder("BI info: timestamp: ");
            sb2.append(System.currentTimeMillis());
            sb2.append(", isForeground: ");
            if (httpRequestInfo.j() != 1) {
                z = false;
            }
            sb2.append(z);
            sb2.append(", Platform: Android");
            objArr[0] = sb2.toString();
            LogUtil.a("HttpProxyManager", objArr);
            b(httpRequestInfo, deviceInfo);
        } catch (bmk unused) {
            LogUtil.b("HttpProxyManager", "parseReceiveData exception");
            c(httpRequestInfo.d(), b(httpRequestInfo.k(), ErrorCode.ERROR_INNER.getCode(), 0));
        }
    }

    private void b(final HttpRequestInfo httpRequestInfo, final DeviceInfo deviceInfo) {
        int b = b(httpRequestInfo);
        if (b != ErrorCode.ERROR_SUCCESS.getCode()) {
            LogUtil.h("HttpProxyManager", "invalid parameters in dispatchRequest.");
            c(httpRequestInfo.d(), b(httpRequestInfo.k(), b, 0));
            return;
        }
        final String url = new jwm(httpRequestInfo.b(), httpRequestInfo.i()).getUrl();
        if (TextUtils.isEmpty(url)) {
            LogUtil.h("HttpProxyManager", "url is empty in dispatchRequest.");
            c(httpRequestInfo.d(), b(httpRequestInfo.k(), ErrorCode.ERROR_GET_GRS_FAILED.getCode(), 0));
        } else if (httpRequestInfo.c() == 1) {
            jwg.b(httpRequestInfo.e(), deviceInfo, new com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ResultCallback() { // from class: jwk
                @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ResultCallback
                public final void onResult(String str) {
                    jwc.this.e(deviceInfo, httpRequestInfo, url, str);
                }
            });
        } else {
            d(httpRequestInfo, url, deviceInfo, httpRequestInfo.e());
        }
    }

    /* synthetic */ void e(DeviceInfo deviceInfo, HttpRequestInfo httpRequestInfo, String str, String str2) {
        if (!StringUtils.g(str2) && str2.equals("file transfer failed")) {
            LogUtil.b("HttpProxyManager", "Failed to obtain body file from device");
            c(deviceInfo, b(httpRequestInfo.k(), ErrorCode.FILE_TRANSFER_FAILED.getCode(), 0));
        } else {
            d(httpRequestInfo, str, deviceInfo, str2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpRequestInfo r10, java.lang.String r11, com.huawei.health.devicemgr.business.entity.DeviceInfo r12, java.lang.String r13) {
        /*
            r9 = this;
            com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpRequestInfo$CloudType r0 = r10.a()
            com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory r0 = defpackage.jwq.e(r0)
            r1 = 0
            java.lang.String r2 = "HttpProxyManager"
            if (r0 != 0) goto L2c
            java.lang.String r11 = "paramsFactory is null in dispatchRequest."
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r11)
            com.huawei.health.devicemgr.business.entity.DeviceInfo r11 = r10.d()
            long r12 = r10.k()
            com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ErrorCode r10 = com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ErrorCode.ERROR_INNER
            int r10 = r10.getCode()
            byte[] r10 = r9.b(r12, r10, r1)
            r9.c(r11, r10)
            return
        L2c:
            java.lang.String r3 = r10.f()
            java.util.Map r3 = r0.getHeaders(r3)
            java.util.Map r12 = r0.getCommonBody(r12)
            int r0 = r10.h()
            r4 = 3
            r5 = 1
            if (r0 == r5) goto L42
            if (r0 != r4) goto L86
        L42:
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: org.json.JSONException -> L68
            r6.<init>(r13)     // Catch: org.json.JSONException -> L68
            java.util.Set r13 = r12.keySet()     // Catch: org.json.JSONException -> L68
            java.util.Iterator r13 = r13.iterator()     // Catch: org.json.JSONException -> L68
        L4f:
            boolean r7 = r13.hasNext()     // Catch: org.json.JSONException -> L68
            if (r7 == 0) goto L63
            java.lang.Object r7 = r13.next()     // Catch: org.json.JSONException -> L68
            java.lang.String r7 = (java.lang.String) r7     // Catch: org.json.JSONException -> L68
            java.lang.Object r8 = r12.get(r7)     // Catch: org.json.JSONException -> L68
            r6.put(r7, r8)     // Catch: org.json.JSONException -> L68
            goto L4f
        L63:
            java.lang.String r13 = r6.toString()     // Catch: org.json.JSONException -> L68
            goto L88
        L68:
            java.lang.String r13 = "JSONException occurs when creating http body"
            java.lang.Object[] r13 = new java.lang.Object[]{r13}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r13)
            com.huawei.health.devicemgr.business.entity.DeviceInfo r13 = r10.d()
            long r6 = r10.k()
            com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ErrorCode r8 = com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ErrorCode.ERROR_INNER
            int r8 = r8.getCode()
            byte[] r1 = r9.b(r6, r8, r1)
            r9.c(r13, r1)
        L86:
            java.lang.String r13 = ""
        L88:
            r9.e()
            if (r0 == r5) goto Laf
            r1 = 2
            if (r0 == r1) goto Lab
            if (r0 == r4) goto La7
            r12 = 4
            if (r0 == r12) goto La3
            java.lang.String r10 = "dispatchRequest method type: "
            java.lang.Integer r11 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r11}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r10)
            goto Lb2
        La3:
            r9.b(r10, r11, r3)
            goto Lb2
        La7:
            r9.e(r10, r11, r3, r13)
            goto Lb2
        Lab:
            r9.c(r10, r11, r3, r12)
            goto Lb2
        Laf:
            r9.c(r10, r11, r3, r13)
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jwc.d(com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpRequestInfo, java.lang.String, com.huawei.health.devicemgr.business.entity.DeviceInfo, java.lang.String):void");
    }

    private int b(HttpRequestInfo httpRequestInfo) {
        if (!d(httpRequestInfo)) {
            return ErrorCode.ERROR_PARAMETER.getCode();
        }
        if (!d()) {
            LogUtil.h("HttpProxyManager", "user is no login in preCheck.");
            return ErrorCode.ERROR_USER_NO_LOGIN.getCode();
        }
        if (httpRequestInfo.o() && Utils.l()) {
            LogUtil.h("HttpProxyManager", "dispatchRequest isOverseaNoCloudVersion.");
            return ErrorCode.ERROR_NO_CLOUD_NOT_SUPPORT.getCode();
        }
        if (i()) {
            LogUtil.h("HttpProxyManager", "too many request.");
            return ErrorCode.ERROR_TOO_MANY_REQUEST.getCode();
        }
        if (c()) {
            LogUtil.h("HttpProxyManager", "channel busy");
            return ErrorCode.ERROR_CHANNEL_BUSY.getCode();
        }
        return ErrorCode.ERROR_SUCCESS.getCode();
    }

    private boolean i() {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HttpProxyManager", "Current queue size: ", Integer.valueOf(this.d.size()));
        if (!this.d.isEmpty()) {
            Iterator<Long> it = this.d.iterator();
            while (it.hasNext() && currentTimeMillis - it.next().longValue() > 60000) {
                it.remove();
            }
            if (this.d.size() >= 60) {
                return true;
            }
        }
        this.d.add(Long.valueOf(currentTimeMillis));
        LogUtil.a("HttpProxyManager", "HttpProxy queue add succeed! Current queue size is ", Integer.valueOf(this.d.size()));
        return false;
    }

    private boolean c() {
        return this.i >= 64;
    }

    private boolean d() {
        boolean isLogined;
        boolean a2 = CloudUtils.a();
        health.compact.a.LogUtil.c("HttpProxyManager", "isReconnectionDevice isAllowLogin ", Boolean.valueOf(a2));
        if (!a2) {
            return true;
        }
        if (CommonUtil.z(BaseApplication.e())) {
            String logoutFlag = ThirdLoginDataStorageUtil.getLogoutFlag();
            health.compact.a.LogUtil.c("HttpProxyManager", "isReconnectionDevice logoutFlag ", logoutFlag);
            isLogined = "false".equals(logoutFlag);
        } else {
            isLogined = LoginInit.getInstance(BaseApplication.e()).getIsLogined();
        }
        ReleaseLogUtil.b("DEVMGR_HttpProxyManager", "isReconnectionDevice isLogin ", Boolean.valueOf(isLogined));
        return isLogined;
    }

    private boolean d(HttpRequestInfo httpRequestInfo) {
        if (httpRequestInfo.k() == -1) {
            LogUtil.h("HttpProxyManager", "checkRequestIsValid requestId is empty.");
            return false;
        }
        if (StringUtils.g(httpRequestInfo.i())) {
            LogUtil.h("HttpProxyManager", "checkRequestIsValid path is empty.");
            return false;
        }
        if (StringUtils.g(httpRequestInfo.b()) && StringUtils.g(httpRequestInfo.g())) {
            LogUtil.h("HttpProxyManager", "checkRequestIsValid host and grsHostKey is empty.");
            return false;
        }
        if (httpRequestInfo.h() == -1) {
            LogUtil.h("HttpProxyManager", "checkRequestIsValid method is empty.");
            return false;
        }
        if (httpRequestInfo.j() != 1 && httpRequestInfo.j() != 0) {
            LogUtil.h("HttpProxyManager", "checkRequestIsValid isForeground is ", Integer.valueOf(httpRequestInfo.j()));
            return false;
        }
        if (httpRequestInfo.a() != null) {
            return true;
        }
        LogUtil.h("HttpProxyManager", "checkRequestIsValid cloudType is null.");
        return false;
    }

    private void c(final HttpRequestInfo httpRequestInfo, String str, Map<String, String> map, String str2) {
        ReleaseLogUtil.c("R_HttpProxyManager", "app httpProxy send post request, requestId: ", Long.valueOf(httpRequestInfo.k()));
        this.c.post(str, map, str2, new ResultCallback<byte[]>() { // from class: jwc.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(byte[] bArr) {
                jwc.this.a(httpRequestInfo, bArr);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                jwc.this.a(httpRequestInfo, th);
            }
        });
    }

    private void c(final HttpRequestInfo httpRequestInfo, String str, Map<String, String> map, Map<String, String> map2) {
        ReleaseLogUtil.c("R_HttpProxyManager", "app httpProxy send get request, requestId: ", Long.valueOf(httpRequestInfo.k()));
        this.c.get(str, map, map2, new ResultCallback<byte[]>() { // from class: jwc.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(byte[] bArr) {
                jwc.this.a(httpRequestInfo, bArr);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                jwc.this.a(httpRequestInfo, th);
            }
        });
    }

    private void e(final HttpRequestInfo httpRequestInfo, String str, Map<String, String> map, String str2) {
        ReleaseLogUtil.c("R_HttpProxyManager", "app httpProxy send put request, requestId: ", Long.valueOf(httpRequestInfo.k()));
        this.c.put(str, map, str2, new ResultCallback<byte[]>() { // from class: jwc.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(byte[] bArr) {
                jwc.this.a(httpRequestInfo, bArr);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                jwc.this.a(httpRequestInfo, th);
            }
        });
    }

    private void b(final HttpRequestInfo httpRequestInfo, String str, Map<String, String> map) {
        ReleaseLogUtil.c("R_HttpProxyManager", "app httpProxy send delete request, requestId: ", Long.valueOf(httpRequestInfo.k()));
        this.c.delete(str, map, new ResultCallback<byte[]>() { // from class: jwc.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(byte[] bArr) {
                jwc.this.a(httpRequestInfo, bArr);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                jwc.this.a(httpRequestInfo, th);
            }
        });
    }

    private byte[] b(long j, int i, int i2) {
        bms bmsVar = new bms();
        bmsVar.d(1, j).a(2, i).h(3, i2).d(4, "".getBytes()).a(5, 0);
        return bmsVar.d();
    }

    private byte[] e(long j, int i, byte[] bArr, int i2) {
        bms bmsVar = new bms();
        bmsVar.d(1, j).a(2, i).h(3, 200).d(4, bArr).a(5, i2);
        return bmsVar.d();
    }

    private byte[] a(long j, int i, String str, int i2) {
        bms bmsVar = new bms();
        bmsVar.d(1, j).a(2, i).h(3, 200).d(4, str).a(5, i2);
        return bmsVar.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final HttpRequestInfo httpRequestInfo, byte[] bArr) {
        if (httpRequestInfo == null) {
            LogUtil.b("HttpProxyManager", "requestInfo is null in handleRequestSuccess.");
            return;
        }
        if (bArr == null) {
            LogUtil.b("HttpProxyManager", "response is null in handleRequestSuccess.");
            return;
        }
        ReleaseLogUtil.c("R_HttpProxyManager", "app httpProxy request succeed, request method: ", Integer.valueOf(httpRequestInfo.h()), " requestId: ", Long.valueOf(httpRequestInfo.k()));
        int i = bArr.length > 3072 ? 1 : 0;
        if (i == 1) {
            jwg.c(httpRequestInfo.k(), bArr, new com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ResultCallback() { // from class: jwi
                @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ResultCallback
                public final void onResult(String str) {
                    jwc.this.d(httpRequestInfo, str);
                }
            });
        } else {
            c(httpRequestInfo.d(), e(httpRequestInfo.k(), ErrorCode.ERROR_SUCCESS.getCode(), bArr, i));
        }
    }

    /* synthetic */ void d(HttpRequestInfo httpRequestInfo, String str) {
        if (!StringUtils.g(str) && str.equals("create cloud response file failed")) {
            LogUtil.b("HttpProxyManager", "Failed to write cloud response file");
            c(httpRequestInfo.d(), b(httpRequestInfo.k(), ErrorCode.APP_CREATE_FILE_FAILED.getCode(), 0));
        } else {
            c(httpRequestInfo.d(), a(httpRequestInfo.k(), ErrorCode.ERROR_SUCCESS.getCode(), str, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HttpRequestInfo httpRequestInfo, Throwable th) {
        if (httpRequestInfo == null) {
            LogUtil.b("HttpProxyManager", "requestInfo is null in handleRequestFailure.");
        } else {
            if (th == null) {
                LogUtil.b("HttpProxyManager", "throwable is null in handleRequestFailure.");
                return;
            }
            int e = th instanceof lqj ? ((lqj) th).e() : 0;
            LogUtil.a("HttpProxyManager", "HttpProxy request failed");
            c(httpRequestInfo.d(), b(httpRequestInfo.k(), ErrorCode.ERROR_NETWORK_REQUEST_FAILED.getCode(), e));
        }
    }

    private void b() {
        this.i--;
    }

    private void e() {
        this.i++;
    }

    private void c(DeviceInfo deviceInfo, byte[] bArr) {
        b();
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.httpProxy");
        cvpVar.setWearPkgName("hw.wearable.httpProxy");
        cvpVar.a(800100012L);
        cvpVar.setCommandId(2);
        cvpVar.b(0);
        cvpVar.e(bArr);
        cuk.b().sendSampleEventCommand(deviceInfo, cvpVar, "HttpProxyManager");
    }

    static class a {
        private static final jwc b = new jwc();
    }
}
