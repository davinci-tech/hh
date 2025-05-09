package com.autonavi.base.amap.mapcore.maploader;

import android.content.Context;
import com.amap.api.col.p0003sl.cz;
import com.amap.api.col.p0003sl.ds;
import com.amap.api.col.p0003sl.dv;
import com.amap.api.col.p0003sl.hn;
import com.amap.api.col.p0003sl.hq;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.col.p0003sl.hz;
import com.amap.api.col.p0003sl.iv;
import com.amap.api.col.p0003sl.jv;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.ae.gmap.GLMapEngine;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes2.dex */
public class AMapLoader implements jv.a {
    private static final int GET_METHOD = 0;
    private static final String NETWORK_RESPONSE_CODE_STRING = "网络异常状态码：";
    private jv downloadManager;
    ADataRequestParam mDataRequestParam;
    private int mEngineID;
    GLMapEngine mGLMapEngine;
    private volatile boolean isCanceled = false;
    private long requestMapDataTimestamp = 0;
    private long requestMapDataPackageSize = 0;
    private boolean mRequestCancel = false;

    public static class ADataRequestParam {
        public byte[] enCodeString;
        public long handler;
        public int nCompress;
        public int nRequestType;
        public String requestBaseUrl;
        public String requestUrl;
    }

    /* loaded from: classes8.dex */
    public static class AMapGridDownloadRequest extends cz {
        private final Context mContext;
        private byte[] postEntityBytes;
        private String sUrl;
        private String userAgent;

