/*
 * Copyright 2018 Emmanuel Kehinde
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emmanuelkehinde.quicksms;

import android.support.annotation.NonNull;

/**
 * POJO representing an SMS
 */
public class Sms {

    private String sender = "";
    private String recipient = "";
    private String message = "";
    private boolean supportDnd = false;
    private String time = "";

    public Sms() {

    }

    /**
     * @param sender
     */
    private void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @param recipient
     */
    private void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * @param message
     */
    private void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param supportDnd
     */
    private void setSupportDnd(boolean supportDnd) {
        this.supportDnd = supportDnd;
    }

    /**
     * @return String
     */
    public String getSender() {
        return sender;
    }

    /**
     * @return String
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return boolean
     */
    public boolean isSupportDnd() {
        return supportDnd;
    }

    /**
     * @return String
     */
    public String getSchedule() {
        return time;
    }

    /**
     * @param time
     */
    public void schedule(String time) {
        this.time = time;
    }

    public static class Builder {

        private String sender = "";
        private String recipient = "";
        private String message = "";
        private boolean supportDnd = false;
        private String time = "";

        /**
         * @param sender
         * @return Builder
         */
        public Builder setSender(@NonNull String sender) {
            this.sender = sender;
            return this;
        }

        /**
         * @param recipient
         * @return Builder
         */
        public Builder setRecipient(@NonNull String recipient) {
            this.recipient = recipient;
            return this;
        }

        /**
         * @param message
         * @return Builder
         */
        public Builder setMessage(@NonNull String message) {
            this.message = message;
            return this;
        }

        /**
         * @param supportDnd
         * @return Builder
         */
        public Builder isDndSupported(@NonNull boolean supportDnd) {
            this.supportDnd = supportDnd;
            return this;
        }

        /**
         * @param time
         * @return Builder
         */
        public Builder schedule(@NonNull String time) {
            this.time = time;
            return this;
        }

        /**
         * @return Sms
         */
        public Sms build() {
            Sms sms = new Sms();
            sms.setSender(this.sender);
            sms.setRecipient(this.recipient);
            sms.setMessage(this.message);
            sms.setSupportDnd(this.supportDnd);
            sms.schedule(this.time);
            return sms;
        }
    }
}
