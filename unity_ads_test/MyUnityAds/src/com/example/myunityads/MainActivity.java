package com.example.myunityads;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.UnityAds;


public class MainActivity extends ActionBarActivity implements IUnityAdsListener{
	
	private Button NextButton = null;
	private MainActivity self = null;
	private Boolean adsOn = false;
	private int numberOfmyItem = 0;
	private TextView myItem = null;
	private String _exampleAppLogTag = "UnityAdsExample";
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(_exampleAppLogTag, "UnityAdsTestStartActivity->onCreate()");
		super.onCreate(savedInstanceState);
		
		self = this;
		setContentView(R.layout.activity_main);
		
		
		UnityAds.setDebugMode(true);
		UnityAds.setTestMode(true);
		
		myItem = (TextView)findViewById(R.id.myItem);
		myItem.setText(String.valueOf(numberOfmyItem));
		NextButton = (Button)findViewById(R.id.nextButton);
		NextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				UnityAds.init(self, "131624241", self);
				UnityAds.setListener(self);
				
				AlertDialog diaBox = createDialogBox();
				diaBox.show();
				
			
			}
			
		});
		
	}


	protected AlertDialog createDialogBox() {
		
		AlertDialog adsDialogBox = new AlertDialog.Builder(this)
			.setTitle("Notice")
			.setMessage("Would you like to watch an advertisement?")
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					adsOn = true;
					Toast.makeText(getApplicationContext(), "yes clicked", Toast.LENGTH_LONG).show();
										
					if(UnityAds.canShow() && UnityAds.canShowAds()) {
						
							Map<String, Object> optionsMap = new HashMap<String, Object>();
							optionsMap.put(UnityAds.UNITY_ADS_OPTION_NOOFFERSCREEN_KEY, true);
							optionsMap.put(UnityAds.UNITY_ADS_OPTION_OPENANIMATED_KEY, false);
							optionsMap.put(UnityAds.UNITY_ADS_OPTION_GAMERSID_KEY, "gom");
							optionsMap.put(UnityAds.UNITY_ADS_OPTION_MUTE_VIDEO_SOUNDS, false);
							optionsMap.put(UnityAds.UNITY_ADS_OPTION_VIDEO_USES_DEVICE_ORIENTATION, false);
							
							UnityAds.show(optionsMap);
						  
						}

				}
			})
			
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					adsOn = false;
					Toast.makeText(getApplicationContext(), "no clicked", Toast.LENGTH_LONG).show();
						
				}
			})
			
			.create();
		
		return adsDialogBox;
	}
	
	
	@Override
	public void onFetchCompleted() {
						
		Log.d(_exampleAppLogTag, "UnityAdsTestStartActivity->onFetchCompleted()");
		if(adsOn==true) {
			Log.d(_exampleAppLogTag, "UnityAdsTestStartActivity->onFetchCompleted(),adsOn=true");
			
			// Open with options test
			Map<String, Object> optionsMap = new HashMap<String, Object>();
			optionsMap.put(UnityAds.UNITY_ADS_OPTION_NOOFFERSCREEN_KEY, true);
			optionsMap.put(UnityAds.UNITY_ADS_OPTION_OPENANIMATED_KEY, false);
			optionsMap.put(UnityAds.UNITY_ADS_OPTION_GAMERSID_KEY, "gom");
			optionsMap.put(UnityAds.UNITY_ADS_OPTION_MUTE_VIDEO_SOUNDS, false);
			optionsMap.put(UnityAds.UNITY_ADS_OPTION_VIDEO_USES_DEVICE_ORIENTATION, false);
			
			UnityAds.show(optionsMap);
			// Open without options (defaults)
			//UnityAds.show();
		}
		adsOn=false;
			
	}


	@Override
	public void onFetchFailed() {
		// TODO Auto-generated method stub
		
		Log.d(_exampleAppLogTag, "onFetchFailed()");
		
	}


	@Override
	public void onHide() {
		// TODO Auto-generated method stub
		
		Log.d(_exampleAppLogTag, "onHide()");
		
	}


	@Override
	public void onShow() {
		// TODO Auto-generated method stub
		
		Log.d(_exampleAppLogTag, "onShow()");
		
	}


	@Override
	public void onVideoCompleted(String Item, boolean skipped) {
		// TODO Auto-generated method stub
		myItem = (TextView)findViewById(R.id.myItem);
		
		Log.d(_exampleAppLogTag, "current item :" + Item);
		
		if(skipped) {
    		Log.d(_exampleAppLogTag, "Video was skipped!");
    	}
		else {
			numberOfmyItem++;
			myItem.setText(String.valueOf(numberOfmyItem));
			Log.d(_exampleAppLogTag, "Video watching completed!");
		}
		
	}


	@Override
	public void onVideoStarted() {
		// TODO Auto-generated method stub
		
		Log.d(_exampleAppLogTag, "onVideoStarted()");
		
	}
	
	@Override
    public void onResume () {
    	Log.d(_exampleAppLogTag, "UnityAdsTestStartActivity->onResume()");
    	super.onResume();
    	
   		UnityAds.changeActivity(this);
   		UnityAds.setListener(this);
    }
	
	@Override
	protected void onDestroy() {
    	Log.d(_exampleAppLogTag, "UnityAdsTestStartActivity->onDestroy()");
    	super.onDestroy();		
	}
	

	
}