        @Override // com.amap.api.col.p0003sl.cz, com.amap.api.col.p0003sl.ka
        public Map<String, String> getParams() {
            return null;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public boolean isSupportIPV6() {
            return true;
        }

        public AMapGridDownloadRequest(Context context, String str, String str2) {
            this.mContext = context;
            this.sUrl = str;
            this.userAgent = str2;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public Map<String, String> getRequestHead() {
            hz a2 = dv.a();
            String b = a2 != null ? a2.b() : null;
            String f = hn.f(this.mContext);
            try {
                f = URLEncoder.encode(f, "UTF-8");
            } catch (Throwable unused) {
            }
            Hashtable hashtable = new Hashtable(16);
            hashtable.put("User-Agent", this.userAgent);
            hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", b, "3dmap"));
            hashtable.put("x-INFO", hq.a(this.mContext));
            hashtable.put(MedalConstants.EVENT_KEY, f);
            hashtable.put("logversion", "2.1");
            return hashtable;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public String getURL() {
            return this.sUrl;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public String getIPV6URL() {
            return dv.a(getURL());
        }

        public void setPostEntityBytes(byte[] bArr) {
            this.postEntityBytes = bArr;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public byte[] getEntityBytes() {
            return this.postEntityBytes;
        }
    }

    @Override // com.amap.api.col.3sl.jv.a
    public void onDownload(byte[] bArr, long j) {
        GLMapEngine gLMapEngine;
        ADataRequestParam aDataRequestParam;
        if (bArr == null || (gLMapEngine = this.mGLMapEngine) == null || (aDataRequestParam = this.mDataRequestParam) == null) {
            return;
        }
        gLMapEngine.receiveNetData(this.mEngineID, aDataRequestParam.handler, bArr, bArr.length);
    }

    @Override // com.amap.api.col.3sl.jv.a
    public void onStop() {
        ADataRequestParam aDataRequestParam;
        GLMapEngine gLMapEngine = this.mGLMapEngine;
        if (gLMapEngine != null && (aDataRequestParam = this.mDataRequestParam) != null) {
            gLMapEngine.netStop(this.mEngineID, aDataRequestParam.handler, -1);
        }
        staticNetworkPerformance();
    }

    @Override // com.amap.api.col.3sl.jv.a
    public void onFinish() {
        ADataRequestParam aDataRequestParam;
        GLMapEngine gLMapEngine = this.mGLMapEngine;
        if (gLMapEngine != null && (aDataRequestParam = this.mDataRequestParam) != null) {
            gLMapEngine.finishDownLoad(this.mEngineID, aDataRequestParam.handler);
        }
        staticNetworkPerformance();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003b, code lost:
    
        if (r0 != null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0070, code lost:
    
        com.amap.api.col.p0003sl.iv.c(r10, "AMapLoader", "download onException");
        com.amap.api.col.p0003sl.dx.b(com.amap.api.col.p0003sl.dw.e, "map loader exception " + r10.getMessage());
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x008e, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
    
        com.amap.api.col.p0003sl.ds.a(r0.getContext(), r9.mGLMapEngine.hashCode(), !r9.mGLMapEngine.isNetworkConnected() ? 1 : 0, getNetworkFailedReason(r10.getMessage()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0051, code lost:
    
        if (r0 == null) goto L24;
     */
    @Override // com.amap.api.col.3sl.jv.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onException(java.lang.Throwable r10) {
        /*
            r9 = this;
            java.lang.String r0 = "UTF-8"
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L3e
            java.lang.String r2 = r10.getMessage()     // Catch: java.lang.Throwable -> L3e
            byte[] r2 = r2.getBytes(r0)     // Catch: java.lang.Throwable -> L3e
            r1.<init>(r2, r0)     // Catch: java.lang.Throwable -> L3e
            boolean r0 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L3e
            r2 = -1
            if (r0 != 0) goto L28
            java.lang.String r0 = "网络异常状态码："
            int r0 = r1.indexOf(r0)     // Catch: java.lang.Throwable -> L3e
            if (r0 == r2) goto L28
            int r0 = r0 + 8
            java.lang.String r0 = r1.substring(r0)     // Catch: java.lang.Throwable -> L3e
            int r2 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L3e
        L28:
            r8 = r2
            com.autonavi.base.ae.gmap.GLMapEngine r3 = r9.mGLMapEngine
            if (r3 == 0) goto L39
            com.autonavi.base.amap.mapcore.maploader.AMapLoader$ADataRequestParam r0 = r9.mDataRequestParam
            if (r0 == 0) goto L39
            int r4 = r9.mEngineID
            long r5 = r0.handler
            r7 = -1
            r3.netError(r4, r5, r7, r8)
        L39:
            com.autonavi.base.ae.gmap.GLMapEngine r0 = r9.mGLMapEngine
            if (r0 == 0) goto L70
            goto L53
        L3e:
            com.autonavi.base.ae.gmap.GLMapEngine r1 = r9.mGLMapEngine
            if (r1 == 0) goto L4f
            com.autonavi.base.amap.mapcore.maploader.AMapLoader$ADataRequestParam r0 = r9.mDataRequestParam
            if (r0 == 0) goto L4f
            int r2 = r9.mEngineID
            long r3 = r0.handler
            r5 = -1
            r6 = -1
            r1.netError(r2, r3, r5, r6)
        L4f:
            com.autonavi.base.ae.gmap.GLMapEngine r0 = r9.mGLMapEngine
            if (r0 == 0) goto L70
        L53:
            android.content.Context r0 = r0.getContext()
            com.autonavi.base.ae.gmap.GLMapEngine r1 = r9.mGLMapEngine
            int r1 = r1.hashCode()
            com.autonavi.base.ae.gmap.GLMapEngine r2 = r9.mGLMapEngine
            boolean r2 = r2.isNetworkConnected()
            r2 = r2 ^ 1
            java.lang.String r3 = r10.getMessage()
            java.lang.String r3 = r9.getNetworkFailedReason(r3)
            com.amap.api.col.p0003sl.ds.a(r0, r1, r2, r3)
        L70:
            java.lang.String r0 = "AMapLoader"
            java.lang.String r1 = "download onException"
            com.amap.api.col.p0003sl.iv.c(r10, r0, r1)
            java.lang.String r0 = com.amap.api.col.p0003sl.dw.e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "map loader exception "
            r1.<init>(r2)
            java.lang.String r10 = r10.getMessage()
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            com.amap.api.col.p0003sl.dx.b(r0, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.base.amap.mapcore.maploader.AMapLoader.onException(java.lang.Throwable):void");
    }

    public AMapLoader(int i, GLMapEngine gLMapEngine, ADataRequestParam aDataRequestParam) {
        this.mEngineID = 0;
        this.mDataRequestParam = aDataRequestParam;
        this.mEngineID = i;
        this.mGLMapEngine = gLMapEngine;
    }

    public void doRequest() {
        if (hw.a(this.mGLMapEngine.getContext(), dv.a()).f1161a == hw.c.SuccessCode && !this.mRequestCancel) {
            String str = this.mDataRequestParam.requestBaseUrl;
            String str2 = this.mDataRequestParam.requestUrl;
            if (!str.endsWith("?")) {
                str = str + "?";
            }
            String requestParams = getRequestParams(str2.replaceAll(";", getEncodeRequestParams(";").toString()), str != null && str.contains("http://m5.amap.com/"), this.mDataRequestParam.nRequestType);
            StringBuffer stringBuffer = new StringBuffer();
            if (this.mDataRequestParam.nRequestType == 0) {
                stringBuffer.append(requestParams);
                stringBuffer.append("&csid=" + UUID.randomUUID().toString());
            } else {
                stringBuffer.append("csid=" + UUID.randomUUID().toString());
            }
            try {
                AMapGridDownloadRequest aMapGridDownloadRequest = new AMapGridDownloadRequest(this.mGLMapEngine.getContext(), str + generateQueryString(this.mGLMapEngine.getContext(), stringBuffer.toString()), this.mGLMapEngine.getUserAgent());
                aMapGridDownloadRequest.setConnectionTimeout(30000);
                aMapGridDownloadRequest.setSoTimeout(30000);
                if (this.mDataRequestParam.nRequestType != 0) {
                    aMapGridDownloadRequest.setPostEntityBytes(requestParams.getBytes("UTF-8"));
                }
                this.requestMapDataTimestamp = System.currentTimeMillis();
                this.requestMapDataPackageSize = aMapGridDownloadRequest.getEntityBytes() == null ? 0L : aMapGridDownloadRequest.getEntityBytes().length;
                jv jvVar = new jv(aMapGridDownloadRequest, 0L, -1L, MapsInitializer.getProtocol() == 2);
                this.downloadManager = jvVar;
                jvVar.a(this);
            } catch (Throwable th) {
                try {
                    onException(th);
                } finally {
                    doCancel();
                }
            }
        }
    }

    public void doCancel() {
        this.mRequestCancel = true;
        if (this.downloadManager == null || this.isCanceled) {
            return;
        }
        synchronized (this.downloadManager) {
            try {
                this.isCanceled = true;
                this.downloadManager.a();
                this.mGLMapEngine.setMapLoaderToTask(this.mEngineID, this.mDataRequestParam.handler, null);
            } finally {
            }
        }
    }

    public void doCancelAndNotify() {
        doCancel();
        onCancel();
    }

    private void onCancel() {
        ADataRequestParam aDataRequestParam;
        GLMapEngine gLMapEngine = this.mGLMapEngine;
        if (gLMapEngine == null || (aDataRequestParam = this.mDataRequestParam) == null) {
            return;
        }
        gLMapEngine.netCancel(this.mEngineID, aDataRequestParam.handler, -1);
    }

    private String getEncodeRequestParams(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String getRequestParams(String str, boolean z, int i) {
        StringBuffer stringBuffer = new StringBuffer(str);
        if (z) {
            stringBuffer.append("&channel=amap7&div=GNaviMap");
        } else {
            stringBuffer.append("&channel=amapapi&div=GNaviMap");
        }
        return stringBuffer.toString();
    }

    private String generateQueryString(Context context, String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        String f = hn.f(this.mGLMapEngine.getContext());
        try {
            f = URLEncoder.encode(f, "UTF-8");
        } catch (Throwable unused) {
        }
        stringBuffer.append("&key=").append(f);
        String sortReEncoderParams = sortReEncoderParams(stringBuffer.toString());
        String a2 = hq.a();
        stringBuffer.append("&ts=".concat(String.valueOf(a2)));
        stringBuffer.append("&scode=" + hq.a(context, a2, sortReEncoderParams));
        stringBuffer.append("&dip=16300");
        return stringBuffer.toString();
    }

    private String sortReEncoderParams(String str) {
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(strReEncoder(str2));
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.length() > 1 ? (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1) : str;
    }

    private String strReEncoder(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            iv.c(e, "AbstractProtocalHandler", "strReEncoder");
            return "";
        } catch (Exception e2) {
            iv.c(e2, "AbstractProtocalHandler", "strReEncoderException");
            return "";
        }
    }

    private String getNetworkFailedReason(String str) {
        return !this.mGLMapEngine.isNetworkConnected() ? "无网络" : str;
    }

    private void staticNetworkPerformance() {
        GLMapEngine gLMapEngine = this.mGLMapEngine;
        if (gLMapEngine != null) {
            ds.a(gLMapEngine.getContext(), this.mGLMapEngine.hashCode(), System.currentTimeMillis() - this.requestMapDataTimestamp, this.requestMapDataPackageSize);
        }
    }
}
