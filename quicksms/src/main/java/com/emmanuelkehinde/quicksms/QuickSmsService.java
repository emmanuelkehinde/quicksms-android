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

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface QuickSmsService {

    /**
     * @param username
     * @param password
     * @param sender
     * @param message
     * @param recipient
     * @param balance
     * @param schedule
     * @param convert
     * @param report
     * @param route
     * @return Call<String>
     */
    @GET("sendsms.php")
    Call<String> sendSms(
            @Query(QuickSmsConstants.Query.USERNAME) String username,
            @Query(QuickSmsConstants.Query.PASSWORD) String password,
            @Query(QuickSmsConstants.Query.SENDER) String sender,
            @Query(QuickSmsConstants.Query.MESSAGE) String message,
            @Query(QuickSmsConstants.Query.RECIPIENT) String recipient,
            @Query(QuickSmsConstants.Query.BALANCE) String balance,
            @Query(QuickSmsConstants.Query.SCHEDULE) String schedule,
            @Query(QuickSmsConstants.Query.CONVERT) String convert,
            @Query(QuickSmsConstants.Query.REPORT) String report,
            @Query(QuickSmsConstants.Query.ROUTE) String route
    );

    /**
     * @param username
     * @param password
     * @param balance
     * @return Call<String>
     */
    @GET("sendsms.php")
    Call<String> getBalance(
            @Query(QuickSmsConstants.Query.USERNAME) String username,
            @Query(QuickSmsConstants.Query.PASSWORD) String password,
            @Query(QuickSmsConstants.Query.BALANCE) String balance
    );

    /**
     * @param username
     * @param password
     * @param msgId
     * @return Call<String>
     */
    @GET("getdelivery.php")
    Call<String> getDeliveryReport(
            @Query(QuickSmsConstants.Query.USERNAME) String username,
            @Query(QuickSmsConstants.Query.PASSWORD) String password,
            @Query(QuickSmsConstants.Query.MESSAGE_ID) String msgId
    );

}
