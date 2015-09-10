/*
 * Copyright (C) 2015 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.twitter.sdk.android.tweetcomposer;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AppCardView extends LinearLayout {
    ImageView appImageView;
    ViewGroup appInfoLayout;
    TextView appInstallButton;
    TextView appNameView;
    TextView appStoreNameView;

    public AppCardView(Context context) {
        this(context, null);
    }

    public AppCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AppCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    void init(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        inflate(context, R.layout.tw__app_card, this);
        findSubviews();
        applyTheme();
    }

    void findSubviews() {
        appImageView = (ImageView) findViewById(R.id.tw__app_image);
        appNameView = (TextView) findViewById(R.id.tw__app_name);
        appStoreNameView = (TextView) findViewById(R.id.tw__app_store_name);
        appInstallButton = (TextView) findViewById(R.id.tw__app_install_button);
        appInfoLayout = (ViewGroup) findViewById(R.id.tw__app_info_layout);
    }

    void setCard(Card card) {
        setImage(Uri.parse(card.imageUri));
        setAppName(card.appName);
    }

    void setImage(Uri uri) {
        Picasso.with(getContext())
                .load(uri)
                .fit()
                .centerCrop()
                .into(appImageView);
    }

    void setAppName(String name) {
        appNameView.setText(name);
    }

    void applyTheme() {
        final int textColor = getResources().getColor(R.color.tw__deep_gray);
        appNameView.setTextColor(textColor);
        appStoreNameView.setTextColor(textColor);
        appInstallButton.setTextColor(textColor);
    }
}
