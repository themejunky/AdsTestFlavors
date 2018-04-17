package com.personalstyler.client.mylibrary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;

import java.util.List;

/**
 * Created by ThemeJunky on 4/17/2018.
 */

public class ModuleAdsManager2 {
    private AdLoader adLoader;
    public boolean isLoaded=false;
    View mAd_NativeAdmob;
    public ModuleAdsManager2(Context activity, final View view, String idUnitAdmob, int type ){
        Log.d("dasdadadas","0 : "+view.getTag());
        final LayoutInflater inflater = (LayoutInflater) activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );



        LayoutInflater factory = LayoutInflater.from(activity);
        View DialogInflateView = factory.inflate(R.layout.ads_native_admob, null);
        mAd_NativeAdmob = DialogInflateView.findViewById(R.id.containerAdmob);

        if(type== 2){

            Log.d("infoadmob","initAdmobNativeAdvance 1-2");

            adLoader = new AdLoader.Builder(activity, idUnitAdmob)
                    .forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
                        @Override
                        public void onContentAdLoaded(NativeContentAd contentAd) {

                            RelativeLayout frameLayout = view.findViewById(R.id.containerAdmobNativeAds);
                            NativeContentAdView adView = (NativeContentAdView)inflater.inflate(R.layout.ad_content, null);
                            populateContentAdView(contentAd, adView);
                            frameLayout.removeAllViews();
                            frameLayout.addView(adView);

                        }
                    })
                    .withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(int errorCode) {

                            Log.d("infoadmob","initAdmobNativeAdvance 1-2-2 : "+errorCode);


                            switch (errorCode) {
                                case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                                    break;
                                case AdRequest.ERROR_CODE_INVALID_REQUEST:
                                    break;
                                case AdRequest.ERROR_CODE_NETWORK_ERROR:
                                    break;
                                case AdRequest.ERROR_CODE_NO_FILL:
                                    break;
                                default:
                            }
                        }
                    })
                    .withNativeAdOptions(new NativeAdOptions.Builder()
                            // Methods in the NativeAdOptions.Builder class can be
                            // used here to specify individual options settings.
                            .build())
                    .build();

            Log.d("infoadmob","initAdmobNativeAdvance 1-2-3");


        }else if(type==1){

            Log.d("dasdadadas","initAdmobNativeAdvance 1-1");


            adLoader = new AdLoader.Builder(activity, idUnitAdmob)
                    .forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
                        @Override
                        public void onAppInstallAdLoaded(NativeAppInstallAd appInstallAd) {

                            Log.d("dasdadadas","1 : "+view.getTag());

                            RelativeLayout frameLayout = view.findViewById(R.id.containerAdmobNativeAds);
                            NativeAppInstallAdView adView = (NativeAppInstallAdView)inflater.inflate(R.layout.ad_app_install, null);
                            populateAppInstallAdView(appInstallAd, adView);
                            frameLayout.removeAllViews();
                            frameLayout.addView(adView);

                        }
                    })
                    .withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            Log.d("infoadmob","initAdmobNativeAdvance 1-1-2 : "+errorCode);
                            switch (errorCode) {
                                case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                                    break;
                                case AdRequest.ERROR_CODE_INVALID_REQUEST:
                                    break;
                                case AdRequest.ERROR_CODE_NETWORK_ERROR:
                                    break;
                                case AdRequest.ERROR_CODE_NO_FILL:
                                    break;
                                default:
                            }
                        }
                    })
                    .withNativeAdOptions(new NativeAdOptions.Builder()
                            // Methods in the NativeAdOptions.Builder class can be
                            // used here to specify individual options settings.
                            .build())
                    .build();
        }


        adLoader.loadAd(new AdRequest.Builder().build());


    }

    private void populateAppInstallAdView(NativeAppInstallAd nativeAppInstallAd,
                                          NativeAppInstallAdView adView) {

        adView.setHeadlineView(adView.findViewById(R.id.appinstall_headline));
        // adView.setBodyView(adView.findViewById(R.id.appinstall_body));
        adView.setCallToActionView(adView.findViewById(R.id.appinstall_call_to_action));
        adView.setIconView(adView.findViewById(R.id.appinstall_app_icon));
        // adView.setPriceView(adView.findViewById(R.id.appinstall_price));
        adView.setStarRatingView(adView.findViewById(R.id.appinstall_stars));
        adView.setStoreView(adView.findViewById(R.id.appinstall_store));
        adView.setMediaView((MediaView) adView.findViewById(R.id.appinstall_media));
        //adView.setIconView(adView.findViewById(R.id.appinstall_image));


        if(adView.getHeadlineView()!=null){
            isLoaded=true;
            //      listenerAds.loadNativeAds("admob");
        }


        // Some assets are guaranteed to be in every NativeAppInstallAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAppInstallAd.getHeadline());
        //  ((TextView) adView.getBodyView()).setText(nativeAppInstallAd.getBody());
        ((Button) adView.getCallToActionView()).setText(nativeAppInstallAd.getCallToAction());
        ((ImageView) adView.getIconView()).setImageDrawable(
                nativeAppInstallAd.getIcon().getDrawable());

        MediaView mediaView = adView.findViewById(R.id.appinstall_media);
        //ImageView mainImageView = adView.findViewById(R.id.appinstall_image);

       /* if (nativeAppInstallAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAppInstallAd.getPrice());
        }*/

        if (nativeAppInstallAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAppInstallAd.getStore());
        }

        if (nativeAppInstallAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAppInstallAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAppInstallAd);
    }
    private void populateContentAdView(NativeContentAd nativeContentAd,
                                       NativeContentAdView adView) {


        adView.setHeadlineView(adView.findViewById(R.id.contentad_headline));
        adView.setImageView(adView.findViewById(R.id.contentad_image));
        adView.setBodyView(adView.findViewById(R.id.contentad_body));
        adView.setCallToActionView(adView.findViewById(R.id.contentad_call_to_action));
        adView.setLogoView(adView.findViewById(R.id.contentad_logo));
        // adView.setAdvertiserView(adView.findViewById(R.id.contentad_advertiser));

        // Some assets are guaranteed to be in every NativeContentAd.
        ((TextView) adView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) adView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
        //((TextView) adView.getAdvertiserView()).setText(nativeContentAd.getAdvertiser());
        if(adView.getHeadlineView()!=null){
            isLoaded=true;
            // listenerAds.loadNativeAds("admob");
        }

        List<NativeAd.Image> images = nativeContentAd.getImages();

        if (images.size() > 0) {
            ((ImageView) adView.getImageView()).setImageDrawable(images.get(0).getDrawable());
        }

        // Some aren't guaranteed, however, and should be checked.
        NativeAd.Image logoImage = nativeContentAd.getLogo();

        if (logoImage == null) {
            Log.d("infoicon"," nu Este");
            adView.getLogoView().setVisibility(View.INVISIBLE);
        } else {
            Log.d("infoicon","  Este");
            ((ImageView) adView.getLogoView()).setImageDrawable(logoImage.getDrawable());
            adView.getLogoView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeContentAd);
    }

    public View getAds() {
        return mAd_NativeAdmob;
    }
}
