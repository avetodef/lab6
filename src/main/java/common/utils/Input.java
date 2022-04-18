package common.utils;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

public class Input implements Serializable {
    public String getString(String message){
        FunctionalInputGetter<String> getter = new FunctionalInputGetter<>();
        return getter.parseSomething((x) -> {
            try {
                Integer result = Integer.parseInt(x);
                return Optional.of(result);
            } catch (NumberFormatException ignored){
                return Optional.empty();

            }
        },message);
    }

    class FunctionalInputGetter<T> implements Serializable{
        public T parseSomething(Function)
    }

}
