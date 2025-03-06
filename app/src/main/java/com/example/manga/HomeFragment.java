package com.example.manga;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RviewInterface {

    ArrayList<Model> models = new ArrayList<>();
    int[] modelImages = {R.mipmap.gojo, R.mipmap.naruto, R.mipmap.luffy, R.mipmap.ichigo, R.mipmap.mainlogo,
            R.mipmap.gojo, R.mipmap.naruto, R.mipmap.luffy, R.mipmap.ichigo, R.mipmap.mainlogo,
            R.mipmap.gojo, R.mipmap.naruto, R.mipmap.luffy };
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.mRecyclerView);

        setUp(); // populate data

        Radapter radapter = new Radapter(requireContext(), models,this);
        recyclerView.setAdapter(radapter);

        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return view;
    }


    private void setUp() {
        String[] names = getResources().getStringArray(R.array.fullNames);
        String[] descriptions = getResources().getStringArray(R.array.lilDescriptions);

        for (int i = 0; i < names.length; i++) {
            models.add(new Model(names[i], modelImages[i], descriptions[i]));
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(requireContext(), HomeFragmentTwo.class);
        i.putExtra("Name", models.get(position).getName());
        i.putExtra("Image", models.get(position).getImage());
        i.putExtra("Description", models.get(position).getDescription());
        startActivity(i);
    }


}
