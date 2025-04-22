package ra.edu.business.service.statictis;

import java.util.Map;

public interface StatictisService {
    Map<String, Integer> statisticCourseByStudent();
    Map<String, Integer> statisticCourseTop5HighestRegisted();
    Map<String, Integer> statisticCourseWith10StudentsOrHigher();

}
