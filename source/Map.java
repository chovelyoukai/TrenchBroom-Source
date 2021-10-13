import java.util.*;
import java.io.*;

public class Map
{
	public ArrayList<Entity> entities;
	public String mapName;
	int entId, brushId, planeId;
	int brushCount, planeCount, entCount;
	boolean standardFormat;
	
	public Map(String mapName, boolean format)
	{
		entities = new ArrayList<Entity>();
		this.mapName = mapName;
		entId = brushId = planeId = 1;
		brushCount = planeCount = entCount = 0;
		standardFormat = format;
	}
	
	public boolean parseMap()
	{
		File mapFile = new File(mapName);
		Entity entity = null;
		Brush brush = null;
		Plane plane = null;
		KeyValue kv = null;
		boolean shouldAdd;
		Scanner mapScanner;
		try
		{
			mapScanner = new Scanner(mapFile);
		}
		catch (FileNotFoundException e)
		{
			return false;
		}

		String line;
		int level = 0;
		while (mapScanner.hasNextLine())
		{
			line = mapScanner.nextLine();
			line = line.replaceAll("\\s+", " ");
			line = line.replaceAll("^\\s+", "");
			if (line.startsWith("//"))
			{
				continue;
			}

			if (line.startsWith("{"))
			{
				if (level == 0)
				{
					entity = new Entity(entCount);
					if (entCount == 0)
					{
						entity.isWorld = true;
					}
					entCount++;
				}
				else
				{
					brushCount++;
					brush = new Brush();
					brush.id = brushCount;
				}
				level++;
			}
			else if (line.startsWith("}"))
			{
				if (level == 1)
				{
					entities.add(entity);
				}
				else
				{
					entity.brushes.add(brush);
				}
				level--;
			}
			else if (line.startsWith("("))
			{
				planeCount++;
				plane = new Plane(standardFormat);
				plane.parsePlane(line, planeCount);
				if (brush != null)
				{
					brush.planes.add(plane);
				}

			}
			else if (line.startsWith("\""))
			{
				kv = new KeyValue();
				shouldAdd = kv.parseKeyValue(line);
				if (shouldAdd && entity != null)
				{
					entity.keyValues.add(kv);
				}
			}
		}

		mapScanner.close();
		return true;
	}

	void setLightMapScale(int lightMapScale)
	{
		for (Entity e : entities)
		{
			for (Brush b : e.brushes)
			{
				for (Plane p : b.planes)
				{
					p.lightMapScale = lightMapScale;
				}
			}
		}
	}
	
	public void printMap()
	{
		for (Entity e : entities)
		{
			e.print();
		}
	}
}
