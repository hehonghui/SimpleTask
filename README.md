# SimpleTask
A Simple Task Library to use AsyncTask easier.Highly inspired by [TinyTask](https://github.com/inaka/TinyTask),
but with a simpler API.

## Abstract
Android's AsyncTasks are highly criticized for being bad, unreliable, outdated, etc. Are they perfect? No. Do we have better alternatives? Sure, but sometimes all we want is a quick and simple way to run something in the background.

## What is it, really?
Just a simple wrapper around an AsyncTask, with a easier API.
See the case below : 

```
        new AsyncTask<Void, Void, String>() {

            protected void onPreExecute() {
            };

            @Override
            protected String doInBackground(Void... params) {
                return null;
            }

            protected void onPostExecute(String result) {
            };
        }.execute();
```        
The two Void generic param is not necessary, but how can we simplify the work ? SimpleTask is the Answer.     

## Code Example

```
        SimpleTask.newTask(new Worker<String>() {

            @Override
            protected void beforeExecute() {
              // execute before doInBackground. ( UI Thread )
            }

            @Override
            protected String doInBackground() {
                // do your work in background.
                return null;
            }

            @Override
            protected void afterExecute(String result) {
              // execute after doInBackground. ( UI Thread )
            }

        }).execute();
```

## Different with TinyTask
* the api is more readble ;
* the api is simpler;
* type-safe, no warnning, see TinyTask : ![img](http://img.blog.csdn.net/20150307095409882).     
