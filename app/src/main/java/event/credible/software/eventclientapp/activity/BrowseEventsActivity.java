package event.credible.software.eventclientapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;

import event.credible.software.eventclientapp.R;
import event.credible.software.eventclientapp.activity.adapter.EventAdapter;
import event.credible.software.eventclientapp.activity.helper.RoboAppCompatActivity;
import event.credible.software.eventclientapp.domain.Event;
import io.realm.Realm;
import io.realm.RealmResults;

public class BrowseEventsActivity extends RoboAppCompatActivity  {

    private Realm realm;
    private EventAdapter adapter;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_events);
        realm = Realm.getInstance(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        if(realm.where(Event.class).findAll().size() == 0) {
            realm.beginTransaction();
            Event event1 = new Event(); event1.setSummary("Foo"); event1.setGuid("1"); event1.setDetails("Foo Detail"); realm.copyToRealm(event1);
            Event event2 = new Event(); event2.setSummary("Foo"); event2.setGuid("2"); event2.setDetails("Foo Detail");
            realm.copyToRealm(event2);
            realm.commitTransaction();
        }
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventAdapter(this, realm, true);
        adapter.setResults(realm.where(Event.class).findAll());
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}