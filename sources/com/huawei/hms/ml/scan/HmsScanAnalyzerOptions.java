package com.huawei.hms.ml.scan;

import com.huawei.hms.scankit.p.w7;

/* loaded from: classes4.dex */
public class HmsScanAnalyzerOptions {
    public final boolean errorCheck;
    public final int mode;
    public final boolean parseResult;
    public final boolean photoMode;
    public final boolean showGuide;
    public final int viewType;

    public static class Creator {
        private int type = 0;
        private boolean photoMode = false;
        private int viewType = 0;
        private boolean errorCheck = false;
        private boolean parseResult = true;
        private boolean showGuide = false;

        public HmsScanAnalyzerOptions create() {
            return new HmsScanAnalyzerOptions(this.type, this.photoMode, this.viewType, this.errorCheck, this.parseResult, this.showGuide);
        }

        public Creator setErrorCheck(boolean z) {
            this.errorCheck = z;
            return this;
        }

        public Creator setHmsScanTypes(int i, int... iArr) {
            int a2 = w7.a(i);
            this.type = a2;
            if (iArr != null && iArr.length > 0) {
                this.type = w7.b(a2);
                for (int i2 : iArr) {
                    this.type = w7.b(i2) | this.type;
                }
            }
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

        public Creator setShowGuide(boolean z) {
            this.showGuide = z;
            return this;
        }

        public Creator setViewType(int i) {
            this.viewType = i;
            return this;
        }
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return super.hashCode();
    }

    private HmsScanAnalyzerOptions(int i, boolean z, int i2, boolean z2, boolean z3, boolean z4) {
        this.mode = i;
        this.photoMode = z;
        this.viewType = i2;
        this.errorCheck = z2;
        this.parseResult = z3;
        this.showGuide = z4;
    }
}
