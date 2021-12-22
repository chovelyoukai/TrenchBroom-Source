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
		boolean skipping = false;
		while (mapScanner.hasNextLine())
		{
			line = mapScanner.nextLine();
			line = line.replaceAll("\\s+", " ");
			line = line.replaceAll("^\\s+", "");
			if (line.startsWith("//"))
			{
				continue;
			}

			if (line.startsWith("{") && !skipping)
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
				if (skipping)
				{
					skipping = false;
					continue;
				}
				if (level == 1)
				{
					entities.add(entity);
				}
				else
				{
					if (brush != null)
					{
						entity.brushes.add(brush);
					}
				}
				level--;
			}
			else if (line.startsWith("(") && !skipping)
			{
				planeCount++;
				plane = new Plane(standardFormat);
				plane.parsePlane(line, planeCount);
				if (brush != null)
				{
					brush.planes.add(plane);
				}

			}
			else if (line.startsWith("\"") && !skipping)
			{
				kv = new KeyValue();
				int shouldAdd = kv.parseKeyValue(line);
				if (entity != null)
				{
					if (shouldAdd == 1)
					{
						entity.keyValues.add(kv);
					}
					else if (shouldAdd == 2)
					{
						entity.connections.add(kv);
					}
				}
			}
			else if (line.startsWith("patchDef"))
			{
				brushCount--;
				brush = null;
				skipping = true;
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
