package com.huawei.ui.main.stories.recommendcloud.util;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.recommendcloud.service.RecommendCloudHttpCallBack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public class HttpUtil {
    private static final int EXPECTED_BUFFER_DATA = 1024;
    private static final String TAG = "UIDV_RecommendHttpUtil";
    private static volatile ExecutorService sExecutorService;

    private HttpUtil() {
    }

    public static void doRefresh(final String str, final String str2, final RecommendCloudHttpCallBack recommendCloudHttpCallBack) {
        LogUtil.a(TAG, "pullRefresh");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h(TAG, "url or param is null");
            return;
        }
        if (sExecutorService == null || sExecutorService.isShutdown()) {
            LogUtil.a(TAG, "new executorService");
            sExecutorService = Executors.newSingleThreadExecutor();
        }
        sExecutorService.execute(new Runnable() { // from class: com.huawei.ui.main.stories.recommendcloud.util.HttpUtil.1
            @Override // java.lang.Runnable
            public void run() {
                HttpUtil.doPostReq(str, str2, recommendCloudHttpCallBack);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(23:0|1|(5:2|3|4|5|6)|(3:137|138|(23:140|142|143|145|146|147|148|10|11|12|13|14|15|17|18|19|20|21|(3:22|23|(3:25|26|27)(1:34))|35|36|37|38))|8|9|10|11|12|13|14|15|17|18|19|20|21|(4:22|23|(0)(0)|27)|35|36|37|38|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(27:0|1|2|3|4|5|6|(3:137|138|(23:140|142|143|145|146|147|148|10|11|12|13|14|15|17|18|19|20|21|(3:22|23|(3:25|26|27)(1:34))|35|36|37|38))|8|9|10|11|12|13|14|15|17|18|19|20|21|(4:22|23|(0)(0)|27)|35|36|37|38|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x01f2, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01f6, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01f4, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01e1, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01e2, code lost:
    
        r1 = r0;
        r9 = r1;
        r8 = null;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0220, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x021c, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x022a, code lost:
    
        r9 = r1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x021b, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0233, code lost:
    
        r1 = r1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x022d, code lost:
    
        r1 = r1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0222, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0224, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0228, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0226, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x020b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x020c, code lost:
    
        r1 = r0;
        r9 = r1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x024b, code lost:
    
        r1 = -1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x023f, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x023d, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0249, code lost:
    
        r9 = -1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x023c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x024d, code lost:
    
        r1 = -1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0241, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0243, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0247, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0245, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0239, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x023a, code lost:
    
        r1 = r0;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01b2, code lost:
    
        r17 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01a0, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x019d, code lost:
    
        r17 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x019c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01bb, code lost:
    
        r17 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01a2, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01a4, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01a8, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01a6, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0190, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0191, code lost:
    
        r17 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01de, code lost:
    
        r17 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0207, code lost:
    
        r1 = r17;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01db, code lost:
    
        r17 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0200, code lost:
    
        r1 = r17;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01d1, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01cd, code lost:
    
        r17 = r1;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01f9, code lost:
    
        r9 = r17;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01cc, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01d3, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01d5, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01d9, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01d7, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01c4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01c5, code lost:
    
        r1 = r0;
        r9 = r1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01fd, code lost:
    
        r17 = r1;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01ee, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01ea, code lost:
    
        r17 = r1;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01f8, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01e9, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0204, code lost:
    
        r17 = r1;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01f0, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x012b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0143 A[EDGE_INSN: B:34:0x0143->B:35:0x0143 BREAK  A[LOOP:0: B:22:0x0125->B:27:0x0134], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45, types: [int] */
    /* JADX WARN: Type inference failed for: r1v47 */
    /* JADX WARN: Type inference failed for: r1v48 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15, types: [com.huawei.ui.main.stories.recommendcloud.service.RecommendCloudHttpCallBack] */
    /* JADX WARN: Type inference failed for: r2v16, types: [com.huawei.ui.main.stories.recommendcloud.service.RecommendCloudHttpCallBack] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [int] */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void doPostReq(java.lang.String r17, java.lang.String r18, com.huawei.ui.main.stories.recommendcloud.service.RecommendCloudHttpCallBack r19) {
        /*
            Method dump skipped, instructions count: 759
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.recommendcloud.util.HttpUtil.doPostReq(java.lang.String, java.lang.String, com.huawei.ui.main.stories.recommendcloud.service.RecommendCloudHttpCallBack):void");
    }

    private static void closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b(TAG, "closeInputStream IOException:");
            }
        }
    }

    private static void closeOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
                LogUtil.b(TAG, "closeOutputStream IOException");
            }
        }
    }

    private static void closeHttpUrlConnection(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private static void closeBufferedReader(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused) {
                LogUtil.b(TAG, "closeBufferedReader IOException");
            }
        }
    }

    private static void closeOutputStreamWriter(OutputStreamWriter outputStreamWriter) {
        if (outputStreamWriter != null) {
            try {
                outputStreamWriter.close();
            } catch (IOException unused) {
                LogUtil.b(TAG, "closeOutputStreamWriter IOException");
            }
        }
    }

    private static void closePrintWriter(PrintWriter printWriter) {
        if (printWriter != null) {
            printWriter.close();
        }
    }

    private static void closeInputStreamReader(InputStreamReader inputStreamReader) {
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException unused) {
                LogUtil.b(TAG, "closeInputStreamReader IOException");
            }
        }
    }
}
