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

class QuickSmsConstants {

    public static String COMMON_ERROR_MESSAGE = "An error has occurred, check your internet connection and try again";

    public static class Query {
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String SENDER = "sender";
        public static final String MESSAGE = "message";
        public static final String RECIPIENT = "recipient";
        public static final String BALANCE = "balance";
        public static final String SCHEDULE = "schedule";
        public static final String CONVERT = "convert";
        public static final String REPORT = "report";
        public static final String ROUTE = "route";
        public static final String MESSAGE_ID = "msgid";
    }

    public static class ErrorCode {
        public static final String INCORRECT_USERNAME_OR_PASSWORD = "1001";
        public static final String INVALID_SENDER = "1002";
        public static final String INVALID_MESSAGE = "1003";
        public static final String INVALID_RECIPIENT = "1004";
        public static final String INSUFFICIENT_BALANCE = "1005";
    }

    public static class ErrorMessage {
        public static final String INCORRECT_USERNAME_OR_PASSWORD = "Incorrect Username or Password specified";
        public static final String INVALID_SENDER = "Invalid Sender ID";
        public static final String INVALID_MESSAGE = "Invalid Message";
        public static final String INVALID_RECIPIENT = "Invalid Recipient(s) Number";
        public static final String INSUFFICIENT_BALANCE = "Insufficient Balance";
    }
}
