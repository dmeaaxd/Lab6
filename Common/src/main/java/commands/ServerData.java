package commands;

import lombok.Getter;
import сoloringText.ColorClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class ServerData implements Serializable {
    @Getter
    private ArrayList<String> message;
    @Getter
    private HashSet<CommandData> commandDataHashSet;
    public ServerData(HashSet<CommandData> hashSet,ArrayList<String> message){
        this.commandDataHashSet = hashSet;
        this.message = message;
    }
    public ServerData(ArrayList<String> message){
        this.message = message;
    }

    public byte[] getBytes()  {
        byte[] serializedObj = {};
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            serializedObj = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (NullPointerException e) {
            System.out.println(ColorClass.red + "Ошибка! NullPointerException" + ColorClass.reset);
        } catch (IOException e) {
            System.out.println(ColorClass.red + "Не удалось перевести сообщение сервера в байты" + ColorClass.reset);
        }
        return serializedObj;
    }
}
