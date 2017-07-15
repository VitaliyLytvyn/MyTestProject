/*
* Copyright (C) 2014 The Android Open Source Project
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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import me.pinxter.letters.Letters;
import timber.log.Timber;


public class RecyclerViewFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragment";
    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;

    protected User[] mDatasetU;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /////////////////////////////////////////////////////////////
        // View:

        List<User> list = Arrays.asList(mDatasetU);

        Letters letters = new Letters(getContext(), "name", new ArrayList<Object>(list));
        letters.setOnSelect(new Letters.OnSelect() {
            @Override
            public void onSelect(int index, String letter) {

                Timber.d("onSelect() index:"+index+" letter: "+letter);
                ((RecyclerView) rootView.findViewById(R.id.recyclerView))
                        .getLayoutManager().scrollToPosition(index);
            }
        });
        ((FrameLayout) rootView.findViewById(R.id.frameLetters)).removeAllViews();
        ((FrameLayout) rootView.findViewById(R.id.frameLetters)).addView(letters.getLetterLayout());

        // Adapters:
        //((TextView) rootView.findViewById(R.id.item_users_letter)).setText(letters.getLetter(position));

        /////////////////////////////////////////////////////////////

        mAdapter = new CustomAdapter(mDatasetU);

        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setHasFixedSize(true);


        return rootView;
    }

    private void initDataset() {

        mDatasetU = new User[]{new User("Stev", "Buscer"),
        new User("Don", "Amery"),
        new User("Ken", "Flinn"),
        new User("Ron", "Orey"),
        new User("Bob", "Anderson"),
        new User("Fob", "Lnderson"),
        new User("Fsab", "Jnderson"),
        new User("Cawb", "Ionderson"),
        new User("Aneob", "Jenderson"),
        new User("Verob", "Kunderson"),
        new User("Fob", "Lnderson"),
        new User("Nob", "Kderson"),
        new User("Irub", "Zuderson"),
        new User("Autb", "Myderson"),
        new User("Outob", "Awderson"),
        new User("Pnuob", "Yinderson"),
        new User("Croob", "Edderson"),
        new User("Zatob", "Eyoderson"),
        new User("Etanob", "Xuderson"),
        new User("Kwob", "Verderson"),
        new User("Kob", "Onderson"),
        new User("Iaob", "Otnderson"),
        new User("Mob", "Underson"),
        new User("Zub", "Bederson"),
        new User("Cuob", "Ziderson"),
        new User("Lob", "Noderson"),
        new User("Kib", "Ederson"),
        new User("Exyb", "Brderson"),
        new User("Prob", "ZAnderson"),
        new User("Uarob", "Banderson"),
        new User("Herob", "Nenderson"),
        new User("Lurob", "Munderson"),
        new User("Orob", "Qwenderson"),
        new User("Krob", "Wenderson"),
        new User("Barob", "Aurnderson"),
        new User("Feturob", "Nunderson"),
        new User("Vrob", "Dunderson"),
        new User("Zob", "Qenderson"),
        new User("Azob", "Enderson"),
        new User("Tob", "Venderson"),
        new User("Ostin", "Pawers"),
        new User("Larry", "York"),
        new User("Adam", "Born") };

    }
}
