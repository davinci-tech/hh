package com.huawei.openalliance.ad.beans.parameter;

import com.huawei.openalliance.ad.annotations.a;
import java.util.List;

/* loaded from: classes5.dex */
public class BiddingParam {
    private static final String TAG = "BiddingParam";

    @a
    private Float bidFloor;
    private String bidFloorCur;
    private List<String> bpkgName;

    /* loaded from: classes9.dex */
    public static final class Builder {

        @a
        private Float bidFloor;
        private String bidFloorCur;
        private List<String> bpkgName;

        public Builder setBpkgName(List<String> list) {
            this.bpkgName = list;
            return this;
        }

        public Builder setBidFloorCur(String str) {
            this.bidFloorCur = str;
            return this;
        }

        public Builder setBidFloor(Float f) {
            this.bidFloor = f;
            return this;
        }

        public BiddingParam build() {
            return new BiddingParam(this);
        }
    }

    public String toString() {
        return "BiddingParam{bidFloorCur = " + this.bidFloorCur + ", bpkgName = " + this.bpkgName + '}';
    }

    public List<String> getBpkgName() {
        return this.bpkgName;
    }

    public String getBidFloorCur() {
        return this.bidFloorCur;
    }

    public Float getBidFloor() {
        return this.bidFloor;
    }

    public BiddingParam(Builder builder) {
        if (builder != null) {
            this.bidFloor = builder.bidFloor;
            this.bidFloorCur = builder.bidFloorCur;
            this.bpkgName = builder.bpkgName;
        }
    }

    public BiddingParam() {
    }
}
