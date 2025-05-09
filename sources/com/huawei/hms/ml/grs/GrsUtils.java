package com.huawei.hms.ml.grs;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.framework.network.grs.IQueryUrlCallBack;
import com.huawei.hms.ml.common.utils.SmartLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class GrsUtils {
    private static final String BACKUP_URL_KEY = "ROOTBACK";
    private static final String HTTPS_HEADER = "https://";
    private static final String HTTP_HEADER = "http://";
    private static final String MAIN_URL_KEY = "ROOT";
    private static final String TAG = "GRS";
    private static GrsClient grsClient;
    private static GrsListener grsListener;
    private static GrsClient grsVisionSearchClient;
    private static GrsBaseInfo hmsGrsInfo;
    private static GrsBaseInfo hmsVisionGrsInfo;
    private static Context mContext;

    public static void initGrsClient(Context context) {
        synchronized (GrsUtils.class) {
            if (grsClient != null) {
                return;
            }
            mContext = context;
            GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
            hmsGrsInfo = grsBaseInfo;
            grsBaseInfo.setSerCountry(GrsApp.getInstance().getIssueCountryCode(mContext));
            grsClient = new GrsClient(mContext, hmsGrsInfo);
        }
    }

    public static void initGrsVisionSearchClient(Context context) {
        synchronized (GrsUtils.class) {
            if (grsVisionSearchClient != null) {
                return;
            }
            mContext = context;
            GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
            hmsVisionGrsInfo = grsBaseInfo;
            grsBaseInfo.setSerCountry(GrsApp.getInstance().getIssueCountryCode(mContext));
            grsVisionSearchClient = new GrsClient(mContext, hmsVisionGrsInfo);
        }
    }

    public static GrsClient initGrsVisionSearchClientWithCountry(Context context, String str) {
        synchronized (GrsUtils.class) {
            mContext = context;
            if (TextUtils.isEmpty(str)) {
                SmartLog.e(TAG, "grsClient not initialized!");
                return null;
            }
            GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
            hmsGrsInfo = grsBaseInfo;
            grsBaseInfo.setSerCountry(str.toUpperCase(Locale.ENGLISH));
            return new GrsClient(mContext, hmsGrsInfo);
        }
    }

    public static String getUrl(String str) {
        GrsClient grsClient2 = grsClient;
        if (grsClient2 == null) {
            SmartLog.e(TAG, "grsClient not initialized!");
            return "";
        }
        String synGetGrsUrl = grsClient2.synGetGrsUrl(BuildConfig.GRS_MODE, str);
        if (TextUtils.isEmpty(synGetGrsUrl)) {
            SmartLog.e(TAG, "grs get url is empty");
            if (hmsGrsInfo.getSerCountry() != null) {
                SmartLog.e(TAG, "grs get url is empty, countryCode = " + hmsGrsInfo.getSerCountry());
            }
            return "";
        }
        SmartLog.i(TAG, "grs get url success:   countryCode = " + hmsGrsInfo.getSerCountry());
        return synGetGrsUrl;
    }

    public static void setGrsListener(GrsListener grsListener2) {
        grsListener = grsListener2;
    }

    public static void getAsrUrls(Context context, boolean z) {
        final ArrayList arrayList = new ArrayList();
        SmartLog.i("getGrsUrl", "getGrsUrlList: getGrsUrlList");
        if (!z && grsClient == null) {
            SmartLog.i("getGrsUrl", "getGrsUrlList: grsClient == null");
            initGrsClient(context);
        } else if (z && grsVisionSearchClient == null) {
            SmartLog.i("getGrsUrl", "getGrsUrlList: grsClient != null");
            initGrsVisionSearchClient(context);
        }
        final GrsClient grsClient2 = z ? grsVisionSearchClient : grsClient;
        SmartLog.i("getGrsUrl", "getGrsUrlList: synGetGrsUrl");
        grsClient2.ayncGetGrsUrl(BuildConfig.GRS_MODE, "ROOT", new IQueryUrlCallBack() { // from class: com.huawei.hms.ml.grs.GrsUtils.1
            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackSuccess(String str) {
                SmartLog.d(GrsUtils.TAG, "onCallBackSuccess() called with: url = [" + str + "]");
                if (!TextUtils.isEmpty(str)) {
                    SmartLog.i("getGrsUrl", "getGrsUrlList: url!=null");
                    arrayList.add(str);
                } else {
                    SmartLog.i("getGrsUrl", "getGrsUrlList: url==null");
                    SmartLog.e(GrsUtils.TAG, "grs get url is empty");
                    if (GrsUtils.hmsGrsInfo.getSerCountry() != null) {
                        SmartLog.e(GrsUtils.TAG, "grs get url is empty, countryCode = " + GrsUtils.hmsGrsInfo.getSerCountry());
                    }
                    arrayList.add("");
                }
                GrsUtils.getAsrBackUrls(arrayList, grsClient2);
            }

            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackFail(int i) {
                SmartLog.d(GrsUtils.TAG, "onCallBackFail() called with: i = [" + i + "]");
                GrsUtils.getAsrBackUrls(arrayList, grsClient2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getAsrBackUrls(final List<String> list, GrsClient grsClient2) {
        SmartLog.d("getGrsUrl", "getAsrBackUrls() called with: list = [" + list + "], grs = [" + grsClient2 + "]");
        grsClient2.ayncGetGrsUrl(BuildConfig.GRS_MODE, BACKUP_URL_KEY, new IQueryUrlCallBack() { // from class: com.huawei.hms.ml.grs.GrsUtils.2
            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackSuccess(String str) {
                SmartLog.d(GrsUtils.TAG, "onCallBackSuccess() called with: urlBackup = [" + str + "]");
                if (!TextUtils.isEmpty(str)) {
                    SmartLog.i("getGrsUrl", "getGrsUrlList: urlBackup!=null");
                    list.add(str);
                } else {
                    SmartLog.i("getGrsUrl", "getGrsUrlList: urlBackup==null");
                    SmartLog.e(GrsUtils.TAG, "grs get url is empty");
                    if (GrsUtils.hmsGrsInfo.getSerCountry() != null) {
                        SmartLog.e(GrsUtils.TAG, "grs get url is empty, countryCode = " + GrsUtils.hmsGrsInfo.getSerCountry());
                    }
                    list.add("");
                }
                if (GrsUtils.grsListener != null) {
                    GrsUtils.grsListener.onAsynGrsUrls(list);
                }
            }

            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackFail(int i) {
                SmartLog.d(GrsUtils.TAG, "onCallBackFail() called with: i = [" + i + "]");
                if (GrsUtils.grsListener != null) {
                    GrsUtils.grsListener.onAsynGrsUrls(list);
                }
            }
        });
    }

    public static List<String> getUrls(Context context, boolean z) {
        SmartLog.d("getGrsUrl", "getUrls() called with: context = [" + context + "], isVisionSearch = [" + z + "]");
        ArrayList arrayList = new ArrayList();
        if (!z && grsClient == null) {
            initGrsClient(context);
        } else if (z && grsVisionSearchClient == null) {
            initGrsVisionSearchClient(context);
        }
        GrsClient grsClient2 = z ? grsVisionSearchClient : grsClient;
        String synGetGrsUrl = grsClient2.synGetGrsUrl(BuildConfig.GRS_MODE, "ROOT");
        if (!TextUtils.isEmpty(synGetGrsUrl)) {
            arrayList.add(synGetGrsUrl);
        } else {
            SmartLog.e(TAG, "grs get url is empty");
            if (hmsGrsInfo.getSerCountry() != null) {
                SmartLog.e(TAG, "grs get url is empty, countryCode = " + hmsGrsInfo.getSerCountry());
            }
            arrayList.add("");
        }
        String synGetGrsUrl2 = grsClient2.synGetGrsUrl(BuildConfig.GRS_MODE, BACKUP_URL_KEY);
        if (!TextUtils.isEmpty(synGetGrsUrl2)) {
            arrayList.add(synGetGrsUrl2);
        } else {
            SmartLog.e(TAG, "grs get url is empty");
            if (hmsGrsInfo.getSerCountry() != null) {
                SmartLog.e(TAG, "grs get url is empty, countryCode = " + hmsGrsInfo.getSerCountry());
            }
            arrayList.add("");
        }
        return arrayList;
    }

    public static List<String> getVisionSearchUrls(GrsClient grsClient2) {
        ArrayList arrayList = new ArrayList();
        if (grsClient2 == null) {
            return arrayList;
        }
        String synGetGrsUrl = grsClient2.synGetGrsUrl(BuildConfig.GRS_MODE, "ROOT");
        if (!TextUtils.isEmpty(synGetGrsUrl)) {
            arrayList.add(synGetGrsUrl);
        } else {
            SmartLog.e(TAG, "grs get url is empty");
            if (hmsGrsInfo.getSerCountry() != null) {
                SmartLog.e(TAG, "grs get url is empty, countryCode = " + hmsGrsInfo.getSerCountry());
            }
            arrayList.add("");
        }
        String synGetGrsUrl2 = grsClient2.synGetGrsUrl(BuildConfig.GRS_MODE, BACKUP_URL_KEY);
        if (!TextUtils.isEmpty(synGetGrsUrl2)) {
            arrayList.add(synGetGrsUrl2);
        } else {
            SmartLog.e(TAG, "grs get url is empty");
            if (hmsGrsInfo.getSerCountry() != null) {
                SmartLog.e(TAG, "grs get url is empty, countryCode = " + hmsGrsInfo.getSerCountry());
            }
            arrayList.add("");
        }
        return arrayList;
    }

    public static void getAsrVisionSearchUrls(final GrsClient grsClient2) {
        final ArrayList arrayList = new ArrayList();
        GrsListener grsListener2 = grsListener;
        if (grsListener2 == null) {
            throw new NullPointerException("grsListener is null");
        }
        if (grsClient2 == null) {
            grsListener2.onAsynGrsUrls(arrayList);
        }
        grsClient2.ayncGetGrsUrl(BuildConfig.GRS_MODE, "ROOT", new IQueryUrlCallBack() { // from class: com.huawei.hms.ml.grs.GrsUtils.3
            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                } else {
                    SmartLog.e(GrsUtils.TAG, "grs get url is empty");
                    if (GrsUtils.hmsGrsInfo.getSerCountry() != null) {
                        SmartLog.e(GrsUtils.TAG, "grs get url is empty, countryCode = " + GrsUtils.hmsGrsInfo.getSerCountry());
                    }
                    arrayList.add("");
                }
                GrsUtils.getAsrBackVisionSearchUrls(grsClient2, arrayList);
            }

            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackFail(int i) {
                GrsUtils.getAsrBackVisionSearchUrls(grsClient2, arrayList);
                SmartLog.e(GrsUtils.TAG, "onCallBackFail() called with: failCode = [" + i + "]");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getAsrBackVisionSearchUrls(GrsClient grsClient2, final List<String> list) {
        grsClient2.ayncGetGrsUrl(BuildConfig.GRS_MODE, BACKUP_URL_KEY, new IQueryUrlCallBack() { // from class: com.huawei.hms.ml.grs.GrsUtils.4
            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    list.add(str);
                } else {
                    SmartLog.e(GrsUtils.TAG, "grs get url is empty");
                    if (GrsUtils.hmsGrsInfo.getSerCountry() != null) {
                        SmartLog.e(GrsUtils.TAG, "grs get url is empty, countryCode = " + GrsUtils.hmsGrsInfo.getSerCountry());
                    }
                    list.add("");
                }
                GrsUtils.grsListener.onAsynGrsUrls(list);
            }

            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackFail(int i) {
                SmartLog.e(GrsUtils.TAG, "onCallBackFail() called with: failCode = [" + i + "]");
                GrsUtils.grsListener.onAsynGrsUrls(list);
            }
        });
    }

    public static List<String> addHttpsHeaders(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() != 0) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String lowerCase = it.next().trim().toLowerCase(Locale.ENGLISH);
                if (!lowerCase.startsWith("http://") && !lowerCase.startsWith("https://")) {
                    lowerCase = "https://" + lowerCase;
                }
                if (!lowerCase.endsWith("/")) {
                    lowerCase = lowerCase + "/";
                }
                SmartLog.i(TAG, "GrsClient grs url is: " + lowerCase);
                arrayList.add(lowerCase);
            }
        }
        return arrayList;
    }
}
