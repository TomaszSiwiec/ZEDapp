package com.zedapp.domain;

import lombok.Getter;

@Getter
public class Mail {
    private String mailTo;
    private String toCc;
    private String subject;
    private String message;

    private Mail(String mailTo, String toCc, String subject, String message) {
        this.mailTo = mailTo;
        this.toCc = toCc;
        this.subject = subject;
        this.message = message;
    }

    public static class Builder {
        private String mailTo;
        private String toCc;
        private String subject;
        private String message;

        public Builder mailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public Builder toCc(String toCc) {
            this.toCc = toCc;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Mail build() {
            return new Mail(mailTo, toCc, subject, message);
        }
    }
}
