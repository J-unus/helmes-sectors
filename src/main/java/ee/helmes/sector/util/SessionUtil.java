package ee.helmes.sector.util;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@UtilityClass
public class SessionUtil {

    public Optional<Long> getInputDataId(HttpSession session) {
        Object inputDataId = session.getAttribute(AttributeName.INPUT_DATA_ID);

        if (inputDataId instanceof Long) {
            return Optional.of((Long) inputDataId);
        }

        return Optional.empty();
    }

    public void setInputDataId(HttpSession session, Long inputDatId) {
        session.setAttribute(AttributeName.INPUT_DATA_ID, inputDatId);
    }
}
