package cl.telematica.android.certamen2.presenters.contract;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Erlend on 01.11.2016.
 */

public interface ListPresenter {
    public void inflateList(final RecyclerView recyclerView, final String userName);
}
