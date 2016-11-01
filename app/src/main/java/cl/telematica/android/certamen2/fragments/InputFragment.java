package cl.telematica.android.certamen2.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.presenters.InputPresenterImpl;
import cl.telematica.android.certamen2.presenters.contract.InputPresenter;
import cl.telematica.android.certamen2.views.InputView;


/**
 * Created by Erlend on 30.09.2016.
 */

public class InputFragment extends Fragment implements InputView{

    private Button button;
    private EditText user;

    private InputPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_input, container, false);

        button = (Button)rootView.findViewById(R.id.button);
        user = (EditText)rootView.findViewById(R.id.editText);

        this.mPresenter = new InputPresenterImpl(getActivity(), this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPresenter.doOnClick();
            }
        });
        return rootView;
    }

    @Override
    public String getInputText() {
        return user.getText().toString();
    }
}
