package cl.telematica.android.certamen2.presenters;

import android.app.Activity;
import android.app.FragmentManager;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.adapters.GithubRepoAdapter;
import cl.telematica.android.certamen2.connection.HttpServerConnection;
import cl.telematica.android.certamen2.fragments.UserNotFoundFragment;
import cl.telematica.android.certamen2.models.GithubRepo;
import cl.telematica.android.certamen2.presenters.contract.ListPresenter;
import cl.telematica.android.certamen2.views.ListView;

/**
 * Created by Erlend on 01.11.2016.
 */

public class ListPresenterImpl implements ListPresenter{
    private Activity mContext;
    private RecyclerView.LayoutManager mLayoutManager;
    private ListView mListView;

    private RecyclerView.Adapter adapter;

    public ListPresenterImpl(Activity mContext, RecyclerView.LayoutManager mLayoutManager, ListView mListView){
        this.mContext = mContext;
        this.mLayoutManager = mLayoutManager;
        this.mListView = mListView;
    }

    public void inflateList(final RecyclerView recyclerView, final String userName) {
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
            FragmentManager fragmentManager = mContext.getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_main, userNotFoundFragment).commit();
            e.printStackTrace();
            return repos;
        }
    }
}
