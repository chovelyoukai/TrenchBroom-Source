public class KeyValue
{
    public String key, value;
    
    public KeyValue()
    {
        key = new String();
        value = new String();
    }
    
    public boolean parseKeyValue(String keyValue)
    {
        keyValue = keyValue.replace("\" \"", "\"");
        String[] tokens = keyValue.split("\"");
        key = tokens[1];
        value = tokens[2];
        
        if (key.equals("mapversion") || key.startsWith("_tb"))
        {
            return false;
        }
        
        return true;
    }
    
    public void print()
    {
        System.out.printf("\t\"%s\" \"%s\"\n", key, value);
    }
}
