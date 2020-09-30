package ch.sowatec.pg.notenapp.Database;

import android.os.AsyncTask;

public class DatabaseAdapter extends AsyncTask<Void, Void, Void> {
    private Runnable action;
    private Runnable action_finally;

    public DatabaseAdapter(Runnable action) {
        this.action = action;
    }

    public DatabaseAdapter(Runnable action, Runnable postAction) {
        this.action = action;
        this.action_finally = postAction;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        action.run();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (action_finally != null)
            action_finally.run();
        super.onPostExecute(aVoid);
    }
}
