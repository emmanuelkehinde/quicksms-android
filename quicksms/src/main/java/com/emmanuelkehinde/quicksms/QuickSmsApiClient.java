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

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * API Client for QuickSms
 */
class QuickSmsApiClient {

    private static final String BASE_URL = "http://www.quicksms1.com/api/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES);

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(ScalarsConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    /**
     * @param serviceClass
     * @param <S>
     * @return QuickSmsService
     */
    protected static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
