package com.huawei.agconnect.common.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.common.appinfo.SafeAppInfo;
import com.huawei.agconnect.https.annotation.Header;
import com.huawei.agconnect.version.LibraryInfos;
import com.huawei.hms.mlsdk.common.AgConnectInfo;
import com.tencent.connect.common.Constants;

/* loaded from: classes2.dex */
public abstract class BaseRequest {

    @Header(Constants.PARAM_ACCESS_TOKEN)
    private String accessToken;

    @Header("x-apik")
    private String apiKey;

    @Header("appVersion")
    private String appVersion;

    @Header("Authorization")
    private String authorization;

    @Header("x-cert-fp")
    private String certFp;

    @Header("aaId")
    private String headerAaId;

    @Header("appId")
    private String headerAppId;

    @Header("client_id")
    private String headerClientId;

    @Header("productId")
    private String headerProductId;

    @Header("packageName")
    private String packageName;

    @Header("sdkPlatform")
    private String sdkPlatform;

    @Header("sdkPlatformVersion")
    private String sdkPlatformVersion;

    @Header("sdkServiceName")
    private String sdkServiceName;

    @Header("sdkType")
    private String sdkType;

    @Header("sdkVersion")
    private String sdkVersion;

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public void setSdkServiceName(String str) {
        this.sdkServiceName = str;
    }

    public void setSdkPlatformVersion(String str) {
        this.sdkPlatformVersion = str;
    }

    public void setSdkPlatform(String str) {
        this.sdkPlatform = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setHeaderProductId(String str) {
        this.headerProductId = str;
    }

    public void setHeaderClientId(String str) {
        this.headerClientId = str;
    }

    public void setHeaderAppId(String str) {
        this.headerAppId = str;
    }

    public void setHeaderAaId(String str) {
        this.headerAaId = str;
    }

    public void setCertFp(String str) {
        this.certFp = str;
    }

    public void setAuthorization(String str) {
        this.authorization = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setApiKey(String str) {
        this.apiKey = str;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void initBase(AGConnectInstance aGConnectInstance) {
        Context context = aGConnectInstance.getContext();
        this.sdkType = LibraryInfos.getInstance().getLibraryType();
        this.sdkPlatform = Agent.OS_NAME;
        this.sdkPlatformVersion = Build.VERSION.RELEASE;
        this.packageName = context.getPackageName();
        PackageInfo safeGetPackageInfo = SafeAppInfo.safeGetPackageInfo(context.getPackageManager(), this.packageName, 16384);
        if (safeGetPackageInfo != null) {
            this.appVersion = safeGetPackageInfo.versionName;
        }
        AGConnectOptions options = aGConnectInstance.getOptions();
        this.packageName = aGConnectInstance.getOptions().getPackageName();
        this.headerProductId = options.getString("client/product_id");
        this.headerAppId = options.getString(AgConnectInfo.AgConnectKey.APPLICATION_ID);
        this.headerClientId = options.getString("client/client_id");
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public String getSdkServiceName() {
        return this.sdkServiceName;
    }

    public String getSdkPlatformVersion() {
        return this.sdkPlatformVersion;
    }

    public String getSdkPlatform() {
        return this.sdkPlatform;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getHeaderProductId() {
        return this.headerProductId;
    }

    public String getHeaderClientId() {
        return this.headerClientId;
    }

    public String getHeaderAppId() {
        return this.headerAppId;
    }

    public String getHeaderAaId() {
        return this.headerAaId;
    }

    public String getCertFp() {
        return this.certFp;
    }

    public String getAuthorization() {
        return this.authorization;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public BaseRequest(AGConnectInstance aGConnectInstance) {
        initBase(aGConnectInstance);
    }

    public BaseRequest() {
        initBase(AGConnectInstance.getInstance());
    }
}
