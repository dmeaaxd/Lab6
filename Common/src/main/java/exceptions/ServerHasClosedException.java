package exceptions;

import сoloringText.ColorClass;

public class ServerHasClosedException extends Exception{
    public ServerHasClosedException() {
        System.out.println(ColorClass.red + "Сервер недоступен" + ColorClass.reset);
    }
}
