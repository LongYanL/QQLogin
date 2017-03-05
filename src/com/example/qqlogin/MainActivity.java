package com.example.qqlogin;


import org.json.JSONObject;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
    private String APP_ID="1105924500";
 	private Tencent mtencent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	   mtencent=Tencent.createInstance(APP_ID,this);
       Button bt =  (Button)findViewById(R.id.Bnt_activity_button);
       bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mtencent.login(MainActivity.this, "All", loginListener);
		}
	});
	}
	IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
        }
    };
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {

            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {
           Log.i("miaojx",values.toString());
        }

        @Override
        public void onError(UiError e) {          
            Log.i("miaojx",e.toString());
        }

        @Override
        public void onCancel() {

        }
    }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    Tencent.onActivityResultData(requestCode,resultCode,data,loginListener);

}
}
