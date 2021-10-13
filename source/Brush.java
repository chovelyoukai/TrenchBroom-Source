import java.util.*;

public class Brush
{
	public int id;
	public ArrayList<Plane> planes;
	
	public Brush()
	{
		planes = new ArrayList<Plane>();
	}
	
	public void print()
	{
		System.out.println("\tsolid\n\t{");
		System.out.printf("\t\t\"id\" \"%d\"\n", id);
		for (Plane p : planes)
		{
			p.print();
		}
		System.out.println("\t}");
	}
}
