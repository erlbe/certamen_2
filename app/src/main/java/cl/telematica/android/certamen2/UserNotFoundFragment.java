package cl.telematica.android.certamen2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Erlend on 30.09.2016.
 */

public class UserNotFoundFragment extends Fragment{

    public UserNotFoundFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_not_found, container, false);
        return rootView;
    }
}
