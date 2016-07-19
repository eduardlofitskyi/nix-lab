package lab8;

import interfaces.task8.SerializableUtils;

import java.io.*;

public class SerializableUtilsImpl implements SerializableUtils{
    @Override
    public void serialize(OutputStream outputStream, Object o) {

        if (o == null) throw new NullPointerException();
        if (!(o instanceof Serializable)) throw new RuntimeException(new NotSerializableException("Class " + o.getClass() + " cannot be serializable"));

        try {
            ObjectOutputStream outputStream1 = new ObjectOutputStream(outputStream);
            outputStream1.writeObject(o);
            outputStream1.flush();
            outputStream1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(InputStream inputStream) {

        if (inputStream == null) throw new NullPointerException();

        Object o = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            o = ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return o;
    }
}
