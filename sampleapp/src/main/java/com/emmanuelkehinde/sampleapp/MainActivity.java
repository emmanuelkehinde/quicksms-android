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

package com.emmanuelkehinde.sampleapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emmanuelkehinde.quicksms.GetAccountBalanceResult;
import com.emmanuelkehinde.quicksms.GetDeliveryReportResult;
import com.emmanuelkehinde.quicksms.QuickSms;
import com.emmanuelkehinde.quicksms.QuickSmsException;
import com.emmanuelkehinde.quicksms.SendSmsResult;
import com.emmanuelkehinde.quicksms.Sms;
import com.emmanuelkehinde.quicksms.listeners.OnFailureListener;
import com.emmanuelkehinde.quicksms.listeners.OnSuccessListener;

public class MainActivity extends AppCompatActivity {


    private TextInputEditText edtSender;
    private TextInputEditText edtMessage;
    private TextInputEditText edtRecipient;
    private Button btnSend;
    private QuickSms quickSms;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSender = findViewById(R.id.edt_sender);
        edtMessage = findViewById(R.id.edt_message);
        edtRecipient = findViewById(R.id.edt_recipient);
        btnSend = findViewById(R.id.btn_send_message);
        progressDialog = new ProgressDialog(this);

        quickSms = new QuickSms.Builder()
                .setUsername("test@yopmail.com")
                .setPassword("1234")
                .build();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms();
            }
        });

    }

    private void sendSms() {
        Sms sms = new Sms.Builder()
                .setSender(edtSender.getText().toString())
                .setRecipient(edtRecipient.getText().toString())
                .setMessage(edtMessage.getText().toString())
                .isDndSupported(true)
                .build();

        showProgress("Sending message...");
        quickSms.sendSms(sms)
                .addOnSuccessListener(new OnSuccessListener<SendSmsResult>() {
                    @Override
                    public void onSuccess(SendSmsResult result) {
                        hideProgress();
                        showToastMessage(result.getMessageId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(QuickSmsException e) {
                        hideProgress();
                        showToastMessage(e.getMessage());
                    }
                });
    }

    private void getAccountBalance() {
        showProgress("Checking balance...");
        quickSms.getAccountBalance()
                .addOnSuccessListener(new OnSuccessListener<GetAccountBalanceResult>() {
                    @Override
                    public void onSuccess(GetAccountBalanceResult result) {
                        hideProgress();
                        showToastMessage(result.getBalance().toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(QuickSmsException e) {
                        hideProgress();
                        showToastMessage(e.getMessage());
                    }
                });
    }

    private void getDeliveryReport() {
        showProgress("Checking balance...");
        quickSms.getDeliveryReport("45795")
                .addOnSuccessListener(new OnSuccessListener<GetDeliveryReportResult>() {
                    @Override
                    public void onSuccess(GetDeliveryReportResult result) {
                        hideProgress();
                        showToastMessage(result.getStatus());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(QuickSmsException e) {
                        hideProgress();
                        showToastMessage(e.getMessage());
                    }
                });
    }

    private void showProgress(String msg) {
        progressDialog.setCancelable(false);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    private void hideProgress() {
        progressDialog.hide();
    }

    private void showToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
