package apis.weatherapp.threading.services;

import apis.weatherapp.threading.models.ThreadControl;
import apis.weatherapp.threading.models.ThreadControlDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadControlService {
    @Autowired
    private ThreadControlDAO threadControlDAO;

    public void save(ThreadControl threadControl) {
        threadControlDAO.save(threadControl);
    }

    public List<ThreadControl> findAll() {
        return threadControlDAO.findAll();
    }

}
