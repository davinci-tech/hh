package com.huawei.featureuserprofile.media;

import com.huawei.up.model.UserInfomation;

/* loaded from: classes3.dex */
public interface UserInfoMedia {

    public interface UserInfoReader {

        public interface Callback {
            void onFail(int i);

            void onSuccess(UserInfomation userInfomation);
        }

        void block();

        void read(Callback callback);

        void unBlock();
    }

    public interface UserInfoWriter {

        public interface Callback {
            void onFail(int i);

            void onSuccess();
        }

        void block();

        void unBlock();

        void write(UserInfomation userInfomation, Callback callback);
    }

    UserInfoReader obtainReader();

    UserInfoWriter obtainWriter();
}
