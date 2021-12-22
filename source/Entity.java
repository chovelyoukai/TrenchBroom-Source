import java.util.*;

public class Entity
{
	public boolean isWorld;
	public ArrayList<KeyValue> keyValues;
	public ArrayList<KeyValue> connections;
	public ArrayList<Brush> brushes;
	
	public Entity(int id)
	{
		keyValues = new ArrayList<KeyValue>();
		connections = new ArrayList<KeyValue>();
		brushes = new ArrayList<Brush>();
		KeyValue kvId = new KeyValue();
		kvId.parseKeyValue("\"id\" \"" + id + "\"");
		keyValues.add(kvId);
		isWorld = false;
	}
	
	public void print()
	{
		if (isWorld)
		{
			System.out.println("world");
		}
		else
		{
			System.out.println("entity");
		}
		
		System.out.println("{");
		
		for (KeyValue kv : keyValues)
		{
			kv.print();
		}

		if (connections.size() != 0)
		{
			System.out.println("\tconnections");
			System.out.println("\t{");
			for (KeyValue conn : connections)
			{
				System.out.print("\t");
				conn.print();
			}
			System.out.println("\t}");
		}
		
		for (Brush b : brushes)
		{
			b.print();
		}
		
		System.out.println("}");
	}
}
