package com.huawei.hms.ml.scan;

/* loaded from: classes9.dex */
public class HmsScanFrameOptions {
    private boolean multiMode;
    private boolean parseResult;
    private boolean photoMode;
    private int type;

    public static class Creator {
        private int type = 0;
        private boolean multiMode = false;
        private boolean photoMode = false;
        private boolean parseResult = true;

        public HmsScanFrameOptions create() {
            return new HmsScanFrameOptions(this.type, this.photoMode, this.multiMode, this.parseResult);
        }

        public Creator setHmsScanTypes(int i, int... iArr) {
            this.type = i;
            if (iArr != null && iArr.length > 0) {
                for (int i2 : iArr) {
                    this.type = i2 | this.type;
                }
            }
            return this;
        }

        public Creator setMultiMode(boolean z) {
            this.multiMode = z;
            return this;
        }

        public Creator setParseResult(boolean z) {
            this.parseResult = z;
            return this;
        }

        public Creator setPhotoMode(boolean z) {
            this.photoMode = z;
            return this;
        }
    }

    public int getType() {
        return this.type;
    }

    public boolean isMultiMode() {
        return this.multiMode;
    }

    public boolean isParseResult() {
        return this.parseResult;
    }

    public boolean isPhotoMode() {
        return this.photoMode;
    }

    private HmsScanFrameOptions(int i, boolean z, boolean z2, boolean z3) {
        this.type = i;
        this.photoMode = z;
        this.multiMode = z2;
        this.parseResult = z3;
    }
}
