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
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuickSms {

    private static final String BALANCE_QUERY = "1";
    private static final String CONVERT_QUERY = "1";
    private static final String REPORT_QUERY = "1";
    private static final String ROUTE_QUERY_DND = "1";
    private static final String ROUTE_QUERY_NO_DND = "0";

    private String username;
    private String password;

    private Task<SendSmsResult> sendSmsTask;
    private Task<GetAccountBalanceResult> getAccountBalanceTask;
    private Task<GetDeliveryReportResult> getDeliveryReportTask;

    public QuickSms() {

    }

    /**
     * @param username
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param sms
     * @return Task<SendSmsResult>
     */
    public Task<SendSmsResult> sendSms(Sms sms) {
        if (sendSmsTask == null) {
            sendSmsTask = new Task<>();
        }
        QuickSmsService quickSmsService = QuickSmsApiClient.createService(QuickSmsService.class);
        Call<String> balanceCall =  quickSmsService.sendSms(
                this.username, this.password,sms.getSender(),sms.getMessage(),sms.getRecipient(),
                BALANCE_QUERY,sms.getSchedule(),CONVERT_QUERY,REPORT_QUERY,
                sms.isSupportDnd() ? ROUTE_QUERY_DND: ROUTE_QUERY_NO_DND
        );
        balanceCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                String result = response.body();
                if (result != null) {
                    if (result.contains("OK")) {
                        SendSmsResult sendSmsResult = QuickSmsHelper.getSmsResult(result);
                        sendSmsTask.getSuccessListener().onSuccess(sendSmsResult);
                    } else {
                        String message = QuickSmsHelper.getErrorMessageFromCode(result);
                        QuickSmsException quickSmsException = new QuickSmsException(message);
                        sendSmsTask.getFailureListener().onFailure(quickSmsException);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                QuickSmsException quickSmsException = new QuickSmsException(QuickSmsConstants.COMMON_ERROR_MESSAGE, t);
                sendSmsTask.getFailureListener().onFailure(quickSmsException);
            }
        });
        return sendSmsTask;
    }

    /**
     * @return Task<GetAccountBalanceResult>
     */
    public Task<GetAccountBalanceResult> getAccountBalance() {
        if (getAccountBalanceTask == null) {
            getAccountBalanceTask = new Task<>();
        }
        QuickSmsService quickSmsService = QuickSmsApiClient.createService(QuickSmsService.class);
        Call<String> balanceCall =  quickSmsService.getBalance(this.username,this.password,BALANCE_QUERY);
        balanceCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                String result = response.body();
                if (result != null) {
                    if (result.equals(QuickSmsConstants.ErrorCode.INVALID_SENDER)) {
                        QuickSmsException quickSmsException = new QuickSmsException(QuickSmsConstants.COMMON_ERROR_MESSAGE);
                        getAccountBalanceTask.getFailureListener().onFailure(quickSmsException);
                        return;
                    }
                    GetAccountBalanceResult balanceResult = new GetAccountBalanceResult();
                    balanceResult.setBalance(Double.parseDouble(result));
                    getAccountBalanceTask.getSuccessListener().onSuccess(balanceResult);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                QuickSmsException quickSmsException = new QuickSmsException(QuickSmsConstants.COMMON_ERROR_MESSAGE, t);
                getAccountBalanceTask.getFailureListener().onFailure(quickSmsException);
            }
        });
        return getAccountBalanceTask;
    }

    /**
     * @param messageId
     * @return Task<GetDeliveryReportResult>
     */
    public Task<GetDeliveryReportResult> getDeliveryReport(String messageId) {
        if (getDeliveryReportTask == null) {
            getDeliveryReportTask = new Task<>();
        }
        QuickSmsService quickSmsService = QuickSmsApiClient.createService(QuickSmsService.class);
        Call<String> balanceCall =  quickSmsService.getDeliveryReport(
                this.username, this.password, messageId
        );
        balanceCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                String result = response.body();
                if (result != null) {
                    if (result.isEmpty()) {
                        QuickSmsException quickSmsException = new QuickSmsException(QuickSmsConstants.COMMON_ERROR_MESSAGE);
                        getDeliveryReportTask.getFailureListener().onFailure(quickSmsException);
                        return;
                    }
                    GetDeliveryReportResult reportResult = new GetDeliveryReportResult();
                    reportResult.setStatus(result);
                    getDeliveryReportTask.getSuccessListener().onSuccess(reportResult);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                QuickSmsException quickSmsException = new QuickSmsException(
                        QuickSmsConstants.COMMON_ERROR_MESSAGE, t);
                getDeliveryReportTask.getFailureListener().onFailure(quickSmsException);
            }
        });
        return getDeliveryReportTask;
    }

    public static class Builder {

        private String username = "";
        private String password = "";

        /**
         * @param username
         * @return Builder
         */
        public Builder setUsername(@NonNull String username) {
            this.username = username;
            return this;
        }

        /**
         * @param password
         * @return Builder
         */
        public Builder setPassword(@NonNull String password) {
            this.password = password;
            return this;
        }

        /**
         * @return QuickSms
         */
        public QuickSms build() {
            QuickSms quickSms = new QuickSms();
            quickSms.setUsername(this.username);
            quickSms.setPassword(this.password);
            return quickSms;
        }
    }

}
