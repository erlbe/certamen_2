package cl.telematica.android.certamen2;

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

import cl.telematica.android.certamen2.adapters.GithubRepoAdapter;
import cl.telematica.android.certamen2.connection.HttpServerConnection;
import cl.telematica.android.certamen2.models.GithubRepo;

/**
 * Created by Erlend on 30.09.2016.
 */
public class ListFragment extends Fragment {
    String userName;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private TextView textView;

    public ListFragment(){
    }

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

        /*
        // Hardcoded data without internet connection.
        adapter = new GithubRepoAdapter(getRepoListDummyData());
        recyclerView.setAdapter(adapter);
        */

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
            }

            @Override
            protected String doInBackground(Void... voids) {
                // FIXME: Replace link with: "https://api.github.com/users/"+username+"/repos"
                String result = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/57eee3822600009324111202", 15000);
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result != null){
                    System.out.println(result);

                    // Specify the adapter
                    adapter = new GithubRepoAdapter(getRepoList(result), userName);
                    recyclerView.setAdapter(adapter);
                }
            }
        };
        task.execute();

        return rootView;
    }

    private List<GithubRepo> getRepoListDummyData(){
        List<GithubRepo> repos = new ArrayList<>();
        repos.add(new GithubRepo("Number 1", "Descriptioneeien", "Updatednever"));
        repos.add(new GithubRepo("Number 2", "Descriptioen", "Updated right now"));
        return repos;
    }

    private List<GithubRepo> getRepoList(String result) {
        List<GithubRepo> repos = new ArrayList<>();
        try{
            JSONArray jList= new JSONArray(result);
            int size = jList.length();
            for (int i = 0; i < size; i++){
                JSONObject jObject = jList.getJSONObject(i);
                String jTitle = jObject.getString("name");
                String jDescription = jObject.getString("description");
                String jUpdatedAt = jObject.getString("updated_at");
                GithubRepo newRepo = new GithubRepo(jTitle, jDescription, jUpdatedAt);
                repos.add(newRepo);
            }
            return repos;
        } catch (JSONException e) {

            // Handle if user doesn't exist.
            UserNotFoundFragment userNotFoundFragment = new UserNotFoundFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_main, userNotFoundFragment).commit();
            e.printStackTrace();
            return repos;
        }
    }

    public void setUsername(String userName){
        this.userName = userName;
    }
}
