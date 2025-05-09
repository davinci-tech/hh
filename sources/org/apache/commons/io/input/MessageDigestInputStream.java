package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.input.ObservableInputStream;

/* loaded from: classes10.dex */
public final class MessageDigestInputStream extends ObservableInputStream {
    private final MessageDigest messageDigest;

    public static class Builder extends AbstractStreamBuilder<MessageDigestInputStream, Builder> {
        private MessageDigest messageDigest;

        @Override // org.apache.commons.io.function.IOSupplier
        public MessageDigestInputStream get() throws IOException {
            return new MessageDigestInputStream(getInputStream(), this.messageDigest);
        }

        public Builder setMessageDigest(MessageDigest messageDigest) {
            this.messageDigest = messageDigest;
            return this;
        }

        public Builder setMessageDigest(String str) throws NoSuchAlgorithmException {
            this.messageDigest = MessageDigest.getInstance(str);
            return this;
        }
    }

    public static class MessageDigestMaintainingObserver extends ObservableInputStream.Observer {
        private final MessageDigest messageDigest;

        public MessageDigestMaintainingObserver(MessageDigest messageDigest) {
            this.messageDigest = (MessageDigest) Objects.requireNonNull(messageDigest, "messageDigest");
        }

        @Override // org.apache.commons.io.input.ObservableInputStream.Observer
        public void data(byte[] bArr, int i, int i2) throws IOException {
            this.messageDigest.update(bArr, i, i2);
        }

        @Override // org.apache.commons.io.input.ObservableInputStream.Observer
        public void data(int i) throws IOException {
            this.messageDigest.update((byte) i);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private MessageDigestInputStream(InputStream inputStream, MessageDigest messageDigest) {
        super(inputStream, new MessageDigestMaintainingObserver(messageDigest));
        this.messageDigest = messageDigest;
    }

    public MessageDigest getMessageDigest() {
        return this.messageDigest;
    }
}
