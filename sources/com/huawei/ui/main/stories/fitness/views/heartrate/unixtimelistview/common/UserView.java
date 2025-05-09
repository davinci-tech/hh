package com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.common;

/* loaded from: classes6.dex */
public enum UserView {
    UNKNOWN_DATAS(-1),
    DETAIL_DATAS(0),
    MONTH_DATAS(1),
    YEAR_DATAS(2),
    ALL_DATAS(3);

    private int mLevel;

    UserView(int i) {
        this.mLevel = i;
    }

    public UserView upper() {
        int i = this.mLevel;
        for (UserView userView : values()) {
            if (userView.mLevel == i + 1) {
                return userView;
            }
        }
        return UNKNOWN_DATAS;
    }

    public UserView lower() {
        int i = this.mLevel;
        for (UserView userView : values()) {
            if (userView.mLevel == i - 1) {
                return userView;
            }
        }
        return UNKNOWN_DATAS;
    }
}
