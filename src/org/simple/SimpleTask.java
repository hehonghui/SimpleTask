/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Umeng, Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.simple;

import android.os.AsyncTask;

@SuppressWarnings({
        "unchecked", "rawtypes"
})
public final class SimpleTask<T> {
    static SimpleTask sTask;
    private Worker<T> mWorker;

    private SimpleTask(Worker<T> worker) {
        mWorker = worker;
    }

    /**
     * @param worker
     * @return
     */
    public static SimpleTask newTask(Worker worker) {
        if (sTask == null) {
            sTask = new SimpleTask(worker);
        } else {
            sTask.mWorker = worker;
        }

        return sTask;
    }

    public void execute() {
        new InternalTask().execute();
    }

    /**
     * @author mrsimple
     * @param <Result> the type of the Result
     */
    private class InternalTask<Result> extends AsyncTask<Void, Void, Result> {

        @Override
        protected void onPreExecute() {
            mWorker.beforeExecute();
        }

        @Override
        protected void onPostExecute(Result result) {
            mWorker.afterExecute((T) result);
        }

        @Override
        protected Result doInBackground(Void... params) {
            return (Result) mWorker.doInBackground();
        }
    } // end of InternalTask

}
