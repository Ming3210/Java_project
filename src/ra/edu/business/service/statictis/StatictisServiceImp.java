package ra.edu.business.service.statictis;

import ra.edu.business.dao.statictis.StatictisDAO;
import ra.edu.business.dao.statictis.StatictisDAOImp;

import java.util.Map;

public class StatictisServiceImp implements StatictisService{
    private final StatictisDAO statictisDAO;

    public StatictisServiceImp() {
        statictisDAO = new StatictisDAOImp();
    }

    @Override
    public Map<String, Integer> statisticCourseByStudent() {
        return statictisDAO.statisticCourseByStudent();
    }

    @Override
    public Map<String, Integer> statisticCourseTop5HighestRegisted() {
        return statictisDAO.statisticCourseTop5HighestRegisted();
    }

    @Override
    public Map<String, Integer> statisticCourseWith10StudentsOrHigher() {
        return statictisDAO.statisticCourseWith10StudentsOrHigher();
    }


}
