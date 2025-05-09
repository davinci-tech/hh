package com.huawei.ui.openservice.db.model;

import android.text.TextUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.ui.openservice.OpenServiceUtil;
import health.compact.a.HiDateUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes7.dex */
public class OpenService {
    private String appID;
    private AuthTypeList authType;
    private String description;
    private String downloadWebUrl;
    private int endDate;
    private int hmsAuth;
    private String homePageIcon;
    private int homePagePosition;
    private String label;
    private String packageName;
    private String pluginUrl;
    private String productName;
    private String requiredVersion;
    private String serviceDetail;
    private String serviceID;
    private String serviceIcon;
    private String serviceMidIcon;
    private String serviceProvider;
    private String serviceTypeID;
    private String serviceUrl;
    private String serviceVersion;
    private List<String> showLabels;
    private int showVersion;
    private int startDate;
    private int subPosition;
    private int isServiceCard = 1;
    private int needRecommend = 0;
    private int isPlugin = 0;
    private String serviceSource = OpenServiceUtil.Source.THIRD_H5;

    public List<String> getShowLabels() {
        return this.showLabels;
    }

    public void setShowLabels(List<String> list) {
        this.showLabels = list;
    }

    public int getShowVersion() {
        return this.showVersion;
    }

    public void setShowVersion(int i) {
        this.showVersion = i;
    }

    public String getServiceID() {
        return this.serviceID;
    }

    public void setServiceID(String str) {
        this.serviceID = str;
    }

    public String getServiceTypeID() {
        return this.serviceTypeID;
    }

    public void setServiceTypeID(String str) {
        this.serviceTypeID = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public int getSubPosition() {
        return this.subPosition;
    }

    public void setSubPosition(int i) {
        this.subPosition = i;
    }

    public String getServiceIcon() {
        return this.serviceIcon;
    }

    public void setServiceIcon(String str) {
        this.serviceIcon = str;
    }

    public String getServiceProvider() {
        return this.serviceProvider;
    }

    public void setServiceProvider(String str) {
        this.serviceProvider = str;
    }

    public String getAppID() {
        return this.appID;
    }

    public void setAppID(String str) {
        this.appID = str;
    }

    public String getServiceUrl() {
        return this.serviceUrl;
    }

    public void setServiceUrl(String str) {
        this.serviceUrl = str;
    }

    public String getRequiredVersion() {
        return this.requiredVersion;
    }

    public void setRequiredVersion(String str) {
        this.requiredVersion = str;
    }

    public int getStartDate() {
        return this.startDate;
    }

    public void setStartDate(int i) {
        this.startDate = i;
    }

    public int getEndDate() {
        return this.endDate;
    }

    public void setEndDate(int i) {
        this.endDate = i;
    }

    public int getHomePagePosition() {
        return this.homePagePosition;
    }

    public void setHomePagePosition(int i) {
        this.homePagePosition = i;
    }

    public String getHomePageIcon() {
        return this.homePageIcon;
    }

    public void setHomePageIcon(String str) {
        this.homePageIcon = str;
    }

    public int getHmsAuth() {
        return this.hmsAuth;
    }

    public void setHmsAuth(int i) {
        this.hmsAuth = i;
    }

    public AuthTypeList getAuthType() {
        return this.authType;
    }

    public void setAuthType(AuthTypeList authTypeList) {
        this.authType = authTypeList;
    }

    public void setAuthType(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.authType = (AuthTypeList) HiJsonUtil.e(str, AuthTypeList.class);
    }

    public String getAuthTypeStr() {
        AuthTypeList authTypeList = this.authType;
        if (authTypeList == null) {
            return null;
        }
        return HiJsonUtil.e(authTypeList);
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public int getIsServiceCard() {
        return this.isServiceCard;
    }

    public void setIsServiceCard(int i) {
        this.isServiceCard = i;
    }

    public int getNeedRecommend() {
        return this.needRecommend;
    }

    public void setNeedRecommend(int i) {
        this.needRecommend = i;
    }

    public int getIsPlugin() {
        return this.isPlugin;
    }

    public void setIsPlugin(int i) {
        this.isPlugin = i;
    }

    public String getPluginUrl() {
        return this.pluginUrl;
    }

    public void setPluginUrl(String str) {
        this.pluginUrl = str;
    }

    public String getServiceSource() {
        return this.serviceSource;
    }

    public void setServiceSource(String str) {
        this.serviceSource = str;
    }

    public String getServiceDetail() {
        return this.serviceDetail;
    }

    public void setServiceDetail(String str) {
        this.serviceDetail = str;
    }

    public String getServiceMidIcon() {
        return this.serviceMidIcon;
    }

    public void setServiceMidIcon(String str) {
        this.serviceMidIcon = str;
    }

    public String getServiceVersion() {
        return this.serviceVersion;
    }

    public void setServiceVersion(String str) {
        this.serviceVersion = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getDownloadWebUrl() {
        return this.downloadWebUrl;
    }

    public void setDownloadWebUrl(String str) {
        this.downloadWebUrl = str;
    }

    public boolean isDateLegal() {
        int c = HiDateUtil.c(System.currentTimeMillis());
        return c >= this.startDate && c <= this.endDate;
    }

    public boolean checkData() {
        return (TextUtils.isEmpty(this.serviceID) || TextUtils.isEmpty(this.serviceTypeID) || TextUtils.isEmpty(this.appID) || TextUtils.isEmpty(this.serviceUrl)) ? false : true;
    }

    public static boolean isEmpty(List<OpenService> list) {
        return list == null || list.isEmpty();
    }

    public static void orderOpenService(List<OpenService> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(list, new Comparator<OpenService>() { // from class: com.huawei.ui.openservice.db.model.OpenService.1
            @Override // java.util.Comparator
            public int compare(OpenService openService, OpenService openService2) {
                return openService.getSubPosition() - openService2.getSubPosition();
            }
        });
    }
}
