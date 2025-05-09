package com.huawei.updatesdk.service.otaupdate;

import com.huawei.updatesdk.service.appmgr.bean.Param;
import java.util.List;

/* loaded from: classes7.dex */
public class UpdateParams {
    private final boolean isShowImmediate;
    private final int minIntervalDay;
    private final boolean mustBtnOne;
    private final List<String> packageList;
    private List<Param> paramList;
    private final String serviceZone;
    private final String targetPkgName;

    public static final class Builder {
        private boolean isShowImmediate;
        private int minIntervalDay;
        private boolean mustBtnOne;
        private List<String> packageList;
        private List<Param> paramList;
        private String serviceZone = f.e().b();
        private String targetPkgName;

        public Builder setTargetPkgName(String str) {
            this.targetPkgName = str;
            return this;
        }

        public Builder setServiceZone(String str) {
            this.serviceZone = str;
            return this;
        }

        public Builder setParamList(List<Param> list) {
            this.paramList = list;
            return this;
        }

        public Builder setPackageList(List<String> list) {
            this.packageList = list;
            return this;
        }

        public Builder setMustBtnOne(boolean z) {
            this.mustBtnOne = z;
            return this;
        }

        public Builder setMinIntervalDay(int i) {
            this.minIntervalDay = i;
            return this;
        }

        public Builder setIsShowImmediate(boolean z) {
            this.isShowImmediate = z;
            return this;
        }

        public UpdateParams build() {
            return new UpdateParams(this);
        }
    }

    public void resetParamList() {
        this.paramList = null;
    }

    public boolean isShowImmediate() {
        return this.isShowImmediate;
    }

    public boolean isMustBtnOne() {
        return this.mustBtnOne;
    }

    public String getTargetPkgName() {
        return this.targetPkgName;
    }

    public String getServiceZone() {
        return this.serviceZone;
    }

    public List<Param> getParamList() {
        return this.paramList;
    }

    public List<String> getPackageList() {
        return this.packageList;
    }

    public int getMinIntervalDay() {
        return this.minIntervalDay;
    }

    private UpdateParams(Builder builder) {
        this.serviceZone = builder.serviceZone;
        this.targetPkgName = builder.targetPkgName;
        this.isShowImmediate = builder.isShowImmediate;
        this.minIntervalDay = builder.minIntervalDay;
        this.mustBtnOne = builder.mustBtnOne;
        this.packageList = builder.packageList;
        this.paramList = builder.paramList;
    }
}
