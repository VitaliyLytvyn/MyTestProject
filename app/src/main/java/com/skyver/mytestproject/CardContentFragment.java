/*
 * Copyright (C) 2015 The Android Open Source Project
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
 */

package com.skyver.mytestproject;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import me.pinxter.letters.Letters;
import timber.log.Timber;


public class CardContentFragment extends Fragment {

    private static List<Cards> mCards;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareCards();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(
                R.layout.card_view_fragm, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /////////////////////////////////////////////////////////////

        Letters letters = new Letters(getContext(), "eventName", new ArrayList<Object>(mCards));
        //Letters letters = new Letters(getContext(), "name", list);
        letters.setOnSelect(new Letters.OnSelect() {
            @Override
            public void onSelect(int index, String letter) {

                Timber.d("onSelect() index:"+index+" letter: "+letter);
                ((RecyclerView) view.findViewById(R.id.recyclerView))
                        .getLayoutManager().scrollToPosition(index);
            }
        });
        ((FrameLayout) view.findViewById(R.id.frameLetters)).removeAllViews();
        ((FrameLayout) view.findViewById(R.id.frameLetters)).addView(letters.getLetterLayout());

        // Adapters:
        //((TextView) rootView.findViewById(R.id.item_users_letter)).setText(letters.getLetter(position));

        /////////////////////////////////////////////////////////////

        return view;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView time;
        public TextView host;
        public TextView place;
        public TextView address;
        public TextView descriptin;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));

            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title_top);
            time = (TextView) itemView.findViewById(R.id.card_title_top2);
            host = (TextView) itemView.findViewById(R.id.card_title_top3);
            place = (TextView) itemView.findViewById(R.id.card_title_top4);
            address = (TextView) itemView.findViewById(R.id.card_title);
            descriptin = (TextView) itemView.findViewById(R.id.card_text);

            // Adding Snackbar to Action Button inside card
            Button button1 = (Button)itemView.findViewById(R.id.action_button);
            button1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "More is pressed", Snackbar.LENGTH_LONG).show();
                }
            });

            Button button2 = (Button) itemView.findViewById(R.id.action_button2);
            button2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Restore is pressed", Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

     //Adapter to display recycler view.
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 3;

        public ContentAdapter(Context context){};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mCards.get(position).getPicture());
            holder.name.setText(mCards.get(position).getEventName());
            holder.time.setText(mCards.get(position).getEventTime());
            holder.host.setText(mCards.get(position).getEventHost());
            holder.place.setText(mCards.get(position).getEventPlace());
            holder.address.setText(mCards.get(position).getEventAddress());
            holder.descriptin.setText(mCards.get(position).getEventDescription());
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

    private void prepareCards(){

        Resources resources = getResources();
        String[] mNames = resources.getStringArray(R.array.names);
        String[] mTimes = resources.getStringArray(R.array.time);
        String[] mHosts = resources.getStringArray(R.array.host);
        String[] mPlaces = resources.getStringArray(R.array.places);
        String[] mAddresses = resources.getStringArray(R.array.address);
        TypedArray a = resources.obtainTypedArray(R.array.place_picture);
        Drawable[] mPlacePictures = new Drawable[a.length()];
        String description = resources.getString(R.string.detail_desc);
        for (int i = 0; i < mPlacePictures.length; i++) {
            mPlacePictures[i] = a.getDrawable(i);
        }
        a.recycle();

        mCards = new ArrayList<>();
        for (int i = 0; i < mPlaces.length; i++) {
            mCards.add(new Cards(
                    mNames[i],
                    mTimes[i],
                    mHosts[i],
                    mPlaces[i],
                    mAddresses[i],
                    description,
                    mPlacePictures[i]
                    ));
        }
    }
}

