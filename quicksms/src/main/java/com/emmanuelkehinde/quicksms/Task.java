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

import com.emmanuelkehinde.quicksms.listeners.OnFailureListener;
import com.emmanuelkehinde.quicksms.listeners.OnSuccessListener;

/**
 * @param <TResult>
 */
public class Task<TResult> {

    private OnSuccessListener<? super TResult> onSuccessListener;
    private OnFailureListener onFailureListener;

    /**
     * @param onSuccessListener
     * @return Task<TResult>
     */
    public Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.onSuccessListener = onSuccessListener;
        return this;
    }

    /**
     * @param onFailureListener
     * @return Task<TResult>
     */
    public Task<TResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    /**
     * @return OnSuccessListener<? super TResult>
     */
    protected OnSuccessListener<? super TResult> getSuccessListener () {
        return this.onSuccessListener;
    }

    /**
     * @return OnFailureListener
     */
    protected OnFailureListener getFailureListener () {
        return this.onFailureListener;
    }
}
