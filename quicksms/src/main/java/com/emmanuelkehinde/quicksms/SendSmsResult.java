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

/**
 * POJO representing result from Send Sms request
 */
public class SendSmsResult {

    private String totalNumberOfSmsCreditsUsed;
    private String messageId;

    public SendSmsResult() {
    }

    /**
     * @return String
     */
    public String getTotalNumberOfSmsCreditsUsed() {
        return totalNumberOfSmsCreditsUsed;
    }

    /**
     * @param totalNumberOfSmsCreditsUsed
     */
    protected void setTotalNumberOfSmsCreditsUsed(String totalNumberOfSmsCreditsUsed) {
        this.totalNumberOfSmsCreditsUsed = totalNumberOfSmsCreditsUsed;
    }

    /**
     * @return String
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param messageId
     */
    protected void setMessageId(String messageId) {
        this.messageId = messageId;
    }

}
