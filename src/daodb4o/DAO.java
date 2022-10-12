package daodb4o;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

public abstract class DAO<T> implements DAOInterface<T> {
    protected static ObjectContainer manager;

    public static void open() {
        if (manager == null)
            manager = Util.db4oUtil();
    }

    public static void close() {
        if (manager != null && !manager.ext().isClosed()) {
            manager.close();
            manager = null;
        }
    }

    public void create(T obj) {
        manager.store(obj);
    }

    public abstract T read(Object chave);

    public T update(T obj) {
        manager.store(obj);
        return obj;
    }

    public void delete(T obj) {
        manager.delete(obj);
    }

    public static void refresh(Object obj) {
        manager.ext().refresh(obj, Integer.MAX_VALUE);
    }

    @SuppressWarnings("unchecked")
    public List<T> readAll() {
        manager.ext().purge();
        Class<T> classe = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        Query q = manager.query();
        q.constrain(classe);
        return (List<T>) q.execute();
    }

    public void deleteAll() {
        for (T obj : readAll())
            delete(obj);
    }

    public static void begin() {}

    public static void commit() {
        manager.commit();
    }

    public static void rollback() {
        manager.rollback();
    }

    @SuppressWarnings("unchecked")
    public int gerarId() {
        Class<T> classe = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
                
        List<T> lista = readAll();
        if (lista.size() == 0)
            return 1;
        else {
            try {
                Query q = manager.query();
                q.constrain(classe);
                q.descend("id").orderDescending();
                List<T> resultados = q.execute();
                T ultimo = resultados.get(0);
                Field atributo = classe.getDeclaredField("id");
                atributo.setAccessible(true);
                return (int) atributo.get(ultimo) + 1;
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("classe " + classe.getName()
                        + "- nao possui atributo id");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("classe " + classe.getName()
                        + "- atributo id inacessivel");
            }
        }
    }
}
