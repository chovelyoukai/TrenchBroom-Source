public class MapToVmf
{
    public static void main(String[] args)
    {
        int lightMapScale = 128;
        String mapFile = "";
        boolean readMapFile = false;

        if (args.length == 0)
        {
            System.out.println("Please specify map!");
            return;
        }

        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals("-lightmapscale"))
            {
                lightMapScale = Integer.parseInt(args[i + 1]);
                i++;
            }
            else
            {
                mapFile = args[i];
                readMapFile = true;
            }
        }

        if (!readMapFile)
        {
            System.out.println("Please specify map!");
            return;
        }
        
        Map map = new Map(mapFile);
        
        boolean success = map.parseMap();
        map.setLightMapScale(lightMapScale);
        
        if (!success)
        {
            System.out.println("Error!");
            return;
        }
        
        map.printMap();
    }
}
