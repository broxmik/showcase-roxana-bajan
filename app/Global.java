import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.ApplicationModule;
import play.Application;
import play.GlobalSettings;
import play.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Roxana
 * @version 1.0
 */
public class Global extends GlobalSettings {

    private static final Injector injector = Guice.createInjector(new ApplicationModule());

    private static final Timer timer = new Timer("Timer-Check-App-Status", true);

    @Override
    public void onStart(Application application) {
        super.onStart(application);

        // start a timer
        timer.scheduleAtFixedRate(new ContactTimerTask(), 60000L, 240000L);
    }

    @Override
    public void onStop(Application application) {
        super.onStop(application);

        // stop timer
        timer.cancel();
    }

    @Override
    public <T> T getControllerInstance(Class<T> aClass) throws Exception {
        return injector.getInstance(aClass);
    }

    private static class ContactTimerTask extends TimerTask {
        @Override
        public void run() {
            URLConnection myURLConnection = null;
            try {
                URL myURL = new URL("http://roxanabajan.herokuapp.com/");
                myURLConnection  = myURL.openConnection();
                myURLConnection.connect();
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
                    String inputLine = in.readLine();
                    if (inputLine != null) {
                        Logger.trace("Retrieved content. Site is online.");
                    }
                }finally {
                    if (in != null) {
                        in.close();
                    }
                }

            } catch (IOException e) {
                // openConnection() failed
                Logger.error(e.getMessage());
            }
        }
    }
}
