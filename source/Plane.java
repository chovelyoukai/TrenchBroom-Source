public class Plane
{
    public String material;
    public int id, lightMapScale, smoothingGroups;
    public float x1, y1, z1, x2, y2, z2, x3, y3, z3;
    public float ux, uy, uz, uOff, uScale, vx, vy, vz, vOff, vScale;
    public float rotation;
    
    public Plane()
    {
        material = new String();
        lightMapScale = 128;
        smoothingGroups = 0;
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
