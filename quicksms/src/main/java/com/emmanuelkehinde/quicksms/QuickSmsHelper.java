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

import android.util.Log;

class QuickSmsHelper {

    /**
     * @param code
     * @return String
     */
    public static String getErrorMessageFromCode(String code) {
        String res;
        switch (code) {
            case QuickSmsConstants.ErrorCode.INCORRECT_USERNAME_OR_PASSWORD: {
                res = QuickSmsConstants.ErrorMessage.INCORRECT_USERNAME_OR_PASSWORD;
                break;
            }
            case QuickSmsConstants.ErrorCode.INSUFFICIENT_BALANCE: {
                res = QuickSmsConstants.ErrorMessage.INSUFFICIENT_BALANCE;
                break;
            }
            case QuickSmsConstants.ErrorCode.INVALID_MESSAGE: {
                res = QuickSmsConstants.ErrorMessage.INVALID_MESSAGE;
                break;
            }
            case QuickSmsConstants.ErrorCode.INVALID_RECIPIENT: {
                res = QuickSmsConstants.ErrorMessage.INVALID_RECIPIENT;
                break;
            }
            case QuickSmsConstants.ErrorCode.INVALID_SENDER: {
                res = QuickSmsConstants.ErrorMessage.INVALID_SENDER;
                break;
            }
            default: {
                res = "";
                break;
            }
        }

        return res;
    }

    /**
     * @param result
     * @return SendSmsResult
     */
    public static SendSmsResult getSmsResult(String result) {
        SendSmsResult sendSmsResult = new SendSmsResult();
        try {
            String[] splString = result.split(" ");
            String credits = splString[1];
            sendSmsResult.setTotalNumberOfSmsCreditsUsed(splString[1]);
            if (credits.equals("1")) {
                sendSmsResult.setMessageId(splString[3]);
            } else {
                String[] splNumber = splString[3].split("\\|");
                sendSmsResult.setMessageId(splNumber[0]);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return sendSmsResult;
    }
}
