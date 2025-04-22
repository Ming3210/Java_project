package ra.edu.business.dao.statictis;

import java.util.Map;

public interface StatictisDAO {
    Map<String, Integer> statisticCourseByStudent();
    Map<String, Integer> statisticCourseTop5HighestRegisted();
    Map<String, Integer> statisticCourseWith10StudentsOrHigher();

}
