package apis.weatherapp.threading.views;

import apis.weatherapp.threading.models.ThreadControl;
import apis.weatherapp.threading.services.ThreadControlService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ThreadControlView {
    @Autowired
    private ThreadControlService threadControlService;

    public List<ThreadControl> getThreadRequests() {
        return threadControlService.findAll();
    }
}
