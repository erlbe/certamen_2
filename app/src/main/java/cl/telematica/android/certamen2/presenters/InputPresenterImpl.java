package cl.telematica.android.certamen2.presenters;

import android.app.Activity;
import android.app.FragmentManager;

import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.fragments.ListFragment;
import cl.telematica.android.certamen2.presenters.contract.InputPresenter;
import cl.telematica.android.certamen2.views.InputView;

/**
 * Created by Erlend on 01.11.2016.
 */

public class InputPresenterImpl implements InputPresenter{
    private Activity mContext;
    private InputView mInputView;

    public InputPresenterImpl(Activity mContext, InputView mInputView) {
        this.mContext = mContext;
        this.mInputView = mInputView;
    }

    public void doOnClick() {
        ListFragment listFragment = new ListFragment();
        listFragment.setUsername(mInputView.getInputText());
        FragmentManager fragmentManager = mContext.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_main, listFragment).commit();
    }
}
