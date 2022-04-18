package common.utils;

import java.io.Serializable;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

import static java.lang.System.*;

public class Input implements Serializable {
    public static int getInt(String message){
        FunctionalInputGetter<Integer> getter = new FunctionalInputGetter<>();
        return getter.parseSomething((x) -> {
            try {
                Integer result = Integer.parseInt(x);
                return Optional.of(result);
            } catch (NumberFormatException ignored){
                return Optional.empty();

            }
        },message);
    }


    public static String getString(String message){
        FunctionalInputGetter<String> getter = new FunctionalInputGetter<>();
        return getter.parseSomething((x) -> {
            if (!x.contains(",") && !(x.isEmpty())) return Optional.of(x);
            else return Optional.empty();
        },message);

    }

}



class FunctionalInputGetter<T> implements Serializable{
    public T parseSomething(Function<String,Optional<T>> input,String message) {
        boolean isRight = false;
        Scanner scanner = new Scanner(in);
        Optional<T> result = Optional.empty();

        while (!isRight){
            out.println(message);
            String tmp = scanner.nextLine();
            result= input.apply(tmp);
            if (result.isPresent()) {
                isRight = true;
            }
        }
        return result.get();
    }
}
