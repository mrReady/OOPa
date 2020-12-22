package data.exceptions;

import java.io.IOException;

public class IOErrorHandler implements ConsoleErrorHandler {
    @Override
    public boolean handle(Throwable e) {
        if (e instanceof IOException) {
            System.out.println(e.getLocalizedMessage());
            return true;
        }

        return false;
    }
}
