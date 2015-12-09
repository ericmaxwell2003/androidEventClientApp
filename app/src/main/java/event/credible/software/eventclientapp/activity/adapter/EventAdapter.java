package event.credible.software.eventclientapp.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import event.credible.software.eventclientapp.R;
import event.credible.software.eventclientapp.domain.Event;
import event.credible.software.eventclientapp.remote.dto.EventDto;
import io.realm.Realm;
import io.realm.RealmResults;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    private LayoutInflater layoutInflater;
    private List<EventDto> events = new ArrayList<>();

    public EventAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.event_item, parent, false);
        EventHolder eventHolder = new EventHolder(view);
        return eventHolder;
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        EventDto event = events.get(position);
        holder.setDetails(event.getDetails());
        holder.setSummary(event.getSummary());
    }

    public void prependResults(List<EventDto> results) {
        this.events.addAll(0, results);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return events == null ? 0 : events.size();
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
