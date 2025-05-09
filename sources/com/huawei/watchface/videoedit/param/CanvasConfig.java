package com.huawei.watchface.videoedit.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.watchface.videoedit.sysc.Optional;
import com.huawei.watchface.videoedit.utils.EncoderFormat;
import com.huawei.watchface.videoedit.utils.SaveResolutionUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class CanvasConfig {
    public static final String DEFAULT_CONFIG = "default";
    public static final String FULL_CONFIG = "full";
    private static final int HASH_CODE_START_NUM = 17;
    private static final int MAGIC_NUM = 31;
    private static final int RESOLUTION_I = 1920;
    private static final int RESOLUTION_II = 1080;

    @SerializedName(ParsedFieldTag.EXCHANGE_PROPORTION)
    @Expose
    private String ratio;

    @SerializedName("resolution")
    @Expose
    private SaveResolutionUtils.VideoResolution resolution;

    @SerializedName("width")
    @Expose
    private int width = 1920;

    @SerializedName("height")
    @Expose
    private int height = 1080;

    @SerializedName("isSingleVideo")
    @Expose
    private boolean isSingleVideo = false;

    @SerializedName("widthResolution")
    @Expose
    private int widthResolution = 1920;

    @SerializedName("heightResolution")
    @Expose
    private int heightResolution = 1080;

    public CanvasConfig(String str) {
        this.resolution = SaveResolutionUtils.VideoResolution.RESOLUTION_FULL_HD;
        this.ratio = str;
        this.resolution = SaveResolutionUtils.VideoResolution.RESOLUTION_ORIGIN_SIZE;
    }

    public void setResolution(SaveResolutionUtils.VideoResolution videoResolution) {
        this.resolution = videoResolution;
    }

    public int getWidthResolution() {
        int minLength = getMinLength();
        float realRatio = getRealRatio();
        if (realRatio >= 1.0d) {
            minLength = (int) (minLength * realRatio);
        }
        return (minLength / 2) * 2;
    }

    public int getHeightResolution() {
        int minLength = getMinLength();
        float realRatio = getRealRatio();
        if (realRatio < 1.0d) {
            minLength = (int) (minLength / realRatio);
        }
        return (minLength / 2) * 2;
    }

    public void setRatio(String str) {
        this.ratio = str;
    }

    public String getRatio() {
        return this.ratio;
    }

    public CanvasConfig withRatio(String str) {
        this.ratio = str;
        return this;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getWidth() {
        return this.width;
    }

    public CanvasConfig withWidth(int i) {
        this.width = i;
        return this;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getHeight() {
        return this.height;
    }

    public CanvasConfig withHeight(int i) {
        this.height = i;
        return this;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CanvasConfig) {
            CanvasConfig canvasConfig = (CanvasConfig) obj;
            if (canvasConfig.ratio.equals(this.ratio) && canvasConfig.width == this.width && canvasConfig.height == this.height) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((this.ratio.hashCode() + 527) * 31) + this.width) * 31) + this.height;
    }

    public String toString() {
        return "{ratio: " + this.ratio + ", width: " + this.width + ", height: " + this.height + "}";
    }

    public boolean isSingleVideo() {
        return this.isSingleVideo;
    }

    public void flushCanvasWithVideo(Optional<Videos> optional) {
        if (optional.isPresent()) {
            this.isSingleVideo = true;
            Videos videos = optional.get();
            if (videos.getRotation() == 0.0f || videos.getRotation() == 180.0f) {
                this.widthResolution = videos.getWidth();
                this.heightResolution = videos.getHeight();
            } else {
                this.heightResolution = videos.getWidth();
                this.widthResolution = videos.getHeight();
            }
        }
    }

    public int getBitRate(int i) {
        return getVideoBitRateByLength(i);
    }

    public int getActiveBitRate() {
        return getBitRate(getMinLength());
    }

    public ArrayList<ResolutionInfo> getOptionalResolutionList() {
        int min = Math.min(this.widthResolution, this.heightResolution);
        ArrayList<ResolutionInfo> arrayList = new ArrayList<>();
        if (min >= 1080) {
            arrayList.add(new ResolutionInfo(1080, SaveResolutionUtils.VideoResolution.RESOLUTION_FULL_HD, 1));
        }
        if (min >= 720) {
            arrayList.add(new ResolutionInfo(720, SaveResolutionUtils.VideoResolution.RESOLUTION_HD, 2));
        }
        if (min >= 480) {
            arrayList.add(new ResolutionInfo(480, SaveResolutionUtils.VideoResolution.RESOLUTION_MEDIUM_SIZE, 3));
        }
        if (arrayList.size() == 0 || arrayList.get(0).getResolutionSize() != min) {
            arrayList.add(0, new ResolutionInfo(min, SaveResolutionUtils.VideoResolution.RESOLUTION_ORIGIN_SIZE, 0));
        }
        return arrayList;
    }

    public ArrayList<ResolutionInfo> getInfoList(long j) {
        ArrayList<ResolutionInfo> optionalResolutionList = getOptionalResolutionList();
        Iterator<ResolutionInfo> it = optionalResolutionList.iterator();
        while (it.hasNext()) {
            it.next().setFileSize((int) (((((getBitRate(r2.getResolutionSize()) * j) / 1000.0f) / 1024.0f) / 1024.0f) / 8.0f));
        }
        return optionalResolutionList;
    }

    public int getMinLength() {
        if (this.resolution == null) {
            this.resolution = SaveResolutionUtils.VideoResolution.RESOLUTION_FULL_HD;
        }
        int i = AnonymousClass1.$SwitchMap$com$huawei$watchface$videoedit$utils$SaveResolutionUtils$VideoResolution[this.resolution.ordinal()];
        if (i == 1) {
            return Math.min(this.widthResolution, this.heightResolution);
        }
        if (i != 2) {
            return i != 3 ? 480 : 720;
        }
        return 1080;
    }

    /* renamed from: com.huawei.watchface.videoedit.param.CanvasConfig$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huawei$watchface$videoedit$utils$SaveResolutionUtils$VideoResolution;

        static {
            int[] iArr = new int[SaveResolutionUtils.VideoResolution.values().length];
            $SwitchMap$com$huawei$watchface$videoedit$utils$SaveResolutionUtils$VideoResolution = iArr;
            try {
                iArr[SaveResolutionUtils.VideoResolution.RESOLUTION_ORIGIN_SIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huawei$watchface$videoedit$utils$SaveResolutionUtils$VideoResolution[SaveResolutionUtils.VideoResolution.RESOLUTION_FULL_HD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huawei$watchface$videoedit$utils$SaveResolutionUtils$VideoResolution[SaveResolutionUtils.VideoResolution.RESOLUTION_HD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private float getRealRatio() {
        float f;
        int i;
        if (this.isSingleVideo && "default".equals(this.ratio)) {
            f = this.widthResolution;
            i = this.heightResolution;
        } else {
            f = this.width;
            i = this.height;
        }
        return f / i;
    }

    private int getVideoBitRateByLength(int i) {
        return EncoderFormat.getVideoEncBitrate(i, i);
    }

    public int getSaveWidth() {
        int minLength = getMinLength();
        int i = this.width;
        if (i > minLength) {
            minLength = i;
        }
        float realRatio = getRealRatio();
        if (realRatio >= 1.0d) {
            minLength = (int) (minLength * realRatio);
        }
        return (minLength / 2) * 2;
    }

    public int getSaveHeight() {
        int minLength = getMinLength();
        int i = this.height;
        if (i > minLength) {
            minLength = i;
        }
        float realRatio = getRealRatio();
        if (realRatio < 1.0d) {
            minLength = (int) (minLength / realRatio);
        }
        return (minLength / 2) * 2;
    }
}
