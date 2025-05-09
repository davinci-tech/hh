package com.huawei.hms.mlsdk.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.ml.common.utils.SmartLog;
import com.huawei.hms.network.NetworkKit;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class AgConnectInfo {
    private String apiKey;
    private String appMLKitGrsPolicy;
    private String applicationId;
    private String certFingerprint;
    private List<String> haCollectorUrls;
    private List<String> mlServiceUrls;
    private String packageName;
    private String projectId;
    private String region;

    /* loaded from: classes9.dex */
    static final class AgConnectKey {
        public static final String ANALYTICS_COLLECTOR_URL = "service/analytics/collector_url";
        public static final String API_KEY = "client/api_key";
        public static final String APPLICATION_ID = "client/app_id";
        public static final String APP_MLKIT_GRS_POLICY = "processing_location_policy/mlkit";
        public static final String ML_SERVICE_URL = "service/ml/mlservice_url";
        public static final String PACKAGE_NAME = "client/package_name";
        public static final String PROJECT_ID = "client/project_id";
        public static final String REGION = "region";

        private AgConnectKey() {
        }
    }

    public AgConnectInfo(Context context) {
        this.mlServiceUrls = new ArrayList();
        this.haCollectorUrls = new ArrayList();
        context = context == null ? AGConnectInstance.getInstance().getContext() : context;
        if (context == null) {
            throw new IllegalStateException("Context not found.");
        }
        AGConnectServicesConfig fromContext = AGConnectServicesConfig.fromContext(context);
        this.region = fromContext.getString("region");
        SmartLog.w("AgConnectInfo", "AGConnectServicesConfig: " + fromContext + "region: " + this.region);
        String string = fromContext.getString(AgConnectKey.PACKAGE_NAME);
        this.packageName = string;
        if (string == null) {
            this.packageName = context.getPackageName();
        } else {
            SmartLog.w("AgConnectInfo", "AGConnectServicesConfig: " + fromContext + "packageName: " + this.packageName);
        }
        String string2 = fromContext.getString(AgConnectKey.APPLICATION_ID);
        this.applicationId = string2;
        if (string2 == null) {
            this.applicationId = this.packageName;
        } else {
            SmartLog.w("AgConnectInfo", "AGConnectServicesConfig: " + fromContext + "applicationId: " + this.applicationId);
        }
        this.apiKey = fromContext.getString(AgConnectKey.API_KEY);
        this.certFingerprint = getRawSignature(context);
        String string3 = fromContext.getString(AgConnectKey.ML_SERVICE_URL);
        if (string3 != null) {
            for (String str : string3.split(",")) {
                String lowerCase = str.trim().toLowerCase(Locale.ENGLISH);
                if (!lowerCase.startsWith("http://") && !lowerCase.startsWith("https://")) {
                    lowerCase = "https://" + lowerCase;
                }
                if (!lowerCase.endsWith("/")) {
                    lowerCase = lowerCase + "/";
                }
                this.mlServiceUrls.add(lowerCase);
            }
        }
        String string4 = fromContext.getString(AgConnectKey.ANALYTICS_COLLECTOR_URL);
        if (string4 != null) {
            for (String str2 : string4.split(",")) {
                String lowerCase2 = str2.trim().toLowerCase(Locale.ENGLISH);
                if (!lowerCase2.startsWith("http://") && !lowerCase2.startsWith("https://")) {
                    lowerCase2 = "https://" + lowerCase2;
                }
                this.haCollectorUrls.add(lowerCase2);
            }
        }
        this.projectId = fromContext.getString(AgConnectKey.PROJECT_ID);
        SmartLog.w("AgConnectInfo", "AGConnectServicesConfig: " + fromContext + "project_id: " + this.projectId);
        this.appMLKitGrsPolicy = fromContext.getString(AgConnectKey.APP_MLKIT_GRS_POLICY);
        SmartLog.w("AgConnectInfo", "AGConnectServicesConfig: " + fromContext + "processing_location_policy/mlkit: " + this.appMLKitGrsPolicy);
        networkKitInit(context);
    }

    private void networkKitInit(Context context) {
        NetworkKit.init(context, new NetworkKit.Callback() { // from class: com.huawei.hms.mlsdk.common.AgConnectInfo.1
            @Override // com.huawei.hms.network.NetworkKit.Callback
            public void onResult(boolean z) {
                if (z) {
                    SmartLog.w("AgConnectInfo", "networkKit init success");
                } else {
                    SmartLog.w("AgConnectInfo", "networkKit init failed");
                }
            }
        });
    }

    public AgConnectInfo() {
        this(null);
    }

    private String getRawSignature(Context context) {
        PackageInfo packageInfo;
        Signature signature;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (packageInfo == null) {
            return "";
        }
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr != null && signatureArr.length > 0) {
            signature = signatureArr[0];
            SmartLog.i("AgConnectInfo", "signature is not null ");
        } else {
            SmartLog.i("AgConnectInfo", "signature is null ");
            signature = null;
        }
        if (signature != null) {
            return getSignatureString(signature, "SHA256");
        }
        return "";
    }

    private final String getSignatureString(Signature signature, String str) {
        try {
            byte[] byteArray = signature.toByteArray();
            StringBuffer stringBuffer = new StringBuffer();
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.reset();
            messageDigest.update(byteArray);
            byte[] digest = messageDigest.digest();
            for (int i = 0; i < digest.length; i++) {
                if (Integer.toHexString(digest[i] & 255).length() == 1) {
                    stringBuffer.append("0").append(Integer.toHexString(digest[i] & 255));
                } else {
                    stringBuffer.append(Integer.toHexString(digest[i] & 255));
                }
                if (i != digest.length - 1) {
                    stringBuffer.append(":");
                }
            }
            return stringBuffer.toString().toUpperCase(Locale.ENGLISH);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String getRegion() {
        return this.region;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getCertFingerprint() {
        return this.certFingerprint;
    }

    public List<String> getMLServiceUrls() {
        return this.mlServiceUrls;
    }

    public List<String> getHaCollectorUrls() {
        return this.haCollectorUrls;
    }

    public String getAppMLKitGrsPolicy() {
        return this.appMLKitGrsPolicy;
    }

    public String getProjectId() {
        return this.projectId;
    }
}
