# QuickSms
#### An android library for QuickSms SMS gateway.

### Overview of QuickSms library
QuickSms library will help you achieve the following:

* Send SMS easily.
* Check Account Balance.
* Get Delivery Report.

Simply register at [quicksms1.com](http://quicksms1.com) and you are good to go!


## Using QuickSms library in your android application

Add this in your build.gradle
```groovy
implementation 'com.emmanuelkehinde:quicksms:1.0.0'
```

Initialize QuickSms with your Username and Password
```java
QuickSms quickSms = new QuickSms.Builder()
                .setUsername("YOUR_EMAIL")
                .setPassword("YOUR_PASSWORD")
                .build();
```

### Send SMS

Create an `Sms` Instance

```java
Sms sms = new Sms.Builder()
                .setSender("Tester")
                .setRecipient("08000000000")
                .setMessage("This is just a test")
                .build();
```

Using the `sms` instance as an argument

```java
quickSms.sendSms(sms)
                .addOnSuccessListener(new OnSuccessListener<SendSmsResult>() {
                    @Override
                    public void onSuccess(SendSmsResult result) {
                        showToastMessage(result.getMessageId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(QuickSmsException e) {
                        showToastMessage(e.getMessage());
                    }
                });
```

##### To specify Multiple Recipients
```java
Sms sms = new Sms.Builder()
                ...
                ...
                .setRecipient("08000000000,08000000001,08000000002")
                .build();
```

##### To specify to deliver to DND numbers
```java
Sms sms = new Sms.Builder()
                ...
                ...
                .isDndSupported(true)
                .build();
```

##### To schedule an sms for later delivery
```java
Sms sms = new Sms.Builder()
                ...
                ...
                .schedule("2018-10-01 12:30:00")
                .build();
```

### Check Account Balance
```java
 quickSms.getAccountBalance()
                .addOnSuccessListener(new OnSuccessListener<GetAccountBalanceResult>() {
                    @Override
                    public void onSuccess(GetAccountBalanceResult result) {
                        showToastMessage(result.getBalance().toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(QuickSmsException e) {
                        showToastMessage(e.getMessage());
                    }
                });
```

### Get Delivery Report

Using `messageID` as an argument

```java
quickSms.getDeliveryReport("45795")
                .addOnSuccessListener(new OnSuccessListener<GetDeliveryReportResult>() {
                    @Override
                    public void onSuccess(GetDeliveryReportResult result) {
                        showToastMessage(result.getStatus());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(QuickSmsException e) {
                        showToastMessage(e.getMessage());
                    }
                });
```

## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:


### License
```
   Copyright (C) 2018 Emmanuel Kehinde

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

### Contributing to Shutdown
All pull requests are welcome, make sure to follow the [contribution guidelines](CONTRIBUTING.md)
when you submit pull request.
