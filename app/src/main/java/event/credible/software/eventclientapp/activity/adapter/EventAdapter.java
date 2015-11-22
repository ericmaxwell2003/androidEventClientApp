package event.credible.software.eventclientapp.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import event.credible.software.eventclientapp.R;
import event.credible.software.eventclientapp.domain.Event;
import io.realm.Realm;
import io.realm.RealmResults;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    private LayoutInflater mInflater;
    private RealmAdapter<Event> realmBaseAdapter;

    public EventAdapter(Context context, Realm realm, boolean automaticUpdate) {
        realmBaseAdapter = new RealmAdapter<Event>(context, realm, automaticUpdate);
        mInflater = LayoutInflater.from(context);
    }

    public Event getItem(int position) {
        return realmBaseAdapter.getItem(position);
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.event_item, parent, false);
        EventHolder eventHolder = new EventHolder(view);
        return eventHolder;
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        Event event = realmBaseAdapter.getItem(position);
        holder.setDetails(event.getDetails());
        holder.setSummary(event.getSummary());
    }

    public void setResults(RealmResults<Event> results) {
        realmBaseAdapter.setResults(results);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return realmBaseAdapter.getCount();
    }

    public static class EventHolder extends RecyclerView.ViewHolder {
        private TextView summary;
        private TextView details;

        public EventHolder(View itemView) {
            super(itemView);
            summary = (TextView) itemView.findViewById(R.id.summary);
            details = (TextView) itemView.findViewById(R.id.details);
        }

        public void setSummary(String text) {
            summary.setText(text);
        }

        public void setDetails(String text) {
            details.setText(text);
        }
    }
}