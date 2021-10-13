public class Plane
{
	public String material;
	public int id, lightMapScale, smoothingGroups;
	public float x1, y1, z1, x2, y2, z2, x3, y3, z3;
	public float ux, uy, uz, uOff, uScale, vx, vy, vz, vOff, vScale;
	public float rotation;
	boolean standardFormat;
	
	public Plane(boolean format)
	{
		material = new String();
		lightMapScale = 128;
		smoothingGroups = 0;
		standardFormat = format;
	}
	
	public void parsePlane(String plane, int id)
	{
		this.id = id;

		plane = plane.replace("(", "");
		plane = plane.replace(")", "");
		plane = plane.replace("[", "");
		plane = plane.replace("]", "");
		plane = plane.replaceAll("\\s+", " ");
		plane = plane.replaceAll("^\\s+", "");

		String[] tokens = plane.split(" ");

		x1 = Float.parseFloat(tokens[0]);
		y1 = Float.parseFloat(tokens[1]);
		z1 = Float.parseFloat(tokens[2]);
		x2 = Float.parseFloat(tokens[3]);
		y2 = Float.parseFloat(tokens[4]);
		z2 = Float.parseFloat(tokens[5]);
		x3 = Float.parseFloat(tokens[6]);
		y3 = Float.parseFloat(tokens[7]);
		z3 = Float.parseFloat(tokens[8]);
		material = tokens[9];

		if (standardFormat)
		{
			float nx, ny, nz;
			float sx, sy, sz;
			float tx, ty, tz;

			uOff = Float.parseFloat(tokens[10]);
			vOff = Float.parseFloat(tokens[11]);
			rotation = Float.parseFloat(tokens[12]);
			uScale = Float.parseFloat(tokens[13]);
			vScale = Float.parseFloat(tokens[14]);

			ux = uy = uz = vx = vy = vz = 0;

			// calculate vectors across the plane
			sx = x3 - x1;
			sy = y3 - y1;
			sz = z3 - z1;
			tx = x2 - x1;
			ty = y2 - y1;
			tz = z2 - z1;

			// calculate normal vector of plane
			nx = (sy * tz) - (sz * ty);
			ny = (sz * tx) - (sx * tz);
			nz = (sx * ty) - (sy * tx);

			// choose uv based on the maximum magnitude of the normal vector
			float m = Math.max(Math.max(nx, ny), nz);
			if (m == nx)
			{
				uy = 1;
				vz = 1;
			}
			else if (m == ny)
			{
				ux = 1;
				vz = 1;
			}
			else
			{
				ux = 1;
				vy = 1;
			}
		}
		else
		{
			ux = Float.parseFloat(tokens[10]);
			uy = Float.parseFloat(tokens[11]);
			uz = Float.parseFloat(tokens[12]);
			uOff = Float.parseFloat(tokens[13]);
			vx = Float.parseFloat(tokens[14]);
			vy = Float.parseFloat(tokens[15]);
			vz = Float.parseFloat(tokens[16]);
			vOff = Float.parseFloat(tokens[17]);
			rotation = Float.parseFloat(tokens[18]);
			uScale = Float.parseFloat(tokens[19]);
			vScale = Float.parseFloat(tokens[20]);
		}
	}
	
	public void print()
	{
		System.out.println("\t\tside");
		System.out.println("\t\t{");
		System.out.printf("\t\t\t\"id\" \"%d\"\n", id);
		System.out.printf("\t\t\t\"plane\" \"(%.2f %.2f %.2f) (%.2f %.2f %.2f) (%.2f %.2f %.2f)\"\n",
			x1, y1, z1, x2, y2, z2, x3, y3, z3);
		System.out.printf("\t\t\t\"material\" \"%s\"\n", material.toUpperCase());
		System.out.printf("\t\t\t\"uaxis\" \"[%.2f %.2f %.2f %.2f] %.2f\"\n", ux, uy, uz, uOff, uScale);
		System.out.printf("\t\t\t\"vaxis\" \"[%.2f %.2f %.2f %.2f] %.2f\"\n", vx, vy, vz, vOff, vScale);
		System.out.printf("\t\t\t\"rotation\" \"%.2f\"\n", rotation);
		System.out.printf("\t\t\t\"lightmapscale\" \"%d\"\n", lightMapScale);
		System.out.printf("\t\t\t\"smoothing_groups\" \"%d\"\n", smoothingGroups);
		System.out.println("\t\t}");
	}
}
