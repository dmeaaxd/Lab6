package commands;

import lombok.Getter;
import сoloringText.ColorClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataClients implements Serializable {
    @Getter
    private final String command;

    @Getter
    private final String[] args;

    public DataClients(String command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public byte[] getBytes()  {
        byte[] serializedObj = {};
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            serializedObj = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (NullPointerException e) {
            ColorClass.colorPrintln(ColorClass.RED, "Ошибка! NullPointerException");
        } catch (IOException e) {
            ColorClass.colorPrintln(ColorClass.RED, "Не удалось перевести сообщение сервера в байты");
        }
        return serializedObj;
    }
}
