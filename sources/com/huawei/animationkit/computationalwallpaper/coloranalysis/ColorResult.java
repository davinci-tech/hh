package com.huawei.animationkit.computationalwallpaper.coloranalysis;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.abv;
import defpackage.acf;
import defpackage.acg;
import defpackage.acj;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class ColorResult implements Parcelable {
    private static final String ADDITIONAL_COLORS = "additional_colors";
    private static final String ADDITIONAL_GRADIENTS = "additional_gradients";
    private static final String COLOR_DELIMITER = ",";
    public static final Parcelable.Creator<ColorResult> CREATOR = new Parcelable.Creator<ColorResult>() { // from class: com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: fJ_, reason: merged with bridge method [inline-methods] */
        public ColorResult createFromParcel(Parcel parcel) {
            try {
                return new ColorResult(parcel);
            } catch (abv e) {
                parcel.writeException(e);
                return null;
            }
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ColorResult[] newArray(int i) {
            return new ColorResult[i];
        }
    };
    private static final String MAIN_COLORS = "main_colors";
    private static final String MAIN_GRADIENTS = "main_gradients";
    private static final String PROPERTY_DELIMITER = ":";
    private List<acf> mAdditionalColors;
    private List<acf> mAdditionalGradients;
    private List<acf> mMainColors;
    private List<acf> mMainGradients;
    private List<acf> mOriginalColors;
    private List<List<acf>> mProcessHistory;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ColorResult() {
        this.mMainColors = Collections.emptyList();
        this.mMainGradients = Collections.emptyList();
        this.mAdditionalColors = Collections.emptyList();
        this.mAdditionalGradients = Collections.emptyList();
    }

    public ColorResult(ColorResult colorResult) {
        this.mOriginalColors = new ArrayList(colorResult.getOriginalColors());
        this.mMainColors = new ArrayList(colorResult.getMainColors());
        this.mAdditionalColors = new ArrayList(colorResult.getAdditionalColors());
        this.mMainGradients = new ArrayList(colorResult.getMainGradients());
        this.mAdditionalGradients = new ArrayList(colorResult.getAdditionalGradients());
    }

    protected ColorResult(Parcel parcel) throws abv {
        initColor(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
    }

    public List<acf> getOriginalColors() {
        return this.mOriginalColors;
    }

    public void setOriginalColors(List<acf> list) {
        this.mOriginalColors = list;
    }

    public List<acf> getMainColors() {
        return this.mMainColors;
    }

    public void setMainColors(List<acf> list) {
        this.mMainColors = list;
    }

    public List<acf> getAdditionalColors() {
        return this.mAdditionalColors;
    }

    public void setAdditionalColors(List<acf> list) {
        this.mAdditionalColors = list;
    }

    public List<acf> getMainGradients() {
        return this.mMainGradients;
    }

    public void setMainGradients(List<acf> list) {
        this.mMainGradients = list;
    }

    public List<acf> getAdditionalGradients() {
        return this.mAdditionalGradients;
    }

    public void setAdditionalGradients(List<acf> list) {
        this.mAdditionalGradients = list;
    }

    public List<Integer> getAllColors() {
        List<Integer> list = (List) this.mMainColors.stream().map(new acg()).collect(Collectors.toList());
        List<acf> list2 = this.mAdditionalColors;
        if (list2 != null) {
            list.addAll((Collection) list2.stream().map(new acg()).collect(Collectors.toList()));
        }
        return list;
    }

    public List<Integer> getAllGradients() {
        List<Integer> list = (List) this.mMainGradients.stream().map(new acg()).collect(Collectors.toList());
        List<acf> list2 = this.mAdditionalGradients;
        if (list2 != null) {
            list.addAll((Collection) list2.stream().map(new acg()).collect(Collectors.toList()));
        }
        return list;
    }

    public void save(Properties properties) {
        properties.put(MAIN_COLORS, (String) this.mMainColors.stream().map(new acj(this)).collect(Collectors.joining(",")));
        properties.put(ADDITIONAL_COLORS, (String) this.mAdditionalColors.stream().map(new acj(this)).collect(Collectors.joining(",")));
        properties.put(MAIN_GRADIENTS, (String) this.mMainGradients.stream().map(new acj(this)).collect(Collectors.joining(",")));
        properties.put(ADDITIONAL_GRADIENTS, (String) this.mAdditionalGradients.stream().map(new acj(this)).collect(Collectors.joining(",")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatColor(acf acfVar) {
        return acfVar.c() + ":" + acfVar.a();
    }

    public void load(Properties properties) throws abv {
        initColor(properties.getProperty(MAIN_COLORS), properties.getProperty(MAIN_GRADIENTS), properties.getProperty(ADDITIONAL_COLORS), properties.getProperty(ADDITIONAL_GRADIENTS));
    }

    public void addHistory(List<acf> list) {
        if (this.mProcessHistory == null) {
            this.mProcessHistory = new ArrayList();
        }
        this.mProcessHistory.add(list);
    }

    public List<List<acf>> getHistory() {
        return this.mProcessHistory;
    }

    private void initColor(String str, String str2, String str3, String str4) throws abv {
        this.mMainColors = new ArrayList();
        for (String str5 : str.split(",")) {
            this.mMainColors.add(parseColor(str5));
        }
        this.mMainGradients = new ArrayList();
        for (String str6 : str2.split(",")) {
            this.mMainGradients.add(parseColor(str6));
        }
        if (str3 != null && !str3.isEmpty()) {
            this.mAdditionalColors = new ArrayList();
            String[] split = str3.split(",");
            for (String str7 : split) {
                this.mAdditionalColors.add(parseColor(str7));
            }
        }
        if (str4 == null || str4.isEmpty()) {
            return;
        }
        this.mAdditionalGradients = new ArrayList();
        for (String str8 : str4.split(",")) {
            this.mAdditionalGradients.add(parseColor(str8));
        }
    }

    private acf parseColor(String str) throws abv {
        String[] split = str.split(":");
        if (split.length != 2) {
            throw new abv("parse color failed " + str);
        }
        try {
            return new acf(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        } catch (NumberFormatException e) {
            throw new abv(e);
        }
    }

    public String toString() {
        return "{mainColors=" + this.mMainColors + ", additionalColors=" + this.mAdditionalColors + ", mainGradients=" + this.mMainGradients + ", additionalGradients=" + this.mAdditionalGradients + ", originalColors=" + this.mOriginalColors + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString((String) this.mMainColors.stream().map(new acj(this)).collect(Collectors.joining(",")));
        parcel.writeString((String) this.mAdditionalColors.stream().map(new acj(this)).collect(Collectors.joining(",")));
        parcel.writeString((String) this.mMainGradients.stream().map(new acj(this)).collect(Collectors.joining(",")));
        parcel.writeString((String) this.mAdditionalGradients.stream().map(new acj(this)).collect(Collectors.joining(",")));
    }
}
