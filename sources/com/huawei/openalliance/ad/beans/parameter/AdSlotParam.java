package com.huawei.openalliance.ad.beans.parameter;

import android.content.Context;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.inner.LocationSwitches;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.Location;
import com.huawei.openalliance.ad.beans.metadata.Video;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.oq;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bl;
import com.huawei.openalliance.ad.utils.cz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class AdSlotParam {
    private static final String TAG = "AdSlotParam";
    private Integer adHeight;
    private List<String> adIds;
    private int adType;
    private Integer adWidth;
    private Integer allowMobileTraffic;
    private com.huawei.openalliance.ad.beans.metadata.App appInfo;
    private Integer bannerRefFlag;
    private String contentBundle;

    @f
    private Map<String, String> contentBundleMap;
    private List<String> detailedCreativeTypeList;
    private int deviceType;
    private Integer endMode;
    private Map<String, Integer> fcFlagMap;
    private int grpIdCode;
    private int height;
    private boolean isPreload;
    private Integer isSmart;
    private Boolean isTrackLimited;
    private String jssdkVersion;
    private Integer linkedMode;
    private Location location;
    private LocationSwitches locationSwitches;
    private int maxCount;
    private String oaid;
    private int orientation;
    private String requestId;
    private RequestOptions requestOptions;
    private String requestSequence;
    private Integer requestType;
    private Integer splashStartMode;
    private Integer splashType;
    private boolean supportTptAd;
    private boolean test;
    private int totalDuration;
    private String uiEngineVer;
    private Map<String, Integer> unsupportedTags;
    private Video video;
    private int width;
    private Integer wlacStatus;

    public static final class Builder {
        private Integer adHeight;
        private int adType;
        private Integer adWidth;
        private Integer allowMobileTraffic;
        private Integer bannerRefFlag;
        private String contentBundle;
        private Map<String, String> contentBundleMap;
        private List<String> detailedCreativeTypeList;
        private Integer endMode;
        private Integer isSmart;
        private Boolean isTrackLimited;
        private String jssdkVersion;
        private Integer linkedMode;
        private android.location.Location location;
        private int maxCount;
        private String oaid;
        private String requestId;
        private RequestOptions requestOptions;
        private String requestSequence;
        private Integer requestType;
        private String searchTerm;
        private int totalDuration;
        private Map<String, Integer> unsupportedTags;
        private Video video;
        private List<String> adIds = new ArrayList(0);
        private int orientation = 1;
        private boolean test = false;
        private int deviceType = 4;
        private int width = 0;
        private int height = 0;
        private boolean isPreload = false;
        private boolean supportTptAd = false;

        public Builder setWidth(int i) {
            this.width = i;
            return this;
        }

        public Builder setUnsupportedTags(Map<String, Integer> map) {
            this.unsupportedTags = map;
            return this;
        }

        public Builder setTrackLimited(Boolean bool) {
            this.isTrackLimited = bool;
            return this;
        }

        public Builder setTotalDuration(int i) {
            this.totalDuration = i;
            return this;
        }

        public Builder setTest(boolean z) {
            this.test = z;
            return this;
        }

        public Builder setSearchTerm(String str) {
            this.searchTerm = str;
            return this;
        }

        public Builder setRequestSequence(String str) {
            this.requestSequence = str;
            return this;
        }

        public Builder setRequestOptions(RequestOptions requestOptions) {
            this.requestOptions = requestOptions;
            return this;
        }

        public Builder setOrientation(int i) {
            this.orientation = i;
            return this;
        }

        public Builder setOaid(String str) {
            this.oaid = str;
            return this;
        }

        public Builder setMaxCount(int i) {
            this.maxCount = i;
            return this;
        }

        public Builder setLocation(android.location.Location location) {
            this.location = location;
            return this;
        }

        public Builder setLinkedMode(Integer num) {
            this.linkedMode = num;
            return this;
        }

        public Builder setIsPreload(Boolean bool) {
            this.isPreload = bool.booleanValue();
            return this;
        }

        public Builder setHeight(int i) {
            this.height = i;
            return this;
        }

        public Builder setEndMode(Integer num) {
            this.endMode = num;
            return this;
        }

        public Builder setDeviceType(int i) {
            this.deviceType = i;
            return this;
        }

        public Builder setDetailedCreativeTypeList(List<Integer> list) {
            if (list == null) {
                return this;
            }
            if (list.size() > 100) {
                list = list.subList(0, 100);
            }
            this.detailedCreativeTypeList = new ArrayList(list.size());
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                this.detailedCreativeTypeList.add(Integer.toString(it.next().intValue()));
            }
            return this;
        }

        public Builder setContentBundle(String str) {
            Map<String, String> map = (Map) be.b(str, Map.class, new Class[0]);
            if (bl.a(map)) {
                ho.c(AdSlotParam.TAG, "contentBundle info is empty or not json string");
            } else {
                String c = AdSlotParam.c(map);
                this.contentBundleMap = map;
                this.contentBundle = c;
            }
            return this;
        }

        public Builder setAdWidth(Integer num) {
            this.adWidth = num;
            return this;
        }

        public Builder setAdIds(List<String> list) {
            this.adIds = list;
            return this;
        }

        public Builder setAdHeight(Integer num) {
            this.adHeight = num;
            return this;
        }

        public void c(Integer num) {
            this.requestType = num;
        }

        public AdSlotParam build() {
            return new AdSlotParam(this);
        }

        public Builder b(Integer num) {
            this.isSmart = num;
            return this;
        }

        public void a(Video video) {
            this.video = video;
        }

        public Builder a(boolean z) {
            this.supportTptAd = z;
            return this;
        }

        public Builder a(String str) {
            this.jssdkVersion = str;
            return this;
        }

        public Builder a(Integer num) {
            this.bannerRefFlag = num;
            return this;
        }

        public Location a() {
            android.location.Location location = this.location;
            if (location == null) {
                return null;
            }
            return new Location(Double.valueOf(location.getLongitude()), Double.valueOf(this.location.getLatitude()));
        }
    }

    public Integer z() {
        return this.adHeight;
    }

    public Integer y() {
        return this.adWidth;
    }

    public Integer x() {
        return this.isSmart;
    }

    public Integer w() {
        return this.wlacStatus;
    }

    public List<String> v() {
        return this.detailedCreativeTypeList;
    }

    public LocationSwitches u() {
        return this.locationSwitches;
    }

    public Integer t() {
        return this.bannerRefFlag;
    }

    public String s() {
        return this.requestId;
    }

    public Integer r() {
        return this.splashStartMode;
    }

    public Integer q() {
        return this.splashType;
    }

    public Integer p() {
        return this.linkedMode;
    }

    public Integer o() {
        return this.allowMobileTraffic;
    }

    public int n() {
        return this.totalDuration;
    }

    public int m() {
        return this.maxCount;
    }

    public RequestOptions l() {
        return this.requestOptions;
    }

    public Location k() {
        return this.location;
    }

    public boolean j() {
        return this.isPreload;
    }

    public com.huawei.openalliance.ad.beans.metadata.App i() {
        return this.appInfo;
    }

    public Boolean h() {
        return this.isTrackLimited;
    }

    public String g() {
        return this.oaid;
    }

    public int f() {
        return this.height;
    }

    public void e(Integer num) {
        this.wlacStatus = num;
    }

    public void e(int i) {
        this.grpIdCode = i;
    }

    public int e() {
        return this.width;
    }

    public void d(Integer num) {
        this.splashStartMode = num;
    }

    public void d(int i) {
        this.adType = i;
    }

    public int d() {
        return this.deviceType;
    }

    public boolean c() {
        return this.test;
    }

    public void c(String str) {
        this.uiEngineVer = str;
    }

    public void c(Integer num) {
        this.splashType = num;
    }

    public void c(int i) {
        this.height = i;
    }

    public void b(String str) {
        this.requestId = str;
    }

    public void b(Integer num) {
        this.linkedMode = num;
    }

    public void b(int i) {
        this.width = i;
    }

    public int b() {
        return this.orientation;
    }

    public void a(boolean z) {
        this.isPreload = z;
    }

    public void a(Map<String, Integer> map) {
        this.fcFlagMap = map;
    }

    public void a(List<String> list) {
        this.adIds = list;
    }

    public void a(String str) {
        this.oaid = str;
    }

    public void a(Integer num) {
        this.allowMobileTraffic = num;
    }

    public void a(Boolean bool) {
        this.isTrackLimited = bool;
    }

    public void a(RequestOptions requestOptions) {
        this.requestOptions = requestOptions;
    }

    public void a(Location location) {
        this.location = location;
    }

    public void a(com.huawei.openalliance.ad.beans.metadata.App app) {
        this.appInfo = app;
    }

    public void a(LocationSwitches locationSwitches) {
        this.locationSwitches = locationSwitches;
    }

    public void a(int i) {
        this.deviceType = i;
    }

    public List<String> a() {
        return this.adIds;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String a(Context context, int i) {
        Map<String, String> map;
        if (fh.b(context).ab() && i != 1 && i != 18) {
            String a2 = oq.a(context).a();
            Map map2 = (Map) be.b(a2, Map.class, new Class[0]);
            if (!bl.a(map2) && !bl.a(this.contentBundleMap)) {
                ho.b(TAG, "merge auto content Bundle");
                for (Map.Entry entry : map2.entrySet()) {
                    String str = (String) entry.getKey();
                    String str2 = (String) entry.getValue();
                    if (!this.contentBundleMap.containsKey(str) && !cz.b(str2)) {
                        this.contentBundleMap.put(str, map2.get(str));
                    }
                }
                if (this.contentBundleMap.containsKey("content") && this.contentBundleMap.containsKey(Constants.AUTOCONTENT_CONTENT_AUTO)) {
                    this.contentBundleMap.remove(Constants.AUTOCONTENT_CONTENT_AUTO);
                }
                map = this.contentBundleMap;
            } else if (!bl.a(map2)) {
                ho.b(TAG, "set auto content Bundle");
                map = (Map) be.b(a2, Map.class, new Class[0]);
                if (bl.a(map)) {
                    ho.c(TAG, "auto contentBundle info is empty or not json string");
                }
            }
            this.contentBundle = c(map);
        }
        return this.contentBundle;
    }

    public AdSlotParam I() {
        AdSlotParam adSlotParam = new AdSlotParam();
        adSlotParam.adIds = this.adIds;
        adSlotParam.orientation = this.orientation;
        adSlotParam.test = this.test;
        adSlotParam.deviceType = this.deviceType;
        adSlotParam.width = this.width;
        adSlotParam.height = this.height;
        adSlotParam.requestSequence = this.requestSequence;
        adSlotParam.oaid = this.oaid;
        adSlotParam.isTrackLimited = this.isTrackLimited;
        adSlotParam.appInfo = this.appInfo;
        adSlotParam.video = this.video;
        adSlotParam.isPreload = this.isPreload;
        adSlotParam.requestOptions = this.requestOptions;
        adSlotParam.location = this.location;
        adSlotParam.maxCount = this.maxCount;
        adSlotParam.allowMobileTraffic = this.allowMobileTraffic;
        adSlotParam.linkedMode = this.linkedMode;
        adSlotParam.totalDuration = this.totalDuration;
        adSlotParam.splashType = this.splashType;
        adSlotParam.splashStartMode = this.splashStartMode;
        adSlotParam.locationSwitches = this.locationSwitches;
        adSlotParam.bannerRefFlag = this.bannerRefFlag;
        adSlotParam.detailedCreativeTypeList = this.detailedCreativeTypeList;
        adSlotParam.isSmart = this.isSmart;
        adSlotParam.adWidth = this.adWidth;
        adSlotParam.adHeight = this.adHeight;
        adSlotParam.adType = this.adType;
        adSlotParam.requestType = this.requestType;
        adSlotParam.contentBundle = this.contentBundle;
        adSlotParam.contentBundleMap = this.contentBundleMap;
        adSlotParam.endMode = this.endMode;
        adSlotParam.unsupportedTags = this.unsupportedTags;
        adSlotParam.supportTptAd = this.supportTptAd;
        adSlotParam.uiEngineVer = this.uiEngineVer;
        adSlotParam.jssdkVersion = this.jssdkVersion;
        return adSlotParam;
    }

    public Map<String, Integer> H() {
        Map<String, Integer> map = this.fcFlagMap;
        return map != null ? map : new HashMap();
    }

    public Map<String, Integer> G() {
        return this.unsupportedTags;
    }

    public Integer F() {
        return this.endMode;
    }

    public int E() {
        return this.grpIdCode;
    }

    public int D() {
        return this.adType;
    }

    public Integer C() {
        return this.requestType;
    }

    public String B() {
        return this.jssdkVersion;
    }

    public boolean A() {
        return this.supportTptAd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(new ImpEX(entry.getKey(), cz.d(entry.getValue())));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("contentBundle", arrayList);
        return be.b(hashMap);
    }

    private AdSlotParam(Builder builder) {
        this.isPreload = false;
        this.supportTptAd = false;
        this.adIds = builder.adIds;
        this.orientation = builder.orientation;
        this.test = builder.test;
        this.deviceType = builder.deviceType;
        this.width = builder.width;
        this.height = builder.height;
        this.requestSequence = builder.requestSequence;
        this.oaid = builder.oaid;
        this.isTrackLimited = builder.isTrackLimited;
        this.video = builder.video;
        this.isPreload = builder.isPreload;
        this.requestOptions = builder.requestOptions;
        this.location = builder.a();
        this.maxCount = builder.maxCount;
        this.allowMobileTraffic = builder.allowMobileTraffic;
        this.linkedMode = builder.linkedMode;
        this.totalDuration = builder.totalDuration;
        this.requestId = builder.requestId;
        this.bannerRefFlag = builder.bannerRefFlag;
        this.detailedCreativeTypeList = builder.detailedCreativeTypeList;
        this.isSmart = builder.isSmart;
        this.adWidth = builder.adWidth;
        this.adHeight = builder.adHeight;
        this.requestType = builder.requestType;
        this.contentBundle = builder.contentBundle;
        this.contentBundleMap = builder.contentBundleMap;
        this.adType = builder.adType;
        this.endMode = builder.endMode;
        this.unsupportedTags = builder.unsupportedTags;
        this.supportTptAd = builder.supportTptAd;
        this.jssdkVersion = builder.jssdkVersion;
    }

    public AdSlotParam() {
        this.isPreload = false;
        this.supportTptAd = false;
    }
}
