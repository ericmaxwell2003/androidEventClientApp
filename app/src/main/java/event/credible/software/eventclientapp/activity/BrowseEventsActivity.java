package event.credible.software.eventclientapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import event.credible.software.eventclientapp.R;
import event.credible.software.eventclientapp.activity.adapter.EventAdapter;
import event.credible.software.eventclientapp.activity.helper.RoboAppCompatActivity;
import event.credible.software.eventclientapp.domain.Event;
import event.credible.software.eventclientapp.remote.EventService;
import event.credible.software.eventclientapp.remote.TokenHolder;
import event.credible.software.eventclientapp.remote.dto.EventDto;
import io.realm.Realm;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_browse_events)
public class BrowseEventsActivity extends RoboAppCompatActivity  {

    private static final String TAG = "BrowseEventsActivity";

    private Realm realm;
    private EventAdapter adapter;
    private RecyclerView recycler;
    private boolean isFetching;

    private ProgressDialog progressDialog;
    @Inject private EventService eventService;
    @Inject private TokenHolder tokenHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this, R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching New Events...");

        realm = Realm.getInstance(this);
        adapter = new EventAdapter(this, realm, true);
        adapter.setResults(realm.where(Event.class).findAll());

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_browse_event, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                if(!isFetching) { // don't fetch if in the middle of fetching.
                    FetchEventsTask fetchEventsTask = new FetchEventsTask();
                    fetchEventsTask.execute();
                }
                return true;
            case R.id.action_logout:
                tokenHolder.setToken(null);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class FetchEventsTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            isFetching = true;
            showProgress();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean wasSuccess = true;
            try {
                List<EventDto> events = eventService.fetchEvents();
                try(Realm r = Realm.getInstance(BrowseEventsActivity.this)) {
                    r.beginTransaction();
                    r.clear(Event.class);
                    for(EventDto dto : events) {
                        Event e = new Event();
                        e.setGuid(dto.getGuid());
                        e.setDetails(dto.getDetails());
                        e.setSummary(dto.getSummary());
                        r.copyToRealm(e);
                    }
                    r.commitTransaction();
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                wasSuccess = false;
            }
            return wasSuccess;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            hideProgress();
            isFetching = false;
            if(success) {
                adapter.setResults(realm.where(Event.class).findAll());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(BrowseEventsActivity.this, "Error Fetching Events", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showProgress() {
        progressDialog.show();
    }

    private void hideProgress() {
        progressDialog.dismiss();
    }


}