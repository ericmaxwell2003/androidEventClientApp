package event.credible.software.eventclientapp.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmAdapter<T extends RealmObject> extends RealmBaseAdapter<T> {
    protected RealmResults<T> mResults;
    private Realm realm;

    public RealmAdapter(Context context, Realm realm, boolean automaticUpdate) {
        super(context, null, automaticUpdate);
        this.realm = realm;
    }

    @Override
    public int getCount() {
        return mResults != null ? mResults.size() : 0;
    }

    public T getItem(int position) {
        return mResults != null ? mResults.get(position) : null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setResults(RealmResults<T> results) {
        mResults = results;
        notifyDataSetChanged();
    }
}
