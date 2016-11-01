package cl.telematica.android.certamen2.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.adapters.GithubRepoAdapter;
import cl.telematica.android.certamen2.connection.HttpServerConnection;
import cl.telematica.android.certamen2.models.GithubRepo;
import cl.telematica.android.certamen2.presenters.ListPresenterImpl;
import cl.telematica.android.certamen2.presenters.contract.ListPresenter;
import cl.telematica.android.certamen2.views.ListView;

/**
 * Created by Erlend on 30.09.2016.
 */
public class ListFragment extends Fragment implements ListView {
    private String userName;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView;

    private ListPresenter mListPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        textView = (TextView)rootView.findViewById(R.id.textView3);
        textView.setText("Lista de repositorios del usuario " + userName);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        mListPresenter = new ListPresenterImpl(getActivity(), layoutManager, this);
        mListPresenter.inflateList(recyclerView, userName);

        return rootView;
    }

    @Override
    public void setUsername(String userName){
        this.userName = userName;
    }
}
