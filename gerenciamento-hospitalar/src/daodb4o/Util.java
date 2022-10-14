package daodb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Atendimento;

public class Util {

	public static ObjectContainer db4oUtil(){
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration();
		
		config.common().objectClass(Atendimento.class).cascadeOnDelete(false);
		config.common().objectClass(Atendimento.class).cascadeOnUpdate(false);
		config.common().objectClass(Atendimento.class).cascadeOnActivate(false);
		
		config.common().objectClass(Atendimento.class).objectField("protocolo").indexed(true);
		
		config.common().objectClass(Atendimento.class).updateDepth(5);
		config.common().objectClass(Atendimento.class).minimumActivationDepth(5);
		
		ObjectContainer manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}
	
}
