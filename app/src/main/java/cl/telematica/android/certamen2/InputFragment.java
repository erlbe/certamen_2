package cl.telematica.android.certamen2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Erlend on 30.09.2016.
 */

public class InputFragment extends Fragment {

    Button button;
    EditText user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_input, container, false);

        button = (Button)rootView.findViewById(R.id.button);
        user = (EditText)rootView.findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ListFragment listFragment = new ListFragment();
                listFragment.setUsername(user.getText().toString());
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.activity_main, listFragment).commit();
            }
        });

        return rootView;
    }
}
